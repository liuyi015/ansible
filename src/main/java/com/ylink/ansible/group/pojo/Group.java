package com.ylink.ansible.group.pojo;

import com.ylink.ansible.pojo.SummaryField;

public class Group {

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
	private  String variables;
	private  Boolean has_active_failures;
	private  Integer total_hosts;
	private  Integer hosts_with_active_failures;
	private  Integer total_groups;
	private  Integer groups_with_active_failures;
	private  Boolean has_inventory_sources;
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
	public final String getVariables() {
		return variables;
	}
	public final Boolean getHas_active_failures() {
		return has_active_failures;
	}
	public final Integer getTotal_hosts() {
		return total_hosts;
	}
	public final Integer getHosts_with_active_failures() {
		return hosts_with_active_failures;
	}
	public final Integer getTotal_groups() {
		return total_groups;
	}
	public final Integer getGroups_with_active_failures() {
		return groups_with_active_failures;
	}
	public final Boolean getHas_inventory_sources() {
		return has_inventory_sources;
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
	
	public final Integer getInventory() {
		return inventory;
	}
	public final void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public final void setVariables(String variables) {
		this.variables = variables;
	}
	public final void setHas_active_failures(Boolean has_active_failures) {
		this.has_active_failures = has_active_failures;
	}
	public final void setTotal_hosts(Integer total_hosts) {
		this.total_hosts = total_hosts;
	}
	public final void setHosts_with_active_failures(Integer hosts_with_active_failures) {
		this.hosts_with_active_failures = hosts_with_active_failures;
	}
	public final void setTotal_groups(Integer total_groups) {
		this.total_groups = total_groups;
	}
	public final void setGroups_with_active_failures(Integer groups_with_active_failures) {
		this.groups_with_active_failures = groups_with_active_failures;
	}
	public final void setHas_inventory_sources(Boolean has_inventory_sources) {
		this.has_inventory_sources = has_inventory_sources;
	}
	
	
}
