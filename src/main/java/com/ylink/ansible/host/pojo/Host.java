package com.ylink.ansible.host.pojo;

public class Host {

	private Integer id;
	private String type;
	private String url;
	private  Object related;
	private  Object summary_fields;
	private String created;
	private String modified;
	private String name;
	private String description;
	
	private  Integer inventory;
	private  Boolean enabled;
	private  String instance_id;
	private  String variables;
	private  Boolean has_active_failures;
	private  Boolean has_inventory_sources;
	private  Integer last_job;
	private  Integer last_job_host_summary;
	public final Integer getId() {
		return id;
	}
	public final String getType() {
		return type;
	}
	public final String getUrl() {
		return url;
	}
	public final Object getRelated() {
		return related;
	}
	public final Object getSummary_fields() {
		return summary_fields;
	}
	public final String getCreated() {
		return created;
	}
	public final String getModified() {
		return modified;
	}
	public final String getName() {
		return name;
	}
	public final String getDescription() {
		return description;
	}
	public final Integer getInventory() {
		return inventory;
	}
	public final Boolean getEnabled() {
		return enabled;
	}
	public final String getInstance_id() {
		return instance_id;
	}
	public final String getVariables() {
		return variables;
	}
	public final Boolean getHas_active_failures() {
		return has_active_failures;
	}
	public final Boolean getHas_inventory_sources() {
		return has_inventory_sources;
	}
	public final Integer getLast_job() {
		return last_job;
	}
	public final Integer getLast_job_host_summary() {
		return last_job_host_summary;
	}
	public final void setId(Integer id) {
		this.id = id;
	}
	public final void setType(String type) {
		this.type = type;
	}
	public final void setUrl(String url) {
		this.url = url;
	}
	public final void setRelated(Object related) {
		this.related = related;
	}
	public final void setSummary_fields(Object summary_fields) {
		this.summary_fields = summary_fields;
	}
	public final void setCreated(String created) {
		this.created = created;
	}
	public final void setModified(String modified) {
		this.modified = modified;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final void setDescription(String description) {
		this.description = description;
	}
	public final void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public final void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public final void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}
	public final void setVariables(String variables) {
		this.variables = variables;
	}
	public final void setHas_active_failures(Boolean has_active_failures) {
		this.has_active_failures = has_active_failures;
	}
	public final void setHas_inventory_sources(Boolean has_inventory_sources) {
		this.has_inventory_sources = has_inventory_sources;
	}
	public final void setLast_job(Integer last_job) {
		this.last_job = last_job;
	}
	public final void setLast_job_host_summary(Integer last_job_host_summary) {
		this.last_job_host_summary = last_job_host_summary;
	}
	
	
}
