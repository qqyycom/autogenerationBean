package com.qqyycom.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ListModel;

public class PropertyListModel extends AbstractListModel<DBModel> {

	private static final long serialVersionUID = 2823007500065500722L;
	
	List<DBModel> list;
	
	public PropertyListModel(List<DBModel> list) {
		super();
		this.list = list;
	}

	@Override
	public DBModel getElementAt(int index) {
		if(list == null ) return null;
		return list.get(index);
	}

	@Override
	public int getSize() {
		if(list == null ) return 0;
		return list.size();
	}

	public List<DBModel> getSelectedPropertyList() {
		List<DBModel> selectedList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if (((SelectedItem) list.get(i)).isSelected()) {
				selectedList.add(list.get(i));
				System.out.println(list.get(i).getName());
			}
		}
		return selectedList;
	}
}
