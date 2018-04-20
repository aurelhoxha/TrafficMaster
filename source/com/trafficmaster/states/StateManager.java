package com.trafficmaster.states;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

public class StateManager 
{
	//Variables
	
	//Create a Map variable to map state to their names
	public Map<String, State> map;
	//Save the current state
	private State currentState;
	
	//Constructor of StateManager
	//A new state can be creating by mapping the name of the State to the state
	public StateManager()
	{
		map = new HashMap<String,State>();
	}
	
	//Add State takes as argument the state
	//Get the state name and map it with the state
	//Add this state the collection of Maps
	public void addState(State state)
	{
		map.put(state.getName().toUpperCase(),state);
		state.init();
		if(currentState == null)
		{
			state.enter();
			currentState = state;
		}
	}
	
	//Set the stateName
	//Take as argument the name that the user wants to put to a state
	public void setState(String name)
	{
		//If the user let it empty an error is displayed
		State state = map.get(name.toUpperCase());
		if( state == null)
		{
			System.out.println("State < " + name + " > does not exist!");
			return;
		}
		//When the state is change the following actions are done
		//1.First the current state is exited
		currentState.exit();
		//2.The state the user want is entered
		state.enter();
		//3.The current state is changed
		currentState = state;
		
	}
	
	//Check the state at every time tick
	//Every time tick is 60 FPS(Frame Per Seconds)
	public void tick()
	{
		currentState.tick(this);
	}
	
	//Render the state using graphics
	public void render(Graphics2D g)
	{
		currentState.render(g);
	}

}
