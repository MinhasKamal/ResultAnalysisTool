/**
 * Developer : Shahriar Mohammed Ishmam BSSE 0523 IIT,University Of Dhaka
 * Date : 28-7-2014
 * 
 * 
 * comment : Sort <code>fileName<code>'s info, in descending order
 */
package com.resultAnalysisTool.tools.dataAnalyse.analyseDataBase.addNewColumn.operation;

import java.util.ArrayList;

public class AddNewColumnTwo {

	
	
	/**
	 * 
	 * @param fileInfo Two Dimensional String array,that contains file's content........array[column][row]
	 * @param infix if flag[0]==0...then this string have to be evaluated
	 * @param newField A string that is the name of new field
	 * @param flag an array of integer, if flag[0]==0,then it will be infix evolution,if flag[0]==1 then empty column,flag[0]=2
	 * for numerical,flag[0]==3 will be alphabetic ,flag[1]==range of increment......
	 * @return a two dimensional array that contains a new column
	 */
	
	public String[][] doAdding(String[][] fileInfo,String infix,String newField,int[] flag){
		
		int fileInfoLength=fileInfo.length;
		int fileInfoWidth=fileInfo[0].length;
		String[] infixEvaluatedResult=new String[fileInfoWidth]; 
		
		
		if(flag[0]!=0 && infix==""){//add a new column at the last without infix evaluation
			infixEvaluatedResult=addColumn(fileInfo[0].length,flag[0],flag[1],newField);
		}
		
		
		
		else if(flag[0]==0 && infix!=""){//infix eavluation
			
			AddNewColumn anc=new AddNewColumn();
			
			
			
			String[] splitedByPowerSign=splitStringBaseOnPower(infix);
			
//			for(int i=0;i<splitedByPowerSign.length;i++)
//				System.out.println(splitedByPowerSign[i]);
			
			
			
			for(int i=0;i<splitedByPowerSign.length;i++){
				if(splitedByPowerSign[i].charAt(0)==(char)200 && 
						splitedByPowerSign[i].charAt(splitedByPowerSign[i].length()-1)==(char)200){//when splitedByPowerSign[i]
					//is a infix to evaluate cause it is identified by (char)200 infix (char)200
					String tempString="";
					for(int j=1;j<splitedByPowerSign[i].length()-1;j++)// deducting (char)200 from infix
						tempString=tempString+splitedByPowerSign[i].charAt(j);
						
					if(i==0){
						infixEvaluatedResult=anc.doAdding(fileInfo,tempString , newField, flag);//if splitedByPowerSign[0] has infix
					}
					else{
						
						String[] tempEvaluatedArray=anc.doAdding(fileInfo,tempString , newField, flag);
						
						for(int j=1;j<tempEvaluatedArray.length;j++){//adding the recent evaluated result with previous
							infixEvaluatedResult[j]=infixEvaluatedResult[j]+tempEvaluatedArray[j];
						}
						
					}
				}
				
				
				
				else{//when splitedByPowerSign[] contains a String without infix to evalute
					if(i==0){
						infixEvaluatedResult[0]=newField;
						
						for(int j=1;j<infixEvaluatedResult.length;j++)
							infixEvaluatedResult[j]=splitedByPowerSign[i];
					}
					else{//adding with previous
						for(int j=1;j<infixEvaluatedResult.length;j++){
							infixEvaluatedResult[j]=infixEvaluatedResult[j]+splitedByPowerSign[i];
						}
					}
				}
			}
			
			
			
		}
		
		
		
		
		String[][] finalArray=new String[fileInfoLength+1][];
		
		for(int i=0;i<fileInfoLength;i++)//pointing address to the given two dim array
			finalArray[i]=fileInfo[i];
		finalArray[fileInfoLength]=infixEvaluatedResult;//pointing the address
		
		
		return finalArray;
	}
	
	/**
	 * Prepare a New Column
	 * @param fileInfoArrayWidth number of row
	 * @param whatToAdd if whatToAdd=1..add empty column, if 2 add numerical column ..if 3 add alphabetic
	 * @param rangeOfIncrement if numerical or alphabetic...it is the range after what value will be incremented 4 ex..
	 * if rangeOfIncrement=2 then for numerica 1,1,2,2,3,3,4,4
	 * @param newField Name of the new field
	 * @return Prepared column in an Array
	 */
	public String[] addColumn(int fileInfoArrayWidth , int whatToAdd , int rangeOfIncrement , String newField){//whatToAdd 0=empty, 1=numeric 2=alphabetic

		String[] newRow=new String[fileInfoArrayWidth];
		newRow[0]=newField;
		if(whatToAdd==1){
				for(int i=1;i<fileInfoArrayWidth;i++)
					newRow[i]="";
		}
		
		
		
		
		else if(whatToAdd==2){
			
			int k=0;
			int m=1;
			
			for(int i=1;i<fileInfoArrayWidth;i++){
				newRow[i]=m+"";
				k++;
				if(k==rangeOfIncrement){
					m++;
					k=0;
				}
			}
			
		}
		
		
		
		else if(whatToAdd==3){
			int k=0;
			String fieldElement="a";
			
			for(int i=1;i<fileInfoArrayWidth;i++){
				newRow[i]=fieldElement;
				k++;
				if(k==rangeOfIncrement){
					fieldElement=incrementAlphabete(fieldElement);
					k=0;
				}
			}	
		}
		
		
		
		
		
		return newRow;
	}



