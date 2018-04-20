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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.trafficmaster.TrafficMaster;
import com.trafficmaster.handlers.KeyInput;
import com.trafficmaster.handlers.MouseInput;

public class OptionsState implements State
{
	//Variables
	
	//The name of the file that need to be open
	private static final String FILENAME = "resources/settings/gameOptions.txt";
	
	// Store the game Options
    String gameOptions;
    
	//Save the buttons of the options
    private  Button[] optionsButtons;
    
    //Save the current selection
    private int currentSelection;
    
	//Save the image that will be used as background for Help Menu
	private BufferedImage optionsImage;
	
	//Save the theme settings
	private String themeSettings = "1";
	
	//Save the sound settings 
	private String soundSettings = "1";
	
	//Initialize the OptionsState
	//Open the background image
	//Initialize the button
	public void init()
	{
		//Initialize the gameOptions to empty
		gameOptions = "";
		
		// This will reference one line at a time
        String line = null;
        
		//Try and catch whether the image is in the path given
		//If it is save the image
		//If the image does not exists or the path is incorrect throw an exception
		try
        {
			optionsImage = ImageIO.read(new File("resources/textures/mainBackground.png"));
        }
        catch(IOException e )
		{
        	e.printStackTrace();
        }
		
		//Try to read the file
        try 
        {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(FILENAME);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            
            //Loop through the text
            while((line = bufferedReader.readLine()) != null) 
            {
            	//Add the line of options to the line of game options
                gameOptions = gameOptions + line;   
            }   

            // Always close files.
            bufferedReader.close();
            
        }
        catch(FileNotFoundException ex) 
        {
        	//Show a message if the file is unable to be open
        	//Show the message in a messageDialog
            System.out.println(
                "Unable to open file '" + 
                		FILENAME + "'"); 
            JOptionPane.showMessageDialog(
            		null, "Unable to open file with path: " + FILENAME,
            	    "Error Reading File",
            	    JOptionPane.WARNING_MESSAGE);
        }
        //Catch also the error while reading files
        catch(IOException ex) 
        {
            System.out.println(
                "Error reading file '" 
                + FILENAME + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
		
        //Save the value of the theme settings
        themeSettings = gameOptions.substring(0, 1);
        
        //Save the value of the sound settings
        soundSettings = gameOptions.substring(1);
        
        
        //Initialize the array of the buttons
        optionsButtons = new Button[5];
		
		//Initialize the themeButton Button
		//Save the button configuration to default
        optionsButtons[0]= new Button("Theme 1", 200+0*60,
				new Font("Arial", Font.PLAIN,26 ),new Font("Arial", Font.PLAIN,20),
				Color.WHITE,Color.YELLOW);
		
		//Initialize the soundButton Button
		//Save the button configuration to default
		optionsButtons[1] = new Button("On", 200+1*60,
				new Font("Arial", Font.PLAIN,26 ),new Font("Arial", Font.PLAIN,20),
				Color.WHITE,Color.YELLOW);
		
		//Initialize the applyButton Button
		//Save the button configuration to default
		optionsButtons[2] = new Button("Apply", 200+2*60,
				new Font("Arial", Font.PLAIN,26 ),new Font("Arial", Font.PLAIN,20),
				Color.WHITE,Color.YELLOW);
		
		//Initialize the resetButton Button
		//Save the button configuration to default
		optionsButtons[3] = new Button("Reset", 200+3*60,
				new Font("Arial", Font.PLAIN,26 ),new Font("Arial", Font.PLAIN,20),
				Color.WHITE,Color.YELLOW);
		//Initialize the backToMenu Button
		//Save the button configuration to default
		optionsButtons[4] = new Button("Back", 200+5*80,
				new Font("Arial", Font.PLAIN,26 ),new Font("Arial", Font.PLAIN,20),
				Color.WHITE,Color.YELLOW);
		
		//Check the theme Settings
		if(themeSettings.equals("1"))
		{
			//Set the first theme is the Settings for theme is 1
			optionsButtons[0].setText("Theme 1");
		}
		else
		{
			//Set the first theme is the Settings for theme is 0
			optionsButtons[0].setText("Theme 2");
		}
		
		//Check the sound Settings
		if(soundSettings.equals("1"))
		{
			//Set the sound on is the Settings for sound is 1
			optionsButtons[1].setText("On");
		}
		else
		{
			//Set the sound on is the Settings for sound is 0
			optionsButtons[1].setText("Off");
		}
		
				
	}
	
	//Check the user behavior when he enter the state
	@Override
	public void enter() 
	{
		// TODO Auto-generated method stub
		
	}
	public void tick(StateManager mStateManager)
	{
		//If the up arrow in the keyboard is pressed
		//Move the selection up
		if( KeyInput.wasPressed(KeyEvent.VK_UP))
		{
			currentSelection--;
			if(currentSelection < 0 )
				currentSelection = optionsButtons.length-1;
		}
		
		//If the down arrow in the keyboard is pressed
		//Move the selection down
		if( KeyInput.wasPressed(KeyEvent.VK_DOWN))
		{
			currentSelection++;
			if(currentSelection >= optionsButtons.length )
				currentSelection = 0;
		}
		
		//Make the mouse clicked initially false
		//Update when the mouse is clicked
		boolean mouseClicked = false;
		for(int i =0; i < optionsButtons.length; i++)
		{
			//Check when the mouse pointer is within boundaries of the selection
			if(optionsButtons[i].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(),1,1)))
			{
				currentSelection = i;
				mouseClicked = MouseInput.wasPressed(MouseEvent.BUTTON1);
			}
		}
		//Select the option with Enter or with Space keys
		if(mouseClicked || KeyInput.wasPressed(KeyEvent.VK_ENTER) || KeyInput.wasPressed(KeyEvent.VK_SPACE) )
			select(mStateManager);
	}
	
