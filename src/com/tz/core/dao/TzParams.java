/**
 * tzdesk系统平台
 * cms
 * com.tz.core.dao
 * TzParams.java
 * 创建人:maerhuan 
 * 时间：2017年3月9日-下午6:38:06 
 * 2017潭州教育公司-版权所有
 */
package com.tz.core.dao;

public class TzParams {
	private String keyword;
	private String date;
	private Integer uid;

	private String img;
	private Integer channelId;

	public TzParams() {

	}

	public TzParams(String keyword, String date, Integer uid) {
		this.keyword = keyword;
		this.date = date;
		this.uid = uid;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	

}
