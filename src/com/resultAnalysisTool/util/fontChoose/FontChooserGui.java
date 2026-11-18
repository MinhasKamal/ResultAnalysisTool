/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: 06-Jun-2015																							*
*************************************************************************************************************/

package com.resultAnalysisTool.util.fontChoose;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class FontChooserGui extends JDialog {
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	JPanel jPanelMain;
	
	JLabel jLabelFontFace;
	@SuppressWarnings("rawtypes")
	JComboBox jComboBoxFontFace;
	
	JLabel jLabelFontStyle;
	JToggleButton[] jTButtonFontStyle;
	
	JLabel jLabelFontSize;
	@SuppressWarnings("rawtypes")
	JComboBox jComboBoxFontSize;
	
	JLabel jLabelTextColor;
	JButton jButtonTextColor;
	
	JLabel jLabelTextBackgroundColor;
	JButton jButtonTextBackgroundColor;
	
	JLabel jLabelTextAlign;
	JToggleButton[] jTButtonAlignment;
	
	JTextField jTextFieldSample;
	
	JButton jButtonOk;
	JButton jButtonCancle;
	// End of Variable Declaration 																			#_______D_______#

	
	/**
	 * Constructor.
	 */
	public FontChooserGui() {
		

		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables, placing them all to specific space on the frame and adding action
	 * listener to them. Also specifies criteria of the main frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialComponent() {
		//**
		// Initialization 																				#*******I*******#
		//**
		jPanelMain = new JPanel();
		
		jLabelFontFace = new JLabel();
		jComboBoxFontFace = new JComboBox();
		
		jLabelFontStyle = new JLabel();
		jTButtonFontStyle = new JToggleButton[2];
		
		jLabelFontSize = new JLabel();
		jComboBoxFontSize = new JComboBox();
		
		jLabelTextColor = new JLabel();
		jButtonTextColor = new JButton();
		
		jLabelTextBackgroundColor = new JLabel();
		jButtonTextBackgroundColor = new JButton();
		
		jLabelTextAlign = new JLabel();
		jTButtonAlignment = new JToggleButton[3];
		
		jTextFieldSample = new JTextField();
		
		jButtonOk = new JButton();
		jButtonCancle = new JButton();
		// End of Initialization																		#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 												#*******S*******#
		//**
		jPanelMain.setBackground(Color.white);
		jPanelMain.setLayout(null);

		jLabelFontFace.setText("Font Face");
		jLabelFontFace.setBounds(10, 10, 130, 20);
		
		jComboBoxFontFace.setBounds(10, 30, 130, 25);
		jComboBoxFontFace.setModel(new DefaultComboBoxModel(new String[]{"Arial", "Tahoma", "Times New Roman",
				"Verdana", "Consolas", "Calibri", "Bell MT", "Lucida Sans", "Century", "DigifaceWide", "Lucida Bright",
				"Serif", "Monospaced", "Dialog"}));
		
		jLabelFontStyle.setText("Font Style");
		jLabelFontStyle.setBounds(10, 70, 130, 20);
		
		jTButtonFontStyle[0] = new JToggleButton();
		jTButtonFontStyle[0].setText("B");
		jTButtonFontStyle[0].setFont(new Font(Font.SERIF, Font.BOLD, 14));
		jTButtonFontStyle[0].setBounds(10, 90, 65, 25);
		
		jTButtonFontStyle[1] = new JToggleButton();
		jTButtonFontStyle[1].setText("I");
		jTButtonFontStyle[1].setFont(new Font(Font.SERIF, Font.ITALIC, 14));
		jTButtonFontStyle[1].setBounds(75, 90, 65, 25);
		
		
		jLabelFontSize.setText("Font Size");
		jLabelFontSize.setBounds(10, 130, 130, 20);
		
		jComboBoxFontSize.setBounds(10, 150, 130, 25);
		jComboBoxFontSize.setModel(new DefaultComboBoxModel(new String[]{"6", "10", "12", "14", "18", "20",
				"24", "28", "32", "38", "48", "54", "60"}));
		jComboBoxFontSize.setEditable(true);
		
		jLabelTextColor.setText("Text Color");
		jLabelTextColor.setBounds(220, 10, 130, 20);
		
		jButtonTextColor.setText("");
		jButtonTextColor.setBounds(220, 30, 130, 25);
		jButtonTextColor.setHorizontalAlignment(SwingConstants.LEFT);
		
		jLabelTextBackgroundColor.setText("Background Color");
		jLabelTextBackgroundColor.setBounds(220, 70, 130, 20);
		
		jButtonTextBackgroundColor.setText("");
		jButtonTextBackgroundColor.setBounds(220, 90, 130, 25);
		jButtonTextBackgroundColor.setHorizontalAlignment(SwingConstants.LEFT);
		
		jLabelTextAlign.setText("Text Align");
		jLabelTextAlign.setBounds(220, 130, 130, 20);
		
		for(int i=0; i<3; i++){
			jTButtonAlignment[i] = new JToggleButton();
			jTButtonAlignment[i].setBounds(220+i*44, 150, 41, 25);
			jTButtonAlignment[i].setFont(new Font("Vrinda", Font.PLAIN, 14));
			jTButtonAlignment[i].setBorder(null);
		}
		jTButtonAlignment[0].setText("|>");
		jTButtonAlignment[1].setText("||");
		jTButtonAlignment[2].setText("<|");
		
		jTextFieldSample.setBounds(10, 210, 340, 50);
		jTextFieldSample.setText("ABYZabyz 0123@;?!");
		jTextFieldSample.setEditable(false);
		jTextFieldSample.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
		
		jButtonOk.setBounds(210, 280, 70, 25);
		jButtonOk.setText("OK");
		
		jButtonCancle.setBounds(280, 280, 70, 25);
		jButtonCancle.setText("Cancle");
		
		// End of Setting Bounds and Attributes 														#_______S_______#

		//**
		// Adding Components 																			#*******A*******#
		//**
		jPanelMain.add(jLabelFontFace);
		jPanelMain.add(jComboBoxFontFace);
		
		jPanelMain.add(jLabelFontStyle);
		jPanelMain.add(jTButtonFontStyle[0]);
		jPanelMain.add(jTButtonFontStyle[1]);
		
		jPanelMain.add(jLabelFontSize);
		jPanelMain.add(jComboBoxFontSize);

		jPanelMain.add(jLabelTextColor);
		jPanelMain.add(jButtonTextColor);
		
		jPanelMain.add(jLabelTextBackgroundColor);
		jPanelMain.add(jButtonTextBackgroundColor);
		
		jPanelMain.add(jLabelTextAlign);
		for(int i=0; i<3; i++){
			jPanelMain.add(jTButtonAlignment[i]);
		}
		
		jPanelMain.add(jButtonOk);
		jPanelMain.add(jButtonCancle);

		jPanelMain.add(jTextFieldSample);
		// End of Adding Components 																	#_______A_______#

		//**Setting Criterion of the Frame**//
		setIconImage(new ImageIcon(getClass().getResource("")).getImage());
		setBounds(270, 200, 370, 350);
		setTitle("Font Chooser");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new GridLayout());
		setResizable(false);
		add(jPanelMain);
	}

	/**///Main Method
	public static void main(String args[]) {
		/*// Set the Web look and feel //*/
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create and display the form */
		FontChooserGui fontChooserGui = new FontChooserGui();
		fontChooserGui.setVisible(true);
	}
}
