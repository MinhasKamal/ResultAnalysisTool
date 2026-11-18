/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 4-Jan-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.imageProcess.dataExtract;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;


/**
 * 
 * 
 * @author Minhas Kamal
 */
@SuppressWarnings("serial")
public class ImageDataExtractorGui extends JFrame {
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JPanel jPanelMain;
	
	JLabel jLabelSelectImgFolder;
	JTextField jTextFieldSelectImgFolder;
	JButton jButtonSelectImgFolder;
	
	JLabel jLabelSelectImgDefGrph;
	JTextField jTextFieldSelectImgDefGrph;
	JButton jButtonSelectImgDefGrph;
	
	JLabel jLabelSelectCommaSepData;
	JTextField jTextFieldSelectCommaSepData;
	
	JButton jButtonStart;
	
	JLabel jLabelMessage;
	
	JTextArea jTextAreaProgress;
	JScrollPane jScrollPaneProgress;
	
	JButton jButtonClose;
	
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public ImageDataExtractorGui() {

		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		//**
		// Initialization 																		#*******I*******#
		//**
		jPanelMain = new JPanel();
		
		jLabelSelectImgFolder = new JLabel("*Select image folder:");
		jTextFieldSelectImgFolder = new JTextField();
		jButtonSelectImgFolder = new JButton("...");
		
		jLabelSelectImgDefGrph = new JLabel("*Select *.IDG file:");
		jTextFieldSelectImgDefGrph = new JTextField();
		jButtonSelectImgDefGrph = new JButton("...");
		
		jLabelSelectCommaSepData = new JLabel("Name *.CSD file:");
		jTextFieldSelectCommaSepData = new JTextField();
		
		jButtonStart = new JButton();
		
		jLabelMessage = new JLabel();
		
		jTextAreaProgress = new JTextArea();
		jScrollPaneProgress = new JScrollPane();
		
		jButtonClose = new JButton();
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jPanelMain.setBackground(Color.white);
		jPanelMain.setLayout(null);
		
		
		jLabelSelectImgFolder.setBounds(15, 10, 140, 30);
		jLabelSelectImgFolder.setFont(new Font("Arial", 0, 14));
		
		jTextFieldSelectImgFolder.setBounds(160, 10, 250, 30);
		jTextFieldSelectImgFolder.setFont(new Font("Arial", 0, 13));
		jTextFieldSelectImgFolder.setEditable(false);
		
		jButtonSelectImgFolder.setBounds(420, 10, 30, 30);
		jButtonSelectImgFolder.setFont(new Font("Arial", 1, 15));
		
		
		jLabelSelectImgDefGrph.setBounds(15, 50, 140, 30);
		jLabelSelectImgDefGrph.setFont(new Font("Arial", 0, 14));
		
		jTextFieldSelectImgDefGrph.setBounds(160, 50, 250, 30);
		jTextFieldSelectImgDefGrph.setFont(new Font("Arial", 0, 13));
		jTextFieldSelectImgDefGrph.setEditable(false);
		
		jButtonSelectImgDefGrph.setBounds(420, 50, 30, 30);
		jButtonSelectImgDefGrph.setFont(new Font("Arial", 1, 15));

		
		jLabelSelectCommaSepData.setBounds(15, 90, 140, 30);
		jLabelSelectCommaSepData.setFont(new Font("Arial", 0, 14));
		
		jTextFieldSelectCommaSepData.setBounds(160, 90, 250, 30);
		jTextFieldSelectCommaSepData.setFont(new Font("Arial", 0, 13));
		jTextFieldSelectCommaSepData.setEditable(false);
		
		
		jButtonStart.setBounds(380, 130, 70, 30);
		jButtonStart.setText("Start");
		
		jLabelMessage.setBounds(80, 165, 300, 30);
		jLabelMessage.setText("");
		jLabelMessage.setHorizontalAlignment(JLabel.CENTER);
		
		jTextAreaProgress.setEditable(false);
		jScrollPaneProgress.setBounds(15, 190, 430, 160);
		jScrollPaneProgress.setViewportView(jTextAreaProgress);
		
		jButtonClose.setBounds(380, 360, 70, 30);
		jButtonClose.setText("Close");
		
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Frame**//
		setIconImage(new ImageIcon(getClass().getResource("")).getImage());
		setBounds(200, 100, 470, 430);
		setTitle("Image Data Extractor");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		//**
		// Adding Components 																	#*******A*******#
		//**
		jPanelMain.add(jLabelSelectImgFolder);
		jPanelMain.add(jTextFieldSelectImgFolder);
		jPanelMain.add(jButtonSelectImgFolder);
		
		jPanelMain.add(jLabelSelectImgDefGrph);
		jPanelMain.add(jTextFieldSelectImgDefGrph);
		jPanelMain.add(jButtonSelectImgDefGrph);
		
		jPanelMain.add(jLabelSelectCommaSepData);
		jPanelMain.add(jTextFieldSelectCommaSepData);
		
		jPanelMain.add(jButtonStart);
		
		jPanelMain.add(jLabelMessage);
		
		jPanelMain.add(jScrollPaneProgress);
		
		jPanelMain.add(jButtonClose);
		
		add(jPanelMain);
		// End of Adding Components 															#_______A_______#
	}

	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create and display the form */
		ImageDataExtractorGui gui = new ImageDataExtractorGui();
		gui.setVisible(true);
	}
}
