/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: 03-Jun-2014																							*
* Modified: 18-May-2015																							*
*************************************************************************************************************/

package com.resultAnalysisTool.util.message;

import javax.swing.*;

@SuppressWarnings("serial")
public class Message extends JDialog {
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	public static final int MESSAGE_TYPE_PLAIN_MESSAGE = 0;
	public static final int MESSAGE_TYPE_CONFIRMATION_MESSAGE = 1;
	public static final int MESSAGE_TYPE_WARNING_MESSAGE = 210;
	public static final int MESSAGE_TYPE_ERROR_MESSAGE = 420;
	
	private JLabel jLabelMain;
	private JLabel jLabelIcon;
	private JLabel[] jLabelMessages;	
	private JButton jButtonOK;
	
	//other variables
	private String[] messages;
	private int messageType;
	// End of Variable Declaration 																			#_______D_______#

	
	/**
	 * Constructor.
	 * Shows message to the user to guide or give information of the operation status.
	 * @param message main part of message to be shown
	 * @param messageType type of message, like- plain message(0), confirmation message(1), warning message(210) and
	 * 	error message(420)
	 */
	public Message(String message, int messageType) {
		this.messages = getMessage(message);
		this.messageType=messageType;

		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables, placing them all to specific space on the frame and adding action
	 * listener to them. Also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		//**
		// Initialization 																				#*******I*******#
		//**
		jLabelMain = new JLabel();
		jLabelIcon = new JLabel();
		jLabelMessages = new JLabel[3];
		jButtonOK = new JButton();
		// End of Initialization																		#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 												#*******S*******#
		//**
		jLabelMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/img/background/MessageBackground.png")));
        jLabelMain.setBounds(0, 0, 400, 220);
        jLabelMain.setLayout(null);
        
        
        jLabelIcon.setBounds(10, 33, 70, 70);
        if(messageType==MESSAGE_TYPE_CONFIRMATION_MESSAGE){
        	jLabelIcon.setIcon(new ImageIcon(getClass().getResource("/res/img/icon/ConfirmIcon.png")));
        }else if(messageType==MESSAGE_TYPE_WARNING_MESSAGE){
        	jLabelIcon.setIcon(new ImageIcon(getClass().getResource("/res/img/icon/WarningIcon.png")));
        }else if(messageType==MESSAGE_TYPE_ERROR_MESSAGE){
        	jLabelIcon.setIcon(new ImageIcon(getClass().getResource("/res/img/icon/ErrorIcon.png")));
        }else {
        	jLabelIcon.setIcon(new ImageIcon(getClass().getResource("/res/img/icon/MessageIcon.png")));
        }
        
        for(int i=0; i<3; i++){
        	jLabelMessages[i] = new JLabel();
        	jLabelMessages[i].setBounds(100, 40+20*i, 280, 20);
        	jLabelMessages[i].setFont(new java.awt.Font("Lucida", 0, 16));
        	jLabelMessages[i].setText(messages[i]);
        }
        
        jButtonOK.setText("OK");
        jButtonOK.setBounds(310, 130, 60, 30);
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
		// End of Setting Bounds and Attributes 														#_______S_______#

		//**
		// Adding Components 																			#*******A*******#
		//**
		jLabelMain.add(jLabelIcon);
		jLabelMain.add(jButtonOK);
		for(int i=0; i<3; i++){
			jLabelMain.add(jLabelMessages[i]);
		}
		// End of Adding Components 																	#_______A_______#

		//**Setting Criterion of the Frame**//
		setIconImage(new ImageIcon(getClass().getResource("")).getImage());
		setBounds(240, 200, 400, 200);
		setTitle("Message");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setModal(true);
		add(jLabelMain);
		setVisible(true);
	}

	//**
	// Action Events 																				#********AE*******#
	//**
	private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt){
		dispose();
	}
	// End of Action Events 																		#________AE_______#

	/**///Main Method
	public static void main(String args[]) {
		/*// Set the Web look and feel //*/
		try {
			javax.swing.UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create and display the form */
		new Message("Hi You are grounded for your \ninsignificant and nonsence \nactions.", 210);
		
	}
	/**/

	//**
	// Auxiliary Methods 																			#********AM*******#
	//**
	private String[] getMessage(String message){
		String[] messages = new String[3];
		
		if(message.contains("\n")){
			messages[0] = message.substring(0, message.indexOf("\n"));
			message = message.substring(message.indexOf("\n")+1);
			if(message.contains("\n")){
				messages[1] = message.substring(0, message.indexOf("\n"));
				messages[2] = message.substring(message.indexOf("\n")+1);
				
			}else{
				messages[1] = message;
			}
		}else{
			messages[0] = message;
		}
		
		
		return messages;
	}
	// End of Auxiliary Methods 																	#________AM_______#
}
