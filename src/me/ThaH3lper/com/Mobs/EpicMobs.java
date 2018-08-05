package me.ThaH3lper.com.Mobs;

import java.util.*;
import org.bukkit.entity.*;

public class EpicMobs
{
    public String Mobtype;
    public String Display;
    public boolean DisplayVisible;
    public String file;
    public String cmdName;
    public String oso;
    public String horseStyle;
    public String horseType;
    public String horseColor;
    public String villagerType;
    public double health;
    public double damage;
    public double speed;
    public double knock;
    public double follow;
    public List<String> skills;
    public List<String> loot;
    public List<String> equipment;
    public HashMap<String, Long> cooldowns;
    public boolean despawn;
    public boolean showhp;
    public boolean fair;
    int size;
    int color;
    public int maxCombatDistance;
    public Player targetChanger;
    
    public EpicMobs(final String file, final String cmdName, final String Mobtype, final String Display, boolean DisplayVisible, final List<String> loot, final List<String> equipment, final double health, final double damage, final double speed, final double knock, final double follow, final List<String> skills, final boolean despawn, final boolean showhp, final int size, final int color, final String oso, final String horseStyle, final String horseType, final String horseColor, final String villagerType, final int maxCombatDistance) {
        this.fair = false;
        this.file = file;
        this.cmdName = cmdName;
        this.Mobtype = Mobtype;
        this.Display = Display;
        this.DisplayVisible = DisplayVisible;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.knock = knock;
        this.follow = follow;
        this.skills = skills;
        this.despawn = despawn;
        this.showhp = showhp;
        this.size = size;
        this.color = color;
        this.oso = oso;
        this.horseStyle = horseStyle;
        this.horseType = horseType;
        this.horseColor = horseColor;
        this.loot = loot;
        this.villagerType = villagerType;
        this.maxCombatDistance = maxCombatDistance;
        if (loot != null && loot.size() >= 1 && loot.get(0) != null && loot.get(0).contains(":")) {
            this.fair = true;
        }
        this.equipment = equipment;
        this.cooldowns = new HashMap<String, Long>();
        this.targetChanger = null;
    }
    
    public LivingEntity getLivingenEntity() {
        return null;
    }
}
