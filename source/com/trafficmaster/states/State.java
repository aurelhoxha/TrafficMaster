package com.trafficmaster.states;

import java.awt.Graphics;
import java.awt.Graphics2D;

public interface State 
{
	//Init is used in the beginning of the class to initialize the variables
	public void init();
	//Enter the current state
	public void enter();
	//Tick methods runs automatically by 60FPS and calls the required methods
	public void tick(StateManager mStateManager);
	//Render method will be used in order to draw in the panel using Graphics
	public void render(Graphics2D g);
	//Exits the current State
	public void exit();
	//Return the name of the current State
	public String getName();
	

}
