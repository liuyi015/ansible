package com.ylink.ansible.playbook.pojo;

import java.util.List;

public class Playbook {

	private String folder;
	private String playbook;
	private List<Parameter> parameter;
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
