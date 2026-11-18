/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 10-Jun-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.dataAnalyse.importFile;

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
 * @author Minhas Kamal
 */
@SuppressWarnings("serial")
public class GenerateDataBaseGui extends JFrame {
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JPanel jPanelMain;
	
	JLabel jLabelSelectTabAttrInfo;
	JTextField jTextFieldTabAttrInfo;
	JButton jButtonSelectTabAttrInfo;
	
	JLabel jLabelSelectCommaSepData;
	JTextField jTextFieldSelectCommaSepData;
	JButton jButtonSelectCommaSepData;
	
	JButton jButtonOK;
	
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public GenerateDataBaseGui() {

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
		
		jLabelSelectTabAttrInfo = new JLabel("Select *.TAI file:");
		jTextFieldTabAttrInfo = new JTextField();
		jButtonSelectTabAttrInfo = new JButton("...");
		
		jLabelSelectCommaSepData = new JLabel("Select *.CSD file:");
		jTextFieldSelectCommaSepData = new JTextField();
		jButtonSelectCommaSepData = new JButton("...");
		
		jButtonOK = new JButton();
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jPanelMain.setBackground(Color.white);
		jPanelMain.setLayout(null);
		
		
		jLabelSelectTabAttrInfo.setBounds(15, 30, 140, 30);
		jLabelSelectTabAttrInfo.setFont(new Font("Arial", 0, 14));
		
		jTextFieldTabAttrInfo.setBounds(130, 30, 280, 30);
		jTextFieldTabAttrInfo.setFont(new Font("Arial", 0, 13));
		jTextFieldTabAttrInfo.setEditable(false);
		
		jButtonSelectTabAttrInfo.setBounds(420, 30, 30, 30);
		jButtonSelectTabAttrInfo.setFont(new Font("Arial", 1, 15));

		
		jLabelSelectCommaSepData.setBounds(15, 80, 140, 30);
		jLabelSelectCommaSepData.setFont(new Font("Arial", 0, 14));
		
		jTextFieldSelectCommaSepData.setBounds(130, 80, 280, 30);
		jTextFieldSelectCommaSepData.setFont(new Font("Arial", 0, 13));
		jTextFieldSelectCommaSepData.setEditable(false);
		
		jButtonSelectCommaSepData.setBounds(420, 80, 30, 30);
		jButtonSelectCommaSepData.setFont(new Font("Arial", 1, 15));
		
		
		jButtonOK.setBounds(380, 140, 70, 30);
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
		jPanelMain.add(jLabelSelectTabAttrInfo);
		jPanelMain.add(jTextFieldTabAttrInfo);
		jPanelMain.add(jButtonSelectTabAttrInfo);
		
		jPanelMain.add(jLabelSelectCommaSepData);
		jPanelMain.add(jTextFieldSelectCommaSepData);
		jPanelMain.add(jButtonSelectCommaSepData);
		
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
		GenerateDataBaseGui gui = new GenerateDataBaseGui();
		gui.setVisible(true);
	}
}
