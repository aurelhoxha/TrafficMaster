package com.trafficmaster.utilities.managers;

import java.awt.image.BufferedImage;

public class TextureManager extends ResourseManager
{
	//Variables
	
	//Save the image that will be used as texture
	private BufferedImage image;
	
	//Constructor: Manager works with only a image as parameter
	public TextureManager(BufferedImage image)
	{
		this.image = image;
	}
	
	//Return the image that the TextureManager is using
	public BufferedImage getImage()
	{
		return image;
	}
	

}
