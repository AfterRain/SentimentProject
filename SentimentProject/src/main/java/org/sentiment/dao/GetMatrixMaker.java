package org.sentiment.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sentiment.object.Matrix;
import org.sentiment.object.MatrixDetail;
import org.sentiment.object.NegativeDictionary;
import org.sentiment.object.ObjectLists;
import org.sentiment.object.PositiveDictionary;
import org.sentiment.object.Sentences;

public class GetMatrixMaker {
	public Hashtable<Integer, Matrix> getReadFile(String CSVFilePath) throws IOException {
		
		//String filePath = TrimFileReadMaker.class.getResource("/configuration/TrimSequences.txt").getFile();
		//InputStream in = ClassLoader.getSystemResourceAsStream("csv/ASSYM.xlsx");
		BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        Hashtable<Integer, Matrix> hshMatrix = new Hashtable<Integer, Matrix>();
		try {
			
			//br = new BufferedReader(new FileReader(CSVFilePath));
			br = new BufferedReader(new InputStreamReader(new FileInputStream(CSVFilePath),"euc-kr"));
			
			
			int lineNumber=0;
            while ((line = br.readLine()) != null) {
                // use comma as separator
            	if(lineNumber == 0){
            		lineNumber++;
            		continue;
            	}
            	
                String[] splitRaw = line.split(cvsSplitBy);
                int i = 0;
                String word = "";
                
                for(String str : splitRaw){
                	
			    	if(i==0){
			    		word = str.trim().replace("\"", "");
			    		i++;
			    		continue;
			    	}
			    	
			    	double value = Double.parseDouble(str);
			    	
			    	if(value != 0) {
			    		MatrixDetail matrixdetail = new MatrixDetail();
			    		matrixdetail.setWord(word);
		    			matrixdetail.setTdm(value);

			    		if(hshMatrix.containsKey(i)){
			    			Matrix mt = hshMatrix.get(i);
			    			mt.addMod(matrixdetail);
			    		} else {
			    			Matrix mt = new Matrix();
			    			mt.addMod(matrixdetail);
			    			
			    			hshMatrix.put(i, mt);
			    		}
			    	}
                	
                	i++;
                }
                
                //System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
                lineNumber++;
            }
			/*
			inputStream = new FileInputStream(new File(ExcelFilePath));
				
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			
			Iterator<Row> iterator = firstSheet.iterator();
			DataFormatter formatter = new DataFormatter();
			Hashtable<Integer, Matrix> hshMatrix = new Hashtable<Integer, Matrix>();
			
			int lineNumber=0;
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				
				int i = 1;

				String word = "";
				Sentences sentence = new Sentences();
				while ( cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
			    	
			    	if(lineNumber==0) {
			    		continue; // Skip first header.
			    	}
			    	
			    	if(i==1){
			    		word = formatter.formatCellValue(cell);
			    		i++;
			    		continue;
			    	}
			    	
			    	double value = Double.parseDouble(formatter.formatCellValue(cell));
			    	if(value != 0) {
			    		MatrixDetail matrixdetail = new MatrixDetail();
			    		matrixdetail.setWord(word);
		    			matrixdetail.setTdm(value);
		    			
		    			System.out.println("Matrix , " + word + " : " + value);
		    			
			    		if(hshMatrix.containsKey(i)){
			    			Matrix mt = hshMatrix.get(i);
			    			mt.addMod(matrixdetail);
			    		} else {
			    			Matrix mt = new Matrix();
			    			mt.addMod(matrixdetail);
			    			
			    			hshMatrix.put(i, mt);
			    		}
			    	}
			    	
					i++;
				}
				
				lineNumber++;
			}*/
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
	    		if (br != null) br.close();
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	    	}
	    }
		
	    return hshMatrix;
	}
}
