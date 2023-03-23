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

public class ConfigureLDAP_wasjdbctivolife extends JPanel {
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

	/**
	 * Create the panel.
	 */
	public ConfigureLDAP_wasjdbctivolife(String j1,String j2,String j3,String j4,String j5,String j6
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
		        profPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
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
							  if(name.equalsIgnoreCase("LDAPServerHost"))
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
										  //System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("LDAPServerPort"))
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
										  //System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("LDAPBindDN"))
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
										  //System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("LDAPBindPassword"))
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
									  value.setTextContent(passField.getText());
									  System.out.println(value.getTagName()+" , "+value.getTextContent());
									  //System.out.println(value.getTextContent());
								  }
							  }
							  }
							  }
							  if(name.equalsIgnoreCase("WasFederatedBaseEntryDNRepository"))
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
										  //System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("LoginProperties"))
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
										  //j6 = value.getTextContent();
										  value.setTextContent(textField_6.getText());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
										  //System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("FederatedRepositoriesRealm"))
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
										  //j7 = value.getTextContent();
										  value.setTextContent(textField_7.getText());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
										  //System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("WasFederatedRepositoryId"))
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
										  //j8 = value.getTextContent();
										  value.setTextContent(textField_8.getText());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
										  //System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("WasFederatedBaseEntryDNRealm"))
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
										  //j9 = value.getTextContent();
										  value.setTextContent(textField_9.getText());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
										  //System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("AdminConsoleUser"))
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
										  value.setTextContent(textField_10.getText());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
										  //System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("groupMembershipName"))
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
										  //j11 = value.getTextContent();
										  value.setTextContent(textField_11.getText());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
										  //System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("groupMembershipScope"))
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
										  //j12 = value.getTextContent();
										  value.setTextContent(textField_12.getText());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
										  //System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("SetAsActiveUserReg"))
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
										  currentActive = res;
										  value.setTextContent(res);
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
										  //System.out.println(value.getTextContent());
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
										  value.setTextContent(textField_13.getText());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
										  //System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("SSLEnabled"))
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
										  SSLEnabled = res;
										  value.setTextContent(res);
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
										  //System.out.println(value.getTextContent());
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
        String LDAPServerUser = "";
        String ldapBindDN = textField_2.getText();
        String domainName = "default";
        String ldapType = "AD";
        outFW.write("set _wasVersion " + QUOTE + "9.0" + QUOTE + strEOL);//$NON-NLS-1$ 
		outFW.write("set _admConsoleUser " + QUOTE + textField_10.getText() + QUOTE+ strEOL);//$NON-NLS-1$ 
		outFW.write("set _setCurrent " + QUOTE + currentActive + QUOTE + strEOL);//$NON-NLS-1$ 
		outFW.write("set _ldapType " + QUOTE + ldapType + QUOTE+ strEOL);  //$NON-NLS-1$ 
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
		boolean turnoffSSLcerts = false;
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
       	}
       	catch(IOException ex) {
       		ex.printStackTrace();
       	}
    }
}

