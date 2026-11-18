/**
 * Developer: Minhas Kamal(BSSE-0509, IIT, DU)
 * Date: 01-Jan-2014
**/

package com.resultAnalysisTool.help.instruction;

import java.awt.event.ActionEvent;
import javax.swing.*;

import com.resultAnalysisTool.util.fileReadWrite.FileIO;


@SuppressWarnings("serial")
public class Instruction extends JFrame{
	/**
	 * Variable Declaration												#*******D*******#
	**/
	private JPanel jPanelMain;
	private JPanel jPanelSub;
	private JScrollPane jScrollPane;
	private JTextPane jTextPaneNotification;
	private JButton jButtonOK;
	
	private String data;
	
	//End of Variable Declaration										#_______D_______#
	
	
	//**  Constructor  **//
	public Instruction(String data) {
		this.data=data;
		
		InitialComponents();
	}

	public Instruction() {
		try {
			data=FileIO.readWholeFile(getClass().getResourceAsStream("/res/txt/Instruction.info"));
		} catch (Exception e) {
			data = "error";
		}
		
		InitialComponents();
	}
	
	
	//**  Method for Initializing  **//
	private void InitialComponents(){
		/**
		 * Initialization 												#*******I*******#
		**/
		jPanelMain=new JPanel();
		jPanelSub=new JPanel();
		jScrollPane = new JScrollPane();
		jTextPaneNotification=new JTextPane();
		jButtonOK = new JButton();
    	//End of Initialization 										#_______I_______#
		
		
		/**
		 * Setting Bounds and Attributes of the Elements				#*******S*******#
		**/
		jScrollPane.setViewportView(jTextPaneNotification);
		jScrollPane.setBackground(new java.awt.Color(199, 173, 176));
		jScrollPane.setBounds(10, 10, 473, 486);
		jScrollPane.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		
		jTextPaneNotification.setText(data);
		jTextPaneNotification.setForeground(new java.awt.Color(102, 0, 106));
		jTextPaneNotification.setSelectedTextColor(new java.awt.Color(255, 170, 255));
		jTextPaneNotification.setSelectionColor(new java.awt.Color(80, 0, 80));
		jTextPaneNotification.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
		jTextPaneNotification.setEditable(false);
		
		
		jButtonOK.setText("OK");
		jButtonOK.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
		jButtonOK.setBounds(380, 14, 70, 35);
		jButtonOK.setBackground(new java.awt.Color(207, 177, 177));
		jButtonOK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonOKActionPerformed(evt);
			}
		});
		
		 
        jPanelMain.setBackground(new java.awt.Color(106, 53, 53));
        jPanelMain.setBounds(0, 0, 500, 510);
        jPanelMain.setLayout(null);
        
        jPanelSub.setBackground(new java.awt.Color(146, 93, 93));
        jPanelSub.setBounds(0, 510, 500, 65);
        jPanelSub.setLayout(null);
    	//End of Setting Bounds and Attributes 							#_______S_______#
		
		
		
		/**
		 * Adding Components											#*******A*******#
		**/

        jPanelMain.add(jScrollPane); //jPanelMain.add(jTextPaneNotification); 
        jPanelSub.add(jButtonOK);
    	//End of Adding Components										#_______A_______#
       
        
        //Setting Criterion of the Frame//
    	setIconImage(new ImageIcon(getClass().getResource("")).getImage());
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Instruction");
        setResizable(false);
        setBounds(230, 100, 500, 600);
        setLayout(null);
        add(jPanelMain); add(jPanelSub);
        setAlwaysOnTop(true);
		
	}



	/**
	 * Action Events													#********AE*******#
	**/
	private void jButtonOKActionPerformed(ActionEvent evt){	
		dispose();
	}
    //End of Action Events												#________AE_______#

	    


	//**  Main Method  **//
    public static void main(String args[]) {
        /* Set the NIMBUS look and feel */
    	try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");					
		} catch (Exception ex) {
			//do nothing if operation unsuccessful
		}
        

        /* Create and display the form */
        Instruction gui = new Instruction();
        gui.setVisible(true);
    }
}
