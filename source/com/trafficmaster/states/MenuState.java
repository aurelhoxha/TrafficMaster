package com.trafficmaster.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
import com.trafficmaster.rendering.ui.Button;
import com.trafficmaster.utilities.Fonts;

public class MenuState implements State
{
	
	private  Button[] menuCategories;
	private int currentSelection;
	
	private BufferedImage mainImage;
	
	@Override
	public void init() 
	{
		menuCategories = new Button[6];
		
		menuCategories[0] = new Button("Play", 200+0*60,
				new Font("Arial", Font.PLAIN,26 ),new Font("Arial", Font.PLAIN,24),
				Color.WHITE,Color.YELLOW);
		
		menuCategories[1] = new Button("Help", 200+1*60,
				new Font("Arial", Font.PLAIN,26 ),new Font("Arial", Font.PLAIN,24),
				Color.WHITE,Color.YELLOW);
		
		menuCategories[2] = new Button("HighScore", 200+2*60,
				new Font("Arial", Font.PLAIN,26 ),new Font("Arial", Font.PLAIN,24),
				Color.WHITE,Color.YELLOW);
		
		menuCategories[3] = new Button("Options", 200+3*60,
				new Font("Arial", Font.PLAIN,26 ),new Font("Arial", Font.PLAIN,24),
				Color.WHITE,Color.YELLOW);
		
		menuCategories[4] = new Button("Credits", 200+4*60,
				new Font("Arial", Font.PLAIN,26 ),new Font("Arial", Font.PLAIN,24),
				Color.WHITE,Color.YELLOW);
		menuCategories[5] = new Button("Exit", 200+5*80,
				new Font("Arial", Font.PLAIN,26 ),new Font("Arial", Font.PLAIN,24),
				Color.WHITE,Color.YELLOW);
		
		try
        {
			mainImage = ImageIO.read(new File("resources/textures/mainBackground.png"));
        }
        catch(IOException e ){
        	e.printStackTrace();
        }
		
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub
		
	}
	
	public void tick(StateManager mStateManager)
	{
		
		
		if( KeyInput.wasPressed(KeyEvent.VK_UP))
		{
			currentSelection--;
			if(currentSelection < 0 )
				currentSelection = menuCategories.length-1;
		}
		
		if( KeyInput.wasPressed(KeyEvent.VK_DOWN))
		{
			currentSelection++;
			if(currentSelection >= menuCategories.length )
				currentSelection = 0;
		}
		
		boolean mouseClicked = false;
		for(int i =0; i < menuCategories.length; i++)
		{
			if(menuCategories[i].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(),1,1)))
			{
				currentSelection = i;
				mouseClicked = MouseInput.wasPressed(MouseEvent.BUTTON1);
			}
		}
		if(mouseClicked || KeyInput.wasPressed(KeyEvent.VK_ENTER) ||  KeyInput.wasPressed(KeyEvent.VK_SPACE) )
			select(mStateManager);
	}
	
	private void select(StateManager mStateManager)
	{
		switch(currentSelection){
		case 0:
			System.out.println("Level1");
			mStateManager.setState("Level1");
			break;
		case 1:
			System.out.println("Help");
			mStateManager.setState("Help");
			break;
		case 2:
			System.out.println("HighScore");
			mStateManager.setState("HighScore");
			break;
		case 3:
			System.out.println("Options");
			mStateManager.setState("Options");
			break;
		case 4:
			System.out.println("Credits");
			mStateManager.setState("Credits");
			break;
		case 5:
			System.out.println("Exit");
			TrafficMaster.INSTANCE.stop();
			break;
		}
	}
	public void render(Graphics2D g)
	{
		g.drawImage(mainImage,0,0,(ImageObserver) null);
		Fonts.drawString(g,new Font("Cooper Black", Font.BOLD,72),Color.ORANGE, TrafficMaster.TITLE, 90);
		
		for(int i = 0; i< menuCategories.length;i++)
		{
			if(i == currentSelection)
				menuCategories[i].setSelected(true);
			else
				menuCategories[i].setSelected(false);
			
			menuCategories[i].render(g);
		}
	}

	

	@Override
	public void exit() {
		// TODO Auto-generated method stub
	}

	@Override
	public String getName() {
		return "Menu";
	}
	

}
