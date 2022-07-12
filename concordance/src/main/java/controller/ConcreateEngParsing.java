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

public class ConcreateEngParsing implements ParsingInterface {

	private List<String> readFromFile(ConAppOptions options) {
		
		AbstractReader fileReader = new ConFileReader();
		List<String> list = null ;
		try {
			list = fileReader.read(new BufferedReader(new FileReader(options.getFilePath())));
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
			
			//TODO handle this position
			list = null;
		}
		
		return list;
	}
	
	

	/**
	 * responsible sanitize input sentence and remove abbreviations	
	 * @param list
	 * @return
	 */
	private List<String> sanitizing(List<String> list) {
		
		//List<String> sanitizeList = new ArrayList<>();
		Hashtable<String, String> abbrTable = null;
		EnglishAbbreviation engAbbrev = EnglishAbbreviation.instance();
		try {
			abbrTable = engAbbrev.getAbbrevTable();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		//System.err.println(abbrTable);
		
		//for(String sourceLine : list) {
			
			
		for(int i=0; i < list.size() ;i++)	{
			String sourceLine = list.get(i);
			
			Enumeration<String> abbrKeyStr = abbrTable.keys();
			
	        while (abbrKeyStr.hasMoreElements()) {
	        	 
	            // Getting the key of a particular entry
	            String abbrvKeyString = abbrKeyStr.nextElement();
	            
	            if( sourceLine.indexOf(abbrvKeyString) != -1) {
	            	
	            	//System.out.println(sourceLine);
	            	//System.out.println(abbrvKeyString + " " + abbrTable.get( abbrvKeyString ));
	            	
	            	sourceLine = sourceLine.replace( abbrvKeyString , abbrTable.get( abbrvKeyString ));
	            	
	            	//System.out.println(sourceLine);
	      
	            	list.set(i, sourceLine);
	            }
	        }	
		}
		
		return list;
	}
	
	private List<String> sentenceTokenizer(List<String> list, ConAppOptions options){
		List<String> sentenceList = new ArrayList<>();
		
		for(int i=0;i<list.size();i++) {
			String strLine = list.get(i);
			
			//System.out.println(strLine);
			
			StringTokenizer sentencetokenizer = new StringTokenizer(strLine, options.getSentenceSeperator());
			
			while(sentencetokenizer.hasMoreTokens()) {
				String sentence = sentencetokenizer.nextToken();
				sentenceList.add(sentence);
				System.out.println(sentence);
			}
		}
		return sentenceList;
	}
	
	private Hashtable<String, Word> process(List<String> list, ConAppOptions options) {
		Hashtable<String, Word> wordTable = new Hashtable<String, Word>();
		for(int sentenceIndex = 0; sentenceIndex < list.size(); sentenceIndex++) {

			String sentence =  list.get(sentenceIndex);
			
			//System.err.println(sentence);
			
			StringTokenizer words = new StringTokenizer(sentence, " ");

			//System.err.println("Number of Words " + words.countTokens());
			
			while(words.hasMoreTokens()) {
				//String word = words.nextToken().trim();				
				String word = AppUtil.removePunctuations(words.nextToken(), options.getPunctuations());				
				//System.err.println(word);
				
				if(wordTable.containsKey(word)){
					Word wordEntity = wordTable.get(word);
					wordEntity.setCount( wordEntity.getCount() + 1 );
					wordEntity.addSentenceOccurenceNumber( sentenceIndex+1 );
					wordTable.put(word, wordEntity);
					
				}else{
					Word newWordBean = new Word(word, 1, sentenceIndex +1);
					wordTable.put(word, newWordBean);
				}
				
			}
			
		}
		
		return wordTable;

	}

	private List<Word> revertAbbrvBack(List<Word> list) {
		Hashtable<String, String> revertedAbbrTable = null;
		EnglishAbbreviation engAbbrev = EnglishAbbreviation.instance();
		try {
			revertedAbbrTable = engAbbrev.getConvertedAbbrevTable();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		for(int i=0;i<list.size();i++) {
			Word word = list.get(i);
			
			Enumeration<String> abbrKeyStr = revertedAbbrTable.keys();
			
	        while (abbrKeyStr.hasMoreElements()) {
	        	 
	            // Getting the key of a particular entry
	            String abbrvKeyString = abbrKeyStr.nextElement();
	            
	            if(abbrvKeyString.equals(word.getWord())) {
//	            	System.err.println(abbrvKeyString + " " +  revertedAbbrTable.get(abbrvKeyString)  +" " + word);
	            	
	            	word.setWord(revertedAbbrTable.get(abbrvKeyString));
	            	
	            	list.set(i, word);
	            
	            }
	        }
			
		}
		
		return list;
	}

	@Override
	public List<Word> parsingExecution(ConAppOptions options) {
		List<String> rawSentenceList = readFromFile(options);
		List<String> pureSentenceList = sanitizing(rawSentenceList);
		
		List<String> list = sentenceTokenizer(pureSentenceList, options);
		
		Hashtable<String, Word> wordTable = process(list, options);
		
		List<Word> finalWordList = revertAbbrvBack(AppUtil.convertHashtableToList(wordTable));
		
		return finalWordList;
	}




}
