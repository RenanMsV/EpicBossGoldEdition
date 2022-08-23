package me.ThaH3lper.com.SaveLoad.Load;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.SaveLoad.*;
import me.ThaH3lper.com.Skills.*;
import java.util.*;

public class LoadSkills
{
    public static void LoadAllSkills() {
        for (final SaveLoad sl : EpicBoss.plugin.saveSkillList) {
            for (final String s : sl.getCustomConfig().getConfigurationSection("").getKeys(false)) {
                final int cooldown = sl.getCustomConfig().getInt(String.valueOf(s) + ".Cooldown");
                if (sl.getCustomConfig().getStringList(String.valueOf(s) + ".Skills") != null) {
                    final String cmdName = s;
                    final String file = sl.thefile.getName();
                    final List<String> list = (List<String>)sl.getCustomConfig().getStringList(String.valueOf(s) + ".Skills");
                    EpicBoss.plugin.listSkills.add(new EpicSkill(cmdName, file, list, cooldown));
                }
            }
        }
    }
}
