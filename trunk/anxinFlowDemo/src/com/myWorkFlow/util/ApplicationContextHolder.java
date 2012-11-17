package com.myWorkFlow.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextHolder implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@SuppressWarnings("all")
	public void setApplicationContext(ApplicationContext context){
		if (this.applicationContext != null) {
			throw new IllegalStateException(
					"ApplicationContextHolder already holded 'applicationContext'.");
		}
		this.applicationContext = context;
	}

	public static ApplicationContext getApplicationContext() {
		if (applicationContext == null)
			throw new IllegalStateException(
					"'applicationContext' property is null,ApplicationContextHolder not yet init.");
		return applicationContext;
	}

	public static Object getBean(String beanName) {
		return getApplicationContext().getBean(beanName);
	}

	public static void cleanHolder() {
		applicationContext = null;
	}
}