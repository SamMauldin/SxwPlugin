package com.github.Sxw1212.sxwplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.*;
import org.bukkit.Server;
import org.bukkit.event.*;

public class SxwPlugin extends JavaPlugin{

	@Override
    public void onEnable(){
		getServer().getPluginManager().registerEvents(new SxwEvents(), this);
		getLogger().info("SxwPlugin loaded!");
    }
 
    @Override
    public void onDisable() {
    	getLogger().info("SxwPlugin unloaded!");
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	if(cmd.getName().equalsIgnoreCase("explode")&&args.length==1){
    		float explosionPower = 4F;
    		Player target = sender.getServer().getPlayer(args[0]);
    		target.getWorld().createExplosion(target.getLocation(), explosionPower);
   			target.setHealth(0);
   			sender.sendMessage("BOOM!");
   			return true;
    	}else if(cmd.getName().equalsIgnoreCase("hide")&&args.length==1){
    		Player s = (Player)sender;
            Player target = sender.getServer().getPlayer(args[0]);
            if(target.hasPermission("sxw.nohide")){
            	s.sendMessage("You can't hide from that player!");
            }else{
            	target.hidePlayer(s);
            	s.sendMessage("Hidden!");
            }
            return true;
    	}else if(cmd.getName().equalsIgnoreCase("unhide")&&args.length==1){
    		Player s = (Player)sender;
            Player target = sender.getServer().getPlayer(args[0]);
            target.showPlayer(s);
            s.sendMessage("Unhidden!");
            return true;
    	}else if(cmd.getName().equalsIgnoreCase("hiding")&&args.length==1){
    		Player s = (Player)sender;
            Player target = sender.getServer().getPlayer(args[0]);
            if(target.canSee(s)){
            	s.sendMessage("He can see you...");
            }else{
            	s.sendMessage("You are hidden!");
            }
            return true;
    	}else if(cmd.getName().equalsIgnoreCase("enderopen")&&args.length==1){
    		Player s=(Player) sender;
    		Player t=s.getServer().getPlayer(args[0]);
    		if(t != null){
    			s.openInventory(t.getEnderChest());
    			return true;
    		}else{
    			s.sendMessage("Could not find player!");
    		}
    	}else if(cmd.getName().equalsIgnoreCase("invopen")&&args.length==1){
    		Player s=(Player) sender;
    		Player t=s.getServer().getPlayer(args[0]);
    		if(t != null){
    			s.openInventory(t.getInventory());
    			return true;
    		}else{
    			s.sendMessage("Could not find player!");
    		}
    	}else if(cmd.getName().equalsIgnoreCase("viewopen")&&args.length==1){
    		Player s=(Player) sender;
    		Player t=s.getServer().getPlayer(args[0]);
    		if(t != null){
    			s.openInventory(t.getOpenInventory());
    			return true;
    		}else{
    			s.sendMessage("Could not find player!");
    		}
    	}
    	return false; 
    }
}
