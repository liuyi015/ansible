package com.ylink.ansible.inventory.pojo;

public class InventoryUpdate {
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
	private Boolean failed;
	private String started;
	private String finished;
	private String execution_node;
	private Integer credential;
	private Integer inventory_source;
	private Integer source_script;
	
	//查id时多出的字段
	private Object job_env;
	private String result_stdout;
	private Boolean license_error;
	private Float elapsed;
	private String source;
	private Boolean overwrite;
	private Boolean overwrite_vars;
	
	
	public final Float getElapsed() {
		return elapsed;
	}

	public final void setElapsed(Float elapsed) {
		this.elapsed = elapsed;
	}

	public final String getSource() {
		return source;
	}

	public final void setSource(String source) {
		this.source = source;
	}

	public final Boolean getOverwrite() {
		return overwrite;
	}

	public final void setOverwrite(Boolean overwrite) {
		this.overwrite = overwrite;
	}

	public final Boolean getOverwrite_vars() {
		return overwrite_vars;
	}

	public final void setOverwrite_vars(Boolean overwrite_vars) {
		this.overwrite_vars = overwrite_vars;
	}

	public final Boolean getLicense_error() {
		return license_error;
	}

	public final void setLicense_error(Boolean license_error) {
		this.license_error = license_error;
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

	public Integer getSource_script() {
		return source_script;
	}

	public void setSource_script(Integer source_script) {
		this.source_script = source_script;
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

	public Integer getCredential() {
		return credential;
	}

	public void setCredential(Integer credential) {
		this.credential = credential;
	}

	public Integer getInventory_source() {
		return inventory_source;
	}

	public void setInventory_source(Integer inventory_source) {
		this.inventory_source = inventory_source;
	}

	
	
}
