
package com.Automation.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import com.Automation.data.excel.Data;
import com.Automation.data.excel.TestCase;
import com.Automation.data.excel.TestStep;
import com.Automation.data.excel.TestSuite;
import com.Automation.vo.TestcaseVo;


public class XlsUtils {
	public static ArrayList<TestCase> listTestCases = null;

	public  XlsReader xlsreader =null;
	public static HashMap<String,TestCase> testCaseMap=null;
	public static HashMap<String, ArrayList<TestStep>> testStepMapAllValue = null;
	public static HashMap<String, HashMap<String, Data>> testDataMap= null;

	public TestcaseVo readTestSuite(StringBuilder build,String strFile, XlsReader xlsreader,HashMap<String, Method> keyMap,Keywords keobj) {
		this.xlsreader=xlsreader;
		TestSuite testSuite = null;
		RunTest runTestObj=null;
		TestcaseVo testcaseVoobj=null;
		Utils.initializeLogger("XlsUtils");
		try {
			runTestObj=new RunTest();
			loadTestCases();
			loadTestSteps();
			loadTestData();
			testSuite = new TestSuite(testCaseMap, testStepMapAllValue,testDataMap);
			
			//testcaseVoobj=runTestObj.startTest(testSuite,build,strFile,keyMap,keobj);
			
			testcaseVoobj=runTestObj.startTest(testSuite, build, strFile, keyMap, keobj);
			Utils.getApplicationLogs().debug("First Sheet is completed...............");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			testCaseMap=null;
			testStepMapAllValue=null;
			testDataMap=null;
			testSuite=null;
			runTestObj=null;
		}
		return testcaseVoobj;
	}

	
	private void loadTestCases() {
		testCaseMap=new HashMap<String,TestCase>();
		int testCasecolNumumnCount = 0;
		int testCaseRowCount = 0;
		TestCase testCase = null;
		try {
			testCaseRowCount = xlsreader.getRowCount((ExcelTypeEnum.TESTCASES.getText()));
			testCasecolNumumnCount = xlsreader.getColumnCount((ExcelTypeEnum.TESTCASES.getText()));
			Utils.getApplicationLogs().debug("Test Case Column Count--->" + testCasecolNumumnCount);
			Utils.getApplicationLogs().debug("Test Case Row Count--->" + testCaseRowCount);

			for (int rowNum = 2; rowNum <= testCaseRowCount; rowNum++) {
				testCase = new TestCase();
				for (int colNumNum = 0; colNumNum < testCasecolNumumnCount; colNumNum++) {
					if (colNumNum == 0) {
						testCase.setStrTCID(xlsreader.getCellData(ExcelTypeEnum.TESTCASES.getText(), colNumNum,rowNum));

					} else if (colNumNum == 1) {
						testCase.setStrDescription(xlsreader.getCellData(
								ExcelTypeEnum.TESTCASES.getText(), colNumNum,
								rowNum));

					} else if (colNumNum == 2) {
						testCase.setStrTDID(xlsreader.getCellData(
								ExcelTypeEnum.TESTCASES.getText(), colNumNum,
								rowNum));

					} 
					else if (colNumNum == 3) {
						testCase.setStrPreCondition(xlsreader.getCellData(
								ExcelTypeEnum.TESTCASES.getText(), colNumNum,
								rowNum));

					} 
					else if (colNumNum == 4) {
						testCase.setStrPostCondition(xlsreader.getCellData(
								ExcelTypeEnum.TESTCASES.getText(), colNumNum,
								rowNum));

					} 
					else if (colNumNum == 5) {
						testCase.setStrTestStepRef(xlsreader.getCellData(
								ExcelTypeEnum.TESTCASES.getText(), colNumNum,
								rowNum));

					} 
					else if (colNumNum == 6) {
						testCase.setStrTestDataRef(xlsreader.getCellData(
								ExcelTypeEnum.TESTCASES.getText(), colNumNum,
								rowNum));
					} 
					else if (colNumNum == 7) {
						testCase.setStrRunMode(xlsreader.getCellData(
								ExcelTypeEnum.TESTCASES.getText(), colNumNum,
								rowNum));
					} 
					else if (colNumNum == 8) {
						testCase.setStrSeverity(xlsreader.getCellData(
								ExcelTypeEnum.TESTCASES.getText(), colNumNum,
								rowNum));
					} 

				}

				Utils.getApplicationLogs().debug("TCID--->"+testCase.getStrTCID());
				testCaseMap.put(testCase.getStrTCID(), testCase);
				testCase=null;
			}

			Utils.getApplicationLogs().debug("TestCasesMap Size-->"+testCaseMap.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadTestSteps() {
		int testStepcolNumumnCount = 0;
		int testStepRowCount = 0;
		TestStep testStep = null;

		ArrayList<TestStep> alist = new ArrayList<TestStep>();
		testStepMapAllValue = new HashMap<String, ArrayList<TestStep>>();
		try {
			testStepRowCount = xlsreader.getRowCount(ExcelTypeEnum.TESTSTEPS
					.getText());
			testStepcolNumumnCount = xlsreader
					.getColumnCount(ExcelTypeEnum.TESTSTEPS.getText());
			Utils.getApplicationLogs().debug("TestSteps RowsCount-->" + testStepRowCount);
			Utils.getApplicationLogs().debug("TestSteps Columncount-->" + testStepcolNumumnCount);
			
			if (testCaseMap.size()> 0) {

				for (int rowNum = 2; rowNum <= testStepRowCount; rowNum++) {
					testStep = new TestStep();
					for (int colNum = 0; colNum < testStepcolNumumnCount; colNum++) {
						if (colNum == 0) { 	
							testStep.setStrTCID(xlsreader.getCellData(
									ExcelTypeEnum.TESTSTEPS.getText(), colNum,
									rowNum));
						} else if (colNum == 1) {
							testStep.setStrTSID(xlsreader.getCellData(
									ExcelTypeEnum.TESTSTEPS.getText(), colNum,
									rowNum));
						} else if (colNum == 2) {
							testStep.setStrTSDesc(xlsreader.getCellData(
									ExcelTypeEnum.TESTSTEPS.getText(), colNum,
									rowNum));
						} else if (colNum == 3) {
							testStep.setStrKeyword(xlsreader.getCellData(
									ExcelTypeEnum.TESTSTEPS.getText(), colNum,
									rowNum));
						} else if (colNum == 4) {
							testStep.setStrObject(xlsreader.getCellData(
									ExcelTypeEnum.TESTSTEPS.getText(), colNum,
									rowNum));
						} else if (colNum == 5) {
							testStep.setStrObjectType(xlsreader.getCellData(
									ExcelTypeEnum.TESTSTEPS.getText(), colNum,
									rowNum));
						} else if (colNum == 6) {
							testStep.setStrData(xlsreader.getCellData(
									ExcelTypeEnum.TESTSTEPS.getText(), colNum,
									rowNum));
						} 
					}
					alist = testStepMapAllValue.containsKey(testStep.getStrTCID()) ? alist : new ArrayList<TestStep>();
					alist.add(testStep);
					testStepMapAllValue.put(testStep.getStrTCID(), alist);
				}
				Utils.getApplicationLogs().debug("TestStepMap Size--->"
						+ testStepMapAllValue.size());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadTestData() {
		int dataColumnCount = 0;
		int dataRowCount = 0;
		Data data = null;
		testDataMap=new HashMap<String, HashMap<String, Data>>();
		HashMap<String,Data> testDataMaps  =new HashMap<String, Data>();
		try {
			dataRowCount = xlsreader.getRowCount(ExcelTypeEnum.TESTDATA.getText());
			dataColumnCount = xlsreader.getColumnCount(ExcelTypeEnum.TESTDATA.getText());
			Utils.getApplicationLogs().debug("DataRowCount-->" + dataRowCount);
			Utils.getApplicationLogs().debug("DataColumnCount-->" + dataColumnCount);
			for (int row1 = 2; row1 <= dataRowCount; row1++) {
				data = new Data();
				for (int col = 0; col < dataColumnCount; col++) {
					if (col == 0) {
						data.setStrTestDataID(xlsreader.getCellData(
								ExcelTypeEnum.TESTDATA.getText(), col, row1));
					} else if (col == 1) {
						data.setStrTestValueId(xlsreader.getCellData(
								ExcelTypeEnum.TESTDATA.getText(), col, row1));
					}
					else if (col == 2) {
						data.setStrTestValue(xlsreader.getCellData(
								ExcelTypeEnum.TESTDATA.getText(), col, row1));
					}
				}
				testDataMaps=testDataMap.containsKey(data.getStrTestDataID())?testDataMaps:new HashMap<String, Data>();
				testDataMaps.put(data.getStrTestValueId(), data);
				testDataMap.put(data.getStrTestDataID(), testDataMaps);
			}
			Utils.getApplicationLogs().debug("TotalListSize-->" + testDataMap.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
