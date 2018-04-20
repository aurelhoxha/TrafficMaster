package com.trafficmaster.states;
import com.trafficmaster.rendering.ui.Button;
import com.trafficmaster.utilities.Fonts;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.trafficmaster.TrafficMaster;
import com.trafficmaster.handlers.KeyInput;
import com.trafficmaster.handlers.MouseInput;

public class CreditsState implements State
{
	//Variables
	
	//Save the button that direct user back to the main menu
	private  Button backToMenu;
	
	//Save the image that will be used as background for Credits Menu
	private BufferedImage creditsImage;
	
	//Initialize the CreditsState
	//Open the background image
	//Initialize the button
	public void init()
	{
		//Try and catch whether the image is in the path given
		//If it is save the image
		//If the image does not exists or the path is incorrect throw an exception
		try
        {
			creditsImage = ImageIO.read(new File("resources/textures/creditsBackground.png"));
        }
        catch(IOException e ){
        	e.printStackTrace();
        }
		
		//Initialize the backToMenu Button
		//Save the button configuration to default
		backToMenu = new Button("Back", 200+5*80,
				new Font("Arial", Font.PLAIN,32 ),new Font("Arial", Font.PLAIN,24),
				Color.WHITE,Color.YELLOW);
	}
	
	//Check the user behavior when he enter the state
	@Override
	public void enter() 
	{
		// TODO Auto-generated method stub
		
	}
	public void tick(StateManager mStateManager)
	{
		boolean mouseClicked = false;
		if(backToMenu.intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(),1,1)))
		{
			mouseClicked = MouseInput.wasPressed(MouseEvent.BUTTON1);
		}
		if(mouseClicked || KeyInput.wasPressed(KeyEvent.VK_ENTER) )
		{
			select(mStateManager);
		}
	
	}
	private void select(StateManager mStateManager)
	{
		
			mStateManager.setState("Menu");
	}
	public void render(Graphics2D g)
	{
		g.drawImage(creditsImage,0,0,(ImageObserver) null);
		backToMenu.setSelected(true);
		backToMenu.render(g);
//		for(int i = 0; i< menuCategories.length;i++)
//		{
//			if(i == currentSelection)
//				menuCategories[i].setSelected(true);
//			else
//				menuCategories[i].setSelected(false);
//			
//			menuCategories[i].render(g);
//		}
	}
	@Override
	public void exit() 
	{
		// TODO Auto-generated method stub
	
	}

	@Override
	public String getName() 
	{
		return "Credits";
	}
	
}