	/**
	 * Find the next incremented string of a given String
	 * @param string A String ..SUppose a,ee,aaz,zzzz,cczz
	 * @return incremented String...Suppose b,ef,aba,aaaaa,cdaa
	*/
	private String incrementAlphabete(String string){
		int stringLength=string.length();
		int flagOne=1;
		for(int i=0;i<stringLength;i++){
			if(string.charAt(i)!='z')
				flagOne=0;
		}
		String temp="";
		
		
		boolean flagTwo=true;
		if(flagOne==1){
			string="";
			for(int i=0;i<stringLength;i++)
				string=string+'a';
			
			return string+'a';
		}
		
		
		else{
			for(int i=stringLength-1;i>=0;i--){
				if(string.charAt(i)=='z'){
					temp=temp+'a';
					continue;
				}
			
					char m=string.charAt(i);
					//System.out.println(m);
					if(flagTwo==true){
						m++;
						flagTwo=false;
					}
					temp=temp+m;
				
			}
		}
		
		int tempLength=temp.length();
		String tempTwo="";
		
		for(int i=tempLength-1;i>=0;i--)
			tempTwo=tempTwo+temp.charAt(i);
		
		return tempTwo;
	}
	
	/**
	 * Split a string on basis of power sign(^)
	 * @param unSplitedString A String
	 * @return A String Array 
	 */
	
	String[] splitStringBaseOnPower(String unSplitedString){
		ArrayList<String> arrayList=new ArrayList<String>();
		int unSplitedStringLength = unSplitedString.length();
		
		String string2="";
		for(int i=0;i<unSplitedStringLength;i++){

			if(unSplitedString.charAt(i)!='^'  && unSplitedString.charAt(i)!='\n'){
				string2=string2+unSplitedString.charAt(i);	
			}
			
			if(unSplitedString.charAt(i)=='^' || i==unSplitedString.length()-1){
				if(string2!=""){
					arrayList.add(string2);
				}
				string2="";	
			}
		}
		String[] tempArray = new String[(int) arrayList.size()];
		tempArray = arrayList.toArray(tempArray);
		
		return tempArray;
	}
	
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		DoCSVFileCheck operation=new DoCSVFileCheck();
		String arr[][]=operation.CSVFileCheck("E:/java/fileForTest/m1.csv");
		int[] flag={0, 0};
		AddNewColumnTwo a=new AddNewColumnTwo();
		String infixString=(char)200+"\"merit\" + 40 + 4 + \"marks\" + \"roll number\" - 44 + 44 x .5 "+(char)200;
		String arr1[][]=a.doAdding(arr,infixString+"^ is your score  ^"+infixString, "Edited Column", flag);
		//String arr1[][]=a.doAdding(arr, "","Edited Column", flag);
		for(int i=0;i<arr1.length;i++){
			for(int j=0;j<arr1[0].length;j++){
				System.out.print(arr1[i][j]+" | ");
			}
			
			System.out.println();
		}
		
//		String infix="\"roll number\"40+\"merit\"+50+60+\"marks\"+3.5";
//		String arr1[]=a.divideInfix("40+3.5");
//		
//		for(int i=0;i<arr1.length;i++)
//			System.out.print(arr1[i]+" ");
		
		//System.out.println();
		
//		int arr2[]=a.identifyTheIndexOfInfixArrayWhoHasColumnName(arr1);
//		for(int i=0;i<arr2.length;i++)
//			System.out.print(arr2[i]+" ");
//		String string="";
//		for(int i=0;i<arr.length;i++){
//			string=string+arr[i][0];
//			if(i!=arr.length-1)
//				string=string+",";
//		}
		//System.out.println("tttt "+string);
//		int arr3[]=a.identifyTheColumnNumberOfInFoFile(string, arr1, arr2);
//		for(int i=0;i<arr3.length;i++)
//			System.out.print(arr3[i]+" ");
		
//		String[] arr=a.splitStringBaseOnPower("ishmam^shahriar^turzo");
//		
//		for(int i=0;i<arr.length;i++)
//			System.out.println(arr[i]);
		
		
	}
}
