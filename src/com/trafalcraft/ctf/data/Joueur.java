package com.trafalcraft.ctf.data;

import org.bukkit.entity.Player;

public class Joueur {
	Player player;
	String classe = "defaut";
	String arene;
	boolean inGame;
	boolean hasFlag;
	String team;
	
	
	public Joueur(Player p, String c, String a, String t){
		this.player = p;
		this.classe = c;
		this.arene = a;
		this.inGame = false;
		this.hasFlag = false;
		this.team = t;
	}
	
	public void setPlayer(Player pl){
		this.player = pl;
	}
	
	public void setClasse(String cl){
		this.classe = cl;
	}
	
	public void setArene(String ar){
		this.arene = ar;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public String getClasse(){
		return this.classe;
	}
	
	public String getArene(){
		return this.arene;
	}
	
	public boolean getInGame(){
		return this.inGame;
	}
	
	public void setInGame(boolean b){
		this.inGame = b;
	}
	
	public void setHasFlag(boolean b){
		this.hasFlag = b;
	}
	
	public boolean getHasFlag(){
		return this.hasFlag;
	}
	
	public String getTeam(){
		return this.team;
	}
}
