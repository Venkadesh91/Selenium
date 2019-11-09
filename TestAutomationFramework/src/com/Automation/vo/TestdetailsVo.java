package com.Automation.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class TestdetailsVo implements Serializable{
private String strRunDate;
private String strStrDate;
private String strEndDate;
private String strExeDate;
private String strRunEnv;
private String strBrowTp;
private String strRlsVer;
SimpleDateFormat sdfHours = new SimpleDateFormat("HH");

public String getStrRlsVer() {
	return strRlsVer;
}
public void setStrRlsVer(String strRlsVer) {
	this.strRlsVer = strRlsVer;
}
public String getStrRunDate() {
	return strRunDate;
}
public void setStrRunDate(String strRunDate) {
	this.strRunDate = strRunDate;
}
public String getStrStrDate() {
	return strStrDate;
}
public void setStrStrDate(String strStrDate) {
	this.strStrDate = strStrDate;
}
public String getStrEndDate() {
	return strEndDate;
}
public void setStrEndDate(String strEndDate) {
	this.strEndDate = strEndDate;
}
public String getStrExeDate() {
	return strExeDate;
}
public void setStrExeDate(String strExeDate) {
	this.strExeDate = strExeDate;
}
public String getStrRunEnv() {
	return strRunEnv;
}
public void setStrRunEnv(String strRunEnv) {
	this.strRunEnv = strRunEnv;
}
public String getStrBrowTp() {
	return strBrowTp;
}
public void setStrBrowTp(String strBrowTp) {
	this.strBrowTp = strBrowTp;
}

}
