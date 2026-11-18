/****************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																	*
* Date: 09-Jun-2015																								*
****************************************************************************************************************/

package com.resultAnalysisTool.tools.dataAnalyse.tableDefine;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;


/**
 * @author Minhas Kamal
 */
@SuppressWarnings("serial")
public class TableAttributeDefinerGui extends JFrame {
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JPanel jPanelMain;
	
	JTextField jTextFieldFullPart;
	JSlider jSliderRangeSelect;
	
	JLabel jLabelSelectedPart;
	JTextField jTextFieldSelectedPart;
	JTextField jTextFieldCharCounter;
	
	JLabel jLabelFieldName;
	@SuppressWarnings("rawtypes")
	JComboBox jComboBoxFieldName;
	JCheckBox jCheckBoxIsAnswer;
	
	JButton jButtonOK;
	JButton jButtonCancel;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public TableAttributeDefinerGui() {

		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialComponent() {
		//**
		// Initialization 																		#*******I*******#
		//**
		jPanelMain = new JPanel();
		
		jTextFieldFullPart = new JTextField();
		jSliderRangeSelect = new JSlider();
		
		jLabelSelectedPart = new JLabel();
		jTextFieldSelectedPart = new JTextField();
		jTextFieldCharCounter = new JTextField();
		
		jLabelFieldName = new JLabel();
		jComboBoxFieldName = new JComboBox(new String[]{"Empty", "Roll Number", "Set Code", "Institute Code",  "Answers",
				"Registration Number"});
		jCheckBoxIsAnswer = new JCheckBox();
		
		jButtonOK = new JButton();
		jButtonCancel = new JButton();
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jPanelMain.setBackground(Color.white);
		jPanelMain.setLayout(null);
		
		jTextFieldFullPart.setCaretPosition(0);
		jTextFieldFullPart.setEditable(false);
		jTextFieldFullPart.setBackground(new Color(200, 190, 230));
		jTextFieldFullPart.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		jTextFieldFullPart.setFont(new Font("sansserif", Font.PLAIN, 15));
		jTextFieldFullPart.setBounds(20, 20, 490, 30);

		jSliderRangeSelect.setBounds(20, 30, 490, 70);
		jSliderRangeSelect.setMajorTickSpacing(10);
		jSliderRangeSelect.setMinorTickSpacing(1);
		jSliderRangeSelect.setPaintTicks(true);
		jSliderRangeSelect.setOrientation(SwingConstants.HORIZONTAL);
		jSliderRangeSelect.setValue(0);
		jSliderRangeSelect.setMinimum(0);
		jSliderRangeSelect.setMaximum(100);
		
		jLabelSelectedPart.setText("Slected part ");
		jLabelSelectedPart.setBounds(20, 105, 150, 20);
		jLabelSelectedPart.setFont(new Font("sansserif", Font.PLAIN, 15));
		
		jTextFieldSelectedPart.setEditable(false);
		jTextFieldSelectedPart.setBackground(new Color(200, 190, 230));
		jTextFieldSelectedPart.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		jTextFieldSelectedPart.setBounds(110, 100, 350, 30);
		jTextFieldSelectedPart.setFont(new Font("sansserif", Font.PLAIN, 15));
		jTextFieldSelectedPart.setHorizontalAlignment(JTextField.RIGHT);

		jTextFieldCharCounter.setEditable(false);
		jTextFieldCharCounter.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		jTextFieldCharCounter.setBackground(new Color(200, 190, 230));
		jTextFieldCharCounter.setFont(new Font("sansserif", Font.PLAIN, 15));
		jTextFieldCharCounter.setBounds(465, 100, 40, 30);
		jTextFieldCharCounter.setHorizontalAlignment(JTextField.CENTER);
		
		jLabelFieldName.setText("Field name ");
		jLabelFieldName.setFont(new Font("sansserif", Font.TRUETYPE_FONT, 15));
		jLabelFieldName.setBounds(20, 165, 100, 30);
		
		jComboBoxFieldName.setBackground(new Color(245, 245, 235));
		jComboBoxFieldName.setBounds(110, 165, 255, 30);
		jComboBoxFieldName.setFont(new Font("sansserif", Font.PLAIN, 15));
		jComboBoxFieldName.setEditable(true);
		
		jCheckBoxIsAnswer.setText("Is Answer  Field");
		jCheckBoxIsAnswer.setBounds(380, 165, 130, 30);
		jCheckBoxIsAnswer.setFont(new Font("sansserif", Font.PLAIN, 15));
		
		
		jButtonOK.setText("Next");
		jButtonOK.setBounds(345, 245, 80, 30);
		jButtonOK.setBackground(new Color(200, 190, 230));
		
		jButtonCancel.setText("Cancel");
		jButtonCancel.setBounds(430, 245, 80, 30);
		jButtonCancel.setBackground(new Color(200, 190, 230));
		
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Frame**//
		setIconImage(new ImageIcon(getClass().getResource("")).getImage());
		setBounds(200, 200, 535, 320);
		setTitle("Table Attribute Definer");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new GridLayout());
		setResizable(false);
		
		//**
		// Adding Components 																	#*******A*******#
		//**
		jPanelMain.add(jTextFieldFullPart);
		jPanelMain.add(jSliderRangeSelect);
		jPanelMain.add(jLabelSelectedPart);
		jPanelMain.add(jTextFieldCharCounter);
		jPanelMain.add(jTextFieldSelectedPart);
		jPanelMain.add(jLabelFieldName);
		jPanelMain.add(jComboBoxFieldName);
		jPanelMain.add(jCheckBoxIsAnswer);
		jPanelMain.add(jButtonOK);
		jPanelMain.add(jButtonCancel);
		
		add(jPanelMain);
		// End of Adding Components 															#_______A_______#
	}

	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create and display the form */
		TableAttributeDefinerGui gui = new TableAttributeDefinerGui();
		gui.setVisible(true);
	}
}
