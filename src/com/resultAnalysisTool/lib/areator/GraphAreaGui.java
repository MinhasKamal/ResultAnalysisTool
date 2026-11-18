/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 24-Jan-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.lib.areator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.Border;


/**
 * 
 * 
 * @author Minhas Kamal
 */
@SuppressWarnings("serial")
public class GraphAreaGui extends JComponent{
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	protected JToggleButton jTButtonProperty;
	
	//pop up menue
	protected JPopupMenu jPMenuProperty;
	
	protected JLabel jLabelPopupMenu;
	
	JLabel jLabelMove;
	JButton[] jButtonsMove;
	JLabel jLabelResize;
	JButton[] jButtonsResize;
	protected JLabel jLabelSpecial;
	
	Border borderSimple, borderSelected;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public GraphAreaGui() {

		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the component. It also specifies criteria of the main component.
	 */
	private void initialComponent() {
		//**
		// Initialization 																		#*******I*******#
		//**
		jTButtonProperty = new JToggleButton();
		
		jPMenuProperty = new JPopupMenu();
		
		jLabelPopupMenu = new JLabel();
		
		jLabelMove = new JLabel();
		jButtonsMove = new JButton[4];
		jLabelResize = new JLabel();
		jButtonsResize = new JButton[4];
		jLabelSpecial = new JLabel();
		
		borderSimple = BorderFactory.createDashedBorder(null);
		borderSelected = BorderFactory.createLineBorder(Color.black, 2, false);
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jTButtonProperty.setBounds(0, 0, 20, 20);
		jTButtonProperty.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jTButtonProperty.setText("G");
		jTButtonProperty.setBorder(null);
		jTButtonProperty.setFont(new Font("Arial", Font.PLAIN, 10));
		jTButtonProperty.setComponentPopupMenu(jPMenuProperty);
		
		jPMenuProperty.setLayout(new BorderLayout());
		jPMenuProperty.setPopupSize(235, 200);
		
		jLabelPopupMenu.setBounds(0, 0, 250, 220);
		
		jLabelMove.setBounds(5, 5, 110, 90);
		jLabelMove.setBorder(BorderFactory.createTitledBorder("Move"));
		
		for(int i=0; i<4; i++){
			jButtonsMove[i] = new JButton();
		}
		jButtonsMove[0].setBounds(35, 20, 40, 30);
		jButtonsMove[0].setIcon(new ImageIcon(getClass().getResource("/res/img/icon/UpIcon.png")));
		jButtonsMove[1].setBounds(10, 20, 30, 60);
		jButtonsMove[1].setIcon(new ImageIcon(getClass().getResource("/res/img/icon/LeftIcon.png")));
		jButtonsMove[2].setBounds(35, 50, 40, 30);
		jButtonsMove[2].setIcon(new ImageIcon(getClass().getResource("/res/img/icon/DownIcon.png")));
		jButtonsMove[3].setBounds(70, 20, 30, 60);
		jButtonsMove[3].setIcon(new ImageIcon(getClass().getResource("/res/img/icon/RightIcon.png")));
		
		jLabelResize.setBounds(120, 5, 110, 90);
		jLabelResize.setBorder(BorderFactory.createTitledBorder("Resize"));
		
		for(int i=0; i<4; i++){
			jButtonsResize[i] = new JButton();
		}
		jButtonsResize[0].setBounds(35, 20, 40, 30);
		jButtonsResize[0].setIcon(new ImageIcon(getClass().getResource("/res/img/icon/UpIcon.png")));
		jButtonsResize[1].setBounds(10, 20, 30, 60);
		jButtonsResize[1].setIcon(new ImageIcon(getClass().getResource("/res/img/icon/LeftIcon.png")));
		jButtonsResize[2].setBounds(35, 50, 40, 30);
		jButtonsResize[2].setIcon(new ImageIcon(getClass().getResource("/res/img/icon/DownIcon.png")));
		jButtonsResize[3].setBounds(70, 20, 30, 60);
		jButtonsResize[3].setIcon(new ImageIcon(getClass().getResource("/res/img/icon/RightIcon.png")));
		
		jLabelSpecial.setBounds(5, 100, 225, 90);
		jLabelSpecial.setBorder(BorderFactory.createTitledBorder("Special Property"));
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Label**//
		setBorder(borderSimple);
		setLayout(null);
		
		//**
		// Adding Components 																	#*******A*******#
		//**
		for(int i=0; i<4; i++){
			jLabelMove.add(jButtonsMove[i]);
		}
		for(int i=0; i<4; i++){
			jLabelResize.add(jButtonsResize[i]);
		}
		jLabelPopupMenu.add(jLabelMove);
		jLabelPopupMenu.add(jLabelResize);
		jLabelPopupMenu.add(jLabelSpecial);
		
		jPMenuProperty.add(jLabelPopupMenu);
		
		add(jTButtonProperty);
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
		GraphAreaGui gui = new GraphAreaGui();
		gui.setBounds(20, 20, 200, 50);
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 10, 350, 200);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(null);
		jFrame.add(gui);
	}
}
