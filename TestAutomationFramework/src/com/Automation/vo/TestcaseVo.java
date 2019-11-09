package com.Automation.vo;

import java.io.Serializable;

public class TestcaseVo implements Serializable{
private String strFile;
private int intS1Pass;
private int intS2Pass;
private int intS1Fail;
private int intS2Fail;
private int intTttestcount;
private int intSkipcount;
private String color;

private String strTCID;
private String strDescrip;
private String strSeverity;
private String strTestParam;

private String strFailureReason;


public String getStrFailureReason() {
	return strFailureReason;
}
public void setStrFailureReason(String strFailureReason) {
	this.strFailureReason = strFailureReason;
}

public int getIntSkipcount() {
	return intSkipcount;
}
public void setIntSkipcount(int intSkipcount) {
	this.intSkipcount = intSkipcount;
}
public String getStrTCID() {
	return strTCID;
}
public void setStrTCID(String strTCID) {
	this.strTCID = strTCID;
}
public String getStrDescrip() {
	return strDescrip;
}
public void setStrDescrip(String strDescrip) {
	this.strDescrip = strDescrip;
}
public String getStrSeverity() {
	return strSeverity;
}
public void setStrSeverity(String strSeverity) {
	this.strSeverity = strSeverity;
}
public String getStrTestParam() {
	return strTestParam;
}
public void setStrTestParam(String strTestParam) {
	this.strTestParam = strTestParam;
}
public String getStrFile() {
	return strFile;
}
public void setStrFile(String strFile) {
	this.strFile = strFile;
}
public int getIntS1Pass() {
	return intS1Pass;
}
public void setIntS1Pass(int intS1Pass) {
	this.intS1Pass = intS1Pass;
}
public int getIntS2Pass() {
	return intS2Pass;
}
public void setIntS2Pass(int intS2Pass) {
	this.intS2Pass = intS2Pass;
}
public int getIntS1Fail() {
	return intS1Fail;
}
public void setIntS1Fail(int intS1Fail) {
	this.intS1Fail = intS1Fail;
}
public int getIntS2Fail() {
	return intS2Fail;
}
public void setIntS2Fail(int intS2Fail) {
	this.intS2Fail = intS2Fail;
}
public int getIntTttestcount() {
	return intTttestcount;
}
public void setIntTttestcount(int intTttestcount) {
	this.intTttestcount = intTttestcount;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
}
