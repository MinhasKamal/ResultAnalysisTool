/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 28-08-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.lib.egami.view;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

/**
 * 
 * 
 * @author Minhas Kamal
 */
public class ImageViewer{
	// GUI Declaration
	private ImageViewerGui gui;
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	private JLabel jLabel;
	private JButton[] jButtonsZoom;;
	
	private String imageFilePath;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public ImageViewer(){
		imageFilePath="";
		
		initialComponent();
	}
	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new ImageViewerGui();
		gui.setVisible(true);
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jLabel = gui.jLabel;
		jButtonsZoom = gui.jButtonsZoom;
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jButtonsZoom[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButtonsZoomActionPerformed(evt);
			}
		});
		jButtonsZoom[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButtonsZoomActionPerformed(evt);
			}
		});
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	private void jButtonsZoomActionPerformed(ActionEvent evt){
		if(evt.getSource() == jButtonsZoom[0]){
			resizeImage(1.15);
		}else{
			resizeImage(.75);
		}
	}
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**

	public void loadImage(String imageFilePath){
		this.imageFilePath = imageFilePath;
		showImage();
	}
	
	private void showImage(){
		
		try {
			BufferedImage bufferedImage = ImageIO.read(new File(imageFilePath));
			ImageIcon image = new ImageIcon(bufferedImage);
			
			Thread.sleep(1000);
			int width = gui.getWidth();
			int height = (int) (bufferedImage.getHeight() * ((double)width/bufferedImage.getWidth()));
			
			jLabel.setIcon(new ImageIcon((image.getImage()).
					getScaledInstance(width, height, Image.SCALE_SMOOTH)));
			
		} catch (Exception e) {
			e.printStackTrace();
			//new Message("Error opening image.", 420);
		}
		
	}
	
	private void resizeImage(double multiplier){
		
		int width = jLabel.getIcon().getIconWidth();
		int height = jLabel.getIcon().getIconHeight();
		
		width = (int) (width*multiplier);
		height = (int) (height*multiplier);
		
		if(jLabel.getIcon()!=null){
			try {
				jLabel.setIcon(new ImageIcon(((new ImageIcon(ImageIO.read(new File(imageFilePath)))).
						getImage()).getScaledInstance(width, height, Image.SCALE_SMOOTH)));
			} catch (IOException e) {
				jLabel.setIcon(new ImageIcon((((ImageIcon) jLabel.getIcon()).
						getImage()).getScaledInstance(width, height, Image.SCALE_SMOOTH)));
			}
		}
		
	}
	
	public void attachTo(JComponent jComponent) {
		jComponent.add(gui);
		jComponent.revalidate();
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
		ImageViewer imageViewer = new ImageViewer();
		
		JLabel jLabel = new JLabel();
		jLabel.setLayout(new GridLayout());
		
		JFrame jFrame = new JFrame("Image Viewer");
		jFrame.setBounds(40, 30, 740, 660);
		
		imageViewer.attachTo(jLabel);
		jFrame.add(jLabel);
		
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jFrame.setVisible(true);
		
		imageViewer.loadImage("C:\\Users\\admin\\Desktop\\IIT-MIT\\MIT.jpg");
	}
}

