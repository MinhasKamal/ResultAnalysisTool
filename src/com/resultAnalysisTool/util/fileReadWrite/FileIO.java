/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 02-Feb-2014																								*
* Modified: 31-Dec-2014																							*
****************************************************************************************************************/

package com.resultAnalysisTool.util.fileReadWrite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Contains file read & file write static operations.
 *
 * @author Minhas Kamal
 */
public class FileIO {
	/**
	 * Takes a file path & returns whole content of the file. Used for reading external
	 * files.
	 * @param filePath input file path
	 * @return full information of the file
	 * @throws Exception 
	 */
	public static String readWholeFile(String filePath) throws Exception{
		
		byte[] bytes = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(new File(filePath).toURI()));
		String Information = new String(bytes);	//contains the full file
			
		return Information; 
	}
	
	/**
	 * Takes a input stream & returns whole content of the file. Mainly used for reading
	 * files inside jar.
	 * @param filePath input file path
	 * @return full information of the file
	 * @throws Exception 
	 */
	public static String readWholeFile(InputStream inputStream) throws Exception{
		String string = "";	//for temporary data store
		String Information = "";	//#contains the full file 
		
		BufferedReader mainBR = new BufferedReader(new InputStreamReader(inputStream));
		
		string = mainBR.readLine();
		while(string!=null){	//reading step by step
			Information += string + "\n"; 
			
			string = mainBR.readLine();
		}
		
		mainBR.close();
		
		return Information;
	}
	
	/**
	 * Takes a file name, line number & returns specific line of the file 
	 * @param FileName
	 * @param lineNumber starts from 1
	 * @return
	 * @throws Exception 
	 */
	public static String readSpecificFileLine(String FileName, int lineNumber) throws Exception{
		String string = "";
		File mainFile = new File(FileName);
		
		
		/*file reading*/
		BufferedReader mainBR = new BufferedReader(new FileReader(mainFile));	//making buffered reader object
		
		string = mainBR.readLine();
		for(int i=1; i<lineNumber && string!=null; i++){
			string = mainBR.readLine();
		}
		
		mainBR.close();
		return string; 
	}
	
	/**
	 * Takes file path and information to be written as string and writes the whole string to the file at once.
	 * If file name already exists over-writes it, else creates new file. 
	 * @param FileName full file path
	 * @param Information information to be written
	 * @throws Exception 
	 */
	public static void writeWholeFile(String FileName, String Information) throws Exception{
		
		BufferedWriter mainBW = new BufferedWriter(new FileWriter(FileName));
		mainBW.write(Information);
		mainBW.close();
			
		return ;
	}
}
