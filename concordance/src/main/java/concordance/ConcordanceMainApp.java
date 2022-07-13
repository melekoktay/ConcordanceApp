package concordance;

import java.io.FileNotFoundException;

import util.ConAppOptions;


public class ConcordanceMainApp {

	public static void main(String[] args) {

		if(args.length <= 1) {	
			System.err.println("You must provide file for analyzing !");
			System.exit(-1);
		}
		
		System.out.println("Start processing concordance analysis. Input file name is " + args[0]);
		System.out.println("Abbreviation file is " + args[1]);
		
		
		ConAppOptions options = new ConAppOptions();
		options.setFilePath( args[0] );
		options.setAbbrvFilePath(args[1]);

		options.setLang("en");
		options.setSentenceSeperator(".!\n");
		options.setPunctuations("[!\"#$%&'()*+,-./:;<=>?\\[\\]^_`{|}~]");
		options.setOrderName("alphabetical");
//		options.setOrderName("mostfrequent");
		options.setWordSeperator(" ");
	
		
		try {
			(new Concordance()).startAnalyzeFile(options);
		} catch (FileNotFoundException e) {
			System.err.println(args[0] + " file not found for concordance analysis !");
			e.printStackTrace();
			System.exit(-1);
		}

	}

}
