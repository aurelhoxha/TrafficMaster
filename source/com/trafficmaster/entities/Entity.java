package com.trafficmaster.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.trafficmaster.rendering.textures.Sprite;
import com.trafficmaster.states.GameState;

public abstract class Entity 
{
	
	protected double x;
	protected double y;
	protected Sprite mSprite;
	protected GameState state;
	
	//Constructor
	public Entity(Sprite mSprite, double x, double y, GameState state) {
		super();
		this.mSprite = mSprite;
		this.x = x;
		this.y = y;
		this.state = state;
		
	}
	
	public abstract void tick();
	
	public void render(Graphics2D g)
	{
		mSprite.render(g,x,y);
	}
	

}
