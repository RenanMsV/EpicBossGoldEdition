package me.ThaH3lper.com.SkillsCollection;

import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.potion.*;

public class SkillPotion
{
    public static void ExecutePotion(final LivingEntity l, final String skill, final Player player) {
        final String[] base = skill.split(" ");
        final String[] data = base[1].split(":");
        final float chance = Float.parseFloat(base[base.length - 1]);
        if (EpicBoss.r.nextFloat() < chance && SkillHandler.CheckHealth(base[base.length - 2], l, skill)) {
            final BossSkillEvent event = new BossSkillEvent(l, skill, player, false);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (event.isChanceled()) {
                return;
            }
            final int radius = Integer.parseInt(data[0]);
            final String pType = data[1];
            final float pDuration = Float.parseFloat(data[2]);
            final int pLevel = Integer.parseInt(data[3]) - 1;
            final PotionEffect pe = new PotionEffect(PotionEffectType.getByName(pType), (int)(pDuration * 20.0f), pLevel);
            if (pe != null) {
                if (radius > 0) {
                    for (final Player p : SkillHandler.getRadious(l, radius)) {
                        p.addPotionEffect(pe);
                    }
                }
                else {
                    if (player == null) {
                        return;
                    }
                    player.addPotionEffect(pe);
                }
            }
        }
    }
}
