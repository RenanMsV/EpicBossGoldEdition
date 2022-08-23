package me.ThaH3lper.com.API;

import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Mobs.*;

public class EpicBossAPI
{
    public static boolean isBoss(final LivingEntity l) {
        return EpicBoss.plugin.allMobs.contains(l.getUniqueId());
    }
    
    public static String getBossName(final LivingEntity l) {
        final EpicMobs em = MobCommon.getEpicMob(l);
        return em.cmdName;
    }
    
    public static String getBossDisplayName(final LivingEntity l) {
        final EpicMobs em = MobCommon.getEpicMob(l);
        return em.Display;
    }
    
	public static double getMaxHealth(final LivingEntity l) {
        return l.getMaxHealth();
    }
    
    public static double getHealth(final LivingEntity l) {
        return l.getHealth();
    }
    
    public static boolean isShowingHp(final LivingEntity l) {
        final EpicMobs em = MobCommon.getEpicMob(l);
        return em.showhp;
    }
    
    public static EpicMobs getEpicBoss(final LivingEntity l) {
        final EpicMobs em = MobCommon.getEpicMob(l);
        return em;
    }
}
