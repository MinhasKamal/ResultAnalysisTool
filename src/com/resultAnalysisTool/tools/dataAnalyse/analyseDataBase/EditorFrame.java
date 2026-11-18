/************************************************************************************************************
* Developer: Khandaker Mamun Ahmed(BSSE-0535, IIT, DU) & Minhas Kamal(BSSE-0509, IIT, DU)					*
* Date: 22-May-2014; Modified: 02-Jun-2014																	*
* Comment: This is actually an excel-sheet for CSV file. First Khandaker Mamun Ahmed designed a class which	*
* 	could show CSV file in a sheet, edit & jMISave the file. Later Minhas Kamal created this class	using the	*
* 	theme and some methods from that editor class and added necessary features needed for this class.		*
*************************************************************************************************************/

package com.resultAnalysisTool.tools.dataAnalyse.analyseDataBase;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.resultAnalysisTool.tools.dataAnalyse.analyseDataBase.addNewColumn.gui.AddNewColumnGui;
import com.resultAnalysisTool.tools.dataAnalyse.analyseDataBase.searching.gui.TableSearch;
import com.resultAnalysisTool.util.fileChoose.FileChooser;
import com.resultAnalysisTool.util.fileReadWrite.FileIO;
import com.resultAnalysisTool.util.message.Message;

@SuppressWarnings("serial")
public class EditorFrame extends JPanel implements ActionListener{
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private DefaultTableModel tableModel;
	private JTable jTableMain;
	private JScrollPane jScrollPaneMain;
	
	private JPopupMenu popUpMenu;
	private JMenuItem jMIAddRowAfterSelectedRow, jMIAddRowAtLast, jMIAddColumnAtLast, jMIDeleteCurrentRow,
			jMIDeleteCurrentColumn, jMIRank, jMISearch, jMISave;
	
	//other variables
	private String fileContent;
	
	private String[] fieldNames; 
	private String[] fileLineContent;
	
	private int lineSize;
	private int lineNumber;
	
	private String[][] fileData;
	private String filePath;
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##***/
	public EditorFrame(String filePath) {
		if(!filePath.contains(".")){
			return ;
		}
		
		this.filePath = filePath;
		setFileContent(filePath);
		
		initialComponent();
	}
	
	public EditorFrame() {
		this(new FileChooser(new String[]{"dbf"}).chooseFilePathFromComputer());
	}

	
	/**
	 * Method for Initializing all the GUI variables, placing them all to specific space on the frame and adding action
	 * listener to them. Also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		//**
		// Initialization 																					#*******I*******#
		//**
		tableModel = new DefaultTableModel();
		jTableMain = new JTable();
		jScrollPaneMain = new JScrollPane();
		
		popUpMenu = new JPopupMenu();		
		
		jMIAddRowAfterSelectedRow = new JMenuItem(); jMIAddRowAtLast = new JMenuItem();
		jMIAddColumnAtLast = new JMenuItem(); jMIDeleteCurrentRow = new JMenuItem();
		jMIDeleteCurrentColumn = new JMenuItem(); jMIRank = new JMenuItem();
		jMISearch = new JMenuItem(); jMISave = new JMenuItem();
		
		//other variables
		fieldNames = readFieldNames(1);
		fileLineContent = fileContent.split("\n");
		
		lineSize = fieldNames.length;
		lineNumber = fileLineContent.length;
		
		fileData = getDataFromFileContent();
		
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		tableModel.setDataVector(fileData, fieldNames);
		
		//jTableMain.setBackground(new Color(220, 250, 250));
		jTableMain.setFont(new Font("sansserif", Font.TRUETYPE_FONT, 15));
		jTableMain.setPreferredScrollableViewportSize(new Dimension(180, 65));
		jTableMain.setFillsViewportHeight(true);
		jTableMain.setAutoCreateRowSorter(true);
		jTableMain.setAutoCreateColumnsFromModel(true);
		jTableMain.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTableMain.setComponentPopupMenu(popUpMenu);
		jTableMain.setShowGrid(true);
		jTableMain.setModel(tableModel);
		
		//jScrollPaneMain.setBounds(0, 0, 705, 650);
		jScrollPaneMain.setLocation(0, 0);
		jScrollPaneMain.setViewportView(jTableMain);
		jScrollPaneMain.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPaneMain.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		jMIAddRowAfterSelectedRow.setText("Add Row"); jMIAddRowAfterSelectedRow.addActionListener(this);
		jMIAddRowAtLast.setText("Add Row At Fast"); jMIAddRowAtLast.addActionListener(this);
		jMIAddColumnAtLast.setText("Add Column"); jMIAddColumnAtLast.addActionListener(this);
		jMIDeleteCurrentRow.setText("Delete Row"); jMIDeleteCurrentRow.addActionListener(this);
		jMIDeleteCurrentColumn.setText("Delete Column"); jMIDeleteCurrentColumn.addActionListener(this);
		jMIRank.setText("Rank"); jMIRank.addActionListener(this);
		jMISearch.setText("Search"); jMISearch.addActionListener(this);
		jMISave.setText("Save"); jMISave.addActionListener(this);
		// End of Setting Bounds and Attributes 															#_______S_______#

		//**
		// Adding Components 																				#*******A*******#
		//**
		add(jScrollPaneMain);
		
		popUpMenu.add(jMIAddRowAfterSelectedRow); popUpMenu.add(jMIAddRowAtLast);
		popUpMenu.add(jMIAddColumnAtLast); popUpMenu.add(jMIDeleteCurrentRow);
		popUpMenu.add(jMIDeleteCurrentColumn); popUpMenu.add(jMIRank);
		popUpMenu.add(jMISearch); popUpMenu.add(jMISave);
		// End of Adding Components 																		#_______A_______#
		
		setBackground(Color.WHITE);
		setLayout(new GridLayout());
	}

	//**
	// Action Events 																						#********AE*******#
	//**

	@Override							
	public void actionPerformed(ActionEvent e) {
		JMenuItem menu = (JMenuItem) e.getSource();	
		tableModel = (DefaultTableModel) jTableMain.getModel();
		
		if(menu == jMIAddRowAfterSelectedRow){
			jMIAddRowAfterSelectedRow();
		}else if(menu == jMIAddRowAtLast){
			addRowAtFast();
		}else if(menu == jMIAddColumnAtLast){
			addColumn();
		}else if(menu == jMIDeleteCurrentRow){
			jMIDeleteCurrentRow();
		}else if(menu == jMIDeleteCurrentColumn){
			jMIDeleteCurrentColumn();
		}else if(menu == jMIRank){
			jMIRankFileData();
		}else if(menu == jMISearch){
			jMISearchFileData();
		}else if(menu == jMISave){
			jMISaveFile();
		}else{
			//do nothing
		}
		
		jTableMain.setModel(tableModel);
		jTableMain.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		// gets the total column number(int)
		int TotalTableColumn = jTableMain.getColumnModel().getColumnCount();
		
		//set the table column size to fit with window
		int minWidth = 800/TotalTableColumn;
		
		for(int a=0; a<TotalTableColumn; a++){
			TableColumn tableColumn_Editor = jTableMain.getColumnModel().getColumn(a);
			tableColumn_Editor.setMinWidth(minWidth);
		}
	}																					// Action Event

	
	/*
	 * Add row at first 
	*/
	private void addRowAtFast() {
		tableModel.insertRow(0, new Object[tableModel.getColumnCount()]);
	}
	

