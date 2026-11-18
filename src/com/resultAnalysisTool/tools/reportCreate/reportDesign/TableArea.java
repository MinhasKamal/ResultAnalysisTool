/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 21-Jan-2015																								*
* Modified: 17-May-2015																							*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.reportCreate.reportDesign;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.resultAnalysisTool.lib.areator.GraphArea;
import com.resultAnalysisTool.lib.areator.GraphAreaGui;
import com.resultAnalysisTool.util.fileChoose.FileChooser;
import com.resultAnalysisTool.util.fileReadWrite.FileIO;
import com.resultAnalysisTool.util.message.Message;


/**
 * 
 * 
 * @author Minhas Kamal
 */
public class TableArea extends GraphArea{
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JButton jButtonSetContent;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public TableArea(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		initialComponent();
		
		relocateJTBProperty();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jButtonSetContent = ((TableAreaGui) gui).jButtonSetContent;
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jButtonSetContent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButtonSetContentActionPerformed(evt);
			}
		});
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	private void jButtonSetContentActionPerformed(ActionEvent evt){
		String filePath = new FileChooser("", new String[]{"dbf"}).chooseFilePathFromComputer();
		if(filePath.contains(".")){
			try {
				setData(filePath);
			} catch (Exception e) {
				new Message("Error", 420);
			}
		}
	}
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	private void setData(String filePath) throws Exception{
		String[] lines = FileIO.readWholeFile(filePath).split("\n");
		
		String[] columnHeaders = lines[0].split(";");
		String[][] data = new String[lines.length-1][];
		
		for(int i=1; i<lines.length; i++){
			data[i-1] = lines[i].split(";");
		}
		
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.setDataVector(data, columnHeaders);
		((TableAreaGui)gui).jTable.setModel(tableModel);
	}
	
	@Override
	protected GraphAreaGui getGui(){
		return new TableAreaGui();
	}
	
	@Override
	public void resize(int direction, int amount) {
		super.resize(direction, amount);
		
		((TableAreaGui)gui).jScrollPane.setSize(gui.getSize());
		((TableAreaGui)gui).jTable.setSize(((TableAreaGui)gui).jScrollPane.getSize());

		gui.repaint();
	}
	
	@Override
	protected void relocateJTBProperty(){
		jTButtonProperty.setLocation(width-jTBPropertySide-3, height-jTBPropertySide-3);
		
		gui.repaint();
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
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create */
		TableArea iArea = new TableArea(20, 30, 300, 80);
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(40, 30, 400, 200);
		jFrame.setVisible(true);
		jFrame.setLayout(null);
		jFrame.add(iArea.gui);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
