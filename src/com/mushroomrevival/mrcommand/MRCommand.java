package com.mushroomrevival.mrcommand;

import com.mushroomrevival.mrcommand.PlayerCommandListener;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class MRCommand extends JavaPlugin {
	public final Logger logger = Bukkit.getServer().getLogger();
	public static MRCommand plugin;
	public static MRCommand instance;
	public static MRCommand getInstance() {
		return instance;
	}

	public void onEnable() {

		File configFile = new File(getDataFolder().getAbsolutePath(),
				"config.yml");
		if (!configFile.exists()) {
			configFile.mkdir();
			saveDefaultConfig();
		}
		
		initializeListeners();
		instance = this;

	}
	
	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		this.logger.info(pdfFile.getName() + " has been disabled");
	}
	public void initializeListeners() {
		new PlayerCommandListener(this);
	}
	
}
