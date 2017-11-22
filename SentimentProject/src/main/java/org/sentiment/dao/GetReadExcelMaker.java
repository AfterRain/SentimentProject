package org.sentiment.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sentiment.object.NegativeDictionary;
import org.sentiment.object.ObjectLists;
import org.sentiment.object.PositiveDictionary;
import org.sentiment.object.Sentences;


public class GetReadExcelMaker implements FileReadMaker{
	
	public ObjectLists getReadFile(String ExcelFilePath) throws IOException {
		
		//String filePath = TrimFileReadMaker.class.getResource("/configuration/TrimSequences.txt").getFile();
		//InputStream in = ClassLoader.getSystemResourceAsStream("csv/ASSYM.xlsx");
		
		FileInputStream inputStream = null;
		ObjectLists objLst = new ObjectLists();
		try {
			
			inputStream = new FileInputStream(new File(ExcelFilePath));
				
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			Sheet SecondSheet = workbook.getSheetAt(1);
			Sheet ThirdSheet = workbook.getSheetAt(2);
			
			Iterator<Row> iterator = firstSheet.iterator();
			DataFormatter formatter = new DataFormatter();
			 
			int lineNumber=0;
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				
				int i = 1;

				String indexName = "";
				Sentences sentence = new Sentences();
				while ( cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
			    	
			    	if(lineNumber==0) {
			    		continue; // Skip first header.
			    	}
			    	
			    	if(i==1)
			    		sentence.setID_1(formatter.formatCellValue(cell).trim());
			    	if(i==2)
			    		sentence.setID_2(formatter.formatCellValue(cell).trim());
			    	if(i==3)
			    		sentence.setScore(formatter.formatCellValue(cell));
			    	if(i==4)
			    		sentence.setText(formatter.formatCellValue(cell).trim());
			    	if(i==5)
			    		sentence.setRescale(Double.parseDouble(formatter.formatCellValue(cell)));
			    	if(i==6)
			    		sentence.setRidge(Double.parseDouble(formatter.formatCellValue(cell)));

					i++;
				}
				if(!sentence.getID_1().equals(""))
					objLst.addLstSentences(sentence);
				
				lineNumber++;
			}
			
			Iterator<Row> iterator2 = SecondSheet.iterator();
			 
			lineNumber=0;
			while (iterator2.hasNext()) {
				Row nextRow = iterator2.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				
				int i = 1;

				String indexName = "";
				PositiveDictionary pdic = new PositiveDictionary();
				while ( cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
			    	
			    	if(lineNumber==0) {
			    		continue; // Skip first header.
			    	}
			    	
			    	if(i==1)
			    		pdic.setKeyWord(formatter.formatCellValue(cell).trim());
			    	if(i==2)
			    		pdic.setScore(Double.parseDouble(formatter.formatCellValue(cell)));
					i++;
				}
				if(!pdic.getKeyWord().equals(""))
					objLst.addLstPositiveDictionary(pdic);
				
				lineNumber++;
			}
			
			Iterator<Row> iterator3 = ThirdSheet.iterator();
			 
			lineNumber=0;
			while (iterator3.hasNext()) {
				Row nextRow = iterator3.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				
				int i = 1;

				String indexName = "";
				NegativeDictionary ndic = new NegativeDictionary();
				while ( cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
			    	
			    	if(lineNumber==0) {
			    		continue; // Skip first header.
			    	}
			    	
			    	if(i==1){
			    		ndic.setKeyWord(formatter.formatCellValue(cell).trim());
			    	}
			    	if(i==2){
			    		//System.out.println(Float.parseFloat(formatter.formatCellValue(cell)));
			    		ndic.setScore(Double.parseDouble(formatter.formatCellValue(cell)));
			    	}
					i++;
				}
				if(!ndic.getKeyWord().equals(""))
					objLst.addLstNegativeDictionary(ndic);

				lineNumber++;
			}
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
	    		if (inputStream != null) inputStream.close();
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	    	}
	    }
		
	    return objLst;
	}
}
