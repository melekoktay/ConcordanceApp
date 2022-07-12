package controller;

import java.util.List;

import entity.Word;
import util.ConAppOptions;

public interface ParsingInterface {
	
	List<Word> parsingExecution(ConAppOptions options);


}
