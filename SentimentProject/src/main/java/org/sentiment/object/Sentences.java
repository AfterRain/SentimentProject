package org.sentiment.object;

public class Sentences {
	String ID_1 = "";
	String ID_2 = "";
	String Score = "";
	String Text = "";
	double Rescale = 0;
	double Ridge = 0;
	double ReCalRidge = 0;
	public String getID_1() {
		return ID_1;
	}
	public void setID_1(String iD_1) {
		ID_1 = iD_1;
	}
	public String getID_2() {
		return ID_2;
	}
	public void setID_2(String iD_2) {
		ID_2 = iD_2;
	}
	public String getScore() {
		return Score;
	}
	public void setScore(String score) {
		Score = score;
	}
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	public double getRescale() {
		return Rescale;
	}
	public void setRescale(double rescale) {
		Rescale = rescale;
	}
	public double getRidge() {
		return Ridge;
	}
	public void setRidge(double ridge) {
		Ridge = ridge;
	}
	public double getReCalRidge() {
		return ReCalRidge;
	}
	public void setReCalRidge(double reCalRidge) {
		ReCalRidge = reCalRidge;
	}
}
