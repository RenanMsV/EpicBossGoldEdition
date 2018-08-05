package me.ThaH3lper.com.Listener;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Timer.*;
import me.ThaH3lper.com.Timer.Timer;
import me.ThaH3lper.com.Location.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.*;
import java.util.*;

public class SignPlace implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void SignChange(final SignChangeEvent e) {
        if (e.getLine(0).equalsIgnoreCase("epictimer")) {
            final EpicTimer et = TimerHandler.getEpicTimer(e.getLine(1));
            final EpicLocation el = LocationHandler.getEpicLocation(e.getLine(2));
            if (et != null && el != null && (e.getPlayer().hasPermission("epicboss.admin") || e.getPlayer().hasPermission("epicboss.timer"))) {
                EpicBoss.plugin.allTimers.add(new Timer(e.getBlock().getLocation(), et, el, e.getLines()));
                e.getPlayer().sendMessage(ChatColor.GREEN + "Timer Created!");
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void RemoveTimer(final BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.SIGN_POST || e.getBlock().getType() == Material.WALL_SIGN) {
            Timer timer = null;
            for (final Timer t : EpicBoss.plugin.allTimers) {
                if (t.loc.equals((Object)e.getBlock().getLocation())) {
                    timer = t;
                }
            }
            if (timer != null) {
                if (e.getPlayer().hasPermission("epicboss.admin") || e.getPlayer().hasPermission("epicboss.timer")) {
                    EpicBoss.plugin.allTimers.remove(timer);
                    e.getPlayer().sendMessage(ChatColor.RED + "Timer Removed!");
                }
                else {
                    e.setCancelled(true);
                }
            }
        }
    }
}
