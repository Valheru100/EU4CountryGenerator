package main;

import java.util.ArrayList;

//Represents a player
public class Player
{
	private String name;
	private ArrayList<Integer> rankedChoices = new ArrayList<Integer>();
	
	//Takes a line from the input file PlayerChoices.txt and plits it via the format name,choice1,choice2,choice3,choic4,choice5
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
