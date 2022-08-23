package me.ThaH3lper.com.Skills;

import java.util.*;
import org.bukkit.*;
import me.ThaH3lper.com.*;
import org.bukkit.plugin.*;
import me.ThaH3lper.com.SkillsCollection.*;
import org.bukkit.metadata.*;
import org.bukkit.entity.*;
import me.ThaH3lper.com.Mobs.*;

public class SkillHandler
{
    public static void ExecuteSkills(final List<String> list, final LivingEntity l, final Player p) {
        for (final String line : list) {
            ExecuteSkill(l, line, p);
        }
    }
    
    public static void ExecutePackSkills(final List<String> list, final LivingEntity l, final Player p) {
        final List<String> DelayedSkills = new ArrayList<String>();
        boolean delayrest = false;
        int delayamount = 0;
        for (final String line : list) {
            if (!delayrest) {
                final String[] split = line.split(" ");
                if (split[0].equals("delay")) {
                    delayrest = true;
                    delayamount = Integer.parseInt(split[1]);
                }
                else {
                    ExecuteSkill(l, line, p);
                }
            }
            else {
                DelayedSkills.add(line);
            }
        }
        if (delayrest) {
            final DelayedSkill ds = new DelayedSkill(DelayedSkills, l, p);
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)EpicBoss.plugin, (Runnable)ds, (long)delayamount);
        }
    }
    
    public static void ExecuteSkill(final LivingEntity l, final String skill, final Player p) {
        final String[] split = skill.split(" ");
        if (split[0] != null) {
            if (split[0].equals("msg")) {
                SkillMsg.ExecuteMsg(l, skill, p);
            }
            else if (split[0].equals("bossfirework")) {
                SkillBossFirework.ExecuteBossFirework(l, skill);
            }
            else if (split[0].equals("playerfirework")) {
                SkillPlayerFirework.ExecutePlayerFirework(l, skill, p);
            }
            else if (split[0].equals("radiousfirework") || split[0].equals("radiusfirework")) {
                SkillRadiousFirework.ExecuteRadiousFirework(l, skill);
            }
            else if (split[0].equals("pack")) {
                SkillPack.ExecutePack(l, skill, p);
            }
            else if (split[0].equals("randompack")) {
                SkillRandomPack.ExecuteRandomPack(l, skill, p);
            }
            else if (split[0].equals("damage")) {
                SkillDamage.ExecuteDamage(l, skill, p);
            }
            else if (split[0].equals("heal")) {
                SkillHeal.ExecuteHeal(l, skill, p);
            }
            else if (split[0].equals("cmd")) {
                SkillCommand.ExecuteCommand(l, skill, p);
            }
            else if (split[0].equals("radiouscmd") || split[0].equals("radiuscmd")) {
                SkillRadiusCommand.ExecuteCommand(l, skill);
            }
            else if (split[0].equals("potion")) {
                SkillPotion.ExecutePotion(l, skill, p);
            }
            else if (split[0].equals("potionboss")) {
                SkillPotionBoss.ExecutePotionBoss(l, skill, p);
            }
            else if (split[0].equals("potionmobs")) {
                SkillPotionMobs.ExecutePotionMobs(l, skill);
            }
            else if (split[0].equals("throw")) {
                SkillThrow.ExecuteThrow(l, skill, p);
            }
            else if (split[0].equals("lightning")) {
                SkillLightning.ExecuteLightning(l, skill, p);
            }
            else if (split[0].equals("shootfireball")) {
                SkillShootFireball.ExecuteShoot(l, skill, p);
            }
            else if (split[0].equals("shootprojectile") || split[0].equals("projectile")) {
                SkillShootProjectile.ExecuteShoot(l, skill, p);
            }
            else if (split[0].equals("shootpotion") || split[0].equals("throwpotion")) {
                SkillShootPotion.ExecuteShoot(l, skill, p);
            }
            else if (split[0].equals("shootskull")) {
                SkillShootSkull.ExecuteShoot(l, skill, p);
            }
            else if (split[0].equals("swarm")) {
                SkillSwarm.ExecuteSwarm(l, skill, p);
            }
            else if (split[0].equals("teleport")) {
                SkillTeleport.ExecuteTeleport(l, skill, p);
            }
            else if (split[0].equals("teleportnear")) {
                SkillTeleportNear.ExecuteTeleportNear(l, skill, p);
            }
            else if (split[0].equals("pull")) {
                SkillPull.ExecutePull(l, skill, p);
            }
            else if (split[0].equals("forcepull")) {
                SkillForcePull.ExecuteForcePull(l, skill, p);
            }
            else if (split[0].equals("forcepullnear")) {
                SkillForcePullNear.ExecuteForcePullNear(l, skill, p);
            }
            else if (split[0].equals("consume")) {
                SkillConsume.ExecuteConsume(l, skill);
            }
            else if (split[0].equals("newtarget")) {
                SkillNewTarget.ExecuteNewTarget(l, skill);
            }
            else if (split[0].equals("effect")) {
                SkillEffect.ExecuteEffect(l, skill, p);
            }
            else {
                SkillCustom.ExecuteCustom(l, skill, p);
            }
        }
    }
    
    public static List<Player> getRadious(final LivingEntity l, final int i) {
        final List<Player> list = new ArrayList<Player>();
        for (final Player p : l.getWorld().getPlayers()) {
            if (l.getLocation().distance(p.getLocation()) < i) {
                list.add(p);
            }
        }
        return list;
    }
    
    public static boolean CheckHealth(String health, final LivingEntity l, final String full) {
        if (health.contains(">")) {
            health = health.replace(">", "");
            final double hp = Double.parseDouble(health);
            if (l.getHealth() > hp) {
                return true;
            }
        }
        else if (health.contains("=")) {
            health = health.replace("=", "");
            final double hp = Double.parseDouble(health);
            if (l.getHealth() <= hp && !hasUsed(full, l)) {
                MobCommon.setMeta(l, full, full);
                return true;
            }
        }
        else if (health.contains("<")) {
            health = health.replace("<", "");
            final double hp = Double.parseDouble(health);
            if (l.getHealth() < hp) {
                return true;
            }
        }
        else if (health.contains("-")) {
            final String[] hps = health.split("-");
            final double hp2 = Double.parseDouble(hps[0]);
            final double hp3 = Double.parseDouble(hps[1]);
            if (l.getHealth() > hp3 && l.getHealth() < hp2) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean hasUsed(final String full, final LivingEntity l) {
        final List<MetadataValue> list = (List<MetadataValue>)l.getMetadata(full);
        for (final MetadataValue mv : list) {
            if (mv.asString().equals(full)) {
                return true;
            }
        }
        return false;
    }
    
    private static class DelayedSkill implements Runnable
    {
        private List<String> list;
        private LivingEntity boss;
        private Player player;
        private boolean cancelled;
        
        public DelayedSkill(final List<String> list, final LivingEntity l, final Player p) {
            this.list = list;
            this.boss = l;
            this.player = p;
            this.cancelled = false;
        }
        
        public void cancel() {
            this.list = null;
            this.cancelled = true;
        }
        
        @Override
        public void run() {
            if (!this.cancelled && this.boss.isValid()) {
                final EpicMobs em = MobCommon.getEpicMob(this.boss);
                if (em.targetChanger != null) {
                    this.player = em.targetChanger;
                    em.targetChanger = null;
                }
                if (this.player != null && this.player.isValid()) {
                    SkillHandler.ExecutePackSkills(this.list, this.boss, this.player);
                    return;
                }
                if (this.boss instanceof Creature && ((Creature)this.boss).getTarget() instanceof Player) {
                    this.player = (Player)((Creature)this.boss).getTarget();
                    SkillHandler.ExecutePackSkills(this.list, this.boss, this.player);
                    return;
                }
                final List<Entity> list = (List<Entity>)this.boss.getNearbyEntities(16.0, 8.0, 16.0);
                for (final Entity e : list) {
                    if (e instanceof Player) {
                        if (this.boss instanceof Creature) {
                            ((Creature)this.boss).setTarget((LivingEntity)e);
                        }
                        SkillHandler.ExecutePackSkills(this.list, this.boss, (Player)e);
                        return;
                    }
                }
                this.cancel();
            }
        }
    }
}
