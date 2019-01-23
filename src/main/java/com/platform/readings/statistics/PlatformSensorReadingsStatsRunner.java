package com.platform.readings.statistics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.platform.readings.statistics.service.PlatformReadingsStatisticsService;

public class PlatformSensorReadingsStatsRunner {
	private static final Logger LOG = LogManager.getLogger(PlatformReadingsStatisticsService.class);

	public static void main(String[] args) {
		long strTime = System.currentTimeMillis();
		LOG.debug("*** Begin ***");
		PlatformReadingsStatisticsService pfStatsSvc = new PlatformReadingsStatisticsService();
		pfStatsSvc.generatePlatformReadingsStatistics();
		LOG.debug("*** End ***:: TT :: {}", (System.currentTimeMillis() - strTime));
	}
}
