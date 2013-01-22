package com.github.Sxw1212.sxwplugin;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.material.item.GenericCustomItem;

public class SuperFuel extends GenericCustomItem implements Burnable{

	public SuperFuel(Plugin plugin) {
		super(plugin, "SuperFuel", "http://dl.dropbox.com/u/18827584/SuperFuel.png");
	}

	@Override
	public int getBurnTime() {
		return 10000;
	}

}
