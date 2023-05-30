package com.ibm.ecm.configmgr;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import javax.swing.JButton;
import javax.swing.JFileChooser;
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

public class DeployApplication_wasgeneralst extends JPanel {
	private static final String appEditionVersion = "";
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private String domainName="default";
	private static final String EAR = "ear";
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";

	/**
	 * Create the panel.
	 */
	public DeployApplication_wasgeneralst()
	{
		
	}
	public DeployApplication_wasgeneralst(String j1,String j2,String j3,String j4,String j5,String j6
			,String j7,String j8,String j9,String j10,String j11,String j12,String j13,
			String j14,String j15,final File file) 
	{
		
		JLabel lblNewLabel = new JLabel("Deploy Application");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_1 = new JLabel("Application Name* :");
		
		textField = new JTextField();
		textField.setText(j1);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Application Server Name* :");
		
		textField_1 = new JTextField();
		textField_1.setText(j3);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Context root for Engine-init.war :");
		
		textField_2 = new JTextField();
		textField_2.setText(j6);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Context root for Engine-health.war :");
		
		textField_3 = new JTextField();
		textField_3.setText(j8);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Context root for acce :");
		
		textField_4 = new JTextField();
		textField_4.setText(j10);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Context root for IBM Content Case Engine :");
		
		textField_5 = new JTextField();
		textField_5.setText(j12);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("GCD JDBC Data Source Name :");
		
		textField_6 = new JTextField();
		textField_6.setText(j14);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Application Server Node* :");
		
		textField_7 = new JTextField();
		textField_7.setText(j2);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Application Server Virtual Host* :");
		
		textField_8 = new JTextField();
		textField_8.setText(j4);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Context root for Client-download.war :");
		
		textField_9 = new JTextField();
		textField_9.setText(j7);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Context root for IBM FileNet P8 CEWS :");
		
		textField_10 = new JTextField();
		textField_10.setText(j9);
		textField_10.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Context root for IBM FileNet BPM PEWSI :");
		
		textField_11 = new JTextField();
		textField_11.setText(j11);
		textField_11.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Context root for IBM Content Case Engine WS-Broken :");
		
		textField_12 = new JTextField();
		textField_12.setText(j13);
		textField_12.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("GCD JDBC XA Data Source Name :");
		
		textField_13 = new JTextField();
		textField_13.setText(j15);
		textField_13.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("Script :");
		
		textField_14 = new JTextField();
		textField_14.setText(j5);
		textField_14.setColumns(10);
		
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
		        textField_14.setText(profAPath);
			}
		});
		
		JSeparator separator_1 = new JSeparator();
		
		JButton btnNewButton_1 = new JButton("Run Task");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				runActionPerformed(e);
			}
		});
		
		JButton btnNewButton_2 = new JButton("Save");
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PropertyFactory props = new PropertyFactory();
				props.XmlDoc(file.toString(), (String)null);
				Element doc1 = props.getDocElem();
				props.setChildValueByName(doc1, PROPERTY, "ApplicationName", VALUE, textField.getText());
				props.setChildValueByName(doc1, PROPERTY, "ApplicationServerNode", VALUE, textField_7.getText());
				props.setChildValueByName(doc1, PROPERTY, "ApplicationServerName", VALUE, textField_1.getText());
				props.setChildValueByName(doc1, PROPERTY, "ApplicationServerVirtualHost", VALUE, textField_8.getText());
				props.setChildValueByName(doc1, PROPERTY, "Script", VALUE, textField_14.getText());
				props.setChildValueByName(doc1, PROPERTY, "engineinit", VALUE, textField_2.getText());
				props.setChildValueByName(doc1, PROPERTY, "clientdownload", VALUE, textField_9.getText());
				props.setChildValueByName(doc1, PROPERTY, "enginehealth", VALUE, textField_3.getText());
				props.setChildValueByName(doc1, PROPERTY, "cews", VALUE, textField_10.getText());
				props.setChildValueByName(doc1, PROPERTY, "acce", VALUE, textField_4.getText());
				props.setChildValueByName(doc1, PROPERTY, "pewsi", VALUE, textField_11.getText());
				props.setChildValueByName(doc1, PROPERTY, "engine", VALUE, textField_5.getText());
				props.setChildValueByName(doc1, PROPERTY, "enginebroker", VALUE, textField_12.getText());
				props.setChildValueByName(doc1, PROPERTY, "JNDIName", VALUE, textField_6.getText());
				props.setChildValueByName(doc1, PROPERTY, "JNDIXAName", VALUE, textField_13.getText());
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
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1204, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(62)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(62)
					.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(62)
					.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(62)
					.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(62)
					.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel_6)
					.addGap(40)
					.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(62)
					.addComponent(lblNewLabel_13, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(62)
					.addComponent(lblNewLabel_14, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel_15, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_14, GroupLayout.PREFERRED_SIZE, 617, GroupLayout.PREFERRED_SIZE)
					.addGap(100)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 1204, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(866)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_1))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_8))
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_2))
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_9))
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_3))
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_10))
						.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_4))
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_11))
						.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_5))
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_12))
						.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_6))
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_13))
						.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_7))
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_14))
						.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_15))
						.addComponent(textField_14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(4)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_1)))
		);
		setLayout(groupLayout);
	}
	public void runActionPerformed(ActionEvent e)
	{
		String console = exec();
        JOptionPane.showMessageDialog(null, "Deploying FileNetEngine application for " + CMUtil.profileName + ". Click Ok to proceed.", "Console View", JOptionPane.INFORMATION_MESSAGE);
    	//new NewJFrame(CMUtil.profileName,console).setVisible(true);
        ConsoleOP.appendText(console);
        //dispose();    	
    }//GEN-LAST:event_jButton3ActionPerformed

    public String exec() {
    	String script = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\scripts\\deployWSApplication.tcl";
        String tempFile = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\tmp\\deployapplication.tcl";
        setScript(script, tempFile);
                	    	
    	String EMPTY_STRING = "";
    	String command = EMPTY_STRING;
		String jvmargs = EMPTY_STRING;
		String arguments = EMPTY_STRING;
		String workingDir = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure";
		File workingDirf = new File(workingDir);
		
		command = "configmgr_cl execute -task deployapplication -profile "+CMUtil.profileName;
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
			ioe.printStackTrace();
			//String localizedMsg1 = ioe.getLocalizedMessage();
	    }
		return sbout.toString();
	}
    @SuppressWarnings("deprecation")
   	public void setScript(String script, String wsTclRuntime) {
       	try {
       		//
       	String QUOTE = "\"", strEOL = "\r\n";
       	String toAuthnJar = CMUtil.appServerInstallFolder + File.separator+ "lib"+ File.separator+ "Engine-authn.jar";
       	System.out.println(toAuthnJar);
		String authnJar = "C:\\Program Files\\IBM\\FileNet\\ContentEngine" + File.separator+ "lib"+ File.separator+ "Engine-authn.jar";
		if (new File(authnJar).exists()) {
			FileUtil.copy(new File(authnJar), new File(toAuthnJar));
		}
       	FileReader inFR = new FileReader(script);
		BufferedReader inBR = new BufferedReader(inFR);
		FileWriter outFW = new FileWriter(wsTclRuntime);
		String earFileToDeploy = "";
		if (earFileToDeploy == null || earFileToDeploy.length() <= 0) {
			String newearFileLoc = "C:\\Program Files\\IBM\\FileNet\\ContentEngine"+ File.separator+ "lib"+ File.separator+ "Engine-ws.ear";
			//String newearFileLoc = configMgrProps.getConfigMgrProperty(ConfigKeys.CE_INSTALL_DIR)+ File.separator+ "lib"+ File.separator+ "Engine-ws.ear";
				//earFileToDeploy = profilePath + File.separator + EAR;
				earFileToDeploy = NewJFrame.filePath + File.separator + CMUtil.profileName + File.separator + EAR ;
				//Always copy from lib to ear
				new File(earFileToDeploy).mkdir();
				earFileToDeploy += File.separator + "Engine-wl.ear";
				FileUtil.copy(new File(newearFileLoc), new File(earFileToDeploy));
				
		}
		File earFile = new File(earFileToDeploy);
		outFW.write("set _ear " + QUOTE + earFileToDeploy.replace('\\', '/') + QUOTE + strEOL);//$NON-NLS-1$ 
		outFW.write("set _wasVersion " + QUOTE + CMUtil.appServerVersion + QUOTE + strEOL);//$NON-NLS-1$ 
		/*if (type.equalsIgnoreCase(Messages.DEPLOYMENT_TYPE_CLUSTER_DESC))
			outFW.write("set _clusterName  " + QUOTE + applicationServerClusterName + QUOTE + strEOL);//$NON-NLS-1$ */
		//if (!type.equalsIgnoreCase(Messages.DEPLOYMENT_TYPE_CLUSTER_DESC)) {
			outFW.write("set _nodeName " + QUOTE + textField_7.getText() + QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("set _serverName " + QUOTE + textField_1.getText() + QUOTE + strEOL);//$NON-NLS-1$ 
		//}

		outFW.write("set _virtualHost " + QUOTE + textField_8.getText() + QUOTE + strEOL);//$NON-NLS-1$
		outFW.write("set _applicationName " + QUOTE + textField.getText() + QUOTE + strEOL);//$NON-NLS-1$ 
		// change for defect 3467 start
		if (appEditionVersion != null && !appEditionVersion.equals("default") && !appEditionVersion.equals("")) {
			outFW.write("set _applicationNameAppEdition " + QUOTE + textField.getText() + "-edition" + appEditionVersion + QUOTE + strEOL);//$NON-NLS-1$
		} else {
			outFW.write("set _applicationNameAppEdition " + QUOTE + textField.getText() + QUOTE + strEOL);//$NON-NLS-1$
		}
		// Change for defect 3467 end
		outFW.write("set _cellName " + QUOTE + CMUtil.appServerCell + QUOTE + strEOL);//$NON-NLS-1$ 
		outFW.write("set _deploymentType " + QUOTE + CMUtil.implementorID + QUOTE + strEOL);//$NON-NLS-1$ to-be changed
		//outFW.write("set _timeout " + transactionTimeOut + strEOL);$NON-NLS-1$ 

		if (CMUtil.appServerDomain.equals("default")) {
			outFW.write("set _domainNotSet \"true\"" + strEOL);
		} else {
			outFW.write("set _domainNotSet \"false\"" + strEOL);
			outFW.write("set _domainName " + QUOTE + domainName + QUOTE + strEOL);//$NON-NLS-1$ 
		}
		// change for defect 3467 start
		if (appEditionVersion.equals("default")) {
			outFW.write("set _appEdition \"false\"" + strEOL);
			outFW.write("set _appEditionVersion " + QUOTE + appEditionVersion + QUOTE + strEOL);//$NON-NLS-1$ 
		} else {
			outFW.write("set _appEdition \"true\"" + strEOL);
			outFW.write("set _appEditionVersion " + QUOTE + appEditionVersion + QUOTE + strEOL);//$NON-NLS-1$ 
		}
			outFW.write("set _engineInitContextroot " + QUOTE + textField_2.getText() + QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("set _clientDownloadContextroot " + QUOTE + textField_9.getText() + QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("set _pewsiContextroot " + QUOTE + textField_11.getText() + QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("set _acceContextroot " + QUOTE + textField_4.getText() + QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("set _engineContextroot " + QUOTE + textField_5.getText() + QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("set _engineHealthContextroot " + QUOTE + textField_3.getText() + QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("set _engineBrokerContextroot " + QUOTE + textField_12.getText() + QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("set _cewsContextroot " + QUOTE + textField_10.getText() + QUOTE + strEOL);//$NON-NLS-1$
			
		//}
			String aLine;
			boolean turnoffSSLcerts = Boolean.parseBoolean(CMUtil.turnOffSSL);
			while ((aLine = inBR.readLine()) != null) {
				if (aLine.startsWith("set _turnoffSSLcerts") && !turnoffSSLcerts) //$NON-NLS-1$
					outFW.write("set _turnoffSSLcerts \"true\""); //$NON-NLS-1$
				else
					outFW.write(aLine + strEOL);
			}

			inFR.close();
			inBR.close();
			outFW.close();
       	}
       	catch(IOException ex) {
       		ex.printStackTrace();
       	}
    }
}
