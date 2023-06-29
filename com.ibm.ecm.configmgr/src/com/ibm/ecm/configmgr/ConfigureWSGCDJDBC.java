package com.ibm.ecm.configmgr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.ibm.Factory.PropertyFactory;

abstract class ConfigureWSGCDJDBC 
{
	private static final String EMPTY_STRING = ""; //$NON-NLS-1$
    private static final String TRUE = "true"; //$NON-NLS-1$
    private static final String FALSE = "false"; //$NON-NLS-1$
	private static final String SLASH = "/"; //$NON-NLS-1$
	private static final String N_A = "N/A"; //$NON-NLS-1$ JKS SSL
	//private static final String DATABASE_CONNECTION_FAILED = "Failed to connect to the DataSource";//
	       
    /*protected WSJDBCBaseTestActivity(TestContext ctx)
    {
        super(ctx);
    }*/
    
    public final String testWork()
    {
        String test_result = EMPTY_STRING;
        String test_result1 = EMPTY_STRING;
        String test_result2 = EMPTY_STRING;
        String jdbcDataSourceName = EMPTY_STRING;
        String jdbcDataSourceXAName = EMPTY_STRING;
        //final String _6 = "6"; //$NON-NLS-1$
        boolean non_XA_exist = false;
        boolean XA_exist = false;
        boolean dsSSL = false; //20484
        PropertyFactory props = new PropertyFactory();
        
        //WebSphereJMXInvoker invoker = new WebSphereJMXInvoker(JMXParametersResolver.getWebSphereConnectionParameters(getEnvironmentCMProperties()));
        try 
        {	
        	//first test the datasources names
        	jdbcDataSourceName = ConfigKeys.JDBCDSName;   
        	jdbcDataSourceXAName = ConfigKeys.JDBCDSXAName; //getTaskCMProperties().getValue(ConfigKeys.JDBC_DATASOURCE_XA_NAME);
        	
			/*
			 * test_result1 = invoker.testDataSourceNameExists(jdbcDataSourceName);
			 * test_result2 = invoker.testDataSourceNameExists(jdbcDataSourceXAName);
			 * 
			 * if//getTaskCMProperties().getValue(ConfigKeys.JDBC_DATASOURCE_NAME);
			 * (test_result1.equalsIgnoreCase(TRUE)||test_result1.equalsIgnoreCase(FALSE)) {
			 * non_XA_exist = Boolean.valueOf(test_result1).booleanValue(); XA_exist =
			 * Boolean.valueOf(test_result2).booleanValue();
			 * 
			 * // DTS 912139 - If one exist and not the other, it is an error. // If both
			 * don't exist or both exist, go to actually test the connection. if
			 * (non_XA_exist && !XA_exist) { String message =
			 * Messages.bind(Messages.NOT_BOTH_DATASOURCE_EXIST,jdbcDataSourceName,
			 * jdbcDataSourceXAName); return Messages.bind
			 * (Messages.TESTING_DATASOURCE_NAMES_ERROR, message); } else if (!non_XA_exist
			 * && XA_exist) { String message =
			 * Messages.bind(Messages.NOT_BOTH_DATASOURCE_EXIST,jdbcDataSourceXAName,
			 * jdbcDataSourceName); return Messages.bind
			 * (Messages.TESTING_DATASOURCE_NAMES_ERROR, message); } } else return
			 * test_result1;
			 */
        	
        	//test the database connection
            //String applicationServerVersion = getEnvironmentCMProperties().getValue(ConfigKeys.APPLICATION_SERVER_VERSION);
            //String majorVersion = applicationServerVersion.substring(0, applicationServerVersion.indexOf("."));//$NON-NLS-1$
        	String dbdriver = ""; //getTaskCMProperties().getValue(ConfigKeys.JDBC_DRIVER_NAME);
        	//if (dbdriver.contains("SSL enabled")) dbdriver = "OracleSSL";
        	if (dbdriver.contains("SSL")) dbdriver = "OracleSSL";//21053
            
            String databaseUsername = ConfigKeys.DBUserName; //getTaskCMProperties().getValue(ConfigKeys.DATABASE_USERNAME).trim();
            String databasePassword = ConfigKeys.DBPassword; //getTaskCMProperties().getValue(ConfigKeys.DATABASE_PASSWORD);
            String databaseName = ConfigKeys.DBName; //getTaskCMProperties().getValue(ConfigKeys.DATABASE_NAME);
            //changes for defect 3470 start
            boolean databaseServiceNameOracleSet = Boolean.parseBoolean(ConfigKeys.ServiceName); //getTaskCMProperties().getValue(ConfigKeys.ORACLE_DATABASE_SERVICE_NAME_CHECK_BOX));
            //changes for defect 3470 end
            String databaseServerName = ConfigKeys.DBServer; //getTaskCMProperties().getValue(ConfigKeys.DATABASE_SERVER_NAME);
            String databasePortNumber = ConfigKeys.DBPort; //getTaskCMProperties().getValue(ConfigKeys.DATABASE_PORT_NUMBER);
            String databaseVersion = ""; //getTaskCMProperties().getValue(ConfigKeys.DATABASE_VERSION);
            String jdbcDriverVersion = ""; //getTaskCMProperties().getValue(ConfigKeys.JDBC_DRIVER_VERSION);
            String odbcjarname = "";
            String mssqljarname = "";
            //changes for defect 3470  start
            if(Integer.parseInt(getJDBCtype()) == 4)
            	odbcjarname = ConfigKeys.Jar; //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.ODBC_JAR_NAME));
           	//changes for defect 3470 end
            //changes for defect 9654 start
            if(Integer.parseInt(getJDBCtype()) == 3)
            	mssqljarname = ConfigKeys.Jar; //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.MSSQL_JAR_NAME));
            //changes for defect 9654 end
            String driverImplementation = EMPTY_STRING; 
            String driverPath =EMPTY_STRING; 
            String driverType =EMPTY_STRING;//DB2 - only 
            String url = EMPTY_STRING;//Oracle - only 
            
            //20484                
            if ((CMUtil.isCmuiClient) && ((dbdriver).equalsIgnoreCase("OracleSSL")))
            	dsSSL = true;
            	//dsSSL = Boolean.parseBoolean(getTaskCMProperties().getValue(ConfigKeys.SSL_ENABLED));//Oracle - only 20484
            
            switch (Integer.parseInt(getJDBCtype()))
            {
                case 3:
                	//driverType="4";//10.2
                	//if (mssqljarname.contains("10.2")) 
                	driverType="3";//41669
                    driverImplementation = "com.microsoft.sqlserver.jdbc.SQLServerConnectionPoolDataSource"; //$NON-NLS-1$
                    // majorVersion 7 and 8 and 8.5
                    //if (jdbcDriverVersion.equals("2.0")||jdbcDriverVersion.equals("3.0") ||jdbcDriverVersion.equals("4.0"))
                    driverPath = "${MICROSOFT_JDBC_DRIVER_PATH}/"+mssqljarname; //$NON-NLS-1$
                    break;	
                case 4:
                    driverImplementation = "oracle.jdbc.pool.OracleConnectionPoolDataSource"; //$NON-NLS-1$
                    url = "jdbc:oracle:thin:@"+ databaseServerName+ ":" +databasePortNumber+ ":"+ databaseName; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    driverType="4"; //$NON-NLS-1$ - 36220
                    // majorVersion is  7 or 8 or 8.5
                    if (databaseVersion.equalsIgnoreCase("Oracle 10g")) //$NON-NLS-1$
                           driverPath = "${ORACLE_JDBC_DRIVER_PATH}/ojdbc14.jar"; //$NON-NLS-1$
                    else
                          driverPath = "${ORACLE_JDBC_DRIVER_PATH}/"+odbcjarname; //$NON-NLS-1$
                    //changes for defect 3470 start
                    if(databaseServiceNameOracleSet)
                    {
                    	url = "jdbc:oracle:thin:@//"+ databaseServerName+ ":" +databasePortNumber+ "/"+ databaseName;
                    	//driverPath = "${ORACLE_JDBC_DRIVER_PATH}/ojdbc6.jar";  
                    	//change for task 5710
                    	driverPath = "${ORACLE_JDBC_DRIVER_PATH}/"+odbcjarname;
                    }
                   //changes for defect 3470 end
                    
                   // 20484
                   if (dsSSL)
                   {
                	    url = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCPS)(HOST="+databaseServerName+")(PORT="+databasePortNumber+"))(CONNECT_DATA=(SERVICE_NAME="+ databaseName+")))";
                	    driverPath = "${ORACLE_JDBC_DRIVER_PATH}/"+odbcjarname;                    	   
                   }
                   
