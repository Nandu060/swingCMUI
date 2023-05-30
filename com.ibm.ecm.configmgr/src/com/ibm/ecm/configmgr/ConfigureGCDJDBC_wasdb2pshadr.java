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
import java.util.StringTokenizer;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import org.w3c.dom.Element;

import com.ibm.Factory.PropertyFactory;

public class ConfigureGCDJDBC_wasdb2pshadr extends JPanel {
	private JTextField JDBCDSNamet;
	private JTextField JDBCDSXANamet;
	private JTextField DBservert;
	private JTextField DBPortt;
	private JTextField DBNamet;
	private JTextField RetryIntervalt;
	private JTextField DBUserNamet;
	private JPasswordField DBPasswordt;
	private JTextField MaxRetriest;
	private JTextField ServerPortClientt;
	private JTextField MinConnt;
	private JTextField MaxConnt;
	private JTextField UnusedTimet;
	private JTextField ReapTimet;
	private JTextField Scriptt;
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";
	private static final String DB2 = "DB2"; //$NON-NLS-1$
    private static final String EMPTY_STRING = ""; //$NON-NLS-1$
	private static final String DONE_CONFIGURING_XA_DATA_SOURCE = "Done configuring XA DataSource"; //$NON-NLS-1$
	private static final String DONE_CONFIGURING_DATA_SOURCE = "Done configuring DataSource"; //$NON-NLS-1$
	private static final String ALREADY_EXISTS = " already exists"; //$NON-NLS-1$
	private static final String LOG_EXT = ".log"; //$NON-NLS-1$
	private static final String QUOTE = "\""; //$NON-NLS-1$
	private static final String N_A = "N/A"; //$NON-NLS-1$
	private static final String TCL_EXT = ".tcl"; //$NON-NLS-1$
	private static final String SLASH = "/";
	private PropertyFactory props = new PropertyFactory();
	private static final String CONFIGURE_WS_JDBC_TCL = "configureWSJDBC.tcl"; //$NON-NLS-1$
	private static final String JDBC_TYPE = "5"; //$NON-NLS-1$ 
	private static String[] ServerPort_URLS;
	private static StringTokenizer ServerPorturl;
	private static int Count;
	private static String Server,Port;

