/* IBM Confidential
 * 
 * OCO Source Materials
 * 
 * 5724-R81
 * 
 * � Copyright IBM Corp. 2008, 2009, 2017, 2020, 2021
 * 
 * The source code for this program is not published or otherwise
 * divested of its trade secrets, irrespective of what has
 * been deposited with the U.S. Copyright Office.
 */
package com.ibm.ecm.configmgr;
import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
//import java.util.Arrays;
import java.util.Properties;

/* Reverting action performed for 10944
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
*/

//import org.eclipse.osgi.util.NLS;
/* Reverting action performed for 10944
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
*/

/*import com.ibm.ecm.configmgr.engine.ConfigurationManagerException;
import com.ibm.ecm.configmgr.engine.util.CMLogger;
import com.ibm.ecm.configmgr.engine.util.CMUtil;
import com.ibm.ecm.configmgr.engine.util.FileUtil;
import com.ibm.ecm.configmgr.engine.rcp.Activator;*/

public class WebsphereUtil {

	private static final String PASSWORD_ARG2 = "-password"; //$NON-NLS-1$
	private static final String PASSWORD_MASK = "******"; //$NON-NLS-1$
	private static final String SET__BIND_PASSWORD = "set _bindPassword"; //$NON-NLS-1$
	private static final String SET__SERVER_PASSWORD = "set _serverPassword"; //$NON-NLS-1$
	private static final String BIND_PASSWORD_ARG = "-bindPassword"; //$NON-NLS-1$
	private static final String SET__ALIAS_PASSWORD = "set _aliasPassword"; //$NON-NLS-1$
	private static final String BACKSLASH = "\\"; //$NON-NLS-1$
	private static final String COM_IBM_SOAP_REQUEST_TIMEOUT = "com.ibm.SOAP.requestTimeout="; //$NON-NLS-1$
	private static final String COM_IBM_SOAP_LOGINUSERID = "com.ibm.SOAP.loginUserid=";// 15108
	private static final String COM_IBM_SOAP_LOGINPASSWORD = "com.ibm.SOAP.loginPassword=";// 15108
	private static final String COM_IBM_SOAP_LOGINSOURCE = "com.ibm.SOAP.loginSource"; // 15108 , default set to prompt
	private static final String COM_IBM_SOAP_SECENABLED = "com.ibm.SOAP.securityEnabled"; // 20224
	private static final String SOAP_CLIENT_PROPS = "soap.client.props"; //$NON-NLS-1$
	private static final String PROPERTIES = "properties"; //$NON-NLS-1$
	private static final String BACKUP_EXT = ".backup"; //$NON-NLS-1$
	/*
	 * private static final String PASSWORD_ARG = " -password "; //$NON-NLS-1$
	 * private static final String USER_ARG = " -user "; //$NON-NLS-1$
	 */
	private static final String DASH_F = " -f "; //$NON-NLS-1$
	private static final String DASH_JAVAOPTION = " -javaoption "; //$NON-NLS-1$
	private static final String DASH_XMS512M = "-Xms512m"; //$NON-NLS-1$
	private static final String DASH_XMX1024M = "-Xmx1024m"; //$NON-NLS-1$
	// private static final String JACL = " jacl "; //$NON-NLS-1$
	private static final String LANG_ARG = " -lang "; //$NON-NLS-1$
	private static final String HOST_ARG = " -host "; //$NON-NLS-1$
	private static final String PORT_ARG = " -port "; //$NON-NLS-1$
	private static final String SOAP = " SOAP "; //$NON-NLS-1$
	private static final String CONNTYPE_ARG = " -conntype ";//$NON-NLS-1$
	private static final String QUOTE = "\""; //$NON-NLS-1$
	private static final String EMPTY_STRING = ""; //$NON-NLS-1$
	private static final String PATH_TO_WSADMIN_NONWINDOWS = "/bin/wsadmin.sh"; //$NON-NLS-1$
	private static final String PATH_TO_WSADMIN_WINDOWS = "\\bin\\wsadmin.bat"; //$NON-NLS-1$
	private static final String DOT = "."; //$NON-NLS-1$
	private static final String LOGS = "logs";//$NON-NLS-1$
	private static final String WSADMIN_TRACEOUT = "wsadmin.traceout";//$NON-NLS-1$
	private static final String POUND = "#"; //$NON-NLS-1$
	private static final String UNIX_SLASH_BIN = "/bin"; //$NON-NLS-1$
	private static final String WINDOWS_SLASH_BIN = "\\bin"; //$NON-NLS-1$

