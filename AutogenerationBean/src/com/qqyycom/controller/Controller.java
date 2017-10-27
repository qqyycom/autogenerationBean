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
	// ����
	private ConnectPanel connPane;
	// ѡ��Table��
	private TablePanel tablePane;
	// ѡ��Property��
	private PropertyPanel propertyPane;
	// ������
	private ButtonPanel buttonPane;
	// ·��������
	private NamingDialog namingDialog;
	// ҵ���߼�
	private ServiceImpl service;

	// ���ݿ�����
	private Connection conn;
	
	private DatabaseMetaData metaData;

	public Controller(ConnectPanel connPane, TablePanel tablePane, PropertyPanel propertyPane, ButtonPanel buttonPane, NamingDialog namingDialog, JFileChooser selectPathDig) {
		super();
		// �� connect����װ�����¼�
		connPane.getConnectButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �������ݿ�
				connectDB();
			}
		});
		// ��TableListװ����ʹ�¼�
		tablePane.getTableList().addMouseListener(new TableListModelListener() {

			@Override
			public void doubleClick(TableModel item) {
				// System.out.println(item.getTableName());
				// ��ѯ�����ֶ�
				getAllPropertiesByTableName(item.getTableName());
			}
		});
		// ��propertyList����������¼�
		propertyPane.getPropertyList().addMouseListener(new CheckListMouseListener() {

			@Override
			public void oneClick(DBModel item) {
				// ��model��״̬����Ϊѡ��
				if (((SelectEnable) item).isSelectEnable()) {
					item.setSelected(!((SelectedItem) item).isSelected());
				}
			}
		});
		// ��okbutton��װ�¼�������
		buttonPane.getOkButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ����·��ѡ��������Ի���
				namingDialog.setVisible(true);
//				int result = selectPathDig.showDialog(propertyPane, "ȷ��");
//				if(result == JFileChooser.APPROVE_OPTION){// ���ѡ���˱��水ť
//					System.out.println(result);
//					
//				}
			}
		});
		// TODO ��cancelbutton��װ�����¼� 
		buttonPane.getCancelButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// ��NamingDialog��װ����������  TODO  cancelButton
		namingDialog.getOkButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ȡѡ�е�propertyModel
				List<DBModel> list = propertyPane.getListModel().getSelectedPropertyList();
				// ��ȡѡ��·��
				String path = namingDialog.getFileUrlTextField().getText();
				// ��ȡ�ļ�����
				String fileName = namingDialog.getFileNameTextField().getText();
				// �����ļ�
				createBeanClassFile(list, path, fileName);
				
			}
		});
		namingDialog.getSelectPathButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ����·��ѡ���
				int result = selectPathDig.showDialog(namingDialog, "ȷ��");
				if(result == JFileChooser.APPROVE_OPTION){// ���ѡ����"ȷ��"��ť
					// ��ȡѡ�е�·��
					String path = selectPathDig.getSelectedFile().getAbsolutePath();
					// ��·��ֵ�����Ӧ��λ��
					namingDialog.getFileUrlTextField().setText(path);
					//System.out.println(path);
				}
			}
		});
		namingDialog.getFileNameTextField().addKeyListener(new FileNameKeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				// ���̵���ʱ,�����Ƿ��뵱ǰĿ¼�µ��ļ�����
				String SelectedPath = namingDialog.getFileUrlTextField().getText();
				String InputFileName = namingDialog.getFileNameTextField().getText();
				// ���û��ѡ��·��
				if(SelectedPath == null || "".equals(SelectedPath)){
					namingDialog.getMessageArea().setText("����ѡ���ļ�·��!");
					return;
				}
				// 
				String[] fileNames = null;
				try {
					fileNames = getJavaFileNamesInSpecifiedPath(SelectedPath);
				} catch (IOException e1) {
					namingDialog.getMessageArea().setText("��ѡ���ļ�����Ϊ�ļ�·��!");
					return;
				}
				if(checkRepeatedFileName(fileNames, InputFileName)){
					namingDialog.getMessageArea().setText("��Bean�ļ��Ѵ���,����������");
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

	// ����������ļ���,�Ƿ��뵱ǰ�ļ����µ��ļ�������
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
			
			throw new IOException("��ѡ���ļ�����Ϊ�ļ�·��!");
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

	// ����list����bean�ļ�
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
		JOptionPane.showMessageDialog(connPane, "�����ɹ�");
	}

	// ���ݱ�����ȡ���������ֶ�,����ʾ��propertyList��
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

	// �������ݿ�,�������б���ʾ��TableList��
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
