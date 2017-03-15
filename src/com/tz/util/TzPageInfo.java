/**
 * tzdesk系统平台
 * cms
 * com.tz.util
 * TzPageInfo.java
 * 创建人:maerhuan 
 * 时间：2017年3月9日-下午6:59:10 
 * 2017潭州教育公司-版权所有
 */
package com.tz.util;

import java.io.Serializable;

public class TzPageInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String DEFAULT_PAGE_SIZE = "11";// 默认每页多少条记
	public static final String DEFAULT_LIST_OFFSET = "0";// 默认从第几条记录
	private String firstResult = "0"; // 分页从第几条记录
	private String maxResults = DEFAULT_PAGE_SIZE;
	private String pageNo = "0"; // 当前
	private String offset;
	private String pageSize;
	private String totalPage;//计算有多少页
	private String totalCount;//
	private transient String itemCount = "0"; // 总记录数

	public String getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(String firstResult) {
		this.firstResult = firstResult;
	}

	public String getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(String maxResults) {
		this.maxResults = maxResults;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getItemCount() {
		return itemCount;
	}
	
	public void setItemCount(String itemCount) {
		this.itemCount = itemCount;
	}
	
	public String getTotalPage() {
		int totalRecoreds = Integer.parseInt(itemCount);
		int pz = Integer.parseInt(maxResults);
		int psize = totalRecoreds / pz;
		if(totalRecoreds % pz !=0){
			psize = psize+1;
		}
		if(psize<=1){psize=1;}
		this.totalPage = String.valueOf(psize); 
		return this.totalPage;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}


}
