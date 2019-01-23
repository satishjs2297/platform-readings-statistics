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
public class PlatformReadings {

	private String platformName;
	private String sendTime;
	private String recieveTime;
	private String numberOfSensors;
	private String readingValue;

}
