package com.example.hwapp;

import java.util.Properties;

public class Config {

	private static Config objConfig;
	private Properties properties = new Properties();
	
	public Config() {
		properties.setProperty("API_LINK", "http://www.codehasher.de/rest/api/query.php?");
	}
	
	public static Config getInstance() {
	   if (objConfig == null)
	   {
	      objConfig = new Config();
	   }
	   return objConfig;
	}
	
	public String getValue(String key) {
		return properties.getProperty(key);
	}
}