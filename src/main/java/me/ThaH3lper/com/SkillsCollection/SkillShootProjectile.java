package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.event.*;
import java.lang.reflect.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.projectiles.*;
import org.bukkit.plugin.*;
import org.bukkit.metadata.*;
import org.bukkit.util.*;

public class SkillShootProjectile
{
    private static Class<? extends Projectile> projectileClass;
    
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
            final String projectileType = (Array.getLength(data) > 0) ? data[0] : "arrow";
            final int damage = (Array.getLength(data) > 1) ? Integer.parseInt(data[1]) : 1;
            final float velocity = (Array.getLength(data) > 2) ? Float.parseFloat(data[2]) : 1.0f;
            final int maxdistance = (Array.getLength(data) > 3) ? Integer.parseInt(data[3]) : 64;
            if (l.getLocation().distance(player.getLocation()) > maxdistance) {
                return;
            }
            if (projectileType.equalsIgnoreCase("arrow")) {
                SkillShootProjectile.projectileClass = (Class<? extends Projectile>)Arrow.class;
                l.getWorld().playEffect(l.getLocation(), Effect.BOW_FIRE, 0);
            }
            else if (projectileType.equalsIgnoreCase("snowball")) {
                SkillShootProjectile.projectileClass = (Class<? extends Projectile>)Snowball.class;
                l.getWorld().playEffect(l.getLocation(), Effect.BOW_FIRE, 0);
            }
            else if (projectileType.equalsIgnoreCase("egg")) {
                SkillShootProjectile.projectileClass = (Class<? extends Projectile>)Egg.class;
                l.getWorld().playEffect(l.getLocation(), Effect.BOW_FIRE, 0);
            }
            else if (projectileType.equalsIgnoreCase("enderpearl")) {
                SkillShootProjectile.projectileClass = (Class<? extends Projectile>)EnderPearl.class;
            }
            else {
                SkillShootProjectile.projectileClass = (Class<? extends Projectile>)Arrow.class;
            }
            Projectile projectile;
            if (l instanceof Creature && ((Creature)l).getTarget() == player) {
                projectile = l.launchProjectile((Class)SkillShootProjectile.projectileClass);
                projectile.setVelocity(l.getLocation().getDirection().multiply(velocity));
            }
            else {
                projectile = l.launchProjectile((Class)SkillShootProjectile.projectileClass);
                final Vector facing = player.getLocation().toVector().subtract(l.getLocation().toVector()).normalize().multiply(velocity);
                projectile.setVelocity(facing);
            }
            projectile.setBounce(false);
            projectile.setShooter((ProjectileSource)l);
            projectile.setMetadata("EpicBossProjectile", (MetadataValue)new FixedMetadataValue((Plugin)EpicBoss.plugin, (Object)new ProjectileData(damage)));
        }
    }
    
    public static class ProjectileData
    {
        public int damage;
        
        public ProjectileData(final int damage) {
            this.damage = damage;
        }
    }
}
