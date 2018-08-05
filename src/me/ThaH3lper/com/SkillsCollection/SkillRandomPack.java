package me.ThaH3lper.com.SkillsCollection;

import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.*;
import org.bukkit.event.*;
import java.lang.reflect.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.Mobs.*;

public class SkillRandomPack
{
    public static void ExecuteRandomPack(final LivingEntity l, final String skill, final Player p) {
        final String[] base = skill.split(" ");
        final float chance = Float.parseFloat(base[base.length - 1]);
        if (EpicBoss.r.nextFloat() < chance && SkillHandler.CheckHealth(base[base.length - 2], l, skill)) {
            final BossSkillEvent event = new BossSkillEvent(l, skill, null, false);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (event.isChanceled()) {
                return;
            }
            final String[] packs = base[1].split(",");
            final int num = Array.getLength(packs);
            final String pack = packs[EpicBoss.r.nextInt(num)];
            final EpicSkill es = getPack(pack);
            if (es != null) {
                if (onCooldown(l, pack)) {
                    String packsNew = "randompack ";
                    String[] array;
                    for (int length = (array = packs).length, i = 0; i < length; ++i) {
                        final String pN = array[i];
                        if (pN != pack) {
                            packsNew = String.valueOf(packsNew) + pN + ",";
                        }
                    }
                    packsNew = String.valueOf(packsNew) + " >0 1";
                    ExecuteRandomPack(l, packsNew, p);
                }
                else {
                    SkillHandler.ExecutePackSkills(es.skills, l, p);
                    setCooldown(l, pack, es.cooldown);
                }
            }
        }
    }
    
    public static EpicSkill getPack(final String s) {
        for (final EpicSkill es : EpicBoss.plugin.listSkills) {
            if (es.cmdName.equals(s)) {
                return es;
            }
        }
        return null;
    }
    
    public static boolean onCooldown(final LivingEntity l, final String skill) {
        final EpicMobs em = MobCommon.getEpicMob(l);
        if (em == null) {
            return true;
        }
        final Long next = em.cooldowns.get(String.valueOf(skill) + l.getEntityId());
        return next != null && next > System.currentTimeMillis();
    }
    
    public static void setCooldown(final LivingEntity l, final String skill, final int cooldown) {
        final EpicMobs em = MobCommon.getEpicMob(l);
        if (em == null) {
            return;
        }
        if (cooldown > 0) {
            em.cooldowns.put(String.valueOf(skill) + l.getEntityId(), System.currentTimeMillis() + cooldown * 1000);
        }
        else {
            em.cooldowns.remove(String.valueOf(skill) + l.getEntityId());
        }
    }
}
