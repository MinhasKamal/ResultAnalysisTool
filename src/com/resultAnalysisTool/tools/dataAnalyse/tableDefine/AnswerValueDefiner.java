/**
 * Developer: Minhas Kamal(BSSE-0509, IIT, DU)
 * Date: 15-02-14
 * Comment: Creates a reliable interface to define marks of the questions.
**/


package com.resultAnalysisTool.tools.dataAnalyse.tableDefine;


import java.awt.event.ActionEvent;
import javax.swing.*;

import com.resultAnalysisTool.util.fileReadWrite.FileIO;
import com.resultAnalysisTool.util.message.Message;

@SuppressWarnings("serial")
public class AnswerValueDefiner extends JFrame{
	/**
	 * Variable Declaration												#*******D*******#
	**/
	private JLabel jMainLabel;
	
	private JButton[] jButtonBF;
	
	private JLabel[] jLabelNotification;
	private JTextField[] jTextFieldValue;
	private JButton jButtonDuplicate;
	private JButton jButtonDuplicateRest;
	
	private JButton jButtonOk;
	private JButton jButtonCancle;

	//operational variables
	private int answerNumber;
	private double[] positiveMark;
	private double[] negativeMark;
	private int position;	//indicates the position of indicator
	
	private String filePath;
	private String[] fieldNames;
	private Integer[] endPositions;
	private int[] positions;
	private Boolean[] answerFlags;
	//End of Variable Declaration										#_______D_______#
    
	
	
    
    //**  Constructor  **//
    public AnswerValueDefiner(String filePath, String[] fieldNames, Integer[] endPositions, Boolean[] answerFlags){
    	this.filePath = filePath;
    	this.fieldNames = fieldNames;
    	this.endPositions = endPositions;
    	this.answerFlags = answerFlags;
    	
    	answerNumber = getAnswerNumber();
    	
    	InitialComponent();
    }
    
    
    //**  Method for Initializing  **//
    private void InitialComponent(){
		/**
		 * Initialization 												#*******I*******#
		**/
    	jMainLabel = new JLabel();
    	
    	jButtonBF = new JButton[2];
    	
    	jLabelNotification = new JLabel[3];
    	jTextFieldValue = new JTextField[3];
    	jButtonDuplicate = new JButton();
    	jButtonDuplicateRest = new JButton();
    	
    	jButtonOk = new JButton();
    	jButtonCancle = new JButton();
    	
    	//operational variables
    	positions = new int[endPositions.length];
    	for(int i=0; i<endPositions.length; i++){
    		positions[i] = endPositions[i].intValue();
    	}
    	
    	positiveMark=new double[answerNumber];
    	negativeMark=new double[answerNumber];
    	for(int i=0; i<answerNumber; i++){
    		positiveMark[i]=1;
    		negativeMark[i]=0;
    	}
    	position=0;
    	//End of Initialization 										#_______I_______#
		
		
		
		
		/**
		 * Setting Bounds and Attributes of the Elements				#*******S*******#
		**/
    	jMainLabel.setBounds(0, 0, 400, 300);
    	
    	for(int i=0; i<2; i++){
    		jButtonBF[i] = new JButton();
    		jButtonBF[i].setSize(40, 30);
    		jButtonBF[i].setLocation(270+i*60, 30);
    		
    	}
    	jButtonBF[0].setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/img/icon/LeftIcon.png")));
    	jButtonBF[0].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jButtonBackActionPerformed();
            }
        });
    	jButtonBF[1].setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/img/icon/RightIcon.png")));
    	jButtonBF[1].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jButtonForwardActionPerformed();
            }
        });
    	
    	for(int i=0; i<3; i++){
    		jLabelNotification[i] = new JLabel();
    		jLabelNotification[i].setSize(100, 30);
    		jLabelNotification[i].setLocation(35, 30+i*50);
    		jLabelNotification[i].setFont(new java.awt.Font("Tahoma", 0, 14));
    	}
    	jLabelNotification[0].setText("Answer Number");
    	jLabelNotification[1].setText("Positive Mark");
    	jLabelNotification[2].setText("Negative Mark");
    	
    	for(int i=0; i<3; i++){
    		jTextFieldValue[i] = new JTextField();
    		jTextFieldValue[i].setSize(80, 30);
    		jTextFieldValue[i].setLocation(150, 30+i*50);
    		jTextFieldValue[i].setHorizontalAlignment(0);
    		jTextFieldValue[i].setAlignmentX(2);
    		jTextFieldValue[i].setText("1");
    	}
    	jTextFieldValue[0].setEditable(false);
    	jTextFieldValue[2].setText("0");
    	
    	
    	jButtonDuplicate.setBounds(270, 80, 100, 30);
    	jButtonDuplicate.setBorder(null);
    	jButtonDuplicate.setText("Duplicate");
    	jButtonDuplicate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jButtonDuplicateActionPerformed();
            }
        });
    	
    	jButtonDuplicateRest.setBounds(270, 130, 100, 30);
    	jButtonDuplicateRest.setBorder(null);
    	jButtonDuplicateRest.setText("Duplicate Rest");
    	jButtonDuplicateRest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jButtonDuplicateRestActionPerformed(evt);
            }
        });
    	
    	jButtonOk.setBounds(250, 215, 65, 33);
    	jButtonOk.setBorder(null);
    	jButtonOk.setText("OK");
    	jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jButtonOkActionPerformed(evt);
            }
        });
    	
    	jButtonCancle.setBounds(320, 215, 65, 33);
    	jButtonCancle.setBorder(null);
    	jButtonCancle.setText("Cancel");
    	jButtonCancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jButtonCancleActionPerformed(evt);
            }
        });
    	
    	//operational variables
    	for(int i=0; i<answerNumber; i++){
    		positiveMark[i]=1;
    		negativeMark[i]=0;
    	}
    	
    	//End of Setting Bounds and Attributes 							#_______S_______#
		
		
		
		
		/**
		 * Adding Components											#*******A*******#
		**/
    	jMainLabel.add(jButtonBF[0]);
    	jMainLabel.add(jButtonBF[1]);
    	
    	for(int i=0; i<3; i++){
	    	jMainLabel.add(jLabelNotification[i]);
	    	jMainLabel.add(jTextFieldValue[i]);
    	}
    	
    	jMainLabel.add(jButtonDuplicate);
    	jMainLabel.add(jButtonDuplicateRest);
    	

    	jMainLabel.add(jButtonOk);
    	jMainLabel.add(jButtonCancle);
    	//End of Adding Components										#_______A_______#
		
		
		
    	//Setting Criterion of the Frame//
    	setIconImage(new ImageIcon(getClass().getResource("")).getImage());
    	setBounds(300, 200, 400, 300);
    	setTitle("");
    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	setTitle("Defining Answer Number");
    	add(jMainLabel);
    	setLayout(null);
    	setResizable(false);
    	//setAlwaysOnTop(true);
