package ansible2;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.ylink.ansible.common.SFTPUtil;

public class FTPTest {

	@Test
	public void test() {
		
		ChannelSftp sftp = null;
		Session session = null;
		try {
			session = SFTPUtil.connect("172.168.65.88", 22, "root", "root1234", 30000);
			Channel channel = session.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			/*SFTPUtil.upload(destDir, srcfile.getAbsolutePath(), sftp);*/
			SFTPUtil.download(null, "/var/lib/awx/projects/hello_world/hello.yml","D:/helloTest.yml", sftp);
			//SFTPUtil.upload("/var/lib/awx/projects/test", "D:/helloTest.yml", sftp);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(sftp != null)sftp.disconnect();
			if(session != null)session.disconnect();
		}
	}
	/*@Test
	public void test2() throws IOException {
		SFTPUtil.replaceFileStr("D:/hello.yml", "D:/helloTest.yml");
		
	}*/
	
	@Test
	public void testDate() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSS");
		System.out.println(format.format(date));
		System.out.println(date.getTime());
		System.out.println(date.getDate());
		System.out.println(date.getYear());
		System.out.println(date.getHours());
		System.out.println(date.getDay());
		System.out.println(date.getMinutes());
		System.out.println(date.getSeconds());
		System.out.println(date.getTimezoneOffset());
		
	}
	
	@Test
	public void testexe() {
		String s="cp -r Templates test01";
		String a="ls";
		String b="cd test";
		String d="cd /var/lib/awx/projects;ls";
		
		SFTPUtil.exeCommand("172.168.65.88", 22, "root", "root1234", 30000, d);
	}
	
}

