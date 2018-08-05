package me.ThaH3lper.com.Spawning;

import java.util.*;
import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Mobs.*;
import org.bukkit.block.*;

public class SpawningHandler
{
    public static List<LivingEntity> templist;
    
    static {
        SpawningHandler.templist = new ArrayList<LivingEntity>();
    }
    
    public static void updateSpawning() {
        for (final LivingEntity l : SpawningHandler.templist) {
            if (l != null) {
                Spawn(l);
            }
        }
        SpawningHandler.templist.clear();
    }
    
    public static boolean Spawn(final LivingEntity l) {
        String type = "";
        if (l instanceof Monster) {
            type = "Monster";
        }
        else if (l instanceof Animals) {
            type = "Animal";
        }
        for (final EpicSpawning es : EpicBoss.plugin.listSpawning) {
            final boolean b = cheack(l, es, type);
            if (b) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean cheack(final LivingEntity l, final EpicSpawning es, final String type) {
        if (!es.worlds.contains(l.getWorld())) {
            return false;
        }
        if (!getType(es.em.Mobtype).equals(type)) {
            return false;
        }
        if (!canSpawnInBiome(es.biomes, l)) {
            return false;
        }
        if (es.chance >= EpicBoss.r.nextFloat()) {
            final LivingEntity liv = MobHandler.SpawnMob(es.em.cmdName, l.getLocation());
            liv.setRemoveWhenFarAway(true);
            return true;
        }
        return false;
    }
    
    public static String getType(final String name) {
        if (name.equals("zombie") || name.equals("babyzombie") || name.equals("villagezombie") || name.equals("babyvillagezombie") || name.equals("silverfish")) {
            return "Monster";
        }
        if (name.equals("wither") || name.equals("witch") || name.equals("spider") || name.equals("skeleton") || name.equals("witherskeleton")) {
            return "Monster";
        }
        if (name.equals("pigzombie") || name.equals("angrypigzombie") || name.equals("babypigzombie") || name.equals("angrybabypigzombie")) {
            return "Monster";
        }
        if (name.equals("giant") || name.equals("enderman") || name.equals("creeper") || name.equals("poweredcreeper")) {
            return "Monster";
        }
        if (name.equals("cavespider") || name.equals("blaze")) {
            return "Monster";
        }
        if (name.equals("vex")) {
            return "Monster";
        }
        if (name.equals("husk")) {
            return "Monster";
        }
        if (name.equals("shulker")) {
            return "Monster";
        }
        if (name.equals("evoker")) {
            return "Monster";
        }
        if (name.equals("guardian")) {
            return "Monster";
        }
        if (name.equals("elderguardian")) {
            return "Monster";
        }
        if (name.equals("endermite")) {
            return "Monster";
        }
        if (name.equals("stray")) {
            return "Monster";
        }
        if (name.equals("vindicator")) {
            return "Monster";
        }
        if (name.equals("wolf") || name.equals("babywolf") || name.equals("angrywolf") || name.equals("angrybabywolf")) {
            return "Animal";
        }
        if (name.equals("sheep") || name.equals("babysheep") || name.equals("pig") || name.equals("babypig")) {
            return "Animal";
        }
        if (name.equals("ocelot") || name.equals("babyocelot") || name.equals("mushroomcow") || name.equals("babymushroomcow")) {
            return "Animal";
        }
        if (name.equals("horse") || name.equals("babyhorse") || name.equals("cow") || name.equals("babycow")) {
            return "Animal";
        }
        if (name.equals("chicken") || name.equals("babychicken")) {
            return "Animal";
        }
        if (name.equals("rabbit")) {
            return "Animal";
        }
        if (name.equals("polarbear")) {
            return "Animal";
        }
        return "None";
    }
    
    public static boolean canSpawnInBiome(final List<Biome> list, final LivingEntity l) {
        return list.size() == 0 || list.contains(l.getWorld().getBiome(l.getLocation().getBlockX(), l.getLocation().getBlockZ()));
    }
}
