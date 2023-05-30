package com.ibm.ecm.configmgr;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

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

public class ConfigureGCDJDBC_wlorrac extends JPanel {
	private JTextField JDBCDSNamet;
	private JTextField JDBCDSXANamet;
	private JTextField OrRacUrlt;
	private JTextField DBUserNamet;
	private JTextField textField_6;
	private JTextField Scriptt;
	private JPasswordField DBPasswordt;
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
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";
	private PropertyFactory props = new PropertyFactory();
	private static final String CONFIGURE_WL_JDBC_PY = "configureWLJDBC.py"; //$NON-NLS-1$
	private static final String DB_TYPE = "ORACLE"; //$NON-NLS-1$ 
	private static final String DB_DRIVER_NAME = "oracle.jdbc.OracleDriver";//$NON-NLS-1$
	private static final String DB_DRIVER_XA_NAME = "oracle.jdbc.xa.client.OracleXADataSource";//$NON-NLS-1$
	private static final String DB_TEST_TABLE = "SQL SELECT 1 FROM DUAL";//$NON-NLS-1$

	private static String[] DATABASE_SERVER_NAME_RAC_NODES,DATABASE_PORT_NUMBER_RAC_NODES,DATABASE_NAME_RAC_NODES;
	private static StringTokenizer oracleracurl;
	private static int nodes = 0;
	

