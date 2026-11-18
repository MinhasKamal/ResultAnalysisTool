/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 21-Jan-2015																								*
* Modified: 16-May-2015																							*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.reportCreate.reportDesign;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.resultAnalysisTool.lib.areator.GraphArea;
import com.resultAnalysisTool.lib.areator.GraphAreaGui;
import com.resultAnalysisTool.util.fontChoose.FontChooser;
import com.resultAnalysisTool.util.fontChoose.TextFont;


/**
 * 
 * 
 * @author Minhas Kamal
 */
public class TextArea extends GraphArea{
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	private TextFont textFont;
	
	private JTextField jTextField;
	
	private JButton jButtonChangeFont;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public TextArea(int x, int y, int width, int height) {
		super(x, y, width, height);
		textFont = new TextFont(new Font(Font.SERIF, Font.PLAIN, 15), Color.BLACK, Color.WHITE, JTextField.CENTER);
		
		initialComponent();
		setSampleTextDefaultFont();
		
		relocateJTBProperty();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jTextField = ((TextAreaGui) gui).jTextField;
		
		jButtonChangeFont = ((TextAreaGui) gui).jButtonChangeFont;
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		
		jButtonChangeFont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButtonChangeFontActionPerformed(evt);
			}
		});
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	
	private void jButtonChangeFontActionPerformed(ActionEvent evt){
		new FontChooser(textFont);
		setSampleTextDefaultFont();
	}
	
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	public void changeFont(String fontFace, int fontStyle, int fontSize){
		jTextField.setFont(new Font(fontFace, fontStyle, fontSize));
	}
	
	public void changeFontFace(String fontFace) {
		int fontStyle = jTextField.getFont().getStyle();
		int fontSize = jTextField.getFont().getSize();
		
		changeFont(fontFace, fontStyle, fontSize);
	}
	
	public void changeFontStyle(int fontStyle) {
		String fontFace = jTextField.getFont().getFontName();
		int fontSize = jTextField.getFont().getSize();
		
		changeFont(fontFace, fontStyle, fontSize);
	}
	
	public void changeFontSize(int fontSize) {
		String fontFace = jTextField.getFont().getFontName();
		int fontStyle = jTextField.getFont().getStyle();
		
		changeFont(fontFace, fontStyle, fontSize);
	}
	
	public void changeFontColor(int r, int g, int b, int a) {
		jTextField.setForeground(new Color(r, g, b, a));
	}
	
	public void changeBackgroundColor(int r, int g, int b, int a){
		jTextField.setBackground(new Color(r, g, b, a));
	}
	
	public void changeTextAlignment(int textAlignment){
		jTextField.setAlignmentX(textAlignment);
	}
	
	@Override
	protected GraphAreaGui getGui(){
		return new TextAreaGui();
	}
	
	@Override
	public void resize(int direction, int amount) {
		super.resize(direction, amount);
		
		((TextAreaGui)gui).jTextField.setSize(gui.getSize());
		
		gui.repaint();
	}
	
	@Override
	protected void relocateJTBProperty(){
		jTButtonProperty.setLocation(width-jTBPropertySide-3, 0);
		
		gui.repaint();
	}
	
	private void setSampleTextDefaultFont(){
		jTextField.setFont(textFont.fontType);
		jTextField.setForeground(textFont.textColor);
		jTextField.setBackground(textFont.backgroundColor);
		jTextField.setHorizontalAlignment(textFont.textAlign);
	}
	// End of Auxiliary Methods 																#_______AM_______#
	
	//**
	// Unimplemented Methods 																	#*******UM*******#
	//**
	
	// End of Unimplemented Methods 															#_______UM_______#
	
	
	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create */
		TextArea textArea = new TextArea(20, 30, 300, 80);
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(40, 30, 400, 200);
		jFrame.setVisible(true);
		jFrame.setLayout(null);
		jFrame.add(textArea.gui);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
