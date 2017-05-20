/**
 * tzdesk系统平台
 * cms
 * com.tz.web
 * CheckImgAction.java
 * 创建人:maerhuan 
 * 时间：2017年5月15日-下午4:16:45 
 * 2017潭州教育公司-版权所有
 */
package com.tz.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("checkImgAction")
@Scope("prototype")
public class CheckImgAction {
	public String execute() throws Exception {
		//图片什么：是一个二进制文件，一定通过out流 以字节流
		//验证码:
		try{
		    //java.awt.*就通过java去生成图片的包
		    //图片的宽度和高度
		    int width = 60;
		    int height = 30;
		    int linesize = 30;
		    int stringNum = 4;
		    //1:创建一个图片缓存对象，（设定图片的宽度和高度）
		    BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		    //2:取得画布对象
		    Graphics g = bufferedImage.getGraphics();
		    //从画布的左顶点位置开始以图片设定的宽度和高度为基线，进行填充内容（文字，图片）
		    //fillRect 这个绘制矩形
		    g.fillRect(0, 0, width, height);
		    g.setColor(getRandColor(100, 130));
		    g.setFont(new Font("Times New Roman",Font.ROMAN_BASELINE,20));
		    //建立随机数
		    Random random = new Random();
		    //干扰线的绘制
		    for(int i=0;i<=linesize;i++){
		    	int x = random.nextInt(width);
		    	int y = random.nextInt(height);
		    	int x1 = random.nextInt(10);
		    	int y1 = random.nextInt(15);
		    	g.setColor(getRandColor(150, 230));
		    	g.drawLine(x, y, x1+x, y1+y);
		    }  
		    //就中文需要将转换成十六进制编码
		    String str ="23456789qwertyuipasdfghjkzxcvbnmQWERTYUIPASDFGHJKZXCVBNM";
		    
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < stringNum; i++) {
				int index = random.nextInt(str.length());
				String rand = String.valueOf(str.charAt(index)); 
				buffer.append(rand);
				//一个绘制，每个文字颜色也不一样，坐标不一样
				g.setFont((new Font("Fixedsys",Font.CENTER_BASELINE,20)));
				g.setColor(getRandColor(100, 180));
				//g.translate(random.nextInt(3), random.nextInt(3));
				g.drawString(rand, 15*i+4, 25);
			}
			//验证码放入session中
			ServletActionContext.getRequest().getSession().removeAttribute("code");
			ServletActionContext.getRequest().getSession().setAttribute("code", buffer.toString().toLowerCase());
			
		    //4:输出图片---输出给浏览器 ,磁盘 new FileOutPutStream("d://aaa.jpg")
		    OutputStream os = ServletActionContext.getResponse().getOutputStream();
		    ImageIO.write(bufferedImage, "JPEG", os);
		    //5:关闭流
		    os.flush();
		    os.close();
		    os = null;
		    //解决getOutputStream() has already been called for this response 的发生
		    ServletActionContext.getResponse().flushBuffer();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 取其某一范围的color
	 * 
	 * @param fc
	 *            int 范围参数1
	 * @param bc
	 *            int 范围参数2
	 * @return Color
	 */
	private Color getRandColor(int fc, int bc) {
		// 取其随机颜色
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
