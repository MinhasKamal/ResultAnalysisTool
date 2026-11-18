/********************************************************************************
 * Developer: Minhas Kamal (BSSE0509)											*
 * Date : 19-06-2015															*
 *******************************************************************************/

package com.resultAnalysisTool.util.fileChoose;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.resultAnalysisTool.util.fileReadWrite.FileIO;
import com.resultAnalysisTool.util.message.Message;

@SuppressWarnings("serial")
public class FileSaver extends JFileChooser{
	private String fileData;
	private String fileName, directory;
	
	public FileSaver(String probableFilePath, String fileData){
		this.directory = probableFilePath.substring(0, probableFilePath.lastIndexOf('\\'));
		this.fileName = probableFilePath.substring(probableFilePath.lastIndexOf('\\')+1);
		this.fileData = fileData;
		
		initialComponent();
	}

	private void initialComponent(){
		setCurrentDirectory(new File(directory));
		setPreferredSize(new Dimension(500, 500));
		setSelectedFile(new File(fileName));
		setDialogTitle("Save File");
		
		setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				return "*"+fileName.substring(fileName.lastIndexOf('.'));
			}
			
			@Override
			public boolean accept(File file) {
				if(!file.isFile()){
					return true;
				}
				
				String extention = fileName.substring(fileName.lastIndexOf('.'));
				String fileExtention=file.getName().substring(file.getName().lastIndexOf('.'));
				
				return fileExtention.equalsIgnoreCase(extention);
			}
		});
		
		addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent actionEvent) {
				String command = actionEvent.getActionCommand();
				
				if (command.equals(APPROVE_SELECTION)) {
					try {
						String filePath = getSelectedFile().getAbsolutePath();
						if(!filePath.contains(".")){
							filePath += fileName.substring(fileName.lastIndexOf('.'));
						}
						
						FileIO.writeWholeFile(filePath, fileData);
					} catch (Exception e) {
						new Message("Error!", 420);
					}
				}
			}
		});
		
		showSaveDialog(null);
	}
	
	public static void main(String args[]){
		/* Set the NIMBUS look and feel */
    	try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");					
		} catch (Exception ex) {
			//do nothing if operation is unsuccessful
		}

		new FileSaver("C:\\Users\\admin\\Desktop\\IIT.txt", "Empty");
	}
}
