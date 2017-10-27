package com.qqyycom.ui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import com.qqyycom.listener.CheckListMouseListener;
import com.qqyycom.model.DBModel;
import com.qqyycom.model.PropertyListModel;
import com.qqyycom.model.SelectedItem;
import com.qqyycom.renderer.PropertyListCellRenderer;

@SuppressWarnings("serial")
public class PropertyPanel extends JPanel {
	private JList<DBModel> propertyList;
	
	private List<DBModel> list;
	
	private PropertyListModel listModel;
	/**
	 * Create the panel.
	 */
	public PropertyPanel(List<DBModel> list) {
		this.list = list;
		//TODO test
		list= createModelList();
		listModel = new PropertyListModel(list);
		
		this.setLayout(new BorderLayout(0, 0));
		
		propertyList = new JList<DBModel>(listModel);
		
		propertyList.setLayoutOrientation(JList.VERTICAL_WRAP);
		
		
		JScrollPane scrollPane = new JScrollPane(propertyList);
		this.add(scrollPane);
		
		// …Ë÷√µ•‘™‰÷»æ∆˜
		propertyList.setCellRenderer(new PropertyListCellRenderer(propertyList));
	}
	
	// TODO test
	private List<DBModel> createModelList() {
		ArrayList<DBModel> modelList = new ArrayList<>(4);
		for(int i = 0; i < 4; i++){
			DBModel m = new DBModel("aa"+i,"bb"+i);
			m.setSelected(true);
			modelList.add(m);
		}
		return modelList;
	}

	public JList<DBModel> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(JList<DBModel> propertyList) {
		this.propertyList = propertyList;
	}

	public List<DBModel> getList() {
		return list;
	}

	public PropertyListModel getListModel() {
		return listModel;
	}

	public void freshList(List<DBModel> list) {
		this.list = list;
		listModel = new PropertyListModel(list);
		propertyList.setModel(listModel);
		propertyList.repaint();
	}
	
	

}
