package com.trafficmaster.entities;

import com.trafficmaster.TrafficMaster;
import com.trafficmaster.rendering.textures.Sprite;
import com.trafficmaster.states.GameState;

public class Turtle extends WaterMob 
{
	//--------------------------------------------------------------
	//Constructor of Turtle: Takes the picture and initial location
	//					  and specify the state the Turtle should be
	//					  shown
	//--------------------------------------------------------------
	public Turtle(Sprite mSprite, double x, double y, GameState state) {
		super(mSprite, x, y, state);
		// TODO Auto-generated constructor stub
	}
	
	// Update the location of Turtle at every time tick
	// Calls super tick() method to move the object
	public void tick()
	{
		if(x==0)
		{
			x = TrafficMaster.WIDTH;
		}
		dx =  -2;
		super.tick();
	}

}
