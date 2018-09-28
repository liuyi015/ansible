package com.ylink.ansible.common;
/**
 * 封装返回页面的结果
 * @author liuyi
 *
 */
public class ResultInfo {

	private Object list;
	private int count;  //总数量
	private int totalPage; //总页数
	private int currentPage; //当前页数
	public final Object getList() {
		return list;
	}
	public final int getCount() {
		return count;
	}
	public final int getTotalPage() {
		return totalPage;
	}
	public final int getCurrentPage() {
		return currentPage;
	}
	public final void setList(Object list) {
		this.list = list;
	}
	public final void setCount(int count) {
		this.count = count;
	}
	public final void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public final void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
}
