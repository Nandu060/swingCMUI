package com.ibm.ecm.configmgr;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.Element;

import com.ibm.Factory.PropertyFactory;

public class WAS_FreshProfile extends JFrame {

	private JPanel contentPane;
	private JTextField AppServerInstallt;
	private JTextField AppServerAdminnamet;
	private JPasswordField AppServerAdminpasswordt;
	private JPasswordField ConfirmPasswordt;
	private JTextField AppServerSOAPt;
	private JTextField AppServerHostt;
	private JTextField AppServerTimeoutt;
	private JTextField AppServerProfilet;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_2;
	private JCheckBox TurnOffSSLc;
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";
	public static String cellValue="";
	private static String scenario;
	private static String appServer="";
	PropertyFactory props = new PropertyFactory();
	public static String filePath = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\profiles\\";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WAS_FreshProfile frame = new WAS_FreshProfile(scenario);
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
	public WAS_FreshProfile(String scenario) {
		this.scenario = scenario;
		setBounds(100, 100, 638, 656);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(scenario.equals("Fresh"))
		setTitle("Create New Installation Profile");
		else if(scenario.equals("Upgrade"))
		{
			setTitle("Upgrade Configuration Profile");
			if(!CMUtil.profileName.equals(""))
			{
				String loadedFile = filePath+"\\"+CMUtil.profileName+"\\applicationserver.xml";
				props.XmlDoc(loadedFile, (String)null);
				Element doc1 = props.getDocElem();
				CMUtil.appServerVersion = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerVersion", VALUE);
				CMUtil.appServerInstallFolder = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerInstallationFolder", VALUE);
				CMUtil.appServerProfile = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerProfileFolder", VALUE);
				CMUtil.appServerAdminPassword = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerAdminPassword", VALUE);
				CMUtil.appServerAdminUser = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerAdminUsername", VALUE);
				CMUtil.appServerSOAP = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerSoapPort", VALUE);
				CMUtil.appServerTimeout = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerTransactionTimeout", VALUE);
				CMUtil.appServerHostName = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerHostName", VALUE);
				CMUtil.securityDomain = props.getChildValueByName(doc1, PROPERTY, "SecurityDomain", VALUE);
				CMUtil.turnOffSSL = props.getChildValueByName(doc1, PROPERTY, "TurnOffSSLCerticates", VALUE);
				System.out.println("Inside upgrade "+CMUtil.appServerAdminUser);
			}
		}
		
		JPanel upperPanel = new JPanel();
		upperPanel.setBackground(new Color(254, 255, 255));
		
		JLabel lblNewLabel = props.setLabel("Set Properties for WebSphere Application Server"); //new JLabel("Set Properties for WebSphere Application Server");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		final JLabel lblNewLabel_1 = props.setLabel("*Please fill all the required fields"); //new JLabel("*Please fill all the required fields");
		
		JPanel lowerPanel = new JPanel();
		
		JLabel lblNewLabel_2 = props.setLabel("Application server version* :"); //new JLabel("Application server version* :");
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8.5.5", "9.0" }));
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String wasVersion = comboBox.getSelectedItem().toString();
				CMUtil.wasVersion = wasVersion;
				CMUtil.appServerVersion = wasVersion;
			}
		});
		
		JLabel lblNewLabel_3 = props.setLabel("Application server installation directory* :"); //new JLabel("Application server installation directory* :");
		
		AppServerInstallt = props.getTextField(); //new JTextField();
		AppServerInstallt.setText(CMUtil.appServerInstallFolder);
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
		            JOptionPane.showMessageDialog(rootPane, "The Application Server directory does not exist. \n Please provide the correct application directory path.");
		        }
		        String profAPath = profDir.getAbsolutePath();
		        AppServerInstallt.setText(profAPath);
			}
		});
		AppServerInstallt.setText(CMUtil.appServerInstallFolder);
		CMUtil.appServerInstallFolder = AppServerInstallt.getText();
		
		JLabel lblNewLabel_4 = props.setLabel("Application server administration user name* :"); //new JLabel("Application server administration user name* :");
		
		AppServerAdminnamet = props.getTextField(); //new JTextField();
		AppServerAdminnamet.setColumns(10);
		AppServerAdminnamet.setText(CMUtil.appServerAdminUser);
		
		JLabel lblNewLabel_5 = props.setLabel("Application server administrator password* :"); //new JLabel("Application server administrator password* :");
		
		AppServerAdminpasswordt = props.getPasswordField(); //new JPasswordField();
		AppServerAdminpasswordt.setText(CMUtil.appServerAdminPassword);
		
		JLabel lblNewLabel_6 = props.setLabel("Confirm* :"); //new JLabel("Confirm* :");
		
		ConfirmPasswordt = props.getPasswordField(); //new JPasswordField();
		
		JLabel lblNewLabel_7 = props.setLabel("Application server SOAP port* :"); //new JLabel("Application server SOAP port* :");
		
		AppServerSOAPt = props.getTextField(); //new JTextField();
		AppServerSOAPt.setText(CMUtil.appServerSOAP);
		AppServerSOAPt.setColumns(10);
		
		TurnOffSSLc = props.getCheckBox("SSL certificates"); //new JCheckBox("SSL enabled");
		TurnOffSSLc.setSelected(Boolean.parseBoolean(CMUtil.turnOffSSL));
		
		JLabel lblNewLabel_8 = props.setLabel("Application server host* :"); //new JLabel("Application server host* :");
		
		AppServerHostt = props.getTextField(); //new JTextField();
		AppServerHostt.setText(CMUtil.appServerHostName);
		AppServerHostt.setColumns(10);
		
		JLabel lblNewLabel_9 = props.setLabel("Application server cell* :"); //new JLabel("Application server cell* :");
		
		JLabel lblNewLabel_10 = props.setLabel("Security Domain* :"); //new JLabel("Security Domain* :");
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<>(new String[] {"default"}));
		comboBox_1.setSelectedItem(CMUtil.securityDomain);
		CMUtil.securityDomain = "default";
		
		JLabel lblNewLabel_11 = props.setLabel("Application server transaction timeout* :"); //new JLabel("Application server transaction timeout* :");
		
		AppServerTimeoutt = props.getTextField(); //new JTextField();
		AppServerTimeoutt.setText(CMUtil.appServerTimeout);
		AppServerTimeoutt.setColumns(10);
		
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
		
		JButton nextBtn = props.getButton("Next>"); //new JButton("Next >");
		if(scenario.equals("Upgrade"))
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
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		    	CMUtil.appServerCell = comboBox_2.getSelectedItem().toString();
		    	System.out.println(CMUtil.appServerCell);
			}
		});
		
		comboBox_2.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				lblNewLabel_1.setText("*Fetching the application server cell name ");
				String scriptName = "fetchcell.py", licenseValue = "";
	    		cellValue = WebsphereUtil.runScript(scriptName,licenseValue);
	    		lblNewLabel_1.setText("*Server cell fetched successfully");
	    		comboBox_2.setModel(new DefaultComboBoxModel<>(new String[] {cellValue}));
	    		System.out.println(cellValue);
			}
		});
		
		JLabel lblNewLabel_12 = props.setLabel("Application server profile directory* :"); //new JLabel("Application server profile directory* :");
		
		AppServerProfilet = props.getTextField(); //new JTextField();
		AppServerProfilet.setText(CMUtil.appServerProfile);
		AppServerProfilet.setColumns(10);
		
		JButton browseBtnProfile = props.getButton("Browse"); //new JButton("Browse");
		browseBtnProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser profPath = new JFileChooser();
		        profPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        profPath.setCurrentDirectory(new java.io.File("."));
		        profPath.showOpenDialog(null);
		        File profDir = profPath.getSelectedFile();
		        if (!(profDir.isDirectory() && profDir.exists())) {
		            JOptionPane.showMessageDialog(rootPane, "The Application Server directory does not exist. \n Please provide the correct application directory path.");
		        }
		        String profAPath = profDir.getAbsolutePath();
		        AppServerProfilet.setText(profAPath);
			}
		});
		AppServerProfilet.setText(CMUtil.appServerProfile);
		//CMUtil.appServerProfile = AppServerProfilet.getText();
		
		GroupLayout gl_panel_1 = new GroupLayout(lowerPanel);
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
					.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(153)
							.addComponent(browseBtnProfile, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addComponent(AppServerProfilet, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(AppServerAdminnamet, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(AppServerAdminpasswordt, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addComponent(ConfirmPasswordt, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(AppServerSOAPt, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(TurnOffSSLc, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(AppServerHostt, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(AppServerTimeoutt, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(326)
					.addComponent(testBtn, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 590, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(237)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(87)
							.addComponent(nextBtn, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
					.addGap(4)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(88)
							.addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addComponent(finishBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
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
					.addGap(3)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_12))
						.addComponent(browseBtnProfile)
						.addComponent(AppServerProfilet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_4))
						.addComponent(AppServerAdminnamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_5))
						.addComponent(AppServerAdminpasswordt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_6))
						.addComponent(ConfirmPasswordt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_7))
						.addComponent(AppServerSOAPt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(1)
							.addComponent(TurnOffSSLc)))
					.addGap(16)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_8))
						.addComponent(AppServerHostt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_9))
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_11))
						.addComponent(AppServerTimeoutt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_10))
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addComponent(testBtn)
					.addGap(12)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(nextBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(finishBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
		);
		lowerPanel.setLayout(gl_panel_1);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 626, GroupLayout.PREFERRED_SIZE)
						.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 626, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 527, GroupLayout.PREFERRED_SIZE))
		);
		getContentPane().setLayout(groupLayout);
	}
	public void nextPerformed(ActionEvent ev)
	{
		CMUtil.appServerInstallFolder = AppServerInstallt.getText();
		CMUtil.appServerAdminPassword = AppServerAdminpasswordt.getText();
		CMUtil.appServerAdminUser = AppServerAdminnamet.getText();
		CMUtil.appServerSOAP = AppServerSOAPt.getText();
		CMUtil.appServerTimeout = AppServerTimeoutt.getText();
		CMUtil.appServerHostName = AppServerHostt.getText();
		CMUtil.securityDomain = comboBox.getSelectedItem().toString();
		CMUtil.appServerProfile = AppServerProfilet.getText();
		CMUtil.turnOffSSL = "false";
		if(TurnOffSSLc.isSelected())
		  CMUtil.turnOffSSL = "true";
		setVisible(false);
		ChooseTasksPanel ctp = new ChooseTasksPanel(CMUtil.appServer);
		ctp.setVisible(true);
		ctp.setLocationRelativeTo(null);
        //this.dispose();
	}
	public void backPerformed(ActionEvent ev)
	{
		if(scenario.equals("Upgrade"))
		{
			setVisible(false);
			UpgradeConfiguration_server ucs = new UpgradeConfiguration_server();
			ucs.setVisible(true);
			ucs.setLocationRelativeTo(null);
		}
		else if(scenario.equals("Fresh"))
		{
			setVisible(false);
			ProfDetails2 pd2 = new ProfDetails2();
			pd2.setVisible(true);
			pd2.setLocationRelativeTo(null);
		}
		//this.dispose();
	}
	public void finishPerformed(ActionEvent evt)
	{
		if(scenario.equals("Upgrade"))
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
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerProfileFolder", VALUE, CMUtil.appServerProfile);
			props.setChildValueByName(doc1, PROPERTY, "ApplicationServerCell", VALUE, CMUtil.appServerCell);
			props.setChildValueByName(doc1, PROPERTY, "TurnOffSSLCerticates", VALUE, CMUtil.turnOffSSL);
			props.setChildValueByName(doc1, PROPERTY, "SecurityDomain", VALUE, CMUtil.securityDomain);
			props.saveXMLDoc(loadedFile);
			//setVisible(false);
			
			//ConsoleOP.appendText("Verify your settings");
			String msg = "Verify the accuracy of the application server property values.\nClick OK after you have verified the settings, "
					+ "or click Cancel\nto go back and change the settings.\n\nIf your Content Engine upgrade includes a change in the \n"
					+ "application server (an upgrade of the application server\nor a migration to a new application server instance),\n"
					+ "you must run these tasks:\n    - Configure GCD JDBC Data Sources\n    - Configure Object Store JDBC Data Sources\n    "
					+ "- Configure Login Modules\n    - Configure LDAP\n    - Deploy Application\n\nIf your Content Engine upgrade does not include a change "
					+ "in\nthe application server, you must run these tasks:\n\n    - Configure Login Modules\n    - Deploy Application";
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
	}
}

