package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main
{
	public static ArrayList<Group>	groupList	= new ArrayList<Group>();
	public static ArrayList<Player>	playerList	= new ArrayList<Player>();

	public static void main(String[] args) throws FileNotFoundException
	{
		fillGroups();
		fillPlayers();
		assignGroups(0);
	}

	//This method takes the filled group and filled player lists and deals out countries by preference, so if we have
	//persons a, b, c and d and they all pick group 1 as their primary group it'll randomly assign each country in that
	//group to 3 of the people. Once a country is picked it's then removed from the group so it won't be picked again.
	//This happens recursively until all groups are picked, running through the player choices, so if we imagine person d
	//didn't get group 1 but group 2 is their number 2 choice then if there's any group 2 countries left then it'll put them
	//into the running for those and so on.
	public static void assignGroups(int increase)
	{
		for (Group group : groupList)
		{
			//Makes a new ArrayList with all the players where their current ranked choice matches the groupId
			//this will increase recursively until either all countries have been taken or we get to the number 5
			//choices
			ArrayList<Player> playersByChoice = (ArrayList<Player>) playerList.stream()
					.filter(player -> player.getRankedChoices().get(0 + increase) == group.getGroupId())
					.collect(Collectors.toList());
			Collections.shuffle(playersByChoice);
			
			//For each country
			for (int i = 0; i < group.getCountriesInGroup().size(); i++)
			{
				//Had to do this because of scoping issues with streams, wouldn't let me use i because it's not final
				int j = i;
				//try matching players against countries, if we run out of players (ie. 2 players have group 1) we
				//catch the exception and move along. Here we also print out the matched choices and remove those people
				//from people list who've been assigned to groups already
				try
				{
					group.getCountriesInGroup().get(i).setPlayer(playersByChoice.get(i));
					System.out.println(group.getCountriesInGroup().get(i));
					playerList = (ArrayList<Player>) playerList.stream()
							.filter(player -> player.getName() != playersByChoice.get(j).getName())
							.collect(Collectors.toList());
				} catch (Exception e)
				{
				}
			}
		}
		
		//Once the first iteration has happened, remove the countries that have assigned players from their groups
		for (Group group : groupList)
		{
			for(Country country : group.getCountriesInGroup())
			{
				if(country.getPlayer() != null)
				{
					group.removeCountryFromList(country.getName());
				}
			}
		}

		//recursively call the method increasing the input by 1, until all sets of choices are processed
		if (increase < 5 && playerList.size() > 0)
		{
			assignGroups(increase + 1);
		}
	}

	//Fill groupList from GroupData.txt
	@SuppressWarnings("resource")
	public static void fillGroups() throws FileNotFoundException
	{
		File file = new File("src/resources/GroupData.txt");
		Scanner reader = new Scanner(file);
		String input = null;

		while (reader.hasNext())
		{
			input = reader.nextLine();
			groupList.add(new Group(input));
		}
	}

	//Fill playerList from PlayerChoices.txt
	@SuppressWarnings("resource")
	public static void fillPlayers() throws FileNotFoundException
	{
		File file = new File("src/resources/PlayerChoices.txt");
		Scanner reader = new Scanner(file);
		String input = null;

		while (reader.hasNext())
		{
			input = reader.nextLine();
			playerList.add(new Player(input));
		}
	}
}
