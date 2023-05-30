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

public class DeployApplication_wlgeneral extends JPanel {
	private JTextField CPEAppNamet;
	private JTextField Scriptt;
	private JTextField JDBCDSNamet;
	private JTextField JDBCDSXANamet;
	private static final String DEPLOY_WL_APPLICATION_PY = "deployWLApplication.py"; //$NON-NLS-1$
	private static final String FILE_NET_ENGINE = "FileNetEngine"; //$NON-NLS-1$
	private static final String ENGINE_WL_EAR = "Engine-wl.ear"; //$NON-NLS-1$ changed from Engine-wl.ear to Engine-wl1221.ear 18537

	private static final String EAR = "ear"; //$NON-NLS-1$
	private static final String NA = "NA"; //$NON-NLS-1$
	private static final String DONE_DEPLOYING_APPLICATION = "Done deploying application"; //$NON-NLS-1$
	private static final String LOG_EXT = ".log"; //$NON-NLS-1$
	private static final String SINGLE_QUOTE = "'"; //$NON-NLS-1$
	private static final String T3S_PROTOCOL = "t3s://"; //$NON-NLS-1$
	private static final String T3_PROTOCOL = "t3://"; //$NON-NLS-1$
	private static final String TRUE = "true"; //$NON-NLS-1$
	private static final String COLON = ":"; //$NON-NLS-1$
	private static final String PY_EXT = ".py"; 
	private static final String _12c_2_1 = "12.2"; //$NON-NLS-1$ 12c(12.2.1)
	private static final String _14c_R1 = "14.1";
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";
	PropertyFactory props = new PropertyFactory();

	/**
	 * Create the panel.
	 */
	public DeployApplication_wlgeneral()
	{
		
	}
	public DeployApplication_wlgeneral(String CPEAppName,String Script,String JDBCDSName,String JDBCDSXAName,final File file) 
	{
		
		JLabel lblNewLabel = props.setLabel("Deploy Application"); //new JLabel("Deploy Application");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_1 = props.setLabel("Content Platform Engine application name* :"); //new JLabel("Content Platform Engine application name* :");
		
		CPEAppNamet = props.getTextField();//new JTextField();
		CPEAppNamet.setText(CPEAppName);
		CPEAppNamet.setColumns(10);
		
		JLabel lblNewLabel_2 = props.setLabel("Script* :"); //new JLabel("Script* :");
		
		JButton browseBtn = props.getButton("Browse"); //new JButton("Browse");
		browseBtn.addActionListener(new ActionListener() {
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
		        Scriptt.setText(profAPath);
			}
		});
		
		Scriptt = props.getTextField(); //new JTextField();
		Scriptt.setText(Script);
		Scriptt.setColumns(10);
		
		JLabel lblNewLabel_3 = props.setLabel("GCD JDBC Data Source name* :"); //new JLabel("GCD JDBC Data Source name* :");
		
		JDBCDSNamet = props.getTextField(); //new JTextField();
		JDBCDSNamet.setText(JDBCDSName);
		JDBCDSNamet.setColumns(10);
		
		JLabel lblNewLabel_4 = props.setLabel("GCD JDBC XA Data Source name* :"); //new JLabel("GCD JDBC XA Data Source name* :");
		
