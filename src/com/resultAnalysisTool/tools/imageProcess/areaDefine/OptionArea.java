/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 30-Dec-2014																								*
* Date: 05-Jan-2015																								*
* Date: 25-Jan-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.imageProcess.areaDefine;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import com.resultAnalysisTool.lib.areator.GraphArea;
import com.resultAnalysisTool.lib.areator.GraphAreaGui;


/**
 * Defines area where option is filled.
 * 
 * @author Minhas Kamal
 */
public class OptionArea extends GraphArea{
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	/**
	 * control variables
	 */
	//partitioning left to right.	||
	public static final int HORIZONTAL_PARTITION = 1;
	//partitioning top to bottom.	=
	public static final int VERTICAL_PARTITION = 2;
	//Option area contains a, b, c...
	public static final int ALPHABETIC_OPTION_AREA = 1;
	//Option area contains 1, 2, 3...
	public static final int NUMERIC_OPTION_AREA = 2;
	//tag for storing data.
	public static final String OA_TAG = "option_area";
	public static final String OA_SUB_TAG_TYPE = "type";
	public static final String OA_SUB_TAG_DARKNESS = "dark";
	public static final String OA_SUB_TAG_AREA = "rect";
	
	//gui components
	private Queue<JComponent> jComponentPartitions;
	private JButton[] jButtonPartitions;
	private JToggleButton[] jTButtonOpAreaType;
	private JButton[] jButtonsMinDarkArea;
	private JTextField jTextFieldMinDarkArea;
	
	//other variables
	//Two types of option area- alphabetic and numeric
	private int optionAreaType;
	//minimum percentage of dark area to be considered as marked
	private int darkness;
	//Two types of partition- top to bottom & left to right.
	private int partitionType;
	// End of Variable Declaration 																#_______D_______#
	/***##Constructor##***/
	public OptionArea(int x, int y, int width, int height, int optionAreaType, int darkness,
			int partitionType, int numOfPartitions){
		super(x, y, width, height);
		
		this.optionAreaType = optionAreaType;
		this.darkness = darkness;
		this.partitionType = partitionType;
		
		jTBPropertySide = 8;
		
		if(partitionType==HORIZONTAL_PARTITION){
			super.resize(HORIZONTAL_WAY, jTBPropertySide+1);
		}else{
			super.resize(VERTICAL_WAY, jTBPropertySide+1);
		}
		
		initialComponent();
		
		setPartition(numOfPartitions);
	}
	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		//**
		// Assignation 																			#*******A*******#
		//**
		jComponentPartitions = ((OptionAreaGui) gui).jComponentPartitions;
		
