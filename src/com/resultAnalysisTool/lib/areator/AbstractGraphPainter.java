/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 25-Jan-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.lib.areator;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import com.resultAnalysisTool.util.fileChoose.FileChooser;
import com.resultAnalysisTool.util.fileReadWrite.FileIO;
import com.resultAnalysisTool.util.message.Message;


/**
 * 
 * 
 * @author Minhas Kamal
 */
public abstract class AbstractGraphPainter {
	
	public static final String IMAGE_SIZE_TAG = "image_size";
	
	//GUI Declaration
	protected AbstractGraphPainterGui gui;
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	private JButton jButtonDone;
	
	private JButton[] jButtonsMove;
	private JButton jButtonMoveDegree;
	private JButton[] jButtonsResize;
	private JButton jButtonResizeDegree;
	private JButton jButtonCopy;
	private JButton jButtonRemove;
	
	private JButton jButtonLoadGraph;
	private JButton jButtonLoadImage;
	
	protected JLabel jLabelImage;
	private ImageIcon imagePage;
	
	private JLabel jLabelTempGraphArea;
	
	private JLabel jLabelCursorLocation;
	
	//other variables
	private int mouseLocationX;
	private int mouseLocationY;
	
	private int moveDegree;
	private int resizeDegree;
	
	//keep reference to the graph areas
	protected LinkedList<GraphArea> graphAreaList;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public AbstractGraphPainter() {
		moveDegree=1;
		resizeDegree=1;
		
		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = getGui();
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jButtonDone = gui.jButtonDone;
		
		jButtonsMove = gui.jButtonsMove;
		jButtonMoveDegree = gui.jButtonMoveDegree;
		jButtonsResize = gui.jButtonsResize;
		jButtonResizeDegree = gui.jButtonResizeDegree;
		jButtonCopy = gui.jButtonCopy;
		jButtonRemove = gui.jButtonRemove;
		
		jButtonLoadGraph = gui.jButtonLoadGraph;
		jButtonLoadImage = gui.jButtonLoadImage;
		
		jLabelImage = gui.jLabelImage;
		imagePage = gui.imagePage;
		
		jLabelTempGraphArea = gui.jLabelTempGraphArea;
		
		jLabelCursorLocation = gui.jLabelCursorLocation;
		
		graphAreaList = gui.graphAreaList;
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jButtonDone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonDoneActionPerformed(evt);
            }
        });
		
		for(int i=0; i<4; i++){
			jButtonsMove[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButtonsMoveActionPerformed(evt);
				}
			});
		}
		
		jButtonMoveDegree.setText(moveDegree+"");
		jButtonMoveDegree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonMoveDegreeActionPerformed(evt);
            }
        });
		
		for(int i=0; i<4; i++){
			jButtonsResize[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButtonsResizeActionPerformed(evt);
				}
			});
		}
		
		jButtonResizeDegree.setText(resizeDegree+"");
		jButtonResizeDegree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonResizeDegreeActionPerformed(evt);
            }
        });
		
		jButtonCopy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonCopyActionPerformed(evt);
            }
        });
		
		jButtonRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonRemoveActionPerformed(evt);
            }
        });
		
		jButtonLoadImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonLoadImageActionPerformed(evt);
            }
        });
		
		jButtonLoadGraph.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonLoadGraphActionPerformed(evt);
            }
        });
		
		jLabelImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				jLabelMousePressed(e);
	        }

			@Override
			public void mouseReleased(MouseEvent e) {
				jLabelMouseReleased(e);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				jLabelImageMouseExited(e);
			}
	    });
		
		jLabelImage.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e){
				jLabelImageMouseMoved(e);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				jLabelImageMouseDragged(e);
			}
		});
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	private void jButtonDoneActionPerformed(ActionEvent evt) {
		saveInformation();
		new Message("Operation is successful.", 1);
	}
	
	private void jButtonsMoveActionPerformed(ActionEvent evt){
		JButton jButtonTemp = (JButton) evt.getSource();
		int direction;
		int amount;
		
		if(jButtonTemp.equals(jButtonsMove[0])){
			direction = GraphArea.VERTICAL_WAY;
			amount = -moveDegree;
		}else if(jButtonTemp.equals(jButtonsMove[1])){
			direction = GraphArea.HORIZONTAL_WAY;
			amount = -moveDegree;
		}else if(jButtonTemp.equals(jButtonsMove[2])){
			direction = GraphArea.VERTICAL_WAY;
			amount = moveDegree;
		}else{
			direction = GraphArea.HORIZONTAL_WAY;
			amount = moveDegree;
		}
		
		for(GraphArea graphArea: graphAreaList){
			if(graphArea.isSelected()){
				graphArea.move(direction, amount);
			}
		}
		
		gui.repaint();
	}
	
	private void jButtonMoveDegreeActionPerformed(ActionEvent evt) {
		moveDegree += 2;
		
		if(moveDegree>9){
			moveDegree=1;
		}
		
		jButtonMoveDegree.setText(moveDegree+"");
	}
	
	private void jButtonsResizeActionPerformed(ActionEvent evt){
		JButton jButtonTemp = (JButton) evt.getSource();
		int direction;
		int amount;
		
		if(jButtonTemp.equals(jButtonsResize[0])){
			direction = GraphArea.VERTICAL_WAY;
			amount = -resizeDegree;
		}else if(jButtonTemp.equals(jButtonsResize[1])){
			direction = GraphArea.HORIZONTAL_WAY;
			amount = -resizeDegree;
		}else if(jButtonTemp.equals(jButtonsResize[2])){
			direction = GraphArea.VERTICAL_WAY;
			amount = resizeDegree;
		}else{
			direction = GraphArea.HORIZONTAL_WAY;
			amount = resizeDegree;
		}
		
		for(GraphArea graphArea: graphAreaList){
			if(graphArea.isSelected()){
				graphArea.resize(direction, amount);
			}
		}
		
		gui.repaint();
	}
	
	private void jButtonResizeDegreeActionPerformed(ActionEvent evt) {
		resizeDegree += 2;
		
		if(resizeDegree>9){
			resizeDegree=1;
		}
		
		jButtonResizeDegree.setText(resizeDegree+"");
	}
	
	private void jButtonCopyActionPerformed(ActionEvent evt){
		for(GraphArea graphArea: graphAreaList){
			if(graphArea.isSelected()){
				try {
					GraphArea newGraphArea = graphArea.getCopy();
					graphArea.unSelect();
					newGraphArea.select();
					newGraphArea.attachTo(jLabelImage);
					graphAreaList.add(newGraphArea);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				break;
			}
		}
		
		gui.repaint();
	}
	
	private void jButtonRemoveActionPerformed(ActionEvent evt){
		for(GraphArea graphArea: graphAreaList){
			if(graphArea.isSelected()){
				graphArea.remove();
				graphAreaList.remove(graphArea);
				break;
			}
		}
		
		gui.repaint();
	}
	
	protected abstract void jButtonLoadGraphActionPerformed(ActionEvent evt);
	
	private void jButtonLoadImageActionPerformed(ActionEvent evt){
		String filePath = new FileChooser(new String[]{"png", "jpg", "jpeg"}).chooseFilePathFromComputer();
		setImage(filePath);
	}
	
	private void jLabelMousePressed(MouseEvent e){
		 mouseLocationX = e.getX();
         mouseLocationY = e.getY();
	}
	
	private void jLabelMouseReleased(MouseEvent e) {
		if(jLabelImage.getCursor().toString().contains("CustomCursor")){
			addNewGraphArea(jLabelTempGraphArea.getBounds());
		}
		jLabelTempGraphArea.setBounds(0, 0, 0, 0);
	}
	
	private void jLabelImageMouseExited(MouseEvent e) {
		jLabelCursorLocation.setLocation(-80, -80);
	}
	
	private void jLabelImageMouseMoved(MouseEvent e) {
		int x=e.getX(), y=e.getY();
		jLabelCursorLocation.setLocation(x+2, y-22);
		jLabelCursorLocation.setText(x + "," + y);
	}
	
	private void jLabelImageMouseDragged(MouseEvent e) {
		int x=e.getX(), y=e.getY();
		jLabelCursorLocation.setLocation(x+2, y-22);
		jLabelCursorLocation.setText(x + "," + y);
		
		((JComponent) e.getSource()).scrollRectToVisible(new Rectangle(x, y, 1, 1));
		
		int x0 = mouseLocationX, y0 = mouseLocationY;
		
		if(x0>x){
			int t=x;
			x=x0;
			x0=t;
		}if(y0>y){
			int t=y;
			y=y0;
			y0=t;
		}
		
		jLabelTempGraphArea.setBounds(x0, y0, x-x0, y-y0);
	}
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	public void setImage(String filePath) {
		if(! filePath.substring(filePath.length()-5, filePath.length()).contains(".")){
			return ;
		}
		
		imagePage = new ImageIcon(filePath);
		
		jLabelImage.setIcon(imagePage);
		gui.repaint();
	}
	
	/**
	 * Adds new graph area. Should be overridden by child class.
	 * @param rect
	 */
	protected abstract void addNewGraphArea(Rectangle rect);
	
	/**
	 * Changes the rectangle to valid form.
	 * @param rect
	 */
	protected boolean validateArea(Rectangle rect){
		if(rect.x<0){
			rect.width += rect.x;
			rect.x=0;
		}if(rect.y<0){
			rect.height += rect.y;
			rect.y=0;
		}
		
		int imageWidth = imagePage.getIconWidth(),
		imageHeight = imagePage.getIconHeight();
		
		if(rect.x+rect.width > imageWidth){
			rect.width = imageWidth-rect.x;
		}if(rect.y+rect.height > imageHeight){
			rect.height = imageHeight-rect.y;
		}
		
		
		if(rect.width>GraphArea.LIMIT_SIZE && rect.height>GraphArea.LIMIT_SIZE){
			return true;
		}else{
			return false;
		}
	}
	
	protected boolean isValidImageSize(int width, int height){
		if(width<=imagePage.getIconWidth() && height<=imagePage.getIconHeight()){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Attaches graphArea to canvas.
	 * @param graphArea
	 */
	protected void drawGraphArea(GraphArea graphArea){
		graphArea.attachTo(jLabelImage);
		gui.repaint();
	}
	
	protected String getImagePath(){
		if(imagePage.getIconHeight() <= 0){
			return "";
		}
		
		return imagePage.toString();
	}
	
	
	/**
	 * Returns all information in a defined format.
	 * @return
	 */
	private String dump() {
		String info="";
		
		//image
		info += "<"+IMAGE_SIZE_TAG+">"+imagePage.getIconWidth()+","+imagePage.getIconHeight()+"</"+IMAGE_SIZE_TAG+">\n\n";
		
		//indicator areas
		for(GraphArea graphArea: graphAreaList){
				info += graphArea.dump()+"\n";
		}
		
		return info;
	}
	
	private void saveInformation(){
		try {
			String filePath = imagePage.toString();
			
			filePath = filePath.substring(0, filePath.length()-3) + getExtension();
			
			FileIO.writeWholeFile(filePath, this.dump());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected String getExtension(){
		return "grph";
	}
	
	/**
	 * Returns consequent gui. Should be overridden by child class.
	 * @return
	 */
	protected AbstractGraphPainterGui getGui() {
		return new AbstractGraphPainterGui();
	}
	
	public void attachTo(JComponent jComponent){
		jComponent.add(gui);
		jComponent.revalidate();
	}
	// End of Auxiliary Methods 																#_______AM_______#
	
	//**
	// Unimplemented Methods 																	#*******UM*******#
	//**
	
	// End of Unimplemented Methods 															#_______UM_______#
}
