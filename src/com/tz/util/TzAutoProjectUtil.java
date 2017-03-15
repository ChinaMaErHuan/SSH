/**
 * tzdesk系统平台
 * cms
 * com.tz.util
 * TzAutoProjectUtil.java
 * 创建人:maerhuan 
 * 时间：2017年3月3日-下午3:50:48 
 * 2017潭州教育公司-版权所有
 */
package com.tz.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
/**
 * 
 * 自动构建项目的工具类 使用此类的时候要注意这个产生的文件会覆盖原来文件的名称
 * TzAutoProjectUtil
 * 创建人:maerhuan 
 * 时间：2017年3月4日-下午10:58:59 
 * @version 1.0.0
 *
 */
public class TzAutoProjectUtil {
	//注释 作者
	private static String author = "maerhuan";
	//模块
	private static String description = "学生管理";
	//日期
	private static String date = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
	//JavaBean
	private static String entity = "Permission";
	private static String lowEntity = "permission";
	
	//JavaBean存放包
	public static final String ENTITYPACKAGE="src\\com\\tz\\model\\"; 
	//数据库dao存放包
	public static final String DAOPACKAGE="src\\com\\tz\\dao\\"+lowEntity; 
	//数据库实现类存放包
	public static final String DAOIMPLPACKAGE=DAOPACKAGE+"\\impl"; 
	//service存放包
	public static final String SERVICEPACKAGE="src\\com\\tz\\service\\"+lowEntity; 
	//serviceImpl存放包
	public static final String SERVICEIMPLPACKAGE=SERVICEPACKAGE+"\\impl"; 
	//action存放包
	public static final String ACTIONPACKAGE="src\\com\\tz\\web\\"+lowEntity; 
	

