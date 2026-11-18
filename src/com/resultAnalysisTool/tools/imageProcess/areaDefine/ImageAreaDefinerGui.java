/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 30-Dec-2014																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.imageProcess.areaDefine;

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
public class ImageAreaDefinerGui extends AbstractGraphPainterGui {
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JToggleButton jTButtonIndicatorArea;
	JToggleButton jTButtonOptionArea;
	
	//JToggleButton[] jTButtonOpAreaType;
	
	Cursor cursorIndicatorArea;
	Cursor cursorOptionArea;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public ImageAreaDefinerGui() {
		
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
		jTButtonIndicatorArea = new JToggleButton();
		jTButtonOptionArea = new JToggleButton();
		
//		jTButtonOpAreaType = new JToggleButton[2];
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		
		//jTButtonIndicatorArea.setBounds(10, 50, 120, 40);
		jTButtonIndicatorArea.setText("Indicator Area");
		jTButtonIndicatorArea.setBackground(new Color(210, 200, 200));
		jTButtonIndicatorArea.setFont(new Font("Arial", 0, 14));
		
		//jTButtonOptionArea.setBounds(10, 100, 120, 40);
		jTButtonOptionArea.setText("Option Area");
		jTButtonOptionArea.setBackground(new Color(210, 200, 200));
		jTButtonOptionArea.setFont(new Font("Arial", 0, 14));
		
//		for(int i=0; i<2; i++){
//			jTButtonOpAreaType[i] = new JToggleButton();
//			jTButtonOpAreaType[i].setBounds(5+i*50, 60, 50, 20);
//			jTButtonOpAreaType[i].setFont(new Font("Arial", 0, 11));
//		}
//		jTButtonOpAreaType[0].setText("Alp");
//		jTButtonOpAreaType[1].setText("Num");
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		cursorIndicatorArea = toolkit.createCustomCursor(toolkit.createImage(getClass().
				getResource("/res/img/cursor/IndicatorAreaCursor.png")), new Point(16, 16), "indicatorArea");
		cursorOptionArea = toolkit.createCustomCursor(toolkit.createImage(getClass().
				getResource("/res/img/cursor/OptionAreaCursor.png")), new Point(16, 16), "optionArea");
		
		//**Setting Criterion of the Label**//
		// End of Setting Bounds and Attributes 												#_______S_______#

		//**
		// Adding Components 																	#*******A*******#
		//**
		jLabelGraphArea.add(jTButtonIndicatorArea, "w 100:150:200");
		jLabelGraphArea.add(jTButtonOptionArea, "w 100:150:200");
		
//		jLabelOthers.add(jTButtonOpAreaType[0]);
//		jLabelOthers.add(jTButtonOpAreaType[1]);

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
		/* Create and display the form */
		ImageAreaDefinerGui gui = new ImageAreaDefinerGui();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 10, 950, 700);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.add(gui);
		
	}
}
