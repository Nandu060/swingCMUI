package com.ibm.ecm.configmgr;
import java.awt.BorderLayout;
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

public class LoadConfigureLDAP {

	public String LDServerHost,LDServerPort,LDBindDN,LDBindPass,LDBaseDN,LDUserFilter,LDGrFilter,LDUserID,
	LDGrID,LDAdmin,Overwrite,ActiveUser,Script,SSLEnabled,j15,j16,
	j17,j18,j19,j20,j21,j22,j23,j24,j25,j26,j27;
	public String BaseEntryDN, Login, FedRepo, WSFedRepoID, WSFedBaseDN,GrMemName, GrMemScope;
	
	//private MainFrame main1;
	public JPanel defaultPanel = new JPanel();
	private ConfigureLDAP_wasjdbctivolist conf;
	private ConfigureLDAP_wasjdbctivolife conf2;
	private ConfigureLDAP_wlgeneral confgen;
	private File file;
	
	public JPanel loadFromFile(File file)
	{
		System.out.println("File selected");
		this.file = file;
		try 
		{
			System.out.println(file);
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder(); 
			Document doc2 = documentBuilder.parse(file);
			
			NodeList configuration = doc2.getElementsByTagName("configuration");
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
			if(CMUtil.implementorID.startsWith("standard"))
			{
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
								  LDServerHost = value.getTextContent();
								  System.out.println(value.getTextContent());
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
								  LDServerPort = value.getTextContent();
								  System.out.println(value.getTextContent());
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
								  LDBindDN = value.getTextContent();
								  System.out.println(value.getTextContent());
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
							  LDBindPass = value.getTextContent();
							  System.out.println(value.getTextContent());
						  }
					  }
					  }
					  }
					  if(name.equalsIgnoreCase("LDAPBaseDN"))
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
								  LDBaseDN = value.getTextContent();
								  System.out.println(value.getTextContent());
							  }
						  }
					  }
					  }
					  if(name.equalsIgnoreCase("LDAPUserFilter"))
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
								  LDUserFilter = value.getTextContent();
								  System.out.println(value.getTextContent());
							  }
						  }
					  }
					  }
					  if(name.equalsIgnoreCase("LDAPGroupFilter"))
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
								  LDGrFilter = value.getTextContent();
								  System.out.println(value.getTextContent());
							  }
						  }
					  }
					  }
					  if(name.equalsIgnoreCase("LDAPUserIDMap"))
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
								  LDUserID = value.getTextContent();
								  System.out.println(value.getTextContent());
							  }
						  }
					  }
					  }
					  if(name.equalsIgnoreCase("LDAPGroupIDMap"))
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
								  LDGrID = value.getTextContent();
								  System.out.println(value.getTextContent());
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
								  LDAdmin = value.getTextContent();
								  System.out.println(value.getTextContent());
							  }
						  }
					  }
					  }
					  if(name.equalsIgnoreCase("OverwriteExistingUserReg"))
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
								  Overwrite = value.getTextContent();
								  System.out.println(value.getTextContent());
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
								  ActiveUser = value.getTextContent();
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
								  Script = value.getTextContent();
								  System.out.println(value.getTextContent());
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
								  SSLEnabled = value.getTextContent();
								  System.out.println(value.getTextContent());
							  }
						  }
					  }
					  }
				  }
			  }
			  conf = new ConfigureLDAP_wasjdbctivolist(LDServerHost, LDServerPort, LDBindDN, LDBindPass, LDBaseDN, LDUserFilter, LDGrFilter, LDUserID, 
						LDGrID, LDAdmin, Overwrite, ActiveUser, Script, SSLEnabled,file);
				return conf;
		  }
			else if(CMUtil.implementorID.startsWith("federated"))
			{
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
									  LDServerHost = value.getTextContent();
									  System.out.println(value.getTextContent());
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
									  LDServerPort = value.getTextContent();
									  System.out.println(value.getTextContent());
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
									  LDBindDN = value.getTextContent();
									  System.out.println(value.getTextContent());
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
								  LDBindPass = value.getTextContent();
								  System.out.println(value.getTextContent());
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
									  BaseEntryDN = value.getTextContent();
									  System.out.println(value.getTextContent());
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
									  Login = value.getTextContent();
									  System.out.println(value.getTextContent());
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
									  FedRepo = value.getTextContent();
									  System.out.println(value.getTextContent());
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
									  WSFedRepoID = value.getTextContent();
									  System.out.println(value.getTextContent());
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
									  WSFedBaseDN = value.getTextContent();
									  System.out.println(value.getTextContent());
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
									  LDAdmin = value.getTextContent();
									  System.out.println(value.getTextContent());
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
									  GrMemName = value.getTextContent();
									  System.out.println(value.getTextContent());
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
									  GrMemScope = value.getTextContent();
									  System.out.println(value.getTextContent());
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
									  ActiveUser = value.getTextContent();
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
									  Script = value.getTextContent();
									  System.out.println(value.getTextContent());
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
									  SSLEnabled = value.getTextContent();
									  System.out.println(value.getTextContent());
								  }
							  }
						  }
						  }
					  }
				  }
				conf2 = new ConfigureLDAP_wasjdbctivolife(LDServerHost, LDServerPort, LDBindDN, LDBindPass, BaseEntryDN, Login, FedRepo, WSFedRepoID, WSFedBaseDN, LDAdmin, GrMemName, GrMemScope, ActiveUser, Script, SSLEnabled, file);
				return conf2;
			}
			}
			else if((CMUtil.appServer.equals("weblogic"))||CMUtil.appServer.startsWith("WebLogic"))
			{
				NodeList nd = doc2.getElementsByTagName("property");
				System.out.println(nd.getLength());  
				  for(int i=0;i<nd.getLength();i++)
				  {
					  Node p = nd.item(i);
					  if(p.getNodeType() == Node.ELEMENT_NODE)
					  {
						  Element property = (Element)p;
						  String name = property.getAttribute("name");
						  if(name.equalsIgnoreCase("LDAPDisplayName"))
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
								  }
							  }
						  }
						  }
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
									  j16 = value.getTextContent();
									  System.out.println(value.getTextContent());
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
									  j17 = value.getTextContent();
									  System.out.println(value.getTextContent());
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
									  j18 = value.getTextContent();
									  System.out.println(value.getTextContent());
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
									  j19 = value.getTextContent();
									  System.out.println(value.getTextContent());
								  }
							  }
						  }
						  }
						  if(name.equalsIgnoreCase("LDAPUserBaseDN"))
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
						  if(name.equalsIgnoreCase("LDAPGroupBaseDN"))
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
						  if(name.equalsIgnoreCase("LDAPUserFromNameFilter"))
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
						  if(name.equalsIgnoreCase("LDAPGroupFromNameFilter"))
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
						  if(name.equalsIgnoreCase("LDAPUsernameAttribute"))
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
						  if(name.equalsIgnoreCase("LDAPStaticGroupAttribute"))
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
									  j26 = value.getTextContent();
									  System.out.println(value.getTextContent());
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
									  j27 = value.getTextContent();
									  System.out.println(value.getTextContent());
								  }
							  }
						  }
						  }
					  }
				  }
			  confgen = new ConfigureLDAP_wlgeneral(j15,j16,j17,j18,j19,j20,j21,j22,j23,j24,j25,j26,j27,file);
				  return confgen;
			}
		}
		  catch(DOMException |
				  ParserConfigurationException | SAXException | IOException de) {
				  de.printStackTrace(); 
				  }
		//pan = new PanelWindow(j1,j2,j3,j4,j5,j6,j7,j8,j9,j10,j11,j12,j13,j14,j15,j16,file);
		
		//main1 = new MainFrame();
		//new NewFrame(pan).setVisible(true);
		//new1.add(pan,BorderLayout.CENTER);
		//main1.add(pan,BorderLayout.CENTER);
		return defaultPanel;
	}
}
