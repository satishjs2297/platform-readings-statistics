package com.platform.readings.statistics.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.platform.readings.statistics.model.PlatformReadings;
import com.platform.readings.statistics.model.PlatformStatistics;

public class PlatformReadingsStatisticsUtils {

	public static void calculateMinGapTime(Map<String, List<PlatformReadings>> pfReadingsMap,
			Map<String, PlatformStatistics> pfStatsMap) {		
		pfReadingsMap.forEach((platformName, pfReaderLst) -> {
			int minGap = 0;
			for(int i = 0; i < pfReaderLst.size() - 1; i++) {
				int fromVal = Integer.valueOf(pfReaderLst.get(i).getRecieveTime());
				int toVal = Integer.valueOf(pfReaderLst.get(i + 1).getRecieveTime());
				if(i == 0) {
					minGap = (toVal - fromVal);
				} else if((toVal - fromVal) < minGap) {
					minGap = (toVal - fromVal);
				}
			}
			if(null == pfStatsMap.get(platformName)) {
				pfStatsMap.put(platformName, new PlatformStatistics());
			}
			pfStatsMap.get(platformName).setMinGapTime(convertSecondsToHHMMSSFormat(minGap));
		});
		
	}

	public static void calculateAvgWeightValue(Map<String, List<PlatformReadings>> pfReadingsMap,
			Map<String, PlatformStatistics> pfStatsMap) {

	}

	public static void calculateTotalGapTime(Map<String, List<PlatformReadings>> pfReadingsMap,
			Map<String, PlatformStatistics> pfStatsMap) {

	}

	public static void calculateMaxFlightTime(Map<String, List<PlatformReadings>> pfReadingsMap,
			Map<String, PlatformStatistics> pfStatsMap) {

	}

	
	private static String convertSecondsToHHMMSSFormat(int seconds) {
		Date d = new Date(seconds * 1000L);
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss"); // HH for 0-23
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		String time = df.format(d);
		return time;
	}
}
