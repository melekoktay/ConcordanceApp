package concordance;

import java.io.FileNotFoundException;
import java.util.List;

import controller.ConcordanceService;
import entity.Word;
import util.ConAppOptions;

public class Concordance {
	
	private ConcordanceService fileService = null;
	
	public Concordance(){
		
	}
	
	public void startAnalyzeFile(ConAppOptions options) throws FileNotFoundException {
		fileService = new ConcordanceService(options);

		fileService.startAnalyzingFile();
	}
	
	public List<Word> getConcordanceList(){
		return fileService.getConcordanceList();
	}

}
