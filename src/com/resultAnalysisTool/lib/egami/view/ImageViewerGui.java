/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 28-08-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.lib.egami.view;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;


/**
 * 
 * 
 * @author Minhas Kamal
 */
@SuppressWarnings("serial")
public class ImageViewerGui extends JLabel {
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JLabel jLabel;
	JScrollPane jScrollPane;
	
	JButton[] jButtonsZoom;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public ImageViewerGui() {

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
		jLabel = new JLabel();
		jScrollPane = new JScrollPane();
		
		jButtonsZoom = new JButton[2];
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jLabel.setHorizontalAlignment(0);
		jLabel.setVerticalAlignment(0);
		jLabel.setLayout(null);
		
		jScrollPane.setViewportView(jLabel);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		for(int i=0; i<2; i++){
			jButtonsZoom[i] = new JButton();
			jButtonsZoom[i].setBounds(i*20+5, 5, 20, 20);
			jButtonsZoom[i].setBorder(null);
			jButtonsZoom[i].setFont(new Font("Times New Roman", 0, 18));
		}
		jButtonsZoom[0].setText("+");
		jButtonsZoom[1].setText("-");
		
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Label**//
		setLayout(new GridLayout());
		
		//**
		// Adding Components 																	#*******A*******#
		//**
		add(jScrollPane);
		
		jLabel.add(jButtonsZoom[0]);
		jLabel.add(jButtonsZoom[1]);
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
		ImageViewerGui gui = new ImageViewerGui();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 10, 950, 700);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.add(gui);
	}
}
