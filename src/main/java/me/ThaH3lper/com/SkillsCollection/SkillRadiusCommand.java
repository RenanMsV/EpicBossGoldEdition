package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import org.bukkit.entity.*;

public class SkillRadiusCommand
{
    public static void ExecuteCommand(final LivingEntity l, final String skill) {
        final String[] base = skill.split(" ");
        final String[] data = base[1].split(":");
        final float chance = Float.parseFloat(base[base.length - 1]);
        if (EpicBoss.r.nextFloat() < chance && SkillHandler.CheckHealth(base[base.length - 2], l, skill)) {
            final int radious = Integer.parseInt(data[0]);
            if (radious != 0) {
                for (final Player p : SkillHandler.getRadious(l, radious)) {
                    SkillCommand.ExecuteCommand(l, skill, p);
                }
            }
        }
    }
}
