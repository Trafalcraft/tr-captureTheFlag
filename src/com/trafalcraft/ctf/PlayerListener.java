package com.trafalcraft.ctf;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_10_R1.block.CraftBanner;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import com.trafalcraft.ctf.Controler.ArenaControle;
import com.trafalcraft.ctf.Controler.PlayerControle;
import com.trafalcraft.ctf.file.FileControler;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerSendMessageEvent(AsyncPlayerChatEvent e){
		String Message = e.getMessage();
		e.setCancelled(true);

		Message = ("§3§lCTF> §b" + e.getPlayer().getDisplayName() + ">> " + Message);
		Bukkit.getServer().broadcastMessage(Message);
	}
	
	@EventHandler
	public void OnplayerInteractEvent(PlayerInteractEvent e){
		if(PlayerControle.contains(e.getPlayer().getName())){
			
			String arene = PlayerControle.getJoueur(e.getPlayer().getName()).getArene();
			
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
				
				if(e.getClickedBlock().getType() == Material.WALL_SIGN || e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST){
					
					if(((Sign)e.getClickedBlock().getState()).getLine(0).equalsIgnoreCase("ctf-classe")){
						
						if(((Sign)e.getClickedBlock().getState()).getLine(2) != null){
							
							ArenaControle.getArena(arene).getClasse().addChestClass(e.getPlayer(), e.getClickedBlock().getLocation(), ((Sign)e.getClickedBlock().getState()).getLine(2));
							PlayerControle.getJoueur(e.getPlayer().getName()).setClasse(((Sign)e.getClickedBlock().getState()).getLine(2));
						}
					}						
				}
			}else if(e.getAction() == Action.LEFT_CLICK_BLOCK){
				e.setCancelled(true);
			}
		}
	}
	
	
	@EventHandler
	public void OnBreackBlockEvent(BlockBreakEvent e){
		if(e.getPlayer().isOp()){
			if(PlayerControle.contains(e.getPlayer().getName())){
				e.setCancelled(true);
			}
		}else{
			e.setCancelled(true);
		}
	}
	
	
	@EventHandler
	public void onMob(EntitySpawnEvent e){
		e.setCancelled(true);
	}
	
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e){
		
		if(e.getPlayer().isOp() == true){
			
			if(PlayerControle.contains(e.getPlayer().getName())){
				
				e.setCancelled(true);
				
			}
			
		}else{
			e.setCancelled(true);
		}
	}
	
	
	@EventHandler
	public void OnblockPlaceEvent(BlockPlaceEvent e){
		if(e.getPlayer().isOp()){
			if(PlayerControle.contains(e.getPlayer().getName())){
				e.setCancelled(true);
			}
		}else{
			e.setCancelled(true);
		}
	}
	
	
	@EventHandler
	public void onPlayerpickEvent(PlayerPickupItemEvent e){
		if(e.getPlayer().isOp()){
			if(PlayerControle.contains(e.getPlayer().getName())){
				e.setCancelled(true);
			}
		}else{
			e.setCancelled(true);
		}
	}
	
	
	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent e){
		
		if(e.getCause() != DamageCause.ENTITY_ATTACK && e.getCause() != DamageCause.PROJECTILE){
			
			if(e.getEntity().getType() == EntityType.PLAYER){
				
				if(PlayerControle.contains(((Player)e.getEntity()).getName())){
					
					String arene = PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getArene();
					
					if(PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getInGame() == true){
						
						if((((Player)e.getEntity()).getHealth()) - (e.getFinalDamage()) <= 0){
							
							e.setCancelled(true);
							((Player)e.getEntity()).setHealth(20);
							((Player)e.getEntity()).setFoodLevel(20);
							((Player)e.getEntity()).setSaturation(0);
							ClearPotion.clearEffect(((Player)e.getEntity()));
							Tp.teleport((Player)e.getEntity());
							
							if(PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getHasFlag() == true){
								
								String team = PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getTeam();
								
								PlayerControle.getJoueur(((Player)e.getEntity()).getName()).setHasFlag(false);
								ArenaControle.getArena(arene).getTeam("-" + team).setFlagAtHome(true);
								
								String autreTeam;
								DyeColor color;
								
								if(team.equalsIgnoreCase("red")){
									autreTeam = "blue";
									color = DyeColor.BLUE;
								}else{
									autreTeam = "red";
									color = DyeColor.RED;
								}
								
								Location loc = new Location(Bukkit.getWorld(FileControler.getArena(arene).getString("world")), FileControler.getArena(arene).getInt(autreTeam + "flag.x"),  FileControler.getArena(arene).getInt(autreTeam + "flag.y"),  FileControler.getArena(arene).getInt(autreTeam + "flag.z"));
								
								loc.getBlock().setType(Material.STANDING_BANNER);
	                   			CraftBanner banner = new CraftBanner(loc.getBlock());
	                   			banner.setBaseColor(color);
	                   			org.bukkit.material.Banner banner2 = (org.bukkit.material.Banner) banner.getData();
	                   			banner2.setFacingDirection(BlockFace.valueOf(FileControler.getArena(arene).getString(autreTeam + "flag.direction")));
	                   			banner.update();
							}
							
							if(e.getCause() == DamageCause.FALL){
								
								for(Player p : ArenaControle.getArena(arene).getPlayerList()){
									p.sendMessage("§3§lCTF> §b" + ((Player)e.getEntity()).getName() + " est mort(e) de chute");
								}
							}else if(e.getCause() == DamageCause.FIRE || e.getCause() == DamageCause.FIRE_TICK){
								
								for(Player p : ArenaControle.getArena(arene).getPlayerList()){
									p.sendMessage("§3§lCTF> §b" + ((Player)e.getEntity()).getName() + " est mort(e) brulé(e)");
								}
							}else if(e.getCause() == DamageCause.LAVA){
								
								for(Player p : ArenaControle.getArena(arene).getPlayerList()){
									p.sendMessage("§3§lCTF> §b" + ((Player)e.getEntity()).getName() + " est mort(e) dans la lave,");
								}
							}else if(e.getCause() == DamageCause.DROWNING){
								
								for(Player p : ArenaControle.getArena(arene).getPlayerList()){
									p.sendMessage("§3§lCTF> §b" + ((Player)e.getEntity()).getName() + " est mort(e) noyé(e)");
								}
							}else if(e.getCause() == DamageCause.POISON){
								
								for(Player p : ArenaControle.getArena(arene).getPlayerList()){
									p.sendMessage("§3§lCTF> §b" + ((Player)e.getEntity()).getName() + " est mort(e) empoisonné(e)");
								}
							}else if(e.getCause() != DamageCause.ENTITY_ATTACK && e.getCause() != DamageCause.PROJECTILE){
								
								for(Player p : ArenaControle.getArena(arene).getPlayerList()){
									p.sendMessage("§3§lCTF> §b" + ((Player)e.getEntity()).getName() + " est mort(e)");
								}
							}
						}
					}else{
						e.setCancelled(true);
					}
				}
			}else{
				e.setCancelled(true);
			}
		}
	}
	
	
	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e){
		
		if(e.getEntity().getType() == EntityType.PLAYER){
			
			if(PlayerControle.contains(((Player)e.getEntity()).getName())){
				
				if(e.getDamager().getType() == EntityType.PLAYER){
					
					if(PlayerControle.contains(((Player)e.getDamager()).getName())){
						
						String arene = PlayerControle.getJoueur(((Player)e.getDamager()).getName()).getArene();
						
						if(PlayerControle.getJoueur(((Player)e.getDamager()).getName()).getInGame() == true){
							
							if(PlayerControle.getJoueur(((Player)e.getDamager()).getName()).getArene().equalsIgnoreCase(PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getArene())){
								
								boolean friendly = FileControler.getArena(arene).getBoolean("friendlyfire");
								
								if((!PlayerControle.getJoueur(((Player)e.getDamager()).getName()).getTeam().equalsIgnoreCase(PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getTeam())) || friendly == true ){
									
									if((((Player)e.getEntity()).getHealth()) - (e.getFinalDamage()) <= 0){
										
										e.setCancelled(true);
										((Player)e.getEntity()).setHealth(20);
										((Player)e.getEntity()).setFoodLevel(20);
										((Player)e.getEntity()).setSaturation(0);
										ClearPotion.clearEffect(((Player)e.getEntity()));
										Tp.teleport((Player)e.getEntity());
										
										if(PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getHasFlag() == true){
											
											String team = PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getTeam();
											
											PlayerControle.getJoueur(((Player)e.getEntity()).getName()).setHasFlag(false);
											ArenaControle.getArena(arene).getTeam("-" + team).setFlagAtHome(true);
											
											String autreTeam;
											DyeColor color;
											
											if(team.equalsIgnoreCase("red")){
												autreTeam = "blue";
												color = DyeColor.BLUE;
											}else{
												autreTeam = "red";
												color = DyeColor.RED;
											}
											
											Location loc = new Location(Bukkit.getWorld(FileControler.getArena(arene).getString("world")), FileControler.getArena(arene).getInt(autreTeam + "flag.x"),  FileControler.getArena(arene).getInt(autreTeam + "flag.y"),  FileControler.getArena(arene).getInt(autreTeam + "flag.z"));
											
											loc.getBlock().setType(Material.STANDING_BANNER);
				                   			CraftBanner banner = new CraftBanner(loc.getBlock());
				                   			banner.setBaseColor(color);
				                   			org.bukkit.material.Banner banner2 = (org.bukkit.material.Banner) banner.getData();
				                   			banner2.setFacingDirection(BlockFace.valueOf(FileControler.getArena(arene).getString(autreTeam + "flag.direction")));
				                   			banner.update();
										}
										
										for(Player p : ArenaControle.getArena(arene).getPlayerList()){
											p.sendMessage("§3§lCTF> §b" + ((Player)e.getEntity()).getName() + " a étè tué(e) par " + ((Player)e.getDamager()).getName());
										}
										
									}
								}else{
									e.setCancelled(true);
								}
							}else{
								e.setCancelled(true);
							}
						}else{
							e.setCancelled(true);
						}
					}else{
						e.setCancelled(true);
					}
				}else if(e.getDamager().getType() == EntityType.ARROW || e.getDamager().getType() == EntityType.SPECTRAL_ARROW || e.getDamager().getType() == EntityType.TIPPED_ARROW ){
					
					if(PlayerControle.contains(((Player)((Arrow)e.getDamager()).getShooter()).getName())){
						
						String arene = PlayerControle.getJoueur(((Player)((Arrow)e.getDamager()).getShooter()).getName()).getArene();
						
						if(PlayerControle.getJoueur(((Player)((Arrow)e.getDamager()).getShooter()).getName()).getInGame() == true){
							
							if(PlayerControle.getJoueur(((Player)((Arrow)e.getDamager()).getShooter()).getName()).getArene().equalsIgnoreCase(PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getArene())){
								
								boolean friendly = FileControler.getArena(arene).getBoolean("friendlyfire");
								
								if((!PlayerControle.getJoueur(((Player)((Arrow)e.getDamager()).getShooter()).getName()).getTeam().equalsIgnoreCase(PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getTeam())) || friendly == true){
									
									if((((Player)e.getEntity()).getHealth()) - (e.getFinalDamage()) <= 0){
										
										e.setCancelled(true);
										((Player)e.getEntity()).setHealth(20);
										((Player)e.getEntity()).setFoodLevel(20);
										((Player)e.getEntity()).setSaturation(0);
										ClearPotion.clearEffect(((Player)e.getEntity()));
										Tp.teleport((Player)e.getEntity());
										
										if(PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getHasFlag() == true){
											
											String team = PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getTeam();
											
											PlayerControle.getJoueur(((Player)e.getEntity()).getName()).setHasFlag(false);
											ArenaControle.getArena(arene).getTeam("-" + team).setFlagAtHome(true);
											
											String autreTeam;
											DyeColor color;
											
											if(team.equalsIgnoreCase("red")){
												autreTeam = "blue";
												color = DyeColor.BLUE;
											}else{
												autreTeam = "red";
												color = DyeColor.RED;
											}
											
											Location loc = new Location(Bukkit.getWorld(FileControler.getArena(arene).getString("world")), FileControler.getArena(arene).getInt(autreTeam + "flag.x"),  FileControler.getArena(arene).getInt(autreTeam + "flag.y"),  FileControler.getArena(arene).getInt(autreTeam + "flag.z"));
											
											loc.getBlock().setType(Material.STANDING_BANNER);
				                   			CraftBanner banner = new CraftBanner(loc.getBlock());
				                   			banner.setBaseColor(color);
				                   			org.bukkit.material.Banner banner2 = (org.bukkit.material.Banner) banner.getData();
				                   			banner2.setFacingDirection(BlockFace.valueOf(FileControler.getArena(arene).getString(autreTeam + "flag.direction")));
				                   			banner.update();
										}
										
										for(Player p : ArenaControle.getArena(arene).getPlayerList()){
											p.sendMessage("§3§lCTF> §b" + ((Player)e.getEntity()).getName() + " a été tué(e) par " + ((Player)((Arrow)e.getDamager()).getShooter()).getName());
										}
										
									}
								}else{
									e.setCancelled(true);
								}
							}else{
								e.setCancelled(true);
							}
						}else{
							e.setCancelled(true);
						}
					}else{
						e.setCancelled(true);
					}
				}
			}
		}else{
			e.setCancelled(true);
		}
	}
	
	
	@EventHandler
	public void onQuite(PlayerQuitEvent e){
		Quite.leave(e.getPlayer());
	}
	
	@EventHandler
	public void OnPlayerDeathEvent(PlayerDeathEvent e){
		e.setDeathMessage("");
	}
	
	@EventHandler
	public void onPlayerMooveEvent(PlayerMoveEvent e){
		
		if(PlayerControle.contains(e.getPlayer().getName())){
			
			String arene = PlayerControle.getJoueur(e.getPlayer().getName()).getArene();
			String team = PlayerControle.getJoueur(e.getPlayer().getName()).getTeam();
			if(ArenaControle.getArena(arene).getStatus().equalsIgnoreCase("in-game")){
				
					Location redFlag = new Location(Bukkit.getWorld(FileControler.getArena(arene).getString("world")), FileControler.getArena(arene).getInt("redflag.x"), FileControler.getArena(arene).getInt("redflag.y"), FileControler.getArena(arene).getInt("redflag.z"));
					Location blueFlag = new Location(Bukkit.getWorld(FileControler.getArena(arene).getString("world")), FileControler.getArena(arene).getInt("blueflag.x"), FileControler.getArena(arene).getInt("blueflag.y"), FileControler.getArena(arene).getInt("blueflag.z"));
					
					
					if(team.equalsIgnoreCase("red")){
						
						if(e.getPlayer().getLocation().getBlockX() == FileControler.getArena(arene).getInt("redflag.x") && e.getPlayer().getLocation().getBlockY() == FileControler.getArena(arene).getInt("redflag.y") && e.getPlayer().getLocation().getBlockZ() == FileControler.getArena(arene).getInt("redflag.z")){
							
							if(PlayerControle.getJoueur(e.getPlayer().getName()).getHasFlag() == true){
							
								
								if(ArenaControle.getArena(arene).getTeam("red").getFlagAtHome() == true){
									
									PlayerControle.getJoueur(e.getPlayer().getName()).setHasFlag(false);
									ArenaControle.getArena(arene).getTeam("blue").setFlagAtHome(true);
								
									blueFlag.getBlock().setType(Material.STANDING_BANNER);
									CraftBanner bannerb = new CraftBanner(blueFlag.getBlock());
									bannerb.setBaseColor(DyeColor.BLUE);
									org.bukkit.material.Banner bannerb2 = (org.bukkit.material.Banner) bannerb.getData();
									bannerb2.setFacingDirection(BlockFace.valueOf(FileControler.getArena(arene).getString("blueflag.direction")));
									bannerb.update();
									
									ArenaControle.getArena(arene).getClasse().loadChestClass(e.getPlayer(), PlayerControle.getJoueur(e.getPlayer().getName()).getClasse());
									ArenaControle.getArena(arene).getTeam("red").setNbrPoint(ArenaControle.getArena(arene).getTeam("red").getNbrPoint() + 1);
									ArenaControle.getArena(arene).getScoreBoard().updateScore("red", ArenaControle.getArena(arene).getName());
									
									for(Player p : ArenaControle.getArena(arene).getPlayerList()){
										p.sendMessage("§3§lCTF> §b" + "l'équipe rouge a marqué un point");
									}
									
									for(Player p : ArenaControle.getArena(arene).getTeam("red").getPlayerList()){
										p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 50.0F, 1.0F);
									}
									
									for(Player p : ArenaControle.getArena(arene).getTeam("blue").getPlayerList()){
										p.playSound(p.getLocation(), Sound.ENTITY_HORSE_DEATH, 50.0F, 1.0F);
									}
									
									if(ArenaControle.getArena(arene).getTeam("red").getNbrPoint() == FileControler.getArena(arene).getInt("maxpoint")){
										ArenaControle.getArena(arene).stopGameTimer();
										ArenaControle.getArena(arene).WhoWin();
										ArenaControle.getArena(arene).setStatus("end");
										
										for(Player p : ArenaControle.getArena(arene).getPlayerList()){
											PlayerControle.getJoueur(p.getName()).setInGame(false);
										}
									}
									
								}
							}
						}else if(e.getPlayer().getLocation().getBlockX() == FileControler.getArena(arene).getInt("blueflag.x") && e.getPlayer().getLocation().getBlockY() == FileControler.getArena(arene).getInt("blueflag.y") && e.getPlayer().getLocation().getBlockZ() == FileControler.getArena(arene).getInt("blueflag.z")){
							
							if(ArenaControle.getArena(arene).getTeam("blue").getFlagAtHome() == true){
								
								ArenaControle.getArena(arene).getTeam("blue").setFlagAtHome(false);
								PlayerControle.getJoueur(e.getPlayer().getName()).setHasFlag(true);
								ItemStack item = new ItemStack(Material.BANNER);
								BannerMeta bmeta = (BannerMeta) item.getItemMeta();
								//bmeta.addPattern(new Pattern(DyeColor.BLUE , PatternType.HALF_HORIZONTAL));
								bmeta.setBaseColor(DyeColor.BLUE );
								item.setItemMeta(bmeta);
								e.getPlayer().getInventory().setHelmet(item);
								//e.getClickedBlock().setTypeIdAndData(Material.WOOL.getId(), DyeColor.WHITE.getWoolData(), true);
								blueFlag.getBlock().setType(Material.AIR);
							}
						}
					}else{
						
						if(e.getPlayer().getLocation().getBlockX() == FileControler.getArena(arene).getInt("blueflag.x") && e.getPlayer().getLocation().getBlockY() == FileControler.getArena(arene).getInt("blueflag.y") && e.getPlayer().getLocation().getBlockZ() == FileControler.getArena(arene).getInt("blueflag.z")){
							
							
							if(PlayerControle.getJoueur(e.getPlayer().getName()).getHasFlag() == true){
							
								if(ArenaControle.getArena(arene).getTeam("blue").getFlagAtHome() == true){
									
									PlayerControle.getJoueur(e.getPlayer().getName()).setHasFlag(false);
									ArenaControle.getArena(arene).getTeam("red").setFlagAtHome(true);
									
									redFlag.getBlock().setType(Material.STANDING_BANNER);
		                   			CraftBanner banner = new CraftBanner(redFlag.getBlock());
		                   			banner.setBaseColor(DyeColor.RED);
		                   			org.bukkit.material.Banner banner2 = (org.bukkit.material.Banner) banner.getData();
		                   			banner2.setFacingDirection(BlockFace.valueOf(FileControler.getArena(arene).getString("redflag.direction")));
		                   			banner.update();
									
									ArenaControle.getArena(arene).getClasse().loadChestClass(e.getPlayer(), PlayerControle.getJoueur(e.getPlayer().getName()).getClasse());
									ArenaControle.getArena(arene).getTeam("blue").setNbrPoint(ArenaControle.getArena(arene).getTeam("blue").getNbrPoint() + 1);
									ArenaControle.getArena(arene).getScoreBoard().updateScore("blue", ArenaControle.getArena(arene).getName());
									
									for(Player p : ArenaControle.getArena(arene).getPlayerList()){
										p.sendMessage("§3§lCTF> §b" + "l'équipe bleue a marqué un point");
									}
									
									for(Player p : ArenaControle.getArena(arene).getTeam("blue").getPlayerList()){
										p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 50.0F, 1.0F);
									}
									
									for(Player p : ArenaControle.getArena(arene).getTeam("red").getPlayerList()){
										p.playSound(p.getLocation(), Sound.ENTITY_HORSE_DEATH, 50.0F, 1.0F);
									}
									
									if(ArenaControle.getArena(arene).getTeam("blue").getNbrPoint() == FileControler.getArena(arene).getInt("maxpoint")){
										ArenaControle.getArena(arene).stopGameTimer();
										ArenaControle.getArena(arene).WhoWin();
										ArenaControle.getArena(arene).setStatus("end");
										
										for(Player p : ArenaControle.getArena(arene).getPlayerList()){
											PlayerControle.getJoueur(p.getName()).setInGame(false);
										}
									}
									
								}
							}
						}else if(e.getPlayer().getLocation().getBlockX() == FileControler.getArena(arene).getInt("redflag.x") && e.getPlayer().getLocation().getBlockY() == FileControler.getArena(arene).getInt("redflag.y") && e.getPlayer().getLocation().getBlockZ() == FileControler.getArena(arene).getInt("redflag.z")){
							
							if(ArenaControle.getArena(arene).getTeam("red").getFlagAtHome() == true){
								
								ArenaControle.getArena(arene).getTeam("red").setFlagAtHome(false);
								PlayerControle.getJoueur(e.getPlayer().getName()).setHasFlag(true);
								ItemStack item = new ItemStack(Material.BANNER);
								BannerMeta bmeta = (BannerMeta) item.getItemMeta();
								
								bmeta.setBaseColor(DyeColor.RED);
								item.setItemMeta(bmeta);
								e.getPlayer().getInventory().setHelmet(item);
								//e.getClickedBlock().setTypeIdAndData(Material.WOOL.getId(), DyeColor.WHITE.getWoolData(), true);
								redFlag.getBlock().setType(Material.AIR);
							}
						}
					}
			}
		}
	}
}
