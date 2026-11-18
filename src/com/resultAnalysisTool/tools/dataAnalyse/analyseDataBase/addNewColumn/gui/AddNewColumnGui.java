package com.resultAnalysisTool.tools.dataAnalyse.analyseDataBase.addNewColumn.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import com.resultAnalysisTool.tools.dataAnalyse.analyseDataBase.addNewColumn.operation.AddNewColumnTwo;
import com.resultAnalysisTool.util.message.Message;

@SuppressWarnings("serial")
public class AddNewColumnGui extends JDialog implements ActionListener{
	private JPanel contentPane;
	private JLabel NewColumnName_JLabel, incrementDistance_JLabel, orderType_JLabel, fileColumnNames_JLabel;
	private JTextField columnName_JTextField, incrementDistance_JTextField, display, fileColumnNames_JTextField;
	private JRadioButton integerElement_JRadioButton, alphabetElement_JRadioButton, empty_JRadioButton, infixEvolution; 
	private JButton ok, cancel, add1, add2, delete;
	private JButton zero, one, two, three, four, five, six, seven, eight, nine, dot, plus, minus, multiplication, division;  
	private ButtonGroup orderType_ButtonGroup;
	private String[] stringComboBoxElement = {"a", "b"}; 							// Testing only
	private int[] flag = {420, 420};
	private int infixTrack =0, addCharTrack=0;
	private boolean add2Track = false;
	private String mainString = "";
	private String[][] fileContent;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxFields;
	
	private Vector<String> displayData= new Vector<String>();
	private Vector<String> dataToBeSend= new Vector<String>();
	
	
	
	public AddNewColumnGui(String[][] fileContent, String[] columnNames) {
		int fileContentLength = fileContent.length;
		this.fileContent = new String[fileContentLength+1][];
		this.fileContent[0] = columnNames;
		for(int i=0; i<fileContentLength; i++){
			this.fileContent[i+1] = fileContent[i];
		}
		
		stringComboBoxElement =  columnNames;
		initComponent();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initComponent() {
		BufferedImage removeJavaImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		setIconImage(removeJavaImage);
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		
		display = new JTextField();
		display.setEditable(false);
		display.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		display.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 18));
		display.setBackground(new Color(200, 230, 230));
		
