/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 07-Jun-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.util.fontChoose;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;


/**
 * @author Minhas Kamal
 */
public class FontChooser{
	// GUI Declaration
	private FontChooserGui gui;
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	private TextFont textFont;
	
	@SuppressWarnings("rawtypes")
	private JComboBox jComboBoxFontFace;
	
	private JToggleButton[] jTButtonFontStyle;
	
	@SuppressWarnings("rawtypes")
	private JComboBox jComboBoxFontSize;
	
	private JButton jButtonTextColor;
	
	private JButton jButtonTextBackgroundColor;
	
	private JToggleButton[] jTButtonAlignment;
	
	private JTextField jTextFieldSample;
	
	private JButton jButtonOk;
	private JButton jButtonCancle;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public FontChooser(TextFont textFont) {

		this.textFont = textFont;
		initialComponent();
		setSampleTextDefaultFont();
		
		gui.setModal(true);
		gui.setVisible(true);
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new FontChooserGui();
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jComboBoxFontFace = gui.jComboBoxFontFace;
		jTButtonFontStyle = gui.jTButtonFontStyle;
		jComboBoxFontSize = gui.jComboBoxFontSize;
		jButtonTextColor = gui.jButtonTextColor;
		jButtonTextBackgroundColor = gui.jButtonTextBackgroundColor;
		jTButtonAlignment = gui.jTButtonAlignment;
		jTextFieldSample = gui.jTextFieldSample;
		jButtonOk = gui.jButtonOk;
		jButtonCancle = gui.jButtonCancle;
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jComboBoxFontFace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jComboBoxFontFaceActionPerformed(evt);
			}
		});
		
		jTButtonFontStyle[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jTButtonFontStyle0ActionPerformed(evt);
			}
		});
		jTButtonFontStyle[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jTButtonFontStyle1ActionPerformed(evt);
			}
		});
		
		jComboBoxFontSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jComboBoxFontSizeActionPerformed(evt);
			}
		});
		
		jButtonTextColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButtonTextColorActionPerformed(evt);
			}
		});
		
		jButtonTextBackgroundColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButtonTextBackgroundColorActionPerformed(evt);
			}
		});
		
		for(int i=0; i<3; i++){
			jTButtonAlignment[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jTButtonAlignmentActionPerformed(evt);
				}
			});
		}
		
		jButtonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButtonOkActionPerformed(evt);
			}
		});
		
		jButtonCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButtonCancleActionPerformed(evt);
			}
		});
		
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	private void jComboBoxFontFaceActionPerformed(ActionEvent evt){
		String fontFace = jComboBoxFontFace.getSelectedItem().toString();
		int fontStyle = jTextFieldSample.getFont().getStyle();
		int fontSize = jTextFieldSample.getFont().getSize();
		Font newFont = new Font(fontFace, fontStyle, fontSize);
		
		jTextFieldSample.setFont(newFont);
		textFont.fontType = newFont;
	}
		
	private void jTButtonFontStyle0ActionPerformed(ActionEvent evt){
		if(jTButtonFontStyle[0].isSelected()){
			if(jTButtonFontStyle[1].isSelected()){
				jTextFieldSample.setFont(jTextFieldSample.getFont().deriveFont(Font.BOLD + Font.ITALIC));
			}else{
				jTextFieldSample.setFont(jTextFieldSample.getFont().deriveFont(Font.BOLD));
			}
		}else{
			jTextFieldSample.setFont(jTextFieldSample.getFont().deriveFont
					(jTextFieldSample.getFont().getStyle()-Font.BOLD));
		}
		
		textFont.fontType = jTextFieldSample.getFont();
	}
	
	private void jTButtonFontStyle1ActionPerformed(ActionEvent evt){
		if(jTButtonFontStyle[1].isSelected()){
			if(jTButtonFontStyle[0].isSelected()){
				jTextFieldSample.setFont(jTextFieldSample.getFont().deriveFont(Font.BOLD + Font.ITALIC));
			}else{
				jTextFieldSample.setFont(jTextFieldSample.getFont().deriveFont(Font.ITALIC));
			}
		}else{
			jTextFieldSample.setFont(jTextFieldSample.getFont().deriveFont
					(jTextFieldSample.getFont().getStyle()-Font.ITALIC));
		}
		
		textFont.fontType = jTextFieldSample.getFont();
	}
	
	private void jComboBoxFontSizeActionPerformed(ActionEvent evt){
		String fontFace = jTextFieldSample.getFont().getFamily();
		int fontStyle = jTextFieldSample.getFont().getStyle();
		int fontSize = Integer.parseInt(jComboBoxFontSize.getSelectedItem().toString());
		Font newFont = new Font(fontFace, fontStyle, fontSize);
		
		jTextFieldSample.setFont(newFont);
		textFont.fontType = newFont;
	}
	
	private void jButtonTextColorActionPerformed(ActionEvent evt){
		Color color = JColorChooser.showDialog(this.gui, "Choose color", Color.white);
		jButtonTextColor.setBackground(color);
		jTextFieldSample.setForeground(color);
		
		textFont.textColor = color;
	}
	
	private void jButtonTextBackgroundColorActionPerformed(ActionEvent evt){
		Color color = JColorChooser.showDialog(this.gui, "Choose color", Color.white);
		jButtonTextBackgroundColor.setBackground(color);
		jTextFieldSample.setBackground(color);
		
		textFont.backgroundColor = color;
	}
	
	private void jTButtonAlignmentActionPerformed(ActionEvent evt){
		for(int i=0; i<3; i++){
			if(jTButtonAlignment[i] == evt.getSource()){
				jTButtonAlignment[i].setSelected(true);
				continue;
			}
			
			jTButtonAlignment[i].setSelected(false);
		}
		
		if(jTButtonAlignment[0].isSelected()){
			jTextFieldSample.setHorizontalAlignment(JTextField.LEFT);
			textFont.textAlign = JTextField.LEFT;
		}else if(jTButtonAlignment[1].isSelected()){
			jTextFieldSample.setHorizontalAlignment(JTextField.CENTER);
			textFont.textAlign = JTextField.CENTER;
		}else if(jTButtonAlignment[2].isSelected()){
			jTextFieldSample.setHorizontalAlignment(JTextField.RIGHT);
			textFont.textAlign = JTextField.RIGHT;
		}
	}
	
	private void jButtonOkActionPerformed(ActionEvent evt){
		
		
		gui.dispose();
	}
	
	private void jButtonCancleActionPerformed(ActionEvent evt){
		gui.dispose();
	}
	

	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	private void setSampleTextDefaultFont(){
		jComboBoxFontFace.setSelectedItem(textFont.fontType.getFamily());
		if(textFont.fontType.isBold()){
			jTButtonFontStyle[0].setSelected(true);
		}if(textFont.fontType.isItalic()){
			jTButtonFontStyle[1].setSelected(true);
		}
		jTextFieldSample.setFont(textFont.fontType);
		
		jButtonTextColor.setBackground(textFont.textColor);
		jTextFieldSample.setForeground(textFont.textColor);
		
		jButtonTextBackgroundColor.setBackground(textFont.backgroundColor);
		jTextFieldSample.setBackground(textFont.backgroundColor);
		
		if(textFont.textAlign == JTextField.LEFT){
			jTButtonAlignment[0].setSelected(true);
		}else if(textFont.textAlign == JTextField.CENTER){
			jTButtonAlignment[1].setSelected(true);
		}else if(textFont.textAlign == JTextField.RIGHT){
			jTButtonAlignment[2].setSelected(true);
		}
		jTextFieldSample.setHorizontalAlignment(textFont.textAlign);
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
		new FontChooser(new TextFont(new Font(Font.DIALOG, 3, 20), Color.BLACK, Color.GRAY, JTextField.CENTER));
	}
}
