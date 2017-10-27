package com.qqyycom.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.qqyycom.controller.Controller;
import com.qqyycom.model.DBModel;
import com.qqyycom.model.TableModel;

@SuppressWarnings("serial")
public class Frame extends JFrame {

	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setTitle("bean\u81EA\u52A8\u751F\u6210\u5668");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// ������
		ButtonPanel buttonePane = new ButtonPanel();
		contentPane.add(buttonePane, BorderLayout.SOUTH);
		
		// ����ѡ����
		ConnectPanel connectPane = new ConnectPanel();
		contentPane.add(connectPane, BorderLayout.NORTH);
		
		// ѡ��Table�� ˫��ѡ���, ѡ������ظ�����
		List<TableModel> list = new ArrayList<TableModel>();
		for (int i = 0; i < 4; i++) {
			list.add(new TableModel("aaa"+i));
		}
		TablePanel tablePanel = new TablePanel(list);
		contentPane.add(tablePanel, BorderLayout.WEST);
		
		// ѡ���ֶ�����,ѡ��Table��,�滻Table����
		PropertyPanel propertyPane = new PropertyPanel(new ArrayList<DBModel>());
		contentPane.add(propertyPane, BorderLayout.CENTER);
		
		// ������������
		NamingDialog namingDialog = new NamingDialog(this);
		
		// ����·��ѡ���
		JFileChooser selectPathDig = new SelectPathDialog();
		
		// ��װ������
		new Controller(connectPane, tablePanel, propertyPane, buttonePane, namingDialog, selectPathDig);
	}

}
