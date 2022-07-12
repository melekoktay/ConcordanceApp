package controller;

import java.util.ArrayList;
import java.util.List;

import entity.Word;

public interface SortingStrategy {
	
	List<Word> sort(List<Word> list);

}
