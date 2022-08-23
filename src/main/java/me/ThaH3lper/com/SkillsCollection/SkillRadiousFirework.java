package me.ThaH3lper.com.SkillsCollection;

import me.ThaH3lper.com.Libs.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.API.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.*;
import java.util.*;

public class SkillRadiousFirework
{
    static FireWorkEffect effect;
    
    static {
        SkillRadiousFirework.effect = new FireWorkEffect();
    }
    
    public static void ExecuteRadiousFirework(final LivingEntity l, final String skill) {
        final String[] base = skill.split(" ");
        final String[] data = base[1].split(":");
        final float chance = Float.parseFloat(base[base.length - 1]);
        if (EpicBoss.r.nextFloat() < chance && SkillHandler.CheckHealth(base[base.length - 2], l, skill)) {
            final BossSkillEvent event = new BossSkillEvent(l, skill, null, false);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (event.isChanceled()) {
                return;
            }
            final int amount = Integer.parseInt(data[0]);
            final int radious = Integer.parseInt(data[1]);
            final boolean flicker = Boolean.valueOf(data[4]);
            final boolean trail = Boolean.valueOf(data[5]);
            final String[] c = data[3].split(",");
            final Color color = Color.fromRGB(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2]));
            final FireworkEffect.Type type = FireworkEffect.Type.valueOf(data[2]);
            if (color != null && type != null) {
                for (final Location loc : getLocations(amount, radious, l)) {
                    try {
                        SkillRadiousFirework.effect.playFirework(loc.getWorld(), loc, FireworkEffect.builder().with(type).withColor(color).trail(trail).flicker(flicker).build());
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    public static List<Location> getLocations(final int amount, final int radious, final LivingEntity entity) {
        final List<Location> list = new ArrayList<Location>();
        final double Sangle = 360 / amount;
        for (int i = 0; i < amount; ++i) {
            final double x = Math.cos(Sangle * i);
            final double z = Math.sin(Sangle * i);
            final Location l = new Location(entity.getWorld(), entity.getLocation().getX() + x * radious, entity.getLocation().getY(), entity.getLocation().getZ() + z * radious);
            list.add(l);
        }
        return list;
    }
}
