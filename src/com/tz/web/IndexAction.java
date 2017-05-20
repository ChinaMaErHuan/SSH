/**
 * tzdesk系统平台
 * CMS
 * com.tz.web.content
 * IndexAction.java
 * 创建人:maerhuan 
 * 时间：2017年2月24日-下午4:16:20 
 * 2017潭州教育公司-版权所有
 */
package com.tz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tz.core.action.BaseAction;
import com.tz.service.channel.IChannelService;
import com.tz.service.comment.ICommentService;
import com.tz.service.content.IContentService;
import com.tz.service.user.IUserService;
import com.tz.service.ws.IChannelServiceWeb;
@Controller("indexAction")
@Scope("prototype")
public class IndexAction extends BaseAction{
	private Integer totalUser;
	private Integer totalChannel;
	private Integer totalContent;
	private Integer totalComment;
	
	@Autowired
	private IContentService contentService;
	@Autowired
	private ICommentService commentService;
	@Autowired
	private IChannelService channelService;
	@Autowired
	private IUserService userService;
	
//	@Method(method=TzRequestMethod.POST)
	public String execute(){
		totalUser = userService.countUser();
		totalComment = commentService.countComment(params);
		totalChannel = channelService.countChannel(params);
		System.out.println(totalChannel);
		totalContent = contentService.countCotent(params);
		//System.out.println(totalUser);
		return SUCCESS;
	}

	public Integer getTotalUser() {
		return totalUser;
	}

	public void setTotalUser(Integer totalUser) {
		this.totalUser = totalUser;
	}


	public Integer getTotalChannel() {
		return totalChannel;
	}

	public void setTotalChannel(Integer totalChannel) {
		this.totalChannel = totalChannel;
	}

	public Integer getTotalContent() {
		return totalContent;
	}

	public void setTotalContent(Integer totalContent) {
		this.totalContent = totalContent;
	}


	public Integer getTotalComment() {
		return totalComment;
	}

	public void setTotalComment(Integer totalComment) {
		this.totalComment = totalComment;
	}

	
}
