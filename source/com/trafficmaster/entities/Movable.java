package com.trafficmaster.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

import com.trafficmaster.TrafficMaster;
import com.trafficmaster.rendering.textures.Sprite;
import com.trafficmaster.states.GameState;

public abstract class Movable extends Entity 
{
	
	
	protected double dx;
	protected double dy;
	private int numberOfLives;
	private int score;
	private String name;
	public Movable(Sprite mSprite, double x, double y, GameState state) 
	{
		super(mSprite, x, y,state);
		state.addEntity(this);
		numberOfLives = 5;
		score = 1500;
	}
	
	@Override
	public void tick() 
	{
		move();
		//System.out.println(getScore());
		//System.out.println(getNumberOfLives());
		
		if(gameIsOver())
		{
		JOptionPane.showMessageDialog(TrafficMaster.INSTANCE,
				   "You lost the game");
		// Prompt the user to enter their name
	    name = JOptionPane.showInputDialog(TrafficMaster.INSTANCE, "What's your name?");

	    // Get the user's input. note that if they press Cancel, 'name' will be null
	    System.out.printf("The user's name is '%s' and score '%d'.\n", name, score);
		};
		
		
		score = score-1;
		
	}
	
	public void move() 
	{
		if(!hasHorizontalCollision())
		{
			x = x + dx;
		}
		if(!hasVerticalCollision())
		{
			y = y + dy;
		}
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
		
		
		//
		public void render(Graphics2D g)
		{
			mSprite.render(g,x,y);
//			g.setColor(Color.YELLOW);
//			g.draw(getTop());
//			g.draw(getBottom());
//			g.draw(getLeft());
//			g.draw(getRight());
		}
		protected boolean hasVerticalCollision()
		{
			for(int i = 0; i < state.getMob().size();i++)
			{
				Mob t = state.getMob().get(i);
				if(getBounds().intersects(t.getTop()) && dy > 0 )
				{
					dy = 0;
					//System.out.println("Has collision");
					decreaseNumberOfLives();
					//Decreases the score
					decreaseScore();
					//System.out.println(getNumberOfLives());
					//setScore(0);
					return true;
				}
				if(getBounds().intersects(t.getBottom()) && dy < 0)
				{
					dy = 0;
					//System.out.println("Has collision");
					decreaseNumberOfLives();
					decreaseScore();
					//setScore(0);
					return true;
				}
			}
			return false;
			
		}
		
		protected boolean hasHorizontalCollision()
		{
			for(int i = 0; i < state.getMob().size();i++)
			{
				Mob t = state.getMob().get(i);
				if(getBounds().intersects(t.getRight()))
				{
					//System.out.println("Has collision");
					decreaseNumberOfLives();
					decreaseScore();
					//System.out.println(getNumberOfLives());
					//setScore(0);
					return true;
				}
				if(getBounds().intersects(t.getLeft()))
				{
					//System.out.println("Has collision");
					decreaseNumberOfLives();
					decreaseScore();
					//System.out.println(getNumberOfLives());
					//setScore(0);
					return true;
				}
			}
			return false;
		}
		
		//Decreased the number of lives
		public void decreaseNumberOfLives()
		{
			numberOfLives--;
			
		}
		
		//Return the number of lives
		public int getNumberOfLives()
		{
			return numberOfLives;
			
		}
		
		//Set the number of lives
		public void restartLives()
		{
			numberOfLives = 5;
		}
		
		
		//Check whether or not the game is over
		public boolean gameIsOver()
		{
			return getNumberOfLives() == 0;
		}
		
		//Update the score when the user is collide
		public void decreaseScore()
		{
			setScore(getScore()-20);
		}
		
		//Get the score of the player
		public int getScore()
		{
			return score;
		}
		
		//Set the score of the player
		public void setScore(int value)
		{
			score = value;
		}
		
		//Set the name of the player
		public void setName(String name)
		{
			this.name = name;
		}
		
		public String getName()
		{
			return name;
		}
}
