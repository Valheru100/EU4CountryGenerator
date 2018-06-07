package main;

import java.util.ArrayList;

public class Player
{
	private String name;
	private ArrayList<Integer> rankedChoices = new ArrayList<Integer>();
	
	public Player(String inputLine)
	{
		String[] splitInput = inputLine.split(",");
		this.setName(splitInput[0]);
		for(int i = 1; i < splitInput.length; i++)
		{
			rankedChoices.add(Integer.parseInt(splitInput[i]));
		}
	}
	
	@Override
	public String toString()
	{
		return name + ": " + rankedChoices + "\n";
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public ArrayList<Integer> getRankedChoices()
	{
		return rankedChoices;
	}
	public void setRankedChoices(ArrayList<Integer> rankedChoices)
	{
		this.rankedChoices = rankedChoices;
	}
}
