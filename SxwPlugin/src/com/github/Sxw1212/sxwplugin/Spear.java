package com.github.Sxw1212.sxwplugin;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.material.item.GenericCustomItem;

public class Spear extends GenericCustomItem{
	public int id;
	public Spear(Plugin plugin) {
		super(plugin, "Magic Spear", "http://dl.dropbox.com/u/18827584/Spear.png");
		id=this.getRawId();
	}

}