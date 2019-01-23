package com.platform.readings.statistics.parser;

import java.util.Map;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.platform.readings.statistics.model.PlatformStatistics;

public class PlatformReadingsParserTest {

	private PlatformReadingsParser pfReadingsParser;
	
	@Before
	public void setUp() {
		pfReadingsParser = new PlatformReadingsParser();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testReadPlatformSensorReadings() {
		pfReadingsParser.readPlatformSensorReadings();
	}
	
	@Test
	public void testWritePlatformStatistics() {
		PlatformStatistics pfStats1 = new PlatformStatistics("EFGH","00:20:40","393","00:43:20","00:60:00");
		PlatformStatistics pfStats2 = new PlatformStatistics("ABCD","00:16:40","393","00:33:20","00:50:00");
		Map<String, PlatformStatistics> pfStatMap = new TreeMap<>();
		pfStatMap.put(pfStats1.getPlatformName(), pfStats1);
		pfStatMap.put(pfStats2.getPlatformName(), pfStats2);
		pfReadingsParser.writePlatformStatistics(pfStatMap);
		
		System.out.println("Please see the oput in output.csv file under project root directory.");
	}
}
