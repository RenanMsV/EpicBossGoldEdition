package me.ThaH3lper.com.SkillsCollection;

import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.event.*;
import java.util.*;
import org.bukkit.*;

public class SkillForcePullNear
{
    public static void ExecuteForcePullNear(final LivingEntity l, final String skill, final Player player) {
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
            final double new_radius_xz = Double.parseDouble(data[1]);
            final double new_radius_y = Double.parseDouble(data[2]);
            if (radius > 0) {
                for (final Player p : SkillHandler.getRadious(l, radius)) {
                    final Location Loc = l.getLocation();
                    double new_x = Loc.getX();
                    double new_z = Loc.getZ();
                    double new_y = Loc.getY();
                    new_x = ((EpicBoss.r.nextInt(2) == 1) ? (new_x + EpicBoss.r.nextInt((int)new_radius_xz + 1)) : (new_x - EpicBoss.r.nextInt((int)new_radius_xz + 1)));
                    new_z = ((EpicBoss.r.nextInt(2) == 1) ? (new_z + EpicBoss.r.nextInt((int)new_radius_xz + 1)) : (new_z - EpicBoss.r.nextInt((int)new_radius_xz + 1)));
                    new_y = ((EpicBoss.r.nextInt(2) == 1) ? (new_y + EpicBoss.r.nextInt((int)new_radius_y + 1)) : (new_y - EpicBoss.r.nextInt((int)new_radius_y + 1)));
                    Loc.setX(new_x);
                    Loc.setZ(new_z);
                    Loc.setY(new_y);
                    p.teleport(Loc);
                }
            }
            else {
                final Location Loc = l.getLocation();
                double new_x2 = Loc.getX();
                double new_z2 = Loc.getZ();
                double new_y2 = Loc.getY();
                new_x2 = ((EpicBoss.r.nextInt(2) == 1) ? (new_x2 + EpicBoss.r.nextInt((int)new_radius_xz + 1)) : (new_x2 - EpicBoss.r.nextInt((int)new_radius_xz + 1)));
                new_z2 = ((EpicBoss.r.nextInt(2) == 1) ? (new_z2 + EpicBoss.r.nextInt((int)new_radius_xz + 1)) : (new_z2 - EpicBoss.r.nextInt((int)new_radius_xz + 1)));
                new_y2 = ((EpicBoss.r.nextInt(2) == 1) ? (new_y2 + EpicBoss.r.nextInt((int)new_radius_y + 1)) : (new_y2 - EpicBoss.r.nextInt((int)new_radius_y + 1)));
                Loc.setX(new_x2);
                Loc.setZ(new_z2);
                Loc.setY(new_y2);
                player.teleport(Loc);
            }
        }
    }
}
