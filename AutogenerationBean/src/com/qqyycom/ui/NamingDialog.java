package com.qqyycom.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class NamingDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField fileUrlTextField;
	private JTextField fileNameTextField;
	private JButton selectPathButton;
	private JLabel MessageArea;
	private JButton okButton;

	/**
	 * Create the dialog.
	 */
	public NamingDialog(JFrame frame) {
		// …Ë÷√root
		super(frame,true);
		setTitle("\u521B\u5EFAbean");
		setBounds(100, 100, 449, 165);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {50, 250, 100};
		gbl_contentPanel.rowHeights = new int[]{21, 23, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel label = new JLabel("\u8DEF\u5F84");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.anchor = GridBagConstraints.EAST;
			gbc_label.gridx = 0;
			gbc_label.gridy = 0;
			contentPanel.add(label, gbc_label);
		}
		{
			fileUrlTextField = new JTextField();
			GridBagConstraints gbc_fileUrlTextField = new GridBagConstraints();
			gbc_fileUrlTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_fileUrlTextField.insets = new Insets(0, 0, 5, 5);
			gbc_fileUrlTextField.gridx = 1;
			gbc_fileUrlTextField.gridy = 0;
			contentPanel.add(fileUrlTextField, gbc_fileUrlTextField);
			fileUrlTextField.setColumns(10);
		}
		{
			selectPathButton = new JButton("\u9009\u62E9\u6587\u4EF6\u5939");
			selectPathButton.setActionCommand("SelectPath");
			GridBagConstraints gbc_selectPathButton = new GridBagConstraints();
			gbc_selectPathButton.insets = new Insets(0, 0, 5, 0);
			gbc_selectPathButton.gridy = 0;
			gbc_selectPathButton.gridx = 2;
			selectPathButton.setPreferredSize(new Dimension(100, 21));
			contentPanel.add(selectPathButton, gbc_selectPathButton);
		}
		{
			JLabel lblBean = new JLabel("bean\u540D\u79F0");
			GridBagConstraints gbc_lblBean = new GridBagConstraints();
			gbc_lblBean.anchor = GridBagConstraints.EAST;
			gbc_lblBean.insets = new Insets(0, 0, 5, 5);
			gbc_lblBean.gridx = 0;
			gbc_lblBean.gridy = 1;
			contentPanel.add(lblBean, gbc_lblBean);
		}
		{
			fileNameTextField = new JTextField();
			GridBagConstraints gbc_fileNameTextField = new GridBagConstraints();
			gbc_fileNameTextField.insets = new Insets(0, 0, 5, 5);
			gbc_fileNameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_fileNameTextField.gridx = 1;
			gbc_fileNameTextField.gridy = 1;
			contentPanel.add(fileNameTextField, gbc_fileNameTextField);
			fileNameTextField.setColumns(10);
		}
		{
			MessageArea = new JLabel("");
			GridBagConstraints gbc_MessageArea = new GridBagConstraints();
			gbc_MessageArea.insets = new Insets(0, 0, 0, 5);
			gbc_MessageArea.gridx = 1;
			gbc_MessageArea.gridy = 2;
			contentPanel.add(MessageArea, gbc_MessageArea);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				//okButton.setEnabled(false);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JTextField getFileUrlTextField() {
		return fileUrlTextField;
	}

	public JTextField getFileNameTextField() {
		return fileNameTextField;
	}

	public JButton getSelectPathButton() {
		return selectPathButton;
	}

	public JLabel getMessageArea() {
		return MessageArea;
	}

	public JButton getOkButton() {
		return okButton;
	}

	
}
