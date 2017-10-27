package com.qqyycom.ui;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.qqyycom.listener.TableListModelListener;
import com.qqyycom.model.PropertyListModel;
import com.qqyycom.model.TableListModel;
import com.qqyycom.model.TableModel;
import com.qqyycom.renderer.TableListCellRenderer;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class TablePanel extends JPanel {

	private List<TableModel> list;
	
	private JList<TableModel> tableList;
	/**
	 * Create the panel.
	 */
	public TablePanel(List<TableModel> list) {
		this.list = list;
		
		setLayout(new BorderLayout(0, 0));
		
		tableList = new JList<>(new TableListModel(list));
		//tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableList.setCellRenderer(new TableListCellRenderer(tableList));
		JScrollPane scrollPane = new JScrollPane(tableList);
		this.add(scrollPane,BorderLayout.WEST);
		
	}
	public List<TableModel> getList() {
		return list;
	}
	public void freshList(List<TableModel> list) {
		this.list = list;
		tableList.setModel(new TableListModel(list));
		tableList.repaint();
		
	}
	public JList<TableModel> getTableList() {
		return tableList;
	}
	public void setTableList(JList<TableModel> tableList) {
		this.tableList = tableList;
	}

	
}
