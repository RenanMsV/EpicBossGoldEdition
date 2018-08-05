package me.ThaH3lper.com.SkillsCollection;

import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.event.*;
import org.bukkit.*;
import me.ThaH3lper.com.Mobs.*;
import java.util.*;

public class SkillMsg
{
    public static void ExecuteMsg(final LivingEntity l, final String skill, final Player player) {
    	final String[] temp = skill.split("'");
        String msg = temp[1];
        final String[] base = skill.split(" ");
        final String[] data = base[1].split(":");
        final float chance = Float.parseFloat(base[base.length - 1]);
        if (EpicBoss.r.nextFloat() < chance && SkillHandler.CheckHealth(base[base.length - 2], l, skill)) {
            final BossSkillEvent event = new BossSkillEvent(l, skill, player, false);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (event.isChanceled()) {
                return;
            }
            final int radious = Integer.parseInt(data[0]);
            if (msg.contains("$player")) {
                if (player == null) {
                    return;
                }
                msg = msg.replace("$player", player.getName());
            }
            if (msg.contains("$bosshp")) {
                msg = msg.replace("$bosshp", String.valueOf(l.getHealth()));
            }
            if (msg.contains("$boss")) {
                final EpicMobs em = MobCommon.getEpicMob(l);
                msg = msg.replace("$boss", em.Display);
            }
            msg = ChatColor.translateAlternateColorCodes('&', msg);
            if (radious != 0) {
                for (final Player p : SkillHandler.getRadious(l, radious)) {
                    p.sendMessage(msg);
                }
            }
            else {
                Bukkit.broadcastMessage(msg);
            }
        }
    }
}
