package com.ibm.ecm.configmgr;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import java.awt.Desktop.Action;
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

public class ConfigureLDAP_wlgeneral extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JPasswordField passField;
	private JPasswordField passField_1;
	private JCheckBox chckbxNewCheckBox;
	private String sslenabled;
	private static final String OBJECTCLASS_VAR = "objectclass="; //$NON-NLS-1$
	private static final String GROUP_OBJECT_CLASS_VAR = "groupObjectClass="; //$NON-NLS-1$
	private static final String STATIC_GROUP_NAME_ATTR_VAR = "staticGroupNameAttr="; //$NON-NLS-1$
	private static final String GROUP_FROM_NAME_FILTER_VAR = "groupFromNameFilter="; //$NON-NLS-1$
	private static final String GROUP_BASE_DN_VAR = "groupBaseDn="; //$NON-NLS-1$
	private static final String USER_NAME_ATTR_VAR = "userNameAttr="; //$NON-NLS-1$
	private static final String USER_FROM_NAME_FILTER_VAR = "userFromNameFilter="; //$NON-NLS-1$
	private static final String USER_BASE_DN_VAR = "userBaseDn="; //$NON-NLS-1$
	private static final String PORT_VAR = "port="; //$NON-NLS-1$
	private static final String HOST_VAR = "host="; //$NON-NLS-1$
	private static final String CREDENTIAL_VAR = "credential="; //$NON-NLS-1$
	private static final String PRINCIPAL_VAR = "principal="; //$NON-NLS-1$
	private static final String DISPLAY_NAME_VAR = "displayName="; //$NON-NLS-1$
	private static final String PROVIDER_NAME_VAR = "providerName="; //$NON-NLS-1$
	private static final String CONNECTION_URL_VAR = "connectionURL="; //$NON-NLS-1$
	private static final String WL_ADMIN_PASSWORD_VAR = "wlAdminPassword="; //$NON-NLS-1$
	private static final String WL_ADMIN_VAR = "wlAdmin="; //$NON-NLS-1$
	private static final String CLOSING_PAREN = ")"; //$NON-NLS-1$
	private static final String EMPTY_STRING = ""; //$NON-NLS-1$
	private static final String DONE_CONFIGURING_AUTHENTICATOR = "Done configuring Authenticator"; //$NON-NLS-1$
	private static final String SSL_NOT_ENABLED = "sslEnabled=0"; //$NON-NLS-1$
	private static final String SSL_ENABLED = "sslEnabled=1"; //$NON-NLS-1$
	private static final String LOG_EXT = ".log"; //$NON-NLS-1$
	private static final String SINGLE_QUOTE = "'"; //$NON-NLS-1$
	private static final String T3S_PROTOCOL = "t3s://"; //$NON-NLS-1$
	private static final String T3_PROTOCOL = "t3://"; //$NON-NLS-1$
	private static final String COLON = ":"; //$NON-NLS-1$
	private static final String TRUE = "true"; //$NON-NLS-1$
	private static final String PY = ".py"; //$NON-NLS-1$
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";
	private PropertyFactory props = new PropertyFactory();

	/**
	 * Create the panel.
	 */
	public ConfigureLDAP_wlgeneral() {
		
	}
	public ConfigureLDAP_wlgeneral(String j1,String j2,String j3,String j4,String j5,String j6,String j7
			,String j8,String j9,String j10,String j11,String j12,String j13,final File file) {
		
		JLabel lblNewLabel = new JLabel("Configure LDAP");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_1 = new JLabel("Directory service display name* :");
		
		textField = new JTextField();
		textField.setText(j1);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Directory service server host name* :");
		
		textField_1 = new JTextField();
		textField_1.setText(j2);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Directory service port number* :");
		
		textField_2 = new JTextField();
		textField_2.setText(j3);
		textField_2.setColumns(10);
		
		chckbxNewCheckBox = new JCheckBox("SSL Enabled");
		chckbxNewCheckBox.setSelected(Boolean.parseBoolean(j13));
		
		JLabel lblNewLabel_4 = new JLabel("Directory service bind user name* :");
		
		textField_3 = new JTextField();
		textField_3.setText(j4);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Directory service bind user password* :");
		
		passField = new JPasswordField();
		passField.setText(j5);
		passField.setColumns(10);
		
		passField_1 = new JPasswordField();
		passField_1.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Confirm* :");
		
		JLabel lblNewLabel_7 = new JLabel("User base distinguished name* :");
		
		textField_6 = new JTextField();
		textField_6.setText(j6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Group base distinguished name* :");
		
		textField_7 = new JTextField();
		textField_7.setText(j7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("User from name filter* :");
		
		textField_8 = new JTextField();
		textField_8.setText(j8);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Group from name filter* :");
		
		textField_9 = new JTextField();
		textField_9.setText(j9);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("User name attribute* :");
		
		textField_10 = new JTextField();
		textField_10.setText(j10);
		textField_10.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Static group name attribute* : ");
		
		textField_11 = new JTextField();
		textField_11.setText(j11);
		textField_11.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Script* :");
		
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
		        textField_12.setText(profAPath);
			}
		});
		
		textField_12 = new JTextField();
		textField_12.setText(j12);
		textField_12.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		
		JButton btnNewButton_1 = new JButton("Test LDAP Connection");
		
		JButton btnNewButton_2 = new JButton("Save");
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				props.XmlDoc(file.toString(), (String)null);
				Element doc1 = props.getDocElem();
				props.setChildValueByName(doc1, PROPERTY, "LDAPDisplayName", VALUE, textField.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPServerHost", VALUE, textField_1.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPServerPort", VALUE, textField_2.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPBindDN", VALUE, textField_3.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPBindPassword", VALUE, passField.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPUserBaseDN", VALUE, textField_6.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPGroupBaseDN", VALUE, textField_7.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPUserFromNameFilter", VALUE, textField_8.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPGroupFromNameFilter", VALUE, textField_9.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPUsernameAttribute", VALUE, textField_10.getText());
				props.setChildValueByName(doc1, PROPERTY, "LDAPStaticGroupAttribute", VALUE, textField_11.getText());
				props.setChildValueByName(doc1, PROPERTY, "Script", VALUE, textField_12.getText());
				props.setChildValueByName(doc1, PROPERTY, "SSLEnabled", VALUE, props.getValue(chckbxNewCheckBox));
				//props.writeXML(file, doc1);
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
					.addGap(16)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 679, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					.addGap(64)
					.addComponent(chckbxNewCheckBox, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(passField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(passField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblNewLabel_13, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(233)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 679, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(328)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(250)
							.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(181)
							.addComponent(btnNewButton_2))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_1))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_2))
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_3))
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(chckbxNewCheckBox)))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_4))
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_5))
						.addComponent(passField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_6))
						.addComponent(passField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_7))
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_8))
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_9))
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_10))
						.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_11))
						.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_12))
						.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_13))
						.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(27)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_3)
						.addComponent(btnNewButton_2)))
		);
		setLayout(groupLayout);
	}
	public void runActionPerformed(ActionEvent e)
	{
		String console = exec();
		ConsoleOP.appendText(console);
    	//new NewJFrame(CMUtil.profileName, console).setVisible(true);
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
    	
    	try {
			String strEOL = CMUtil.getPlatformEOL();
			String url = T3_PROTOCOL+CMUtil.appServerHostName+COLON+CMUtil.appServerSOAP;
			if (CMUtil.SSLEnabled.equalsIgnoreCase(TRUE))
				url = T3S_PROTOCOL+CMUtil.appServerHostName+COLON+CMUtil.appServerSOAP;
				
			FileReader inFR = new FileReader(script);
			BufferedReader inBR = new BufferedReader(inFR);
			FileWriter outFW = new FileWriter(wlPyRuntime);

			String aLine = null;
			String providerName = CMUtil.ldapServerType;
			sslenabled = props.getValue(chckbxNewCheckBox);

			outFW.write(WL_ADMIN_VAR + SINGLE_QUOTE + CMUtil.appServerAdminUser + SINGLE_QUOTE+ strEOL);
			outFW.write(WL_ADMIN_PASSWORD_VAR + SINGLE_QUOTE+ CMUtil.appServerAdminPassword + SINGLE_QUOTE + strEOL);
			outFW.write(CONNECTION_URL_VAR + SINGLE_QUOTE + url + SINGLE_QUOTE + strEOL);
			outFW.write(PROVIDER_NAME_VAR + SINGLE_QUOTE + providerName + SINGLE_QUOTE + strEOL);
			outFW.write(DISPLAY_NAME_VAR + SINGLE_QUOTE + textField.getText() + SINGLE_QUOTE + strEOL);

			outFW.write(PRINCIPAL_VAR + SINGLE_QUOTE + textField_3.getText() + SINGLE_QUOTE + strEOL);
			outFW.write(CREDENTIAL_VAR + SINGLE_QUOTE + passField.getText() + SINGLE_QUOTE + strEOL);
			outFW.write(HOST_VAR + SINGLE_QUOTE + textField_1.getText() + SINGLE_QUOTE + strEOL);
			outFW.write(PORT_VAR + SINGLE_QUOTE + textField_2.getText() + SINGLE_QUOTE + strEOL);
			if (sslenabled.compareToIgnoreCase(TRUE) == 0) {
				outFW.write(SSL_ENABLED + strEOL);
			} 
			else {
				outFW.write(SSL_NOT_ENABLED + strEOL);
			}
			outFW.write(USER_BASE_DN_VAR + SINGLE_QUOTE + textField_6.getText() + SINGLE_QUOTE + strEOL);
			outFW.write(USER_FROM_NAME_FILTER_VAR + SINGLE_QUOTE + textField_8.getText() + SINGLE_QUOTE + strEOL);
			outFW.write(USER_NAME_ATTR_VAR + SINGLE_QUOTE + textField_10.getText() + SINGLE_QUOTE+ strEOL);
			outFW.write(GROUP_BASE_DN_VAR + SINGLE_QUOTE + textField_7.getText() + SINGLE_QUOTE + strEOL);
			outFW.write(GROUP_FROM_NAME_FILTER_VAR + SINGLE_QUOTE + textField_9.getText() + SINGLE_QUOTE+ strEOL);
			outFW.write(STATIC_GROUP_NAME_ATTR_VAR + SINGLE_QUOTE + textField_11.getText() + SINGLE_QUOTE+ strEOL);
			String groupClass = getAttrValue(textField_9.getText(), OBJECTCLASS_VAR);
			outFW.write(GROUP_OBJECT_CLASS_VAR + SINGLE_QUOTE + groupClass + SINGLE_QUOTE+ strEOL);
			while ((aLine = inBR.readLine()) != null)
				outFW.write(aLine + strEOL);
			inFR.close();
			inBR.close();
			outFW.close();
    	}
        catch(IOException ex)
        {
        	ex.printStackTrace();
        }
    	
    	
    }
    private String getAttrValue(String str, String token)
    { 
		String value = EMPTY_STRING;
    	String lowstr = str.toLowerCase();
    	token = token.toLowerCase();
    	int len = token.length();
    	int indx = lowstr.indexOf(token);
    	  
    	if (indx < 0)
    		return value;
    	   
    	String sub = str.substring(indx + len, str.length());
    	  
    	value = sub.substring(0, sub.indexOf(CLOSING_PAREN));
    	   
    	if (value.length() > 0)
    		value = value.trim();

    	return value;
    }
}