	/*
	 * public static final class ScriptRunningConnectionParameters { private final
	 * String applicationServerVersion; private final String
	 * "C:\\Program Files\\IBM\\WebSphere\\AppServer\\profiles\\AppSrv03"; private
	 * final String applicationServerAdminUsername; private final String
	 * applicationServerAdminPassword; private final String
	 * applicationServerSoapPort; private final String applicationServerHostname;
	 * 
	 * public ScriptRunningConnectionParameters( String applicationServerVersion,
	 * String "C:\\Program Files\\IBM\\WebSphere\\AppServer\\profiles\\AppSrv03",
	 * String applicationServerAdminUsername, String applicationServerAdminPassword,
	 * String applicationServerSoapPort, String applicationServerHostname) {
	 * this.applicationServerVersion = applicationServerVersion;
	 * this."C:\\Program Files\\IBM\\WebSphere\\AppServer\\profiles\\AppSrv03" =
	 * "C:\\Program Files\\IBM\\WebSphere\\AppServer\\profiles\\AppSrv03";
	 * this.applicationServerAdminUsername = applicationServerAdminUsername;
	 * this.applicationServerAdminPassword = applicationServerAdminPassword;
	 * this.applicationServerSoapPort = applicationServerSoapPort;
	 * this.applicationServerHostname = applicationServerHostname; } }
	 */

