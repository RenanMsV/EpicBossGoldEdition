package me.ThaH3lper.com.Commands;

import org.bukkit.command.*;
import me.ThaH3lper.com.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.*;
import org.bukkit.entity.*;
import me.ThaH3lper.com.Mobs.*;
import java.util.*;
import org.bukkit.*;
import me.ThaH3lper.com.Location.*;

public class MobCommands
{
    public static void getMob(final CommandSender sender, final Command cmd, final String commandlabel, final String[] args) {
        if (args.length == 1) {
            sender.sendMessage(EpicBoss.plugin.menu);
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb mob list" + ChatColor.GREEN + ChatColor.ITALIC + " - list all mobs");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb mob list [word]" + ChatColor.GREEN + ChatColor.ITALIC + " - search for a mob");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb mob info [MobName]" + ChatColor.GREEN + ChatColor.ITALIC + " - show info about boss");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb mob killall" + ChatColor.GREEN + ChatColor.ITALIC + " - remove all bosses");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb mob kill [word]" + ChatColor.GREEN + ChatColor.ITALIC + " - kill bosses that contains word");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb mob spawn [MobName]" + ChatColor.GREEN + ChatColor.ITALIC + " - Spawn a mob");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb mob spawn [MobName] [Amount]" + ChatColor.GREEN + ChatColor.ITALIC + " - spawn mobs");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb mob spawn [MobName] [Amount] [world,x,y,z]" + ChatColor.GREEN + ChatColor.ITALIC + " - spawn mobs at cords");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb mob spawn [MobName] [Amount] [loc]" + ChatColor.GREEN + ChatColor.ITALIC + " - spawn mobs at location");
        }
        else if (args.length == 2) {
            if (args[1].equalsIgnoreCase("list")) {
                String s = ChatColor.LIGHT_PURPLE + "Mobs Loaded: ";
                for (final EpicMobs em : EpicBoss.plugin.listMobs) {
                    s = String.valueOf(s) + ChatColor.RED + em.cmdName + ChatColor.GRAY + ", ";
                }
                for (final EpicMobsList el : EpicBoss.plugin.listMobslist) {
                    s = String.valueOf(s) + ChatColor.RED + el.cmdName + ChatColor.GRAY + ", ";
                }
                sender.sendMessage(s);
            }
            else if (args[1].equalsIgnoreCase("killall")) {
                for (final LivingEntity l : EpicBoss.plugin.getMobsAll()) {
                    final EntityDeathEvent event = new EntityDeathEvent(l, (List)new ArrayList(0));
                    EpicBoss.plugin.getServer().getPluginManager().callEvent((Event)event);
                    l.remove();
                }
                sender.sendMessage(ChatColor.GREEN + "All bosses removed!");
            }
        }
        else if (args.length == 3) {
            if (args[1].equalsIgnoreCase("list")) {
                String s = ChatColor.LIGHT_PURPLE + "Mobs Found: ";
                for (final EpicMobs em : EpicBoss.plugin.listMobs) {
                    if (em.cmdName.contains(args[2])) {
                        final String[] parts = em.cmdName.split(args[2]);
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
                for (final EpicMobsList el : EpicBoss.plugin.listMobslist) {
                    if (el.cmdName.contains(args[2])) {
                        final String[] parts = el.cmdName.split(args[2]);
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
            if (args[1].equalsIgnoreCase("spawn")) {
                if (sender instanceof Player) {
                    final Player p = (Player)sender;
                    final LivingEntity i = MobHandler.SpawnMob(args[2], p.getLocation());
                    if (i != null) {
                        sender.sendMessage(ChatColor.GREEN + "Mob Spawned!");
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + "There is no mob called " + args[2]);
                    }
                }
                else {
                    sender.sendMessage(ChatColor.RED + "This Command is only for Players! not for Consoles!");
                }
            }
            if (args[1].equalsIgnoreCase("kill")) {
                String s = ChatColor.GREEN + "Mobs Killed: ";
                for (final LivingEntity i : EpicBoss.plugin.getMobsAll()) {
                    final EpicMobs em2 = MobCommon.getEpicMob(i);
                    if (em2 != null && em2.cmdName.contains(args[2])) {
                        final EntityDeathEvent event2 = new EntityDeathEvent(i, (List)new ArrayList(0));
                        EpicBoss.plugin.getServer().getPluginManager().callEvent((Event)event2);
                        i.remove();
                    }
                }
                for (final EpicMobs em : EpicBoss.plugin.listMobs) {
                    if (em.cmdName.contains(args[2])) {
                        final String[] parts = em.cmdName.split(args[2]);
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
            if (args[1].equalsIgnoreCase("info")) {
                if (MobCommon.getEpicMob(args[2]) != null) {
                    final EpicMobs em3 = MobCommon.getEpicMob(args[2]);
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "File: " + ChatColor.RED + em3.file);
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "CmdName: " + ChatColor.RED + em3.cmdName);
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "Display: " + ChatColor.RED + em3.Display + ChatColor.GRAY + " / " + ChatColor.translateAlternateColorCodes('&', em3.Display));
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "Type: " + ChatColor.RED + em3.Mobtype);
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "MaxHealth: " + ChatColor.RED + em3.health);
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "Damage: " + ChatColor.RED + em3.damage);
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "Despawn: " + ChatColor.RED + em3.despawn);
                }
                else {
                    sender.sendMessage(ChatColor.RED + "There is no mob called " + args[2]);
                }
            }
        }
        else if (args.length == 4) {
            if (args[1].equalsIgnoreCase("spawn") && sender instanceof Player) {
                final Player p = (Player)sender;
                final int m = Integer.parseInt(args[3]);
                LivingEntity j = null;
                for (int k = 0; k < m; ++k) {
                    j = MobHandler.SpawnMob(args[2], p.getLocation());
                }
                if (j != null) {
                    sender.sendMessage(ChatColor.GREEN + "Mobs Spawned!");
                }
                else {
                    sender.sendMessage(ChatColor.RED + "There is no mob called " + args[2]);
                }
            }
        }
        else if (args.length == 5) {
            if (args[1].equalsIgnoreCase("spawn") && args[4].contains(",")) {
                final String[] part = args[4].split(",");
                final World w = Bukkit.getWorld(part[0]);
                final float x = Float.parseFloat(part[1]);
                final float y = Float.parseFloat(part[2]);
                final float z = Float.parseFloat(part[3]);
                if (w != null) {
                    final Location loc = new Location(w, (double)x, (double)y, (double)z);
                    final int m2 = Integer.parseInt(args[3]);
                    LivingEntity l2 = null;
                    for (int i2 = 0; i2 < m2; ++i2) {
                        l2 = MobHandler.SpawnMob(args[2], loc);
                    }
                    if (l2 != null) {
                        sender.sendMessage(ChatColor.GREEN + "Mobs Spawned!");
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + "There is no mob called " + args[2]);
                    }
                }
                else {
                    sender.sendMessage(ChatColor.RED + "There is no World called " + part[0]);
                }
            }
            else if (args[1].equalsIgnoreCase("spawn")) {
                final EpicLocation el2 = LocationHandler.getEpicLocation(args[4]);
                if (el2 != null) {
                    final int m = Integer.parseInt(args[3]);
                    LivingEntity j = null;
                    for (int k = 0; k < m; ++k) {
                        j = MobHandler.SpawnMob(args[2], el2.location);
                    }
                    if (j != null) {
                        sender.sendMessage(ChatColor.GREEN + "Mobs Spawned!");
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + "There is no mob called " + args[2]);
                    }
                }
                else {
                    sender.sendMessage(ChatColor.RED + "There is no Location called " + args[4]);
                }
            }
        }
    }
}
