package com.rohlx.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.rohlx.util.email.EmailHelperUtil;

public class TestUtil {
	@Test
	public void test() {
		assertFalse(EmailHelperUtil.getGeneratedRequestNumber().equals(
				EmailHelperUtil.getGeneratedRequestNumber()));
	}
	
}
