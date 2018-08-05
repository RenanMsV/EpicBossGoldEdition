package me.ThaH3lper.com.Timer;

import me.ThaH3lper.com.Location.*;
import org.bukkit.block.*;
import org.bukkit.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Mobs.*;
import org.bukkit.entity.*;
import java.util.*;

public class Timer
{
    public EpicTimer et;
    public List<UUID> mobs;
    public EpicLocation el;
    public Location loc;
    public Sign sign;
    int clock;
    
    public Timer(final Location loc, final EpicTimer et, final EpicLocation el, final String[] lines) {
        this.mobs = new ArrayList<UUID>();
        this.loc = loc;
        this.et = et;
        this.el = el;
        this.clock = et.interval;
        (this.sign = (Sign)loc.getBlock().getState()).setLine(0, ChatColor.GREEN + "[EpicTimer]");
        this.sign.setLine(1, lines[1]);
        this.sign.setLine(2, lines[2]);
        this.sign.update();
    }
    
    public void Update() {
        this.sign.setLine(3, new StringBuilder(String.valueOf(this.clock)).toString());
        this.sign.update();
        this.sign = (Sign)this.loc.getBlock().getState();
    }
    
    public boolean tick(final int sec) {
        if (this.mobs.size() >= this.et.amount) {
            return false;
        }
        this.clock -= sec;
        this.Update();
        if (this.clock <= 0) {
            this.clock = this.et.interval;
            if (this.mobs.size() < this.et.amount) {
                this.el.LoadChunk();
                this.mobs.add(MobHandler.SpawnMob(this.et.bosses.get(EpicBoss.r.nextInt(this.et.bosses.size())).cmdName, this.el.location).getUniqueId());
                return true;
            }
        }
        return false;
    }
    
    public void WalkCheck() {
        if (this.et.walk == 0) {
            return;
        }
        for (final LivingEntity l : this.loc.getWorld().getLivingEntities()) {
            if (this.mobs.contains(l.getUniqueId()) && this.el.location.distance(l.getLocation()) >= this.et.walk) {
                l.teleport(this.el.location);
            }
        }
    }
}
