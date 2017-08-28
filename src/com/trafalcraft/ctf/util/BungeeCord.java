package com.trafalcraft.ctf.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.trafalcraft.ctf.Main;

public class BungeeCord implements PluginMessageListener {

	public static void sendPlayerToHub(Player p) {
		System.out.println("success");
		final ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF("jeux");
		if (Bukkit.getOnlinePlayers().size() > 0) {
			p.sendPluginMessage(Main.getPlugin(), "BungeeCord", out.toByteArray());
		}
	}
	public void onPluginMessageReceived(String arg0, Player arg1, byte[] arg2) {}
}
