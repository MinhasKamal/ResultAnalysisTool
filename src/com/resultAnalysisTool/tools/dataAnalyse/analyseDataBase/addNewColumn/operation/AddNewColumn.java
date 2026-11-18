/**
 * Developer : Shahriar Mohammed Ishmam BSSE 0523 IIT,University Of Dhaka
 * Date : 28-7-2014
 * 
 * 
 * comment : works under 'AddNewColumnTwo' class
 */
package com.resultAnalysisTool.tools.dataAnalyse.analyseDataBase.addNewColumn.operation;

import java.util.ArrayList;
import java.util.Iterator;

public class AddNewColumn {
	
	//public final static int INFIX_EVALUATION = 0;
	
	/**
	 * 
	 * @param fileInfo Two Dimensional String array,that contains file's content........array[column][row]
	 * @param infix if flag[0]==0...then this string have to be evaluated
	 * @param newField A string that is the name of new field
	 * @param flag an array of integer, if flag[0]==0,then it will be infix evolution,if flag[0]==1 then empty column,flag[0]=2 for 
	 * numerical,flag[0]==3 will be alphabetic ,flag[1]==range of increment......
	 * @return a one dimensional array that contains a new column
	 */

	public String[] doAdding(String[][] fileInfo,String infix,String newField,int[] flag){
		int fileInfoLength=fileInfo.length;
		int fileInfoWidth=fileInfo[0].length;
		String[] infixEvaluatedResult=new String[fileInfoWidth]; //it will contain the evaluated column
		
				
			
		//	String splitedByPowerSign[]=splitStringBaseOnPower(infix);
			
			
				
			infixEvaluatedResult[0]=newField;//inserting new field name
			AdvancedCalculatorOperationsExecutor prefixEve=new AdvancedCalculatorOperationsExecutor();
			
			
			String[] dividedInfixArray=divideInfix(infix);
			//divide the infix to separate column name in infix...4 example...."30*2+"HSC"+40x"SSC"....    
			//dividedInfixArray[0]=30*2+   dividedInfixArray[1]="HSC"    dividedInfixArray[2]=40x   dividedInfixArray[3]="SSC"
			
			int[] infixIndexArray=identifyTheIndexOfInfixArrayWhoHasColumnName(dividedInfixArray);
			//it have the index of dividedInfixArray who has column name..in the previous comment it is.. 1,3
			
			
			String fieldsNameString="";
			for(int i=0;i<fileInfoLength;i++){//all fields' name in a String excluding new Filed
				fieldsNameString=fieldsNameString+fileInfo[i][0];
				if(i!=fileInfoLength-1)
					fieldsNameString=fieldsNameString+',';
			}
			
			int[] columnIndexArray=identifyTheColumnNumberOfInFoFile(fieldsNameString,dividedInfixArray , infixIndexArray);
			//identify the index which columns in two dim. array have task with infix
			
			
			if(dividedInfixArray.length==1){//when the infix has no field name..so we don't have to divide it..4 e. 30*4+2/2-30
				for(int i=1;i<fileInfoWidth;i++){
					infixEvaluatedResult[i]=prefixEve.infixEvaluation(infix);
				}	
			}
			
			else{//when...infix has both arithmetic command and column name ... 4 e. 30*"HHS"+3-7/3+"SSC"
				int k;
				boolean flagOne;
				for(int i=1;i<fileInfoWidth;i++){//for all row in two dim. array
					k=0;
					infix="";
					flagOne=true;
					for(int j=0;j<dividedInfixArray.length;j++){//to replace column name with its' value 4 e. 30*"HHC"=30*40
						if(flagOne && j==infixIndexArray[k]){//when infixIndexArray[k]=column name
							
							//System.out.println("f "+dividedInfixArray.length);
								infix=infix+fileInfo[columnIndexArray[k]][i];//add fields' name equivalent info
								k++;
								if(k>=infixIndexArray.length)//when...k is equal or big than column number
									flagOne=false;
							
							
						}
						else {
							infix=infix+dividedInfixArray[j];//if there is no column name in dividedInfixArray[] ,we add directly
						}
						
					}
					//System.out.println(infix);	
					infixEvaluatedResult[i]=prefixEve.infixEvaluation(infix);//get the result of infix
				
			}
		}
		
		return infixEvaluatedResult;
		
	}
	
	/**
	 * Take a string and divide it  to separate column name in infix...   
	 *
	 * @param infix a String....4 ex. "30*2+"HSC"+40x"SSC"
	 * @return a String array.. 4 ex. dividedInfixArray[0]=30*2+   dividedInfixArray[1]="HSC"   
	 *  dividedInfixArray[2]=40x   dividedInfixArray[3]="SSC"
	 */
	
	
	
