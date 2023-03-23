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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Package visible class used by the app server util classes
 */
public class OutputProcessor extends Thread {
    private InputStream is;
    private StringBuffer sb;

    public OutputProcessor(InputStream is, StringBuffer sb) {
        this.is = is;
        this.sb = sb;
        start();
    }

    public void run() {
        try{
            String strEOL = "\r\n";//CMUtil.getPlatformEOL();
            String line;
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            //sb.append("Executing task for " + CMUtil.profileName + " profile.");
            //sb.append(strEOL);
            
            while ((line = br.readLine())!=null)
            {
            	System.out.println(line);
            	if (line.startsWith("WASX7209I"))
            		continue;
            	else
            		sb.append(line+ strEOL);
            	//System.out.println(sb);
            }
            System.out.println(sb);
            isr.close();
            br.close();
            
        }
        catch (IOException e)
        {
            //do we log this?
        }
    }

}
