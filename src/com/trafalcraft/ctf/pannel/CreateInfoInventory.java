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

public class CreateInfoInventory {

	public static void loadInventoryInfo(Player p, String arene){
		
		Inventory inv = Bukkit.createInventory(p, 36, arene + ";infoInventory;ctf");
		
		if(FileControler.getArena(arene).contains("maxplayer")){
			
			ItemStack item = new ItemStack(Material.PAPER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("maxplayer");
			
			List<String> lore = new ArrayList<String>();
			lore.add(FileControler.getArena(arene).getInt("maxplayer") + "");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(9, item);
			
		}else{
			
			ItemStack item = new ItemStack(Material.BARRIER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("maxplayer");
			
			List<String> lore = new ArrayList<String>();
			lore.add("information non configurée");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(9, item);
		}
		
		if(FileControler.getArena(arene).contains("maxpoint")){
			
			ItemStack item = new ItemStack(Material.PAPER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("maxpoint");
			
			List<String> lore = new ArrayList<String>();
			lore.add(FileControler.getArena(arene).getInt("maxpoint") + "");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(11, item);
			
		}else{
			
			ItemStack item = new ItemStack(Material.BARRIER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("maxpoint");
			
			List<String> lore = new ArrayList<String>();
			lore.add("information non configurée");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(11, item);
		}
		
		if(FileControler.getArena(arene).contains("temps")){
			
			ItemStack item = new ItemStack(Material.PAPER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("temps");
			
			List<String> lore = new ArrayList<String>();
			lore.add(FileControler.getArena(arene).getString("temps") + "");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(13, item);
			
		}else{
			
			ItemStack item = new ItemStack(Material.BARRIER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("temps");
			
			List<String> lore = new ArrayList<String>();
			lore.add("information non configurée");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(13, item);
		}
		
		if(FileControler.getArena(arene).contains("status")){
			
			ItemStack item = new ItemStack(Material.PAPER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("status");
			
			List<String> lore = new ArrayList<String>();
			lore.add(FileControler.getArena(arene).getString("status") + "");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(15, item);
			
		}else{
			
			ItemStack item = new ItemStack(Material.BARRIER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("status");
			
			List<String> lore = new ArrayList<String>();
			lore.add("information non configurée");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(15, item);
		}
		
		if(FileControler.getArena(arene).contains("friendlyfire")){
			
			ItemStack item = new ItemStack(Material.PAPER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("friendlyfire");
			
			List<String> lore = new ArrayList<String>();
			lore.add(FileControler.getArena(arene).getString("friendlyfire") + "");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(17, item);
			
		}else{
			
			ItemStack item = new ItemStack(Material.BARRIER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("friendlyfire");
			
			List<String> lore = new ArrayList<String>();
			lore.add("information non configurée");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(17, item);
		}
		
		ItemStack item = new ItemStack(Material.ARROW);		
		ItemMeta i = item.getItemMeta();
		i.setDisplayName("back");
		
		item.setItemMeta(i);
		
		inv.setItem(31, item);
		
		p.openInventory(inv);
	}
}
