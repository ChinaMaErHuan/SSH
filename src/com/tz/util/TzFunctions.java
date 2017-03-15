package com.tz.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

/**
 * jsp函数自定义标签
 * 
 * TzFunctions
 * 创建人:maerhuan 
 * 时间：2017年3月2日-下午11:21:36 
 * @version 1.0.0
 *
 */
public class TzFunctions {

	/**
	 * 
	 * 方法名：toMap 创建人：xuchengfei 时间：2015年4月16日-上午12:42:53
	 * 
	 * @param content
	 *            17#安徽省#1.00000#2.00000
	 * @return String <a href="javascript:void(0)" class='h_maps_area'
	 *         data-x='1' data-y='2'>安徽省<\\/a>
	 * @exception
	 * @since 1.0.0
	 */
	public static String toMap(String content) {
		if (TzStringUtils.isEmpty(content))
			return null;
		String[] areas = content.split("#");
		return "<a href='javascript:void(0)' data-id='" + areas[0]
				+ "' class='area-items' data-x='" + areas[2] + "' data-y='"
				+ areas[3] + "'>" + areas[1] + "</a>";
	}

	/**
	 * 
	 * 字符串转换成大写 com.tz.util 方法名：toUpper 创建人：maerhuan 时间：2017年3月2日-下午11:12:54
	 * 
	 * @param content
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public static String toUpper(String content) {
		if (TzStringUtils.isEmpty(content))
			return null;
		return content.toUpperCase();
	}

	/**
	 * 
	 * 空判断 com.tz.util 方法名：isEmpty 创建人：maerhuan 时间：2017年3月2日-下午11:14:11
	 * 
	 * @param content
	 * @return boolean
	 * @exception
	 * @since 1.0.0
	 */
	public static boolean isEmpty(String content) {
		return TzStringUtils.isEmpty(content);
	}

	/**
	 * 
	 * 非空判断 com.tz.util 方法名：isNotEmpty 创建人：maerhuan 时间：2017年3月2日-下午11:14:32
	 * 
	 * @param content
	 * @return boolean
	 * @exception
	 * @since 1.0.0
	 */
	public static boolean isNotEmpty(String content) {
		return !isEmpty(content);
	}

	/**
	 * 
	 * 星期几转换 com.tz.util 方法名：getWeekChinesee 创建人：maerhuan
	 * 时间：2017年3月2日-下午11:14:59
	 * 
	 * @param week
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public static String getWeekChinesee(int week) {
		String cweek = "";
		if (week == 1)
			cweek = "一";
		if (week == 2)
			cweek = "二";
		if (week == 3)
			cweek = "三";
		if (week == 4)
			cweek = "四";
		if (week == 5)
			cweek = "五";
		if (week == 6)
			cweek = "六";
		if (week == 7)
			cweek = "日";
		return cweek;
	}

	/**
	 * 
	 * 根据索引转换字母</br> com.tz.util </br> 方法名：getCharacter</br> 创建人：maerhuan </br>
	 * 时间：2017年3月2日-下午11:15:17 </br>
	 * 
	 * @param num
	 * @return String</br>
	 * @exception </br>
	 * @since 1.0.0
	 */
	public static String getCharacter(int num) {
		return String.valueOf((char) (64 + num));
	}

	/**
	 * 日期具体的几秒钟以前 方法名：dateToString<BR>
	 * 创建人：maerhuan <BR>
	 * 时间：2014年11月11日-下午10:34:33 <BR>
	 * 
	 * @param startTime
	 * @return String<BR>
	 * @exception <BR>
	 * @since 1.0.0
	 */
	public static String dateToString(String startTime) {
		return TzDateUtil.getTimeFormat(startTime);
	}

	/**
	 * 日期具体的几秒钟以前 方法名：dateToString2<BR>
	 * 创建人：maerhuan <BR>
	 * 时间：2014年11月11日-下午10:38:06 <BR>
	 * 
	 * @param startTime
	 * @return String<BR>
	 * @exception <BR>
	 * @since 1.0.0
	 */
	public static String dateToString2(Date startTime) {
		return TzDateUtil.getTimeFormat(startTime);
	}

