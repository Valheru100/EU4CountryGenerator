package main;

public class Country
{
	private String name;
	private Player player = null;
	
	public Country(String name)
	{
		this.setName(name);
	}
	
	@Override
	public String toString()
	{
		String pName = "Unassigned";
		if(player != null)
		{
			if(player.getName() != null)
			{
				pName = player.getName();
			}
		}
		return "\n" + name + ": " + pName + "";
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Player getPlayer()
	{
		return player;
	}
	public void setPlayer(Player player)
	{
		this.player = player;
	}
}
