package util;

import java.io.BufferedReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConFileReader implements AbstractReader {

	public ConFileReader() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<String> read(BufferedReader br) {
		List<String> lineList = new ArrayList<>();
		try {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	//System.err.println(line);
		    	lineList.add(line.toLowerCase());
		    }
		} catch (IOException e) {
			e.printStackTrace();
			lineList = null;
		} finally {
		    try {
				br.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return lineList;
	}

}
