package com.trafficmaster.utilities.managers;

public abstract class ResourseManager 
{
	//Variables
	
	//Save the number of resources
	protected int count = 1;
	
	//When a reference is added increase the count of resources
	public void addReference()
	{
		count++;
	}
	
	//When a reference is deleted decrease the count of resources
	public boolean removeReference()
	{
		count--;
		return count ==   0;
	}

}
