package me.ThaH3lper.com.Libs;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;

public class MobAttribute
{
    public static void setMaxHealth(final Entity e, final double health) {
        LivingEntity le = (LivingEntity) e;
        try {
            Attribute attr = Attribute.GENERIC_MAX_HEALTH;
            le.getAttribute(attr).addModifier(new AttributeModifier(UUID.randomUUID(), "generic." + attr + ".modded", health, Operation.ADD_NUMBER));
        } catch (IllegalArgumentException ex) {
            Bukkit.getLogger().info("Invalid attribute (" + "GENERIC MAX HEALTH" + ")");
        }

        //final AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttribute(Attribute.GENERIC_MAX_HEALTH);
        //attributes.setValue(health);
        final LivingEntity l = (LivingEntity)e;
        l.setHealth(l.getMaxHealth());
    }

    public static void setFollowRange(final Entity e, final double range) {
        LivingEntity le = (LivingEntity) e;
        try {
            Attribute attr = Attribute.GENERIC_FOLLOW_RANGE;
            le.getAttribute(attr).addModifier(new AttributeModifier(UUID.randomUUID(), "generic." + attr + ".modded", range, Operation.ADD_NUMBER));
        } catch (IllegalArgumentException ex) {
            Bukkit.getLogger().info("Invalid attribute (" + "GENERIC FOLLOW RANGE" + ")");
        }

        /*
        final AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttribute(Attribute.GENERIC_FOLLOW_RANGE);
        if (attributes != null) {
            attributes.setValue(range);
        }*/
    }

    public static void setKnockBackResistance(final Entity e, final double knock) {
        LivingEntity le = (LivingEntity) e;
        try {
            Attribute attr = Attribute.GENERIC_KNOCKBACK_RESISTANCE;
            le.getAttribute(attr).addModifier(new AttributeModifier(UUID.randomUUID(), "generic." + attr + ".modded", knock, Operation.ADD_NUMBER));
        } catch (IllegalArgumentException ex) {
            Bukkit.getLogger().info("Invalid attribute (" + "GENERIC KNOCK" + ")");
        }

        /*
        final AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
        if (attributes != null) {
            attributes.setValue(knock);
        }*/
    }

    public static void setMobSpeed(final Entity e, final double speed) {
        LivingEntity le = (LivingEntity) e;
        try {
            Attribute attr = Attribute.GENERIC_MOVEMENT_SPEED;
            le.getAttribute(attr).addModifier(new AttributeModifier(UUID.randomUUID(), "generic." + attr + ".modded", speed, Operation.ADD_NUMBER));
        } catch (IllegalArgumentException ex) {
            Bukkit.getLogger().info("Invalid attribute (" + "GENERIC SPEED" + ")");
        }
        /*
        final AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        if (attributes != null) {
            attributes.setValue(speed);
        }
         */

    }

    public static void setAttackDamage(final Entity e, final double damage) {
        LivingEntity le = (LivingEntity) e;
        try {
            Attribute attr = Attribute.GENERIC_ATTACK_DAMAGE;
            le.getAttribute(attr).addModifier(new AttributeModifier(UUID.randomUUID(), "generic." + attr + ".modded", damage, Operation.ADD_NUMBER));
        } catch (IllegalArgumentException ex) {
            Bukkit.getLogger().info("Invalid attribute (" + "GENERIC DAMAGE" + ")");
        }
        /*
        final AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        if (attributes != null) {
            attributes.setValue(damage);
        }
        */
    }
}
