/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 09-Jun-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.dataAnalyse.tableDefine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.resultAnalysisTool.util.fileReadWrite.FileIO;
import com.resultAnalysisTool.util.message.Message;


/**
 * @author Minhas Kamal
 */
public class TableAttributeDefiner{
	// GUI Declaration
	private TableAttributeDefinerGui gui;
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	private JTextField jTextFieldFullPart;
	private JSlider jSliderRangeSelect;
	
	private JTextField jTextFieldSelectedPart;
	private JTextField jTextFieldCharCounter;
	
	@SuppressWarnings("rawtypes")
	private JComboBox jComboBoxFieldName;
	private JCheckBox jCheckBoxIsAnswer;
	
	private JButton jButtonOK;
	private JButton jButtonCancel;
	
	//other variables
	private int sliderValue =0,
			trackPosition =0;
	
	private String mainString = "";
	private String filePath = "";
	
	private Vector<String>  rawFieldNames= new Vector<String>() ;
	private Vector<Integer> rawEndPositions = new Vector<Integer>();
	private Vector<Boolean> rawAnswerFlags = new Vector<Boolean>();
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public TableAttributeDefiner(String filePath) {
		this.filePath = filePath;
		try {
			this.mainString = getMainString(FileIO.readSpecificFileLine(filePath, 1));
			
		} catch (Exception e) {
			new Message("Error reading file.", 420);
			return ;
		}
		
		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new TableAttributeDefinerGui();
		gui.setVisible(true);
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jTextFieldFullPart = gui.jTextFieldFullPart;
		jSliderRangeSelect = gui.jSliderRangeSelect;
		
		jTextFieldSelectedPart = gui.jTextFieldSelectedPart;
		jTextFieldCharCounter = gui.jTextFieldCharCounter;
		
		jComboBoxFieldName = gui.jComboBoxFieldName;
		jCheckBoxIsAnswer = gui.jCheckBoxIsAnswer;
		
		jButtonOK = gui.jButtonOK;
		jButtonCancel = gui.jButtonCancel;
		// End of Assignation																	#_______A_______#

		jTextFieldFullPart.setText(mainString);
		
		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jSliderRangeSelect.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				jSliderRangeSelectStateChanged();
			}
		});
		
		jButtonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButtonOKActionPerformed(evt);
			}
		});
		
		jButtonCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				jButtonCancelActionPerformed(evt);
			}
		});
		
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	private void jSliderRangeSelectStateChanged(){
		sliderValue = jSliderRangeSelect.getValue();

		if(sliderValue > mainString.length()){
			sliderValue = mainString.length();
		}

		jTextFieldCharCounter.setText(Integer.toString(sliderValue));
		jTextFieldSelectedPart.setText(mainString.substring(0, sliderValue));
	}
	
	private void jButtonCancelActionPerformed(ActionEvent evt){
		gui.dispose();
	}
	

	private void jButtonOKActionPerformed(ActionEvent evt) {

		if (jComboBoxFieldName.getSelectedItem().toString().equals("")) {
			new Message("Field name is blank", 210);
			return ;
		} else if (jSliderRangeSelect.getValue() == 0) {
			new Message("Counter field is Zero", 210);
			return ;
		} else if (isDuplicateFieldName()){
			new Message("Duplicated field name", 210);
			return ;
		}else if(jComboBoxFieldName.getSelectedItem().toString().contains(",") ||
				jComboBoxFieldName.getSelectedItem().toString().contains(";")){
			new Message("Invalid character(, or ;)", 210);
			return ;
		}
		
		//////////////////////////////////////////////////////////////////////////
		trackPosition += sliderValue;
		
		rawFieldNames.add(jComboBoxFieldName.getSelectedItem().toString());
		rawEndPositions.add(trackPosition);
		rawAnswerFlags.add(jCheckBoxIsAnswer.isSelected());

		mainString = mainString.substring(sliderValue);
		jSliderRangeSelect.setValue(0);
		jTextFieldFullPart.setText(mainString);
		jTextFieldFullPart.setCaretPosition(0);

		///////////////////////////////////////////////////////////////////////////
		if (mainString.length() == 0) {

			String[] fieldNames = new String[rawFieldNames.size()];
			Integer[] endPositions = new Integer[rawFieldNames.size()];
			Boolean[] answerFlags = new Boolean[rawFieldNames.size()];

			int emptyNumber = 1;
			for (int i=0; i < rawFieldNames.size(); i++) {
				if (rawFieldNames.get(i).equals("Empty")) {
					fieldNames[i] = rawFieldNames.get(i) + emptyNumber;
					emptyNumber++;
				} else {
					fieldNames[i] = rawFieldNames.get(i);
				}
				endPositions[i] = rawEndPositions.get(i);
				answerFlags[i] = rawAnswerFlags.get(i);
			}

			new AnswerValueDefiner(filePath, fieldNames, endPositions, answerFlags).setVisible(true);

			gui.dispose();
		}
	
		return ;
	}

	
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	private String getMainString(String string){
		String mainString = "";
		String[] stringParts = string.substring(0, string.lastIndexOf('&')-1).split(",");
		for(String stringPart: stringParts){
			mainString += stringPart.charAt(0);
		}
		
		return mainString;
	}
	
	/**
	 * Checks the duplicates field names except "Empty" 
	 * @return true if duplicated field is found 
	 * @return false if duplicated field isn't found
	 */
	private boolean isDuplicateFieldName(){
		int size = rawFieldNames.size();
		String temp_String = jComboBoxFieldName.getSelectedItem().toString(); 
		for(int i=0; i<size ; i++ ){
			if(temp_String.equals(rawFieldNames.elementAt(i)) && ! ("Empty".equals(rawFieldNames.elementAt(i)))){
				return true;
			}
		}
		
		return false;
	}
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
		new TableAttributeDefiner("C:\\Users\\admin\\Desktop\\OMR\\OMR_Sheetimg.csd");
	}
}