	// public static void runScript(ScriptRunningConnectionParameters connParams,
	// String script, String logname
	// public static void runScript(ScriptRunningConnectionParameters connParams,
	// String script, String logname)
	public static String runScript(String script, String logname) {
		// CMLogger log = Activator.getLogger();
		final String methodName = "runScript()"; //$NON-NLS-1$
		String className = "WebsphereUtil"; //$NON-NLS-1$
		// log.entry( className, methodName ); //$NON-NLS-1$

		// log.info(NLS.bind(Messages.RUN_SCRIPT_START_DEBUG_MSG, script));

		/*
		 * String applicationServerVersion = connParams.applicationServerVersion; String
		 * "C:\\Program Files\\IBM\\WebSphere\\AppServer\\profiles\\AppSrv03" =
		 * connParams."C:\\Program Files\\IBM\\WebSphere\\AppServer\\profiles\\AppSrv03"
		 * ; String majorVersion = applicationServerVersion.substring(0,
		 * applicationServerVersion.indexOf(DOT)); String applicationServerAdminUsername
		 * = connParams.applicationServerAdminUsername; String
		 * applicationServerAdminPassword = connParams.applicationServerAdminPassword;
		 * String applicationServerSoapPort = connParams.applicationServerSoapPort;
		 * String applicationServerHostname = connParams.applicationServerHostname;
		 * String workingDir = null; File workingDirf = null;
		 */
		String workingDir = null;
		File workingDirf = null;
		String wsAdmin;
		String LANG;
		if (script.endsWith(".tcl"))
			LANG = " jacl ";
		else
			LANG = " jython ";

		/*
		 * if (CMUtil.isWinOS()) {
		 */
		wsAdmin = "C:\\Program Files\\IBM\\WebSphere\\AppServer\\profiles\\AppSrv02" + PATH_TO_WSADMIN_WINDOWS;// "C:\\Program
																												// Files\\IBM\\WebSphere\\AppServer\\profiles\\AppSrv03"
		workingDir = "C:\\Program Files\\IBM\\WebSphere\\AppServer" + WINDOWS_SLASH_BIN;
		/*
		 * } else { wsAdmin =
		 * "C:\\Program Files\\IBM\\WebSphere\\AppServer\\profiles\\AppSrv03"
		 * +PATH_TO_WSADMIN_NONWINDOWS; workingDir =
		 * "C:\\Program Files\\IBM\\WebSphere\\AppServer\\profiles\\AppSrv03"
		 * +UNIX_SLASH_BIN; }
		 */

		workingDirf = new File(workingDir);

		// DTS906142 - If file wsadmin.traceout does not exist, create the file.
		// check permissions before executing the script
		// need to have write access to the profile folder and its sub-directories
		File profileFolder = new File("C:\\Program Files\\IBM\\WebSphere\\AppServer\\profiles\\AppSrv02");
		File wsadmintraceout = new File("C:\\Program Files\\IBM\\WebSphere\\AppServer\\profiles\\AppSrv02"
				+ File.separator + LOGS + File.separator + WSADMIN_TRACEOUT);

		if (!wsadmintraceout.exists()) {
			try {
				OutputStream out = new FileOutputStream(wsadmintraceout);
				out.close();
			} catch (IOException ioe) {
				String localizedMsg = ioe.getLocalizedMessage();
				System.out.println(localizedMsg);
				// log.trace(cleanupScriptOutput(localizedMsg));
				// cleanupScriptOnExit(script);
				/*
				 * ConfigurationManagerException cmrte = new
				 * ConfigurationManagerException(NLS.bind(Messages.EXCEPTION_RUNNING_SCRIPT,
				 * wsAdmin, cleanupScriptOutput(localizedMsg)));
				 * log.exceptionBeforeThrowing(cmrte.getLocalizedMessage(), cmrte, className,
				 * methodName); throw cmrte;
				 */
			}
		}
		if (!(profileFolder.canWrite() && wsadmintraceout.canWrite())) {
			String path = !(profileFolder.canWrite()) ? profileFolder.getAbsolutePath()
					: wsadmintraceout.getAbsolutePath();
			/*
			 * ConfigurationManagerException cmrte = new
			 * ConfigurationManagerException(Messages.bind(Messages.ERROR_RUNNING_SCRIPT,
			 * Messages.bind(Messages.MISSING_WRITE_PERMISSION,path)));
			 * log.exceptionBeforeThrowing(cmrte.getLocalizedMessage(), cmrte, className,
			 * methodName); throw cmrte;
			 */
		}

		// setSOAPTimeout(majorVersion,"C:\\Program
		// Files\\IBM\\WebSphere\\AppServer\\profiles\\AppSrv03", true);

		String arguments = EMPTY_STRING;
		/*
		 * if (applicationServerAdminUsername.length() <= 0 ||
		 * applicationServerAdminPassword.length() <= 0) { arguments+= CONNTYPE_ARG;
		 * arguments+= SOAP; arguments+= PORT_ARG; arguments+=
		 * applicationServerSoapPort; arguments+= HOST_ARG; arguments+=
		 * applicationServerHostname; arguments+= DASH_JAVAOPTION; arguments+=
		 * DASH_XMS512M; //DASH_XMX1024M; arguments+= DASH_JAVAOPTION; arguments+=
		 * DASH_XMX1024M; //arguments+= DASH_JAVAOPTION; //arguments+=
		 * "-verbose:sizes "; arguments+= LANG_ARG; //arguments+= JACL; arguments+=
		 * LANG; arguments+= DASH_F;
		 * 
		 * } else {
		 */
		// System.out.println("Setting username and password in soap.client.props");
		// setWASCredentials("C:\\Program
		// Files\\IBM\\WebSphere\\AppServer\\profiles\\AppSrv03",applicationServerAdminUsername,applicationServerAdminPassword);
		// //15108

		/*
		 * if(CMUtil.isWinOS()) { arguments+= USER_ARG; arguments+= QUOTE
		 * +applicationServerAdminUsername + QUOTE ; arguments+= PASSWORD_ARG;
		 * arguments+= QUOTE + applicationServerAdminPassword + QUOTE ;
		 * 
		 * } else { // Java's exec() routine is having problems with using quoted
		 * parameters on Linux. arguments+= USER_ARG; arguments+= "'" +
		 * applicationServerAdminUsername + "'"; arguments+= PASSWORD_ARG; arguments+=
		 * "'" + applicationServerAdminPassword + "'";
		 * 
		 * }
		 */
		arguments += CONNTYPE_ARG;
		arguments += SOAP;
		arguments += PORT_ARG;
		arguments += "8882";
		arguments += HOST_ARG;
		arguments += "localhost";
		arguments += DASH_JAVAOPTION;
		arguments += DASH_XMS512M; // DASH_XMX1024M;
		arguments += DASH_JAVAOPTION;
		arguments += DASH_XMX1024M;
		// arguments+= DASH_JAVAOPTION;
		// arguments+= "-verbose:sizes ";
		arguments += LANG_ARG;
		// arguments+= JACL;
		arguments += LANG;
		arguments += DASH_F;

		// }

		String command = EMPTY_STRING;
		String jvmargs = EMPTY_STRING;
		// if(CMUtil.isCmuiClient) {
		
		arguments+= "\"" + script + "\"";
		jvmargs += " all com.filenet.gcd.LicenseModel ABCD";
		//command = "wsadmin.bat" + arguments + jvmargs;
		command = "wsadmin.bat -conntype SOAP -port 8881 -host localhost -lang jython -f C:\\xmlexport_import\\com.ibm.ecm.configmgr\\src\\com\\ibm\\ecm\\configmgr\\fetchcell.py";
		System.out.println(command);
		StringBuffer sbout = new StringBuffer();
		//
		try {
			Process p;

			// if(CMUtil.isWinOS()) {
			// Windows environment, proceed with old way
			String cmdArray[] = new String[] { "cmd.exe", "/C", command };
			ProcessBuilder pb = new ProcessBuilder(cmdArray);
			pb.directory(workingDirf);
			p = pb.start();

			/*
			 * } else { // unix environment String[] cmdArray = new
			 * String[]{"sh","-c",command}; ProcessBuilder pb = new
			 * ProcessBuilder(cmdArray); pb.directory(workingDirf); p = pb.start(); }
			 */
			InputStream inputstream = p.getInputStream();
			InputStream errorStream = p.getErrorStream();

			//StringBuffer sbout = new StringBuffer();
			StringBuffer sberr = new StringBuffer();

			new OutputProcessor(inputstream, sbout);
			new OutputProcessor(errorStream, sberr);

			p.waitFor();
			inputstream.close();
			errorStream.close();

			// merge error and output strings into the log file
			/*
			 * FileWriter fw = new FileWriter(logname);
			 * fw.write(cleanupScriptOutput(sbout.toString())); //
			 * log.info(cleanupScriptOutput(sbout.toString()));
			 * 
			 * fw.write(cleanupScriptOutput(sberr.toString())); //
			 * log.info(cleanupScriptOutput(sberr.toString()));
			 * 
			 * fw.close();
			 */
			// cleanupScriptOnExit(script);

			// fw.close();
		} catch (IOException ioe) {
			// ecmdb00776196:
			// when the process executing the command fails,
			// it usually includes the command passed, which may contain the password in
			// plain text
			ioe.printStackTrace();
			String localizedMsg1 = ioe.getLocalizedMessage();
			// log.trace(cleanupScriptOutput(localizedMsg));
			// cleanupScriptOnExit(script);
			/*
			 * ConfigurationManagerException cmrte = new
			 * ConfigurationManagerException(NLS.bind(Messages.EXCEPTION_RUNNING_SCRIPT,
			 * wsAdmin, cleanupScriptOutput(localizedMsg)));
			 * log.exceptionBeforeThrowing(cmrte.getLocalizedMessage(), cmrte, className,
			 * methodName); throw cmrte;
			 */

		} catch (Exception e) {
			// log.error(e.getMessage());
			e.printStackTrace();
			// log.trace(cleanupScriptOutput(e.getLocalizedMessage()));
			// cleanupScriptOnExit(script);
			/*
			 * ConfigurationManagerException cmrte = new ConfigurationManagerException(e);
			 * log.exceptionBeforeThrowing(cmrte.getLocalizedMessage(), cmrte, className,
			 * methodName); throw cmrte;
			 */
		}

		return sbout.toString();
		// cleanupScriptOnExit(script);
		// setSOAPTimeout(majorVersion,"C:\\Program
		// Files\\IBM\\WebSphere\\AppServer\\profiles\\AppSrv03", false);

		// log.exit( className, methodName ); //$NON-NLS-1$
	}
	public static String escapeSpecialChar(String message)
    {
        String[] specialCharList = new String[]{BACKSLASH,"&",";","`","'","\"","|","*","?","~","<",">","^","(",")","[","]","{","}","$"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$ //$NON-NLS-16$ //$NON-NLS-17$ //$NON-NLS-18$ //$NON-NLS-19$ 
        for (int i=0;i<specialCharList.length;i++)
        {
            message = escapeSpecialChar(message, specialCharList[i]);
        }
        
        return message;
    }
    
    private static String escapeSpecialChar(String message, String specialChar)
    {
        int i=message.indexOf(specialChar);
        if (i>=0)
        {
            String messageTail=message.substring(i+1);
            //check if there is more than one special char in the string
            if (messageTail.indexOf(specialChar)>=0)
                messageTail=escapeSpecialChar(messageTail, specialChar);
            message=message.substring(0,i)+BACKSLASH+specialChar+messageTail; 
        }

        return message;
    }
	
	public static void main (String args[]) {
		//WebsphereUtil wasconn = new WebSphereUtil();
		String script = "C:\\WASConnection\\WASConnect\\customJVMProperty.py";
		String logname = "C:\\WASConnection\\WASConnect\\customJVMProperty.log";
		WebsphereUtil.runScript(script,logname);
	}
}
	/*
	 * private static void setSOAPTimeout(String version, String wsProfile, boolean
	 * set) { //CMLogger log = Activator.getLogger(); final String className =
	 * "WebsphereUtil"; //$NON-NLS-1$ final String methodName = "setSOAPTimeout()";
	 * //$NON-NLS-1$ log.entry( className, methodName );
	 * 
	 * //first check permissions on properties folder and file //if no write
	 * permission on both properties folder and soap.client.props, log an error and
	 * return String propertiesFolder = wsProfile + File.separator + PROPERTIES;
	 * String props = wsProfile + File.separator + PROPERTIES + File.separator +
	 * SOAP_CLIENT_PROPS; if (!(new File(propertiesFolder).canWrite() && new
	 * File(props).canWrite())) { String path = !(new
	 * File(propertiesFolder).canWrite())?propertiesFolder:props;
	 * log.error(NLS.bind(Messages.ERROR_SETTING_SOAP_TIMEOUT,
	 * Messages.bind(Messages.MISSING_WRITE_PERMISSION,path))); return; }
	 * 
	 * try {
	 * 
	 * String backup = props + BACKUP_EXT; if (set == true) { if (new
	 * File(props).exists() == true) { FileUtil.copy(new File(props), new
	 * File(backup)); new File(props).delete(); FileReader inFR = new
	 * FileReader(backup); BufferedReader inBR = new BufferedReader(inFR);
	 * FileWriter fw = new FileWriter(props); String aLine; while ((aLine =
	 * inBR.readLine()) != null) { if (aLine.indexOf(COM_IBM_SOAP_REQUEST_TIMEOUT)
	 * >= 0) fw.write(COM_IBM_SOAP_REQUEST_TIMEOUT+0+CMUtil.getPlatformEOL()); else
	 * fw.write(aLine+CMUtil.getPlatformEOL()); } inFR.close(); inBR.close();
	 * fw.close(); } } else { if (new File(backup).exists() == true) {
	 * FileUtil.copy(new File(backup), new File(props)); new File(backup).delete();
	 * } }
	 * 
	 * } catch (Exception e) { log.exception(e.getLocalizedMessage(), e, className,
	 * methodName); }
	 * 
	 * log.exit( className, methodName ); }
	 */

