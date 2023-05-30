package com.ibm.ecm.configmgr;

import java.io.File;

import javax.swing.JPanel;

import org.w3c.dom.Element;

import com.ibm.Factory.PropertyFactory;

public class LoadConfigureLogin 
{
	private ConfigureLogin confl;
	public static String PROPERTY = "property";
	public static String NAME = "name";
	public static String CONFIGURATION = "configuration";
	public static String VALUE = "value";
	public String Script,tempDir;
	public JPanel defaultPanel = new JPanel();
	
	public JPanel loadFromFile(File file)
	{
		System.out.println("File selected");
		//this.file = file;
		try 
		{
			PropertyFactory props = new PropertyFactory();
			props.XmlDoc(file.toString(), (String)null);
			Element doc1 = props.getDocElem();
			Script = props.getChildValueByName(doc1, PROPERTY, "Script", VALUE);
			tempDir = props.getChildValueByName(doc1, PROPERTY, "tempDir", VALUE); // to be removed
			confl = new ConfigureLogin(Script, tempDir, file);
			return confl;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return defaultPanel;
	}
}
