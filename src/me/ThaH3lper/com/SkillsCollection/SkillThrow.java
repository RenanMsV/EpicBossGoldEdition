package me.ThaH3lper.com.SkillsCollection;

import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.*;
import org.bukkit.event.*;
import java.util.*;
import org.bukkit.util.*;
import org.bukkit.util.Vector;

public class SkillThrow
{
    public static void ExecuteThrow(final LivingEntity l, final String skill, final Player player) {
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
            final float strength = Float.parseFloat(data[1]) / 10.0f;
            final float strengthY = Float.parseFloat(data[2]) / 10.0f;
            if (radius > 0) {
                for (final Player p : SkillHandler.getRadious(l, radius)) {
                    final Vector V = p.getLocation().toVector().subtract(l.getLocation().toVector()).normalize().multiply(strength);
                    if (strength == 0.0f) {
                        V.setY(strengthY);
                    }
                    else {
                        V.setY(strengthY + V.getY());
                    }
                    p.setVelocity(V);
                }
            }
            else {
                if (player == null) {
                    return;
                }
                final Vector V = player.getLocation().toVector().subtract(l.getLocation().toVector()).normalize().multiply(strength);
                if (strength == 0.0f) {
                    V.setY(strengthY);
                }
                else {
                    V.setY(strengthY + V.getY());
                }
                player.setVelocity(V);
            }
        }
    }
}
