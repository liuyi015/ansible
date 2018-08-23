package com.ylink.ansible.config.pojo;

public class Config {

	public String version;
	public String ansible_version;
	public String project_base_dir;
	public String[] project_local_paths;
	public Object license_info;
	public Object time_zone;
	
	
	public Object getTime_zone() {
		return time_zone;
	}
	public void setTime_zone(Object time_zone) {
		this.time_zone = time_zone;
	}
	public Object getLicense_info() {
		return license_info;
	}
	public void setLicense_info(Object license_info) {
		this.license_info = license_info;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAnsible_version() {
		return ansible_version;
	}
	public void setAnsible_version(String ansible_version) {
		this.ansible_version = ansible_version;
	}
	public String getProject_base_dir() {
		return project_base_dir;
	}
	public void setProject_base_dir(String project_base_dir) {
		this.project_base_dir = project_base_dir;
	}
	public String[] getProject_local_paths() {
		return project_local_paths;
	}
	public void setProject_local_paths(String[] project_local_paths) {
		this.project_local_paths = project_local_paths;
	}
	
	
}
