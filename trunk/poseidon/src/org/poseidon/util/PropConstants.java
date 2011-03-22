package org.poseidon.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Properties;



public class PropConstants {
	public static Properties props = new Properties();
	
	static{
		init();
	}
	
	public static String getProperties(String key){
		return PropConstants.props.getProperty(key);
	}
	
	public static void reLoad(){
		init();
	}
	
	private static void init(){
		String rootPath = PropConstants.class.getResource("/").getFile();
		System.out.println("root path: " + rootPath);
        FilenameFilter filter = new FilenameFilter() {
           public boolean accept(File dir, String name) {
              return name.endsWith(".properties");
           }
        };
        File[] fileArray = new File(rootPath).listFiles(filter);
        System.out.println("fileArray size: " + fileArray.length);
        
        try {
        	 for (File file : fileArray) {
             	System.out.println("loading properties: " + file.getName());
             	props.load(new FileInputStream(file));
             }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
