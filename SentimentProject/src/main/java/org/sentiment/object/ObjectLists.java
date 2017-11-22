package org.sentiment.object;

import java.util.ArrayList;

public class ObjectLists {
	ArrayList<Sentences> LstSentences;
	ArrayList<PositiveDictionary> LstPositiveDictionary;
	ArrayList<NegativeDictionary> LstNegativeDictionary;
	
	public ObjectLists() {
		// TODO Auto-generated constructor stub
		LstSentences = new ArrayList<Sentences>();
		LstPositiveDictionary = new ArrayList<PositiveDictionary>();
		LstNegativeDictionary = new ArrayList<NegativeDictionary>();
	}
	public ArrayList<Sentences> getLstSentences() {
		return LstSentences;
	}
	public void setLstSentences(ArrayList<Sentences> lstSentences) {
		LstSentences = lstSentences;
	}
	public ArrayList<PositiveDictionary> getLstPositiveDictionary() {
		return LstPositiveDictionary;
	}
	public void setLstPositiveDictionary(
			ArrayList<PositiveDictionary> lstPositiveDictionary) {
		LstPositiveDictionary = lstPositiveDictionary;
	}
	public ArrayList<NegativeDictionary> getLstNegativeDictionary() {
		return LstNegativeDictionary;
	}
	public void setLstNegativeDictionary(
			ArrayList<NegativeDictionary> lstNegativeDictionary) {
		LstNegativeDictionary = lstNegativeDictionary;
	}
	
	public void addLstSentences(Sentences sentence) {
		LstSentences.add(sentence);
	}
	
	public void addLstPositiveDictionary(PositiveDictionary pdic) {
		LstPositiveDictionary.add(pdic);
	}
	
	public void addLstNegativeDictionary(NegativeDictionary ndic) {
		LstNegativeDictionary.add(ndic);
	}
}
