package com.qqyycom.renderer;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.qqyycom.model.TableModel;

@SuppressWarnings("serial")
public class TableListCellRenderer extends JLabel implements ListCellRenderer<TableModel>{
	private JList<? extends TableModel> list;
	
	public TableListCellRenderer(JList<? extends TableModel> list) {
		super();
		this.list = list;
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends TableModel> list, TableModel value, int index,
			boolean isSelected, boolean cellHasFocus) {
		/************ ��ʾTable��  ***********/
		this.setText(value.getTableName());
		/************ ���ÿؼ�����ɫ  ***********/
		if (isSelected) {
			this.setBackground(list.getSelectionBackground());
			this.setForeground(list.getSelectionForeground());
		} else {
			this.setBackground(list.getBackground());
			this.setForeground(list.getForeground());
		}
		setOpaque(true);
		return this;
	}

}
