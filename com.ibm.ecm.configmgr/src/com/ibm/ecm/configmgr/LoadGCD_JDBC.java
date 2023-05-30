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

import com.ibm.Factory.PropertyFactory;

public class LoadGCD_JDBC {

	private File file;
	private String JDBCDSName,JDBCDSXAName,DBServer,DBPort,DBName,ServiceName,DBUserName,DBPassword,Jar,
	MinConn,MaxConn,UnusedTime,ReapTime,Script,DBFailOver;
	private ConfigureGCDJDBC_wasoracle ora;
	private ConfigureGCDJDBC_wassql sql;
	private ConfigureGCDJDBC_wlorrac orrac;
	private ConfigureGCDJDBC_wloracle wl_oracle;
	private ConfigureGCDJDBC_wlsqldb2 panel2;
	private ConfigureGCDJDBC_wasdb2 wasdb2;
	private ConfigureGCDJDBC_wasorrac wasorr;
	private ConfigureGCDJDBC_wasorssl wasors;
	private ConfigureGCDJDBC_wasdb2pshadr wasph;
	private ConfigureGCDJDBC_wasdb2zos waszos;
	public JPanel defaultPanel = new JPanel();
	private String OrRacUrl;
	private String DBService;
	private String OrRacEnt;
	private String OrRacDel;
	private String KStPath;
	private String KStType;
	private String SSLKStPass;
	private String JKSKStPath;
	private String JKSSSLKStPass;
	private String RetryInterval;
	private String MaxRetries;
	private String ServerPortClient;
	private String zOSInstance;
	private String zOSDefault;
	private String zOSGroup;
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";
	
