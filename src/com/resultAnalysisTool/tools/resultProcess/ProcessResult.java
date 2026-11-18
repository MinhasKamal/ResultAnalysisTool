/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: --2014																							*
* Comment: 	*									
* 		*
* 		*
*************************************************************************************************************/

package com.resultAnalysisTool.tools.resultProcess;


import java.awt.Font;

import javax.swing.*;

import com.resultAnalysisTool.util.fileReadWrite.FileIO;
import com.resultAnalysisTool.util.message.Message;

@SuppressWarnings("serial")
public class ProcessResult extends JFrame {
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private JLabel jLabelMain;
	
	private JLabel jLabelMainAnsFile;
	private JTextField jTFieldMainAnsFile;
	
	private JLabel jLabelPrompt;
	
	private JCheckBox[] jCheckBoxFields;
	
	private JButton jButtonStepUp, jButtonStepDown;
	
	//other variables
	private String TAIFilePath, CSDMainAnswerFilePath, DBFStudentFilePath;
	private String[] fieldNames;
	private boolean[] isParameterField, isSubjectField;
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##
	 * @throws Exception ***/
	public ProcessResult(String TAIFilePath, String CSDMainAnswerFilePath, String DBFStudentFilePath) throws Exception {
		this.TAIFilePath = TAIFilePath;
		this.CSDMainAnswerFilePath = CSDMainAnswerFilePath;
		this.DBFStudentFilePath = DBFStudentFilePath;
		
		this.fieldNames = FileIO.readSpecificFileLine(this.TAIFilePath, 0).split(",");
		
		isParameterField = new boolean[fieldNames.length];
		isSubjectField = new boolean[fieldNames.length];

		initialComponent(); 
	}

	
	/**
	 * Method for Initializing all the GUI variables, placing them all to specific space on the frame and adding action
	 * listener to them. Also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		//**
		// Initialization 																					#*******I*******#
		//**
		jLabelMain = new JLabel();
		
		jLabelMainAnsFile = new JLabel();
		jTFieldMainAnsFile = new JTextField();
		
		jLabelPrompt = new JLabel();
		
		jCheckBoxFields = new JCheckBox[fieldNames.length];
		
		jButtonStepUp = new JButton();
		jButtonStepDown = new JButton();
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		jLabelMain.setBounds(0, 0, 500, 430);
		jLabelMain.setLayout(null);
		
		jLabelMainAnsFile.setBounds(15, 10, 120, 30);
		jLabelMainAnsFile.setText("Main Answer File");
		jLabelMainAnsFile.setFont(new Font("Arial", 0, 14));
		
		jTFieldMainAnsFile.setBounds(150, 10, 330, 30);
		jTFieldMainAnsFile.setEditable(false);
		jTFieldMainAnsFile.setText(TAIFilePath.substring(TAIFilePath.lastIndexOf("\\")+1));
		jTFieldMainAnsFile.setFont(new Font("Arial", 0, 14));
		
		jLabelPrompt.setBounds(15, 60, 300, 30);
		jLabelPrompt.setText("Select Parameters from the list");
		jLabelPrompt.setFont(new java.awt.Font("Arial", 0, 14));

		for(int i=0, j=0; i<fieldNames.length; i++){
			jCheckBoxFields[i] = new JCheckBox();
			jCheckBoxFields[i].setBounds(15+j*155, 90+((i%10)*25), 150, 30);
			jCheckBoxFields[i].setText(fieldNames[i]);
			if((i+1)%10==0) j++;
		}
		
		jButtonStepUp.setBounds(330, 355, 70, 30);
		jButtonStepUp.setText("Next >");
		jButtonStepUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jButtonActionPerformed(evt);
            }
        });
		
		jButtonStepDown.setBounds(410, 355, 70, 30);
		jButtonStepDown.setText("Cancel");
		jButtonStepDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jButtonActionPerformed(evt);
            }
        });
		// End of Setting Bounds and Attributes 															#_______S_______#

		//**
		// Adding Components 																				#*******A*******#
		//**
		jLabelMain.add(jLabelMainAnsFile);
		jLabelMain.add(jTFieldMainAnsFile);
		
		jLabelMain.add(jLabelPrompt);
		
		for(int i=0; i<fieldNames.length; i++){
			jLabelMain.add(jCheckBoxFields[i]);
		}
		
		jLabelMain.add(jButtonStepUp);
		jLabelMain.add(jButtonStepDown);
		// End of Adding Components 																		#_______A_______#

		//**Setting Criterion of the Frame**//
		setIconImage(new ImageIcon(getClass().getResource("")).getImage());
		setBounds(200, 140, 500, 430);
		setTitle("Result Process");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		add(jLabelMain);
		setVisible(true);
	}

	//**
	// Action Events 																						#********AE*******#
	//**
	private void jButtonActionPerformed(java.awt.event.ActionEvent evt){
		if(evt.getActionCommand().equals("Next >")){
			jButtonStepUp.setText("OK");
			jButtonStepDown.setText("< Back");
			jLabelPrompt.setText("Select subject field from the list");
			
			int index=0;
			for(JCheckBox jCheckBox: jCheckBoxFields){	//disable selected check box items
				if(jCheckBox.isSelected()){
					isParameterField[index]=true;
					jCheckBox.setEnabled(false);
				}else{
					isParameterField[index]=false;
				}
				index++;
			}
		}else if(evt.getActionCommand().equals("Cancel")){
			dispose();
		}else if(evt.getActionCommand().equals("< Back")){
			jButtonStepUp.setText("Next >");
			jButtonStepDown.setText("Cancle");
			jLabelPrompt.setText("Select parameter field from the list");
			
			for(JCheckBox jCheckBox: jCheckBoxFields){	//enable all check box items
				jCheckBox.setEnabled(true);
			}
		}else{
			int index=0;
			for(JCheckBox jCheckBox: jCheckBoxFields){	//disable selected check box items
				if(jCheckBox.isSelected() && jCheckBox.isEnabled()){
					isSubjectField[index]=true;
					jCheckBox.setEnabled(false);
				}else{
					isSubjectField[index]=false;
				}
				index++;
			}
			
			//object method call
			try {
				System.out.println(TAIFilePath+"|"+CSDMainAnswerFilePath+"|"+DBFStudentFilePath);
				for(int i=0; i<isParameterField.length; i++){
					System.out.println(isParameterField[i] +"|"+ isSubjectField[i] );
				}
				
				new ProcessResultOpt(TAIFilePath, CSDMainAnswerFilePath, DBFStudentFilePath,
						isParameterField, isSubjectField);
				
			} catch (Exception e) {
				new Message("Error!", 420);
			}
			
			dispose();
		}
	}
	// End of Action Events 																				#________AE_______#

	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create and display the form */
		//String[] fileNames = {"TestSubject/MainAnswer.dat.csv", "TestSubject/CandidateAnswer.dat"};
		String TAIFilePath = "C:/Users/admin/Desktop/OMR/OMR.csd_tableDesign.tai";
		String CSDMainAnswerFilePath = "C:/Users/admin/Desktop/MainAnswer.csd";
		String DBFStudentFilePath = "C:/Users/admin/Desktop/MainAnswer.dbf";

		try {
			new ProcessResult(TAIFilePath, CSDMainAnswerFilePath, DBFStudentFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//**
	// Auxiliary Methods 																					#********AM*******#
	//**
	
	// End of Auxiliary Methods 																			#________AM_______#
}
