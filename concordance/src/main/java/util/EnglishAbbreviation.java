package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
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

	public Properties getEnglishAbbrvProperties(ConAppOptions options) throws FileNotFoundException, IOException {
		String propertiesFilePath = null;
		Properties prop = new Properties();
		if (options.getAbbrvFilePath() != null) {
			propertiesFilePath = options.getAbbrvFilePath() ;
		} else {
			String separator = System.getProperty("file.separator");
			String path = new File(".").getCanonicalPath();

			propertiesFilePath = path + separator + "src" + separator + "main" + separator + "resources" + separator
					+ "english.abbrv.properties";

		}
		prop.load(new FileInputStream(propertiesFilePath));

		return prop;
	}
	
	public Hashtable<String, String> getAbbrevTable(ConAppOptions options) throws FileNotFoundException, IOException{
		Properties prop = getEnglishAbbrvProperties(options);
		Hashtable<String, String>  abbrvTable = new Hashtable<>();
		
		prop.forEach((key, value) -> abbrvTable.put((String)key, (String)value));
		
		return abbrvTable;
	}
	
	
	public Hashtable<String, String> getConvertedAbbrevTable(ConAppOptions options) throws FileNotFoundException, IOException{
		Properties prop = getEnglishAbbrvProperties(options);
		Hashtable<String, String>  abbrvTable = new Hashtable<>();
		
		prop.forEach((key, value) -> abbrvTable.put( (String)value, (String)key) );
		
		return abbrvTable;
	}
}
