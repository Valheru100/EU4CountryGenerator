package main;

import java.util.ArrayList;
import java.util.stream.Collectors;

//Represent a group of countries
public class Group
{
	private ArrayList<Country>	countriesInGroup	= new ArrayList<Country>();
	private Integer				groupId;

	//Constructor made to take in group from csv format: groupId,countryName,countryName,countryName...
	//uses addCountryToGroup to add country using the input name
	public Group(String inputLine)
	{
		String[] splitInput = inputLine.split(",");
		this.setGroupId(Integer.parseInt(splitInput[0]));
		for (int i = 1; i < splitInput.length; i++)
		{
			this.addCountryToGroup(splitInput[i]);
		}
	}

	@Override
	public String toString()
	{
		return "[" + groupId + ": " + countriesInGroup + "]\n";
	}

	public void removeCountryFromList(String countryName)
	{
		this.setCountriesInGroup((ArrayList<Country>) countriesInGroup.stream()
				.filter(country -> country.getName() != countryName).collect(Collectors.toList()));
	}

	public ArrayList<Country> getCountriesInGroup()
	{
		return countriesInGroup;
	}

	public void setCountriesInGroup(ArrayList<Country> countriesInGroup)
	{
		this.countriesInGroup = countriesInGroup;
	}

	public Integer getGroupId()
	{
		return groupId;
	}

	public void setGroupId(Integer groupId)
	{
		this.groupId = groupId;
	}

	public void addCountryToGroup(String countryName)
	{
		Country country = new Country(countryName);
		countriesInGroup.add(country);
	}
}
