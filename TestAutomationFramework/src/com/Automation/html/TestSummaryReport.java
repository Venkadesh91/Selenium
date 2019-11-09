package com.Automation.html;

import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.Automation.utils.Utils;
import com.Automation.vo.TestCaseList;
import com.Automation.vo.TestcaseVo;
import com.Automation.vo.TestdetailsVo;

public class TestSummaryReport {
	static StringBuffer sbuild=new StringBuffer();
	static StringBuffer sbuild1=new StringBuffer();
	static StringBuffer sbuild2=new StringBuffer();
	SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy hh:mm:ss");
	
	static int v=1;
/*	public void testDetailsHtml(TestdetailsVo testdtObj,TestCaseList tesCasList,String strFile,boolean isMain){
		System.out.println("11111111111111111111111111");
		TestcaseVo testCaseVoObj=null;
		try{
			sbuild1.append("<html><head>");
			sbuild1.append("<script src='jquery-1.3.2.min.js'></script>");
			sbuild1.append("<style>body {background-color:lightgray}h1   {color:blue}p    {color:green}th   {bgcolor:green}a[id^='content']{text-decoration:none;}</style></head>");
			sbuild1.append("<body><table name='van' border='1' id='content'><tbody><tr bgcolor='green'><td colspan='8'><center>Reimbursement Application - Scenario1_TC1.1 Automation Execution Result</center></td></tr>");
			sbuild1.append("<tr><td colspan='2'>Date &amp; Time : </td><td colspan='2'>"+ testdtObj.getStrRunDate() + "</td>");
			sbuild1.append("<td colspan='2'>Iteration Mode : </td><td colspan='2'>RunOneIterationOnly</td></tr>");
			sbuild1.append("<tr><td colspan='2'>Start Iteration : </td><td colspan='2'>1</td><td colspan='2'>End Iteration : </td><td colspan='2'>1</td></tr>");
			sbuild1.append("<tr><td colspan='2'>Browser : </td><td colspan='2'>Chrome</td><td colspan='2'>Executed on : </td><td colspan='2'>Local Machine</td></tr>");
			sbuild1.append("<tr><th>Suite Name</th><th>No. of Test Cases Passed</th><th>No. of Test Cases Failed</th><th>No. of Test Cases Skipped</th><th>S1-Test Cases Failed</th><th>S2-Test Cases Failed</th><th>Total Test Cases</th><th>Quality Criteria</th></tr>");
			int v1=1;
			for(int i=0;i<tesCasList.size();i++){
				testCaseVoObj=tesCasList.get(i);
				sbuild1.append("<tr><td><a href='#' id='content"+v1+"'>+</a>" + testCaseVoObj.getStrFile()
						+ "</a></td><td>" + (testCaseVoObj.getIntS1Pass() + testCaseVoObj.getIntS2Pass()) + "</td><td>"
						+ (testCaseVoObj.getIntS1Fail() + testCaseVoObj.getIntS2Fail()) + "</td><td>" + testCaseVoObj.getIntSkipcount()
						+ "</td><td>" + testCaseVoObj.getIntS1Fail() + "</td><td>" + testCaseVoObj.getIntS2Fail()
						+ "</td><td>" + testCaseVoObj.getIntTttestcount() + "</td><td bgcolor='"
						+ testCaseVoObj.getColor() + "'></td></tr>");
				testCaseVoObj=null;
				v1++;
			}
			sbuild1.append("</tbody></table><br><br><br>");
			sbuild.append(sbuild1);
			sbuild.append(sbuild2);
			generateHtml(sbuild.toString(), strFile, isMain);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}*/
	/*public void testSubHtml(TestCaseList testCasList,String strFile,boolean isMain){
		System.out.println("000000000000000000000000");
		StringBuffer sbuild1=new StringBuffer();
		TestcaseVo testcasobj=null;
		try{
	sbuild.append("<html><head>");
		sbuild.append("<body><center><h1>" + strFile
		+ "Summary Report</h1></center></body>");
		sbuild2.append("<table border=1 id='content"+v+"id' style='display:none;'><tbody><tr class='content"+v+"text'><th>TCID</th><th>Description</th><th>Severity</th><th>Status</th><th>FailureReason</th><th>Date</th><th>Screenshot</th></tr>");
		for(int i=0;i<testCasList.size();i++){
			testcasobj=testCasList.get(i);
		sbuild2.append("<tr class='content"+v+"text'><td>" + testcasobj.getStrTCID() + "</td><td>" + testcasobj.getStrDescrip()
		+ "</td><td>" + testcasobj.getStrSeverity() + "</td><td>" + testcasobj.getStrTestParam() + "</td><td>"+testcasobj.getStrFailureReason()+"</td>");
		sbuild2.append("<td>"+new Date()+"</td><td><a href='"+testcasobj.getStrTCID()+"-"+strFile+".png'>screenshot</a></td></tr>");
		testcasobj=null;
		}
		sbuild2.append("</tbody></table>");
		sbuild.append("</head>");
		/////////////////////////////////////
		////////////////////////////////////
		sbuild2.append("<p><B><U>Quality Criteria Legend:</U></B></p>");
		sbuild2.append("<p><B>S1</B>--Severity 1 TestCases</p>");
		sbuild2.append("<p><B>S2</B>--Severity 2 TestCases</p>");
		sbuild2.append("<table border=3><tr><td bgcolor='red'>---</td><td>More than 25% S1 Cases are fail</td></tr>"
				+ "<tr><td bgcolor='orange'>---</td><td><25% of S1 and >50% S2 Cases are fail</td></tr>"
				+ "<tr><td bgcolor='yellow'>---</td></td><td><25% of S1 and <50% S2 are fail</td></tr>"
				+ "<tr><td bgcolor='blue'>---</td></td><td><25% of S1 and  S2 all pass</td></tr>"
				+ "<tr><td bgcolor='cyan'>---</td></td><td>S1 Cases are all pass and <50% are fail</td></tr>"
				+ "<tr><td bgcolor='green'>---</td><td>S1 and S2 cases are all pass</td></tr></table>");
		sbuild2.append("</body>");
		sbuild2.append("</html>");
		v++;
		
//		generateHtml(sbuild.toString(), strFile, isMain);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	*/
	public void generateHtml(String strTestParam, String strFile, boolean isMain) {
		boolean isFileexists = false;
		File file = null;
		FileOutputStream fout = null;
		String testReport="TestReport.html";
		try {
			System.out.println("ExecutionSummaryPath---"+ Utils.getPathProperties().getProperty(
							"executionsummarypath"));
			if (isMain) {
				file = new File(Utils.getPathProperties().getProperty("executionsummarypath")+File.separator+testReport);
			} else {
				String str = Utils.getPathProperties().getProperty("executionsummarypath")+File.separator+testReport;
				str = str.replaceAll(testReport, strFile+".html");
				file = new File(str);
			}
			fout = new FileOutputStream(file);
			isFileexists = file.exists() ? true : file.createNewFile();
			System.out.println("File is created :: "+file.getName());
			byte resultByte[] = strTestParam.getBytes();
			fout.write(resultByte);
			fout.flush();
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
public void testDetailsHtml(TestdetailsVo testdtObj,TestCaseList tesCasList,String strFile,boolean isMain){
	
		TestcaseVo testCaseVoObj=null;
		try{
			sbuild.append("<html><head>");
			
			sbuild.append("<script> function addRow(){ var table=document.getElementById('van'); var tbody=document.getElementsByTagName('TBODY')[0]; var row=document.createElement('TR'); var cell1=document.createElement('TD'); var cell2=document.createElement('TD'); var cell3=document.createElement('TD');cell1.innerHTML='One';cell2.innerHTML='Two';cell3.innerHTML='Three';row.appendChild(cell1);row.appendChild(cell2);row.appendChild(cell3);tbody.appendChild(row);}</script>");
			sbuild.append("<style>body {background-color:lightgray}h1   {color:blue}p    {color:green}th   {bgcolor:green}</style></head>");
			sbuild.append("<body><table border='1' name='van'><tbody><tr bgcolor='green' style='height:50px;'><td colspan='8'><center style='color:white;'><font size='6'>TestAutomation Execution Result</font></center></td></tr>");
		sbuild.append("<tr><td colspan='2'>Start Time : </td><td colspan='2'>"+ testdtObj.getStrRunDate() + "</td>");
		sbuild.append("<td colspan='2'>End Time : </td><td colspan='2'>"+ sdf.format(new Date()) + "</td></tr>");
//			sbuild.append("<tr><td colspan='2'>Start Iteration : </td><td colspan='2'>1</td><td colspan='2'>End Iteration : </td><td colspan='2'>1</td></tr>");
		sbuild.append("<tr><td colspan='2'>Browser : </td><td colspan='2'>Chrome</td><td colspan='2'>Executed on : </td><td colspan='2'>"+getIpAddress()+"</td></tr>");
		sbuild.append("<tr><th>Suite Name</th><th>No. of Test Cases Passed</th><th>No. of Test Cases Failed</th><th>No. of Test Cases Skipped</th><th>S1-Test Cases Failed</th><th>S2-Test Cases Failed</th><th>Total Test Cases</th><th>Quality Criteria</th></tr>");

		for(int i=0;i<tesCasList.size();i++){
			testCaseVoObj=tesCasList.get(i);
			sbuild.append("<tr><td><a href='" + testCaseVoObj.getStrFile() + ".html'>" + testCaseVoObj.getStrFile()
					+ "</a></td><td>" + (testCaseVoObj.getIntS1Pass() + testCaseVoObj.getIntS2Pass()) + "</td><td>"
					+ (testCaseVoObj.getIntS1Fail() + testCaseVoObj.getIntS2Fail()) + "</td><td>" + testCaseVoObj.getIntSkipcount()
						+ "</td><td>" + testCaseVoObj.getIntS1Fail() + "</td><td>" + testCaseVoObj.getIntS2Fail()
						+ "</td><td>" + testCaseVoObj.getIntTttestcount() + "</td><td bgcolor='"
						+ testCaseVoObj.getColor() + "'></td></tr>");
				testCaseVoObj=null;
		}

		sbuild.append("</body>");
		sbuild.append("</tbody></table><br><br><br>");
		sbuild.append("<p><B><U>Quality Criteria Legend:</U></B></p>");
			sbuild.append("<p><B>S1</B>--Severity 1 TestCases</p>");
		sbuild.append("<p><B>S2</B>--Severity 2 TestCases</p>");
		sbuild.append("<table border=3><tr><td bgcolor='red'>---</td><td>More than 25% S1 Cases are fail</td></tr>"
				+ "<tr><td bgcolor='orange'>---</td><td><25% of S1 and >50% S2 Cases are fail</td></tr>"
				+ "<tr><td bgcolor='yellow'>---</td></td><td><25% of S1 and <50% S2 are fail</td></tr>"
				+ "<tr><td bgcolor='blue'>---</td></td><td><25% of S1 and  S2 all pass</td></tr>"
				+ "<tr><td bgcolor='cyan'>---</td></td><td>S1 Cases are all pass and <50% are fail</td></tr>"					+ "<tr><td bgcolor='green'>---</td><td>S1 and S2 cases are all pass</td></tr></table>");
			sbuild.append("</html>");
			generateHtml(sbuild.toString(), strFile, isMain);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void testSubHtml(TestCaseList testCasList,String strFile,boolean isMain){
	StringBuffer sbuild=new StringBuffer();
	TestcaseVo testcasobj=null;
	try{
	sbuild.append("<html><head>");
	sbuild.append("<body><center><h3>" + strFile
			+"    "+"Summary Report</h3></center></body>");
	sbuild.append("<center> <table border=1><tr style='width:40px;'><th>TCID</th><th>Description</th><th>Severity</th><th>Status</th><th>FailureReason</th></tr>");
for(int i=0;i<testCasList.size();i++){
		testcasobj=testCasList.get(i);
	sbuild.append("<tr><td>" + testcasobj.getStrTCID() + "</td><td>" + testcasobj.getStrDescrip()
+ "</td><td>" + testcasobj.getStrSeverity() + "</td><td>" + testcasobj.getStrTestParam() + "</td><td>"+testcasobj.getStrFailureReason()+"</td>");
	//sbuild.append("<td>" + testcasobj.getStrTCID() + "</td><td>" + testcasobj.getStrDescrip()+"</td></tr>");
	testcasobj=null;
	}
	sbuild.append("</table>");
	sbuild.append("</head>");
generateHtml(sbuild.toString(), strFile, isMain);
	}
	catch(Exception e){
		e.printStackTrace();
}

}
	private String getIpAddress(){
		String strIp="";
		try{
			strIp = InetAddress.getLocalHost().getHostAddress();
			
		}catch(Exception e){}
		return strIp;
	}
}
