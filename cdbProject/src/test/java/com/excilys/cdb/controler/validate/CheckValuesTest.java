package com.excilys.cdb.controler.validate;

import junit.framework.TestCase;

import org.junit.Test;

public class CheckValuesTest extends TestCase {
	
	@Test
	public void testName1() {
		assertEquals(true, CheckValues.checkName("truc random"));
	}

	@Test
	public void testName2() {
		assertEquals(false, CheckValues.checkName(""));
	}

	@Test
	public void testName3() {
		CheckValues.checkName(null);
	}

	@Test
	public void testName4() {
		assertEquals(true, CheckValues.checkName("a123456789a123456789a123456789a123456789a123456789a123456789a123456789a123456789a123456789a123456789"
				+ "a123456789a123456789a123456789a123456789a123456789a123456789a123456789a123456789a123456789a123456789"
				+ "a123456789a123456789a123456789a123456789a123456789a1234"));
	}

	@Test
	public void testName5() {
		assertEquals(false, CheckValues.checkName("a123456789a123456789a123456789a123456789a123456789a123456789a123456789a123456789a123456789a123456789"
				+ "a123456789a123456789a123456789a123456789a123456789a123456789a123456789a123456789a123456789a123456789"
				+ "a123456789a123456789a123456789a123456789a123456789a123456789a123456789a123456789a123456789a123456789a123456789a123456789a1234"));
	}
}
