package com.trafficmaster.entities;



import com.trafficmaster.TrafficMaster;
import com.trafficmaster.rendering.textures.Sprite;
import com.trafficmaster.states.GameState;

public class Car extends Mob
{

	//--------------------------------------------------------------
	//Constructor of Car: Takes the picture and initial location
	//					  and specify the state the Car should be
	//					  shown
	//--------------------------------------------------------------
	public Car(Sprite mSprite, double x, double y, GameState state) 
	{
		
		super(mSprite, x, y, state);
	}
	
	// Update the location of car at every time tick
	// Calls super tick() method to move the object
	public void tick()
	{
		//Check the image that is being used
		//If the image is one of the car moving left move the object left
		//Else move the object right
		if(mSprite.getSpriteName().equals("carLeft"))
		{
			//If the object goes to zero show it again at the end
			if(x+mSprite.getWidth()==0)
			{
				x = TrafficMaster.WIDTH;
			}
			//The speed of the car
			dx =  -2;
			//Call render every time tick
			super.tick();
		}
		else
		{
			//If the object goes to WIDTH of frame show it again at the beginning
			if(x==TrafficMaster.WIDTH)
			{
				x = 0-mSprite.getWidth();
			}
			//The speed of the car
			dx =  +2;
			//Call render every time tick
			super.tick();
		}
	}

}
