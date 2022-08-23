package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.potion.*;
import org.bukkit.entity.*;
import me.ThaH3lper.com.Mobs.*;

public class SkillPotionMobs
{
    public static void ExecutePotionMobs(final LivingEntity l, final String skill) {
        final String[] base = skill.split(" ");
        final String[] data = base[1].split(":");
        final float chance = Float.parseFloat(base[base.length - 1]);
        if (EpicBoss.r.nextFloat() < chance && SkillHandler.CheckHealth(base[base.length - 2], l, skill)) {
            final BossSkillEvent event = new BossSkillEvent(l, skill, null, false);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (event.isChanceled()) {
                return;
            }
            final int radius = Integer.parseInt(data[0]);
            final String pType = data[1];
            final float pDuration = Float.parseFloat(data[2]);
            final int pLevel = Integer.parseInt(data[3]) - 1;
            final String[] mobtypes = base[2].split(",");
            final PotionEffect pe = new PotionEffect(PotionEffectType.getByName(pType), (int)(pDuration * 20.0f), pLevel);
            if (pe != null) {
                for (final Entity e : l.getNearbyEntities((double)radius, (double)radius, (double)radius)) {
                    if (e instanceof LivingEntity) {
                        String[] array;
                        for (int length = (array = mobtypes).length, i = 0; i < length; ++i) {
                            final String mob = array[i];
                            if (e.getType() == EntityType.fromName(mob)) {
                                ((LivingEntity)e).addPotionEffect(pe);
                            }
                            else if (EpicBoss.plugin.allMobs.contains(e.getUniqueId())) {
                                final EpicMobs em = MobCommon.getEpicMob((LivingEntity)e);
                                if (mob.equals(em.cmdName)) {
                                    ((LivingEntity)e).addPotionEffect(pe);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
