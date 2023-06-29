package com.ibm.ecm.configmgr;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JSeparator;
import javax.swing.JTextField;
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
import com.ibm.ecm.configmgr.ConfigureGCDJDBC_wassql.executeTask;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

public class ConfigureGCDJDBC_wasoracle extends JPanel {
	private JTextField JDBCDSNamet;
	private JTextField JDBCDSXANamet;
	private JTextField DBServert;
	private JTextField DBPortt;
	private JTextField DBNamet;
	private JPasswordField DBPasswordt;
	private JPasswordField passField_1;
	private JCheckBox OrServiceNamec;
	private JCheckBox DBFailOverc;
	private JComboBox<String> comboBox;
	private JTextField DBUserNamet;
	//private JTextField passField;
	private JTextField MinConnt;
	private JTextField MaxConnt;
	private JTextField UnusedTimet;
	private JTextField ReapTimet;
	private JTextField Scriptt;
	private JButton browseBtn;
	private JButton testBtn;
	private JButton saveBtn;
	private JButton runBtn;
	private JLabel lblNewLabel_8;
	public String DBFailover = "";
	public String jar = "";
	//private String xmlFilePath = "C:\\xmlexport_import\\trialconfig.xml";
	//private ORAGCDDS gcd;
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";
	
	final PropertyFactory props = new PropertyFactory();
	private static final String JDBC_TYPE = "4"; //$NON-NLS-1$
	private static final String ORACLE = "Oracle";
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
	
	public String jdbcProviderName,jdbcType ;

