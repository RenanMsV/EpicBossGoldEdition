package me.ThaH3lper.com.SkillsCollection;

import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class SkillHeal
{
    public static void ExecuteHeal(final LivingEntity l, final String skill, final Player player) {
        final String[] base = skill.split(" ");
        final float chance = Float.parseFloat(base[base.length - 1]);
        if (EpicBoss.r.nextFloat() < chance && SkillHandler.CheckHealth(base[base.length - 2], l, skill)) {
            final BossSkillEvent event = new BossSkillEvent(l, skill, player, false);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (event.isChanceled()) {
                return;
            }
            final double amount = Double.parseDouble(base[1]);
            final double health = l.getHealth() + amount;
            if (health >= l.getMaxHealth()) {
                l.setHealth(l.getMaxHealth());
            }
            else {
                l.setHealth(health);
            }
        }
    }
}
