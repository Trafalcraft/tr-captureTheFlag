package com.trafalcraft.ctf;

import com.trafalcraft.ctf.Controler.ArenaControle;

public class RandowmTeam {
	
	public static String chooseTeam(String arene){
		
		int playerRed = ArenaControle.getArena(arene).getTeam("red").getPlayerList().size();
		int playerBlue = ArenaControle.getArena(arene).getTeam("blue").getPlayerList().size();
		
		if(playerRed == playerBlue){
			double rand = Math.random();
			
			if(rand < 0.5){
				return "red";
			}else{
				return "blue";
			}
		}else if(playerRed < playerBlue){
			return "red";
		}else{
			return "blue";
		}
	}
}
