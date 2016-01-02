package perslib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class _Properties {
	
	static String prodpath="./WEB-INF/classes/";
	static String devpath="./../";
	
	public static Properties loadProperties() {		
		return loadPropertiesFile((new File("/var/www/scheme_prod")).exists() ? prodpath+"app_prod.properties" : 
			devpath+"app.properties");
	}
	public static Properties loadProperties(String fileName) {		
		return loadPropertiesFile((new File("/var/www/scheme_prod")).exists() ? prodpath+fileName : 
			devpath+fileName);
	}
	
	private static Properties loadPropertiesFile(String fileName) {
		Properties properties= new Properties();
		try {
			FileInputStream in = new FileInputStream(fileName);
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Unable to load config file.");
		}				
		return properties;
	}
}
