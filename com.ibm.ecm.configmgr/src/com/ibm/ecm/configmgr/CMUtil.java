package com.ibm.ecm.configmgr;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ibm.Factory.PropertyFactory;

public class CMUtil {
	//
	public static String profileName="";
	public static boolean profileOpen = false;
	private static final String UNIX_EOL = "\n"; //$NON-NLS-1$
	private static final String WIN_EOL = "\r\n"; //$NON-NLS-1$
	private static final String WIN = "Win"; //$NON-NLS-1$
	private static final String OS_NAME = "os.name"; //$NON-NLS-1$
	public static boolean serverEarlier551 = false;
	public static String upgradeProfile = "";
	public static String upgradeAppServer = "";
	public static String appServer = "";
	public static String ldapRepoType="";
	public static String dbType = "";
	public static String deployMode = "";
	public static String wasVersion = "";
	public static String ldapServerType = "";
	public static String implementorID = "";
	public static String wlVersion = "";
	public static String appServerVersion = "";
	public static String appServerInstallFolder = "C:\\Program Files\\IBM\\WebSphere\\AppServer";
	public static String appServerAdminUser = "";
	public static String appServerAdminPassword = "";
	public static String appServerSOAP = "";
	public static String appServerHostName = "localhost";
	public static String appServerTimeout = "180";
	public static String appServerProfile = "C:\\Program Files\\IBM\\WebSphere\\AppServer\\profiles\\AppSrv02";
	public static String appServerCell = "";
	public static String turnOffSSL = "";
	public static String securityDomain = "";
	public static String SSLEnabled = "";
	public static String appServerDomain = "";
	public static String targetName = "";
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";
	
	
	
	public static boolean isWinOS()
	{
	    boolean isWinOS = false;
	    String osName = System.getProperty(OS_NAME);
	    if (osName.indexOf(WIN) >= 0)
	    {
	        isWinOS = true;
	    }
		return isWinOS;
	}
	
	public static String getPlatformEOL()
	{
		if (isWinOS())
			return WIN_EOL;
		else
			return UNIX_EOL;
	}
   
    public static void loadAppServer(String file)
    {
    	PropertyFactory props = new PropertyFactory();
		props.XmlDoc(file.toString(), (String)null);
		Element doc1 = props.getDocElem();
		String imp = props.getConfigAttr(doc1, "implementorid"); // - working
		System.out.println("The imp is "+imp);
		appServer = imp;
		appServerVersion = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerVersion", VALUE);
		appServerInstallFolder = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerInstallationFolder", VALUE);
		appServerAdminUser = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerAdminUsername", VALUE);
		appServerAdminPassword = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerAdminPassword", VALUE);
		appServerSOAP = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerSoapPort", VALUE);
		appServerHostName = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerHostName", VALUE);
		appServerTimeout = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerTransactionTimeout", VALUE);
		if((appServer.equalsIgnoreCase("websphere"))||(appServer.startsWith("WebSphere")))
		{
			appServerProfile = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerProfileFolder", VALUE);
			appServerCell = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerCell", VALUE);
			turnOffSSL = props.getChildValueByName(doc1, PROPERTY, "TurnOffSSLCerticates", VALUE);
			securityDomain = props.getChildValueByName(doc1, PROPERTY, "SecurityDomain", VALUE);
		}
		else if((CMUtil.appServer.equals("weblogic"))||(CMUtil.appServer.startsWith("WebLogic")))
		{
			SSLEnabled = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerSSLEnabled", VALUE);
			appServerDomain = props.getChildValueByName(doc1, PROPERTY, "ApplicationServerDomainDirectory", VALUE);
			targetName = props.getChildValueByName(doc1, PROPERTY, "TargetName", VALUE);
		}
		else
		{
			System.out.println("Invalid");
		}
    }
}
