package com.qqyycom.model;

import java.util.List;

import javax.swing.AbstractListModel;

@SuppressWarnings("serial")
public class TableListModel extends AbstractListModel<TableModel> {

	private List<TableModel> list;
	
	public TableListModel(List<TableModel> list) {
		super();
		this.list = list;
	}
	
	@Override
	public int getSize() {
		if (list == null) return 0;
		return list.size();
	}

	@Override
	public TableModel getElementAt(int index) {
		if (list == null) return null;
		return list.get(index);
	}

	
}
