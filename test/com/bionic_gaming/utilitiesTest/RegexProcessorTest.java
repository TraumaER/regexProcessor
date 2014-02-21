package com.bionic_gaming.utilitiesTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bionic_gaming.utilities.RegexProcessor;
import com.bionic_gaming.utilities.obj.RegexResult;

public class RegexProcessorTest {

	private static RegexProcessor rp;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rp = new RegexProcessor("\\d{2}/\\d{2}/\\d{4}");
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		rp = null;
	}

	@Test
	public void testFindInString() {
		try {
			RegexResult result = rp.findInString("01/22/1987 at the beginning of the string");
			
			assertTrue("Date was not found at the beginning of the string", result.getStart() == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception thrown");
		}
	}

	@Test
	public void testFindAllInString() {
		try {
			List<RegexResult> results = rp.findAllInString("01/22/1987 at the beginning 01/23/1987 of the string");
			
			assertTrue("Result size should be 2 - Size: " + results.size(), results.size() == 2);
			assertTrue("First date was not found at the beginning of the string - Start: " + results.get(0).getStart(), results.get(0).getStart() == 0);
			assertTrue("Second date was not found at the beginning of the string - Start: " + results.get(1).getStart(), results.get(1).getStart() == 28);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception thrown");
		}
	}

	@Test
	public void testFindInFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllInFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindInFiles() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllInFiles() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindInDirectoryWithExtensions() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllInDirectoryWithExtensions() {
		fail("Not yet implemented");
	}

}
