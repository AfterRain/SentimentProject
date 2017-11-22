package org.sentiment.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

import org.sentiment.object.Matrix;
import org.sentiment.object.MatrixDetail;
import org.sentiment.object.NegativeDictionary;
import org.sentiment.object.ObjectLists;
import org.sentiment.object.PositiveDictionary;
import org.sentiment.object.Results;
import org.sentiment.object.Sentences;

public class TestMain {
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		String ExcelFilePath = args[0];
		String MatrixFilePath = args[1];
		FileReadMaker fileReadMaker = new GetReadExcelMaker();
		GetMatrixMaker getMatrixMaker = new GetMatrixMaker();
		ObjectLists objList = fileReadMaker.getReadFile(ExcelFilePath);
		Hashtable<Integer, Matrix> hshMt = getMatrixMaker.getReadFile(MatrixFilePath);
		
		int i = 1;
		for(Sentences sentence : objList.getLstSentences()){
			String Text = getReplacedValue(sentence.getText());
			String UsingSentence = "";
			Matrix matrix = hshMt.get(i);
			Hashtable<String, Results> htResult = new Hashtable<String, Results>();
			
			String[] arrSentences = Text.split("\\.");
			
			//System.out.println(Text);
			if(arrSentences.length > 1){
				UsingSentence = arrSentences[arrSentences.length-1];
			}// else {
				//UsingSentence = arrSentences[arrSentences.length-1];
			//}
			// 3번 가중치
			/*			
  			if(arrSentences.length == 0){
				UsingSentence = Text;
			}
			else if(arrSentences.length <= 2){
				UsingSentence = arrSentences[0];
			} else {
				UsingSentence = arrSentences[arrSentences.length-1];
			}*/
			
			double denominator = 0;
			double denominatorWeight = 0;
			double numerator = 0;
			double numeratorWeight = 0;
			if(matrix != null) {
				for(MatrixDetail mtd : matrix.getMd()) {
					String Word = mtd.getWord();
					//if(Text.contains(mtd.getWord())){
					if(htResult.containsKey(Word)){
						Results rlts = htResult.get(Word);
						rlts.setTfidf(mtd.getTdm());
					} else {
						Results rlts = new Results();
						rlts.setTfidf(mtd.getTdm());
						htResult.put(Word, rlts);
					}
					//}
				}
				
				Set<String> keys = htResult.keySet();
				
				if(matrix != null) {
					for(String rls : keys){
						Results rlts = htResult.get(rls);
						
						for(PositiveDictionary pdic : objList.getLstPositiveDictionary()){
							//System.out.println("P , " + pdic.getKeyWord() + " : " +pdic.getScore());
							String Word = pdic.getKeyWord();
							if(rls.equals(Word)){
								rlts.setPositivePredict(pdic.getScore());
								break;
							}
						}
						
						for(NegativeDictionary ndic : objList.getLstNegativeDictionary()){
							//System.out.println("P , " + pdic.getKeyWord() + " : " +pdic.getScore());
							String Word = ndic.getKeyWord();
							if(rls.equals(Word)){
								rlts.setNegativePredict(ndic.getScore());
								break;
							}
						}
						
						double polarity = 0;
						double tfidf = rlts.getTfidf();
						double withWeight = 1.5;
						double withoutWeight = 1;
						//double resultWeight = 0;
						
						polarity = (rlts.getPositivePredict() - rlts.getNegativePredict()) / (rlts.getPositivePredict() + rlts.getNegativePredict());
						
						
						
						rlts.setResult(polarity);
						//System.out.println("Word : " + rls + ", tfidf : " + rlts.getTfidf() + ", positivePredict : " + rlts.getPositivePredict()+ ", negativePredict : " +rlts.getNegativePredict() + ", Result : " + rlts.getResult());
						//denominator += (rlts.getTfidf()*result);
						
						if(UsingSentence.contains(rls)) {
							numeratorWeight += (tfidf * polarity * withWeight);
							numerator += (tfidf * polarity * withoutWeight);
							denominator += (tfidf * withoutWeight);
							denominatorWeight += (tfidf * withWeight);
						}
						else {
							numeratorWeight += (tfidf * polarity * withoutWeight);
							numerator += (tfidf * polarity * withoutWeight);
							denominator += (tfidf * withoutWeight);
							denominatorWeight += (tfidf * withoutWeight);
						}
						
					}
				}
				
			} else {
				
			}
			
			double ridge = 0;
			double ridgeWeight = 0;
			if(denominator == 0 && numerator == 0){
				ridge = 0;
				ridgeWeight = 0;
			} else if(denominator == -1 && numerator == -1) {
				ridge = -1;
				ridgeWeight = -1;
			} else if(denominator == 1 && numerator == 1) {
				ridge = 1;
				ridgeWeight = 1;
			}
			else {
				ridge = numerator/denominator;
				ridgeWeight = numeratorWeight/denominatorWeight;
			}
			
			//System.out.println(Text);
			//System.out.println(numerator);
			//System.out.println(denominator);
			//System.out.println(denominatorWeight);
			//System.out.println(numeratorWeight);
			
			System.out.println(ridge+"\t"+ridgeWeight);
			
			i++;
			//System.out.println("-------------------------------------------------------");
			//if(i==20)
			//	break;
		}
	}
	
	public static String getReplacedValue(String str){ 
		return str.replaceAll("\\.+", ".");
	}

}
