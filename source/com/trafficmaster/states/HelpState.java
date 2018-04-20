package com.trafficmaster.states;
import com.trafficmaster.rendering.ui.Button;
import com.trafficmaster.utilities.Fonts;

import java.awt.Color;
import java.awt.Font;
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

public class HelpState implements State
{
	//Variables
	
	//Save the button that direct user back to the main menu
	private  Button backToMenu;
	
	//Save the image that will be used as background for Help Menu
	private BufferedImage helpImage;
	
	//Initialize the HelpState
	//Open the background image
	//Initialize the button
	public void init()
	{
		//Try and catch whether the image is in the path given
		//If it is save the image
		//If the image does not exists or the path is incorrect throw an exception
		try
        {
			helpImage = ImageIO.read(new File("resources/textures/helpBackground.png"));
        }
        catch(IOException e ){
        	e.printStackTrace();
        }
		
		//Initialize the backToMenu Button
		//Save the button configuration to default
		backToMenu = new Button("Back", 200+5*80,
				new Font("Arial", Font.PLAIN,26 ),new Font("Arial", Font.BOLD,24),
				Color.WHITE,Color.YELLOW);
	}
	
	//Check the user behavior when he enter the state
	@Override
	public void enter() 
	{
		// TODO Auto-generated method stub
		
	}
	//At every time tick check for mouse clicked
	public void tick(StateManager mStateManager)
	{
		//First initialize mouseClicked as false
		boolean mouseClicked = false;
		
		//Then check if back Button is clicked
		//Checks the pointer of the mouse
		//Button is clicked is pointer is on the text and mouse is pressed
		if(backToMenu.intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(),1,1)))
		{
			mouseClicked = MouseInput.wasPressed(MouseEvent.BUTTON1);
		}
		
		//Process the operation when backButton is pressed
		//Move the state to menu state
		if(mouseClicked || KeyInput.wasPressed(KeyEvent.VK_ENTER) )
		{
			//Call select Method
			select(mStateManager);
		}
	
	}
	//Select method basically changes the state of the game
	private void select(StateManager mStateManager)
	{
			mStateManager.setState("Menu");
	}
	
	//Draws everything that is displayed in the help menu
	//Such includes the helpBackgroun image
	//Button is also render and initially selelected
	public void render(Graphics2D g)
	{
		g.drawImage(helpImage,0,0,(ImageObserver) null);
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
		return "Help";
	}
	
}
