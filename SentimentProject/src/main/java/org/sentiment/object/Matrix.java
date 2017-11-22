package org.sentiment.object;

import java.util.ArrayList;

public class Matrix {
	ArrayList<MatrixDetail> md ;
	
	public Matrix() {
		// TODO Auto-generated constructor stub
		md = new ArrayList<MatrixDetail>();
	}

	public ArrayList<MatrixDetail> getMd() {
		return md;
	}

	public void setMd(ArrayList<MatrixDetail> md) {
		this.md = md;
	}
	
	public void addMod(MatrixDetail md){
		this.md.add(md);
	}
	
}
