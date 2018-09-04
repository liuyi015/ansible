package com.ylink.ansible.inventory.pojo;

import com.ylink.ansible.pojo.SummaryField;

public class Inventory {
	private Integer id;
	private String type;
	private String url;
	private  Object related;
	private  SummaryField summary_fields;
	private String created;
	private String modified;
	private String name;
	private String description;
	
	private Integer organization;


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

	public final SummaryField getSummary_fields() {
		return summary_fields;
	}

	public final void setSummary_fields(SummaryField summary_fields) {
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

	public Integer getOrganization() {
		return organization;
	}

	public void setOrganization(Integer organization) {
		this.organization = organization;
	}
	
	
}
