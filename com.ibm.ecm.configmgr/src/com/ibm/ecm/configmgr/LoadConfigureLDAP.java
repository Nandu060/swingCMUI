package com.ibm.ecm.configmgr;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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

public class LoadConfigureLDAP {

	public String LDServerHost,LDServerPort,LDBindDN,LDBindPass,LDBaseDN,LDUserFilter,LDGrFilter,LDUserID,
	LDGrID,LDAdmin,Overwrite,ActiveUser,Script,SSLEnabled,LDAPDisplay,j16,
	j17,j18,LDAPUB,LDAPGB,LDUserNFilter,LDGroupNFilter,LDUserAttr,LDStaticAttr,j26,j27;
	public String BaseEntryDN, Login, FedRepo, WSFedRepoID, WSFedBaseDN,GrMemName, GrMemScope;
	public JPanel defaultPanel = new JPanel();
	private ConfigureLDAP_wasjdbcgeneralst conf;
	private ConfigureLDAP_wasjdbcgeneralfe conf2;
	private ConfigureLDAP_wlgeneral confgen;
	private File file;
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";
	
	public JPanel loadFromFile(File file)
	{
		System.out.println("File selected");
		this.file = file;
		try {
		PropertyFactory props = new PropertyFactory();
		props.XmlDoc(file.toString(), (String)null);
		Element doc1 = props.getDocElem();
		String imp = props.getConfigAttr(doc1, "implementorid"); // - working
		System.out.println("The imp is "+imp);
		CMUtil.implementorID = imp;
		int index = imp.indexOf(".");
		String a = imp.substring(index+1, imp.length());
		CMUtil.ldapServerType  = a;
		//String arr[] = CMUtil.implementorID.split("standard");
		//for(String a:arr)
		System.out.println("Directory server is :"+a);
		String[] names = props.getElementNamesByTagName(doc1, PROPERTY);
		if((CMUtil.appServer.equalsIgnoreCase("websphere"))||(CMUtil.appServer.startsWith("WebSphere")))
		{
			if(CMUtil.implementorID.startsWith("standalone"))
			{
				LDServerHost = props.getChildValueByName(doc1, PROPERTY, "LDAPServerHost", VALUE);
				LDServerPort = props.getChildValueByName(doc1, PROPERTY, "LDAPServerPort", VALUE);
				LDBindDN = props.getChildValueByName(doc1, PROPERTY, "LDAPBindDN", VALUE);
				LDBindPass = props.getChildValueByName(doc1, PROPERTY, "LDAPBindPassword", VALUE);
				LDBaseDN = props.getChildValueByName(doc1, PROPERTY, "LDAPBaseDN", VALUE);
				LDUserFilter = props.getChildValueByName(doc1, PROPERTY, "LDAPUserFilter", VALUE);
				LDGrFilter = props.getChildValueByName(doc1, PROPERTY, "LDAPGroupFilter", VALUE);
				LDUserID = props.getChildValueByName(doc1, PROPERTY, "LDAPUserIDMap", VALUE);
				LDGrID = props.getChildValueByName(doc1, PROPERTY, "LDAPGroupIDMap", VALUE);
				LDAdmin = props.getChildValueByName(doc1, PROPERTY, "AdminConsoleUser", VALUE);
				Overwrite = props.getChildValueByName(doc1, PROPERTY, "OverwriteExistingUserReg", VALUE);
				ActiveUser = props.getChildValueByName(doc1, PROPERTY, "SetAsActiveUserReg", VALUE);
				Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				SSLEnabled = props.getChildValueByName(doc1, PROPERTY, "SSLEnabled", VALUE);
				System.out.println("Diff value is "+LDUserID);
				conf = new ConfigureLDAP_wasjdbcgeneralst(LDServerHost, LDServerPort, LDBindDN, LDBindPass, LDBaseDN, LDUserFilter, LDGrFilter, LDUserID, 
						LDGrID, LDAdmin, Overwrite, ActiveUser, Script, SSLEnabled,file);
				return conf;
			}
			else if(CMUtil.implementorID.startsWith("federated"))
			{
				LDServerHost = props.getChildValueByName(doc1, PROPERTY, "LDAPServerHost", VALUE);
				LDServerPort = props.getChildValueByName(doc1, PROPERTY, "LDAPServerPort", VALUE);
				LDBindDN = props.getChildValueByName(doc1, PROPERTY, "LDAPBindDN", VALUE);
				LDBindPass = props.getChildValueByName(doc1, PROPERTY, "LDAPBindPassword", VALUE);
				BaseEntryDN = props.getChildValueByName(doc1, PROPERTY, "WasFederatedBaseEntryDNRepository", VALUE);
				Login = props.getChildValueByName(doc1, PROPERTY, "LoginProperties", VALUE);
				FedRepo = props.getChildValueByName(doc1, PROPERTY, "FederatedRepositoriesRealm", VALUE);
				WSFedRepoID = props.getChildValueByName(doc1, PROPERTY, "WasFederatedRepositoryId", VALUE);
				WSFedBaseDN = props.getChildValueByName(doc1, PROPERTY, "WasFederatedBaseEntryDNRealm", VALUE);
				LDAdmin = props.getChildValueByName(doc1, PROPERTY, "AdminConsoleUser", VALUE);
				GrMemName = props.getChildValueByName(doc1, PROPERTY, "groupMembershipName", VALUE);
				GrMemScope = props.getChildValueByName(doc1, PROPERTY, "groupMembershipScope", VALUE);
				ActiveUser = props.getChildValueByName(doc1, PROPERTY, "SetAsActiveUserReg", VALUE);
				Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				SSLEnabled = props.getChildValueByName(doc1, PROPERTY, "SSLEnabled", VALUE);
				System.out.println("Diff 2 value is "+GrMemName);
				conf2 = new ConfigureLDAP_wasjdbcgeneralfe(LDServerHost, LDServerPort, LDBindDN, LDBindPass, BaseEntryDN, Login, FedRepo, WSFedRepoID, WSFedBaseDN, LDAdmin, GrMemName, GrMemScope, ActiveUser, Script, SSLEnabled, file);
				return conf2;
			}
		}
		else if((CMUtil.appServer.equals("weblogic"))||(CMUtil.appServer.startsWith("WebLogic"))||(CMUtil.appServer.startsWith("Oracle")))
		{
			LDAPDisplay = props.getChildValueByName(doc1, PROPERTY, "LDAPDisplayName", VALUE);
			LDServerHost = props.getChildValueByName(doc1, PROPERTY, "LDAPServerHost", VALUE);
			LDServerPort = props.getChildValueByName(doc1, PROPERTY, "LDAPServerPort", VALUE);
			LDBindDN = props.getChildValueByName(doc1, PROPERTY, "LDAPBindDN", VALUE);
			LDBindPass = props.getChildValueByName(doc1, PROPERTY, "LDAPBindPassword", VALUE);
			LDAPUB = props.getChildValueByName(doc1, PROPERTY, "LDAPUserBaseDN", VALUE);
			LDAPGB = props.getChildValueByName(doc1, PROPERTY, "LDAPGroupBaseDN", VALUE);
			LDUserNFilter = props.getChildValueByName(doc1, PROPERTY, "LDAPUserFromNameFilter", VALUE);
			LDGroupNFilter = props.getChildValueByName(doc1, PROPERTY, "LDAPGroupFromNameFilter", VALUE);
			LDUserAttr = props.getChildValueByName(doc1, PROPERTY, "LDAPUsernameAttribute", VALUE);
			LDStaticAttr = props.getChildValueByName(doc1, PROPERTY, "LDAPStaticGroupAttribute", VALUE);
			Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
			SSLEnabled = props.getChildValueByName(doc1, PROPERTY, "SSLEnabled", VALUE);
			confgen = new ConfigureLDAP_wlgeneral(LDAPDisplay,LDServerHost,LDServerPort,LDBindDN,LDBindPass,LDAPUB,LDAPGB,LDUserNFilter,LDGroupNFilter,LDUserAttr,LDStaticAttr,Script,SSLEnabled,file);
			return confgen;
		}
	}
	catch(DOMException de) 
	{
		//de.printStackTrace(); 
		System.out.println("Empty fields present");
	}
	return defaultPanel;
	}
}