                   break;
                case 5:
                    driverType="4"; //$NON-NLS-1$
                    driverImplementation = "com.ibm.db2.jcc.DB2ConnectionPoolDataSource"; //$NON-NLS-1$
                    if (jdbcDriverVersion.equals("3.0")){
                    	driverPath = "${DB2UNIVERSAL_JDBC_DRIVER_PATH}/db2jcc.jar;${DB2UNIVERSAL_JDBC_DRIVER_PATH}/db2jcc_license_cu.jar"; //$NON-NLS-1$
                    } else {
                    	driverType="5";
                    	driverPath = "${DB2_JCC_DRIVER_PATH}/db2jcc4.jar; ${DB2_JCC_DRIVER_PATH}/db2jcc_license_cu.jar";
                    }
                    break;
                case 6:
                    driverType="4"; //$NON-NLS-1$
                    driverImplementation = "com.ibm.db2.jcc.DB2ConnectionPoolDataSource"; //$NON-NLS-1$
                    if (jdbcDriverVersion.equals("3.0")){
                		driverPath = "${DB2UNIVERSAL_JDBC_DRIVER_PATH}/db2jcc.jar;${DB2UNIVERSAL_JDBC_DRIVER_PATH}/db2jcc_license_cu.jar;${DB2UNIVERSAL_JDBC_DRIVER_PATH}/db2jcc_license_cisuz.jar"; //$NON-NLS-1$
                	} else {
                		driverType="5";
                		driverPath = "${DB2_JCC_DRIVER_PATH}/db2jcc4.jar;${DB2_JCC_DRIVER_PATH}/db2jcc_license_cu.jar;${DB2_JCC_DRIVER_PATH}/db2jcc_license_cisuz.jar"; //$NON-NLS-1$	
                	}
                    break;
           }
            if (!dsSSL) 
            {
        	    if (driverType.equals("3"))
            		test_result = testDataBaseConnection(databaseUsername, databasePassword, databaseName, databaseServerName, databasePortNumber, driverType, databaseVersion, jdbcDriverVersion, databaseServiceNameOracleSet, odbcjarname, mssqljarname, url);
            	else 
				{
					test_result = testDBDSConnection(databaseUsername, databasePassword, databaseName, databaseServerName, databasePortNumber, driverType, databaseVersion, jdbcDriverVersion, url, jdbcDataSourceName, jdbcDataSourceXAName, odbcjarname, mssqljarname); //jarType,
					/*
					 * if (CMUtil.isGUI) { test_result =
					 * invoker.testDBConnection(driverImplementation, databaseUsername,
					 * databasePassword, databaseName, databaseServerName, databasePortNumber,
					 * driverPath, driverType, url); } else
					 */
				}
            }
            else
            	test_result = testDBConnection(databaseUsername, databasePassword, databaseName, databaseServerName, databasePortNumber, odbcjarname, jdbcDataSourceName, jdbcDataSourceXAName);
        }
        
        catch(Exception e) {
            test_result = e.getLocalizedMessage();
            //LOGGER.exception(e.getLocalizedMessage(), e, this.getClass().getName(), "testWork()"); //$NON-NLS-1$
        }
        if (test_result.startsWith("DSRA8040I") && (dsSSL))
        	test_result = "Failed to connect to the Database. Please check: a. if SSL enabled is checked or b. Check the database parameters";

        if (non_XA_exist && XA_exist)
        {
        	return "Both DS Exists";
        	//return test_result + ' ' + Messages.bind(Messages.BOTH_DATASOURCE_EXIST,jdbcDataSourceName,jdbcDataSourceXAName );           	                
        }
        return test_result;
   }
    
    //to be overwritten by extending classes
    protected abstract String getJDBCtype();

    public String testDBConnection(String user, String password, String dbname,String dbServer,String port, String odbcjar, String jdbcDataSourceName, String jdbcDataSourceXAName )
    {
    	String strEOL = CMUtil.getPlatformEOL();
        String result = strEOL;
        try
        {
            String QUOTE = "\""; //$NON-NLS-1$
            //String DOT ="."; //$NON-NLS-1$
            String JDBC_ORACLE_THIN = "jdbc:oracle:thin:@"; //$NON-NLS-1$

            String tempDir = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\tmp"; //getTaskCMProperties().getValue(ConfigKeys.TEMP_DIR);
        	File testTclFile = new File (ConfigKeys.Script);
        	String testTclParent = testTclFile.getParent();//+ File.separator + DOT + DOT + File.separator + "testDBConnect.tcl";
        	String wsTclScript  = testTclParent + File.separator + "testDBConnect.tcl";
        	//System.out.println("wsTclScript path = " + wsTclScript);
        	//File testTclScript = new File(wsTclScript);
        	String wsTclRuntime = tempDir+ SLASH + "testORADBConnect.tcl";
            boolean dbconnect = false;
    		         		
    		String keyStorePath = ConfigKeys.KStPath; //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DS_SSL_KEYSTORE_WALLET_PATH));
    		String keyStoreType = ConfigKeys.KStType; //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DS_SSL_KEYSTORE_WALLET_TYPE));
    		String keyStorePassword = ConfigKeys.SSLKStPass; //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DS_SSL_PASSWORD));

			String dsConnProps = "javax.net.ssl.trustStore=" + keyStorePath.replace('\\', '/') + ";javax.net.ssl.trustStoreType=" + keyStoreType + ";javax.net.ssl.trustStorePassword=" + keyStorePassword + ";oracle.net.ssl_version=1.0";
    		//String dsConnProps = "javax.net.ssl.trustStore=" + keyStorePath.replace('\\', '/') + ";javax.net.ssl.trustStoreType=" + keyStoreType + ";javax.net.ssl.trustStorePassword=" + keyStorePassword + ";javax.net.ssl.keyStore=C://jksclientwallet//keystore//jkskeystore.jks;javax.net.ssl.keyStoreType=JKS;javax.net.ssl.keyStorePassword=IBMFileNetP8" +  ";oracle.net.ssl_version=1.0";

    		String jkskeyStorePath = N_A, jkskeyStoreType = N_A, jkskeyStorePassword = N_A;//JKS SSL 
    		if (keyStoreType.equalsIgnoreCase("JKS")) // JKS SSL
    		{
    			jkskeyStorePath = ConfigKeys.JKSKStPath; //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DS_SSL_JKS_KEYSTORE_WALLET_PATH));
    			jkskeyStoreType = keyStoreType;
    			jkskeyStorePassword = ConfigKeys.JKSSSLKStPass; //WebsphereUtil.escapeSpecialChar(getTaskCMProperties().getValue(ConfigKeys.DS_SSL_JKS_PASSWORD));
				if (!(jkskeyStorePath.equals(N_A)) && !(jkskeyStoreType.equals("PKCS12")) && !(jkskeyStorePassword.equals(N_A)))// JKS SSL
					dsConnProps = "javax.net.ssl.trustStore=" + keyStorePath.replace('\\', '/') +  ";javax.net.ssl.trustStoreType=" + keyStoreType + ";javax.net.ssl.trustStorePassword=" + keyStorePassword + ";javax.net.ssl.keyStore=" + jkskeyStorePath.replace('\\', '/') +  ";javax.net.ssl.keyStoreType=" + keyStoreType + ";javax.net.ssl.keyStorePassword=" + jkskeyStorePassword + ";oracle.net.ssl_version=1.0";// replace File.pathSeparatorChar with ; 21025
    		}
    		
			//System.out.println("keyStorePath :" + keyStorePath);
			String url = JDBC_ORACLE_THIN + "(DESCRIPTION=(ADDRESS=(PROTOCOL=TCPS)(HOST="+ dbServer + ")(PORT="+ port +"))(CONNECT_DATA=(SERVICE_NAME="+ dbname + ")))";
			//System.out.println("url :" + url);
			//boolean dsSSL = Boolean.parseBoolean(getTaskCMProperties().getValue(ConfigKeys.SSL_ENABLED));//Oracle - only 20484
			
			FileReader inFR = new FileReader(wsTclScript);
			BufferedReader inBR = new BufferedReader(inFR);
			FileWriter outFW = new FileWriter(wsTclRuntime);
			
			//$_aliasName $_aliasPassword $_dbName $_dbServerName $_portNumber false" $_odbcjarType $_oracleSSLURL $_sslEnabled $_connProps :user password dbname server port isXA odbcjarType dsURL dsSSL dsConnProps} {
			outFW.write("set _aliasName " + QUOTE + user + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _aliasPassword " + QUOTE + password + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _dbName " + QUOTE + dbname + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _dbServerName " + QUOTE + dbServer + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _portNumber " + QUOTE + port + QUOTE+ strEOL);//$NON-NLS-1$
			outFW.write("set _odbcjarType " + QUOTE + odbcjar + QUOTE + strEOL);//$NON-NLS-1$    		
			outFW.write("set _dsURL " + QUOTE + url + QUOTE + strEOL);//$NON-NLS-1$
			//outFW.write("set _dsSSL " + QUOTE + dsSSL + QUOTE + strEOL);//$NON-NLS-1$
			outFW.write("set _dsConnProps " + QUOTE + dsConnProps + QUOTE + strEOL);//$NON-NLS-1$
			outFW.write(strEOL);
			String aLine;
			while ((aLine=inBR.readLine()) != null)
					outFW.write(aLine+ strEOL);
			
			inFR.close();
			inBR.close();
			outFW.close();
			
            String tclLog =  tempDir+ File.separator + "testORADBConnection.log";
            
            /*if ((keyStorePath != null && keyStorePath.length() > 0) && (keyStoreType != "" && keyStoreType.length() > 0) && (keyStorePassword != "" && keyStorePassword.length() > 0))
            	WebsphereUtil.runScript(AppServerParametersResolver.getWebsphereScriptRunningConnectionParameters(getEnvironmentCMProperties()), wsTclRuntime,tclLog);
            else
            {
            	result = "Please ensure to provide valid values for SSL KeyStore properties.";
                dbconnect = true;
            }*/
                            
            if (!dbconnect)
            {
                String output = strEOL;
                inFR = new FileReader(tclLog);
                inBR = new BufferedReader(inFR);
                while (((aLine=inBR.readLine()) != null) && (!aLine.equals("")))
                {
            		output = aLine + strEOL;
                //	if (aLine.indexOf(Messages.DATABASE_CONNECTION_SUCCESSFUL) >= 0)
            		if (aLine.indexOf("Successfully connected to DataSource") >= 0)
            		{
                		dbconnect = false;
                		break;
                	}
                	else if ((!aLine.startsWith("WASX7")) && (!aLine.contains("Failed with Exception")))//21053
                	{
                		dbconnect = true;
                		result += output;//21053
                	}
                }
                inFR.close();
                inBR.close();	               

                String remStr = "java.lang.ClassNotFoundException";//21053
        		if (result.contains(remStr))
        		{
        			result = result.substring(result.indexOf(remStr)+remStr.length()+2, result.length());
        		}
            }
               
            if (!dbconnect)
            	result = "Successfully connected to database"; //Messages.DATABASE_CONNECTION_SUCCESSFUL;
             
        }
        catch(Exception e)
        {
            //LOGGER.exceptionBeforeThrowing(e.getLocalizedMessage(), e, this.getClass().getName(), "testDBConnection");
        	//throw new ConfigurationManagerException(NLS.bind(Messages.EXCEPTION_EXECUTING_TASK_CONFIGURATION, e.getLocalizedMessage()));
        }

        return result;

    }
    
    //user password dbname server port driverType dbVersion driverVersion isXA oracleRacUrl oracleServiceNameURL odbcjarType mssqljarType
    public String testDataBaseConnection(String user, String password, String dbname,String dbServer,String port, String driverType, String dbVersion, String driverVersion, boolean databaseServiceNameOracleSet, String odbcjar, String mssqljarType, String url)
    {
    	String strEOL = CMUtil.getPlatformEOL();
        String result = strEOL;
        try
        {
            String QUOTE = "\""; //$NON-NLS-1$
            
            String tempDir = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\tmp"; //getTaskCMProperties().getValue(ConfigKeys.TEMP_DIR);
        	File testTclFile = new File(ConfigKeys.Script); // (getTaskCMProperties().getValue(ConfigKeys.SCRIPT));
        	String testTclParent = testTclFile.getParent();//+ File.separator + DOT + DOT + File.separator + "testDBConnect.tcl";
        	String wsTclScript  = testTclParent + File.separator + "testWASDBConnection.tcl";
        	//System.out.println("wsTclScript path = " + wsTclScript);
        	//File testTclScript = new File(wsTclScript);
        	String wsTclRuntime = tempDir+ SLASH + "testWASDBConn.tcl";
            boolean dbconnect = false;
    		            		
    		FileReader inFR = new FileReader(wsTclScript);
			BufferedReader inBR = new BufferedReader(inFR);
			FileWriter outFW = new FileWriter(wsTclRuntime);
			    			
			outFW.write("set _aliasName " + QUOTE + user + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _aliasPassword " + QUOTE + password + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _dbName " + QUOTE + dbname + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _dbServerName " + QUOTE + dbServer + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _portNumber " + QUOTE + port + QUOTE+ strEOL);//$NON-NLS-1$
			outFW.write("set _jdbcProviderType " + QUOTE + driverType + QUOTE+ strEOL);//$NON-NLS-1$
			outFW.write("set _odbcjarType " + QUOTE + odbcjar + QUOTE + strEOL);//$NON-NLS-1$
			outFW.write("set _mssqljarType " + QUOTE + mssqljarType + QUOTE + strEOL);//$NON-NLS-1$    			
			outFW.write("set _dbVersion " + QUOTE + dbVersion + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _driverVersion " + QUOTE + driverVersion + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _oracleServiceNameURL "+ QUOTE + url + QUOTE + strEOL);
			outFW.write("set _oracleRacUrl "+ QUOTE + "" + QUOTE + strEOL);
			outFW.write(strEOL);
			String aLine;
			while ((aLine=inBR.readLine()) != null)
					outFW.write(aLine+ strEOL);
			
			inFR.close();
			inBR.close();
			outFW.close();
			
            String tclLog =  tempDir+ File.separator + "testWASDBConnection.log";
            //WebsphereUtil.runScript(AppServerParametersResolver.getWebsphereScriptRunningConnectionParameters(getEnvironmentCMProperties()), wsTclRuntime,tclLog);
                            
            if (!dbconnect)
            {
                String output = strEOL;
                inFR = new FileReader(tclLog);
                inBR = new BufferedReader(inFR);
                while (((aLine=inBR.readLine()) != null) && (!aLine.equals("")))
                {
            		output = aLine + strEOL;
                //	if (aLine.indexOf(Messages.DATABASE_CONNECTION_SUCCESSFUL) >= 0)
            		if (aLine.indexOf("Successfully connected to DataSource") >= 0)
            		{
                		dbconnect = false;
                		break;
                	}
                	else if ((!aLine.startsWith("WASX7")) && (!aLine.contains("Failed with Exception")))//21053
                	{
                		dbconnect = true;
                		result += output;//21053
                	}
                }
                inFR.close();
                inBR.close();	               

                String remStr = "java.lang.ClassNotFoundException";//21053
        		if (result.contains(remStr))
        		{
        			result = result.substring(result.indexOf(remStr)+remStr.length()+2, result.length());
        		}
            }
               
            if (!dbconnect)
            	result = "Successfully connected to database"; //Messages.DATABASE_CONNECTION_SUCCESSFUL;
             
        }
        catch(Exception e)
        {
        	result = "Exception executing task"; //NLS.bind(Messages.EXCEPTION_EXECUTING_TASK_CONFIGURATION, e.getLocalizedMessage());
            //LOGGER.exceptionBeforeThrowing(e.getLocalizedMessage(), e, this.getClass().getName(), "testDBConnection");
        	//throw new ConfigurationManagerException(NLS.bind(Messages.EXCEPTION_EXECUTING_TASK_CONFIGURATION, e.getLocalizedMessage()));
        }

        return result;

    }        

    public String testDBDSConnection(String user, String password, String dbname, String dbServer, String port, String driverType, String databaseVersion, String jdbcDriverVersion, String url, String jdbcDataSourceName, String jdbcDataSourceXAName, String ojdbcjarType, String mssqljarType ) //String jarName, 
    {
    	String strEOL = CMUtil.getPlatformEOL();
        String result = strEOL;
        try
        {
            String QUOTE = "\""; //$NON-NLS-1$
            //String DOT ="."; //$NON-NLS-1$
            //String JDBC_ORACLE_THIN = "jdbc:oracle:thin:@"; //$NON-NLS-1$

            String tempDir = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\tmp"; //getTaskCMProperties().getValue(ConfigKeys.TEMP_DIR);
        	File testTclFile = new File(ConfigKeys.Script); //(getTaskCMProperties().getValue(ConfigKeys.SCRIPT));
        	String testTclParent = testTclFile.getParent();//+ File.separator + DOT + DOT + File.separator + "testDBConnect.tcl";
        	String wsTclScript  = testTclParent + File.separator + "testWASDBConnection.tcl";
        	//System.out.println("wsTclScript path = " + wsTclScript);
        	//File testTclScript = new File(wsTclScript);
        	String wsTclRuntime = tempDir+ SLASH + "testWASDBConnect.tcl";
            boolean dbconnect = false;
            
			FileReader inFR = new FileReader(wsTclScript);
			BufferedReader inBR = new BufferedReader(inFR);
			FileWriter outFW = new FileWriter(wsTclRuntime);
			
			outFW.write("set _aliasName " + QUOTE + user + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _aliasPassword " + QUOTE + password + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _dbName " + QUOTE + dbname + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _dbServerName " + QUOTE + dbServer + QUOTE+ strEOL);//$NON-NLS-1$ 
			outFW.write("set _portNumber " + QUOTE + port + QUOTE+ strEOL);//$NON-NLS-1$    			
			outFW.write("set _jdbcProviderType " + QUOTE + driverType + QUOTE + strEOL);//$NON-NLS-1$    		
			outFW.write("set _dbVersion " + QUOTE + databaseVersion + QUOTE + strEOL);//$NON-NLS-1$    		
			outFW.write("set _driverVersion " + QUOTE + jdbcDriverVersion + QUOTE + strEOL);//$NON-NLS-1$
			outFW.write("set _dsURL " + QUOTE + url + QUOTE + strEOL);//$NON-NLS-1$
			//outFW.write("set _jarType " + QUOTE + jarName + QUOTE + strEOL);//$NON-NLS-1$
			outFW.write("set _dsName " + QUOTE + jdbcDataSourceName + QUOTE + strEOL);//$NON-NLS-1$
			outFW.write("set _dsXAName " + QUOTE + jdbcDataSourceXAName + QUOTE + strEOL);//$NON-NLS-1$  
			outFW.write("set _odbcjarType " + QUOTE + ojdbcjarType + QUOTE + strEOL);
			outFW.write("set _mssqljarType " + QUOTE + mssqljarType + QUOTE + strEOL);
			outFW.write("set _oracleRacUrl " + QUOTE + QUOTE + strEOL);
			outFW.write("set _oracleServiceNameURL " + QUOTE + QUOTE + strEOL);
			//outFW.write("set _dsSSL " + QUOTE + dsSSL + QUOTE + strEOL);//$NON-NLS-1$
			outFW.write(strEOL);

			String aLine;
			while ((aLine=inBR.readLine()) != null)
					outFW.write(aLine+ strEOL);
			
			inFR.close();
			inBR.close();
			outFW.close();
			
            String tclLog =  tempDir+ File.separator + "testWASDBConnection.log";
                                            
            //WebsphereUtil.runScript(AppServerParametersResolver.getWebsphereScriptRunningConnectionParameters(getEnvironmentCMProperties()), wsTclRuntime,tclLog);

            //if (!dbconnect)
            //{
                String output = strEOL;
                inFR = new FileReader(tclLog);
                inBR = new BufferedReader(inFR);
                while (((aLine=inBR.readLine()) != null) && (!aLine.equals("")))
                {
            		output = aLine + strEOL;
                //	if (aLine.indexOf(Messages.DATABASE_CONNECTION_SUCCESSFUL) >= 0)
            		//if (aLine.indexOf(Messages.DATABASE_CONNECTED) >= 0)
            		if (aLine.indexOf("Succesfully Connected") >= 0)
            		{
                		dbconnect = false;
                		break;
                	}
                	else if ((!aLine.startsWith("WASX7")) && (!aLine.contains("Failed with Exception")))//21053
                	{
                		dbconnect = true;
                		result += output;//21053
                	}
                }
                inFR.close();
                inBR.close();	               

                String remStr = "java.lang.ClassNotFoundException";//21053
        		if (result.contains(remStr))
        		{
        			result = result.substring(result.indexOf(remStr)+remStr.length()+2, result.length());
        		}
            //}
               
            if (!dbconnect)
            	result = "Successfully connected to DB"; //Messages.DATABASE_CONNECTION_SUCCESSFUL;
             
        }
        catch(Exception e)
        {
        	e.printStackTrace();
            //LOGGER.exception(e.getLocalizedMessage(), e, this.getClass().getName(), "testDBConnection");
        	//throw new ConfigurationManagerException(NLS.bind(Messages.EXCEPTION_EXECUTING_TASK_CONFIGURATION, e.getLocalizedMessage()));
        }

        return result;

    }
}
