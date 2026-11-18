package com.resultAnalysisTool.tools.projectExplorer;

import java.awt.Desktop;
import java.io.File;
import java.util.Arrays;

import javax.swing.JPanel;

import com.resultAnalysisTool.lib.jexplore.FileExplorer;
import com.resultAnalysisTool.tools.dataAnalyse.analyseCommaSeperatedData.CSVTable;
import com.resultAnalysisTool.tools.dataAnalyse.analyseDataBase.EditorFrame;
import com.resultAnalysisTool.tools.imageProcess.areaDefine.ImageAreaDefiner;
import com.resultAnalysisTool.util.message.Message;

public class ProjectExplorer extends FileExplorer{
	private JPanel jPanelOperation;
	
	public ProjectExplorer(JPanel jPanelOperation) {
		super();
		
		this.jPanelOperation = jPanelOperation;
	}
	
	@Override
	protected void openWithSuitableEditor(File file) {
		String filePath = file.getAbsolutePath();
		String extention = filePath.substring(filePath.lastIndexOf('.')+1);
		
		if(Arrays.asList(new String[]{"jpg", "png", "jpeg", "JPG", "PNG", "JPEG"}).contains(extention)){
			jPanelOperation.removeAll();
	    	jPanelOperation.repaint();
	    	
	    	ImageAreaDefiner imageAreaDefiner = new ImageAreaDefiner();
	    	imageAreaDefiner.attachTo(jPanelOperation);
	    	imageAreaDefiner.setImage(filePath);
	    	jPanelOperation.revalidate();
	    	
		}else if(Arrays.asList(new String[]{"csd"}).contains(extention)){
			jPanelOperation.removeAll();
        	jPanelOperation.repaint();
        	
        	new CSVTable(filePath).attachTo(jPanelOperation);
        	
		}else if(Arrays.asList(new String[]{"dbf"}).contains(extention)){
			jPanelOperation.removeAll();
        	jPanelOperation.repaint();
        	
        	jPanelOperation.add(new EditorFrame(filePath));
        	jPanelOperation.revalidate();
        	
		}else{
			try {
				Desktop.getDesktop().open(file);
			} catch (Exception e) {
				new Message("Error opening file!", 420);
			}
		}
		//System.out.println(extention);
	}
	
}
