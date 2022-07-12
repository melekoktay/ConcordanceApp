package controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import entity.Word;

public class FrequencyOrderStrategy implements SortingStrategy {

	@Override
	public List<Word> sort(List<Word> list) {
        Collections.sort(list, new Comparator<Word>() {
            public int compare(Word w1, Word w2) {
            	if( w1.getCount() < w2.getCount() )
            		return 1;
            	else 
            		return -1;
            }
        });
        
		return list;

	}

}