	/**
	 * Create the panel.
	 */
	public ConfigureGCDJDBC_wasdb2pshadr(String JDBCDSName, String JDBCXAName, String DBServer, String DBPort, String DBName, String DBUserName, String DBPassword, String RetryInterval, String MaxRetries, 
			String ServerPortClient, String MinConn, String MaxConn, String UnusedTime, String ReapTime, String Script,final File file) {
		
		final PropertyFactory props = new PropertyFactory();
		JLabel lblNewLabel = props.setLabel("Configure DB2 (ps/HADR) GCD JDBC "); //new JLabel("Configure GCD JDBC ");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_1 = props.setLabel("JDBC Data Source name* :"); //new JLabel("JDBC Data Source name* :");
		
		JDBCDSNamet = props.getTextField(); //new JTextField();
		JDBCDSNamet.setColumns(10);
		JDBCDSNamet.setText(JDBCDSName);
		
		JLabel lblNewLabel_2 = props.setLabel("JDBC XA Data Source name* :");  //new JLabel("JDBC XA Data Source name* :");
		
		JDBCDSXANamet = props.getTextField(); //new JTextField();
		JDBCDSXANamet.setColumns(10);
		JDBCDSXANamet.setText(JDBCXAName);
		
		JLabel lblNewLabel_3 = props.setLabel("Database server name* :");  //new JLabel("Database server name* :");
		
		DBservert = props.getTextField();  //new JTextField();
		DBservert.setColumns(10);
		DBservert.setText(DBServer);
		
		JLabel lblNewLabel_4 = props.setLabel("Database port number* :");  //new JLabel("Database port number* :");
		
		DBPortt = props.getTextField(); //new JTextField();
		DBPortt.setColumns(10);
		DBPortt.setText(DBPort);
		
		JLabel lblNewLabel_5 = props.setLabel("Database name* :");  //new JLabel("Database name* :");
		
		DBNamet = props.getTextField(); //new JTextField();
		DBNamet.setColumns(10);
		DBNamet.setText(DBName);
		
		JLabel lblNewLabel_6 = props.setLabel("Retry interval for client reroute* :"); //new JLabel("Retry interval for client reroute* :");
		
		RetryIntervalt = props.getTextField(); //new JTextField();
		RetryIntervalt.setColumns(10);
		RetryIntervalt.setText(RetryInterval);
		
		JLabel lblNewLabel_7 = props.setLabel("Database username* :"); //new JLabel("Database username* :");
		
		DBUserNamet = props.getTextField(); //new JTextField();
		DBUserNamet.setColumns(10);
		DBUserNamet.setText(DBUserName);
		
		JLabel lblNewLabel_8 = props.setLabel("Database password* :"); //new JLabel("Database password* :");
		
		DBPasswordt = props.getPasswordField(); // new JPasswordField();
		DBPasswordt.setText(DBPassword);
		
		JLabel lblNewLabel_9 = props.setLabel("Maximum retries for client reroute* :"); //new JLabel("Maximum retries for client reroute* :");
		
		MaxRetriest = props.getTextField(); //new JTextField();
		MaxRetriest.setColumns(10);
		MaxRetriest.setText(MaxRetries);
		
		JLabel lblNewLabel_10 = props.setLabel("Alternate server name and port number* :"); // new JLabel("Alternate server name and port number* :");
		
		ServerPortClientt = props.getTextField(); //new JTextField();
		ServerPortClientt.setColumns(10);
		ServerPortClientt.setText(ServerPortClient);
		
		JLabel lblNewLabel_11 = props.setLabel("Minimum connections* :"); //new JLabel("Minimum connections* :");
		
		MinConnt = props.getTextField(); //new JTextField();
		MinConnt.setColumns(10);
		MinConnt.setText(MinConn);
		
		JLabel lblNewLabel_12 = props.setLabel("Maximum connections* :"); //new JLabel("Maximum connections* :");
		
		MaxConnt = props.getTextField(); //new JTextField();
		MaxConnt.setColumns(10);
		MaxConnt.setText(MaxConn);
		
		JLabel lblNewLabel_13 = props.setLabel("Unused Timeout (sec)* :"); //new JLabel("Unused Timeout (sec)* :");
		
		UnusedTimet = props.getTextField(); //new JTextField();
		UnusedTimet.setColumns(10);
		UnusedTimet.setText(UnusedTime);
		
		JLabel lblNewLabel_14 = props.setLabel("Reap time (sec)* :"); //new JLabel("Reap time (sec)* :");
		
		ReapTimet = props.getTextField(); //new JTextField();
		ReapTimet.setColumns(10);
		ReapTimet.setText(ReapTime);
		
		JLabel lblNewLabel_15 = props.setLabel("Script"); //new JLabel("Script* :");
		
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
		
		Scriptt = props.getTextField(); //new JTextField();
		Scriptt.setColumns(10);
		Scriptt.setText(Script);
		
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
				props.setChildValueByName(doc1, PROPERTY, "DatabaseServerName", VALUE, DBservert.getText());
				props.setChildValueByName(doc1, PROPERTY, "DatabasePortNumber", VALUE, DBPortt.getText());
				props.setChildValueByName(doc1, PROPERTY, "DatabaseName", VALUE, DBNamet.getText());
				props.setChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE, DBUserNamet.getText());
				props.setChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE, DBPasswordt.getText());
				props.setChildValueByName(doc1, PROPERTY, "retryIntervalForClientReroute", VALUE,RetryIntervalt.getText());
				props.setChildValueByName(doc1, PROPERTY, "maxRetriesForClientReroute", VALUE,MaxRetriest.getText());
				props.setChildValueByName(doc1, PROPERTY, "ServerPortClientReroute", VALUE,ServerPortClientt.getText());
				props.setChildValueByName(doc1, PROPERTY, "Minimum connections", VALUE, MinConnt.getText());
				props.setChildValueByName(doc1, PROPERTY, "Maximum connections", VALUE, MaxConnt.getText());
				props.setChildValueByName(doc1, PROPERTY, "Unused timeout (seconds)", VALUE, UnusedTimet.getText());
				props.setChildValueByName(doc1, PROPERTY, "Reap time (seconds)", VALUE, ReapTimet.getText());
				props.setChildValueByName(doc1, PROPERTY, "Script", VALUE, Scriptt.getText());
				props.saveXMLDoc(file.toString());
				String[] names = props.getElementNamesByTagName(doc1, PROPERTY);
				for(int i=0;i<names.length;i++)
				{
					//System.out.println(names[i]);
					System.out.println(props.getChildValueByName(doc1, PROPERTY, names[i], VALUE));
				}
			}
		});
		
		JButton btnNewButton_3 = new JButton("Test Database Connection");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 873, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(JDBCDSNamet, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(95)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(JDBCDSXANamet, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(DBservert, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(95)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(DBPortt, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(DBNamet, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(82)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(RetryIntervalt, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(DBUserNamet, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(95)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(DBPasswordt, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(MaxRetriest, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addGap(56)
					.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(ServerPortClientt, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(MinConnt, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(95)
					.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(MaxConnt, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_13, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(UnusedTimet, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(95)
					.addComponent(lblNewLabel_14, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(ReapTimet, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_15, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(Scriptt, GroupLayout.PREFERRED_SIZE, 483, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(browseBtn, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 873, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(432)
					.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(runBtn, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_1))
						.addComponent(JDBCDSNamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_2))
						.addComponent(JDBCDSXANamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_3))
						.addComponent(DBservert, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_4))
						.addComponent(DBPortt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_5))
						.addComponent(DBNamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_6))
						.addComponent(RetryIntervalt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_7))
						.addComponent(DBUserNamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_8))
						.addComponent(DBPasswordt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_9))
						.addComponent(MaxRetriest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE))
						.addComponent(ServerPortClientt, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_11))
						.addComponent(MinConnt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_12))
						.addComponent(MaxConnt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_13))
						.addComponent(UnusedTimet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_14))
						.addComponent(ReapTimet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_15))
						.addComponent(Scriptt, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(browseBtn))
					.addGap(8)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_3)
						.addComponent(saveBtn)
						.addComponent(runBtn)))
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
		try
        {
        	String strEOL = CMUtil.getPlatformEOL();

        	//String script = getTaskCMProperties().getValue(ConfigKeys.SCRIPT);
            //String tempDir = getTaskCMProperties().getValue(ConfigKeys.TEMP_DIR);
            //String wsTclRuntime = tempDir+ SLASH + getSymbolicName() + TCL_EXT;

            String databaseUsername = DBUserNamet.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_USERNAME));
            String databasePassword = DBPasswordt.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_PASSWORD));
            String jdbcProviderDesc = ""; //Messages.JDBC_PROVIDER_DESC_DESC;
            String jdbcDataSourceName = JDBCDSNamet.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.JDBC_DATASOURCE_NAME).trim());      // Remove leading & trailing whitespace
            String jdbcDataSourceXAName = JDBCDSXANamet.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.JDBC_DATASOURCE_XA_NAME).trim()); // Remove leading & trailing whitespace
            String databaseName = DBNamet.getText();// WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_NAME));
            String databaseServerName = DBservert.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_SERVER_NAME));
            String databasePortNumber = DBPortt.getText(); //getTaskCMProperties().getValue(ConfigKeys.DATABASE_PORT_NUMBER);
           	String databaseVersion = ""; //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_VERSION));
           	String driverVersion = ""; //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.JDBC_DRIVER_VERSION));
           	boolean turnOnDBFailover = false; //Boolean.parseBoolean(getTaskCMProperties().getValue(ConfigKeys.DBFAILOVER_ENABLED));
           	String zOsDbName = N_A;
            String zOsDefaultTblspace = N_A;
            String zOsDefaultSTOGroup = N_A;
            String zOsSTOGROUPOptions = N_A;
            String applicationServerVersion = CMUtil.appServerVersion; //getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_VERSION);
            String majorVersion = applicationServerVersion.substring(0, applicationServerVersion.indexOf("."));//$NON-NLS-1$
            String applicationServerCell = CMUtil.appServerCell; //WebsphereUtil.escapeSpecialChar(getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_CELL).trim());
            boolean turnoffSSLcerts = Boolean.parseBoolean(CMUtil.turnOffSSL); //getEnvironmentCMProperties().getValue(ConfigKeys.TURNOFF_SSL_CERTIFICATE));
            
            //DB2 PureScale / HADR values
            String retryIntervalForClientReroute = RetryIntervalt.getText(); //getTaskCMProperties().getValue(ConfigKeys.RETRY_INTERVAL_CLIENT_REROUTE).trim();
            String maxRetriesForClientReroute = MaxRetriest.getText(); //getTaskCMProperties().getValue(ConfigKeys.MAX_RETRIES_CLIENT_REROUTE).trim();
            String ServerPorturlClientReroute =  ServerPortClientt.getText(); //getTaskCMProperties().getValue(ConfigKeys.SERVER_PORT_CLIENT_REROUTE).trim();
            TokenizeUrl(ServerPorturlClientReroute);
            //end of DB2 PureScale / HADR values
            
          //changes for defect 6652  start
           	String odbcjarname = N_A; //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.ODBC_JAR_NAME));
           	//changes for defect 6652 end
            
           	//changes for defect 10589  start,related 9654
           	String mssqljarname = N_A; //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.MSSQL_JAR_NAME));
           	//changes for defect 10589 end
            //String	jdbcType = getJDBCtype();
            String jdbcProviderName = "JDBC Provider for DB2 (pureScale/HADR)"; //NLS.bind(Messages.JDBC_PROVIDER_NAME_DESC, getProviderName());
            
            String minPoolSize = N_A, maxPoolSize = N_A, unusedTimeOut = N_A, reapTime = N_A;
            //if (CMUtil.isCmuiClient)
            //{
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
			outFW.write("set _jdbcProviderType " + QUOTE + JDBC_TYPE + QUOTE+ strEOL);//$NON-NLS-1$ 
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
			
			
			outFW.write("set _ClientRerouteEnable " + QUOTE + "true" + QUOTE+ strEOL);//$NON-NLS-1$
			outFW.write("set _retryIntervalForClientReroute " + QUOTE + retryIntervalForClientReroute + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _maxRetriesForClientReroute " + QUOTE + maxRetriesForClientReroute + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _clientRerouteAlternateServerName " + QUOTE + Server + QUOTE+ strEOL);//$NON-NLS-1$
			outFW.write("set _clientRerouteAlternatePortNumber " + QUOTE + Port + QUOTE+ strEOL);//$NON-NLS-1$
			
			outFW.write("set _zOsDbName " + QUOTE + zOsDbName + QUOTE+ strEOL); //$NON-NLS-1$ 
			outFW.write("set _zOsDefaultTblspace " + QUOTE + zOsDefaultTblspace + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _zOsDefaultSTOGroup " + QUOTE + zOsDefaultSTOGroup + QUOTE+ strEOL); //$NON-NLS-1$ 
			outFW.write("set _zOsSTOGROUPOptions " + QUOTE + zOsSTOGROUPOptions + QUOTE+ strEOL);//$NON-NLS-1$ 
			
			//changes for defect 6652 start
			outFW.write("set _odbcjarType " + QUOTE + odbcjarname + QUOTE+ strEOL);//$NON-NLS-1$
			//changes for defect 6652 end

			//changes for defect 10589,related 9654
			outFW.write("set _mssqljarType " + QUOTE + mssqljarname + QUOTE+ strEOL);//$NON-NLS-1$
			//changes for defect 10589 end
			
			//if (CMUtil.isCmuiClient)
			//{
    			//20626
    			outFW.write("set _minpoolsize " + Integer.parseInt(minPoolSize) + strEOL);
    			outFW.write("set _maxpoolsize " + Integer.parseInt(maxPoolSize) + strEOL);
    			outFW.write("set _unusedtimeout " + Integer.parseInt(unusedTimeOut) + strEOL);
    			outFW.write("set _reaptime " + Integer.parseInt(reapTime) + strEOL);
   			//}
			
			String aLine;
			while ((aLine=inBR.readLine()) != null)
			{
				if (aLine.startsWith("set _turnoffSSLcerts") && !turnoffSSLcerts) //$NON-NLS-1$
					outFW.write("set _turnoffSSLcerts \"true\""+ strEOL); //$NON-NLS-1$
				else if (aLine.startsWith("set _dbVersion")) //$NON-NLS-1$
					outFW.write("set _dbVersion " + QUOTE + databaseVersion + QUOTE+ strEOL);//$NON-NLS-1$ 
				else if (aLine.startsWith("set _driverVersion")) //$NON-NLS-1$
					outFW.write("set _driverVersion " + QUOTE + driverVersion + QUOTE+ strEOL);//$NON-NLS-1$ 
				else if (aLine.startsWith("set _enableSysplexWLB")) //$NON-NLS-1$
					outFW.write("set _enableSysplexWLB " + QUOTE + "true" + QUOTE+ strEOL); //$NON-NLS-1$
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
	private static void TokenizeUrl(String val){
    	
    	if ( val.contains(",")){
			ServerPorturl = new StringTokenizer(val,",");
			Count = ServerPorturl.countTokens();
			ServerPort_URLS = new String[Count];
			for(int i=0;ServerPorturl.hasMoreElements();i++){
				ServerPort_URLS[i]=ServerPorturl.nextToken();
			}
		}else{ 
			ServerPort_URLS = new String[] {val} ;
			Count = 1;
		}
    	for(int i=0;i<Count;i++){
    		if(i==0){
    			Server= ServerPort_URLS[i].substring(0, ServerPort_URLS[i].indexOf(":"));
    			Port=ServerPort_URLS[i].substring(ServerPort_URLS[i].indexOf(":")+1,ServerPort_URLS[i].length());
    			continue;
    		}	
    		Server = Server + "," + ServerPort_URLS[i].substring(0, ServerPort_URLS[i].indexOf(":"));
    		Port = Port + "," + ServerPort_URLS[i].substring(ServerPort_URLS[i].indexOf(":")+1,ServerPort_URLS[i].length());
    	}
    	
    }
}

