package com.github.Sxw1212.sxwplugin;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.material.block.GenericCubeCustomBlock;

public class MarbleBlock extends GenericCubeCustomBlock{

	public MarbleBlock(Plugin plugin){
		super(plugin, "Marble Block", 98, "http://dl.dropbox.com/u/18827584/MarbleBlock.png", 16);
	}
}
