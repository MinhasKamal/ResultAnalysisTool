/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 10-Feb-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.dataAnalyse.analyseCommaSeperatedData;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
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
public class CSVTableGui extends JLabel {
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JLabel jLabelOperation;
	JButton jButtonSearch, jButtonList, jButtonAddColumn, jButtonSave;
	
	JScrollPane jSPaneTable;
	//JTable jTableRowNumber;
	JTable jTable;
	
	JPopupMenu jPopupMenu;
	JMenuItem jMItemAddRowBefore, jMItemAddRowAfter, jMItemDeleteRow, jMItemDeleteColumn, jMItemShowImage;
	JSeparator separator;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public CSVTableGui() {

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
		jLabelOperation = new JLabel();
		
		jButtonSearch = new JButton();
		jButtonList = new JButton();
		jButtonAddColumn = new JButton();
		jButtonSave = new JButton();
		
		jSPaneTable = new JScrollPane();
		//jTableRowNumber = new JTable();
		jTable = new JTable();
		
		jPopupMenu = new JPopupMenu();
		
		jMItemAddRowBefore = new JMenuItem();
		jMItemAddRowAfter = new JMenuItem();
		jMItemDeleteRow = new JMenuItem();
		jMItemDeleteColumn = new JMenuItem();
		jMItemShowImage = new JMenuItem();
		
		separator = new JSeparator();
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jLabelOperation.setLayout(new MigLayout());
//		jLabelOperation.setBorder(BorderFactory.createDashedBorder(null));
		
		jButtonSearch.setText("Search");
		
		jButtonList.setText("List");
		
		jButtonAddColumn.setText("Add Column");
		
		jButtonSave.setText("Save");
		
//		jTableRowNumber.setFillsViewportHeight(true);
//		jTableRowNumber.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		jTableRowNumber.setSelectionBackground(Color.LIGHT_GRAY);
		
		jTable.setAutoCreateRowSorter(true);
		jTable.setFillsViewportHeight(true);
		jTable.setShowGrid(true);
		jTable.setAutoCreateColumnsFromModel(true);
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.setSelectionBackground(Color.DARK_GRAY);
		
		jSPaneTable.setViewportView(jTable);
		jSPaneTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jSPaneTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//jSPaneTable.setBorder(BorderFactory.createDashedBorder(null));
		
		//jTable.list();
		
		jMItemAddRowBefore.setText("Add Row Before");
		jMItemAddRowAfter.setText("Add Row After");
		jMItemDeleteRow.setText("Delete Row");
		jMItemDeleteColumn.setText("Delete Column");
		jMItemShowImage.setText("Show Image");
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Label**//
		//setBorder(BorderFactory.createDashedBorder(null));
		setLayout(new MigLayout());

		//**
		// Adding Components 																	#*******A*******#
		//**
		jLabelOperation.add(jButtonSearch, "w :120:700, h 30:40:50");
		jLabelOperation.add(jButtonList, "w :120:700, h 30:40:50");
		jLabelOperation.add(jButtonAddColumn, "w :120:700, h 30:40:50");
		jLabelOperation.add(jButtonSave, "w :120:700, h 30:40:50");
		
		add(jLabelOperation, "north, h 40:60:, w 200:1900:");
		add(jSPaneTable, "south, h 200:1000:1500, w 200:1900:");
		
		jPopupMenu.add(jMItemAddRowBefore);
		jPopupMenu.add(jMItemAddRowAfter);
		jPopupMenu.add(jMItemDeleteRow);
		jPopupMenu.add(jMItemDeleteColumn);
		jPopupMenu.add(separator);
		jPopupMenu.add(jMItemShowImage);
		
		jTable.setComponentPopupMenu(jPopupMenu);
		// End of Adding Components 															#_______A_______#
	}

	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
//			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
		} catch (Exception e) {
			// do nothing if operation is unsuccessful
//			e.printStackTrace();
		}

		/* Create and display the form */
		CSVTableGui gui = new CSVTableGui();
		
		DefaultTableModel tableModel = new DefaultTableModel(500, 10);
		gui.jTable.setModel(tableModel);
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 10, 950, 700);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.add(gui);
	}
}
