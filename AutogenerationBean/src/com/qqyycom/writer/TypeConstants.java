package com.qqyycom.writer;

import java.util.HashMap;
import java.util.Map;

/**
 * @deprecated  π”√ java.sql.Types¿‡
 * @author QGM
 *
 */
public class TypeConstants {
	public static final String JAVA_String = "java.lang.String";

	public static final String JAVA_Date = "java.util.Date";
	
	public static final String JAVA_Int = "int";
	
	public static final Map<String, String> typeMap = new HashMap<>();
	static{
		typeMap.put("VARCHAR", JAVA_String);
		typeMap.put("CHAR", JAVA_String);
		typeMap.put("DATE", JAVA_Date);
		typeMap.put("DATETIME", JAVA_Date);
		typeMap.put("TIMESTAMP", JAVA_Date);
		typeMap.put("", JAVA_Int);
	}
}
