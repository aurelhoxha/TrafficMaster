package com.trafficmaster.entities;

import com.trafficmaster.TrafficMaster;
import com.trafficmaster.rendering.textures.Sprite;
import com.trafficmaster.states.GameState;

public class Truck extends Mob
{

	public Truck(Sprite mSprite, double x, double y, GameState state) {
		super(mSprite, x, y, state);
		// TODO Auto-generated constructor stub
	}
	
	public void tick()
	{
		//Check the image that is being used
		//If the image is one of the trucks moving left move the object left
		//Else move the object right
		if(mSprite.getSpriteName().equals("truckLeft") || mSprite.getSpriteName().equals("truck1Left"))
		{
			//If the object goes to zero show it again at the end
			if(x+mSprite.getWidth()==0)
			{
				x = TrafficMaster.WIDTH;
			}
			//The speed of the truck
			dx =  -2;
			//Call render every time tick
			super.tick();
		}
		else
		{
			//If the object goes to WIDTH of frame show it again at the beginning
			if(x==TrafficMaster.WIDTH)
			{
				x = 0 - mSprite.getWidth();
			}
			//The speed of the truck
			dx =  +2;
			//Call render every time tick
			super.tick();
		}
	}

}
