package com.qqyycom.listener;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;

import com.qqyycom.model.DBModel;

public abstract class CheckListMouseListener extends MouseAdapter {
	
	@Override
	public void mousePressed(MouseEvent e) {
        @SuppressWarnings("rawtypes")
		JList list = (JList) e.getSource();
        int index = list.locationToIndex(e.getPoint());
		DBModel item = (DBModel) list.getModel().getElementAt(index);
		oneClick(item);

		Rectangle rect = list.getCellBounds(index, index);

		list.repaint(rect);

   }
	
	public abstract void oneClick(DBModel item);
}
