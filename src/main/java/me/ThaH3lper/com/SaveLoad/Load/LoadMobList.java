package me.ThaH3lper.com.SaveLoad.Load;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Mobs.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.entity.*;

public class LoadMobList
{
    public static void LoadMobsList() {
        if (EpicBoss.plugin.savelist.getCustomConfig().contains("Mobslist")) {
            final String s = EpicBoss.plugin.savelist.getCustomConfig().getString("Mobslist");
            final List<UUID> list = StringToList(s);
            EpicBoss.plugin.allMobs = list;
        }
    }
    
    public static void SaveMobsList() {
        final String s = ListToString(EpicBoss.plugin.getMobsAll());
        EpicBoss.plugin.savelist.getCustomConfig().set("Mobslist", (Object)s);
        EpicBoss.plugin.savelist.saveCustomConfig();
    }
    
    public static String ListToString(final List<LivingEntity> list) {
        String s = "";
        for (final LivingEntity i : list) {
            if (!i.isDead()) {
                s = String.valueOf(s) + i.getUniqueId() + ":" + MobCommon.getEpicMob(i).cmdName + ":" + i.getWorld().getName() + ":" + i.getWorld().getChunkAt(i.getLocation()).getX() + ":" + i.getWorld().getChunkAt(i.getLocation()).getZ() + ",";
            }
        }
        return s;
    }
    
    public static List<UUID> StringToList(final String s) {
        final List<UUID> list = new ArrayList<UUID>();
        final String[] parts = s.split(",");
        String[] array;
        for (int length = (array = parts).length, j = 0; j < length; ++j) {
            final String ent = array[j];
            if (ent.contains(":")) {
                final String[] data = ent.split(":");
                final World w = Bukkit.getWorld(data[2]);
                Entity[] entities;
                for (int length2 = (entities = w.getChunkAt(Integer.parseInt(data[3]), Integer.parseInt(data[4])).getEntities()).length, k = 0; k < length2; ++k) {
                    final Entity e = entities[k];
                    if (e instanceof LivingEntity) {
                        LivingEntity l = (LivingEntity)e;
                        final UUID i = UUID.fromString(data[0]);
                        if (l.getUniqueId().compareTo(i) == 0) {
                            l = MobCommon.setMeta(l, data[1], "cmdname");
                            list.add(l.getUniqueId());
                        }
                    }
                }
            }
        }
        return list;
    }
    
    public static List<UUID> Refresh(final List<LivingEntity> list) {
        final List<UUID> newList = new ArrayList<UUID>();
        for (final LivingEntity i : list) {
            if (!i.isDead()) {
                newList.add(i.getUniqueId());
            }
        }
        return newList;
    }
}
