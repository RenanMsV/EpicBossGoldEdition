package me.ThaH3lper.com.SkillsCollection;

import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class SkillCustom
{
    public static void ExecuteCustom(final LivingEntity l, final String skill, final Player player) {
        final String[] base = skill.split(" ");
        final float chance = Float.parseFloat(base[base.length - 1]);
        if (EpicBoss.r.nextFloat() < chance && SkillHandler.CheckHealth(base[base.length - 2], l, skill)) {
            final BossSkillEvent event = new BossSkillEvent(l, skill, player, true);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
        }
    }
}
