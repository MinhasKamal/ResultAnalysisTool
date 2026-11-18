/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: --2015																								*
* Comment: 	*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.emailPost;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;


/**
 * 
 * 
 * @author Minhas Kamal
 */
@SuppressWarnings("serial")
public class EmailSenderGui extends JFrame {
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JPanel jPanel;
	
	JLabel jLabelprompt;
	JLabel jLabelInput;
	
	JLabel jLabelHost;
	JLabel jLabelPort;
	JLabel jLabelYourEmail;
	JLabel jLabelPassword;
	JLabel jLabelReceiver;
	JLabel jLabelSubject;
	JLabel jLabelMessage;
	JLabel jLabelAttachments;
	
	JTextField jTextFieldHost;
	JTextField jTextFieldPort;
	JTextField jTextFieldYourEmail;
	JPasswordField jPasswordFieldPassord;
	JTextField jTextFieldReceiver;
	JTextField jTextFieldSubject;
	
	JTextArea jTextAreaMessage;
	JTextArea jTextAreaAttachments;
	JButton jButtonAttachments;
	
	JScrollPane jScrollPaneMessage;
	JScrollPane jScrollPaneAttachments;
	
	JButton jButtonSend;
	
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public EmailSenderGui() {

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
		jPanel = new JPanel();
		
		jLabelprompt = new JLabel();
		jLabelInput = new JLabel();
		
		jLabelHost = new JLabel("*Host");
		jLabelPort = new JLabel("*Port");
		jLabelYourEmail = new JLabel("*Your Email");
		jLabelPassword = new JLabel("*Password");
		jLabelReceiver = new JLabel("*Receivers");
		jLabelSubject = new JLabel("Subject");
		jLabelMessage = new JLabel("Message");
		jLabelAttachments = new JLabel("Attachments");
		
		jTextFieldHost = new JTextField();
		jTextFieldPort = new JTextField();
		jTextFieldYourEmail = new JTextField();
		jPasswordFieldPassord = new JPasswordField();
		jTextFieldReceiver = new JTextField();
		jTextFieldSubject = new JTextField();
		
		jTextAreaMessage = new JTextArea();
		
		jTextAreaAttachments = new JTextArea();
		jButtonAttachments = new JButton();
		
		jScrollPaneMessage = new JScrollPane();
		jScrollPaneAttachments = new JScrollPane();
		
		jButtonSend = new JButton("Send");
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jPanel.setBackground(new Color(255, 255, 255));
		jPanel.setLayout(new MigLayout());
		
		jLabelprompt.setLayout(new MigLayout());

		jLabelInput.setLayout(new MigLayout());
		
		jTextFieldReceiver.setToolTipText("Seperate email addresses by  ' ; '");
		
		jTextAreaAttachments.setBackground(Color.white);
		
		jButtonAttachments.setText("...");
		jButtonAttachments.setFont(new Font("Arial", 1, 15));
		
		jScrollPaneMessage.setViewportView(jTextAreaMessage);
		jScrollPaneAttachments.setViewportView(jTextAreaAttachments);
		
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Frame**//
		setIconImage(new ImageIcon(getClass().getResource("")).getImage());
		setBackground(Color.white);
		setResizable(false);
		setBounds(200, 100, 450, 460);
		setTitle("Email Sender");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new MigLayout());
		//setResizable(false);
		
		//**
		// Adding Components 																	#*******A*******#
		//**
		
		jLabelprompt.add(jLabelHost, "north, w 90:90:100, h 20:30:30");
		jLabelprompt.add(jLabelPort, "north, w 90:90:100, h 20:30:30");
		jLabelprompt.add(jLabelYourEmail, "north, w 70:90:100, h 20:30:30");
		jLabelprompt.add(jLabelPassword, "north, w 70:90:100, h 20:30:30");
		jLabelprompt.add(jLabelReceiver, "north, w 70:90:100, h 20:30:30");
		jLabelprompt.add(jLabelSubject, "north, w 70:90:100, h 20:30:30");
		jLabelprompt.add(jLabelMessage, "north, w 70:90:100, h 20:30:30, gapbottom 10:70:90");
		jLabelprompt.add(jLabelAttachments, "north, w 70:90:100, h 20:30:30, gapbottom 10:70:90");
		
		jLabelInput.add(jTextFieldHost, "north, w 200:310:400, h 20:30:30");
		jLabelInput.add(jTextFieldPort, "north, w 200:310:400, h 20:30:30");
		jLabelInput.add(jTextFieldYourEmail, "north, w 200:310:400, h 20:30:30");
		jLabelInput.add(jPasswordFieldPassord, "north, w 200:310:400, h 20:30:30");
		jLabelInput.add(jTextFieldReceiver, "north, w 200:310:400, h 20:30:30");
		jLabelInput.add(jTextFieldSubject, "north, w 200:310:400, h 20:30:30");
		jLabelInput.add(jScrollPaneMessage, "north, w 90:310:400, h 70:100:200");
		jLabelInput.add(jButtonAttachments, "east, w 20:30:30, h 20:30:30, gapbottom 70");
		jLabelInput.add(jScrollPaneAttachments, "north, w 90:280:280, h 70:100:200");
		
		jPanel.add(jLabelprompt, "w 100:100:200, h 340:400:430, gapleft 5:10:30");
		jPanel.add(jLabelInput, "w 200:320:500, h 340:400:430");
		
		add(jPanel, "west, north, w 60:450:500, h 350:400:500");
		add(jButtonSend, "east, w 60:70:100, h 30:30:40, gaptop 2, gapbottom 6, gapright 10");
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
		EmailSenderGui gui = new EmailSenderGui();
		gui.setVisible(true);
	}
}
