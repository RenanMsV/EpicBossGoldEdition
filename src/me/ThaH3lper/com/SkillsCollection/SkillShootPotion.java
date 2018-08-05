package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.event.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.projectiles.*;
import org.bukkit.entity.*;

public class SkillShootPotion
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
            final String pType = data[0];
            final float pDuration = Float.parseFloat(data[1]);
            final int pLevel = Integer.parseInt(data[2]) - 1;
            final float velocity = Float.parseFloat(data[3]);
            final ItemStack potion = new ItemStack(Material.POTION);
            final PotionMeta pm = (PotionMeta)potion.getItemMeta();
            pm.addCustomEffect(new PotionEffect(PotionEffectType.getByName(pType), (int)(pDuration * 20.0f), pLevel), true);
            potion.setItemMeta((ItemMeta)pm);
            final Projectile projectile = l.launchProjectile((Class)ThrownPotion.class);
            ((ThrownPotion)projectile).setItem(potion);
            projectile.setVelocity(l.getLocation().getDirection().multiply(velocity));
            projectile.setBounce(false);
            projectile.setShooter((ProjectileSource)l);
        }
    }
}
