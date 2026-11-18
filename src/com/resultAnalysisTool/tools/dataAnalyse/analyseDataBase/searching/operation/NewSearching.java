/**
 * Developer : Shahriar Mohammed Ishmam BSSE 0523 IIT,University Of Dhaka
 * Date : 5-8-2014
 * 
 * 
 * comment : Takes file path as a string of array ,a field as String where it will search and a string what it will search
 */

package com.resultAnalysisTool.tools.dataAnalyse.analyseDataBase.searching.operation;

import java.util.ArrayList;
import java.util.Iterator;

import com.resultAnalysisTool.util.fileReadWrite.FileIO;


public class NewSearching {
	/**
	 * Takes file path as a string of array ,a field as String where it will search and a string what it will search
	 * @param arrayOfFilePath Array of String containing file path
	 * @param baseOfSearch Field as String where it will search
	 * @param whatToSearch	What it will be searched
	 * @return Returns 0 if file is written after all file being searched successfully if error occurs,returns -420
	 * @throws Exception 
	 */
	
	public String multipleSearching(String arrayOfFilePath,String columnOfSearch, String whatToSearch) throws Exception{
		String unmatchedString="";//it will carry those string(student's full info) that does not match with searching content
		ArrayList<String> matchedList=new ArrayList<String>();//it will carry those string(full info) that match with search content
		ArrayList<Integer> distanceList=new ArrayList<Integer>();//it carry the distance from where the column start and where
														//the searched content found
		
		String fieldNames = null;
		
			
			
			String fileInfo=FileIO.readWholeFile(arrayOfFilePath);//reading
			long fileInfoLength=fileInfo.length();
			
			
			
		
			
			
			String tempStringOne="";
			int numberOfCommasBeforeGivenField = 0;
			boolean flagOne=true;
			
			for(int i=0;i<fileInfoLength;i++){
				if(fileInfo.charAt(i)!='\n')//making string of a student's info
					tempStringOne=tempStringOne+fileInfo.charAt(i);
				
				else if(fileInfo.charAt(i)=='\n'){
					if(flagOne==true){//collecting the fields' name in string
						numberOfCommasBeforeGivenField=numberOfcommasBeforeGivenColumn(tempStringOne, columnOfSearch);
						//finding number of commas excluding before where have to search 
						if(numberOfCommasBeforeGivenField==-420)
							return null;//if baseOfSort does not match with any field in field name
						fieldNames=tempStringOne;
						flagOne=false;//after getting fields' name loop will not get in this block
					}
					
					else{
						
						String specificColumnElement=getSpecificColumnElement(tempStringOne,numberOfCommasBeforeGivenField);
						//getting string in the column where have to be searched
						
						int textLength=specificColumnElement.length();//start of searching
						int count=0;
						int distance = -420;
						boolean flagTwo=false;

						for(int k=0;k<textLength;k++){
							
							if(whatToSearch.charAt(count)!=specificColumnElement.charAt(k)){
								//System.out.println("not "+specificColumnElement.charAt(k));
								count=0;
								
								
							}
							if(whatToSearch.charAt(count)==specificColumnElement.charAt(k)){
								//System.out.println("yes "+specificColumnElement.charAt(k));
								count++;
								if(count==whatToSearch.length()){
									distance=k;
									flagTwo=true;
									break;
								}
								
								continue;
						
							}
						
						}
						
						
						if(flagTwo==false){//if does not match we collect full info of that line
							unmatchedString=unmatchedString+tempStringOne+'\n';
							
						}
						
						else{
							
							matchedList.add(tempStringOne);//if does match we collect full info of that line and distance
															//from the beginning of column
							distanceList.add(distance);
						}
						
						
						
						
					}
					
					
					
					
					tempStringOne="";//empty the previous students' info to collect new
				}
				
			}
			
			
			
		
//		System.out.println(matchedList);
//		System.out.println(distanceList);
		
		int distanceArray[]=convertToIntegers(distanceList);//arrayList to array
		long distanceArrayLength=distanceArray.length;
		int index[]=new int[(int) distanceArrayLength];
		String sortedArray[]=new String[(int) distanceArrayLength];
		String returnString="";
		
		for(int k=0;k<distanceArrayLength;k++)//
			index[k]=k;
		
		index=quickSort(distanceArray, index,  0, index.length-1);
		
		for(int k=0;k<distanceArrayLength;k++){
			//System.out.println(index[i-1]);
			//System.out.println(eachLineInfoArrayList.get(index[i-1]));
			sortedArray[k]=matchedList.get(index[k]);
			returnString=returnString+sortedArray[k];
			if(k==distanceArrayLength-1){
				if(unmatchedString!="")
					returnString=returnString+"\n";
			}
			else
				returnString=returnString+"\n";
			
		}
		
		returnString=fieldNames+'\n'+returnString+unmatchedString;
		
//		for(int k=0;k<distanceArray.length;k++)
			System.out.println(returnString);
		
		
		
		
		return "done";
		
	}
	
	
	public String[][] multipleSearching(String[][] fileInfo,String columnOfSearch, String whatToSearch){
		
		long fileInfoLength=fileInfo.length;
		long fileInfoWidth=fileInfo[0].length;
		int columnNumber=-420;
		
		
		for(int i=0;i<fileInfoWidth;i++){
			
//			System.out.println(columnOfSearch+" "+fileInfo[0][i]);
			if(fileInfo[0][i].equals(columnOfSearch)){
				
				columnNumber=i;
				break;
			}
			
			
		}
		if(columnNumber==-420){
			
			return null;
		}
		
		ArrayList<Integer> unmatchedIndexList=new ArrayList<Integer>();
		ArrayList<Integer> matchedIndexList=new ArrayList<Integer>();
		ArrayList<Integer> distanceList=new ArrayList<Integer>();
		
		
		String specificColumnElement="";
		for(int i=1;i<fileInfoLength;i++){
			specificColumnElement=fileInfo[i][columnNumber];
			
			int textLength=specificColumnElement.length();//start of searching
			int count=0;
			int distance = -420;
			boolean flagTwo=false;
			
			

			for(int k=0;k<textLength;k++){
				
				if(whatToSearch.charAt(count)!=specificColumnElement.charAt(k)){
					//System.out.println("not "+specificColumnElement.charAt(k));
					count=0;
					
					
				}
				if(whatToSearch.charAt(count)==specificColumnElement.charAt(k)){
					//System.out.println("yes "+specificColumnElement.charAt(k));
					count++;
					if(count==whatToSearch.length()){
						distance=k;
						flagTwo=true;
						break;
					}
					
					continue;
			
				}
			
			}
			
			
			if(flagTwo==false){//if does not match we collect full info of that line
				unmatchedIndexList.add(i);
				
			}
			
			else{
				
				matchedIndexList.add(i);//if does match we collect full info of that line and distance
												//from the beginning of column
				distanceList.add(distance);
			}
			
		}
		
		int matchedIndexArray[]=convertToIntegers(matchedIndexList);
		int matchedIndexArrayLength=matchedIndexArray.length;
		
		int unmatchedIndexArray[]=convertToIntegers(unmatchedIndexList);
		int unmatchedIndexArrayLength=unmatchedIndexArray.length;
		
		int distanceArray[]=convertToIntegers(distanceList);//arrayList to array
		
		
		
//		String sortedArray[]=new String[(int) distanceArrayLength];
		
		matchedIndexArray=quickSort(distanceArray, matchedIndexArray,  0, matchedIndexArray.length-1);
		
		String[][] sortedArray =new String[(int) fileInfoLength][(int) fileInfoWidth];
		sortedArray[0]=fileInfo[0];
		
		int tempVariable=-420;
		
		for(int j=0;j<matchedIndexArrayLength;j++){
			sortedArray[j+1]=fileInfo[matchedIndexArray[j]];
			
			if(j == matchedIndexArrayLength - 1)
				tempVariable=j+1;
		}
		tempVariable++;
		for(int j=0;j<unmatchedIndexArrayLength;j++){
			sortedArray[tempVariable+j]=fileInfo[unmatchedIndexArray[j]];
		}
		
		return sortedArray;
	}
	
	
	
	
	/**
	 * Get number of commas(excluding commas between to quotations),after what a sub string is situated in a String
	 * @param fullString A String
	 * @param BaseOfSpecification A Substring
	 * @return number of Commas(excluding commas between to quotations),after what the the substring is situated
	 */
private int numberOfcommasBeforeGivenColumn(String fullString,String BaseOfSpecification){
		
		//splited the full string into array base on comma//?????arrayBasedOnComma[]
		
		int numberOfcommas=0;
		boolean flag=false;
		int fullStringLength = fullString.length();
		
		String string2="";
		int quotationCounter=0;
		for(int i=0;i<fullStringLength;i++){
			if(quotationCounter%2==0){
				if(fullString.charAt(i)!=','  && fullString.charAt(i)!='\n'){
					string2=string2+fullString.charAt(i);
				}
				
				if(fullString.charAt(i)==',' || i==fullString.length()-1){
					if(string2.equals(BaseOfSpecification)){
						flag=true;
						break;
					}
					string2="";	
				}
				
				if(fullString.charAt(i)==',')
					numberOfcommas++;
			}
			else if(quotationCounter%2==1){
					string2=string2+fullString.charAt(i);
			}
			
			if(fullString.charAt(i)=='"'){
				quotationCounter++;	
			}
			

		}
		if(flag)
			return numberOfcommas;
		else
			return -420;
	}
	
/**
 * Return a substring in a string after specific number of comma is found
 * @param fullString A string that contains a student's full info
 * @param numberOfCommasBeforeGivenField number of commas or column before given column content is found
 * @return content of given field
 */

private String getSpecificColumnElement(String fullString,int numberOfCommasBeforeGivenField){
	fullString=fullString+',';
	int fullStringLength = fullString.length();
	int numberOfcommas = 0;
	String string2="";
	int quotationCounter=0;
	
	
	for(int i=0;i<fullStringLength;i++){
		if(quotationCounter%2==0){
			if(fullString.charAt(i)!=','  && fullString.charAt(i)!='\n'){
				string2=string2+fullString.charAt(i);
			}
			
			if(fullString.charAt(i)==','){
				if(numberOfCommasBeforeGivenField==numberOfcommas){
					return string2;
				}
				string2="";	
			}
			
			if(fullString.charAt(i)==',')
				numberOfcommas++;
			
		}
		else if(quotationCounter%2==1){
				string2=string2+fullString.charAt(i);
		}
		
		if(fullString.charAt(i)=='"'){
			quotationCounter++;	
		}
		

	}
	
	return "";
}


/**
 * choose a pivot point and create partion in array
 * @param arr File's Data 
 * @param left 1st index of array
 * @param right the last index of array
 * @param columnNumber indicates data have to be sorted on what column's in4mation
 * @param typeOfData Integer sort? String sort?
 * @return partition pint of array
 */
private int partition(int mainArr[],int indexArr[], int left, int right){

	int i = left, j = right;

	int temp;
	
	double pivot = mainArr[(left+right)/2];//pivot is a value,comparion with it the data will be sorted
	
	while (i <= j) {

		while (mainArr[i]<pivot){
			i++;
		}


		
		while (pivot<mainArr[j]){
			j--;
		}

		if (i <= j) {//swaping

			temp = mainArr[i];

			mainArr[i] = mainArr[j];

			mainArr[j] = temp;
			
			temp=indexArr[i];
			indexArr[i]=indexArr[j];
			indexArr[j]=(int) temp;
			i++;

			j--;

		}

	};


	return i;

}



/**
 * Sort the information of array, on basis of specific column's element
 * @param arr contains file's information as String
 * @param left 1st index of array as Integer
 * @param right last index of array as Integer
 * @param columnNumber what column is responsible for sorting,his index as Integer
 * @param typeOfData typeOfData as Integer Integer sort? String sort?
 * @return Sorted Information A String Array
 */
private int[] quickSort(int mainArr[],int indexArr[], int left, int right) {
	
	
	int index = partition(mainArr,indexArr, left, right);
	
	if (left < index - 1)

		quickSort(mainArr,indexArr, left, index - 1);
	

	if (index < right)

		quickSort(mainArr,indexArr, index, right);
	
	return indexArr;

}

/**
 * convert an integer arrayList to integer array
 * @param integers Integer ArrayList
 * @return Integer Array
 */
private int[] convertToIntegers(ArrayList<Integer> integers)
{
    int[] ret = new int[integers.size()];
    Iterator<Integer> iterator = integers.iterator();
    for (int i = 0; i < ret.length; i++)
    {
        ret[i] = iterator.next().intValue();
    }
    return ret;
}


private String[][] forTestOnly(String[] arr){//only for testing
	ArrayList<String> list=splitStringBasedOnComma(arr[0]);
	String arr2[][]=new String[arr.length][list.size()];
	
	for(int i=0;i<arr.length;i++){
		list=splitStringBasedOnComma(arr[i]);
		
		for(int j=0;j<list.size();j++)
			arr2[i][j]=list.get(j);
		
	}
	
	return arr2;
}

private ArrayList<String> splitStringBasedOnComma(String unSplitedString){//only for testing
	
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




	/**///only for test
	public static void main(String[] args) throws Exception {
		NewSearching operation=new NewSearching();
		
		String fileInformation=FileIO.readWholeFile("C:\\Users\\admin\\Desktop\\OMR\\OMR_Sheetimg.csd_Imported.dbf");
		
		String arr3[]=fileInformation.split("\n");
		
		String arr4[][]=operation.forTestOnly(arr3);
		
//		operation.multipleSearching("E:/workspace/ProjectLab1/TestSubject/ResultIITDU.csv","Reg. No" , "31");
		String sortedArray[][]=operation.multipleSearching(arr4,"\"Roll Number\"" , "011");
		
		System.out.println(sortedArray);
	}
	/**/
}
