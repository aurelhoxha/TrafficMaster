package com.trafficmaster.rendering.textures;

import java.awt.Graphics2D;

//This class is responsible for Animations
//It will use spriteSheets in order to provide good animations for the objects of the game
public class Animation 
{
	//Saves the total count of the images
	private int count;
	
	//Saves the current index of the images
	private int index;
	
	//Saves the speed that the images is changes
	private int speed;
	
	//Saves the total numbers of frames
	private int numOfFrames;
	
	//Saves the current Frame
	private Texture currentFrame;
	
	//Saves the array of the frames
	private Texture[] frames;
	
	
	//Construcor: Take the speed of the animation
	//And the texture responsible for the frame
	public Animation(int speed, Texture... frames)
	{
		this.speed = speed;
		this.frames = frames;
		this.numOfFrames = frames.length;
	}
	
	//Go to next frame
	//If the index is more than number of frames select the first frame
	private void nextFrame()
	{
		currentFrame = frames[index++];
		if(index > numOfFrames)
		{
			index = 0;
		}
		
	}
	
	//Behavior while the animation is running
	public void run()
	{
		//Increase the count
		count++;
		
		//If the count required for one pass is same as speed
		//Pass to the next frame
		if(count > speed )
		{
			count = 0;
			nextFrame();	
		}
	}
	
	public void render(Graphics2D g, double x, double y)
	{
		if(currentFrame != null )
		{
			currentFrame.render(g, x, y);
		}
	}


	
	
	

}
