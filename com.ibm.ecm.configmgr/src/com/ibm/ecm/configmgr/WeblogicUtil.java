
/* IBM Confidential
 * 
 * OCO Source Materials
 * 
 * 5724-R81
 * 
 * © Copyright IBM Corp. 2008, 2009, 2018, 2020, 2021
 * 
 * The source code for this program is not published or otherwise
 * divested of its trade secrets, irrespective of what has
 * been deposited with the U.S. Copyright Office.
 */

package com.ibm.ecm.configmgr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
/*import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;*/

//import org.eclipse.osgi.util.NLS;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;

/*import com.ibm.ecm.configmgr.engine.ConfigurationManagerException;
import com.ibm.ecm.configmgr.engine.util.CMLogger;
import com.ibm.ecm.configmgr.engine.util.CMUtil;
import com.ibm.ecm.configmgr.engine.util.FileUtil;
import com.ibm.ecm.configmgr.engine.rcp.Activator;*/

public class WeblogicUtil {

    private static final String PASSWORD_MASK = "******"; //$NON-NLS-1$
    private static final String CREDENTIAL = "credential="; //$NON-NLS-1$
    private static final String WL_ADMIN_PASSWORD = "wlAdminPassword="; //$NON-NLS-1$
    private static final String DB_PASSWORD = "dbPassword="; //$NON-NLS-1$
    private static final String BACKUP_EXT = ".backup"; //$NON-NLS-1$
    //private static final String QUOTEWAS = "\""; //$NON-NLS-1$
    //private static final String QUOTE = "\"";
    //private static final String PATH_FOR_WLST_SH_NONWINDOWS = "/common/bin/wlst.sh "; //$NON-NLS-1$
    private static final String PATH_FOR_WLST_CMD_WINDOWS = "\\oracle_common\\common\\bin\\wlst.cmd "; //$NON-NLS-1$
	private static final String PATH_FOR_WLST_SH_NONWINDOWS = "/oracle_common/common/bin/wlst.sh "; //$NON-NLS-1$
    private static final String PATH_FOR_WLST_BIN_WINDOWS = "\\oracle_common\\common\\bin"; //$NON-NLS-1$
	private static final String PATH_FOR_WLST_BIN_NONWINDOWS = "/oracle_common/common/bin"; //$NON-NLS-1$
	private static final String CONFIG_FOLDER = "config";
	private static final String CONFIG_XML = "config.xml";
    public static void runScript(String applicationServerInstallationFolder, String script, String logname, String version) 
    {
        //CMLogger log = Activator.getLogger();
        final String methodName = "runScript()"; //$NON-NLS-1$
        String className = "WeblogicUtil";
        //log.entry( className, methodName ); //$NON-NLS-1$
        
        //log.info(NLS.bind(Messages.RUN_SCRIPT_START_DEBUG_MSG, script));

        String wlAdmin;
        String command= null;
        //String args = "";
        String workingDir = null;
        File  workingDirf = null;
        /*if (CMUtil.isWinOS()) 
            wlAdmin = "wlst.cmd";//applicationServerInstallationFolder+ PATH_FOR_WLST_CMD_WINDOWS;
        else
            wlAdmin = "wlst.sh";//applicationServerInstallationFolder+ PATH_FOR_WLST_SH_NONWINDOWS;
*/
        //String args[] = new String[2];

        if (CMUtil.isWinOS()) 
        {
        	//wlAdmin = "wlst.cmd";
        	wlAdmin = CMUtil.appServerInstallFolder + PATH_FOR_WLST_CMD_WINDOWS ;// "\\oracle_common\\common\\bin");
        	workingDir = CMUtil.appServerInstallFolder + PATH_FOR_WLST_BIN_WINDOWS;
    		//script = script.replace("Program Files (x86)", "Progra~2");
    		//logname = logname.replace("Program Files (x86)", "Progra~2");
    		//command = QUOTE + wlAdmin + QUOTE + QUOTE + script + QUOTE;
        } 
        else 
        {
        	wlAdmin = applicationServerInstallationFolder+ PATH_FOR_WLST_SH_NONWINDOWS;
        	workingDir = applicationServerInstallationFolder + PATH_FOR_WLST_BIN_NONWINDOWS;
        	command = wlAdmin + script;
        	
        }
        	workingDirf = new File (workingDir);
        	
        	/*args[0] = wlAdmin;
            args[1] = QUOTE + script + QUOTE; */
        	//script = script.replaceAll("\\", "\\\\");
        	//workingDirf = (new File (applicationServerInstallationFolder+"\\oracle_common\\common\\bin"));
        	//wlAdmin = "wlst.cmd";
        	//wlAdmin = workingDirf+"wlst.cmd";
            //command = QUOTE + script + QUOTE ; //wlAdmin
        //} else {
            /*args[0] = wlAdmin;
            args[1] = script;*/
        	//wlAdmin = applicationServerInstallationFolder+ PATH_FOR_WLST_SH_NONWINDOWS_FOR_12c1221;
        	
        	/*if ("12c(12.2.1)".equalsIgnoreCase(version)){
        		//System.out.println("applicationServerInstallationFolder=="+applicationServerInstallationFolder);
        		wlAdmin = applicationServerInstallationFolder+ PATH_FOR_WLST_SH_NONWINDOWS_FOR_12c1221;
        	} else {
        		wlAdmin = applicationServerInstallationFolder+ PATH_FOR_WLST_SH_NONWINDOWS;
        	}*/
        	//wlAdmin = applicationServerInstallationFolder+ PATH_FOR_WLST_SH_NONWINDOWS;
        	//command = wlAdmin + script;
       // }
        //System.out.println("command = "+command + " : script = " + script);
        //Runtime r = Runtime.getRuntime();
        try {
        	
            Process p ;
        	if(CMUtil.isWinOS()) {
        		// Windows environment, proceed with old way   
        		String cmdArray[] = new String[]{"cmd.exe","/C","wlst.cmd","\""+script+"\""};
        		//String cmdArray[] = new String[]{"cmd.exe","/C",command};
        		ProcessBuilder pb = new ProcessBuilder(cmdArray);
        		pb.directory(workingDirf);
        		/*if ("12c(12.2.1)".equalsIgnoreCase(version)){
        			pb.directory(new File(applicationServerInstallationFolder+"\\oracle_common\\common\\bin\\"));
        		} else {				
					pb.directory(new File(applicationServerInstallationFolder+"\\common\\bin\\"));
				}*/
        		//log.info("exec "+Arrays.toString(cmdArray));
        		p = pb.start();
        		
        	}
        	else {
        		//log.info("exec"+args);
        		//p = r.exec(args, null);
        		String cmdArray[] = new String[]{"sh","-c",command};
        		ProcessBuilder pb = new ProcessBuilder(cmdArray);
        		//pb.directory(workingDirf);
        		pb.directory(new File(applicationServerInstallationFolder+"/oracle_common/common/bin/"));//18766
        		/*if ("12c(12.2.1)".equalsIgnoreCase(version)){
        			pb.directory(new File(applicationServerInstallationFolder+"/oracle_common/common/bin/"));
        		}else{				
					pb.directory(new File(applicationServerInstallationFolder+"/common/bin/"));
				}*/
        		//log.info("exec "+Arrays.toString(cmdArray));
        		p = pb.start();
        	}
            InputStream inputstream = p.getInputStream();
            InputStream errorStream = p.getErrorStream();
            StringBuffer sbout = new StringBuffer();
            StringBuffer sberr = new StringBuffer();

            new OutputProcessor(inputstream,sbout);
            new OutputProcessor(errorStream,sberr);
            
            p.waitFor();
            inputstream.close();
            errorStream.close();
            
            //merge error and output strings into the log file
            FileWriter fw = new FileWriter(logname);
            fw.write(cleanupScriptOutput(sbout.toString()));
            //log.info(cleanupScriptOutput(sbout.toString()));

            fw.write(cleanupScriptOutput(sberr.toString()));
            //log.info(cleanupScriptOutput(sberr.toString()));
            
            fw.close();
            cleanupScriptOnExit(script);
        }
        catch (IOException ioe)
        {
        	ioe.printStackTrace();
            //log.error(cleanupScriptOutput(ioe.getLocalizedMessage()));
            cleanupScriptOnExit(script);
            //ConfigurationManagerException cmrte = new ConfigurationManagerException(NLS.bind(Messages.EXCEPTION_RUNNING_SCRIPT, wlAdmin, cleanupScriptOutput(ioe.getLocalizedMessage())));
            //log.exceptionBeforeThrowing(cmrte.getLocalizedMessage(), cmrte, className, methodName);
            //throw cmrte;
        } catch (Exception e) {
        	e.printStackTrace();
            cleanupScriptOnExit(script);
            
            //log.error(cleanupScriptOutput(e.getLocalizedMessage()));
            //ConfigurationManagerException cmrte = new ConfigurationManagerException(e);
            //log.exceptionBeforeThrowing(cmrte.getLocalizedMessage(), cmrte, className, methodName);
            //throw cmrte;
        }
        
        //log.exit( className, methodName ); //$NON-NLS-1$
    }
    
