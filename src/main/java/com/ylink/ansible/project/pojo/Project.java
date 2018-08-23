package com.ylink.ansible.project.pojo;

import java.util.Map;


public class Project {
	
	private String id;
	private String type;
	private String url;
	public  Map<String,String> related;
	public Map<String,Map> summary_fields;
	private String created;
	private String modified;
	
	private String name;
	private String description;
	private String local_path;
	
	private String scm_type;
	private String scm_url;
	private String scm_branch;
	private boolean scm_clean;
	private boolean scm_delete_on_update;
	private boolean scm_update_on_launch;
	
	private Integer timeout;
	private String next_job_run;
	private String last_job_run;
	
	private String credential;
	private Integer organization;
	
	private Integer scm_update_cache_timeout;   // 0~2147483647,
	
	private String scm_revision;   //版本
	
	private String last_updated;   
	
	
	public String getLast_job_run() {
		return last_job_run;
	}

	public void setLast_job_run(String last_job_run) {
		this.last_job_run = last_job_run;
	}

	public String getNext_job_run() {
		return next_job_run;
	}

	public void setNext_job_run(String next_job_run) {
		this.next_job_run = next_job_run;
	}

	public Map<String, String> getRelated() {
		return related;
	}

	public void setRelated(Map<String, String> related) {
		this.related = related;
	}

	public Map<String, Map> getSummary_fields() {
		return summary_fields;
	}

	public void setSummary_fields(Map<String, Map> summary_fields) {
		this.summary_fields = summary_fields;
	}

	public String getScm_revision() {
		return scm_revision;
	}

	public void setScm_revision(String scm_revision) {
		this.scm_revision = scm_revision;
	}

	public String getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
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

	public String getLocal_path() {
		return local_path;
	}

	public void setLocal_path(String local_path) {
		this.local_path = local_path;
	}

	public String getScm_type() {
		return scm_type;
	}

	public void setScm_type(String scm_type) {
		this.scm_type = scm_type;
	}

	public String getScm_url() {
		return scm_url;
	}

	public void setScm_url(String scm_url) {
		this.scm_url = scm_url;
	}

	public String getScm_branch() {
		return scm_branch;
	}

	public void setScm_branch(String scm_branch) {
		this.scm_branch = scm_branch;
	}

	public boolean isScm_clean() {
		return scm_clean;
	}

	public void setScm_clean(boolean scm_clean) {
		this.scm_clean = scm_clean;
	}

	public boolean isScm_delete_on_update() {
		return scm_delete_on_update;
	}

	public void setScm_delete_on_update(boolean scm_delete_on_update) {
		this.scm_delete_on_update = scm_delete_on_update;
	}

	public boolean isScm_update_on_launch() {
		return scm_update_on_launch;
	}

	public void setScm_update_on_launch(boolean scm_update_on_launch) {
		this.scm_update_on_launch = scm_update_on_launch;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public String getCredential() {
		return credential;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}

	public Integer getOrganization() {
		return organization;
	}

	public void setOrganization(Integer organization) {
		this.organization = organization;
	}

	public Integer getScm_update_cache_timeout() {
		return scm_update_cache_timeout;
	}

	public void setScm_update_cache_timeout(Integer scm_update_cache_timeout) {
		this.scm_update_cache_timeout = scm_update_cache_timeout;
	}
	
	
}
