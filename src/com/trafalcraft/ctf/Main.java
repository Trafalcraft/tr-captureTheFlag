package com.trafalcraft.ctf;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.inventivetalent.bossbar.BossBarAPI;
import org.inventivetalent.bossbar.BossBarAPI.Color;
import org.inventivetalent.bossbar.BossBarAPI.Property;
import org.inventivetalent.bossbar.BossBarAPI.Style;

import com.trafalcraft.client.api.SocketApi;
import com.trafalcraft.ctf.Controler.ArenaControle;
import com.trafalcraft.ctf.Controler.PlayerControle;
import com.trafalcraft.ctf.file.FileControler;
import com.trafalcraft.ctf.pannel.CreateAreneList;
import com.trafalcraft.ctf.pannel.InventoryClickListener;
import com.trafalcraft.ctf.util.BungeeCord;

import net.md_5.bungee.api.chat.TextComponent;

public class Main extends JavaPlugin implements Listener{
	private static JavaPlugin plugin;
	
	public void onEnable(){
		
		plugin = this;
		getPlugin().getConfig().options().copyDefaults(true);
		
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(),this);
		Bukkit.getServer().getPluginManager().registerEvents(new InventoryClickListener(),this);
		
		SocketApi.registerPlugin("ctf", new com.trafalcraft.ctf.util.SocketPerso());
		
