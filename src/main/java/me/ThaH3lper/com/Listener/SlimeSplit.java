package me.ThaH3lper.com.Listener;

import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import org.bukkit.event.*;

public class SlimeSplit implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void SplitEvent(final SlimeSplitEvent e) {
        if (!(e.getEntity() instanceof LivingEntity)) {
            return;
        }
        final LivingEntity l = (LivingEntity)e.getEntity();
        if (!EpicBoss.plugin.allMobs.contains(l.getUniqueId())) {
            return;
        }
        e.setCount(0);
    }
}
