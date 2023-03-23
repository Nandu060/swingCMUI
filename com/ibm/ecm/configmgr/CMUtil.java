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

public class CMUtil {

	public static String profileName="";
	public static boolean profileOpen = false;
	private static final String UNIX_EOL = "\n"; //$NON-NLS-1$
	private static final String WIN_EOL = "\r\n"; //$NON-NLS-1$
	private static final String WIN = "Win"; //$NON-NLS-1$
	private static final String OS_NAME = "os.name"; //$NON-NLS-1$
	public static String appServer = "";
	public static String ldapRepoType="";
	public static String dbType = "";
	public static String deployMode = "";
	public static String wasVersion = "";
	public static String ldapServerType = "";
	public static String implementorID = "";
	public static String wlVersion = "";
	public static String appServerVersion = "";
	public static String appServerInstallFolder = "";
	public static String appServerAdminUser = "";
	public static String appServerAdminPassword = "";
	public static String appServerSOAP = "";
	public static String appServerHostName = "";
	public static String appServerTimeout = "";
	public static String appServerProfile = "";
	public static String appServerCell = "";
	public static String turnOffSSL = "";
	public static String securityDomain = "";
	public static String SSLEnabled = "";
	public static String appServerDomain = "";
	public static String targetName = "";
	
	
	
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
	
