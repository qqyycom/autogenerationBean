package com.qqyycom.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qqyycom.jdbcConnect.DBUtils;
import com.qqyycom.jdbcConnect.DBUtils.ConnectResource;
import com.qqyycom.model.DBModel;
import com.qqyycom.model.PropertyListModel;
import com.qqyycom.model.TableModel;
import com.qqyycom.ui.PropertyPanel;
import com.qqyycom.ui.TablePanel;
import com.qqyycom.writer.WriterUtils;

/**
 * 业务逻辑层
 * 单例模式  
 * @author QGM
 */
public class ServiceImpl {
	
	private static ServiceImpl serviceImpl = null;
	
	private ServiceImpl(){}
	//静态工厂方法  
	public static synchronized ServiceImpl getInstance(){
		if(serviceImpl == null){
			serviceImpl = new ServiceImpl(); 
		}
		return serviceImpl;
	}
	
	public List<TableModel> convertTableList(ResultSet mrs) throws SQLException {
		List<TableModel> list = new ArrayList<>();
		while(mrs.next()){
			String tableName = mrs.getString(3);
			list.add(new TableModel(tableName));
		}
		return list;
	}

	public void showTables(ResultSet mrs, TablePanel tablePane) throws SQLException {
		List<TableModel> list = convertTableList(mrs);
		tablePane.freshList(list);
		mrs.close();
	}
	
	public void showProperties(ResultSet rs, PropertyPanel propertyPane) throws Exception {
		
		List<DBModel> list = new ArrayList<>();
		while(rs.next()){
			String name = rs.getString("COLUMN_NAME");
			String type = rs.getString("TYPE_NAME");	
			String comment = rs.getString("REMARKS");
			DBModel model = new DBModel(name, type);
			model.setComment(comment);
			// 将状态设为选中
			model.setSelected(true);
			list.add(model);
		}
		propertyPane.freshList(list);
		rs.close();
	}
	
	// 创建bean文件
	public void createFile(List<DBModel> list, String path, String fileName) throws FileNotFoundException, IOException {
		File beanFile = new File(path + File.separator + fileName + ".java");
		try {
			try(OutputStream os = new FileOutputStream(beanFile)){
				WriterUtils.writeBeanFile(fileName, list, os);
			}
		} catch (IOException e) {
			throw e;
		} 
	}
}
