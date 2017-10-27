package com.qqyycom.renderer;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

import com.qqyycom.model.DBModel;
import com.qqyycom.model.SelectEnable;
import com.qqyycom.model.SelectedItem;

@SuppressWarnings("serial")
public class PropertyListCellRenderer extends JPanel implements ListCellRenderer<DBModel> {

	private JList<? extends DBModel> list;

	private JCheckBox checkBox;

	private JLabel label;

	public PropertyListCellRenderer(JList<? extends DBModel> list) {
		this.list = list;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(checkBox = new JCheckBox());
		this.add(label = new JLabel());
		// ���ù̶�����ɫ
		//this.setBackground(list.getBackground());
		checkBox.setBackground(UIManager.getColor("Tree.textBackground"));
		label.setForeground(UIManager.getColor("Tree.textForeground"));
		
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends DBModel> list, DBModel value, int index,
			boolean isSelected, boolean cellHasFocus) {
		// ��� �տ�ʼ value��isSeleceted��״̬��true 
		isSelected = value.isSelected();
		/************ ����Jlabel������ ***********/
		String text = "<html><b>" + value.getName() + "</b><br/><small>" + value.getType() + "</small></html>";
		label.setText(text);
		
		/************ ���ÿؼ�����ɫ (�ɱ�) ***********/
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		/************ ����checkbox��ѡ��״̬  ***********/
		checkBox.setSelected(((SelectedItem) value).isSelected());
		/************ �����Ƿ�ѡ��  ***********/
		if(((SelectEnable) value).isSelectEnable()){
			((SelectedItem) value).setSelected(isSelected);
		}
		return this;
	}
}
