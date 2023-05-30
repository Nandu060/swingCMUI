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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import org.w3c.dom.Element;

import com.ibm.Factory.PropertyFactory;

public class DeployApplication_wasgeneralcl extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";
	private PropertyFactory props = new PropertyFactory();
	private static final String EAR = "ear";

	/**
	 * Create the panel.
	 */
	public DeployApplication_wasgeneralcl(String j1, String j2, String j4, String j5, String j6, String j7, String j14, String j15, final File file) {
		
		JLabel lblNewLabel = new JLabel("Deploy Application");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_1 = new JLabel("Content Platform Engine application name* :");
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setText(j1);
		
		JLabel lblNewLabel_2 = new JLabel("Application server cluster name* : ");
		
		comboBox = new JComboBox();
		comboBox.setSelectedItem(j2);
		
		JLabel lblNewLabel_3 = new JLabel("Application server virtual host* :");
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel<>(new String[] {"default_host","b"}));
		comboBox_1.setSelectedItem(j4);
		
		JLabel lblNewLabel_4 = new JLabel("WAS Application Edition management* :");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setText(j5);
		
		JLabel lblNewLabel_5 = new JLabel("Script* :");
		
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
		        textField_2.setText(profAPath);
			}
		});
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setText(j6);
		
		/*JLabel lblNewLabel_6 = new JLabel("Temporary directory* :");
		
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
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setText(j7);*/
		
		JLabel lblNewLabel_7 = new JLabel("GCD JDBC data source name* :");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setText(j14);
		
		JLabel lblNewLabel_8 = new JLabel("GCD JDBC XA data source name* :");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setText(j15);
		
		JSeparator separator_1 = new JSeparator();
		
		JButton btnNewButton_2 = new JButton("Run task");
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				runActionPerformed(e);
			}
		});
		
		JButton btnNewButton_3 = new JButton("Save");
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				props.XmlDoc(file.toString(), (String)null);
				Element doc1 = props.getDocElem();
				props.setChildValueByName(doc1, PROPERTY, "ApplicationName", VALUE, textField.getText());
				props.setChildValueByName(doc1, PROPERTY, "ApplicationServerVirtualHost", VALUE, props.getValue(comboBox_1));
				props.setChildValueByName(doc1, PROPERTY, "ApplicationServerClusterName", VALUE, props.getValue(comboBox));
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
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 754, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
				/*.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))*/
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 754, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(521)
					.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_1))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_2))
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_3))
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_4))
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_5))
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(13)
					/*.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_6))
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1))
					.addGap(14)*/
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_7))
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_8))
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_3)
						.addComponent(btnNewButton_2)))
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
    		outFW.write("set _clusterName  " + QUOTE + props.getValue(comboBox) + QUOTE + strEOL);
    		outFW.write("set _virtualHost " + QUOTE + props.getValue(comboBox_1) + QUOTE + strEOL);//$NON-NLS-1$
			outFW.write("set _applicationName " + QUOTE + textField.getText() + QUOTE + strEOL);
    		if (appEditionVersion != null && !appEditionVersion.equals("default") && !appEditionVersion.equals("")) {
				outFW.write("set _applicationNameAppEdition " + QUOTE + textField.getText() + "-edition" + appEditionVersion + QUOTE + strEOL);//$NON-NLS-1$
			} else {
				outFW.write("set _applicationNameAppEdition " + QUOTE + textField.getText() + QUOTE + strEOL);//$NON-NLS-1$
			}
    		outFW.write("set _cellName " + QUOTE + CMUtil.appServerCell + QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("set _deploymentType " + QUOTE + "cluster" + QUOTE + strEOL);//$NON-NLS-1$ 
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

