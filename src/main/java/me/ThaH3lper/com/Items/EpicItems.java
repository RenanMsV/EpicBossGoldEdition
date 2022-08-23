package me.ThaH3lper.com.Items;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EpicItems
{
    public String id;
    public int data;
    public int amount;
    public String Display;
    public String cmdName;
    public String file;
    public String color;
    public String player;
    public List<String> Lores;
    public List<String> Enchants;
    public double speed;
    public double damage;
    public double knock;
    public double health;
    public double range;
    
    public EpicItems(final String file, final String cmdName, final String id, final int data, final int amount, final String Display, final List<String> Lores, final List<String> Enchants, final double health, final double damage, final double knock, final double range, final double speed, final String color, final String player) {
        this.id = id;
        this.data = data;
        this.amount = amount;
        this.Display = Display;
        this.Lores = Lores;
        this.Enchants = Enchants;
        this.cmdName = cmdName;
        this.speed = speed;
        this.damage = damage;
        this.range = range;
        this.knock = knock;
        this.health = health;
        this.file = file;
        this.color = color;
        this.player = player;
    }
    
    public ItemStack getItemStack() {
        return ItemHandler.getItemStack(this);
    }
}