		JDBCDSXANamet = props.getTextField(); //new JTextField();
		JDBCDSXANamet.setText(JDBCDSXAName);
		JDBCDSXANamet.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		
		JButton runBtn = props.getButton("Run task"); //new JButton("Run Task");
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
				props.setChildValueByName(doc1, PROPERTY, "ApplicationName", VALUE, CPEAppNamet.getText());
				props.setChildValueByName(doc1, PROPERTY, "Script", VALUE, Scriptt.getText());
				props.setChildValueByName(doc1, PROPERTY, "JNDIName", VALUE, JDBCDSNamet.getText());
				props.setChildValueByName(doc1, PROPERTY, "JNDIXAName", VALUE, JDBCDSXANamet.getText());
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
					.addGap(19)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 775, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(CPEAppNamet, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(Scriptt, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(303)
							.addComponent(browseBtn, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(JDBCDSNamet, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(JDBCDSXANamet, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 770, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(502)
					.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(runBtn, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_1))
						.addComponent(CPEAppNamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_2))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(Scriptt, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addComponent(browseBtn))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_3))
						.addComponent(JDBCDSNamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_4))
						.addComponent(JDBCDSXANamet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(saveBtn)
						.addComponent(runBtn)))
		);
		setLayout(groupLayout);
	}
	public void runActionPerformed(ActionEvent evt)
	{
		String console = exec();
        JOptionPane.showMessageDialog(null, "Deploying FileNetEngine application for " + CMUtil.profileName + ". Click Ok to proceed.", "Console View", JOptionPane.INFORMATION_MESSAGE);
    	//new NewJFrame(CMUtil.profileName,console).setVisible(true);
        ConsoleOP.appendText(console);
	}
	public String exec() {
    	String script = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\scripts\\deployWLApplication.py";
        String tempFile = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\tmp\\deployapplication.py";
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
   	public void setScript(String script, String wlPyRuntime) {
       	try {
       		String strEOL = CMUtil.getPlatformEOL();
       		String earFileToDeploy =  "";
       		if(CMUtil.appServerVersion.contains(_12c_2_1) || CMUtil.appServerVersion.contains(_14c_R1))
            {
            	try
            	{
            		if (earFileToDeploy == null || earFileToDeploy.length() <= 0)
					{	            		
	            		String newearFileLoc = "C:\\Program Files\\IBM\\FileNet\\ContentEngine"+ File.separator+ "lib"+ File.separator;
    	        		
    	        		//newearFileLoc= version1.contains(_12c_2_1) ? newearFileLoc+"Engine-wl122.ear" : newearFileLoc+"Engine-wl.ear"; //changed from equals to contains 18537
	            		newearFileLoc= newearFileLoc+"Engine-wl122.ear"; // 18537
						earFileToDeploy = NewJFrame.filePath + File.separator + CMUtil.profileName + File.separator + EAR ;
						new File(earFileToDeploy).mkdir(); // if ear directory does not exist. 
						earFileToDeploy += File.separator + "Engine-wl.ear"; // changed from Engine-wl.ear to Engine-wl122.ear 18557

						FileUtil.copy(new File(newearFileLoc), new File(earFileToDeploy));    
    					//System.out.println("Copied Engine-wl.ear from lib to ear.");
    				}

            	}catch(Exception e){
            		e.printStackTrace();
            	}
            }

			String destination_ear = earFileToDeploy; // ... to here

			File earFile = new File(earFileToDeploy);
			String url = T3_PROTOCOL+CMUtil.appServerHostName+COLON+CMUtil.appServerSOAP;
			if (CMUtil.SSLEnabled.equalsIgnoreCase(TRUE))
				url = T3S_PROTOCOL+CMUtil.appServerHostName+COLON+CMUtil.appServerSOAP;
	        
			destination_ear = destination_ear.replace('\\', '/');				
    		
			FileReader inFR = new FileReader(script);
			BufferedReader inBR = new BufferedReader(inFR);
			FileWriter outFW = new FileWriter(wlPyRuntime);
			outFW.write("wlAdmin=" + SINGLE_QUOTE + CMUtil.appServerAdminUser + SINGLE_QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("wlAdminPassword=" + SINGLE_QUOTE + CMUtil.appServerAdminPassword + SINGLE_QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("connectionURL=" + SINGLE_QUOTE + url + SINGLE_QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("wlTransactionTimeOut=" + SINGLE_QUOTE + CMUtil.appServerTimeout + SINGLE_QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("destinationEar=" + SINGLE_QUOTE + destination_ear + SINGLE_QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("deployTarget=" + SINGLE_QUOTE + CMUtil.targetName + SINGLE_QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("appName=" + SINGLE_QUOTE + CPEAppNamet.getText() + SINGLE_QUOTE + strEOL);//$NON-NLS-1$ 
			String cmpath = NewJFrame.filePath + File.separator + CMUtil.profileName + File.separator + "plan.xml"; //13674
			cmpath = cmpath.replace('\\','/');
			outFW.write("deploymentPlan=" + SINGLE_QUOTE + cmpath + SINGLE_QUOTE + strEOL);//$NON-NLS-1$
			String aLine;
			while ((aLine=inBR.readLine()) != null)
				outFW.write(aLine+ strEOL);
			inFR.close();
			inBR.close();
			outFW.close();
       	}
       	catch(IOException e)
       	{
       		e.printStackTrace();
       	}
    }
}
