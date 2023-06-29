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

	private File file;//JDBCDSName,JDBCDSXAName
	//private String DBServer,DBPort,DBName,ServiceName,DBUserName,DBPassword,Jar,
	//MinConn,MaxConn,UnusedTime,ReapTime,Script,DBFailOver;
	private ConfigureGCDJDBC_wasoracle ora;
	private ConfigureGCDJDBC_wassql sql;
	private ConfigureGCDJDBC_wlorrac orrac;
	private ConfigureGCDJDBC_wloracle wl_oracle;
	private ConfigureGCDJDBC_wlsql panel2;
	private ConfigureGCDJDBC_wldb2 wl_db2;
	private ConfigureGCDJDBC_wasdb2 wasdb2;
	private ConfigureGCDJDBC_wasorrac wasorr;
	private ConfigureGCDJDBC_wasorssl wasors;
	private ConfigureGCDJDBC_wasdb2pshadr wasph;
	private ConfigureGCDJDBC_wasdb2zos waszos;
	public JPanel defaultPanel = new JPanel();
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
				ConfigKeys.JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				ConfigKeys.JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				ConfigKeys.DBServer = props.getChildValueByName(doc1, PROPERTY, "DatabaseServerName", VALUE);
				ConfigKeys.DBPort = props.getChildValueByName(doc1, PROPERTY, "DatabasePortNumber", VALUE);
				ConfigKeys.DBName = props.getChildValueByName(doc1, PROPERTY, "DatabaseName", VALUE);
				ConfigKeys.ServiceName = props.getChildValueByName(doc1, PROPERTY, "ServiceNameEnabled", VALUE);
				ConfigKeys.DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				ConfigKeys.DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				ConfigKeys.Jar = props.getChildValueByName(doc1, PROPERTY, "OJDBC Jar", VALUE);
				ConfigKeys.MinConn = props.getChildValueByName(doc1, PROPERTY, "Minimum connections", VALUE);
				ConfigKeys.MaxConn = props.getChildValueByName(doc1, PROPERTY, "Maximum connections", VALUE);
				ConfigKeys.UnusedTime = props.getChildValueByName(doc1, PROPERTY, "Unused timeout (seconds)", VALUE);
				ConfigKeys.ReapTime = props.getChildValueByName(doc1, PROPERTY, "Reap time (seconds)", VALUE);
				ConfigKeys.Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				ConfigKeys.DBFailOver = props.getChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE);
				ora = new ConfigureGCDJDBC_wasoracle(ConfigKeys.JDBCDSName,ConfigKeys.JDBCDSXAName,ConfigKeys.DBServer,ConfigKeys.DBPort,ConfigKeys.DBName,ConfigKeys.ServiceName,ConfigKeys.DBUserName,ConfigKeys.DBPassword,
						ConfigKeys.Jar,ConfigKeys.MinConn,ConfigKeys.MaxConn,ConfigKeys.UnusedTime,ConfigKeys.ReapTime,ConfigKeys.Script,ConfigKeys.DBFailOver,file);
				return ora;
			}
			else if(CMUtil.implementorID.equals("mssql"))
			{
				ConfigKeys.JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				ConfigKeys.JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				ConfigKeys.DBServer = props.getChildValueByName(doc1, PROPERTY, "DatabaseServerName", VALUE);
				ConfigKeys.DBPort = props.getChildValueByName(doc1, PROPERTY, "DatabasePortNumber", VALUE);
				ConfigKeys.DBName = props.getChildValueByName(doc1, PROPERTY, "DatabaseName", VALUE);
				ConfigKeys.DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				ConfigKeys.DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				ConfigKeys.Jar = props.getChildValueByName(doc1, PROPERTY, "MSSQL Jar", VALUE);
				ConfigKeys.MinConn = props.getChildValueByName(doc1, PROPERTY, "Minimum connections", VALUE);
				ConfigKeys.MaxConn = props.getChildValueByName(doc1, PROPERTY, "Maximum connections", VALUE);
				ConfigKeys.UnusedTime = props.getChildValueByName(doc1, PROPERTY, "Unused timeout (seconds)", VALUE);
				ConfigKeys.ReapTime = props.getChildValueByName(doc1, PROPERTY, "Reap time (seconds)", VALUE);
				ConfigKeys.Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				ConfigKeys.DBFailOver = props.getChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE);
				sql = new ConfigureGCDJDBC_wassql(ConfigKeys.JDBCDSName,ConfigKeys.JDBCDSXAName,ConfigKeys.DBServer,ConfigKeys.DBPort,ConfigKeys.DBName,ConfigKeys.DBUserName,
						ConfigKeys.DBPassword,ConfigKeys.Jar,ConfigKeys.MinConn,ConfigKeys.MaxConn,ConfigKeys.UnusedTime,ConfigKeys.ReapTime,ConfigKeys.Script,ConfigKeys.DBFailOver,file);
				return sql;
			}
			else if(CMUtil.implementorID.equals("oraclerac"))
			{
				ConfigKeys.JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				ConfigKeys.JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				ConfigKeys.OrRacUrl = props.getChildValueByName(doc1, PROPERTY, "OracleRacUrl", VALUE);
				ConfigKeys.DBService = props.getChildValueByName(doc1, PROPERTY, "DatabaseServiceName", VALUE);
				ConfigKeys.OrRacEnt = props.getChildValueByName(doc1, PROPERTY, "OracleRacRetries", VALUE);
				ConfigKeys.OrRacDel = props.getChildValueByName(doc1, PROPERTY, "OracleRacDelay", VALUE);
				ConfigKeys.DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				ConfigKeys.DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				ConfigKeys.Jar = props.getChildValueByName(doc1, PROPERTY, "OJDBC Jar", VALUE);
				ConfigKeys.MinConn = props.getChildValueByName(doc1, PROPERTY, "Minimum connections", VALUE);
				ConfigKeys.MaxConn = props.getChildValueByName(doc1, PROPERTY, "Maximum connections", VALUE);
				ConfigKeys.UnusedTime = props.getChildValueByName(doc1, PROPERTY, "Unused timeout (seconds)", VALUE);
				ConfigKeys.ReapTime = props.getChildValueByName(doc1, PROPERTY, "Reap time (seconds)", VALUE);
				ConfigKeys.Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				ConfigKeys.DBFailOver = props.getChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE);
				wasorr = new ConfigureGCDJDBC_wasorrac(ConfigKeys.JDBCDSName,ConfigKeys.JDBCDSXAName,ConfigKeys.OrRacUrl,ConfigKeys.DBService,ConfigKeys.OrRacEnt,ConfigKeys.OrRacDel,ConfigKeys.DBUserName,ConfigKeys.DBPassword,ConfigKeys.Jar,
						ConfigKeys.MinConn,ConfigKeys.MaxConn,ConfigKeys.UnusedTime,ConfigKeys.ReapTime,ConfigKeys.Script,ConfigKeys.DBFailOver,file);
				return wasorr;
			}
			else if(CMUtil.implementorID.equals("oraclessl"))
			{
				ConfigKeys.JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				ConfigKeys.JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				ConfigKeys.DBServer = props.getChildValueByName(doc1, PROPERTY, "DatabaseServerName", VALUE);
				ConfigKeys.DBPort = props.getChildValueByName(doc1, PROPERTY, "DatabasePortNumber", VALUE);
				ConfigKeys.DBName = props.getChildValueByName(doc1, PROPERTY, "DatabaseName", VALUE);
				ConfigKeys.ServiceName = props.getChildValueByName(doc1, PROPERTY, "ServiceNameEnabled", VALUE);
				ConfigKeys.DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				ConfigKeys.DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				ConfigKeys.Jar = props.getChildValueByName(doc1, PROPERTY, "OJDBC Jar", VALUE);
				ConfigKeys.KStPath = props.getChildValueByName(doc1, PROPERTY, "KeyStorePath", VALUE);
				ConfigKeys.KStType = props.getChildValueByName(doc1, PROPERTY, "KeyStoreType", VALUE);
				ConfigKeys.SSLKStPass = props.getChildValueByName(doc1, PROPERTY, "SSLKeyStorePassword", VALUE);
				ConfigKeys.JKSKStPath = props.getChildValueByName(doc1, PROPERTY, "JKSKeyStorePath(JKS)", VALUE);
				ConfigKeys.JKSSSLKStPass = props.getChildValueByName(doc1, PROPERTY, "JKSSSLKeyStorePassword(JKS)", VALUE);
				ConfigKeys.MinConn = props.getChildValueByName(doc1, PROPERTY, "Minimum connections", VALUE);
				ConfigKeys.MaxConn = props.getChildValueByName(doc1, PROPERTY, "Maximum connections", VALUE);
				ConfigKeys.UnusedTime = props.getChildValueByName(doc1, PROPERTY, "Unused timeout (seconds)", VALUE);
				ConfigKeys.ReapTime = props.getChildValueByName(doc1, PROPERTY, "Reap time (seconds)", VALUE);
				ConfigKeys.Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				ConfigKeys.DBFailOver = props.getChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE);
				wasors = new ConfigureGCDJDBC_wasorssl(ConfigKeys.JDBCDSName,ConfigKeys.JDBCDSXAName,ConfigKeys.DBServer,ConfigKeys.DBPort,ConfigKeys.DBName,ConfigKeys.ServiceName,
						ConfigKeys.DBUserName,ConfigKeys.DBPassword,ConfigKeys.Jar,ConfigKeys.KStPath,ConfigKeys.KStType,ConfigKeys.SSLKStPass,ConfigKeys.JKSKStPath,ConfigKeys.JKSSSLKStPass,ConfigKeys.MinConn,ConfigKeys.MaxConn,ConfigKeys.UnusedTime,ConfigKeys.
						ReapTime,ConfigKeys.Script,ConfigKeys.DBFailOver,file);
				return wasors;
			}
			else if(CMUtil.implementorID.equals("db2"))
			{
				ConfigKeys.JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				ConfigKeys.JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				ConfigKeys.DBServer = props.getChildValueByName(doc1, PROPERTY, "DatabaseServerName", VALUE);
				ConfigKeys.DBPort = props.getChildValueByName(doc1, PROPERTY, "DatabasePortNumber", VALUE);
				ConfigKeys.DBName = props.getChildValueByName(doc1, PROPERTY, "DatabaseName", VALUE);
				ConfigKeys.DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				ConfigKeys.DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				ConfigKeys.MinConn = props.getChildValueByName(doc1, PROPERTY, "Minimum connections", VALUE);
				ConfigKeys.MaxConn = props.getChildValueByName(doc1, PROPERTY, "Maximum connections", VALUE);
				ConfigKeys.UnusedTime = props.getChildValueByName(doc1, PROPERTY, "Unused timeout (seconds)", VALUE);
				ConfigKeys.ReapTime = props.getChildValueByName(doc1, PROPERTY, "Reap time (seconds)", VALUE);
				ConfigKeys.Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				ConfigKeys.DBFailOver = props.getChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE);
				wasdb2 = new ConfigureGCDJDBC_wasdb2(ConfigKeys.JDBCDSName,ConfigKeys.JDBCDSXAName,ConfigKeys.DBServer,ConfigKeys.DBPort,ConfigKeys.DBName,ConfigKeys.DBUserName,ConfigKeys.DBPassword,
						ConfigKeys.MinConn,ConfigKeys.MaxConn,ConfigKeys.UnusedTime,ConfigKeys.ReapTime,ConfigKeys.Script,ConfigKeys.DBFailOver,file);
				return wasdb2;
			}
			else if((CMUtil.implementorID.equals("db2ps")||(CMUtil.implementorID.equals("db2hadr"))))
			{
				ConfigKeys.JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				ConfigKeys.JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				ConfigKeys.DBServer = props.getChildValueByName(doc1, PROPERTY, "DatabaseServerName", VALUE);
				ConfigKeys.DBPort = props.getChildValueByName(doc1, PROPERTY, "DatabasePortNumber", VALUE);
				ConfigKeys.DBName = props.getChildValueByName(doc1, PROPERTY, "DatabaseName", VALUE);
				ConfigKeys.DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				ConfigKeys.DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				ConfigKeys.RetryInterval = props.getChildValueByName(doc1, PROPERTY, "retryIntervalForClientReroute", VALUE);
				ConfigKeys.MaxRetries = props.getChildValueByName(doc1, PROPERTY, "maxRetriesForClientReroute", VALUE);
				ConfigKeys.ServerPortClient = props.getChildValueByName(doc1, PROPERTY, "ServerPortClientReroute", VALUE);
				ConfigKeys.MinConn = props.getChildValueByName(doc1, PROPERTY, "Minimum connections", VALUE);
				ConfigKeys.MaxConn = props.getChildValueByName(doc1, PROPERTY, "Maximum connections", VALUE);
				ConfigKeys.UnusedTime = props.getChildValueByName(doc1, PROPERTY, "Unused timeout (seconds)", VALUE);
				ConfigKeys.ReapTime = props.getChildValueByName(doc1, PROPERTY, "Reap time (seconds)", VALUE);
				ConfigKeys.Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				System.out.println("New is "+ConfigKeys.Script);
				wasph = new ConfigureGCDJDBC_wasdb2pshadr(ConfigKeys.JDBCDSName,ConfigKeys.JDBCDSXAName,ConfigKeys.DBServer,ConfigKeys.DBPort,ConfigKeys.DBName,ConfigKeys.DBUserName,ConfigKeys.DBPassword,ConfigKeys.RetryInterval,ConfigKeys.MaxRetries,ConfigKeys.ServerPortClient,
						ConfigKeys.MinConn,ConfigKeys.MaxConn,ConfigKeys.UnusedTime,ConfigKeys.ReapTime,ConfigKeys.Script,file);
				return wasph;
			}
			else if(CMUtil.implementorID.equals("db2zos"))
			{
				ConfigKeys.JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				ConfigKeys.JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				ConfigKeys.DBServer = props.getChildValueByName(doc1, PROPERTY, "DatabaseServerName", VALUE);
				ConfigKeys.DBPort = props.getChildValueByName(doc1, PROPERTY, "DatabasePortNumber", VALUE);
				ConfigKeys.zOSInstance = props.getChildValueByName(doc1, PROPERTY, "zOSInstanceName", VALUE);
				ConfigKeys.DBName = props.getChildValueByName(doc1, PROPERTY, "DatabaseName", VALUE);
				ConfigKeys.DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				ConfigKeys.DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				ConfigKeys.zOSDefault = props.getChildValueByName(doc1, PROPERTY, "zOSDefaultSTOGROUP", VALUE);
				ConfigKeys.zOSGroup = props.getChildValueByName(doc1, PROPERTY, "zOSSTOGROUPOptions", VALUE);
				ConfigKeys.MinConn = props.getChildValueByName(doc1, PROPERTY, "Minimum connections", VALUE);
				ConfigKeys.MaxConn = props.getChildValueByName(doc1, PROPERTY, "Maximum connections", VALUE);
				ConfigKeys.UnusedTime = props.getChildValueByName(doc1, PROPERTY, "Unused timeout (seconds)", VALUE);
				ConfigKeys.ReapTime = props.getChildValueByName(doc1, PROPERTY, "Reap time (seconds)", VALUE);
				ConfigKeys.Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				ConfigKeys.DBFailOver = props.getChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE);
				waszos = new ConfigureGCDJDBC_wasdb2zos(ConfigKeys.JDBCDSName,ConfigKeys.JDBCDSXAName,ConfigKeys.DBServer,ConfigKeys.DBPort,ConfigKeys.zOSInstance,ConfigKeys.DBName,ConfigKeys.DBUserName,ConfigKeys.DBPassword,ConfigKeys.zOSDefault,ConfigKeys.zOSGroup,
						ConfigKeys.MinConn,ConfigKeys.MaxConn,ConfigKeys.UnusedTime,ConfigKeys.ReapTime,ConfigKeys.Script,ConfigKeys.DBFailOver,file);
				return waszos;
			}
		}
		else if((CMUtil.appServer.equals("weblogic"))||(CMUtil.appServer.startsWith("WebLogic"))||(CMUtil.appServer.startsWith("Oracle")))
		{
			if(CMUtil.implementorID.equals("oraclerac"))
			{
				ConfigKeys.JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				ConfigKeys.JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				ConfigKeys.OrRacUrl = props.getChildValueByName(doc1, PROPERTY, "OracleRacUrl", VALUE);
				ConfigKeys.DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				ConfigKeys.DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				ConfigKeys.Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				ConfigKeys.DBFailOver = props.getChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE);
				orrac = new ConfigureGCDJDBC_wlorrac(ConfigKeys.JDBCDSName,ConfigKeys.JDBCDSXAName,ConfigKeys.OrRacUrl,ConfigKeys.DBUserName,ConfigKeys.DBPassword,ConfigKeys.Script,ConfigKeys.DBFailOver,file);
				return orrac;
			}
			else if(CMUtil.implementorID.equals("mssql"))
			{
				ConfigKeys.JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				ConfigKeys.JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				ConfigKeys.DBServer = props.getChildValueByName(doc1, PROPERTY, "DatabaseServerName", VALUE);
				ConfigKeys.DBPort = props.getChildValueByName(doc1, PROPERTY, "DatabasePortNumber", VALUE);
				ConfigKeys.DBName = props.getChildValueByName(doc1, PROPERTY, "DatabaseName", VALUE);
				ConfigKeys.DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				ConfigKeys.DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				ConfigKeys.Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				ConfigKeys.DBFailOver = props.getChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE);
				panel2 = new ConfigureGCDJDBC_wlsql(ConfigKeys.JDBCDSName,ConfigKeys.JDBCDSXAName,ConfigKeys.DBServer,ConfigKeys.DBPort,ConfigKeys.DBName,ConfigKeys.DBUserName,
						ConfigKeys.DBPassword,ConfigKeys.Script,ConfigKeys.DBFailOver,file);
				return panel2;
			}
			else if(CMUtil.implementorID.equals("db2"))
			{
				ConfigKeys.JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				ConfigKeys.JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				ConfigKeys.DBServer = props.getChildValueByName(doc1, PROPERTY, "DatabaseServerName", VALUE);
				ConfigKeys.DBPort = props.getChildValueByName(doc1, PROPERTY, "DatabasePortNumber", VALUE);
				ConfigKeys.DBName = props.getChildValueByName(doc1, PROPERTY, "DatabaseName", VALUE);
				ConfigKeys.DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				ConfigKeys.DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				ConfigKeys.Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				ConfigKeys.DBFailOver = props.getChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE);
				wl_db2 = new ConfigureGCDJDBC_wldb2(ConfigKeys.JDBCDSName,ConfigKeys.JDBCDSXAName,ConfigKeys.DBServer,ConfigKeys.DBPort,ConfigKeys.DBName,ConfigKeys.DBUserName,
						ConfigKeys.DBPassword,ConfigKeys.Script,ConfigKeys.DBFailOver,file);
				return panel2;
			}
			else if(CMUtil.implementorID.equals("oracle"))
			{
				ConfigKeys.JDBCDSName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceName", VALUE);
				ConfigKeys.JDBCDSXAName = props.getChildValueByName(doc1, PROPERTY, "JDBCDataSourceXAName", VALUE);
				ConfigKeys.DBServer = props.getChildValueByName(doc1, PROPERTY, "DatabaseServerName", VALUE);
				ConfigKeys.DBPort = props.getChildValueByName(doc1, PROPERTY, "DatabasePortNumber", VALUE);
				ConfigKeys.DBName = props.getChildValueByName(doc1, PROPERTY, "DatabaseName", VALUE);
				ConfigKeys.ServiceName = props.getChildValueByName(doc1, PROPERTY, "ServiceNameEnabled", VALUE);
				ConfigKeys.DBUserName = props.getChildValueByName(doc1, PROPERTY, "DatabaseUsername", VALUE);
				ConfigKeys.DBPassword = props.getChildValueByName(doc1, PROPERTY, "DatabasePassword", VALUE);
				ConfigKeys.Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
				ConfigKeys.DBFailOver = props.getChildValueByName(doc1, PROPERTY, "DBFailoverEnabled", VALUE);
				wl_oracle = new ConfigureGCDJDBC_wloracle(ConfigKeys.JDBCDSName, ConfigKeys.JDBCDSXAName, ConfigKeys.DBServer, ConfigKeys.DBPort, ConfigKeys.DBName, ConfigKeys.ServiceName, 
						ConfigKeys.DBUserName, ConfigKeys.DBPassword, ConfigKeys.Script, ConfigKeys.DBFailOver,file);
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
