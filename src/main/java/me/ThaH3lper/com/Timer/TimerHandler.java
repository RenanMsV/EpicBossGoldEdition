package me.ThaH3lper.com.Timer;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Location.EpicLocation;
import me.ThaH3lper.com.Location.LocationHandler;
import me.ThaH3lper.com.Mobs.EpicMobs;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TimerHandler
{
    public static List<EpicMobs> getMobs(final String s) {
        final List<EpicMobs> list = new ArrayList<EpicMobs>();
        for (final EpicMobs em : EpicBoss.plugin.listMobs) {
            if (em.cmdName.contains(s)) {
                list.add(em);
            }
        }
        return list;
    }
    
    public static EpicTimer getEpicTimer(final String s) {
        for (final EpicTimer et : EpicBoss.plugin.listTimers) {
            if (s.equals(et.cmdName)) {
                return et;
            }
        }
        return null;
    }
    
    public static void SaveAllTimers() {
        final List<String> save = new ArrayList<String>();
        for (final Timer t : EpicBoss.plugin.allTimers) {
            final String loc = String.valueOf(t.loc.getWorld().getName()) + "," + t.loc.getX() + "," + t.loc.getY() + "," + t.loc.getZ();
            final String time = new StringBuilder(String.valueOf(t.clock)).toString();
            String mobs = "";
            for (final UUID c : t.mobs) {
                mobs = String.valueOf(mobs) + c + ",";
            }
            if (mobs.equals("")) {
                mobs = "null";
            }
            save.add(String.valueOf(loc) + ":" + time + ":" + mobs);
        }
        EpicBoss.plugin.savelist.getCustomConfig().set("Timers", (Object)save);
        EpicBoss.plugin.savelist.saveCustomConfig();
    }
    
    public static void LoadAllTimers() {
        final List<String> list = (List<String>)EpicBoss.plugin.savelist.getCustomConfig().getStringList("Timers");
        if (list == null) {
            return;
        }
        for (final String s : list) {
            final String[] parts = s.split(":");
            final String[] data = parts[0].split(",");
            final Location loc = new Location(Bukkit.getWorld(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]));
            final int time = Integer.parseInt(parts[1]);
            final List<UUID> mobslist = new ArrayList<UUID>();
            final String[] mobs = parts[2].split(",");
            String[] array;
            for (int length = (array = mobs).length, j = 0; j < length; ++j) {
                final String m = array[j];
                if (!m.equals("") && !m.equals("null")) {
                    for (final LivingEntity l : EpicBoss.plugin.getMobsAll()) {
                        final UUID i = UUID.fromString(m);
                        if (l.getUniqueId().compareTo(i) == 0) {
                            mobslist.add(l.getUniqueId());
                        }
                    }
                }
            }
            if (loc.getBlock().getType() == Material.OAK_SIGN || loc.getBlock().getType() == Material.OAK_WALL_SIGN) {
                final Sign sign = (Sign)loc.getBlock().getState();
                final EpicTimer et = getEpicTimer(sign.getLine(1));
                final EpicLocation el = LocationHandler.getEpicLocation(sign.getLine(2));
                final Timer t = new Timer(loc, et, el, sign.getLines());
                t.clock = time;
                t.mobs = mobslist;
                EpicBoss.plugin.allTimers.add(t);
            }
        }
    }
}
