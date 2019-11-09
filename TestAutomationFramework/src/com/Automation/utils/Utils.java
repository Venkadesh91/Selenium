

package com.Automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;

import com.Automation.vo.TestcaseVo;


public class Utils {

	private static Properties objectRepository = null;
	private static Properties pathProperties = null;
	private static Logger applicationLogs = null;
	private static XlsReader testSuite = null;
	private static FileInputStream fileInputPath = null;
	private static Properties logProperty = null;
	private static String logDirectory = null;
	private static Logger logger = null;
	static Date date;
	static String finalDate;
	static SimpleDateFormat simpleDateFormat;
	public static Properties getLogProperty() {
		return logProperty;
	}

	public static void setLogProperty(Properties logProperty) {
		Utils.logProperty = logProperty;
	}

	public static String getLogDirectory() {
		return logDirectory;
	}

	public static void setLogDirectory(String logDirectory) {
		Utils.logDirectory = logDirectory;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		Utils.logger = logger;
	}

	public static Properties getObjectRepository() {
		return objectRepository;
	}

	public static void setObjectRepository(Properties objectRepository) {
		Utils.objectRepository = objectRepository;
	}

	public static Properties getPathProperties() {
		return pathProperties;
	}

	public static void setPathProperties(Properties pathProperties) {
		Utils.pathProperties = pathProperties;
	}

	public static Logger getApplicationLogs() {
		return applicationLogs;
	}

	public static void setApplicationLogs(Logger applicationLogs) {
		Utils.applicationLogs = applicationLogs;
	}

	public static XlsReader getTestSuite() {
		return testSuite;
	}

	public static void setTestSuite(XlsReader testSuite) {
		Utils.testSuite = testSuite;
	}

	public static FileInputStream getFileInputPath() {
		return fileInputPath;
	}

	public static void setFileInputPath(FileInputStream fileInputPath) {
		Utils.fileInputPath = fileInputPath;
	}

	public File initializeAllProperties(String pathLocationProperty)
			throws IOException {
		File file = null;
		try {

			String applicationProperties = "applicationproperties";
			String testsuitePath = "testsuitepath";
			String utils="Utils";

			setFileInputPath(new FileInputStream(pathLocationProperty));
			setPathProperties(new Properties());
			getPathProperties().load(getFileInputPath());
			initializeLogProperties();
			initializeLogger(utils);
			Utils.getApplicationLogs().debug(
					"********************Execution Started******************");
			getApplicationLogs().debug(
					"Path properties location accepted successfully--->" + pathLocationProperty);

			setFileInputPath(new FileInputStream(getPathProperties()
					.getProperty(applicationProperties)));
			setObjectRepository(new Properties());
			getObjectRepository().load(getFileInputPath());
			getApplicationLogs().debug(
					"Object properties location accepted successfully--->"
							+ getPathProperties().getProperty(
									applicationProperties));

			file = new File(getPathProperties().getProperty(testsuitePath));

		} catch (Exception e) {
		/*	getApplicationLogs().debug(
					"Invalid property location"+e.getMessage());*/
			e.getStackTrace();
		}
		return file;
	}

	public TestcaseVo executeTestSuite(StringBuilder build, String strFile,XlsReader xlsreader,HashMap<String, Method> keyMap,Keywords keobj) throws IOException {
		XlsUtils xlsUtilsObj = new XlsUtils();
		TestcaseVo testCaseVoobj=null;
		try {

			testCaseVoobj = xlsUtilsObj.readTestSuite(build, strFile, xlsreader,keyMap,keobj);
			xlsUtilsObj=null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testCaseVoobj;
	}

	public static void initializeLogProperties() throws IOException {
		String log4jpath = "log4jpropertiesfilelocation";
		String logstoringpath = "logsstorageplace";
		date=new Date();
		simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HHmmss");
		finalDate=simpleDateFormat.format(date);
		setFileInputPath(new FileInputStream(getPathProperties().getProperty(
				log4jpath)));
		setLogProperty(new Properties());
		getLogProperty().load(getFileInputPath());
		String logfilegenerationpath1 = getPathProperties().getProperty(
				logstoringpath);
		setLogDirectory(logfilegenerationpath1);

	}

	public static void initializeLogger(String className) {
	
		
	   
	    
	    
		String layout = "layoutdesign";
		String filename = "StafExecution-"+finalDate+".log";
		String filesize = "filesize";
		String backup = "backupindex";
		String debug = "DEBUG";
		
		
		RollingFileAppender fileApp = new RollingFileAppender();
		setApplicationLogs(Logger.getLogger(className));
		Logger logger = getApplicationLogs();
		System.out.println("logname--"+logger.getName());
		if (className != null) {
			
			PatternLayout p = new PatternLayout();
			Utils.getApplicationLogs().debug(getLogProperty().getProperty(layout));

			p.setConversionPattern(getLogProperty().getProperty(layout));
			
			fileApp.setFile(getLogDirectory() + File.separator + filename);
			fileApp.setName(className);
			fileApp.setMaxFileSize(getLogProperty().getProperty(filesize));
			fileApp.setMaxBackupIndex(Integer.parseInt(getLogProperty().getProperty(backup)));
			fileApp.setLayout(p);
			fileApp.setThreshold(Level.toLevel(debug));
			fileApp.setAppend(true);
			fileApp.activateOptions();
			getApplicationLogs().addAppender(fileApp);
		}

	}

}