	public static void getGCDNames(String profilePath, ArrayList<String> properties) {
		
		/*
		 * for (int i=0; i < properties.size(); i++)
		 * System.out.println(properties.get(i));
		 */
		
		  profilePath += File.separator+"configurejdbcgcd.xml";
	        File xmlFile = new File(profilePath);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder;
	        try {
	            dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(xmlFile);
	            doc.getDocumentElement().normalize();
	            getDSValues(doc, xmlFile, properties);
	            
	           } 
	        	catch (Exception e1) {
	            e1.printStackTrace();
	        }
	}
	//public static void getImplementoID()
    private static void getDSValues(Document doc, File file, ArrayList <String> props) {
   	 NodeList employees = doc.getElementsByTagName("property");
       Element emp = null;        
       //System.out.println("Employees length = " + employees.getLength());
       //loop for each employee
       for(int i=0; i<employees.getLength();i++){
           emp = (Element) employees.item(i);     
           //System.out.println(emp.getAttribute("name"));
           if(emp.getAttribute("name").equalsIgnoreCase("JDBCDataSourceName")) { 
        	   emp = (Element) employees.item(i);
        	   Node nvalue = emp.getElementsByTagName("value").item(0).getFirstChild();
               nvalue.setNodeValue(props.get(i));               
           } else if (emp.getAttribute("name").equalsIgnoreCase("JDBCDataSourceXAName")){ //this three
        	   emp = (Element) employees.item(i);
        	   Node nvalue = emp.getElementsByTagName("value").item(0).getFirstChild();
               nvalue.setNodeValue(props.get(i));
           } else if (emp.getAttribute("name").equalsIgnoreCase("DatabaseServerName")){ //this three
        	   emp = (Element) employees.item(i);
        	   Node nvalue = emp.getElementsByTagName("value").item(0).getFirstChild();
               nvalue.setNodeValue(props.get(i));
           } else if (emp.getAttribute("name").equalsIgnoreCase("DatabasePortNumber")){ //this three
        	   emp = (Element) employees.item(i);
        	   Node nvalue = emp.getElementsByTagName("value").item(0).getFirstChild();
               nvalue.setNodeValue(props.get(i));
           } else if (emp.getAttribute("name").equalsIgnoreCase("DatabaseName")){ //this three
        	   emp = (Element) employees.item(i);
        	   Node nvalue = emp.getElementsByTagName("value").item(0).getFirstChild();
               nvalue.setNodeValue(props.get(i));
           } else if (emp.getAttribute("name").equalsIgnoreCase("ServiceNameEnabled")){ //this three
        	   emp = (Element) employees.item(i);
        	   Node nvalue = emp.getElementsByTagName("value").item(0).getFirstChild();
               nvalue.setNodeValue(props.get(i));
           } else if (emp.getAttribute("name").equalsIgnoreCase("DatabaseUsername")){ //this three
        	   emp = (Element) employees.item(i);
        	   Node nvalue = emp.getElementsByTagName("value").item(0).getFirstChild();
               nvalue.setNodeValue(props.get(i));
           } else if (emp.getAttribute("name").equalsIgnoreCase("DatabasePassword")){ //this three
        	   emp = (Element) employees.item(i);
        	   Node nvalue = emp.getElementsByTagName("value").item(0).getFirstChild();
               nvalue.setNodeValue(props.get(i));
           } else if (emp.getAttribute("name").equalsIgnoreCase("OJDBC Jar")){ //this three
        	   emp = (Element) employees.item(i);
        	   Node nvalue = emp.getElementsByTagName("value").item(0).getFirstChild();
               nvalue.setNodeValue(props.get(i));
           } else if (emp.getAttribute("name").equalsIgnoreCase("Minimum connections")){ //this three
        	   emp = (Element) employees.item(i);
        	   Node nvalue = emp.getElementsByTagName("value").item(0).getFirstChild();
               nvalue.setNodeValue(props.get(i));
           } else if (emp.getAttribute("name").equalsIgnoreCase("Maximum connections")){ //this three
        	   emp = (Element) employees.item(i);
        	   Node nvalue = emp.getElementsByTagName("value").item(0).getFirstChild();
               nvalue.setNodeValue(props.get(i));
           } else if (emp.getAttribute("name").equalsIgnoreCase("Unused timeout (seconds)")){ //this three
        	   emp = (Element) employees.item(i);
        	   Node nvalue = emp.getElementsByTagName("value").item(0).getFirstChild();
               nvalue.setNodeValue(props.get(i));
           } else if (emp.getAttribute("name").equalsIgnoreCase("Reap time (seconds)")){ //this three
        	   emp = (Element) employees.item(i);
        	   Node nvalue = emp.getElementsByTagName("value").item(0).getFirstChild();
               nvalue.setNodeValue(props.get(i));
           } else if (emp.getAttribute("name").equalsIgnoreCase("Script")){ //this three
        	   emp = (Element) employees.item(i);
			  Node nvalue = emp.getElementsByTagName("value").item(0).getFirstChild();
			  nvalue.setNodeValue(props.get(i));
           } else if (emp.getAttribute("name").equalsIgnoreCase("tempDir")){ //this three
        	   emp = (Element) employees.item(i);
				/*
				 * Node nvalue = emp.getElementsByTagName("value").item(0).getFirstChild();
				 * nvalue.setNodeValue(props.get(i));
				 */
           } else if (emp.getAttribute("name").equalsIgnoreCase("DBFailoverEnabled")){ //this three
        	   emp = (Element) employees.item(i);
        	   Node nvalue = emp.getElementsByTagName("value").item(0).getFirstChild();
        	   nvalue.setNodeValue(props.get(i));
           }
          
       }
       
       try {
       	TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(doc);
		StreamResult sResult = new StreamResult(file);
		transformer.transform(source, sResult);
       } catch (Exception ue) {
			ue.getMessage();
       }
    }
    