    private static void cleanupScriptOnExit(String script)
    {
        //CMLogger log = Activator.getLogger();

        String[] passwordKeys = new String[]{DB_PASSWORD,WL_ADMIN_PASSWORD,CREDENTIAL}; 
        try
        {
            if (new File(script).exists()) {
                String backup = script + BACKUP_EXT;
                FileUtil.copy(new File(script), new File(backup));
                new File(script).delete();
                FileReader inFR = new FileReader(backup);
                BufferedReader inBR = new BufferedReader(inFR);
                FileWriter fw = new FileWriter(script);
                String aLine;
                while ((aLine = inBR.readLine()) != null) {
                    for (int i=0;i<passwordKeys.length;i++)
                    {
                        if (aLine.indexOf(passwordKeys[i])>=0)
                        {
                            int index = aLine.indexOf(passwordKeys[i])+passwordKeys[i].length()+1;
                            aLine = aLine.substring(0,index) +PASSWORD_MASK; 
                        }
                        
                    }
                    fw.write(aLine+CMUtil.getPlatformEOL());
                }
                inFR.close();
                inBR.close();
                fw.close();
                new File(backup).delete();
            }
        }
        catch (Exception e)
        {
        	e.printStackTrace();
           // log.exception(e.getLocalizedMessage(), e, "WeblogicUtil", "cleanupScriptOnExit");
        }
    }

