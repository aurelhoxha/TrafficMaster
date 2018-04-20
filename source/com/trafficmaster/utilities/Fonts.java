package com.trafficmaster.utilities;

import java.awt.Color;
import java.awt.Graphics;

import com.trafficmaster.TrafficMaster;

import java.awt.Font;
import java.awt.FontMetrics;

public class Fonts 
{
	//Create a method that drawString
	//Take as argument graphics g to draw the string, font, color, text and x and y for position 
	public static void drawString(Graphics g, Font f, Color c, String text, int x, int y)
	{
		//Set the color for g to draw
		g.setColor(c);
		//Set the font for the text
		g.setFont(f);
		//Draw the text in the required position (x,y)
		g.drawString(text, x, y);
		
	}
	
	//Draw string method with out position x and y
	public static void drawString(Graphics g, Font f, Color c, String text)
	{
		//Create variable of FontMetrics
		FontMetrics fm = g.getFontMetrics(f);
		//Put the text on the Horizontal Center
		int x = (TrafficMaster.WIDTH - fm.stringWidth(text)) / 2;
		//Put the text on the Vertical Center
		int y = ((TrafficMaster.WIDTH - fm.getHeight()) / 2) + fm.getAscent(); 
	}
	//Draw string method within a square box
	//For this only one position is required
	public static void drawString(Graphics g, Font f, Color c, String text,double x)
	{
		FontMetrics fm = g.getFontMetrics(f);
		//Put the text on the Vertical Center
		int y = ((TrafficMaster.WIDTH - fm.getHeight()) / 2) + fm.getAscent();
		drawString(g,f,c,text, (int)x, y);
	
	}
	
	//Draw string method within a square box
	//For this only one position is required
	public static void drawString(Graphics g, Font f, Color c, String text,int y)
	{
		FontMetrics fm = g.getFontMetrics(f);
		//Put the text on the Horizontal Center
		int x = ((TrafficMaster.WIDTH - fm.stringWidth(text)) / 2);
		drawString(g,f,c,text, x, y);
	
	}
	

}
