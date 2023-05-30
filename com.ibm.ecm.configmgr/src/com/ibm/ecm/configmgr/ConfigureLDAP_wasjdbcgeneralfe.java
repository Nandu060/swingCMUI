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

public class ConfigureLDAP_wasjdbcgeneralfe extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	//private JTextField passField;
	private JTextField textField_4;
	//private JTextField passField_1;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JPasswordField passField;
	private JPasswordField passField_1;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	public String SSLEnabled="false";
	public String currentActive = "false";
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";
	private PropertyFactory props = new PropertyFactory();

	/**
	 * Create the panel.
	 */
	public ConfigureLDAP_wasjdbcgeneralfe(String j1,String j2,String j3,String j4,String j5,String j6
			,String j7,String j8,String j9,String j10,String j11,String j12,String j13,
			String j14,String j15,final File file) {
		
		JLabel lblNewLabel = new JLabel("Configure LDAP");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_1 = new JLabel("Directory service server host name* :");
		
		textField = new JTextField();
		textField.setText(j1);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Directory service port number* :");
		
		textField_1 = new JTextField();
		textField_1.setText(j2);
		textField_1.setColumns(10);
		
		chckbxNewCheckBox = new JCheckBox("SSL Enabled");
		chckbxNewCheckBox.setSelected(Boolean.parseBoolean(j15));
		
		JLabel lblNewLabel_3 = new JLabel("Directory service bind user name* :");
		
		textField_2 = new JTextField();
		textField_2.setText(j3);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Directory service bind user password* :");
		
		passField = new JPasswordField();
		passField.setText(j4);
		passField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Base entry distiguished name (Repository)* :");
		
		textField_4 = new JTextField();
		textField_4.setText(j5);
		textField_4.setColumns(10);
		
		passField_1 = new JPasswordField();
		passField_1.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Confirm :");
		
		JLabel lblNewLabel_7 = new JLabel("Login properties* :");
		
		textField_6 = new JTextField();
		textField_6.setText(j6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Federated repository virtual realm name* :");
		
		textField_7 = new JTextField();
		textField_7.setText(j7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Repository identifier* :");
		
		textField_8 = new JTextField();
		textField_8.setText(j8);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Base entry distinguished name (Realm)* :");
		
		textField_9 = new JTextField();
		textField_9.setText(j9);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Administrative console user name* :");
		
		textField_10 = new JTextField();
		textField_10.setText(j10);
		textField_10.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Name of group membership attribute* :");
		
		textField_11 = new JTextField();
		textField_11.setText(j11);
		textField_11.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Scope of group membership attribute* :");
		
		textField_12 = new JTextField();
		textField_12.setText(j12);
		textField_12.setColumns(10);
		
		chckbxNewCheckBox_1 = new JCheckBox("Set as current active user registry");
		chckbxNewCheckBox_1.setSelected(Boolean.parseBoolean(j13));
		
		JLabel lblNewLabel_14 = new JLabel("Script* :");
		
		textField_13 = new JTextField();
		textField_13.setText(j14);
		textField_13.setColumns(10);
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			
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
		        textField_13.setText(profAPath);
			}
		});
		
		JSeparator separator_1 = new JSeparator();
		
		JButton btnNewButton_1 = new JButton("Test LDAP Connection");
		
		JButton btnNewButton_2 = new JButton("Save");
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				props.XmlDoc(file.toString(), (String)null);
				Element doc1 = props.getDocElem();
				props.setChildValueByName(doc1, PROPERTY, "LDAPServerHost", VALUE, textField.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPServerPort", VALUE, textField_1.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPBindDN", VALUE, textField_2.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPBindPassword", VALUE, passField.getText());
				props.setChildValueByName(doc1, PROPERTY, "WasFederatedBaseEntryDNRepository", VALUE, textField_4.getText());
				props.setChildValueByName(doc1, PROPERTY, "LoginProperties", VALUE, textField_6.getText());
				props.setChildValueByName(doc1, PROPERTY, "FederatedRepositoriesRealm", VALUE, textField_7.getText());
				props.setChildValueByName(doc1, PROPERTY, "WasFederatedRepositoryId", VALUE, textField_8.getText());
				props.setChildValueByName(doc1, PROPERTY, "WasFederatedBaseEntryDNRealm", VALUE, textField_9.getText());
				props.setChildValueByName(doc1, PROPERTY, "AdminConsoleUser", VALUE, textField_10.getText());
				props.setChildValueByName(doc1, PROPERTY, "groupMembershipName", VALUE, textField_11.getText());
				props.setChildValueByName(doc1, PROPERTY, "groupMembershipScope", VALUE, textField_12.getText());
				props.setChildValueByName(doc1, PROPERTY, "SetAsActiveUserReg", VALUE, props.getValue(chckbxNewCheckBox_1));
				props.setChildValueByName(doc1, PROPERTY, "Script", VALUE, textField_13.getText());
				props.setChildValueByName(doc1, PROPERTY, "SSLEnabled", VALUE, props.getValue(chckbxNewCheckBox));
				props.saveXMLDoc(file.toString());
				String[] names = props.getElementNamesByTagName(doc1, PROPERTY);
				for(int i=0;i<names.length;i++)
				{
					//System.out.println(names[i]);
					System.out.println(props.getChildValueByName(doc1, PROPERTY, names[i], VALUE));
				}
			}
		});
		
		JButton btnNewButton_3 = new JButton("Run Task");
		btnNewButton_3.addActionListener(new ActionListener() {
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
					.addGap(17)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 729, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addGap(57)
					.addComponent(chckbxNewCheckBox, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(passField, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addComponent(passField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblNewLabel_13, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(329)
					.addComponent(chckbxNewCheckBox_1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(lblNewLabel_14, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 729, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(329)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_1))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_2))
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(chckbxNewCheckBox)))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_3))
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_4))
						.addComponent(passField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_6))
						.addComponent(passField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_5))
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_7))
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_8))
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_9))
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_10))
						.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_11))
						.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_12))
						.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_13))
						.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addComponent(chckbxNewCheckBox_1)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_14))
						.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(4)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_3)))
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
    	String script = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\scripts\\configureWSLDAP.tcl";
        String tempFile = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\tmp\\configureLDAP.tcl";
        setScript(script, tempFile);
                	    	
    	String EMPTY_STRING = "";
    	String command = EMPTY_STRING;
		String jvmargs = EMPTY_STRING;
		String arguments = EMPTY_STRING;
		String workingDir = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure";
		File workingDirf = new File(workingDir);
		
		command = "configmgr_cl execute -task configureldap -profile "+CMUtil.profileName;
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
	    } 
		catch (Exception ioe) 
		{
			ioe.printStackTrace();
			//String localizedMsg1 = ioe.getLocalizedMessage();
	    }
		return sbout.toString();
	}
    @SuppressWarnings("deprecation")
   	public void setScript(String script, String wsTclRuntime) {
       	try {
       	String QUOTE = "\"", strEOL = "\r\n";
       	FileReader inFR = new FileReader(script);
		BufferedReader inBR = new BufferedReader(inFR);
		FileWriter outFW = new FileWriter(wsTclRuntime);
        //String LDAPServerUser = "";
        String ldapBindDN = textField_2.getText();
        String domainName = CMUtil.appServerDomain;
        //String ldapType = CMUtil.ldapRepoType;
        SSLEnabled = props.getValue(chckbxNewCheckBox);
        currentActive = props.getValue(chckbxNewCheckBox_1);
        outFW.write("set _wasVersion " + QUOTE + CMUtil.appServerVersion + QUOTE + strEOL);//$NON-NLS-1$ 
		outFW.write("set _admConsoleUser " + QUOTE + textField_10.getText() + QUOTE+ strEOL);//$NON-NLS-1$ 
		outFW.write("set _setCurrent " + QUOTE + currentActive + QUOTE + strEOL);//$NON-NLS-1$ 
		outFW.write("set _ldapType " + QUOTE + getLDAPType(CMUtil.ldapServerType) + QUOTE+ strEOL);  //$NON-NLS-1$ 
		outFW.write("set _ldapHost " + QUOTE + textField.getText() + QUOTE+ strEOL);  //$NON-NLS-1$ 
		outFW.write("set _ldapPort " + QUOTE + textField_1.getText() + QUOTE+ strEOL); //$NON-NLS-1$ 
		outFW.write("set _sslEnabled " + QUOTE + SSLEnabled + QUOTE+ strEOL);//$NON-NLS-1$ 
		outFW.write("set _ldapBindDN " + QUOTE + ldapBindDN + QUOTE+ strEOL);	//$NON-NLS-1$ 
		outFW.write("set _bindPassword " + QUOTE + passField.getText() + QUOTE+ strEOL); //$NON-NLS-1$ 
		outFW.write("set _baseEntryNDRealm " + QUOTE + textField_9.getText() + QUOTE+ strEOL); //$NON-NLS-1$ 
		outFW.write("set _baseEntryNDRepository " + QUOTE + textField_4.getText() + QUOTE+ strEOL); //$NON-NLS-1$ 
		outFW.write("set _repoId " + QUOTE + textField_8.getText() + QUOTE+ strEOL); //$NON-NLS-1$ 
		outFW.write("set _loginProperties " + QUOTE + textField_6.getText() + QUOTE+ strEOL); //$NON-NLS-1$ 
		outFW.write("set _fedReposRealm " + QUOTE + textField_7.getText() + QUOTE+ strEOL); //$NON-NLS-1$ 
		
		outFW.write("set _groupMembershipName " + QUOTE + textField_11.getText() + QUOTE+ strEOL); //$NON-NLS-1$ 
		if(textField_12.getText().startsWith("All"))
			outFW.write("set _groupMembershipScope " + QUOTE + "all" + QUOTE+ strEOL); //$NON-NLS-1$ 
		else if(textField_12.getText().startsWith("Nested"))
			outFW.write("set _groupMembershipScope " + QUOTE + "nested" + QUOTE+ strEOL); //$NON-NLS-1$
		else
			outFW.write("set _groupMembershipScope " + QUOTE + "direct" + QUOTE+ strEOL); //$NON-NLS-1$
		if (domainName.equals("default"))
		{
			outFW.write("set _domainNotSet \"true\""+strEOL);
		}
		else
		{
			outFW.write("set _domainNotSet \"false\""+strEOL);
		}

		String aLine;
		boolean turnoffSSLcerts = Boolean.parseBoolean(CMUtil.turnOffSSL);
		while ((aLine=inBR.readLine()) != null)
		{
			if (aLine.startsWith("set _turnoffSSLcerts") && !turnoffSSLcerts) //$NON-NLS-1$
				outFW.write("set _turnoffSSLcerts \"true\"" + strEOL); //$NON-NLS-1$
			else
				outFW.write(aLine+ strEOL);
		}

		
		inFR.close();
		inBR.close();
		outFW.close();
		
		/*String scope=null;
        if(groupMembershipScope.startsWith("All"))
        	scope="all"; //$NON-NLS-1$ 
		else if(groupMembershipScope.startsWith("Nested"))
			scope="nested";  //$NON-NLS-1$
		else
			scope="direct";  //$NON-NLS-1$
        
        
        //If domain name is set then execute the jython file which adds the ldap module to specified domain.
        if(!domainName.equals("default"))
        {
        	 script = CMUtil.getCMDir()+File.separator+"scripts"+File.separator+"configureWSLDAPFederatedSecDomain.py";
	            String wsPyRuntime = tempDir+SLASH+"configureWSLDAPFederatedSecDomain"+".py";
	            String pyLog =  tempDir+SLASH + "configureWSLDAPFederatedSecDomain" + LOG_EXT;
	            
				inFR = new FileReader(script);
	            
	             inBR = new BufferedReader(inFR);
				 outFW = new FileWriter(wsPyRuntime);

				outFW.write("ldapid='"+repoId+"'"+ strEOL);//$NON-NLS-1$ 
				outFW.write("ldaptype='"+ldapType+"'"+ strEOL);//$NON-NLS-1$
				outFW.write("host='"+ldapServerHost+"'"+ strEOL);//$NON-NLS-1$
				outFW.write("port='"+ldapServerPort+"'"+ strEOL);//$NON-NLS-1$
				outFW.write("repobaseentryname='"+baseEntryNDRepository+"'"+ strEOL);//$NON-NLS-1$
				outFW.write("realmbaseentryname='"+baseEntryNDRealm+"'"+ strEOL);//$NON-NLS-1$
				outFW.write("binddn='"+ldapBindDN+"'"+ strEOL);//$NON-NLS-1$
				outFW.write("bindpassword='"+ldapBindPassword+"'"+ strEOL);//$NON-NLS-1$
				outFW.write("sslenabled='"+sslEnabled+"'"+ strEOL);//$NON-NLS-1$
				outFW.write("domainname='"+domainName+"'"+ strEOL);//$NON-NLS-1$
				outFW.write("loginProperties='"+loginProperties+"'"+ strEOL);//$NON-NLS-1$
				outFW.write("groupname='"+groupMembershipName+"'"+ strEOL);//$NON-NLS-1$
				outFW.write("scope='"+scope+"'"+ strEOL);//$NON-NLS-1$
				outFW.write("fedreposrealm='"+fedReposRealm+"'"+ strEOL);//$NON-NLS-1$
				
				
				
				
				while ((aLine=inBR.readLine()) != null)
    			{
    				outFW.write(aLine+ strEOL);
    			}
				
				 inFR.close();
		         inBR.close();
		         outFW.close();
				*/
		
       	}
       	catch(IOException ex) {
       		ex.printStackTrace();
       	}
    }
    
    public String getLDAPType(String DS)
    {
    	String LDAPType = "";
    	if(DS.equals("sunjavads"))
    		LDAPType = "IPLANET";
    	else if(DS.equals("tivolids"))
    		LDAPType = "IBM_DIRECTORY_SERVER";
    	else if(DS.equals("ad"))
    		LDAPType = "ACTIVE_DIRECTORY";
    	else if(DS.equals("adam"))
    		LDAPType = "CUSTOM";
    	else if(DS.equals("ca"))
    		LDAPType = "CUSTOM";
    	else if(DS.equals("edirectory"))
    		LDAPType = "NDS";
    	else if(DS.equals("oid"))
    		LDAPType = "CUSTOM";
    	return LDAPType;
    }
}

