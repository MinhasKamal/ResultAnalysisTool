/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 25-Jan-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.lib.areator;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.UIManager;


/**
 * 
 * 
 * @author Minhas Kamal
 */
@SuppressWarnings("serial")
public class GraphPainterGui extends AbstractGraphPainterGui {
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JToggleButton jTButtonGraphArea;
	
	Cursor cursorGraphArea;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public GraphPainterGui() {
		super();
		
		initialComponent();
	}
	
	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		//**
		// Initialization 																		#*******I*******#
		//**
		jTButtonGraphArea = new JToggleButton();
		
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jTButtonGraphArea.setText("Graph Area");
		jTButtonGraphArea.setBackground(new Color(210, 200, 200));
		jTButtonGraphArea.setFont(new Font("Arial", 0, 14));
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		cursorGraphArea = toolkit.createCustomCursor(toolkit.createImage(getClass().
				getResource("/res/img/cursor/GraphAreaCursor.png")),
				new Point(16, 16), "graphArea");
		
		// End of Setting Bounds and Attributes 												#_______S_______#

		//**
		// Adding Components 																	#*******A*******#
		//**
		jLabelGraphArea.add(jTButtonGraphArea, "w 50:120:130");
		
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
		AbstractGraphPainterGui gui = new GraphPainterGui();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 10, 950, 700);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.add(gui);
	}
}
