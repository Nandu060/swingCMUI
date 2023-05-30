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
import java.io.InputStream;
import java.util.StringTokenizer;

import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.ibm.Factory.PropertyFactory;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import org.w3c.dom.Element;

public class ConfigureGCDJDBC_wasorrac extends JPanel {
	private JTextField JDBCDSNamet;
	private JTextField JDBCDSXANamet;
	private JTextField OrRacUrlt;
	private JTextField DBServicet;
	private JTextField OrRacEntt;
	private JTextField DBUserNamet;
	private JTextField MinConnt;
	private JTextField MaxConnt;
	private JTextField UnusedTimet;
	private JTextField ReapTimet;
	private JTextField Scriptt;
	private JTextField OrRacDelt;
	private JPasswordField DBPasswordt;
	private JComboBox<String> comboBox;
	private JCheckBox DBFailOverc;
	private String jar;
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";
	private static final String DONE_CONFIGURING_XA_DATA_SOURCE = "Done configuring XA DataSource"; //$NON-NLS-1$
	private static final String DONE_CONFIGURING_DATA_SOURCE = "Done configuring DataSource"; //$NON-NLS-1$
	private static final String LOG_EXT = ".log"; //$NON-NLS-1$
	private static final String QUOTE = "\""; //$NON-NLS-1$
	private static final String N_A = "N/A";  //$NON-NLS-1$
	private static final String EMPTY_STRING = ""; //$NON-NLS-1$
	private static final String SLASH = "/"; //$NON-NLS-1$
	private static final String TCL_EXT = ".tcl"; //$NON-NLS-1$
	private static final String ALREADY_EXISTS = " already exists";
	private static final String CONFIGURE_WS_JDBC_TCL = "configureWSJDBC.tcl"; //$NON-NLS-1$
	private static int nodes = 0; 
    private static final String JDBC_TYPE = "4"; //$NON-NLS-1$ 
    
    private static String[] DATABASE_SERVER_NAME_RAC_NODES,DATABASE_PORT_NUMBER_RAC_NODES;
	private static StringTokenizer wsoracleracurl;
	private PropertyFactory props = new PropertyFactory();
	

