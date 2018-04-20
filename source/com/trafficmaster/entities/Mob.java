package com.trafficmaster.entities;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.trafficmaster.rendering.textures.Sprite;
import com.trafficmaster.states.GameState;

public abstract class Mob extends Entity 
{
	
	protected double dx;
	protected double dy;

	public Mob(Sprite mSprite, double x, double y, GameState state) 
	{
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
	
	
	//Get the bound of the Mob
	public Rectangle getBounds()
	{
		return new Rectangle((int)x,(int)y,mSprite.getWidth(),
				mSprite.getHeight());
	}
	
	//Get the bound of the Top
	public Rectangle getTop()
	{
		return new Rectangle((int)x + 6,(int)y,mSprite.getWidth()-6,
				4);
	}
	
	//Get the bound of the Bottom 
	public Rectangle getBottom()
	{
		return new Rectangle((int)x+6,(int)y + mSprite.getHeight()-4,mSprite.getWidth()-6,
				4);
	}
	
	//Get the bound of the Right
	public Rectangle getRight()
	{
		return new Rectangle((int)x + mSprite.getWidth()-4,(int)y+6,4,
				mSprite.getHeight()-6);
	}
	
	//Get the bound of the Left
	public Rectangle getLeft()
	{
		return new Rectangle((int)x,(int)y+6,4,
				mSprite.getHeight()-6);
	}
	
	
	//Draw rectangles around the images
	//Change the color of rectangle
	//Do this in order to test the borders
	public void render(Graphics2D g)
	{
		mSprite.render(g,x,y);
//		g.setColor(Color.RED);
//		g.draw(getTop());
//		g.setColor(Color.BLUE);
//		g.draw(getBottom());
//		g.setColor(Color.MAGENTA);
//		g.draw(getLeft());
//		g.setColor(Color.ORANGE);
//		g.draw(getRight());
	}
	
	
}
