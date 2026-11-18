/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 18-Mar-2015																								*
****************************************************************************************************************/


package com.resultAnalysisTool.util.fileCompress;

import java.io.File;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import net.lingala.zip4j.core.ZipFile;


public class Compressor {
	public void zip(String targetFolderPath, String destinationFilePath, String password){
		try {
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			if(password.length()>0){
				parameters.setEncryptFiles(true);
	            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
	            parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
	            
	            parameters.setPassword(password);
			}
			
			ZipFile zipFile = new ZipFile(destinationFilePath);
			
			File targetFile = new File(targetFolderPath);
			if(targetFile.isFile()){
				zipFile.addFile(targetFile, parameters);
			}else if(targetFile.isDirectory()){
				zipFile.addFolder(targetFile, parameters);
			}else{
				//show error message
			}
			
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
    
	public void unzip(String targetZipFilePath, String destinationFilePath, String password){
	    try {
	         ZipFile zipFile = new ZipFile(targetZipFilePath);
	         if (zipFile.isEncrypted()) {
	            zipFile.setPassword(password);
	         }
	         zipFile.extractAll(destinationFilePath.substring(0, destinationFilePath.lastIndexOf('\\')));
	    } catch (ZipException e) {
	        e.printStackTrace();
	    }
	}
	
	//test only
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String targetFolderPath = "C:\\Users\\admin\\Desktop\\New Microsoft Office Word Document.docx";
		String zipFilePath = "C:\\Users\\admin\\Desktop\\Result_Univ.zip";
		String destinationFolderPath = "C:\\Users\\admin\\Desktop\\Result_Univ_Unzipped";
		String password = "as";
		
		new Compressor().zip(targetFolderPath, zipFilePath, password);
		//unzip(zipFilePath, destinationFolderPath, password);
	}
}
