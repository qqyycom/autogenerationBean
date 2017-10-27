package com.qqyycom.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;

import com.qqyycom.model.TableModel;

public abstract class TableListModelListener extends MouseAdapter {
	@SuppressWarnings("rawtypes")
	@Override
	public void mousePressed(MouseEvent e){
		JList list = (JList) e.getSource();
        int index = list.locationToIndex(e.getPoint());
		TableModel item = (TableModel)list.getModel().getElementAt(index);
		if(e.getClickCount() == 2){
			doubleClick(item);
		}
	}
	
	/**
	 *  Ë«»÷ÊÂ¼þ
	 * @param item
	 */
	public abstract void doubleClick(TableModel item);
}
