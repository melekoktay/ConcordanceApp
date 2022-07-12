package concordance;

import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.List;

import controller.FileService;
import entity.Word;
import util.ConAppOptions;

public class Concordance {
	
	private FileService fileService = null;
	
	public Concordance(){
		
	}
	
	public void startAnalyzeFile(ConAppOptions options) throws FileNotFoundException {
		fileService = new FileService(options);

		fileService.startAnalyzingFile();
	}
	
	public List<Word> getConcordanceList(){
		return fileService.getConcordanceList();
	}

}
