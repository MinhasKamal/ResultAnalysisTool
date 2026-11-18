/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 19-Jan-2015																								*
* Modified: 17-May-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.reportCreate.reportDesign;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import com.resultAnalysisTool.lib.areator.AbstractGraphPainterGui;


/**
 * 
 * 
 * @author Minhas Kamal
 */
@SuppressWarnings("serial")
public class ReportDesignerGui extends AbstractGraphPainterGui {
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JToggleButton jTButtonTextArea;
	JToggleButton jTButtonImageArea;
	JToggleButton jTButtonTableArea;
	
	Cursor cursorTextArea;
	Cursor cursorImageArea;
	Cursor cursorTableArea;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public ReportDesignerGui() {
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
		jTButtonTextArea = new JToggleButton();
		jTButtonImageArea = new JToggleButton();
		jTButtonTableArea = new JToggleButton();
		
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jTButtonTextArea.setText("Text Area");
		jTButtonTextArea.setBackground(new Color(210, 200, 200));
		jTButtonTextArea.setFont(new Font("Arial", 0, 14));
		
		jTButtonImageArea.setText("Image Area");
		jTButtonImageArea.setBackground(new Color(210, 200, 200));
		jTButtonImageArea.setFont(new Font("Arial", 0, 14));
		
		jTButtonTableArea.setText("Table Area");
		jTButtonTableArea.setBackground(new Color(210, 200, 200));
		jTButtonTableArea.setFont(new Font("Arial", 0, 14));
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		toolkit.getBestCursorSize(10, 10);
		cursorTextArea = toolkit.createCustomCursor(toolkit.createImage(getClass().
				getResource("/res/img/cursor/TextAreaCursor.png")), new Point(16, 16), "TextArea");
		cursorImageArea = toolkit.createCustomCursor(toolkit.createImage(getClass().
				getResource("/res/img/cursor/ImageAreaCursor.png")), new Point(16, 16), "ImageArea");
		cursorTableArea = toolkit.createCustomCursor(toolkit.createImage(getClass().
				getResource("/res/img/cursor/TableAreaCursor.png")), new Point(16, 16), "TableArea");
		
		// End of Setting Bounds and Attributes 												#_______S_______#

		//**
		// Adding Components 																	#*******A*******#
		//**
		jLabelGraphArea.add(jTButtonTextArea, "w 50:120:130");
		jLabelGraphArea.add(jTButtonImageArea, "w 50:120:130");
		jLabelGraphArea.add(jTButtonTableArea, "w 50:120:130");
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
		ReportDesignerGui gui = new ReportDesignerGui();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 10, 950, 700);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.add(gui);
		
	}
}
