package com.trafalcraft.ctf.pannel;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.trafalcraft.ctf.file.FileControler;

public class CreateTeleportationInventory {
	
	public static void loadInventoryteleportation(Player p, String arene){
		
		Inventory inv = Bukkit.createInventory(p, 36, arene + ";tpInventory;ctf");
		
		if(FileControler.getArena(arene).contains("bluespawn.x")){
			
			ItemStack item = new ItemStack(Material.ENDER_PEARL);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("spawn bleu");
			
			List<String> lore = new ArrayList<String>();
			lore.add(FileControler.getArena(arene).getInt("bluespawn.x") + "");
			lore.add(FileControler.getArena(arene).getInt("bluespawn.y") + "");
			lore.add(FileControler.getArena(arene).getInt("bluespawn.z") + "");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(10, item);
			
		}else{
			
			ItemStack item = new ItemStack(Material.BARRIER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("spawn bleu");
			
			List<String> lore = new ArrayList<String>();
			lore.add("information non configurée");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(10, item);
		}
		
		if(FileControler.getArena(arene).contains("redspawn.x")){
			
			ItemStack item = new ItemStack(Material.ENDER_PEARL);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("spawn rouge");
			
			List<String> lore = new ArrayList<String>();
			lore.add(FileControler.getArena(arene).getInt("redspawn.x") + "");
			lore.add(FileControler.getArena(arene).getInt("redspawn.y") + "");
			lore.add(FileControler.getArena(arene).getInt("redspawn.z") + "");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(12, item);
			
		}else{
			
			ItemStack item = new ItemStack(Material.BARRIER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("spawn rouge");
			
			List<String> lore = new ArrayList<String>();
			lore.add("information non configurée");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(12, item);
		}
		
		if(FileControler.getArena(arene).contains("lobby.red.x")){
			
			ItemStack item = new ItemStack(Material.ENDER_PEARL);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("lobby rouge");
			
			List<String> lore = new ArrayList<String>();
			lore.add(FileControler.getArena(arene).getInt("lobby.red.x") + "");
			lore.add(FileControler.getArena(arene).getInt("lobby.red.y") + "");
			lore.add(FileControler.getArena(arene).getInt("lobby.red.z") + "");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(14, item);
			
		}else{
			
			ItemStack item = new ItemStack(Material.BARRIER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("lobby rouge");
			
			List<String> lore = new ArrayList<String>();
			lore.add("information non configurée");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(14, item);
		}
		
		if(FileControler.getArena(arene).contains("lobby.blue.x")){
			
			ItemStack item = new ItemStack(Material.ENDER_PEARL);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("lobby bleu");
			
			List<String> lore = new ArrayList<String>();
			lore.add(FileControler.getArena(arene).getInt("lobby.blue.x") + "");
			lore.add(FileControler.getArena(arene).getInt("lobby.blue.y") + "");
			lore.add(FileControler.getArena(arene).getInt("lobby.blue.z") + "");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(16, item);
			
		}else{
			
			ItemStack item = new ItemStack(Material.BARRIER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("lobby bleu");
			
			List<String> lore = new ArrayList<String>();
			lore.add("information non configurée");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(16, item);
		}
		
		if(FileControler.getArena(arene).contains("eventlobby.x")){
			
			ItemStack item = new ItemStack(Material.ENDER_PEARL);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("lobby event");
			
			List<String> lore = new ArrayList<String>();
			lore.add(FileControler.getArena(arene).getInt("eventlobby.x") + "");
			lore.add(FileControler.getArena(arene).getInt("eventlobby.y") + "");
			lore.add(FileControler.getArena(arene).getInt("eventlobby.z") + "");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(20, item);
			
		}else{
			
			ItemStack item = new ItemStack(Material.BARRIER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("lobby event");
			
			List<String> lore = new ArrayList<String>();
			lore.add("information non configurée");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(20, item);
		}
		
		if(FileControler.getArena(arene).contains("redflag.x")){
			
			ItemStack item = new ItemStack(Material.ENDER_PEARL);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("drapeau rouge");
			
			List<String> lore = new ArrayList<String>();
			lore.add(FileControler.getArena(arene).getInt("redflag.x") + "");
			lore.add(FileControler.getArena(arene).getInt("redflag.y") + "");
			lore.add(FileControler.getArena(arene).getInt("redflag.z") + "");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(22, item);
			
		}else{
			
			ItemStack item = new ItemStack(Material.BARRIER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("drapeau rouge");
			
			List<String> lore = new ArrayList<String>();
			lore.add("information non configurée");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(22, item);
		}
		
		if(FileControler.getArena(arene).contains("blueflag.x")){
			
			ItemStack item = new ItemStack(Material.ENDER_PEARL);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("drapeau bleu");
			
			List<String> lore = new ArrayList<String>();
			lore.add(FileControler.getArena(arene).getInt("blueflag.x") + "");
			lore.add(FileControler.getArena(arene).getInt("blueflag.y") + "");
			lore.add(FileControler.getArena(arene).getInt("blueflag.z") + "");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(24, item);
			
		}else{
			
			ItemStack item = new ItemStack(Material.BARRIER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("drapeau bleu");
			
			List<String> lore = new ArrayList<String>();
			lore.add("information non configurée");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(24, item);
		}
		
		ItemStack item = new ItemStack(Material.ARROW);		
		ItemMeta i = item.getItemMeta();
		i.setDisplayName("back");
		
		item.setItemMeta(i);
		
		inv.setItem(31, item);
		
		p.openInventory(inv);
		
	}
}
