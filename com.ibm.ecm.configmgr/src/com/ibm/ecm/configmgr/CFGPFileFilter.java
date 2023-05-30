package com.ibm.ecm.configmgr;
import java.io.File;

import javax.swing.filechooser.FileFilter;

public class CFGPFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		// TODO Auto-generated method stub
		if(f.isDirectory())
			return true;
		String name = f.getName();
		String extension = Utils.getFileExtension(name);
		if(extension==null)
			return false;
		if(extension.equals("cfgp"))
			return true;
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "cfgp files (*.cfgp)";
	}

}
