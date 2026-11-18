/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 10-Feb-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.lib.table;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;


/**
 * 
 * 
 * @author Minhas Kamal
 */
@SuppressWarnings("serial")
public class TableGui extends JLabel {
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JScrollPane jSPaneTable;
	JLabel jLabelTable;
	JTable jTableRowNumber, jTable;
	
	JPopupMenu jPopupMenu;
	JMenuItem jMItemAddRowBefore, jMItemAddRowAfter, jMItemDeleteRow, jMItemDeleteColumn;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public TableGui() {

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
		jSPaneTable = new JScrollPane();
		jLabelTable = new JLabel(); 
		jTableRowNumber = new JTable();
		jTable = new JTable();
		
		jPopupMenu = new JPopupMenu();
		
		jMItemAddRowBefore = new JMenuItem();
		jMItemAddRowAfter = new JMenuItem();
		jMItemDeleteRow = new JMenuItem();
		jMItemDeleteColumn = new JMenuItem();
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jTable.setAutoCreateRowSorter(true);
		jTable.setFillsViewportHeight(true);
		jTable.setShowGrid(true);
		jTable.setAutoCreateColumnsFromModel(true);
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.setSelectionBackground(Color.DARK_GRAY);
		
		jTableRowNumber.setShowGrid(true);
		
		jLabelTable.setLayout(new MigLayout());
		
		jSPaneTable.setViewportView(jLabelTable);
		jSPaneTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jSPaneTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//jSPaneTable.setBorder(BorderFactory.createDashedBorder(null));
		
		//jTable.list();
		
		jMItemAddRowBefore.setText("Add Row Before");
		jMItemAddRowAfter.setText("Add Row After");
		jMItemDeleteRow.setText("Delete Row");
		jMItemDeleteColumn.setText("Delete Column");
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Label**//
		//setBorder(BorderFactory.createDashedBorder(null));
		setLayout(new MigLayout());

		//**
		// Adding Components 																	#*******A*******#
		//**
		
		jLabelTable.add(jTableRowNumber, "left");
		jLabelTable.add(jTable, "left");
		
		add(jSPaneTable);
		
		jPopupMenu.add(jMItemAddRowBefore);
		jPopupMenu.add(jMItemAddRowAfter);
		jPopupMenu.add(jMItemDeleteRow);
		jPopupMenu.add(jMItemDeleteColumn);
		
		jTable.setComponentPopupMenu(jPopupMenu);
		// End of Adding Components 															#_______A_______#
	}

	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			// do nothing if operation is unsuccessful
		}

		/* Create and display the form */
		TableGui gui = new TableGui();
		
		DefaultTableModel tableModel = new DefaultTableModel(500, 10);
		gui.jTable.setModel(tableModel);
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 10, 950, 700);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.add(gui);
	}
}
