package me.ThaH3lper.com.Listener;

import org.bukkit.event.entity.*;
import org.bukkit.metadata.*;
import me.ThaH3lper.com.SkillsCollection.*;
import org.bukkit.entity.*;
import me.ThaH3lper.com.*;
import org.bukkit.plugin.*;
import java.util.*;
import org.bukkit.event.*;

public class SkillShootProjectileListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void onProjectileHit(final EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Projectile)) {
            return;
        }
        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }
        final Projectile projectile = (Projectile)event.getDamager();
        final List<MetadataValue> metas = (List<MetadataValue>)projectile.getMetadata("EpicBossProjectile");
        if (metas == null || metas.size() == 0) {
            return;
        }
        final Iterator<MetadataValue> iterator = metas.iterator();
        if (iterator.hasNext()) {
            final MetadataValue meta = iterator.next();
            final SkillShootProjectile.ProjectileData data = (SkillShootProjectile.ProjectileData)meta.value();
            event.setDamage((double)data.damage);
            event.getDamager().getType();
            final EntityType ender_PEARL = EntityType.ENDER_PEARL;
        }
        projectile.removeMetadata("EpicBossProjectile", (Plugin)EpicBoss.plugin);
        projectile.remove();
    }
}
