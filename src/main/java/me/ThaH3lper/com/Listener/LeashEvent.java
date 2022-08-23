package me.ThaH3lper.com.Listener;

import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.*;

public class LeashEvent implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void LeashEntity(final PlayerLeashEntityEvent e) {
        if (!(e.getEntity() instanceof LivingEntity)) {
            return;
        }
        final LivingEntity l = (LivingEntity)e.getEntity();
        if (EpicBoss.plugin.allMobs.contains(l.getUniqueId())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "You can't leash this mob");
        }
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void TagEvent(final PlayerInteractEntityEvent e) {
        if (e.getRightClicked() != null && e.getRightClicked() instanceof LivingEntity) {
            final LivingEntity l = (LivingEntity)e.getRightClicked();
            if (EpicBoss.plugin.allMobs.contains(l.getUniqueId()) && e.getPlayer().getItemInHand().getType() == Material.NAME_TAG) {
                e.setCancelled(true);
            }
        }
    }
}
