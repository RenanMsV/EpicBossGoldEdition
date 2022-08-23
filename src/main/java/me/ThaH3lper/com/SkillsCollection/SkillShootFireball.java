package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.event.*;
import org.bukkit.entity.*;
import org.bukkit.projectiles.*;
import org.bukkit.util.*;
import org.bukkit.*;

public class SkillShootFireball
{
    public static void ExecuteShoot(final LivingEntity l, final String skill, final Player player) {
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
            final float power = Float.parseFloat(data[0]);
            final boolean incendiary = Boolean.parseBoolean(data[1]);
            final int fireticks = Integer.parseInt(data[2]);
            Fireball fireball;
            if (l instanceof Creature && ((Creature)l).getTarget() == player) {
                fireball = (Fireball)l.launchProjectile((Class)Fireball.class);
            }
            else {
                final Vector facing = player.getLocation().toVector().subtract(l.getLocation().toVector()).normalize();
                final Location loc = l.getLocation().clone();
                final double yaw = Math.toDegrees(Math.atan2(-facing.getX(), facing.getZ()));
                final double pitch = Math.toDegrees(-Math.asin(facing.getY()));
                loc.setYaw((float)yaw);
                loc.setPitch((float)pitch);
                loc.add(facing.multiply(2));
                fireball = (Fireball)l.getLocation().getWorld().spawn(loc, (Class)Fireball.class);
            }
            l.getWorld().playEffect(l.getLocation(), Effect.GHAST_SHOOT, 0);
            fireball.setBounce(false);
            fireball.setIsIncendiary(incendiary);
            fireball.setFireTicks(fireticks);
            fireball.setYield(power);
            fireball.setShooter((ProjectileSource)l);
        }
    }
}
