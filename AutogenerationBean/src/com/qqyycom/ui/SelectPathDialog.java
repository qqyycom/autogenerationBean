package com.qqyycom.ui;

import javax.swing.JFileChooser;

@SuppressWarnings("serial")
public class SelectPathDialog extends JFileChooser {

	/**
	 * Create the dialog.
	 */
	public SelectPathDialog() {
		// ���ñ���
		this.setDialogTitle("\u9009\u62E9\u6587\u4EF6\u8DEF\u5F84");
		// ����ѡ��ģʽΪֻ��ѡ���ļ���
		this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}

}