	/*
	 * private static void setWASCredentials(String wsProfile, String userName,
	 * String password) { CMLogger log = Activator.getLogger(); final String
	 * className = "WebsphereUtil"; //$NON-NLS-1$ final String methodName =
	 * "setlogingpwd()"; //$NON-NLS-1$ log.entry( className, methodName );
	 * 
	 * String propertiesFolder = wsProfile + File.separator + PROPERTIES; String
	 * props = wsProfile + File.separator + PROPERTIES + File.separator +
	 * SOAP_CLIENT_PROPS; if (!(new File(propertiesFolder).canWrite() && new
	 * File(props).canWrite())) { String path = !(new
	 * File(propertiesFolder).canWrite())?propertiesFolder:props;
	 * log.error(NLS.bind(Messages.ERROR_SETTING_SOAP_CREDENTIALS,
	 * Messages.bind(Messages.MISSING_WRITE_PERMISSION,path))); return; }
	 * 
	 * try { Properties prop = new Properties(); FileInputStream in = new
	 * FileInputStream(props); prop.load(in);
	 * 
	 * String loginprompt = prop.getProperty(COM_IBM_SOAP_LOGINSOURCE); String
	 * secEnabled = prop.getProperty(COM_IBM_SOAP_SECENABLED);
	 * 
	 * //System.out.println("loginprompt set to " + loginprompt); in.close();
	 * boolean updateuser = false, updatepwd = false, updatesource = false,
	 * updatesec = false, updateProps = false;
	 * 
	 * String backup = props + ".backup";
	 * 
	 * //if (loginprompt.equalsIgnoreCase("prompt")) if
	 * ((loginprompt.equalsIgnoreCase("prompt") &&
	 * (secEnabled.equalsIgnoreCase("false"))) ||
	 * (loginprompt.equalsIgnoreCase("nothing") &&
	 * (secEnabled.equalsIgnoreCase("false"))) ||
	 * (loginprompt.equalsIgnoreCase("prompt") &&
	 * (secEnabled.equalsIgnoreCase("true"))) ||
	 * (loginprompt.equalsIgnoreCase("nothing") &&
	 * (secEnabled.equalsIgnoreCase("true")))) updateProps = true;
	 * 
	 * if (updateProps) { if (new File(props).exists()) { FileUtil.copy(new
	 * File(props), new File(backup)); new File(props).delete(); FileReader inFR =
	 * new FileReader(backup); BufferedReader inBR = new BufferedReader(inFR);
	 * FileWriter fw = new FileWriter(props); String aLine; while ((aLine =
	 * inBR.readLine()) != null) { if ((aLine.indexOf(COM_IBM_SOAP_LOGINUSERID) >=
	 * 0) && !updateuser) {
	 * fw.write(COM_IBM_SOAP_LOGINUSERID+userName+CMUtil.getPlatformEOL());
	 * updateuser = true; } else if ((aLine.indexOf(COM_IBM_SOAP_LOGINPASSWORD) >=
	 * 0) && !updatepwd) {
	 * fw.write(COM_IBM_SOAP_LOGINPASSWORD+password+CMUtil.getPlatformEOL());
	 * updatepwd = true; } else if ((aLine.indexOf(COM_IBM_SOAP_LOGINSOURCE+"=") >=
	 * 0) && !updatesource) {
	 * fw.write(COM_IBM_SOAP_LOGINSOURCE+"=nothing"+CMUtil.getPlatformEOL());
	 * updatesource = true; } else if ((aLine.indexOf(COM_IBM_SOAP_SECENABLED+"=")
	 * >= 0) && !updatesec) {
	 * fw.write(COM_IBM_SOAP_SECENABLED+"=true"+CMUtil.getPlatformEOL()); updatesec
	 * = true; } else fw.write(aLine+CMUtil.getPlatformEOL()); } inFR.close();
	 * inBR.close(); fw.close(); } if (updateuser || updatepwd || updatesource ||
	 * updatesec) { if (new File(backup).exists() == true) { //FileUtil.copy(new
	 * File(backup), new File(props)); new File(backup).delete(); } }
	 * 
	 * 
	 * //Encode soap.client.props file 20224 String pwdTool = ""; String command =
	 * wsProfile; String workingDir = ""; if (CMUtil.isWinOS()) { pwdTool =
	 * "PropFilePasswordEncoder.bat"; workingDir = wsProfile + WINDOWS_SLASH_BIN; }
	 * else { pwdTool = "PropFilePasswordEncoder.sh"; workingDir = wsProfile +
	 * UNIX_SLASH_BIN; }
	 * 
	 * String soapProps = wsProfile + File.separator + PROPERTIES + File.separator +
	 * SOAP_CLIENT_PROPS; String arguments = QUOTE + soapProps + QUOTE + " " +
	 * "com.ibm.SOAP.loginPassword" ; command = QUOTE + workingDir + File.separator
	 * + pwdTool + QUOTE + " " + arguments; //-profileName wsProfile (Optional)
	 * 
	 * String cmdArray[] = null; if(CMUtil.isWinOS()) { // Windows environment,
	 * proceed with old way cmdArray = new String[]{"cmd.exe","/C",command}; } else
	 * { // unix environment cmdArray = new String[]{"sh","-c",command}; }
	 * 
	 * ProcessBuilder builder = new ProcessBuilder(cmdArray); File workingDirf = new
	 * File (workingDir); builder.redirectErrorStream(true);
	 * builder.directory(workingDirf);
	 * 
	 * Process p = builder.start(); BufferedReader r = new BufferedReader(new
	 * InputStreamReader(p.getInputStream()));
	 * 
	 * p.waitFor(); String line; while (true) { line = r.readLine(); if (line ==
	 * null) { break; } //System.out.println(line); }
	 * 
	 * r.close();
	 * 
	 * }
	 * 
	 * } catch (Exception e) { log.exception(e.getLocalizedMessage(), e, className,
	 * methodName); }
	 * 
	 * }
	 */
	/*
	 * public static String escapeSpecialChar(String message) { String[]
	 * specialCharList = new String[] { BACKSLASH, "&", ";", "`", "'", "\"", "|",
	 * "*", "?", "~", "<", ">", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	 * //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
	 * //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ "^", "(", ")", "[", "]", "{",
	 * "}", "$" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	 * //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ for (int i = 0; i <
	 * specialCharList.length; i++) { message = escapeSpecialChar(message,
	 * specialCharList[i]); }
	 * 
	 * return message; }
	 * 
	 * private static String escapeSpecialChar(String message, String specialChar) {
	 * int i = message.indexOf(specialChar); if (i >= 0) { String messageTail =
	 * message.substring(i + 1); // check if there is more than one special char in
	 * the string if (messageTail.indexOf(specialChar) >= 0) messageTail =
	 * escapeSpecialChar(messageTail, specialChar); message = message.substring(0,
	 * i) + BACKSLASH + specialChar + messageTail; }
	 * 
	 * return message; }
	 */

