package me.ThaH3lper.com.SkillsCollection;

import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.*;
import org.bukkit.event.*;
import java.util.*;

public class SkillLightning
{
    public static void ExecuteLightning(final LivingEntity l, final String skill, final Player player) {
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
            final int damage = Integer.parseInt(data[1]);
            if (radius > 0) {
                for (final Player p : SkillHandler.getRadious(l, radius)) {
                    p.getLocation().getWorld().strikeLightningEffect(p.getLocation());
                    p.damage((double)damage);
                }
            }
            else {
                if (player == null) {
                    return;
                }
                player.getLocation().getWorld().strikeLightningEffect(player.getLocation());
                player.damage((double)damage);
            }
        }
    }
}
