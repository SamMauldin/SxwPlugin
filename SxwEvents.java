package com.github.Sxw1212.sxwplugin;

import org.bukkit.block.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.*;
import org.bukkit.util.Vector;
import org.bukkit.Server;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerInteractEvent;

public class SxwEvents implements Listener{
	@EventHandler
	public void CartRocket(PlayerInteractEvent ev){
        if(ev.getPlayer().isInsideVehicle()&&ev.getPlayer().hasPermission("sxw.cartrocket")){
        	Vehicle v=(Vehicle) ev.getPlayer().getVehicle();
        	v.setVelocity(ev.getPlayer().getLocation().getDirection());
        }
	}
}