	/**
	 * Create the panel.
	 */
	public ConfigureGCDJDBC_wasorrac(String JDBCDSName, String JDBCDSXAName, String OrRacUrl, String DBService, String OrRacEnt, 
			String OrRacDel, String DBUserName, String DBPassword, String Jar, String MinConn, String MaxConn, String UnusedTime, String ReapTime, 
			String Script, String DBFailOver, final File file) {
		
		final PropertyFactory props = new PropertyFactory();
		
		JLabel lblNewLabel = props.setLabel("Configure Oracle (RAC) GCD JDBC"); //new JLabel("Configure Oracle (RAC) GCD JDBC");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_1 = props.setLabel("JDBC Data Source Name* :"); //new JLabel("JDBC Data Source Name :");
		
		JDBCDSNamet = props.getTextField(); //new JTextField();
		JDBCDSNamet.setToolTipText("jdbc datasource name");
		JDBCDSNamet.setColumns(10);
		JDBCDSNamet.setText(JDBCDSName);
		
		JLabel lblNewLabel_2 = props.setLabel("JDBC XA Data Source Name* :"); //new JLabel("JDBC XA Data Source Name:");
		
		JDBCDSXANamet = props.getTextField(); //new JTextField();
		JDBCDSXANamet.setColumns(10);
		JDBCDSXANamet.setText(JDBCDSXAName);
		
		JLabel lblNewLabel_3 = props.setLabel("Oracle JDBC RAC Data* :"); //new JLabel("Oracle JDBC RAC Data :");
		
		OrRacUrlt = props.getTextField(); //new JTextField();
		OrRacUrlt.setColumns(10);
		OrRacUrlt.setText(OrRacUrl);
		
		JLabel lblNewLabel_4 = props.setLabel("Database Service Name* :"); //new JLabel("Database Service Name :");
		
		DBServicet = props.getTextField(); //new JTextField();
		DBServicet.setColumns(10);
		DBServicet.setText(DBService);
		
		OrRacEntt = props.getTextField(); //new JTextField();
		OrRacEntt.setColumns(10);
		OrRacEntt.setText(OrRacEnt);
		
		JLabel lblNewLabel_5 = props.setLabel("Oracle RAC retries* :"); //new JLabel("Oracle RAC retries :");
		
		JLabel lblNewLabel_6 = props.setLabel("Database Username* :"); //new JLabel("Database Username :");
		
		DBUserNamet = props.getTextField(); //new JTextField();
		DBUserNamet.setColumns(10);
		DBUserNamet.setText(DBUserName);
		
		JLabel lblNewLabel_7 = props.setLabel("Database Password* :"); //new JLabel("Database Password :");
		
		DBPasswordt = props.getPasswordField(); //new JPasswordField();
		DBPasswordt.setColumns(10);
		DBPasswordt.setText(DBPassword);
		
		JLabel lblNewLabel_8 = props.setLabel("OJDBC Jar :"); //new JLabel("OJDBC Jar :");
		
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
		
		DBFailOverc = props.getCheckBox("Database Failover support Enabled"); //new JCheckBox("Database Failover support Enabled");
		DBFailOverc.setSelected(Boolean.parseBoolean(DBFailOver));
		
		JLabel lblNewLabel_9 = props.setLabel("Minimum Connections :"); //new JLabel("Minimum Connections :");
		
		MinConnt = props.getTextField(); //new JTextField();
		MinConnt.setColumns(10);
		MinConnt.setText(MinConn);
		
		JLabel lblNewLabel_10 = props.setLabel("Maximum Connections : "); //new JLabel("Maximum Connections : ");
		
		MaxConnt = props.getTextField(); //new JTextField();
		MaxConnt.setColumns(10);
		MaxConnt.setText(MaxConn);
		
		JLabel lblNewLabel_11 = props.setLabel("Unused Timeout (sec) :"); //new JLabel("Unused Timeout (sec) :");
		
		UnusedTimet = props.getTextField(); //new JTextField();
		UnusedTimet.setColumns(10);
		UnusedTimet.setText(UnusedTime);
		
		JLabel lblNewLabel_12 = props.setLabel("Reap time (sec) :"); //new JLabel("Reap time (sec) :");
		
		ReapTimet = props.getTextField(); //new JTextField();
		ReapTimet.setColumns(10);
		ReapTimet.setText(ReapTime);
		
		Scriptt = props.getTextField(); //new JTextField();
		Scriptt.setColumns(10);
		Scriptt.setText(Script);
		
		JLabel lblNewLabel_13 = props.setLabel("Script Directory :"); //new JLabel("Script Directory :");
		
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
		
		JButton testBtn = props.getButton("Test Database Connection"); //new JButton("Test Database Connection");
		testBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JSeparator separator_1 = new JSeparator();
		
		JButton saveBtn = props.getButton("Save"); //new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				props.XmlDoc(file.toString(), (String)null);
				Element doc1 = props.getDocElem();
				props.setChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE, JDBCDSNamet.getText());
				props.setChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE, JDBCDSXANamet.getText());
				props.setChildValueByName(doc1, PROPERTY, "OracleRacUrl", VALUE, OrRacUrlt.getText());
				props.setChildValueByName(doc1, PROPERTY, "DatabaseServiceName", VALUE, DBServicet.getText());
				props.setChildValueByName(doc1, PROPERTY, "OracleRacRetries", VALUE, OrRacEntt.getText());
				props.setChildValueByName(doc1, PROPERTY, "OracleRacDelay", VALUE, OrRacDelt.getText());
				props.setChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE, DBUserNamet.getText());
				props.setChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE, DBPasswordt.getText());
				props.setChildValueByName(doc1, PROPERTY, "ODBC Jar", VALUE, props.getValue(comboBox));
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
		
		JButton runBtn = props.getButton("Run Task"); //new JButton("Run Task");
		runBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				runActionPerformed(e);
			}
		});
		
		JLabel lblNewLabel_14 = props.setLabel("Oracle RAC delay :"); //new JLabel("Oracle RAC delay :");
		
		OrRacDelt = new JTextField();
		OrRacDelt.setColumns(10);
		OrRacDelt.setText(OrRacDel);
		
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
					.addComponent(OrRacUrlt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(DBServicet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(OrRacEntt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(lblNewLabel_14, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(OrRacDelt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
						.addComponent(OrRacUrlt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_4))
						.addComponent(DBServicet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_5))
						.addComponent(OrRacEntt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_14))
						.addComponent(OrRacDelt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
            //String wsTclRuntime = tempDir+SLASH + getSymbolicName() + TCL_EXT;

            String databaseUsername = DBUserNamet.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_USERNAME));
            String databasePassword = DBPasswordt.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_PASSWORD));
            String jdbcProviderDesc = ""; //Messages.JDBC_PROVIDER_DESC_DESC;
            String jdbcDataSourceName = JDBCDSNamet.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.JDBC_DATASOURCE_NAME));
            String jdbcDataSourceXAName = JDBCDSXANamet.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.JDBC_DATASOURCE_XA_NAME));
           	String databaseVersion = ""; //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_VERSION));
           	boolean turnOnDBFailover = Boolean.parseBoolean(props.getValue(DBFailOverc)); //getTaskCMProperties().getValue(ConfigKeys.DBFAILOVER_ENABLED));
           	TokenizeOracleRACUrl(OrRacUrlt.getText());
            String databaseServerName = EMPTY_STRING; 
            String databasePortNumber = EMPTY_STRING; 
            
            String oracleRacUrl = "jdbc:oracle:thin:@(DESCRIPTION=(load_balance=yes)(ADDRESS_LIST="; //$NON-NLS-1$
            for (int i=0;i<nodes;i++)
            {	
            	//databaseServerName = WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_SERVER_NAME_RAC_NODE+(i+1)));
            	//databasePortNumber = getTaskCMProperties().getValue(ConfigKeys.DATABASE_PORT_NUMBER_RAC_NODE+(i+1));
				databaseServerName = DATABASE_SERVER_NAME_RAC_NODES[i];
				databasePortNumber = DATABASE_PORT_NUMBER_RAC_NODES[i];
                oracleRacUrl +="(ADDRESS=(PROTOCOL=TCP)(HOST="+databaseServerName+")(PORT="+databasePortNumber+"))"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
                        
            String databaseServiceName = DBServicet.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DATABASE_SERVICENAME));
            oracleRacUrl +=")(CONNECT_DATA=(SERVICE_NAME="+databaseServiceName+")"; //$NON-NLS-1$ //$NON-NLS-2$
            
            String retries = OrRacEntt.getText(); //getTaskCMProperties().getValue(ConfigKeys.ORACLE_RAC_RETRIES);
            String delay = OrRacDelt.getText(); //getTaskCMProperties().getValue(ConfigKeys.ORACLE_RAC_DELAY);
            oracleRacUrl +="(failover_mode=(type=select)(method=basic)(retries="+retries+")(delay="+delay+"))))"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            
            String zOsDbName = N_A;
            String zOsDefaultTblspace = N_A;
            String zOsDefaultSTOGroup = N_A;
            String zOsSTOGROUPOptions = N_A;
            
            String retryIntervalForClientReroute = N_A;
            String maxRetriesForClientReroute = N_A;
            String clientRerouteAlternateServerName = N_A;
            String clientRerouteAlternatePortNumber = N_A;
            
            String applicationServerVersion = CMUtil.appServerVersion; //getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_VERSION);
            String majorVersion = applicationServerVersion.substring(0, applicationServerVersion.indexOf("."));//$NON-NLS-1$
            String applicationServerCell = CMUtil.appServerCell; //WebsphereUtil.escapeSpecialChar(getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_CELL));
            boolean turnoffSSLcerts = Boolean.parseBoolean(CMUtil.turnOffSSL); //getEnvironmentCMProperties().getValue(ConfigKeys.TURNOFF_SSL_CERTIFICATE));

            String jdbcProviderName = "JDBC provider for Oracle (SSL Enabled)"; //NLS.bind(Messages.JDBC_PROVIDER_NAME_DESC, "Oracle");//$NON-NLS-1$
            
            //changes for defect 3470  start
           	String odbcjarname = props.getValue(comboBox); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.ODBC_JAR_NAME));
           	//changes for defect 3470 end
           	
            //changes for defect 9654 start
            String mssqljarname = N_A; //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.MSSQL_JAR_NAME));
            //changes for defect 9654 end

            //20484
            boolean sslEnabled = false;
            String minPoolSize = "", maxPoolSize = "", unusedTimeOut = "", reapTime = "";
            //if (CMUtil.isCmuiClient)
            //{
                //20626
                minPoolSize = MinConnt.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.MIN_POOL_SIZE));
                maxPoolSize = MaxConnt.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.MAX_POOL_SIZE));
                unusedTimeOut = UnusedTimet.getText(); //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.UNUSED_TIMEOUT));
                reapTime = ReapTimet.getText();
                		//WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.REAP_TIME));                	
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
			outFW.write("set _dbName " + QUOTE + databaseServiceName + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _dbServerName " + QUOTE + databaseServerName + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _portNumber " + QUOTE + databasePortNumber + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _dbFailOver " + QUOTE + turnOnDBFailover + QUOTE+ strEOL);//$NON-NLS-1$
			
			outFW.write("set _ClientRerouteEnable " + QUOTE + "false" + QUOTE+ strEOL);//$NON-NLS-1$
			outFW.write("set _retryIntervalForClientReroute " + QUOTE + retryIntervalForClientReroute + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _maxRetriesForClientReroute " + QUOTE + maxRetriesForClientReroute + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _clientRerouteAlternateServerName " + QUOTE + clientRerouteAlternateServerName + QUOTE+ strEOL);//$NON-NLS-1$
			outFW.write("set _clientRerouteAlternatePortNumber " + QUOTE + clientRerouteAlternatePortNumber + QUOTE+ strEOL);//$NON-NLS-1$
			//changes for defect 3470 start
			outFW.write("set _odbcjarType " + QUOTE + odbcjarname + QUOTE+ strEOL);//$NON-NLS-1$
			// changes for defect 3470 end
			
			//changes for defect 9653 start
			outFW.write("set _mssqljarType " + QUOTE + mssqljarname + QUOTE+ strEOL);//$NON-NLS-1$
			//changes for defect 9654 end
			outFW.write("set _zOsDbName " + QUOTE + zOsDbName + QUOTE+ strEOL); //$NON-NLS-1$ 
			outFW.write("set _zOsDefaultTblspace " + QUOTE + zOsDefaultTblspace + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _zOsDefaultSTOGroup " + QUOTE + zOsDefaultSTOGroup + QUOTE+ strEOL); //$NON-NLS-1$ 
			outFW.write("set _zOsSTOGROUPOptions " + QUOTE + zOsSTOGROUPOptions + QUOTE+ strEOL);//$NON-NLS-1$ 

			//20484 settings
			//if (CMUtil.isCmuiClient)
			//{
				outFW.write("set _sslEnabled " + QUOTE + sslEnabled + QUOTE+ strEOL);//$NON-NLS-1$
				outFW.write("set _sslConn " + QUOTE + sslEnabled + QUOTE+ strEOL);//$NON-NLS-1$
				outFW.write("set _oracleSSLURL "+ QUOTE + EMPTY_STRING + QUOTE + strEOL);
    			outFW.write("set _connProps " + QUOTE + EMPTY_STRING + QUOTE + strEOL);

    			//20626
    			outFW.write("set _minpoolsize " + Integer.parseInt(minPoolSize) + strEOL);
    			outFW.write("set _maxpoolsize " + Integer.parseInt(maxPoolSize) + strEOL);
    			outFW.write("set _unusedtimeout " + Integer.parseInt(unusedTimeOut) + strEOL);
    			outFW.write("set _reaptime " + Integer.parseInt(reapTime) + strEOL);
			//}
			
			String aLine;
			while ((aLine=inBR.readLine()) != null)
			{
				if (aLine.startsWith("set _oracleRacUrl")) //$NON-NLS-1$
					outFW.write("set _oracleRacUrl " + QUOTE + oracleRacUrl + QUOTE+ strEOL);//$NON-NLS-1$ 
				else if (aLine.startsWith("set _turnoffSSLcerts") && !turnoffSSLcerts) //$NON-NLS-1$
					outFW.write("set _turnoffSSLcerts \"true\""+ strEOL); //$NON-NLS-1$
				else if (aLine.startsWith("set _dbVersion")) //$NON-NLS-1$
					outFW.write("set _dbVersion " + QUOTE + databaseVersion + QUOTE+ strEOL);//$NON-NLS-1$
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
	private static void TokenizeOracleRACUrl(String WSOracleRACUrl){
		wsoracleracurl = new StringTokenizer(WSOracleRACUrl, ":,");
		nodes = wsoracleracurl.countTokens()/2;
		DATABASE_SERVER_NAME_RAC_NODES = new String[nodes];
		DATABASE_PORT_NUMBER_RAC_NODES = new String[nodes];
		
		for(int i=0;wsoracleracurl.hasMoreTokens();i++) {
			DATABASE_SERVER_NAME_RAC_NODES[i]=wsoracleracurl.nextToken();
			DATABASE_PORT_NUMBER_RAC_NODES[i]=wsoracleracurl.nextToken();
		}
	}
}

