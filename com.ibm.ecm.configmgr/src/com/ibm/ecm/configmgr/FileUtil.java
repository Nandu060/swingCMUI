/* IBM Confidential
 * 
 * OCO Source Materials
 * 
 * 5724-R81
 * 
 * � Copyright IBM Corp. 2008, 2009
 * 
 * The source code for this program is not published or otherwise
 * divested of its trade secrets, irrespective of what has
 * been deposited with the U.S. Copyright Office.
 */
package com.ibm.ecm.configmgr;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

//import com.ibm.ecm.configmgr.engine.nls.NLS;

//import com.ibm.ecm.configmgr.engine.util.CMLogger;
//import com.ibm.ecm.configmgr.engine.rcp.Activator;;

public class FileUtil {
    
    static public final String MESSAGE_FILE_NOT_FOUND = "File not found:";
    private static final String TMP_PREFIX = "tmp"; //$NON-NLS-1$
    static public void copy(File src, File dst)
    {
        final String methodName = "copy()";
        final String className = "FileUtil";
        final String tmp = dst.toString();
        
        //removing .invalid at the end of the filename while copying to dst; src filename is of the form .swtag.inactive 
        if(tmp.contains(".inactive"))
        {
        	dst = new File(tmp.substring(0,tmp.lastIndexOf('.')));
        }
        
        //CMLogger log = Activator.getLogger();
        if (dst.exists())
        {
            // Verify source and destination are not the same file
            File src2=null;
            File dst2=null;
            try {
                src2 = src.getCanonicalFile();
                dst2 = dst.getCanonicalFile();
            } catch (IOException ioe) {
                IllegalStateException e = new IllegalStateException(ioe);
                //log.exceptionBeforeThrowing(e.getLocalizedMessage(), e, className, methodName); 
                throw e;
            }
            if (src2.equals(dst2))
            {
                IllegalStateException e = new IllegalStateException("The file cannot be copied because the source and destination location are the same: "+ src2.getPath());
                //log.exceptionBeforeThrowing(e.getLocalizedMessage(), e, className, methodName); 
                throw e;
            }
            if (!dst.canWrite())
                dst.delete();
        }
        InputStream in;
        OutputStream out;
        try {
            in = new FileInputStream(src);
            out = new FileOutputStream(dst);
        } catch (FileNotFoundException fnfe) {
            IllegalStateException e = new IllegalStateException(fnfe);
            //log.exceptionBeforeThrowing(e.getLocalizedMessage(), e, className, methodName); 
            throw e;
        }
    
        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        try {
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (IOException ioe) {
            IllegalStateException e = new IllegalStateException(ioe);
            //log.exceptionBeforeThrowing(e.getLocalizedMessage(), e, className, methodName);
            throw e;
        }
    }
    
    public static void  copyDirectory(File srcPath, File dstPath) throws IOException
	{    		  
		if (srcPath.isDirectory())
		{
			if (!dstPath.exists())
				dstPath.mkdir();    		 
		 
		    String files[] = srcPath.list();    		  
		    for(int i = 0; i < files.length; i++){
		    	copyDirectory(new File(srcPath, files[i]), new File(dstPath, files[i]));
		    }
		}    		 
		else
		{    		  
			if(srcPath.exists())
				copy(srcPath,dstPath);
		}
	}
    
    public static void deleteDirContents(File f) throws IOException
	{
		File[] children = f.listFiles();
        
        if (f == null || f.length()== 0 || children == null || children.length == 0)
        	return;
        
        for(int i = 0; i < children.length; i++)
        {
        	children[i].delete();
        } 
	}
    
    public static void deleteDirContents(File f,String format) throws IOException
	{
		File[] children = f.listFiles();
        
        if (f == null || f.length()== 0 || children == null || children.length == 0)
        	return;
        
        for(int i = 0; i < children.length; i++)
        {
        	if(children[i].getName().endsWith(format))
        			children[i].delete();
        } 
	}
    
    public static File modifyTextBySearchAndReplace(String filePath, String textToSearch, String textToReplace) throws IOException
	{	   	
		File fileToModify = new File(filePath);
		//int lineCounter = 0;
		if(fileToModify.exists())
		{
			String strEOL = CMUtil.getPlatformEOL(); 
			String tempDir = getTmpDir();
			File tempFileToModify = File.createTempFile(TMP_PREFIX, null, new File(tempDir));

			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileToModify));
			Scanner scanner = new Scanner(fileToModify);

			String oneLine = null;
			//String trimmedLine = null;
			while(scanner.hasNextLine())
			{
				oneLine = scanner.nextLine();
				//trimmedLine = oneLine.trim();
				if(oneLine.contains(textToSearch))
				{
					String modifiedLine = oneLine.replace(textToSearch, textToReplace);
					writer.write(modifiedLine + strEOL);
//					if(lineCounter != linesToReplace.length - 1)
					//	lineCounter++;
				}
				else
				{
					writer.write(oneLine + strEOL);
				}
			}

			scanner.close();
			writer.close();

			return tempFileToModify; 
		}
		else
			throw new FileNotFoundException(fileToModify.getCanonicalPath());
	}
    
    public static String getTmpDir() throws IOException
	{
		//return CMUtil.CANONICAL_CM_PATH+File.separator+CMUtil.getTmpSubdir();
    	return "C:\\Program Files\\IBM\\FileNet\\ContentEngine\\tools\\configure\\tmp";
	}
    
    /**
	 * Starts a process with the provided excute parameters and working directory.  All outputs are piped into a temporary file and
	 * that file is returned.  Call deleteOnExit() on the file to delete the file once it's done.
	 * @param executablePath
	 * @param workingDirectory
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
    public static File startProcess(String executablePath, File workingDirectory, String executionFile) throws IOException, InterruptedException
	{
		return startProcess(new String[]{executablePath}, workingDirectory, executionFile);
	}
	
	/**
	 * Starts a process with the provided excute parameters and working directory.  All outputs are piped into a temporary file and
	 * that file is returned.  Call deleteOnExit() on the file to delete the file once it's done.
	 * @param executeParameters
	 * @param workingDirectory
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static File startProcess(String[] executeParameters, File workingDirectory, String executableFile) throws IOException, InterruptedException
	{
		ProcessBuilder pb;
		ArrayList<String> commands = new ArrayList<String>();
		String tempDir = getTmpDir();
		
		
		FileWriter outFW;
		File tempBatFile = null,tempShFile=null;
		if (CMUtil.isWinOS())
		{   // Use cmd.exe if windows
			tempBatFile = File.createTempFile(TMP_PREFIX, ".bat", new File(tempDir));
			outFW = new FileWriter(tempBatFile);
			outFW.write("cd /D "+workingDirectory+ CMUtil.getPlatformEOL());
			outFW.write("domain.conf.bat && "+ executeParameters[0]+ CMUtil.getPlatformEOL());
			outFW.close();
			commands.add("cmd.exe");
			commands.add("/C");
			commands.add(tempBatFile.getName());
		}
		else
		{   // Execute directly if unix
			
			tempShFile = File.createTempFile(TMP_PREFIX, ".sh", new File(tempDir));
			outFW = new FileWriter(tempShFile);
			outFW.write("cd "+workingDirectory+ CMUtil.getPlatformEOL());
			outFW.write("domain.conf.sh && "+ executeParameters[0]+ CMUtil.getPlatformEOL());
			outFW.close();
			commands.add("sh");
			commands.add("-c");
			commands.add(tempShFile.getName());
			
			/*File unixExecutableFile = new File(workingDirectory.getCanonicalFile() + File.separator + executableFile + ".cli");
			if(!unixExecutableFile.canExecute()) {
				//chmod the file for non-root users
				chmodUnixFile(executableFile+ ".cli", workingDirectory);
			}*/
		}
			
		
		pb = new ProcessBuilder(commands);//$NON-NLS-1$      
		Map<String, String> env = pb.environment();
		env.put("NOPAUSE", "true");
		//pb.directory(workingDirectory);	
		pb.directory(new File(tempDir));
		// merge child's error and normal output streams.
		pb.redirectErrorStream(true);
		Process proc = pb.start();
		
		//log all the output to a log file
		
		File tempLogFile = new File(new File(tempDir),executableFile+".log");//File.createTempFile(TMP_PREFIX, ".log", new File(tempDir));
		InputStream in = proc.getInputStream();
		InputStreamReader reader = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(reader);
		outFW = new FileWriter(tempLogFile);
        String aLine;
		while ((aLine=br.readLine()) != null)
			outFW.write(aLine+ CMUtil.getPlatformEOL());
		in.close();
		reader.close();
		br.close();
		outFW.close();
		
		proc.waitFor();
		//tempBatFile.delete();
		return tempLogFile;
	}
	
	
}

