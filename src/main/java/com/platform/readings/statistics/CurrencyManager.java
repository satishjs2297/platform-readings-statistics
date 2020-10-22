package com.platform.readings.statistics;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CurrencyManager {

	private static final String FILE_PATH = "C:\\Users\\satis\\OneDrive\\Desktop\\java\\";

	private static final String BUILD_FILE_PATH = FILE_PATH + "buildID.txt";

	private static String buildId;

	private static Map<String, String> currencyMap = new HashMap<>();

	private static ObjectMapper mapper = new ObjectMapper();

	@SuppressWarnings("unchecked")
	public static void poolForCurrencyUpdates() throws Exception {
		Map<String, String> buildMetaMap = mapper.readValue(new File(BUILD_FILE_PATH), Map.class);

		if (Objects.nonNull(buildId) && buildId.equals(buildMetaMap.get("buildID"))) {
			System.out.println("Build Id is same.. Skip the process.");
			return;
		} else {
			buildId = buildMetaMap.get("buildID");
		}

		String currencyFilePath = FILE_PATH + buildMetaMap.get("FileName");
		currencyMap.putAll(mapper.readValue(new File(currencyFilePath), Map.class));
		System.out.println(currencyMap);

	}

	public static void main(String[] args) throws Exception {

		TimerTask task = new TimerTask() {
			public void run() {
				try {
					poolForCurrencyUpdates();
				} catch (Exception e) {
					System.err.println("failed to calcuate currency :: " + e.getMessage());
				}
			}
		};
		Timer timer = new Timer("Timer");

		long delay = 60000L;
		timer.scheduleAtFixedRate(task, 0, delay);
	}

}
