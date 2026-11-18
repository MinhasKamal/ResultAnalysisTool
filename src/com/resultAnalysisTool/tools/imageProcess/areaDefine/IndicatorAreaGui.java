/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 04-Jan-2015																								*
* Date: 25-Jan-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.imageProcess.areaDefine;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.resultAnalysisTool.lib.areator.GraphAreaGui;


/**
 * 
 * 
 * @author Minhas Kamal
 */
@SuppressWarnings("serial")
public class IndicatorAreaGui extends GraphAreaGui{
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public IndicatorAreaGui() {

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
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jTButtonProperty.setText("");
		
		jPMenuProperty.setPopupSize(235, 105);
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Label**//
		setBorder(BorderFactory.createLineBorder(Color.black, 1, false));

		//**
		// Adding Components 																	#*******A*******#
		//**
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
		IndicatorAreaGui gui = new IndicatorAreaGui();
		gui.setBounds(20, 20, 220, 60);
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 10, 350, 200);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(null);
		jFrame.add(gui);
	}
}
