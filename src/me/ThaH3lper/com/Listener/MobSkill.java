package me.ThaH3lper.com.Listener;

import org.bukkit.event.entity.*;
import me.ThaH3lper.com.*;
import org.bukkit.entity.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.Mobs.*;
import org.bukkit.event.*;
import me.ThaH3lper.com.SaveLoad.*;
import java.util.*;

public class MobSkill implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void MobSkillEvent(final EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof LivingEntity)) {
            return;
        }
        final LivingEntity l = (LivingEntity)e.getEntity();
        if (!EpicBoss.plugin.allMobs.contains(l.getUniqueId())) {
            return;
        }
        final EpicMobs em = MobCommon.getEpicMob(l);
        this.showHealth(l, em);
        if (!(e.getDamager() instanceof Player)) {
            if (e.getDamager() instanceof Projectile) {
                if (em.maxCombatDistance > 0 && l.getLocation().distanceSquared(((Entity)((Projectile)e.getDamager()).getShooter()).getLocation()) > em.maxCombatDistance * em.maxCombatDistance) {
                    e.setCancelled(true);
                    return;
                }
                if (((Projectile)e.getDamager()).getShooter() instanceof Player) {
                    SkillHandler.ExecuteSkills(em.skills, l, (Player)((Projectile)e.getDamager()).getShooter());
                    return;
                }
            }
            SkillHandler.ExecuteSkills(em.skills, l, null);
            return;
        }
        if (em.maxCombatDistance > 0 && l.getLocation().distanceSquared(e.getDamager().getLocation()) > em.maxCombatDistance * em.maxCombatDistance) {
            e.setCancelled(true);
            return;
        }
        SkillHandler.ExecuteSkills(em.skills, l, (Player)e.getDamager());
    }
    
    public void showHealth(final LivingEntity l, final EpicMobs em) {
        if (!em.showhp) {
            return;
        }
        final int per = (int)(l.getHealth() / l.getMaxHealth() * 10.0) + 1;
        if (per >= 10) {
            return;
        }
        if (!SkillHandler.hasUsed("percentage" + per, l)) {
            MobCommon.setMeta(l, "percentage" + per, "percentage" + per);
            String string = LoadSetup.ShowHealth;
            string = string.replace("$percentage", String.valueOf(per) + "0");
            string = string.replace("$boss", new StringBuilder(String.valueOf(l.getCustomName())).toString());
            for (final Player p : SkillHandler.getRadious(l, LoadSetup.ShowHealthRadius)) {
                p.sendMessage(string);
            }
        }
    }
}
