package com.resultAnalysisTool.util.fontChoose;

import java.awt.Color;
import java.awt.Font;

public class TextFont {
	public Font fontType;
	public Color textColor;
	public Color backgroundColor;
	public int textAlign;
	
	public TextFont(Font fontType, Color textColor, Color backgroundColor, int textAlign){
		this.fontType = fontType;
		this.textColor = textColor;
		this.backgroundColor = backgroundColor;
		this.textAlign = textAlign;
	}
}
