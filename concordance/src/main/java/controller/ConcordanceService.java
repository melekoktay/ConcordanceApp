package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import entity.Word;
import util.AppUtil;
import util.ConAppOptions;


public class ConcordanceService implements Runnable {

	ParsingTemplate parserTemplate;
	
	private ConAppOptions options;
	
	private List<Word> concordanceList;

	public ConcordanceService(ConAppOptions newOptions) throws FileNotFoundException {
		
		setOptions(newOptions);
		
		File f = new File(options.getFilePath());
		if(!f.exists() ) {
			System.err.println("File " + f.getName() + " is not exist !");
			throw new FileNotFoundException();
		}
		
		if(f.isDirectory()) {
			System.err.println("Given path is Directory  !");
			throw new FileNotFoundException();
		}
		
		long sizeInBytes = f.length();
		//transform in MB
		long sizeInMb = sizeInBytes / (1024 * 1024);
		
		if(sizeInMb > 500) {
			System.err.println("Input file could not exceed 500MB !");
			System.exit(-2);
		}
		
	}
	
	public void startAnalyzingFile(){
		Thread thread = new Thread(this);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.err.println("Thread could not be join, program is terminated !");
			System.exit(-1);
		}
	}

	public void run() {
		SortingStrategy sortStrategy;
		parserTemplate = new ConcreateEngParsing();
		
		
		List<Word> wordList = parserTemplate.parsingTemplateMethod(getOptions());
		if(wordList == null) {
			System.err.println("Anomaly occurs, could not continue execution!");
			return;
		}

		if(getOptions().getOrderName().equals("alphabetical"))			
			sortStrategy = new AlphabeticalOrderStrategy();
		else
			sortStrategy = new FrequencyOrderStrategy();
		
		List<Word> list = sortStrategy.sort(wordList);
		
		setConcordanceList(list);
		
		AppUtil.printArrayList(list);
		
		
	}

	public List<Word> getConcordanceList() {
		return concordanceList;
	}

	private void setConcordanceList(List<Word> concordanceList) {
		this.concordanceList = concordanceList;
	}

	public ConAppOptions getOptions() {
		return options;
	}

	public void setOptions(ConAppOptions options) {
		this.options = options;
	}

}
