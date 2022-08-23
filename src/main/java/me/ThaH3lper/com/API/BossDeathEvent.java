package me.ThaH3lper.com.API;

import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.event.*;
import me.ThaH3lper.com.Mobs.*;

public class BossDeathEvent extends Event
{
    private LivingEntity l;
    private LivingEntity killer;
    private List<ItemStack> drops;
    private int exp;
    private static final HandlerList handlers;
    
    static {
        handlers = new HandlerList();
    }
    
    public BossDeathEvent(final LivingEntity l, final LivingEntity killer, final List<ItemStack> drops, final int exp) {
        this.exp = exp;
        this.killer = killer;
        this.l = l;
        this.drops = drops;
    }
    
    public LivingEntity getLivingEntity() {
        return this.l;
    }
    
    public EpicMobs getEpicMobs() {
        final EpicMobs em = MobCommon.getEpicMob(this.l);
        return em;
    }
    
    public LivingEntity getKiller() {
        return this.killer;
    }
    
    public List<ItemStack> getDrops() {
        return this.drops;
    }
    
    public void setDrops(final List<ItemStack> list) {
        this.drops = list;
    }
    
    public int getExp() {
        return this.exp;
    }
    
    public void setExp(final int amount) {
        this.exp = amount;
    }
    
    public HandlerList getHandlers() {
        return BossDeathEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return BossDeathEvent.handlers;
    }
}
