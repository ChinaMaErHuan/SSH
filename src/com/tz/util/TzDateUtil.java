package com.tz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 日期工具类
 * 
 * TzDateUtil
 * 创建人:maerhuan 
 * 时间：2017年3月2日-下午11:42:27 
 * @version 1.0.0
 *
 */
public class TzDateUtil {
	/**
	 * 
	 * 判断一年是否为闰年
	 * com.tz.util 
	 * 方法名：isLeapYear
	 * 创建人：maerhuan 
	 * 时间：2017年3月2日-下午11:41:03 
	 * @param year
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isLeapYear(int year){
		return (year%4==0 && year%100!=0)  ||  year%400==0;
	}
	
	/**
	 * 
	 * 获取一年中某月的天数
	 * com.tz.util 
	 * 方法名：getMonthDay
	 * 创建人：maerhuan 
	 * 时间：2017年3月2日-下午11:41:39 
	 * @param year
	 * @param month
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public static int getMonthDay(int year,int month){
		int result = 31;	
		switch (month) {
				case 4:
				case 6:
				case 9:
				case 11:
					result = 30;
				break;
			case 2:
				result = TzDateUtil.isLeapYear(year)?29:28;
				break;
			default:
				result = 31;
				break;
		}
		return result;	
	}
	/**
	 * 日期转换 方法名：stringToDate<BR>
	 * 创建人：潭州学院-keke <BR>
	 * 时间：2014年11月11日-下午10:28:41 <BR>
	 * 
	 * @param time
	 * @return Date<BR>
	 * @exception <BR>
	 * @since 1.0.0
	 */
	public static Date stringToDate(String dateString, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 日期转换字符串 com.tz.util 方法名：dateToString 创建人：maerhuan 时间：2017年3月2日-下午11:34:30
	 * 
	 * @param date
	 * @param pattern
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public static String dateToString(Date date, String pattern) {
		if (date != null) {
			return new SimpleDateFormat(pattern).format(date);

		} else {
			return "";
		}
	}

	/**
	 * 
	 * 方法名：getTimeFormat<BR>
	 * 创建人：潭州学院-keke <BR>
	 * 时间：2014年11月11日-下午10:28:21 <BR>
	 * 
	 * @param startTime
	 * @return String<BR>
	 * @exception <BR>
	 * @since 1.0.0
	 */
	public static String getTimeFormat(String startTime) {
		return getTimeFormat(stringToDate(startTime, "yyyy-MM-dd HH:mm:ss"));
	}
	
	
	

	/**
	 * 获取日期几分钟前，几年前 方法名：getTimeFormat<BR>
	 * 创建人：潭州学院-keke <BR>
	 * 时间：2014年11月11日-下午10:27:54 <BR>
	 * 
	 * @param startTime
	 * @return String<BR>
	 * @exception <BR>
	 * @since 1.0.0
	 */
	public static String getTimeFormat(Date startTime) {
		try {
			long startTimeMills = startTime.getTime();
			long endTimeMills = System.currentTimeMillis();
			long diff = (endTimeMills - startTimeMills) / 1000;// 秒
			long day_diff = (long) Math.floor(diff / 86400);// 天
			StringBuffer buffer = new StringBuffer();
			if (day_diff < 0) {
				return "[error],时间越界...";
			} else {
				if (day_diff == 0 && diff < 60) {
					if (diff == 0)
						diff = 1;
					buffer.append(diff + "秒前");
				} else if (day_diff == 0 && diff < 120) {
					buffer.append("1 分钟前");
				} else if (day_diff == 0 && diff < 3600) {
					buffer.append(Math.round(Math.floor(diff / 60)) + "分钟以前");
				} else if (day_diff == 0 && diff < 7200) {
					buffer.append("1小时前");
				} else if (day_diff == 0 && diff < 86400) {
					buffer.append(Math.round(Math.floor(diff / 3600)) + "小时前");
				} else if (day_diff == 1) {
					buffer.append("1天前");
				} else if (day_diff < 7) {
					buffer.append(day_diff + "天前");
				} else if (day_diff < 30) {
					buffer.append(Math.round(Math.ceil(day_diff / 7)) + " 星期前");
				} else if (day_diff >= 30 && day_diff <= 179) {
					buffer.append(Math.round(Math.ceil(day_diff / 30)) + "月前");
				} else if (day_diff >= 180 && day_diff < 365) {
					buffer.append("半年前");
				} else if (day_diff >= 365) {
					buffer.append(Math.round(Math.ceil(day_diff / 30 / 12))
							+ "年前");
				}
			}
			return buffer.toString();
		} catch (Exception ex) {
			return "";
		}
	}
}