	/*
	 * Deletes the current selected column 
	*/
	private void jMIDeleteCurrentColumn() {
		if(jTableMain.getSelectedColumn()== -1){
			new Message("Column has not selected", 420);
		}else{
			TableColumn col = jTableMain.getColumnModel().getColumn(jTableMain.getSelectedColumn());
			jTableMain.removeColumn(col);
		}
	}
	
	
	/*
	 * Deletes the current selected rows 
	*/
	private void jMIDeleteCurrentRow() {
		if(jTableMain.getSelectedRow() == -1){
			new Message("Row is not selected", 420);
		}
		else{
			int a[] = jTableMain.getSelectedRows();
			int row_Counter_Track = 0;
			
			for(int i=0; i<a.length; i++){
				tableModel.removeRow(a[i]+row_Counter_Track);
				row_Counter_Track--;
			}
		}
	}

	
	/*
	 * Adds a new column at last
	*/
	private void addColumn() {
		
		//When no column is selected add column at last
		if(jTableMain.getSelectedColumn() == -1){
			//new AddColumn();
			new Message("No Column is selected", 420);
			
			//editedModel.addColumn(JOptionPane.showInputDialog(null, "Enter The Column Name"));
		}
		//When a column is selected add column after that
		else{
			//new AddColumn();
			int totalColumn = tableModel.getColumnCount();
			String[] columnNames =  new String[totalColumn];
			
			for(int i=0; i<totalColumn; i++){
				columnNames[i] = tableModel.getColumnName(i);
			}
			
			fileData = new AddNewColumnGui(fileData, columnNames).getNewFileContent();
			refresh();
			
			//editedModel.addColumn(JOptionPane.showInputDialog(null, "Enter The Column Name"));
		}
	}

	
	/*
	 * Adds a new row after the selected row and if any row is not selected then sets a new row at last   
	*/
	private void jMIAddRowAfterSelectedRow() {
		if(jTableMain.getSelectedRow() == -1){
			tableModel.addRow(new Object[tableModel.getColumnCount()]);
		}
		else{
			int a[] = jTableMain.getSelectedRows();
			int row_Counter_Track = 1;
			
			for(int i=0; i<a.length; i++){
				tableModel.insertRow(a[i]+row_Counter_Track, new Object[tableModel.getColumnCount()]);
				row_Counter_Track++;
			}
		}
	}
	
