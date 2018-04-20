package com.trafficmaster.entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.trafficmaster.handlers.KeyInput;
import com.trafficmaster.rendering.textures.Sprite;
import com.trafficmaster.states.GameState;

public class Player extends Movable
{
	public Player(Sprite mSprite, double x, double y, GameState state) 
	{
		super(mSprite, x,y, state);
	}
	
	public void tick()
	{
		if(KeyInput.isDown(KeyEvent.VK_UP)) 
		{
			if(y > 0)
				dy =  -2;
		}
		if(KeyInput.isDown(KeyEvent.VK_DOWN))
		{
			if(y < 600)
				dy = +2;
		}
		if(KeyInput.isDown(KeyEvent.VK_LEFT))
		{
			dx = -4;
		}
		if(KeyInput.isDown(KeyEvent.VK_RIGHT))
		{
			dx = +2;
		}
		
		if( KeyInput.wasReleased(KeyEvent.VK_UP) || KeyInput.wasReleased(KeyEvent.VK_DOWN)) 
		{
			dy = 0;
		}
		if( KeyInput.wasReleased(KeyEvent.VK_LEFT) || KeyInput.wasReleased(KeyEvent.VK_RIGHT))
		{
			dx = 0;
		}
		super.tick();
	}
	
	


}
