package com.trafficmaster.states;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.trafficmaster.TrafficMaster;
import com.trafficmaster.entities.Car;
import com.trafficmaster.entities.Car1;
import com.trafficmaster.entities.CopCar;
import com.trafficmaster.entities.Entity;
import com.trafficmaster.entities.Mob;
import com.trafficmaster.entities.Movable;
import com.trafficmaster.entities.Player;
import com.trafficmaster.entities.Truck;
import com.trafficmaster.rendering.textures.Sprite;

public class GameState implements State 
{
	
	//The name of the file that need to be open
	private static final String FILENAME = "resources/settings/highscore.txt";
	
	//Save the Movable object which is the person
	private ArrayList<Movable> mEntities;
	
	//Save the Mob objects which are the car
	//Also check them for collision
	private ArrayList<Mob> mMob;
	
	// Store the game Options
	String gameHigh;
	
	private Player myPlayer;
	
	int lines;
	

	//Store the names
	ArrayList<String> scoresName;
	
	//Store the scores
	ArrayList<String> scores;

	@Override
	public void init() 
	{
		
		
	}

	@Override
	public void enter() 
	{
				//Initialize movable arraylist
				mEntities = new ArrayList<Movable>();
				
				//Initiliaze the string that save the info
				gameHigh = "";
						
				//Initialize lines
				lines = 0;
				
				//Initialize the scoresName arrayList
				scoresName = new ArrayList<>();
				
				//Initialize the scores arrayList
				scores = new ArrayList<>();
				
				//Initialize mob arraylist
				mMob = new ArrayList<Mob>();
				//Add the player
				myPlayer = new Player(new Sprite("frog1"),480,600,this);
				
				//Add the car and other object to the mob arrayList
				mMob.add(new Car(new Sprite("carLeft"),900,510,this));
				mMob.add(new CopCar(new Sprite("copCarLeft"),1250,510,this));
				mMob.add(new Car1(new Sprite("car1Left"),1500,510,this));
				mMob.add(new Truck(new Sprite("truck1Left"),1300,435,this));
				mMob.add(new Truck(new Sprite("truckLeft"),780,435,this));
				mMob.add(new CopCar(new Sprite("truck1Right"),700,362,this));
				mMob.add(new CopCar(new Sprite("car1Right"),350,362,this));
				mMob.add(new CopCar(new Sprite("carRight"),100,290,this));
				mMob.add(new CopCar(new Sprite("truckRight"),500,290,this));
	}

	@Override
	public void tick(StateManager mStateManager) 
	{
		for(Movable e: mEntities)
			e.tick();
		for(Mob e: mMob)
			e.tick();
		
		//Check whether the game is over or not
		if(myPlayer.gameIsOver())
		{
			select(mStateManager);
		}
	}
	
	//Select method basically changes the state of the game
	private void select(StateManager mStateManager)
	{
			String name = myPlayer.getName();
			int score = myPlayer.getScore() + 1;
			
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
                	
                	//Add the name of the scores to the scoresName
                	scoresName.add(line.substring(0, line.indexOf(' ')));
                	
                	//Add the score corespoding to that player to the scores
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
            //System.out.println(scoresName);
            //System.out.println(scores);
            //System.out.println(name);
            //System.out.println(score);
            
            for(int i = 0; i < scoresName.size();i++)
            {
            	if(score >= Integer.parseInt(scores.get(i)))
            	{
            		scores.add(i, ""+score);
            		scoresName.add(i, name);
            		break;
            	}
            }
             // System.out.println(scoresName);
            //System.out.println(scores);
            
            if(scoresName.size() == 6)
            {
            	scoresName.remove(scoresName.size()-1);
            	scores.remove(scores.size()-1);
            }
            
            for(int i = 0; i < scoresName.size();i++)
            {
            	if(i == scoresName.size()-1)
            		gameHigh = gameHigh + scoresName.get(i) + " " + scores.get(i) ;
            	else
            		gameHigh = gameHigh + scoresName.get(i) + " " + scores.get(i) + "\n";
            }
            
            System.out.println(gameHigh);
          //Try to reach the file that will be written
			try 
			{
				File fout = new File("resources/settings/highscore.txt");
				FileOutputStream fos = new FileOutputStream(fout);
				//Write the string into the file
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
					bw.write(gameHigh);
				//Close the file
				bw.close();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
			mStateManager.setState("Menu");
			myPlayer.restartLives();
	}

	@Override
	public void render(Graphics2D g) {
		for(Movable e: mEntities)
			e.render(g);
		for(Mob e: mMob)
			e.render(g);
	}

	@Override
	public void exit() 
	{
		mEntities.clear();
		
		mMob.clear();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Level1";
	}

	public void addEntity(Movable entity) 
	{
		mEntities.add(entity);
	}
	
	public ArrayList<Mob> getMob()
	{
		return mMob;
	}
	
	public Player getPlayer()
	{
		return myPlayer;
	}

}
