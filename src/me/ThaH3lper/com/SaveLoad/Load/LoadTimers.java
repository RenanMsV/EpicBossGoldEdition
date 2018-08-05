package me.ThaH3lper.com.SaveLoad.Load;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.SaveLoad.*;
import me.ThaH3lper.com.Timer.*;
import java.util.*;
import me.ThaH3lper.com.Mobs.*;

public class LoadTimers
{
    public static void LoadAllTimers() {
        for (final SaveLoad sl : EpicBoss.plugin.saveTimerList) {
            for (final String s : sl.getCustomConfig().getConfigurationSection("").getKeys(false)) {
                if (sl.getCustomConfig().getString(String.valueOf(s) + ".Bosses") != null) {
                    final String cmdName = s;
                    final String file = sl.thefile.getName();
                    final String string = sl.getCustomConfig().getString(String.valueOf(s) + ".Bosses");
                    final List<EpicMobs> bosses = TimerHandler.getMobs(string);
                    final int amount = sl.getCustomConfig().getInt(String.valueOf(s) + ".MaxAmount");
                    final int interval = sl.getCustomConfig().getInt(String.valueOf(s) + ".RespawnTime");
                    final int walk = sl.getCustomConfig().getInt(String.valueOf(s) + ".WalkDistance");
                    EpicBoss.plugin.listTimers.add(new EpicTimer(bosses, amount, interval, walk, cmdName, file));
                }
            }
        }
    }
}
