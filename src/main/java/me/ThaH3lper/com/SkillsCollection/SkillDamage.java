package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.event.*;
import java.lang.reflect.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class SkillDamage
{
    public static void ExecuteDamage(final LivingEntity l, final String skill, final Player player) {
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
            final double damage = Double.parseDouble(data[1]);
            final boolean ignorearmor = Array.getLength(data) > 2 && Boolean.parseBoolean(data[2]);
            if (radius > 0) {
                for (final Player p : SkillHandler.getRadious(l, radius)) {
                    DoDamage(l, p, damage, ignorearmor);
                }
            }
            else {
                if (player == null) {
                    return;
                }
                DoDamage(l, player, damage, ignorearmor);
            }
        }
    }
    
    private static void DoDamage(final LivingEntity l, final Player p, final double damage, final boolean ignorearmor) {
        if (p.isDead()) {
            return;
        }
        final EntityDamageByEntityEvent event = new EntityDamageByEntityEvent((Entity)l, (Entity)p, EntityDamageEvent.DamageCause.ENTITY_ATTACK, damage);
        Bukkit.getServer().getPluginManager().callEvent((Event)event);
        if (event.isCancelled()) {
            return;
        }
        final double edamage = event.getDamage();
        if (ignorearmor) {
            if (p.getHealth() - edamage < 1.0) {
                p.setLastDamageCause((EntityDamageEvent)event);
                p.setHealth(0.0);
                p.damage(1.0);
            }
            else {
                p.setHealth(p.getHealth() - edamage);
                p.playEffect(EntityEffect.HURT);
            }
        }
        else {
            p.damage(edamage);
            p.setLastDamageCause((EntityDamageEvent)event);
        }
    }
}
