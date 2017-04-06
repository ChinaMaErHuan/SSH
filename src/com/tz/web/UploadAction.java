/**
 * tzdesk系统平台
 * cms
 * com.tz.web
 * UploadAction.java
 * 创建人:maerhuan 
 * 时间：2017年4月6日-下午3:36:18 
 * 2017潭州教育公司-版权所有
 */
package com.tz.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tz.core.action.BaseAction;
import com.tz.util.TzDateUtil;
import com.tz.util.TzFileUtil;

@Controller("uploadAction")
@Scope("prototype")
public class UploadAction extends BaseAction {
	// 接受文件
	private File file;
	// 文件名
	private String fileFileName;
	// 上传文件的类型
	private String fileContentType;

	public String demo(){
		System.out.println("我是i文件上传");
		return "demo";
	}
	
	public String demo2() {
		return "demo2";
	}
	//第一步建立uploadaction
		//第二部配置uploaaction
		//第三步：能够上传的页面
		//第四步：定义文件上传的方法和属性
		//第五步：正在的上传
//			System.out.println("我是文件上传!!!!");
//			System.out.println("我上传的文件是:"+file);
//			System.out.println("文件名是:"+fileFileName);
//			System.out.println("文件类型是:"+fileContentType);
	public String upload(){
		HashMap<String, Object> map = new HashMap<String,Object>();
		//1：上传放入服务器，如何获取服务器的上传的路径
		String uploadPath = ServletActionContext.getServletContext().getRealPath("upload");
		//2：如果服务器上传路径不存在，就创建
		File dirPath = new File(uploadPath);
		if(!dirPath.exists()){
			dirPath.mkdirs();
		}
		String filename = TzDateUtil.dateToString(new Date(), "yyyy-MM-dd");
		//二級文件夹
		File filePath = new File(dirPath, filename);
		if(!filePath.exists())filePath.mkdirs();
		//3:进行io流读写，将本地的图片上传到服务器的上传路基下
		//对上传文件的重命名
		String newFileName = generateFileName(fileFileName);
		//文件上传了 io也可以用FileUtils
		//建立文件上传的缓存读和写的流
		BufferedOutputStream outputStream =null;//reponse.getWriter(),它也是一个输出流.往浏览器输出的
		BufferedInputStream inputStream = null;
		try {
			//将文件开始输入流
			FileInputStream fis = new FileInputStream(file);
			inputStream = new BufferedInputStream(fis);
			//输出流
			FileOutputStream out = new FileOutputStream(new File(filePath,newFileName));
			outputStream = new BufferedOutputStream(out);
			//以多少开始读取文件的流 文件，
			byte[] buf = new byte[4096];
			int len = -1;
			while((len = inputStream.read(buf))!=-1){//读客户端的文件
				outputStream.write(buf, 0, len);//写入到服务器
			}
			map.put("name", newFileName);
			map.put("oldName", fileFileName);
			map.put("url", uploadPath+File.separator+filename+File.separator+newFileName);
			map.put("size", file.length());
			map.put("ext", TzFileUtil.getExtNoPoint(fileFileName));
			map.put("type", fileContentType);
			map.put("path", uploadPath);
			map.put("sizeString", TzFileUtil.countFileSize(file.length()));
			result = JSONUtil.serialize(map);
			System.out.println(result);
		} catch (Exception e) {
		} finally{
			try {
				if(outputStream!=null){
					outputStream.close();
				}
				if(inputStream!=null){
					inputStream.close();
				}
			} catch (Exception e2) {
			}
		}
		return "callback";
	}
	
	/**
	 * 文件的重命名
	 * 方法名：generateFileName
	 * 创建人：xuchengfei 
	 * 时间：2015年5月14日-下午9:45:55 
	 * @param fileName
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	private String generateFileName(String fileName){
		String dateFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		int random = new Random().nextInt(100000);
		String ext = fileName.substring(fileName.lastIndexOf("."));
		String newFilName = dateFormat+random+ext;
		return newFilName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	
}
