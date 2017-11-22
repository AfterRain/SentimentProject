package org.sentiment.object;

public class NegativeDictionary {
	String KeyWord = "";
	double score = 0;
	
	public String getKeyWord() {
		return KeyWord;
	}
	public void setKeyWord(String keyWord) {
		KeyWord = keyWord;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
}
