package com.trafalcraft.ctf.data;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Banner;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_10_R1.block.CraftBanner;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.inventivetalent.bossbar.BossBarAPI;
import org.inventivetalent.bossbar.BossBarAPI.Property;
import org.inventivetalent.bossbar.BossBarAPI.Style;

import com.trafalcraft.ctf.ClearPotion;
import com.trafalcraft.ctf.Main;
import com.trafalcraft.ctf.ManageClasses;
import com.trafalcraft.ctf.Quite;
import com.trafalcraft.ctf.ScoreBoard;
import com.trafalcraft.ctf.Tp;
import com.trafalcraft.ctf.Controler.PlayerControle;
import com.trafalcraft.ctf.file.FileControler;
import com.trafalcraft.ctf.util.BungeeCord;

import net.md_5.bungee.api.chat.TextComponent;

public class Arene extends BossBarAPI{
	private String name;
	private ArrayList <Player> playerList = new ArrayList<Player>();
	private Team redTeam;
	private Team blueTeam;
	private String status;
	private int temps;
	private int taskLobby;
	private int taskGame;
	private int taskFinish;
	private String winner;
	private ManageClasses classes;
	private ScoreBoard score;
	private boolean event;
	
	public Arene(String nom){
		
		this.name = nom;
		
		this.status = "lobby";
		
		Team red = new Team();
		this.redTeam = red;
		Team blue = new Team();
		this.blueTeam = blue;
		
		ManageClasses classe = new ManageClasses();
		this.classes = classe;
		
	}
	
	public int getTaskLobby(){
		return this.taskLobby;
	}
	
	public void setTemps(int i){
		this.temps = i;
	}
	
	public int getTemps(){
		return this.temps;
	}
	
	public void setWinner(String s){
		this.winner = s;
	}
	
	public void setEvent(boolean e){
		this.event = e;
	}
	
	public boolean getEvent(){
		return this.event;
	}
	
	public ScoreBoard getScoreBoard(){
		return this.score;
	}
	
	public ManageClasses getClasse(){
		return this.classes;
	}
	
	public void setName(String nom){
		this.name = nom;
	}
	
	public String getName(){
		return name;
	}
	
	public void addPlayer(Player p, String s){
		playerList.add(p);
		getTeam(s).addPlayer(p);
		
		if(getTeam(s).getPlayerList().size() == 1 && getTeam("-" + s).getPlayerList().size() >= 1){
			startLobbyTimer();
			
			for(Player pl : getPlayerList()){
				BossBarAPI.removeAllBars(pl);
				BossBarAPI.addBar(pl, new TextComponent("§b" + "capture de drapeau"), Color.BLUE, Style.PROGRESS, 1.0f, 620, 1, Property.PLAY_MUSIC);
			
				pl.setLevel(30);
			}
		}
	}
	
	public void removePlayer(Player p, String s){
		playerList.remove(p);
		getTeam(s).removePlayer(p);
		
		if(getTeam(s).getPlayerList().size() < 1){
			if(this.taskLobby != 0){
				stopLobbyTimer();
				
				for(Player pl : getPlayerList()){
					pl.sendMessage("§3§lCTF> §b" + "il n'y a plus assez de joueurs pour commencer la partie");
					
					BossBarAPI.removeAllBars(pl);
					BossBarAPI.addBar(pl, new TextComponent("§b" + "capture de drapeau"), Color.BLUE, Style.PROGRESS, 1.0f);
					
					pl.setLevel(0);
				}
			}
		}
	}
	
	public int PlayerIndexOf(Player p){
		return playerList.indexOf(p);
	}
	
