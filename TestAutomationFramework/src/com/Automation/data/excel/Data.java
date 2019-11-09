
/*
 *Added getter ,Setter method  for all the testdata sheet parameters 
 */
package com.Automation.data.excel;

import java.io.Serializable;

import com.Automation.utils.ExcelTypeEnum;



public class Data implements Serializable {
	private String strTestDataID = null;
	private String strTestValueId = null;
	private String strTestValue = null;

	public String getStrTestValue() {
		return strTestValue;
	}

	public void setStrTestValue(String strTestValue) {
		this.strTestValue = strTestValue;
	}

	public String getStrTestValueId() {
		return strTestValueId;
	}

	public void setStrTestValueId(String strTestValueId) {
		this.strTestValueId = strTestValueId;
	}

	public static ExcelTypeEnum excelTypeEnum = null;

	public String getStrTestDataID() {
		return strTestDataID;
	}

	public void setStrTestDataID(String strTestDataID) {
		this.strTestDataID = strTestDataID;
	}

}
