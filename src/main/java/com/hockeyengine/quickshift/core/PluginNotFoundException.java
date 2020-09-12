package com.hockeyengine.quickshift.core;

public class PluginNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public PluginNotFoundException(String message) {
		super(message);
	}

	public PluginNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
