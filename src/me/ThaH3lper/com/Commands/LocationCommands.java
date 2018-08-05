package me.ThaH3lper.com.Commands;

import org.bukkit.command.*;
import me.ThaH3lper.com.*;
import org.bukkit.entity.*;
import me.ThaH3lper.com.SaveLoad.Load.*;
import me.ThaH3lper.com.Location.*;
import java.util.*;
import org.bukkit.*;

public class LocationCommands
{
    public static void getLoc(final CommandSender sender, final Command cmd, final String commandlabel, final String[] args) {
        if (args.length == 1) {
            sender.sendMessage(EpicBoss.plugin.menu);
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb loc list" + ChatColor.GREEN + ChatColor.ITALIC + " - list all locations");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb loc list [word]" + ChatColor.GREEN + ChatColor.ITALIC + " - search for location");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb loc add [name]" + ChatColor.GREEN + ChatColor.ITALIC + " - add new location");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb loc remove [name]" + ChatColor.GREEN + ChatColor.ITALIC + " - remove location");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb loc teleport [name]" + ChatColor.GREEN + ChatColor.ITALIC + " - teleport to location");
        }
        else if (args.length == 2) {
            if (args[1].equalsIgnoreCase("list")) {
                String s = ChatColor.LIGHT_PURPLE + "Locations: ";
                for (final EpicLocation el : EpicBoss.plugin.listLoc) {
                    s = String.valueOf(s) + ChatColor.RED + el.name + ChatColor.GRAY + ", ";
                }
                sender.sendMessage(s);
            }
        }
        else if (args.length == 3) {
            if (args[1].equalsIgnoreCase("list")) {
                String s = ChatColor.LIGHT_PURPLE + "Locations Found: ";
                for (final EpicLocation el : EpicBoss.plugin.listLoc) {
                    if (el.name.contains(args[2])) {
                        final String[] parts = el.name.split(args[2]);
                        if (parts.length == 2) {
                            s = String.valueOf(s) + ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.RED + parts[1] + ChatColor.GRAY + ", ";
                        }
                        if (parts.length == 1) {
                            s = String.valueOf(s) + ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", ";
                        }
                        if (parts.length != 0) {
                            continue;
                        }
                        s = String.valueOf(s) + ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", ";
                    }
                }
                sender.sendMessage(s);
            }
            else if (args[1].equalsIgnoreCase("add")) {
                if (sender instanceof Player) {
                    final Player p = (Player)sender;
                    final String name = args[2];
                    final Location l = p.getLocation();
                    EpicBoss.plugin.listLoc.add(new EpicLocation(l, name));
                    LoadLocation.saveAllLocations();
                    p.sendMessage(ChatColor.GREEN + "Location Created!" + ChatColor.GRAY + " [" + ChatColor.RED + name + ChatColor.GRAY + "]");
                }
                else {
                    sender.sendMessage(ChatColor.RED + "This Command is only for Players! not for Consoles!");
                }
            }
            else if (args[1].equalsIgnoreCase("remove")) {
                final String name2 = args[2];
                final EpicLocation el = LocationHandler.getEpicLocation(name2);
                if (el != null) {
                    EpicBoss.plugin.listLoc.remove(el);
                    LoadLocation.saveAllLocations();
                    sender.sendMessage(ChatColor.GREEN + "Location Removed!" + ChatColor.GRAY + " [" + ChatColor.RED + name2 + ChatColor.GRAY + "]");
                }
                else {
                    sender.sendMessage(ChatColor.RED + "Location " + name2 + " do not excist!");
                }
            }
            else if (args[1].equalsIgnoreCase("teleport") && sender instanceof Player) {
                final Player p = (Player)sender;
                final String name = args[2];
                final EpicLocation el2 = LocationHandler.getEpicLocation(name);
                if (el2 != null) {
                    p.teleport(el2.location);
                }
                else {
                    sender.sendMessage(ChatColor.RED + "Location " + name + " do not excist!");
                }
            }
        }
    }
}
