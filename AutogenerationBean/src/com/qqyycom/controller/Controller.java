package com.qqyycom.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.qqyycom.jdbcConnect.DBUtils;
import com.qqyycom.listener.CheckListMouseListener;
import com.qqyycom.listener.FileNameKeyListener;
import com.qqyycom.listener.TableListModelListener;
import com.qqyycom.model.DBModel;
import com.qqyycom.model.SelectEnable;
import com.qqyycom.model.SelectedItem;
import com.qqyycom.model.TableModel;
import com.qqyycom.service.ServiceImpl;
import com.qqyycom.ui.ButtonPanel;
import com.qqyycom.ui.ConnectPanel;
import com.qqyycom.ui.NamingDialog;
import com.qqyycom.ui.PropertyPanel;
import com.qqyycom.ui.TablePanel;

public class Controller {
	// 连接
	private ConnectPanel connPane;
	// 选择Table区
	private TablePanel tablePane;
	// 选择Property区
	private PropertyPanel propertyPane;
	// 按键区
	private ButtonPanel buttonPane;
	// 路径命名区
	private NamingDialog namingDialog;
	// 业务逻辑
	private ServiceImpl service;

	// 数据库连接
	private Connection conn;
	
	private DatabaseMetaData metaData;

	public Controller(ConnectPanel connPane, TablePanel tablePane, PropertyPanel propertyPane, ButtonPanel buttonPane, NamingDialog namingDialog, JFileChooser selectPathDig) {
		super();
		// 给 connect按键装监听事件
		connPane.getConnectButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 连接数据库
				connectDB();
			}
		});
		// 给TableList装监听使事件
		tablePane.getTableList().addMouseListener(new TableListModelListener() {

			@Override
			public void doubleClick(TableModel item) {
				// System.out.println(item.getTableName());
				// 查询表中字段
				getAllPropertiesByTableName(item.getTableName());
			}
		});
		// 给propertyList添加鼠标监听事件
		propertyPane.getPropertyList().addMouseListener(new CheckListMouseListener() {

			@Override
			public void oneClick(DBModel item) {
				// 将model的状态设置为选中
				if (((SelectEnable) item).isSelectEnable()) {
					item.setSelected(!((SelectedItem) item).isSelected());
				}
			}
		});
		// 给okbutton安装事件监听器
		buttonPane.getOkButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 弹出路径选择和起名对话框
				namingDialog.setVisible(true);
//				int result = selectPathDig.showDialog(propertyPane, "确定");
//				if(result == JFileChooser.APPROVE_OPTION){// 如果选中了保存按钮
//					System.out.println(result);
//					
//				}
			}
		});
		// TODO 给cancelbutton安装监听事件 
		buttonPane.getCancelButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// 给NamingDialog安装动作监听器  TODO  cancelButton
		namingDialog.getOkButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取选中的propertyModel
				List<DBModel> list = propertyPane.getListModel().getSelectedPropertyList();
				// 获取选中路径
				String path = namingDialog.getFileUrlTextField().getText();
				// 获取文件名称
				String fileName = namingDialog.getFileNameTextField().getText();
				// 创建文件
				createBeanClassFile(list, path, fileName);
				
			}
		});
		namingDialog.getSelectPathButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 弹出路径选择框
				int result = selectPathDig.showDialog(namingDialog, "确定");
				if(result == JFileChooser.APPROVE_OPTION){// 如果选中了"确定"按钮
					// 获取选中的路径
					String path = selectPathDig.getSelectedFile().getAbsolutePath();
					// 将路径值放入对应的位置
					namingDialog.getFileUrlTextField().setText(path);
					//System.out.println(path);
				}
			}
		});
		namingDialog.getFileNameTextField().addKeyListener(new FileNameKeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				// 键盘弹起时,检验是否与当前目录下的文件重名
				String SelectedPath = namingDialog.getFileUrlTextField().getText();
				String InputFileName = namingDialog.getFileNameTextField().getText();
				// 如果没有选择路径
				if(SelectedPath == null || "".equals(SelectedPath)){
					namingDialog.getMessageArea().setText("请先选择文件路径!");
					return;
				}
				// 
				String[] fileNames = null;
				try {
					fileNames = getJavaFileNamesInSpecifiedPath(SelectedPath);
				} catch (IOException e1) {
					namingDialog.getMessageArea().setText("请选择文件夹作为文件路径!");
					return;
				}
				if(checkRepeatedFileName(fileNames, InputFileName)){
					namingDialog.getMessageArea().setText("该Bean文件已存在,请重新命名");
				}else{
					namingDialog.getMessageArea().setText("");
				}
				
			}
		});
		this.connPane = connPane;
		this.tablePane = tablePane;
		this.propertyPane = propertyPane;
		this.buttonPane = buttonPane;
		this.namingDialog = namingDialog;
		this.service = ServiceImpl.getInstance();
	}

	// 检验输入的文件名,是否与当前文件夹下的文件名重名
	protected boolean checkRepeatedFileName(String[] fileNames, String inputFileName) {
		if(fileNames == null ) return false;
		if(fileNames.length == 0) return false;
		for (String name : fileNames) {
			if(inputFileName.equals(name.substring(0, name.length()-".java".length()))){
				return true;
			}
		}
		return false;
	}

	protected String[] getJavaFileNamesInSpecifiedPath(String SelectedPath) throws IOException {
		File dir = new File(SelectedPath);
		if(!dir.isDirectory()){
			
			throw new IOException("请选择文件夹作为文件路径!");
		}
		String[] fileNames =  dir.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				if(name.contains(".java")) return true;
				return false;
			}
		});
		return fileNames;
	}

	// 根据list创建bean文件
	public void createBeanClassFile(List<DBModel> list, String path, String fileName) {
		try {
			service.createFile(list, path, fileName);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(connPane, e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(connPane, e.getMessage());
			e.printStackTrace();
		}
		namingDialog.setVisible(false);
		JOptionPane.showMessageDialog(connPane, "创建成功");
	}

	// 根据表名获取表中所有字段,并显示在propertyList中
	public void getAllPropertiesByTableName(String tableName) {
		try {
			ResultSet rs = DBUtils.getTableResultSet(metaData, tableName);
			service.showProperties(rs, propertyPane);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(connPane, e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(connPane, e.getMessage());
			e.printStackTrace();
		}
	}

	// 连接数据库,并将所有表显示到TableList中
	public void connectDB() {
		String url = connPane.getUrlTextField().getText();
		String userName = connPane.getNameTextField().getText();
		String password = connPane.getPasswdTextField().getText();

		try {
			conn = DBUtils.getDBConnection(url, userName, password);
			if(metaData == null)
				metaData = conn.getMetaData();
			ResultSet mrs = metaData.getTables(null, null, null, new String[] { "TABLE" });
			service.showTables(mrs, tablePane);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(connPane, e.getMessage());
			e.printStackTrace();
		}
	}

}
