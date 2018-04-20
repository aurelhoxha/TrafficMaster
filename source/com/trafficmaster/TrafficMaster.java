package com.trafficmaster;

//Imports 
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.trafficmaster.handlers.KeyInput;
import com.trafficmaster.handlers.MouseInput;
import com.trafficmaster.states.CreditsState;
import com.trafficmaster.states.GameState;
import com.trafficmaster.states.HelpState;
import com.trafficmaster.states.HighscoreState;
import com.trafficmaster.states.MenuState;
import com.trafficmaster.states.OptionsState;
import com.trafficmaster.states.StateManager;

public class TrafficMaster extends Canvas implements Runnable 
{
	//CONSTANTS
	
	
	//Variables
	
	//The name of the file that need to be open
	private static final String FILENAME = "resources/settings/gameOptions.txt";
	
	// Store the game Options
    String gameOptions;
	
	//Save the name of the game as static
	public static final String TITLE = "Traffic-Master";
	
	//Save the WIDTH of the Game Frame
	public static int WIDTH  = 960;
	
	//Save the WIDTH of the Game Frame
	public static int HEIGHT = 700;
	
	//Varibles
	private boolean executing;
	
	//Create a varible of MenuState since the game starts there
	private MenuState menu;
	
	//Create a StateManager to control States
	private StateManager mStateManager;
	
	//Create a variable to save the backgroundImage of the menu
	private BufferedImage backgroundImage;
	
	//Save the instance of the game
	public static TrafficMaster INSTANCE;
	
	//Save the theme settings
	private String themeSettings = "1";
	
	//CONSTRUCTOR: Does not take any parameter
	//Initialize all variable
	public TrafficMaster()
	{
		
		//Initialize the gameOptions to empty
		gameOptions = "";
		
		// This will reference one line at a time
        String line = null;
        
		//Add keyListener to the game
		addKeyListener(new KeyInput());
		
		//Create a mouseInput variable
		MouseInput mMouseInput = new MouseInput();
		
		//Add mouseInput to mouseListener
		addMouseListener(mMouseInput);
		
		//Add the mouseListener to the game
		addMouseMotionListener(mMouseInput);
		
		//Initialize the stateManager
		mStateManager = new StateManager();
		
		
		
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
        
        if(themeSettings.equals("1"))
        {
      //Try to load the backgroundImage of the menu
      		try
              {
              	backgroundImage = ImageIO.read(new File("resources/textures/gameBackground.png"));
              }
      		//Catch any possible errors
              catch(IOException e )
      		{
              	e.printStackTrace();
              }
        }
        else
        {
        	try
            {
            	backgroundImage = ImageIO.read(new File("resources/textures/gameBackground0.png"));
            }
    		//Catch any possible errors
            catch(IOException e )
    		{
            	e.printStackTrace();
            }
        }
		
		//Add the states to the state Managers
		//States are MenuState, GameState, HighscoreState, HelpState,CreditsState and Options State
		mStateManager.addState(new MenuState());
		mStateManager.addState(new GameState());
		mStateManager.addState(new HighscoreState());
		mStateManager.addState(new HelpState());
		mStateManager.addState(new CreditsState());
		mStateManager.addState(new OptionsState());
		
		//Initialize the instance to the current game opened
		INSTANCE = this;
	}
	
	//Call the tick method of the stateManager for every time tick of the game
	//Calls the tick for every state 
	//At every tick every state check different behaviors of the state
	private void tick()
	{
		mStateManager.tick();
	}
	
	//Render first load the background image and then render each state
	//This basically make the program wait a little in the beginning
	//Eliminate extra wait during the usage of the game
	private void render()
	{
		BufferStrategy myBufferStrategy = getBufferStrategy();
		if(myBufferStrategy == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		//Use graphics to draw the images
		Graphics2D g = (Graphics2D) myBufferStrategy.getDrawGraphics();
		///////////////////DRAW COMPONENTS///////////////////
		g.drawImage(backgroundImage,0,0,this);
		mStateManager.render(g);
		////////////////////////////////////////////////////
		g.dispose();
		myBufferStrategy.show();
	}
	
	private void start()
	{
		//Check if the game is running or not.
		if(executing) 
			return;
		executing = true;
		new Thread(this,"TrafficMaster - Thread!").start();
	}
	
	public void stop()
	{
		//Check if the game stopped.
		if(!executing) 
			return;
		executing = false;
		
	}
	
	//While the game runs it basically fix the Frame per second error
	@Override
	public void run() 
	{
		requestFocus();
		double target = 60.0;
		//Set the total Frequency of Frame
		double frameFrequency = 1000000000.0/target;
		double lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		//Set the remained time
		double remainedTime = 0.0;
		//Set Frame per Seconds(FPS)
		int fps = 0;
		//Set Tick per Second(TPS)
		int tps = 0;
		
		boolean isRenderable = false;

		while(executing)
		{
			long moment = System.nanoTime();
			remainedTime += (moment - lastTime) / frameFrequency;
			lastTime = moment;
			
			
			if(remainedTime >= 1.0)
			{
				tick();
				KeyInput.update();
				MouseInput.update();
				remainedTime--;
				tps++;
				isRenderable = true;
			}
			else
			{
				isRenderable = false;
			}
			
			try 
			{
				Thread.sleep(1);
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(isRenderable){
				render();
				fps++;
			}
			
			if(System.currentTimeMillis() - 1000 > timer)
			{
				timer = timer + 1000;
				//System.out.printf("FPS: %d | TPS: %d\n", fps,tps);
				fps = 0;
				tps = 0;
			}
		}
		System.exit(0);
	}
	
	//Create the Game
	public static void main(String[] args)
	{
		TrafficMaster game = new TrafficMaster();
		JFrame gameFrame = new JFrame(TITLE);
		
		//Create the frame of the game
		gameFrame.add(game);
		gameFrame.setSize(WIDTH, HEIGHT);
		//Set the frame resizable false in order to avoid repaint
		gameFrame.setResizable(false);
		//Set the focusable true in order to be able to use keyboard and mouse
		gameFrame.setFocusable(true);
		//Set the panel to close the game
		gameFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e)
			{
				//Print message when the game exits
				System.err.println("Closing game");
			}
		});
		//Set the game in the middle of the screen
		gameFrame.setLocationRelativeTo(null);
		//Make the frame visible
		gameFrame.setVisible(true);
		game.start();
	}
	
	//Stop the game executing
	public void stopExecuting()
	{
		executing = false;
	}

}
