package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;


public class EnglishAbbreviation {
	public static EnglishAbbreviation instance = null;
	
	private EnglishAbbreviation() {
		
	}
	
	public static EnglishAbbreviation instance() {
		if(instance == null) {
			instance = new EnglishAbbreviation();
		}
		return instance;
	}

	public Properties getEnglishAbbrvProperties() throws FileNotFoundException, IOException {

		String separator = System.getProperty("file.separator");
		String path = new File(".").getCanonicalPath();

		
		Properties prop = new Properties();
		String propertiesFilePath = path + separator + "src" + separator + "main" + separator + "resources" + separator + "english.abbrv.properties";
		//System.err.println(propertiesFilePath);
		prop.load( new FileInputStream(propertiesFilePath));
		
		//prop.keySet().forEach(x -> System.out.println(x));
		//prop.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " + value));
		
		//System.err.println("Proj File Path " + path);

		
		return prop;
	}
	
	public Hashtable<String, String> getAbbrevTable() throws FileNotFoundException, IOException{
		Properties prop = getEnglishAbbrvProperties();
		Hashtable<String, String>  abbrvTable = new Hashtable<>();
		
		prop.forEach((key, value) -> abbrvTable.put((String)key, (String)value));
		
		return abbrvTable;
	}
	
	
	public Hashtable<String, String> getConvertedAbbrevTable() throws FileNotFoundException, IOException{
		Properties prop = getEnglishAbbrvProperties();
		Hashtable<String, String>  abbrvTable = new Hashtable<>();
		
		prop.forEach((key, value) -> abbrvTable.put( (String)value, (String)key) );
		
		return abbrvTable;
	}
}
