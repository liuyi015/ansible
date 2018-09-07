package ansible2;

import java.io.File;
import java.io.IOException;

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
	@Test
	public void test2() throws IOException {
		SFTPUtil.replaceFileStr("D:/hello.yml", "D:/helloTest.yml");
	}
}
