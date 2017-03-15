/**
 * tzdesk系统平台
 * cms
 * com.tz.util
 * TzGatherContentUtil.java
 * 创建人:maerhuan 
 * 时间：2017年3月10日-下午2:43:01 
 * 2017潭州教育公司-版权所有
 */
package com.tz.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;



public class TzGatherContentUtil {
	private final static Whitelist user_content_filter = Whitelist.relaxed(); 
	static {       
		//增加可信标签到白名单        
		user_content_filter.addTags("embed","object","param","span","div");     //增加可信属性    
		user_content_filter.addAttributes(":all", "style", "class", "id", "name");      
		user_content_filter.addAttributes("object", "width", "height","classid","codebase");      
		user_content_filter.addAttributes("param", "name", "value");      
		user_content_filter.addAttributes("embed", "src","quality","width","height","allowFullScreen","allowScriptAccess","flashvars","name","type","pluginspage"); 
	}
	/**
     * 根据网页和网页编码获取网页内容.
     * 
     * @param url
     * @param encoding
     * @return
     */
    public static String getHtmlResourceByURL(String url, String encoding) {
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        InputStreamReader in = null;
        URLConnection uc = null;
        URL urlObj = null;
        try {
            urlObj = new URL(url);
            uc = urlObj.openConnection();
//            System.out.println(uc.getContentEncoding());
            uc.setRequestProperty("User-Agent", "java");
            in = new InputStreamReader(uc.getInputStream(), encoding);
            reader = new BufferedReader(in);
            String line = null;
            while ((line = reader.readLine()) != null) {
            	if (!line.trim().equals("")){//去掉空格行
                buffer.append(line + "\r\n");
            	}
            }
        } catch (Exception e) {
            return "connection timeout....";
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer.toString();
    }
    /**
     * 
     * 抓取网页源代码</br>
     * com.tz.util </br>
     * 方法名：getHTMLSource </br>
     * 创建人：maerhuan </br>
     * 时间：2017年3月10日-下午3:16:25 </br>
     * @param link
     * @return String
     * @exception 
     * @since  1.0.0
     */
    public static String getHTMLSource(String link,String encoding) {
		try {
			// 第一步：初始化以URL对象
			URL url = new URL(link);
			// 第二步：获取打开URL和java程序之间连接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			// connection.setConnectTimeout(6000);//6秒之内如果没有响应，此次请求结束,如果不设定是一致等待
			// 伪装浏览器的方式去抓取网络信息
			connection
					.setRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
			StringBuilder builder = new StringBuilder();
			try (
					InputStream inputStream = connection.getInputStream();
					InputStreamReader isReader = new InputStreamReader(
							inputStream,encoding);
					BufferedReader reader = new BufferedReader(isReader);

			) {
				String lineString = "";
				while ((lineString = reader.readLine()) != null) {
					if (!lineString.trim().equals("")){//去掉空格行
					builder.append(lineString + "\r\n");
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return builder.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
    
	public static void main(String[] args) throws MalformedURLException, IOException {
		String url = "http://news.qq.com/";
		String filterUrl  = "http://news.qq.com/a/";
		//第一步 导包
		//2.根据网站url获取网页源代码
		Document document = Jsoup.parse(getHTMLSource(url, "UTF-8"));
//		System.out.println(document.html());
		//3.抓取网页中所需要的url
		Elements links = document.getElementsByTag("a");
		Set<String> urls = new HashSet<String>();
		for(Element element:links){
			String href = element.attr("href");
			if(TzStringUtils.isNotEmpty(href) && href.startsWith(filterUrl)){
				urls.add(href);
			}
		}
		//第四步：解析匹配出来的URL，讲需要的匹配出来
		for (String string : urls) {
			try {
				Document document2 = Jsoup.parse(getHTMLSource(string, "GBK"));
				String title = document2.getElementsByTag("h1").get(0).text();
				String content = document2.getElementById("Cnt-Main-Article-QQ").html();
				System.out.println(title);
				System.out.println(Jsoup.clean(content, Whitelist.simpleText()));
			} catch (Exception e) {
				continue;
			}
		}
		
		//第五步：保存数据库
		//在junit中保存
	}
}
