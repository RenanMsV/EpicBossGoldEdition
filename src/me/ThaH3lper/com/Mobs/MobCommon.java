package me.ThaH3lper.com.Mobs;

import me.ThaH3lper.com.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.metadata.*;
import org.bukkit.plugin.*;
import org.bukkit.inventory.*;
import me.ThaH3lper.com.Drops.*;

public class MobCommon
{
    public static Random r;
    
    static {
        MobCommon.r = new Random();
    }
    
    public static EpicMobs getEpicMob(final String s) {
        for (final EpicMobs em : EpicBoss.plugin.listMobs) {
            if (em.cmdName.equals(s)) {
                return em;
            }
        }
        return null;
    }
    
    public static EpicMobsList getEpicMobList(final String s) {
        for (final EpicMobsList el : EpicBoss.plugin.listMobslist) {
            if (el.cmdName.equals(s)) {
                return el;
            }
        }
        return null;
    }
    
    public static EpicMobs getEpicMob(final LivingEntity l) {
        final List<MetadataValue> list = (List<MetadataValue>)l.getMetadata("cmdname");
        for (final EpicMobs em : EpicBoss.plugin.listMobs) {
            for (final MetadataValue mv : list) {
                if (mv.asString().equals(em.cmdName)) {
                    return em;
                }
            }
        }
        return null;
    }
    
    public static LivingEntity setMeta(final LivingEntity l, final String s, final String key) {
        l.setMetadata(key, (MetadataValue)new FixedMetadataValue((Plugin)EpicBoss.plugin, (Object)s));
        return l;
    }
    
    public static void setEquipment(final LivingEntity l, final EpicMobs em) {
        EpicItemStack helmet = null;
        EpicItemStack chest = null;
        EpicItemStack leggings = null;
        EpicItemStack boots = null;
        EpicItemStack hand = null;
        final EntityEquipment ee = l.getEquipment();
        ee.setItemInHandDropChance(0.0f);
        ee.setHelmetDropChance(0.0f);
        ee.setChestplateDropChance(0.0f);
        ee.setLeggingsDropChance(0.0f);
        ee.setBootsDropChance(0.0f);
        for (final String s : em.equipment) {
            String prefix = "";
            EpicNormal en;
            if (s.contains(":")) {
                final String[] split = s.split(":");
                en = DropHandler.getEpicNormal(split[0]);
                prefix = split[1];
            }
            else {
                en = DropHandler.getEpicNormal(s);
            }
            for (final EpicItemStack is : en.list) {
                if (is.slot != 5 && MobCommon.r.nextFloat() <= is.chance) {
                    if (is.slot == 0 && compair(is, hand)) {
                        hand = is;
                        if (prefix.equals("!")) {
                            ee.setItemInHandDropChance(1.0f);
                        }
                    }
                    if (is.slot == 1 && compair(is, boots)) {
                        boots = is;
                        if (prefix.equals("!")) {
                            ee.setBootsDropChance(1.0f);
                        }
                    }
                    if (is.slot == 2 && compair(is, leggings)) {
                        leggings = is;
                        if (prefix.equals("!")) {
                            ee.setLeggingsDropChance(1.0f);
                        }
                    }
                    if (is.slot == 3 && compair(is, chest)) {
                        chest = is;
                        if (prefix.equals("!")) {
                            ee.setChestplateDropChance(1.0f);
                        }
                    }
                    if (is.slot != 4 || !compair(is, helmet)) {
                        continue;
                    }
                    helmet = is;
                    if (!prefix.equals("!")) {
                        continue;
                    }
                    ee.setHelmetDropChance(1.0f);
                }
            }
        }
        if (hand != null) {
            ee.setItemInHand(hand.stack);
        }
        if (helmet != null) {
            ee.setHelmet(helmet.stack);
        }
        if (chest != null) {
            ee.setChestplate(chest.stack);
        }
        if (leggings != null) {
            ee.setLeggings(leggings.stack);
        }
        if (boots != null) {
            ee.setBoots(boots.stack);
        }
    }
    
    public static boolean compair(final EpicItemStack stack, final EpicItemStack old) {
        return old == null || old.chance >= stack.chance;
    }
}
