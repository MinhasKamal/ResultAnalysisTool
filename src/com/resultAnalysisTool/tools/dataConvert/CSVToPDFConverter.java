/*Collector : Shahriar Mohammed Ishmam
 */
package com.resultAnalysisTool.tools.dataConvert;


import java.io.*;

import au.com.bytecode.opencsv.CSVReader;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.resultAnalysisTool.util.message.Message;



public class CSVToPDFConverter {  
	/**
	 * Get a file name as String, and create a new PDF file containing file's information
	 * @param fileName path of file as String
	 */
        public void doConvertToPDF(String fileName){//fileName=file path
        	
        	
        	
        	String fieldNamesOfFile=readFirstLine(fileName);//reading first line , i mean fields name of file
        	
        	int numberOfFields=countCommaOfCSVFile(fieldNamesOfFile)+1;//fieldNumber=numberOfComma(without counting comma between quotation)+1
        	
        	
            CSVReader reader = null;
			try {
				reader = new CSVReader(new FileReader(fileName));
			} catch (FileNotFoundException e) {
				new Message("File is not found!", 420);
			}
            /* Variables to loop through the CSV File */
			
            String [] nextLine; /* for every line in the file */   
            
            
            /* Step-2: Initialize PDF documents - logical objects */
            Document my_pdf_data = new Document();
            try {
				PdfWriter.getInstance(my_pdf_data, new FileOutputStream(fileName+"converted_PDF_File.pdf"));
			} catch (FileNotFoundException | DocumentException e) {
				new Message("Error!", 420);
			}
            my_pdf_data.open();  
            
            PdfPTable my_first_table = new PdfPTable(numberOfFields);
            
            PdfPCell table_cell;
            
            /* Step -3: Loop through CSV file and populate data to PDF table */
            
            try {
				while ((nextLine = reader.readNext()) != null) {  
		
					for(int i=0;i<numberOfFields;i++){
						table_cell=new PdfPCell(new Phrase(nextLine[i]));
				        my_first_table.addCell(table_cell);
					}
				   
				}
				
				
				reader.close();
				my_pdf_data.add(my_first_table);
				my_pdf_data.close(); 
				
				new Message("File is converted to PDF.", 1);
			} catch (Exception e) {
				new Message("Error!", 420);
			}
        }
        
        /**
         * get file path as String and returns the fields' name of file
         * @param fileName path of file
         * @return fields name of file
         */
        private String readFirstLine(String fileName){
    		String information = new String();	//#contains the full file 
    		
    		try{
    			File mainFile = new File(fileName);
    			
    			/*file reading*/
    			BufferedReader mainBR = new BufferedReader(new FileReader(mainFile));	//making buffered reader object
    			
    			
    			information= mainBR.readLine();
    	
    			
    			mainBR.close();	//closing the file
    			
    		}catch(IOException e){			//catching exception
    			new Message("File Not Found!", 420);
    		}catch(Exception e){
    			new Message("Error!", 420);
    		}
    			return information; 
    	
        }
        
        /**
         * Count number of comma excluding commas between two quotation
         * @param string A String
         * @return number of commas in the String excluding commas between two quotations
         */
        private int countCommaOfCSVFile(String string){
    		int count=0;
    		int numberOfComma=0;
    		for(int i=0;i<string.length();i++){
    			if(string.charAt(i)=='"'){
    				count++;
    				//System.out.println("  quote got ,count is "+count);
    			}
    			
    			else if(string.charAt(i)==',' && count%2==0){
    				numberOfComma++;
    				//System.out.println(" , got,comma number "+numberOfComma);
    			}
    		}
    		
    		//System.out.println("total number of comma  "+numberOfComma);
    		
    		
    		return numberOfComma;
    	}
        
        public static void main(String args[]){
        	CSVToPDFConverter operation=new CSVToPDFConverter();
        	operation.doConvertToPDF("D:/java/fileForTest/mergedFile.csv");
        }
}