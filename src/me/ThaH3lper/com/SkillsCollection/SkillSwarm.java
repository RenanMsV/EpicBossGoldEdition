package me.ThaH3lper.com.SkillsCollection;

import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.event.*;
import org.bukkit.*;
import me.ThaH3lper.com.Mobs.*;

public class SkillSwarm
{
    public static void ExecuteSwarm(final LivingEntity l, final String skill, final Player player) {
        final String[] base = skill.split(" ");
        final String[] data = base[1].split(":");
        final float chance = Float.parseFloat(base[base.length - 1]);
        if (EpicBoss.r.nextFloat() < chance && SkillHandler.CheckHealth(base[base.length - 2], l, skill)) {
            final BossSkillEvent event = new BossSkillEvent(l, skill, player, false);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (event.isChanceled()) {
                return;
            }
            final int amount = Integer.parseInt(data[1]);
            final int radious = Integer.parseInt(data[2]);
            if (data[0].contains("$")) {
                final String s = data[0].replace("$", "");
                final EpicMobs em = MobCommon.getEpicMob(s);
                if (em != null) {
                    for (int i = 1; i <= amount; ++i) {
                        final double x = l.getLocation().getX() - radious + EpicBoss.r.nextInt(radious * 2);
                        final double z = l.getLocation().getZ() - radious + EpicBoss.r.nextInt(radious * 2);
                        final Location loc = new Location(l.getWorld(), x, l.getLocation().getY(), z);
                        MobHandler.SpawnMob(s, loc);
                    }
                }
            }
            else {
                for (int j = 0; j <= amount; ++j) {
                    final double x2 = l.getLocation().getX() - radious + EpicBoss.r.nextInt(radious * 2);
                    final double z2 = l.getLocation().getZ() - radious + EpicBoss.r.nextInt(radious * 2);
                    final Location loc2 = new Location(l.getWorld(), x2, l.getLocation().getY(), z2);
                    AllMobs.spawnMob(data[0], loc2);
                }
            }
        }
    }
}
