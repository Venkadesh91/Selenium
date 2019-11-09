
  

package com.Automation.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.io.File;
import java.io.FileOutputStream;

import com.Automation.base.Base;
import com.Automation.data.excel.Data;
import com.Automation.data.excel.TestCase;
import com.Automation.data.excel.TestStep;
import com.Automation.data.excel.TestSuite;
import com.Automation.html.TestSummaryReport;
import com.Automation.vo.TestCaseList;
import com.Automation.vo.TestcaseVo;


public class RunTest {
	
	
	public TestCase listTestCases = null;
	HashMap<String, Method> keyMap =null;
	 Keywords kobj = null;
	int intS1Fail = 0, intS2Fail = 0, intS1Pass = 0, intS2Pass = 0,
			intSkipcount = 0;
	static String strFailureReason;
	public TestcaseVo startTest(TestSuite testSuite, StringBuilder sbuild,String strFile,HashMap<String, Method> keyMap,Keywords kobj) {
	    Utils.initializeLogger("RunTest");
		HashMap<String, TestCase> testCaseMap = null;
		HashMap<String, ArrayList<TestStep>> testStepMapAllValue = null;
		HashMap<String, HashMap<String, Data>> testDataMap = null;
		String strTestParam = null;
		StringBuilder sbuff = new StringBuilder();
		int intTttestcount = 0;
		Base baseObj = new Base();
		TestcaseVo tescaseObj=new TestcaseVo();
		TestCaseList testCasList=new TestCaseList();
		TestcaseVo tscasObj=null;
		TestSummaryReport htmlObj=new TestSummaryReport();
		try {
			this.kobj=kobj;
			this.keyMap=keyMap;
			testCaseMap = testSuite.getTestCaseMap();
			testStepMapAllValue = testSuite.getTestStepMapAllValue();
			testDataMap = testSuite.getTestDataMap();
			if (testCaseMap.containsKey("PRCS")) {
				listTestCases = testCaseMap.get("PRCS");
				strTestParam = preSuite(listTestCases, testStepMapAllValue,testDataMap);
				listTestCases = null;
			}
			if (true) {
				Set<String> set = testCaseMap.keySet();
				Iterator<String> it = set.iterator();
				String strTest = "";
				String strTestSteps="";
				while (it.hasNext()) {
					String strKey = it.next();
					Utils.getApplicationLogs().debug("strKey ::: " + strKey);
					if (strKey.startsWith("TC")) {
						strTest += strKey + "~";
					}
				}
				strTest = strTest.substring(0, strTest.length() - 1);
				String strTests[] = strTest.split("~");
				Arrays.sort(strTests);
				intTttestcount = strTests.length;
				for (int i = 0; i < intTttestcount; i++) {
					TestCase testCase = testCaseMap.get(strTests[i]);
					Utils.initializeLogger("RunTest--"+strTests[i]);
					tscasObj = testCondition(testCase, testStepMapAllValue,testDataMap, testCaseMap, sbuff);
					testCasList.add(tscasObj);
					testCase = null;
				}
			}

			if (testCaseMap.containsKey("POCS")) {
				listTestCases = null;
				listTestCases = testCaseMap.get("POCS");
				strTestParam = postSuite(listTestCases, testStepMapAllValue,
						testDataMap);
				listTestCases = null;
			}
			int tots1 = 0, tots2 = 0, alltotal = 0;
			String color = null;
			float tots1pass = intS1Pass;
			float tots1fail = intS1Fail;
			float tots2pass = intS2Pass;
			float tots2fail = intS2Fail;

			// New logic
			float totals1 = tots1pass + tots1fail;
			float totals2 = tots2pass + tots2fail;
			float totaltestcases = totals1 + totals2;

			float s1failpercent = (tots1fail / totals1) * 100;
			float s2failpercent = (tots2fail / totals2) * 100;
			float totalfailedcase = s1failpercent + s2failpercent;

			color = (tots1fail == 0 && tots2fail == 0) ? "green"
					: (s1failpercent > 25) ? "red"
							: (s1failpercent == 0 && s2failpercent < 50) ? "cyan"
									: (s1failpercent < 25 && s2failpercent == 0) ? "blue"
											: (s1failpercent < 25 && s2failpercent < 50) ? "yellow"
													: (s1failpercent < 25 || s2failpercent > 50) ? "orange"
															: "red";
			
			
			tescaseObj.setStrFile(strFile);
			tescaseObj.setIntS1Fail(intS1Fail);
			tescaseObj.setIntS2Fail(intS2Fail);
			tescaseObj.setIntS1Pass(intS1Pass);
			tescaseObj.setIntS2Pass(intS2Pass);
			tescaseObj.setIntSkipcount(intSkipcount);
			tescaseObj.setIntTttestcount(intTttestcount);
			tescaseObj.setColor(color);

			intS1Fail = 0;
			intS2Fail = 0;
			intS1Pass = 0;
			intS2Pass = 0;
			intSkipcount = 0;
			intTttestcount = 0;
			tots1pass = 0;
			tots1fail = 0;
			tots2pass = 0;
			tots2fail = 0;
			totals1 = 0;
			totals2 = 0;
			totaltestcases = 0;
			sbuff.append("</table>");
			sbuff.append("</head>");
			
			htmlObj.testSubHtml(testCasList, strFile, false);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			htmlObj=null;
			testCasList=null;
			strFile=null;
		}

		return tescaseObj;
	}


