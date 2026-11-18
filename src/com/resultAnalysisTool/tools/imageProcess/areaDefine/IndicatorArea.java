/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 03-Jan-2015																								*
* Date: 25-Jan-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.imageProcess.areaDefine;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.resultAnalysisTool.lib.areator.GraphArea;
import com.resultAnalysisTool.lib.areator.GraphAreaGui;
import com.resultAnalysisTool.lib.egami.matrix.Matrix;

/**
 * Defines area where option is filled.
 * 
 * @author Minhas Kamal
 */
public class IndicatorArea extends GraphArea{
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	//tag for storing data.
	public static final String IA_TAG = "indicator_area";
	public static final String IA_SUB_TAG_AREA = "rect";
	public static final String IA_SUB_TAG_IMG = "img";
	
	//other variables
	private String imagePath;
	
	/***##Constructor##***/
	public IndicatorArea(int x, int y, int width, int height, String imagePath){
		super(x, y, width, height);
		
		this.imagePath = imagePath;
		jTBPropertySide = 10;
		
		initialComponent();
		
		relocateJTBProperty();
	}
	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		jTButtonProperty.setSize(jTBPropertySide, jTBPropertySide);
		//**
		// Assignation 																			#*******A*******#
		//**
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	@Override
	protected GraphAreaGui getGui(){
		return new IndicatorAreaGui();
	}
	
	
	/**
	 * Return all information as a tagged string in a defined format.
	 * @return information
	 */
	@Override
	public String dump(){
		Matrix mat = null;;
		try {
			mat = new Matrix(imagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mat = mat.subMatrix(y, y+height, x, x+width);
		
		String info = "<"+IA_TAG+">\n<"+IA_SUB_TAG_AREA+">"+x+","+y+","+width+","+height+"</"+IA_SUB_TAG_AREA+
				">\n<"+IA_SUB_TAG_IMG+">";
		
		String matString="[";
		for(int i=0, j; i<height; i++){
			for(j=0; j<width; j++){
				matString += (int)mat.getPixel(i, j)+",";
			}
		}
		matString += "]";
		
		info += matString;
		
		info += "</"+IA_SUB_TAG_IMG+">\n</"+IA_TAG+">";
		
		return info;
	}
	
	/**
	 * Should be overridden by child
	 * @return
	 */
	public GraphArea getCopy(){
		GraphArea graphArea = new IndicatorArea(x, y, width, height, imagePath);
		
		if(width>height){
			graphArea.move(VERTICAL_WAY, height);
		}else{
			graphArea.move(HORIZONTAL_WAY, width);
		}
		
		return graphArea;
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

		/* Create and display the form */
		IndicatorArea area = new IndicatorArea(30, 30, 200, 30, "");
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(40, 30, 400, 200);
		jFrame.setVisible(true);
		jFrame.setLayout(null);
		jFrame.add(area.gui);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
