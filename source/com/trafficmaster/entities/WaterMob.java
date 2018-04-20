package com.trafficmaster.entities;

import com.trafficmaster.rendering.textures.Sprite;
import com.trafficmaster.states.GameState;

public abstract class WaterMob extends Entity 
{
	
	protected double dx;
	protected double dy;

	public WaterMob(Sprite mSprite, double x, double y, GameState state) {
		super(mSprite, x, y,state);
	}
	
	@Override
	public void tick() 
	{
		move();
	}
	
	public void move() 
	{
		x = x + dx;
		y = y + dy;
	}
}
