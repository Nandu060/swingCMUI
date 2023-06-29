package com.ibm.ecm.configmgr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

abstract class ConfigureWLGCDJDBC 
{
	private static final String EMPTY_STRING = "";
    private static final String TRUE = "true"; //$NON-NLS-1$
    private static final String FALSE = "false"; //$NON-NLS-1$
    private static final String JDBC_ORACLE_THIN = "jdbc:oracle:thin:@//"; //$NON-NLS-1$
    private static final String COLON = ":"; //$NON-NLS-1$
	
    private static final String TARGET_NAME_VAR = "targetName="; //$NON-NLS-1$
	private static final String DS_XA_NAME_VAR = "dsXaName="; //$NON-NLS-1$
	private static final String DS_NAME_VAR = "dsName="; //$NON-NLS-1$
	private static final String CONNECTION_URL_VAR = "connectionURL="; //$NON-NLS-1$
	private static final String WL_ADMIN_PASSWORD_VAR = "wlAdminPassword="; //$NON-NLS-1$
	private static final String WL_ADMIN_VAR = "wlAdmin="; //$NON-NLS-1$
	private static final String ALREADY_EXISTS = " already exists"; //$NON-NLS-1$
	private static final String DB_CONNECTION_SUCCESSFUL = "The connection to the database was successful."; //$NON-NLS-1$
	private static final String SINGLE_QUOTE = "'"; //$NON-NLS-1$
	private static final String T3S_PROTOCOL = "t3s://"; //$NON-NLS-1$
	private static final String T3_PROTOCOL = "t3://"; //$NON-NLS-1$
	private static final String SLASH = "/";
	
