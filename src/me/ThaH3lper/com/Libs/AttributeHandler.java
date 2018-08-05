package me.ThaH3lper.com.Libs;

import org.bukkit.inventory.*;

public class AttributeHandler
{
    public static ItemStack addSpeed(final ItemStack s, final double i) {
        final Attributes attributes = new Attributes(s);
        attributes.add(Attributes.Attribute.newBuilder().name("Speed").type(Attributes.AttributeType.GENERIC_MOVEMENT_SPEED).amount(i).build());
        return attributes.getStack();
    }
    
    public static ItemStack addHealth(final ItemStack s, final double i) {
        final Attributes attributes = new Attributes(s);
        attributes.add(Attributes.Attribute.newBuilder().name("Health").type(Attributes.AttributeType.GENERIC_MAX_HEALTH).amount(i).build());
        return attributes.getStack();
    }
    
    public static ItemStack addKnockBackRes(final ItemStack s, final double i) {
        final Attributes attributes = new Attributes(s);
        attributes.add(Attributes.Attribute.newBuilder().name("KnockBack").type(Attributes.AttributeType.GENERIC_KNOCKBACK_RESISTANCE).amount(i).build());
        return attributes.getStack();
    }
    
    public static ItemStack addDamage(final ItemStack s, final double i) {
        final Attributes attributes = new Attributes(s);
        attributes.add(Attributes.Attribute.newBuilder().name("Damage").type(Attributes.AttributeType.GENERIC_ATTACK_DAMAGE).amount(i).build());
        return attributes.getStack();
    }
    
    public static ItemStack addFollowRange(final ItemStack s, final double i) {
        final Attributes attributes = new Attributes(s);
        attributes.add(Attributes.Attribute.newBuilder().name("FollowRange").type(Attributes.AttributeType.GENERIC_FOLLOW_RANGE).amount(i).build());
        return attributes.getStack();
    }
}
