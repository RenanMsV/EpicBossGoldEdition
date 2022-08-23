package me.ThaH3lper.com.SkillsCollection;

import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.event.*;
import org.bukkit.*;

public class SkillTeleportNear
{
    public static void ExecuteTeleportNear(final LivingEntity l, final String skill, final Player player) {
        if (player == null) {
            return;
        }
        final String[] base = skill.split(" ");
        final String[] data = base[1].split(":");
        final double radius_xz = Double.parseDouble(data[0]);
        final double radius_y = Double.parseDouble(data[1]);
        final float chance = Float.parseFloat(base[base.length - 1]);
        if (EpicBoss.r.nextFloat() < chance && SkillHandler.CheckHealth(base[base.length - 2], l, skill)) {
            if (data.length > 2) {
                final int max_distance = Integer.parseInt(data[2]);
                if (l.getLocation().distanceSquared(player.getLocation()) > max_distance * max_distance) {
                    return;
                }
            }
            final BossSkillEvent event = new BossSkillEvent(l, skill, player, false);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (event.isChanceled()) {
                return;
            }
            final Location Loc = player.getLocation();
            double new_x = Loc.getX();
            double new_z = Loc.getZ();
            double new_y = Loc.getY();
            new_x = ((EpicBoss.r.nextInt(2) == 1) ? (new_x + EpicBoss.r.nextInt((int)radius_xz + 1)) : (new_x - EpicBoss.r.nextInt((int)radius_xz + 1)));
            new_z = ((EpicBoss.r.nextInt(2) == 1) ? (new_z + EpicBoss.r.nextInt((int)radius_xz + 1)) : (new_z - EpicBoss.r.nextInt((int)radius_xz + 1)));
            new_y = ((EpicBoss.r.nextInt(2) == 1) ? (new_y + EpicBoss.r.nextInt((int)radius_y + 1)) : (new_y - EpicBoss.r.nextInt((int)radius_y + 1)));
            Loc.setX(new_x);
            Loc.setZ(new_z);
            Loc.setY(new_y);
            l.teleport(Loc);
        }
    }
}
