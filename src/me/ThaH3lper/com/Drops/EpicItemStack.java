package me.ThaH3lper.com.Drops;

import org.bukkit.inventory.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Items.*;

public class EpicItemStack
{
    public ItemStack stack;
    public float chance;
    public int slot;
    
    public EpicItemStack(final ItemStack stack, final float chance) {
        this.slot = 5;
        this.stack = stack;
        this.chance = chance;
    }
    
    public EpicItemStack(final String s) {
        this.slot = 5;
        final String[] part = s.split(" ");
        this.chance = Float.parseFloat(part[1]);
        if (!part[0].contains(":")) {
            final EpicItems ei = ItemHandler.getEpicItem(part[0]);
            if (ei != null) {
                this.stack = ei.getItemStack();
            }
        }
        else {
            final String[] split = part[0].split(":");
            if (split.length == 2) {
                final EpicItems ei2 = ItemHandler.getEpicItem(split[0]);
                if (ei2 != null) {
                    this.stack = ei2.getItemStack();
                }
                this.slot = Integer.parseInt(split[1]);
            }
            else {
                final int id = Integer.parseInt(split[0]);
                final short data = Short.parseShort(split[1]);
                int amount = 0;
                if (split[2].contains("-")) {
                    final String[] nr = split[2].split("-");
                    final int min = Integer.parseInt(nr[0]);
                    final int max = Integer.parseInt(nr[1]);
                    amount = EpicBoss.r.nextInt(max - min) + min;
                }
                else {
                    amount = Integer.parseInt(split[2]);
                }
                if (split.length == 4) {
                    this.slot = Integer.parseInt(split[3]);
                }
                this.stack = new ItemStack(id, amount, data);
            }
        }
    }
}
