package com.trafalcraft.ctf.pannel;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.trafalcraft.ctf.file.FileControler;

public class InventoryClickListener implements Listener {
	
	@EventHandler
	public void onPlayerClickInventoryEvent(InventoryClickEvent e){
		
		if(e.getCurrentItem() != null){
			
			if(e.getInventory().getName().contains(";")){
				
				if(e.getInventory().getName().split(";")[2].equalsIgnoreCase("ctf")){
					
					if(e.getInventory().getName().split(";")[0].equalsIgnoreCase("arenalist")){
						
						if(e.getCurrentItem().getType() != Material.AIR){
							
							e.getWhoClicked().closeInventory();
							CreateOptionInventory.loadInventoryOption((Player)e.getWhoClicked(), e.getCurrentItem().getItemMeta().getDisplayName());
							
						}
					}else if(e.getInventory().getName().split(";")[1].equalsIgnoreCase("optionlist")){
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("teleportation")){
							
							e.getWhoClicked().closeInventory();
							CreateTeleportationInventory.loadInventoryteleportation((Player) e.getWhoClicked(), e.getInventory().getName().split(";")[0]);
						
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("information")){
							
							e.getWhoClicked().closeInventory();
							CreateInfoInventory.loadInventoryInfo((Player) e.getWhoClicked(), e.getInventory().getName().split(";")[0]);
							
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("back")){
							
							e.getWhoClicked().closeInventory();
							CreateAreneList.loadInventoryArena((Player) e.getWhoClicked());
						}
					}else if(e.getInventory().getName().split(";")[1].equalsIgnoreCase("tpInventory")){
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("spawn bleu")){
						
							Location loc = new Location(Bukkit.getWorld(FileControler.getArena(e.getInventory().getName().split(";")[0]).getString("world")), FileControler.getArena(e.getInventory().getName().split(";")[0]).getInt("bluespawn.x"), FileControler.getArena(e.getInventory().getName().split(";")[0]).getInt("bluespawn.y"), FileControler.getArena(e.getInventory().getName().split(";")[0]).getInt("bluespawn.z"), (float)FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("bluespawn.yaw"), (float)FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("bluespawn.pitch"));
							e.getWhoClicked().teleport(loc);
							
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("spawn rouge")){

							Location loc = new Location(Bukkit.getWorld(FileControler.getArena(e.getInventory().getName().split(";")[0]).getString("world")), FileControler.getArena(e.getInventory().getName().split(";")[0]).getInt("redspawn.x"), FileControler.getArena(e.getInventory().getName().split(";")[0]).getInt("redspawn.y"), FileControler.getArena(e.getInventory().getName().split(";")[0]).getInt("redspawn.z"), (float)FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("redspawn.yaw"), (float)FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("redspawn.pitch"));
							e.getWhoClicked().teleport(loc);
								
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("lobby rouge")){
							
							Location loc = new Location(Bukkit.getWorld(FileControler.getArena(e.getInventory().getName().split(";")[0]).getString("world")), FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("lobby.red.x"), FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("lobby.red.y"), FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("lobby.red.z"), (float)FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("lobby.red.yaw"), (float)FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("lobby.red.pitch"));
							e.getWhoClicked().teleport(loc);
							
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("lobby bleu")){
							
							Location loc = new Location(Bukkit.getWorld(FileControler.getArena(e.getInventory().getName().split(";")[0]).getString("world")), FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("lobby.blue.x"), FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("lobby.blue.y"), FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("lobby.blue.z"), (float)FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("lobby.blue.yaw"), (float)FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("lobby.blue.pitch"));
							e.getWhoClicked().teleport(loc);
							
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("lobby event")){
							
							Location loc = new Location(Bukkit.getWorld(FileControler.getArena(e.getInventory().getName().split(";")[0]).getString("world")), FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("eventlobby.x"), FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("eventlobby.y"), FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("eventlobby.z"), (float)FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("eventlobby.yaw"), (float)FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("eventlobby.pitch"));
							e.getWhoClicked().teleport(loc);
							
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("drapeau rouge")){
							
							Location loc = new Location(Bukkit.getWorld(FileControler.getArena(e.getInventory().getName().split(";")[0]).getString("world")), FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("redflag.x"), FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("redflag.y"), FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("redflag.z"), (float)FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("redflag.yaw"), (float)FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("redflag.pitch"));
							e.getWhoClicked().teleport(loc);
							
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("drapeau bleu")){
							
							Location loc = new Location(Bukkit.getWorld(FileControler.getArena(e.getInventory().getName().split(";")[0]).getString("world")), FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("blueflag.x"), FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("blueflag.y"), FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("blueflag.z"), (float)FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("blueflag.yaw"), (float)FileControler.getArena(e.getInventory().getName().split(";")[0]).getDouble("blueflag.pitch"));
							e.getWhoClicked().teleport(loc);
							
							
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("back")){
							
							e.getWhoClicked().closeInventory();
							CreateOptionInventory.loadInventoryOption((Player)e.getWhoClicked(), e.getInventory().getName().split(";")[0]);
							
						}
						
					}else if(e.getInventory().getName().split(";")[1].equalsIgnoreCase("infoInventory")){
						
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("back")){
							
							e.getWhoClicked().closeInventory();
							CreateOptionInventory.loadInventoryOption((Player)e.getWhoClicked(), e.getInventory().getName().split(";")[0]);
							
						}else{
							e.setCancelled(true);
						}
					}
				}
			}
		}
	}
	
}
