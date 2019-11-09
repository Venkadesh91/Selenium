


package com.Automation.utils;

public enum ExcelTypeEnum {

	/**
	 * Locator Types for the web elements
	 */

	TESTCASES("TESTCASES"),

	TESTSTEPS("TESTSTEPS"),

	TESTDATA("TESTDATA"),

	TCID("TCID"),

	DESCRIPTION("DESCRIPTION"),

	TDID("TDID"),

	RUNMODE("RUNMODE"),

	RUNMODEY("Y"), RUNMODEN("N"), PRECONDITIONS("PRECONDITIONS"), POSTCONDITIONS(
			"POSTCONDITIONS"), TESTSTEPREFERENCE("TESTSTEPREFERENCE"), TESTDATAREFERENCE(
			"TESTDATAREFERENCE"),

	TSID("TSID"),

	KEYWORD("KEYWORD"),

	OBJECT("OBJECT"),

	OBJECTTYPE("OBJECTTYPE"),

	DATA("DATA"),

	SKIP("SKIP"),

	PASS("PASS"),

	FAIL("FAIL");

	String text;

	private ExcelTypeEnum(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public ExcelTypeEnum getExcelTypeEnum(String text) {
		return this;
	}

}
