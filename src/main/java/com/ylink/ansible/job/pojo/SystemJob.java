package com.ylink.ansible.job.pojo;

public class SystemJob {

	private Integer id;
	private String type;
	private String url;
	private  Object related;
	private  Object summary_fields;
	private String created;
	private String modified;
	private String name;
	private String description;
	
	private Integer unified_job_template;
	private String launch_type;
	private String status;
	private boolean failed;
	private String started;
	private String finished;
	private String execution_node;
	private Integer system_job_template;
	private String job_type;
	private Object extra_vars;
	private Float elapsed;
	private Object job_env;
	private String result_stdout;
	
	
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
	public Integer getSystem_job_template() {
		return system_job_template;
	}
	public void setSystem_job_template(Integer system_job_template) {
		this.system_job_template = system_job_template;
	}
	public Object getExtra_vars() {
		return extra_vars;
	}
	public void setExtra_vars(Object extra_vars) {
		this.extra_vars = extra_vars;
	}
	
	
}