	private void jMIRankFileData() {
		//TODO
		System.out.println("Rank");
		//Call the jMIRank class
	}
	
	private void jMISearchFileData() {
		int totalColumn = tableModel.getColumnCount();
		String[] columnNames =  new String[totalColumn];
		
		for(int i=0; i<totalColumn; i++){
			columnNames[i] = tableModel.getColumnName(i);
		}
		
		fileData = new TableSearch(fileData, columnNames).getNewFileContent();
		
//		for(int i=0; i<fileData.length; i++){
//			for(int j=0; j<fileData[0].length; j++){
//				System.out.print(fileData[i][j]+",");
//			}
//			System.out.println();
//		}
		
		tableModel.setDataVector(fileData, fieldNames);
		jTableMain.setModel(tableModel);
	}
	
	private void jMISaveFile() {
		jTableMain.validate();
		
		String mainString="";
		
		String[] columnHeaders = getColumnHeaders();
		for(String columnHeader: columnHeaders){
			mainString += columnHeader + ",";
		}
		mainString = mainString.substring(0, mainString.length()-1);
		mainString += "\n";
		
		String[][] data = getTableData();
		
		String tempString="";
		for(String[] rowdata: data){
			for(String griddata: rowdata){
				tempString += griddata + ",";
			}
			tempString = tempString.substring(0, tempString.length()-1);
			mainString += tempString+"\n";
			tempString = "";
		}
		
		try {
			FileIO.writeWholeFile(filePath+"new.dbf", mainString);
			new Message("File is saved.", 1);
		} catch (Exception e) {
			new Message("Error!", 420);
		}
	}
	// End of Action Events 																				#________AE_______#

	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			javax.swing.UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");//****//
			//javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create and display the form */
		EditorFrame gui = new EditorFrame();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 10, 950, 700);
		jFrame.setLayout(new GridLayout());
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.add(gui);
	}

	//**
	// Auxiliary Methods 																					#********AM*******#
	//**
	private String[] getColumnHeaders(){
		int numberOfColumns= jTableMain.getColumnCount();
		String[] columnHeaders = new String[numberOfColumns];
		for(int i=0; i<numberOfColumns; i++){
			columnHeaders[i] = jTableMain.getColumnName(i);
		}
		
		return columnHeaders;
	}
	
	private String[][] getTableData(){
		//DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
		int numberOfRows = jTableMain.getRowCount(),
			numberOfColumns = jTableMain.getColumnCount();
		String[][] data = new String[numberOfRows][numberOfColumns];
		
		for(int i=0; i<numberOfRows; i++){
			for(int j=0; j<numberOfColumns; j++){
				data[i][j] = (String) jTableMain.getValueAt(i, j);
			}
		}
		
		return data;
	}
	
	
	/**
	 * 
	 * @param fileContent
	 */
	public void setFileContent(String filePath){
		try {
			this.fileContent = FileIO.readWholeFile(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refresh(){
		fieldNames = readFieldNames(1);
		fileLineContent = fileContent.split("\n");
				
		lineSize = fieldNames.length;
		lineNumber = fileLineContent.length;
				
		fileData = getDataFromFileContent();
		
		tableModel.setDataVector(fileData, fieldNames);
		jTableMain.setModel(tableModel);	
	}
	
	
	/**
	 * 
	 * @param FileName
	 * @return
	 */
	private String[] readFieldNames(int lineNumber){
		String rawFieldNames = getLine(fileContent, lineNumber);
		
		return rawFieldNames.split(";");
	}
	
	
	/**
	 * 
	 * @param mainString
	 * @param lineNo
	 * @return
	 */
	private String getLine(String mainString, int lineNo){
		String line="";
		int length = mainString.length();
		char ch;
		
		for(int i=0, lNo=1; i<length; i++){
			ch=mainString.charAt(i);
			
			if(ch=='\n'){
				if(lNo==lineNo){
					break;
				}
				
				line="";
				lNo++;
			}
			else{
				line += ch;
			}
		}
		return line;
	}

	
	/**
	 * 
	 * @return
	 */
	private String[][] getDataFromFileContent(){
		String[][] data = new String[lineNumber-1][lineSize];
		//System.out.println(lineNumber-1 + " " + lineSize);///test
		
		String[] temp = new String[lineSize];
		int i=2;
		while(true){
			if(i == lineNumber+1){
				break;
			}
			
			temp = readFieldNames(i);
			//TODO
			int currentLineSize = temp.length;
			for(int j=0; j< currentLineSize; j++){
				//System.out.println(temp[j]);///test
				//System.out.println(i+" "+j);///test
				data[i-2][j] = temp[j];
				//System.out.println(rows[i-2][j]+"\t");///test
			}
			
			i++;
		}	
		
		return data;
	}

	// End of Auxiliary Methods 																			#________AM_______#
}
