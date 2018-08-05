package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.Libs.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.*;

public class SkillBossFirework
{
    static FireWorkEffect effect;
    
    static {
        SkillBossFirework.effect = new FireWorkEffect();
    }
    
    public static void ExecuteBossFirework(final LivingEntity l, final String skill) {
        final String[] base = skill.split(" ");
        final String[] data = base[1].split(":");
        final float chance = Float.parseFloat(base[base.length - 1]);
        if (EpicBoss.r.nextFloat() < chance && SkillHandler.CheckHealth(base[base.length - 2], l, skill)) {
            final BossSkillEvent event = new BossSkillEvent(l, skill, null, false);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (event.isChanceled()) {
                return;
            }
            final boolean flicker = Boolean.valueOf(data[2]);
            final boolean trail = Boolean.valueOf(data[3]);
            final String[] c = data[1].split(",");
            final Color color = Color.fromRGB(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2]));
            final FireworkEffect.Type type = FireworkEffect.Type.valueOf(data[0]);
            if (color != null && type != null) {
                try {
                    SkillBossFirework.effect.playFirework(l.getWorld(), l.getLocation(), FireworkEffect.builder().with(type).withColor(color).trail(trail).flicker(flicker).build());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
