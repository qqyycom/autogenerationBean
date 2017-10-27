package com.qqyycom.writer;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Types;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.qqyycom.model.DBModel;

public class WriterUtils {
	
	private static String pattern1 = "_[a-z|A-Z]";
	private static String pattern2 = "[a-z]";
	private static Pattern p1 = Pattern.compile(pattern1);
	private static Pattern p2 = Pattern.compile(pattern2);
	/**
	 * 写文件
	 * @throws IOException 
	 */
	public static void writeBeanFile(String beanName, List<DBModel> propertyList, OutputStream os) throws IOException{
		//写class头
		os.write(createJavaClassHeader(beanName).getBytes());
		//写属性
		for (DBModel model : propertyList) {
			
			os.write(createJavaProperty(model).getBytes());
			
		}
		// 写getset方法
		
		// 写最后一个括号
		os.write("}".getBytes());
		
	}
	
	/**
	 * 生成class头
	 */
	public static String createJavaClassHeader(String className){
		String header = "public class "+className+"{\n";
		return header;
	}
	
	/**
	 *  生成java属性代码
	 * @param model
	 * @return
	 */
	public static String createJavaProperty(DBModel model){
		// 使用java.sql.Types把数据库类型转化成java类型
		String type = typeProcessor(model.getType());
		// 驼峰命名法命名变量名
		String propName = convertCamelCaseName(model.getName());
		// 生成beanclass
		String propertyString = "\tprivate\t"+type+"\t"+propName+";\t//"+ model.getComment() + "\n";
		return propertyString;
	}
	
	/**
	 * 生成getset方法
	 */
	public static String createGetterSetter(){
		return null;
	}
	
	/**
	 * 将数据类型转化成java中的数据类型
	 * @param type
	 * @return
	 */ 
	public static String typeProcessor(String sqlType) {
		String newType;
		if (sqlType.equalsIgnoreCase("varchar"))
			newType = "java.lang.String";
		else if (sqlType.equalsIgnoreCase("char"))
			newType = "java.lang.String";
		else if (sqlType.equalsIgnoreCase("bigint"))
			newType = "long";
		else if (sqlType.equalsIgnoreCase("smallint"))
			newType = "int";
		else if (sqlType.equalsIgnoreCase("integer"))
			newType = "int";
		else if (sqlType.equalsIgnoreCase("decimal"))
			newType = "double";
		else if (sqlType.equalsIgnoreCase("date"))
			newType = "java.util.Date";
		else if (sqlType.equalsIgnoreCase("dateTime"))
			newType = "java.util.Date";
		else if (sqlType.equalsIgnoreCase("timestamp"))
			newType = "java.util.Date";
		else {
			newType = sqlType;
		}
		return newType;
	}

	/**
	 * 将数据库字段名用驼峰命名法命名
	 */
	public static String convertCamelCaseName(String propertyName) {
		
		Matcher m = p1.matcher(propertyName);
		StringBuffer sb = new StringBuffer();
		while(m.find()){
			String replacement = convertToUppercase(m.group());
			m.appendReplacement(sb, replacement);
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 获取_后字母的大写
	 */
	private static String convertToUppercase(String unPatterned) {
		
		Matcher m = p2.matcher(unPatterned);
		if (m.find()) {
			char lowercase = m.group().charAt(0);
			return new Character(toUpper(lowercase)).toString();
		} else {
			return unPatterned.replace("_", "");
		}
	}

	/**
	 * 小写转大写
	 */
	public static char toUpper(char lower) {
		char upper = (char) (lower & 223); // 与运算将第5位置0,223二进制1101 1111
		return upper;
	}

	/** 
	 *大写转小写 
	 */  
	public static char toLower(char upper){  
	    char lower = (char) (upper |32 ); //或运算将第5位置1,32二进制0010 0000  
		return lower;
	}
	
}
