package com.qqyycom.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ConnectPanel extends JPanel {

	private JTextField urlTextField;
	private JTextField nameTextField;
	private JTextField passwdTextField;
	
	private JButton connectButton;
	/**
	 * Create the panel.
	 */
	public ConnectPanel() {
		//contentPane.add(this, BorderLayout.NORTH);
		GridBagLayout gbl_connectPanel = new GridBagLayout();
		gbl_connectPanel.columnWidths = new int[] {150, 270, 0};
		gbl_connectPanel.rowHeights = new int[] {21, 21, 21, 5, 0};
		gbl_connectPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_connectPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.setLayout(gbl_connectPanel);
		
		JLabel urlLabel = new JLabel("url");
		GridBagConstraints gbc_urlLabel = new GridBagConstraints();
		gbc_urlLabel.fill = GridBagConstraints.BOTH;
		gbc_urlLabel.insets = new Insets(0, 0, 5, 5);
		gbc_urlLabel.gridx = 0;
		gbc_urlLabel.gridy = 0;
		this.add(urlLabel, gbc_urlLabel);
		urlLabel.setBounds(new Rectangle(50, 20, 200, 20));
		urlLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		urlLabel.setSize(new Dimension(7, 7));
		
		urlTextField = new JTextField();
		GridBagConstraints gbc_urlTextField = new GridBagConstraints();
		gbc_urlTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_urlTextField.insets = new Insets(0, 0, 5, 0);
		gbc_urlTextField.gridx = 1;
		gbc_urlTextField.gridy = 0;
		this.add(urlTextField, gbc_urlTextField);
		urlTextField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		urlTextField.setColumns(20);
		
		JLabel userNameLabel = new JLabel("name");
		GridBagConstraints gbc_userNameLabel = new GridBagConstraints();
		gbc_userNameLabel.fill = GridBagConstraints.BOTH;
		gbc_userNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_userNameLabel.gridx = 0;
		gbc_userNameLabel.gridy = 1;
		this.add(userNameLabel, gbc_userNameLabel);
		userNameLabel.setSize(new Dimension(7, 7));
		userNameLabel.setBounds(new Rectangle(50, 20, 200, 20));
		userNameLabel.setAlignmentX(0.5f);
		
		nameTextField = new JTextField();
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.fill = GridBagConstraints.BOTH;
		gbc_nameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_nameTextField.gridx = 1;
		gbc_nameTextField.gridy = 1;
		this.add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(20);
		nameTextField.setAlignmentX(1.0f);
		
		JLabel passwdLabel = new JLabel("password");
		GridBagConstraints gbc_passwdLabel = new GridBagConstraints();
		gbc_passwdLabel.fill = GridBagConstraints.BOTH;
		gbc_passwdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwdLabel.gridx = 0;
		gbc_passwdLabel.gridy = 2;
		this.add(passwdLabel, gbc_passwdLabel);
		passwdLabel.setSize(new Dimension(7, 7));
		passwdLabel.setBounds(new Rectangle(50, 20, 200, 20));
		passwdLabel.setAlignmentX(0.5f);
		
		passwdTextField = new JTextField();
		GridBagConstraints gbc_passwdTextField = new GridBagConstraints();
		gbc_passwdTextField.insets = new Insets(0, 0, 5, 0);
		gbc_passwdTextField.fill = GridBagConstraints.BOTH;
		gbc_passwdTextField.gridx = 1;
		gbc_passwdTextField.gridy = 2;
		this.add(passwdTextField, gbc_passwdTextField);
		passwdTextField.setColumns(20);
		passwdTextField.setAlignmentX(1.0f);
		
		connectButton = new JButton("connect");
		
		GridBagConstraints gbc_btnConnect = new GridBagConstraints();
		gbc_btnConnect.anchor = GridBagConstraints.NORTH;
		gbc_btnConnect.insets = new Insets(0, 0, 0, 5);
		gbc_btnConnect.gridx = 0;
		gbc_btnConnect.gridy = 3;
		this.add(connectButton, gbc_btnConnect);
	}
	public JTextField getUrlTextField() {
		return urlTextField;
	}
	public JTextField getNameTextField() {
		return nameTextField;
	}
	public JTextField getPasswdTextField() {
		return passwdTextField;
	}
	public JButton getConnectButton() {
		return connectButton;
	}

	
	
}
