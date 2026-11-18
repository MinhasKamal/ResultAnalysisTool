/**
 * Developer: Minhas Kamal(BSSE-0509, IIT, DU)
 * Date: 01-Jan-2014
**/

package com.resultAnalysisTool.help.about;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.*;

import com.resultAnalysisTool.util.fileReadWrite.FileIO;


@SuppressWarnings("serial")
public class About extends JFrame{
	/**
	 * Variable Declaration												#*******D*******#
	**/
	private JLabel jLabelMain;
	private JScrollPane jScrollPane;
	private JTextPane jTextPaneNotification;
	private JButton jButtonOK;

	private String data;
	//End of Variable Declaration										#_______D_______#
	
	
	//**  Constructor  **//
	public About() {
		/**
		 * write your message here/////////////////////////////////////////////////////////////////
		**/
		try {
			data=FileIO.readWholeFile(getClass().getResourceAsStream("/res/txt/About.info"));
		} catch (Exception e) {
			data = "error";
		}
				
		InitialComponents();
	}
	
	
	private void InitialComponents(){

		/**
		 * Initialization 												#*******I*******#
		**/
		jLabelMain=new JLabel();
		jScrollPane = new JScrollPane();
		jTextPaneNotification=new JTextPane();
		jButtonOK = new JButton();
    	//End of Initialization 										#_______I_______#
		
		
		
		
		/**
		 * Setting Bounds and Attributes of the Elements				#*******S*******#
		**/
		jScrollPane.setViewportView(jTextPaneNotification);
		jScrollPane.setBounds(10, 10, 472, 503);
		jScrollPane.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		
		jTextPaneNotification.setText(data);
		jTextPaneNotification.setForeground(new java.awt.Color(102, 0, 106));
		jTextPaneNotification.setSelectedTextColor(new java.awt.Color(255, 170, 255));
		jTextPaneNotification.setSelectionColor(new java.awt.Color(80, 0, 80));
		jTextPaneNotification.setFont(new java.awt.Font("Gabriola", 0, 19)); // NOI18N
		jTextPaneNotification.setEditable(false);
		jTextPaneNotification.setCaretPosition(0);

		
		
		jButtonOK.setText("OK");
		jButtonOK.setFont(new Font("Tahoma", 0, 17));
		jButtonOK.setBounds(390, 526, 80, 35);
		jButtonOK.setBackground(new java.awt.Color(212, 227, 250));
		jButtonOK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonOKActionPerformed(evt);
			}
		}); 
		jButtonOK.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
	    	put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER,0), "ENTER_pressed");
	    jButtonOK.getActionMap().put("ENTER_pressed", new AbstractAction() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	jButtonOKActionPerformed(evt);
	        }
	    });
        
        jLabelMain.setIcon(new ImageIcon(getClass().getResource("/res/img/background/AboutBackground.png")));
        jLabelMain.setBounds(0, 0, 500, 600);
        jLabelMain.setLayout(null);
    	//End of Setting Bounds and Attributes 							#_______S_______#
		
		
		
		
		/**
		 * Adding Components											#*******A*******#
		**/
		jLabelMain.add(jScrollPane); 
		jLabelMain.add(jButtonOK);     	
    	//End of Adding Components										#_______A_______#
		
        
        //Setting Criterion of the Frame//
    	setIconImage(new ImageIcon(getClass().getResource("")).getImage());
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About");
        setResizable(false);
        setBounds(230, 100, 500, 600);
        setLayout(null);
        add(jLabelMain);
	}



	/**
	 * Actions/////////////////////////////////////////////////////////////////////////////////////////
	**/
	private void jButtonOKActionPerformed(ActionEvent evt){	
		dispose();
	}
	


    /**
     * Main Method//////////////////////////////
    **/
    public static void main(String args[]) {
        /* Set the NIMBUS look and feel */
    	try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");					
		} catch (Exception ex) {
			//do nothing if operation unsuccessful
		}

        /* Create and display the form */
        About about = new About();
        about.setVisible(true);
    }
}
