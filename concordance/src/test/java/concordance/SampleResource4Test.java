package concordance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import entity.Word;
import util.ConAppOptions;

public class SampleResource4Test {

	private static ConAppOptions options;
	
	@Before
	public void setUp() throws IOException, InterruptedException {

		StringBuilder pathBuilder = TestUtil.defaultSourcePath("sample.resource4.txt" );
		
		options = TestUtil.getDefaultOptions();
		options.setFilePath(pathBuilder.toString());
		System.err.println(pathBuilder.toString());
	}
	
	@Test
	public void testParsingFile4NumberOfElement() throws FileNotFoundException {
		Concordance conApp = new Concordance();
		conApp.startAnalyzeFile(SampleResource4Test.options);

		assertEquals("Number of Element on " + options.getFilePath(), 15, conApp.getConcordanceList().size());
	}
	

	@Test
	public void testMrKeyword() throws FileNotFoundException {
		Word mrWord = new Word("mr.", 2, 1);
		mrWord.addSentenceOccurenceNumber(2);
		
		Concordance conApp = new Concordance();
		conApp.startAnalyzeFile(SampleResource4Test.options);

		List<Word> resultList = conApp.getConcordanceList();
		
		boolean flag = false;
		for(Word word: resultList) {
			if(word.getWord().equals(mrWord.getWord() )) {
				assertEquals(" Word must same ", word.toString(), mrWord.toString());
				flag = true;
				break;
			}
		}
		
		if(!flag)
			fail("mr. keyword does not exist in resource4.txt files");
	}
	
}