	public void setStatus(String s){
		this.status = s;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public ArrayList<Player> getPlayerList(){
		return this.playerList;
	}
	
	public Team getTeam(String s){
		
		if(s.equalsIgnoreCase("red") || s.equalsIgnoreCase("-blue")){
			return this.redTeam;
		}else if(s.equalsIgnoreCase("blue") || s.equalsIgnoreCase("-red")){
			return this.blueTeam;
		}else{
			return null;
		}
		
	}
	
	public void startLobbyTimer(){
		this.temps = 31;
		this.taskLobby = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            
			public void run() {
            	
						for(Player pl : getPlayerList()){
							pl.setLevel(temps);
						}
                   		if(temps == 30||temps == 20||temps == 10||(temps <= 5&&temps>0)){
                   			for(Player pl : getPlayerList()){
                   				pl.sendMessage("§3§lCTF> §b" + "la partie commence dans " + temps + " seconde(s)");
                   				pl.playSound(pl.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
                   				pl.sendTitle("§b" + temps + "", "§b" + "seconde(s)");
                   			}
                   		}else if(temps <= 0){
                   			for(Player pl : getPlayerList()){
                   				pl.sendMessage("§3§lCTF> §b" + "la partie commence !!!");  
                   				pl.playSound(pl.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100.0F, 1.0F);
                   			}
                   			
                   			Location redFlag = new Location(Bukkit.getWorld(FileControler.getArena(name).getString("world")), FileControler.getArena(name).getInt("redflag.x"), FileControler.getArena(name).getInt("redflag.y"), FileControler.getArena(name).getInt("redflag.z"));
                   			Location blueFlag = new Location(Bukkit.getWorld(FileControler.getArena(name).getString("world")), FileControler.getArena(name).getInt("blueflag.x"), FileControler.getArena(name).getInt("blueflag.y"), FileControler.getArena(name).getInt("blueflag.z"));
                   			
                   			redFlag.getBlock().setType(Material.STANDING_BANNER);
                   			CraftBanner banner = new CraftBanner(redFlag.getBlock());
                   			banner.setBaseColor(DyeColor.RED);
                   			org.bukkit.material.Banner banner2 = (org.bukkit.material.Banner) banner.getData();
                   			banner2.setFacingDirection(BlockFace.valueOf(FileControler.getArena(name).getString("redflag.direction")));
                   			banner.update();
                   			
                   			blueFlag.getBlock().setType(Material.STANDING_BANNER);
                   			CraftBanner bannerb = new CraftBanner(blueFlag.getBlock());
                   			bannerb.setBaseColor(DyeColor.BLUE);
                   			org.bukkit.material.Banner bannerb2 = (org.bukkit.material.Banner) bannerb.getData();
                   			bannerb2.setFacingDirection(BlockFace.valueOf(FileControler.getArena(name).getString("blueflag.direction")));
                   			bannerb.update();
                   			
                   			stopLobbyTimer();
                   			taskLobby = 0;
               				startGameTimer();
               				status = "in-game";
               				for(Player p : playerList){
               					Tp.teleport(p);
               					PlayerControle.getJoueur(p.getName()).setInGame(true);
               					p.setHealth(20);
               					p.setSaturation(0);
               					p.setFoodLevel(20);
               					ClearPotion.clearEffect(p);
               					String color;
               					if(PlayerControle.getJoueur(p.getName()).getTeam().equalsIgnoreCase("red")){
               						color = "§4";
               					}else{
               						color = "§1";
               					}
               					p.setDisplayName(color + p.getName());
               					
               					BossBarAPI.removeAllBars(p);
                   				BossBarAPI.addBar(p, new TextComponent("§b" + "capture de drapeau"), Color.BLUE, Style.PROGRESS, 1.0f, (FileControler.getArena(getName()).getInt("temps")) * 20 * 60, 1, Property.PLAY_MUSIC);
            					
               				}
               				
               			    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {

               	                @Override
               	                public void run() {
               	                   for(Player p : playerList){
               	                	   p.sendTitle("", "");
               	                   }
               	                }
               	            },20);
               				
               				score = new ScoreBoard(getName());
                   		}
            	temps = temps-1;
            }
         }, 0, 20);
	}
	
	public void stopLobbyTimer(){
		Bukkit.getServer().getScheduler().cancelTask(this.taskLobby);
	}
	
	public void startGameTimer(){
		this.temps = (FileControler.getArena(name).getInt("temps"))*60;
		this.taskGame = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            
			public void run() {
				
						if(temps == 30||temps == 20||temps == 10||(temps <= 5&&temps>0)){
							for(Player pl : getPlayerList()){
								pl.playSound(pl.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
							}
						}
            	
                   		if(temps == 60){
                   			for(Player pl : getPlayerList()){
                   				pl.sendMessage("§3§lCTF> §b" + "la partie se termine dans une minute !!!");
                   			}
                   		}else if(temps <= 0){
                   			for(Player pl : getPlayerList()){
                   				pl.sendMessage("§3§lCTF> §b" + "la partie est terminée !!!");
                   				PlayerControle.getJoueur(pl.getName()).setInGame(false);
                   			}
                   			stopGameTimer();
                   			setStatus("end");
                   			taskGame = 0;
               				WhoWin();
                   		}
            	temps = temps-1;
            }
         }, 0, 20);
	}
	
	public void stopGameTimer(){
		Bukkit.getServer().getScheduler().cancelTask(taskGame);
	}
	
	public void WhoWin(){
		int redPoint = getTeam("red").getNbrPoint();
		int bluePoint = getTeam("blue").getNbrPoint();
		
		if(redPoint < bluePoint){
			for(Player pl : getPlayerList()){
   				pl.sendMessage("§3§lCTF> §b" + "l'équipe bleue a gagné la partie !!!");
   				pl.sendTitle("§b" + "l'équipe bleue", "a gagné!!!");
   				

				BossBarAPI.removeAllBars(pl);
				BossBarAPI.addBar(pl, new TextComponent("§b" + "l'équipe bleue a gagné!!!"), Color.BLUE, Style.PROGRESS, 1.0f, Property.PLAY_MUSIC);
   				
   			}
			
			this.winner = "blue";
		}else if(redPoint > bluePoint){
			for(Player pl : getPlayerList()){
   				pl.sendMessage("§3§lCTF> §b" + "l'équipe rouge a gagné la partie !!!");
   				pl.sendTitle("§b" + "l'équipe rouge", "a gagné!!!");
   				
   				BossBarAPI.removeAllBars(pl);
				BossBarAPI.addBar(pl, new TextComponent("§b" + "l'équipe rouge a gagné!!!"), Color.BLUE, Style.PROGRESS, 1.0f, Property.PLAY_MUSIC);
   				
   			}
			
			this.winner = "red";
		}else{
			for(Player pl : getPlayerList()){
   				pl.sendMessage("§3§lCTF> §b" + "il y a égalité !!!");
   				pl.sendTitle("§b" + "égalité", "");
   				
   				BossBarAPI.removeAllBars(pl);
				BossBarAPI.addBar(pl, new TextComponent("§b" + "égalité!!!"), Color.BLUE, Style.PROGRESS, 1.0f, Property.PLAY_MUSIC);
   				
   				
   			}
			
			this.winner = null;
		}
		
		startFinishTimer();
	}
	
	public void startFinishTimer(){
		this.temps = 5;
		this.taskFinish = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            
			public void run() {
            	
                   		if(temps != 0){
                   			if(winner != null){
                   				for(Player listwinner : getTeam(winner).getPlayerList()){
                   	
                   					Firework f = (Firework) listwinner.getWorld().spawn(listwinner.getLocation(), Firework.class);
                   					FireworkMeta fm = f.getFireworkMeta();
                    		
                   					fm.addEffects(FireworkEffect.builder()
                   							.flicker(true)
                   							.trail(true)
                   							.with(Type.BALL_LARGE)
                   							.withColor(org.bukkit.Color.AQUA)
                   							.withColor(org.bukkit.Color.RED)
                   							.withColor(org.bukkit.Color.AQUA)
                   							.withColor(org.bukkit.Color.RED)
                   							.build()
                   							);
                    		
                   					fm.setPower(2);
                   					f.setFireworkMeta(fm);
                   				}
                   			}
                   		}else{
                   			
                   			for(Player p : getPlayerList()){
                   				p.setDisplayName(p.getName());
                   			}
                   			
                   			if(event == true){
	                   			for(Player p : getPlayerList()){
	                   				p.teleport(new Location(Bukkit.getWorld(FileControler.getArena(getName()).getString("world")), FileControler.getArena(getName()).getDouble("eventlobby.x"), FileControler.getArena(getName()).getDouble("eventlobby.y"), FileControler.getArena(getName()).getDouble("eventlobby.z"), (float)FileControler.getArena(getName()).getDouble("eventlobby.yaw"), (float)FileControler.getArena(getName()).getDouble("eventlobby.pitch")));
	                   			}	
                   			}
                   			StopFinishTimer();
                   			taskFinish = 0;
                   			finGame();
                   		}
            	temps = temps-1;
            }
         }, 0, 20);
	}
	
	public void StopFinishTimer(){
		Bukkit.getServer().getScheduler().cancelTask(taskFinish);
	}
	
	public void finGame(){
		
		if(this.event == false){
			for(int i = 0; i < getPlayerList().size(); i = i){
				BungeeCord.sendPlayerToHub(getPlayerList().get(0));
				Quite.leave(getPlayerList().get(0));
			}
		}else{
			for(int i = 0; i < getPlayerList().size(); i = i){
				Quite.leave(getPlayerList().get(0));
			}
		}
		
		Location redFlag = new Location(Bukkit.getWorld(FileControler.getArena(name).getString("world")), FileControler.getArena(name).getInt("redflag.x"), FileControler.getArena(name).getInt("redflag.y"), FileControler.getArena(name).getInt("redflag.z"));
		Location blueFlag = new Location(Bukkit.getWorld(FileControler.getArena(name).getString("world")), FileControler.getArena(name).getInt("blueflag.x"), FileControler.getArena(name).getInt("blueflag.y"), FileControler.getArena(name).getInt("blueflag.z"));
		
		redFlag.getBlock().setType(Material.STANDING_BANNER);
		CraftBanner banner = new CraftBanner(redFlag.getBlock());
		banner.setBaseColor(DyeColor.RED);
		org.bukkit.material.Banner banner2 = (org.bukkit.material.Banner) banner.getData();
		banner2.setFacingDirection(BlockFace.valueOf(FileControler.getArena(name).getString("redflag.direction")));
		banner.update();
			
		blueFlag.getBlock().setType(Material.STANDING_BANNER);
		CraftBanner bannerb = new CraftBanner(blueFlag.getBlock());
		bannerb.setBaseColor(DyeColor.BLUE);
		org.bukkit.material.Banner bannerb2 = (org.bukkit.material.Banner) bannerb.getData();
		bannerb2.setFacingDirection(BlockFace.valueOf(FileControler.getArena(name).getString("blueflag.direction")));
		bannerb.update();
		
		this.playerList.clear();
		this.status = "lobby";
		this.winner = null;
		this.temps = 0;
		this.taskLobby = 0;
		this.taskGame = 0;
		this.taskFinish = 0;
		getTeam("red").clear();
		getTeam("blue").clear();
		getClasse().clear();
		getScoreBoard().clearScoreBoard();
	}
}
