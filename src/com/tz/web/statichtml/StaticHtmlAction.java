package com.tz.web.statichtml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tz.core.action.BaseAction;
import com.tz.model.Content;
import com.tz.service.content.IContentService;
import com.tz.util.TzStringUtils;

@Controller("staticHtmlAction")
@Scope("prototype")
public class StaticHtmlAction extends BaseAction{
	
	private Integer cid;
	@Autowired
	private IContentService contentService;
	
	
	//静态化首页--备份
	//静态化栏目页
	//静态化banner
	//静态化区块
	/**
	 * jsp静态化的处理类
	 * 方法名：staticHtml
	 * 创建人：maerhuan 
	 * 时间：2015年5月23日-下午11:19:39 
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String staticHtml(){
		if(cid==null){
			result = "empty";
			return AJAX_SUCCESS;
		}
		result = staticContent(cid);
		return AJAX_SUCCESS;
	}
	
//	public String batchHtml(){
//		for (int i = 0; i < 100; i++) {静态化就不需要静态
//			result = staticContent(i);
//		}
//		return AJAX_SUCCESS;
//	}
	//静态化处理
	public  String  staticContent(Integer cid){
		Content content = contentService.get(cid);
		if(content==null){
			return "nofound";
		}
		FileOutputStream fos = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ServletContext sc = ServletActionContext.getServletContext();
		String staticUrl = content.getStaticUrl();
		File targetFile = null;
		try {
			if(TzStringUtils.isNotEmpty(staticUrl)){//已经静态化
				String path = request.getRealPath("/");
				File rootPath = new File(path);
				targetFile = new File(rootPath,staticUrl);
			}else{
				String ymd = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
				String rpath = "pages/"+ymd;
				String path = request.getRealPath(rpath);
				File rootPath = new File(path);
				if(!rootPath.exists())rootPath.mkdirs();
				String name = cid+getRandomString(8)+".html"; 
				targetFile = new File(rootPath,name);
				staticUrl = rpath+"/"+name;
			}
			RequestDispatcher rd = sc.getRequestDispatcher("/template/content.jsp");
			final ByteArrayOutputStream os = new ByteArrayOutputStream();
			final ServletOutputStream stream = new ServletOutputStream() {//匿名内部类，回调函数
				public void write(byte[] data, int offset, int length) {
					os.write(data, offset, length);
				}
				public void write(int b) throws IOException {
					os.write(b);
				}
				@Override
				public boolean isReady() {
					return false;
				}
				@Override
				public void setWriteListener(WriteListener arg0) {
					
				}
			};
			final PrintWriter pw = new PrintWriter(new OutputStreamWriter(os,"gbk"));
			HttpServletResponse rep = new HttpServletResponseWrapper(response) {
				public ServletOutputStream getOutputStream() {
					return stream;
				}

				public PrintWriter getWriter() {
					return pw;
				}
			};
			
			//存放id
			request.setAttribute("cid",cid);
			rd.include(request, rep);//发起请求
			pw.flush();
			fos = new FileOutputStream(targetFile);
			os.writeTo(fos);//讲源代码写入磁盘文件中
			//静态化的url更新数据表中
			content.setStaticUrl(staticUrl);
			contentService.updateDefault(content);
			return "success";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "error";
		} catch (ServletException e) {
			e.printStackTrace();
			return "error";
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
			}
		}
	}
	
	private static String getRandomString(int length) {
		StringBuffer bu = new StringBuffer();
		String[] arr = { "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c",
				"d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "p", "q",
				"r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
				"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		Random random = new Random();
		while (bu.length() < length) {
			String temp = arr[random.nextInt(57)];
			if (bu.indexOf(temp) == -1) {
				bu.append(temp);
			}
		}
		return bu.toString();
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
}
