/**
 * Developer: Shahriar Mohammed Ishmam (BSSE0523, IIT, DU)
 * Date: 02-Mar-2014, Modified: 21/5/2014
 * Comment: 
 */


package com.resultAnalysisTool.tools.dataAnalyse.analyseDataBase.searching.operation;

import java.util.ArrayList;

import com.resultAnalysisTool.util.fileReadWrite.FileIO;

public class DoSearching {
	/**
	 * Search something in a specific fields of file
	 * @param fileName file path 
	 * @param baseOfSearch under what fields it will be searched..suppose roll number,reg number
	 * @param whatToSearch string that have to search
	 * @param previousFileSearchedData save the previous file's data, when 1st file <code>previousFileSearchedData<code>=""
	 * @return the method will return a string of information of matched students
	 * @throws Exception 
	 */
	public String partialStringSearchMethodOne(String fileName,String baseOfSearch, String whatToSearch,String previousFileSearchedData) throws Exception{
			
		
		String fullFileInformation;
		boolean flag=false;
		
		
		fullFileInformation=FileIO.readWholeFile(fileName);
		ArrayList<String> arrayList=new ArrayList<String>();
		
		String eachStudentInformationArray[]=fullFileInformation.split("\n");//each index carry individual student's information
		
		
		
		String[] previousFileSearchedDataArray=null;
		ArrayList<String> commonFieldsArrayList;
		String[] commonFieldsArray=null;
		
		if(previousFileSearchedData!=""){//for 1st file it will not execute,for other it will
			previousFileSearchedDataArray=previousFileSearchedData.split("\n");//making array of previous file's sorted data
			commonFieldsArrayList=splitStringBasedOnComma(commonFieldOfTwoFiles(eachStudentInformationArray[0], previousFileSearchedDataArray[0]));//finding the common field
			
			commonFieldsArray=commonFieldsArrayList.toArray(new String[commonFieldsArrayList.size()]);//to array from list
			
			eachStudentInformationArray=doAdding(eachStudentInformationArray, previousFileSearchedDataArray,commonFieldsArray);//adding the two file's common field
		}
		
		int eachStudentInformationArrayLength=eachStudentInformationArray.length;
		
		
		int index=findSpecificColumn(eachStudentInformationArray[0], baseOfSearch);//get the index of baseOfSearch
		
		if(index<0)
			return "error";
		
		
		for(int i=1;i<eachStudentInformationArrayLength;i++){
			ArrayList<String> columnBasedArrayList=splitStringBasedOnComma(eachStudentInformationArray[i]);//split a student's information base on comma
		
		
			flag=partialStringSearchMethodTwo(columnBasedArrayList.get(index), whatToSearch);
			
			if(flag==true)
				arrayList.add(eachStudentInformationArray[i]);//if matched found,the info is saving in arrayList..index+info
			
		}
		
		String returnString= eachStudentInformationArray[0]+"\n";
		
		int arrayListSize=arrayList.size();
		for(int i=0;i<arrayListSize;i++){//creating one string
			returnString=returnString+arrayList.get(i);
				
			if(i!=arrayList.size()-1)
				returnString=returnString+"\n";//last line will not have "\n"
		}
		
			if(arrayListSize==0){
				FileIO.writeWholeFile(fileName+"PartialSearch"+".csv", "");
				return "error";
			}
		
		return returnString;
	}
	

	/**
	 * Check a pattern in String
	 * suppose,text="Shashriar" ,pattern="shr",first it will search for s,if match then for h,if match then for
	   	r,if do not match the i=0,so it will again search for s,then h,then r...............
		if pattern match,it will return true
	 * @param text as String
	 * @param pattern as String
	 * @return if matched, return true else false
	 */
	private boolean partialStringSearchMethodTwo(String text,String pattern){
		int textLength=text.length();
		int i=0;
		
		
		for(int j=0;j<textLength;j++){
			
			if(pattern.charAt(i)!=text.charAt(j)){
				
				i=0;
				continue;
				
			}
			if(pattern.charAt(i)==text.charAt(j)){
				
				i++;
				if(i==pattern.length()){
					return true;
				}
				
				continue;
		
			}
		
		}
		
		
		return false;
	}
	
	
	/**
	 * it will return the column number where <code>BaseOfSpecification</code> situated
	 * @param fullString a Stirng contains fields' name
	 * @param BaseOfSpecification a field name that have to be searched in fullString
	 * @return if found return the columnNumber-1 else -100
	 */
	private int findSpecificColumn(String fullString,String BaseOfSpecification){
		
		ArrayList<String> arrayListBasedOnComma=splitStringBasedOnComma(fullString);//splited the full string into array base on comma
		
		int arrayListBasedOnCommaLength=arrayListBasedOnComma.size();
		
		for(int i=0;i<arrayListBasedOnCommaLength;i++){
			
			if(arrayListBasedOnComma.get(i).equals(BaseOfSpecification)){
				return i;
			}
		}
		
		return -100; //when,no match is found
	}
	