	private String preSuite(TestCase testCase,
			HashMap<String, ArrayList<TestStep>> testStepMapAllValue,
			HashMap<String, HashMap<String, Data>> testDataMap) {
		
		String strTDID = null;
		String strTCID = null;
		ArrayList<TestStep> testStepList = null;
		String strTestParam = null;
		try {
			strTCID = testCase.getStrTCID();
			Utils.getApplicationLogs().debug("TCID---PreCond--->" + strTCID);
			strTDID = testCase.getStrTDID();
			testStepList = testStepMapAllValue.get(strTCID);
			strTestParam = executefinalTest(testStepList, testDataMap, strTDID);
			strTestParam = "Test Case Is   :" + strTCID + "\nRun Mode Is    :Y"
					+ "\nTest Result Is :" + strTestParam + "\n\n";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			testStepList = null;
			strTDID = null;
			strTCID = null;
		}
		return strTestParam;
	}

	private String postSuite(TestCase testCase,
			HashMap<String, ArrayList<TestStep>> testStepMapAllValue,
			HashMap<String, HashMap<String, Data>> testDataMap) {
		
		String strTDID = null;
		String strTCID = null;
		ArrayList<TestStep> testStepList = null;
		String strTestParam = null;
		try {
			strTCID = testCase.getStrTCID();
			Utils.getApplicationLogs().debug("strTCID :::::postcond::::>>>>>>>>>>>> "	+ strTCID);
			strTDID = testCase.getStrTDID();
			testStepList = testStepMapAllValue.get(strTCID);
			strTestParam = executefinalTest(testStepList, testDataMap, strTDID);
			strTestParam = "Test Case Is   :" + strTCID + "\nRun Mode Is    :Y"
					+ "\nTest Result Is :" + strTestParam + "\n\n";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			testStepList = null;
			strTDID = null;
			strTCID = null;
		}
		return strTestParam;
	}

