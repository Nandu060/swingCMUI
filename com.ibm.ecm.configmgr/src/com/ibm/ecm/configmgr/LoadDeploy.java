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

import com.ibm.Factory.PropertyFactory;

public class LoadDeploy {

	public String AppName,AppServerNode,AppServerName,AppServerVirtualHost,AppEdition,Script
	,clientdownload,enginehealth,cews,acce,pewsi,engine,enginebroker,JNDIName,JNDIXAName;
	private File file;
	public JPanel defaultPanel = new JPanel();
	public DeployApplication_wasgeneralst dep;
	public DeployApplication_wasgeneralnet depnet;
	public DeployApplication_wasgeneralcl depcl;
	public DeployApplication_wlgeneral depgen;
	private String AppServerCluster;
	private String engineinit;
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";
	
	public JPanel loadFromFile(File file)
	{
		System.out.println("File selected");
		this.file = file;
		PropertyFactory props = new PropertyFactory();
		props.XmlDoc(file.toString(), (String)null);
		Element doc1 = props.getDocElem();
		String imp = props.getConfigAttr(doc1, "implementorid"); // - working
		System.out.println("The imp is "+imp);
		CMUtil.implementorID = imp;
		String[] names = props.getElementNamesByTagName(doc1, PROPERTY);
		//for(int i=0;i<names.length;i++)
		if((CMUtil.appServer.equalsIgnoreCase("websphere"))||(CMUtil.appServer.startsWith("WebSphere")))
		{
			if(CMUtil.implementorID.equals("standard"))
			{
				AppName = props.getChildValueByName(doc1, PROPERTY, "ApplicationName", VALUE);
				AppServerNode = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerNode", VALUE);
				AppServerName = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerName", VALUE);
				AppServerVirtualHost = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerVirtualHost", VALUE);
				Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				engineinit = props.getChildValueByName(doc1, PROPERTY, "engineinit", VALUE);
				clientdownload = props.getChildValueByName(doc1, PROPERTY, "clientdownload", VALUE);
				enginehealth = props.getChildValueByName(doc1, PROPERTY, "enginehealth", VALUE);
				cews = props.getChildValueByName(doc1, PROPERTY, "cews", VALUE);
				acce = props.getChildValueByName(doc1, PROPERTY, "acce", VALUE);
				pewsi = props.getChildValueByName(doc1, PROPERTY, "pewsi", VALUE);
				engine = props.getChildValueByName(doc1, PROPERTY, "engine", VALUE);
				enginebroker = props.getChildValueByName(doc1, PROPERTY, "enginebroker", VALUE);
				JNDIName = props.getChildValueByName(doc1, PROPERTY, "JNDIName", VALUE);
				JNDIXAName = props.getChildValueByName(doc1, PROPERTY, "JNDIXAName", VALUE);
				System.out.println("New "+enginebroker);
				dep = new DeployApplication_wasgeneralst(AppName, AppServerNode, AppServerName, AppServerVirtualHost, Script, 
						engineinit, clientdownload, enginehealth, cews,acce,pewsi,engine,enginebroker,JNDIName,JNDIXAName,file);
				return dep;
			}
			else if(CMUtil.implementorID.equals("nd"))
			{
				AppName = props.getChildValueByName(doc1, PROPERTY, "ApplicationName", VALUE);
				AppServerNode = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerNode", VALUE);
				AppServerName = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerName", VALUE);
				AppServerVirtualHost = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerVirtualHost", VALUE);
				AppEdition = props.getChildValueByName(doc1, PROPERTY, "AppEdition", VALUE);
				Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				clientdownload = props.getChildValueByName(doc1, PROPERTY, "tempDir", VALUE);// to be removed
				JNDIName = props.getChildValueByName(doc1, PROPERTY, "JNDIName", VALUE);
				JNDIXAName = props.getChildValueByName(doc1, PROPERTY, "JNDIXAName", VALUE);
				depnet = new DeployApplication_wasgeneralnet(AppName, AppServerNode, AppServerName, AppServerVirtualHost, AppEdition, Script, clientdownload, JNDIName, JNDIXAName, file);
				return depnet;
			}
			else if(CMUtil.implementorID.equals("cluster"))
			{
				AppName = props.getChildValueByName(doc1, PROPERTY, "ApplicationName", VALUE);
				AppServerCluster = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerClusterName", VALUE);
				AppServerVirtualHost = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerVirtualHost", VALUE);
				AppEdition = props.getChildValueByName(doc1, PROPERTY, "AppEdition", VALUE);
				Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				clientdownload = props.getChildValueByName(doc1, PROPERTY, "tempDir", VALUE);//to be removed
				JNDIName = props.getChildValueByName(doc1, PROPERTY, "JNDIName", VALUE);
				JNDIXAName = props.getChildValueByName(doc1, PROPERTY, "JNDIXAName", VALUE);
				depcl = new DeployApplication_wasgeneralcl(AppName,AppServerCluster,AppServerVirtualHost,AppEdition,Script,clientdownload,JNDIName,JNDIXAName,file);
				return depcl;
			}
		}
		else if((CMUtil.appServer.equals("weblogic"))||(CMUtil.appServer.startsWith("WebLogic"))||(CMUtil.appServer.startsWith("Oracle")))
		{
			AppName = props.getChildValueByName(doc1, PROPERTY, "ApplicationName", VALUE);
			Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
			JNDIName = props.getChildValueByName(doc1, PROPERTY, "JNDIName", VALUE);
			JNDIXAName = props.getChildValueByName(doc1, PROPERTY, "JNDIXAName", VALUE);
			System.out.println("Old is "+JNDIName);	  
			depgen = new DeployApplication_wlgeneral(AppName, Script, JNDIName, JNDIXAName,file);
			return depgen;
		}
		return defaultPanel;
	}
}