	/**
	 * Get String and split the String on basis of commas,will not split if commas are found between two quotations.
	 * @param unSplitedString A String 
	 * @return An ArrayList contains the element get from splitting the String
	 */
private ArrayList<String> splitStringBasedOnComma(String unSplitedString){
		
		ArrayList<String> arrayList=new ArrayList<String>();
		int unSplitedStringLength = unSplitedString.length();
		
		String string2="";
		int quotationCounter=0;
		for(int i=0;i<unSplitedStringLength;i++){
			if(quotationCounter%2==0){

				if(unSplitedString.charAt(i)!=','  && unSplitedString.charAt(i)!='\n'){
					string2=string2+unSplitedString.charAt(i);
				}
			}
			else if(quotationCounter%2==1){
					string2=string2+unSplitedString.charAt(i);
			}
			
			if(unSplitedString.charAt(i)=='"'){
				quotationCounter++;	
			}
			
			if(quotationCounter%2==0 && (unSplitedString.charAt(i)==',' || i==unSplitedString.length()-1)){
				if(string2!=""){
					arrayList.add(string2);
					
				}
				string2="";	
			}
		}
		
		return arrayList;
	}	
	
/**
 * Returns the common field of two information
 * @param fileOneInfo Fields of 1st file
 * @param fileTwoInfo Fields of 2nd file
 * @return Common Fields of two Strings
 */
	private String commonFieldOfTwoFiles(String fileOneInfo,String fileTwoInfo){
		
		
		ArrayList<String> fileOneInfoArrayList=splitStringBasedOnComma(fileOneInfo);
		
		ArrayList<String> fileTwoInfoArrayList=splitStringBasedOnComma(fileTwoInfo);
		
		int fileOneInfoArrayListLength=fileOneInfoArrayList.size();
		int fileTwoInfoArrayListLength=fileTwoInfoArrayList.size();
		
		String returnString="";
		
		for(int i=0;i<fileOneInfoArrayListLength;i++){
			for(int j=0;j<fileTwoInfoArrayListLength;j++){
				if(fileOneInfoArrayList.get(i).equals(fileTwoInfoArrayList.get(j))){
					
					returnString=returnString+fileOneInfoArrayList.get(i)+",";
					break;
				}
			}
		}
		
		return returnString;
		
	}
	
	
	/**
	 *Add two files information on basis of common fields
	 * @param fileOneInfoArray First file's information as String
	 * @param fileTwoInfoArray Second file's information as String
	 * @param fieldNameArray CommonFields of two files
	 * @return Array of String contains added information
	 */
	public String[] doAdding(String[] fileOneInfoArray,
			String[] fileTwoInfoArray, String[] fieldNameArray) {

		String newString = "";

		long fileOneInfoArrayLength = fileOneInfoArray.length;
		long fileTwoInfoArrayLength = fileTwoInfoArray.length;
		int fieldNameArrayLength = fieldNameArray.length;

		String tempString = "";

		for (int j = 0; j < fieldNameArrayLength; j++) {
			tempString = tempString + fieldNameArray[j];
			if (j != fieldNameArrayLength - 1)
				tempString = tempString + ",";

		}
		
		// getting the index of file's field's name that shall be in new file
		ArrayList<Integer> indexFileOneArrayList = keyFieldIndexInFile(
				splitStringBasedOnComma(fileOneInfoArray[0]), fieldNameArray);

		int indexFileOneArrayListLength = indexFileOneArrayList.size();

		// comparing index then making new string

		for (int j = 1; j < fileOneInfoArrayLength; j++) {
			// line's info comma separated in an array
			ArrayList<String> eachLineInfoArrayList = splitStringBasedOnComma(fileOneInfoArray[j]);

			for (int k = 0; k < indexFileOneArrayListLength; k++) {// index
				newString = newString
						+ eachLineInfoArrayList.get(indexFileOneArrayList
								.get(k));// making string
				if (k != indexFileOneArrayListLength - 1)
					newString = newString + ",";

			}

			newString = newString + "\n";

		}

		ArrayList<Integer> indexFileTwoArrayList = keyFieldIndexInFile(
				// getting the index of file's field's name that shall be in new file
				splitStringBasedOnComma(fileTwoInfoArray[0]), fieldNameArray);

		int indexFileTwoArrayListLength = indexFileTwoArrayList.size();

		for (int j = 1; j < fileTwoInfoArrayLength; j++) {
			//line's info comma separated in an array
			ArrayList<String> eachLineInfoArrayList = splitStringBasedOnComma(fileTwoInfoArray[j]);

			for (int k = 0; k < indexFileTwoArrayListLength; k++) {// index
				newString = newString
						+ eachLineInfoArrayList.get(indexFileTwoArrayList
								.get(k));// making string
				if (k != indexFileTwoArrayListLength - 1)
					newString = newString + ",";
			}

			newString = newString + "\n";

		}

		tempString = tempString + "\n" + newString;

		return tempString.split("\n");
	}
	
	/**
	 * comparing arrayList and array's info..if equal that shall return an array of index of arrayList
	 * @param fieldNamesArrayList defines the arrayList of fields of one fileds
	 * @param keyFieldArray contains common field names of files and the new file's field name
	 * @return
	 */
	private ArrayList<Integer> keyFieldIndexInFile(ArrayList<String> fieldNamesArrayList,String[] keyFieldArray){
		//here, keyFieldArray=field names of files that have to be created
		//fieldNamesArrayList=splitting fieldNames..get array....
		ArrayList<Integer> indexArrayList=new ArrayList<Integer>();
	
		int keyFieldArrayLength=keyFieldArray.length;
		int fieldNamesArrayListLength=fieldNamesArrayList.size();
	
		for(int i=0;i<keyFieldArrayLength;i++){
			for(int j=0;j<fieldNamesArrayListLength;j++){
				if(keyFieldArray[i].equals(fieldNamesArrayList.get(j))){
					indexArrayList.add(j);
					break;
				}
			}
		}

		return indexArrayList;
	}
	
	
	/**///only for test
	public static void main(String[] args) throws Exception {
		DoSearching operation = new DoSearching();
		
		String fileInformation=FileIO.readWholeFile("D:/java/fileForTest/f2.csv");
		System.out.println(operation.partialStringSearchMethodOne("D:/java/fileForTest/f1.csv","roll number" , "3",fileInformation));

	}
	/**/
}
