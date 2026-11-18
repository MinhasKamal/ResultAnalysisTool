/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 16-05-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.emailPost;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import com.resultAnalysisTool.util.fileChoose.FileChooser;


/**
 * 
 * 
 * @author Minhas Kamal
 */
public class EmailSender{
	// GUI Declaration
	private EmailSenderGui gui;
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	private JTextArea jTextAreaAttachments;
	private JButton jButtonAttachments;
	
	private JButton jButtonSend;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public EmailSender() {

		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new EmailSenderGui();
		gui.setVisible(true);
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jTextAreaAttachments = gui.jTextAreaAttachments;
		jButtonAttachments = gui.jButtonAttachments;
		
		jButtonSend = gui.jButtonSend;
		// End of Assignation																	#_______A_______#
		gui.jTextFieldHost.setText("smtp.gmail.com");
		gui.jTextFieldPort.setText("465");
		
		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jButtonAttachments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonAttachmentsActionPerformed(evt);
            }
        });
		
		jButtonSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonSendActionPerformed(evt);
            }
        });
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	private void jButtonAttachmentsActionPerformed(ActionEvent evt) {
		String filePath = new FileChooser().chooseFilePathFromComputer();
		
		if(!filePath.contains(".")){
			return ;
		}
		
		jTextAreaAttachments.append(filePath+"\n");
	}
	
	private void jButtonSendActionPerformed(ActionEvent evt) {
		String host = gui.jTextFieldHost.getText();
		String port = gui.jTextFieldPort.getText();
		String userName = gui.jTextFieldYourEmail.getText();
		String password = new String(gui.jPasswordFieldPassord.getPassword());
		String[] toAddresses = gui.jTextFieldReceiver.getText().split(";");
		String subject = gui.jTextFieldSubject.getText();
		String message = gui.jTextAreaMessage.getText();
		String[] attachFiles = jTextAreaAttachments.getText().split("\n");
		
		try {
			if(!host.isEmpty() && !port.isEmpty() && !userName.isEmpty() && !password.isEmpty() && 
					!toAddresses[0].isEmpty() && (!subject.isEmpty() || !message.isEmpty()) ){
				
				if(attachFiles[0].isEmpty()){
					attachFiles = new String[]{};
				}
				
				sendEmailWithAttachments(host, port, userName, password, toAddresses, subject, message, attachFiles);
				
				new com.resultAnalysisTool.util.message.Message("Email is sent.", 1);
			}else{
				new com.resultAnalysisTool.util.message.Message("Please fill up required fields", 210);
			}
		} catch (Exception e) {
			new com.resultAnalysisTool.util.message.Message("Error.", 420);
		}
	}
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	/**
	 * 
	 * @param host
	 * @param port
	 * @param userName
	 * @param password
	 * @param toAddresses
	 * @param subject
	 * @param message
	 * @param attachFiles
	 * @throws Exception
	 */
	 private void sendEmailWithAttachments(String host, String port, final String userName, final String password,
	    		String[] toAddresses, String subject, String message, String[] attachFiles) 
	    				throws Exception {
	        
    	// sets SMTP server properties
        Properties properties = new Properties();
        
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
 
        // creates authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        
        // creates a new session
        Session session = Session.getInstance(properties, auth);
 
        // receiver addresses
        InternetAddress[] internetAddresses = new InternetAddress[toAddresses.length];
        int i=0;
        for(String toAddress: toAddresses){
        	internetAddresses[i] = new InternetAddress(toAddress);
        	i++;
        }
 
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
        if (attachFiles.length>0) {
            for (String filePath: attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
                attachPart.attachFile(filePath);
 
                multipart.addBodyPart(attachPart);
            }
        }
        
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(userName));
        msg.setRecipients(Message.RecipientType.TO, internetAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
    }
	// End of Auxiliary Methods 																#_______AM_______#
	
	//**
	// Unimplemented Methods 																	#*******UM*******#
	//**
	
	// End of Unimplemented Methods 															#_______UM_______#
	
	
	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create */
		new EmailSender();
	}
}
