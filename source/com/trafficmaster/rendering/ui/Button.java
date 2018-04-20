package com.trafficmaster.rendering.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.trafficmaster.TrafficMaster;
import com.trafficmaster.utilities.Fonts;

public class Button extends Rectangle
{
	//Variables
	
	//Saves the Font that will be used
	private Font font;
	//Saves the font that is selected
	private Font selectedFont;
	//Saves the color that will be used for the Font
	private Color color;
	//Save the color that will be used for the selected Font
	private Color selectedColor;
	//Saves whether the Font is selected or not
	private boolean selected;
	//Save the current text
	private String text;
	//Save height of the text
	private int textY;
	
	//Constructor: Take the text, the height of the text, the font that will be used, color and the color
	//that will be used as selected color and color that will be used as selected font
	//Initialize all variables
	public Button(String text, int textY, Font font, Font selectedFont, Color color, Color selectedColor) 
	{
		super();
		this.text = text;
		this.textY = textY;
		this.font = font;
		this.selectedFont = selectedFont;
		this.color = color;
		this.selectedColor = selectedColor;
	}

	//Set the font selected to the boolean
	public void setSelected(boolean selected) 
	{
		this.selected = selected;
	}
	
	//Draw the text using the Graphics and drawString method
	//Determine when the font should be drawn as selected or not
	//Use FontMetrics to display the code nice
	public void render(Graphics g)
	{
		//Check whether the font is selected or not
		if(selected)
			Fonts.drawString(g,selectedFont,selectedColor,text,textY);
		else
			Fonts.drawString(g, font, color, text, textY);
		
		FontMetrics fm = g.getFontMetrics();
		this.x = ((TrafficMaster.WIDTH - fm.stringWidth(text)) / 2);
		this.y = textY - fm.getHeight();
		this.width = fm.stringWidth(text);
		this.height = fm.getHeight();
		
	}
	
	//Get the text of the Button
	public String getText()
	{
		return text;
	}
	
	//Change the text of the button
	public void setText(String text)
	{
		this.text = text;
	}
	
}
