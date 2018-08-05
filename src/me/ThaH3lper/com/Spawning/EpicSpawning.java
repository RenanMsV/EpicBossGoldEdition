package me.ThaH3lper.com.Spawning;

import org.bukkit.block.*;
import java.util.*;
import me.ThaH3lper.com.Mobs.*;
import org.bukkit.*;

public class EpicSpawning
{
    public String cmdName;
    public EpicMobs em;
    public int priority;
    public float chance;
    public List<Biome> biomes;
    public List<World> worlds;
    
    public EpicSpawning(final String cmdName, final String mobname, final int priority, final float chance, final String world, final String biome) {
        this.biomes = new ArrayList<Biome>();
        this.worlds = new ArrayList<World>();
        this.cmdName = cmdName;
        this.em = MobCommon.getEpicMob(mobname);
        this.priority = priority;
        this.chance = chance;
        final String[] worldName = world.split(",");
        String[] array;
        for (int length = (array = worldName).length, i = 0; i < length; ++i) {
            final String name = array[i];
            final World w = Bukkit.getWorld(name);
            if (w != null) {
                this.worlds.add(w);
            }
        }
        if (biome != null) {
            final String[] biomename = biome.split(",");
            String[] array2;
            for (int length2 = (array2 = biomename).length, j = 0; j < length2; ++j) {
                final String name2 = array2[j];
                final Biome b = Biome.valueOf(name2);
                if (b != null) {
                    this.biomes.add(b);
                }
            }
        }
    }
}
