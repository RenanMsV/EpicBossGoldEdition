package me.ThaH3lper.com.SkillsCollection;

import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.*;
import org.bukkit.event.*;
import java.util.*;

public class SkillForcePull
{
    public static void ExecuteForcePull(final LivingEntity l, final String skill, final Player player) {
        if (player == null) {
            return;
        }
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
            if (radius > 0) {
                for (final Player p : SkillHandler.getRadious(l, radius)) {
                    p.teleport(l.getLocation());
                }
            }
            else {
                player.teleport(l.getLocation());
            }
        }
    }
}
