package com.ibm.ecm.configmgr;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import java.awt.BorderLayout;
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

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

public class ConfigureGCDJDBC_wasoracletivolist extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passField;
	private JPasswordField passField_1;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	private JComboBox<String> comboBox;
	private JTextField textField_5;
	//private JTextField passField;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	public String DBFailover = "";
	//private String xmlFilePath = "C:\\xmlexport_import\\trialconfig.xml";
	//private ORAGCDDS gcd;

	/**
	 * Create the panel.
	 */
	public ConfigureGCDJDBC_wasoracletivolist(String j1,String j2,String j3,String j4,String j5,String j6,String j7,
			String j8,String j9,String j10,String j11,String j12,String j13,String j14,String j15,final File file ) {
		//setLayout(null);
		

		JLabel lblNewLabel = new JLabel("Configure Oracle GCD JDBC");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_1 = new JLabel("JDBC Data Source Name :");
		
		textField = new JTextField();
		textField.setToolTipText("jdbc datasource name");
		textField.setText(j1);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("JDBC XA Data Source Name:");
		
		textField_1 = new JTextField();
		textField_1.setText(j2);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Database Server name :");
		
		textField_2 = new JTextField();
		textField_2.setText(j3);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Database Port Number :");
		
		textField_3 = new JTextField();
		textField_3.setText(j4);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setText(j5);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Database Name :");
		
		chckbxNewCheckBox = new JCheckBox("Oracle Service Name Enabled");
		chckbxNewCheckBox.setSelected(Boolean.parseBoolean(j6));
		
		JLabel lblNewLabel_6 = new JLabel("Database Username :");
		
		textField_5 = new JTextField();
		textField_5.setText(j7);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Database Password :");
		
		passField = new JPasswordField();
		passField.setText(j8);
		passField.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("ODBC Jar :");
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"ODBC Jar"}));
		//comboBox.setSelectedItem(new String(j9));
		
		chckbxNewCheckBox_1 = new JCheckBox("Database Failover support Enabled");
		chckbxNewCheckBox_1.setSelected(Boolean.parseBoolean(j15));
		
		JLabel lblNewLabel_9 = new JLabel("Minimum Connections :");
		
		textField_7 = new JTextField();
		textField_7.setText(j10);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Maximum Connections : ");
		
		textField_8 = new JTextField();
		textField_8.setText(j11);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Unused Timeout (sec) :");
		
		textField_9 = new JTextField();
		textField_9.setText(j12);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Reap time (sec) :");
		
		textField_10 = new JTextField();
		textField_10.setText(j13);
		textField_10.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setText(j14);
		textField_11.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Script Directory :");
		
		btnNewButton = new JButton("Browse");
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
		
		btnNewButton_1 = new JButton("Test Database Connection");
		
		JSeparator separator_1 = new JSeparator();
		
		btnNewButton_2 = new JButton("Save");
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					  
					  DocumentBuilderFactory documentFactory =
				  DocumentBuilderFactory.newInstance();
				  
				  DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
				  
				  Document doc1 = documentBuilder.parse(file);
				  NodeList nd = doc1.getElementsByTagName("property");
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
								  value.setTextContent(textField.getText());
								  System.out.println(value.getTagName()+" , "+value.getTextContent());
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
								  value.setTextContent(textField_1.getText());
								  System.out.println(value.getTagName()+" , "+value.getTextContent());
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
								  value.setTextContent(textField_2.getText());
								  System.out.println(value.getTagName()+" , "+value.getTextContent());
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
								  value.setTextContent(textField_3.getText());
								  System.out.println(value.getTagName()+" , "+value.getTextContent());
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
								  value.setTextContent(textField_4.getText());
								  System.out.println(value.getTagName()+" , "+value.getTextContent());
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
								  value.setTextContent(textField_5.getText());
								  System.out.println(value.getTagName()+" , "+value.getTextContent());
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
								  value.setTextContent(passField.getText());
								  System.out.println(value.getTagName()+" , "+value.getTextContent());
							  }
						  }
						  }
						  if(name.equalsIgnoreCase("ODBC Jar"))
						  {
						  NodeList nd1 = property.getChildNodes();
						  for(int j=0;j<nd1.getLength();j++)
						  {
							  Node q = nd1.item(j);
							  if(q.getNodeType()== Node.ELEMENT_NODE)
							  {
								  Element value = (Element)q;
								  if(value.getTagName().equalsIgnoreCase("value"))
								  value.setTextContent(comboBox.getSelectedItem().toString());
								  System.out.println(value.getTagName()+" , "+value.getTextContent());
							  }
						  }
						  }
						  if(name.equalsIgnoreCase("Minimum connections"))
						  {
						  NodeList nd1 = property.getChildNodes();
						  for(int j=0;j<nd1.getLength();j++)
						  {
							  Node q = nd1.item(j);
							  if(q.getNodeType()== Node.ELEMENT_NODE)
							  {
								  Element value = (Element)q;
								  if(value.getTagName().equalsIgnoreCase("value"))
								  value.setTextContent(textField_7.getText());
								  System.out.println(value.getTagName()+" , "+value.getTextContent());
							  }
						  }
						  }
						  if(name.equalsIgnoreCase("Maximum connections"))
						  {
						  NodeList nd1 = property.getChildNodes();
						  for(int j=0;j<nd1.getLength();j++)
						  {
							  Node q = nd1.item(j);
							  if(q.getNodeType()== Node.ELEMENT_NODE)
							  {
								  Element value = (Element)q;
								  if(value.getTagName().equalsIgnoreCase("value"))
								  value.setTextContent(textField_8.getText());
								  System.out.println(value.getTagName()+" , "+value.getTextContent());
							  }
						  }
						  }
						  if(name.equalsIgnoreCase("Unused timeout (seconds)"))
						  {
						  NodeList nd1 = property.getChildNodes();
						  for(int j=0;j<nd1.getLength();j++)
						  {
							  Node q = nd1.item(j);
							  if(q.getNodeType()== Node.ELEMENT_NODE)
							  {
								  Element value = (Element)q;
								  if(value.getTagName().equalsIgnoreCase("value"))
								  value.setTextContent(textField_9.getText());
								  System.out.println(value.getTagName()+" , "+value.getTextContent());
							  }
						  }
						  }
						  if(name.equalsIgnoreCase("Reap time (seconds)"))
						  {
						  NodeList nd1 = property.getChildNodes();
						  for(int j=0;j<nd1.getLength();j++)
						  {
							  Node q = nd1.item(j);
							  if(q.getNodeType()== Node.ELEMENT_NODE)
							  {
								  Element value = (Element)q;
								  if(value.getTagName().equalsIgnoreCase("value"))
								  value.setTextContent(textField_10.getText());
								  System.out.println(value.getTagName()+" , "+value.getTextContent());
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
								  value.setTextContent(textField_11.getText());
								  System.out.println(value.getTagName()+" , "+value.getTextContent());
							  }
						  }
						  }
						  /*if(name.equalsIgnoreCase("tempDir"))
						  {
						  NodeList nd1 = property.getChildNodes();
						  for(int j=0;j<nd1.getLength();j++)
						  {
							  Node q = nd1.item(j);
							  if(q.getNodeType()== Node.ELEMENT_NODE)
							  {
								  Element value = (Element)q;
								  if(value.getTagName().equalsIgnoreCase("value"))
								  value.setTextContent(tempDir.getText());
								  System.out.println(value.getTagName()+" , "+value.getTextContent());
							  }
						  }
						  }*/
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
									  DBFailover = res;
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
				  DOMSource domSource = new DOMSource(doc1); 
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
		
		btnNewButton_3 = new JButton("Run Task");
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
					.addGap(28)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 459, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 760, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(chckbxNewCheckBox, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(passField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(chckbxNewCheckBox_1, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(114)
					.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_13, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(190)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 702, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(489)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_1))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_2))
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_3))
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_4))
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_5))
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxNewCheckBox, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_6))
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_7))
						.addComponent(passField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_8))
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxNewCheckBox_1))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_9))
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_10))
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_11))
						.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_12))
						.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_13))
						.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(20)
					.addComponent(btnNewButton_1)
					.addGap(24)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_3)))
		);
		setLayout(groupLayout);
	}
	public void runActionPerformed(ActionEvent e)
	{
		String console = exec();
    	//new NewJFrame(CMUtil.profileName, console).setVisible(true);
        //new LoginModules().setVisible(true);
    	ConsoleOP.appendText(console);
        //dispose();
        //getContentPane().setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    //Store the details in the tmp script and execute the tmp script
    public String exec() {
    	String script = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\scripts\\configureWSJDBC.tcl";
        String tempFile = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\tmp\\configurejdbcgcd.tcl";
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
	public void setScript(String script, String wsTclRuntime) {
    	
    	String QUOTE = "\"", strEOL = "\r\n";
    	String jdbcDesc = "\"The JDBC provider used for global configuration database or object store data sources.\"";
    	try {		
			
			
			FileReader inFR = new FileReader(script);
			BufferedReader inBR = new BufferedReader(inFR);
			FileWriter outFW = new FileWriter(wsTclRuntime);
			outFW.write("set _wasVersion 9" + strEOL);//$NON-NLS-1$ 
			outFW.write("set _cellName " + QUOTE + "NandanCMUIInstallDev1Node02Cell" + QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("set _aliasName " + QUOTE + this.textField_5.getText().trim().toString() + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _aliasId " + QUOTE + this.textField_5.getText().trim().toString() + QUOTE+ strEOL);
			outFW.write("set _aliasPassword " + QUOTE + this.passField.getText().toString() + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _jdbcProviderType " + QUOTE + " 4 "+ QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("set _jdbcProviderName " + QUOTE +" JDBC provider for Oracle"+ QUOTE + strEOL);//$NON-NLS-1$ 
			outFW.write("set _jdbcProviderDesc " + jdbcDesc + strEOL);//$NON-NLS-1$ 
			outFW.write("set _dsName " + QUOTE + this.textField.getText().trim().toString() + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _jndiName " + QUOTE + this.textField.getText().trim().toString() + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _dsXaName " + QUOTE + this.textField_1.getText().trim().toString() + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _jndiXaName " + QUOTE + this.textField_1.getText().trim().toString() + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _dbName " + QUOTE + this.textField_4.getText().trim().toString() + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _dbServerName " + QUOTE + this.textField_2.getText().trim().toString() + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _portNumber " + QUOTE + this.textField_3.getText().trim().toString() + QUOTE+ strEOL);//$NON-NLS-1$
			outFW.write("set _dbFailOver "+ QUOTE + DBFailover+QUOTE+ strEOL);//$NON-NLS-1$
			
			//changes for defect 3470 start
			outFW.write("set _odbcjarType " + QUOTE + "ojdbc8.jar" + QUOTE+ strEOL);//$NON-NLS-1$
			// changes for defect 3470 end
			
			//changes for defect 9654 start 
			outFW.write("set _mssqljarType " + QUOTE + "" + QUOTE+ strEOL);//$NON-NLS-1$
			//changes for defect 9654 end
			outFW.write("set _ClientRerouteEnable " + QUOTE + "false" + QUOTE+ strEOL);//$NON-NLS-1$
			outFW.write("set _retryIntervalForClientReroute " + QUOTE + "N/A" + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _maxRetriesForClientReroute " + QUOTE + "N/A" + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _clientRerouteAlternateServerName " + QUOTE + "N/A" + QUOTE+ strEOL);//$NON-NLS-1$
			outFW.write("set _clientRerouteAlternatePortNumber " + QUOTE + "N/A" + QUOTE+ strEOL);//$NON-NLS-1$
			
			outFW.write("set _zOsDbName " + QUOTE + "N/A" + QUOTE+ strEOL); //$NON-NLS-1$ 
			outFW.write("set _zOsDefaultTblspace " + QUOTE + "N/A" + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _zOsDefaultSTOGroup " + QUOTE + "N/A" + QUOTE+ strEOL); //$NON-NLS-1$ 
			outFW.write("set _zOsSTOGROUPOptions " + QUOTE + "N/A" + QUOTE+ strEOL);//$NON-NLS-1$ 

			//20484 settings
			//if (CMUtil.isCmuiClient){
			boolean sslEnabled = false;
			outFW.write("set _sslEnabled " + QUOTE + sslEnabled + QUOTE+ strEOL);//$NON-NLS-1$
			outFW.write("set _sslConn " + QUOTE + sslEnabled + QUOTE+ strEOL);//$NON-NLS-1$
			String connProps = "";
			
			String url = "jdbc:oracle:thin:@//ora19c1.fyre.ibm.com:1521/orainst1";
			outFW.write("set _oracleSSLURL "+ QUOTE + url + QUOTE + strEOL);
			//System.out.println("connProps = " + connProps);
			outFW.write("set _connProps " + QUOTE + connProps + QUOTE + strEOL);

			//20626
			outFW.write("set _minpoolsize " + Integer.parseInt(textField_7.getText()) + strEOL);
			outFW.write("set _maxpoolsize " + Integer.parseInt(textField_8.getText()) + strEOL);
			outFW.write("set _unusedtimeout " + Integer.parseInt(textField_9.getText()) + strEOL);
			outFW.write("set _reaptime " + Integer.parseInt(textField_10.getText()) + strEOL);
			
			String aLine;
			boolean turnoffSSLcerts = false;
			
			while ((aLine=inBR.readLine()) != null)
			{
				/*
				 * if (aLine.startsWith("set _turnoffSSLcerts") && !turnoffSSLcerts)
				 * //$NON-NLS-1$ outFW.write("set _turnoffSSLcerts \"true\""+ strEOL);
				 * //$NON-NLS-1$ else if (aLine.startsWith("set _dbVersion")) //$NON-NLS-1$
				 * outFW.write("set _dbVersion " + QUOTE + databaseVersion + QUOTE+
				 * strEOL);//$NON-NLS-1$ else if (aLine.startsWith("set _driverVersion"))
				 * //$NON-NLS-1$ outFW.write("set _driverVersion " + QUOTE + driverVersion +
				 * QUOTE+ strEOL);//$NON-NLS-1$ //changes for defect 3470 start else if
				 * (aLine.startsWith("set _oracleServiceNameURL") &&
				 * databaseServiceNameOracleSet) outFW.write("set _oracleServiceNameURL "+ QUOTE
				 * + url + QUOTE + strEOL); //changes for defect 3470 end //20686 else if
				 * ((aLine.contains("$minpoolsize")) || (aLine.contains("$maxpoolsize")) ||
				 * (aLine.contains("$unusedtimeout")) || (aLine.contains("$reaptime"))) { String
				 * str2replace = "", str2parse = ""; if (aLine.contains("$minpoolsize")) {
				 * str2replace = "$minpoolsize"; str2parse = minPoolSize; } else if
				 * (aLine.contains("$maxpoolsize")) { str2replace = "$maxpoolsize"; str2parse =
				 * maxPoolSize; } else if (aLine.contains("$unusedtimeout")) { str2replace =
				 * "$unusedtimeout"; str2parse = unusedTimeOut; } else if
				 * (aLine.contains("$reaptime")) { str2replace = "$reaptime"; str2parse =
				 * reapTime; } aLine = aLine.substring(0,aLine.indexOf(str2replace)) +
				 * Integer.parseInt(str2parse) + aLine.substring(aLine.indexOf(str2replace) +
				 * str2replace.length(), aLine.length()); //System.out.println(aLine);
				 * outFW.write(aLine+ strEOL); } else
				 */
					outFW.write(aLine+ strEOL);
			}
			
			inFR.close();
			inBR.close();
			outFW.close();
		} catch (Exception e) {
			e.getMessage();
		}
    	
    }
}
