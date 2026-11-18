/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 15-Jun-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.reportPrint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.resultAnalysisTool.util.fileChoose.FileChooser;
import com.resultAnalysisTool.util.filePrint.FilePrinter;
import com.resultAnalysisTool.util.message.Message;


/**
 * 
 * 
 * @author Minhas Kamal
 */
public class ReportPrinter{
	// GUI Declaration
	private ReportPrinterGui gui;
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	private JTextField jTextFieldInputFile;
	private JButton jButtonInputFile;
	
	private JButton jButtonPrint;
	
	//other variables
	private String inputFilePath;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public ReportPrinter() {

		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new ReportPrinterGui();
		gui.setVisible(true);
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jTextFieldInputFile = gui.jTextFieldInputFile;
		jButtonInputFile = gui.jButtonInputFile;
		
		jButtonPrint = gui.jButtonPrint;
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jButtonInputFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonInputFileActionPerformed(evt);
            }
        });
		
		jButtonPrint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonPrintActionPerformed(evt);
            }
        });
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	private void jButtonInputFileActionPerformed(ActionEvent evt){
		this.inputFilePath = new FileChooser(new String[]{"pdf"}).chooseFilePathFromComputer();
		if(!inputFilePath.contains(".")){
			return ;
		}
		
		jTextFieldInputFile.setText(
				inputFilePath.substring(inputFilePath.lastIndexOf('\\')+1, inputFilePath.length()));
	}
	
	private void jButtonPrintActionPerformed(ActionEvent evt){
		if(!jTextFieldInputFile.getText().isEmpty()){
			
			new FilePrinter().printFile(inputFilePath);
			
			new Message("File printing complete.", 1);
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
		new ReportPrinter();
	}
}
