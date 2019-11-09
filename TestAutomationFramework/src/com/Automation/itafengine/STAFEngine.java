/*
 
 *   class will get  input at runtime from the user ,and  initialize  the  properties file 
 */

package com.Automation.itafengine;

import java.io.File;

import com.Automation.base.Base;
import com.Automation.utils.Utils;

public class STAFEngine {
	public static String pathproperty=null;
	public void InitializeITAFEngine(String pathPropertiesLocation)
			 {
		Base bobj=null;
		try{ 
		
			bobj = new Base();
			bobj.initialize(pathPropertiesLocation);                             
			Utils.getApplicationLogs().debug("Path properties location input successfully");
		
		}
		catch(Exception e){	
			e.printStackTrace();
			Utils.getApplicationLogs().debug("Invalid pathproperty"+e.getMessage());
		}
		finally{
			bobj=null;
		}
	}

	public static void main(String[] args) {
		
		pathproperty="E:\\frameworkss\\TestAutomationFramework\\TestAutomationFramework\\conf\\path.properties";
		
		try{
    	         if(pathproperty==null || pathproperty.isEmpty()) {
    		     Utils.getApplicationLogs().debug("Invalid path");
    		     System.exit(0);
        } else {
        	       STAFEngine itaf = new STAFEngine();
			       itaf.InitializeITAFEngine(pathproperty);
        }
         
	}catch(Exception e)
	{
		
		e.printStackTrace();
	}
		
		
		
	/*	
		
		try {
			ITAFEngine itaf = new ITAFEngine();
			itaf.InitializeITAFEngine("E:\\Selenium\\impiger selenium\\ImpigerTestAutomationFramework\\conf\\path.properties");
		} catch (Exception e) {

			e.printStackTrace();
		}*/
	}
 
}
