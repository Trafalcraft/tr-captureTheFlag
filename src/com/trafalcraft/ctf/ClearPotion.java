package com.trafalcraft.ctf;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class ClearPotion {

	public static void clearEffect(Player p){
		p.removePotionEffect(PotionEffectType.POISON);
		p.removePotionEffect(PotionEffectType.ABSORPTION);
		p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
		p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
		p.removePotionEffect(PotionEffectType.HEAL);
		p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
		p.removePotionEffect(PotionEffectType.INVISIBILITY);
		p.removePotionEffect(PotionEffectType.JUMP);
		p.removePotionEffect(PotionEffectType.LUCK);
		p.removePotionEffect(PotionEffectType.NIGHT_VISION);
		p.removePotionEffect(PotionEffectType.REGENERATION);
		p.removePotionEffect(PotionEffectType.SLOW);
		p.removePotionEffect(PotionEffectType.SPEED);
		p.removePotionEffect(PotionEffectType.WATER_BREATHING);
		p.removePotionEffect(PotionEffectType.WEAKNESS);
	}
}
