package com.shadowblox.shadowdisablecommand;

import com.shadowblox.shadowdisablecommand.PlayerCommandListener;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class ShadowDisableCommand extends JavaPlugin {
	public final Logger logger = Bukkit.getServer().getLogger();
	public static ShadowDisableCommand plugin;
	public static ShadowDisableCommand instance;
	public static ShadowDisableCommand getInstance() {
		return instance;
	}

	public void onEnable() {
		
		File configFile = new File(getDataFolder().getAbsolutePath(),
				"config.yml");
		if (!configFile.exists()) {
			configFile.mkdir();
			saveDefaultConfig();
		}
		
		try {
			File file = new File("plugins/ShadowDisableCommand/admins.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (Exception exception) {

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
