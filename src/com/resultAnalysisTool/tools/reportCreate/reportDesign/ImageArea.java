/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 21-Jan-2015																								*
* Modified: 16-May-2015																							*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.reportCreate.reportDesign;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.resultAnalysisTool.lib.areator.GraphArea;
import com.resultAnalysisTool.lib.areator.GraphAreaGui;
import com.resultAnalysisTool.util.fileChoose.FileChooser;


/**
 * 
 * 
 * @author Minhas Kamal
 */
public class ImageArea extends GraphArea{
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	//tag for storing data.
	public static final String IA_TAG = "image_area";
	public static final String IA_SUB_TAG_AREA = "rect";
	public static final String IA_SUB_TAG_IMG_PATH = "img_path";
	
	private JButton jButtonBrowseImage;
	
	private String imagePath;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public ImageArea(int x, int y, int width, int height) {
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
		jButtonBrowseImage = ((ImageAreaGui) gui).jButtonBrowseImage;
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jButtonBrowseImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButtonBrowseImageActionPerformed(evt);
			}
		});
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	private void jButtonBrowseImageActionPerformed(ActionEvent evt){
		String filePath = new FileChooser("", new String[]{"png", "jpg", "jpeg"}).chooseFilePathFromComputer();
		setImage(filePath);
	}
	
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	@Override
	protected GraphAreaGui getGui(){
		return new ImageAreaGui();
	}
	
	@Override
	public void resize(int direction, int amount) {
		super.resize(direction, amount);
		
		adjustImage();
		
		gui.repaint();
	}
	
	@Override
	protected void relocateJTBProperty(){
		jTButtonProperty.setLocation(width-jTBPropertySide-3, 0);
		
		gui.repaint();
	}
	
	/**
	 * sets image to the label.
	 * @param imagePath
	 */
	public void setImage(String imagePath) {
		ImageIcon image = new ImageIcon(imagePath);
		((ImageAreaGui) gui).jLabel.setIcon(image);
		adjustImage();
		
		this.imagePath = imagePath;
		
		gui.repaint();
	}
	
	/**
	 * Adjusts image with the size of label.
	 */
	private void adjustImage(){
		if(((ImageAreaGui) gui).jLabel.getIcon()!=null){
			((ImageAreaGui) gui).jLabel.setIcon(new ImageIcon((((ImageIcon) ((ImageAreaGui) gui).jLabel.getIcon()).
					getImage()).getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH)));
		}
	}
	
	
	/**
	 * Return all information as a tagged string in a defined format.
	 * @return information
	 */
	@Override
	public String dump(){
		String info = "<" + IA_TAG + ">\n<" +
				IA_SUB_TAG_AREA + ">" + x + "," + y + "," + width + "," + height + "</" + IA_SUB_TAG_AREA + ">\n<" +
				IA_SUB_TAG_IMG_PATH + ">" + imagePath + "</" + IA_SUB_TAG_IMG_PATH + ">\n</" +
				IA_TAG + ">";
		
		return info;
	}

	/**
	 * Should be overridden by child
	 * @return
	 */
	@Override
	public GraphArea getCopy(){
		GraphArea graphArea = new ImageArea(x, y, width, height);
		((ImageArea) graphArea).setImage(imagePath);
		
		if(width>height){
			graphArea.move(VERTICAL_WAY, height);
		}else{
			graphArea.move(HORIZONTAL_WAY, width);
		}
		
		return graphArea;
	}
	// End of Auxiliary Methods 																#_______AM_______#
	
	//**
	// Overridden Methods 																		#*******OM*******#
	//**
	
	// End of Overridden Methods 																#_______OM_______#
		
	
	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create */
		ImageArea iArea = new ImageArea(20, 30, 300, 80);
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(40, 30, 400, 200);
		jFrame.setVisible(true);
		jFrame.setLayout(null);
		jFrame.add(iArea.gui);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		iArea.setImage("C:\\Users\\admin\\Desktop\\c.png");
	}
}
