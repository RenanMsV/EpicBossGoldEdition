package me.ThaH3lper.com.Listener;

import org.bukkit.event.entity.*;
import me.ThaH3lper.com.Spawning.*;
import org.bukkit.event.*;

public class MobSpawn implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void MobSpawnEvent(final CreatureSpawnEvent e) {
        if (e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM && e.getEntity() != null) {
            SpawningHandler.templist.add(e.getEntity());
        }
    }
}