//    	setModal(true);
		
    }
    
    
    /**
	 * Action Events													#********AE*******#
	**/
    //back and forward button
    private void jButtonBackActionPerformed(){
    	jButtonBFStartingActionPerformed();
    	
    	if(position>0){
    		position--;
    	}
    	
    	jButtonBFFinishingActionPerformed();
	}
	private void jButtonForwardActionPerformed(){
		jButtonBFStartingActionPerformed();
		
		if(position<answerNumber-1){
    		position++;
    	}
		
		jButtonBFFinishingActionPerformed();
	}
	private void jButtonBFStartingActionPerformed(){	//start
		try {
			positiveMark[position]=Double.parseDouble(jTextFieldValue[1].getText());
			negativeMark[position]=Double.parseDouble(jTextFieldValue[2].getText());
		} catch (NumberFormatException e) {
			new Message("Numbers Only!", 210);
		}catch (Exception e) {
			new Message("Error!", 420);
		}
	}
    private void jButtonBFFinishingActionPerformed(){	//finish
    	jTextFieldValue[0].setText(""+ (position+1) );
    	jTextFieldValue[1].setText(""+positiveMark[position]);
    	jTextFieldValue[2].setText(""+negativeMark[position]);
	}
    
    //duplicate button
	private void jButtonDuplicateActionPerformed(){
		if(position>0){
			jTextFieldValue[1].setText(""+positiveMark[position-1]);
			jTextFieldValue[2].setText(""+negativeMark[position-1]);
		}
    	
    	jButtonForwardActionPerformed();
	}
	    
	private void jButtonDuplicateRestActionPerformed(ActionEvent evt){
		while(position<answerNumber-1){
			jButtonDuplicateActionPerformed();
		}
		jButtonDuplicateActionPerformed();
	}
	
	private void jButtonOkActionPerformed(ActionEvent evt){
		jButtonBFStartingActionPerformed();
		
		try {
			makeTAIFile(fieldNames, positions, answerFlags, positiveMark, negativeMark);
			
			new Message("File importation is complete. \nNew TAI file is made.", 1);
		} catch (Exception e) {
			new Message("Error!", 420);
		}
		
		dispose();
	}
	
	private void jButtonCancleActionPerformed(ActionEvent evt){
//		setModal(false);
		dispose();
	}

    //End of Action Events												#________AE_______#

    
    
    /**
     * Auxiliary Methods												#********AM*******#
    **/
    private int getAnswerNumber(){
    	int answerNumber=0;
    	
    	if(answerFlags[0]){
    		answerNumber = answerNumber + endPositions[0];
    	}
    	
    	for(int i=1; i<answerFlags.length; i++){
    		if(answerFlags[i]){
    			answerNumber = answerNumber + (endPositions[i]-endPositions[i-1]);
    		}
    	}
    	//System.out.println(answerNumber);///test
    	
    	return answerNumber;
    }
    
    public void makeTAIFile(String[] FieldNames, int[] Positions, Boolean[] answerFlags, double[] PositiveMarks,
    		double[] NegativeMarks) throws Exception{
		
		String TAIString="";
		
		for(int i=0; i<FieldNames.length-1; i++){
			TAIString += FieldNames[i] + ",";
		}
		TAIString += FieldNames[FieldNames.length-1] + "\n";
		
		for(int i=0; i<Positions.length-1; i++){
			TAIString += Positions[i] + ",";
		}
		TAIString += Positions[Positions.length-1] + "\n";
		
		for(int i=0; i<answerFlags.length-1; i++){
			TAIString += answerFlags[i] + ",";
		}
		TAIString += answerFlags[answerFlags.length-1] + "\n";
		
		for(int i=0; i<PositiveMarks.length-1; i++){
			TAIString += PositiveMarks[i] + ",";
		}
		TAIString += PositiveMarks[PositiveMarks.length-1] + "\n";
		
		for(int i=0; i<NegativeMarks.length-1; i++){
			TAIString += NegativeMarks[i] + ",";
		}
		TAIString += NegativeMarks[NegativeMarks.length-1] + "\n";
		
		
		FileIO.writeWholeFile(filePath+"_tableDesign.tai", TAIString);
		
		return ;
	}
    //End of Auxiliary Methods											#________AM_______#
    
    
    //**  Main Method  **//
    public static void main(String args[]) {
    	/* Set the NIMBUS look and feel */
    	try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");					
		} catch (Exception ex) {
			//do nothing if operation unsuccessful
		}

        /* Create and display the form */
    	String filePath = "C:\\Users\\admin\\Desktop\\Other_Programs.csd";
    	String[] fieldNames = {"Institute Code", "Empty", "Roll Number", "Set Code", "Answer"};
    	Integer[] endPositions = {4, 6, 10, 11, 41};
    	Boolean[] answerFlags = {false, false, false, false, true};
    	
        AnswerValueDefiner gui = new AnswerValueDefiner(filePath, fieldNames, endPositions, answerFlags);
        gui.setVisible(true);
    }
}





