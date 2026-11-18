/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 25-Jan-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.lib.areator;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import com.resultAnalysisTool.util.fileChoose.FileChooser;
import com.resultAnalysisTool.util.message.Message;

/**
 * 
 * 
 * @author Minhas Kamal
 */
public class GraphPainter extends AbstractGraphPainter{
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	private JToggleButton jTButtonGraphArea;
	
	private Cursor cursorGraphArea;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public GraphPainter() {
		
		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		//**
		// Assignation 																			#*******A*******#
		//**
		jTButtonGraphArea = ((GraphPainterGui) gui).jTButtonGraphArea;
		
		cursorGraphArea = ((GraphPainterGui) gui).cursorGraphArea;
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jTButtonGraphArea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jTButtonGraphAreaActionPerformed(evt);
            }
        });
		
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	private void jTButtonGraphAreaActionPerformed(ActionEvent evt) {
		JToggleButton jTBGraphArea = (JToggleButton) evt.getSource();
		
		if(jTBGraphArea.isSelected()){
			jLabelImage.setCursor(cursorGraphArea);
		}else{
			jLabelImage.setCursor(Cursor.getDefaultCursor());
		}
	}
	
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	/**
	 * Adds new graph area. Should be overridden by child class.
	 * @param rect
	 */
	protected void addNewGraphArea(Rectangle rect){
		
		if(validateArea(rect)){
			GraphArea graphArea = new GraphArea(rect.x, rect.y, rect.width, rect.height);
			graphAreaList.add(graphArea);
			
			drawGraphArea(graphArea);
		}
	}
	
	/**
	 * Returns consequent gui. Should be overridden by child class.
	 * @return
	 */
	@Override
	protected GraphPainterGui getGui() {
		return new GraphPainterGui();
	}
	// End of Auxiliary Methods 																#_______AM_______#
	
	//**
	// Unimplemented Methods 																	#*******UM*******#
	//**
	protected void jButtonLoadGraphActionPerformed(ActionEvent evt){
		String filePath = new FileChooser(new String[]{"grph"}).chooseFilePathFromComputer();
		
		if(!filePath.contains(".")){
			return ;
		}
		
		if(!getImagePath().contains(".")){
			new Message("Load image first!", 210);
			return;
		}else{
			new Message("Sorry not implemented!", 210);
			return;
		}
		
		//LoadGraphAreas(filePath);
	}
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
		GraphPainter graphPainter = new GraphPainter();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 10, 950, 700);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.add(graphPainter.gui);
		
		/*try {
			imageAreaDefiner.load("C:\\Users\\admin\\Desktop\\OMR.idg");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
}
