package com.trafficmaster.rendering.textures;

public class SpriteSheet 
{
	//Variables
	
	//Use texture for the image
	private Texture mTexture;
	//Save the width of the image
	private int width;
	//Save the height of the image 
	private int height;
	
	//Constructor: Take the texture and the size of the image
	public SpriteSheet(Texture mTexture,int size)
	{
		//Create a texture with the give width and height
		this(mTexture,size,size);
		
	}
	
	//Another Constructor: In this constructor the image is rectangle
	public SpriteSheet(Texture mTexture,int width, int height)
	{
		//Initialize the image from what is entered by user
		this.mTexture = mTexture;
		//Initialize the width from what is entered by user
		this.width = width;
		//Initialize the height from what is entered by user
		this.height = height;		
	}
	
	//Return the image that is being used
	public Texture getTexture()
	{
		return mTexture;
	}
	
	//Return the width of the image
	public int getWidth()
	{
		return width;
	}
	
	//Return the height of the image
	public int getHeight()
	{
		return height;
	}
}
