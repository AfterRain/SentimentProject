package org.sentiment.object;

public class Results {
	double tfidf = 0;
	double positivePredict = 0;
	double negativePredict = 0;
	double result = 0;
	
	public double getTfidf() {
		return tfidf;
	}
	public void setTfidf(double tfidf) {
		this.tfidf = tfidf;
	}
	public double getPositivePredict() {
		return positivePredict;
	}
	public void setPositivePredict(double positivePredict) {
		this.positivePredict = positivePredict;
	}
	public double getNegativePredict() {
		return negativePredict;
	}
	public void setNegativePredict(double negativePredict) {
		this.negativePredict = negativePredict;
	}
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	
	
}