	public JPanel loadFromFile(File file)
	{
		System.out.println("File selected");
		System.out.println(CMUtil.appServer);
		this.file = file;
		try 
		{
		PropertyFactory props = new PropertyFactory();
		props.XmlDoc(file.toString(), (String)null);
		Element doc1 = props.getDocElem();
		String imp = props.getConfigAttr(doc1, "implementorid"); // - working
		System.out.println("The imp is "+imp);
		CMUtil.implementorID = imp;
		String[] names = props.getElementNamesByTagName(doc1, PROPERTY);
		if((CMUtil.appServer.equalsIgnoreCase("websphere"))||(CMUtil.appServer.startsWith("WebSphere")))
		{
			if(CMUtil.implementorID.equals("oracle"))
			{
				JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				DBServer = props.getChildValueByName(doc1, PROPERTY, "DatabaseServerName", VALUE);
				DBPort = props.getChildValueByName(doc1, PROPERTY, "DatabasePortNumber", VALUE);
				DBName = props.getChildValueByName(doc1, PROPERTY, "DatabaseName", VALUE);
				ServiceName = props.getChildValueByName(doc1, PROPERTY, "ServiceNameEnabled", VALUE);
				DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				Jar = props.getChildValueByName(doc1, PROPERTY, "OJDBC Jar", VALUE);
				MinConn = props.getChildValueByName(doc1, PROPERTY, "Minimum connections", VALUE);
				MaxConn = props.getChildValueByName(doc1, PROPERTY, "Maximum connections", VALUE);
				UnusedTime = props.getChildValueByName(doc1, PROPERTY, "Unused timeout (seconds)", VALUE);
				ReapTime = props.getChildValueByName(doc1, PROPERTY, "Reap time (seconds)", VALUE);
				Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				DBFailOver = props.getChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE);
				ora = new ConfigureGCDJDBC_wasoracle(JDBCDSName,JDBCDSXAName,DBServer,DBPort,DBName,ServiceName,DBUserName,DBPassword,Jar,MinConn,MaxConn,UnusedTime,ReapTime,Script,DBFailOver,file);
				return ora;
			}
			else if(CMUtil.implementorID.equals("mssql"))
			{
				JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				DBServer = props.getChildValueByName(doc1, PROPERTY, "DatabaseServerName", VALUE);
				DBPort = props.getChildValueByName(doc1, PROPERTY, "DatabasePortNumber", VALUE);
				DBName = props.getChildValueByName(doc1, PROPERTY, "DatabaseName", VALUE);
				DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				Jar = props.getChildValueByName(doc1, PROPERTY, "MSSQL Jar", VALUE);
				MinConn = props.getChildValueByName(doc1, PROPERTY, "Minimum connections", VALUE);
				MaxConn = props.getChildValueByName(doc1, PROPERTY, "Maximum connections", VALUE);
				UnusedTime = props.getChildValueByName(doc1, PROPERTY, "Unused timeout (seconds)", VALUE);
				ReapTime = props.getChildValueByName(doc1, PROPERTY, "Reap time (seconds)", VALUE);
				Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				DBFailOver = props.getChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE);
				sql = new ConfigureGCDJDBC_wassql(JDBCDSName,JDBCDSXAName,DBServer,DBPort,DBName,DBUserName,DBPassword,Jar,MinConn,MaxConn,UnusedTime,ReapTime,Script,DBFailOver,file);
				return sql;
			}
			else if(CMUtil.implementorID.equals("oraclerac"))
			{
				JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				OrRacUrl = props.getChildValueByName(doc1, PROPERTY, "OracleRacUrl", VALUE);
				DBService = props.getChildValueByName(doc1, PROPERTY, "DatabaseServiceName", VALUE);
				OrRacEnt = props.getChildValueByName(doc1, PROPERTY, "OracleRacRetries", VALUE);
				OrRacDel = props.getChildValueByName(doc1, PROPERTY, "OracleRacDelay", VALUE);
				DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				Jar = props.getChildValueByName(doc1, PROPERTY, "OJDBC Jar", VALUE);
				MinConn = props.getChildValueByName(doc1, PROPERTY, "Minimum connections", VALUE);
				MaxConn = props.getChildValueByName(doc1, PROPERTY, "Maximum connections", VALUE);
				UnusedTime = props.getChildValueByName(doc1, PROPERTY, "Unused timeout (seconds)", VALUE);
				ReapTime = props.getChildValueByName(doc1, PROPERTY, "Reap time (seconds)", VALUE);
				Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				DBFailOver = props.getChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE);
				wasorr = new ConfigureGCDJDBC_wasorrac(JDBCDSName,JDBCDSXAName,OrRacUrl,DBService,OrRacEnt,OrRacDel,DBUserName,DBPassword,Jar,MinConn,MaxConn,UnusedTime,ReapTime,Script,DBFailOver,file);
				return wasorr;
			}
			else if(CMUtil.implementorID.equals("oraclessl"))
			{
				JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				DBServer = props.getChildValueByName(doc1, PROPERTY, "DatabaseServerName", VALUE);
				DBPort = props.getChildValueByName(doc1, PROPERTY, "DatabasePortNumber", VALUE);
				DBName = props.getChildValueByName(doc1, PROPERTY, "DatabaseName", VALUE);
				ServiceName = props.getChildValueByName(doc1, PROPERTY, "ServiceNameEnabled", VALUE);
				DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				Jar = props.getChildValueByName(doc1, PROPERTY, "OJDBC Jar", VALUE);
				KStPath = props.getChildValueByName(doc1, PROPERTY, "KeyStorePath", VALUE);
				KStType = props.getChildValueByName(doc1, PROPERTY, "KeyStoreType", VALUE);
				SSLKStPass = props.getChildValueByName(doc1, PROPERTY, "SSLKeyStorePassword", VALUE);
				JKSKStPath = props.getChildValueByName(doc1, PROPERTY, "JKSKeyStorePath(JKS)", VALUE);
				JKSSSLKStPass = props.getChildValueByName(doc1, PROPERTY, "JKSSSLKeyStorePassword(JKS)", VALUE);
				MinConn = props.getChildValueByName(doc1, PROPERTY, "Minimum connections", VALUE);
				MaxConn = props.getChildValueByName(doc1, PROPERTY, "Maximum connections", VALUE);
				UnusedTime = props.getChildValueByName(doc1, PROPERTY, "Unused timeout (seconds)", VALUE);
				ReapTime = props.getChildValueByName(doc1, PROPERTY, "Reap time (seconds)", VALUE);
				Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				DBFailOver = props.getChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE);
				wasors = new ConfigureGCDJDBC_wasorssl(JDBCDSName,JDBCDSXAName,DBServer,DBPort,DBName,ServiceName,DBUserName,DBPassword,Jar,KStPath,KStType,SSLKStPass,JKSKStPath,JKSSSLKStPass,MinConn,MaxConn,UnusedTime,ReapTime,Script,DBFailOver,file);
				return wasors;
			}
			else if(CMUtil.implementorID.equals("db2"))
			{
				JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				DBServer = props.getChildValueByName(doc1, PROPERTY, "DatabaseServerName", VALUE);
				DBPort = props.getChildValueByName(doc1, PROPERTY, "DatabasePortNumber", VALUE);
				DBName = props.getChildValueByName(doc1, PROPERTY, "DatabaseName", VALUE);
				DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				MinConn = props.getChildValueByName(doc1, PROPERTY, "Minimum connections", VALUE);
				MaxConn = props.getChildValueByName(doc1, PROPERTY, "Maximum connections", VALUE);
				UnusedTime = props.getChildValueByName(doc1, PROPERTY, "Unused timeout (seconds)", VALUE);
				ReapTime = props.getChildValueByName(doc1, PROPERTY, "Reap time (seconds)", VALUE);
				Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				DBFailOver = props.getChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE);
				wasdb2 = new ConfigureGCDJDBC_wasdb2(JDBCDSName,JDBCDSXAName,DBServer,DBPort,DBName,DBUserName,DBPassword,MinConn,MaxConn,UnusedTime,ReapTime,Script,DBFailOver,file);
				return wasdb2;
			}
			else if((CMUtil.implementorID.equals("db2ps")||(CMUtil.implementorID.equals("db2hadr"))))
			{
				JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				DBServer = props.getChildValueByName(doc1, PROPERTY, "DatabaseServerName", VALUE);
				DBPort = props.getChildValueByName(doc1, PROPERTY, "DatabasePortNumber", VALUE);
				DBName = props.getChildValueByName(doc1, PROPERTY, "DatabaseName", VALUE);
				DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				RetryInterval = props.getChildValueByName(doc1, PROPERTY, "retryIntervalForClientReroute", VALUE);
				MaxRetries = props.getChildValueByName(doc1, PROPERTY, "maxRetriesForClientReroute", VALUE);
				ServerPortClient = props.getChildValueByName(doc1, PROPERTY, "ServerPortClientReroute", VALUE);
				MinConn = props.getChildValueByName(doc1, PROPERTY, "Minimum connections", VALUE);
				MaxConn = props.getChildValueByName(doc1, PROPERTY, "Maximum connections", VALUE);
				UnusedTime = props.getChildValueByName(doc1, PROPERTY, "Unused timeout (seconds)", VALUE);
				ReapTime = props.getChildValueByName(doc1, PROPERTY, "Reap time (seconds)", VALUE);
				Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				System.out.println("New is "+Script);
				wasph = new ConfigureGCDJDBC_wasdb2pshadr(JDBCDSName,JDBCDSXAName,DBServer,DBPort,DBName,DBUserName,DBPassword,RetryInterval,MaxRetries,ServerPortClient,MinConn,MaxConn,UnusedTime,ReapTime,Script,file);
				return wasph;
			}
			else if(CMUtil.implementorID.equals("db2zos"))
			{
				JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				DBServer = props.getChildValueByName(doc1, PROPERTY, "DatabaseServerName", VALUE);
				DBPort = props.getChildValueByName(doc1, PROPERTY, "DatabasePortNumber", VALUE);
				zOSInstance = props.getChildValueByName(doc1, PROPERTY, "zOSInstanceName", VALUE);
				DBName = props.getChildValueByName(doc1, PROPERTY, "DatabaseName", VALUE);
				DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				zOSDefault = props.getChildValueByName(doc1, PROPERTY, "zOSDefaultSTOGROUP", VALUE);
				zOSGroup = props.getChildValueByName(doc1, PROPERTY, "zOSSTOGROUPOptions", VALUE);
				MinConn = props.getChildValueByName(doc1, PROPERTY, "Minimum connections", VALUE);
				MaxConn = props.getChildValueByName(doc1, PROPERTY, "Maximum connections", VALUE);
				UnusedTime = props.getChildValueByName(doc1, PROPERTY, "Unused timeout (seconds)", VALUE);
				ReapTime = props.getChildValueByName(doc1, PROPERTY, "Reap time (seconds)", VALUE);
				Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				DBFailOver = props.getChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE);
				waszos = new ConfigureGCDJDBC_wasdb2zos(JDBCDSName,JDBCDSXAName,DBServer,DBPort,zOSInstance,DBName,DBUserName,DBPassword,zOSDefault,zOSGroup,MinConn,MaxConn,UnusedTime,ReapTime,Script,DBFailOver,file);
				return waszos;
			}
		}
		else if((CMUtil.appServer.equals("weblogic"))||(CMUtil.appServer.startsWith("WebLogic")))
		{
			if(CMUtil.implementorID.equals("oraclerac"))
			{
				JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				OrRacUrl = props.getChildValueByName(doc1, PROPERTY, "OracleRacUrl", VALUE);
				DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				DBFailOver = props.getChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE);
				orrac = new ConfigureGCDJDBC_wlorrac(JDBCDSName,JDBCDSXAName,OrRacUrl,DBUserName,DBPassword,Script,DBFailOver,file);
				return orrac;
			}
			else if((CMUtil.implementorID.equals("db2"))||(CMUtil.implementorID.equals("mssql")))
			{
				JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				DBServer = props.getChildValueByName(doc1, PROPERTY, "DatabaseServerName", VALUE);
				DBPort = props.getChildValueByName(doc1, PROPERTY, "DatabasePortNumber", VALUE);
				DBName = props.getChildValueByName(doc1, PROPERTY, "DatabaseName", VALUE);
				DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				DBFailOver = props.getChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE);
				panel2 = new ConfigureGCDJDBC_wlsqldb2(JDBCDSName,JDBCDSXAName,DBServer,DBPort,DBName,DBUserName,DBPassword,Script,DBFailOver,file);
				return panel2;
			}
			else if(CMUtil.implementorID.equals("oracle"))
			{
				JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				DBServer = props.getChildValueByName(doc1, PROPERTY, "DatabaseServerName", VALUE);
				DBPort = props.getChildValueByName(doc1, PROPERTY, "DatabasePortNumber", VALUE);
				DBName = props.getChildValueByName(doc1, PROPERTY, "DatabaseName", VALUE);
				ServiceName = props.getChildValueByName(doc1, PROPERTY, "ServiceNameEnabled", VALUE);
				DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				DBFailOver = props.getChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE);
				wl_oracle = new ConfigureGCDJDBC_wloracle(JDBCDSName, JDBCDSXAName, DBServer, DBPort, DBName, ServiceName, DBUserName, DBPassword, Script, DBFailOver,file);
				return wl_oracle;
			}
			else
			{
				System.out.println("Panel not designed yet!!!");
			}
		}
		}
		catch(DOMException de) 
		{
			de.printStackTrace(); 
		}
		return defaultPanel;
	}
}
