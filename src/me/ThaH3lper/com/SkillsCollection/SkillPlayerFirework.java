package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.Libs.*;
import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.event.*;
import org.bukkit.*;

public class SkillPlayerFirework
{
    static FireWorkEffect effect;
    
    static {
        SkillPlayerFirework.effect = new FireWorkEffect();
    }
    
    public static void ExecutePlayerFirework(final LivingEntity l, final String skill, final Player p) {
        final String[] base = skill.split(" ");
        final String[] data = base[1].split(":");
        final float chance = Float.parseFloat(base[base.length - 1]);
        if (EpicBoss.r.nextFloat() < chance && SkillHandler.CheckHealth(base[base.length - 2], l, skill)) {
            final BossSkillEvent event = new BossSkillEvent(l, skill, p, false);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (event.isChanceled()) {
                return;
            }
            final int radious = Integer.parseInt(data[0]);
            final boolean flicker = Boolean.valueOf(data[3]);
            final boolean trail = Boolean.valueOf(data[4]);
            final String[] c = data[2].split(",");
            final Color color = Color.fromRGB(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2]));
            final FireworkEffect.Type type = FireworkEffect.Type.valueOf(data[1]);
            if (color != null && type != null) {
                if (radious == 0) {
                    if (p != null) {
                        try {
                            SkillPlayerFirework.effect.playFirework(p.getWorld(), p.getLocation(), FireworkEffect.builder().with(type).withColor(color).trail(trail).flicker(flicker).build());
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else {
                    for (final Player pla : SkillHandler.getRadious(l, radious)) {
                        try {
                            SkillPlayerFirework.effect.playFirework(pla.getWorld(), pla.getLocation(), FireworkEffect.builder().with(type).withColor(color).trail(trail).flicker(flicker).build());
                        }
                        catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
