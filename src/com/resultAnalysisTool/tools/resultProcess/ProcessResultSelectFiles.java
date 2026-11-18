/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 16-Jun-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.resultProcess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.resultAnalysisTool.util.fileChoose.FileChooser;
import com.resultAnalysisTool.util.message.Message;


/**
 * 
 * 
 * @author Minhas Kamal
 */
public class ProcessResultSelectFiles{
	// GUI Declaration
	private ProcessResultSelectFilesGui gui;
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	private JTextField jTextFieldSelectTAI;
	private JButton jButtonSelectTAI;
	
	private JTextField jTextFieldSelectCSD;
	private JButton jButtonSelectCSD;
	
	private JTextField jTextFieldSelectDBF;
	private JButton jButtonSelectDBF;
	
	private JButton jButtonProcess;
	
	//other variables
	String TAIFilePath, CSDFilePath, DBFFilePath;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public ProcessResultSelectFiles() {

		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new ProcessResultSelectFilesGui();
		gui.setVisible(true);
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jTextFieldSelectTAI = gui.jTextFieldSelectTAI;
		jButtonSelectTAI = gui.jButtonSelectTAI;
		
		jTextFieldSelectCSD = gui.jTextFieldSelectCSD;
		jButtonSelectCSD = gui.jButtonSelectCSD;
		
		jTextFieldSelectDBF = gui.jTextFieldSelectDBF;
		jButtonSelectDBF = gui.jButtonSelectDBF;
		
		jButtonProcess = gui.jButtonProcess;
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jButtonSelectTAI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonSelectTAIActionPerformed(evt);
            }
        });
		jButtonSelectCSD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonSelectCSDActionPerformed(evt);
            }
        });
		jButtonSelectDBF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonSelectDBFActionPerformed(evt);
            }
        });
		jButtonProcess.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonProcessActionPerformed(evt);
            }
        });
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	private void jButtonSelectTAIActionPerformed(ActionEvent evt){
		this.TAIFilePath = new FileChooser(new String[]{"tai"}).chooseFilePathFromComputer();
		if(!TAIFilePath.contains(".")){
			return ;
		}
		
		jTextFieldSelectTAI.setText(TAIFilePath.substring(
				TAIFilePath.lastIndexOf('\\')+1, TAIFilePath.length()));
	}
	
	private void jButtonSelectCSDActionPerformed(ActionEvent evt){
		this.CSDFilePath = new FileChooser(new String[]{"csd"}).chooseFilePathFromComputer();
		if(!CSDFilePath.contains(".")){
			return ;
		}
		
		jTextFieldSelectCSD.setText(CSDFilePath.substring(
				CSDFilePath.lastIndexOf('\\')+1, CSDFilePath.length()));
	}
	
	private void jButtonSelectDBFActionPerformed(ActionEvent evt){
		this.DBFFilePath = new FileChooser(new String[]{"dbf"}).chooseFilePathFromComputer();
		if(!DBFFilePath.contains(".")){
			return ;
		}
		
		jTextFieldSelectDBF.setText(DBFFilePath.substring(
				DBFFilePath.lastIndexOf('\\')+1, DBFFilePath.length()));
	}
	
	private void jButtonProcessActionPerformed(ActionEvent evt){
		try {
			if(!jTextFieldSelectTAI.getText().isEmpty() && !jTextFieldSelectCSD.getText().isEmpty()
					&& !jTextFieldSelectDBF.getText().isEmpty()){
				
				new ProcessResult(TAIFilePath, CSDFilePath, DBFFilePath);
				gui.dispose();
			}else{
				new Message("Please fill up all the fields.", 210);
			}
		} catch (Exception e) {
			new Message("Error.", 420);
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
		new ProcessResultSelectFiles();
	}
}
