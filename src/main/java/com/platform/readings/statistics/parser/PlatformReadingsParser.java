package com.platform.readings.statistics.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.platform.readings.statistics.exception.PlatformReadingsException;
import com.platform.readings.statistics.model.PlatformReadings;
import com.platform.readings.statistics.model.PlatformStatistics;

public class PlatformReadingsParser {
	private static final Logger LOG = LogManager.getLogger(PlatformReadings.class);

	private static final String INPUT_FILE_PATH = "input.csv";
	private static final String OUTPUT_FILE_PATH = "output.csv";
	private static final String[] OUTPUT_CSV_FILE_HEADER = { "Platform Name", "Min Gap Time", "Avg Weight Value",
			"Total Gap Time", "Max Flight Time" };

	public Map<String, List<PlatformReadings>> readPlatformSensorReadings() {
		try {
			Map<String, List<PlatformReadings>> pfReadingsMap = new HashMap<>();
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(INPUT_FILE_PATH).getFile());
			try (Reader in = new FileReader(file);) {
				Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
				for (CSVRecord record : records) {
					String platformName = record.get(0);
					String sendTime = record.get(1);
					String receivedTime = record.get(2);
					String numOfSensors = record.get(3);
					String readingValue = record.get(4);
					PlatformReadings pfReadings = new PlatformReadings(platformName, sendTime, receivedTime,
							numOfSensors, readingValue);
					if (null == pfReadingsMap.get(platformName)) {
						List<PlatformReadings> lst = new ArrayList<>();
						lst.add(pfReadings);
						pfReadingsMap.put(platformName, lst);
					} else {
						pfReadingsMap.get(platformName).add(pfReadings);
					}

				}
			}
			LOG.info("pfReadingsMap size :: " + pfReadingsMap.size());
			return pfReadingsMap;
		} catch (Exception e) {
			LOG.error("Exception while readPlatformSensorReadings. Error :: ", e.getMessage());
			throw new PlatformReadingsException(e);
		}
	}

	public void writePlatformStatistics(Map<String, PlatformStatistics> pfStatMap) {
		try {
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(OUTPUT_FILE_PATH));
			try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(OUTPUT_CSV_FILE_HEADER))) {
				pfStatMap.forEach((pfName, pfStats) -> {
					try {
						csvPrinter.printRecord(pfStats.getPlatformName(), pfStats.getMinGapTime(),
								pfStats.getAvgWeightValue(), pfStats.getTotalGapTime(), pfStats.getMaxFlightTime());
					} catch (IOException e) {
						LOG.error("Exception @ writing stats results into output csv file.", e.getMessage());
					}
				});
			}
		} catch (Exception e) {
			LOG.error("Exception while writePlatformStatistics. Error :: ", e.getMessage());
			throw new PlatformReadingsException(e);
		}
	}

}
