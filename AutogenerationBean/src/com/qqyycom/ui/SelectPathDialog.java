package com.qqyycom.ui;

import javax.swing.JFileChooser;

@SuppressWarnings("serial")
public class SelectPathDialog extends JFileChooser {

	/**
	 * Create the dialog.
	 */
	public SelectPathDialog() {
		// 设置标题
		this.setDialogTitle("\u9009\u62E9\u6587\u4EF6\u8DEF\u5F84");
		// 设置选择模式为只能选择文件夹
		this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}

}
