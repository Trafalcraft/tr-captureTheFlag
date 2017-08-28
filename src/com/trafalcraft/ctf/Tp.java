package com.trafalcraft.ctf;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.trafalcraft.ctf.Controler.ArenaControle;
import com.trafalcraft.ctf.Controler.PlayerControle;
import com.trafalcraft.ctf.file.FileControler;

public class Tp {
	static int temps;
	static int taskTp;
	
	public static void teleport(Player p){
		String arene = PlayerControle.getJoueur(p.getName()).getArene();
		String team = PlayerControle.getJoueur(p.getName()).getTeam();  
		
		Location loc = new Location(Bukkit.getWorld(FileControler.getArena(arene).getString("world")), FileControler.getArena(arene).getDouble(team + "spawn.x"), FileControler.getArena(arene).getDouble(team + "spawn.y"), FileControler.getArena(arene).getDouble(team + "spawn.z"), (float)FileControler.getArena(arene).getDouble(team + "spawn.yaw"), (float)FileControler.getArena(arene).getDouble(team + "spawn.pitch"));
		p.teleport(loc);
		
		startLobbyTimer(p);
	}
	
	public static void startLobbyTimer(Player p){
		taskTp = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
            
			public void run() {
            	
				stopLobbyTimer(p);
				
            }
         }, 3);
	}
	
	public static void stopLobbyTimer(Player p){
		System.out.println("test tp");
		Bukkit.getServer().getScheduler().cancelTask(taskTp);
		
		p.setHealth(0);
		
		p.spigot().respawn();
		
		String arene = PlayerControle.getJoueur(p.getName()).getArene();
		String team = PlayerControle.getJoueur(p.getName()).getTeam();  
		
		Location loc = new Location(Bukkit.getWorld(FileControler.getArena(arene).getString("world")), FileControler.getArena(arene).getDouble(team + "spawn.x"), FileControler.getArena(arene).getDouble(team + "spawn.y"), FileControler.getArena(arene).getDouble(team + "spawn.z"), (float)FileControler.getArena(arene).getDouble(team + "spawn.yaw"), (float)FileControler.getArena(arene).getDouble(team + "spawn.pitch"));
		
		p.teleport(loc);
		
		ArenaControle.getArena(arene).getClasse().loadChestClass(p, PlayerControle.getJoueur(p.getName()).getClasse());
		
	}
	
}
