package com.resultAnalysisTool.tools.dataAnalyse.importFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import com.resultAnalysisTool.util.fileReadWrite.FileIO;

public class GenerateDatabaseOpt {
	private String tabAttrInfoFilePath;
	private String commaSepDataFilePath;
	private String dataBaseFormatFilePath;
	private int[] fieldPositions;
	private boolean[] isAnswerField;
	
	public GenerateDatabaseOpt(String tabAttrInfoFilePath, String commaSepDataFilePath, String dataBaseFormatFilePath)
			throws Exception {
		this.tabAttrInfoFilePath = tabAttrInfoFilePath;
		this.commaSepDataFilePath = commaSepDataFilePath;
		this.dataBaseFormatFilePath = dataBaseFormatFilePath;
		
	
		fieldPositions = getFieldPositions();
		isAnswerField = getIsAnswerField();
		importDatabase();
	}
	
	
	private void importDatabase() throws Exception{
		BufferedWriter mainBW = new BufferedWriter(new FileWriter(dataBaseFormatFilePath));
		BufferedReader mainBR = new BufferedReader(new FileReader(commaSepDataFilePath));
		
		String MainOutputString = getAllFieldNames()+"\n";
		mainBW.write(MainOutputString);
		
		String MainInputString = mainBR.readLine();
		while(MainInputString!=null){
			MainOutputString = processCSD(MainInputString)+"\n";
			mainBW.write(MainOutputString);
			
			MainInputString = mainBR.readLine();
		}
		
		mainBR.close();
		mainBW.close();
	}
	
	private String getAllFieldNames() throws Exception{
		String[] stringFieldNames = FileIO.readSpecificFileLine(tabAttrInfoFilePath, 1).split(",");
		String fieldNameString = "";
		for(String stringFieldName: stringFieldNames){
			fieldNameString += stringFieldName+";";
		}
		fieldNameString += "&ImgPath";
		
		return fieldNameString;
	}
	
	private int[] getFieldPositions() throws Exception{
		String[] stringFieldPositions = FileIO.readSpecificFileLine(tabAttrInfoFilePath, 2).split(",");
		int[] fieldPositions = new int[stringFieldPositions.length];
		for(int i=0; i<stringFieldPositions.length; i++){
			fieldPositions[i] = Integer.parseInt(stringFieldPositions[i]);
		}
		
		return fieldPositions;
	}
	
	private boolean[] getIsAnswerField() throws Exception{
		String[] stringIsAnswerField = FileIO.readSpecificFileLine(tabAttrInfoFilePath, 3).split(",");
		boolean[] isAnswerField = new boolean[stringIsAnswerField.length];
		for(int i=0; i<stringIsAnswerField.length; i++){
			isAnswerField[i] = Boolean.parseBoolean(stringIsAnswerField[i]);
		}
		
		return isAnswerField;
	}
	
	private String processCSD(String MainInputString){
		String MainOutputString = "";
		String[] splitedValues = MainInputString.split(",");
		
		int fieldNo = 0;
		for(int i=0; i<splitedValues.length-1; i++){
			MainOutputString += splitedValues[i];
			
			if(isAnswerField[fieldNo]){
				MainOutputString += ",";
			}
			
			if(i==fieldPositions[fieldNo]-1){
				if(isAnswerField[fieldNo]){
					MainOutputString = MainOutputString.substring(0, MainOutputString.length()-1);
				}
				MainOutputString += ";";
				fieldNo++;
			}
		}
		MainOutputString += splitedValues[splitedValues.length-1];
		
		return MainOutputString;
	}
	
	public static void main(String[] args) {
		String tabAttrInfoFilePath = "C:\\Users\\admin\\Desktop\\OMR\\OMR_Sheet.csd_tableDesign.tai";
		String commaSepDataFilePath = "C:\\Users\\admin\\Desktop\\OMR\\OMR_Sheetimg.csd";
		String dataBaseFormatFilePath = "C:\\Users\\admin\\Desktop\\OMR\\OMR.dbf";
		
		try {
			new GenerateDatabaseOpt(tabAttrInfoFilePath, commaSepDataFilePath, dataBaseFormatFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
