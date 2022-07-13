package concordance;

import java.io.File;
import java.io.IOException;

import util.ConAppOptions;

public class TestUtil {
	
	public static ConAppOptions getDefaultOptions() {
		ConAppOptions options = new ConAppOptions();
		options.setFilePath( "" );
		options.setLang("en");
		options.setSentenceSeperator(".!");
		options.setPunctuations("[!\"#$%&'()*+,-./:;<=>?\\[\\]^_`{|}~]");
		options.setOrderName("alphabetical");
		options.setWordSeperator(" ");
		
		return options;
	} 
	
	public static StringBuilder defaultSourcePath(String fileName) throws IOException {
		StringBuilder sourcePathBuilder = new StringBuilder();
		
		String path = new File(".").getCanonicalPath();
		String separator = System.getProperty("file.separator");
		sourcePathBuilder.append(path);
		sourcePathBuilder.append(separator);
		sourcePathBuilder.append("src");
		sourcePathBuilder.append(separator);
		sourcePathBuilder.append("test");
		sourcePathBuilder.append(separator);
		sourcePathBuilder.append("resources");
		sourcePathBuilder.append(separator);
		sourcePathBuilder.append(fileName);
		
		return sourcePathBuilder;
	}

}