		//instance pour envoyé des messages
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		//instance pour recevoir des messages
		this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeCord());

		
		File d = new File(getPlugin().getDataFolder().getPath()+"//arene");
		
		if(!(d.exists())){
			d.mkdir();
			d.mkdirs();
		}
		
		FileControler.loadAllFile();
		
		for(String s : FileControler.getAllName()){
			if(FileControler.getArena(s).getString("status").equalsIgnoreCase("on")){
				if(SecureConfig.testConfig(s) == true){
					
					ArenaControle.addArene(s);
					Bukkit.getLogger().info("arene " + s + " créer");
					
				}else{
					Bukkit.getLogger().warning("l'arene " + s + " est mal configurér");
				}
			}
		}
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		
		if(command.getName().equalsIgnoreCase("ctf")){
			if(args.length != 0){
				if(args[0].equalsIgnoreCase("create")){
					if(p.isOp()){
						if(!(PlayerControle.contains(p.getName()))){
							if(args.length == 2){
								if(!(FileControler.contains(args[1]))){
									FileControler.addFile(getPlugin().getDataFolder(), args[1]);
									FileControler.getArena(args[1]).set("name", args[1]);
									FileControler.getArena(args[1]).set("world", p.getWorld().getName());
									FileControler.getArena(args[1]).set("status", "off");
									FileControler.saveArena(args[1]);
									p.sendMessage("§3§lCTF> §b" + "arène créée");
								}else{
									p.sendMessage("§3§lCTF> §b" + "cette arène existe déjà");
								}
							}else{
								p.sendMessage("§3§lCTF> §b" + "commande incorrecte");
							}
						}else{
							p.sendMessage("§3§lCTF> §b" + "vous ne pouvez pas configurer une arène pendant une game");
						}
					}else{
						p.sendMessage("§3§lCTF> §b" + "vous n'avez pas le droit d'utiliser cette commande");
					}
				
				
				}else if(FileControler.contains(args[0])){
					if(p.isOp()){
						if(!PlayerControle.contains(p.getName())){
							if(args.length == 3){
								if(args[1].equalsIgnoreCase("set")){
									if(args[2].equalsIgnoreCase("eventlobby")){
										
										FileControler.getArena(args[0]).set("eventlobby.x", p.getLocation().getX());
										FileControler.getArena(args[0]).set("eventlobby.y", p.getLocation().getY());
										FileControler.getArena(args[0]).set("eventlobby.z", p.getLocation().getZ());
										FileControler.getArena(args[0]).set("eventlobby.yaw", p.getLocation().getYaw());
										FileControler.getArena(args[0]).set("eventlobby.pitch", p.getLocation().getPitch());
										FileControler.saveArena(args[0]);
										
										p.sendMessage("§3§lCTF> §b" + "le lobby des events de l'arène " + args[0] + " a bien été enregistré");
									}else{
										p.sendMessage("argument " + args[2] + " invalide veuillez entrer lobby spawn ou eventlobby");
									}
								}else{
									p.sendMessage("§3§lCTF> §b" + "argument " + args[1] + " invalide veuillez entrer l'argument set");
								}
							}else if(args.length == 4){
								if(args[1].equalsIgnoreCase("set")){
									if(args[2].equalsIgnoreCase("spawn")){
										if(args[3].equalsIgnoreCase("red")){
													
											FileControler.getArena(args[0]).set("redspawn.x", p.getLocation().getX());
											FileControler.getArena(args[0]).set("redspawn.y", p.getLocation().getY());
											FileControler.getArena(args[0]).set("redspawn.z", p.getLocation().getZ());
											FileControler.getArena(args[0]).set("redspawn.yaw", p.getLocation().getYaw());
											FileControler.getArena(args[0]).set("redspawn.pitch", p.getLocation().getPitch());
											FileControler.saveArena(args[0]);
													
											p.sendMessage("§3§lCTF> §b" + "le spawn de l'equipe rouge dans l'arène " + args[0] + " a bien été enregistré");
										}else if(args[3].equalsIgnoreCase("blue")){
													
											FileControler.getArena(args[0]).set("bluespawn.x", p.getLocation().getX());
											FileControler.getArena(args[0]).set("bluespawn.y", p.getLocation().getY());
											FileControler.getArena(args[0]).set("bluespawn.z", p.getLocation().getZ());
											FileControler.getArena(args[0]).set("bluespawn.yaw", p.getLocation().getYaw());
											FileControler.getArena(args[0]).set("bluespawn.pitch", p.getLocation().getPitch());
											FileControler.saveArena(args[0]);
													
											p.sendMessage("§3§lCTF> §b" + "le spawn de l'équipe bleue dans l'arène " + args[0] + " a bien étè enregistré");
										}else{
											p.sendMessage("§3§lCTF> §b" + "argument " + args[3] + " invalide veuillez entrer blue ou red");
										}
									}else if(args[2].equalsIgnoreCase("lobby")){
										if(args[3].equalsIgnoreCase("red")){
											
											FileControler.getArena(args[0]).set("lobby.red.x", p.getLocation().getX());
											FileControler.getArena(args[0]).set("lobby.red.y", p.getLocation().getY());
											FileControler.getArena(args[0]).set("lobby.red.z", p.getLocation().getZ());
											FileControler.getArena(args[0]).set("lobby.red.yaw", p.getLocation().getYaw());
											FileControler.getArena(args[0]).set("lobby.red.pitch", p.getLocation().getPitch());
											FileControler.saveArena(args[0]);
													
											p.sendMessage("§3§lCTF> §b" + "le lobby rouge de l'arène " + args[0] + " a bien été enregistré");
											
										}else if(args[3].equalsIgnoreCase("blue")){
										
											FileControler.getArena(args[0]).set("lobby.blue.x", p.getLocation().getX());
											FileControler.getArena(args[0]).set("lobby.blue.y", p.getLocation().getY());
											FileControler.getArena(args[0]).set("lobby.blue.z", p.getLocation().getZ());
											FileControler.getArena(args[0]).set("lobby.blue.yaw", p.getLocation().getYaw());
											FileControler.getArena(args[0]).set("lobby.blue.pitch", p.getLocation().getPitch());
											FileControler.saveArena(args[0]);
													
											p.sendMessage("§3§lCTF> §b" + "le lobby bleu de l'arène " + args[0] + " a bien été enregistré");
										}else{
											p.sendMessage("§3§lCTF> §b" + "argument " + args[3] + " invalide veuillez entrer blue ou red");
										}
									}else if(args[2].equalsIgnoreCase("time")){
										try{
													
											Integer temps = Integer.valueOf(args[3]);
											FileControler.getArena(args[0]).set("temps", temps);
											FileControler.saveArena(args[0]);
											p.sendMessage("§3§lCTF> §b" + "temps maximum enregistré");
												
										}catch(NumberFormatException e){
											p.sendMessage("§3§lCTF> §b" + "vous devez entrer un nombre");
										}
									}else if(args[2].equalsIgnoreCase("maxplayer")){
										try{
													
											Integer max1 = Integer.valueOf(args[3]);
											FileControler.getArena(args[0]).set("maxplayer", max1);
											FileControler.saveArena(args[0]);
											p.sendMessage("§3§lCTF> §b" + "nombre de joueurs maximum par équipe enregistré");
												
										}catch(NumberFormatException e){
											p.sendMessage("§3§lCTF> §b" + "vous devez entrer un nombre");
										}
									}else if(args[2].equalsIgnoreCase("status")){
										if(args[3].equalsIgnoreCase("on") || args[3].equalsIgnoreCase("off")){
													
												FileControler.getArena(args[0]).set("status", args[3]);
												FileControler.saveArena(args[0]);
													
												ArenaControle.addArene(args[0]);
														
												p.sendMessage("§3§lCTF> §b" + "status de l'arène " + args[0] + " enregistrer");
												
										}else{
											p.sendMessage("§3§lCTF> §b" + "vous devez entrer on ou off");
										}
									}else if(args[2].equalsIgnoreCase("flag")){
										if(args[3].equalsIgnoreCase("red")){
													
											FileControler.getArena(args[0]).set("redflag.x", p.getLocation().getBlockX());
											FileControler.getArena(args[0]).set("redflag.y", p.getLocation().getBlockY());
											FileControler.getArena(args[0]).set("redflag.z", p.getLocation().getBlockZ());
											
											final String[] directions = new String[] {"SOUTH", "WEST", "NORTH", "EAST"};

											double par0 = (double)(p.getLocation().getYaw() /* Le yaw du player*/  * 4.0F / 360.0F) + 0.5D; 
											// Parce qu'on aime les maths
											int var2 = (int)par0; // Cast de comparaison pour faire un Math.floor
											int directionInt= par0 < (double)var2 ? var2 - 1 : var2; // Equivalent d'un Math.floor double
											directionInt = directionInt &3;

											String dirStr  = directions[directionInt];
											
											FileControler.getArena(args[0]).set("redflag.direction", dirStr);
											FileControler.saveArena(args[0]);
													
											p.sendMessage("§3§lCTF> §b" + "le flag de l'équipe rouge dans l'arène " + args[0] + " a bien été enregistré");
													
										}else if(args[3].equalsIgnoreCase("blue")){
													
											FileControler.getArena(args[0]).set("blueflag.x", p.getLocation().getBlockX());
											FileControler.getArena(args[0]).set("blueflag.y", p.getLocation().getBlockY());
											FileControler.getArena(args[0]).set("blueflag.z", p.getLocation().getBlockZ());
											
											final String[] directions = new String[] {"SOUTH", "WEST", "NORTH", "EAST"};

											double par0 = (double)(p.getLocation().getYaw() /* Le yaw du player*/  * 4.0F / 360.0F) + 0.5D; 
											// Parce qu'on aime les maths
											int var2 = (int)par0; // Cast de comparaison pour faire un Math.floor
											int directionInt= par0 < (double)var2 ? var2 - 1 : var2; // Equivalent d'un Math.floor double
											directionInt = directionInt &3;

											String dirStr  = directions[directionInt];
											
											FileControler.getArena(args[0]).set("blueflag.direction", dirStr);
											
											FileControler.saveArena(args[0]);
													
											p.sendMessage("§3§lCTF> §b" + "le flag de l'équipe bleue dans l'arène " + args[0] + " a bien été enregistré");
													
										}else{
											p.sendMessage("§3§lCTF> §b" + "argument " + args[3] + " invalide veuillez entrer blue ou red");
										}
									}else if(args[2].equalsIgnoreCase("maxpoint")){
										try{
													
											Integer max1 = Integer.valueOf(args[3]);
											FileControler.getArena(args[0]).set("maxpoint", max1);
											FileControler.saveArena(args[0]);
											p.sendMessage("§3§lCTF> §b" + "le nombre de points nécessaires pour gagner de l'arène " + args[0] + " a bien étè enregistré");
												
										}catch(NumberFormatException e){
											p.sendMessage("§3§lCTF> §b" + "vous devez entrer un nombre");
										}
									}else if(args[2].equalsIgnoreCase("friendlyfire")){
										if(args[3].equalsIgnoreCase("true") || args[3].equalsIgnoreCase("false")){
													
											FileControler.getArena(args[0]).set("friendlyfire", args[3]);
											FileControler.saveArena(args[0]);
													
											p.sendMessage("§3§lCTF> §b" + "activation du friendly fire de l'arène " + args[0] + " enregistrer");
										}else{
											p.sendMessage("§3§lCTF> §b" + "argument " + args[3] + " invalide veuillez rentrer true ou false");
										}
									}else{
										p.sendMessage("§3§lCTF> §b" + "argument " + args[2] + " invalide veuillez rentrer spawn, time, maxplayer ou status");
									}
								
								}else{
										
									p.sendMessage("§3§lCTF> §b" + "argument " + args[1] + " invalide veuiller entrer l'argument set ou delete");
								}
										
							}else{
								p.sendMessage("§3§lCTF> §b" + "la commande est incomplète");
							}
						}else{
							p.sendMessage("§3§lCTF> §b" + "il est impossible de configurer une arène pendant que vous êtes en jeu");
						}
					}else{
						p.sendMessage("§3§lCTF> §b" + "vous n'avez pas l'autorisation d'utiliser cette commande");
					}
				}else if(args[0].equalsIgnoreCase("join")){
					if(args.length == 3){
						if(ArenaControle.contains(args[1])){
							if(args[2].equalsIgnoreCase("red") || args[2].equalsIgnoreCase("blue")){
								PlayerControle.addPlayer(p.getName(), args[1], args[2]);
							}else{
								p.sendMessage("§3§lCTF> §b" + "cette équipe n'existe pas veuillez entrer red ou blue");
							}
						}else{
							p.sendMessage("§3§lCTF> §b" + "cette arène n'existe pas");
						}
					}else if(args.length == 2){
						if(ArenaControle.contains(args[1])){
							PlayerControle.addPlayer(p.getName(), args[1], RandowmTeam.chooseTeam(args[1]));
						}else{
							p.sendMessage("§3§lCTF> §b" + "cette arène n'existe pas");
						}
					}else{
						p.sendMessage("§3§lCTF> §b" + "commande incomplète");
					}
				}else if(args[0].equalsIgnoreCase("leave")){
					BungeeCord.sendPlayerToHub(p);
				}else if(args[0].equalsIgnoreCase("fstart")){
					if(PlayerControle.contains(p.getName())){
						
						String arene = PlayerControle.getJoueur(p.getName()).getArene();
						
						if(ArenaControle.getArena(arene).getStatus().equalsIgnoreCase("lobby")){
							if(ArenaControle.getArena(arene).getTaskLobby() != 0){
								
								ArenaControle.getArena(arene).setTemps(5);
								
								for(Player pl : ArenaControle.getArena(arene).getPlayerList()){
									BossBarAPI.removeAllBars(pl);
									BossBarAPI.addBar(pl, new TextComponent("§b" + "capture de drapeau"), Color.BLUE, Style.PROGRESS, (float)5/30, 100, 1, Property.PLAY_MUSIC);
								}
							
							}else{
								p.sendMessage("§3§lCTF> §b" + "le temps avant le lancement de la partie n'a pas encore commencé");
							}
						}else{
							p.sendMessage("§3§lCTF> §b" + "la partie de l'arène " + arene + " est déjà en cours");
						}
					}else{
						p.sendMessage("§3§lCTF> §b" + "vous n'êtes dans aucune arène vous ne pouvez donc pas utiliser cette commande");
					}
				}else if(args[0].equalsIgnoreCase("start")){
					if(args.length == 3 || args.length == 5){
						if(args[1].equalsIgnoreCase("event")){
							if(ArenaControle.contains(args[2])){
								if(args.length == 5){
									if(args[3].equalsIgnoreCase("admin")){
										if(args[4].equalsIgnoreCase("on")){
											
											ArenaControle.getArena(args[2]).setEvent(true);
											
											for(Player pl : Bukkit.getServer().getOnlinePlayers()){
												PlayerControle.addPlayer(pl.getName(), args[2], RandowmTeam.chooseTeam(args[2]));
											}
											
										}else if(args[4].equalsIgnoreCase("off")){
											
											ArenaControle.getArena(args[2]).setEvent(true);
											
											for(Player pl : Bukkit.getServer().getOnlinePlayers()){
												if(!pl.isOp()){
													PlayerControle.addPlayer(pl.getName(), args[2], RandowmTeam.chooseTeam(args[2]));
												}
											}
											
										}else{
											p.sendMessage("§3§lCTF> §b" + "argument " + args[4] + " invalide veuillez entrer on ou off");
										}
									}else{
										p.sendMessage("§3§lCTF> §b" + "argument " + args[3] + " invalide veuillez entrer admin");
									}
								}else{
									ArenaControle.getArena(args[2]).setEvent(true);
									
									for(Player pl : Bukkit.getServer().getOnlinePlayers()){
										PlayerControle.addPlayer(pl.getName(), args[2], RandowmTeam.chooseTeam(args[2]));
									}
								}
							}else{
								p.sendMessage("§3§lCTF> §b" + "cette arène n'existe pas ou est mal configurée");
							}
						}else{
							p.sendMessage("§3§lCTF> §b" + "argument " + args[1] + " invalide veuillez entrer event");
						}
					}else{
						p.sendMessage("§3§lCTF> §b" + "la commande est incomplète");
					}
				}else if(args[0].equalsIgnoreCase("pannel")){
					if(p.isOp()){
						CreateAreneList.loadInventoryArena(p);
					}else{
						p.sendMessage("§3§lCTF> §b" + "vous n'avez pas le droit d'utiliser cette commande");
					}
				}else{
					p.sendMessage("§3§lCTF> §b" + "argument " + args[0] + " invalide veuillez entrez create, <nom d'une aréne>, join, leave, pannel ou start");
				}
			}else{
				p.sendMessage("§3§lCTF> §b" + "la commande est incomplète");
			}
		}
		return false;
	}
	
	public static JavaPlugin getPlugin(){
		return plugin;
	}
}
