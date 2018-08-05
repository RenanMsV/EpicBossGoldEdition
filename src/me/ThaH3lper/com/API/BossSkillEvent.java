package me.ThaH3lper.com.API;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import me.ThaH3lper.com.Mobs.*;

public class BossSkillEvent extends Event
{
    private String name;
    private Boolean custom;
    private Boolean canceled;
    private LivingEntity l;
    private String[] skillData;
    private Player p;
    private static final HandlerList handlers;
    
    static {
        handlers = new HandlerList();
    }
    
    public BossSkillEvent(final LivingEntity l, final String full, final Player p, final Boolean custom) {
        this.canceled = false;
        this.l = l;
        this.custom = custom;
        this.p = p;
        final String[] parts = full.split(" ");
        this.name = parts[0];
        this.skillData = parts[1].split(":");
    }
    
    public String getBossName() {
        final EpicMobs em = MobCommon.getEpicMob(this.l);
        return em.cmdName;
    }
    
    public LivingEntity getLivingEntity() {
        return this.l;
    }
    
    public EpicMobs getEpicMobs() {
        final EpicMobs em = MobCommon.getEpicMob(this.l);
        return em;
    }
    
    public String getSkillName() {
        return this.name;
    }
    
    public Player getTargetPlayer() {
        return this.p;
    }
    
    public boolean isCustomSkill() {
        return this.custom;
    }
    
    public void setChanceled(final boolean bool) {
        this.canceled = bool;
    }
    
    public boolean isChanceled() {
        return this.canceled;
    }
    
    public String[] getData() {
        return this.skillData;
    }
    
    public HandlerList getHandlers() {
        return BossSkillEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return BossSkillEvent.handlers;
    }
}
