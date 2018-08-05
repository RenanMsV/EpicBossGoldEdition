package me.ThaH3lper.com.SaveLoad.Load;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.SaveLoad.*;
import me.ThaH3lper.com.Mobs.*;
import java.util.*;

public class LoadMobs
{
    public static void LoadAllMobs() {
        for (final SaveLoad sl : EpicBoss.plugin.saveMobList) {
            for (final String s : sl.getCustomConfig().getConfigurationSection("").getKeys(false)) {
                if (sl.getCustomConfig().getString(String.valueOf(s) + ".Bosslist") != null) {
                    final String cmdName = s;
                    final String file = sl.thefile.getName();
                    final String list = sl.getCustomConfig().getString(String.valueOf(s) + ".Bosslist");
                    EpicBoss.plugin.listMobslist.add(new EpicMobsList(file, cmdName, list));
                }
                else {
                    final String cmdName = s;
                    final String file = sl.thefile.getName();
                    final String Mobtype = sl.getCustomConfig().getString(String.valueOf(s) + ".Mobtype");
                    final String Display = sl.getCustomConfig().getString(String.valueOf(s) + ".Display");
                    final double health = sl.getCustomConfig().getDouble(String.valueOf(s) + ".Health");
                    final double damage = sl.getCustomConfig().getDouble(String.valueOf(s) + ".Damage");
                    final boolean showhp = sl.getCustomConfig().getBoolean(String.valueOf(s) + ".ShowHealth");
                    final boolean despawn = sl.getCustomConfig().getBoolean(String.valueOf(s) + ".Despawn");
                    final double speed = sl.getCustomConfig().getDouble(String.valueOf(s) + ".Tags.Speed");
                    final double knock = sl.getCustomConfig().getDouble(String.valueOf(s) + ".Tags.KnockbackRes");
                    final double follow = sl.getCustomConfig().getDouble(String.valueOf(s) + ".Tags.Followrange");
                    final int size = sl.getCustomConfig().getInt(String.valueOf(s) + ".Tags.Size");
                    final int color = sl.getCustomConfig().getInt(String.valueOf(s) + ".Tags.Color");
                    final int maxCombatDistance = sl.getCustomConfig().getInt(String.valueOf(s) + ".Tags.MaxCombatDistance");
                    final String oso = sl.getCustomConfig().getString(String.valueOf(s) + ".Tags.Ocelot");
                    final String horseStyle = sl.getCustomConfig().getString(String.valueOf(s) + ".Tags.HorseStyle");
                    final String horseType = sl.getCustomConfig().getString(String.valueOf(s) + ".Tags.HorseType");
                    final String horseColor = sl.getCustomConfig().getString(String.valueOf(s) + ".Tags.HorseColor");
                    final String villagerType = sl.getCustomConfig().getString(String.valueOf(s) + ".Tags.VillagerType");
                    final List<String> skills = (List<String>)sl.getCustomConfig().getStringList(String.valueOf(s) + ".Skills");
                    final List<String> loot = (List<String>)sl.getCustomConfig().getStringList(String.valueOf(s) + ".Loot");
                    final List<String> equipment = (List<String>)sl.getCustomConfig().getStringList(String.valueOf(s) + ".Equipment");
                    EpicBoss.plugin.listMobs.add(new EpicMobs(file, cmdName, Mobtype, Display, loot, equipment, health, damage, speed, knock, follow, skills, despawn, showhp, size, color, oso, horseStyle, horseType, horseColor, villagerType, maxCombatDistance));
                }
            }
        }
    }
}
