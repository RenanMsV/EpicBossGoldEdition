package me.ThaH3lper.com.SkillsCollection;

import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.event.*;
import org.bukkit.*;
import org.bukkit.command.*;
import java.util.regex.*;
import me.ThaH3lper.com.Mobs.*;

public class SkillCommand
{
    public static void ExecuteCommand(final LivingEntity l, final String skill, final Player player) {
        final String[] temp = skill.split("'");
        String msg = temp[1];
        final String[] base = skill.split(" ");
        final float chance = Float.parseFloat(base[base.length - 1]);
        int rand = 0;
        if (EpicBoss.r.nextFloat() < chance && SkillHandler.CheckHealth(base[base.length - 2], l, skill)) {
            final BossSkillEvent event = new BossSkillEvent(l, skill, player, false);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (event.isChanceled()) {
                return;
            }
            if (msg.contains("$player_x") && player != null) {
                if (msg.contains("$player_x%")) {
                    final Pattern Rpattern = Pattern.compile("player_x%([0-9]+)");
                    final Matcher Rmatcher = Rpattern.matcher(msg);
                    Rmatcher.find();
                    rand = EpicBoss.r.nextInt(2);
                    rand = ((rand == 1) ? EpicBoss.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - EpicBoss.r.nextInt(Integer.parseInt(Rmatcher.group(1)))));
                    msg = msg.replace("$player_x%" + Rmatcher.group(1), Integer.toString(player.getLocation().getBlockX() + rand));
                }
                else {
                    msg = msg.replace("$player_x", Integer.toString(player.getLocation().getBlockX()));
                }
            }
            if (msg.contains("$player_y") && player != null) {
                if (msg.contains("$player_y%")) {
                    final Pattern Rpattern = Pattern.compile("player_y%([0-9]+)");
                    final Matcher Rmatcher = Rpattern.matcher(msg);
                    Rmatcher.find();
                    rand = EpicBoss.r.nextInt(2);
                    rand = ((rand == 1) ? EpicBoss.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - EpicBoss.r.nextInt(Integer.parseInt(Rmatcher.group(1)))));
                    msg = msg.replace("$player_y%" + Rmatcher.group(1), Integer.toString(player.getLocation().getBlockY() + rand));
                }
                else {
                    msg = msg.replace("$player_y", Integer.toString(player.getLocation().getBlockY()));
                }
            }
            if (msg.contains("$player_z") && player != null) {
                if (msg.contains("$player_z%")) {
                    final Pattern Rpattern = Pattern.compile("player_z%([0-9]+)");
                    final Matcher Rmatcher = Rpattern.matcher(msg);
                    Rmatcher.find();
                    rand = EpicBoss.r.nextInt(2);
                    rand = ((rand == 1) ? EpicBoss.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - EpicBoss.r.nextInt(Integer.parseInt(Rmatcher.group(1)))));
                    msg = msg.replace("$player_z%" + Rmatcher.group(1), Integer.toString(player.getLocation().getBlockZ() + rand));
                }
                else {
                    msg = msg.replace("$player_z", Integer.toString(player.getLocation().getBlockZ()));
                }
            }
            if (msg.contains("$player") && player != null) {
                msg = msg.replace("$player", player.getName());
            }
            if (msg.contains("$boss_x")) {
                if (msg.contains("$boss_x%")) {
                    final Pattern Rpattern = Pattern.compile("boss_x%([0-9]+)");
                    final Matcher Rmatcher = Rpattern.matcher(msg);
                    Rmatcher.find();
                    rand = EpicBoss.r.nextInt(2);
                    rand = ((rand == 1) ? EpicBoss.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - EpicBoss.r.nextInt(Integer.parseInt(Rmatcher.group(1)))));
                    msg = msg.replace("$boss_x%" + Rmatcher.group(1), Integer.toString(l.getLocation().getBlockX() + rand));
                }
                else {
                    msg = msg.replace("$boss_x", Integer.toString(l.getLocation().getBlockX()));
                }
            }
            if (msg.contains("$boss_y")) {
                if (msg.contains("$boss_y%")) {
                    final Pattern Rpattern = Pattern.compile("boss_y%([0-9]+)");
                    final Matcher Rmatcher = Rpattern.matcher(msg);
                    Rmatcher.find();
                    rand = EpicBoss.r.nextInt(2);
                    rand = ((rand == 1) ? EpicBoss.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - EpicBoss.r.nextInt(Integer.parseInt(Rmatcher.group(1)))));
                    msg = msg.replace("$boss_y%" + Rmatcher.group(1), Integer.toString(l.getLocation().getBlockY() + rand));
                }
                else {
                    msg = msg.replace("$boss_y", Integer.toString(l.getLocation().getBlockY()));
                }
            }
            if (msg.contains("$boss_z")) {
                if (msg.contains("$boss_z%")) {
                    final Pattern Rpattern = Pattern.compile("boss_z%([0-9]+)");
                    final Matcher Rmatcher = Rpattern.matcher(msg);
                    Rmatcher.find();
                    rand = EpicBoss.r.nextInt(2);
                    rand = ((rand == 1) ? EpicBoss.r.nextInt(1 + Integer.parseInt(Rmatcher.group(1))) : (0 - EpicBoss.r.nextInt(Integer.parseInt(Rmatcher.group(1)))));
                    msg = msg.replace("$boss_z%" + Rmatcher.group(1), Integer.toString(l.getLocation().getBlockZ() + rand));
                }
                else {
                    msg = msg.replace("$boss_z", Integer.toString(l.getLocation().getBlockZ()));
                }
            }
            if (msg.contains("$boss")) {
                final EpicMobs em = MobCommon.getEpicMob(l);
                msg = msg.replace("$boss", ChatColor.translateAlternateColorCodes('&', em.Display));
            }
            if (msg.contains("$world")) {
                msg = msg.replace("$world", l.getWorld().getName());
            }
            if (msg.contains("$buuid")) {
            	msg = msg.replace("$buuid", l.getUniqueId().toString());
            }
            Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), msg);
        }
    }
}
