package me.ThaH3lper.com.Listener;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.Drops.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.*;
import me.ThaH3lper.com.Mobs.*;
import me.ThaH3lper.com.Drops.Fair.*;
import org.bukkit.event.*;
import java.util.*;
import me.ThaH3lper.com.Timer.Timer;

import org.bukkit.event.entity.*;
import org.bukkit.entity.*;

public class MobDrop implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void MobDeathEvent(final EntityDeathEvent e) {
        final LivingEntity l = e.getEntity();
        if (EpicBoss.plugin.allMobs.contains(l.getUniqueId())) {
            removeTimer(l);
            e.getDrops().clear();
            final EpicMobs em = MobCommon.getEpicMob(l);
            SkillHandler.ExecuteSkills(em.skills, l, getKiller(e));
            if (em.fair && DropHandler.getFairDrops(l) != null) {
                final FairDrops fd = DropHandler.getFairDrops(l);
                final List<FairPlayer> list = sort(fd.players);
                fd.Shout(EpicBoss.plugin.menu);
                fd.Shout(String.valueOf(ChatColor.translateAlternateColorCodes('&', em.Display)) + ChatColor.GREEN + " has Died! List off all Players involved:");
                for (final FairPlayer fp : list) {
                    fd.Shout(ChatColor.LIGHT_PURPLE + fp.player.getName() + ChatColor.GRAY + ": " + ChatColor.GOLD + ChatColor.ITALIC + (int)fp.damage + "dmg");
                    final int i = list.indexOf(fp);
                    if (fd.loot.size() > i) {
                        final EpicNormal en = fd.loot.get(i);
                        if (en == null) {
                            continue;
                        }
                        DropHandler.dropPlayer(en, fp.player);
                    }
                    else {
                        if (fd.rest == null) {
                            continue;
                        }
                        final EpicNormal en = fd.rest;
                        if (en == null) {
                            continue;
                        }
                        DropHandler.dropPlayer(en, fp.player);
                    }
                }
                EpicBoss.plugin.listFair.remove(fd);
            }
            else if (!em.fair) {
                for (final String s : em.loot) {
                    final EpicNormal en2 = DropHandler.getEpicNormal(s);
                    if (en2 != null) {
                        final BossDeathEvent event = new BossDeathEvent(l, (LivingEntity)l.getKiller(), en2.getDrops(), en2.getExp());
                        Bukkit.getServer().getPluginManager().callEvent((Event)event);
                        DropHandler.Drop(event.getLivingEntity().getLocation(), event.getExp(), event.getDrops());
                    }
                }
            }
        }
    }
    
    public static List<FairPlayer> sort(final List<FairPlayer> orginal) {
        for (int lenD = orginal.size(), i = 0; i < lenD - 1; ++i) {
            for (int k = i; k < lenD - 1; ++k) {
                if (orginal.get(k).damage < orginal.get(k + 1).damage) {
                    Collections.swap(orginal, k, k + 1);
                    Bukkit.broadcastMessage(String.valueOf(orginal.get(k).damage) + " : " + orginal.get(k + 1).damage);
                }
            }
        }
        return orginal;
    }
    
    public static void removeTimer(final LivingEntity l) {
        for (final Timer t : EpicBoss.plugin.allTimers) {
            if (t.mobs.contains(l.getUniqueId())) {
                t.mobs.remove(l.getUniqueId());
            }
        }
    }
    
    public static Player getKiller(final EntityDeathEvent event) {
        final EntityDamageEvent entityDamageEvent = event.getEntity().getLastDamageCause();
        if (entityDamageEvent != null && !entityDamageEvent.isCancelled() && entityDamageEvent instanceof EntityDamageByEntityEvent) {
            final Entity damager = ((EntityDamageByEntityEvent)entityDamageEvent).getDamager();
            if (damager instanceof Projectile) {
                final LivingEntity shooter = (LivingEntity)((Projectile)damager).getShooter();
                if (shooter != null && shooter instanceof Player) {
                    return (Player)shooter;
                }
            }
            if (damager instanceof Player) {
                return (Player)damager;
            }
        }
        return null;
    }
}
