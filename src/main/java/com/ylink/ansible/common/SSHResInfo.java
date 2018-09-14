package com.ylink.ansible.common;

/**
 * 封装使用jsch执行command命令的结果
 * @author liuyi
 *
 */
public class SSHResInfo {
	private Integer code;   //0:执行成功
	private String successInfo;
	private String errorInfo;
	
	public final Integer getCode() {
		return code;
	}
	public final void setCode(Integer code) {
		this.code = code;
	}
	public final String getSuccessInfo() {
		return successInfo;
	}
	public final String getErrorInfo() {
		return errorInfo;
	}
	public final void setSuccessInfo(String successInfo) {
		this.successInfo = successInfo;
	}
	public final void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	@Override
	public String toString() {
		return "SSHResInfo [code=" + code + ", successInfo=" + successInfo + ", errorInfo=" + errorInfo + "]";
	}

	
}
