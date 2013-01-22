package com.github.Sxw1212.sxwplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SxwEvents implements Listener{
	public SxwPlugin plugin;
	public double genRand(){
		return Math.random()-0.5;
	}
	public SxwEvents(SxwPlugin p) {
		plugin=p;
	}
	@EventHandler
	public void cartRocket(PlayerInteractEvent ev){
        if(ev.getPlayer().isInsideVehicle()&&ev.getPlayer().hasPermission("sxw.cartrocket")&&ev.getItem().getTypeId()==280){
        	Vehicle v=(Vehicle) ev.getPlayer().getVehicle();
        	v.setVelocity(ev.getPlayer().getLocation().getDirection());
        }
	}
	@EventHandler
	public void customFuel(FurnaceBurnEvent ev){
		SpoutItemStack item = new SpoutItemStack(ev.getFuel());
		if (item.getMaterial() instanceof Burnable && ((Burnable)item.getMaterial()).getBurnTime() > 0) {
			ev.setBurning(true);
			ev.setBurnTime(((Burnable)item.getMaterial()).getBurnTime());
		}
	}
	@EventHandler
	public void spearHandler(EntityDamageByEntityEvent ev){
		if(ev.getDamager() instanceof Player){
			Player ply=(Player) ev.getDamager();
			if(ply.getItemInHand().getTypeId()==SxwPlugin.getSpear().id){
				if(ev.getEntity() instanceof LivingEntity){
					LivingEntity le=(LivingEntity) ev.getEntity();
					le.damage(10);
				}else{
					ev.getEntity().remove();
				}
			}
		}
	}
	@EventHandler
	public void getSpout(final PlayerJoinEvent ev){
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
		    @Override 
		    public void run() {
		    	SpoutPlayer ply=(SpoutPlayer) ev.getPlayer();
		    	if(!ply.isSpoutCraftEnabled()){
		    		ply.sendMessage(ChatColor.RED+"Please get spoutcraft!");
		    	}
		    }
		}, 60L);
	}
}
