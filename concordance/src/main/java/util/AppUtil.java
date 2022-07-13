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
	
	public static String alpabeticalSequence(int i) {
	    return i < 0 ? "" : alpabeticalSequence((i / 26) - 1) + (char)(65 + i % 26);
	}
	
	public static void printArrayList(List<Word> list) {
		System.out.println();
		
		String fmt = "%-5s %s\n";

		
		for(int i=0; i<list.size();i++) {
			Word w = list.get(i);
			System.out.printf(fmt, alpabeticalSequence(i).toLowerCase(),w.toString());
		}
		
		System.out.println();
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
