package com.trafficmaster.rendering.textures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.trafficmaster.utilities.managers.TextureManager;

public class Texture 
{
	//Create Map to save the Texture
	private final static Map<String,TextureManager> texMap = new HashMap<String,TextureManager>();
	private TextureManager manager;
	
	//String variable to save the name of texture to process it easier later
	private String fileName;
	
	//Constructor of texture
	//Just take the name of the texture
	public Texture(String fileName)
	{
		TextureManager previousTexture = texMap.get(fileName);
		
		//Check whether the path given for image exists or not
		//If it exists add reference to this path
		//If it does not exists show error to the user
		if(previousTexture != null )
		{
			manager = previousTexture;
			manager.addReference();
		}
		else
		{
			try {
				System.out.println("Loading Texture:" + fileName);
				manager = new TextureManager(ImageIO.read(new File("/Users/Aurel/Desktop/Project319/TrafficMaster/resources/textures/" + fileName + ".png"))); 
				texMap.put(fileName, manager);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	//When the work with image is done delete it
	//Remove the image reference too
	@Override
	protected void finalize() throws Throwable
	{
		if(manager.removeReference() && !fileName.isEmpty())
		{
			texMap.remove(fileName);
			
			System.out.println("Removing texture from memory:" + fileName ); 
		}
		super.finalize();
	}
	
	//Takes as parameter the graphics and location x and y
	//Use graphics to draw the image at location (x,y)
	public void render(Graphics g, double x, double y)
	{
		g.drawImage(manager.getImage(),(int)x, (int)y,null);
	}
	
	//Return the images required
	public BufferedImage getImage()
	{
		return manager.getImage();
	}
	
}
