package com.ylink.ansible.user.pojo;

import java.util.Date;
import java.util.Map;

public class User {
	
	public Integer id;
	public String type;    //choice类型
	public String url;
	public Related related;
	public Map<String,Map> summary_fields;
	public String created;
	public String username;     //不多于30个字符。只能用字母、数字和字符 @/./+/-/_ 。 (string)
	public String password;
	public String first_name;
	public String last_name;
	public String email;
	public boolean is_superuser;   //超级用户状态
	public boolean is_system_auditor;   //Is system auditor
	public String ldap_dn;
	public User external_account;
	public String[] auth;
	//自己创建，方便前台传值
	public String user_type;
	
	
	public final String getUser_type() {
		return user_type;
	}
	public final void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getIs_superuser() {
		return is_superuser;
	}
	public void setIs_superuser(boolean is_superuser) {
		this.is_superuser = is_superuser;
	}
	public boolean getIs_system_auditor() {
		return is_system_auditor;
	}
	public void setIs_system_auditor(boolean is_system_auditor) {
		this.is_system_auditor = is_system_auditor;
	}
	public String[] getAuth() {
		return auth;
	}
	public void setAuth(String[] auth) {
		this.auth = auth;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Related getRelated() {
		return related;
	}
	public void setRelated(Related related) {
		this.related = related;
	}
	public Map<String, Map> getSummary_fields() {
		return summary_fields;
	}
	public void setSummary_fields(Map<String, Map> summary_fields) {
		this.summary_fields = summary_fields;
	}
	
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getLdap_dn() {
		return ldap_dn;
	}
	public void setLdap_dn(String ldap_dn) {
		this.ldap_dn = ldap_dn;
	}
	public User getExternal_account() {
		return external_account;
	}
	public void setExternal_account(User external_account) {
		this.external_account = external_account;
	}
	
}
