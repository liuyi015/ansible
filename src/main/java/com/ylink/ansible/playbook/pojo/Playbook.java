package com.ylink.ansible.playbook.pojo;

import java.util.List;

public class Playbook {

	private String folder;
	private String playbook;
	private String peoject_name;
	private List<Parameter> parameter;
	
	
	public final String getPeoject_name() {
		return peoject_name;
	}
	public final void setPeoject_name(String peoject_name) {
		this.peoject_name = peoject_name;
	}
	public final String getFolder() {
		return folder;
	}
	public final String getPlaybook() {
		return playbook;
	}
	public final List<Parameter> getParameter() {
		return parameter;
	}
	public final void setFolder(String folder) {
		this.folder = folder;
	}
	public final void setPlaybook(String playbook) {
		this.playbook = playbook;
	}
	public final void setParameter(List<Parameter> parameter) {
		this.parameter = parameter;
	}
	
	
}
