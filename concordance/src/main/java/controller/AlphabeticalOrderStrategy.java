package controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import entity.Word;


public class AlphabeticalOrderStrategy implements SortingStrategy {

	@Override
	public List<Word> sort(List<Word> list) {
		
        Collections.sort(list, new Comparator<Word>() {
            public int compare(Word w1, Word w2) {
            	return w1.getWord().compareTo(w2.getWord());
            }
        });
        
		return list;
	}

}
