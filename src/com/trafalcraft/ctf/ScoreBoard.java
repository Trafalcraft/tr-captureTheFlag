package com.trafalcraft.ctf;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.trafalcraft.ctf.Controler.ArenaControle;


public class ScoreBoard {
	private static ScoreboardManager manager = Bukkit.getScoreboardManager();
	private Scoreboard Board;
	private Objective o;
	
	public ScoreBoard(String aname){
		addScoreBoard(aname);
		
	}

	
	private void addScoreBoard(String aname) {
		this.Board = manager.getNewScoreboard();
		this.o = Board.registerNewObjective("Team", "point");
		this.sendScoreBoard(aname);
	}
	
	
	private void sendScoreBoard(String aname){
		this.o.setDisplaySlot(DisplaySlot.SIDEBAR);
		this.o.setDisplayName("§3§l" + "point(s) marqué(s)");
		
			this.o.getScore("rouge").setScore(0);
			this.o.getScore("bleu").setScore(0);
		
		for(Player allp : ArenaControle.getArena(aname).getPlayerList()){
			allp.setScoreboard(Board);
		}
		
	}
	
	
	public void updateScore(String team, String arene){
		String equipe;
		
		if(team.equalsIgnoreCase("red")){
			equipe = "rouge";
		}else{
			equipe = "bleu";
		}
		this.o.getScore(equipe).setScore(ArenaControle.getArena(arene).getTeam(team).getNbrPoint());
	}
	
	
	public void clearScoreBoard(){
		this.Board.clearSlot(DisplaySlot.SIDEBAR);
		this.o.unregister();
	}
}
