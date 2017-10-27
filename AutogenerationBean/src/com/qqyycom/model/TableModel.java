package com.qqyycom.model;

public class TableModel implements SelectedItem, SelectEnable{
	private String tableName;

	private boolean selectEnable = true;
	
	private boolean isSelected = true;
	
	public TableModel() {
		super();
	}

	public TableModel(String tableName) {
		super();
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public boolean isSelectEnable() {
		return selectEnable;
	}

	public void setSelectEnable(boolean selectEnable) {
		this.selectEnable = selectEnable;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	
	
	
}
