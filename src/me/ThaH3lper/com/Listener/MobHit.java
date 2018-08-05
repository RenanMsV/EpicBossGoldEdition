package me.ThaH3lper.com.Listener;

import org.bukkit.event.entity.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Drops.*;
import me.ThaH3lper.com.Drops.Fair.*;
import org.bukkit.entity.*;
import me.ThaH3lper.com.Mobs.*;
import org.bukkit.event.*;

public class MobHit implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void MobDamageEvent(final EntityDamageByEntityEvent e) {
        final Entity entity = e.getEntity();
        final Entity damager = e.getDamager();
        if (!(entity instanceof LivingEntity)) {
            return;
        }
        if (!(damager instanceof Player)) {
            return;
        }
        final LivingEntity l = (LivingEntity)entity;
        if (!EpicBoss.plugin.allMobs.contains(l.getUniqueId())) {
            return;
        }
        final Player p = (Player)damager;
        final FairDrops fd = DropHandler.getFairDrops(l);
        if (fd != null) {
            final FairPlayer fp = DropHandler.getFairPlayer(fd, p);
            if (fp != null) {
                if (l.getNoDamageTicks() < 10) {
                    final FairPlayer fairPlayer = fp;
                    fairPlayer.damage += e.getDamage();
                }
            }
            else {
                fd.players.add(new FairPlayer(p, e.getDamage()));
            }
        }
        else {
            final EpicMobs em = MobCommon.getEpicMob(l);
            if (em.fair) {
                EpicBoss.plugin.listFair.add(new FairDrops(l, DropHandler.getFairDropList(em), DropHandler.getEpicNormalRest(em)));
            }
        }
    }
}
