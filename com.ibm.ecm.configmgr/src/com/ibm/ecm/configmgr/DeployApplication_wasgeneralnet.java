package com.ibm.ecm.configmgr;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
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


public class DeployApplication_wasgeneralnet extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_2;
	private JComboBox<String> comboBox_3;
	private JButton btnNewButton;
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";
	private PropertyFactory props = new PropertyFactory();
	private static final String EAR = "ear";

	/**
	 * Create the panel.
	 */
	/*public DeployApplication() {
		
	}
	*/
	public DeployApplication_wasgeneralnet(String j1,String j2,String j3,String j4,String j5,String j6
			,String j7,String j8,String j9,final File file) {
		
		JLabel lblNewLabel = new JLabel("Deploy Application ");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JSeparator separator = new JSeparator();
		
		
		JLabel lblNewLabel_2 = new JLabel("Content Platform Engine Application name :");
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setText(j1);
		
		JLabel lblNewLabel_3 = new JLabel("Application server node :");
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<>(new String[] {"a","b"}));
		comboBox_1.setSelectedItem(new String(j2));
		
		JLabel lblNewLabel_4 = new JLabel("Application server name :");
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setModel(new DefaultComboBoxModel<>(new String[] {"a","b"}));
		comboBox_2.setSelectedItem(new String(j3));
		
		JLabel lblNewLabel_5 = new JLabel("Application Server virtual host :");
		
		comboBox_3 = new JComboBox<String>();
		comboBox_3.setModel(new DefaultComboBoxModel<>(new String[] {"default_host","b"}));
		comboBox_3.setSelectedItem(new String(j4));
		
		JLabel lblNewLabel_6 = new JLabel("WAS Application Edition Management :");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setText(j5);
		
		JLabel lblNewLabel_7 = new JLabel("Script :");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setText(j6);
		
		btnNewButton = new JButton("Browse");
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
		        textField_2.setText(profAPath);
			}
		});
		
		JLabel lblNewLabel_8 = new JLabel("Temporary Directory :");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setText(j7);
		
		JButton btnNewButton_1 = new JButton("Browse");
		btnNewButton_1.addActionListener(new ActionListener() {
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
		        textField_3.setText(profAPath);
			}
		});
		
		JLabel lblNewLabel_9 = new JLabel("GCD JDBC Datasource name :");
		
		JLabel lblNewLabel_10 = new JLabel("GCD JDBC XA Datasource name :");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setText(j8);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setText(j9);
		
		JSeparator separator_1 = new JSeparator();
		
		JButton btnNewButton_2 = new JButton("Save");
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				props.XmlDoc(file.toString(), (String)null);
				Element doc1 = props.getDocElem();
				props.setChildValueByName(doc1, PROPERTY, "ApplicationName", VALUE, textField.getText());
				props.setChildValueByName(doc1, PROPERTY, "ApplicationServerNode", VALUE, props.getValue(comboBox_1));
				props.setChildValueByName(doc1, PROPERTY, "ApplicationServerName", VALUE, props.getValue(comboBox_2));
				props.setChildValueByName(doc1, PROPERTY, "ApplicationServerVirtualHost", VALUE, props.getValue(comboBox_3));
				props.setChildValueByName(doc1, PROPERTY, "Script", VALUE, textField_2.getText());
				props.setChildValueByName(doc1, PROPERTY, "AppEdition", VALUE, textField_1.getText());
				props.setChildValueByName(doc1, PROPERTY, "tempDir", VALUE, textField_3.getText());
				props.setChildValueByName(doc1, PROPERTY, "JNDIName", VALUE, textField_4.getText());
				props.setChildValueByName(doc1, PROPERTY, "JNDIXAName", VALUE, textField_5.getText());
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
		
		JButton btnNewButton_3 = new JButton("Run task");
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
					.addGap(33)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 729, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 325, Short.MAX_VALUE)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 112, Short.MAX_VALUE)))
					.addGap(6))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 325, Short.MAX_VALUE)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 112, Short.MAX_VALUE)))
					.addContainerGap(6, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 717, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(496)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(57)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED, 5, Short.MAX_VALUE))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE))
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE))
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_5))
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_6))
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(8)
							.addComponent(lblNewLabel_7))
						.addComponent(btnNewButton)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 3, Short.MAX_VALUE)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_8))
						.addComponent(btnNewButton_1, Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 3, Short.MAX_VALUE)))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_9))
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_10))
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_3))
					.addGap(6))
		);
		setLayout(groupLayout);
	}
	
	public void runActionPerformed(ActionEvent evt)
	{
		String console = exec();
        JOptionPane.showMessageDialog(null, "Deploying FileNetEngine application for " + CMUtil.profileName + ". Click Ok to proceed.", "Console View", JOptionPane.INFORMATION_MESSAGE);
        ConsoleOP.appendText(console);
	}
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
       		String QUOTE = "\"", strEOL = "\r\n";
           	String toAuthnJar = CMUtil.appServerInstallFolder + File.separator+ "lib"+ File.separator+ "Engine-authn.jar";
    		String authnJar = "C:\\Program Files\\IBM\\FileNet\\ContentEngine" + File.separator+ "lib"+ File.separator+ "Engine-authn.jar";
    		String appEditionVersion = textField_1.getText();
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
			outFW.write("set _wasVersion " + QUOTE + CMUtil.appServerVersion + QUOTE + strEOL);
			outFW.write("set _nodeName " + QUOTE + props.getValue(comboBox_1) + QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("set _serverName " + QUOTE + props.getValue(comboBox_2) + QUOTE + strEOL);
			outFW.write("set _virtualHost " + QUOTE + props.getValue(comboBox_3) + QUOTE + strEOL);//$NON-NLS-1$
			outFW.write("set _applicationName " + QUOTE + textField.getText() + QUOTE + strEOL);
    		if (appEditionVersion != null && !appEditionVersion.equals("default") && !appEditionVersion.equals("")) {
				outFW.write("set _applicationNameAppEdition " + QUOTE + textField.getText() + "-edition" + appEditionVersion + QUOTE + strEOL);//$NON-NLS-1$
			} else {
				outFW.write("set _applicationNameAppEdition " + QUOTE + textField.getText() + QUOTE + strEOL);//$NON-NLS-1$
			}
    		outFW.write("set _cellName " + QUOTE + CMUtil.appServerCell + QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("set _deploymentType " + QUOTE + "network" + QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("set _timeout " + CMUtil.appServerTimeout + strEOL);
			if (CMUtil.appServerDomain.equals("default")) {
				outFW.write("set _domainNotSet \"true\"" + strEOL);
			} else {
				outFW.write("set _domainNotSet \"false\"" + strEOL);
				outFW.write("set _domainName " + QUOTE + CMUtil.appServerDomain + QUOTE + strEOL);
			}
			if (appEditionVersion.equals("default")) {
				outFW.write("set _appEdition \"false\"" + strEOL);
				outFW.write("set _appEditionVersion " + QUOTE + appEditionVersion + QUOTE + strEOL);//$NON-NLS-1$ 
			} else {
				outFW.write("set _appEdition \"true\"" + strEOL);
				outFW.write("set _appEditionVersion " + QUOTE + appEditionVersion + QUOTE + strEOL);
			}
			String jdbcdsname = textField_4.getText();
			String jdbcdsxaname = textField_5.getText();
			
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