/**
 * tzdesk系统平台
 * TzSSH
 * com.tz.web.content
 * ContentAction.java
 * 创建人:maerhuan 
 * 时间：2017年2月21日-下午3:56:55 
 * 2017潭州教育公司-版权所有
 */
package com.tz.web.content;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tz.core.Method;
import com.tz.core.action.BaseAction;
import com.tz.core.interceptor.TzRequestMethod;
import com.tz.model.Content;
import com.tz.model.User;
import com.tz.service.content.IContentService;
import com.tz.util.TzDateUtil;
import com.tz.util.TzFileUtil;
import com.tz.util.TzStringUtils;

/**
 * 
 * 
 * ContentAction 创建人:maerhuan 时间：2017年2月23日-下午3:42:23
 * 
 * @version 1.0.0
 * 
 */
@Controller("contentAction")
@Scope("prototype")
// prototype:每次action创建的对象都是一个全新的对象
// singleton:是单例
public class ContentAction extends BaseAction {
	@Autowired
	private IContentService contentService;

	private List<Content> contents;

	private Content content;

	/**
	 * 
	 * 查询展示</br> com.tz.web.content </br> 方法名：list </br> 创建人：maerhuan </br>
	 * 时间：2017年3月12日-下午11:14:54 </br>
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public String list() {
		contents = contentService.findContents(params, page);
		int itemCount = contentService.countCotent(params);
		page.setItemCount(String.valueOf(itemCount));
		return LIST;
	}

	/**
	 * 
	 * 模板加载</br> com.tz.web.content </br> 方法名：listTemplate </br> 创建人：maerhuan
	 * </br> 时间：2017年3月12日-下午11:15:06 </br>
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public String listTemplate() {
		contents = contentService.findContents(params, page);
		int itemCount = contentService.countCotent(params);
		page.setItemCount(String.valueOf(itemCount));
		return "listTemplate";
	}

	/**
	 * 
	 * 删除方法</br> com.tz.web.content </br> 方法名：delete </br> 创建人：maerhuan </br>
	 * 时间：2017年3月12日-下午11:14:30 </br>
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	@Method(method=TzRequestMethod.POST)
	public String delete() {
		try {
			contentService.deleteById(id);
			result = SUCCESS;
		} catch (Exception e) {
			result = FAIL;
		}
		return AJAX_SUCCESS;
	}
	
	/**
	 * 
	 * 更新</br>
	 * com.tz.web.content </br>
	 * 方法名：update </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月12日-下午11:16:13 </br>
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@Method(method=TzRequestMethod.POST)
	public String update(){
		content.setUpdateTime(new Date());
		contentService.updateDefault(content);
		//对象太大 要清空不然会报错Ajax 不要返回大对象
		content = null;
		result = SUCCESS;
		return AJAX_SUCCESS;
	}
	/**
	 * 
	 * 保存</br>
	 * com.tz.web.content </br>
	 * 方法名：save </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月14日-下午10:09:35 </br>
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@Method(method = TzRequestMethod.POST)
	public String save(){
		content.setUser(new User(1));
		content.setIsComment(1);
		content.setStatus(1);
		content.setSort(0);
		content.setHit(0);
		content.setIsDelete(1);
		content.setIsTop(0);
		content = contentService.save(content);
		content.setUser(null);
		result = SUCCESS;
		return AJAX_SUCCESS;
	}

	
	/**
	 * 
	 * 编辑方法</br>
	 * com.tz.web.content </br>
	 * 方法名：edit </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月14日-下午10:06:56 </br> void
	 * @exception 
	 * @since  1.0.0
	 */
	public void edit(){//和struts2的ajax无关了
		Content content2 = contentService.get(id);
		outObject(content2);
		
	}
	/**
	 * 
	 * 下载网络图片</br>
	 * com.tz.web.content </br>
	 * 方法名：downimg </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月16日-下午6:54:18 </br>
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@Method(method = TzRequestMethod.POST)
	public String downimg(){
		//返回的本地化图片以后的网址
		result = downNetImg(params.getImg());
		return AJAX_SUCCESS;
	}
	
	/**
	 * 
	 * ajax获取正在上传的进度条信息</br>
	 * com.tz.web.content </br>
	 * 方法名：imgPercent </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月17日-下午3:25:56 </br> void
	 * @exception 
	 * @since  1.0.0
	 */
	public void imgPercent(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		result = (String) session.getAttribute(params.getImg());
		if (TzStringUtils.isEmpty(result)) {
			result = "0";
		}
		outObject(result);
	}
	
	
	
	
	private static String downNetImg(String src){
		try {
			URL url = new URL(src);
			URLConnection connection = url.openConnection();
			int totalSize = connection.getContentLength();//网络图片的大小,字节 跑道的100米
			InputStream inputStream = connection.getInputStream();
			//获取服务器的路径
//			String uploadPath = ServletActionContext.getRequest().getRealPath("upload");
			String uploadPath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("upload");
			String filename = TzDateUtil.dateToString(new Date(), "yyyy-MM-dd");
			//如果upload不存在就创建
			File rootFile = new File(uploadPath);
			if(!rootFile.exists())rootFile.mkdirs();
			//文件夹
			File file = new File(rootFile, filename);
			if(!file.exists())file.mkdirs();
			//获取新的文件名
			String newname = TzFileUtil.getUUID()+TzFileUtil.getExt(src);
			//获取磁盘输出流 file(parent,chindren)构造函数免去斜线与反斜线的问题
			FileOutputStream outputStream = new FileOutputStream(new File(file,newname));
			byte[] b = new byte[2048];
			//输入流
			BufferedInputStream in = new BufferedInputStream(inputStream);
			//io读写瓶颈--
			int len = 0;
			//开始读和写
			int length =0; 
			HttpSession session = ServletActionContext.getRequest().getSession();
			while((len = in.read(b))!=-1){//读
				length += len;//已经下载的长度,小涛跑25米， 25%
				Thread.sleep(50);
				//System.out.println("您当前下载了:"+TzStringUtils.getPercent(length, (float)totalSize,"#"));
				session.setAttribute(src, TzStringUtils.getPercent(length, (float)totalSize,"#"));
				outputStream.write(b, 0, len);//写
			}
			in.close();
			outputStream.close();//关闭
			inputStream.close();
			return "upload"+File.separator+filename+File.separator+newname;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

}
