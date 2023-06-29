package com.ibm.ecm.configmgr;


import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
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
import com.ibm.ecm.configmgr.ConfigureGCDJDBC_wlorrac.executeTask;

public class ConfigureGCDJDBC_wlsql extends JPanel {
	private JTextField JDBCDSNamet;
	private JTextField JDBCDSXANamet;
	private JTextField DBServert;
	private JTextField DBPortt;
	private JTextField DBUserNamet;
	private JTextField Scriptt;
	private JTextField DBNamet;
	private JPasswordField DBPasswordt;
	private JPasswordField passField_1;
	private JCheckBox DBFailOverc;
	public String dbFailover = "";
	private static final String SINGLE_QUOTE = "'";
	String strEOL = CMUtil.getPlatformEOL();
	private static final String DB_TEST_TABLE_VAR = "dbTestTable="; //$NON-NLS-1$
	private static final String DB_XA_PROPS_VAR = "dbXaProps="; //$NON-NLS-1$
	private static final String DB_PROPS_VAR = "dbProps="; //$NON-NLS-1$
	private static final String DB_URL_VAR = "dbURL="; //$NON-NLS-1$
	private static final String DB_DRIVER_XA_NAME_VAR = "dbDriverXaName="; //$NON-NLS-1$
	private static final String DB_DRIVER_NAME_VAR = "dbDriverName="; //$NON-NLS-1$
	private static final String DB_NAME_VAR = "dbName="; //$NON-NLS-1$
	private static final String DB_PASSWORD_VAR = "dbPassword="; //$NON-NLS-1$
	private static final String DB_USER_VAR = "dbUser="; //$NON-NLS-1$
	private static final String DB_PORT_VAR = "dbPort="; //$NON-NLS-1$
	private static final String DB_SERVER_VAR = "dbServer="; //$NON-NLS-1$
	private static final String DB_TYPE_VAR = "dbType="; //$NON-NLS-1$
	private static final String TARGET_NAME_VAR = "targetName="; //$NON-NLS-1$
	private static final String DS_XA_NAME_VAR = "dsXaName="; //$NON-NLS-1$
	private static final String DS_NAME_VAR = "dsName="; //$NON-NLS-1$
	private static final String JDBC_XA_NAME_VAR = "jdbcXaName="; //$NON-NLS-1$
	private static final String JDBC_NAME_VAR = "jdbcName="; //$NON-NLS-1$
	private static final String CONNECTION_URL_VAR = "connectionURL="; //$NON-NLS-1$
	private static final String WL_ADMIN_PASSWORD_VAR = "wlAdminPassword="; //$NON-NLS-1$
	private static final String WL_ADMIN_VAR = "wlAdmin="; //$NON-NLS-1$
	private static final String LOG_EXT = ".log"; //$NON-NLS-1$
	private static final String ALREADY_EXISTS = " already exists"; //$NON-NLS-1$
	private static final String DONE_CONFIGURING_XA_DATA_SOURCE = "Done configuring XA DataSource"; //$NON-NLS-1$
	private static final String DONE_CONFIGURING_DATA_SOURCE = "Done configuring DataSource"; //$NON-NLS-1$
	//private static final String SINGLE_QUOTE = "'"; //$NON-NLS-1$
	private static final String T3S_PROTOCOL = "t3s://"; //$NON-NLS-1$
	private static final String T3_PROTOCOL = "t3://"; //$NON-NLS-1$
	private static final String COLON = ":"; //$NON-NLS-1$
	private static final String TRUE = "true"; //$NON-NLS-1$
	private static final String CONNECTION_POOL_FOR_FILE_NET_ENGINE_XA_DATASOURCE = "Connection Pool For FileNet Engine XA Datasource"; //$NON-NLS-1$
	private static final String CONNECTION_POOL_FOR_FILE_NET_ENGINE_DATASOURCE = "Connection Pool For FileNet Engine Datasource"; //$NON-NLS-1$
	private static final String PY = ".py"; //$NON-NLS-1$
	private static final String _12c_2_1 = "12.2"; //$NON-NLS-1$ "12c(12.2.1)";  18507
	private static final String _14c_R1 = "14.1";//18743
	private static final String DB_FAILOVER_VAR = "dbFailOver="; //$NON-NLS-1$
	private static final String JDBC_ORACLE_THIN = "jdbc:oracle:thin:@//";
	private static final String SELECT_MODE_DEFAULT_DATABASE_NAME = ";selectMode=default;databaseName=";
	private static final String USER = "user=";
	private static final String JDBC_SQLSERVER = "jdbc:sqlserver://";
	private static final String SERVER_NAME = ";serverName=";
	private static final String DATABASE_NAME = ";databaseName=";
	private static final String PORTNUMBER = ";portnumber=";
	private static final String CONFIGURE_WL_JDBC_PY = "configureWLJDBC.py";
	private static final String DB_TYPE = "MSSQL";//$NON-NLS-1$
    private static final String DB_DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//$NON-NLS-1$
    private static final String DB_DRIVER_XA_NAME = "com.microsoft.sqlserver.jdbc.SQLServerXADataSource";//$NON-NLS-1$
    private static final String DB_TEST_TABLE = "SQL select count(*) from sysusers";
    private static final String JDBC_DB2 = "jdbc:db2://"; //$NON-NLS-1$
	private static final String DRIVER_TYPE_4 = ";driverType=4"; //$NON-NLS-1$
	private static final String SLASH = "/"; //$NON-NLS-1$//$NON-NLS-1$
	private static final String DB_TYPE_DB2 = "DB2";//$NON-NLS-1$
    private static final String DB_DRIVER_NAME_DB2 = "com.ibm.db2.jcc.DB2Driver";//$NON-NLS-1$ 
    private static final String DB_DRIVER_XA_NAME_DB2 = "com.ibm.db2.jcc.DB2XADataSource";//$NON-NLS-1$ 
    private static final String DB_TEST_TABLE_DB2 = "SQL SELECT COUNT(*) FROM SYSIBM.SYSTABLES";
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";
	private PropertyFactory props = new PropertyFactory();

