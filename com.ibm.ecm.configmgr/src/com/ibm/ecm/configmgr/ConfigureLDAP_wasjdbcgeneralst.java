package com.ibm.ecm.configmgr;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
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

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

public class ConfigureLDAP_wasjdbcgeneralst extends JPanel{

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	//private JTextField passField;
	//private JTextField passField_1;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JPasswordField passField;
	private JPasswordField passField_1;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	private JCheckBox chckbxNewCheckBox_2;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private String sslEnabled = "";
	private String currentActive = "";
	private String overwrite = "";
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";
	private PropertyFactory props = new PropertyFactory();
	
	public ConfigureLDAP_wasjdbcgeneralst()
	{
		
	}
	
	public ConfigureLDAP_wasjdbcgeneralst(String j1,String j2,String j3,String j4,String j5,String j6
			,String j7,String j8,String j9,String j10,String j11,String j12,String j13,
			String j14,final File file) {
		// TODO Auto-generated constructor stub
		//super("LDAP");
		//set;

		JLabel lblNewLabel = new JLabel("Configure LDAP");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		//JLabel lblNewLabel_1 = new JLabel("Directory service provider type :");
		
		JSeparator separator = new JSeparator();
		
		JSeparator separator_1 = new JSeparator();
		
		//JComboBox comboBox = new JComboBox();
		
		//JLabel lblNewLabel_2 = new JLabel("WebSphere Application Server LDAP Repository type :");
		
		//JComboBox comboBox_1 = new JComboBox();
		
		JLabel lblNewLabel_3 = new JLabel("Directory Service server host name :");
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setText(j1);
		
		JLabel lblNewLabel_4 = new JLabel("Directory service port number :");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setText(j2);
		
		JLabel lblNewLabel_5 = new JLabel("Directory service bind user name :  ");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setText(j3);
		
		JLabel lblNewLabel_6 = new JLabel("Directory service bind password :");
		
		passField = new JPasswordField();
		passField.setColumns(10);
		passField.setText(j4);
		
		JLabel lblNewLabel_7 = new JLabel("Confirm :");
		
		passField_1 = new JPasswordField();
		passField_1.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Base distinguished name :");
		
		textField_5 = new JTextField();
		textField_5.setText(j5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("User Filter :");
		
		textField_6 = new JTextField();
		textField_6.setText(j6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Group Filter :");
		
		textField_7 = new JTextField();
		textField_7.setText(j7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("User ID Map :");
		
		textField_8 = new JTextField();
		textField_8.setText(j8);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Group ID Map :");
		
		textField_9 = new JTextField();
		textField_9.setText(j9);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Administrative user console name :");
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setText(j10);
		
		chckbxNewCheckBox = new JCheckBox("Over-write stand alone existing LDAP repository type");
		chckbxNewCheckBox.setSelected(Boolean.parseBoolean(j11));
		
		chckbxNewCheckBox_1 = new JCheckBox("Set as current active user registry ");
		chckbxNewCheckBox_1.setSelected(Boolean.parseBoolean(j12));
		
		JLabel lblNewLabel_14 = new JLabel("Script :");
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setText(j13);
		
		JLabel lblNewLabel_15 = new JLabel("Temporary directory :");
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		//textField_12.setText(j);
		
		
		chckbxNewCheckBox_2 = new JCheckBox("SSL Enabled");
		chckbxNewCheckBox_2.setSelected(Boolean.parseBoolean(j14));
		
		JButton btnNewButton_3 = new JButton("Run Task");
		btnNewButton_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				runActionPerformed(e);
			}
		});
		
		JButton btnNewButton_2 = new JButton("Test LDAP Connection");
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
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
		        textField_11.setText(profAPath);
			}
		});
		
		JButton btnNewButton_1 = new JButton("Browse");
		btnNewButton_1.addActionListener(new ActionListener() {
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
		        textField_12.setText(profAPath);
			}
		});
		
		
		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				props.XmlDoc(file.toString(), (String)null);
				Element doc1 = props.getDocElem();
				props.setChildValueByName(doc1, PROPERTY, "LDAPServerHost", VALUE, textField.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPServerPort", VALUE, textField_1.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPBindDN", VALUE, textField_2.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPBindPassword", VALUE, passField.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPBaseDN", VALUE, textField_5.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPUserFilter", VALUE, textField_6.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPGroupFilter", VALUE, textField_7.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPUserIDMap", VALUE, textField_8.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPGroupIDMap", VALUE, textField_9.getText());
				props.setChildValueByName(doc1, PROPERTY, "AdminConsoleUser", VALUE, textField_10.getText());
				props.setChildValueByName(doc1, PROPERTY, "OverwriteExistingUserReg", VALUE, props.getValue(chckbxNewCheckBox));
				props.setChildValueByName(doc1, PROPERTY, "SetAsActiveUserReg", VALUE, props.getValue(chckbxNewCheckBox_1));
				props.setChildValueByName(doc1, PROPERTY, "Script", VALUE, textField_11.getText());
				props.setChildValueByName(doc1, PROPERTY, "SSLEnabled", VALUE, props.getValue(chckbxNewCheckBox_2));
				props.saveXMLDoc(file.toString());
				String[] names = props.getElementNamesByTagName(doc1, PROPERTY);
				for(int i=0;i<names.length;i++)
				{
					//System.out.println(names[i]);
					System.out.println(props.getChildValueByName(doc1, PROPERTY, names[i], VALUE));
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 891, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(354)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
				/*.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel_2)
					.addGap(21)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))*/
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(passField, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(lblNewLabel_7)
					.addGap(39)
					.addComponent(passField_1, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel_13, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(380)
					.addComponent(chckbxNewCheckBox))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(380)
					.addComponent(chckbxNewCheckBox_1, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel_14, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, 517, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel_15, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, 517, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(380)
					.addComponent(chckbxNewCheckBox_2))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(371)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_3)))
				.addContainerGap(6, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addComponent(lblNewLabel)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					/*.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_2))
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)*/
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_3))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_4))
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_5))
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_6))
						.addComponent(passField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_7))
						.addComponent(passField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_8))
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_9))
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_10))
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_11))
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_12))
						.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_13))
						.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addComponent(chckbxNewCheckBox)
					.addGap(10)
					.addComponent(chckbxNewCheckBox_1)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(lblNewLabel_14))
						.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(btnNewButton)))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(lblNewLabel_15))
						.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(btnNewButton_1)))
					.addGap(14)
					.addComponent(chckbxNewCheckBox_2)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_2)
						.addComponent(saveBtn)
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
		
		outFW.write("set _wasVersion " + QUOTE + CMUtil.appServerVersion + QUOTE + strEOL);//$NON-NLS-1$ 
		outFW.write("set _admConsoleUser " + QUOTE + textField_10.getText() + QUOTE+ strEOL);//$NON-NLS-1$ 

		sslEnabled = props.getValue(chckbxNewCheckBox_2);
		currentActive = props.getValue(chckbxNewCheckBox_1);
		overwrite = props.getValue(chckbxNewCheckBox);
        String LDAPServerUser = "";
        String domainName = CMUtil.appServerDomain;
        String ldapBindDN = textField_2.getText();
        String ldapBaseDN = textField_5.getText();
        int eq = ldapBindDN.indexOf('=');
        if ( eq < 0)  //short name specified
        {
        	LDAPServerUser = ldapBindDN;
            ldapBindDN = "CN" + LDAPServerUser + "," + ldapBaseDN;
        }
        else
        {
            int co = ldapBindDN.indexOf(',');
            if (co < 0) co = ldapBindDN.length();
            LDAPServerUser = ldapBindDN.substring(eq + 1, co);
        }

			outFW.write("set _ldapType	" + QUOTE + getLDAPType(CMUtil.ldapServerType) + QUOTE+ strEOL);  //$NON-NLS-1$ 
			outFW.write("set _serverUser " + QUOTE + LDAPServerUser + QUOTE+ strEOL); //$NON-NLS-1$ 
			outFW.write("set _serverPassword " + QUOTE + passField.getText() + QUOTE+ strEOL); //$NON-NLS-1$ 
			outFW.write("set _ldapHost	" + QUOTE + textField.getText() + QUOTE+ strEOL);  //$NON-NLS-1$ 
			outFW.write("set _ldapPort	" + QUOTE + textField_1.getText() + QUOTE+ strEOL); //$NON-NLS-1$ 
			outFW.write("set _sslEnabled	" + QUOTE + sslEnabled + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _baseDn " + QUOTE + ldapBaseDN + QUOTE+ strEOL); //$NON-NLS-1$ 
			outFW.write("set _bindDn " + QUOTE + ldapBindDN + QUOTE+ strEOL);	//$NON-NLS-1$ 
			outFW.write("set _bindPassword " + QUOTE + passField.getText() + QUOTE+ strEOL); //$NON-NLS-1$ 
			outFW.write("set _userFilter " + QUOTE + textField_6.getText() + QUOTE+ strEOL); //$NON-NLS-1$ 
			outFW.write("set _groupFilter " + QUOTE + textField_7.getText() + QUOTE+ strEOL); //$NON-NLS-1$ 
			outFW.write("set _userIdMap	" + QUOTE + textField_8.getText() + QUOTE+ strEOL); //$NON-NLS-1$ 
			outFW.write("set _groupIdMap " + QUOTE + textField_9.getText() + QUOTE+ strEOL); //$NON-NLS-1$ 
			outFW.write("set _groupMemberIdMap \"memberof:member\""+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _overwrite " + QUOTE + overwrite + QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("set _setCurrent " + QUOTE + currentActive + QUOTE + strEOL);//$NON-NLS-1$ 
			if (CMUtil.appServerDomain.equals("default"))
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
		
		/*if(!domainName.equals("default"))
        {
        	 script = CMUtil.getCMDir()+File.separator+"scripts"+File.separator+"configureWSLdapSecDomain.py";
	            String wsPyRuntime = tempDir+SLASH+"configureWSLdapSecDomain"+".py";
	            String pyLog =  tempDir+SLASH + "configureWSLdapSecDomain" + LOG_EXT;
	            
				inFR = new FileReader(script);
	            
	             inBR = new BufferedReader(inFR);
				 outFW = new FileWriter(wsPyRuntime);

				outFW.write("domainName='"+domainName+"'"+ strEOL);//$NON-NLS-1$ 
				outFW.write("ldapType='"+ldapType+"'"+ strEOL);//$NON-NLS-1$
				outFW.write("hostname='"+ldapServerHost+"'"+ strEOL);//$NON-NLS-1$
				outFW.write("port='"+ldapServerPort+"'"+ strEOL);//$NON-NLS-1$
				outFW.write("baseDN='"+ldapBaseDN+"'"+ strEOL);//$NON-NLS-1$
				outFW.write("bindDN='"+ldapBindDN+"'"+ strEOL);//$NON-NLS-1$
				outFW.write("bindPassword='"+ldapBindPassword+"'"+ strEOL);//$NON-NLS-1$
				outFW.write("sslEnabled='"+sslEnabled+"'"+ strEOL);//$NON-NLS-1$
				outFW.write("userFilter='"+ldapUserFilterpy+"'"+strEOL);//$NON-NLS-1$
				outFW.write("groupFilter='"+ldapGroupFilterpy+"'"+strEOL);//$NON-NLS-1$
				outFW.write("userIdMap='"+ldapUserIDMappy+"'"+strEOL);//$NON-NLS-1$
				outFW.write("groupIdMap='"+ldapGroupIDMappy+"'"+strEOL);//$NON-NLS-1$
				 
				
				
				while ((aLine=inBR.readLine()) != null)
    			{
    				outFW.write(aLine+ strEOL);
    			}
				
				 inFR.close();
		         inBR.close();
		         outFW.close();*/
		
       	}
       	catch(IOException ex)
       	{
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

