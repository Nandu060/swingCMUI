package com.ibm.ecm.configmgr;

import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LoadGCD_JDBC {

	private File file;
	private String j1,j2,j3,j4,j5,j6,j7,j8,j9,j10,j11,j12,j13,j14,j15,
	j16,j17,j18,j19,j20,j21,j22,j23,j24,j25;
	//private int a;
	//private PanelWindow pan;
	private ConfigureGCDJDBC_wasoracletivolist pan;
	private ConfigureGCDJDBC_wlorrac orrac;
	private ConfigureGCDJDBC_wloracle wl_oracle;
	private ConfigureGCDJDBC_wljdbcdb2 panel2;
	public JPanel defaultPanel = new JPanel();
	public JPanel loadFromFile(File file)
	{
		System.out.println("File selected");
		System.out.println(CMUtil.appServer);
		this.file = file;
		try 
		{
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder(); 
			Document doc1 = documentBuilder.parse(file);
			
			NodeList configuration = doc1.getElementsByTagName("configuration");
			Node p1 = configuration.item(0); 
			if(p1.getNodeType()==Node.ELEMENT_NODE)
			{
				Element props = (Element) p1;
				String implementorID = props.getAttribute("implementorid");
				System.out.println(implementorID);
				CMUtil.implementorID = implementorID;
			}
			
			if((CMUtil.appServer.equalsIgnoreCase("websphere"))||(CMUtil.appServer.startsWith("WebSphere")))
			{
			
			
			NodeList nd = doc1.getElementsByTagName("property");
			System.out.println(nd.getLength());  
			  //System.out.println(jdbcDatabasesourceName.getText());
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
								  j1 = value.getTextContent();
								  System.out.println(value.getTextContent());
								  
							  }
						  }
					  }
					  //System.out.println(name);
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
								  j2 = value.getTextContent();
								  System.out.println(value.getTextContent());
							  }
							  //value.setTextContent(jdbcDatasourcexaname.getText());
							  //System.out.println(value.getTagName()+" , "+value.getTextContent());
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
								  j3 = value.getTextContent();
								  System.out.println(value.getTextContent());
							  }
							  //value.setTextContent(databaseServerName.getText());
							  //System.out.println(value.getTagName()+" , "+value.getTextContent());
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
							  j4 = value.getTextContent();
							  System.out.println(value.getTextContent());
						  }
						  //value.setTextContent(portNumber.getText());
						  //System.out.println(value.getTagName()+" , "+value.getTextContent());
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
								  j5 = value.getTextContent();
								  System.out.println(value.getTextContent());
							  }
							  //value.setTextContent(databaseName.getText());
							  //System.out.println(value.getTagName()+" , "+value.getTextContent());
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
								  j6 = value.getTextContent();
									/*
									 * String res = "false"; if(serviceNameEnabled.isSelected()) res = "true";
									 * value.setTextContent(res);
									 * System.out.println(value.getTagName()+" , "+value.getTextContent());
									 */
								  System.out.println(value.getTextContent());
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
								  j7 = value.getTextContent();
								  System.out.println(value.getTextContent());
							  }
							  //value.setTextContent(usernamedb.getText());
							  //System.out.println(value.getTagName()+" , "+value.getTextContent());
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
								  j8 = value.getTextContent();
								  System.out.println(value.getTextContent());
							  }
							  //value.setTextContent(dbpassword.getText());
							  //System.out.println(value.getTagName()+" , "+value.getTextContent());
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
							  {
								  j9 = value.getTextContent();
								  System.out.println(value.getTextContent());
							  }
							  //value.setTextContent(odbc_jar.getSelectedItem().toString());
							  //System.out.println(value.getTagName()+" , "+value.getTextContent());
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
							  {
								  j10 = value.getTextContent();
								  System.out.println(value.getTextContent());
							  }
							  //value.setTextContent(minimumConnection.getText());
							  //System.out.println(value.getTagName()+" , "+value.getTextContent());
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
							  {
								  j11 = value.getTextContent();
								  System.out.println(value.getTextContent());
							  }
							  //value.setTextContent(maximumConnection.getText());
							  //System.out.println(value.getTagName()+" , "+value.getTextContent());
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
							  {
								  j12 = value.getTextContent();
								  System.out.println(value.getTextContent());
							  }
							  //value.setTextContent(unusedTimeout.getText());
							  //System.out.println(value.getTagName()+" , "+value.getTextContent());
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
							  {
								  j13 = value.getTextContent();
								  System.out.println(value.getTextContent());
							  }
							  //value.setTextContent(reapTime.getText());
							  //System.out.println(value.getTagName()+" , "+value.getTextContent());
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
								  j14 = value.getTextContent();
								  System.out.println(value.getTextContent());
							  }
							  //value.setTextContent(script.getText());
							  //System.out.println(value.getTagName()+" , "+value.getTextContent());
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
								  j15 = value.getTextContent();
								  System.out.println(value.getTextContent());
									/*
									 * String res = "false"; if(dbfailoverenabled.isSelected()) res = "true";
									 * value.setTextContent(res);
									 * System.out.println(value.getTagName()+" , "+value.getTextContent());
									 */
							  }
						  }
					  }
					  }
				  }
			  }
		  
			pan = new ConfigureGCDJDBC_wasoracletivolist(j1,j2,j3,j4,j5,j6,j7,j8,j9,j10,j11,j12,j13,j14,j15,file);
			return pan;
			}
			else if((CMUtil.appServer.equals("weblogic"))||(CMUtil.appServer.startsWith("WebLogic")))
			{
				if(CMUtil.implementorID.equals("oraclerac"))
				{
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
									  {
										  j16 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j17 = value.getTextContent();
										  System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
							  if(name.equalsIgnoreCase("OracleRacUrl"))
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
										  j18 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j19 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j20 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j21 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j22 = value.getTextContent();
										  System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
						  }  
					  }
					  orrac = new ConfigureGCDJDBC_wlorrac(j16,j17,j18,j19,j20,j21,j22,file);
					  return orrac;
				}
				else if((CMUtil.implementorID.equals("db2"))||(CMUtil.implementorID.equals("mssql")))
				{
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
									  {
										  j16 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j17 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j18 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j19 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j20 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j21 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j22 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j23 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j24 = value.getTextContent();
										  System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
						  }  
					  }
					  panel2 = new ConfigureGCDJDBC_wljdbcdb2(j16,j17,j18,j19,j20,j21,j22,j23,j24,file);
					  return panel2;
				}
				else if(CMUtil.implementorID.equals("oracle"))
				{
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
									  {
										  j16 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j17 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j18 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j19 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j20 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j21 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j22 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j23 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j24 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j25 = value.getTextContent();
										  System.out.println(value.getTextContent());
									  }
								  }
							  }
							  }
						  }  
					  }
					  wl_oracle = new ConfigureGCDJDBC_wloracle(j16, j17, j18, j19, j20, j21, j22, j23, j24, j25,file);
					  return wl_oracle;
				}
				else
				{
					
				}
			}
		}
		  catch(DOMException |
				  ParserConfigurationException | SAXException | IOException de) {
				  de.printStackTrace(); 
				  }
		
		//pan = new PanelWindow(j1,j2,j3,j4,j5,j6,j7,j8,j9,j10,j11,j12,j13,j14,j15,file);
		//new NewFrame(pan).setVisible(true);
		
		//main1 = new MainFrame();
		//main1.add(pan,BorderLayout.CENTER);
		//initComponents();
		/*
		 * java.awt.EventQueue.invokeLater(new Runnable() { public void run() { new
		 * LoadProfileDet(1).setVisible(true); } });
		 */
		return defaultPanel;
	}
	
}
