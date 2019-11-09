

package com.Automation.utils;

public enum WebElementLocatorTypeEnum {

	/**
	 * Locator Types for the web elements
	 */
	XPATH("XPATH"),
	CSS("CSS"),
	ID("ID"),
	NAME("NAME");

	String text;

	private WebElementLocatorTypeEnum(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public WebElementLocatorTypeEnum getWebElementLocatorTypeEnum(String text) {
		return this;
	}

}
