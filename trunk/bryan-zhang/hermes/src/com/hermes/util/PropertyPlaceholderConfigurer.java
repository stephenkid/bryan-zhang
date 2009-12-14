package com.hermes.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public class PropertyPlaceholderConfigurer extends
		org.springframework.beans.factory.config.PropertyPlaceholderConfigurer {
	/**
	 * 根属性文件位置
	 */
	private Resource rootLocation;

	/**
	 * 属性
	 */
	private Properties properties;

	/**
	 * 子属性文件位置
	 */
	private String[] childLocations;

	/**
	 * 子属性文件位置
	 */
	private String childLocation;

	private Resource getResource(String location) {
		location = parseStringValue(location, properties, new HashSet<String>());
		DefaultResourceLoader loader = new DefaultResourceLoader();
		return loader.getResource(location);
	}

	public Resource getRootLocation() {
		return rootLocation;
	}

	public void setRootLocation(Resource rootLocation) throws IOException {
		this.rootLocation = rootLocation;
		properties = new Properties();
		InputStream in = rootLocation.getInputStream();
		try {
			properties.load(rootLocation.getInputStream());
		} finally {
			in.close();
		}
	}

	public String[] getChildLocations() {
		return childLocations;
	}

	public void setChildLocations(String[] childLocations) {
		this.childLocations = childLocations;
		Resource[] resources = new Resource[childLocations.length + 1];
		for (int i = 0; i < childLocations.length; i++) {
			resources[i] = getResource(childLocations[i]);
		}

		resources[childLocations.length] = rootLocation;

		setLocations(resources);
	}

	public String getChildLocation() {
		return childLocation;
	}

	public void setChildLocation(String childLocation) {
		this.childLocation = childLocation;
		setLocation(getResource(childLocation));
	}

}
