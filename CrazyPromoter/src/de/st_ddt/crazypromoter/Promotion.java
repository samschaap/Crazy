package de.st_ddt.crazypromoter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import com.platymuus.bukkit.permissions.PermissionsPlugin;

import de.st_ddt.crazyutil.ChatHelper;
import de.st_ddt.crazyutil.conditions.Condition;
import de.st_ddt.crazyutil.conditions.ConditionBase;

public class Promotion
{

	protected String name;
	protected ConditionBase<Player> condition;
	protected List<String> commands;
	protected final PermissionsPlugin permissionsPlugin = ((PermissionsPlugin) Bukkit.getPluginManager().getPlugin("PermissionsBukkit"));

	public Promotion(ConfigurationSection config)
	{
		super();
		name = config.getString("name");
		commands = config.getStringList("commands");
		condition = ConditionBase.load(config.getConfigurationSection("condition"));
	}

	public Promotion(String name)
	{
		super();
		this.name = name;
		this.commands = new ArrayList<String>();
		this.commands.add("say Please edit Promotion because $0$ wants to promote!");
	}

	public boolean isApplyable(Player player)
	{
		return condition.match(player);
	}

	public void apply(Player player)
	{
		CommandSender console = Bukkit.getConsoleSender();
		for (String command : commands)
			permissionsPlugin.getServer().dispatchCommand(console, ChatHelper.putArgs(command, player.getName()));
	}

	public String getName()
	{
		return name;
	}

	public void save(ConfigurationSection config, String path)
	{
		config.set(path + "name", name);
		config.set(path + "commands", commands);
		condition.save(config, path + "condition.");
	}

	public Condition<Player> getCondition()
	{
		return condition;
	}

	public void setCondition(ConditionBase<Player> condition)
	{
		this.condition = condition;
	}
}
