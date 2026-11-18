/**
 * Developer: Shahriar Mohammed Ishmam (BSSE0523, IIT, DU)
 * Date: 21-May-2014
 * Comment: 
 */


package com.resultAnalysisTool.tools.dataAnalyse.analyseDataBase.searching.operation;

import com.resultAnalysisTool.util.fileReadWrite.FileIO;

public class DoMultipleSearching {
	
	/**
	 * Takes file path as a string of array ,a field as String where it will search and a string what it will search
	 * @param arrayOfFilePath Array of String containing file path
	 * @param baseOfSearch Field as String where it will search
	 * @param whatToSearch	What it will be searched
	 * @return Returns 0 if file is written after all file being searched successfully if error occurs,returns -420
	 * @throws Exception 
	 */
	
	public int multipleSearching(String[] arrayOfFilePath,String baseOfSearch, String whatToSearch) throws Exception{
		String previousFileSearchedData="";
		
		int lengthOfArrayOfFilePath=arrayOfFilePath.length;
		
		DoSearching ds=new DoSearching();
		
		
		for(int i=0;i<lengthOfArrayOfFilePath;i++){
			
			previousFileSearchedData=ds.partialStringSearchMethodOne(arrayOfFilePath[i], baseOfSearch, whatToSearch, previousFileSearchedData);//pass each fileName and save the sorted data 
			if(previousFileSearchedData.equals("error"))
				return -420;
		}
		//System.out.println(previousFileSearchedData);///test

		
		FileIO.writeWholeFile(arrayOfFilePath[0]+"Searched.csv", previousFileSearchedData);
		
		return 0;
	}
	/**///only for test
	public static void main(String[] args) throws Exception {
		DoMultipleSearching dmfr=new DoMultipleSearching();
		
		String arr[]={"D:/java/fileForTest/f1.csv","D:/java/fileForTest/f2.csv","D:/java/fileForTest/m1.csv"};
		
		dmfr.multipleSearching(arr,"roll number" , "7");

	}
	/**/
}
