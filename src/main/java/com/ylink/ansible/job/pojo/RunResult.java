package com.ylink.ansible.job.pojo;

import com.ylink.ansible.pojo.SummaryField;

public class RunResult {

	private Object ignored_fields;
	private Integer id;
	private String type;
	private String url;
	private  Object related;
	private  SummaryField summary_fields;
	private String created;
	private String modified;
	private String name;
	private String description;
	
	private String job_type;
	private Integer inventory;
	private Integer project;
	private String playbook;
	private String credential;
	private String cloud_credential;
	private String network_credential;
	private String forks;
	private String limit;
	private String verbosity;
	private Integer unified_job_template;
	private String launch_type;
	private String status;
	private boolean failed;
	private String started;
	private String finished;
	private String result_stdout;
	private String execution_node;
	private String result_traceback;
	private Integer job_template;
	private Integer job;
	private String scm_revision;
	private Object passwords_needed_to_start;
	private Object artifacts;
	private Object job_env;
	
	
	
	public final Object getJob_env() {
		return job_env;
	}
	public final void setJob_env(Object job_env) {
		this.job_env = job_env;
	}
	public String getScm_revision() {
		return scm_revision;
	}
	public void setScm_revision(String scm_revision) {
		this.scm_revision = scm_revision;
	}
	public Object getArtifacts() {
		return artifacts;
	}
	public void setArtifacts(Object artifacts) {
		this.artifacts = artifacts;
	}
	public Object getPasswords_needed_to_start() {
		return passwords_needed_to_start;
	}
	public void setPasswords_needed_to_start(Object passwords_needed_to_start) {
		this.passwords_needed_to_start = passwords_needed_to_start;
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
	public String getCredential() {
		return credential;
	}
	public void setCredential(String credential) {
		this.credential = credential;
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
	
	public SummaryField getSummary_fields() {
		return summary_fields;
	}
	public void setSummary_fields(SummaryField summary_fields) {
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
	public String getLaunch_type() {
		return launch_type;
	}
	public void setLaunch_type(String launch_type) {
		this.launch_type = launch_type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isFailed() {
		return failed;
	}
	public void setFailed(boolean failed) {
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
	public String getJob_type() {
		return job_type;
	}
	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}
	public Object getIgnored_fields() {
		return ignored_fields;
	}
	public void setIgnored_fields(Object ignored_fields) {
		this.ignored_fields = ignored_fields;
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
	public String getForks() {
		return forks;
	}
	public void setForks(String forks) {
		this.forks = forks;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getVerbosity() {
		return verbosity;
	}
	public void setVerbosity(String verbosity) {
		this.verbosity = verbosity;
	}
	public String getResult_stdout() {
		return result_stdout;
	}
	public void setResult_stdout(String result_stdout) {
		this.result_stdout = result_stdout;
	}
	public String getResult_traceback() {
		return result_traceback;
	}
	public void setResult_traceback(String result_traceback) {
		this.result_traceback = result_traceback;
	}
	public Integer getJob_template() {
		return job_template;
	}
	public void setJob_template(Integer job_template) {
		this.job_template = job_template;
	}
	public Integer getJob() {
		return job;
	}
	public void setJob(Integer job) {
		this.job = job;
	}
	
	
}