		jButtonPartitions = ((OptionAreaGui) gui).jButtonPartitions;
		jTButtonOpAreaType = ((OptionAreaGui) gui).jTButtonOpAreaType;
		jButtonsMinDarkArea = ((OptionAreaGui) gui).jButtonsMinDarkArea;
		jTextFieldMinDarkArea = ((OptionAreaGui) gui).jTextFieldMinDarkArea;
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		for(int i=0; i<3; i++){
			jButtonPartitions[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButtonsPartitionActionPerformed(evt);
				}
			});
		}
		
		if(optionAreaType==ALPHABETIC_OPTION_AREA){
			jTButtonOpAreaType[0].setSelected(true);
		}else{
			jTButtonOpAreaType[1].setSelected(true);
		}
		for(int i=0; i<2; i++){
			jTButtonOpAreaType[i].addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	jTButtonOpAreaTypeActionPerformed(evt);
	            }
	        });
		}
		
		for(int i=0; i<2; i++){
			jButtonsMinDarkArea[i].addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	jButtonsMinDarkAreaActionPerformed(evt);
	            }
	        });
		}
		
		jTextFieldMinDarkArea.setText(darkness + "% Dark");
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	private void jButtonsPartitionActionPerformed(ActionEvent evt){
		JButton jButtonTemp = (JButton) evt.getSource();
		
		if(jButtonTemp.equals(jButtonPartitions[0])){
			setPartition(jComponentPartitions.size()+1);
		}else if(jButtonTemp.equals(jButtonPartitions[1])){
			changePartitionType();
		}else{
			setPartition(jComponentPartitions.size()-1);
		}
	}
	
	private void jTButtonOpAreaTypeActionPerformed(ActionEvent evt){
		if(evt.getSource().equals(jTButtonOpAreaType[0])){
			jTButtonOpAreaType[0].setSelected(true);
			jTButtonOpAreaType[1].setSelected(false);
			optionAreaType = ALPHABETIC_OPTION_AREA;
		}else{
			jTButtonOpAreaType[1].setSelected(true);
			jTButtonOpAreaType[0].setSelected(false);
			optionAreaType = NUMERIC_OPTION_AREA;
		}
	}
	
	private void jButtonsMinDarkAreaActionPerformed(ActionEvent evt){
		if(evt.getSource().equals(jButtonsMinDarkArea[0])){
			if(darkness>0){
				darkness--;
			}
			jTextFieldMinDarkArea.setText(darkness + "% Dark");
		}else{
			if(darkness<99){
				darkness++;
			}
			jTextFieldMinDarkArea.setText(darkness + "% Dark");
		}
	}
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	@Override
	public void resize(int direction, int amount) {
		super.resize(direction, amount);
		
		setPartition(jComponentPartitions.size());
		
		gui.repaint();
	}
	
	@Override
	protected void relocateJTBProperty(){
		if(partitionType==HORIZONTAL_PARTITION){
			jTButtonProperty.setBounds(width-jTBPropertySide, 0, jTBPropertySide, height);
		}else{
			jTButtonProperty.setBounds(0, height-jTBPropertySide, width, jTBPropertySide);
		}
		
		gui.repaint();
	}
	
	/**
	 * Changes partition type, resizes main label, resets button position & partitions.
	 */
	public void changePartitionType() {
		if(partitionType==HORIZONTAL_PARTITION){
			this.partitionType = VERTICAL_PARTITION;
			
			width = width-jTBPropertySide-1;
			height = height+jTBPropertySide+1;
		}else{
			this.partitionType = HORIZONTAL_PARTITION;
			
			width = width+jTBPropertySide+1;
			height = height-jTBPropertySide-1;
		}
		
		gui.setSize(width, height);
		
		relocateJTBProperty();
		setPartition(jComponentPartitions.size());
		
		gui.repaint();
	}
	
	
	/**
	 * Removes all old partitions and set new partitions.
	 * @param n number of partitions.
	 */
	public void setPartition(int n) {
		if(n<1){
			return;
		}
		
		//remove old jcomponents
		for(JComponent jComponent: jComponentPartitions){
			gui.remove(jComponent);
		}
		jComponentPartitions.clear(); //clear queue
		
		if(partitionType == HORIZONTAL_PARTITION){
			makeHorizontalcalPartition(n);
		}else{
			makeVerticalPartition(n);
		}
		
		gui.repaint();
	}
	
	@SuppressWarnings("serial")
	private void makeHorizontalcalPartition(int n) {
		//calculate width of new jcomponents
		double partitionSpace = ((double) width-jTBPropertySide)/n;
		
		//add new jcomponents
		for(int i=0; i<n ;i++){
			JComponent jComponent = new JComponent(){};
			jComponent.setBounds((int) (partitionSpace*i), 0, (int) (partitionSpace+1), height);
			jComponent.setBorder(BorderFactory.createLineBorder(new Color(60, 40, 40), 1, false));
			
			gui.add(jComponent);
			jComponentPartitions.add(jComponent);
		}
	}
	
	@SuppressWarnings("serial")
	private void makeVerticalPartition(int n) {
		//calculate width of new jcomponents
		double partitionSpace = ((double) height-jTBPropertySide)/n;
		
		//add new jcomponents
		for(int i=0; i<n ;i++){
			JComponent jComponent = new JComponent(){};
			jComponent.setBounds(0, (int) (partitionSpace*i), width, (int) (partitionSpace+1));
			jComponent.setBorder(BorderFactory.createLineBorder(new Color(60, 40, 40), 1, false));
			
			gui.add(jComponent);
			jComponentPartitions.add(jComponent);
		}
	}
	

	/**
	 * Returns <code>GraphArea</code> with optimal settings.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static OptionArea buildOptionArea(int x, int y, int width, int height){
		if(width>height){
			return new OptionArea(x, y, width, height, ALPHABETIC_OPTION_AREA, 15, HORIZONTAL_PARTITION, 4);
		}else{
			return new OptionArea(x, y, width, height, ALPHABETIC_OPTION_AREA, 15, VERTICAL_PARTITION, 4);
		}
	}
	

	@Override
	protected GraphAreaGui getGui(){
		return new OptionAreaGui();
	}
	
	
	/**
	 * Return all information as a tagged string in a defined format.
	 * @return information
	 */
	@Override
	public String dump(){
		String info = "<"+OA_TAG+">\n<"+OA_SUB_TAG_TYPE+">"+ optionAreaType +"</"+OA_SUB_TAG_TYPE+">\n<"+
				OA_SUB_TAG_DARKNESS+">"+ darkness +"</"+OA_SUB_TAG_DARKNESS+">\n";
		
		for(JComponent jComponentPartition: jComponentPartitions){
			info += "<"+OA_SUB_TAG_AREA+">"+ (x+jComponentPartition.getX()) + "," + (y+jComponentPartition.getY()) + "," +
					jComponentPartition.getWidth() + "," + jComponentPartition.getHeight() + "</"+OA_SUB_TAG_AREA+">\n";
		}
		
		info += "</"+OA_TAG+">";
		
		return info;
	}
	
	/**
	 * Should be overridden by child
	 * @return
	 */
	@Override
	public GraphArea getCopy(){
		int width = this.width;
		int height = this.height;
		
		if(partitionType == HORIZONTAL_PARTITION){
			width -= jTBPropertySide+1;
		}else{
			height -= jTBPropertySide+1;
		}
		
		GraphArea graphArea = new OptionArea(x, y, width, height, optionAreaType, darkness,
				partitionType, jComponentPartitions.size());
		
		if(width>height){
			graphArea.move(VERTICAL_WAY, height);
		}else{
			graphArea.move(HORIZONTAL_WAY, width);
		}
		
		return graphArea;
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

		/* Create and display the form */
		OptionArea area = OptionArea.buildOptionArea(30, 30, 200, 30);
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(40, 30, 400, 200);
		jFrame.setVisible(true);
		jFrame.setLayout(null);
		jFrame.add(area.gui);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
