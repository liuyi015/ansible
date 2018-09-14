package com.ylink.ansible.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
 
/**
 * SFTP帮助类(使用jsch)
 * @author liuyi
 *
 */
public class SFTPUtil {
	
	private static Log log = LogFactory.getLog(SFTPUtil.class);
	
	
	/**
	 * 连接sftp服务器
	 * @param host 远程主机ip地址
	 * @param port sftp连接端口，null 时为默认端口
	 * @param user 用户名
	 * @param password 密码
	 * @return
	 * @throws JSchException 
	 */
	public static Session connect(String host, Integer port, String user, String password,Integer timeout) throws JSchException{
		Session session = null;
		try {
			JSch jsch = new JSch();
			if(port != null){
				session = jsch.getSession(user, host, port.intValue());
			}else{
				session = jsch.getSession(user, host);
			}
			session.setPassword(password);
			//设置第一次登陆的时候提示，可选值:(ask | yes | no)，StrictHostKeyChecking选项是SSH客户端是否接受SSH服务端的hostkey
			session.setConfig("StrictHostKeyChecking", "no");
			//30秒连接超时
			session.connect(timeout);
		} catch (JSchException e) {
			e.printStackTrace();
			System.out.println("SFTPUitl 获取连接发生错误");
			throw e;
		}
		return session;
	}
	
	/**
	 * sftp上传文件(夹)
	 * @param directory
	 * @param uploadFile
	 * @param sftp
	 * @throws SftpException 
	 */
	@SuppressWarnings("unchecked")
	public static Boolean upload(String directory, String uploadFile, ChannelSftp sftp) throws SftpException{
		System.out.println("sftp upload file [directory] : "+directory);
		System.out.println("sftp upload file [uploadFile] : "+ uploadFile);
		File file = new File(uploadFile);
		if(!file.exists()){
			System.out.println("文件不存在");
			return false;
		}
		//这里有点投机取巧，因为ChannelSftp无法去判读远程linux主机的文件路径,无奈之举
		try {
			Vector<LsEntry> content = sftp.ls(directory);
			if(content == null){
				sftp.mkdir(directory);
			}
		} catch (SftpException e) {
			sftp.mkdir(directory);
		}
		
		if(!file.isFile()){
			return false;
		}
		//进入目标路径
		try {
			sftp.cd(directory);
			InputStream ins = new FileInputStream(file);
			//中文名称的
			sftp.put(ins, new String(file.getName().getBytes(),"UTF-8"));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("上传失败");
			return false;
		} 
		
	}
	
	public static Boolean download(String srcFile, String saveFile, ChannelSftp sftp) {
		return download(null, srcFile, saveFile, sftp);
	}
	/**
	 * sftp下载文件
	 * @param directory 下载文件上级目录
	 * @param srcFile 下载文件完全路径
	 * @param saveFile 保存文件路径
	 * @param sftp ChannelSftp
	 * @throws UnsupportedEncodingException
	 */
	public static Boolean download(String directory,String srcFile, String saveFile, ChannelSftp sftp){
		System.out.println("sftp download file [directory] : "+srcFile);
		File file = new File(saveFile);
		if(!file.exists()) file.mkdir();
		//文件
		if(srcFile.indexOf(".") > -1){
			try {
				//下载文件
				sftp.get(srcFile, saveFile);
				return true;
			} catch (SftpException e) {
				e.printStackTrace();
				log.debug("ChannelSftp sftp下载文件发生错误",e);
				return false;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> downloadDir(String directory, ChannelSftp sftp) throws Exception{
		System.out.println("sftp downloadDir directory [directory] : "+directory);
		List<String> list=new ArrayList<>();
		Vector<LsEntry> files = sftp.ls(directory);
		if(files==null) {
			return null;
		}
		Iterator<ChannelSftp.LsEntry> iterator = files.iterator();
		while(iterator.hasNext()) {
			ChannelSftp.LsEntry str = iterator.next();
			String filename = str.getFilename();
			 if (filename.equals(".") || filename.equals("..")) {
                 continue;
             }
			 list.add(filename);
		}
		System.out.println(list.toString());
		return list;
	}
	
	public static SSHResInfo exeCommand(String command,ChannelExec channelExec) {
		SSHResInfo rs = new SSHResInfo();
		
		try {
			System.out.println("执行语句："+command);
			channelExec.setCommand(command);
			channelExec.connect();
			
			InputStream stdStream = channelExec.getInputStream();
            InputStream errStream = channelExec.getErrStream();  
            
            String success = IOUtils.toString(stdStream,"UTF-8");
            String error = IOUtils.toString(errStream,"UTF-8");
            rs.setSuccessInfo(success);
            rs.setErrorInfo(error);
            
            if(channelExec.isClosed()) {
            	int code = channelExec.getExitStatus();
            	rs.setCode(code);
            }
            System.out.println(rs.toString());
            Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(channelExec != null)channelExec.disconnect();
		}
		return rs;
	}
	
	/*public static SSHResInfo exeCommand(String host, Integer port, String user, String password,Integer timeout,String command) {
		SSHResInfo rs = new SSHResInfo();
		ChannelExec channelExec = null;
		Session session = null;
		try {
			session = SFTPUtil.connect(host,port,user,password,timeout);
			channelExec = (ChannelExec) session.openChannel("exec");
			channelExec.setCommand(command);
			channelExec.connect();
			
			InputStream stdStream = channelExec.getInputStream();
            InputStream errStream = channelExec.getErrStream();  
            
            String success = IOUtils.toString(stdStream,"UTF-8");
            String error = IOUtils.toString(errStream,"UTF-8");
            rs.setSuccessInfo(success);
            rs.setErrorInfo(error);
            
            if(channelExec.isClosed()) {
            	int code = channelExec.getExitStatus();
            	rs.setCode(code);
            }
            System.out.println(rs.toString());
            Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(channelExec != null)channelExec.disconnect();
			if(session != null)session.disconnect();
		}
		return rs;
	}*/
}
