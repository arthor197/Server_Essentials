package me.ksmit799197.server_essentials;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
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
		this.logger.info("[Server Essentials]" + pdfFile.getName() + " v" + pdfFile.getVersion() + " Has Been Disabled!");
		saveConfig();
	}
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info("[Server Essentials] " + pdfFile.getName() + " v" + pdfFile.getVersion() + " Has Been Enabled!");
		saveConfig();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args){
		if(sender instanceof Player){
			Player player = (Player) sender;
			Block blockid = player.getTargetBlock(null, 10000);
			if(commandLabel.equalsIgnoreCase("setwp")){
				getConfig().set(player.getName() + ".x", player.getLocation().getBlockX());
				getConfig().set(player.getName() + ".y", player.getLocation().getBlockY());
				getConfig().set(player.getName() + ".z", player.getLocation().getBlockZ());
				saveConfig();
			}else if(commandLabel.equalsIgnoreCase("tpwp")){
				int x = getConfig().getInt(player.getName() + ".x"), y = getConfig().getInt(player.getName() + ".y"), z = getConfig().getInt(player.getName() + ".z");
				player.teleport(new Location(player.getWorld(), x, y, z));
			}else if(commandLabel.equalsIgnoreCase("heal") || commandLabel.equalsIgnoreCase("h")){
				if(args.length == 0){
					player.setHealth(20);
					player.setFireTicks(0);
					player.sendMessage(ChatColor.GREEN + "You Have Been Healed!");
				}else if(args.length == 1){
					if(player.getServer().getPlayer(args [0])!=null){
					Player targetPlayer = player.getServer().getPlayer(args [0]);
					targetPlayer.setHealth(20);
					targetPlayer.setFireTicks(0);
					targetPlayer.sendMessage(ChatColor.GREEN + " You Have Been Healed!");
					}else{
						player.sendMessage(ChatColor.RED + "[ERROR] That Player is Not Online!");
					}
				}	
			}else if(commandLabel.equalsIgnoreCase("food1")){
				if(args.length == 0){
					player.setFoodLevel(20);
					player.sendMessage(ChatColor.GREEN + "Your Hunger has been Satisfied!");	
				}
				else if(args.length == 1){
					if(player.getServer().getPlayer(args [0])!=null){
						Player targetPlayer = player.getServer().getPlayer(args [0]);
						targetPlayer.setFoodLevel(20);
						player.sendMessage(ChatColor.GREEN + "Set Player's Food to 20!");
						targetPlayer.sendMessage(ChatColor.GREEN + "Your Hunger has been Satisfied!");
					}
					else{
						player.sendMessage(ChatColor.RED + "[ERROR] That Player is Not Online!");
					}
				}	
				}else if(commandLabel.equalsIgnoreCase("food0")){
					if(args.length == 0){
					player.setFoodLevel(0);
					player.sendMessage(ChatColor.GREEN + "Your Suddenly Starving!");	
				}
					else if(args.length == 1){
						if(player.getServer().getPlayer(args [0])!=null){
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							targetPlayer.setFoodLevel(0);
							player.sendMessage(ChatColor.GREEN + "Set Player's Food to 0!");
							targetPlayer.sendMessage(ChatColor.GREEN + "Your Suddenly Starving!");
						}else{
							player.sendMessage(ChatColor.RED + "[ERROR] That Player is Not Online!");
						}
					}
				}else if((commandLabel.equalsIgnoreCase("id"))){
					player.sendMessage(ChatColor.GREEN + "Block ID; " + blockid.getTypeId());
				}
				else if(commandLabel.equalsIgnoreCase("ignite")){
					if(args.length == 0){
						player.setFireTicks(500);
						player.sendMessage(ChatColor.GREEN + "Your On Fire!");	
					}
					else if(args.length == 1){
						if(player.getServer().getPlayer(args [0])!=null){
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							targetPlayer.setFireTicks(500);
							targetPlayer.sendMessage(ChatColor.GREEN + "You're On Fire!");
							player.sendMessage(ChatColor.GREEN + "Set Player On Fire!");
						}else{
							player.sendMessage(ChatColor.RED + "[ERROR] That Player is Not Online!");
						}
					}
				}
				else if(commandLabel.equalsIgnoreCase("slap")){
					if(args.length == 0){
						player.damage(1);
						player.sendMessage(ChatColor.GREEN + "Stop Slapping Yourself!");	
					}
					else if(args.length == 1){
						if(player.getServer().getPlayer(args [0])!=null){
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							player.damage(1);
							targetPlayer.sendMessage(ChatColor.GREEN + "You've Been Slaped!");
							player.sendMessage(ChatColor.GREEN + "Slaped Player!");
						}else{
							player.sendMessage(ChatColor.RED + "[ERROR] That Player is Not Online!");
						}
					}
				}
				else if(commandLabel.equalsIgnoreCase("kill")){
					if(args.length == 0){
						player.damage(20);
						player.sendMessage(ChatColor.GREEN + "Killed Yourself!");	
					}
					else if(args.length == 1){
						if(player.getServer().getPlayer(args [0])!=null){
							Player targetPlayer = player.getServer().getPlayer(args [0]);
							targetPlayer.damage(20);
							targetPlayer.sendMessage(ChatColor.GREEN + "You Died!");
							player.sendMessage(ChatColor.GREEN + "Killed Player!");
						}else{
							player.sendMessage(ChatColor.RED + "[ERROR] That Player is Not Online!");
						}
					}
				}
		}return false;
	}
}