	public TestcaseVo testCondition(TestCase listTestcas,
			HashMap<String, ArrayList<TestStep>> testStepMapAllValue,
			HashMap<String, HashMap<String, Data>> testDataMap,
			HashMap<String, TestCase> testCaseMap, StringBuilder sbuild) {
		
		String strTestParam = "Fail";
		String strTestPreCons = null;
		String strTestPostCons = null;
		String strTestStepRefs = null;
		String strTestDatas = null;
		String strTDIDs = null;
		ArrayList<TestStep> testStepList = null;
		String strTCID = null;
		String strRunMode = null;
		boolean isPass = true;
		String strSeverity = null;
		String strDescrip = null;
		TestcaseVo tscasObj=new TestcaseVo();
		try {
			Utils.getApplicationLogs().debug("listTestCases-- testcond");

			strTestPreCons = listTestcas.getStrPreCondition();
			String strTestPreCon[] = strTestPreCons.split(",");

			strTestPostCons = listTestcas.getStrPostCondition();
			Utils.getApplicationLogs().debug("Post condition" + strTestPostCons);
			String strTestPostCon[] = strTestPostCons.split(",");
			Utils.getApplicationLogs().debug("Post condition " + strTestPostCon.length);
			strTestStepRefs = listTestcas.getStrTestStepRef();
			strTestDatas = listTestcas.getStrTestDataRef();
			strTDIDs = listTestcas.getStrTDID();
			strTCID = listTestcas.getStrTCID();
			strRunMode = listTestcas.getStrRunMode();
			strSeverity = listTestcas.getStrSeverity();
			strDescrip = listTestcas.getStrDescription();
		
			Utils.getApplicationLogs().debug("TCID :: " + strTCID + " Precond :: "
					+ strTestPreCons + " PostCond :: " + strTestPostCons
					+ " TestREf  :: " + strTestStepRefs + " TDID :: "
					+ strTDIDs + " TestDataRef :: " + strTestDatas
					+ " Description :: " + strDescrip + "Run Mode "
					+ strRunMode);
			try {
				if (strRunMode.equalsIgnoreCase("Y")) {
					// test precondition
					if (strTestPreCons != null && strTestPreCons.length() > 0) {
						for (int i = 0; i < strTestPreCon.length; i++) {
							TestCase testCase = testCaseMap
									.get(strTestPreCon[i]);
							String strPreTDID = testCase.getStrTDID();
							Utils.getApplicationLogs().debug(" strTestPreCon "
									+ strTestPreCon[i]);
							testStepList = testStepMapAllValue
									.get(strTestPreCon[i]);
													
							strTestParam = executefinalTest(testStepList,
									testDataMap, strPreTDID);
							
						
//							isPass = strTestParam.substring(0, 4).equals("Fail") ? false : true;
							strFailureReason=strTestParam.substring(4, strTestParam.length());
							System.out.println("FailureReason--precond--"+strFailureReason);
							Utils.getApplicationLogs().debug("completed>>strTestPreCon>>>..");
							testStepList = null;
							testCase = null;
						}
					}

					// teststepreference
					if (strTestStepRefs != null && strTestStepRefs.trim().length() > 0) {
						Utils.getApplicationLogs().debug("&&&&&&&&&&&&true block&&&&&&&&&&&&&&&&&&&&&&&&"
										+ strTestStepRefs
										+ " :: "
										+ strTestDatas);

						String strTestStepRef[] = strTestStepRefs.split(",");
						System.out.println("step reference length--->"+strTestStepRef.length);
						for (int i = 0; i < strTestStepRef.length; i++) {
							testStepList = testStepMapAllValue
									.get(strTestStepRef[i]);
							System.out.println("teststep reference--->"+testStepMapAllValue.get(strTestStepRef[i]));
							if (strTDIDs != null
									&& strTDIDs.trim().length() > 0) {

								String strTDID[] = strTDIDs.split(",");
								for (int j = 0; j < strTDID.length; j++) {
									System.out
											.println("test data.... from ref");
									strTestParam = executefinalTest(
											testStepList, testDataMap,
											strTDID[j] + "~" + strTestDatas);
									isPass = isPass ? (strTestParam.substring(0, 4)
											.equals("Fail") ? false : true)
											: false;
									strFailureReason=strFailureReason+"--"+strTestParam.substring(4, strTestParam.length());
									System.out.println("failurereason-teststepref----"+strFailureReason);
								}
								strTDID = null;
							}
							testStepList = null;
						}
						Utils.getApplicationLogs().debug("complted ......test step..............");
					} if(true) {
						
						testStepList = testStepMapAllValue.get(strTCID);

						if (strTDIDs != null && strTDIDs.trim().length() > 0) {

							String strTDID[] = strTDIDs.split(",");
							for (int j = 0; j < strTDID.length; j++) {
								
								strTestParam = executefinalTest(testStepList,
										testDataMap, strTDID[j] + "~"
												+ strTestDatas);
								System.out.println("+++++van++++++ "+strTestParam);
								isPass = isPass ? (strTestParam.substring(0, 4).equals("Fail") ? false
										: true)
										: false;
								strFailureReason=strFailureReason+"--"+strTestParam.substring(4, strTestParam.length());
								System.out.println("FailureReason--testcase---"+strFailureReason);
								
							}
							strTDID = null;
						} else {
							strTestParam = executefinalTest(testStepList,
									testDataMap, "");
							System.out.println("-----strTestParam--------"+strTestParam);
							isPass = isPass ? (strTestParam.substring(0, 4).equalsIgnoreCase("Fail") ? false
									: true)
									: false;
							strFailureReason=strFailureReason+"--"+strTestParam.substring(4, strTestParam.length());
							System.out.println("FailureReason--teststep---"+strFailureReason);
							
						}
						testStepList = null;
					}

					if (strTestPostCons != null && strTestPostCons.length() > 0) {

						for (int i = 0; i < strTestPostCon.length; i++) {
							TestCase testCase = testCaseMap
									.get(strTestPostCon[i]);
							String strPostTDID = testCase.getStrTDID();
							testStepList = testStepMapAllValue
									.get(strTestPostCon[i]);
							Utils.getApplicationLogs().debug("????????????????strTestPostCon????????????"
											+ strPostTDID);
							strTestParam = executefinalTest(testStepList,
									testDataMap, strPostTDID);
							System.out.println("********strTestParam*****"+strTestParam);
//							isPass = isPass ? (strTestParam.substring(0, 4).equals("Fail") ? false: true): false;
							strFailureReason=strFailureReason+"--"+strTestParam.substring(4, strTestParam.length());
							System.out.println("FailureReason--postcond--"+strFailureReason);
							
							testStepList = null;
							testCase = null;
							strPostTDID = null;
						}

					}
				}
			} catch (Exception e) {
				strTestParam = e.getMessage();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			strTestPreCons = null;
			strTestPostCons = null;
			strTestStepRefs = null;
			strTestDatas = null;
			strTDIDs = null;
			testStepList = null;

		}
		if (!isPass && (strRunMode.equalsIgnoreCase("Y"))) {
			int o = strSeverity.equals("S1") ? intS1Fail++ : intS2Fail++;
			strTestParam = "Fail";
			System.out.println("fail result--"+strFailureReason);
			
		} else if (strRunMode.equalsIgnoreCase("N")) {
			strTestParam = "Skipped";
			strFailureReason="Skipped";
			intSkipcount++;
			
		} else {
			int o = strSeverity.equals("S1") ? intS1Pass++ : intS2Pass++;
			strTestParam = "Pass";
			strFailureReason="Pass";
			
		}
	
		tscasObj.setStrTCID(strTCID);
		tscasObj.setStrDescrip(strDescrip);
		tscasObj.setStrSeverity(strSeverity);
		tscasObj.setStrTestParam(strTestParam);
		tscasObj.setStrFailureReason(strFailureReason);
		
		strTCID = null;
		strRunMode = null;
		return tscasObj;
	}

	private String executefinalTest(ArrayList<TestStep> testStepList,
			HashMap<String, HashMap<String, Data>> testDataMap, String strTDID) {
		
		String strTestResult = null;
		Method method = null;
		String strStepValue = null, strObject = null, strparm = null,strObjectType=null;
	
		Data data = null;
		HashMap<String, Data> testdata = null;
		String strTestStepData = null;
		TestStep testStep = null;
		String keywords = null;
		try {
           	for (int i = 0; i<testStepList.size(); i++) {
				testStep = testStepList.get(i);
				keywords = testStep.getStrKeyword();
				Utils.getApplicationLogs().debug("keywords---" + keywords);
				strTestStepData = testStep.getStrData();
				strObject = testStep.getStrObject();
				strObjectType=testStep.getStrObjectType();
				strparm = strObject+"~~"+strObjectType;
				Utils.getApplicationLogs().debug("TestStepData--->" + strTestStepData
						+ "strparm :: " + strparm);
				if (strTestStepData != null
						&& strTestStepData.trim().length() > 0) {
					Utils.getApplicationLogs().debug("TDID--->" + strTDID);
					String strTDIDs[] = strTDID.split("~");
					if (strTDIDs != null && strTDIDs.length > 0) {
						testdata = testDataMap.get(strTDIDs[0]);
						data = testdata.get(strTestStepData);
						strStepValue = data.getStrTestValue();
					if (strStepValue != null
								&& strStepValue.trim().length() > 0) {
							strparm = strObject + "~~" + strObjectType +"~~"+strStepValue;
						} else {     	
							testdata = testDataMap.get(strTDIDs[1]);
							data = testdata.get(strTestStepData);
							strStepValue = data.getStrTestValue();
							if (strStepValue != null
									&& strStepValue.trim().length() > 0) {
								strparm = strObject + "~~" +strObjectType+"~~"+ strStepValue;
							}
						}
					}
				}
				Utils.getApplicationLogs().debug("keywords---> " + keywords + "--Parmeter--->" + strparm);
				method = keyMap.get(keywords);
				//Utils.getApplicationLogs().debug("Method Name" + method.getName());
				strTestResult = (String) method.invoke(kobj, strparm);
				System.out.println("%%%%%%strTestResult%%%%%%%%"+strTestResult);
				Utils.getApplicationLogs().debug("Result---> " + strTestResult.substring(0,4));
				if(strTestResult.substring(0,4).equalsIgnoreCase("Fail")){
					System.out.println("^^^^^^^^^^^^^^^^^^^^^");
					break;
				}
				strparm = null;
				// resultSet.add(result);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method = null;
			method = null;
			strStepValue = null;
			strObject = null;
			strparm = null;
			strparm = null;
			strStepValue = null;
			strTestStepData = null;
			strObject = null;
			strparm = null;
			testStep = null;
			testdata = null;
			data = null;
		}
		return strTestResult;
	}

}
