package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.Libs.*;
import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.*;
import org.bukkit.event.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.Mobs.*;

public class SkillPack
{
    static FireWorkEffect effect;
    
    static {
        SkillPack.effect = new FireWorkEffect();
    }
    
    public static void ExecutePack(final LivingEntity l, final String skill, final Player p) {
        final String[] base = skill.split(" ");
        final float chance = Float.parseFloat(base[base.length - 1]);
        if (EpicBoss.r.nextFloat() < chance && SkillHandler.CheckHealth(base[base.length - 2], l, skill)) {
            final BossSkillEvent event = new BossSkillEvent(l, skill, null, false);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (event.isChanceled()) {
                return;
            }
            final EpicSkill es = getPack(base[1]);
            if (es != null) {
                if (onCooldown(l, base[1])) {
                    return;
                }
                SkillHandler.ExecutePackSkills(es.skills, l, p);
                setCooldown(l, base[1], es.cooldown);
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
