package me.ThaH3lper.com.Libs;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;


public class AttributeHandler
{
    public static EquipmentSlot getSlotByMaterial (Material material) {

        Map<String, EquipmentSlot> map = new HashMap<String, EquipmentSlot>();

        map.put(Material.PLAYER_HEAD.name(), EquipmentSlot.HEAD);
        map.put(Material.SKELETON_SKULL.name(), EquipmentSlot.HEAD);
        map.put(Material.WITHER_SKELETON_SKULL.name(), EquipmentSlot.HEAD);
        map.put(Material.CREEPER_HEAD.name(), EquipmentSlot.HEAD);
        map.put(Material.ZOMBIE_HEAD.name(), EquipmentSlot.HEAD);
        map.put(Material.TURTLE_HELMET.name(), EquipmentSlot.HEAD);
        map.put(Material.DRAGON_HEAD.name(), EquipmentSlot.HEAD);

        map.put(Material.DIAMOND_HELMET.name(), EquipmentSlot.HEAD);
        map.put(Material.IRON_HELMET.name(), EquipmentSlot.HEAD);
        map.put(Material.GOLDEN_HELMET.name(), EquipmentSlot.HEAD);
        map.put(Material.LEATHER_HELMET.name(), EquipmentSlot.HEAD);
        map.put(Material.NETHERITE_HELMET.name(), EquipmentSlot.HEAD);
        map.put(Material.CHAINMAIL_HELMET.name(), EquipmentSlot.HEAD);

        map.put(Material.DIAMOND_CHESTPLATE.name(), EquipmentSlot.CHEST);
        map.put(Material.IRON_CHESTPLATE.name(), EquipmentSlot.CHEST);
        map.put(Material.GOLDEN_CHESTPLATE.name(), EquipmentSlot.CHEST);
        map.put(Material.LEATHER_CHESTPLATE.name(), EquipmentSlot.CHEST);
        map.put(Material.NETHERITE_CHESTPLATE.name(), EquipmentSlot.CHEST);
        map.put(Material.CHAINMAIL_CHESTPLATE.name(), EquipmentSlot.CHEST);

        map.put(Material.DIAMOND_LEGGINGS.name(), EquipmentSlot.LEGS);
        map.put(Material.IRON_LEGGINGS.name(), EquipmentSlot.LEGS);
        map.put(Material.GOLDEN_LEGGINGS.name(), EquipmentSlot.LEGS);
        map.put(Material.LEATHER_LEGGINGS.name(), EquipmentSlot.LEGS);
        map.put(Material.NETHERITE_LEGGINGS.name(), EquipmentSlot.LEGS);
        map.put(Material.CHAINMAIL_LEGGINGS.name(), EquipmentSlot.LEGS);

        map.put(Material.DIAMOND_BOOTS.name(), EquipmentSlot.FEET);
        map.put(Material.IRON_BOOTS.name(), EquipmentSlot.FEET);
        map.put(Material.GOLDEN_BOOTS.name(), EquipmentSlot.FEET);
        map.put(Material.LEATHER_BOOTS.name(), EquipmentSlot.FEET);
        map.put(Material.NETHERITE_BOOTS.name(), EquipmentSlot.FEET);
        map.put(Material.CHAINMAIL_BOOTS.name(), EquipmentSlot.FEET);

        EquipmentSlot slot = EquipmentSlot.HAND;

        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry mEntry = (Map.Entry) iter.next();
            String key = (String) mEntry.getKey();
            EquipmentSlot value = (EquipmentSlot) mEntry.getValue();
            if (key == material.name()) {
                slot = value;
                break;
            }
        }

        return slot;
    }
    public static ItemStack addSpeed(final ItemStack s, final double i) {
        ItemMeta meta = s.getItemMeta();
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.movementSpeed", i, AttributeModifier.Operation.ADD_NUMBER, getSlotByMaterial(s.getType()));
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier);
        s.setItemMeta(meta);
        return s;
        //final Attributes attributes = new Attributes(s);
        //attributes.add(Attributes.Attribute.newBuilder().name("Speed").type(Attributes.AttributeType.GENERIC_MOVEMENT_SPEED).amount(i).build());
        //return attributes.getStack();
    }
    
    public static ItemStack addHealth(final ItemStack s, final double i) {
        ItemMeta meta = s.getItemMeta();
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.maxHealth", i, AttributeModifier.Operation.ADD_NUMBER, getSlotByMaterial(s.getType()));
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier);
        s.setItemMeta(meta);
        return s;
        //final Attributes attributes = new Attributes(s);
        //attributes.add(Attributes.Attribute.newBuilder().name("Health").type(Attributes.AttributeType.GENERIC_MAX_HEALTH).amount(i).build());
        //return attributes.getStack();
    }
    
    public static ItemStack addKnockBackRes(final ItemStack s, final double i) {
        ItemMeta meta = s.getItemMeta();
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.knockbackResistance", i, AttributeModifier.Operation.ADD_NUMBER, getSlotByMaterial(s.getType()));
        meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, modifier);
        s.setItemMeta(meta);
        return s;
        /*
        final Attributes attributes = new Attributes(s);
        attributes.add(Attributes.Attribute.newBuilder().name("KnockBack").type(Attributes.AttributeType.GENERIC_KNOCKBACK_RESISTANCE).amount(i).build());
        return attributes.getStack();*/
    }
    
    public static ItemStack addDamage(final ItemStack s, final double i) {
        ItemMeta meta = s.getItemMeta();
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", i, AttributeModifier.Operation.ADD_NUMBER, getSlotByMaterial(s.getType()));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
        s.setItemMeta(meta);
        return s;
        /*
        final Attributes attributes = new Attributes(s);
        attributes.add(Attributes.Attribute.newBuilder().name("Damage").type(Attributes.AttributeType.GENERIC_ATTACK_DAMAGE).amount(i).build());
        return attributes.getStack();*/
    }
    
    public static ItemStack addFollowRange(final ItemStack s, final double i) {
        ItemMeta meta = s.getItemMeta();
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.followRange", i, AttributeModifier.Operation.ADD_NUMBER, getSlotByMaterial(s.getType()));
        meta.addAttributeModifier(Attribute.GENERIC_FOLLOW_RANGE, modifier);
        s.setItemMeta(meta);
        return s;
        /*final Attributes attributes = new Attributes(s);
        attributes.add(Attributes.Attribute.newBuilder().name("FollowRange").type(Attributes.AttributeType.GENERIC_FOLLOW_RANGE).amount(i).build());
        return attributes.getStack();*/
    }
}
