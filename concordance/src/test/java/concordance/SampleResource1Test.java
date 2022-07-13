package concordance;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.Word;
import util.ConAppOptions;

public class SampleResource1Test {

	private static ConAppOptions options;// = new ConAppOptions();

	@Before
	public void setUp() throws IOException, InterruptedException {

		StringBuilder pathBuilder = TestUtil.defaultSourcePath("sample.resource1.txt" );
//		
//		String path = new File(".").getCanonicalPath();
//		
//		String separator = System.getProperty("file.separator");
//		String sampleFile1Path = path + separator + "src" + separator + "test" + separator + "resources" + separator
//				+ "sample.resource1.txt";

		options = TestUtil.getDefaultOptions();
		options.setFilePath(pathBuilder.toString());
	}

//	@After
//	public void tearDown() {
//		System.out.println("Test.tearDown");
//	}

	@Test(expected = java.io.FileNotFoundException.class)
	public void testFileNotExist() throws Exception {
		Concordance conApp = new Concordance();
		ConAppOptions options = TestUtil.getDefaultOptions();
		options.setFilePath("blabla.txt");

		conApp.startAnalyzeFile(options);
	}

	@Test
	public void testParsingFile1NumberOfElement() throws FileNotFoundException {

		Concordance conApp = new Concordance();
		conApp.startAnalyzeFile(SampleResource1Test.options);

		assertEquals("Number of Element on " + options.getFilePath(), 8, conApp.getConcordanceList().size());
	}

	@Test
	public void testParsingFile1ArrayList() throws FileNotFoundException {
		List<Word> preparedList = new ArrayList<>();
		preparedList.add(new Word("an", 1, 1));
		preparedList.add(new Word("arbitrary", 1, 1));
		preparedList.add(new Word("document", 1, 1));
		preparedList.add(new Word("english", 1, 1));
		preparedList.add(new Word("given", 1, 1));
		preparedList.add(new Word("in", 1, 1));
		preparedList.add(new Word("text", 1, 1));
		preparedList.add(new Word("written", 1, 1));

		Concordance conApp = new Concordance();
		conApp.startAnalyzeFile(SampleResource1Test.options);

		List<Word> resultList = conApp.getConcordanceList();

		//System.out.println(preparedList.toArray().toString());
		//System.out.println(resultList.toArray().toString());

		assertEquals("Elementh size are equal ", resultList.size(), preparedList.size());

		for (int i = 0; i < resultList.size(); i++) {
			Word w1 = resultList.get(i);
			Word w2 = preparedList.get(i);

			assertEquals(i + " elementh ", w1.toString(), w2.toString());

		}

	}

}
