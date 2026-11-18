/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 16-Jan-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.resultProcess;

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
public class ProcessResultSelectFilesGui extends JFrame {
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JPanel jPanelMain;
	
	JLabel jLabelSelectTAI;
	JTextField jTextFieldSelectTAI;
	JButton jButtonSelectTAI;
	
	JLabel jLabelSelectCSD;
	JTextField jTextFieldSelectCSD;
	JButton jButtonSelectCSD;
	
	JLabel jLabelSelectDBF;
	JTextField jTextFieldSelectDBF;
	JButton jButtonSelectDBF;
	
	JButton jButtonProcess;
	
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public ProcessResultSelectFilesGui() {

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
		
		jLabelSelectTAI = new JLabel("*Select *.TAI file:");
		jTextFieldSelectTAI = new JTextField();
		jButtonSelectTAI = new JButton("...");
		
		jLabelSelectCSD = new JLabel("*Select *.CSD file:");
		jTextFieldSelectCSD = new JTextField();
		jButtonSelectCSD = new JButton("...");
		
		jLabelSelectDBF = new JLabel("*Select *.DBF file:");
		jTextFieldSelectDBF = new JTextField();
		jButtonSelectDBF = new JButton("...");
		
		jButtonProcess = new JButton();
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jPanelMain.setBackground(Color.white);
		jPanelMain.setLayout(null);
		
		
		jLabelSelectTAI.setBounds(15, 30, 140, 30);
		jLabelSelectTAI.setFont(new Font("Arial", 0, 14));
		
		jTextFieldSelectTAI.setBounds(160, 30, 250, 30);
		jTextFieldSelectTAI.setFont(new Font("Arial", 0, 13));
		jTextFieldSelectTAI.setEditable(false);
		
		jButtonSelectTAI.setBounds(420, 30, 30, 30);
		jButtonSelectTAI.setFont(new Font("Arial", 1, 15));
		
		
		jLabelSelectCSD.setBounds(15, 70, 140, 30);
		jLabelSelectCSD.setFont(new Font("Arial", 0, 14));
		
		jTextFieldSelectCSD.setBounds(160, 70, 250, 30);
		jTextFieldSelectCSD.setFont(new Font("Arial", 0, 13));
		jTextFieldSelectCSD.setEditable(false);
		
		jButtonSelectCSD.setBounds(420, 70, 30, 30);
		jButtonSelectCSD.setFont(new Font("Arial", 1, 15));

		
		jLabelSelectDBF.setBounds(15, 110, 140, 30);
		jLabelSelectDBF.setFont(new Font("Arial", 0, 14));
		
		jTextFieldSelectDBF.setBounds(160, 110, 250, 30);
		jTextFieldSelectDBF.setFont(new Font("Arial", 0, 13));
		jTextFieldSelectDBF.setEditable(false);
		
		jButtonSelectDBF.setBounds(420, 110, 30, 30);
		jButtonSelectDBF.setFont(new Font("Arial", 1, 15));
		
		jButtonProcess.setBounds(360, 160, 90, 30);
		jButtonProcess.setText("Next");
		
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Frame**//
		setIconImage(new ImageIcon(getClass().getResource("")).getImage());
		setBounds(200, 100, 470, 250);
		setTitle("Image Data Extractor");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		//**
		// Adding Components 																	#*******A*******#
		//**
		jPanelMain.add(jLabelSelectTAI);
		jPanelMain.add(jTextFieldSelectTAI);
		jPanelMain.add(jButtonSelectTAI);
		
		jPanelMain.add(jLabelSelectCSD);
		jPanelMain.add(jTextFieldSelectCSD);
		jPanelMain.add(jButtonSelectCSD);
		
		jPanelMain.add(jLabelSelectDBF);
		jPanelMain.add(jTextFieldSelectDBF);
		jPanelMain.add(jButtonSelectDBF);
		
		jPanelMain.add(jButtonProcess);
		
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
		ProcessResultSelectFilesGui gui = new ProcessResultSelectFilesGui();
		gui.setVisible(true);
	}
}
