package com.dantefung.page;

import java.util.List;

public class PageBean<T> {
	// [数据库]  当前页的数据
	private List<T> data = null;
	// [数据库]中查询 总记录数
	private Integer totalCount;
	
	// 固定首页为1
	private Integer firstPage;
	// 计算
	private Integer prePage;
	// 计算
	private Integer nextPage;
	// 计算   总页数 也即 尾页
	private Integer totalPage;
	
	
	// 【用户】传递 当前页
	private Integer curPage;
	// 【用户】  每页显示记录数
	private Integer pageSize;
	

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	// 首页 固定为 1
	public Integer getFirstPage() {
		return 1;
	}

//	public void setFirstPage() {
//		this.firstPage = 1;
//	}

	/* 1.当前页为首页：
	 *       则   prePage = 1;
	 * 2.当前页不为首页：
	 *       则  prePage = curPage -1;
	 */
	public Integer getPrePage() {
		return this.curPage==this.firstPage?
			    1:
			    (curPage - 1);
	}

//	public void setPrePage(Integer prePage) {
//		this.prePage = prePage;
//	}

	/*
	 * 1.当前页为尾页
	 *      则  nextPage = totalPage;
	 * 2.当前页不为尾页
	 *      则 nextPage = curPage +1;
	 * 
	 */
	public Integer getNextPage() {
		return this.curPage==this.totalPage?
				totalPage:
				curPage+1;
	}

//	public void setNextPage(Integer nextPage) {
//		this.nextPage = nextPage;
//	}

	/*
	 * 1.总记录数%每页显示数==0 
	 *        则  totalPage = 总记录数/每页显示数
	 * 2.总记录数%每页显示数!=0
	 *        则 totalPage = 总记录数/每页显示数 +1
	 * 
	 */
	public Integer getTotalPage() {
		return (Integer) (this.totalCount%this.pageSize==0?
				 this.totalCount/this.pageSize:
				this.totalCount/this.pageSize+1)
				;
	}
//
//	public void setTotalPage(Integer totalPage) {
//		this.totalPage = totalPage;
//	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PageBean [data=" + data + ", totalCount=" + totalCount
				+ ", firstPage=" + this.getFirstPage() + ", prePage=" + this.getPrePage()
				+ ", nextPage=" + this.getNextPage() + ", totalPage=" + this.getTotalPage()
				+ ", curPage=" + curPage + ", pageSize=" + pageSize + "]";
	}

	
	
}
