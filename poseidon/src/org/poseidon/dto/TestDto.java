package org.poseidon.dto;

import java.io.Serializable;

public class TestDto implements Serializable {

	private static final long serialVersionUID = -1144317506868446461L;

	private String testString;
	
	private Long testLong;
	
	private boolean testBoo;
	
	private String testDate;
	
	private String[] testStringArray;
	
	private Long[] testLongArray;
	

	public String getTestString() {
		return testString;
	}

	public void setTestString(String testString) {
		this.testString = testString;
	}

	public Long getTestLong() {
		return testLong;
	}

	public void setTestLong(Long testLong) {
		this.testLong = testLong;
	}

	public boolean isTestBoo() {
		return testBoo;
	}

	public void setTestBoo(boolean testBoo) {
		this.testBoo = testBoo;
	}

	public String[] getTestStringArray() {
		return testStringArray;
	}

	public void setTestStringArray(String[] testStringArray) {
		this.testStringArray = testStringArray;
	}

	public Long[] getTestLongArray() {
		return testLongArray;
	}

	public void setTestLongArray(Long[] testLongArray) {
		this.testLongArray = testLongArray;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}
}
