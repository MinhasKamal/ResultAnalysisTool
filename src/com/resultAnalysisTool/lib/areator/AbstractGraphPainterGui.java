/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 25-Jan-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.lib.areator;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import net.miginfocom.swing.MigLayout;


/**
 * 
 * 
 * @author Minhas Kamal
 */
@SuppressWarnings("serial")
public class AbstractGraphPainterGui extends JLabel {
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JLabel jLabelArea;
	protected JLabel jLabelGraphArea;
	JButton jButtonDone;
	
	protected JLabel jLabelProperty;
	
	JLabel jLabelMove;
	JButton[] jButtonsMove;
	JButton jButtonMoveDegree;
	
	JLabel jLabelResize;
	JButton[] jButtonsResize;
	JButton jButtonResizeDegree;
	
	protected JLabel jLabelOthers;
	JButton jButtonCopy;
	JButton jButtonRemove;
	
	JButton jButtonLoadGraph;
	JButton jButtonLoadImage;
	
	JLabel jLabelWindow;
	JScrollPane jScrollPaneWindow;
	JLabel jLabelImage;
	protected ImageIcon imagePage;
	
	JLabel jLabelTempGraphArea;
	
	JLabel jLabelCursorLocation;
	
	//keep reference of all graph areas
	protected LinkedList<GraphArea> graphAreaList;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public AbstractGraphPainterGui() {
		
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
		jLabelArea = new JLabel();
		jLabelGraphArea = new JLabel();
		jButtonDone = new JButton();
		
		jLabelProperty = new JLabel();
		
		jLabelMove = new JLabel();
		jButtonsMove = new JButton[4];
		jButtonMoveDegree = new JButton();
		
		jLabelResize = new JLabel();
		jButtonsResize = new JButton[4];
		jButtonResizeDegree = new JButton();

		jLabelOthers = new JLabel();
		jButtonCopy = new JButton();
		jButtonRemove = new JButton();
		
		jButtonLoadGraph = new JButton();
		jButtonLoadImage = new JButton();
		
		jLabelWindow = new JLabel();
		jScrollPaneWindow = new JScrollPane();
		jLabelImage = new JLabel();
		imagePage = new ImageIcon();
		
		jLabelTempGraphArea = new JLabel();
		
		jLabelCursorLocation = new JLabel();
		
		graphAreaList = new LinkedList<>();	//to store addresses of painted graphs
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jLabelArea.setLayout(new MigLayout());
		jLabelGraphArea.setLayout(new MigLayout());

		jButtonDone.setText("Done");
		jButtonDone.setFont(new Font("Arial", 0, 14));
		jButtonDone.setBackground(new Color(200, 180, 180));
		
		jLabelProperty.setLayout(new MigLayout());
		
		jLabelMove.setBorder(BorderFactory.createTitledBorder("Move"));
		
		for(int i=0; i<4; i++){
			jButtonsMove[i] = new JButton();
		}
		jButtonsMove[0].setBounds(35, 20, 40, 30);
		jButtonsMove[0].setIcon(new javax.swing.
				ImageIcon(getClass().getResource("/res/img/icon/UpIcon.png")));
		jButtonsMove[1].setBounds(10, 20, 30, 70);
		jButtonsMove[1].setIcon(new javax.swing.
				ImageIcon(getClass().getResource("/res/img/icon/LeftIcon.png")));
		jButtonsMove[2].setBounds(35, 60, 40, 30);
		jButtonsMove[2].setIcon(new javax.swing.
				ImageIcon(getClass().getResource("/res/img/icon/DownIcon.png")));
		jButtonsMove[3].setBounds(70, 20, 30, 70);
		jButtonsMove[3].setIcon(new javax.swing.
				ImageIcon(getClass().getResource("/res/img/icon/RightIcon.png")));
		
		jButtonMoveDegree.setBounds(37, 47, 36, 16);
		jButtonMoveDegree.setFont(new Font("Arial", 0, 10));
		jButtonMoveDegree.setText("1");
		
		jLabelResize.setBorder(BorderFactory.createTitledBorder("Resize"));
		
		for(int i=0; i<4; i++){
			jButtonsResize[i] = new JButton();
		}
		jButtonsResize[0].setBounds(35, 20, 40, 30);
		jButtonsResize[0].setIcon(new javax.swing.
				ImageIcon(getClass().getResource("/res/img/icon/UpIcon.png")));
		jButtonsResize[1].setBounds(10, 20, 30, 70);
		jButtonsResize[1].setIcon(new javax.swing.
				ImageIcon(getClass().getResource("/res/img/icon/LeftIcon.png")));
		jButtonsResize[2].setBounds(35, 60, 40, 30);
		jButtonsResize[2].setIcon(new javax.swing.
				ImageIcon(getClass().getResource("/res/img/icon/DownIcon.png")));
		jButtonsResize[3].setBounds(70, 20, 30, 70);
		jButtonsResize[3].setIcon(new javax.swing.
				ImageIcon(getClass().getResource("/res/img/icon/RightIcon.png")));
		
		jButtonResizeDegree.setBounds(37, 47, 36, 16);
		jButtonResizeDegree.setFont(new Font("Arial", 0, 10));
		jButtonResizeDegree.setText("1");
		
		jLabelOthers.setBorder(BorderFactory.createTitledBorder("Others"));
		
		jButtonCopy.setBounds(5, 20, 100, 20);
		jButtonCopy.setText("Copy");
		jButtonCopy.setFont(new Font("Arial", 0, 11));
		
		jButtonRemove.setBounds(5, 40, 100, 20);
		jButtonRemove.setText("Remove");
		jButtonRemove.setFont(new Font("Arial", 0, 11));
		
		jButtonLoadGraph.setText("Load Graph");
		jButtonLoadImage.setText("Load Image");
		
		jLabelWindow.setLayout(new GridLayout());
		
		jLabelImage.setVerticalAlignment(TOP);
		jLabelImage.setIcon(imagePage);
		
		jScrollPaneWindow.setViewportView(jLabelImage);
		jScrollPaneWindow.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		jScrollPaneWindow.setAutoscrolls(true);
		
		jLabelTempGraphArea.setBorder(BorderFactory.createDashedBorder(null));
		jLabelTempGraphArea.setBounds(0, 0, 0, 0);
		
		jLabelCursorLocation.setSize(40, 18);
		jLabelCursorLocation.setLocation(-80, -80);
		jLabelCursorLocation.setFont(new Font("Arial", 0, 8));
		jLabelCursorLocation.setHorizontalAlignment(CENTER);
		jLabelCursorLocation.setForeground(new Color(80, 50, 40));
		jLabelCursorLocation.setBorder(BorderFactory.createLineBorder(new Color(80, 50, 40), 1, false));

		//**Setting Criterion of the Label**//
		//setIcon(new ImageIcon(getClass().getResource("/res/img/background/ImageAreaDefinerBackground.png")));
		setLayout(new MigLayout());
		// End of Setting Bounds and Attributes 												#_______S_______#

		//**
		// Adding Components 																	#*******A*******#
		//**
		add(jLabelProperty, "west, w 125:180:250");
		add(jLabelArea, "north, h 50:60:60");
		add(jLabelWindow, "w 200:1000:1000, h 200:800:800");
		
		jLabelImage.add(jLabelTempGraphArea);
		jLabelImage.add(jLabelCursorLocation);
		
		jLabelArea.add(jLabelGraphArea, "w 300:850:900, h 30:40:50");
		jLabelArea.add(jButtonDone, "gapleft 10:350:800, w 100, gaptop 7");
		
		for(int i=0; i<4; i++){
			jLabelMove.add(jButtonsMove[i]);
		}
		jLabelMove.add(jButtonMoveDegree);
		
		for(int i=0; i<4; i++){
			jLabelResize.add(jButtonsResize[i]);
		}
		jLabelResize.add(jButtonResizeDegree);

		
		jLabelOthers.add(jButtonCopy);
		jLabelOthers.add(jButtonRemove);

		jLabelProperty.add(jLabelMove, "gaptop :45:, w 110, h 100!, wrap");
		jLabelProperty.add(jLabelResize, "gaptop :15:, w 110, h 100!, wrap");
		jLabelProperty.add(jLabelOthers, "gaptop :15:, gapbottom 10:240:300, w 110, h 90!, wrap");
		
		jLabelProperty.add(jButtonLoadGraph, "w 110, h 30, wrap");
		jLabelProperty.add(jButtonLoadImage, "w 110, h 30, wrap");
		
		jLabelWindow.add(jScrollPaneWindow);
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
		/* Create and display the form */
		AbstractGraphPainterGui gui = new AbstractGraphPainterGui();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 10, 950, 700);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.add(gui);
		
		gui.jLabelImage.setIcon(new ImageIcon("C:\\Users\\admin\\Desktop\\Temporary\\OMR1.png"));
	}
}
