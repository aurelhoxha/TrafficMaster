package com.trafficmaster.handlers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
	
	//CONSTANTS
	
	//Save the total number of keys
	private static final int NUM_KEYS = 256;
	
	//Create an array to save all the keys they will be pressed
	private static final boolean[] theKeys = new boolean[NUM_KEYS];
	
	//Create an array to save all the keys they were be pressed
	private static final boolean[] lastKeys = new boolean[NUM_KEYS];
	
	//Get the code of the key
	//Make that location true
	@Override
	public void keyPressed(KeyEvent e)
	{
		theKeys[e.getKeyCode()] = true;
	}
	
	//Get the code of the key
	//Make that location false
	@Override
	public void keyReleased(KeyEvent e)
	{
		theKeys[e.getKeyCode()] = false;
	}
	
	//Move all pressed keys to lastKeys
	public static void update()
	{
		for(int i = 0; i < NUM_KEYS;i++)
			lastKeys[i] = theKeys[i];
	}
	
	//Check whether a key is being pressed or not
	//Check the location of that key into the array
	public static boolean isDown(int keyCode)
	{
		return theKeys[keyCode];
	}
	
	//Check whether a key is pressed or not
	//Check the location of that key into the array
	public static boolean wasPressed(int keyCode)
	{
		return isDown(keyCode) && !lastKeys[keyCode];
	}
	
	//Check whether a key is released or not
	//Check the location of that key into the array
	public static boolean wasReleased(int keyCode) 
	{
        return !isDown(keyCode) && lastKeys[keyCode];
    }
}
