package com.trafalcraft.ctf.Controler;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.inventivetalent.bossbar.BossBarAPI;

import com.google.common.collect.Maps;
import com.trafalcraft.ctf.ClearPotion;
import com.trafalcraft.ctf.data.Joueur;
import com.trafalcraft.ctf.file.FileControler;

import net.md_5.bungee.api.chat.TextComponent;

public class PlayerControle extends BossBarAPI{
	private final static Map<String, Joueur> inGamePlayers = Maps.newHashMap();
	
	public static void addPlayer(String name, String aname, String team){
		if(!inGamePlayers.containsKey(name)){
			if(ArenaControle.getArena(aname).getStatus().equalsIgnoreCase("lobby")){
				if(team.equalsIgnoreCase("red") || team.equalsIgnoreCase("blue")){
					if(ArenaControle.getArena(aname).getTeam(team).getPlayerList().size() < FileControler.getArena(aname).getInt("maxplayer")){
					
					
						Joueur player = new Joueur(Bukkit.getPlayer(name), "defaut", aname, team);
						inGamePlayers.put(name, player);
						ArenaControle.getArena(aname).addPlayer(Bukkit.getPlayer(name), team);
					
						Location loc = new Location(Bukkit.getWorld(FileControler.getArena(aname).getString("world")),FileControler.getArena(aname).getDouble("lobby." + team + ".x"),FileControler.getArena(aname).getDouble("lobby." + team + ".y"),FileControler.getArena(aname).getDouble("lobby." + team + ".z"),(float)FileControler.getArena(aname).getDouble("lobby." + team + ".yaw"),(float)FileControler.getArena(aname).getDouble("lobby." + team + ".pitch"));
						Bukkit.getPlayer(name).teleport(loc);
						Bukkit.getPlayer(name).setHealth(20);
						Bukkit.getPlayer(name).setFoodLevel(20);
						Bukkit.getPlayer(name).setSaturation(20);
						Bukkit.getPlayer(name).getInventory().clear();
						Bukkit.getPlayer(name).setGameMode(GameMode.SURVIVAL);
						ClearPotion.clearEffect(Bukkit.getPlayer(name));
					
						String equipe;
						if(team.equalsIgnoreCase("red")){
							equipe = "rouge";
						}else{
							equipe = "bleu";
						}
						
						if(ArenaControle.getArena(aname).getTeam("blue").getPlayerList().size() == 2 || ArenaControle.getArena(aname).getTeam("red").getPlayerList().size() == 2){
							
							BossBarAPI.removeAllBars(Bukkit.getPlayer(name));
							BossBarAPI.addBar(Bukkit.getPlayer(name), new TextComponent("§b" + "capture de drapeau"), Color.BLUE, Style.PROGRESS, 1.0f);
						
						}else{
							
							BossBarAPI.removeAllBars(Bukkit.getPlayer(name));
							BossBarAPI.addBar(Bukkit.getPlayer(name), new TextComponent("§b" + "capture de drapeau"), Color.BLUE, Style.PROGRESS, (float)1, 20*ArenaControle.getArena(aname).getTemps(), 1, Property.PLAY_MUSIC);
							
							Bukkit.getPlayer(name).setLevel(ArenaControle.getArena(aname).getTemps());;
						}
						
						for(Player p : ArenaControle.getArena(aname).getPlayerList()){
							p.sendMessage("§3§lCTF> §b" + name + " a rejoint l'équipe " + equipe +" (" + ArenaControle.getArena(aname).getPlayerList().size() + "/" + FileControler.getArena(aname).getInt("maxplayer")*2 + ")");
						}
					
					}else{
						Bukkit.getPlayer(name).sendMessage("§3§lCTF> §b" + "la partie est déjà pleine");
					}
				}else{
					Bukkit.getPlayer(name).sendMessage("§3§lCTF> §b" + "cette équipe n'existe pas");
				}
			}else{
				Bukkit.getPlayer(name).sendMessage("§3§lCTF> §b" + "la partie est déjà en cours");
			}
		}else{
			Bukkit.getPlayer(name).sendMessage("§3§lCTF> §b" + "vous ếtes déjà dans une arène");
		}
	}
	
	public static void removePlayer(String name){
			inGamePlayers.remove(name);
	}
	
	public static Joueur getJoueur(String s){
		return inGamePlayers.get(s);
	}
	
	public static boolean contains(String s){
		if(inGamePlayers.containsKey(s)){
			return true;
		}else{
			return false;
		}
	}
}
