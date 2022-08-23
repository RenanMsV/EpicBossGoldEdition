package me.ThaH3lper.com.Listener;

import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class MobDamaged implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void MobDamagedEvent(final EntityDamageEvent e) {
        if (!(e.getEntity() instanceof LivingEntity)) {
            return;
        }
        final LivingEntity l = (LivingEntity)e.getEntity();
        if (!EpicBoss.plugin.allMobs.contains(l.getUniqueId())) {
            return;
        }
        if (e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {
            final Location Loc = l.getLocation();
            Loc.setY(Loc.getY() + 2.0);
            l.teleport(Loc);
            e.setCancelled(true);
        }
    }
}
