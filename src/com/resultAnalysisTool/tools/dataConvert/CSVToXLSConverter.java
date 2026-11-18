

package com.resultAnalysisTool.tools.dataConvert;

import java.io.*;
import java.util.ArrayList;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;

import com.resultAnalysisTool.util.message.Message;

public class CSVToXLSConverter {

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public void convertCsvToXls(String fileName) {
		
		//String fileName = "C:/Users/admin/Desktop/ResultIITDU.csv";//test
		DataInputStream myInput;
		try {
			
			myInput = new DataInputStream(new FileInputStream(fileName));
		
			ArrayList<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>();
			ArrayList<String> al = null;
			String thisLine;
			
			while ((thisLine = myInput.readLine()) != null) {
				al = new ArrayList<String>();
				String strar[] = thisLine.split(",");
				for (int j = 0; j < strar.length; j++) {
					al.add(strar[j]);
				}
				arrayList.add(al);
			}
			
			myInput.close();
		
			
			
			HSSFWorkbook hwb = new HSSFWorkbook();
			HSSFSheet sheet = hwb.createSheet("new sheet");
			
			for(int k=0; k<arrayList.size(); k++) {
				ArrayList ardata = (ArrayList) arrayList.get(k);
				HSSFRow row = sheet.createRow((short) 0 + k);
				
				for(int p=0; p<ardata.size(); p++) {
					HSSFCell cell = row.createCell((short) p);
					String data = ardata.get(p).toString();
					
					if(data.startsWith("=")) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						data = data.replaceAll("\"", "");
						data = data.replaceAll("=", "");
						cell.setCellValue(data);
					} else if(data.startsWith("\"")) {
						data = data.replaceAll("\"", "");
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(data);
					} else{
						data = data.replaceAll("\"", "");
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(data);
					}
					
				}
			}
			
			FileOutputStream fileOut = new FileOutputStream(fileName.substring(0, fileName.length()-4) + ".xls");
			hwb.write(fileOut);
			fileOut.close();
			
			new Message("File is converted to XLS.", 1);
		}catch (FileNotFoundException e) {
			new Message("File not found!", 420);
		} catch (Exception ex) {
			new Message("Error occured while converting!", 420);
		} 
	}
	
	/**///test
	public static void main(String[] args) {
		
		new CSVToXLSConverter().convertCsvToXls("C:/Users/admin/Desktop/ResultIITDU.csv");
	}
	/**/
}
