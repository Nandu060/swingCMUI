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

public class LoadDeploy {

	public String j1,j2,j3,j4,j5,j6,j7,j8,j9,j10,j11,j12,j13,j14,j15,j16,j17,j18,j19;
	private File file;
	//private PanelWindow pan;
	public JPanel defaultPanel = new JPanel();
	public DeployApplication_wasjdbctivolist dep;
	public DeployApplication_wlgeneral depgen;
	//private MainFrame main1;
	
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
									  j1 = value.getTextContent();
									  System.out.println(value.getTextContent());
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
									  j2 = value.getTextContent();
									  System.out.println(value.getTextContent());
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
									  j3 = value.getTextContent();
									  System.out.println(value.getTextContent());
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
								  j4 = value.getTextContent();
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
									  j5 = value.getTextContent();
									  System.out.println(value.getTextContent());
								  }
							  }
						  }
						  }
						  if(name.equalsIgnoreCase("engineinit"))
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
									  System.out.println(value.getTextContent());
								  }
							  }
						  }
						  }
						  if(name.equalsIgnoreCase("clientdownload"))
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
							  }
						  }
						  }
						  if(name.equalsIgnoreCase("enginehealth"))
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
							  }
						  }
						  }
						  if(name.equalsIgnoreCase("cews"))
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
							  }
						  }
						  }
						  if(name.equalsIgnoreCase("acce"))
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
							  }
						  }
						  }
						  if(name.equalsIgnoreCase("pewsi"))
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
							  }
						  }
						  }
						  if(name.equalsIgnoreCase("engine"))
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
							  }
						  }
						  }
						  if(name.equalsIgnoreCase("enginebroker"))
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
									  j14 = value.getTextContent();
									  System.out.println(value.getTextContent());
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
									  j15 = value.getTextContent();
									  System.out.println(value.getTextContent());
								  }
							  }
						  }
						  }
					  }
				  }
				  dep = new DeployApplication_wasjdbctivolist(j1, j2, j3, j4, j5, j6, j7, j8, j9,j10,j11,j12,j13,j14,j15,file);
				return dep;
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
										  j16 = value.getTextContent();
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
										  j17 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j18 = value.getTextContent();
										  System.out.println(value.getTextContent());
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
										  j19 = value.getTextContent();
										  System.out.println(value.getTextContent());
									  }
								  }
							  }
						  }
					  }
				  }
						  
				}
			depgen = new DeployApplication_wlgeneral(j16, j17, j18, j19,file);
			return depgen;
		  }
		  catch(DOMException |
				  ParserConfigurationException | SAXException | IOException de) {
				  de.printStackTrace(); 
				  }
		
		//new NewFrame(pan).setVisible(true);
		//main1 = new MainFrame();
		//main1.add(pan,BorderLayout.CENTER);
		/*if(CMUtil.appServer.equals("websphere"))
		{
			dep = new DeployApplication_wasjdbctivolist(j1, j2, j3, j4, j5, j6, j7, j8, j9,j10,j11,j12,j13,j14,j15,file);
			return dep;
		}
		else if(CMUtil.appServer.equals("weblogic"))
		{
			depgen = new DeployApplication_wlgeneral(j16, j17, j18, j19,file);
			return depgen;
		}*/
		return defaultPanel;
	}
}