	/**
	 * Create the panel.
	 */
	public ConfigureGCDJDBC_wasoracle(String JDBCDSName,String JDBCDSXAName,String DBServer,String DBPort,String DBName,String OrServiceName,String DBUserName,
			String DBPassword,String Jar,String MinConn,String MaxConn,String UnusedTime,String ReapTime,String Script,String DBFailOver,final File file ) {
		//setLayout(null);
		
		

		JLabel lblNewLabel = props.setLabel("Configure Oracle GCD JDBC"); //new JLabel("Configure Oracle GCD JDBC");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_1 = props.setLabel("JDBC Data Source Name* :"); //new JLabel("JDBC Data Source Name :");
		
		JDBCDSNamet = props.getTextField(); //new JTextField();
		JDBCDSNamet.setToolTipText("jdbc datasource name");
		JDBCDSNamet.setText(JDBCDSName);
		JDBCDSNamet.setColumns(10);
		
		JLabel lblNewLabel_2 = props.setLabel("JDBC XA Data Source Name* :"); //new JLabel("JDBC XA Data Source Name:");
		
		JDBCDSXANamet = props.getTextField(); //new JTextField();
		JDBCDSXANamet.setText(JDBCDSXAName);
		JDBCDSXANamet.setColumns(10);
		
		JLabel lblNewLabel_3 = props.setLabel("Database Server name* :"); //new JLabel("Database Server name :");
		
		DBServert = props.getTextField(); //new JTextField();
		DBServert.setText(DBServer);
		DBServert.setColumns(10);
		
		JLabel lblNewLabel_4 = props.setLabel("Database Port Number* :"); //new JLabel("Database Port Number :");
		
		DBPortt = props.getTextField(); //new JTextField();
		DBPortt.setText(DBPort);
		DBPortt.setColumns(10);
		
		DBNamet = props.getTextField(); //new JTextField();
		DBNamet.setText(DBName);
		DBNamet.setColumns(10);
		
		JLabel lblNewLabel_5 = props.setLabel("Database Name* :"); //new JLabel("Database Name :");
		
		OrServiceNamec = props.getCheckBox("Oracle Service Name Enabled"); //new JCheckBox("Oracle Service Name Enabled");
		OrServiceNamec.setSelected(Boolean.parseBoolean(OrServiceName));
		
		JLabel lblNewLabel_6 = props.setLabel("Database Username* :"); //new JLabel("Database Username :");
		
		DBUserNamet = props.getTextField(); //new JTextField();
		DBUserNamet.setText(DBUserName);
		DBUserNamet.setColumns(10);
		
		JLabel lblNewLabel_7 = props.setLabel("Database Password* :"); //new JLabel("Database Password :");
		
		DBPasswordt = props.getPasswordField(); //new JPasswordField();
		DBPasswordt.setText(DBPassword);
		DBPasswordt.setColumns(10);
		
		lblNewLabel_8 = props.setLabel("OJDBC Jar* :");
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
		comboBox.setSelectedItem(new String(Jar));
		
		DBFailOverc = props.getCheckBox("Database Failover support Enabled"); //new JCheckBox("Database Failover support Enabled");
		DBFailOverc.setSelected(Boolean.parseBoolean(DBFailOver));
		
		JLabel lblNewLabel_9 = props.setLabel("Minimum Connections :"); //new JLabel("Minimum Connections :");
		
		MinConnt = props.getTextField(); //new JTextField();
		MinConnt.setText(MinConn);
		MinConnt.setColumns(10);
		
		JLabel lblNewLabel_10 = props.setLabel("Maximum Connections : "); //new JLabel("Maximum Connections : ");
		
		MaxConnt = props.getTextField(); //new JTextField();
		MaxConnt.setText(MaxConn);
		MaxConnt.setColumns(10);
		
		JLabel lblNewLabel_11 = props.setLabel("Unused Timeout (sec) :"); //new JLabel("Unused Timeout (sec) :");
		
		UnusedTimet = props.getTextField(); //new JTextField();
		UnusedTimet.setText(UnusedTime);
		UnusedTimet.setColumns(10);
		
		JLabel lblNewLabel_12 = props.setLabel("Reap time (sec) :"); //new JLabel("Reap time (sec) :");
		
		ReapTimet = props.getTextField(); //new JTextField();
		ReapTimet.setText(ReapTime);
		ReapTimet.setColumns(10);
		
		Scriptt = props.getTextField(); //new JTextField();
		Scriptt.setText(Script);
		Scriptt.setColumns(10);
		
		JLabel lblNewLabel_13 = props.setLabel("Script Directory :"); //new JLabel("Script Directory :");
		
		browseBtn = props.getButton("Browse"); //new JButton("Browse");
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
		
		testBtn = props.getButton("Test Database Connection"); //new JButton("Test Database Connection");
		testBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				testActionPerformed(e);
			}
		});
		
		JSeparator separator_1 = new JSeparator();
		
		saveBtn = props.getButton("Save"); //new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//PropertyFactory props = new PropertyFactory();
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
				props.setChildValueByName(doc1, PROPERTY, "OJDBC Jar", VALUE, props.getValue(comboBox));
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
				jdbcType = JDBC_TYPE;
		        jdbcProviderName = ORACLE;
			}
		});
		
		runBtn = props.getButton("Run task"); //new JButton("Run Task");
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
					.addGap(28)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 459, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 760, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(JDBCDSNamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(JDBCDSXANamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(DBServert, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(DBPortt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(DBNamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(OrServiceNamec, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(DBUserNamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(DBPasswordt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(DBFailOverc, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(MinConnt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(MaxConnt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(UnusedTimet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(ReapTimet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_13, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addComponent(Scriptt, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addComponent(browseBtn, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(190)
					.addComponent(testBtn, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 702, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(489)
					.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(runBtn, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_1))
						.addComponent(JDBCDSNamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_2))
						.addComponent(JDBCDSXANamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_3))
						.addComponent(DBServert, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_4))
						.addComponent(DBPortt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_5))
						.addComponent(DBNamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(OrServiceNamec, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
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
						.addComponent(DBFailOverc))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_9))
						.addComponent(MinConnt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_10))
						.addComponent(MaxConnt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_11))
						.addComponent(UnusedTimet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_12))
						.addComponent(ReapTimet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_13))
						.addComponent(Scriptt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(browseBtn))
					.addGap(20)
					.addComponent(testBtn)
					.addGap(24)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(saveBtn)
						.addComponent(runBtn)))
		);
		setLayout(groupLayout);
	}
	public void runActionPerformed(ActionEvent e)
	{
		String console = exec();
    	//new NewJFrame(CMUtil.profileName, console).setVisible(true);
        //new LoginModules().setVisible(true);
    	ConsoleOP.appendText(console);
        //dispose();
        //getContentPane().setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    //Store the details in the tmp script and execute the tmp script
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
    @SuppressWarnings("deprecation")
	public void setScript(String script, String wsTclRuntime) {
    	
    	String strEOL = CMUtil.getPlatformEOL();

    	//String script = getTaskCMProperties().getValue(ConfigKeys.SCRIPT);
        //String tempDir = getTaskCMProperties().getValue(ConfigKeys.TEMP_DIR);
        //String wsTclRuntime = tempDir+ SLASH + getSymbolicName() + TCL_EXT;
        //String wsPyRuntime = CMUtil.CANONICAL_CM_PATH + SLASH + "scripts" + SLASH + "customJVMProperty.py"; 
        String databaseUsername = DBUserNamet.getText();// WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_USERNAME)).trim();
        String databasePassword = DBPasswordt.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_PASSWORD));
        String jdbcProviderDesc = ""; //Messages.JDBC_PROVIDER_DESC_DESC;
        String jdbcDataSourceName = JDBCDSNamet.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.JDBC_DATASOURCE_NAME).trim());      // Remove leading & trailing whitespace
        String jdbcDataSourceXAName = JDBCDSXANamet.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.JDBC_DATASOURCE_XA_NAME).trim()); // Remove leading & trailing whitespace
        String databaseName = DBNamet.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_NAME));
        String databaseServerName = DBServert.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_SERVER_NAME));
        String databasePortNumber = DBPortt.getText(); //getTaskCMProperties().getValue(ConfigKeys.DATABASE_PORT_NUMBER);
       	String databaseVersion = ""; //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_VERSION));
       	String driverVersion = ""; //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.JDBC_DRIVER_VERSION));
       	boolean turnOnDBFailover = Boolean.parseBoolean(props.getValue(DBFailOverc)); //getTaskCMProperties().getValue(ConfigKeys.DBFAILOVER_ENABLED));
       	//changes for defect 3470 start
       	boolean databaseServiceNameOracleSet = Boolean.parseBoolean(props.getValue(OrServiceNamec)); //getTaskCMProperties().getValue(ConfigKeys.ORACLE_DATABASE_SERVICE_NAME_CHECK_BOX));
       	String odbcjarname = props.getValue(comboBox); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.ODBC_JAR_NAME));
       	//changes for defect 3470 end
        //changes for defect 9654 start
       	String mssqljarname = N_A; //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.MSSQL_JAR_NAME));
       	//changes for defect 9654 end
       	String zOsDbName = N_A;
        String zOsDefaultTblspace = N_A;
        String zOsDefaultSTOGroup = N_A;
        String zOsSTOGROUPOptions = N_A;
        //changes for defect 3470 start
        //URL for Oracle service name
        String url = "jdbc:oracle:thin:@//"+ databaseServerName+ ":" +databasePortNumber+ "/"+ databaseName;
