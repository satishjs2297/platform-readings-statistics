package com.platform.readings.statistics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlatformStatistics {

	private String platformName;
	private String minGapTime;
	private String avgWeightValue;
	private String totalGapTime;	
	private String maxFlightTime;
	
}
