/**
 * Developer: Mostaque Ahmed(BSSE-0530, IIT, DU) & Minhas Kamal(BSSE-0509, IIT, DU)
 * Date: 2-Apr-2014
 * Comment:	This class can print any kind of file.
**/


package com.resultAnalysisTool.util.filePrint;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.resultAnalysisTool.util.message.Message;

public class FilePrinter {
	public int printFile(String filePath){
		try {
			Desktop desktop = null;
			if (Desktop.isDesktopSupported()) {
				desktop = Desktop.getDesktop();
			}
			desktop.print(new File(filePath));
		} catch (IOException e) {
			new Message("File not found or unsupported!", 420);
			return 420;
		}catch (Exception e) {
			new Message("Error!", 420);
			return 421;
		}
		
		return 0;
	}
	
	/**///only for test
	public static void main(String[] args) {
		String FileName = "C:/Users/admin/Desktop/Temporary/Project View/MainAnswer.dat";
		
		FilePrinter operation = new FilePrinter();
		operation.printFile(FileName);
	}
	/**/
}
