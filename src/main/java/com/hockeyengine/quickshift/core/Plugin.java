/**
 * 
 */
package com.hockeyengine.quickshift.core;

/**
 * @author mpietras
 *
 */
public interface Plugin {
	
	public void init() throws PluginException;
	
	public String start() throws PluginException;
	
	public String stop() throws PluginException;
	
	public String getReport() throws PluginException;
	
	public String helthCheck() throws PluginException;

}
