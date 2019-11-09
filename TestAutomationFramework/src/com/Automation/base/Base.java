/*
 * 

Scope Test Automation framework 
 *All the initialization  activities  done in Base

 */

package com.Automation.base;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.Automation.data.excel.Data;
import com.Automation.html.TestSummaryReport;
import com.Automation.utils.Keywords;
import com.Automation.utils.Utils;
import com.Automation.utils.XlsReader;
import com.Automation.vo.TestCaseList;
import com.Automation.vo.TestcaseVo;
import com.Automation.vo.TestdetailsVo;

public class Base {

	Date startDate = null;
	Date endDate = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy hh:mm:ss");
	int stHrs = 0, endHrs = 0;

	String executionTime;

	public void initialize(String pathLocationProperty) throws Exception {

		System.out.println("Path Properties Location---->"
				+ pathLocationProperty);
		Utils uobj = null;
		File files = null;
		String strFile = null;
		boolean isMain = true;
		HashMap<String, Method> keyMap = null;
		Keywords keobj = null;
		StringBuilder sbuild = null;
		XlsReader xlsreader = null;
		TestdetailsVo testdetailsVobj = new TestdetailsVo();
		TestCaseList testCaseListobj = new TestCaseList();
		TestcaseVo testCaseVoObj = null;
		TestSummaryReport htmlObj = new TestSummaryReport();
		try {
			if (pathLocationProperty == null || pathLocationProperty.isEmpty()) {
			} else {
				uobj = new Utils();
				sbuild = new StringBuilder();
				files = uobj.initializeAllProperties(pathLocationProperty);

				File file[] = files.listFiles();
				System.out.println("File count----> " + file.length);
				startDate = new Date();

				testdetailsVobj.setStrBrowTp(Utils.getObjectRepository().getProperty("browsertype"));
				testdetailsVobj.setStrRlsVer(Utils.getObjectRepository().getProperty("releaseversion"));
				testdetailsVobj.setStrRunEnv(Utils.getObjectRepository().getProperty("environment"));
				testdetailsVobj.setStrRunDate(sdf.format(startDate));
				testdetailsVobj.setStrStrDate(startDate.getHours() + ":"+ startDate.getMinutes() + ":"+ startDate.getSeconds());

				for (int i = 0; i < file.length; i++) {
					keyMap = new HashMap<String, Method>();
					strFile = file[i].getName();
					strFile = strFile.substring(0, strFile.length() - 5);
					xlsreader = new XlsReader(file[i]);
					keobj = new Keywords();
					keyMap = keyWordsMethods(keyMap, keobj);
					testCaseVoObj = uobj.executeTestSuite(sbuild, strFile,
							xlsreader, keyMap, keobj);
					testCaseListobj.add(testCaseVoObj);
					keobj = null;
					keyMap = null;
					xlsreader = null;
					strFile = null;
					testCaseVoObj = null;

				}
				endDate = new Date();
				testdetailsVobj.setStrEndDate(endDate.getHours() + ":"
						+ endDate.getMinutes() + ":" + endDate.getSeconds());
				testdetailsVobj.setStrExeDate(endDate.getHours()
						- startDate.getHours() + ":"
						+ (endDate.getMinutes() - startDate.getMinutes()) + ":"
						+ (endDate.getSeconds() - startDate.getSeconds()));
				htmlObj.testDetailsHtml(testdetailsVobj, testCaseListobj,
						strFile, isMain);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			keobj = null;
			keyMap = null;
			xlsreader = null;
			strFile = null;
			testCaseListobj = null;
			testdetailsVobj = null;
		}
		Utils.getApplicationLogs().debug(
				"********************Execution Ended******************");

	}

	private HashMap<String, Method> keyWordsMethods(
			HashMap<String, Method> keyMap, Keywords kobj) {

		try {
			Method methods[] = kobj.getClass().getDeclaredMethods();
			for (Method method : methods) {
				keyMap.put(method.getName(), method);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keyMap;
	}
}