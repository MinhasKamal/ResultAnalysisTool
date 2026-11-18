/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 19-Jan-2015																								*
* Modified: 16-May-2015																							*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.reportCreate.reportDesign;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.resultAnalysisTool.lib.areator.GraphAreaGui;


/**
 * 
 * 
 * @author Minhas Kamal
 */
@SuppressWarnings("serial")
public class TextAreaGui extends GraphAreaGui {
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JTextField jTextField;
	
	JButton jButtonChangeFont;
	
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public TextAreaGui() {

		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the component. It also specifies criteria of the main component.
	 */
	private void initialComponent() {
		//**
		// Initialization 																		#*******I*******#
		//**
		jTextField = new JTextField();
		
		jButtonChangeFont = new JButton();
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jTButtonProperty.setText("T");
		
		jTextField.setBorder(null);
		jTextField.setLayout(null);
		
		jButtonChangeFont.setText("Change Font");
		jButtonChangeFont.setSize(217, 20);
		jButtonChangeFont.setLocation(9, 95);
		
		jPMenuProperty.setPopupSize(235, 130);
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Label**//
		setLayout(new GridLayout());

		//**
		// Adding Components 																	#*******A*******#
		//**
		jLabelPopupMenu.remove(jLabelSpecial);
		jLabelPopupMenu.add(jButtonChangeFont);
		
		remove(jTButtonProperty);
		
		jTextField.add(jTButtonProperty);
		add(jTextField);
		
		// End of Adding Components 															#_______A_______#
	}

	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create and display the form */
		TextAreaGui gui = new TextAreaGui();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 10, 350, 200);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(null);
		jFrame.add(gui);
		gui.setBounds(20, 30, 300, 80);
	}
}
