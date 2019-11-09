/*
 * 
 * 
 *  Added getter ,Setter method  for all the Testcase sheet parameters 
 */
package com.Automation.data.excel;

import java.io.Serializable;

import com.Automation.utils.ExcelTypeEnum;


public class TestCase implements Serializable {
	private String strTCID = null;
	private String strDescription = null;
	private String strRunMode = null;
	private String strTDID = null;
	private String strPreCondition = null;
	private String strPostCondition = null;
	private String strTestStepRef = null;
	private String strTestDataRef = null;
	private String strSeverity = null;

	public String getStrSeverity() {
		return strSeverity;
	}

	public void setStrSeverity(String strSeverity) {
		this.strSeverity = strSeverity;
	}

	public String getStrPostCondition() {
		return strPostCondition;
	}

	public void setStrPostCondition(String strPostCondition) {
		this.strPostCondition = strPostCondition;
	}

	public String getStrTestStepRef() {
		return strTestStepRef;
	}

	public void setStrTestStepRef(String strTestStepRef) {
		this.strTestStepRef = strTestStepRef;
	}

	public String getStrTestDataRef() {
		return strTestDataRef;
	}

	public void setStrTestDataRef(String strTestDataRef) {
		this.strTestDataRef = strTestDataRef;
	}

	public String getStrPreCondition() {
		return strPreCondition;
	}

	public void setStrPreCondition(String strPreCondition) {
		this.strPreCondition = strPreCondition;
	}

	public static ExcelTypeEnum excelTypeEnum = null;

	public void setStrTDID(String strTDID) {
		this.strTDID = strTDID;
	}

	public String getStrTDID() {
		return strTDID;
	}

	public String getStrRunMode() {
		return strRunMode;
	}

	public void setStrRunMode(String strRunMode) {
		this.strRunMode = strRunMode;
	}

	public String getStrTCID() {
		return strTCID;
	}

	public void setStrTCID(String strTCID) {
		this.strTCID = strTCID;
	}

	public String getStrDescription() {
		return strDescription;
	}

	public void setStrDescription(String strDescription) {
		this.strDescription = strDescription;
	}

}
