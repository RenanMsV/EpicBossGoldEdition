package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.entity.*;
import java.util.*;
import me.ThaH3lper.com.Mobs.*;

public class SkillConsume
{
    public static void ExecuteConsume(final LivingEntity l, final String skill) {
        final String[] base = skill.split(" ");
        final String[] data = base[1].split(":");
        final float chance = Float.parseFloat(base[base.length - 1]);
        if (EpicBoss.r.nextFloat() < chance && SkillHandler.CheckHealth(base[base.length - 2], l, skill)) {
            final BossSkillEvent event = new BossSkillEvent(l, skill, null, false);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (event.isChanceled()) {
                return;
            }
            final int radius_xz = Integer.parseInt(data[0]);
            final int radius_y = Integer.parseInt(data[1]);
            final int damage = Integer.parseInt(data[2]);
            final int heal = Integer.parseInt(data[3]);
            final String[] mobtypes = base[2].split(",");
            final List<Entity> moblist = (List<Entity>)l.getNearbyEntities((double)radius_xz, (double)radius_y, (double)radius_xz);
            for (final Entity e : moblist) {
                if (e instanceof LivingEntity) {
                    String[] array;
                    for (int length = (array = mobtypes).length, i = 0; i < length; ++i) {
                        final String mob = array[i];
                        if (e.getType() == EntityType.fromName(mob)) {
                            ConsumeMob(l, (LivingEntity)e, damage, heal);
                        }
                        else if (EpicBoss.plugin.allMobs.contains(e.getUniqueId())) {
                            final EpicMobs em = MobCommon.getEpicMob((LivingEntity)e);
                            if (mob.equals(em.cmdName)) {
                                ConsumeMob(l, (LivingEntity)e, damage, heal);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private static void ConsumeMob(final LivingEntity consumer, final LivingEntity consumed, final int damage, final int heal) {
        consumed.damage((double)damage);
        if (consumer.getHealth() + heal >= consumer.getMaxHealth()) {
            consumer.setHealth(consumer.getMaxHealth());
        }
        else {
            consumer.setHealth(consumer.getHealth() + heal);
        }
    }
}
