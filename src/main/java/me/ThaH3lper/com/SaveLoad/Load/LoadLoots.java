package me.ThaH3lper.com.SaveLoad.Load;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.SaveLoad.*;
import me.ThaH3lper.com.Drops.*;
import java.util.*;

public class LoadLoots
{
    public static void LoadAllLoot() {
        for (final SaveLoad sl : EpicBoss.plugin.saveLootList) {
            for (final String s : sl.getCustomConfig().getConfigurationSection("").getKeys(false)) {
                if (sl.getCustomConfig().getStringList(String.valueOf(s) + ".Loot") != null) {
                    final String cmdName = s;
                    final String file = sl.thefile.getName();
                    final List<String> list = (List<String>)sl.getCustomConfig().getStringList(String.valueOf(s) + ".Loot");
                    EpicBoss.plugin.listLoots.add(new EpicNormal(list, cmdName, file));
                }
            }
        }
    }
}
