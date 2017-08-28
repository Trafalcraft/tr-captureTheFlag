package com.trafalcraft.ctf.data;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class Team {
	private ArrayList <Player> playerList = new ArrayList<Player>();
	private int nbrPoint;
	private boolean flagAtHome;
	
	public Team(){
		this.nbrPoint = 0;
		this.flagAtHome = true;
	}
	
	public void setNbrPoint(int i){
		this.nbrPoint = i;
	}
	
	public int getNbrPoint(){
		return this.nbrPoint;
	}
	
	public void addPlayer(Player p){
		playerList.add(p);
	}
	
	public ArrayList<Player> getPlayerList(){
		return playerList;
	}
	
	public void removePlayer(Player p){
		playerList.remove(p);
	}
	
	public void clear(){
		this.playerList.clear();
		this.nbrPoint = 0;
	}
	
	public void setFlagAtHome(boolean b){
		this.flagAtHome = b;
	}
	
	public boolean getFlagAtHome(){
		return this.flagAtHome;
	}
}
