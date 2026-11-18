/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 15-Jun-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.fileEncode;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;


/**
 * 
 * 
 * @author Minhas Kamal
 */
@SuppressWarnings("serial")
public class FileEncoderGui extends JFrame {
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JPanel jPanelMain;
	
	JLabel jLabelInputFile;
	JTextField jTextFieldInputFile;
	JButton jButtonInputFile;
	
	JLabel jLabelOutputFile;
	JTextField jTextFieldOutputFile;
	
	JLabel jLabelPassword;
	JPasswordField jPasswordFieldPassword;
	
	JButton jButtonOK;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public FileEncoderGui() {

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
		
		jLabelInputFile = new JLabel("*Select input file:");
		jTextFieldInputFile = new JTextField();
		jButtonInputFile = new JButton("...");
		
		jLabelOutputFile = new JLabel("Output file:");
		jTextFieldOutputFile = new JTextField();
		
		jLabelPassword = new JLabel("Enter password:");
		jPasswordFieldPassword = new JPasswordField();
		
		jButtonOK = new JButton();
		
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jPanelMain.setBackground(Color.white);
		jPanelMain.setLayout(null);
		
		
		jLabelInputFile.setBounds(15, 20, 140, 30);
		jLabelInputFile.setFont(new Font("Arial", 0, 14));
		
		jTextFieldInputFile.setBounds(160, 20, 250, 30);
		jTextFieldInputFile.setFont(new Font("Arial", 0, 13));
		jTextFieldInputFile.setEditable(false);
		
		jButtonInputFile.setBounds(420, 20, 30, 30);
		jButtonInputFile.setFont(new Font("Arial", 1, 15));
		
		
		jLabelOutputFile.setBounds(15, 60, 140, 30);
		jLabelOutputFile.setFont(new Font("Arial", 0, 14));
		
		jTextFieldOutputFile.setBounds(160, 60, 250, 30);
		jTextFieldOutputFile.setFont(new Font("Arial", 0, 13));
		jTextFieldOutputFile.setEditable(false);
		
		jLabelPassword.setBounds(15, 100, 140, 30);
		jLabelPassword.setFont(new Font("Arial", 0, 14));
		
		jPasswordFieldPassword.setBounds(160, 100, 250, 30);
		jPasswordFieldPassword.setFont(new Font("Arial", 0, 13));
		
		
		jButtonOK.setBounds(380, 150, 70, 30);
		jButtonOK.setText("OK");
		
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Frame**//
		setIconImage(new ImageIcon(getClass().getResource("")).getImage());
		setBounds(200, 100, 470, 220);
		setTitle("Image Data Extractor");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		//**
		// Adding Components 																	#*******A*******#
		//**
		jPanelMain.add(jLabelInputFile);
		jPanelMain.add(jTextFieldInputFile);
		jPanelMain.add(jButtonInputFile);
		
		jPanelMain.add(jLabelOutputFile);
		jPanelMain.add(jTextFieldOutputFile);
		
		jPanelMain.add(jLabelPassword);
		jPanelMain.add(jPasswordFieldPassword);
		
		jPanelMain.add(jButtonOK);
		
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
		FileEncoderGui gui = new FileEncoderGui();
		gui.setVisible(true);
	}
}
