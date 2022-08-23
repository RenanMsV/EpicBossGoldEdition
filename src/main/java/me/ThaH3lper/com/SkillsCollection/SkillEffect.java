package me.ThaH3lper.com.SkillsCollection;

import java.util.logging.*;
import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.event.*;
import org.bukkit.*;
import me.ThaH3lper.com.SkillsCollection.EffectsCollection.*;

public class SkillEffect
{
    public static Logger logger;
    
    static {
        SkillEffect.logger = Logger.getLogger("Minecraft");
    }
    
    public static void ExecuteEffect(final LivingEntity l, final String skill, final Player player) {
        final String[] base = skill.split(" ");
        final float chance = Float.parseFloat(base[base.length - 1]);
        if (EpicBoss.r.nextFloat() < chance && SkillHandler.CheckHealth(base[base.length - 2], l, skill)) {
            final BossSkillEvent event = new BossSkillEvent(l, skill, null, false);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (event.isChanceled()) {
                return;
            }
            final String location = base[1];
            final String effect = base[2];
            final String effectdata = base[3];
            if (location.equals("boss")) {
                EffectHandler(l.getLocation(), l, effect, effectdata);
            }
            else if (location.equals("player")) {
                EffectHandler(player.getLocation(), l, effect, effectdata);
            }
        }
    }
    
    public static void EffectHandler(final Location Loc, final LivingEntity l, final String effect, final String effectdata) {
        if (effect.equals("sound")) {
            EffectSound.PlaySound(Loc, effectdata);
        }
    }
}
