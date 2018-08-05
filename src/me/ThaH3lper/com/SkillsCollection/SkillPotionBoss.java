package me.ThaH3lper.com.SkillsCollection;

import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.potion.*;

public class SkillPotionBoss
{
    public static void ExecutePotionBoss(final LivingEntity l, final String skill, final Player player) {
        final String[] base = skill.split(" ");
        final String[] data = base[1].split(":");
        final float chance = Float.parseFloat(base[base.length - 1]);
        if (EpicBoss.r.nextFloat() < chance && SkillHandler.CheckHealth(base[base.length - 2], l, skill)) {
            final BossSkillEvent event = new BossSkillEvent(l, skill, player, false);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (event.isChanceled()) {
                return;
            }
            final String pType = data[0];
            final float pDuration = Float.parseFloat(data[1]);
            final int pLevel = Integer.parseInt(data[2]) - 1;
            final PotionEffect pe = new PotionEffect(PotionEffectType.getByName(pType), (int)(pDuration * 20.0f), pLevel);
            l.addPotionEffect(pe);
        }
    }
}
