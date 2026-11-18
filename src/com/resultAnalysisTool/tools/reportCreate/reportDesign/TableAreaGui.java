/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 21-Jan-2015																								*
* Modified: 17-May-2015																							*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.reportCreate.reportDesign;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.resultAnalysisTool.lib.areator.GraphAreaGui;


/**
 * 
 * 
 * @author Minhas Kamal
 */
@SuppressWarnings("serial")
public class TableAreaGui extends GraphAreaGui {
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JScrollPane jScrollPane;
	JTable jTable;
	
	JButton jButtonSetContent;	
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public TableAreaGui() {

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
		jScrollPane = new JScrollPane();
		jTable = new JTable();
		
		jButtonSetContent = new JButton();
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jTButtonProperty.setText("B");
		
		jScrollPane.setBorder(null);
		jScrollPane.setViewportView(jTable);
		
		jTable.setAutoCreateRowSorter(true);
		jTable.setFillsViewportHeight(true);
		jTable.setShowGrid(true);
		jTable.setSelectionBackground(Color.black);
		
		jButtonSetContent.setText("Set Content");
		jButtonSetContent.setSize(217, 20);
		jButtonSetContent.setLocation(9, 95);
		
		jPMenuProperty.setPopupSize(235, 130);
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Label**//
		setLayout(new GridLayout());

		//**
		// Adding Components 																	#*******A*******#
		//**
		jLabelPopupMenu.remove(jLabelSpecial);
		jLabelPopupMenu.add(jButtonSetContent);
		
		remove(jTButtonProperty);
		
		jTable.add(jTButtonProperty);
		add(jScrollPane);
		
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
		TableAreaGui gui = new TableAreaGui();
		
		String[] columnHeaders = {"Roll", "Name", "Number", "Grade"};
		String[][] data = {{"01", "Prithul", "97", "A+"}, {"02", "Minhas", "79", "A"}};
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.setDataVector(data, columnHeaders);
		gui.jTable.setModel(tableModel);
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 10, 350, 200);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(null);
		jFrame.add(gui);
		gui.setBounds(20, 30, 300, 90);
	}
}
