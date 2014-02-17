package com.mushroomrevival.mrcommand;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandListener implements Listener {
	public static MRCommand plugin;
	public static List<String> admins = new LinkedList<String>();
	public static List<String> blockedcommands = new LinkedList<String>();

	public PlayerCommandListener(MRCommand instance) {
		plugin = instance;
		Bukkit.getServer().getPluginManager().registerEvents(this, instance);
	}

	@EventHandler
	public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
		Player player = (Player) event.getPlayer();
		String message = event.getMessage();
		int ii = 1;
		while (plugin.getConfig().contains("Command" + ii)) {
			if (message
					.startsWith(plugin.getConfig().getString("Command" + ii))) {
				readAdmins();
				if ((admins.contains(player.getName()))) {

				} else {
					event.setCancelled(true);
					player.sendMessage(ChatColor.WHITE
							+ "Unknown command. Type \"/help\" for help.");
				}
			}
			ii++;
		}
	}

	public void readAdmins() {
		try {
			File file = new File("plugins/MRCommand/admins.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String string = scanner.nextLine();
				admins.add(string);
			}
			scanner.close();
		} catch (Exception exception) {

		}

	}

}