package me.ThaH3lper.com.Drops.Fair;

import org.bukkit.entity.*;
import me.ThaH3lper.com.Drops.*;
import java.util.*;

public class FairDrops
{
    public List<FairPlayer> players;
    public LivingEntity entity;
    public EpicNormal rest;
    public List<EpicNormal> loot;
    
    public FairDrops(final LivingEntity entity, final List<EpicNormal> loot, final EpicNormal rest) {
        this.players = new ArrayList<FairPlayer>();
        this.entity = entity;
        this.loot = loot;
        this.rest = rest;
    }
    
    public void Shout(final String msg) {
        for (final FairPlayer fp : this.players) {
            fp.player.sendMessage(msg);
        }
    }
}
