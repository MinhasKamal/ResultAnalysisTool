/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 04-Jan-2015																								*
* Date: 25-Jan-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.imageProcess.areaDefine;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import com.resultAnalysisTool.lib.areator.GraphAreaGui;


/**
 * 
 * 
 * @author Minhas Kamal
 */
@SuppressWarnings("serial")
public class OptionAreaGui extends GraphAreaGui{
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	Queue<JComponent> jComponentPartitions;
	
	JButton[] jButtonPartitions;
	JToggleButton[] jTButtonOpAreaType;
	
	JButton[] jButtonsMinDarkArea;
	JTextField jTextFieldMinDarkArea;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public OptionAreaGui() {

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
		jComponentPartitions = new LinkedList<JComponent>();
		
		jButtonPartitions = new JButton[3];
		jTButtonOpAreaType = new JToggleButton[2];
		
		jButtonsMinDarkArea = new JButton[2];
		jTextFieldMinDarkArea = new JTextField();
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jTButtonProperty.setText("");
		
		jLabelSpecial.setBorder(BorderFactory.createTitledBorder("Partition"));
		
		for(int i=0; i<3; i++){
			jButtonPartitions[i] = new JButton();
			jButtonPartitions[i].setBounds(10, i*20+20, 90, 20);
			jButtonPartitions[i].setFont(new Font("Arial", 0, 11));
			jButtonPartitions[i].setBorder(null);
		}
		jButtonPartitions[0].setText("Increase");
		jButtonPartitions[1].setText("Change Type");
		jButtonPartitions[2].setText("Decrease");
		
		for(int i=0; i<2; i++){
			jTButtonOpAreaType[i] = new JToggleButton();
			jTButtonOpAreaType[i].setBounds(125+i*45, 20, 45, 20);
			jTButtonOpAreaType[i].setFont(new Font("Arial", 0, 11));
			jTButtonOpAreaType[i].setBorder(null);
		}
		jTButtonOpAreaType[0].setText("Alp");
		jTButtonOpAreaType[1].setText("Num");
		
		for(int i=0; i<2; i++){
			jButtonsMinDarkArea[i] = new JButton();
			jButtonsMinDarkArea[i].setBounds(125+i*70, 40, 20, 20);
			jButtonsMinDarkArea[i].setFont(new Font("Arial", 1, 11));
			jButtonsMinDarkArea[i].setBorder(null);
		}
		jButtonsMinDarkArea[0].setText("<");
		jButtonsMinDarkArea[1].setText(">");
		
		jTextFieldMinDarkArea.setBounds(140, 40, 60, 20);
		jTextFieldMinDarkArea.setEditable(false);
		jTextFieldMinDarkArea.setBackground(Color.LIGHT_GRAY);
		jTextFieldMinDarkArea.setHorizontalAlignment(JTextField.CENTER);
		jTextFieldMinDarkArea.setFont(new Font("Arial", 0, 10));
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Label**//
		setBorder(BorderFactory.createLineBorder(Color.black, 1, false));

		//**
		// Adding Components 																	#*******A*******#
		//**
		for(int i=0; i<3; i++){
			jLabelSpecial.add(jButtonPartitions[i]);
		}
		for(int i=0; i<2; i++){
			jLabelSpecial.add(jTButtonOpAreaType[i]);
		}
		for(int i=0; i<2; i++){
			jLabelSpecial.add(jButtonsMinDarkArea[i]);
		}
		jLabelSpecial.add(jTextFieldMinDarkArea);
		
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
		OptionAreaGui gui = new OptionAreaGui();
		gui.setBounds(20, 20, 220, 60);
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 10, 350, 200);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(null);
		jFrame.add(gui);
	}
}
