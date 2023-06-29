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
import java.io.InputStream;

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

import com.ibm.Factory.PropertyFactory;

public class WL_FreshProfile extends JFrame {
	private JTextField AppServerInstallt;
	private JTextField AppServerAdminNamet;
	private JPasswordField AppServerAdminPasswordt;
	private JPasswordField ConfirmPasswordt;
	private JTextField AppServerSoapt;
	private JTextField AppServerHostt;
	private JTextField AppServerDomaint;
	private JTextField AppServerTimeoutt;
	private static String appServer;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JCheckBox AppServerSSLEnabledc;
	private static String scenario="";
	PropertyFactory props = new PropertyFactory();
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";
	public static String filePath = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\profiles\\";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WL_FreshProfile frame = new WL_FreshProfile(appServer);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WL_FreshProfile(String scenario) {
		this.scenario = scenario;
		setBounds(100, 100, 638, 656);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(scenario);
		if(scenario.equals("Fresh")||scenario.equals("UpgradeWithoutServers"))
			setTitle("Create New Installation Profile");
		else if(scenario.equals("UpgradeWithServers"))
		{
			setTitle("Upgrade Configuration Profile");
			if(!CMUtil.profileName.equals(""))
			{
				String loadedFile = filePath+"\\"+CMUtil.profileName+"\\applicationserver.xml";
				props.XmlDoc(loadedFile, (String)null);
				Element doc1 = props.getDocElem();
				CMUtil.appServerVersion = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerVersion", VALUE);
				CMUtil.appServerInstallFolder = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerInstallationFolder", VALUE);
				CMUtil.appServerAdminPassword = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerAdminPassword", VALUE);
				CMUtil.appServerAdminUser = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerAdminUsername", VALUE);
				CMUtil.appServerSOAP = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerSoapPort", VALUE);
				CMUtil.appServerTimeout = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerTransactionTimeout", VALUE);
				CMUtil.appServerHostName = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerHostName", VALUE);
				CMUtil.SSLEnabled = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerSSLEnabled", VALUE);
				CMUtil.appServerDomain = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerDomainDirectory", VALUE);
				CMUtil.targetName = props.getChildValueByName(doc1, PROPERTY,"TargetName" , VALUE);
				System.out.println("Inside upgrade "+CMUtil.appServerAdminUser);
			}
		}
		
		JPanel upperPanel = new JPanel();
		upperPanel.setBackground(new Color(254, 255, 255));
		
		JLabel lblNewLabel = props.setLabel("Set Properties for Oracle WebLogic Server"); //new JLabel("Set Properties for Oracle WebLogic Server");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JLabel lblNewLabel_1 = props.setLabel("*Please fill all the required fields"); //new JLabel("*Please fill all the required fields"); //new JLabel(\"*Please fill all the required fields");
		
		GroupLayout gl_panel = new GroupLayout(upperPanel);
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
		upperPanel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		
		JLabel lblNewLabel_2 = props.setLabel("Application server version* :"); //new JLabel("Application server version* :");
		
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
		
		
		JLabel lblNewLabel_3 = props.setLabel("Application server installation directory* :"); //new JLabel("Application server installation directory* :");
		
		AppServerInstallt = props.getTextField(); //new JTextField();
		AppServerInstallt.setText("C:\\Oracle\\Middleware\\Oracle_Home");
		AppServerInstallt.setColumns(10);
		
