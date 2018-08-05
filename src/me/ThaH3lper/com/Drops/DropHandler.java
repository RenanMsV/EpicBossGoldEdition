package me.ThaH3lper.com.Drops;

import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Mobs.*;
import java.util.*;
import org.bukkit.entity.*;
import me.ThaH3lper.com.Drops.Fair.*;
import org.bukkit.*;
import org.bukkit.inventory.*;

public class DropHandler
{
    public static EpicNormal getEpicNormal(final String s) {
        for (final EpicNormal en : EpicBoss.plugin.listLoots) {
            if (en.cmdName.equals(s)) {
                return en;
            }
        }
        return null;
    }
    
    public static void Drop(final Location loc, final int Exp, final List<ItemStack> drops) {
        for (final ItemStack is : drops) {
            loc.getWorld().dropItemNaturally(loc, is);
        }
        if (Exp != 0) {
            final int i = Exp % 4;
            final int per = (Exp - Exp % 4) / 4;
            for (int y = 0; y < 4; ++y) {
                final ExperienceOrb eo = (ExperienceOrb)loc.getWorld().spawnEntity(loc, EntityType.EXPERIENCE_ORB);
                eo.setExperience(per);
            }
            if (i != 0) {
                final ExperienceOrb eo2 = (ExperienceOrb)loc.getWorld().spawnEntity(loc, EntityType.EXPERIENCE_ORB);
                eo2.setExperience(i);
            }
        }
    }
    
    public static FairDrops getFairDrops(final LivingEntity l) {
        for (final FairDrops fd : EpicBoss.plugin.listFair) {
            if (fd.entity.equals(l)) {
                return fd;
            }
        }
        return null;
    }
    
    public static List<EpicNormal> getFairDropList(final EpicMobs em) {
        final List<EpicNormal> list = new ArrayList<EpicNormal>();
        int i = 0;
        for (final String s : em.loot) {
            if (s.contains(":")) {
                final String[] split = s.split(":");
                final EpicNormal en = getEpicNormal(split[0]);
                if (split[1].equals("rest")) {
                    continue;
                }
                i = Integer.parseInt(split[1]);
                if (en == null || i == 0) {
                    continue;
                }
                list.add(i - 1, en);
            }
        }
        return list;
    }
    
    public static EpicNormal getEpicNormalRest(final EpicMobs em) {
        for (final String s : em.loot) {
            if (s.contains(":")) {
                final String[] split = s.split(":");
                if (!split[1].equals("rest")) {
                    continue;
                }
                final EpicNormal en = getEpicNormal(split[0]);
                if (en != null) {
                    return en;
                }
                continue;
            }
        }
        return null;
    }
    
    public static FairPlayer getFairPlayer(final FairDrops fd, final Player p) {
        for (final FairPlayer fp : fd.players) {
            if (fp.player.equals(p)) {
                return fp;
            }
        }
        return null;
    }
    
    public static void dropPlayer(final EpicNormal en, final Player p) {
        final String name = ChatColor.GOLD + "Loot! " + ChatColor.GREEN + "Exp: " + ChatColor.GRAY + en.Exp;
        final Inventory i = Bukkit.getServer().createInventory((InventoryHolder)null, 18, name);
        for (final ItemStack s : en.getDrops()) {
            i.addItem(new ItemStack[] { s });
        }
        p.openInventory(i);
        p.giveExp(en.Exp);
    }
}
