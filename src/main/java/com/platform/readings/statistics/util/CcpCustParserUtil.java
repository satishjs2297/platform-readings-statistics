package com.platform.readings.statistics.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CcpCustParserUtil {

	@SuppressWarnings("unchecked")
	public static List<Map<String, String>> jsonToMapConverter(String delimiter, String jsonString) throws IOException {

		List<Map<String, String>> colValMapLst = null;
//		ObjectMapper mapper = new ObjectMapper();
//		Map<String, Object> keyValueMap = mapper.readValue(jsonString, Map.class);
//		colValMapLst = (List<Map<String, String>>) keyValueMap.get(delimiter);

		return colValMapLst;
	}
	
	public static String toCSV(Object[] record) {
        String result = "";

        if (record.length > 0) {
            StringBuilder sb = new StringBuilder();

            for (Object s : record) {
                sb.append(s).append(",");
            }

            result = sb.deleteCharAt(sb.length() - 1).toString();
        }
        return result;
}

	public static void main(String[] args) throws IOException {
		
		
		System.out.println("********************Json to Map String****************************");
		List<Map<String, String>> colValMapLst = jsonToMapConverter("privacy-identifying-key",
				"{\"privacy-identifying-key\":"
						+ " [{\"ACCT_ID\" :\"20000000187900451\",\"SOR_ID\" :\"2\",\"ENT_CUST_ID\":\"1127814962\"}]}");
		System.out.println(colValMapLst);
		
		System.out.println("********************CSV String****************************");
		
		List<Object[]> records = new ArrayList<>();
		records.add(Arrays.asList("Testing", 121, 342.32, "abc").toArray());
		records.add(Arrays.asList("abc", 2297, 12323.32, "pqr").toArray());
		for(Object[] record : records) {
			System.out.println(toCSV(record));
		}
	}
}
