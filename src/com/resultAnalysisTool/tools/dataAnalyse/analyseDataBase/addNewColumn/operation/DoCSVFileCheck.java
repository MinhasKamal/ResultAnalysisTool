/**
 * Developer : Shahriar Mohammed Ishmam BSSE 0523 IIT,University Of Dhaka
 * Date : 11-7-2014
 * Modified Date :6-9-2014
 * 
 * comment : Take a file path as string,check if it is .csv formated,if it is,then return the file info in two dimensional array
 */
package com.resultAnalysisTool.tools.dataAnalyse.analyseDataBase.addNewColumn.operation;

import java.util.ArrayList;

import com.resultAnalysisTool.util.fileReadWrite.FileIO;

public class DoCSVFileCheck {
/**
 * Take a file path as string,check if it is .csv formated,if it is,then return the file info in two dimensional array
 * @param fileName A file path as string
 * @return file's content
 * @throws Exception 
 */
	
	public String[][] CSVFileCheck(String fileName) throws Exception{
		int lengthOfFileName=fileName.length()-1;//-1 as indexing start from 0....
		
		String string="";
		
		while(fileName.charAt(lengthOfFileName)!='.'){//taking character from last until . is not got
			string=string+fileName.charAt(lengthOfFileName);
			lengthOfFileName--;
		}
		
		
		int stringLenth=string.length();
		String extentionOfFile="";
		for(int i=stringLenth-1;i>=0;i--){//reversing string to get file extension
			extentionOfFile=extentionOfFile+string.charAt(i);
		}
		
		//System.out.println("extention "+extentionOfFile);
		
		if(!extentionOfFile.equalsIgnoreCase("csv"))
			return null;
		
		String fileInfo=FileIO.readWholeFile(fileName);//logic:create arrayList spliting line on basis of commas
													//	in each line,check the size with fieldNumbers, 
													//if line has equal column,its csv
		
		//System.out.println(fileInfo);
		int i=0;
		int numberOfComma=0;
		int counter=0;
		while(fileInfo.charAt(i)!='\n'){
			if(fileInfo.charAt(i)=='"')
				counter++;
			
			if(counter%2==0 && fileInfo.charAt(i)==',')
				numberOfComma++;
			i++;
		}
		
		//System.out.println(numberOfComma);
		//ArrayList<String> arrayList[] = new ArrayList[numberOfComma+1];
		@SuppressWarnings("unchecked")
		ArrayList<String>[] arrayList = (ArrayList<String>[])new ArrayList[numberOfComma+1];
		for(i=0;i<numberOfComma+1;i++)
			arrayList[i]=new ArrayList<String>();
		
		long fileInfoLength=fileInfo.length();
		
		int j=0;
		counter=0;
		String string2="";
		for(i=0;i<fileInfoLength;i++){
				
			
				if(counter%2==0){

					if(fileInfo.charAt(i)!=','  && fileInfo.charAt(i)!='\n'){
						string2=string2+fileInfo.charAt(i);
					}
				}
				else if(counter%2==1){
						string2=string2+fileInfo.charAt(i);
				}
				
				if(fileInfo.charAt(i)=='"'){
					counter++;	
				}	
				
				if(counter%2==0 && (fileInfo.charAt(i)==',' || fileInfo.charAt(i)=='\n')){
					if(string2!=""){
						//System.out.println("tob "+string2);
						arrayList[j].add(string2);/////////////
						j++;
					}
					string2="";	
				}
			
			if(fileInfo.charAt(i)=='\n'){
				j=0;
				counter=0;
			}
		}
		
		String[][] fileInfoArray=new String[numberOfComma+1][];
		for(i=0;i<numberOfComma+1;i++){
			String[] tempArray = new String[(int) arrayList[i].size()];
			tempArray = arrayList[i].toArray(tempArray);
			
			fileInfoArray[i]=tempArray;
		}
		
		return fileInfoArray;
	}
	
	public static void main(String[] args) {
		try {
			DoCSVFileCheck operation = new DoCSVFileCheck();
			String arr[][];
			arr = operation.CSVFileCheck("E:/java/fileForTest/m1.csv");
			// System.out.println(arr[0].length);

			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					System.out.print(arr[i][j] + " | ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
