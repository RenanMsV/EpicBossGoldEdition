package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

public class SkillPull
{
    public static void ExecutePull(final LivingEntity l, final String skill, final Player player) {
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
            final float velocity = Float.parseFloat(data[1]);
            if (radius > 0) {
                for (final Player p : SkillHandler.getRadious(l, radius)) {
                    if (p.hasLineOfSight((Entity)l)) {
                        final double distance = l.getLocation().distance(p.getLocation());
                        final double modxz = distance * 0.5 * velocity;
                        double mody = distance * 0.34 * velocity;
                        mody = ((l.getLocation().getY() - p.getLocation().getY() != 0.0) ? (mody * (Math.abs(l.getLocation().getY() - p.getLocation().getY()) * 0.5)) : mody);
                        final Vector direction = p.getLocation().toVector().subtract(l.getLocation().toVector()).normalize().multiply(velocity);
                        direction.setX(direction.getX() * -1.0 * modxz);
                        direction.setZ(direction.getZ() * -1.0 * modxz);
                        direction.setY(direction.getY() * -1.0 * mody);
                        p.setVelocity(direction);
                    }
                }
            }
            else {
                final Vector direction2 = player.getLocation().toVector().subtract(l.getLocation().toVector()).normalize().multiply(velocity);
                direction2.setX(direction2.getX() * -1.0);
                direction2.setZ(direction2.getZ() * -1.0);
                player.setVelocity(direction2);
            }
        }
    }
}
