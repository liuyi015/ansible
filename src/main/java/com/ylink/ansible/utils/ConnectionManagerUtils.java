package com.ylink.ansible.utils;


import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

@SuppressWarnings("deprecation")
public class ConnectionManagerUtils {
		private static HttpParams httpParams;
		private static PoolingHttpClientConnectionManager  connectionManager;
	 
		/**
		 * 最大连接数
		 */
		public final static int MAX_TOTAL_CONNECTIONS = 800;
		/**
		 * 获取连接的最大等待时间
		 */
		public final static int WAIT_TIMEOUT = 60000;
		/**
		 * 每个路由最大连接数
		 */
		public final static int MAX_ROUTE_CONNECTIONS = 50;
		/**
		 * 连接超时时间
		 */
		public final static int CONNECT_TIMEOUT = 10000;
		/**
		 * 读取超时时间
		 */
		public final static int READ_TIMEOUT = 10000;
	 
		static {
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
	            SSLContext ctx;
	            SSLConnectionSocketFactory socketFactory = null ;
				try {
					ctx = SSLContext.getInstance("TLS");
					//使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
					ctx.init(null, new TrustManager[] { xtm }, null);
		            //创建SSLSocketFactory
		            socketFactory= new SSLConnectionSocketFactory(ctx, hostnameVerifier);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            
			httpParams = new BasicHttpParams();
	 
			 Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
		                .register("https", socketFactory)
		                .register("http", new PlainConnectionSocketFactory())
		                .build();
			connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			connectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
			connectionManager.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);
		}
	 
		public static HttpClient getHttpClient() {
			ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() { 
			    public long getKeepAliveDuration(HttpResponse response, HttpContext context) { 
			        // Honor 'keep-alive' header 
			        HeaderElementIterator it = new BasicHeaderElementIterator( 
			                response.headerIterator(HTTP.CONN_KEEP_ALIVE)); 
			        while (it.hasNext()) { 
			            HeaderElement he = it.nextElement(); 
			            String param = he.getName(); 
			            String value = he.getValue(); 
			            if (value != null && param.equalsIgnoreCase("timeout")) { 
			                try { 
			                    return Long.parseLong(value) * 1000; 
			                } catch(NumberFormatException ignore) { 
			                } 
			            } 
			        } 
			        HttpHost target = (HttpHost) context.getAttribute( 
			                HttpClientContext.HTTP_TARGET_HOST); 
			        if ("172.168.65.88".equalsIgnoreCase(target.getHostName())) { 
			            // Keep alive for 5 seconds only 
			            return 5 * 1000; 
			        } else { 
			            // otherwise keep alive for 30 seconds 
			            return 30 * 1000; 
			        } 
			    } 
			}; 
		//return new DefaultHttpClient(connectionManager, httpParams);
			
/*	
 * httpclient3.2以上，不自动处理重定向（301或者302），需要以下配置（使用httpclient连接池时）
 * 或者HttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
 * */
//			return HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).setConnectionManager(connectionManager).setKeepAliveStrategy(myStrategy).build();
			
			return HttpClients.custom().setConnectionManager(connectionManager).setKeepAliveStrategy(myStrategy).build();
		}
	 
	}


