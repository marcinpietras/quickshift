/**
 * 
 */
package com.hockeyengine.quickshift.core;

/**
 * @author mpietras
 *
 */
public class PluginExecution {
	
	private Plugin plugin;

	private String pluginName;

	private PluginStatus status;
	
	public PluginExecution(String pluginName, Plugin plugin) {
		this.pluginName = pluginName;
		this.plugin = plugin;
		this.status = PluginStatus.READY;
	}

	public String getPluginName() {
		return pluginName;
	}

	public PluginStatus getStatus() {
		return status;
	}

	public void setStatus(PluginStatus status) {
		this.status = status;
	}

	public Plugin getPlugin() {
		return plugin;
	}

	@Override
	public String toString() {
		return "PluginExecution [plugin=" + plugin + ", pluginName=" + pluginName + ", status=" + status + "]";
	}

}