	/*
	 * private static void cleanupScriptOnExit(String script) { CMLogger log =
	 * Activator.getLogger(); String restofline = null; int index = 0; int
	 * passwordlen = 0;
	 * 
	 * String[] passwordKeys = new
	 * String[]{SET__ALIAS_PASSWORD,BIND_PASSWORD_ARG,SET__SERVER_PASSWORD,
	 * SET__BIND_PASSWORD};
	 * 
	 * try { if (new File(script).exists()) { String backup = script + BACKUP_EXT;
	 * FileUtil.copy(new File(script), new File(backup)); new File(script).delete();
	 * FileReader inFR = new FileReader(backup); BufferedReader inBR = new
	 * BufferedReader(inFR); FileWriter fw = new FileWriter(script); String aLine;
	 * while ((aLine = inBR.readLine()) != null) { if (aLine.indexOf(POUND) != 0) {
	 * Fix it so that it will create a configureldap.tcl file output. This code will
	 * put ***** on the password field. Skip the lines with comment # due to a bug.
	 * 
	 * for (int i=0;i<passwordKeys.length;i++) { if
	 * (aLine.indexOf(passwordKeys[i])>=0) { index =
	 * aLine.indexOf(passwordKeys[i])+passwordKeys[i].length()+1;
	 * 
	 * // restofline point to beginning of password restofline = aLine.substring
	 * (index); restofline.trim();
	 * 
	 * get to end of password looking for space, if there is space, mask the
	 * password and print out rest of line. If there is no space, the password is at
	 * end of line, mask the password.
	 * 
	 * passwordlen = restofline.indexOf(' '); if (passwordlen>0) aLine =
	 * aLine.substring(0,index) + PASSWORD_MASK +restofline.substring(passwordlen);
	 * else aLine = aLine.substring(0,index) + PASSWORD_MASK;
	 * 
	 * 
	 * } } } fw.write(aLine+CMUtil.getPlatformEOL()); } inFR.close(); inBR.close();
	 * fw.close(); new File(backup).delete(); } } catch (Exception e) {
	 * log.exception(e.getLocalizedMessage(), e, "WebsphereUtil",
	 * "cleanupScriptOnExit"); //$NON-NLS-1$ //$NON-NLS-2$ }
	 * 
	 * }
	 */

