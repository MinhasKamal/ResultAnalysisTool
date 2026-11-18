/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 15-Jun-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.reportPrint;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;


/**
 * 
 * 
 * @author Minhas Kamal
 */
@SuppressWarnings("serial")
public class ReportPrinterGui extends JFrame {
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JPanel jPanelMain;
	
	JLabel jLabelInputFile;
	JTextField jTextFieldInputFile;
	JButton jButtonInputFile;
	
	JButton jButtonPrint;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public ReportPrinterGui() {

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
		jPanelMain = new JPanel();
		
		jLabelInputFile = new JLabel("*Select file:");
		jTextFieldInputFile = new JTextField();
		jButtonInputFile = new JButton("...");
		
		jButtonPrint = new JButton();
		
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jPanelMain.setBackground(Color.white);
		jPanelMain.setLayout(null);
		
		
		jLabelInputFile.setBounds(15, 30, 140, 30);
		jLabelInputFile.setFont(new Font("Arial", 0, 14));
		
		jTextFieldInputFile.setBounds(160, 30, 250, 30);
		jTextFieldInputFile.setFont(new Font("Arial", 0, 13));
		jTextFieldInputFile.setEditable(false);
		
		jButtonInputFile.setBounds(420, 30, 30, 30);
		jButtonInputFile.setFont(new Font("Arial", 1, 15));
		
		
		jButtonPrint.setBounds(380, 90, 70, 30);
		jButtonPrint.setText("Print");
		
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Frame**//
		setIconImage(new ImageIcon(getClass().getResource("")).getImage());
		setBounds(200, 100, 470, 160);
		setTitle("Image Data Extractor");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		//**
		// Adding Components 																	#*******A*******#
		//**
		jPanelMain.add(jLabelInputFile);
		jPanelMain.add(jTextFieldInputFile);
		jPanelMain.add(jButtonInputFile);
		
		jPanelMain.add(jButtonPrint);
		
		add(jPanelMain);
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
		ReportPrinterGui gui = new ReportPrinterGui();
		gui.setVisible(true);
	}
}
