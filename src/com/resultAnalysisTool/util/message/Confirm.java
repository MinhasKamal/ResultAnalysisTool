/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: 03-Jun-2014																							*
* Comment: 	*									
*************************************************************************************************************/

package com.resultAnalysisTool.util.message;


import javax.swing.*;

@SuppressWarnings("serial")
public class Confirm extends JDialog {
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	public static final boolean POSITIVE_DECISION = true;
	public static final boolean NEGATIVE_DECISION = false;
	
	private JLabel jLabelMain;
	private JLabel jLabelIcon;
	private JLabel[] jLabelTexts;	
	private JButton jButtonYes, jButtonNo;
	
	//other variables
	private String[] message;
	private boolean decision;
	// End of Variable Declaration 																			#_______D_______#

	
	/**
	 * Constructor.
	 * Shows message to the user & takes confirmation over an important decision.
	 * @param message main part of message to be shown
	 */
	public Confirm(String message) {
		this.message = getMessage(message);
		decision=NEGATIVE_DECISION;

		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables, placing them all to specific space on the frame and adding action
	 * listener to them. Also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		//**
		// Initialization 																					#*******I*******#
		//**
		jLabelMain = new JLabel();
		jLabelIcon = new JLabel();
		jLabelTexts = new JLabel[3];	
		jButtonYes = new JButton();
		jButtonNo = new JButton();
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		jLabelMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/img/background/ConfirmBackground.png")));
        jLabelMain.setBounds(0, 0, 400, 200);
        jLabelMain.setLayout(null);
        
        
        jLabelIcon.setBounds(10, 33, 70, 70);
       	jLabelIcon.setIcon(new ImageIcon(getClass().getResource("/res/img/icon/QuestionIcon.png")));
        
       	for(int i=0; i<3; i++){
       		jLabelTexts[i] = new JLabel();
	       	jLabelTexts[i].setBounds(100, 40+20*i, 280, 20);
	        jLabelTexts[i].setFont(new java.awt.Font("Lucida", 0, 16));
	        jLabelTexts[i].setText(message[i]);
       	}
       	
        jButtonYes.setText("Yes");
        jButtonYes.setBounds(240, 130, 60, 30);
        jButtonYes.setBackground(new java.awt.Color(212, 227, 250));
        jButtonYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonYesActionPerformed(evt);
            }
        });
	    
	    jButtonNo.setText("No");
	    jButtonNo.setBounds(310, 130, 60, 30);
	    jButtonNo.setBackground(new java.awt.Color(212, 227, 250));
	    jButtonNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNoActionPerformed(evt);
            }
        });
		// End of Setting Bounds and Attributes 															#_______S_______#

		//**
		// Adding Components 																				#*******A*******#
		//**
		jLabelMain.add(jLabelIcon);
		for(int i=0; i<3; i++){
			jLabelMain.add(jLabelTexts[i]);
		}
		jLabelMain.add(jButtonYes);
		jLabelMain.add(jButtonNo);
		// End of Adding Components 																		#_______A_______#

		//**Setting Criterion of the Frame**//
		setIconImage(new ImageIcon(getClass().getResource("")).getImage());
		setBounds(240, 200, 400, 200);
		setTitle("Confirm");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setModal(true);
		add(jLabelMain);
		setVisible(true);
	}

	//**
	// Action Events 																						#********AE*******#
	//**
	private void jButtonYesActionPerformed(java.awt.event.ActionEvent evt){
		decision=POSITIVE_DECISION;
		dispose();
	}
	
	private void jButtonNoActionPerformed(java.awt.event.ActionEvent evt){
		decision=NEGATIVE_DECISION;
		dispose();
	}
	// End of Action Events 																				#________AE_______#

	/**///Main Method
	public static void main(String args[]) {
		/*// Set the Web look and feel //*/
		try {
			javax.swing.UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create and display the form */
		Confirm confirm = new Confirm("Hi! You are grounded for your \ninsignificant and nonsence \nactions.");
		System.out.println(confirm.getDecision());
	}
	/**/

	//**
	// Auxiliary Methods 																					#********AM*******#
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
	
	public boolean getDecision(){
		return decision;
	}
	// End of Auxiliary Methods 																			#________AM_______#
}
