package com.platform.readings.statistics.exception;

/**
 * @author ysatish
 *
 */
public class PlatformReadingsException extends RuntimeException {	
	private static final long serialVersionUID = -8252401155791492660L;

	public PlatformReadingsException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlatformReadingsException(Throwable cause) {
		super(cause);
	}

	public PlatformReadingsException() {
		
	}
	
	
}
