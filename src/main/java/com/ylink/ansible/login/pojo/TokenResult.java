package com.ylink.ansible.login.pojo;
/**
 * api返回的token+expires值
 * @author ASUS
 *
 */
public class TokenResult {
	
	public String token;
	public String expires;
	public final String getToken() {
		return token;
	}
	public final void setToken(String token) {
		this.token = token;
	}
	public final String getExpires() {
		return expires;
	}
	public final void setExpires(String expires) {
		this.expires = expires;
	}
	
}
