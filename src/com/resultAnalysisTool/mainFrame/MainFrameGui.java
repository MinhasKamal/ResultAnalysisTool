/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 10-Jun-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.mainFrame;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.UIManager;


/**
 * 
 * 
 * @author Minhas Kamal
 */
@SuppressWarnings("serial")
public class MainFrameGui extends JFrame {
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JMenuBar jMenuBarMain;
	JMenu jMenuOperation, jMenuTools, jMenuHelp;
    
	JMenuItem jMenuItemDefineImage, jMenuItemProcessImage, jMenuItemAnalyseRawData, jMenuItemDefineTable,
		jMenuItemConstructDatabase, jMenuItemAnalyseDatabase, jMenuItemProcessResult, jMenuItemDefineReport,
		jMenuItemGenerateReport, jMenuItemCloseOperation;
	JSeparator separator1, separator2, separator3;
	JMenuItem jMenuItemEmailSender, jMenuItemFilePrinter, jMenuItemFileEncoder, jMenuItemDBFileConverter,
		jMenuItemCalculator;
	JMenuItem jMenuItemInstruction, jMenuItemAbout;
	
	JPanel jPanelFileExplorer;
	JSplitPane jSplitPane;
	JPanel jPanelOperation;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public MainFrameGui() {

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
		jMenuBarMain = new JMenuBar();
		
		jMenuOperation = new JMenu();
		jMenuTools = new JMenu();
		jMenuHelp = new JMenu();
	    
		jMenuItemDefineImage = new JMenuItem();
		jMenuItemProcessImage = new JMenuItem();
		jMenuItemAnalyseRawData = new JMenuItem();
		jMenuItemDefineTable = new JMenuItem();
		jMenuItemConstructDatabase = new JMenuItem();
		jMenuItemAnalyseDatabase = new JMenuItem();
		jMenuItemProcessResult = new JMenuItem();
		jMenuItemDefineReport = new JMenuItem();
		jMenuItemGenerateReport = new JMenuItem();
		jMenuItemCloseOperation = new JMenuItem();
		
		separator1 = new JSeparator();
		separator2 = new JSeparator();
		separator3 = new JSeparator();
		
		jMenuItemEmailSender = new JMenuItem();
		jMenuItemFilePrinter = new JMenuItem();
		jMenuItemFileEncoder = new JMenuItem();
		jMenuItemDBFileConverter = new JMenuItem();
		jMenuItemCalculator = new JMenuItem();
		
		jMenuItemInstruction = new JMenuItem();
		jMenuItemAbout = new JMenuItem();
		
		jPanelFileExplorer = new JPanel();
		jSplitPane = new JSplitPane();
		jPanelOperation = new JPanel();
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jMenuOperation.setText("   Operations   ");
		jMenuTools.setText("   Tools   ");
		jMenuHelp.setText("   Help   ");
		
		jMenuItemDefineImage.setText("Define Image");
		jMenuItemProcessImage.setText("Process Image");
		jMenuItemAnalyseRawData.setText("Analyse Raw Data");
		jMenuItemDefineTable.setText("Define Table");
		jMenuItemConstructDatabase.setText("Construct Database");
		jMenuItemAnalyseDatabase.setText("Analyse Database");
		jMenuItemProcessResult.setText("Process Result");
		jMenuItemDefineReport.setText("Define Report");
		jMenuItemGenerateReport.setText("Generate Report");
		jMenuItemCloseOperation.setText("Close Operation");
		
		jMenuItemEmailSender.setText("Send Email");
		jMenuItemFilePrinter.setText("Print");
		jMenuItemFileEncoder.setText("Encode File");
		jMenuItemDBFileConverter.setText("Convert DBF File");
		jMenuItemCalculator.setText("Calculator");
		
		jMenuItemInstruction.setText("Instruction");
		jMenuItemAbout.setText("About");
		
		jPanelFileExplorer.setBackground(Color.WHITE);
		jPanelFileExplorer.setLayout(new GridLayout());
		
		jPanelOperation.setBackground(Color.WHITE);
		jPanelOperation.setLayout(new GridLayout());
		
		jSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		jSplitPane.setOneTouchExpandable(true);
		jSplitPane.setLeftComponent(jPanelFileExplorer);
		jSplitPane.setRightComponent(jPanelOperation);
		jSplitPane.setDividerLocation(250);
		jSplitPane.setDividerSize(8);

		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Frame**//
		setIconImage(new ImageIcon(getClass().getResource("/res/img/icon/RATIcon.png")).getImage());
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Result Analysis Tool");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//**
		// Adding Components 																	#*******A*******#
		//**
		jMenuOperation.add(jMenuItemDefineImage);
		jMenuOperation.add(jMenuItemProcessImage);
		jMenuOperation.add(jMenuItemAnalyseRawData);
		jMenuOperation.add(separator1);
		jMenuOperation.add(jMenuItemDefineTable);
		jMenuOperation.add(jMenuItemConstructDatabase);
		jMenuOperation.add(jMenuItemAnalyseDatabase);
		jMenuOperation.add(jMenuItemProcessResult);
		jMenuOperation.add(separator2);
		jMenuOperation.add(jMenuItemDefineReport);
		jMenuOperation.add(jMenuItemGenerateReport);
		jMenuOperation.add(separator3);
		jMenuOperation.add(jMenuItemCloseOperation);
		
		jMenuTools.add(jMenuItemEmailSender);
		jMenuTools.add(jMenuItemFilePrinter);
		jMenuTools.add(jMenuItemFileEncoder);
		jMenuTools.add(jMenuItemDBFileConverter);
		jMenuTools.add(jMenuItemCalculator);

		jMenuHelp.add(jMenuItemInstruction);
		jMenuHelp.add(jMenuItemAbout);
		
		jMenuBarMain.add(jMenuOperation);
		jMenuBarMain.add(jMenuTools);
		jMenuBarMain.add(jMenuHelp);
		
		setJMenuBar(jMenuBarMain);
		add(jSplitPane);
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
		MainFrameGui gui = new MainFrameGui();
		gui.setVisible(true);
	}
}
