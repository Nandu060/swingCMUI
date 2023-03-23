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

public class DeployApplication_wlgeneral extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
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

	/**
	 * Create the panel.
	 */
	public DeployApplication_wlgeneral()
	{
		
	}
	public DeployApplication_wlgeneral(String j1,String j2,String j3,String j4,final File file) 
	{
		
		JLabel lblNewLabel = new JLabel("Deploy Application");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_1 = new JLabel("Content Platform Engine application name* :");
		
		textField = new JTextField();
		textField.setText(j1);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Script* :");
		
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
		        textField_1.setText(profAPath);
			}
		});
		
		textField_1 = new JTextField();
		textField_1.setText(j2);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("GCD JDBC Data Source name* :");
		
		textField_2 = new JTextField();
		textField_2.setText(j3);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("GCD JDBC XA Data Source name* :");
		
		textField_3 = new JTextField();
		textField_3.setText(j4);
		textField_3.setColumns(10);
		
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
				try 
				{
					System.out.println(file);
					DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder(); 
					Document doc2 = documentBuilder.parse(file);
					NodeList nd = doc2.getElementsByTagName("property");
					System.out.println(nd.getLength());  
					  for(int i=0;i<nd.getLength();i++)
					  {
						  Node p = nd.item(i);
						  if(p.getNodeType() == Node.ELEMENT_NODE)
						  {
							  Element property = (Element)p;
							  String name = property.getAttribute("name");
							  if(name.equalsIgnoreCase("ApplicationName"))
							  {
							  NodeList nd1 = property.getChildNodes();
							  for(int j=0;j<nd1.getLength();j++)
							  {
								  Node q = nd1.item(j);
								  if(q.getNodeType()== Node.ELEMENT_NODE)
								  {
									  Element value = (Element)q;
									  if(value.getTagName().equalsIgnoreCase("value"))
									  {
										  value.setTextContent(textField.getText());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("Script"))
							  {
							  NodeList nd1 = property.getChildNodes();
							  for(int j=0;j<nd1.getLength();j++)
							  {
								  Node q = nd1.item(j);
								  if(q.getNodeType()== Node.ELEMENT_NODE)
								  {
									  Element value = (Element)q;
									  if(value.getTagName().equalsIgnoreCase("value"))
									  {
										  value.setTextContent(textField_1.getText());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("JNDIName"))
							  {
							  NodeList nd1 = property.getChildNodes();
							  for(int j=0;j<nd1.getLength();j++)
							  {
								  Node q = nd1.item(j);
								  if(q.getNodeType()== Node.ELEMENT_NODE)
								  {
									  Element value = (Element)q;
									  if(value.getTagName().equalsIgnoreCase("value"))
									  {
										  value.setTextContent(textField_2.getText());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("JNDIXAName"))
							  {
							  NodeList nd1 = property.getChildNodes();
							  for(int j=0;j<nd1.getLength();j++)
							  {
								  Node q = nd1.item(j);
								  if(q.getNodeType()== Node.ELEMENT_NODE)
								  {
									  Element value = (Element)q;
									  if(value.getTagName().equalsIgnoreCase("value"))
									  {
										  value.setTextContent(textField_3.getText());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
									  }
								  }
							  }
							  }
						  }
					  }
					  TransformerFactory transformerFactory = TransformerFactory.newInstance();
					  Transformer transformer = transformerFactory.newTransformer(); 
					  DOMSource domSource = new DOMSource(doc2); 
					  StreamResult streamResult = new StreamResult(file);
					  
					  transformer.transform(domSource, streamResult);
					  
					  System.out.println("Done creating XML File");
					  }
					  catch(DOMException |
							  ParserConfigurationException | SAXException | IOException | TransformerException de) {
							  de.printStackTrace(); 
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
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(303)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 770, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(502)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
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
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_2))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_3))
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_4))
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_1)))
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
			outFW.write("appName=" + SINGLE_QUOTE + textField.getText() + SINGLE_QUOTE + strEOL);//$NON-NLS-1$ 
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
