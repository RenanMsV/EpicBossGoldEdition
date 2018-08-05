package me.ThaH3lper.com.Libs;

import org.bukkit.craftbukkit.v1_12_R1.entity.*;
import org.bukkit.entity.*;
import org.bukkit.entity.Entity;

import net.minecraft.server.v1_12_R1.*;

public class MobAttribute
{
    public static void setMaxHealth(final Entity e, final double health) {
        final AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttributeInstance(GenericAttributes.maxHealth);
        attributes.setValue(health);
        final LivingEntity l = (LivingEntity)e;
        l.setHealth(l.getMaxHealth());
    }
    
    public static void setFollowRange(final Entity e, final double range) {
        final AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttributeInstance(GenericAttributes.FOLLOW_RANGE);
        if (attributes != null) {
            attributes.setValue(range);
        }
    }
    
    public static void setKnockBackResistance(final Entity e, final double knock) {
        final AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttributeInstance(GenericAttributes.c);
        if (attributes != null) {
            attributes.setValue(knock);
        }
    }
    
    public static void setMobSpeed(final Entity e, final double speed) {
        final AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
        if (attributes != null) {
            attributes.setValue(speed);
        }
    }
    
    public static void setAttackDamage(final Entity e, final double damage) {
        final AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttributeInstance(GenericAttributes.ATTACK_DAMAGE);
        if (attributes != null) {
            attributes.setValue(damage);
        }
    }
}