	public final String testWork()
    {
		String test_result = EMPTY_STRING; 
		String jdbcDataSourceName = EMPTY_STRING;
		String jdbcDataSourceXAName = EMPTY_STRING;

/*			if (CMUtil.isGUI)
		{

			String test_result1 = EMPTY_STRING;
			String test_result2 = EMPTY_STRING;
			boolean non_XA_exist = false;
			boolean XA_exist = false;
			
			WebLogicJMXInvoker invoker = new WebLogicJMXInvoker(JMXParametersResolver.getWebLogicConnectionParameters(getEnvironmentCMProperties()));
			try 
			{
				//first test the data sources names
				jdbcDataSourceName = getTaskCMProperties().getValue(ConfigKeys.JDBC_DATASOURCE_NAME);
				jdbcDataSourceXAName = getTaskCMProperties().getValue(ConfigKeys.JDBC_DATASOURCE_XA_NAME);
				
				test_result1 = invoker.testDataSourceNameExists(jdbcDataSourceName);
				test_result2 = invoker.testDataSourceNameExists(jdbcDataSourceXAName);
				
				if (test_result1.equalsIgnoreCase(TRUE)||test_result1.equalsIgnoreCase(FALSE))
				{
					non_XA_exist = Boolean.valueOf(test_result1).booleanValue();
					XA_exist = Boolean.valueOf(test_result2).booleanValue();
					
					// DTS 912139 - If one exist and not the other, it is an error.
					// If both don't exist or both exist, go to actually test the connection.
					if (non_XA_exist && !XA_exist)
					{
						String message = Messages.bind(Messages.NOT_BOTH_DATASOURCE_EXIST,jdbcDataSourceName,jdbcDataSourceXAName);
						return Messages.bind (Messages.TESTING_DATASOURCE_NAMES_ERROR, message);
					}
					else if (!non_XA_exist && XA_exist)
					{
						String message = Messages.bind(Messages.NOT_BOTH_DATASOURCE_EXIST,jdbcDataSourceXAName,jdbcDataSourceName);
						return Messages.bind (Messages.TESTING_DATASOURCE_NAMES_ERROR, message);
					}
				}
				else
					return test_result1;

				//test the database connection
				String target = getEnvironmentCMProperties().getValue(ConfigKeys.TARGET_NAME);

				String databaseUsername = getTaskCMProperties().getValue(ConfigKeys.DATABASE_USERNAME).trim();
				String databasePassword = getTaskCMProperties().getValue(ConfigKeys.DATABASE_PASSWORD);
				String databaseName = getTaskCMProperties().getValue(ConfigKeys.DATABASE_NAME);
				String databaseServerName = getTaskCMProperties().getValue(ConfigKeys.DATABASE_SERVER_NAME);
				String databasePortNumber = getTaskCMProperties().getValue(ConfigKeys.DATABASE_PORT_NUMBER);
				
				//added for defect 4144 from here 
				boolean databaseServiceNameOracleSet = Boolean.parseBoolean(getTaskCMProperties().getValue(ConfigKeys.ORACLE_DATABASE_SERVICE_NAME_CHECK_BOX));
				String url = null;
				if (databaseServiceNameOracleSet){
					url = getOracleServiceNameUrl(databaseServerName, databasePortNumber, databaseName);
				}
				else {
					url = getDbUrl(databaseServerName, databasePortNumber, databaseName);
				}
				if (getDbType().equalsIgnoreCase("MSSQL")) 
				{
					final String SEMICOLON = ";";
					url += SEMICOLON+"encrypt=false"+SEMICOLON+"integratedSecurity=false";
					//System.out.println("url =" + url);//62917
				}
				test_result = invoker.testCreateDataSource(getDbTestTable(),url, getDbDriverName(), databasePassword, databaseUsername, databaseName, getDbType(), databaseServerName, databasePortNumber, target);
				// till here. Commented the below line too.
				//test_result = invoker.testCreateDataSource(getDbTestTable(), getDbUrl(databaseServerName, databasePortNumber, databaseName), getDbDriverName(), databasePassword, databaseUsername, databaseName, getDbType(), databaseServerName, databasePortNumber, target);
			}
			catch(Exception e) {
				test_result = e.getLocalizedMessage();
				LOGGER.exception(e.getLocalizedMessage(), e, this.getClass().getName(), "testWork()"); //$NON-NLS-1$
			}
			if (non_XA_exist && XA_exist)
			{
				return test_result + ' ' + Messages.bind(Messages.BOTH_DATASOURCE_EXIST,jdbcDataSourceName,jdbcDataSourceXAName );           	                
			}
			return test_result;
		}
		else
		{*/
		String strEOL = CMUtil.getPlatformEOL();
		test_result = strEOL;
		
		try 
		{

			String applicationServerHost = CMUtil.appServerHostName; //getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_HOST);
			String targetName = CMUtil.targetName; //getEnvironmentCMProperties().getValue(ConfigKeys.TARGET_NAME);				
			String applicationServerAdminUsername = CMUtil.appServerAdminUser; //getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_ADMIN_USERNAME);
			String applicationServerAdminPassword = CMUtil.appServerAdminPassword; //getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_ADMIN_PASSWORD);
			String applicationServerSoapPort = CMUtil.appServerSOAP; //getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_SOAP_PORT);
			String applicationServerSSLEnabled = CMUtil.SSLEnabled; //getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_SSL_ENABLED);
			String url = T3_PROTOCOL+applicationServerHost+COLON+applicationServerSoapPort;
			if (applicationServerSSLEnabled.equalsIgnoreCase(TRUE))
				url = T3S_PROTOCOL+applicationServerHost+COLON+applicationServerSoapPort;
								
			//first test the data sources names
			jdbcDataSourceName = ConfigKeys.JDBCDSName; //getTaskCMProperties().getValue(ConfigKeys.JDBC_DATASOURCE_NAME);
			jdbcDataSourceXAName = ConfigKeys.JDBCDSXAName; //getTaskCMProperties().getValue(ConfigKeys.JDBC_DATASOURCE_XA_NAME);
			
			//test the database connection
			//String target = getEnvironmentCMProperties().getValue(ConfigKeys.TARGET_NAME);
			String tempDir = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\tmp"; //getTaskCMProperties().getValue(ConfigKeys.TEMP_DIR);
			File testPyFile = new File(ConfigKeys.Script); // (getTaskCMProperties().getValue(ConfigKeys.SCRIPT));
			String testPyParent = testPyFile.getParent();//+ File.separator + DOT + DOT + File.separator + "testDBConnect.tcl";
			String wlPyScript  = testPyParent + File.separator + "testWLDBConnection.py";
			//System.out.println("wlPyScript path = " + wlPyScript);
			String wlPyRuntime = tempDir+ SLASH + "testWLDBConnect.py";
			//boolean dbconnect = false;
			
			FileReader inFR = new FileReader(wlPyScript);
			BufferedReader inBR = new BufferedReader(inFR);
			FileWriter outFW = new FileWriter(wlPyRuntime);
			
			outFW.write(WL_ADMIN_VAR + SINGLE_QUOTE + applicationServerAdminUsername + SINGLE_QUOTE + strEOL);
			outFW.write(WL_ADMIN_PASSWORD_VAR + SINGLE_QUOTE + applicationServerAdminPassword + SINGLE_QUOTE + strEOL);
			outFW.write(CONNECTION_URL_VAR + SINGLE_QUOTE + url + SINGLE_QUOTE + strEOL);
			outFW.write(DS_NAME_VAR + SINGLE_QUOTE + jdbcDataSourceName + SINGLE_QUOTE + strEOL);
			outFW.write(DS_XA_NAME_VAR + SINGLE_QUOTE + jdbcDataSourceXAName + SINGLE_QUOTE + strEOL);
			outFW.write(TARGET_NAME_VAR + SINGLE_QUOTE + targetName + SINGLE_QUOTE + strEOL);
		
			String aLine;
			while ((aLine=inBR.readLine()) != null)
				outFW.write(aLine+ strEOL);
			
			inFR.close();
			inBR.close();
			outFW.close();
			
			String pyLog =  tempDir+ File.separator + "testWLDBConnection.log";
			
			//WeblogicUtil.runScript(AppServerParametersResolver.getWeblogicApplicationServerInstallationFolder(getEnvironmentCMProperties()), wlPyRuntime,pyLog,AppServerParametersResolver.getWeblogicApplicationServerVersion(getEnvironmentCMProperties()));

			String output = strEOL;
			boolean bDoneDS = false;
			boolean existDS = false;
			boolean existDSXA = false;
			inFR = new FileReader(pyLog);
			inBR = new BufferedReader(inFR);
			while ((aLine=inBR.readLine()) != null){
			   if ((aLine.indexOf(DB_CONNECTION_SUCCESSFUL) >= 0) || (aLine.indexOf("Database Connected") >= 0)) //(Messages.DATABASE_CONNECTED) >= 0))
				   bDoneDS = true;
			   if (aLine.indexOf(jdbcDataSourceName+ALREADY_EXISTS) >= 0)
				   existDS = true;
			   if (aLine.indexOf(jdbcDataSourceXAName+ALREADY_EXISTS) >= 0)
				   existDSXA = true;
			   output+=aLine+ strEOL;
			}
			
			inFR.close();
			inBR.close();
		   
			if (bDoneDS)
			{
				test_result += ' ' + "Database Connection Successful"+strEOL; //NLS.bind(Messages.DATABASE_CONNECTION_SUCCESSFUL,"")+strEOL;
				//System.out.println(test_result);
			}

			if (existDS && existDSXA)
			{
				test_result += ' ' + "Both Datasources exists"; //Messages.bind(Messages.BOTH_DATASOURCE_EXIST,jdbcDataSourceName,jdbcDataSourceXAName );
			}
			else if (existDS)
				test_result += ' ' + "Datasource already exists" + strEOL; //NLS.bind(Messages.DATASOURCE_ALREADY_EXIST,jdbcDataSourceName)+strEOL;
			else if (existDSXA)
				test_result += ' ' + "Datasource already exists" + strEOL; //NLS.bind(Messages.DATASOURCE_ALREADY_EXIST,jdbcDataSourceXAName)+strEOL;
			else if (!(bDoneDS))
				test_result += ' ' + "Database Connection Unsuccessful" + strEOL; //NLS.bind(Messages.DATABASE_CONNECTION_UNSUCCESSFUL,"")+strEOL;
		}
		catch(Exception e) {
			test_result = e.getLocalizedMessage();				   
			//LOGGER.exception(e.getLocalizedMessage(), e, this.getClass().getName(), "testWork()"); //$NON-NLS-1$
		}
		return test_result;
			
	}
    //}
    
	//added from here 4144
    protected String getOracleServiceNameUrl(String dbserver, String dbport, String dbname){
    	return JDBC_ORACLE_THIN + dbserver + COLON + dbport + "/" + dbname;
    }        // till here
	
	//need to be implemented in each task
    protected abstract String getDbType();

    //need to be implemented in each task
    protected abstract String getDbDriverName();

    //need to be implemented in each task
    protected abstract String getDbUrl(String dbserver, String dbport, String dbname);

    //need to be implemented in each task
    protected abstract String getDbTestTable();
    
}
