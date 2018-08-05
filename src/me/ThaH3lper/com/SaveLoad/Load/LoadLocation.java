package me.ThaH3lper.com.SaveLoad.Load;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Location.*;
import java.util.*;

public class LoadLocation
{
    public static void loadAllLocations() {
        if (EpicBoss.plugin.savelist.getCustomConfig().contains("Locationlist")) {
            final List<String> list = (List<String>)EpicBoss.plugin.savelist.getCustomConfig().getStringList("Locationlist");
            for (final String s : list) {
                EpicBoss.plugin.listLoc.add(new EpicLocation(s));
            }
        }
    }
    
    public static void saveAllLocations() {
        final List<String> list = new ArrayList<String>();
        for (final EpicLocation el : EpicBoss.plugin.listLoc) {
            list.add(el.getString());
        }
        EpicBoss.plugin.savelist.getCustomConfig().set("Locationlist", (Object)list);
        EpicBoss.plugin.savelist.saveCustomConfig();
    }
}
