/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 16-Feb-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.lib.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.resultAnalysisTool.util.message.Message;

/**
 * 
 * 
 * @author Minhas Kamal
 */
public class Table{
	// GUI Declaration
	private TableGui gui;
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	private JTable jTable;
	private JMenuItem jMItemAddRowBefore, jMItemAddRowAfter, jMItemDeleteRow, jMItemDeleteColumn;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public Table(String filePath) {

		initialComponent();
		
		load(filePath);
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new TableGui();
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jTable = gui.jTable;
		jMItemAddRowBefore = gui.jMItemAddRowBefore;
		jMItemAddRowAfter = gui.jMItemAddRowAfter;
		jMItemDeleteRow = gui.jMItemDeleteRow;
		jMItemDeleteColumn = gui.jMItemDeleteColumn;
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		
		jMItemAddRowBefore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jMItemAddRowBeforeActionPerformed(evt);
			}
		});
		jMItemAddRowAfter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jMItemAddRowAfterActionPerformed(evt);
			}
		});
		jMItemDeleteRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jMItemDeleteRowActionPerformed(evt);
			}
		});
		jMItemDeleteColumn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jMItemDeleteColumnActionPerformed(evt);
			}
		});
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	
	private void jMItemAddRowBeforeActionPerformed(ActionEvent evt){
		if(jTable.getSelectedRow() == -1){
			((DefaultTableModel) jTable.getModel()).addRow(new Object[jTable.getModel().getColumnCount()]);
		}
		else{
			int a[] = jTable.getSelectedRows();
			int row_Counter_Track = 1;
			
			for(int i=0; i<a.length; i++){
				((DefaultTableModel) jTable.getModel()).insertRow(a[i]+row_Counter_Track-1, 
						new Object[jTable.getModel().getColumnCount()]);
				row_Counter_Track++;
			}
		}
	}
	private void jMItemAddRowAfterActionPerformed(ActionEvent evt){
		if(jTable.getSelectedRow() == -1){
			((DefaultTableModel) jTable.getModel()).addRow(new Object[jTable.getModel().getColumnCount()]);
		}
		else{
			int a[] = jTable.getSelectedRows();
			int row_Counter_Track = 1;
			
			for(int i=0; i<a.length; i++){
				((DefaultTableModel) jTable.getModel()).insertRow(a[i]+row_Counter_Track, 
						new Object[jTable.getModel().getColumnCount()]);
				row_Counter_Track++;
			}
		}
	}
	private void jMItemDeleteRowActionPerformed(ActionEvent evt){
		if(jTable.getSelectedRow() == -1){
//			new Message("Row is not selected", 210);
		}
		else{
			int a[] = jTable.getSelectedRows();
			int row_Counter_Track = 0;
			
			for(int i=0; i<a.length; i++){
				((DefaultTableModel) jTable.getModel()).removeRow(a[i]+row_Counter_Track);
				row_Counter_Track--;
			}
		}
	}
	private void jMItemDeleteColumnActionPerformed(ActionEvent evt){
		if(jTable.getSelectedColumn()== -1){
			//new Message("Column is not selected", 210);
		}else{
			jTable.removeColumn(jTable.getColumnModel().getColumn(jTable.getSelectedColumn()));
		}
	}
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	
	public String[] getColumnHeaders(){
		int numberOfColumns= jTable.getColumnCount();
		String[] columnHeaders = new String[numberOfColumns];
		for(int i=0; i<numberOfColumns; i++){
			columnHeaders[i] = jTable.getColumnName(i);
		}
		
		return columnHeaders;
	}
	
	public String[][] getTableData(){
		int numberOfRows = jTable.getRowCount(),
			numberOfColumns = jTable.getColumnCount();
		String[][] data = new String[numberOfRows][numberOfColumns];
		
		for(int i=0; i<numberOfRows; i++){
			for(int j=0; j<numberOfColumns; j++){
				data[i][j] = (String) jTable.getValueAt(i, j);
			}
		}
		
		return data;
	}
	
	public void load(String filePath){
		try {
			byte[] bytes = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(new File(filePath).toURI()));
			String fullData = new String(bytes);	//contains the full file
			
			jTable.setModel(constructTableModel(fullData));
			
		} catch (IOException e) {
			new Message("Invalid file." , 210);
		}
	}
	
	public DefaultTableModel constructTableModel(String fullData){
		String[] rowData = fullData.split("\n");
		
		String[][] data = new String[rowData.length][];
		int i=0;
		for(String str: rowData){
			data[i] = str.split(",");
			i++;
		}
		
		
		String[] columnNames = new String[data[0].length];
		
		for(i=0; i<data[0].length; i++){
			columnNames[i] = (i+1) + "";
		}
		
		return new DefaultTableModel(data, columnNames);
	}
	
	// End of Auxiliary Methods 																#_______AM_______#
	
	//**
	// Unimplemented Methods 																	#*******UM*******#
	//**
	
	// End of Unimplemented Methods 															#_______UM_______#
	
	
	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//			UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create */
		Table csvTable= new Table("C:\\Users\\admin\\Desktop\\v.csd");
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 10, 950, 700);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.add(csvTable.gui);
	}
}
