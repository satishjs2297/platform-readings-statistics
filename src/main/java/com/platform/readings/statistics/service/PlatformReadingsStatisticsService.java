package com.platform.readings.statistics.service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.platform.readings.statistics.exception.PlatformReadingsException;
import com.platform.readings.statistics.model.PlatformReadings;
import com.platform.readings.statistics.model.PlatformStatistics;
import com.platform.readings.statistics.parser.PlatformReadingsParser;
import com.platform.readings.statistics.util.PlatformReadingsStatisticsUtils;

public class PlatformReadingsStatisticsService {
	private static final Logger LOG = LogManager.getLogger(PlatformReadingsStatisticsService.class);

	public void generatePlatformReadingsStatistics() {
		try {
			LOG.debug("*****  Begin  *****");
			PlatformReadingsParser pfReadingsParser = new PlatformReadingsParser();
			Map<String, List<PlatformReadings>> pfReadingsMap = pfReadingsParser.readPlatformSensorReadings();

			Map<String, PlatformStatistics> pfStatsMap = new TreeMap<>();

			PlatformReadingsStatisticsUtils.calculateAvgWeightValue(pfReadingsMap, pfStatsMap);
			PlatformReadingsStatisticsUtils.calculateMaxFlightTime(pfReadingsMap, pfStatsMap);
			PlatformReadingsStatisticsUtils.calculateMinGapTime(pfReadingsMap, pfStatsMap);
			PlatformReadingsStatisticsUtils.calculateTotalGapTime(pfReadingsMap, pfStatsMap);

			pfReadingsParser.writePlatformStatistics(pfStatsMap);

			LOG.debug("*****  End  *****");
		} catch (Exception e) {
			LOG.error("Exception @ generatePlatformReadingsStatistics. error :: ", e.getMessage());
			throw new PlatformReadingsException(e);
		}

	}

}