	private String[] divideInfix(String infix){
		ArrayList<String> listToDevideInfix=new ArrayList<String>();
		int infixLength=infix.length();
		boolean flag=true;
		
		String tempString="";
		int counter=0;
		
		for(int i=0;i<infixLength;i++){
			if(infix.charAt(i)!='"')
				tempString=tempString+infix.charAt(i);
			
			if(infix.charAt(i)=='"'){
				counter++;
				if(counter%2==0)
					flag=true;
			}
			
			if((infix.charAt(i)=='"' || i==infix.length()-1) && tempString!=""){
				if(counter%2==0 && flag && counter!=0){
					listToDevideInfix.add('"'+tempString+'"');
					flag=false;
				}
				else
					listToDevideInfix.add(tempString);
				tempString="";
			}	
		}
		
		String[] tempArray = new String[(int) listToDevideInfix.size()];
		tempArray = listToDevideInfix.toArray(tempArray);
		
		return tempArray;
	
	}
	
	/**
	 * Identify the index of array that contains a column name
	 * @param dividedInfixArray A String Array that contains divided infix
	 * @return integer array that contains index of column in <code>dividedInfixArray<code>
	 */
	
	
	private int[] identifyTheIndexOfInfixArrayWhoHasColumnName(String[] dividedInfixArray){
		ArrayList<Integer> listToFindIndex=new ArrayList<Integer>();
		
		int dividedInfixArrayLength=dividedInfixArray.length;
		
		for(int i=0;i<dividedInfixArrayLength;i++){
			
			if(dividedInfixArray[i].charAt(0)=='"')//as columnname is identified by ...."columnName"
				listToFindIndex.add(i);
			
			
		}
		
		return convertIntegers(listToFindIndex) ;
		
	}
	
	
	/**
	 * Identify the column index of two dim. array who has task with infix..4 example
	 *  4*"HSC"..it finds..the column index of HSC in two dim. array
	 * @param fieldsNameString all field name in a String
	 * @param devidedInfix A string array that contains division of infix
	 * @param array an integer array that contains index of devidedInfix[] that has column name
	 * @return the column index of two dim. array who has task with infix
	 */
	
	private int[] identifyTheColumnNumberOfInFoFile(String fieldsNameString,String[] devidedInfix,int[] array){
		ArrayList<Integer> indexArrayList=new ArrayList<Integer>();
		String[] fieldsNmaeArray=splitStringBasedOnComma(fieldsNameString);

		int arrayLength=array.length;
		
		int fieldsNmaeArrayLength=fieldsNmaeArray.length;
		for(int i=0;i<arrayLength;i++){
			for(int j=0;j<fieldsNmaeArrayLength;j++){
				if(devidedInfix[array[i]].equals(fieldsNmaeArray[j])){
					indexArrayList.add(j);
					break;
				}
			}
		}
		return convertIntegers(indexArrayList) ;
		
	}
	
	/**
	 * It is valid only 4 this class cause of..... string2='"'+string2+'"';
	 * Split a string on basis of comma
	 * @param unSplitedString A String
	 * @return String array
	 */
private String[] splitStringBasedOnComma(String unSplitedString){
		
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
					string2='"'+string2+'"';
					arrayList.add(string2);
					
				}
				string2="";	
			}
		}
		String[] tempArray = new String[(int) arrayList.size()];
		tempArray = arrayList.toArray(tempArray);
		
		return tempArray;
	}

/**
 * convert an integer arrayList to integer array
 * @param integers Integer ArrayList
 * @return Integer Array
 */
private static int[] convertIntegers(ArrayList<Integer> integers)
{
    int[] ret = new int[integers.size()];
    Iterator<Integer> iterator = integers.iterator();
    for (int i = 0; i < ret.length; i++)
    {
        ret[i] = iterator.next().intValue();
    }
    return ret;
}






	
	public static void main(String[] args) throws Exception {
		DoCSVFileCheck operation=new DoCSVFileCheck();
		String arr[][]=operation.CSVFileCheck("E:/java/fileForTest/m1.csv");
		int[] flag={0, 1};
		AddNewColumn a=new AddNewColumn();
		//String arr1[][]=a.doAdding(arr, "\"merit\" + 40 + 4 + \"marks\" + \"roll number\" - 44 + 44 x .5 ", "Edited Column", flag);
		String arr1[]=a.doAdding(arr, "\"merit\" + 40 + 4 + \"marks\" + \"roll number\" - 44 + 44 x .5 ", "Edited Column", flag);
		for(int i=0;i<arr1.length;i++){
			
				System.out.print(arr1[i]+" | ");
			
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
