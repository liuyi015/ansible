package com.ylink.ansible.project.pojo;

import java.util.Map;


public class ProjectUpdate {
	
	private String id;
	private String type;
	private String url;
	public  Map<String,String> related;
	public Map<String,Map> summary_fields;
	private String created;
	private String modified;
	
	private String name;
	private String description;
	private Integer unified_job_template;
	private String status;
	private Boolean failed;
	private String started;
	private String finished;
	private String execution_node;
	private String local_path;
	
	private String scm_type;
	private String scm_url;
	private String scm_branch;
	private boolean scm_clean;
	private boolean scm_delete_on_update;
	
	private Integer timeout;
	private Integer project;
	private Integer credential;
	private String job_type;
	private Object job_env;
	private String result_stdout;
	private String launch_type;
	private Float elapsed;
	
	
	public final String getLaunch_type() {
		return launch_type;
	}
	public final void setLaunch_type(String launch_type) {
		this.launch_type = launch_type;
	}
	public final Float getElapsed() {
		return elapsed;
	}
	public final void setElapsed(Float elapsed) {
		this.elapsed = elapsed;
	}
	public final Object getJob_env() {
		return job_env;
	}
	public final void setJob_env(Object job_env) {
		this.job_env = job_env;
	}
	public final String getResult_stdout() {
		return result_stdout;
	}
	public final void setResult_stdout(String result_stdout) {
		this.result_stdout = result_stdout;
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
	public Integer getUnified_job_template() {
		return unified_job_template;
	}
	public void setUnified_job_template(Integer unified_job_template) {
		this.unified_job_template = unified_job_template;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean getFailed() {
		return failed;
	}
	public void setFailed(Boolean failed) {
		this.failed = failed;
	}
	public String getStarted() {
		return started;
	}
	public void setStarted(String started) {
		this.started = started;
	}
	public String getFinished() {
		return finished;
	}
	public void setFinished(String finished) {
		this.finished = finished;
	}
	public String getExecution_node() {
		return execution_node;
	}
	public void setExecution_node(String execution_node) {
		this.execution_node = execution_node;
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
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	public Integer getProject() {
		return project;
	}
	public void setProject(Integer project) {
		this.project = project;
	}
	public Integer getCredential() {
		return credential;
	}
	public void setCredential(Integer credential) {
		this.credential = credential;
	}
	public String getJob_type() {
		return job_type;
	}
	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}
	
	
	
}
