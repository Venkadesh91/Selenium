/*
 *  
 *  
 *  TestSuite contains all the testcases ,teststeps ,and testdata shet datas
 */

package com.Automation.data.excel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class TestSuite implements Serializable {

	private HashMap<String, TestCase> testCaseMap;
	private HashMap<String, ArrayList<TestStep>> testStepMapAllValue;
	private HashMap<String, HashMap<String, Data>> testDataMap;

	public HashMap<String, HashMap<String, Data>> getTestDataMap() {
		return testDataMap;
	}

	public void setTestDataMap(
			HashMap<String, HashMap<String, Data>> testDataMap) {
		this.testDataMap = testDataMap;
	}

	public TestSuite(HashMap<String, TestCase> testCaseMap,
			HashMap<String, ArrayList<TestStep>> testStepMapAllValue,
			HashMap<String, HashMap<String, Data>> testDataMap) {
		this.testCaseMap = testCaseMap;
		this.testStepMapAllValue = testStepMapAllValue;
		this.testDataMap = testDataMap;
	}

	public HashMap<String, TestCase> getTestCaseMap() {
		return testCaseMap;
	}

	public void setTestCaseMap(HashMap<String, TestCase> testCaseMap) {
		this.testCaseMap = testCaseMap;
	}

	public HashMap<String, ArrayList<TestStep>> getTestStepMapAllValue() {
		return testStepMapAllValue;
	}

	public void setTestStepMapAllValue(
			HashMap<String, ArrayList<TestStep>> testStepMapAllValue) {
		this.testStepMapAllValue = testStepMapAllValue;
	}

}
