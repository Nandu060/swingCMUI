package com.ibm.ecm.configmgr;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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


public class DeployApplication_wasjdbctivolinet extends JPanel {
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

	/**
	 * Create the panel.
	 */
	/*public DeployApplication() {
		
	}
	*/
	public DeployApplication_wasjdbctivolinet(String j1,String j2,String j3,String j4,String j5,String j6
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
		comboBox_3.setModel(new DefaultComboBoxModel<>(new String[] {"a","b"}));
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
		        profPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
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
		        profPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
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
										  //j1 = value.getTextContent();
										  value.setTextContent(textField.getText());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
										  //System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("ApplicationServerNode"))
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
										  //j2 = value.getTextContent();
										  value.setTextContent(comboBox_1.getSelectedItem().toString());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
										  //System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("ApplicationServerName"))
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
										  //j3 = value.getTextContent();
										  value.setTextContent(comboBox_2.getSelectedItem().toString());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
										  //System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("ApplicationServerVirtualHost"))
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
									  //j4 = value.getTextContent();
									  value.setTextContent(comboBox_3.getSelectedItem().toString());
									  System.out.println(value.getTagName()+" , "+value.getTextContent());
									  //System.out.println(value.getTextContent());
								  }
							  }
							  }
							  }
							  if(name.equalsIgnoreCase("AppEdition"))
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
										  //j5 = value.getTextContent();
										  value.setTextContent(textField_1.getText());
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
										  //j6 = value.getTextContent()
										  value.setTextContent(textField_2.getText());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
										  //System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("tempDir"))
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
										  value.setTextContent(textField_3.getText());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
										  //System.out.println(value.getTextContent());
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
										  //j8 = value.getTextContent();
										  value.setTextContent(textField_4.getText());
										  System.out.println(value.getTagName()+" , "+value.getTextContent());
										  //System.out.println(value.getTextContent());
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
										  //j9 = value.getTextContent();
										  value.setTextContent(textField_5.getText());
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
		
		JButton btnNewButton_3 = new JButton("Run task");
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
}