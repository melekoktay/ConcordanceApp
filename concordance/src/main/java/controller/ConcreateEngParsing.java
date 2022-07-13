package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

import entity.Word;
import util.AbstractReader;
import util.AppUtil;
import util.ConAppOptions;
import util.ConFileReader;
import util.EnglishAbbreviation;

public class ConcreateEngParsing extends ParsingTemplate {

	/**
	 * Method read files from file system and puts lines inside in list data structures.
	 * 
	 * @param ConAppOptions contains file path that will be open
	 * @return List<String> list of lines that is read from file
	 */
	protected List<String> readFromFile(ConAppOptions options) {
		List<String> list = null ;
		AbstractReader fileReader = new ConFileReader();

		try {
			list = fileReader.read(new BufferedReader(new FileReader(options.getFilePath())));
		} catch (FileNotFoundException e) {
			System.err.println("Could not read File " + options.getFilePath() + " Message " + e.getMessage());
			list = null;
		}
		
		return list;
	}
	
	

	/**
	 * Responsible sanitize input sentence and replace abbreviations to special forms.
	 * @param list
	 * @return
	 */
	protected List<String> sanitizing(List<String> list) {
		Hashtable<String, String> abbrTable = null;
		EnglishAbbreviation engAbbrev = EnglishAbbreviation.instance();
		try {
			abbrTable = engAbbrev.getAbbrevTable();
		} catch (IOException e) {
			System.err.println("Could not get abbrv. table " + e.getMessage());
			return null;
		}
			
		for(int i=0; i < list.size() ;i++)	{
			String sourceLine = list.get(i);
			
			Enumeration<String> abbrKeyStr = abbrTable.keys();
			
	        while (abbrKeyStr.hasMoreElements()) {
	        	 
	            // Getting the key of a particular entry
	            String abbrvKeyString = abbrKeyStr.nextElement();
	            
	            if( sourceLine.indexOf(abbrvKeyString) != -1) {
	            	sourceLine = sourceLine.replace( abbrvKeyString , abbrTable.get( abbrvKeyString ));
	            	list.set(i, sourceLine);
	            }
	        }	
		}
		return list;
	}
	
	/**
	 * Method get list of lines as a parameter and separate these lines to sentences.
	 * 
	 * @param List<String> list of lines that read from file
	 * @param ConAppOptions application need options of sentence separators
	 * @return List of sentences
	 */
	protected List<String> sentenceTokenizer(List<String> list, ConAppOptions options){
		List<String> sentenceList = new ArrayList<>();
		
		for(int i=0;i<list.size();i++) {
			String strLine = list.get(i);
			
			StringTokenizer sentencetokenizer = new StringTokenizer(strLine, options.getSentenceSeperator());
			
			while(sentencetokenizer.hasMoreTokens()) {
				String sentence = sentencetokenizer.nextToken();
				sentenceList.add(sentence);
			}
		}
		return sentenceList;
	}
	
	/**
	 * Method gets list of sentences and tokenize these sentences to words.
	 * And then calculates concordance (frequency of word & occurences list within sentences)
	 * 
	 * @param List<String> list of sentences
	 * @param ConAppOptions application need options of word separators
	 */
	protected Hashtable<String, Word> wordTokenizer(List<String> list, ConAppOptions options) {
		Hashtable<String, Word> wordTable = new Hashtable<String, Word>();
		for(int sentenceIndex = 0; sentenceIndex < list.size(); sentenceIndex++) {

			String sentence =  list.get(sentenceIndex);
			
			StringTokenizer words = new StringTokenizer(sentence, options.getWordSeperator());
	
			while(words.hasMoreTokens()) {			
				String word = AppUtil.removePunctuations(words.nextToken(), options.getPunctuations());				
				
				if(wordTable.containsKey(word)){
					Word wordEntity = wordTable.get(word);
					wordEntity.setCount( wordEntity.getCount() + 1 );
					wordEntity.addSentenceOccurenceNumber( sentenceIndex+1 );
					wordTable.put(word, wordEntity);
					
				}else{
					Word newWordBean = new Word(word, 1, sentenceIndex+1);
					wordTable.put(word, newWordBean);
				}
				
			}
			
		}
		
		return wordTable;

	}

	/**
	 * After counting words process has been finished, special form abbreviation words must revert back to original form
	 * @param list of words objects.
	 * @return final result is list of words
	 */
	protected List<Word> revertAbbrvBack(List<Word> list) {
		Hashtable<String, String> revertedAbbrTable = null;
		EnglishAbbreviation engAbbrev = EnglishAbbreviation.instance();
		try {
			revertedAbbrTable = engAbbrev.getConvertedAbbrevTable();
		} catch (IOException e) {
			System.err.println("Could not retrieve Abbrv Table " + e.getMessage());
			return null;
		}
		
		for(int i=0;i<list.size();i++) {
			Word word = list.get(i);
			
			Enumeration<String> abbrKeyStr = revertedAbbrTable.keys();
			
	        while (abbrKeyStr.hasMoreElements()) {
	        	 
	            // Getting the key of a particular entry
	            String abbrvKeyString = abbrKeyStr.nextElement();
	            
	            if(abbrvKeyString.equals(word.getWord())) {
	            	word.setWord(revertedAbbrTable.get(abbrvKeyString));
	            	list.set(i, word);
	            }
	        }
			
		}
		
		return list;
	}

}
