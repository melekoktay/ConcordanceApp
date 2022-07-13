package controller;

import java.util.Hashtable;
import java.util.List;

import entity.Word;
import util.AppUtil;
import util.ConAppOptions;

public abstract class ParsingTemplate {
	
	protected abstract List<String> readFromFile(ConAppOptions options);
	
	protected abstract List<String> sanitizing(List<String> rawSentenceList);	
	
	protected abstract List<Word> revertAbbrvBack(List<Word> convertHashtableToList);
	
	protected abstract Hashtable<String, Word> wordTokenizer(List<String> list, ConAppOptions options);
	
	protected abstract List<String> sentenceTokenizer(List<String> pureSentenceList, ConAppOptions options);
	
	/**
	 * Template method (Template Patterns) that hides complexity of sequence of method calls and let
	 * concrete subclasses implement these methods. Within template method sequence calls of these method does not change but implementation does !
	 * For example, we could any other language, such as German, Turkish, support could be done within concrete subclasses.
	 * Than, abbreviation translation and its revertback mechanism could be handled by implementor.
	 * 
	 * @param options
	 * @return
	 */
	public List<Word> parsingTemplateMethod(ConAppOptions options) {
		List<String> rawSentenceList = readFromFile(options);
		if(rawSentenceList == null) {
			return null;
		}
		
		List<String> pureSentenceList = sanitizing(rawSentenceList);
		if(pureSentenceList == null) {
			return null;
		}
		
		List<String> list = sentenceTokenizer(pureSentenceList, options);
		
		Hashtable<String, Word> wordTable = wordTokenizer(list, options);
		
		List<Word> finalWordList = revertAbbrvBack(AppUtil.convertHashtableToList(wordTable));
		
		return finalWordList;
	}


}
