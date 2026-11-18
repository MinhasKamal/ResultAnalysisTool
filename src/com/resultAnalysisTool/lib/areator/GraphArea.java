/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 25-Jan-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.lib.areator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.UIManager;


/**
 * 
 * 
 * @author Minhas Kamal
 */
public class GraphArea {
	// GUI Declaration
	protected GraphAreaGui gui;
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	//move or resize way
	public static final int HORIZONTAL_WAY=0;
	public static final int VERTICAL_WAY=1;
	//min size of any graph area.
	public static final int LIMIT_SIZE=5;
	//tag for storing data.
	public static final String GA_TAG = "graph_area";
	
	//for changing property of the component
	protected JToggleButton jTButtonProperty;
	
	//position and shape of the component
	protected int x, y, width, height;
	
	//side of jButtonProperty
	protected int jTBPropertySide;
	
	protected JButton[] jButtonsMove;
	protected JButton[] jButtonsResize;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public GraphArea(int x, int y, int width, int height) {
		if(x<0){
			x=0;
		}if(y<0){
			y=0;
		}if(width<LIMIT_SIZE){
			width=LIMIT_SIZE;
		}if(height<LIMIT_SIZE){
			height=LIMIT_SIZE;
		}
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		jTBPropertySide = 20;
		
		initialComponent();
		
		relocateJTBProperty();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = getGui();
		gui.setVisible(true);
		gui.setBounds(x, y, width, height);
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jTButtonProperty = gui.jTButtonProperty;
		
		jButtonsMove = gui.jButtonsMove;
		jButtonsResize = gui.jButtonsResize;
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jTButtonProperty.setSize(jTBPropertySide, jTBPropertySide);
		jTButtonProperty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jTButtonPropertyActionPerformed(evt);
            }
        });
		
		for(int i=0; i<4; i++){
			jButtonsMove[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButtonsMoveActionPerformed(evt);
				}
			});
		}
		
		for(int i=0; i<4; i++){
			jButtonsResize[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButtonsResizeActionPerformed(evt);
				}
			});
		}
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	protected void jTButtonPropertyActionPerformed(ActionEvent evt){
		if(jTButtonProperty.isSelected()){
			gui.setBorder(gui.borderSelected);
		}else{
			gui.setBorder(gui.borderSimple);
		}
	}
	
	private void jButtonsMoveActionPerformed(ActionEvent evt){
		JButton jButtonTemp = (JButton) evt.getSource();
		
		if(jButtonTemp.equals(jButtonsMove[0])){
			move(VERTICAL_WAY, -1);
		}else if(jButtonTemp.equals(jButtonsMove[1])){
			move(HORIZONTAL_WAY, -1);
		}else if(jButtonTemp.equals(jButtonsMove[2])){
			move(VERTICAL_WAY, 1);
		}else{
			move(HORIZONTAL_WAY, 1);
		}
	}
	
	private void jButtonsResizeActionPerformed(ActionEvent evt){
		JButton jButtonTemp = (JButton) evt.getSource();
		
		if(jButtonTemp.equals(jButtonsResize[0])){
			resize(VERTICAL_WAY, -1);
		}else if(jButtonTemp.equals(jButtonsResize[1])){
			resize(HORIZONTAL_WAY, -1);
		}else if(jButtonTemp.equals(jButtonsResize[2])){
			resize(VERTICAL_WAY, 1);
		}else{
			resize(HORIZONTAL_WAY, 1);
		}
		
		gui.repaint();
	}
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	/**
	 * Attaches GUI with the input <code>jComponent</code>.
	 * @param jComponent with which the GUI should be attached
	 */
	public void attachTo(JComponent jComponent){
		jComponent.add(gui);
		
		jComponent.repaint();
	}
	
	
	/**
	 * Moves the GUI.
	 * @param direction horizontal or vertical direction. horizontal=0, vertical=1
	 * @param amount amount of move
	 */
	public void move(int direction, int amount){
		if(direction==HORIZONTAL_WAY){
			if(x+amount>=0){
				x += amount;
			}
		}else{
			if(y+amount>=0){
				y += amount;
			}
		}
		gui.setLocation(x, y);
	}
	
	/**
	 * Resizes the GUI. Preserves <code>LIMIT_SIZE</code>.
	 * @param direction horizontal or vertical direction. horizontal=0, vertical=1
	 * @param amount amount of change
	 */
	public void resize(int direction, int amount) {
		if(direction==HORIZONTAL_WAY){
			if(width+amount>=LIMIT_SIZE){
				width += amount;
			}
		}else{
			if(height+amount>=LIMIT_SIZE){
				height += amount;
			}
		}
		
		gui.setSize(width, height);
		relocateJTBProperty();
	}
	
	/**
	 * Removes the component from its container & disposes it.
	 */
	public void remove(){
		x=y=width=height=0;
		gui.removeAll();
		gui.setVisible(false);
		gui.jPMenuProperty.setVisible(false);
		gui.getParent().remove(gui);
	}
	
	/**
	 * Should be overridden by child
	 * @return
	 */
	public GraphArea getCopy(){
		GraphArea graphArea = new GraphArea(x, y, width, height);
		
		if(width>height){
			graphArea.move(VERTICAL_WAY, height);
		}else{
			graphArea.move(HORIZONTAL_WAY, width);
		}
		
		return graphArea;
	}
	
	/**
	 * Selects the GraphArea.
	 */
	public void select(){
		jTButtonProperty.setSelected(true);
		gui.setBorder(gui.borderSelected);
	}
	
	/**
	 * Unselects the GraphArea.
	 */
	public void unSelect(){
		jTButtonProperty.setSelected(false);
		gui.setBorder(gui.borderSimple);
	}
	
	/**
	 * Examines if the <code>GraphArea</code> is selected.
	 * @return
	 */
	public boolean isSelected(){
		if(jTButtonProperty.isSelected()){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Return all information as a tagged string in a defined format. Adds no extra new-line.
	 * @return information
	 */
	public String dump(){
		String info = "<"+GA_TAG+">"+x+","+y+","+width+","+height+"<"+GA_TAG+">";
		
		return info;
	}
	
	/**
	 * Shows Property Button and Boarder
	 */
	public void enableEdit(){
		jTButtonProperty.setVisible(true);
		gui.setBorder(gui.borderSimple);
		jTButtonProperty.setSelected(false);
	}
	
	/**
	 * Hides Property Button and Boarder
	 */
	public void disableEdit(){
		jTButtonProperty.setVisible(false);
		gui.setBorder(null);
		jTButtonProperty.setSelected(false);
	}
	
	/**
	 * Sets property button size and location depending on <code>width</code>, <code>height</code> & 
	 * <code>partitiontype</code>.
	 */
	protected void relocateJTBProperty(){
		jTButtonProperty.setLocation(width-jTBPropertySide, 0);
	}
	
	/**
	 * Returns consequent gui. Should be overridden in child class.
	 */
	protected GraphAreaGui getGui() {
		return new GraphAreaGui();
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
		JFrame jFrame = new JFrame();
		jFrame.setBounds(40, 30, 500, 400);
		jFrame.setVisible(true);
		jFrame.setLayout(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GraphArea area = new GraphArea(20, 20, 400, 300);
		jFrame.add(area.gui);
		jFrame.repaint();
	}
}
