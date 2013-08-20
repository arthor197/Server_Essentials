package me.ksmit799197.server_essentials;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	Logger logger = Logger.getLogger("Minecraft");
	
	@Override
	public void onDisable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + pdfFile.getVersion() + " Has Been Disabled! ");
		saveConfig();
	}
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + pdfFile.getVersion() + " Has Been Enabled!");
		saveConfig();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args){
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(commandLabel.equalsIgnoreCase("setmylocation")){
				getConfig().set(player.getName() + ".x", player.getLocation().getBlockX());
				getConfig().set(player.getName() + ".y", player.getLocation().getBlockY());
				getConfig().set(player.getName() + ".z", player.getLocation().getBlockZ());
				saveConfig();
			}else if(commandLabel.equalsIgnoreCase("location")){
				int x = getConfig().getInt(player.getName() + ".x"), y = getConfig().getInt(player.getName() + ".y"), z = getConfig().getInt(player.getName() + ".z");
				player.teleport(new Location(player.getWorld(), x, y, z));
			}
		}return false;
	}

}