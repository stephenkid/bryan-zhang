package org.poseidon.util;

import org.apache.log4j.xml.DOMConfigurator;
import java.net.URL;

/**
 *
 * @author dajiang_fang
 */
public class LoggerUtil {

	private static final String Default_Log4jConfigLocation = "log4j.xml";
	private static final long Default_Log4jRefreshInterval = 30000L;

	/**
	 * 初始化log4j；自动刷新配置。
	 * @see initLogging(org.apache.log4j.Logger logger)
	 * @param clazz
	 * @param log4jConfigLocation
	 * @param refreshInterval
	 */
	public static void initLogging(java.lang.Class<?> clazz, String log4jConfigLocation, long refreshInterval) {
		if (log4jConfigLocation == null) {
			log4jConfigLocation = Default_Log4jConfigLocation;
		}
		if (refreshInterval <= 0L) {
			refreshInterval = Default_Log4jRefreshInterval;
		}
		URL url = clazz.getClassLoader().getResource(log4jConfigLocation);
		if (url == null) {
			return;
		}
		DOMConfigurator.configureAndWatch(url.getPath(), refreshInterval);
	}

	/**
	 * （本方法似乎有一定问题。）
	 * 使用方法：
	 * 定义： private static final Logger LOGGER = Logger.getLogger(SomeClass.class);
	 * 初始化：
	 * 	static {
	 *		LoggerUtil.initLogging(LOGGER);
	 *	}
	 * 使用：
	 * LOGGER.debug("xxxxxxxxxxxx");
	 * @param logger
	 */
	public static void initLogging(org.apache.log4j.Logger logger) {
		initLogging(logger.getClass(), Default_Log4jConfigLocation, Default_Log4jRefreshInterval);
	}
}
