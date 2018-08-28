package com.ylink.ansible.templates.pojo;

public class Template {
	public Integer id;
	public String type;
	public Object related;
	public Object summary_fields;
	public String created;
	public String modified;
	public String name;
	public String description;
	public String job_type;
	public Integer inventory;
	public Integer project;
	public String playbook;
	public String credential;
	public String cloud_credential;
	public String network_credential;
	public String last_job_run;
	public String last_job_failed;
	public String next_job_run;
	public String status;
	public String verbosity;
	
	
	
	public String getVerbosity() {
		return verbosity;
	}
	public void setVerbosity(String verbosity) {
		this.verbosity = verbosity;
	}
	public String getNext_job_run() {
		return next_job_run;
	}
	public void setNext_job_run(String next_job_run) {
		this.next_job_run = next_job_run;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNetwork_credential() {
		return network_credential;
	}
	public void setNetwork_credential(String network_credential) {
		this.network_credential = network_credential;
	}
	public String getCloud_credential() {
		return cloud_credential;
	}
	public void setCloud_credential(String cloud_credential) {
		this.cloud_credential = cloud_credential;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getJob_type() {
		return job_type;
	}
	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public Integer getProject() {
		return project;
	}
	public void setProject(Integer project) {
		this.project = project;
	}
	public String getPlaybook() {
		return playbook;
	}
	public void setPlaybook(String playbook) {
		this.playbook = playbook;
	}
	public String getCredential() {
		return credential;
	}
	public void setCredential(String credential) {
		this.credential = credential;
	}
	public String getLast_job_run() {
		return last_job_run;
	}
	public void setLast_job_run(String last_job_run) {
		this.last_job_run = last_job_run;
	}
	public String getLast_job_failed() {
		return last_job_failed;
	}
	public void setLast_job_failed(String last_job_failed) {
		this.last_job_failed = last_job_failed;
	}

	
}