	/**
	 * 格式化日期的自定义函数 方法名：dateFormat<BR>
	 * 创建人：潭州学院-keke <BR>
	 * 时间：2014年11月11日-下午9:57:18 <BR>
	 * 
	 * @param dateString
	 * @param format
	 * @return String<BR>
	 * @exception <BR>
	 * @since 1.0.0 System.out.println(dateFormat("2014-12-14 12:12:12",
	 *        "yyyy"));年份 System.out.println(dateFormat("2014-12-14 12:12:12",
	 *        "MM"));月份 System.out.println(dateFormat("2014-12-14 12:12:12",
	 *        "dd"));天 System.out.println(dateFormat("2014-12-14 12:12:12",
	 *        "HH:mm:ss"));24小时制
	 *        System.out.println(dateFormat("2014-12-14 12:12:12",
	 *        "hh:mm:ss"));12小时制
	 *        System.out.println(dateFormat("2014-12-14 12:12:12",
	 *        "yyyy-MM-dd"));年月日
	 *        System.out.println(dateFormat("2014-12-14 12:12:12",
	 *        "yyyy-MM-dd HH:mm"));年月日 时分
	 *        System.out.println(dateFormat("2014-12-14 12:12:12",
	 *        "yyyy-MM-dd HH:mm:ss"));年月日 时分秒
	 */
	public static String dateFormat(String dateString, String format) {
		if (TzStringUtils.isEmpty(dateString))
			return "";
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(dateString);
			String ds = new SimpleDateFormat(format).format(date);
			return ds;
		} catch (ParseException e) {
			return "";
		}
	}

	/**
	 * 
	 * 方法名：formateDate<BR>
	 * 创建人：潭州学院-keke <BR>
	 * 时间：2014年11月23日-上午2:29:40 <BR>
	 * 
	 * @param date
	 * @param format
	 * @return String<BR>
	 * @exception <BR>
	 * @since 1.0.0
	 */
	public static String formateDate(Date date, String format) {
		if (date == null)
			return "";
		String ds = new SimpleDateFormat(format).format(date);
		return ds;
	}

	/**
	 * 将数字转换成对应的中文 方法名：intToChnNumConverter<BR>
	 * 创建人：maerhuan <BR>
	 * 时间：2014年11月11日-下午10:33:30 <BR>
	 * 
	 * @param num
	 * @return String<BR>
	 * @exception <BR>
	 * @since 1.0.0
	 */
	public static String intToChnNumConverter(int num) {
		String resultNumber = null;
		if (num > 10000 || num < 0) {
			return "";
		}
		HashMap<Integer, String> chnNumbers = new HashMap<Integer, String>();
		chnNumbers.put(0, "零");
		chnNumbers.put(1, "一");
		chnNumbers.put(2, "二");
		chnNumbers.put(3, "三");
		chnNumbers.put(4, "四");
		chnNumbers.put(5, "五");
		chnNumbers.put(6, "六");
		chnNumbers.put(7, "七");
		chnNumbers.put(8, "八");
		chnNumbers.put(9, "九");

		HashMap<Integer, String> unitMap = new HashMap<Integer, String>();
		unitMap.put(1, "");
		unitMap.put(10, "十");
		unitMap.put(100, "百");
		unitMap.put(1000, "千");
		int[] unitArray = { 1000, 100, 10, 1 };

		StringBuilder result = new StringBuilder();
		int i = 0;
		while (num > 0) {
			int n1 = num / unitArray[i];
			if (n1 > 0) {
				result.append(chnNumbers.get(n1)).append(
						unitMap.get(unitArray[i]));
			}
			if (n1 == 0) {
				if (result.lastIndexOf("零") != result.length() - 1) {
					result.append("零");
				}
			}
			num = num % unitArray[i++];
			if (num == 0) {
				break;
			}
		}
		resultNumber = result.toString();
		if (resultNumber.startsWith("零")) {
			resultNumber = resultNumber.substring(1);
		}
		if (resultNumber.startsWith("一十")) {
			resultNumber = resultNumber.substring(1);
		}
		return resultNumber;
	}

	/**
	 * 
	 * 计算文件的大小 com.tz.util 方法名：countFileSize 创建人：maerhuan
	 * 时间：2017年2月24日-上午10:59:54
	 * 
	 * @param fileSize
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public static String countFileSize(long fileSize) {
		String fileSizeString = "";
		try {
			DecimalFormat df = new DecimalFormat("#.00");
			long fileS = fileSize;
			if (fileS == 0) {
				fileSizeString = "0KB";
			} else if (fileS < 1024) {
				fileSizeString = df.format((double) fileS) + "B";
			} else if (fileS < 1048576) {
				fileSizeString = df.format((double) fileS / 1024) + "KB";
			} else if (fileS < 1073741824) {
				fileSizeString = df
						.format(((double) fileS / 1024 / 1024) - 0.01) + "MB";
			} else {
				fileSizeString = df.format((double) fileS / 1024 / 1024 / 1024)
						+ "G";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileSizeString;
	}

	/**
	 * 
	 * 方法名：cutContent<BR>
	 * 创建人：潭州学院-keke <BR>
	 * 时间：2014年11月14日-上午12:27:16 <BR>
	 * 
	 * @param content
	 * @param begin
	 * @param end
	 * @return String<BR>
	 * @exception <BR>
	 * @since 1.0.0
	 */
	public static String cutContent(String content, int begin, int end) {
		if (TzStringUtils.isEmpty(content))
			return "";
		String start = "";
		String result = "";
		if (content.length() > end) {
			start = content.substring(begin, end);
			result = "<span style='display:none;'>"
					+ content.substring(100, content.length()) + "</span>";
			return start
					+ result
					+ "&nbsp;&nbsp;<a onclick='tm_show_expand(this)' href='javascript:void(0)'>展开</a>";
		} else {
			return content;
		}
	}

	/**
	 * 获取集合的长度 方法名：length<BR>
	 * 创建人：潭州学院-keke <BR>
	 * 时间：2014年11月23日-下午11:33:53 <BR>
	 * 
	 * @param list
	 * @return int<BR>
	 * @exception <BR>
	 * @since 1.0.0
	 */
	public static int getLength(Collection<?> collection) {
		if (collection != null) {
			return collection.size();
		} else {
			return 0;
		}
	}
	/**
	 * 
	 * 扩展字符串判断的indexOf方法</br>
	 * com.tz.util </br>
	 * 方法名：indexOf </br>
	 * 创建人：maerhuan </br>
	 * 时间：2017年3月10日-下午6:06:25 </br>
	 * @param content
	 * @param searchStr
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public static int indexOf(String content,String searchStr){
		if(TzStringUtils.isEmpty(content)) return -1;
		return content.indexOf(searchStr) ;
	}
}
