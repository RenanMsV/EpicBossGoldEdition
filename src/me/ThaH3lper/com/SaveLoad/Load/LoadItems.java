package me.ThaH3lper.com.SaveLoad.Load;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.SaveLoad.*;
import me.ThaH3lper.com.Items.*;
import java.util.*;

public class LoadItems
{
    public static void LoadAllItems() {
        for (final SaveLoad sl : EpicBoss.plugin.saveItemList) {
            for (final String cmdName : sl.getCustomConfig().getConfigurationSection("").getKeys(false)) {
                final String s = cmdName;
                final int Id = sl.getCustomConfig().getInt(String.valueOf(s) + ".Id");
                final int Data = sl.getCustomConfig().getInt(String.valueOf(s) + ".Data");
                final int Amount = sl.getCustomConfig().getInt(String.valueOf(s) + ".Amount");
                final String Display = sl.getCustomConfig().getString(String.valueOf(s) + ".Display");
                final List<String> Lores = (List<String>)sl.getCustomConfig().getStringList(String.valueOf(s) + ".Lores");
                final List<String> Enchants = (List<String>)sl.getCustomConfig().getStringList(String.valueOf(s) + ".Enchants");
                final double speed = sl.getCustomConfig().getDouble(String.valueOf(s) + ".Tags.Speed");
                final double attack = sl.getCustomConfig().getDouble(String.valueOf(s) + ".Tags.Attack");
                final double health = sl.getCustomConfig().getDouble(String.valueOf(s) + ".Tags.Health");
                final double range = sl.getCustomConfig().getDouble(String.valueOf(s) + ".Tags.Followrange");
                final double knock = sl.getCustomConfig().getDouble(String.valueOf(s) + ".Tags.KnockbackRes");
                final String color = sl.getCustomConfig().getString(String.valueOf(s) + ".Tags.Color");
                final String player = sl.getCustomConfig().getString(String.valueOf(s) + ".Tags.Player");
                EpicBoss.plugin.listItems.add(new EpicItems(sl.thefile.getName(), cmdName, Id, Data, Amount, Display, Lores, Enchants, health, attack, knock, range, speed, color, player));
            }
        }
    }
    
    public static boolean Check(final SaveLoad sl, final String s) {
        return sl.getCustomConfig().contains(String.valueOf(s) + ".Id") && sl.getCustomConfig().contains(String.valueOf(s) + ".Data") && sl.getCustomConfig().contains(String.valueOf(s) + ".Amount");
    }
}
