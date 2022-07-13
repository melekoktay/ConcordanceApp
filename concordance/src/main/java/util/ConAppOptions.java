package util;

public class ConAppOptions {
	
	private String filePath = null;
	
	private String lang = null;
	
	private String sentenceSeperator = null;
	
	private String punctuations = null;
	
	private String orderName = null;
	
	private String wordSeperator = null;

	public String getWordSeperator() {
		return wordSeperator;
	}

	public void setWordSeperator(String wordSeperator) {
		this.wordSeperator = wordSeperator;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getPunctuations() {
		return punctuations;
	}

	public void setPunctuations(String punctuations) {
		this.punctuations = punctuations;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getSentenceSeperator() {
		return sentenceSeperator;
	}

	public void setSentenceSeperator(String sentenceSeperator) {
		this.sentenceSeperator = sentenceSeperator;
	}

}
