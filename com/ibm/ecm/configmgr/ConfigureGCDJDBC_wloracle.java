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

public class ConfigureGCDJDBC_wloracle extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	//private JTextField passField_1;
	//private JTextField passField_2;
	private JTextField textField_7;
	private JTextField textField_8;
	private JPasswordField passField_1;
	private JPasswordField passField_2;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	public String serviceName = "";
	public String dbFailover = "";
	private static final String SINGLE_QUOTE = "'";
	String strEOL = CMUtil.getPlatformEOL();
	private static final String DB_TEST_TABLE_VAR = "dbTestTable="; //$NON-NLS-1$
	private static final String DB_XA_PROPS_VAR = "dbXaProps="; //$NON-NLS-1$
	private static final String DB_PROPS_VAR = "dbProps="; //$NON-NLS-1$
	private static final String DB_URL_VAR = "dbURL="; //$NON-NLS-1$
	private static final String DB_DRIVER_XA_NAME_VAR = "dbDriverXaName="; //$NON-NLS-1$
	private static final String DB_DRIVER_NAME_VAR = "dbDriverName="; //$NON-NLS-1$
	private static final String DB_NAME_VAR = "dbName="; //$NON-NLS-1$
	private static final String DB_PASSWORD_VAR = "dbPassword="; //$NON-NLS-1$
	private static final String DB_USER_VAR = "dbUser="; //$NON-NLS-1$
	private static final String DB_PORT_VAR = "dbPort="; //$NON-NLS-1$
	private static final String DB_SERVER_VAR = "dbServer="; //$NON-NLS-1$
	private static final String DB_TYPE_VAR = "dbType="; //$NON-NLS-1$
	private static final String TARGET_NAME_VAR = "targetName="; //$NON-NLS-1$
	private static final String DS_XA_NAME_VAR = "dsXaName="; //$NON-NLS-1$
	private static final String DS_NAME_VAR = "dsName="; //$NON-NLS-1$
	private static final String JDBC_XA_NAME_VAR = "jdbcXaName="; //$NON-NLS-1$
	private static final String JDBC_NAME_VAR = "jdbcName="; //$NON-NLS-1$
	private static final String CONNECTION_URL_VAR = "connectionURL="; //$NON-NLS-1$
	private static final String WL_ADMIN_PASSWORD_VAR = "wlAdminPassword="; //$NON-NLS-1$
	private static final String WL_ADMIN_VAR = "wlAdmin="; //$NON-NLS-1$
	private static final String LOG_EXT = ".log"; //$NON-NLS-1$
	private static final String ALREADY_EXISTS = " already exists"; //$NON-NLS-1$
	private static final String DONE_CONFIGURING_XA_DATA_SOURCE = "Done configuring XA DataSource"; //$NON-NLS-1$
	private static final String DONE_CONFIGURING_DATA_SOURCE = "Done configuring DataSource"; //$NON-NLS-1$
	//private static final String SINGLE_QUOTE = "'"; //$NON-NLS-1$
	private static final String T3S_PROTOCOL = "t3s://"; //$NON-NLS-1$
	private static final String T3_PROTOCOL = "t3://"; //$NON-NLS-1$
	private static final String COLON = ":"; //$NON-NLS-1$
	private static final String TRUE = "true"; //$NON-NLS-1$
	private static final String CONNECTION_POOL_FOR_FILE_NET_ENGINE_XA_DATASOURCE = "Connection Pool For FileNet Engine XA Datasource"; //$NON-NLS-1$
	private static final String CONNECTION_POOL_FOR_FILE_NET_ENGINE_DATASOURCE = "Connection Pool For FileNet Engine Datasource"; //$NON-NLS-1$
	private static final String PY = ".py"; //$NON-NLS-1$
	private static final String _12c_2_1 = "12.2"; //$NON-NLS-1$ "12c(12.2.1)";  18507
	private static final String _14c_R1 = "14.1";//18743
	private static final String DB_FAILOVER_VAR = "dbFailOver="; //$NON-NLS-1$
	private static final String JDBC_ORACLE_THIN = "jdbc:oracle:thin:@//";

	/**
	 * Create the panel.
	 */
	public ConfigureGCDJDBC_wloracle(String j1,String j2,String j3,String j4,String j5,String j6,
			String j7,String j8,String j9,String j10,final File file) {
		
		JLabel lblNewLabel = new JLabel("Configure GCD JDBC Data Source");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_1 = new JLabel("JDBC Data Source Name* :");
		
		textField = new JTextField();
		textField.setText(j1);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("JDBC XA Data Source Name* :");
		
		textField_1 = new JTextField();
		textField_1.setText(j2);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Database server name* :");
		
		textField_2 = new JTextField();
		textField_2.setText(j3);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Database port number* :");
		
		textField_3 = new JTextField();
		textField_3.setText(j4);
		textField_3.setColumns(10);
		
		chckbxNewCheckBox = new JCheckBox("Oracle service name enabled");
		chckbxNewCheckBox.setSelected(Boolean.parseBoolean(j6));
		
		JLabel lblNewLabel_5 = new JLabel("Database username* :");
		
		textField_4 = new JTextField();
		textField_4.setText(j7);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Database password* :");
		
		passField_1 = new JPasswordField();
		passField_1.setText(j8);
		passField_1.setColumns(10);
		
		passField_2 = new JPasswordField();
		passField_2.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Confirm* :");
		
		JLabel lblNewLabel_8 = new JLabel("Script* :");
		
		textField_7 = new JTextField();
		textField_7.setText(j9);
		textField_7.setColumns(10);
		
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
		        textField_7.setText(profAPath);
			}
		});
		
		chckbxNewCheckBox_1 = new JCheckBox("Database failover support enabled");
		chckbxNewCheckBox_1.setSelected(Boolean.parseBoolean(j10));
		
		JSeparator separator_1 = new JSeparator();
		
		JButton btnNewButton_1 = new JButton("Test DB connection");
		
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
							  if(name.equalsIgnoreCase("JDBCDataSourceName"))
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
							  if(name.equalsIgnoreCase("JDBCDataSourceXAName"))
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
							  if(name.equalsIgnoreCase("DatabaseServerName"))
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
							  if(name.equalsIgnoreCase("DatabasePortNumber"))
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
							  if(name.equalsIgnoreCase("DatabaseName"))
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
										  value.setTextContent(textField_8.getText());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("ServiceNameEnabled"))
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
										  String res = "false";
										  if(chckbxNewCheckBox.isSelected())
											  res = "true";
										  serviceName = res;
										  value.setTextContent(res);
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("DatabaseUsername"))
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
										  value.setTextContent(textField_4.getText());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("DatabasePassword"))
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
										  value.setTextContent(passField_1.getText());
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
										  value.setTextContent(textField_7.getText());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("DBFailoverEnabled"))
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
										  String res = "false";
										  if(chckbxNewCheckBox_1.isSelected())
											  res = "true";
										  dbFailover = res;
										  value.setTextContent(res);
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
		
		JButton btnNewButton_3 = new JButton("Run Task");
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				runActionPerformed(e);
			}
		});
		
		JLabel lblNewLabel_9 = new JLabel("Database name* :");
		
		textField_8 = new JTextField();
		textField_8.setText(j5);
		textField_8.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 770, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(chckbxNewCheckBox, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(passField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(77)
							.addComponent(passField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(232)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(334)
					.addComponent(chckbxNewCheckBox_1, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 770, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(250)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(260)
							.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(150)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton_1)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
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
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_3))
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_4))
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(chckbxNewCheckBox)))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_9))
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_5))
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_6))
						.addComponent(passField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_7))
						.addComponent(passField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_8))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton))
					.addGap(7)
					.addComponent(chckbxNewCheckBox_1)
					.addGap(12)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_3)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_1)))
		);
		setLayout(groupLayout);

	}
	public void runActionPerformed(ActionEvent e)
	{
		String console = exec();
		//ConsoleOP consoleOP = new ConsoleOP();
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
	public void setScript(String script, String wlPyRuntime) {
    	
    	String dbType = CMUtil.dbType;
        String dbDriverName = "oracle.jdbc.OracleDriver"; //Hard coded
        String dbDriverXAName = "oracle.jdbc.xa.client.OracleXADataSource"; //Hard coded
        String databaseServerName = textField_2.getText();
        String databasePortNumber = textField_3.getText();
        String databaseName = textField_8.getText();
        String databaseUsername = textField_4.getText();
		String dbUrl = null; //getDbUrl(databaseServerName,databasePortNumber,databaseName);   Commented for making the changes for defect 4144
        //boolean databaseServiceNameOracleSet = Boolean.parseBoolean(getTaskCMProperties().getValue(ConfigKeys.ORACLE_DATABASE_SERVICE_NAME_CHECK_BOX));
        boolean databaseServiceNameOracleSet = Boolean.parseBoolean(serviceName);
        if (databaseServiceNameOracleSet){
        	dbUrl = getOracleServiceNameUrl(databaseServerName, databasePortNumber, databaseName);
        }
        else {
        	dbUrl = getDbUrl(databaseServerName,databasePortNumber,databaseName);
        }
        
        //System.out.println("dbType: " + dbType);	
        if (dbType.equalsIgnoreCase("MSSQL"))
        {
            final String SEMICOLON = ";";
            dbUrl += SEMICOLON+"encrypt=false"+SEMICOLON+"integratedSecurity=false";
            //System.out.println("dbUrl = " + dbUrl);
        }

        String dbProperties = getDbProperties(databaseUsername,databaseServerName,databasePortNumber,databaseName);
        String dbXAProperties = getDbXAProperties(databaseUsername,databaseServerName,databasePortNumber,databaseName);
        String dbTestTable = "SQL SELECT 1 FROM DUAL";//Hard coded
        String url = T3_PROTOCOL+CMUtil.appServerHostName+COLON+CMUtil.appServerSOAP;
        String jdbcConnPoolName = CONNECTION_POOL_FOR_FILE_NET_ENGINE_DATASOURCE;
        String jdbcConnPoolXAName = CONNECTION_POOL_FOR_FILE_NET_ENGINE_XA_DATASOURCE;
        try {
		FileReader inFR = new FileReader(script);
		BufferedReader inBR = new BufferedReader(inFR);
		FileWriter outFW = new FileWriter(wlPyRuntime);
		outFW.write(WL_ADMIN_VAR + SINGLE_QUOTE + CMUtil.appServerAdminUser + SINGLE_QUOTE + strEOL);
		outFW.write(WL_ADMIN_PASSWORD_VAR + SINGLE_QUOTE + CMUtil.appServerAdminPassword + SINGLE_QUOTE + strEOL);
		outFW.write(CONNECTION_URL_VAR + SINGLE_QUOTE + url + SINGLE_QUOTE + strEOL);
	    outFW.write(JDBC_NAME_VAR + SINGLE_QUOTE + jdbcConnPoolName + SINGLE_QUOTE + strEOL);
	    outFW.write(JDBC_XA_NAME_VAR + SINGLE_QUOTE + jdbcConnPoolXAName + SINGLE_QUOTE + strEOL);
	    outFW.write(DS_NAME_VAR + SINGLE_QUOTE + textField.getText() + SINGLE_QUOTE + strEOL);
	    outFW.write(DS_XA_NAME_VAR + SINGLE_QUOTE + textField_1.getText() + SINGLE_QUOTE + strEOL);
	    outFW.write(TARGET_NAME_VAR + SINGLE_QUOTE + CMUtil.targetName + SINGLE_QUOTE + strEOL);
	    outFW.write(DB_TYPE_VAR + SINGLE_QUOTE + dbType + SINGLE_QUOTE + strEOL);
		outFW.write(DB_SERVER_VAR + SINGLE_QUOTE + textField_2.getText() + SINGLE_QUOTE + strEOL);
		outFW.write(DB_PORT_VAR + SINGLE_QUOTE + textField_3.getText() + SINGLE_QUOTE + strEOL);
		outFW.write(DB_USER_VAR + SINGLE_QUOTE + textField_4.getText() + SINGLE_QUOTE + strEOL);
		outFW.write(DB_PASSWORD_VAR + SINGLE_QUOTE + passField_1.getText() + SINGLE_QUOTE + strEOL);
		outFW.write(DB_NAME_VAR + SINGLE_QUOTE + textField_8.getText() + SINGLE_QUOTE + strEOL);
		outFW.write(DB_DRIVER_NAME_VAR + SINGLE_QUOTE + dbDriverName + SINGLE_QUOTE + strEOL);
		outFW.write(DB_DRIVER_XA_NAME_VAR + SINGLE_QUOTE + dbDriverXAName + SINGLE_QUOTE + strEOL);
		outFW.write(DB_URL_VAR + SINGLE_QUOTE + dbUrl + SINGLE_QUOTE + strEOL);
		outFW.write(DB_PROPS_VAR + SINGLE_QUOTE + dbProperties + SINGLE_QUOTE + strEOL);
		outFW.write(DB_XA_PROPS_VAR + SINGLE_QUOTE + dbXAProperties + SINGLE_QUOTE + strEOL);
		outFW.write(DB_TEST_TABLE_VAR + SINGLE_QUOTE + dbTestTable + SINGLE_QUOTE + strEOL); 
		outFW.write(DB_FAILOVER_VAR + SINGLE_QUOTE + dbFailover + SINGLE_QUOTE + strEOL); 
		
		String aLine;
		while ((aLine=inBR.readLine()) != null)
			outFW.write(aLine+ strEOL);
		
		inFR.close();
		inBR.close();
		outFW.close();
        }
        catch(IOException ex)
        {
        	ex.printStackTrace();
        }
    	
    }
    public String getOracleServiceNameUrl(String dbserver, String dbport, String dbname){
    	return JDBC_ORACLE_THIN + dbserver + COLON + dbport + "/" + dbname;
    }
    
    public String getDbProperties(String databaseUsername,String databaseServerName,String databasePortNumber,String databaseName) {
    	return "user="+databaseUsername;
    }
    
    public String getDbUrl(String databaseServerName,String databasePortNumber,String databaseName) {
    	//return "jdbc:sqlserver://ms2022sql1.fyre.ibm.com:1433";
    	return "jdbc:oracle:thin:@//ora19c1.fyre.ibm.com:1521/orainst1";
    }
    
    public String getDbXAProperties(String databaseUsername,String databaseServerName,String databasePortNumber,String databaseName) {
    	return "user="+databaseUsername;
    	//return "user=daphne;portnumber=1433;selectMode=default;databaseName=MS_sampledb;serverName=ms2022sql1.fyre.ibm.com";
    }
    
    //protected abstract String getDbProperties(String dbuser, String dbserver, String dbport, String dbname);
    	
    	
    	
    
}

