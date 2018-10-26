package com.ylink.ansible.playbook.pojo;
/**
 * 
 * @author ASUS
 *
 */
public class Parameter {
	private String name;       //参数的名字
	private String parameter_name;   //旧参数值（参数值的名字）（需替换的参数值）
	private String parameter_value;  //新参数值（用户输入）
	private String remark;          //参数备注
	
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getParameter_name() {
		return parameter_name;
	}
	public final String getParameter_value() {
		return parameter_value;
	}
	public final void setParameter_name(String parameter_name) {
		this.parameter_name = parameter_name;
	}
	public final void setParameter_value(String parameter_value) {
		this.parameter_value = parameter_value;
	}
	public final String getRemark() {
		return remark;
	}
	public final void setRemark(String remark) {
		this.remark = remark;
	}

}
