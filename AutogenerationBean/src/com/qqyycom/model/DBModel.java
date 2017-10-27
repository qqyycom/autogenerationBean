package com.qqyycom.model;

public class DBModel implements SelectEnable, SelectedItem{
	private String name;
	
	private String type;
	
	private String comment;
	
	// 表示JList某项自定义属性
	
	private boolean selectEnable = true;
	
	private boolean isSelected = false;
	
	public DBModel() {
		super();
	}

	public DBModel(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public boolean isSelectEnable() {
		return selectEnable;
	}
	
	@Override
	public void setSelectEnable(boolean selectEnable) {
		this.selectEnable = selectEnable;
	}

	@Override
	public boolean isSelected() {
		return isSelected;
	}

	@Override
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}
