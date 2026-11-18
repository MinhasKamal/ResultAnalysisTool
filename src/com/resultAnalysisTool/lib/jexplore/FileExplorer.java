/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 17-Mar-2015																								*
* Modification: 14-May-2015																						*
****************************************************************************************************************/

package com.resultAnalysisTool.lib.jexplore;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.resultAnalysisTool.util.fileChoose.FileChooser;
import com.resultAnalysisTool.util.message.Confirm;
import com.resultAnalysisTool.util.message.Message;


/**
 * 
 * 
 * @author Minhas Kamal
 */
public class FileExplorer{
	// GUI Declaration
	public FileExplorerGui gui;
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	private String rootPath;
	
	private JTextField jTextFieldRootPath;
	private JButton jButtonGo;
	
	private JPanel jPanelView;
	
	private JButton jButtonOpen;
	private JButton jButtonRefresh;
	private JButton jButtonDelete;
	
	private JTree jTree;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public FileExplorer(String rootPath) {
		this.rootPath = rootPath;
		
		initialComponent();
		loadAllProjects();
	}
	
	public FileExplorer() {
		this(new File("").getAbsoluteFile().getParent());
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new FileExplorerGui();
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jTextFieldRootPath = gui.jTextFieldRootPath;
		jButtonGo = gui.jButtonGo;
		
		jPanelView = gui.jPanelView;
		
		jButtonOpen = gui.jButtonOpen;
		jButtonRefresh = gui.jButtonRefresh;
		jButtonDelete = gui.jButtonDelete;
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jButtonGo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonGoActionPerformed(evt);
            }
        });
		
		jButtonOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
				jButtonOpenActionPerformed(evt);
            }
        });
		
		jButtonRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
           		jButtonRefreshActionPerformed(evt);
            }
        });
		
		jButtonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonDeleteActionPerformed(evt);
            }
        });
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	private void jButtonGoActionPerformed(ActionEvent evt){
		String folderPath = new FileChooser(new String[]{"png", "jpg", "jpeg"}).chooseFilePathFromComputer();
		if(!folderPath.contains(".")){
			return ;
		}
		
		rootPath = folderPath.substring(0, folderPath.lastIndexOf('\\'));
		
		loadAllProjects();
	}
	
	private void jButtonOpenActionPerformed(ActionEvent evt){
		if(jTree.isSelectionEmpty()){
			return;
		}
		
		File file = getSelectedFile();
		if(file.isFile()){
			openWithSuitableEditor(getSelectedFile());
		}
	}
		
	private void jButtonRefreshActionPerformed(ActionEvent evt){
		if(jTree.isSelectionEmpty()){
			return;
		}
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getSelectionPath().getLastPathComponent();		
		DefaultTreeModel model = (DefaultTreeModel) jTree.getModel();
		
		node.removeAllChildren();
		model.reload(node);
		
		jTreeValueChanged();
		
		jTree.expandPath(new TreePath(node));
	}
	
	private void jButtonDeleteActionPerformed(ActionEvent evt){
		if(jTree.isSelectionEmpty()){
			return;
		}
		
		File file = getSelectedFile();
		
		if(new Confirm("Are you sure?\n File will be permanently deleted.").getDecision()){
			file.delete();
		
			if(!file.exists()){
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getSelectionPath().getLastPathComponent();		
				DefaultTreeModel model = (DefaultTreeModel) jTree.getModel();
				
				node.removeFromParent();
				model.reload(node);
			}else{
				new Message("Sorry cannot delete.", 210);
			}
		}
		
	}
	
	private void jTreeValueChanged() {
		if(jTree.isSelectionEmpty()){
			return;
		}
		
		File file = getSelectedFile();
		
		if(!file.isFile()){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getSelectionPath().getLastPathComponent();
			
			if(node.getChildCount() == 0){
				addAllChildNodes(node, file);
			}
		}
	}
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	private void loadAllProjects(){
		File rootFile = new File(rootPath);
		jPanelView.removeAll();
		
		jTree = new JTree(new DefaultMutableTreeNode(rootFile.getName()));
		addActionListener(jTree);
		jPanelView.add(jTree, "north");
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getModel().getRoot();
		addAllChildNodes(node, rootFile);
		
		jTree.expandPath(new TreePath(node));
		
		jTextFieldRootPath.setText(rootPath);
	}
	
	private void addActionListener(JTree jTree){
		jTree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
	        public void valueChanged(TreeSelectionEvent evt) {
	            jTreeValueChanged();
	        }
	    });
	}
	
	private File getSelectedFile() {
		String fullFilePath = jTree.getSelectionPath().toString();
		fullFilePath = fullFilePath.substring(1, fullFilePath.length()-1);
		fullFilePath = "\\" + fullFilePath.replace(", ", "\\");
		fullFilePath = rootPath.substring(0, rootPath.lastIndexOf('\\')) + fullFilePath;
		
		return new File(fullFilePath);
	}
	
	private void addAllChildNodes(DefaultMutableTreeNode parentNode, File parentfile) {
		for(File file: parentfile.listFiles()){
			parentNode.add(new DefaultMutableTreeNode(file.getName()));
		}
	}
	
	protected void openWithSuitableEditor(File file) {
		try {
			Desktop.getDesktop().open(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	
	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create */
		
		
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridLayout());
		
		FileExplorer fileExplorer = new FileExplorer();
		fileExplorer.attachTo(jPanel);
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 5, 500, 700);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.add(jPanel);
	}
}
