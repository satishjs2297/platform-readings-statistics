package com.platform.readings.statistics.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;

import com.platform.readings.statistics.model.PlatformReadings;
import com.platform.readings.statistics.model.PlatformStatistics;

public class PlatformReadingsStatisticsUtilsTest {

	
	@Test
	public void testCalculateAvgWeightValue() {
		
		String platformName = "ABCD";
		Map<String, List<PlatformReadings>> pfReadingMap = new TreeMap<>();
		PlatformReadings pReading1 = new PlatformReadings(platformName, "1000", "2000", "3", "-90");
		PlatformReadings pReading2 = new PlatformReadings(platformName, "1500", "3000", "67", "94");
		PlatformReadings pReading3 = new PlatformReadings(platformName, "1000", "4000", "42", "907");	
		pfReadingMap.put(platformName, Arrays.asList(pReading1, pReading2, pReading3));
	
		Map<String, PlatformStatistics> pfStatsMap = new HashMap<>();
		PlatformReadingsStatisticsUtils.calculateAvgWeightValue(pfReadingMap, pfStatsMap);
	
		Assert.assertFalse(pfStatsMap.isEmpty());
		Assert.assertNotNull(pfStatsMap.get(platformName));
		Assert.assertEquals("393", pfStatsMap.get(platformName).getAvgWeightValue());
		
	}
}
