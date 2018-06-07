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

	public static void assignGroups(int increase)
	{
		for (Group group : groupList)
		{
			ArrayList<Player> playersByChoice = (ArrayList<Player>) playerList.stream()
					.filter(player -> player.getRankedChoices().get(0 + increase) == group.getGroupId())
					.collect(Collectors.toList());
			Collections.shuffle(playersByChoice);

			for (int i = 0; i < group.getCountriesInGroup().size(); i++)
			{
				int j = i;
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

		if (increase < 5 && playerList.size() > 0)
		{
			assignGroups(increase + 1);
		}
	}

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