//        String url = "jdbc:oracle:thin:@(description=(address=(host="+databaseServerName+")(protocol=tcp)(port="+databasePortNumber+"))(connect_data=(service_name="+databaseName+")(server=SHARED)))";
        //changes for defect 3470 end
        String retryIntervalForClientReroute = N_A;
        String maxRetriesForClientReroute = N_A;
        String clientRerouteAlternateServerName = N_A;
        String clientRerouteAlternatePortNumber = N_A;
        
        String applicationServerVersion = CMUtil.appServerVersion; //getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_VERSION);
        String majorVersion = applicationServerVersion.substring(0, applicationServerVersion.indexOf("."));//$NON-NLS-1$
        String applicationServerCell = CMUtil.appServerCell; //WebsphereUtil.escapeSpecialChar(getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_CELL).trim());
        boolean turnoffSSLcerts = Boolean.parseBoolean(CMUtil.turnOffSSL); //getEnvironmentCMProperties().getValue(ConfigKeys.TURNOFF_SSL_CERTIFICATE));

        
        
        //20484
        boolean sslEnabled = false;
        String jkskeyStorePath = N_A, jkskeyStoreType = N_A, jkskeyStorePassword = N_A;//JKS SSL
        String keyStorePath = N_A, keyStoreType = N_A, keyStorePassword = N_A;
        String minPoolSize = N_A, maxPoolSize = N_A, unusedTimeOut = N_A, reapTime = N_A;
            
            //20626
            minPoolSize = MinConnt.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.MIN_POOL_SIZE));
            maxPoolSize = MaxConnt.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.MAX_POOL_SIZE));
            unusedTimeOut = UnusedTimet.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.UNUSED_TIMEOUT));
            reapTime = ReapTimet.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.REAP_TIME));
        
        try {
            
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
		
		} catch (Exception e) {
			e.getMessage();
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
			return jdbcType;
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
    /*public String test() 
    {
    	String script = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\scripts\\testWASDBConnection.tcl";
        String tempFile = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\tmp";
        System.out.println("Hello");
        String result = testWork(script, tempFile);
                	    	
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
    public final String testWork(String script, String wsTclRuntime)
    {
        String test_result = EMPTY_STRING;
        String test_result1 = EMPTY_STRING;
        String test_result2 = EMPTY_STRING;
        String jdbcDataSourceName = EMPTY_STRING;
        String jdbcDataSourceXAName = EMPTY_STRING;
        //final String _6 = "6"; //$NON-NLS-1$
        boolean non_XA_exist = false;
        boolean XA_exist = false;
        boolean dsSSL = false; //20484
        System.out.println(DBUserNamet.getText());
        System.out.println(props.getValue(DBUserNamet));
        //WebSphereJMXInvoker invoker = new WebSphereJMXInvoker(JMXParametersResolver.getWebSphereConnectionParameters(getEnvironmentCMProperties()));
        try 
        {	
        	System.out.println("Hi");
        	//first test the datasources names
        	System.out.println(JDBCDSNamet.getText());
        	System.out.println(props.getValue(JDBCDSNamet));
        	jdbcDataSourceName = JDBCDSNamet.getText(); //getTaskCMProperties().getValue(ConfigKeys.JDBC_DATASOURCE_NAME);
        	jdbcDataSourceXAName = JDBCDSXANamet.getText(); //getTaskCMProperties().getValue(ConfigKeys.JDBC_DATASOURCE_XA_NAME);
        	System.out.println(jdbcDataSourceName + "," + jdbcDataSourceXAName);
            //test_result1 = invoker.testDataSourceNameExists(jdbcDataSourceName);
        	//test_result2 = invoker.testDataSourceNameExists(jdbcDataSourceXAName);
        	
        	/*if (test_result1.equalsIgnoreCase(TRUE)||test_result1.equalsIgnoreCase(FALSE))
        	{
                non_XA_exist = Boolean.valueOf(test_result1).booleanValue();
            	XA_exist = Boolean.valueOf(test_result2).booleanValue();
            	
            	// DTS 912139 - If one exist and not the other, it is an error.
            	// If both don't exist or both exist, go to actually test the connection.
            	if (non_XA_exist && !XA_exist)
            	{
            		String message = Messages.bind(Messages.NOT_BOTH_DATASOURCE_EXIST,jdbcDataSourceName,jdbcDataSourceXAName);
            		return Messages.bind (Messages.TESTING_DATASOURCE_NAMES_ERROR, message);
            	}
            	else if (!non_XA_exist && XA_exist)
            	{
            		String message = Messages.bind(Messages.NOT_BOTH_DATASOURCE_EXIST,jdbcDataSourceXAName,jdbcDataSourceName);
            		return Messages.bind (Messages.TESTING_DATASOURCE_NAMES_ERROR, message);
            	}
        	}
        	else
        		return test_result1;
        	
        	//test the database connection
            //String applicationServerVersion = getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_VERSION);
            //String majorVersion = applicationServerVersion.substring(0, applicationServerVersion.indexOf("."));//$NON-NLS-1$
             * 
             
        	System.out.println(jdbcProviderName);
        	String dbdriver = jdbcProviderName; //getTaskCMProperties().getValue(ConfigKeys.JDBC_DRIVER_NAME);
        	//if (dbdriver.contains("SSL enabled")) dbdriver = "OracleSSL";
        	if (dbdriver.contains("SSL")) dbdriver = "OracleSSL";//21053
            System.out.println(dbdriver);
            String databaseUsername = DBUserNamet.getText(); //getTaskCMProperties().getValue(ConfigKeys.DATABASE_USERNAME).trim();
            String databasePassword = DBPasswordt.getText(); //getTaskCMProperties().getValue(ConfigKeys.DATABASE_PASSWORD);
            String databaseName = DBNamet.getText(); //getTaskCMProperties().getValue(ConfigKeys.DATABASE_NAME);
            //changes for defect 3470 start
            boolean databaseServiceNameOracleSet = Boolean.parseBoolean(props.getValue(OrServiceNamec)); //getTaskCMProperties().getValue(ConfigKeys.ORACLE_DATABASE_SERVICE_NAME_CHECK_BOX));
            //changes for defect 3470 end
            String databaseServerName = props.getValue(DBServert); //getTaskCMProperties().getValue(ConfigKeys.DATABASE_SERVER_NAME);
            String databasePortNumber = props.getValue(DBPortt); //getTaskCMProperties().getValue(ConfigKeys.DATABASE_PORT_NUMBER);
            String databaseVersion = ""; //getTaskCMProperties().getValue(ConfigKeys.DATABASE_VERSION);
            String jdbcDriverVersion = ""; //getTaskCMProperties().getValue(ConfigKeys.JDBC_DRIVER_VERSION);
            //changes for defect 3470  start
           	String odbcjarname = props.getValue(comboBox); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.ODBC_JAR_NAME));
           	//changes for defect 3470 end
            //changes for defect 9654 start
           	//String mssqljarname = WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.MSSQL_JAR_NAME));
            //changes for defect 9654 end
            String driverImplementation = EMPTY_STRING; 
            String driverPath =EMPTY_STRING; 
            String driverType =EMPTY_STRING;//DB2 - only 
            String url = EMPTY_STRING;//Oracle - only 
            
            //20484                
            if ((CMUtil.isCmuiClient) && ((dbdriver).equalsIgnoreCase("OracleSSL")))
            	dsSSL = true;
            	//dsSSL = Boolean.parseBoolean(getTaskCMProperties().getValue(ConfigKeys.SSL_ENABLED));//Oracle - only 20484
            System.out.println(dsSSL);
            System.out.println(jdbcType);
            switch (Integer.parseInt(jdbcType))
            {
                case 3:
                	//driverType="4";//10.2
                	//if (mssqljarname.contains("10.2")) 
                	driverType="3";//41669
                    driverImplementation = "com.microsoft.sqlserver.jdbc.SQLServerConnectionPoolDataSource"; //$NON-NLS-1$
                    // majorVersion 7 and 8 and 8.5
                    //if (jdbcDriverVersion.equals("2.0")||jdbcDriverVersion.equals("3.0") ||jdbcDriverVersion.equals("4.0"))
                    //driverPath = "${MICROSOFT_JDBC_DRIVER_PATH}/"+mssqljarname; //$NON-NLS-1$
                    break;	
                case 4:
                    driverImplementation = "oracle.jdbc.pool.OracleConnectionPoolDataSource"; //$NON-NLS-1$
                    url = "jdbc:oracle:thin:@"+ databaseServerName+ ":" +databasePortNumber+ ":"+ databaseName; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    driverType="4"; //$NON-NLS-1$ - 36220
                    // majorVersion is  7 or 8 or 8.5
                    if (databaseVersion.equalsIgnoreCase("Oracle 10g")) //$NON-NLS-1$
                           driverPath = "${ORACLE_JDBC_DRIVER_PATH}/ojdbc14.jar"; //$NON-NLS-1$
                    else
                          driverPath = "${ORACLE_JDBC_DRIVER_PATH}/"+odbcjarname; //$NON-NLS-1$
                    //changes for defect 3470 start
                    if(databaseServiceNameOracleSet)
                    {
                    	url = "jdbc:oracle:thin:@//"+ databaseServerName+ ":" +databasePortNumber+ "/"+ databaseName;
                    	//driverPath = "${ORACLE_JDBC_DRIVER_PATH}/ojdbc6.jar";  
                    	//change for task 5710
                    	driverPath = "${ORACLE_JDBC_DRIVER_PATH}/"+odbcjarname;
                    }
                   //changes for defect 3470 end
                    
                   // 20484
                   if (dsSSL)
                   {
                	    url = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCPS)(HOST="+databaseServerName+")(PORT="+databasePortNumber+"))(CONNECT_DATA=(SERVICE_NAME="+ databaseName+")))";
                	    driverPath = "${ORACLE_JDBC_DRIVER_PATH}/"+odbcjarname;                    	   
                   }
                   
                   break;
                case 5:
                    driverType="4"; //$NON-NLS-1$
                    driverImplementation = "com.ibm.db2.jcc.DB2ConnectionPoolDataSource"; //$NON-NLS-1$
                    if (jdbcDriverVersion.equals("3.0")){
                    	driverPath = "${DB2UNIVERSAL_JDBC_DRIVER_PATH}/db2jcc.jar;${DB2UNIVERSAL_JDBC_DRIVER_PATH}/db2jcc_license_cu.jar"; //$NON-NLS-1$
                    } else {
                    	driverPath = "${DB2_JCC_DRIVER_PATH}/db2jcc4.jar; ${DB2_JCC_DRIVER_PATH}/db2jcc_license_cu.jar";
                    }
                    break;
                case 6:
                    driverType="4"; //$NON-NLS-1$
                    driverImplementation = "com.ibm.db2.jcc.DB2ConnectionPoolDataSource"; //$NON-NLS-1$
                    if (jdbcDriverVersion.equals("3.0")){
                		driverPath = "${DB2UNIVERSAL_JDBC_DRIVER_PATH}/db2jcc.jar;${DB2UNIVERSAL_JDBC_DRIVER_PATH}/db2jcc_license_cu.jar;${DB2UNIVERSAL_JDBC_DRIVER_PATH}/db2jcc_license_cisuz.jar"; //$NON-NLS-1$
                	} else {
                		driverPath = "${DB2_JCC_DRIVER_PATH}/db2jcc4.jar;${DB2_JCC_DRIVER_PATH}/db2jcc_license_cu.jar;${DB2_JCC_DRIVER_PATH}/db2jcc_license_cisuz.jar"; //$NON-NLS-1$	
                	}
                    break;
           }
            //if (!dsSSL) 
            //{
        	    //if (driverType.equals("3"))
            		//test_result = testDataBaseConnection(databaseUsername, databasePassword, databaseName, databaseServerName, databasePortNumber, driverType, databaseVersion, jdbcDriverVersion, databaseServiceNameOracleSet, odbcjarname, mssqljarname, url);
            	//else 
				//{
					//if (CMUtil.isGUI)
					//{
						//test_result = invoker.testDBConnection(driverImplementation, databaseUsername, databasePassword, databaseName, databaseServerName, databasePortNumber, driverPath, driverType, url);
					//}
					//else
						test_result = testDBDSConnection(databaseUsername, databasePassword, databaseName, databaseServerName, databasePortNumber, driverType, databaseVersion, jdbcDriverVersion, url, jdbcDataSourceName, jdbcDataSourceXAName, odbcjarname, script, wsTclRuntime); //jarType,
				//}
            //}
            //else
            	//test_result = testDBConnection(databaseUsername, databasePassword, databaseName, databaseServerName, databasePortNumber, odbcjarname, jdbcDataSourceName, jdbcDataSourceXAName);
        }
        
        catch(Exception e) {
            test_result = e.getLocalizedMessage();
            //LOGGER.exception(e.getLocalizedMessage(), e, this.getClass().getName(), "testWork()"); //$NON-NLS-1$
        }
        if (test_result.startsWith("DSRA8040I") && (dsSSL))
        	test_result = "Failed to connect to the Database. Please check: a. if SSL enabled is checked or b. Check the database parameters";

        /*if (non_XA_exist && XA_exist)
        {
        	return test_result + ' ' + Messages.bind(Messages.BOTH_DATASOURCE_EXIST,jdbcDataSourceName,jdbcDataSourceXAName );           	                
        }
        return test_result;
   }
    public String testDBDSConnection(String user, String password, String dbname, String dbServer, String port, String driverType, String databaseVersion, String jdbcDriverVersion, String url, String jdbcDataSourceName, String jdbcDataSourceXAName, String ojdbcjarType, String script, String wsTclRuntime ) //String jarName, 
    {
    	String strEOL = CMUtil.getPlatformEOL();
        String result = strEOL;
        try
        {
            String QUOTE = "\""; //$NON-NLS-1$
            //String DOT ="."; //$NON-NLS-1$
            //String JDBC_ORACLE_THIN = "jdbc:oracle:thin:@"; //$NON-NLS-1$

            String tempDir = wsTclRuntime; //wsgetTaskCMProperties().getValue(ConfigKeys.TEMP_DIR);
        	//File testTclFile = new File (script); //getTaskCMProperties().getValue(ConfigKeys.SCRIPT));
        	//String testTclParent = testTclFile.getParent();//+ File.separator + DOT + DOT + File.separator + "testDBConnect.tcl";
        	//String wsTclScript  = testTclParent + File.separator + "testWASDBConnection.tcl";
            String wsTclScript = script;
        	//System.out.println("wsTclScript path = " + wsTclScript);
        	//File testTclScript = new File(wsTclScript);
        	wsTclRuntime = tempDir+ SLASH + "testWASDBConnect.tcl";
            boolean dbconnect = false;
            
			FileReader inFR = new FileReader(wsTclScript);
			BufferedReader inBR = new BufferedReader(inFR);
			FileWriter outFW = new FileWriter(wsTclRuntime);
			
			outFW.write("set _aliasName " + QUOTE + user + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _aliasPassword " + QUOTE + password + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _dbName " + QUOTE + dbname + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _dbServerName " + QUOTE + dbServer + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _portNumber " + QUOTE + port + QUOTE+ strEOL);//$NON-NLS-1$    			
			outFW.write("set _jdbcProviderType " + QUOTE + driverType + QUOTE + strEOL);//$NON-NLS-1$    		
			outFW.write("set _dbVersion " + QUOTE + databaseVersion + QUOTE + strEOL);//$NON-NLS-1$    		
			outFW.write("set _driverVersion " + QUOTE + jdbcDriverVersion + QUOTE + strEOL);//$NON-NLS-1$
			outFW.write("set _dsURL " + QUOTE + url + QUOTE + strEOL);//$NON-NLS-1$
			//outFW.write("set _jarType " + QUOTE + jarName + QUOTE + strEOL);//$NON-NLS-1$
			outFW.write("set _dsName " + QUOTE + jdbcDataSourceName + QUOTE + strEOL);//$NON-NLS-1$
			outFW.write("set _dsXAName " + QUOTE + jdbcDataSourceXAName + QUOTE + strEOL);//$NON-NLS-1$  
			outFW.write("set _odbcjarType " + QUOTE + ojdbcjarType + QUOTE + strEOL);
			//outFW.write("set _mssqljarType " + QUOTE + mssqljarType + QUOTE + strEOL);
			outFW.write("set _oracleRacUrl " + QUOTE + QUOTE + strEOL);
			outFW.write("set _oracleServiceNameURL " + QUOTE + QUOTE + strEOL);
			//outFW.write("set _dsSSL " + QUOTE + dsSSL + QUOTE + strEOL);//$NON-NLS-1$
			outFW.write(strEOL);

			String aLine;
			while ((aLine=inBR.readLine()) != null)
					outFW.write(aLine+ strEOL);
			
			inFR.close();
			inBR.close();
			outFW.close();
			
            String tclLog =  tempDir+ File.separator + "testWASDBConnection.log";
                                            
            //WebsphereUtil.runScript(AppServerParametersResolver.getWebsphereScriptRunningConnectionParameters(getEnvironmentCMProperties()), wsTclRuntime,tclLog);

            //if (!dbconnect)
            //{
                String output = strEOL;
                inFR = new FileReader(tclLog);
                inBR = new BufferedReader(inFR);
                while (((aLine=inBR.readLine()) != null) && (!aLine.equals("")))
                {
            		output = aLine + strEOL;
                //	if (aLine.indexOf(Messages.DATABASE_CONNECTION_SUCCESSFUL) >= 0)
            		if (aLine.indexOf("Succesfully Connected") >= 0)
            		{
                		dbconnect = false;
                		break;
                	}
                	else if ((!aLine.startsWith("WASX7")) && (!aLine.contains("Failed with Exception")))//21053
                	{
                		dbconnect = true;
                		result += output;//21053
                	}
                }
                inFR.close();
                inBR.close();	               

                String remStr = "java.lang.ClassNotFoundException";//21053
        		if (result.contains(remStr))
        		{
        			result = result.substring(result.indexOf(remStr)+remStr.length()+2, result.length());
        		}
            //}
               
            if (!dbconnect)
            	result = "Successfully connected"; //Messages.DATABASE_CONNECTION_SUCCESSFUL;
             
        }
        catch(Exception e)
        {
        	e.printStackTrace();
           // LOGGER.exception(e.getLocalizedMessage(), e, this.getClass().getName(), "testDBConnection");
        	//throw new ConfigurationManagerException(NLS.bind(Messages.EXCEPTION_EXECUTING_TASK_CONFIGURATION, e.getLocalizedMessage()));
        }

        return result;

    }*/
}