	/**
	 * Create the panel.
	 */
	public ConfigureGCDJDBC_wlorrac(String JDBCDSName,String JDBCDSXName,String OrRacUrl,String DBUserName,String DBPassword,String Script,String DBFailOver,final File file) {
		
		JLabel lblNewLabel = props.setLabel("Configure GCD JDBC Data Source"); //new JLabel("Configure GCD JDBC Data Source");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_1 = props.setLabel("JDBC Data Source Name* :"); //new JLabel("JDBC Data Source Name* :");
		
		JDBCDSNamet = props.getTextField(); //new JTextField();
		JDBCDSNamet.setText(JDBCDSName);
		JDBCDSNamet.setColumns(10);
		
		JLabel lblNewLabel_2 = props.setLabel("JDBC XA Data Source Name* :"); //new JLabel("JDBC XA Data Source Name* :");
		
		JDBCDSXANamet = props.getTextField(); //new JTextField();
		JDBCDSXANamet.setText(JDBCDSXName);
		JDBCDSXANamet.setColumns(10);
		
		JLabel lblNewLabel_3 = props.setLabel("Oracle JDBC RAC Data* :"); //new JLabel("Oracle JDBC RAC Data* :");
		
		OrRacUrlt = props.getTextField(); //new JTextField();
		OrRacUrlt.setText(OrRacUrl);
		OrRacUrlt.setColumns(10);
		
		JLabel lblNewLabel_5 = props.setLabel("Database username* :"); //new JLabel("Database username* :");
		
		DBUserNamet = props.getTextField(); //new JTextField();
		DBUserNamet.setText(DBUserName);
		DBUserNamet.setColumns(10);
		
		JLabel lblNewLabel_6 = props.setLabel("Database password* :"); //new JLabel("Database password* :");
		
		DBPasswordt = props.getPasswordField(); //new JPasswordField();
		DBPasswordt.setText(DBPassword);
		DBPasswordt.setColumns(10);
		
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
		
		DBFailOverc = props.getCheckBox("Database failover support enabled"); //new JCheckBox("Database failover support enabled");
		DBFailOverc.setSelected(Boolean.parseBoolean(DBFailOver));
		
		JSeparator separator_1 = new JSeparator();
		
		JButton testBtn = props.getButton("Test Database connection"); //new JButton("Test DB connection");
		testBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
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
				props.setChildValueByName(doc1, PROPERTY, "OracleRacURL", VALUE, OrRacUrlt.getText());
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
		
		JButton runBtn = props.getButton("Run task"); //new JButton("Run Task");
		runBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				runActionPerformed(e);
			}
		});
		
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
					.addComponent(OrRacUrlt, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE))
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
					.addGap(12)
					.addComponent(Scriptt, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(browseBtn, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(334)
					.addComponent(DBFailOverc, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 770, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(334)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(testBtn)
							.addGap(104)
							.addComponent(runBtn, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(174)
							.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))))
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
						.addComponent(OrRacUrlt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_5))
						.addComponent(DBUserNamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_6))
						.addComponent(DBPasswordt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(9)
							.addComponent(lblNewLabel_8))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(Scriptt, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addComponent(browseBtn))
					.addGap(21)
					.addComponent(DBFailOverc, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(testBtn)
						.addComponent(runBtn)
						.addComponent(saveBtn)))
		);
		setLayout(groupLayout);

	}
	public void runActionPerformed(ActionEvent e)
	{
		String console = exec();
    	new NewJFrame(CMUtil.profileName, console).setVisible(true);
	}
	public String exec() {
    	String script = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\scripts\\configureWLLDAP.py";
        String tempFile = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\tmp\\configureldap.py";
        setScript(script, tempFile);
                	    	
    	String EMPTY_STRING = "";
    	String command = EMPTY_STRING;
		//String jvmargs = EMPTY_STRING;
		//String arguments = EMPTY_STRING;
		String workingDir = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure";
		File workingDirf = new File(workingDir);
		
		command = "configmgr_cl execute -task configuregcdjdbc -profile "+CMUtil.profileName;
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
	public void setScript(String script, String wlPyRuntime) 
    {
    	
    	String strEOL = CMUtil.getPlatformEOL();

		String dsList=""; //$NON-NLS-1$
		String dsXAList=""; //$NON-NLS-1$
		TokenizeOracleRACUrl(OrRacUrlt.getText());	
		//create the datasources
		for (int i=0;i<nodes;i++)
		{
			boolean turnOnDBFailover = Boolean.parseBoolean(props.getValue(DBFailOverc)); //getTaskCMProperties().getValue(ConfigKeys.DBFAILOVER_ENABLED));
			//String script = getTaskCMProperties().getValue(ConfigKeys.SCRIPT);
			//String tempDir = getTaskCMProperties().getValue(ConfigKeys.TEMP_DIR);
			//String wlPyRuntime = tempDir+File.separator + getSymbolicName() + "node" + (i+1) + ".py";//$NON-NLS-1$ //$NON-NLS-2$
			String databaseUsername = DBUserNamet.getText(); //getTaskCMProperties().getValue(ConfigKeys.DATABASE_USERNAME);
			String databasePassword = DBPasswordt.getText(); //getTaskCMProperties().getValue(ConfigKeys.DATABASE_PASSWORD);
			String jdbcConnPoolName = "Connection Pool For FileNet Engine Datasource";//$NON-NLS-1$
			String jdbcConnPoolXAName = "Connection Pool For FileNet Engine XA Datasource";//$NON-NLS-1$
			String jdbcDataSourceName = JDBCDSNamet.getText(); //getTaskCMProperties().getValue(ConfigKeys.JDBC_DATASOURCE_NAME)+"node"+i+1; //$NON-NLS-1$
			String jdbcDataSourceXAName = JDBCDSXANamet.getText(); //getTaskCMProperties().getValue(ConfigKeys.JDBC_DATASOURCE_XA_NAME)+"node"+i+1; //$NON-NLS-1$
			if (i==0)
			{
				dsList+=jdbcDataSourceName;
				dsXAList+=jdbcDataSourceXAName;
			}
			else
			{
				dsList+=","+jdbcDataSourceName; //$NON-NLS-1$
				dsXAList+=","+jdbcDataSourceXAName; //$NON-NLS-1$
			}

			String databaseName =        DATABASE_NAME_RAC_NODES[i];
			String databaseServerName =  DATABASE_SERVER_NAME_RAC_NODES[i];
			String databasePortNumber =  DATABASE_PORT_NUMBER_RAC_NODES[i];

			String applicationServerHost = CMUtil.appServerHostName; //getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_HOST);
			String targetName = CMUtil.targetName; //getEnvironmentCMProperties().getValue(ConfigKeys.TARGET_NAME);				
			String applicationServerAdminUsername = CMUtil.appServerAdminUser; //getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_ADMIN_USERNAME);
			String applicationServerAdminPassword = CMUtil.appServerAdminPassword; //getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_ADMIN_PASSWORD);
			String applicationServerSoapPort = CMUtil.appServerSOAP; //getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_SOAP_PORT);
			String applicationServerSSLEnabled = CMUtil.SSLEnabled; //getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_SSL_ENABLED);
			String url = "t3://"+applicationServerHost+":"+applicationServerSoapPort;
			if (applicationServerSSLEnabled.equalsIgnoreCase("true"))
				url = "t3s://"+applicationServerHost+":"+applicationServerSoapPort;

			String dbType = DB_TYPE;
			String dbDriverName = DB_DRIVER_NAME;
			String dbDriverXAName = DB_DRIVER_XA_NAME;
			String dbUrl = getOracleRACUrl(databaseServerName, databasePortNumber, databaseName);
			String dbProperties = getOracleRACProperties(databaseUsername);
			String dbXAProperties = getOracleRACXAProperties(databaseUsername); //XAProperties(databaseUsername, databaseServerName,databasePortNumber, databaseName);
			String dbTestTable = DB_TEST_TABLE;
			try {
			FileReader inFR = new FileReader(script);
			BufferedReader inBR = new BufferedReader(inFR);
			if (new File(wlPyRuntime).exists())
				new File(wlPyRuntime).delete();
			FileWriter outFW = new FileWriter(wlPyRuntime);
			outFW.write("wlAdmin='" + applicationServerAdminUsername + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("wlAdminPassword='" + applicationServerAdminPassword + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("connectionURL='" + url + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("jdbcName='" + jdbcConnPoolName + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("jdbcXaName='" + jdbcConnPoolXAName + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("dsName='" + jdbcDataSourceName + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("dsXaName='" + jdbcDataSourceXAName + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("targetName='" + targetName + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("dbType='" + dbType + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("dbServer='" + databaseServerName + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("dbPort='" + databasePortNumber + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("dbUser='" + databaseUsername + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("dbPassword='" + databasePassword + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("dbName='" + databaseName + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("dbDriverName='" + dbDriverName + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("dbDriverXaName='" + dbDriverXAName + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("dbURL='" + dbUrl + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("dbProps='" + dbProperties + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("dbXaProps='" + dbXAProperties + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("dbTestTable='" + dbTestTable + "'" + strEOL);//$NON-NLS-1$ //$NON-NLS-2$
			outFW.write("dbFailOver='" + turnOnDBFailover + "'" + strEOL); 

			String aLine;
			while ((aLine=inBR.readLine()) != null)
				outFW.write(aLine+ strEOL);

			inFR.close();
			inBR.close();
			outFW.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
    	
    }
    public String getOracleServiceNameUrl(String dbserver, String dbport, String dbname){
    	return JDBC_ORACLE_THIN + dbserver + COLON + dbport + "/" + dbname;
    }
    
    public String getDbProperties(String databaseUsername,String databaseServerName,String databasePortNumber,String databaseName) {
    	return "user="+databaseUsername+";portnumber="+databasePortNumber+";databaseName="+databaseName+";serverName="+databaseServerName;
    }
    
    public String getDbUrl(String databaseServerName,String databasePortNumber,String databaseName) {
    	return "jdbc:sqlserver://ms2022sql1.fyre.ibm.com:1433";
    }
    
    public String getDbXAProperties(String databaseUsername,String databaseServerName,String databasePortNumber,String databaseName) {
    	return "user=daphne;portnumber=1433;selectMode=default;databaseName=MS_sampledb;serverName=ms2022sql1.fyre.ibm.com";
    }
    
    //protected abstract String getDbProperties(String dbuser, String dbserver, String dbport, String dbname);
    	
    private static void TokenizeOracleRACUrl(String OracleRACUrl){
		oracleracurl = new StringTokenizer(OracleRACUrl, ":/,");
		nodes = oracleracurl.countTokens()/3;
		DATABASE_SERVER_NAME_RAC_NODES = new String[nodes];
		DATABASE_PORT_NUMBER_RAC_NODES = new String[nodes];
		DATABASE_NAME_RAC_NODES = new String[nodes];

		for(int i=0;oracleracurl.hasMoreTokens();i++) {

			DATABASE_SERVER_NAME_RAC_NODES[i]=oracleracurl.nextToken();
			DATABASE_PORT_NUMBER_RAC_NODES[i]=oracleracurl.nextToken();
			DATABASE_NAME_RAC_NODES[i]=oracleracurl.nextToken();

		}
	}
    
    private static String getOracleRACUrl(String dbserver, String dbport, String dbname)
	{
		return "jdbc:oracle:thin:@" + dbserver + ":" + dbport + "/" + dbname;//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	private static String getOracleRACProperties(String dbuser)
	{
		return "user=" + dbuser;//$NON-NLS-1$
	}

	private static String getOracleRACXAProperties(String dbuser)
	{
		return "user=" + dbuser;//$NON-NLS-1$
	}
    	
    
}

