/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 16-Mar-2015																								*
* Modification: 14-May-2015																						*
****************************************************************************************************************/

package com.resultAnalysisTool.lib.jexplore;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;


/**
 * 
 * 
 * @author Minhas Kamal
 */
@SuppressWarnings("serial")
public class FileExplorerGui extends JLabel {
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JTextField jTextFieldRootPath;
	JButton jButtonGo;
	JPanel jPanelPath;
	
	JButton jButtonOpen;
	JButton jButtonRefresh;
	JButton jButtonDelete;
	JPanel jPanelControl;
	
	JPanel jPanelView;
	JScrollPane jScrollPane;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public FileExplorerGui() {

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
		jTextFieldRootPath = new JTextField();
		jButtonGo = new JButton("...");
		jPanelPath = new JPanel();
		
		jPanelControl = new JPanel();
		jPanelView = new JPanel();
		jScrollPane = new JScrollPane();
		
		jButtonOpen = new JButton("Open");
		jButtonRefresh = new JButton("Refresh");
		jButtonDelete = new JButton("Delete");
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jTextFieldRootPath.setEditable(false);
		
		jPanelPath.setBackground(new Color(255, 255, 255));
		jPanelPath.setBorder(BorderFactory.createLineBorder(new Color(95, 95, 95), 1, false));
		jPanelPath.setLayout(new MigLayout());
		
		jPanelControl.setBackground(new Color(255, 255, 255));
		jPanelControl.setBorder(BorderFactory.createLineBorder(new Color(95, 95, 95), 1, false));
		jPanelControl.setLayout(new MigLayout());
		
		jPanelView.setBackground(new Color(255, 255, 255));
		jPanelView.setBorder(BorderFactory.createLineBorder(new Color(95, 95, 95), 1, false));
		jPanelView.setLayout(new MigLayout());
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Label**//
		setLayout(new MigLayout());

		//**
		// Adding Components 																	#*******A*******#
		//**
		jPanelPath.add(jTextFieldRootPath, "h 30:35:50, w 120:515:600");
		jPanelPath.add(jButtonGo, "h 30:35:50, w 30:35:50");
		add(jPanelPath, "north, h 45:50:60, w 150:550:700");
		
		jPanelControl.add(jButtonOpen, "h 30:35:50, w 60:100:200");
		jPanelControl.add(jButtonRefresh, "h 30:35:50, w 60:100:200");
		jPanelControl.add(jButtonDelete, "h 30:35:50, w 60:100:200");		
		add(jPanelControl, "h 45:50:60, w 150:550:700");
		
		jScrollPane.setViewportView(jPanelView);
		add(jScrollPane, "south,  h 100:800:900, w 150:550:700");
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
		FileExplorerGui gui = new FileExplorerGui();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 5, 500, 700);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.add(gui);
	}
}
