package com.qqyycom.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {
	// ȷ����
	private JButton okButton;
	// ȡ����
	private JButton cancelButton;
	
	/**
	 * Create the panel.
	 */
	public ButtonPanel() {
		
		this.setToolTipText("");
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		okButton = new JButton("sure");
		okButton.setVerticalAlignment(SwingConstants.BOTTOM);
		this.add(okButton);
		
		cancelButton = new JButton("cancel");
		cancelButton.setVerticalAlignment(SwingConstants.BOTTOM);
		this.add(cancelButton);
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	
}
