package me.ThaH3lper.com.Items;

import org.bukkit.inventory.*;
import me.ThaH3lper.com.Libs.*;
import org.bukkit.enchantments.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import me.ThaH3lper.com.*;

public class ItemHandler
{
    public static ItemStack getItemStack(final EpicItems ei) {
        final int id = ei.id;
        final short data = (short)ei.data;
        final int amount = ei.amount;
        ItemStack stack = new ItemStack(Material.getMaterial(id), amount, data);
        ItemMeta im = stack.getItemMeta();
        if (ei.Lores != null) {
            im = setLores(im, ei.Lores);
        }
        if (ei.Enchants != null) {
            im = setEnchants(im, ei.Enchants);
        }
        if (ei.Display != null) {
            im = setDisplay(im, ei.Display);
        }
        stack.setItemMeta(im);
        if (ei.color != null) {
            stack = setLeatherColor(stack, ei);
        }
        if (ei.player != null) {
            stack = setPlayerName(stack, ei);
        }
        stack = AttributeHandler.addHealth(stack, ei.health);
        stack = AttributeHandler.addDamage(stack, ei.damage);
        stack = AttributeHandler.addSpeed(stack, ei.speed);
        stack = AttributeHandler.addKnockBackRes(stack, ei.knock);
        stack = AttributeHandler.addFollowRange(stack, ei.range);
        return stack;
    }
    
    public static ItemMeta setEnchants(final ItemMeta im, final List<String> enchants) {
        for (final String s : enchants) {
            if (s.contains(":")) {
                final String[] part = s.split(":");
                im.addEnchant(Enchantment.getByName(part[0]), Integer.parseInt(part[1]), true);
            }
        }
        return im;
    }
    
    public static ItemMeta setLores(final ItemMeta im, final List<String> lores) {
        final List<String> list = new ArrayList<String>();
        for (String s : lores) {
            s = ChatColor.translateAlternateColorCodes('&', s);
            list.add(s);
        }
        im.setLore((List)list);
        return im;
    }
    
    public static ItemMeta setDisplay(final ItemMeta im, String s) {
        s = ChatColor.translateAlternateColorCodes('&', s);
        im.setDisplayName(s);
        return im;
    }
    
    public static ItemStack setLeatherColor(final ItemStack item, final EpicItems ei) {
        if (item.getType().equals((Object)Material.LEATHER_CHESTPLATE) || item.getType().equals((Object)Material.LEATHER_BOOTS) || item.getType().equals((Object)Material.LEATHER_LEGGINGS) || item.getType().equals((Object)Material.LEATHER_HELMET)) {
            final String[] rgb = ei.color.split(",");
            final int r = Integer.parseInt(rgb[0]);
            final int g = Integer.parseInt(rgb[1]);
            final int b = Integer.parseInt(rgb[2]);
            final ItemMeta im = item.getItemMeta();
            final LeatherArmorMeta la = (LeatherArmorMeta)im;
            la.setColor(Color.fromRGB(r, g, b));
            item.setItemMeta((ItemMeta)la);
        }
        return item;
    }
    
    public static ItemStack setPlayerName(final ItemStack item, final EpicItems ei) {
        if (item.getType().equals((Object)Material.SKULL) || item.getType().equals((Object)Material.SKULL_ITEM)) {
            final SkullMeta meta = (SkullMeta)item.getItemMeta();
            meta.setOwner(ei.player);
            item.setItemMeta((ItemMeta)meta);
        }
        return item;
    }
    
    public static EpicItems getEpicItem(final String s) {
        for (final EpicItems ei : EpicBoss.plugin.listItems) {
            if (ei.cmdName.equals(s)) {
                return ei;
            }
        }
        return null;
    }
}
