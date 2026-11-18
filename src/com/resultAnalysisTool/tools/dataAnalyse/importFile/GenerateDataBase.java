/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 10-Jun-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.dataAnalyse.importFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.resultAnalysisTool.util.fileChoose.FileChooser;
import com.resultAnalysisTool.util.message.Message;


/**
 * @author Minhas Kamal
 */
public class GenerateDataBase{
	// GUI Declaration
	private GenerateDataBaseGui gui;
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	private JTextField jTextFieldTabAttrInfo;
	private JButton jButtonSelectTabAttrInfo;
	
	private JTextField jTextFieldSelectCommaSepData;
	private JButton jButtonSelectCommaSepData;
	
	private JButton jButtonOK;
	
	//other variables
	private String tabAttrInfoFilePath="";
	private String commaSepDataFilePath="";
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public GenerateDataBase() {

		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new GenerateDataBaseGui();
		gui.setVisible(true);
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jTextFieldTabAttrInfo = gui.jTextFieldTabAttrInfo;
		jButtonSelectTabAttrInfo = gui.jButtonSelectTabAttrInfo;
		
		jTextFieldSelectCommaSepData = gui.jTextFieldSelectCommaSepData;
		jButtonSelectCommaSepData = gui.jButtonSelectCommaSepData;
		
		jButtonOK = gui.jButtonOK;
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jButtonSelectTabAttrInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonSelectTabAttrInfoActionPerformed(evt);
            }
        });
		
		jButtonSelectCommaSepData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonSelectCommaSepDataActionPerformed(evt);
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
	private void jButtonSelectTabAttrInfoActionPerformed(ActionEvent evt){
		this.tabAttrInfoFilePath = new FileChooser(new String[]{"tai"}).chooseFilePathFromComputer();
		if(!tabAttrInfoFilePath.contains(".")){
			return ;
		}
		
		jTextFieldTabAttrInfo.setText(tabAttrInfoFilePath.substring(
				tabAttrInfoFilePath.lastIndexOf('\\')+1, tabAttrInfoFilePath.length()));
	}
	
	private void jButtonSelectCommaSepDataActionPerformed(ActionEvent evt){
		this.commaSepDataFilePath = new FileChooser(new String[]{"csd"}).chooseFilePathFromComputer();
		if(!commaSepDataFilePath.contains(".")){
			return ;
		}
		
		jTextFieldSelectCommaSepData.setText(commaSepDataFilePath.substring(
				commaSepDataFilePath.lastIndexOf('\\')+1, commaSepDataFilePath.length()));
	}
	
	private void jButtonOKActionPerformed(ActionEvent evt){
		if(tabAttrInfoFilePath!="" && commaSepDataFilePath!=""){
			try {
				new GenerateDatabaseOpt(tabAttrInfoFilePath, commaSepDataFilePath, commaSepDataFilePath+"_imp.dbf");
				new Message("Operation Successful.\n *.DBF file is made.", 1);
			} catch (Exception e) {
				new Message("Error!", 420);
			}
			gui.dispose();
		}else{
			new Message("Please fill up required fields", 210);
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
		new GenerateDataBase();
	}
}
