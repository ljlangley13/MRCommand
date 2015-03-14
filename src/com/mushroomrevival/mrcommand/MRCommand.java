package com.mushroomrevival.mrcommand;

import com.mushroomrevival.mrcommand.PlayerCommandListener;
import com.mushroomrevival.mrcommand.Metrics;

import java.io.File;
import java.io.IOException;
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
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			System.out.println("Error Submitting stats!");
		}

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