	/**
	 * Create the panel.
	 */
	public ConfigureGCDJDBC_wlsql(String JDBCDSName,String JDBCDSXAName,String DBServer,String DBPort,String DBName,String DBUserName,
			String DBPassword,String Script,String DBFailOver,final File file) {
		
		JLabel lblNewLabel = props.setLabel("Configure GCD JDBC Data Source"); //new JLabel("Configure GCD JDBC Data Source");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_1 = props.setLabel("JDBC Data Source Name* :"); //new JLabel("JDBC Data Source Name* :");
		
		JDBCDSNamet = props.getTextField(); //new JTextField();
		JDBCDSNamet.setText(JDBCDSName);
		JDBCDSNamet.setColumns(10);
		
		JLabel lblNewLabel_2 = props.setLabel("JDBC XA Data Source Name* :"); //new JLabel("JDBC XA Data Source Name* :");
		
		JDBCDSXANamet = props.getTextField(); //new JTextField();
		JDBCDSXANamet.setText(JDBCDSXAName);
		JDBCDSXANamet.setColumns(10);
		
		JLabel lblNewLabel_3 = props.setLabel("Database server name* :"); //new JLabel("Database server name* :");
		
		DBServert = props.getTextField(); //new JTextField();
		DBServert.setText(DBServer);
		DBServert.setColumns(10);
		
		JLabel lblNewLabel_4 = props.setLabel("Database port number* :"); //new JLabel("Database port number* :");
		
		DBPortt = props.getTextField(); //new JTextField();
		DBPortt.setText(DBPort);
		DBPortt.setColumns(10);
		
		JLabel lblNewLabel_5 = props.setLabel("Database username* :"); //new JLabel("Database username* :");
		
		DBUserNamet = props.getTextField(); //new JTextField();
		DBUserNamet.setText(DBUserName);
		DBUserNamet.setColumns(10);
		
		JLabel lblNewLabel_6 = props.setLabel("Database password* :"); //new JLabel("Database password* :");
		
		DBPasswordt = props.getPasswordField(); //new JPasswordField();
		DBPasswordt.setText(DBPassword);
		DBPasswordt.setColumns(10);
		
		/*passField_1 = new JPasswordField();
		passField_1.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Confirm* :");*/
		
		JLabel lblNewLabel_8 = props.setLabel("Script* :"); //new JLabel("Script* :");
		
		Scriptt = props.getTextField(); //new JTextField();
		Scriptt.setText(Script);
		Scriptt.setColumns(10);
		
		JButton browseBtn = props.getButton("Browse"); //new JButton("Browse");
		browseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser profPath = new JFileChooser();
		        profPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        profPath.showOpenDialog(null);
		        File profDir = profPath.getSelectedFile();
		        if (!(profDir.isDirectory() && profDir.exists())) {
		            profDir.mkdirs();
		        }
		        String profAPath = profDir.getAbsolutePath();
		        Scriptt.setText(profAPath);
			}
		});
		
		DBFailOverc = props.getCheckBox("Database failover support enabled"); //new JCheckBox("Database failover support enabled");
		DBFailOverc.setSelected(Boolean.parseBoolean(DBFailOver));
		
		JSeparator separator_1 = new JSeparator();
		
		JButton testBtn = props.getButton("Test Database connection"); //new JButton("Test DB connection");
		testBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				testActionPerformed(e);
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
				props.setChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE, DBUserNamet.getText());
				props.setChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE, DBPasswordt.getText());
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
		
		JButton runBtn = props.getButton("Run Task"); //new JButton("Run Task");
		runBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				runActionPerformed(e);
			}
		});
		
		JLabel lblNewLabel_9 = props.setLabel("Database name* :"); //new JLabel("Database name* :");
		
		DBNamet = new JTextField();
		DBNamet.setText(DBName);
		DBNamet.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 770, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(JDBCDSNamet, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(JDBCDSXANamet, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(DBServert, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(DBPortt, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(DBFailOverc, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(DBNamet, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(DBUserNamet, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(DBPasswordt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(Scriptt, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(browseBtn, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 770, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(334)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(testBtn)
							.addGap(134)
							.addComponent(runBtn, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(190)
							.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_1))
						.addComponent(JDBCDSNamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_2))
						.addComponent(JDBCDSXANamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_3))
						.addComponent(DBServert, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_4))
						.addComponent(DBPortt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(DBFailOverc, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_9))
						.addComponent(DBNamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_5))
						.addComponent(DBUserNamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_6))
						.addComponent(DBPasswordt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_8))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(Scriptt, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addComponent(browseBtn))
					.addGap(18)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING,false)
						.addComponent(testBtn)
						.addComponent(runBtn)
						.addComponent(saveBtn)))
		);
		setLayout(groupLayout);

	}
	public void runActionPerformed(ActionEvent e)
	{
		String console = exec();
    	//new NewJFrame(CMUtil.profileName, console).setVisible(true);
		ConsoleOP.appendText(console);
	}
	public String exec() {
    	String script = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\scripts\\configureWLJDBC.py";
        String tempFile = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\tmp\\configureldap.py";
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
    @SuppressWarnings("deprecation")
	public void setScript(String script, String wlPyRuntime) {
    	//working only for mssql (jdbc)
    	String dbType="", dbDriverName="", dbDriverXAName="", databaseServerName="", databasePortNumber="", databaseName="", databaseUsername="", 
    	dbProperties="", dbXAProperties="", dbTestTable="", url="", jdbcConnPoolName="", jdbcConnPoolXAName="", dbUrl="";
		dbType = CMUtil.implementorID;
		dbDriverName = DB_DRIVER_NAME; //Hard coded
        dbDriverXAName = DB_DRIVER_XA_NAME; //Hard coded
        databaseServerName = DBServert.getText();
        databasePortNumber = DBPortt.getText();
        databaseName = DBNamet.getText();
        databaseUsername = DBUserNamet.getText();
        dbFailover = props.getValue(DBFailOverc);
		dbUrl = null; //getDbUrl(databaseServerName,databasePortNumber,databaseName);   Commented for making the changes for defect 4144
        //boolean databaseServiceNameOracleSet = Boolean.parseBoolean(getTaskCMProperties().getValue(ConfigKeys.ORACLE_DATABASE_SERVICE_NAME_CHECK_BOX));
        /*boolean databaseServiceNameOracleSet = false;
        if (databaseServiceNameOracleSet){
        	dbUrl = getOracleServiceNameUrl(databaseServerName, databasePortNumber, databaseName);
        }
        else {*/
        dbUrl = getMSSQLUrl(databaseServerName, databasePortNumber); //(databaseServerName,databasePortNumber,databaseName);
        //}
        
        //System.out.println("dbType: " + dbType);	
        //if (dbType.equalsIgnoreCase("MSSQL"))
        //{
            final String SEMICOLON = ";";
            dbUrl += SEMICOLON+"encrypt=false"+SEMICOLON+"integratedSecurity=false";
            //System.out.println("dbUrl = " + dbUrl);
        //}

        dbProperties = getMSSQLProperties(databaseUsername,databaseServerName,databasePortNumber,databaseName);
        dbXAProperties = getMSSQLXAProperties(databaseUsername,databaseServerName,databasePortNumber,databaseName);
        dbTestTable = DB_TEST_TABLE;
        url = T3_PROTOCOL + CMUtil.appServerHostName + COLON + CMUtil.appServerSOAP;
		if (CMUtil.SSLEnabled.equalsIgnoreCase(TRUE))
			url = T3S_PROTOCOL + CMUtil.appServerHostName + COLON + CMUtil.appServerSOAP;
        jdbcConnPoolName = CONNECTION_POOL_FOR_FILE_NET_ENGINE_DATASOURCE;
        jdbcConnPoolXAName = CONNECTION_POOL_FOR_FILE_NET_ENGINE_XA_DATASOURCE;
        try {
		FileReader inFR = new FileReader(script);
		BufferedReader inBR = new BufferedReader(inFR);
		FileWriter outFW = new FileWriter(wlPyRuntime);
		outFW.write(WL_ADMIN_VAR + SINGLE_QUOTE + CMUtil.appServerAdminUser + SINGLE_QUOTE + strEOL);
		outFW.write(WL_ADMIN_PASSWORD_VAR + SINGLE_QUOTE + CMUtil.appServerAdminPassword + SINGLE_QUOTE + strEOL);
		outFW.write(CONNECTION_URL_VAR + SINGLE_QUOTE + url + SINGLE_QUOTE + strEOL);
	    outFW.write(JDBC_NAME_VAR + SINGLE_QUOTE + jdbcConnPoolName + SINGLE_QUOTE + strEOL);
	    outFW.write(JDBC_XA_NAME_VAR + SINGLE_QUOTE + jdbcConnPoolXAName + SINGLE_QUOTE + strEOL);
	    outFW.write(DS_NAME_VAR + SINGLE_QUOTE + JDBCDSNamet.getText() + SINGLE_QUOTE + strEOL);
	    outFW.write(DS_XA_NAME_VAR + SINGLE_QUOTE + JDBCDSXANamet.getText() + SINGLE_QUOTE + strEOL);
	    outFW.write(TARGET_NAME_VAR + SINGLE_QUOTE + CMUtil.targetName + SINGLE_QUOTE + strEOL);
	    outFW.write(DB_TYPE_VAR + SINGLE_QUOTE + dbType + SINGLE_QUOTE + strEOL);
		outFW.write(DB_SERVER_VAR + SINGLE_QUOTE + DBServert.getText() + SINGLE_QUOTE + strEOL);
		outFW.write(DB_PORT_VAR + SINGLE_QUOTE + DBPortt.getText() + SINGLE_QUOTE + strEOL);
		outFW.write(DB_USER_VAR + SINGLE_QUOTE + DBUserNamet.getText() + SINGLE_QUOTE + strEOL);
		outFW.write(DB_PASSWORD_VAR + SINGLE_QUOTE + DBPasswordt.getText() + SINGLE_QUOTE + strEOL);
		outFW.write(DB_NAME_VAR + SINGLE_QUOTE + DBNamet.getText() + SINGLE_QUOTE + strEOL);
		outFW.write(DB_DRIVER_NAME_VAR + SINGLE_QUOTE + dbDriverName + SINGLE_QUOTE + strEOL);
		outFW.write(DB_DRIVER_XA_NAME_VAR + SINGLE_QUOTE + dbDriverXAName + SINGLE_QUOTE + strEOL);
		outFW.write(DB_URL_VAR + SINGLE_QUOTE + dbUrl + SINGLE_QUOTE + strEOL);
		outFW.write(DB_PROPS_VAR + SINGLE_QUOTE + dbProperties + SINGLE_QUOTE + strEOL);
		outFW.write(DB_XA_PROPS_VAR + SINGLE_QUOTE + dbXAProperties + SINGLE_QUOTE + strEOL);
		outFW.write(DB_TEST_TABLE_VAR + SINGLE_QUOTE + dbTestTable + SINGLE_QUOTE + strEOL); 
		outFW.write(DB_FAILOVER_VAR + SINGLE_QUOTE + dbFailover + SINGLE_QUOTE + strEOL); 
		
		String aLine;
		while ((aLine=inBR.readLine()) != null)
			outFW.write(aLine+ strEOL);
		
		inFR.close();
		inBR.close();
		outFW.close();
        }
        catch(IOException ex)
        {
        	ex.printStackTrace();
        }
    	
    }
    public String getOracleServiceNameUrl(String dbserver, String dbport, String dbname){
    	return JDBC_ORACLE_THIN + dbserver + COLON + dbport + "/" + dbname;
    }
    
    public String getDbProperties(String databaseUsername,String databaseServerName,String databasePortNumber,String databaseName) {
    	return "user="+databaseUsername+";portnumber="+databasePortNumber+";databaseName="+databaseName+";serverName="+databaseServerName;
    }
    
    public String getDbUrl(String databaseServerName,String databasePortNumber,String databaseName) {
    	return "jdbc:sqlserver://"+databaseServerName+":"+databasePortNumber;
    }
    
    public String getDbXAProperties(String databaseUsername,String databaseServerName,String databasePortNumber,String databaseName) {
    	return "user="+databaseUsername+";portnumber="+databasePortNumber+";selectMode=default;databaseName="+databaseName+";serverName="+databaseServerName;
    }
    private static String getMSSQLUrl(String dbserver, String dbport)
    {
        return JDBC_SQLSERVER + dbserver+ COLON + dbport; 
    }

    private static String getMSSQLProperties(String dbuser,String dbserver, String dbport, String dbname)
    {
        return USER + dbuser + PORTNUMBER + dbport + DATABASE_NAME + dbname +  SERVER_NAME + dbserver;
    }

    private static String getMSSQLXAProperties(String dbuser, String dbserver, String dbport, String dbname)
    {
        return USER + dbuser + PORTNUMBER + dbport + SELECT_MODE_DEFAULT_DATABASE_NAME + dbname + SERVER_NAME + dbserver;
    }
    
    //protected abstract String getDbProperties(String dbuser, String dbserver, String dbport, String dbname);
    
    public void testActionPerformed(ActionEvent e)
    {
    	executeTask exe = new executeTask();
    	String console = exe.test();
    	ConsoleOP.appendText(console);
    }
    	
    public class executeTask extends ConfigureWLGCDJDBC
	{
		
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

		@Override
		protected String getDbType() {
			// TODO Auto-generated method stub
			return DB_TYPE;
		}

		@Override
		protected String getDbDriverName() {
			// TODO Auto-generated method stub
			return DB_DRIVER_NAME;
		}

		@Override
		protected String getDbUrl(String dbserver, String dbport, String dbname) {
			// TODO Auto-generated method stub
			return getMSSQLUrl(dbserver, dbport);
		}

		@Override
		protected String getDbTestTable() {
			// TODO Auto-generated method stub
			return DB_TEST_TABLE;
		}
	}
}

