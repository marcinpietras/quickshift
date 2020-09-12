/**
 * 
 */
package com.hockeyengine.quickshift.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * @author mpietras
 *
 */
@Component
public class PluginFactory implements ApplicationContextAware {
	
private Logger logger = LoggerFactory.getLogger(PluginFactory.class);
	
	private ApplicationContext applicationContext;

	public Plugin getPlugin(String pluginName) throws PluginNotFoundException {
		try {
			Plugin plugin = (Plugin) this.applicationContext.getBean(pluginName);
			if (plugin != null) {
				return plugin;
			} else {
				logger.error("Cannot find plugin " + pluginName);
				throw new PluginNotFoundException("Cannot find plugin " + pluginName);
			}
		} catch (Exception e) {
			logger.error("Cannot find plugin " + pluginName, e);
			throw new PluginNotFoundException("Cannot find plugin " + pluginName, e);
		}	
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