    private static String cleanupScriptOutput(String output)
    {
        String[] passwordKeys = new String[]{};
        for (int i=0;i<passwordKeys.length;i++)
        {
            if (output.indexOf(passwordKeys[i])>=0)
            {
                int index = output.indexOf(passwordKeys[i])+passwordKeys[i].length()+1;
                output = output.substring(0,index) +PASSWORD_MASK + output.substring(index+7); 
            }
        }
        return output;
    }
    
    /*
     * Util to find WL version based on location of cofig.xml located at DOMAIN_DIRECTORY/config/config.xml
     */
    public static void findWLVersion (String val) {
    	
    	//CMUtil.appServerVersion
    	/*if(new File(val+File.separator+CONFIG_FOLDER+File.separator+CONFIG_XML).exists()) {
    	try {

    		File fXmlFile = new File(val+File.separator+CONFIG_FOLDER+File.separator+CONFIG_XML);
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	    	doc.getDocumentElement().normalize();
	    	NodeList nList =null;
	    	String wlVersion = "";
	    	nList = doc.getElementsByTagName("domain-version") ;
	    	for (int temp = 0; temp < nList.getLength(); temp++) {
	    			Node nNode = nList.item(temp);
	    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	    			Element eElement = (Element) nNode;
	    				String[] versionArray = eElement.getTextContent().split("\\."); //12.1.2.0.0
	    				wlVersion =versionArray[0]+"."+versionArray[1]+"."+versionArray[2];
	    				CMUtil.WLVersion = CMUtil.WL_VERSION_MAP.get(wlVersion);//18765
	    				//System.out.println("CMUtil.WLVersion = " + CMUtil.WLVersion);
	    		}
	    	}
	        } catch (Exception e) {
	        	 //CMLogger log = Activator.getLogger();
	        	 //log.exception(e.getLocalizedMessage(), e, "WeblogicUtil", "findWLVersion");
	        	e.printStackTrace();
	        }
    }
    	}*/

  /*    public static void updateDeploymentPlan (String profilePath) {
    	 String filePath = CMUtil.CANONICAL_CM_PATH+File.separator+CMUtil.getScriptsSubdir()+File.separator+"planNew.xml";
    	 //String updated = CMUtil.CANONICAL_CM_PATH+File.separator+CMUtil.getScriptsSubdir()+File.separator+"plan.xml";
    	 String updated = profilePath + File.separator+"plan.xml"; // 14192
         
    	 File xmlFile = new File(filePath);
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder;
         try {
             dBuilder = dbFactory.newDocumentBuilder();
             Document doc = dBuilder.parse(xmlFile);
             doc.getDocumentElement().normalize();
             //update Element value
             updateElementValue(doc);
             //write the updated document to file or console
             doc.getDocumentElement().normalize();
             TransformerFactory transformerFactory = TransformerFactory.newInstance();
             Transformer transformer = transformerFactory.newTransformer();
             DOMSource source = new DOMSource(doc);
             StreamResult result = new StreamResult(new File(updated));
             transformer.setOutputProperty(OutputKeys.INDENT, "yes");
             transformer.transform(source, result);
             //System.out.println("XML file updated successfully");
             
         } //catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
         	catch (Exception e1) {
             e1.printStackTrace();
         }
     }

    

     private static void updateElementValue(Document doc) {
         NodeList employees = doc.getElementsByTagName("variable");
         Element emp = null;
         //loop for each employee
         for(int i=0; i<employees.getLength();i++){
             emp = (Element) employees.item(i);
             Node name = emp.getElementsByTagName("name").item(0).getFirstChild();
             
             //JNDIXAName,LicenseModel
             // CMUtil.jdbcDataSourceName = jdbcDataSourceName;
             
            if("JNDIName".equalsIgnoreCase(name.getNodeValue()) ) {
         	   Node value = emp.getElementsByTagName("value").item(0).getFirstChild();
         	   value.setNodeValue(CMUtil.jdbcDataSourceName);
         	  CMUtil.jdbcDataSourceName="";
            } else if("JNDIXAName".equalsIgnoreCase(name.getNodeValue())) {
            	Node value = emp.getElementsByTagName("value").item(0).getFirstChild();
            	value.setNodeValue(CMUtil.jdbcDataSourceXAName);
            	CMUtil.jdbcDataSourceXAName="";
            
            } else if("LicenseModel".equalsIgnoreCase(name.getNodeValue())){
            	Node value = emp.getElementsByTagName("value").item(0).getFirstChild();
            	value.setNodeValue(CMUtil.LicenseModel); 
            	CMUtil.LicenseModel="";
            
            }
            
         }

    }
*/
    }
}
