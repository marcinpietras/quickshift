/**
 * 
 */
package com.hockeyengine.quickshift.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mpietras
 *
 */
@Component
public class PluginService {

	private Logger logger = LoggerFactory.getLogger(PluginService.class);

	@Autowired
	private PluginFactory pluginFactory;

	private Map<String, PluginExecution> pluginExecution = new HashMap<String, PluginExecution>();

	public String startPlugin(String pluginName) throws PluginNotFoundException, PluginException {
		logger.info("Starting pluginName={}", pluginName);
		PluginExecution pluginExecution = getPluginExecution(pluginName);
		String result = pluginExecution.getPlugin().start();
		pluginExecution.setStatus(PluginStatus.RUNNING);
		return result;
	}

	public String stopPlugin(String pluginName) throws PluginNotFoundException, PluginException {
		logger.info("Stopping pluginName={}", pluginName);
		PluginExecution pluginExecution = getPluginExecution(pluginName);
		String result = pluginExecution.getPlugin().stop();
		pluginExecution.setStatus(PluginStatus.STOPPED);
		return result;
	}

	public PluginStatus getPluginStatus(String pluginName) throws PluginNotFoundException, PluginException {
		logger.info("Getting status of pluginName={}", pluginName);
		PluginExecution pluginExecution = getPluginExecution(pluginName);
		PluginStatus result = pluginExecution.getStatus();
		return result;
	}

	public String getPluginReport(String pluginName) throws PluginNotFoundException, PluginException {
		logger.info("Getting report of pluginName={}", pluginName);
		PluginExecution pluginExecution = getPluginExecution(pluginName);
		String result = pluginExecution.getPlugin().getReport();
		return result;
	}
	
	public String healthCheck(String pluginName) throws PluginNotFoundException, PluginException {
		logger.info("Getting healthCheck of pluginName={}", pluginName);
		PluginExecution pluginExecution = getPluginExecution(pluginName);
		String result = pluginExecution.getPlugin().helthCheck();
		return result;
	}

	public Set<String> getAllKnownPlugins() {
		logger.info("Getting all known plugins");
		return this.pluginExecution.keySet();
	}

	private synchronized PluginExecution getPluginExecution(String pluginName) throws PluginNotFoundException, PluginException {
		PluginExecution pluginExecution = this.pluginExecution.get(pluginName);
		if (pluginExecution == null) {
			Plugin plugin = this.pluginFactory.getPlugin(pluginName);
			plugin.init();
			pluginExecution = new PluginExecution(pluginName, plugin);
			this.pluginExecution.put(pluginName, pluginExecution);
		}
		return pluginExecution;
	}
}
