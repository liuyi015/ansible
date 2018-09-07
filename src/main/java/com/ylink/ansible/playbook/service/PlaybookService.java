package com.ylink.ansible.playbook.service;



import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.ylink.ansible.common.FileUtil;
import com.ylink.ansible.common.SFTPUtil;
import com.ylink.ansible.playbook.pojo.Playbook;

@Service
public class PlaybookService {
	//模板存放地址
	@Value("${PLAYBOOK_PATH}")
	private String PLAYBOOK_PATH;
	//项目存放地址
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
		List list=null;
		String command="ls /var/lib/awx/playbookTemplates/";
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

	public Boolean addPlaybook(Playbook playbook, HttpServletRequest request) {
		
		String filename = playbook.getPlaybook();
		String srcFile =PLAYBOOK_PATH+playbook.getFolder()+"/"+filename;   //playbook模板的服务器地址
		
		String saveFilePath =request.getServletContext().getRealPath("download");
		String saveFile =saveFilePath+File.separator+filename;
		
		new Date().getYear();
		String directory=PROJECTS_PATH+playbook.getFolder();    //要上传的playbook的根路径
		String newFile=request.getServletContext().getRealPath("upload")+File.separator+filename;
		
		Session session = null;
		ChannelSftp sftp = null;
		Boolean upload =false;
		try {
			session = SFTPUtil.connect(HOST, PROT, USER, PASSWORD,TIMEOUT);
			Channel channel = session.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			//1、下载playbook模板
			Boolean rs = SFTPUtil.download(srcFile, saveFilePath, sftp);
			if(!rs) {
				return false;
			}
			//2、编辑playbook模板
			Boolean str = FileUtil.replaceFileStr(saveFile, newFile, playbook.getParameter());
			if(!str) {
				return false;
			}
			//3、上传playbook
			upload=SFTPUtil.upload(directory, newFile, sftp);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(sftp != null)sftp.disconnect();
			if(session != null)session.disconnect();
		}
		return upload;
	}

}
