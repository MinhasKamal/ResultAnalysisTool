/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 15-Jun-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.fileEncode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.resultAnalysisTool.util.fileChoose.FileChooser;
import com.resultAnalysisTool.util.fileCompress.Compressor;
import com.resultAnalysisTool.util.message.Message;


/**
 * 
 * 
 * @author Minhas Kamal
 */
public class FileEncoder{
	// GUI Declaration
	private FileEncoderGui gui;
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	private JTextField jTextFieldInputFile;
	private JButton jButtonInputFile;
	
	private JTextField jTextFieldOutputFile;
	
	private JPasswordField jPasswordFieldPassword;
	
	private JButton jButtonOK;
	
	//other variables
	private String inputFilePath;
	private String outputFilePath;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public FileEncoder() {

		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new FileEncoderGui();
		gui.setVisible(true);
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jTextFieldInputFile = gui.jTextFieldInputFile;
		jButtonInputFile = gui.jButtonInputFile;
		
		jTextFieldOutputFile = gui.jTextFieldOutputFile;
		
		jPasswordFieldPassword = gui.jPasswordFieldPassword;
		
		jButtonOK = gui.jButtonOK;
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jButtonInputFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonInputFileActionPerformed(evt);
            }
        });
		
		jButtonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonOKActionPerformed(evt);
            }
        });
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	private void jButtonInputFileActionPerformed(ActionEvent evt){
		this.inputFilePath = new FileChooser().chooseFilePathFromComputer();
		if(!inputFilePath.contains(".")){
			return ;
		}
		
		jTextFieldInputFile.setText(
				inputFilePath.substring(inputFilePath.lastIndexOf('\\')+1, inputFilePath.length()));
		
		if(inputFilePath.charAt(inputFilePath.length()-1) == '!'){
			outputFilePath = inputFilePath.substring(0, inputFilePath.length()-1);
		}else{
			outputFilePath = inputFilePath+'!';
		}
		
		jTextFieldOutputFile.setText(
				outputFilePath.substring(outputFilePath.lastIndexOf('\\')+1, outputFilePath.length()));
	}
	
	private void jButtonOKActionPerformed(ActionEvent evt){
		if(!jTextFieldInputFile.getText().isEmpty() && !jTextFieldOutputFile.getText().isEmpty()){
			String password = "master_password";
			if(jPasswordFieldPassword.getPassword().length==0){
				password = new String(jPasswordFieldPassword.getPassword());
			}
			
			if(inputFilePath.length() < outputFilePath.length()){
				new Compressor().zip(inputFilePath, outputFilePath, password);
				new Message("File is encoded.", 1);
			}else{
				new Compressor().unzip(inputFilePath, outputFilePath, password);
				new Message("File is decoded", 1);
			}
			
			new File(inputFilePath).delete();
			
		}else{
			new Message("Please select input file.", 210);
		}
	}
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	
	// End of Auxiliary Methods 																#_______AM_______#
	
	//**
	// Overridden Methods 																		#*******OM*******#
	//**
	
	// End of Overridden Methods 																#_______OM_______#
	
	
	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create */
		new FileEncoder();
	}
}
