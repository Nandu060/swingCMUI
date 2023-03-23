package com.ibm.ecm.configmgr;


import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class WL_FreshProfile extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private static String appServer;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JCheckBox chckbxNewCheckBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WL_FreshProfile frame = new WL_FreshProfile(appServer);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WL_FreshProfile(String appServer) {
		this.appServer = appServer;
		setBounds(100, 100, 638, 656);
		setResizable(false);
		setTitle("Create New Installation Profile");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(254, 255, 255));
		
		JLabel lblNewLabel = new JLabel("Set Properties for Oracle WebLogic Server");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JLabel lblNewLabel_1 = new JLabel("Creating a Fresh profile ");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 588, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblNewLabel_1))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		
		JLabel lblNewLabel_2 = new JLabel("Application server version* :");
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"12cR2(12.2.x)","14cR1(14.1.x)"}));
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String wlVersion = comboBox.getSelectedItem().toString();
				CMUtil.appServerVersion = wlVersion;
				System.out.println(wlVersion);
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("Application server installation directory* :");
		
		textField = new JTextField();
		textField.setText("C:\\Oracle\\Middleware\\Oracle_Home");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser profPath = new JFileChooser();
		        profPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        profPath.setCurrentDirectory(new java.io.File("."));
		        profPath.showOpenDialog(null);
		        File profDir = profPath.getSelectedFile();
		        if (!(profDir.isDirectory() && profDir.exists())) {
		            JOptionPane.showMessageDialog(rootPane, "The Application Server installation directory does not exist. \n Please provide the correct application directory path.");
		        }
		        String profAPath = profDir.getAbsolutePath();
		        textField.setText(profAPath);
			}
		});
		
		
		JLabel lblNewLabel_4 = new JLabel("Application server administration user name* :");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		//System.out.println(CMUtil.appServerAdminUser);
		
		JLabel lblNewLabel_5 = new JLabel("Application server administrator password* :");
		
		passwordField = new JPasswordField();
		
		
		JLabel lblNewLabel_6 = new JLabel("Confirm* :");
		
		passwordField_1 = new JPasswordField();
		
		JLabel lblNewLabel_7 = new JLabel("Application server SOAP port* :");
		
		textField_2 = new JTextField();
		
		
		textField_2.setColumns(10);
		
		chckbxNewCheckBox = new JCheckBox("SSL enabled");
		//CMUtil.SSLEnabled = 
		
		JLabel lblNewLabel_8 = new JLabel("Application server host* :");
		
		textField_3 = new JTextField();
		textField_3.setText("localhost");
		textField_3.setColumns(10);
		
		
		JLabel lblNewLabel_9 = new JLabel("Application server domain directory* :");
		
		textField_4 = new JTextField();
		textField_4.setText("C:\\Oracle\\Middleware\\Oracle_Home\\user_projects\\domains\\base_domain");
		textField_4.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Browse");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser profPath = new JFileChooser();
		        profPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        profPath.setCurrentDirectory(new java.io.File("."));
		        profPath.showOpenDialog(null);
		        File profDir = profPath.getSelectedFile();
		        if (!(profDir.isDirectory() && profDir.exists())) {
		            JOptionPane.showMessageDialog(rootPane, "The Application Server domain directory does not exist. \n Please provide the correct application directory path.");
		        }
		        String profAPath = profDir.getAbsolutePath();
		        textField_4.setText(profAPath);
			}
		});
		
		
		JLabel lblNewLabel_10 = new JLabel("Target name* :");
		
		comboBox_1 = new JComboBox<>(new DefaultComboBoxModel<>(new String[] {"AdminServer"}));
		comboBox_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		CMUtil.targetName = comboBox_1.getSelectedItem().toString();
		
		JLabel lblNewLabel_11 = new JLabel("Application server transaction timeout* :");
		
		textField_5 = new JTextField();
		textField_5.setText("180");
		textField_5.setColumns(10);
		
		
		JButton btnNewButton_2 = new JButton("Test Connection");
		
		JSeparator separator = new JSeparator();
		
		JButton btnNewButton_3 = new JButton("Cancel");
		
		JButton btnNewButton_4 = new JButton("Finish");
		btnNewButton_4.setEnabled(false);
		
		
		JButton btnNewButton_5 = new JButton("Next >");
		btnNewButton_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nextPerformed(e);
			}
		});
		
		JButton btnNewButton_6 = new JButton("< Back");
		btnNewButton_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				backPerformed(e);
			}
		});
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(153)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(chckbxNewCheckBox, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(153)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(326)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 590, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(238)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_6, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(88)
							.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
					.addGap(1)
					.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_2))
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_3))
						.addComponent(btnNewButton)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_4))
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_5))
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_6))
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_7))
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(1)
							.addComponent(chckbxNewCheckBox)))
					.addGap(14)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_8))
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(7)
							.addComponent(lblNewLabel_9))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(2)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton_1))
					.addGap(16)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_10))
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_11))
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addComponent(btnNewButton_2)
					.addGap(15)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_6, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(1)
							.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))))
		);
		panel_1.setLayout(gl_panel_1);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 602, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 602, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 509, GroupLayout.PREFERRED_SIZE))
		);
		getContentPane().setLayout(groupLayout);
	}
	public void nextPerformed(ActionEvent ev)
	{
		new ChooseTasksPanel(appServer).setVisible(true);
		String file2 = NewJFrame.filePath+"\\"+CMUtil.profileName+"\\applicationserver.xml";
		CMUtil.appServerInstallFolder = textField.getText();
		CMUtil.appServerAdminPassword = passwordField.getText();
		CMUtil.appServerAdminUser = textField_1.getText();
		CMUtil.appServerSOAP = textField_2.getText();
		CMUtil.appServerTimeout = textField_5.getText();
		CMUtil.appServerHostName = textField_3.getText();
		CMUtil.appServerDomain = textField_4.getText();
		String res = "false";
		if(chckbxNewCheckBox.isSelected())
			  res = "true";
		  CMUtil.SSLEnabled = res;
        this.dispose();
	}
	public void backPerformed(ActionEvent ev)
	{
		new ProfDetails2().setVisible(true);
		this.dispose();
	}
}