	//Control the behaviors according to the option selected
	private void select(StateManager mStateManager)
	{
		
		switch(currentSelection)
		{
		//If the themeChange button is pressed change the theme of the game
		case 0:
			System.out.println("ThemeChange");
			
			//If theme 1 is currently selected change it to Theme 2
			//Change also the theme Settings in the file that is used as database
			if(optionsButtons[currentSelection].getText().equals("Theme 1"))
			{
				optionsButtons[currentSelection].setText("Theme 2");
				themeSettings = "0";
			}
			//If theme 2 is currently selected change it to Theme 1
			//Change also the theme Settings in the file that is used as database
			else
			{
				optionsButtons[currentSelection].setText("Theme 1");
				themeSettings = "1";
			}
			break;
		//If the soundChange button is pressed change the theme of the game
		case 1:
			System.out.println("Sound");
			
			//If sound is currently on change it to off
			//Change also the sound Settings in the file that is used as database
			if(optionsButtons[currentSelection].getText().equals("On"))
			{
				optionsButtons[currentSelection].setText("Off");
				soundSettings = "0";
			}
			
			//If sound is currently off change it to on
			//Change also the sound Settings in the file that is used as database
			else
			{
				optionsButtons[currentSelection].setText("On");
				soundSettings = "1";
			}
			break;
		
		//Apply the setting in the database file
		case 2:
			System.out.println("Apply");
			//First take the string that will be saved
			gameOptions = themeSettings+soundSettings;
			System.out.println(gameOptions);
			
			//Try to reach the file that will be written
			try 
			{
				File fout = new File("resources/settings/gameOptions.txt");
				FileOutputStream fos = new FileOutputStream(fout);
				//Write the string into the file
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
					bw.write(gameOptions);
				//Close the file
				bw.close();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
			break;
		//Reset the settings into default
		case 3:
			System.out.println("Reset");
			//Restore the themeSettings
			optionsButtons[0].setText("Theme 1");
			themeSettings = "1";
			//Restore the soundSettings
			optionsButtons[1].setText("On");
			soundSettings = "1";
			//Update the gameSettings
			gameOptions = themeSettings+soundSettings;
			System.out.println(gameOptions);
			//Save the default setting into the file
			try 
			{
				File fout = new File("resources/settings/gameOptions.txt");
				FileOutputStream fos = new FileOutputStream(fout);
				
				//Write the default setting into the file
				//The default settings are 11
				//The first 1 correspond to Theme 1 and the second 1 correspond to sound on
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
					bw.write(gameOptions);
				//Clos the file that was written
				bw.close();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
			break;
		//Move to menu State
		case 4:
			System.out.println("Back");
			mStateManager.setState("Menu");
			break;
		}
	}
	
	//Draws all the options component
	public void render(Graphics2D g)
	{
		//Draw the options Menu background image
		g.drawImage(optionsImage,0,0,(ImageObserver) null);
		//Draw the main text that represent the menu
		Fonts.drawString(g,new Font("Arial", Font.BOLD,55),Color.ORANGE, "OPTIONS", 55);
		
		//Draw all the buttons that are used in the Options Menu
		//The buttons are, theme, on/off, apply, reset and back
		for(int i = 0; i< optionsButtons.length;i++)
		{
			//Check whether the button is selected or not
			//Change the characteristics of the buttons according to the selection
			if(i == currentSelection)
				optionsButtons[i].setSelected(true);
			else
				optionsButtons[i].setSelected(false);
			
			optionsButtons[i].render(g);
		}
	}
		
//		for(int i = 0; i< menuCategories.length;i++)
//		{
//			if(i == currentSelection)
//				menuCategories[i].setSelected(true);
//			else
//				menuCategories[i].setSelected(false);
//			
//			menuCategories[i].render(g);
//		}
	@Override
	public void exit() 
	{
		// TODO Auto-generated method stub
	
	}

	@Override
	public String getName() 
	{
		return "Options";
	}
	
}
