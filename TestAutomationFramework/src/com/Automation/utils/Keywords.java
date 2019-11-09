package com.Automation.utils;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;




public class Keywords
{
	


// private webDriver dr=null;
//	private  WebElementLocatorTypeEnum locatorTypeEnum = null;
	private  static ProfilesIni allProfile = new ProfilesIni();
	private  static FirefoxProfile applicationProfile = allProfile.getProfile(Utils.getObjectRepository().getProperty("firefoxprofilename"));
	 static Alert alert=null;
	 static String mainWindow =null;
	 static String childWindow=null;
	 static String extractedText=null;
	 private  WebDriver webDriver = null;
	
		private  EventFiringWebDriver driver = null;
	static String extractedTextSecondTime=null;
	 static String[] extractedFileCount=null;
	 static String[] extractedFileCountSecondTime=null;
	 static Integer fileCount;
	 static Integer fileCountSecondTime;
	 static String[] extractedTextValues=null;
	 static String[] extractedTextValuesSecondTime=null;
	 static ArrayList<String> arrayValue=new ArrayList<String>();
	 static Connection connection=null;
	 static Statement statement=null;
	 static ResultSet resultset=null;
     static int integerValue;
     static ArrayList<String> stringArrayValue=new ArrayList<String>();
     static ArrayList<String> stringValue;
     static ArrayList<String> stringValue1;
   
//	staAppiumDriver driver;
 	public  	String storedInputData =null;
     
     
	public  WebDriver getWebDriver() {
		return webDriver;
	}

	public  void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public  EventFiringWebDriver getDriver() {
		return driver;
	}

