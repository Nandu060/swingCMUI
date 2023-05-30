package com.ibm.Factory;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Demo 
{
	public static void main(String[] args) 
	{
		PropertyFactory props = new PropertyFactory();
		String filePath = "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\profiles\\wasdemo\\configurejdbcgcd.xml";
		String filePath2 = "C:\\h1";
		//Document doc1 = props.parseXML(new File("C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\profiles\\wasdemo\\configurejdbcgcd.xml"));
		//String valueName = props.getChildValueByName(doc1, "property", "JDBCDataSourceXAName", "value");
		props.XmlDoc(filePath, (String)null);
		//props.setConfigAttr(props.getDocElem(), "name", "Nandan");
		Element doc1 = props.getDocElem();
		props.setChildValueByName(doc1, "property", "JDBCDataSourceXAName", "value", "Nandan");
		//String attr = props.getAttribute(doc1, "configuration", "implementorid");
		//String attr = props.getConfigAttr(doc1, "implementorid");
		//System.out.println(attr);
		//props.saveXMLDoc(filePath2);
		String[] names = props.getElementNamesByTagName(doc1, "property");
		for(int i=0;i<names.length;i++)
		{
			System.out.println(props.getChildValueByName(doc1, "property", names[i], "value"));
		}
		
	}
}
