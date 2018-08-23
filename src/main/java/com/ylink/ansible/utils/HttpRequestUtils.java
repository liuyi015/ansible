package com.ylink.ansible.utils;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
 
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

/**
 * 连接API接口的工具类
 * @author liuyi
 *
 */
@SuppressWarnings("deprecation")
public class HttpRequestUtils {
	
	private static PoolingHttpClientConnectionManager cm;   //连接池
	
	private static void init() {
		if(cm ==null) {
			cm=new PoolingHttpClientConnectionManager();
			cm.setMaxTotal(50);   //整个连接池最大的连接数
			cm.setDefaultMaxPerRoute(5);    //每个路由最大的连接数，默认为2
		}
	}
	/**
	 * 通过连接池获取HttpClient
	 * 
	 * @return
	 * @throws Exception 
	 */
	private static HttpClient getHttpClient() throws Exception {
		init();
		
//		return initHttpClient(HttpClients.custom().setConnectionManager(cm).build());
		return HttpClients.custom().setConnectionManager(cm).build();
//		return new DefaultHttpClient();
	}

	
	/**
     * 发送 SSL POST 请求（HTTPS），JSON形式
     * @param apiUrl API接口URL
     * @param json JSON对象
     * @return api返回值（String）
	 * @throws Exception 
     */
	public static String sendHttpsRequestByPost(String apiUrl, Object json) throws Exception {
		return sendHttpsRequestByPostAndCookie(apiUrl, json, null);
    }
	
	/**
     * 发送 SSL POST 请求（HTTPS），JSON形式，带cookie
     * @param apiUrl API接口URL
     * @param json JSON对象
     * @return api返回值（String）
	 * @throws Exception 
     */
	public static String sendHttpsRequestByPostAndCookie(String apiUrl, Object json,Cookie cookie) throws Exception {
		String responseContent = null;
		HttpClient httpClient = ConnectionManagerUtils.getHttpClient();
        HttpPost httpPost = new HttpPost(apiUrl);
        try {
        	if(cookie!=null) {
        		String cookieStr=cookie.getName()+"="+cookie.getValue();
    	        httpPost.addHeader("Cookie", cookieStr);
        	}
        	//发生重定向时服务端返回的数据中可以看到location（需重定向的地址）
//        	httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)"); 
        	
            StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");//解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            
            long begin = System.currentTimeMillis();
            //发送请求
            HttpResponse response = httpClient.execute(httpPost);
            long end = System.currentTimeMillis();
            System.out.println("Time:"+(end-begin)+"ms");
            
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
            	 System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
                return null;
            }
            HttpEntity entity = response.getEntity(); // 获取响应实体
            if (entity == null) {
                return null;
            }
            responseContent = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            // 关闭连接,释放资源
        	httpPost.releaseConnection();
           // httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }
	/**
     * 发送 SSL PUT 请求（HTTPS），JSON形式，带cookie
     * @param apiUrl API接口URL
     * @param json JSON对象
     * @return api返回值（String）
	 * @throws Exception 
     */
	public static String sendHttpsRequestByPutAndCookie(String apiUrl, Object json,Cookie cookie) throws Exception {
		String responseContent = null;
		HttpClient httpClient = ConnectionManagerUtils.getHttpClient();
        HttpPut httpPut = new HttpPut(apiUrl);
        try {
        	if(cookie!=null) {
        		String cookieStr=cookie.getName()+"="+cookie.getValue();
        		httpPut.addHeader("Cookie", cookieStr);
        	}
        	//发生重定向时服务端返回的数据中可以看到location（需重定向的地址）
//        	httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)"); 
        	
            StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");//解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPut.setEntity(stringEntity);
            
            long begin = System.currentTimeMillis();
            //发送请求
            HttpResponse response = httpClient.execute(httpPut);
            long end = System.currentTimeMillis();
            System.out.println("Time:"+(end-begin)+"ms");
            
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
            	 System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
                return null;
            }
            HttpEntity entity = response.getEntity(); // 获取响应实体
            if (entity == null) {
                return null;
            }
            responseContent = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            // 关闭连接,释放资源
        	httpPut.releaseConnection();
           // httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }
    
    /**
     * 发送 SSL get 请求（HTTPS）,携带Cookie
     * @param apiUrl API接口URL
     * @param cookie   要发送的cookie对象
     * @return api返回值（String）
     * @throws Exception 
     */
	public static String sendHttpsRequestByGet(String apiUrl, Cookie cookie) throws Exception {
        String responseContent = null;
        HttpClient httpClient = ConnectionManagerUtils.getHttpClient();
        HttpGet httpGet=new HttpGet(apiUrl);
        
        try {
        	
        	httpGet.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
            String cookieStr=cookie.getName()+"="+cookie.getValue();
            httpGet.addHeader("Cookie", cookieStr);
            
            HttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
            	 System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
                return null;
            }
            HttpEntity entity = response.getEntity(); // 获取响应实体
            if (entity == null) {
                return null;
            }
            responseContent = EntityUtils.toString(entity, "utf-8");//一个方法内只能使用一次
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
        	httpGet.releaseConnection();
            // 关闭连接,释放资源
           // httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }

	/**
     * 发送 SSL delete 请求（HTTPS）,携带Cookie
     * @param apiUrl API接口URL
     * @param cookie   要发送的cookie对象
     * @return api返回值（String）
     * @throws Exception 
     */
	public static String sendHttpsRequestByDelete(String apiUrl, Cookie cookie) throws Exception {
        String responseContent = null;
        HttpClient httpClient = ConnectionManagerUtils.getHttpClient();
        HttpDelete httpDelete = new HttpDelete(apiUrl);
        
        try {
        	
            String cookieStr=cookie.getName()+"="+cookie.getValue();
            httpDelete.addHeader("Cookie", cookieStr);
            
            HttpResponse response = httpClient.execute(httpDelete);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_NO_CONTENT) {
            	responseContent="success";
            }else {
            	 System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
        	httpDelete.releaseConnection();
            // 关闭连接,释放资源
           // httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }
    
    /**
     * 初始化HttpClient，发送https时不验证证书
     * @return 
     */
    public static HttpClient initHttpClient(HttpClient httpClient) throws Exception {
        
        //创建TrustManager
        X509TrustManager xtm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        
        //这个好像是HOST验证
        X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
            public void verify(String arg0, SSLSocket arg1) throws IOException {}
            public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {}
            public void verify(String arg0, X509Certificate arg1) throws SSLException {}
        };
        
            //TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
            SSLContext ctx = SSLContext.getInstance("TLS");
            //使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
            ctx.init(null, new TrustManager[] { xtm }, null);
            //创建SSLSocketFactory
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
            socketFactory.setHostnameVerifier(hostnameVerifier);
            //通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443));
            
            httpClient.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);
            httpClient.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
			return httpClient;
    }
    
	 
    
}
