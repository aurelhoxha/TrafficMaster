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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.trafficmaster.handlers.KeyInput;
import com.trafficmaster.handlers.MouseInput;
import com.trafficmaster.rendering.ui.Button;
import com.trafficmaster.utilities.Fonts;

public class HighscoreState implements State
{
	
	//Variables
	
	//The name of the file that need to be open
	private static final String FILENAME = "resources/settings/highscore.txt";
	
	
	//Save the word "Name" for help
	private static final String NAMEWORD = "NAME";
	
	//Save the word "Score" for help
	private static final String SCORE = "SCORE";
	
	//Store the first string
	String firstShow;
	
	// Store the game Options
	String gameHigh;
	
	//Store a format to show the strings
	String format;
	
	//Store the names
	ArrayList<String> scoresName;
	
	//Store the scores
	ArrayList<String> scores;
	
	//Save the number of lines
	int lines;
	
	//Save the image that will be used as background for Help Menu
	private BufferedImage highscoreImage;
	
	//Save the button that direct user back to the main menu
	private  Button backToMenu;
	

	@Override
	public void init() 
	{
		
        
      //Try and catch whether the image is in the path given
      		//If it is save the image
      		//If the image does not exists or the path is incorrect throw an exception
      		try
            {
      			highscoreImage = ImageIO.read(new File("resources/textures/mainBackground.png"));
            }
            catch(IOException e )
      		{
              	e.printStackTrace();
            }
      	
    		
		
	}

	@Override
	public void enter() 
	{
		// TODO Auto-generated method stub
				//Initialize the gameOptions to empty
				gameHigh = "";
				
				//Initialize the number of lines
				lines = 0;
				
				//Initialize the scoresName arrayList
				scoresName = new ArrayList<>();
				
				//Initialize the scores arrayList
				scores = new ArrayList<>();
				
				//Initialize the format for the strings
				format = "%-35s%5s\n";
				
				//Initialize the first string
				firstShow = String.format(format, NAMEWORD, SCORE);
				
				// This will reference one line at a time
		        String line = null;
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
	                	//Update the number of lines
	                	lines = lines + 1;
	                	//Add the line of options to the line of game options
	                	gameHigh = gameHigh +"\n"+ line;
	                	
	                	//Add the name of the scores to the scoresName
	                	scoresName.add(line.substring(0, line.indexOf(' ')));
	                	
	                	//Add the score corespoding to that player to the scores
	                	if(lines <= 1)
	                		scores.add(line.substring(line.indexOf(' ')));
	                	else
	                		scores.add(line.substring(line.indexOf(' ')+1));
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
	            
	          //Initialize the backToMenu Button
	    	  //Save the button configuration to default
	    	  backToMenu = new Button("Back", 200+5*80,
	    				new Font("Arial", Font.PLAIN,32 ),new Font("Arial", Font.PLAIN,24),
	    				Color.WHITE,Color.YELLOW);
	    	  
	    	  //Check the number of lines
	    	  //Check the score
	    	  //System.out.println(lines);
	            //System.out.println(gameHigh);
	            //System.out.println(scores.size());
	            //for(int i = 0 ; i < scores.size();i++)
	            //{
	            	//System.out.print(scores.get(i));
	            	//System.out.println();
	            //}
		
	}

	@Override
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

	@Override
	public void render(Graphics2D g) 
	{
		
		//Draw the background image
		g.drawImage(highscoreImage,0,0,(ImageObserver) null);
		//Draw the main text that represent the menu
		Fonts.drawString(g,new Font("Arial", Font.BOLD,55),Color.ORANGE, "HIGHSCORE", 55);
		if(firstShow != null)
		Fonts.drawString(g, new Font("Arial", Font.BOLD,24), Color.WHITE, firstShow , 480, 200);
		
		for(int i = 0; i < scoresName.size(); i++)
		{
			String tempShow = String.format(format, scoresName.get(i), scores.get(i));
			Fonts.drawString(g, new Font("Arial", Font.BOLD,24), Color.WHITE, tempShow , 480, 200 +((i+1)*60));
		}
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
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "HighScore";
	}

}
