package com.ibm.Factory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class PropertyFactory 
{
	private static final String NAME = "name"; //$NON-NLS-1$
	private static final String VALUE = "value"; //$NON-NLS-1$
	private static final String PROPERTY = "property"; //$NON-NLS-1$
	private static final String CONFIGURATION = "configuration";
	private static final String IMPLEMENTOR_ID = "implementorid";
	private static final String INDENT_AMOUNT_VALUE = "4"; //$NON-NLS-1$
	private static final String INDENT_AMOUNT = "{http://xml.apache.org/xslt}indent-amount"; //$NON-NLS-1$
	private static final String YES = "yes"; //$NON-NLS-1$
	private static final String LT_CLOSE = "</"; //$NON-NLS-1$
	private static final String GT = ">"; //$NON-NLS-1$
	private static final String LT = "<"; //$NON-NLS-1$
	private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"; //$NON-NLS-1$
	private static final String EMPTY_STRING = ""; //$NON-NLS-

	Document configureDoc = null;
	Element docElem = null;
	String dTDPublicID = EMPTY_STRING;
	String dTDSystemID = EMPTY_STRING;
	
	public PropertyFactory()
	{
		
	}
	
	//Java Swing part
	public JTextField getTextField()
	{
		JTextField text = new JTextField();
		return text;
	}
	public JLabel setLabel(String label)
	{
		JLabel lab = new JLabel(label);
		return lab;
	}
	public String getValue(JTextField text)
	{
		return text.getText().toString();
	}
	public JCheckBox getCheckBox(String cbLabel)
	{
		JCheckBox chckbx = new JCheckBox(cbLabel);
		return chckbx;
	}
	public String getValue(JCheckBox chckbx)
	{
		if(chckbx.isSelected())
			return "true";
		else
			return "false";
	}
	public void setValue(JCheckBox chckbx,String value)
	{
		chckbx.setSelected(Boolean.parseBoolean(value));
	}
	public void setValue(JTextField text,String value)
	{
		text.setText(value);
	}
	public JComboBox<String> getComboBox(String[] defaultValue)
	{
		JComboBox<String> comboBox = new JComboBox<String>();
		DefaultComboBoxModel<String> defModel = new DefaultComboBoxModel<>(defaultValue);
		comboBox.setModel(defModel);
		return comboBox;
	}
	public String getValue(JComboBox<String> comboBox)
	{
		return comboBox.getSelectedItem().toString();
	}
	public void setValue(JComboBox<String> comboBox,String value)
	{
		comboBox.setSelectedItem(value);
	}
	public JPasswordField getPasswordField()
	{
		JPasswordField passField = new JPasswordField();
		return passField;
	}
	public String getValue(JPasswordField passField)
	{
		return passField.getText();
	}
	public void setValue(JPasswordField passField,String value)
	{
		passField.setText(value);
	}
	public JSeparator addSeparator()
	{
		JSeparator separate = new JSeparator();
		return separate;
	}
	public JButton getButton(String name)
	{
		JButton btn = new JButton(name);
		return btn;
	}
	public JFileChooser fileChooser()
	{
		JFileChooser profPath = new JFileChooser();
        profPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        profPath.showOpenDialog(null);
        File profDir = profPath.getSelectedFile();
        if (!(profDir.isDirectory() && profDir.exists())) 
        {
            profDir.mkdirs();
        }
        return profPath;
	}
	
	
	//XML file loading and saving part
	public void XmlDoc(String filePath, String docElemName) {
		getDocElement(filePath, docElemName);
	}
	public Element getDocElem()
	{
		return docElem;
	}
	public void saveXMLDoc(String filePath)
	{		
		saveXMLDoc(filePath, dTDPublicID,dTDSystemID);
	}
	public void saveXMLDoc(String filePath, String publicId, String systemId)
	{				
		final String methodName = "saveXMLDoc"; //$NON-NLS-1$
		//LOGGER.entry( this.getClass().getName(), methodName);
		
		if (configureDoc == null)
		{
			//LOGGER.error(Messages.NULL_CONFIGUREDOC);
			return;
		}
		TransformerFactory tranFactory = TransformerFactory.newInstance();
		try
		{
			configureDoc.normalize();
			Transformer aTransformer = tranFactory.newTransformer();
			if (publicId.length()>0 && systemId.length()>0)
			{
				aTransformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, publicId);
				aTransformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, systemId);
			}		
			aTransformer.setOutputProperty(OutputKeys.INDENT, YES);
			aTransformer.setOutputProperty(INDENT_AMOUNT, INDENT_AMOUNT_VALUE);
			DOMSource src = new DOMSource(configureDoc);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
			StreamResult dest = new StreamResult(bw);
			aTransformer.transform(src, dest);
			configureDoc = null;
			
			bw.close();
			
		}
		catch (IOException ioe) 
		{
			IllegalStateException e = new IllegalStateException(ioe.toString());
            //LOGGER.exceptionBeforeThrowing(e.getLocalizedMessage(), e, this.getClass().getName(), methodName);
			throw e;
		}
		catch(TransformerConfigurationException tce)
		{
			//IllegalStateException e = new IllegalStateException(NLS.bind(Messages.NEW_TRANSFORMER_EXCEPTION, tce.toString()));
            //LOGGER.exceptionBeforeThrowing(e.getLocalizedMessage(), e, this.getClass().getName(), methodName);
			tce.printStackTrace();
		}
		catch(TransformerException te)
		{
			//IllegalStateException e = new IllegalStateException(NLS.bind(Messages.TRANSFORMER_EXCEPTION, te.toString()));
            //LOGGER.exceptionBeforeThrowing(e.getLocalizedMessage(), e, this.getClass().getName(), methodName);
			te.printStackTrace();
		} 
		
		//LOGGER.exit( this.getClass().getName(), methodName);
	}
	public Document parseXML(File file)// - to parse the XML file
	{
		Document parsedDocument = null;
		try 
		{
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			parsedDocument = documentBuilder.parse(file);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return parsedDocument;
	}
	public void writeXML(File file,Document document)
	{
		try 
		{
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer(); 
			DOMSource domSource = new DOMSource(document); 
			StreamResult streamResult = new StreamResult(file);  
			transformer.transform(domSource, streamResult);
			System.out.println("Done creating XML File");
		}
		catch(DOMException |TransformerException de)
		{
			de.printStackTrace();
		}
	}
	private FileInputStream loadXMLFile(String fileName, String elemName)
	{
		final String methodName = "loadXMLFile"; //$NON-NLS-1$
		//LOGGER.entry( this.getClass().getName(), methodName);

		FileInputStream fis = null;
		try {
			File confDir = new File(fileName).getParentFile();
			if (confDir.exists() == false) {
				confDir.mkdirs();
			}
			File file = new File(fileName);
			if (!file.exists()) {
    		    if (file.createNewFile())
    		    {
    		    	String resolvedName = elemName;
    		    	if (resolvedName == null)
    		    		resolvedName = CONFIGURATION;
    		    	BufferedWriter fw = new BufferedWriter( new FileWriter(fileName));
    		    	fw.write(XML_HEADER);
    		    	fw.newLine();
    		    	fw.write(LT + resolvedName + GT);
    		    	fw.newLine();
    		    	fw.write(LT_CLOSE + resolvedName + GT);
    		    	fw.newLine();
    		    	fw.close();
    		    	file = new File(fileName);
    		    }               	
    	   }
    	   
    	   fis = new FileInputStream(file);

		}
		catch (Exception e) 
		{
            //LOGGER.exception(Messages.LOAD_XML_EXCEPTION, e, this.getClass().getName(), methodName);
			e.printStackTrace();
			return null;
		}

		//LOGGER.exit( this.getClass().getName(), methodName);
		
		return fis;
	}
	public boolean getDocElement(String filePath, String elemName)
	{
		final String methodName = "getDocElement"; //$NON-NLS-1$
		//LOGGER.entry( this.getClass().getName(), methodName);

		//LOGGER.info(NLS.bind(Messages.GET_DOC_ELEMENT_PATH, filePath));
		
		DocumentBuilderFactory factory = null;
		DocumentBuilder parser = null;
		FileInputStream fi = loadXMLFile(filePath, elemName);
		if (fi == null) 
			return false;
		InputSource inputStream = null;
		try {
			factory = DocumentBuilderFactory.newInstance();

			parser = factory.newDocumentBuilder();
			if (parser == null) {
				//LOGGER.error(Messages.NULL_PARSER);
				return false;
			}

			inputStream = new InputSource(fi);
			configureDoc = parser.parse(inputStream);
			if (configureDoc == null) {
				//LOGGER.error(Messages.NULL_CONFIGUREDOC);
				return false;
			}
		} catch (ParserConfigurationException pce) {
            //LOGGER.exception(NLS.bind(Messages.NEWDOCUMENTBUILDER_EXCEPTION, pce.toString()), pce, this.getClass().getName(), methodName);
			return false;
		} catch (IOException ioe) {
            //LOGGER.exception(NLS.bind(Messages.CONFIGUREDOC_PARSER_EXCEPTION, ioe.toString()), ioe, this.getClass().getName(), methodName);
			return false;
		} catch (SAXException se) {
            //LOGGER.exception(NLS.bind(Messages.CONFIGUREDOC_PARSER_EXCEPTION, se.toString()), se, this.getClass().getName(), methodName);
			return false;
		}

		docElem = configureDoc.getDocumentElement();
		docElem.normalize();
		//LOGGER.exit( this.getClass().getName(), methodName);
		return true;
	}
	public void setAttribute(Element elem, String attrName, String attrValue)
    {
    	elem.setAttribute(attrName, attrValue);
    }
    
	public void setAttribute(Element parent, String tag, String tagName, String attrName, String attrValue)
	{
		final String methodName = "setAttribute"; //$NON-NLS-1$
		//LOGGER.entry( this.getClass().getName(), methodName);

		if (parent == null)
        {	        	
            return ;
        }
        tag.trim();
        tagName.trim();
        attrName.trim();
        attrValue.trim();
        NodeList tagList = parent.getElementsByTagName(tag);
        int nTags = tagList.getLength();
        if (nTags <= 0 )
        {
            return ;
        }                   
        for (int i = 0; i < nTags; i++)
        {
            Node nd = tagList.item(i);
            if (nd.getNodeType() == Node.ELEMENT_NODE)
            {
                Element ele = (Element)nd;
                String name = ele.getAttribute(NAME);
                if (name.compareToIgnoreCase(tagName) == 0)
                {
                	 ele.setAttribute(attrName, attrValue);
                }
            }
        }

		//LOGGER.exit( this.getClass().getName(), methodName);
	}
	
	public void setConfigAttr(String configName, String attrName, String attrValue)
	{
		setAttribute(docElem, CONFIGURATION, configName, attrName, attrValue );
	}
	
	public Element setConfigAttr(Element confEle, String attrName, String attrValue)
	{
		confEle.setAttribute(attrName, attrValue);
		return confEle;
	}
	public NodeList getElementListByTagName(Element parent, String tag)
    {
    	NodeList ndlst = null;
    	ndlst = parent.getElementsByTagName(tag);
    	return ndlst;
    }
	public String[] getElementNamesByTagName(Element parent, String tag)// - tag is "property", parent is parsed document
    {
    	if (parent == null)
    	{
    		return null;
    	}
    	
    	NodeList tagList = parent.getElementsByTagName(tag);
        int nTags = tagList.getLength();
        if (nTags <= 0 )
        {
            return null;
        } 
        String[] names = new String[nTags];
        for (int i = 0; i < nTags; i++)
        {
            Node nd = tagList.item(i);
            if (nd.getNodeType() == Node.ELEMENT_NODE)
            {
                Element ele = (Element)nd;// - ele is <property>
                names[i] = getConfigAttr(ele, NAME);// - ex. returns JDBCDatasourcename,XA,..... etc
            }    
        }
        return names;
    }
	public Node getChildNode(Element parent, String tag)
    {
    	if (parent == null)
        {
            return null;
        }
        NodeList tagList = parent.getElementsByTagName(tag);
        int nTags = tagList.getLength();
        if (nTags <= 0 )
        {
            return null;
        }                   
        for (int i = 0; i < nTags; i++)
        {
            Node nd = tagList.item(i);
            if (nd.getNodeType() == Node.ELEMENT_NODE)
            {
                return nd;
            }   
        }
        return null;
    }
	public void setChildValueByName(Element parent, String tag, String nameAttr, String childName, String value)
	{
		final String methodName = "setChildValueByName"; //$NON-NLS-1$
		//LOGGER.entry( this.getClass().getName(), methodName);
		if (parent == null)
        {
        	System.out.println("No document selected");
            //return value;
        }
		NodeList nd = parent.getElementsByTagName(tag);
		System.out.println(nd.getLength());  
		for(int i=0;i<nd.getLength();i++)
		{
			Node p = nd.item(i);
			if(p.getNodeType() == Node.ELEMENT_NODE)
			{
				Element property = (Element)p;
				String name = property.getAttribute(NAME);
				if(name.equalsIgnoreCase(nameAttr))
				{
					NodeList nd1 = property.getChildNodes();
					for(int j=0;j<nd1.getLength();j++)
					{
						Node q = nd1.item(j);
						if(q.getNodeType()== Node.ELEMENT_NODE)
						{
							Element value1 = (Element)q;
							if(value1.getTagName().equalsIgnoreCase(childName))
							{
								value1.setTextContent(value);
								System.out.println(value1.getTagName()+" , "+value1.getTextContent());
							}
						}
					}
				}
			}
		}
    	//String value = null;
		
		//LOGGER.exit( this.getClass().getName(), methodName);
	}
	public String getChildValueByName(Element parent, String tag, String nameAttr, String childName)
    {
		final String methodName = "getChildValueByName"; //$NON-NLS-1$
		//LOGGER.entry( this.getClass().getName(), methodName);
		
    	String value = null;
        if (parent == null)
        {
            return value;
        }
        NodeList tagList = parent.getElementsByTagName(tag);
        int nTags = tagList.getLength();
        if (nTags <= 0 )
        {
            return value;
        }                   
        for (int i = 0; i < nTags; i++)
        {
            Node nd = tagList.item(i);
            if (nd.getNodeType() == Node.ELEMENT_NODE)
            {
                Element ele = (Element)nd;
                String name = ele.getAttribute(NAME);
                if (name.compareToIgnoreCase(nameAttr) == 0)
                {
                	try
                	{
                		NodeList nodeName = ele.getElementsByTagName(childName);// - childName is "value"
                		if (nodeName != null && nodeName.getLength() > 0)
                		{   
                			value = nodeName.item(0).getChildNodes().item(0).getNodeValue().trim();
                		}
                	}
                	catch(Exception e)
                	{
                        //LOGGER.exception(e.getLocalizedMessage(), e, this.getClass().getName(), methodName);
                		//e.printStackTrace();
                		System.out.println("Empty field");
                	} 
                	return value;
                }
            }
        }
		/*if(value.equals(null))
		{
			System.out.println("Empty field");
			return "";
		}*/
		//LOGGER.exit( this.getClass().getName(), methodName);
        return value;
    }
	public String getAttribute(Element parent, String tag, String tagName, String attrName)// - tagName is names[] ,i.e, JDBCdatasource,XA,.... etc
    {
		String attr = null;
        if (parent == null)
        {
            return attr;
        }
        NodeList tagList = parent.getElementsByTagName(tag);
        int nTags = tagList.getLength();
        if (nTags <= 0 )
        {
            return attr;
        }                   
        for (int i = 0; i < nTags; i++)
        {
            Node nd = tagList.item(i);
            if (nd.getNodeType() == Node.ELEMENT_NODE)
            {
                Element ele = (Element)nd;
                String name = ele.getAttribute(NAME);
                if (name.contains(tagName) == true)
                {
                	 attr = ele.getAttribute(attrName);
                	 return attr;
                }
            }
        }
		return attr;
    }
    public String getAttribute(Element parent, String tag, String attrName)
    {
    	String implementorID = null;
    	NodeList configuration = parent.getElementsByTagName(tag);
    	int length = configuration.getLength();
    	System.out.println(length);
		Node p1 = configuration.item(0); 
		if(p1.getNodeType()==Node.ELEMENT_NODE)
		{
			Element props1 = (Element) p1;
			implementorID = props1.getAttribute(attrName);
			System.out.println(implementorID);
			//CMUtil.implementorID = implementorID;
		}
		return implementorID;
    }
	/*public String getChildNodeValue(Element ele)
	{
		
	}*/
    public String getConfigAttr(String configName, String attrName)
	{
		return getAttribute(docElem, CONFIGURATION, configName, attrName);
	}
    
    public String getConfigAttr(Element ele, String attrName)//attrName is "name", ele is <property>
    {
    	String attr = null;
    	attr = ele.getAttribute(attrName);
    	return attr;
    }
}