		NewColumnName_JLabel = new JLabel("New Column Name:");
		NewColumnName_JLabel.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 17));
		
		columnName_JTextField = new JTextField();
		columnName_JTextField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		columnName_JTextField.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 15));
		columnName_JTextField.setBackground(new Color(200, 230, 230));
		
		fileColumnNames_JLabel = new JLabel("File Column Name:");
		fileColumnNames_JLabel.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 17));
		
		fileColumnNames_JTextField = new JTextField();
		fileColumnNames_JTextField.setEditable(false);
		fileColumnNames_JTextField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		fileColumnNames_JTextField.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 15));
		fileColumnNames_JTextField.setBackground(new Color(200, 230, 230));
		
		comboBoxFields = new JComboBox(stringComboBoxElement);
		comboBoxFields.setBackground(new Color(245, 245, 235));
		comboBoxFields.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 15));
		comboBoxFields.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		comboBoxFields.addItemListener(new ItemListener() {								//ComboBox Item Listener
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
					fileColumnNames_JTextField.setText((String) e.getItem());
			}
		});																				//end ComboBox Item Listener
		
		zero = new JButton("0");
		zero.setBackground(new Color(200, 230, 230));
		
		one = new JButton("1");
		one.setBackground(new Color(200, 230, 230));
		
		two = new JButton("2");
		two.setBackground(new Color(200, 230, 230));
		
		three = new JButton("3");
		three.setBackground(new Color(200, 230, 230));
		
		four = new JButton("4");
		four.setBackground(new Color(200, 230, 230));
		
		five = new JButton("5");
		five.setBackground(new Color(200, 230, 230));
		
		six = new JButton("6");
		six.setBackground(new Color(200, 230, 230));
		
		seven = new JButton("7");
		seven.setBackground(new Color(200, 230, 230));
		
		eight = new JButton("8");
		eight.setBackground(new Color(200, 230, 230));
		
		nine = new JButton("9");
		nine.setBackground(new Color(200, 230, 230));
		
		dot = new JButton(".");
		dot.setBackground(new Color(200, 230, 230));
		
		plus = new JButton("+");
		plus.setBackground(new Color(200, 230, 230));

		minus = new JButton("-");
		minus.setBackground(new Color(200, 230, 230));

		multiplication = new JButton("x");
		multiplication.setBackground(new Color(200, 230, 230));

		division = new JButton("/");
		division.setBackground(new Color(200, 230, 230));
		
		orderType_ButtonGroup = new ButtonGroup();
		
		orderType_JLabel = new JLabel("Data order type:");
		orderType_JLabel.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 17));
		
		integerElement_JRadioButton = new JRadioButton("Integer");
		integerElement_JRadioButton.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 15));
		integerElement_JRadioButton.addActionListener(this);
		
		alphabetElement_JRadioButton = new JRadioButton("Alphabet");
		alphabetElement_JRadioButton.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 15));
		alphabetElement_JRadioButton.addActionListener(this);
		
		empty_JRadioButton = new JRadioButton("Empty");
		empty_JRadioButton.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 14));
		empty_JRadioButton.addActionListener(this);
		
		infixEvolution = new JRadioButton("Infix Evolution");
		infixEvolution.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 14));
		infixEvolution.addActionListener(this);
		
		incrementDistance_JLabel = new JLabel("Range of Increment:");
		incrementDistance_JLabel.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 15));
		
		incrementDistance_JTextField = new JTextField();
		incrementDistance_JTextField.setEnabled(false);
		incrementDistance_JTextField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		incrementDistance_JTextField.setBounds(130 , 150, 280, 25 );
		incrementDistance_JTextField.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 15));
		incrementDistance_JTextField.setBackground(new Color(200, 230, 230));
		
		/*
		 * Action Listener
		*/
		zero.addActionListener(this);
		one.addActionListener(this);
		two.addActionListener(this);
		three.addActionListener(this);
		four.addActionListener(this);
		five.addActionListener(this);
		six.addActionListener(this);
		seven.addActionListener(this);
		eight.addActionListener(this);
		nine.addActionListener(this);
		dot.addActionListener(this);
		plus.addActionListener(this);
		minus.addActionListener(this);
		multiplication.addActionListener(this);
		division.addActionListener(this);
		
		add1 = new JButton("Add");
		add1.setBackground(new Color(200, 230, 230));
		add1.setEnabled(false);
		add1.addActionListener(new ActionListener() {								//Operation
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = fileColumnNames_JTextField.getText();
				if(display.getText().isEmpty()){
					addChar();
					/*String temp = fileColumnNames_JTextField.getText();
					mainString = mainString +" " + '"' + temp+ '"' "\"temp\"";
					System.out.println(mainString);
					display.setText(display.getText()+ temp);*/
				}
				else{
					if(add2Track){
						setMainString("^");
						//mainString+="^";
						addChar();
						add2Track=false;
						//System.out.println("What");
					}
					/*
					mainString = mainString +" " + '"' + temp+ '"' "\"temp\"";
					System.out.println(mainString);
					display.setText(display.getText()+ temp);
					*/
				}
				setMainString(mainString +" " + '"' + temp+ '"');
				setUserText(temp);
			}
		});																		//Operation

		add2 = new JButton("Add");
		add2.setBackground(new Color(200, 230, 230));
		add2.addActionListener(new ActionListener() {								//Operation
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				infixTrack++;
				if(display.getText().isEmpty()){
					setMainString(incrementDistance_JTextField.getText()+" ^");
					setUserText(incrementDistance_JTextField.getText());
				}else{
					//if(fieldCheck == 0){
					    setMainString(" ");
						addChar();
						String temp = incrementDistance_JTextField.getText();
						setMainString(" ^"+ temp+" ");
						setUserText(temp);
				}
				add2Track=true;
			}
		});																		//Operation
		
		delete = new JButton("Delete");
		delete.setBackground(new Color(200, 230, 230));
		delete.addActionListener(new ActionListener() {								//Operation
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteElement();
			}
		});
		ok = new JButton("OK");
		ok.setBackground(new Color(200, 230, 230));
		ok.addActionListener(new ActionListener() {								//Operation
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(addCharTrack%2==1){
					if(!(add2Track==true)){
						setMainString(" ");
						addChar();
					}
				}
				//System.out.println(mainString);
				errorChecking();
				
				
				fileContent = dimensionChange(fileContent);
				fileContent = new AddNewColumnTwo().doAdding(fileContent, mainString, columnName_JTextField.getText(), flag );
				fileContent = dimensionChange(fileContent);

				
				dispose();
				//call operation column and send data to that class
			}
		});																		//Operation
		
		cancel = new JButton("Cancel");
		cancel.setBounds(325, 200, 80, 30);
		cancel.setBackground(new Color(200, 230, 230));
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		
		/*
		 * Setting bounds
		*/
		display.setBounds(10, 10, 660, 50);
		seven.setBounds(10, 70, 50, 30);
		eight.setBounds(70, 70, 50, 30);
		nine.setBounds(130, 70, 50, 30);
		division.setBounds(190, 70, 50, 30);
		four.setBounds(10, 110, 50, 30);
		five.setBounds(70, 110, 50, 30);
		six.setBounds(130, 110, 50, 30);
		multiplication.setBounds(190, 110, 50, 30);
		one.setBounds(10, 150, 50, 30);
		two.setBounds(70, 150, 50, 30);
		three.setBounds(130, 150, 50, 30);
		minus.setBounds(190, 150, 50, 30);
		zero.setBounds(10, 190, 110, 30);
		dot.setBounds(130, 190, 50, 30);
		plus.setBounds(190, 190, 50, 30);
		
		orderType_JLabel.setBounds(270, 150, 120, 30);
		integerElement_JRadioButton.setBounds(410, 152, 70, 30);
		alphabetElement_JRadioButton.setBounds(480, 152, 88, 30);
		empty_JRadioButton.setBounds(565, 152, 65, 30);
		infixEvolution.setBounds(630, 152, 130, 30);
		incrementDistance_JLabel.setBounds(270, 190, 140, 30);
		incrementDistance_JTextField.setBounds(420, 190, 325, 30);
		
		delete.setBounds(675, 10, 70, 50);
		add1.setBounds(685, 110, 60, 30);
		add2.setBounds(685, 190, 60, 30);
		ok.setBounds(575, 260, 80, 30);
		cancel.setBounds(665, 260, 80, 30);
		
		NewColumnName_JLabel.setBounds(270, 70, 150, 30);
		columnName_JTextField.setBounds(420 , 70, 325, 30 );
		
		fileColumnNames_JLabel.setBounds(270, 110, 150, 30);
		fileColumnNames_JTextField.setBounds(420, 110, 135, 30);
		comboBoxFields.setBounds( 565, 110, 115, 30);
		
		orderType_ButtonGroup.add(integerElement_JRadioButton);
		orderType_ButtonGroup.add(alphabetElement_JRadioButton);
		orderType_ButtonGroup.add(empty_JRadioButton);
		orderType_ButtonGroup.add(infixEvolution);
		
		contentPane.add(display);
		contentPane.add(zero);
		contentPane.add(one);
		contentPane.add(two);
		contentPane.add(three);
		contentPane.add(four);
		contentPane.add(five);
		contentPane.add(six);
		contentPane.add(seven);
		contentPane.add(eight);
		contentPane.add(nine);
		contentPane.add(dot);
		contentPane.add(plus);
		contentPane.add(minus);
		contentPane.add(multiplication);
		contentPane.add(division);
		contentPane.add(NewColumnName_JLabel);
		contentPane.add(columnName_JTextField);
		contentPane.add(fileColumnNames_JLabel);
		contentPane.add(fileColumnNames_JTextField);
		contentPane.add(comboBoxFields);
		
		contentPane.add(orderType_JLabel);
		contentPane.add(integerElement_JRadioButton);
		contentPane.add(alphabetElement_JRadioButton);
		contentPane.add(empty_JRadioButton);	
		contentPane.add(infixEvolution);
		
		contentPane.add(incrementDistance_JLabel);
		contentPane.add(incrementDistance_JTextField);
		
		contentPane.add(delete);
		contentPane.add(ok);
		contentPane.add(add1);
		contentPane.add(cancel);
		
		setTitle("Column Add");
		setContentPane(contentPane);
		setVisible(true);
		setResizable(false);
		setSize(770, 340);
		setLocation(200, 200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
		setModal(true);
	}
	
	private String[][] dimensionChange(String[][] arr){
		int arrLength=arr.length;
		int arrWidth=arr[0].length;
		
		String[][] newArr=new String[(int) arrWidth][(int) arrLength];
		
		for(int i=0;i<arrLength;i++){
			for(int j=0;j<arrWidth;j++){
				newArr[j][i]=arr[i][j];
			}
		}
		
		return newArr;
	}


	private void errorChecking(){
		boolean errorCheck = false;
		
		if(columnName_JTextField.getText().equals("")){
			new Message("Column name is blank.", 210);
		}else if(empty_JRadioButton.isSelected() == false && integerElement_JRadioButton.isSelected() == false && alphabetElement_JRadioButton.isSelected() == false && infixEvolution.isSelected() == false){
			new Message("You have to choose one radioButtion.", 210);
		}else if(empty_JRadioButton.isSelected()){
			errorCheck = true;
		}else if(integerElement_JRadioButton.isSelected() || alphabetElement_JRadioButton.isSelected()){
			try{
				flag[1]= Integer.parseInt(incrementDistance_JTextField.getText());
			}catch(Exception exception){
				new Message("Range of increment is not valid", ERROR);
			}
				
		}else if(infixEvolution.isSelected() && display.getText().equals("")){
			new Message("No infix evolution is given", ERROR);
		}
		
		if(errorCheck){
			//TODO call Ishmam class from here or from EditorWithPopupMenuDemo class 

			dispose();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(infixEvolution.isSelected()){
/*			if(display.getText().isEmpty() && e.getSource() == add2){

				mainString += incrementDistance_JTextField.getText();
				display.setText(display.getText()+incrementDistance_JTextField.getText());
				
				System.out.println(mainString);
			}*/
			/*else{*/
			
			add1.setEnabled(true);
			flag[0]= 1;
			incrementDistance_JTextField.setEnabled(true);
			incrementDistance_JLabel.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 17));
			incrementDistance_JLabel.setText("Enter Text:");
			
			incrementDistance_JTextField.setBounds(420, 190, 250, 30);
			contentPane.add(add2);
			
			add1.setEnabled(true);
			
				if(display.getText().isEmpty() && !(infixTrack==0)){
					addChar();
				//	System.out.println("Hello");
				}
				
				if(add2Track){
					setMainString("^");
					addChar();
					add2Track=false;
				}
				
				if(e.getSource() == zero){
					setMainString(zero.getText());
					setUserText(zero.getText());
					/*display.setText(display.getText()+zero.getText());
					mainString += " "+zero.getText();*/
				}else if(e.getSource() == one){
					setMainString(one.getText());
					setUserText(one.getText());/*
					display.setText(display.getText()+one.getText());
					mainString += one.getText();*/
				}else if(e.getSource() == two){
					setMainString(two.getText());
					setUserText(two.getText());/*
					display.setText(display.getText()+two.getText());
					mainString += two.getText();*/
				}else if(e.getSource() == three){
					setMainString(three.getText());
					setUserText(three.getText());/*
					display.setText(display.getText()+three.getText());
					mainString += three.getText();*/
				}else if(e.getSource() == four){
					setMainString(four.getText());
					setUserText(four.getText());/*
					display.setText(display.getText()+four.getText());
					mainString += four.getText();*/
				}else if(e.getSource() == five){
					setMainString(five.getText());
					setUserText(five.getText());/*
					display.setText(display.getText()+five.getText());
					mainString += five.getText();*/
				}else if(e.getSource() == six){
					setMainString(six.getText());
					setUserText(six.getText());/*
					display.setText(display.getText()+six.getText());
					mainString += six.getText();*/
				}else if(e.getSource() == seven){
					setMainString(seven.getText());
					setUserText(seven.getText());/*
					display.setText(display.getText()+seven.getText());
					mainString += seven.getText();*/
				}else if(e.getSource() == eight){
					setMainString(eight.getText());
					setUserText(eight.getText());/*
					display.setText(display.getText()+eight.getText());
					mainString += eight.getText();*/
				}else if(e.getSource() == nine){
					setMainString(nine.getText());
					setUserText(nine.getText());/*
					display.setText(display.getText()+nine.getText());
					mainString += nine.getText();*/
				}else if(e.getSource() == dot){
					setUserText(dot.getText());
					setMainString(dot.getText());
					/*
					display.setText(display.getText()+dot.getText());
					mainString += " "+dot.getText();*/
				}else if(e.getSource() == plus){
					setUserText(plus.getText());
					setMainString(" "+ plus.getText()+ " ");
					/*display.setText(display.getText()+plus.getText());
					mainString += " "+plus.getText()+ " ";*/
				}else if(e.getSource() == minus){
					setUserText(minus.getText());
					setMainString(" "+ minus.getText()+ " ");
				}else if(e.getSource() == multiplication){
					setUserText(multiplication.getText());
					setMainString(" "+ multiplication.getText()+ " ");
				}else if(e.getSource() == division){
					setUserText(division.getText());
					setMainString(" "+ division.getText()+ " ");
					/*display.setText(display.getText()+division.getText());
					mainString += " "+ division.getText()+ " ";*/
				}
				
				infixTrack++;
		}
		
	/*	if(infixEvolution.isSelected()){
				add1.setEnabled(true);
				flag[0]= 1;
				incrementDistance_JTextField.setEnabled(true);
				incrementDistance_JLabel.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 17));
				incrementDistance_JLabel.setText("Enter Text:");
				
				incrementDistance_JTextField.setBounds(420, 190, 250, 30);
				contentPane.add(add2);
			}*/else{
				
				infixTrack=0;
				mainString="";
				display.setText("");
				dataToBeSend.removeAllElements();
				displayData.removeAllElements();
				//System.out.println(dataToBeSend.toString());
				
				add1.setEnabled(false);
				incrementDistance_JLabel.setFont(new Font("sansserif", Font.TRUETYPE_FONT | Font.PLAIN, 15));
				incrementDistance_JLabel.setText("Range of Increment:");
				incrementDistance_JTextField.setBounds(420, 190, 325, 30);
				contentPane.remove(add2);
				
				if(integerElement_JRadioButton.isSelected()){
					flag[0]= 2;
					
					incrementDistance_JTextField.setEnabled(true);
				}else if(alphabetElement_JRadioButton.isSelected()){
					incrementDistance_JTextField.setEnabled(true);
					flag[0]= 3;
				}else{
					if(empty_JRadioButton.isSelected()){
						flag[0]= 1;
						flag[1] = 0;
						incrementDistance_JTextField.setEnabled(false);
					} 
				}
			}		
	}
	
	private void setMainString(String string) {
		mainString += string;
		dataToBeSend.add(string);
		//System.out.println("Main: "+dataToBeSend.toString()+"  %"+mainString);
	}

	private void setUserText(String text) {
		display.setText(display.getText()+text);
		displayData.add(text);
		//System.out.println("User: "+displayData.toString());
	}

	public void addChar(){
		String s =""+(char)200;
		mainString += (char)200;
		dataToBeSend.add(s);
		addCharTrack++;
	}
	
	protected void deleteElement() {
		
		
		
		if(displayData.isEmpty()){
			new Message("No element found", ERROR);
		}
		else{
			
			displayData.remove(displayData.size()-1);
			dataToBeSend.remove(dataToBeSend.size()-1);
			mainString=dataToBeSend.toString();
			//System.out.println(mainString);
			
			String s= "";
			for(String string: displayData){
				s+=string;
			}
			//System.out.println(s);

			repaint();
			display.setText(s);
		}
	}
	
	public String[][] getNewFileContent(){
		return fileContent;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[][] m = new String[4][4];
		
		new AddNewColumnGui(m, m[0]);
	}
}