	public  void setDriver(EventFiringWebDriver driver) {
		this.driver = driver;
	}
	
public static void setFirefoxProfilePreferences()
	{
		try{
			applicationProfile.setPreference("browser.download.folderList", 2);
			applicationProfile.setPreference("browser.download.manager.showWhenStarting", false);
			applicationProfile.setPreference("browser.download.dir",Utils.getObjectRepository().getProperty("downloadpath"));
			applicationProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
			applicationProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");

		}catch(Exception e)
		{
			e.getMessage();
			
		}
		
	}


public  String openChrome(String str) {
	
	

	Utils.getApplicationLogs().debug("Executing openChrome");
    {
		try {

			if (getDriver() == null) {
				
			//	System.setProperty("webdriver.chrome.driver","	System.setProperty(\"webdriver.chrome.driver\", "E:\\chromedriver\\chromedriver_win32 (1)\\chromedriver.exe" );
						System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver_win32 (1)\\chromedriver.exe");

			setFirefoxProfilePreferences();
				setWebDriver(new ChromeDriver());

				setDriver(new EventFiringWebDriver(getWebDriver()));
				Utils.getApplicationLogs().debug(
						"Browser launched successfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Utils.getApplicationLogs()
					.debug("unable to launch the browser"+e.getMessage());
			return "Fail"+"Unable to launch the browser";

		}
	}
	return "Pass";
}
//2 navigate to the given url
	public  String navigate(String str) {
		Utils.getApplicationLogs().debug("Executing navigate Keyword");

		try {

			if (str == null || str.isEmpty()) {
				Utils.getApplicationLogs().debug(
						"Invalid input parameters for navigation");
			} else {

				String str1[] = str.split("~~");
				String webElement = str1[0];
				
				
				Utils.getApplicationLogs().debug("Input from property file "
						+ Utils.getObjectRepository().getProperty(webElement));
				getDriver().navigate().to(
						Utils.getObjectRepository().getProperty(webElement));
				Utils.getApplicationLogs().debug(
						"Navigated to--->" + webElement);
			}
		} catch (Exception e) {
			Utils.getApplicationLogs()
					.debug("Navigation failed--->"+e.getMessage());

			
			return "Fail"+"Navigation failed";
		}

		return "Pass";

	}
	
	
	public  String click(String str) throws IOException {
		Utils.getApplicationLogs().debug("Executing click Keyword");
		try {

			if (str == null || str.isEmpty()) {
				Utils.getApplicationLogs().debug(
						"Invalid input parameters for click");
			} else {
				String str1[] = str.split("~~");
				String webElement = str1[0];
				String locatorType= str1[1].toUpperCase();
				
				Utils.getApplicationLogs().debug("locatorType---"+locatorType);
			    switch (locatorType)
				{
				      case Constants.XPATH:
		           		getDriver().findElement(
								By.xpath(Utils.getObjectRepository().getProperty(
										webElement))).click();
		           	     Utils.getApplicationLogs().debug("Clicked webelement-->" + webElement+"--successfully");
			         
		                 break;
		           
		             case Constants.ID:
		            	 getDriver().findElement(
		            			 By.id(Utils.getObjectRepository().getProperty(
		            					 webElement))).click();
		            	 Utils.getApplicationLogs().debug("Clicked webelement-->" + webElement+"--successfully");
			         
		              break;
		           
		             case Constants.CSS:
		            	 getDriver().findElement(By.cssSelector(Utils.getObjectRepository().getProperty(webElement))).click();
		            	 	Utils.getApplicationLogs().debug("Clicked webelement-->" + webElement+"--successfully");
			                       
		             break;
		            
		          case Constants.NAME :
		        	  getDriver().findElement(By.name(Utils.getObjectRepository().getProperty(webElement))).click();
		        	  Utils.getApplicationLogs().debug("Clicked webelement-->" + webElement+"--successfully");
		        		
		           break;
		           default:
				    	 Utils.getApplicationLogs().debug("Invalid locator type in click keyword");
				         return "Fail"+"Invalid locator type in click keyword"; 
		           
				}  
			}
				
	
		}

		catch (Exception e) {

//			File a =((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
//    		FileUtils.copyFile(a, new File("C:\\Staf\\ScopeAutomationTestFramework\\ScopeAutomationTestFramework\\Result\\imag\\click.png"));
		
			Utils.getApplicationLogs().debug(
							 "WebElement not found for click keyword---"+e.getMessage());
			return "Fail"+"WebElement not found for click keyword";
		}
		return "Pass";

	}
	
	
	
	

	public  String verifyElementPresent(String str) throws IOException {
		Utils.getApplicationLogs().debug("Executing  verifyElementPresent Keyword");
		try {

			if (str == null || str.isEmpty()) {
				Utils.getApplicationLogs().debug(
						"Invalid input parameters for verifyElementPresent");
			} else {
				String str1[] = str.split("~~");
				String webElement = str1[0];
				String locatorType= str1[1].toUpperCase();
				
				Utils.getApplicationLogs().debug("locatorType---"+locatorType);
			    switch (locatorType)
				{
				      case Constants.XPATH:
		          Boolean result = 		getDriver().findElement(
								By.xpath(Utils.getObjectRepository().getProperty(
										webElement))).isDisplayed();
		           	     
		          
		          if(result==true)
		          {
		        	  Utils.getApplicationLogs().debug("eleemtn displayed");
		        	  
		          }
		          Utils.getApplicationLogs().debug("verifed  webelement-->" + webElement+"--successfully");
			         
		                 break;
		           
		             case Constants.ID:
		            	 getDriver().findElement(
		            			 By.id(Utils.getObjectRepository().getProperty(
		            					 webElement))).click();
		            	 Utils.getApplicationLogs().debug("Clicked webelement-->" + webElement+"--successfully");
			         
		              break;
		           
		             case Constants.CSS:
		            	 getDriver().findElement(By.cssSelector(Utils.getObjectRepository().getProperty(webElement))).click();
		            	 	Utils.getApplicationLogs().debug("Clicked webelement-->" + webElement+"--successfully");
			                       
		             break;
		            
		          case Constants.NAME :
		        	  getDriver().findElement(By.name(Utils.getObjectRepository().getProperty(webElement))).click();
		        	  Utils.getApplicationLogs().debug("Clicked webelement-->" + webElement+"--successfully");
		        		
		           break;
		           default:
				    	 Utils.getApplicationLogs().debug("Invalid locator type in click keyword");
				         return "Fail"+"Invalid locator type in click keyword"; 
		           
				}  
			}
				
	
		}

		catch (Exception e) {

//			File a =((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
//    		FileUtils.copyFile(a, new File("C:\\Staf\\ScopeAutomationTestFramework\\ScopeAutomationTestFramework\\Result\\imag\\click.png"));
		
			Utils.getApplicationLogs().debug(
							 "WebElement not found for click keyword---"+e.getMessage());
			return "Fail"+"WebElement not found for click keyword";
		}
		return "Pass";

	}
	
	
	
	public  String closeBrowser(String str) throws IOException {
		Utils.getApplicationLogs().debug("Executing inputdata Keyword");
		
		
		
		getDriver().quit();
		
		return "pass";
		}
	
	public  String inputData(String str) throws IOException {
		Utils.getApplicationLogs().debug("Executing inputdata Keyword");
		try {
			if (str == null || str.isEmpty()) {
				Utils.getApplicationLogs().debug(
						"Invalid input parameters for inputdata");
			} else {

				String str1[] = str.split("~~");
				
				String webElement = str1[0];
				String locatorType=str1[1].toUpperCase();
				String data = str1[2];
				
				
				String datas[] =data.split("\\.");
				
				System.out.println("the data is "+data);

			//	String allDatas[] =data.split("\\.");
				
			
				Utils.getApplicationLogs().debug("locator type---"+locatorType);
				switch (locatorType)
				{
				      case Constants.XPATH :
		    
				      	getDriver().findElement(
							By.xpath(Utils.getObjectRepository().getProperty(
									webElement))).sendKeys(datas[0]);

					   Utils.getApplicationLogs().debug("Input data-->" + datas[0]);

					   break;
					   
				      case Constants.ID :
				    	  getDriver().findElement(
									By.id(Utils.getObjectRepository().getProperty(
											webElement))).sendKeys(data);

							   Utils.getApplicationLogs().debug("Input data-->" + data);
                        break;
						    
				      case Constants.CSS :
				    	  getDriver().findElement(
									By.cssSelector(Utils.getObjectRepository().getProperty(
											webElement))).sendKeys(data);

							   Utils.getApplicationLogs().debug("Input data-->" + data);
                       break;
						    
				      case Constants.NAME :
				    	  getDriver().findElement(
									By.name(Utils.getObjectRepository().getProperty(
											webElement))).sendKeys(data);

							   Utils.getApplicationLogs().debug("Input data-->" + data);
                     break;
				     default:
					    	 Utils.getApplicationLogs().debug("Invalid locator type in inputData keyword");
					         return "Fail"+"Invalid locator type in inputData keyword";  
				}
				
							
			}
		} catch (Exception e) {
			Utils.getApplicationLogs().debug("WebElement not found or failed to input data in inputData keyword--"+e.getMessage());
			return "Fail"+" WebElement not found or failed to input data in inputData keyword";
		}
		return "Pass";

	}

}