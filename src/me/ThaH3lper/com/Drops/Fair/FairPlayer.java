package me.ThaH3lper.com.Drops.Fair;

import org.bukkit.entity.*;

public class FairPlayer
{
    public Player player;
    public double damage;
    
    public FairPlayer(final Player player, final double damage) {
        this.player = player;
        this.damage = damage;
    }
}
