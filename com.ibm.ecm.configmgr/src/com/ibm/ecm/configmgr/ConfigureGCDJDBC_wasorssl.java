package com.ibm.ecm.configmgr;


import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import org.w3c.dom.Element;

import com.ibm.Factory.PropertyFactory;
import com.ibm.ecm.configmgr.ConfigureGCDJDBC_wasorrac.executeTask;

public class ConfigureGCDJDBC_wasorssl extends JPanel {
	private JTextField JDBCDSNamet;
	private JTextField JDBCDSXANamet;
	private JTextField DBServert;
	private JTextField DBPortt;
	private JTextField DBNamet;
	private JTextField DBUserNamet;
	private JPasswordField DBPasswordt;
	private JTextField KStPatht;
	private JPasswordField SSLKStPasst;
	private JPasswordField JKSSSLKStPasst;
	private JTextField JKSKStPatht;
	private JTextField MinConnt;
	private JTextField MaxConnt;
	private JTextField UnusedTimet;
	private JTextField ReapTimet;
	private JTextField Scriptt;
	private JCheckBox OrServiceNamec;
	private JCheckBox DBFailOverc;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";
	private static final String EMPTY_STRING = ""; //$NON-NLS-1$
	private static final String DONE_CONFIGURING_XA_DATA_SOURCE = "Done configuring XA DataSource"; //$NON-NLS-1$
	private static final String DONE_CONFIGURING_DATA_SOURCE = "Done configuring DataSource"; //$NON-NLS-1$
	private static final String ALREADY_EXISTS = " already exists"; //$NON-NLS-1$
	private static final String LOG_EXT = ".log"; //$NON-NLS-1$
	private static final String QUOTE = "\""; //$NON-NLS-1$
	private static final String N_A = "N/A"; //$NON-NLS-1$
	private static final String TCL_EXT = ".tcl"; //$NON-NLS-1$
	private static final String SLASH = "/"; //$NON-NLS-1$
	private static final String JDBC_ORACLE_THIN = "jdbc:oracle:thin:@";
	private static final String JDBC_TYPE = "4";
	private PropertyFactory props = new PropertyFactory();
	protected String jar;
	protected String kStType;
	/**
	 * Create the panel.
	 */
	public ConfigureGCDJDBC_wasorssl(String JDBCDSName, String JDBCDSXAName, String DBServer, String DBPort, String DBName, String OrServiceName, String DBUserName, 
			String DBPassword, String Jar, String KStPath, String KStType, String SSLKStPass, String JKSKStPath, String JKSSSLKStPass, 
			String MinConn, String MaxConn, String UnusedTime, String ReapTime, String Script, String DBFailOver, final File file) {
		
		final PropertyFactory props = new PropertyFactory();
		
		JLabel lblNewLabel = props.setLabel("Configure Oracle SSL GCD JDBC"); //new JLabel("Configure Oracle SSL GCD JDBC");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_1 = props.setLabel("JDBC data source name* :"); //new JLabel("JDBC data source name :");
		
		JDBCDSNamet = props.getTextField(); //new JTextField();
		JDBCDSNamet.setColumns(10);
		JDBCDSNamet.setText(JDBCDSName);
		
		JLabel lblNewLabel_2 = props.setLabel("JDBC XA data source name* :"); //new JLabel("JDBC XA data source name* :");
		
		JDBCDSXANamet = props.getTextField(); //new JTextField();
		JDBCDSXANamet.setColumns(10);
		JDBCDSXANamet.setText(JDBCDSXAName);
		
		JLabel lblNewLabel_3 = props.setLabel("Database server name* :"); //new JLabel("Database server name* :");
		
		DBServert = props.getTextField(); //new JTextField();
		DBServert.setColumns(10);
		DBServert.setText(DBServer);
		
		JLabel lblNewLabel_4 = props.setLabel("Database port number* :"); //new JLabel("Database port number* :");
		
		DBPortt = props.getTextField(); //new JTextField();
		DBPortt.setColumns(10);
		DBPortt.setText(DBPort);
		
		JLabel lblNewLabel_5 = props.setLabel("Database name* :"); //new JLabel("Database name* :");
		
		DBNamet = props.getTextField(); //new JTextField();
		DBNamet.setColumns(10);
		DBNamet.setText(DBName);
		
		OrServiceNamec = props.getCheckBox("Oracle service name enabled"); //new JCheckBox("Oracle service name enabled");
		OrServiceNamec.setSelected(Boolean.parseBoolean(OrServiceName));
		
		JLabel lblNewLabel_6 = props.setLabel("Database username* :"); //new JLabel("Database username* :");
		
		DBUserNamet = props.getTextField(); //new JTextField();
		DBUserNamet.setColumns(10);
		DBUserNamet.setText(DBUserName);
		
		JLabel lblNewLabel_7 = props.setLabel("Database password* :"); //new JLabel("Database password* :");
		
		DBPasswordt = props.getPasswordField(); //new JPasswordField();
		DBPasswordt.setText(DBPassword);
		
		JLabel lblNewLabel_8 = props.setLabel("OJDBC Jar* :"); //new JLabel("OJDBC Jar* :");
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"ojdbc6.jar","ojdbc8.jar"}));
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jar = comboBox.getSelectedItem().toString();
				System.out.println(jar);
			}
			});
		comboBox.setSelectedItem(Jar);
		
		JLabel lblNewLabel_9 = props.setLabel("SSL TrustStore type* :"); //new JLabel("SSL TrustStore type* :");
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<>(new String[] {"random"}));
		comboBox_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				kStType = comboBox_1.getSelectedItem().toString();
				System.out.println(kStType);
			}
			});
		comboBox_1.setSelectedItem(KStType);
		
		JLabel lblNewLabel_10 = props.setLabel("SSL TrustStore or Oracle Wallet File location* :"); //new JLabel("SSL TrustStore or Oracle Wallet File location* :");
		
		JButton browseBtnKStPath = props.getButton("Browse"); //new JButton("Browse");
		browseBtnKStPath.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser profPath = new JFileChooser();
		        profPath.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		        profPath.showOpenDialog(null);
		        File profDir = profPath.getSelectedFile();
		        if (!(profDir.isDirectory() && profDir.exists())) {
		            profDir.mkdirs();
		        }
		        String profAPath = profDir.getAbsolutePath();
		        KStPatht.setText(profAPath);
			}
		});
		
		KStPatht = props.getTextField(); //new JTextField();
		KStPatht.setColumns(10);
		KStPatht.setText(KStPath);
		
		JLabel lblNewLabel_11 = props.setLabel("SSL TrustStore Password* :"); //new JLabel("SSL TrustStore Password* :");
		
		SSLKStPasst = props.getPasswordField(); //new JPasswordField();
		SSLKStPasst.setText(SSLKStPass);
		
		JLabel lblNewLabel_12 = props.setLabel("SSL KeyStore Password* : (JKS only)"); //new JLabel("SSL KeyStore Password* : (JKS only)");
		
		JKSSSLKStPasst = props.getPasswordField(); //new JPasswordField();
		JKSSSLKStPasst.setText(JKSSSLKStPass);
		
		JLabel lblNewLabel_13 = props.setLabel("SSL KeyStore File location* : (JKS only)"); //new JLabel("SSL KeyStore File location* : (JKS only)");
		
		JButton browseBtnJKStPath = props.getButton("Browse"); //new JButton("Browse");
		browseBtnJKStPath.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser profPath = new JFileChooser();
		        profPath.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		        profPath.showOpenDialog(null);
		        File profDir = profPath.getSelectedFile();
		        if (!(profDir.isDirectory() && profDir.exists())) {
		            profDir.mkdirs();
		        }
		        String profAPath = profDir.getAbsolutePath();
		        JKSKStPatht.setText(profAPath);
			}
		});
		
		JKSKStPatht = props.getTextField(); //new JTextField();
		JKSKStPatht.setColumns(10);
		JKSKStPatht.setText(JKSKStPath);
		
		JLabel lblNewLabel_14 = props.setLabel("Minimum connections* :"); //new JLabel("Minimum connections* :");
		
		MinConnt = props.getTextField(); //new JTextField();
		MinConnt.setColumns(10);
		MinConnt.setText(MinConn);
		
		JLabel lblNewLabel_15 = props.setLabel("Maximum connections* :"); //new JLabel("Maximum connections* :");
		
		MaxConnt = props.getTextField(); //new JTextField();
		MaxConnt.setColumns(10);
		MaxConnt.setText(MaxConn);
		
		JLabel lblNewLabel_16 = props.setLabel("Unused timeout (sec.)* :"); //new JLabel("Unused timeout (sec.)* :");
		
		UnusedTimet = props.getTextField(); //new JTextField();
		UnusedTimet.setColumns(10);
		UnusedTimet.setText(UnusedTime);
		
		JLabel lblNewLabel_17 = props.setLabel("Reap time (sec.)* :"); //new JLabel("Reap time (sec.)* :");
		
		ReapTimet = props.getTextField(); //new JTextField();
		ReapTimet.setColumns(10);
		ReapTimet.setText(ReapTime);
		
		JLabel lblNewLabel_18 = props.setLabel("Script :"); //new JLabel("Script* :");
		
		JButton browseBtn = new JButton("Browse");
		browseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser profPath = new JFileChooser();
		        profPath.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		        profPath.showOpenDialog(null);
		        File profDir = profPath.getSelectedFile();
		        if (!(profDir.isDirectory() && profDir.exists())) {
		            profDir.mkdirs();
		        }
		        String profAPath = profDir.getAbsolutePath();
		        Scriptt.setText(profAPath);
			}
		});
		
		Scriptt = props.getTextField(); //new JTextField();
		Scriptt.setColumns(10);
		Scriptt.setText(Script);
		
		DBFailOverc = props.getCheckBox("Database failover support enabled"); //new JCheckBox("Database failover support enabled");
		DBFailOverc.setSelected(Boolean.parseBoolean(DBFailOver));
		
		JSeparator separator_1 = new JSeparator();
		
		JButton runBtn = props.getButton("Run task"); //new JButton("Run task");
		runBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				runActionPerformed(e);
			}
		});
		
		JButton saveBtn = props.getButton("Save"); //new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				props.XmlDoc(file.toString(), (String)null);
				Element doc1 = props.getDocElem();
				props.setChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE, JDBCDSNamet.getText());
				props.setChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE, JDBCDSXANamet.getText());
				props.setChildValueByName(doc1, PROPERTY, "DatabaseServerName", VALUE, DBServert.getText());
				props.setChildValueByName(doc1, PROPERTY, "DatabasePortNumber", VALUE, DBPortt.getText());
				props.setChildValueByName(doc1, PROPERTY, "DatabaseName", VALUE, DBNamet.getText());
				props.setChildValueByName(doc1, PROPERTY, "ServiceNameEnabled", VALUE, props.getValue(OrServiceNamec));
				props.setChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE, DBUserNamet.getText());
				props.setChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE, DBPasswordt.getText());
				props.setChildValueByName(doc1, PROPERTY, "ODBC Jar", VALUE, props.getValue(comboBox));
				props.setChildValueByName(doc1, PROPERTY, "KeyStoreType", VALUE, props.getValue(comboBox_1));
				props.setChildValueByName(doc1, PROPERTY, "KeyStorePath", VALUE, KStPatht.getText());
				props.setChildValueByName(doc1, PROPERTY, "SSLKeyStorePassword", VALUE, SSLKStPasst.getText());
				props.setChildValueByName(doc1, PROPERTY, "JKSKeyStorePath(JKS)", VALUE, JKSKStPatht.getText());
				props.setChildValueByName(doc1, PROPERTY, "JKSSSLKeyStorePassword(JKS)", VALUE, JKSSSLKStPasst.getText());
				props.setChildValueByName(doc1, PROPERTY, "Minimum connections", VALUE, MinConnt.getText());
				props.setChildValueByName(doc1, PROPERTY, "Maximum connections", VALUE, MaxConnt.getText());
				props.setChildValueByName(doc1, PROPERTY, "Unused timeout (seconds)", VALUE, UnusedTimet.getText());
				props.setChildValueByName(doc1, PROPERTY, "Reap time (seconds)", VALUE, ReapTimet.getText());
				props.setChildValueByName(doc1, PROPERTY, "Script", VALUE, Scriptt.getText());
				props.setChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE, props.getValue(DBFailOverc));
				props.saveXMLDoc(file.toString());
				String[] names = props.getElementNamesByTagName(doc1, PROPERTY);
				for(int i=0;i<names.length;i++)
				{
					//System.out.println(names[i]);
					System.out.println(props.getChildValueByName(doc1, PROPERTY, names[i], VALUE));
				}	 
			}
		});
		
		JButton testBtn = props.getButton("Test Database Connection"); //new JButton("Test Database Connection");
		testBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				testActionPerformed(e);
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 848, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(JDBCDSNamet, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(78)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(JDBCDSXANamet, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(DBServert, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(78)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(DBPortt, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(DBNamet, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(78)
					.addComponent(OrServiceNamec, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(DBUserNamet, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(78)
					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(DBPasswordt, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(78)
					.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(KStPatht, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(browseBtnKStPath, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(SSLKStPasst, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(54)
					.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(JKSSSLKStPasst, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_13, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(JKSKStPatht, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(browseBtnJKStPath, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_14, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(MinConnt, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(78)
					.addComponent(lblNewLabel_15, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(MaxConnt, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_16, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(UnusedTimet, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(78)
					.addComponent(lblNewLabel_17, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(ReapTimet, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_18, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(Scriptt, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(browseBtn))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(DBFailOverc, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 810, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(391)
					.addComponent(testBtn, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addComponent(runBtn, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_1))
						.addComponent(JDBCDSNamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_2))
						.addComponent(JDBCDSXANamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_3))
						.addComponent(DBServert, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_4))
						.addComponent(DBPortt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_5))
						.addComponent(DBNamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(OrServiceNamec)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_6))
						.addComponent(DBUserNamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_7))
						.addComponent(DBPasswordt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_8))
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_9))
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_10))
						.addComponent(KStPatht, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(browseBtnKStPath))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_11))
						.addComponent(SSLKStPasst, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_12))
						.addComponent(JKSSSLKStPasst, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_13))
						.addComponent(JKSKStPatht, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(browseBtnJKStPath))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_14))
						.addComponent(MinConnt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_15))
						.addComponent(MaxConnt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_16))
						.addComponent(UnusedTimet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_17))
						.addComponent(ReapTimet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_18))
						.addComponent(Scriptt, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(browseBtn))
					.addGap(16)
					.addComponent(DBFailOverc)
					.addGap(12)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(testBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(runBtn))))
		);
		setLayout(groupLayout);
	}
	public void runActionPerformed(ActionEvent e)
	{
		String console = exec();
		ConsoleOP.appendText(console);
	}
	public String exec() {
    	String script = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\scripts\\configureWSJDBC.tcl";
        String tempFile = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\tmp\\configurejdbcgcd.tcl";
        setScript(script, tempFile);
                	    	
    	String EMPTY_STRING = "";
    	String command = EMPTY_STRING;
		//String jvmargs = EMPTY_STRING;
		//String arguments = EMPTY_STRING;
		String workingDir = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure";
		File workingDirf = new File(workingDir);
		
		command = "configmgr_cl execute -task configurejdbcgcd -profile "+CMUtil.profileName;
		System.out.println(command);
		StringBuffer sbout = new StringBuffer();
		
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
	public void setScript(String script, String wsTclRuntime) 
	{
    	try {
		
    	String QUOTE = "\"";
		String strEOL = CMUtil.getPlatformEOL();
		//String script = getTaskCMProperties().getValue(ConfigKeys.SCRIPT);
	    //String tempDir = getTaskCMProperties().getValue(ConfigKeys.TEMP_DIR);
	    //String wsTclRuntime = tempDir+ SLASH + getSymbolicName() + TCL_EXT;
	    //String wsPyRuntime = CMUtil.CANONICAL_CM_PATH + SLASH + "scripts" + SLASH + "customJVMProperty.py"; 
	    String databaseUsername = DBUserNamet.getText();//WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_USERNAME)).trim();
	    String databasePassword = DBPasswordt.getText();//WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_PASSWORD));
	    String jdbcProviderDesc = "\"The JDBC provider used for global configuration database or object store data sources.\""; //Messages.JDBC_PROVIDER_DESC_DESC;
	    String jdbcDataSourceName = JDBCDSNamet.getText();//WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.JDBC_DATASOURCE_NAME).trim());      // Remove leading & trailing whitespace
	    String jdbcDataSourceXAName = JDBCDSXANamet.getText();//WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.JDBC_DATASOURCE_XA_NAME).trim()); // Remove leading & trailing whitespace
	    String databaseName = DBNamet.getText();//WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_NAME));
	    String databaseServerName = DBServert.getText();//WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_SERVER_NAME));
	    String databasePortNumber = DBPortt.getText();//getTaskCMProperties().getValue(ConfigKeys.DATABASE_PORT_NUMBER);
	   	//db version for oracle is 10g or 11g
	    String databaseVersion = "";//WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_VERSION));
	   	//
	    String driverVersion = ""; //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.JDBC_DRIVER_VERSION));
	   	boolean turnOnDBFailover = Boolean.parseBoolean(props.getValue(DBFailOverc)); //Boolean.parseBoolean(getTaskCMProperties().getValue(ConfigKeys.DBFAILOVER_ENABLED));
	   	//changes for defect 3470 start
	   	boolean databaseServiceNameOracleSet = Boolean.parseBoolean(props.getValue(OrServiceNamec)); //.getValue(ConfigKeys.ORACLE_DATABASE_SERVICE_NAME_CHECK_BOX));
	   	String odbcjarname = props.getValue(comboBox); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.ODBC_JAR_NAME));
	   	//changes for defect 3470 end
	    //changes for defect 9654 start
	   	String mssqljarname = N_A;//WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.MSSQL_JAR_NAME));
	   	//changes for defect 9654 end
	   	String zOsDbName = N_A;
	    String zOsDefaultTblspace = N_A;
	    String zOsDefaultSTOGroup = N_A;
	    String zOsSTOGROUPOptions = N_A;
	    //changes for defect 3470 start
	    //URL for Oracle service name
	    String url = "jdbc:oracle:thin:@//"+ databaseServerName+ ":" +databasePortNumber+ "/"+ databaseName;
	//    String url = "jdbc:oracle:thin:@(description=(address=(host="+databaseServerName+")(protocol=tcp)(port="+databasePortNumber+"))(connect_data=(service_name="+databaseName+")(server=SHARED)))";
	    //changes for defect 3470 end
	    String retryIntervalForClientReroute = N_A;
	    String maxRetriesForClientReroute = N_A;
	    String clientRerouteAlternateServerName = N_A;
	    String clientRerouteAlternatePortNumber = N_A;
	    
	    String applicationServerVersion = CMUtil.appServerVersion; //getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_VERSION);
	    String majorVersion = applicationServerVersion.substring(0, applicationServerVersion.indexOf("."));//$NON-NLS-1$
	    String applicationServerCell = CMUtil.appServerCell; //WebsphereUtil.escapeSpecialChar(getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_CELL).trim());
	    boolean turnoffSSLcerts = Boolean.parseBoolean(CMUtil.turnOffSSL); //getEnvironmentCMProperties().getValue(ConfigKeys.TURNOFF_SSL_CERTIFICATE));
	
	    String jdbcType = "4"; //getJDBCtype();
	    String jdbcProviderName = "JDBC provider for Oracle (SSL Enabled)"; //NLS.bind(Messages.JDBC_PROVIDER_NAME_DESC, getProviderName());
	    
	    //20484
	    boolean sslEnabled = false;
	    String jkskeyStorePath = N_A, jkskeyStoreType = N_A, jkskeyStorePassword = N_A;//JKS SSL
	    String keyStorePath = N_A, keyStoreType = N_A, keyStorePassword = N_A;
	    String minPoolSize = N_A, maxPoolSize = N_A, unusedTimeOut = N_A, reapTime = N_A;
	   // if (CMUtil.isCmuiClient)
	    //{
	        if (jdbcProviderName.contains("OracleSSL")) 
	        {
	/*                		sslEnabled = Boolean.parseBoolean(getTaskCMProperties().getValue(ConfigKeys.DS_SSL_ENABLED));
	        	if (sslEnabled)
	        	{*/
	        	sslEnabled = true;
	    		keyStorePath = KStPatht.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DS_SSL_KEYSTORE_WALLET_PATH));
	    		keyStoreType = props.getValue(comboBox_1); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DS_SSL_KEYSTORE_WALLET_TYPE));
	    		keyStorePassword = SSLKStPasst.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DS_SSL_PASSWORD));
	//        	}
	    		if (keyStoreType.equalsIgnoreCase("JKS")) // JKS SSL
	    		{
	    			jkskeyStorePath = JKSKStPatht.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DS_SSL_JKS_KEYSTORE_WALLET_PATH));
	    			jkskeyStoreType = keyStoreType;
	    			jkskeyStorePassword = JKSSSLKStPasst.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DS_SSL_JKS_PASSWORD));
	    		}
	        }
	        
	        //20626
	        minPoolSize = MinConnt.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.MIN_POOL_SIZE));
	        maxPoolSize = MaxConnt.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.MAX_POOL_SIZE));
	        unusedTimeOut = UnusedTimet.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.UNUSED_TIMEOUT));
	        reapTime = ReapTimet.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.REAP_TIME));
	    //}
	    
		FileReader inFR = new FileReader(script);
		BufferedReader inBR = new BufferedReader(inFR);
		FileWriter outFW = new FileWriter(wsTclRuntime);
		outFW.write("set _wasVersion " + QUOTE + majorVersion + QUOTE + strEOL);//$NON-NLS-1$ 
		outFW.write("set _cellName " + QUOTE + applicationServerCell + QUOTE+ strEOL);//$NON-NLS-1$ 
		outFW.write("set _aliasName " + QUOTE + databaseUsername + QUOTE+ strEOL);//$NON-NLS-1$ 
		outFW.write("set _aliasId " + QUOTE + databaseUsername + QUOTE+ strEOL);//$NON-NLS-1$ 
		outFW.write("set _aliasPassword " + QUOTE + databasePassword + QUOTE+ strEOL);//$NON-NLS-1$ 
		outFW.write("set _jdbcProviderType " + QUOTE + jdbcType + QUOTE+ strEOL);//$NON-NLS-1$ 
		outFW.write("set _jdbcProviderName " + QUOTE + jdbcProviderName + QUOTE+ strEOL);//$NON-NLS-1$ 
		outFW.write("set _jdbcProviderDesc " + QUOTE + jdbcProviderDesc + QUOTE+ strEOL);//$NON-NLS-1$ 
		outFW.write("set _dsName " + QUOTE + jdbcDataSourceName + QUOTE+ strEOL);//$NON-NLS-1$ 
		outFW.write("set _jndiName " + QUOTE + jdbcDataSourceName + QUOTE+ strEOL);//$NON-NLS-1$ 
		outFW.write("set _dsXaName " + QUOTE + jdbcDataSourceXAName + QUOTE+ strEOL);//$NON-NLS-1$ 
		outFW.write("set _jndiXaName " + QUOTE + jdbcDataSourceXAName + QUOTE+ strEOL);//$NON-NLS-1$ 
		outFW.write("set _dbName " + QUOTE + databaseName + QUOTE+ strEOL);//$NON-NLS-1$ 
		outFW.write("set _dbServerName " + QUOTE + databaseServerName + QUOTE+ strEOL);//$NON-NLS-1$ 
		outFW.write("set _portNumber " + QUOTE + databasePortNumber + QUOTE+ strEOL);//$NON-NLS-1$
		outFW.write("set _dbFailOver " + QUOTE + turnOnDBFailover + QUOTE+ strEOL);//$NON-NLS-1$
		
		//changes for defect 3470 start
		outFW.write("set _odbcjarType " + QUOTE + odbcjarname + QUOTE+ strEOL);//$NON-NLS-1$
		// changes for defect 3470 end
		
		//changes for defect 9654 start 
		outFW.write("set _mssqljarType " + QUOTE + mssqljarname + QUOTE+ strEOL);//$NON-NLS-1$
		//changes for defect 9654 end
		outFW.write("set _ClientRerouteEnable " + QUOTE + "false" + QUOTE+ strEOL);//$NON-NLS-1$
		outFW.write("set _retryIntervalForClientReroute " + QUOTE + retryIntervalForClientReroute + QUOTE+ strEOL);//$NON-NLS-1$ 
		outFW.write("set _maxRetriesForClientReroute " + QUOTE + maxRetriesForClientReroute + QUOTE+ strEOL);//$NON-NLS-1$ 
		outFW.write("set _clientRerouteAlternateServerName " + QUOTE + clientRerouteAlternateServerName + QUOTE+ strEOL);//$NON-NLS-1$
		outFW.write("set _clientRerouteAlternatePortNumber " + QUOTE + clientRerouteAlternatePortNumber + QUOTE+ strEOL);//$NON-NLS-1$
		
		outFW.write("set _zOsDbName " + QUOTE + zOsDbName + QUOTE+ strEOL); //$NON-NLS-1$ 
		outFW.write("set _zOsDefaultTblspace " + QUOTE + zOsDefaultTblspace + QUOTE+ strEOL);//$NON-NLS-1$ 
		outFW.write("set _zOsDefaultSTOGroup " + QUOTE + zOsDefaultSTOGroup + QUOTE+ strEOL); //$NON-NLS-1$ 
		outFW.write("set _zOsSTOGROUPOptions " + QUOTE + zOsSTOGROUPOptions + QUOTE+ strEOL);//$NON-NLS-1$ 
	
		//20484 settings
		//if (CMUtil.isCmuiClient){
		outFW.write("set _sslEnabled " + QUOTE + sslEnabled + QUOTE+ strEOL);//$NON-NLS-1$
		outFW.write("set _sslConn " + QUOTE + sslEnabled + QUOTE+ strEOL);//$NON-NLS-1$
		String connProps = "";
		if(sslEnabled)
			if(!(keyStorePath.equals(N_A)) && !(keyStoreType.equals(N_A)) && !(keyStorePassword.equals(N_A)))
			{
				if (!(jkskeyStorePath.equals(N_A)) && !(jkskeyStoreType.equals("PKCS12")) && !(jkskeyStorePassword.equals(N_A)))// JKS SSL
					connProps = "javax.net.ssl.trustStore=" + keyStorePath.replace('\\', '/') +  ";javax.net.ssl.trustStoreType=" + keyStoreType + ";javax.net.ssl.trustStorePassword=" + keyStorePassword + ";javax.net.ssl.keyStore=" + jkskeyStorePath.replace('\\', '/') +  ";javax.net.ssl.keyStoreType=" + keyStoreType + ";javax.net.ssl.keyStorePassword=" + jkskeyStorePassword + ";oracle.net.ssl_version=1.0";// replace File.pathSeparatorChar with ; 21025
				else
					connProps = "javax.net.ssl.trustStore=" + keyStorePath.replace('\\', '/') +  ";javax.net.ssl.trustStoreType=" + keyStoreType + ";javax.net.ssl.trustStorePassword=" + keyStorePassword + ";oracle.net.ssl_version=1.0";// replace File.pathSeparatorChar with ; 21025
				//System.out.println("keyStorePath :" + keyStorePath);
				url = JDBC_ORACLE_THIN + "(DESCRIPTION=(ADDRESS=(PROTOCOL=TCPS)(HOST="+ databaseServerName + ")(PORT="+ databasePortNumber +"))(CONNECT_DATA=(SERVICE_NAME="+ databaseName + ")))";
				//System.out.println("url :" + url);
			}
	
		outFW.write("set _oracleSSLURL "+ QUOTE + url + QUOTE + strEOL);
		//System.out.println("connProps = " + connProps);
		outFW.write("set _connProps " + QUOTE + connProps + QUOTE + strEOL);
	
		//20626
		outFW.write("set _minpoolsize " + Integer.parseInt(minPoolSize) + strEOL);
		outFW.write("set _maxpoolsize " + Integer.parseInt(maxPoolSize) + strEOL);
		outFW.write("set _unusedtimeout " + Integer.parseInt(unusedTimeOut) + strEOL);
		outFW.write("set _reaptime " + Integer.parseInt(reapTime) + strEOL);
		
		String aLine;
		while ((aLine=inBR.readLine()) != null)
		{
			if (aLine.startsWith("set _turnoffSSLcerts") && !turnoffSSLcerts) //$NON-NLS-1$
				outFW.write("set _turnoffSSLcerts \"true\""+ strEOL); //$NON-NLS-1$
			else if (aLine.startsWith("set _dbVersion")) //$NON-NLS-1$
				outFW.write("set _dbVersion " + QUOTE + databaseVersion + QUOTE+ strEOL);//$NON-NLS-1$ 
			else if (aLine.startsWith("set _driverVersion")) //$NON-NLS-1$
				outFW.write("set _driverVersion " + QUOTE + driverVersion + QUOTE+ strEOL);//$NON-NLS-1$ 
			//changes for defect 3470 start
			else if (aLine.startsWith("set _oracleServiceNameURL") && databaseServiceNameOracleSet)
				outFW.write("set _oracleServiceNameURL "+ QUOTE + url + QUOTE + strEOL);    				
			//changes for defect 3470 end
			//20686
			else if ((aLine.contains("$minpoolsize")) || (aLine.contains("$maxpoolsize")) || (aLine.contains("$unusedtimeout")) || (aLine.contains("$reaptime")))
			{
				String str2replace = "", str2parse = "";
				if (aLine.contains("$minpoolsize")) { str2replace = "$minpoolsize"; str2parse = minPoolSize; }
				else if (aLine.contains("$maxpoolsize")) { str2replace = "$maxpoolsize"; str2parse = maxPoolSize; }
				else if (aLine.contains("$unusedtimeout")) { str2replace = "$unusedtimeout"; str2parse = unusedTimeOut; }
				else if (aLine.contains("$reaptime")) { str2replace = "$reaptime"; str2parse = reapTime; }
				aLine = aLine.substring(0,aLine.indexOf(str2replace)) + Integer.parseInt(str2parse) + aLine.substring(aLine.indexOf(str2replace) + str2replace.length(), aLine.length());
				//System.out.println(aLine);
				outFW.write(aLine+ strEOL);
			}    
			else
				outFW.write(aLine+ strEOL);
		}
		
		inFR.close();
		inBR.close();
		outFW.close();
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
	}
	public void testActionPerformed(ActionEvent evt)
    {
    	executeTask exe = new executeTask();
    	String console = exe.test();
    	ConsoleOP.appendText(console);
    }
    
    public class executeTask extends ConfigureWSGCDJDBC
	{
		protected String getJDBCtype() {
			// TODO Auto-generated method stub
			return JDBC_TYPE;
		}
		
	    public String test() 
	    {
	    	String script = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\scripts\\testWASDBConnection.tcl";
	        String tempFile = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\tmp";
	        System.out.println("Hello");
	        String result = testWork();
	                	    	
	    	String EMPTY_STRING = "";
	    	String command = EMPTY_STRING;
			//String jvmargs = EMPTY_STRING;
			//String arguments = EMPTY_STRING;
			String workingDir = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure";
			File workingDirf = new File(workingDir);
			
			command = "configmgr_cl test -task configurejdbcgcd -profile "+CMUtil.profileName;
			System.out.println(command);
			StringBuffer sbout = new StringBuffer();
			
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
}

