package me.ThaH3lper.com.SaveLoad.Load;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.SaveLoad.*;
import me.ThaH3lper.com.Spawning.*;
import java.util.*;

public class LoadSpawning
{
    public static void LoadSpawnings() {
        for (final SaveLoad sl : EpicBoss.plugin.saveSpawningList) {
            for (final String s : sl.getCustomConfig().getConfigurationSection("").getKeys(false)) {
                if (sl.getCustomConfig().getStringList(String.valueOf(s) + ".Mobname") != null) {
                    final String cmdName = s;
                    final String mobName = sl.getCustomConfig().getString(String.valueOf(s) + ".Mobname");
                    final String worlds = sl.getCustomConfig().getString(String.valueOf(s) + ".Worlds");
                    final String biomes = sl.getCustomConfig().getString(String.valueOf(s) + ".Biomes");
                    final float chance = (float)sl.getCustomConfig().getDouble(String.valueOf(s) + ".Chance");
                    final int priority = sl.getCustomConfig().getInt(String.valueOf(s) + ".Priority");
                    EpicBoss.plugin.listSpawning.add(new EpicSpawning(cmdName, mobName, priority, chance, worlds, biomes));
                }
            }
        }
        EpicBoss.plugin.listSpawning = sort(EpicBoss.plugin.listSpawning);
    }
    
    public static List<EpicSpawning> sort(final List<EpicSpawning> list) {
        for (int i = 0; i < list.size(); ++i) {
            for (int q = 1; q < list.size() - i; ++q) {
                final EpicSpawning o = list.get(q - 1);
                final EpicSpawning n = list.get(q);
                if (o.priority > n.priority) {
                    list.set(q - 1, n);
                    list.set(q, o);
                }
            }
        }
        return list;
    }
}