	/**
	 * 
	 * 创建实体 com.tz.util 方法名：createEntity 创建人：maerhuan 时间：2017年3月3日-下午4:04:01
	 * void
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	public static void createEntity() {
		try {
			// 获取模板
			String entityTemplate = getPath("template\\entity.txt");
			// 替换数据 准备好将替换好的数据写入到文件
			String result = replaceData(entityTemplate);
			// 要生成的根目录
			String entityRoot = getPath(ENTITYPACKAGE);
			// 新建包文件夹
			File rootPathFile = new File(entityRoot);
			// 如果不存在那么久创建
			if (!rootPathFile.exists())
				rootPathFile.mkdirs();
			// 产生.java文件 产生的是空文件还没有内容
			File entityJava = new File(rootPathFile, entity + ".java");
			// 讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(entityJava, result, "UTF-8");
			System.out.println("产生entity:" + entity + "成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 产生数据库Dao接口文件 com.tz.util 方法名：createDao 创建人：maerhuan
	 * 时间：2017年3月3日-下午7:29:40 void
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	public static void createDao() {
		try {
			// 获取模板
			String daoTemplate = getPath("template\\dao.txt");
			// 替换数据
			String result = replaceData(daoTemplate);
			// 要生成的根目录
			String daoRoot = getPath(DAOPACKAGE);
			// 新建包
			File rootPathFile = new File(daoRoot);
			// 如果不存在那么久创建
			if (!rootPathFile.exists())
				rootPathFile.mkdirs();
			// 产生.java文件
			File daoJava = new File(rootPathFile, "I" + entity + "Dao.java");
			// 讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(daoJava, result, "UTF-8");
			System.out.println("产生dao:" + "I" + entity + "Dao" + "成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 创建数据库实现类文件 com.tz.util 方法名：createDaoImpl 创建人：maerhuan
	 * 时间：2017年3月3日-下午7:39:14 void
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	public static void createDaoImpl() {
		try {
			// 获取模板
			String daoImplTemplate = getPath("template\\daoImpl.txt");
			// 替换数据
			String result = replaceData(daoImplTemplate);
			// 要生成的根目录
			String daoImplRoot = getPath(DAOIMPLPACKAGE);
			// 新建包
			File rootPathFile = new File(daoImplRoot);
			// 如果不存在那么就创建
			if (!rootPathFile.exists())
				rootPathFile.mkdirs();
			// 产生.java文件
			File daoImplJava = new File(rootPathFile, entity + "DaoImpl.java");
			// 讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(daoImplJava, result, "UTF-8");
			System.out.println("产生daoImpl:" + entity + "DaoImpl" + "成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 创建service接口文件 com.tz.util 方法名：createService 创建人：maerhuan
	 * 时间：2017年3月3日-下午8:35:56 void
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	public static void createService() {
		try {
			// 获取模板
			String serviceTemplate = getPath("template\\service.txt");
			// 替换数据
			String result = replaceData(serviceTemplate);
			// 要生成的根目录
			String serviceRoot = getPath(SERVICEPACKAGE);
			// 新建包
			File rootPathFile = new File(serviceRoot);
			// 如果不存在那么久创建
			if (!rootPathFile.exists())
				rootPathFile.mkdirs();
			// 产生.java文件 产生的是空文件还没有内容
			File serviceJava = new File(rootPathFile, "I" + entity
					+ "Service.java");
			// 讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(serviceJava, result, "UTF-8");
			System.out.println("产生service:" + "I" + entity + "Service" + "成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 产生service层实现类 com.tz.util 方法名：createServiceImpl 创建人：maerhuan
	 * 时间：2017年3月3日-下午8:42:24 void
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	public static void createServiceImpl() {
		try {
			// 获取模板
			String serviceImplTemplate = getPath("template\\serviceImpl.txt");
			// 替换数据
			String result = replaceData(serviceImplTemplate);
			// 要生成的根目录
			String serviceImplRoot = getPath(SERVICEIMPLPACKAGE);
			// 新建包
			File rootPathFile = new File(serviceImplRoot);
			// 如果不存在那么久创建
			if (!rootPathFile.exists())
				rootPathFile.mkdirs();
			// 产生.java文件 产生的是空文件还没有内容
			File serviceImplJava = new File(rootPathFile,entity + "ServiceImpl.java");
			// 讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(serviceImplJava, result, "UTF-8");
			System.out
					.println("产生serviceImpl:" + entity + "ServiceImpl" + "成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 产生Action
	 * com.tz.util 
	 * 方法名：createAction
	 * 创建人：maerhuan 
	 * 时间：2017年3月3日-下午10:01:41  void
	 * @exception 
	 * @since  1.0.0
	 */
	public static void createAction() {
		try {
			// 获取文件
			String actionPath = getPath("template\\action.txt");
			// 将模板中的数据替换 并且做好准备 接下来写入
			String result = replaceData(actionPath);
			// 获取包的路径
			String actionRoot = getPath(ACTIONPACKAGE);
			// 创建文件夹
			File rootPathFile = new File(actionRoot);
			if (!rootPathFile.exists()) {
				rootPathFile.mkdirs();
			}
			// 创建.java文件
			File actionFile = new File(rootPathFile, entity + "Action.java");
			// 写入数据
			FileUtils.writeStringToFile(actionFile, result, "UTF-8");
			System.out.println("创建" + entity + "Action" + "成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 产生对应entity的list.jsp文件
	 * com.tz.util 
	 * 方法名：createList
	 * 创建人：maerhuan 
	 * 时间：2017年3月3日-下午10:05:36  void
	 * @exception 
	 * @since  1.0.0
	 */
	public static void createList(){
		try {
			//模板页面
			String listTemplate = getPath("template\\list.txt");
			String result = replaceData(listTemplate);
			//要生成的根目录
			String listRoot =  getPath("WebRoot\\WEB-INF\\pages\\"+lowEntity);
			File rootPath  = new File(listRoot);
			//如果不存在那么久创建
			if(!rootPath.exists())rootPath.mkdirs();
			//产生接口文件
			File listJavaFile = new File(rootPath, "list.jsp");
			//讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(listJavaFile, result, "UTF-8");
			System.out.println("产生"+lowEntity+"模块"+"list.jsp"+"成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 获取文件全路径 com.tz.util 方法名：getPath 创建人：maerhuan 时间：2017年3月3日-下午3:53:17
	 * 
	 * @param appendPath
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public static String getPath(String appendPath) {
		String path = System.getProperty("user.dir");
		if (TzStringUtils.isEmpty(appendPath)) {
			return path;
		} else {

			return path + "\\" + appendPath;
		}
	}

	/**
	 * 
	 * 替换模板中的数据 com.tz.util 方法名：replaceData 创建人：maerhuan 时间：2017年3月3日-下午4:02:40
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 *             String
	 * @exception
	 * @since 1.0.0
	 */
	public static String replaceData(String path) throws IOException {
		String result = FileUtils.readFileToString(new File(path), "UTF-8");
		result = result.replaceAll("\\[entity\\]", entity)
				.replaceAll("\\[lowEntity\\]", lowEntity)
				.replaceAll("\\[author\\]", author)
				.replaceAll("\\[description\\]", description)
				.replaceAll("\\[date\\]", date);
		return result;
	}
	
//	/**
//	 * 构建entity
//	 * @throws IOException
//	 */
//	public static void createEntity() throws IOException{
//		String newClassName = getHomeDir("src/com/tz/model")+"/"+entityClass+".java";
//		String actionTempContent = TmFileUtil.readFile(getHomeDir("src/template")+"entity.txt");
//		new File(newClassName).getParentFile().mkdirs();
//		if(!TmFileUtil.isExist(newClassName)){
//			bulidClass(actionTempContent,newClassName,entitypackage);
//			System.out.println("[TM构建类][Entity]===>  " +newClassName+"  [OK]");
//		}else{
//			System.out.println("[TM构建类][Entity]===>  " +entityClass+".java 该Dao类以及存在是否覆盖[y/n]!");
//			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//			String line = reader.readLine();
//			if(line!=null && line.equalsIgnoreCase("y")){
//				bulidClass(actionTempContent,newClassName,entitypackage);
//				System.out.println("[TM构建类][Entity]===>  " +newClassName+"  [覆盖OK]");
//			}
//		}
//	}
//
	
	public static void main(String[] args) {
		// 1.替换模板 中的数据
		// 2.找到要产生新文件的地方
		// 3.生成新文件
//		createEntity();
//		createDao();
//		createDaoImpl();
//		createService();
//		createServiceImpl();
//		createAction();
//		createList();
//		String pathString  = "com.tz.model";
//		String pathString2 = pathString.replaceAll("\\.", "\\\\");
//		System.out.println(pathString2);
//		createList();
		createAction();
	}

}