		JButton browseBtnInstall = props.getButton("Browse"); //new JButton("Browse");
		browseBtnInstall.addActionListener(new ActionListener() {
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
		        AppServerInstallt.setText(profAPath);
			}
		});
		
		
		JLabel lblNewLabel_4 = props.setLabel("Application server administration user name* :"); //new JLabel("Application server administration user name* :");
		
		AppServerAdminNamet = props.getTextField(); //new JTextField();
		AppServerAdminNamet.setColumns(10);
		AppServerAdminNamet.setText(CMUtil.appServerAdminUser);
		
		//System.out.println(CMUtil.appServerAdminUser);
		
		JLabel lblNewLabel_5 = props.setLabel("Application server administrator password* :"); //new JLabel("Application server administrator password* :");
		
		AppServerAdminPasswordt = props.getPasswordField(); //new JPasswordField();
		
		
		JLabel lblNewLabel_6 = props.setLabel("Confirm* :"); //new JLabel("Confirm* :");
		
		ConfirmPasswordt = props.getPasswordField(); //new JPasswordField();
		
		JLabel lblNewLabel_7 = props.setLabel("Application server SOAP port* :"); //new JLabel("Application server SOAP port* :");
		
		AppServerSoapt = props.getTextField(); //new JTextField();
		AppServerSoapt.setText(CMUtil.appServerSOAP);
		AppServerSoapt.setColumns(10);
		
		AppServerSSLEnabledc = props.getCheckBox("SSL enabled"); //new JCheckBox("SSL enabled");
		//CMUtil.SSLEnabled =
		AppServerSSLEnabledc.setSelected(Boolean.parseBoolean(CMUtil.SSLEnabled));
		
		JLabel lblNewLabel_8 = props.setLabel("Application server host* :"); //new JLabel("Application server host* :");
		
		AppServerHostt = props.getTextField(); //new JTextField();
		AppServerHostt.setText(CMUtil.appServerHostName);
		//AppServerHostt.setText("localhost");
		AppServerHostt.setColumns(10);
		
		
		JLabel lblNewLabel_9 = props.setLabel("Application server domain directory* :"); //new JLabel("Application server domain directory* :");
		
		AppServerDomaint = props.getTextField(); //new JTextField();
		AppServerDomaint.setText("C:\\Oracle\\Middleware\\Oracle_Home\\user_projects\\domains\\base_domain");
		AppServerDomaint.setColumns(10);
		
		JButton browseBtnDomain = props.getButton("Browse"); //new JButton("Browse");
		browseBtnDomain.addActionListener(new ActionListener() {
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
		        AppServerDomaint.setText(profAPath);
			}
		});
		//AppServerDomaint.setText(CMUtil.appServerDomain);
		
		JLabel lblNewLabel_10 = props.setLabel("Target name* :"); //new JLabel("Target name* :");
		
		comboBox_1 = new JComboBox<>(new DefaultComboBoxModel<>(new String[] {"AdminServer"}));
		comboBox_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		CMUtil.targetName = comboBox_1.getSelectedItem().toString();
		
		JLabel lblNewLabel_11 = props.setLabel("Application server transaction timeout* :"); //new JLabel("Application server transaction timeout* :");
		
		AppServerTimeoutt = props.getTextField(); //new JTextField();
		AppServerTimeoutt.setText("180");
		AppServerTimeoutt.setColumns(10);
		AppServerTimeoutt.setText(CMUtil.appServerTimeout);
		
		JButton testBtn = props.getButton("Test Connection"); //new JButton("Test Connection");
		testBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JSeparator separator = new JSeparator();
		
		JButton cancelBtn = props.getButton("Cancel"); //new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		JButton finishBtn = props.getButton("Finish"); //new JButton("Finish");
		if(scenario.equals("Fresh"))
			finishBtn.setEnabled(false);
		finishBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				finishPerformed(e);
			}
		});
		
		JButton nextBtn = props.getButton("Next>"); //new JButton("Next>");
		if(scenario.equals("UpgradeWithServers")||scenario.equals("UpgradeWithoutServers"))
			nextBtn.setEnabled(false);
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nextPerformed(e);
			}
		});
		
		JButton backBtn = props.getButton("<Back"); //new JButton("< Back");
		backBtn.addActionListener(new ActionListener() {
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
							.addComponent(browseBtnInstall, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addComponent(AppServerInstallt, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(AppServerAdminNamet, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(AppServerAdminPasswordt, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(ConfirmPasswordt, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(AppServerSoapt, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(AppServerSSLEnabledc, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(AppServerHostt, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(AppServerDomaint, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(153)
							.addComponent(browseBtnDomain, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(AppServerTimeoutt, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(326)
					.addComponent(testBtn, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 590, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(238)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(88)
							.addComponent(nextBtn, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
					.addGap(1)
					.addComponent(finishBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
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
						.addComponent(browseBtnInstall)
						.addComponent(AppServerInstallt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_4))
						.addComponent(AppServerAdminNamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_5))
						.addComponent(AppServerAdminPasswordt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_6))
						.addComponent(ConfirmPasswordt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_7))
						.addComponent(AppServerSoapt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(1)
							.addComponent(AppServerSSLEnabledc)))
					.addGap(14)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_8))
						.addComponent(AppServerHostt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(7)
							.addComponent(lblNewLabel_9))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(2)
							.addComponent(AppServerDomaint, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(browseBtnDomain))
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
						.addComponent(AppServerTimeoutt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addComponent(testBtn)
					.addGap(15)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(nextBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(finishBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(1)
							.addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))))
		);
		panel_1.setLayout(gl_panel_1);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 602, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 602, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 509, GroupLayout.PREFERRED_SIZE))
		);
		getContentPane().setLayout(groupLayout);
	}
	public void nextPerformed(ActionEvent ev)
	{
		CMUtil.appServerInstallFolder = AppServerInstallt.getText();
		CMUtil.appServerAdminPassword = AppServerAdminPasswordt.getText();
		CMUtil.appServerAdminUser = AppServerAdminNamet.getText();
		CMUtil.appServerSOAP = AppServerSoapt.getText();
		CMUtil.appServerTimeout = AppServerTimeoutt.getText();
		CMUtil.appServerHostName = AppServerHostt.getText();
		CMUtil.appServerDomain = AppServerDomaint.getText();
		String res = "false";
		if(AppServerSSLEnabledc.isSelected())
			  res = "true";
		CMUtil.SSLEnabled = res;
		setVisible(false);
		ChooseTasksPanel ctp = new ChooseTasksPanel(CMUtil.appServer,CMUtil.scenario);
		ctp.setVisible(true);
		ctp.setLocationRelativeTo(null);
        //this.dispose();
	}
	public void backPerformed(ActionEvent ev)
	{
		if(scenario.equals("UpgradeWithServers"))
		{
			setVisible(false);
			UpgradeConfiguration_server ucs = new UpgradeConfiguration_server();
			ucs.setVisible(true);
			ucs.setLocationRelativeTo(null);
		}
		else if(scenario.equals("UpgradeWithoutServers"))
		{
			setVisible(false);
			ProfDetails2 pd2 = new ProfDetails2("UpgradeWithoutServers");
			pd2.setVisible(true);
			pd2.setLocationRelativeTo(null);
		}
		else if(scenario.equals("Fresh"))
		{
			setVisible(false);
			ProfDetails2 pd2 = new ProfDetails2("Fresh");
			pd2.setVisible(true);
			pd2.setLocationRelativeTo(null);
		}
	}
	public void finishPerformed(ActionEvent evt)
	{
		CMUtil.appServerInstallFolder = AppServerInstallt.getText();
		CMUtil.appServerAdminPassword = AppServerAdminPasswordt.getText();
		CMUtil.appServerAdminUser = AppServerAdminNamet.getText();
		CMUtil.appServerSOAP = AppServerSoapt.getText();
		CMUtil.appServerTimeout = AppServerTimeoutt.getText();
		CMUtil.appServerHostName = AppServerHostt.getText();
		CMUtil.appServerDomain = AppServerDomaint.getText();
		String res = "false";
		if(AppServerSSLEnabledc.isSelected())
			  res = "true";
		CMUtil.SSLEnabled = res;
		if(scenario.equals("UpgradeWithServers"))
		{
			String loadedFile = filePath+"\\"+CMUtil.profileName+"\\applicationserver.xml";
			props.XmlDoc(loadedFile, (String)null);
			Element doc1 = props.getDocElem();
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerVersion", VALUE, CMUtil.appServerVersion);
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerInstallationFolder", VALUE, CMUtil.appServerInstallFolder);
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerAdminUsername", VALUE, CMUtil.appServerAdminUser);
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerAdminPassword", VALUE, CMUtil.appServerAdminPassword);
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerSoapPort", VALUE, CMUtil.appServerSOAP);
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerHostName", VALUE, CMUtil.appServerHostName);
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerTransactionTimeout", VALUE, CMUtil.appServerTimeout);
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerDomainDirectory", VALUE, CMUtil.appServerDomain);
			props.setChildValueByName(doc1, PROPERTY, "TargetName", VALUE, CMUtil.targetName);
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerSSLEnabled", VALUE, CMUtil.SSLEnabled);
			props.saveXMLDoc(loadedFile);
			//setVisible(false);
			
			//ConsoleOP.appendText("Verify your settings");
			String msg = "Verify the accuracy of the application server property values.\nClick OK after you have verified the settings, "
					+ "or click Cancel\nto go back and change the settings.\n\nIf your Content Engine upgrade includes a change in the \n"
					+ "application server (an upgrade of the application server\nor a migration to a new application server instance),\n"
					+ "you must run these tasks:\n    - Configure GCD JDBC Data Sources\n    - Configure Object Store JDBC Data Sources\n    "
					+ "- Configure LDAP\n    - Deploy Application\n\nIf your Content Engine upgrade does not include a change "
					+ "in\nthe application server, you must run these tasks:\n\n    - Deploy Application";
			int result = JOptionPane.showConfirmDialog(this,msg,"Verify your settings before proceeding"
					,JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
			if(result == JOptionPane.OK_OPTION)
			{
				setVisible(false);
				new NewJFrame(CMUtil.profileName,"").makeUI();
	        }
			else if (result == JOptionPane.CANCEL_OPTION)
			{
				
	        }
			else 
			{
	            System.out.println("Wrong selection");
	        }
		}
		else if(scenario.equals("UpgradeWithoutServers"))
		{
			String consoleOP = exec(CMUtil.profileName);
			String loadedFile = filePath+"\\"+CMUtil.profileName+"\\applicationserver.xml";
			props.XmlDoc(loadedFile, (String)null);
			Element doc1 = props.getDocElem();
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerVersion", VALUE, CMUtil.appServerVersion);
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerInstallationFolder", VALUE, CMUtil.appServerInstallFolder);
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerAdminUsername", VALUE, CMUtil.appServerAdminUser);
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerAdminPassword", VALUE, CMUtil.appServerAdminPassword);
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerSoapPort", VALUE, CMUtil.appServerSOAP);
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerHostName", VALUE, CMUtil.appServerHostName);
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerTransactionTimeout", VALUE, CMUtil.appServerTimeout);
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerDomainDirectory", VALUE, CMUtil.appServerDomain);
			props.setChildValueByName(doc1, PROPERTY, "TargetName", VALUE, CMUtil.targetName);
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerSSLEnabled", VALUE, CMUtil.SSLEnabled);
			props.saveXMLDoc(loadedFile);
			NewJFrame nj = new NewJFrame(CMUtil.profileName, consoleOP);
	        nj.makeUI();
	        dispose();
		}
	}
	public String exec(String profName) 
	{
		StringBuffer sbout = new StringBuffer();
    	String EMPTY_STRING = "";
    	String command = EMPTY_STRING;
		String jvmargs = EMPTY_STRING;
		String arguments = EMPTY_STRING;
		String workingDir = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure";
		File workingDirf = new File(workingDir);
		//arguments+= "\"" + script + "\"";
		//jvmargs += " all com.filenet.gcd.LicenseModel ABCD";
		//command = "wsadmin.bat" + arguments + jvmargs;
		//command = "wsadmin.bat -conntype SOAP -port 8882 -host localhost -lang jython -f C:\\WASConnection\\WASConnect\\fetchcell.py";
		if(CMUtil.appServer.startsWith("WebLogic")){
			command = "configmgr_cl generateupgrade -appserver WebLogic -deploy standard -profile "+profName+" -pre551upgradeprofile false";
		}
		//else {
			//command = "configmgr_cl generateconfig -appserver WebLogic -db "+CMUtil.dbType+" -ldap "+CMUtil.ldapServerType+" -license uvu -deploy standard -profile "+profName;
		//}
		
		System.out.println(command);
		//StringBuffer sbout = new StringBuffer();
		
		try {
			Process p;

			// if(CMUtil.isWinOS()) {
			// Windows environment, proceed with old way
			String cmdArray[] = new String[] { "cmd.exe", "/C", command };
			ProcessBuilder pb = new ProcessBuilder(cmdArray);
			pb.directory(workingDirf);
			p = pb.start();

			InputStream inputstream = p.getInputStream();
			InputStream errorStream = p.getErrorStream();

			//StringBuffer sbout = new StringBuffer();
			StringBuffer sberr = new StringBuffer();

			new OutputProcessor(inputstream, sbout);
			new OutputProcessor(errorStream, sberr);

			p.waitFor();
			inputstream.close();
			errorStream.close();
			
			//Copy existing working profile into new profile
			/*String destProfPath = workingDir + File.separator + "profiles" + File.separator + profName;
			File dstPath = new File(destProfPath);
			String srcProfPath = workingDir + File.separator + "profiles" + File.separator + "2301swasprf";
			File srcPath = new File (srcProfPath);
			FileUtil.copyDirectory(srcPath, dstPath);
			File srcFile = new File(dstPath+File.separator + profName+".cfgp");
			File dstFile = new File(srcPath+File.separator + "2301swasprf.cfgp");
			File orgcfgp = new File(dstPath + File.separator + "2301swasprf.cfgp");
			FileUtil.copy(srcFile, dstFile);
			//System.out.println(orgcfgp);
			if (orgcfgp.exists() && orgcfgp.isFile())
				orgcfgp.delete();
			*/
	    } catch (Exception ioe) {
			// ecmdb00776196:
			// when the process executing the command fails,
			// it usually includes the command passed, which may contain the password in
			// plain text
			ioe.printStackTrace();
			//String localizedMsg1 = ioe.getLocalizedMessage();
	    }
		return sbout.toString();
    }
}

