package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.entity.*;
import java.util.*;
import me.ThaH3lper.com.Mobs.*;

public class SkillNewTarget
{
    public static void ExecuteNewTarget(final LivingEntity l, final String skill) {
        final String[] base = skill.split(" ");
        final String[] data = base[1].split(":");
        final float chance = Float.parseFloat(base[base.length - 1]);
        if (EpicBoss.r.nextFloat() < chance && SkillHandler.CheckHealth(base[base.length - 2], l, skill)) {
            final BossSkillEvent event = new BossSkillEvent(l, skill, null, false);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (event.isChanceled()) {
                return;
            }
            final int radius = Integer.parseInt(data[0]);
            if (radius > 0) {
                final List<Player> players = SkillHandler.getRadious(l, radius);
                if (players.size() < 2) {
                    return;
                }
                final Player newtarget = players.get(EpicBoss.r.nextInt(players.size()));
                final EpicMobs em = MobCommon.getEpicMob(l);
                ((Creature)l).setTarget((LivingEntity)newtarget);
                em.targetChanger = newtarget;
            }
        }
    }
}
