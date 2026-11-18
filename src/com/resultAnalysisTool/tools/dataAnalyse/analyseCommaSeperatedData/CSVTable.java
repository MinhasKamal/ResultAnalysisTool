/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 16-Feb-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.dataAnalyse.analyseCommaSeperatedData;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.resultAnalysisTool.lib.egami.view.ImageViewer;
import com.resultAnalysisTool.util.fileChoose.FileChooser;
import com.resultAnalysisTool.util.fileReadWrite.FileIO;
import com.resultAnalysisTool.util.message.Confirm;
import com.resultAnalysisTool.util.message.Message;

/**
 * 
 * 
 * @author Minhas Kamal
 */
public class CSVTable{
	// GUI Declaration
	private CSVTableGui gui;
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	private JButton jButtonSearch, jButtonList, jButtonAddColumn, jButtonSave;
	private JTable jTable;
	private JMenuItem jMItemAddRowBefore, jMItemAddRowAfter, jMItemDeleteRow, jMItemDeleteColumn, jMItemShowImage;
	
	//other variables
	private String filePath;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public CSVTable(String filePath) {
		if(!filePath.contains(".")){
			return ;
		}

		initialComponent();
		
		load(filePath);
	}
	
	public CSVTable() {
		this(new FileChooser(new String[]{"csd"}).chooseFilePathFromComputer());
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new CSVTableGui();
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jButtonSearch = gui.jButtonSearch;
		jButtonList = gui.jButtonList;
		jButtonAddColumn = gui.jButtonAddColumn; 
		jButtonSave = gui.jButtonSave;
		jTable = gui.jTable;
		jMItemAddRowBefore = gui.jMItemAddRowBefore;
		jMItemAddRowAfter = gui.jMItemAddRowAfter;
		jMItemDeleteRow = gui.jMItemDeleteRow;
		jMItemDeleteColumn = gui.jMItemDeleteColumn;
		jMItemShowImage = gui.jMItemShowImage;
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jButtonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButtonSearchActionPerformed(evt);
			}
		});
		jButtonList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButtonListActionPerformed(evt);
			}
		});
		jButtonAddColumn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButtonAddColumnActionPerformed(evt);
			}
		});
		jButtonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButtonSaveActionPerformed(evt);
			}
		});
		
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
		jMItemShowImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jMItemShowImageActionPerformed(evt);
			}
		});
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	private void jButtonSearchActionPerformed(ActionEvent evt){
		
	}
	private void jButtonListActionPerformed(ActionEvent evt){
		
	}
	private void jButtonAddColumnActionPerformed(ActionEvent evt){
		
	}
	private void jButtonSaveActionPerformed(ActionEvent evt){
		jTable.validate();
		
		String rawData = "";
		
//		String[] columnHeaders = getColumnHeaders();
//		for(String columnHeader: columnHeaders){
//			rawData += columnHeader+",";
//		}
//		rawData += "\n";
		
		String[][] data = getTableData();
		
		for(String[] rowdata: data){
			for(String griddata: rowdata){
				rawData += griddata+",";
			}
			rawData += "\n";
		}
		
		try {
			FileIO.writeWholeFile(filePath, rawData);
		} catch (Exception e) {
			new Message("Error writing file!", 210);
			return;
		}
		
		new Message("File is saved.", 1);
	}
	
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
			new Message("No row is selected", 210);
		}
		else{
			Confirm confirm = new Confirm("The whole column will be deleted! \nAre you sure?");
			
			if(confirm.getDecision()){
			
				int a[] = jTable.getSelectedRows();
				int row_Counter_Track = 0;
				
				for(int i=0; i<a.length; i++){
					((DefaultTableModel) jTable.getModel()).removeRow(a[i]+row_Counter_Track);
					row_Counter_Track--;
				}
			}
		}
	}
	private void jMItemDeleteColumnActionPerformed(ActionEvent evt){
		if(jTable.getSelectedColumn()== -1){
			new Message("No column is selected", 210);
		}else{
			Confirm confirm = new Confirm("The whole column will be deleted! \nAre you sure?");
			
			if(confirm.getDecision()){
				jTable.removeColumn(jTable.getColumnModel().getColumn(jTable.getSelectedColumn()));
			}
		}
	}
	private void jMItemShowImageActionPerformed(ActionEvent evt){
		int selectedRowNo = jTable.getSelectedRow();
		
		if(selectedRowNo == -1){
			new Message("No row is selected", 210);
		}else{
			int numberOfColumns = jTable.getColumnCount();
			String data, filePath = "";
			
			for(int i=0; i<numberOfColumns; i++){
				data = (String) jTable.getValueAt(selectedRowNo, i);
				
				if(data.charAt(0)=='&'){
					filePath = data.substring(1, data.length());
				}
			}
			
			if(filePath.length()>2){
				ImageViewer imageViewer = new ImageViewer();
				
				JLabel jLabel = new JLabel();
				jLabel.setLayout(new GridLayout());
				
				JFrame jFrame = new JFrame("Image Viewer");
				jFrame.setBounds(360, 50, 400, 520);
				
				imageViewer.attachTo(jLabel);
				jFrame.add(jLabel);
				
				jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				jFrame.setVisible(true);
				
				
				imageViewer.loadImage(filePath);

			}else{
				new Message("No image path found!", 420);
			}
		}
	}
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	/*private String[] getRawColumnHeaders(){
		DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
		
		int numberOfColumns= tableModel.getColumnCount();
		String[] columnHeaders = new String[numberOfColumns];
		for(int i=0; i<numberOfColumns; i++){
			columnHeaders[i] = tableModel.getColumnName(i);
		}
		
		return columnHeaders;
	}*/
	
	@SuppressWarnings("unused")
	private String[] getColumnHeaders(){
		int numberOfColumns= jTable.getColumnCount();
		String[] columnHeaders = new String[numberOfColumns];
		for(int i=0; i<numberOfColumns; i++){
			columnHeaders[i] = jTable.getColumnName(i);
		}
		
		return columnHeaders;
	}
	
	/*private String[][] getRawTableData(){
		@SuppressWarnings("unchecked")
		Vector<Vector<String>> vectors = ((DefaultTableModel) jTable.getModel()).getDataVector();
		
		String[][] data = new String[vectors.size()][vectors.elementAt(0).size()];
		
		int i=0;
		for(Vector<String> vector: vectors){
			data[i] = vector.toArray(data[i]);
			i++;
		}
		
		return data;
	}*/
	
	private String[][] getTableData(){
		//DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
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
		this.filePath = filePath;
		
		try {
			byte[] bytes = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(new File(filePath).toURI()));
			String fullData = new String(bytes);	//contains the full file
			
			jTable.setModel(constructTableModel(fullData));
			
		} catch (IOException e) {
			e.printStackTrace();
//			new Message("" , 210);
		}
	}
	
	private DefaultTableModel constructTableModel(String fullData){
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
	
	public void attachTo(JComponent jComponent){
		jComponent.add(gui);
		jComponent.revalidate();
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
		CSVTable csvTable= new CSVTable("C:\\Users\\admin\\Desktop\\IIT-MIT\\MITquestion.csd");
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 10, 950, 700);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.add(csvTable.gui);
	}
}
