package com.ylink.ansible.credential.pojo;

import java.util.Map;

public class Credential {
	private String id;
	private String type;
	private String url;
	private  Object related;
	private  Object summary_fields;
	private String created;
	private String modified;
	
	private String name;
	private String description;
	
	private String kind;
	private boolean cloud;
	private String host;
	private String username;
	private String password;
	private Integer organization;
	
	
	public Integer getOrganization() {
		return organization;
	}
	public void setOrganization(Integer organization) {
		this.organization = organization;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Object getRelated() {
		return related;
	}
	public void setRelated(Object related) {
		this.related = related;
	}
	public Object getSummary_fields() {
		return summary_fields;
	}
	public void setSummary_fields(Object summary_fields) {
		this.summary_fields = summary_fields;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public boolean isCloud() {
		return cloud;
	}
	public void setCloud(boolean cloud) {
		this.cloud = cloud;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
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
	
	
	

}
