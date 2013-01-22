package com.github.Sxw1212.sxwplugin;


import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class SxwPlugin extends JavaPlugin{
	private FileConfiguration cfg;
	
	public static MarbleBlock marble;
	public static SuperFuel superfuel;
	private static Spear spear;
	
	public static Spear getSpear() {
		return spear;
	}

	public static void setSpear(Spear spear) {
		SxwPlugin.spear = spear;
	}

	@Override
    public void onEnable(){
		getServer().getPluginManager().registerEvents(new SxwEvents(this), this);
		this.saveDefaultConfig();
		cfg=this.getConfig();
		setSpear(new Spear((JavaPlugin) this));
		marble = new MarbleBlock((JavaPlugin) this);
		superfuel = new SuperFuel((JavaPlugin) this);
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
    		if(target!=null){
    		target.getWorld().createExplosion(target.getLocation(), explosionPower);
   			target.setHealth(0);
   			sender.sendMessage("BOOM!");
    		}else{
    		sender.sendMessage("Unable to find target.");
    		}
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
    	}else if(cmd.getName().equalsIgnoreCase("cexec")){
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
		   result.append( args[i] );
		   result.append(" ");
		}
		String inp = result.toString();
        sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), inp);
		return true;
	}else if(cmd.getName().equalsIgnoreCase("ca")&&args.length>=1){
		if(sender.hasPermission("sxw.ca."+args[0])){
			String aliascmd=cfg.getString("consolealias."+args[0]);
			if(aliascmd!=null){
				aliascmd=aliascmd.replaceAll("\\$PLAYER", sender.getName());
				for (int i = 1; i < args.length; i++) {
					aliascmd=aliascmd.replaceAll("\\$ARG"+i, args[i]);
				}
				StringBuffer argstr = new StringBuffer();
				for (int i = 1; i < args.length; i++) {
				   argstr.append( args[i] );
				   argstr.append(" ");
				}
				aliascmd=aliascmd.replaceAll("\\$ARGS", argstr.toString());
				String[] aliascmds = aliascmd.split(";;");
				for (int i = 0; i < aliascmds.length; i++) {
					sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), aliascmds[i]);
				}
			}else{
				sender.sendMessage("Unable to find alias!");
			}
		}else{
			sender.sendMessage("You don't have permission to use this alias!");
		}
		return true;
	}else if(cmd.getName().equalsIgnoreCase("sxwreload")){
		this.reloadConfig();
		cfg=this.getConfig();
		sender.sendMessage("Reloaded SxwPlugin v1.0.4");
		return true;
	}else if(cmd.getName().equalsIgnoreCase("chat")){
		StringBuffer argstr = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
		   argstr.append( args[i] );
		   argstr.append(" ");
		}
		sender.getServer().broadcastMessage(argstr.toString());
		return true;
	}else if(cmd.getName().equalsIgnoreCase("sudo")&&args.length>1){
		StringBuffer argstr = new StringBuffer();
		for (int i = 1; i < args.length; i++) {
		   argstr.append( args[i] );
		   argstr.append(" ");
		}
		sender.getServer().getPlayer(args[0]).chat("/"+argstr.toString());
		sender.sendMessage("Sudo action sent...");
		return true;
	}else if(cmd.getName().equalsIgnoreCase("resend")){
		Player ply=(Player) sender;
		Location loc=ply.getLocation();
		double xraw = loc.getChunk().getX();
		double zraw = loc.getChunk().getZ();
		ply.getWorld().refreshChunk((int)xraw, (int)zraw);
		ply.sendMessage("Refreshed!");
		return true;
	}
    	sender.sendMessage("Sorry, that command isn't working with those arguments. Heres the usage.");
    	return false; 
    }
}
