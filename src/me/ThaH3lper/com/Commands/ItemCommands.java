package me.ThaH3lper.com.Commands;

import org.bukkit.command.*;
import me.ThaH3lper.com.*;
import org.bukkit.entity.*;
import me.ThaH3lper.com.Items.*;
import org.bukkit.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;

public class ItemCommands
{
    public static void getItem(final CommandSender sender, final Command cmd, final String commandlabel, final String[] args) {
        if (args.length == 1) {
            sender.sendMessage(EpicBoss.plugin.menu);
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb item list" + ChatColor.GREEN + ChatColor.ITALIC + " - List all items loaded");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb item drop" + ChatColor.GREEN + ChatColor.ITALIC + " - drops all items on ground around you");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb item list [word]" + ChatColor.GREEN + ChatColor.ITALIC + " - List all items whit matching word");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb item info [name]" + ChatColor.GREEN + ChatColor.ITALIC + " - Show info about the item");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb item get [item]" + ChatColor.GREEN + ChatColor.ITALIC + " - get the item");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb item give [item] [player]" + ChatColor.GREEN + ChatColor.ITALIC + " - get player a item");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eb item show [Filename]" + ChatColor.GREEN + ChatColor.ITALIC + " - shows all items from that file [Example.yml]");
        }
        else if (args.length == 2) {
            if (args[1].equalsIgnoreCase("list")) {
                String s = ChatColor.LIGHT_PURPLE + "All items: ";
                sender.sendMessage(ChatColor.GREEN + "Items dropped");
                for (final EpicItems ei : EpicBoss.plugin.listItems) {
                    s = String.valueOf(s) + ChatColor.RED + ei.cmdName + ChatColor.GRAY + ", ";
                }
                sender.sendMessage(s);
            }
            else if (args[1].equalsIgnoreCase("drop")) {
                if (sender instanceof Player) {
                    sender.sendMessage(ChatColor.GREEN + "Items dropped");
                    final Player p = (Player)sender;
                    for (final EpicItems ei : EpicBoss.plugin.listItems) {
                        p.getWorld().dropItem(p.getLocation(), ei.getItemStack());
                    }
                }
                else {
                    sender.sendMessage(ChatColor.RED + "Can only be used from player!");
                }
            }
        }
        else if (args.length == 3) {
            if (args[1].equalsIgnoreCase("list")) {
                String s = ChatColor.LIGHT_PURPLE + "Items Found: ";
                for (final EpicItems ei : EpicBoss.plugin.listItems) {
                    if (ei.cmdName.contains(args[2])) {
                        final String[] parts = ei.cmdName.split(args[2]);
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
            else if (args[1].equalsIgnoreCase("info")) {
                if (ItemHandler.getEpicItem(args[2]) != null) {
                    final EpicItems ei2 = ItemHandler.getEpicItem(args[2]);
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "File: " + ChatColor.RED + ei2.file);
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "CmdName: " + ChatColor.RED + ei2.cmdName);
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "Id: " + ChatColor.RED + ei2.id + ChatColor.GRAY + " / " + ChatColor.RED + Material.getMaterial(ei2.id));
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "Data: " + ChatColor.RED + ei2.data + ChatColor.LIGHT_PURPLE + " Amount: " + ChatColor.RED + ei2.amount);
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "Display: " + ChatColor.RED + ei2.Display + ChatColor.GRAY + " / " + ChatColor.translateAlternateColorCodes('&', ei2.Display));
                }
                else {
                    sender.sendMessage(ChatColor.RED + "There is no item called " + args[2]);
                }
            }
            else if (args[1].equalsIgnoreCase("get")) {
                if (sender instanceof Player) {
                    final Player p = (Player)sender;
                    if (ItemHandler.getEpicItem(args[2]) != null) {
                        final EpicItems ei = ItemHandler.getEpicItem(args[2]);
                        p.getInventory().addItem(new ItemStack[] { ei.getItemStack() });
                        sender.sendMessage(ChatColor.GREEN + "Item Given");
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + "There is no item called " + args[2]);
                    }
                }
                else {
                    sender.sendMessage(ChatColor.RED + "This command needs to be executed by a Player");
                }
            }
            else if (args[1].equalsIgnoreCase("show")) {
                if (sender instanceof Player) {
                    final Player p = (Player)sender;
                    final Inventory i = Bukkit.getServer().createInventory((InventoryHolder)null, InventoryType.CHEST);
                    for (final EpicItems ie : EpicBoss.plugin.listItems) {
                        if (ie.file.equals(args[2])) {
                            i.addItem(new ItemStack[] { ie.getItemStack() });
                        }
                    }
                    if (i.firstEmpty() != 0) {
                        p.openInventory(i);
                    }
                    else {
                        p.sendMessage(ChatColor.RED + args[2] + " file don't excist or has no Items!");
                    }
                }
                else {
                    sender.sendMessage(ChatColor.RED + "This command needs to be executed by a Player");
                }
            }
        }
        else if (args.length == 4 && args[1].equalsIgnoreCase("give")) {
            if (ItemHandler.getEpicItem(args[2]) != null) {
                if (Bukkit.getPlayer(args[3]) != null) {
                    final Player p = Bukkit.getPlayer(args[3]);
                    if (p.isOnline()) {
                        final EpicItems ei = ItemHandler.getEpicItem(args[2]);
                        p.getInventory().addItem(new ItemStack[] { ei.getItemStack() });
                        sender.sendMessage(ChatColor.GREEN + "Item Given");
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + "The player " + args[3] + " is not online!");
                    }
                }
                else {
                    sender.sendMessage(ChatColor.RED + "There is no player called " + args[3]);
                }
            }
            else {
                sender.sendMessage(ChatColor.RED + "There is no item called " + args[2]);
            }
        }
    }
}
