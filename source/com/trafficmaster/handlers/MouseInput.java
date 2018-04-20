package com.trafficmaster.handlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter 
{	
	//Save the total number that are required for mouse operations
	private static final int NUM_BUTTONS = 10;
	
	//Create an array to save all current mouse operations
	private static final boolean[] buttons = new boolean[NUM_BUTTONS];
	//Create an array to save all past mouse operations
	private static final boolean[] lastButtons = new boolean[NUM_BUTTONS];
	
	//Initialize the initialize mouseX and mouseY 
	private static int x = -1;
	private static int y = -1;
	
	//Initialize the last value to initial mouseX and mouseY 
	private static int lastX = x;
	private static int lastY = y;
	
	//Create a boolean variable to check whether the mouse is moving or not
	public static boolean moving;
	
	
	//When mouse is pressed get the code of the operation
	//Make the location of that operation into array true
	@Override
	public void mousePressed(MouseEvent e)
	{
		buttons[e.getButton()] = true;
	}
	
	//When mouse is released get the code of the operation
	//Make the location of that operation into array true
	@Override
	public void mouseReleased(MouseEvent e)
	{
		buttons[e.getButton()] = false;
	}
	
	
	//When mouse is moved get x and y of the mouse
	//Update the system x and y
	@Override
	public void mouseMoved(MouseEvent e)
	{
		x = e.getX();
		y = e.getY();
		
	}
	
	//First copy the last operation done with mouse
	//Determine whether mouse is moving or not
	//Change lastX and lastY values
	public static void update()
	{
		for(int i = 0; i < NUM_BUTTONS;i++)
		{
			lastButtons[i] = buttons[i];
		}
		if(x == lastX && y == lastY)
		{
			moving = true;
		}
		
		lastX = x;
		lastY = y;
	}
	
	//Check whether mouse is being pressed
	public static boolean isDown(int button)
	{
		return buttons[button];
	}
	
	//Determine when the mouse is pressed
	public static boolean wasPressed(int button)
	{
		return isDown(button) && !lastButtons[button];
	}
	
	//Determine when the mouse is released
	public static boolean wasReleased(int button)
	{
		return !isDown(button) && lastButtons[button];
	}
	
	//Return x-coordinate of the mouse
	public static int getX()
	{
		return x;
	}
	
	//Return y-coordinate of the mouse
	public static int getY()
	{
		return y;
	}
	
	//Return true if mouse is moving and false otherwise
	public static boolean isMoving()
	{
		return moving;
	}
}
