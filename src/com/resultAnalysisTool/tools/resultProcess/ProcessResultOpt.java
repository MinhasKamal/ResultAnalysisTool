/**
 * Developer: Minhas Kamal(BSSE0509, IIT, DU)
 * Date: 23-May-2014
 * Rewrite: 21-Jun-2015
 */

package com.resultAnalysisTool.tools.resultProcess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import com.resultAnalysisTool.util.fileReadWrite.FileIO;
import com.resultAnalysisTool.util.message.Message;

public class ProcessResultOpt {
	private String TAIFilePath;
	private String CSDMainAnswerFilePath;
	private String DBFStudentFilePath;
	private String DBFResultFilePath;
	private boolean[] isParameterField;
	private boolean[] isSubjectField;
	
	private int parameterTypes;
	//[parameterTypes][value(class, section, set code)]
	private String[][] parameters;
	//[parameterTypes][subject][answers]
	private String[][][] rightAnswers;
	
	//[subject][value]
	private double[][] positiveNumbers;
	private double[][] negativeNumbers;
	
	public ProcessResultOpt(String TAIFilePath, String CSDMainAnswerFilePath, String DBFStudentFilePath,
			boolean[] isParameterField, boolean[] isSubjectField) {
		
		this.TAIFilePath = TAIFilePath;
		this.CSDMainAnswerFilePath = CSDMainAnswerFilePath;
		this.DBFStudentFilePath = DBFStudentFilePath;
		this.DBFResultFilePath = DBFStudentFilePath+"_res.dbf";
		this.isParameterField = isParameterField;
		this.isSubjectField = isSubjectField;
		
		try {
			initParameter_Answer();
			initNumbers();
			
			processAnswer();
			
			new Message("Operation successful!", 1);
		} catch (Exception e) {
			String exceptionName = e.getClass().getSimpleName();
			
			if(exceptionName.equals("ArrayIndexOutOfBoundsException")){
				new Message("Incompatible file data.", 420);
			}else if(exceptionName.equals("NoSuchFileException")){
				new Message("Error reading file. \nMay be file does not exist.", 420);
			}else{
				new Message("Error", 420);
			}
			
			//e.printStackTrace();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	private void initParameter_Answer() throws Exception{
		String[] mainAnswerFileLines = FileIO.readWholeFile(CSDMainAnswerFilePath).split("\n");
		parameterTypes = mainAnswerFileLines.length;
		
		String[][] mainAnswerFileValues = new String[parameterTypes][];
		for(int i=0; i<parameterTypes; i++){
			mainAnswerFileValues[i] = mainAnswerFileLines[i].split(",");
		}
		
		int[] fieldPositions = getFieldPositions();
		
		//////////////////////////////////////////
		int noOfParameters=0;
		for(boolean bool:isParameterField){
			if(bool){
				noOfParameters++;
			}
		}
		parameters = new String[parameterTypes][noOfParameters];
		for(int i=0; i<parameterTypes; i++){	//go to each line
			int currentFieldPosition=0;
			int parameterNo = 0;
			for(int j=0; j<isParameterField.length; j++){	//go each field
				if(isParameterField[j]){
					parameters[i][parameterNo] = "";
					while(currentFieldPosition<fieldPositions[j]){
						parameters[i][parameterNo] += mainAnswerFileValues[i][currentFieldPosition];
						currentFieldPosition++;
					}
					parameterNo++;
				}else{
					currentFieldPosition = fieldPositions[j];
				}
			}
		}
		
		
		/*/for(String[] strings: parameters){	///test print
			for(String string: strings){
				System.out.print(string+" ");
			}System.out.println();
		}/**/
		
		/////////////////////////////////////////////
		int noOfSubjects=0;
		for(boolean bool:isSubjectField){
			if(bool){
				noOfSubjects++;
			}
		}
		rightAnswers = new String[parameterTypes][noOfSubjects][];
		
		for(int i=0; i<parameterTypes; i++){	//go to each line
			int currentFieldPosition=0;
			int subjectNo = 0;
			for(int j=0; j<isSubjectField.length; j++){	//go each field
				if(isSubjectField[j]){
					String string = "";
					while(currentFieldPosition<fieldPositions[j]){
						string += mainAnswerFileValues[i][currentFieldPosition]+",";
						currentFieldPosition++;
					}
					rightAnswers[i][subjectNo] = string.split(",");
					subjectNo++;
				}else{
					currentFieldPosition = fieldPositions[j];
				}
			}
		}
		
		
		/*/for(String[][] stringses: rightAnswers){	///test print
			for(String[] strings: stringses){
				for(String string: strings){
					System.out.print(string+",");
				}System.out.print(";");
			}System.out.println();
		}/**/
		
		return ;
	}
	
	private int[] getFieldPositions() throws Exception{
		String[] stringFieldPositions = FileIO.readSpecificFileLine(TAIFilePath, 2).split(",");
		int[] fieldPositions = new int[stringFieldPositions.length];
		for(int i=0; i<stringFieldPositions.length; i++){
			fieldPositions[i] = Integer.parseInt(stringFieldPositions[i]);
		}
		
		return fieldPositions;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void initNumbers()  throws Exception{
		positiveNumbers = new double[rightAnswers[0].length][];
		negativeNumbers = new double[rightAnswers[0].length][];
		for(int i=0; i<rightAnswers[0].length; i++){
			positiveNumbers[i] = new double[rightAnswers[0][i].length];
			negativeNumbers[i] = new double[rightAnswers[0][i].length];			
		}
		
		//////////
		
		int[] fieldPositions = getFieldPositions();
		boolean[] isAnswerField =  getIsAnswerField();
		double[][] numbers = getNumbers();
		
		//////////
		
		int numberIndex = 0;
		int subjectIndex = 0;
		int previousFieldPosition = 0;
		for(int i=0; i<isAnswerField.length; i++){
			
			if(isAnswerField[i]){
				if(isSubjectField[i]){
					
					for(int j=0; j<fieldPositions[i]-previousFieldPosition; j++){
						positiveNumbers[subjectIndex][j] = numbers[0][numberIndex+j];
						negativeNumbers[subjectIndex][j] = numbers[1][numberIndex+j];
					}
					
					subjectIndex++;
				}
				
				numberIndex += fieldPositions[i]-previousFieldPosition;
			}
			
			previousFieldPosition = fieldPositions[i];
		}
		
		/*/for(int i=0; i<positiveNumbers.length; i++){	//test
			for(int j=0; j<positiveNumbers[0].length; j++){
				System.out.println(positiveNumbers[i][j] + " | " + negativeNumbers[i][j]);
			}
			System.out.println();
		}/**/
		
		return ;
	}
	
	private boolean[] getIsAnswerField() throws Exception{
		String[] stringIsAnswerField = FileIO.readSpecificFileLine(TAIFilePath, 3).split(",");
		boolean[] isAnswerField = new boolean[stringIsAnswerField.length];
		for(int i=0; i<stringIsAnswerField.length; i++){
			isAnswerField[i] = Boolean.parseBoolean(stringIsAnswerField[i]);
		}
		
		return isAnswerField;
	}
	
	
	private double[][] getNumbers() throws Exception{
		String[] stringNumbers = FileIO.readSpecificFileLine(TAIFilePath, 4).split(",");
		double[][] numbers = new double[2][stringNumbers.length];
		
		for(int i=0; i<stringNumbers.length; i++){
			numbers[0][i] = Double.parseDouble(stringNumbers[i]);
		}
		
		stringNumbers = FileIO.readSpecificFileLine(TAIFilePath, 5).split(",");
		
		for(int i=0; i<stringNumbers.length; i++){
			numbers[1][i] = Double.parseDouble(stringNumbers[i]);
		}
		
		return numbers;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void processAnswer() throws Exception{
		BufferedWriter mainBW = new BufferedWriter(new FileWriter(DBFResultFilePath));
		BufferedReader mainBR = new BufferedReader(new FileReader(DBFStudentFilePath));
		
		String mainInputString = mainBR.readLine();
		
		String mainOutputString = getAllFieldNames(mainInputString)+"\n";
		mainBW.write(mainOutputString);
		
		mainInputString = mainBR.readLine();
		while(mainInputString!=null){
			mainOutputString = processStudentFile(mainInputString)+"\n";
			mainBW.write(mainOutputString);
			
			mainInputString = mainBR.readLine();
		}
		
		mainBR.close();
		mainBW.close();
	}
	
	private String getAllFieldNames(String mainInputString) throws Exception{
		String[] stringFieldNames = mainInputString.split(";");
		String fieldNameString = mainInputString;
		for(int i=0; i<stringFieldNames.length-1; i++){
			if(isSubjectField[i]){
				fieldNameString += ";"+stringFieldNames[i]+" Res";
			}
		}
		
		return fieldNameString;
	}
	
	private String processStudentFile(String mainInputString){
		String mainOutputString = mainInputString;
		
		String[] splittedMainInputString = mainInputString.split(";");
		
		int parameterType = getParameterType(splittedMainInputString);
		double number = 0;
		for(int i=0, j=0; i<isSubjectField.length; i++){
			if(isSubjectField[i]){
				number = getNumber(splittedMainInputString[i], parameterType, j);
				mainOutputString += ";"+number;
				j++;
			}
		}
		
		return mainOutputString;
	}
	
	private int getParameterType(String[] splittedMainInputString){
		String[] newParameters = new String[parameters[0].length];
		for(int i=0, j=0; i<isParameterField.length; i++){
			if(isParameterField[i]){
				newParameters[j] = splittedMainInputString[i];
				j++;
			}
		}
		
		for(int i=0, j; i<parameters.length; i++){
			for(j=0; j<parameters[0].length; j++){
				if(!newParameters[j].equals(parameters[i][j])){
					break;
				}
			}
			
			if(j == parameters[0].length){
				return i;
			}
		}
		
		
		return -1;
	}
	
	private double getNumber(String studentAnswer, int parameterType, int subjectFieldIndex){
		/*if(parameters.length){
			
		}*/
		
		String[] studentAnswers = studentAnswer.split(",");
		
		double number = 0;
		
		for(int i=0; i<rightAnswers[parameterType][subjectFieldIndex].length; i++){
			if(isCorrectAnswer(rightAnswers[parameterType][subjectFieldIndex][i], studentAnswers[i])){
				number += positiveNumbers[subjectFieldIndex][i];
			}else{
				number -= negativeNumbers[subjectFieldIndex][i];
			}
		}
		
		return number;
	}
	
	/***####***/
	private boolean isCorrectAnswer(String rightAnswer, String studentAnswer){
		return rightAnswer.equals(studentAnswer);
	}
	/***####***/
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) throws Exception {
		String TAIFilePath = "C:/Users/admin/Desktop/IIT-MIT/MITquestion.csd_tableDesign.tai";
		String CSDMainAnswerFilePath = "C:/Users/admin/Desktop/IIT-MIT/MITanswer.csd";
		String DBFStudentFilePath = "C:/Users/admin/Desktop/IIT-MIT/MITquestion.csd_imp.dbf";
		
		boolean[] isParameterField = {false, false, false, false};
		boolean[] isSubjectField = {false, false, true, false};
		
		new ProcessResultOpt(TAIFilePath, CSDMainAnswerFilePath, DBFStudentFilePath, isParameterField, isSubjectField);
	}
}
