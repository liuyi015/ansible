package com.ylink.ansible.pojo;

import javax.jws.soap.SOAPBinding.Use;

import com.ylink.ansible.credential.pojo.Credential;
import com.ylink.ansible.inventory.pojo.Inventory;
import com.ylink.ansible.project.pojo.Project;
import com.ylink.ansible.project.pojo.ProjectUpdate;
import com.ylink.ansible.templates.pojo.Template;
import com.ylink.ansible.user.pojo.User;

public class SummaryField {
	private Template job_template;
	private Inventory inventory;
	private Credential credential;
	private Template unified_job_template;
	private ProjectUpdate project_update;
	private Project project;
	private User created_by;
	private User modified_by;
	private Object user_capabilities;
	private Object labels;
	
	
	public final ProjectUpdate getProject_update() {
		return project_update;
	}
	public final void setProject_update(ProjectUpdate project_update) {
		this.project_update = project_update;
	}
	public Template getJob_template() {
		return job_template;
	}
	public void setJob_template(Template job_template) {
		this.job_template = job_template;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public Credential getCredential() {
		return credential;
	}
	public void setCredential(Credential credential) {
		this.credential = credential;
	}
	public Template getUnified_job_template() {
		return unified_job_template;
	}
	public void setUnified_job_template(Template unified_job_template) {
		this.unified_job_template = unified_job_template;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public User getCreated_by() {
		return created_by;
	}
	public void setCreated_by(User created_by) {
		this.created_by = created_by;
	}
	public User getModified_by() {
		return modified_by;
	}
	public void setModified_by(User modified_by) {
		this.modified_by = modified_by;
	}
	public Object getUser_capabilities() {
		return user_capabilities;
	}
	public void setUser_capabilities(Object user_capabilities) {
		this.user_capabilities = user_capabilities;
	}
	public Object getLabels() {
		return labels;
	}
	public void setLabels(Object labels) {
		this.labels = labels;
	}
	
	
	
}
