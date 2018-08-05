package me.ThaH3lper.com.Mobs;

import me.ThaH3lper.com.Libs.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Skills.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class MobHandler
{
    public static LivingEntity SpawnMob(final String cmdName, final Location l) {
        final EpicMobs em = MobCommon.getEpicMob(cmdName);
        if (em != null) {
            return SpawnEpicMob(em, l);
        }
        final EpicMobsList el = MobCommon.getEpicMobList(cmdName);
        if (el != null) {
            return SpawnEpicMobList(el, l);
        }
        return null;
    }
    
    public static LivingEntity SpawnEpicMobList(final EpicMobsList el, final Location loc) {
        final String[] part = el.bosslist.split(",");
        String[] array;
        for (int length = (array = part).length, j = 0; j < length; ++j) {
            final String mob = array[j];
            if (MobCommon.getEpicMob(mob) == null) {
                return null;
            }
        }
        final LivingEntity orginal;
        LivingEntity l1 = orginal = SpawnEpicMob(MobCommon.getEpicMob(part[0]), loc);
        for (int i = 1; i < part.length; ++i) {
            final LivingEntity l2 = SpawnEpicMob(MobCommon.getEpicMob(part[i]), loc);
            l1.setPassenger((Entity)l2);
            l1 = l2;
        }
        return orginal;
    }
    
    public static LivingEntity SpawnEpicMob(final EpicMobs em, final Location loc) {
        LivingEntity l = AllMobs.spawnMob(em.Mobtype, loc);
        l = setDisplay(em, l);
        l = MobCommon.setMeta(l, em.cmdName, "cmdname");
        if (!em.despawn) {
            l.setRemoveWhenFarAway(false);
        }
        if (l instanceof Slime && em.size != 0) {
            ((Slime)l).setSize(em.size);
        }
        if (l instanceof MagmaCube && em.size != 0) {
            ((MagmaCube)l).setSize(em.size);
        }
        if (l instanceof Wolf || l instanceof Sheep) {
            l = setColor(l, em);
        }
        if (l instanceof Ocelot) {
            l = setOcolot(l, em);
        }
        if (l instanceof Horse) {
            l = setHorse(l, em);
        }
        if (l instanceof Villager) {
            l = setVillager(l, em);
        }
        MobCommon.setEquipment(l, em);
        MobAttribute.setAttackDamage((Entity)l, em.damage);
        MobAttribute.setMaxHealth((Entity)l, em.health);
        if (em.speed != 0.0) {
            if (em.follow == -1.0) {
                MobAttribute.setMobSpeed((Entity)l, 0.0);
            }
            else {
                MobAttribute.setMobSpeed((Entity)l, em.speed);
            }
        }
        if (em.follow != 0.0) {
            if (em.follow == -1.0) {
                MobAttribute.setFollowRange((Entity)l, 0.0);
            }
            else {
                MobAttribute.setFollowRange((Entity)l, em.follow);
            }
        }
        MobAttribute.setKnockBackResistance((Entity)l, em.knock);
        EpicBoss.plugin.allMobs.add(l.getUniqueId());
        SkillHandler.ExecuteSkills(em.skills, l, null);
        return l;
    }
    
    public static LivingEntity setDisplay(final EpicMobs em, final LivingEntity l) {
        String s = em.Display;
        s = ChatColor.translateAlternateColorCodes('&', s);
        l.setCustomName(s);
        l.setCustomNameVisible(true);
        return l;
    }
    
    public static LivingEntity setColor(final LivingEntity l, final EpicMobs em) {
        if (l instanceof Wolf) {
            final Wolf e = (Wolf)l;
            e.setCollarColor(DyeColor.getByDyeData((byte)em.color));
            return l;
        }
        if (l instanceof Sheep) {
            final Sheep e2 = (Sheep)l;
            e2.setColor(DyeColor.getByDyeData((byte)em.color));
            return l;
        }
        return null;
    }
    
    public static LivingEntity setOcolot(final LivingEntity l, final EpicMobs em) {
        final Ocelot e = (Ocelot)l;
        if (em.oso != null) {
            e.setCatType(Ocelot.Type.valueOf(em.oso));
        }
        return l;
    }
    
    public static LivingEntity setVillager(final LivingEntity l, final EpicMobs em) {
        final Villager v = (Villager)l;
        if (em.villagerType != null) {
            v.setProfession(Villager.Profession.valueOf(em.villagerType));
        }
        return l;
    }
    
    public static LivingEntity setHorse(final LivingEntity l, final EpicMobs em) {
        final Horse e = (Horse)l;
        if (em.horseStyle != null) {
            e.setStyle(Horse.Style.valueOf(em.horseStyle));
        }
        if (em.horseType != null) {
            e.setVariant(Horse.Variant.valueOf(em.horseType));
        }
        if (em.horseColor != null) {
            e.setColor(Horse.Color.valueOf(em.horseColor));
        }
        return l;
    }
}
