package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import entity.Word;
import util.AppUtil;
import util.ConAppOptions;


public class FileService implements Runnable {

	ParsingInterface parser;
	
	private ConAppOptions options;
	
	private List<Word> concordanceList;

	public FileService(ConAppOptions newOptions) throws FileNotFoundException {
		
		setOptions(newOptions);
		
		File f = new File(options.getFilePath());
		if(!f.exists() /** && f.isDirectory() */) {
			throw new FileNotFoundException();
		}
		
	    //TODO check file size ! If it is TOO BIG to open, throw an exceptions 
		//f.getTotalSpace();
		
		
	}
	
	public void startAnalyzingFile(){
		Thread thread = new Thread(this);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		SortingStrategy sortStrategy;
		parser = new ConcreateEngParsing();
		
		
		List<Word> wordList = parser.parsingExecution(getOptions());

		if(getOptions().getOrderName().equals("alpabetical"))			
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
