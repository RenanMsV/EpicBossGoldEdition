package me.ThaH3lper.com.Drops;

import java.util.*;
import org.bukkit.inventory.*;

public class EpicNormal
{
    Random r;
    public String cmdName;
    public String file;
    public List<EpicItemStack> list;
    int Exp;
    
    public EpicNormal(final List<String> strings, final String name, final String file) {
        this.r = new Random();
        this.list = new ArrayList<EpicItemStack>();
        this.Exp = 0;
        for (final String s : strings) {
            if (s.contains("exp")) {
                final String[] split = s.split(" ");
                this.Exp = Integer.parseInt(split[1]);
            }
            else {
                this.list.add(new EpicItemStack(s));
            }
        }
        this.cmdName = name;
        this.file = file;
    }
    
    public List<ItemStack> getDrops() {
        final List<ItemStack> stack = new ArrayList<ItemStack>();
        for (final EpicItemStack is : this.list) {
            if (this.r.nextFloat() <= is.chance) {
                stack.add(is.stack);
            }
        }
        return stack;
    }
    
    public int getExp() {
        return this.Exp;
    }
}
