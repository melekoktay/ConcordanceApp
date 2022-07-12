package util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import entity.Word;

public class AppUtil {
	
	public static void printHashTable(Hashtable<String, Word>  wordTable) {
		Set<String> wordSet = wordTable.keySet();
		Iterator<String> iter  = wordSet.iterator();
		while(iter.hasNext()){
			String keyString = iter.next();	
			Word word = wordTable.get(keyString);
			
			System.out.println(word);
		}	
	}
	
	public static void printArrayList(List<Word> list) {
		for(Word w: list) {
			System.out.println(w);
		}	
	}
	
	public static List<Word> convertHashtableToList(Hashtable<String,Word> wordTable){
		List<Word> resultList = null;
		
		if(wordTable == null)
			return resultList;
		else 
			resultList = new ArrayList<Word>();

		Set<String> wordSet = wordTable.keySet();
		Iterator<String> iter  = wordSet.iterator();
		while(iter.hasNext()){
			String keyString = iter.next();	
			Word word = wordTable.get(keyString);
			resultList.add(word);
		}
		return resultList;
	}
	
	   public static String removePunctuations(String source, String punctuations) {
//	        return source.replaceAll("[!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]", "");
	        return source.replaceAll(punctuations, "");
	    }

}