    public static void loadAppServer(String file)
    {
    	try 
        {
        	DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder(); 
            Document doc2 = documentBuilder.parse(new File(file));
            NodeList nd = doc2.getElementsByTagName("configuration"); 
            for(int i=0;i<nd.getLength();i++)
            {
                Node p = nd.item(i);
                if(p.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element property = (Element)p;
                    appServer = property.getAttribute("implementorid");
                }
            }
            System.out.println(appServer);
            //CMUtil.appServer = appServer;
            NodeList nd1 = doc2.getElementsByTagName("property");
            for(int i=0;i<nd1.getLength();i++)
            {
            	Node p = nd1.item(i);
            	if(p.getNodeType() == Node.ELEMENT_NODE)
            	{
            		  Element property = (Element)p;
					  String name = property.getAttribute("name");
					  if(name.equalsIgnoreCase("ApplicationServerVersion"))
					  {
					  NodeList nd2 = property.getChildNodes();
					  for(int j=0;j<nd2.getLength();j++)
					  {
						  Node q = nd2.item(j);
						  if(q.getNodeType()== Node.ELEMENT_NODE)
						  {
							  Element value = (Element)q;
							  if(value.getTagName().equalsIgnoreCase("value"))
							  {
								  appServerVersion = value.getTextContent();
								  System.out.println(appServerVersion);
							  }
						  }
					  }
					  }
					  if(name.equalsIgnoreCase("ApplicationServerInstallationFolder"))
					  {
					  NodeList nd2 = property.getChildNodes();
					  for(int j=0;j<nd2.getLength();j++)
					  {
						  Node q = nd2.item(j);
						  if(q.getNodeType()== Node.ELEMENT_NODE)
						  {
							  Element value = (Element)q;
							  if(value.getTagName().equalsIgnoreCase("value"))
							  {
								  appServerInstallFolder = value.getTextContent();
								  System.out.println(appServerInstallFolder);
							  }
						  }
					  }
					  }
					  if(name.equalsIgnoreCase("ApplicationServerAdminUsername"))
					  {
					  NodeList nd2 = property.getChildNodes();
					  for(int j=0;j<nd2.getLength();j++)
					  {
						  Node q = nd2.item(j);
						  if(q.getNodeType()== Node.ELEMENT_NODE)
						  {
							  Element value = (Element)q;
							  if(value.getTagName().equalsIgnoreCase("value"))
							  {
								  appServerAdminUser = value.getTextContent();
								  System.out.println(appServerAdminUser);
							  }
						  }
					  }
					  }
					  if(name.equalsIgnoreCase("ApplicationServerAdminPassword"))
					  {
					  NodeList nd2 = property.getChildNodes();
					  for(int j=0;j<nd2.getLength();j++)
					  {
						  Node q = nd2.item(j);
						  if(q.getNodeType()== Node.ELEMENT_NODE)
						  {
							  Element value = (Element)q;
							  if(value.getTagName().equalsIgnoreCase("value"))
							  {
								  appServerAdminPassword = value.getTextContent();
								  System.out.println(appServerAdminPassword);
							  }
						  }
					  }
					  }
					  if(name.equalsIgnoreCase("ApplicationServerSoapPort"))
					  {
					  NodeList nd2 = property.getChildNodes();
					  for(int j=0;j<nd2.getLength();j++)
					  {
						  Node q = nd2.item(j);
						  if(q.getNodeType()== Node.ELEMENT_NODE)
						  {
							  Element value = (Element)q;
							  if(value.getTagName().equalsIgnoreCase("value"))
							  {
								  appServerSOAP = value.getTextContent();
								  System.out.println(appServerSOAP);
							  }
						  }
					  }
					  }
					  if(name.equalsIgnoreCase("ApplicationServerHostName"))
					  {
					  NodeList nd2 = property.getChildNodes();
					  for(int j=0;j<nd2.getLength();j++)
					  {
						  Node q = nd1.item(j);
						  if(q.getNodeType()== Node.ELEMENT_NODE)
						  {
							  Element value = (Element)q;
							  if(value.getTagName().equalsIgnoreCase("value"))
							  {
								  appServerHostName = value.getTextContent();
								  System.out.println(appServerHostName);
							  }
						  }
					  }
					  }
					  if(name.equalsIgnoreCase("ApplicationServerTransactionTimeout"))
					  {
					  NodeList nd2 = property.getChildNodes();
					  for(int j=0;j<nd2.getLength();j++)
					  {
						  Node q = nd2.item(j);
						  if(q.getNodeType()== Node.ELEMENT_NODE)
						  {
							  Element value = (Element)q;
							  if(value.getTagName().equalsIgnoreCase("value"))
							  {
								  appServerTimeout = value.getTextContent();
								  System.out.println(appServerTimeout);
							  }
						  }
					  }
					  }
					  if((appServer.equalsIgnoreCase("websphere"))||(appServer.startsWith("WebSphere")))
					  {
						  if(name.equalsIgnoreCase("ApplicationServerProfileFolder"))
						  {
						  NodeList nd2 = property.getChildNodes();
						  for(int j=0;j<nd2.getLength();j++)
						  {
							  Node q = nd2.item(j);
							  if(q.getNodeType()== Node.ELEMENT_NODE)
							  {
								  Element value = (Element)q;
								  if(value.getTagName().equalsIgnoreCase("value"))
								  {
									  appServerProfile = value.getTextContent();
									  System.out.println(appServerProfile);
								  }
							  }
						  }
						  }
						  if(name.equalsIgnoreCase("ApplicationServerCell"))
						  {
						  NodeList nd2 = property.getChildNodes();
						  for(int j=0;j<nd2.getLength();j++)
						  {
							  Node q = nd2.item(j);
							  if(q.getNodeType()== Node.ELEMENT_NODE)
							  {
								  Element value = (Element)q;
								  if(value.getTagName().equalsIgnoreCase("value"))
								  {
									  appServerCell = value.getTextContent();
									  System.out.println(appServerCell);
								  }
							  }
						  }
						  }
						  if(name.equalsIgnoreCase("TurnOffSSLCerticates"))
						  {
						  NodeList nd2 = property.getChildNodes();
						  for(int j=0;j<nd2.getLength();j++)
						  {
							  Node q = nd2.item(j);
							  if(q.getNodeType()== Node.ELEMENT_NODE)
							  {
								  Element value = (Element)q;
								  if(value.getTagName().equalsIgnoreCase("value"))
								  {
									  turnOffSSL = value.getTextContent();
									  System.out.println(turnOffSSL);
								  }
							  }
						  }
						  }
						  if(name.equalsIgnoreCase("SecurityDomain"))
						  {
						  NodeList nd2 = property.getChildNodes();
						  for(int j=0;j<nd2.getLength();j++)
						  {
							  Node q = nd2.item(j);
							  if(q.getNodeType()== Node.ELEMENT_NODE)
							  {
								  Element value = (Element)q;
								  if(value.getTagName().equalsIgnoreCase("value"))
								  {
									  securityDomain = value.getTextContent();
									  System.out.println(securityDomain);
								  }
							  }
						  }
						  }
					  }
					  else if((CMUtil.appServer.equals("weblogic"))||(CMUtil.appServer.startsWith("WebLogic")))
					  {
						  if(name.equalsIgnoreCase("ApplicationServerSSLEnabled"))
						  {
						  NodeList nd2 = property.getChildNodes();
						  for(int j=0;j<nd2.getLength();j++)
						  {
							  Node q = nd2.item(j);
							  if(q.getNodeType()== Node.ELEMENT_NODE)
							  {
								  Element value = (Element)q;
								  if(value.getTagName().equalsIgnoreCase("value"))
								  {
									  SSLEnabled = value.getTextContent();
									  System.out.println(SSLEnabled);
								  }
							  }
						  }
						  }
						  if(name.equalsIgnoreCase("ApplicationServerDomainDirectory"))
						  {
						  NodeList nd2 = property.getChildNodes();
						  for(int j=0;j<nd2.getLength();j++)
						  {
							  Node q = nd2.item(j);
							  if(q.getNodeType()== Node.ELEMENT_NODE)
							  {
								  Element value = (Element)q;
								  if(value.getTagName().equalsIgnoreCase("value"))
								  {
									  appServerDomain = value.getTextContent();
									  System.out.println(appServerDomain);
								  }
							  }
						  }
						  }
						  if(name.equalsIgnoreCase("TargetName"))
						  {
						  NodeList nd2 = property.getChildNodes();
						  for(int j=0;j<nd2.getLength();j++)
						  {
							  Node q = nd2.item(j);
							  if(q.getNodeType()== Node.ELEMENT_NODE)
							  {
								  Element value = (Element)q;
								  if(value.getTagName().equalsIgnoreCase("value"))
								  {
									  targetName = value.getTextContent();
									  System.out.println(targetName);
								  }
							  }
						  }
						  }
					  }
            	}
            }
        }
        catch(DOMException |
	        ParserConfigurationException | SAXException | IOException de) {
	        de.printStackTrace(); 
	    }
    }

}
