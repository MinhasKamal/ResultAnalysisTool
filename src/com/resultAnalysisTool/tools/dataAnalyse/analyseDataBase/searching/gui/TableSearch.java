/**
 * @author Khandaker Mamun Ahmed(IIT, DU)
 * Date: 25-02-2014
 * 
 *	This class will search the attempted part ex: Roll, Name etc in the specific file and also in the specific field
 */


package com.resultAnalysisTool.tools.dataAnalyse.analyseDataBase.searching.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import com.resultAnalysisTool.util.message.Message;
import com.resultAnalysisTool.tools.dataAnalyse.analyseDataBase.searching.operation.NewSearching;

@SuppressWarnings("serial")
public class TableSearch extends JDialog{
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxFields;
	private JLabel  labelFlieldName, labelSearchTopic; 
	private JTextField textFieldName, textSearchTopic;
	private JButton ok, cancel;
	private String[][] fileContent;
	private JLabel contentPane;
	//private String stringComboBoxElement[];	
	private String[] columnNames;
	
	/**
	 * constructor for objects of class Search 
	 * @param fileContent The path of file 
	 */
	public TableSearch(String[][] fileContent, String[] columnNames){
		this.fileContent = fileContent;
		this.columnNames = columnNames;
		
		initComponent();
	}
	
	
	
	/**
	 * adds texts to <code>textFieldName</code> textField
	 * @param firstFieldName The first Field(string) of <strong>JCombobox</strong>
	 */
	public void addFirstFieldName(String firstFieldName){
		
		textFieldName.setText(firstFieldName);
	}
	
	public String[][] getNewFileContent(){
		String[][] fileData = new String[fileContent.length-1][];
		
		for(int i=0; i<fileData.length; i++){
			fileData[i] = fileContent[i+1];
		}
		return fileData;
	}
	
	
	/**
	 * creates the Graphics user interface 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initComponent(){
		setTitle("Search");
		BufferedImage removeJavaImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		setIconImage(removeJavaImage);
		
		
		contentPane = new JLabel();	
		

		labelFlieldName = new JLabel("Field  Name");
		labelFlieldName.setBounds(20, 25, 150, 30);
		labelFlieldName.setFont(new Font("sansserif", Font.TRUETYPE_FONT, 15));
		contentPane.add(labelFlieldName);
		/*
		labelFileName = new JLabel ("File  Name");
		labelFileName.setBounds(20, 70, 150, 30);								//
		labelFileName.setFont(new Font("sansserif", Font.TRUETYPE_FONT, 15));
		contentPane.add(labelFileName);
		*/
		labelSearchTopic = new JLabel("Search Content");
		labelSearchTopic.setBounds(20, 70, 150, 30);
		labelSearchTopic.setFont(new Font("sansserif", Font.TRUETYPE_FONT, 15));
		contentPane.add(labelSearchTopic);
		
		textFieldName = new JTextField();
		textFieldName.setEditable(false);
		textFieldName.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		textFieldName.setBounds(140 , 25, 200, 30 );
		textFieldName.setBackground(new Color(180, 200, 230));
		textFieldName.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 15));
		contentPane.add(textFieldName);
		/*
		textFileName = new JTextField();
		textFileName.setText(filePath[0]);
		textFileName.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		textFileName.setBounds(140 , 70, 385, 30 );															//
		textFileName.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 15));
		textFileName.setBackground(new Color(180, 200, 230));
		textFileName.setEditable(false);
		contentPane.add(textFileName);	
		*/
		// adding the first field name to textField
		
		// adding the first field name to textField
		addFirstFieldName(columnNames[0]);
		
		textSearchTopic = new JTextField();
		textSearchTopic.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		textSearchTopic.setBounds( 140 , 70, 385, 30);
		textSearchTopic.setBackground(new Color(180, 200, 230));
		textSearchTopic.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 15));
		contentPane.add(textSearchTopic);
		
		comboBoxFields = new JComboBox(columnNames);
		comboBoxFields.setBackground(new Color(180, 200, 230));
		comboBoxFields.setBounds(350, 25, 175, 30);
		comboBoxFields.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 15));
		comboBoxFields.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		if(columnNames.length > 5)
			comboBoxFields.setMaximumRowCount(5);
		else 
			comboBoxFields.setMaximumRowCount(columnNames.length);
		
		comboBoxFields.addItemListener(new ItemListener() {					//ComboBox Item Listener
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
					textFieldName.setText((String) e.getItem());
			}
		});																	//end ComboBox Item Listener
		contentPane.add(comboBoxFields);
		
		ok = new JButton("OK");
		ok.setBounds(352, 150, 80, 30);
		ok.setBackground(new Color(180, 200, 230));
		contentPane.add(ok);
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(textFieldName.getText().equals(""))
					new Message("Field name is blank", 210);
				else if(textSearchTopic.getText().equals(""))
					new Message("Search Content field is blank", 210);
				else{
					// Call the search operation method and set the fieldName , FileName and the search topic to search 
					
					//TODO multiple file sending 
					String[][] fileData = new String[fileContent.length+1][];
					
					fileData[0] = columnNames;
					for(int i=1; i<fileData.length; i++){
						fileData[i] = fileContent[i-1];
					}
					
					fileContent =	new NewSearching().multipleSearching(fileData, textFieldName.getText(), textSearchTopic.getText() );
					
					/*if(fileContent){
						new Message("Error!", 420);
					}*/
					
					dispose();
				}
			}
		});
		
		cancel = new JButton("Cancel");
		cancel.setBounds(445, 150, 80, 30);
		cancel.setBackground(new Color(180, 200, 230));
		contentPane.add(cancel);
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		
		add(contentPane);
    	setIconImage(new ImageIcon(getClass().getResource("")).getImage());
		setResizable(false);
		setSize(550, 225);
		setLocation(200, 200);
		setModal(true);
		setVisible(true);
//		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	/**///test only
	public static void main(String[] args) {	
		/* Set the NIMBUS look and feel */
    	try {
			javax.swing.UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");					
		} catch (Exception ex) {
			//do nothing if operation unsuccessful
		}
		String[] column = {"a", "b", "c", "d"};
		String[][] fileContents = new String[4][4];
	
		String[][] newFileContent = new TableSearch(fileContents, column).getNewFileContent();
		
		for(String[] str: newFileContent){
			for(String str2: str){
				System.out.print(str2 + ", ");
			}
			
			System.out.println();
		}
	}
	/**/
	
	
}
