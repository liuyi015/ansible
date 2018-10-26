package com.ylink.ansible.playbook.service;



import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.ylink.ansible.common.FileUtil;
import com.ylink.ansible.common.SFTPUtil;
import com.ylink.ansible.common.SSHResInfo;
import com.ylink.ansible.playbook.pojo.Parameter;
import com.ylink.ansible.playbook.pojo.Playbook;

@Service
public class PlaybookService {
	/**模板文件夹存放地址 ：/var/lib/awx/playbookTemplates/ */
	@Value("${PLAYBOOK_PATH}")
	private String PLAYBOOK_PATH;
	
	/** 变量文件地址：/roles/mkusers/vars/	 */
	@Value("${varFile_path}")
	private String varFile_path;
	
	/** 变量文件名：main.yml	 */
	@Value("${varFile_name}")
	private String varFile_name;
	
	/**项目存放地址 :/var/lib/awx/projects/*/
	@Value("${PROJECTS_PATH}")
	private String PROJECTS_PATH;
	
	@Value("${USER}")
	private String USER;
	@Value("${PASSWORD}")
	private String PASSWORD;
	@Value("${PROT}")
	private Integer PROT;
	@Value("${HOST}")
	private String HOST;
	@Value("${TIMEOUT}")
	private Integer TIMEOUT;

	public List getPBTemp(String uri) {
		String path=PLAYBOOK_PATH;
		if(StringUtils.isNotEmpty(uri)) {
			path=path+uri;
		}
		return getDir(path);
	}

	public Boolean addPlaybook(Playbook playbook, HttpServletRequest request) {
		
		
		String saveFile =request.getServletContext().getRealPath("download")+File.separator+varFile_name;
		String newFile=request.getServletContext().getRealPath("upload")+File.separator+varFile_name;
		
		String tempPath=PLAYBOOK_PATH+playbook.getFolder();    //要复制的playbook的路径
		String newTempPath=PROJECTS_PATH+playbook.getPeoject_name();    //复制目的地址
		
		Session session = null;
		ChannelSftp sftp = null;
		ChannelExec channelExec = null;
		Boolean upload =false;
		try {
			//1、编辑playbook变量文件
			Boolean str = FileUtil.replaceFileStr(saveFile, newFile, playbook.getParameter());
			if(!str) {
				return false;
			}
			
			//连接Linux
			session = SFTPUtil.connect(HOST, PROT, USER, PASSWORD,TIMEOUT);
			//2、复制playbook文件夹
			channelExec =(ChannelExec) session.openChannel("exec");
			// cp -r /var/lib/awx/playbookTemplates/mkUsers /var/lib/awx/projects/testUser 
			String command="cp -r "+tempPath+" "+newTempPath;
			SSHResInfo rs = SFTPUtil.exeCommand(command, channelExec);
			if(rs==null) {
				return false;
			}else if(rs.getCode()!=0) {
				return false;
			}
			//关闭通道
			if(channelExec != null) {
				channelExec.disconnect();
			}
			
			//3、上传playbook变量文件
			String directory=newTempPath+varFile_path;
			sftp = (ChannelSftp) session.openChannel("sftp");
			sftp.connect();
			upload=SFTPUtil.upload(directory, newFile, sftp);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(sftp != null)sftp.disconnect();
			if(channelExec != null)channelExec.disconnect();
			if(session != null)session.disconnect();
		}
		return upload;
	}

	public List<Parameter> readFile(String folder,HttpServletRequest request) {
		String srcFile =PLAYBOOK_PATH+folder+varFile_path+varFile_name;   //playbook模板的服务器地址
		
		String saveFilePath =request.getServletContext().getRealPath("download");
		String saveFile =saveFilePath+File.separator+varFile_name;
		Session session = null;
		ChannelSftp sftp = null;
		List<Parameter> list=null;
		try {
			session = SFTPUtil.connect(HOST, PROT, USER, PASSWORD,TIMEOUT);
			Channel channel = session.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			/*Class cl=ChannelSftp.class;

			Field f1 =cl.getDeclaredField("server_version");
			f1.setAccessible(true);
			f1.set(sftp, 2);
			sftp.setFilenameEncoding("utf-8");*/
			//1、下载playbook变量文件
			Boolean rs = SFTPUtil.download(srcFile, saveFilePath, sftp);
			if(rs) {
				//2、读playbook变量文件
				 list=FileUtil.readFile(saveFile);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(sftp != null)sftp.disconnect();
			if(session != null)session.disconnect();
		}
		return list;
	}

	public Boolean checkProjectName(String projectName) {
		List list = this.getDir(PROJECTS_PATH);
		if(list==null) {
			return true;
		}
		int size = list.size();
		for(int i = 0;i<size;i++) {
			String name=(String) list.get(i);
			if(name.equals(projectName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取目录
	 * @param path
	 * @return
	 */
	public List getDir(String path) {
		List list=null;
		Session session = null;
		ChannelSftp sftp = null;
		try {
			session = SFTPUtil.connect(HOST, PROT, USER, PASSWORD,TIMEOUT);
			Channel channel = session.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			 list = SFTPUtil.downloadDir(path, sftp);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(sftp != null)sftp.disconnect();
			if(session != null)session.disconnect();
		}
		return list;
	}
}
