/*
 * 
 *
 * 
 *  Added getter ,Setter method  for all the teststep sheet parameters
 */
package com.Automation.data.excel;

import java.io.Serializable;

public class TestStep implements Serializable {

	private String strTSID = null;
	private String strTSDesc = null;
	private String strKeyword = null;
	private String strObject = null;
	private String strObjectType = null;
	private String strTCID = null;
	private String strData = null;
	private String strTestValueId = null;

	public String getStrTestValueId() {
		return strTestValueId;
	}

	public void setStrTestValueId(String strTestValueId) {
		this.strTestValueId = strTestValueId;
	}

	public String getStrData() {
		return strData;
	}

	public void setStrData(String strData) {
		this.strData = strData;
	}

	public String getStrTCID() {
		return strTCID;
	}

	public void setStrTCID(String strTCID) {
		this.strTCID = strTCID;
	}

	public String getStrTSID() {
		return strTSID;
	}

	public void setStrTSID(String strTSID) {
		this.strTSID = strTSID;
	}

	public String getStrTSDesc() {
		return strTSDesc;
	}

	public void setStrTSDesc(String strTSDesc) {
		this.strTSDesc = strTSDesc;
	}

	public String getStrKeyword() {
		return strKeyword;
	}

	public void setStrKeyword(String strKeyword) {
		this.strKeyword = strKeyword;
	}

	public String getStrObject() {
		return strObject;
	}

	public void setStrObject(String strObject) {
		this.strObject = strObject;
	}

	public String getStrObjectType() {
		return strObjectType;
	}

	public void setStrObjectType(String strObjectType) {
		this.strObjectType = strObjectType;
	}

}
