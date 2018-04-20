package com.trafficmaster.rendering.textures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sprite 
{
	//Save the spritesheed that will be used to take a fragment of image
	private SpriteSheet mSpriteSheet;
	//Save the image that will be processed
	private BufferedImage mImage;
	
	//Save the name of the sprite
	private String imageName;
	
	//Constructor of Sprite: Take the Spritesheet, x and y
	//Process the image with taken a part of the big image
	public Sprite(SpriteSheet mSpriteSheet, int x, int y)
	{
		//Calculate the coordinates that are needed for the image
		//Save that image
		this.mImage = mSpriteSheet.getTexture().getImage().getSubimage((x*mSpriteSheet.getWidth()) - mSpriteSheet.getWidth(),
				(y*mSpriteSheet.getHeight()) - mSpriteSheet.getHeight(),mSpriteSheet.getWidth(), mSpriteSheet.getHeight());
	}
	
	//Create a Sprite with a given name
	//This sprite create a texture with that name
	//Then the image is saved
	public Sprite(String textName)
	{
		//Initialize the sprite name
		imageName = textName;
		Texture tex = new Texture(textName);
		mImage = tex.getImage();
	}
	
	//Takes as parameter graphics, x and y
	//Draw the image using graphics at location x,y 
	public void render(Graphics g, double x, double y)
	{
		g.drawImage(mImage, (int)x, (int)y, null);
	}
	
	//Return the name of the sprite
	public String getSpriteName()
	{
		return imageName;
	}
	
	//Get Width of the image
	public int getWidth()
	{
		return mImage.getWidth();
	}
	
	//Get Height of the image
	public int getHeight()
	{
		return mImage.getHeight();
	}
	

}
