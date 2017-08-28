package com.trafalcraft.ctf;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.inventivetalent.bossbar.BossBarAPI;

import com.trafalcraft.ctf.Controler.ArenaControle;
import com.trafalcraft.ctf.Controler.PlayerControle;

public class Quite {
	public static void leave(Player p){
		
		if(PlayerControle.contains(p.getName())){
			
			ClearPotion.clearEffect(p);
			p.setDisplayName(p.getName());
			BossBarAPI.removeAllBars(p);
			
			String arene = PlayerControle.getJoueur(p.getName()).getArene();
			String team =  PlayerControle.getJoueur(p.getName()).getTeam();
			
			ArenaControle.getArena(arene).removePlayer(Bukkit.getPlayer(p.getName()), PlayerControle.getJoueur(p.getName()).getTeam());
			PlayerControle.removePlayer(p.getName());
			
			if(ArenaControle.getArena(arene).getStatus().equalsIgnoreCase("in-game")){
				
				if(ArenaControle.getArena(arene).getTeam(team).getPlayerList().size() == 0){
					
					for(Player pl : ArenaControle.getArena(arene).getPlayerList()){
						
           				pl.sendMessage("§3§lCTF> §b" + "la partie est terminée, il n'y a plus assez de joueurs pour continuer");
           				PlayerControle.getJoueur(pl.getName()).setInGame(false);
           			}
					
					ArenaControle.getArena(arene).stopGameTimer();
					ArenaControle.getArena(arene).setStatus("end");	
					
					if(team.equalsIgnoreCase("red")){
						
						ArenaControle.getArena(arene).setWinner("blue");
						
						for(Player pl : ArenaControle.getArena(arene).getPlayerList()){
			   				pl.sendMessage("§3§lCTF> §b" + "l'équipe bleue a gagné la partie !!!");
			   			}
					}else{
						
						ArenaControle.getArena(arene).setWinner("red");
						
						for(Player pl : ArenaControle.getArena(arene).getPlayerList()){
			   				pl.sendMessage("§3§lCTF> §b" + "l'équipe rouge a gagné la partie !!!");
			   			}
					}
					
					ArenaControle.getArena(arene).startFinishTimer();
				}
			}
		}
	}
}