	/*
	 * private static String cleanupScriptOutput(String output) { String[]
	 * passwordKeys = new String[] { PASSWORD_ARG2, BIND_PASSWORD_ARG }; String
	 * restofline = null; int index = 0; int passwordlen = 0; for (int i = 0; i <
	 * passwordKeys.length; i++) { if (output.indexOf(passwordKeys[i]) >= 0) { index
	 * = output.indexOf(passwordKeys[i]) + passwordKeys[i].length() + 1; //
	 * restofline point to beginning of password restofline =
	 * output.substring(index); restofline.trim();
	 * 
	 * get to end of password looking for space, if there is space, mask the
	 * password and print out rest of line. If there is no space, the password is at
	 * end of line, mask the password.
	 * 
	 * 
	 * passwordlen = restofline.indexOf(' '); if (passwordlen > 0) output =
	 * output.substring(0, index) + PASSWORD_MASK +
	 * restofline.substring(passwordlen); else output = output.substring(0, index) +
	 * PASSWORD_MASK; } } return output; }
	 */

	/*
	 * Reverting action performed for 10944 public static String getWasVersion
	 * (String ApplicationServerInstallationFolder) { String version =""; String
	 * WAS_PRODUCT_FILE_LOCATION =
	 * ApplicationServerInstallationFolder+File.separator+"properties"+File.
	 * separator+"version"+File.separator+"WAS.product"; try { File fXmlFile = new
	 * File(WAS_PRODUCT_FILE_LOCATION); DocumentBuilderFactory dbFactory =
	 * DocumentBuilderFactory.newInstance(); DocumentBuilder dBuilder =
	 * dbFactory.newDocumentBuilder(); dBuilder.setEntityResolver(new
	 * EntityResolver() { public InputSource resolveEntity(String publicId, String
	 * systemId) throws SAXException, IOException { if
	 * (systemId.contains("product.dtd")) { return new InputSource(new
	 * ByteArrayInputStream("<?xml version='1.0' encoding='UTF-8'?>".getBytes())); }
	 * else return null; } }); Document doc = dBuilder.parse(fXmlFile);
	 * doc.getDocumentElement().normalize(); NodeList nList = null; nList =
	 * doc.getElementsByTagName("version") ; for (int temp = 0; temp <
	 * nList.getLength(); temp++) { Node nNode = nList.item(temp);
	 * 
	 * if (nNode.getNodeType() == Node.ELEMENT_NODE ) { Element eElement = (Element)
	 * nNode; version = eElement.getTextContent(); } } } catch (Exception e) {
	 * e.printStackTrace(); } return version; }
	 */

//}
