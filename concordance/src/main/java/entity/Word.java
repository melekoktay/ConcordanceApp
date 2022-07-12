package entity;

import java.util.ArrayList;
import java.util.List;

public class Word {
	
	private int count;

	private String word;
	
	List<Long> occurrences ;
	
	public Word() {
		occurrences = new ArrayList<>();
	}
	
	public Word(String newWord, int newCount, int sentenceNumber) {
		occurrences = new ArrayList<>();
		
		setWord(newWord);
		setCount(newCount);
		addSentenceOccurenceNumber(sentenceNumber);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	public void addSentenceOccurenceNumber(long newNumber) {
		occurrences.add(newNumber);
	}
	
	@Override
	public String toString() {
		String fmt = "%-20s {%d:%s}";
		return String.format(fmt, word , count, occurrences.toString() );
	}

}
