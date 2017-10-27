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
	 * д�ļ�
	 * @throws IOException 
	 */
	public static void writeBeanFile(String beanName, List<DBModel> propertyList, OutputStream os) throws IOException{
		//дclassͷ
		os.write(createJavaClassHeader(beanName).getBytes());
		//д����
		for (DBModel model : propertyList) {
			
			os.write(createJavaProperty(model).getBytes());
			
		}
		// дgetset����
		
		// д���һ������
		os.write("}".getBytes());
		
	}
	
	/**
	 * ����classͷ
	 */
	public static String createJavaClassHeader(String className){
		String header = "public class "+className+"{\n";
		return header;
	}
	
	/**
	 *  ����java���Դ���
	 * @param model
	 * @return
	 */
	public static String createJavaProperty(DBModel model){
		// ʹ��java.sql.Types�����ݿ�����ת����java����
		String type = typeProcessor(model.getType());
		// �շ�����������������
		String propName = convertCamelCaseName(model.getName());
		// ����beanclass
		String propertyString = "\tprivate\t"+type+"\t"+propName+";\t//"+ model.getComment() + "\n";
		return propertyString;
	}
	
	/**
	 * ����getset����
	 */
	public static String createGetterSetter(){
		return null;
	}
	
	/**
	 * ����������ת����java�е���������
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
	 * �����ݿ��ֶ������շ�����������
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
	 * ��ȡ_����ĸ�Ĵ�д
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
	 * Сдת��д
	 */
	public static char toUpper(char lower) {
		char upper = (char) (lower & 223); // �����㽫��5λ��0,223������1101 1111
		return upper;
	}

	/** 
	 *��дתСд 
	 */  
	public static char toLower(char upper){  
	    char lower = (char) (upper |32 ); //�����㽫��5λ��1,32������0010 0000  
		return lower;
	}
	
}
