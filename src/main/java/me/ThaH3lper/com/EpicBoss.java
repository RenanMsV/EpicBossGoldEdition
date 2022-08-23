package me.ThaH3lper.com;

import org.bukkit.plugin.java.*;
import java.util.logging.*;
import me.ThaH3lper.com.Drops.Fair.*;
import me.ThaH3lper.com.Drops.*;
import me.ThaH3lper.com.Items.*;
import me.ThaH3lper.com.Mobs.*;
import me.ThaH3lper.com.Location.*;
import me.ThaH3lper.com.Skills.*;
import me.ThaH3lper.com.Timer.*;
import me.ThaH3lper.com.Timer.Timer;
import me.ThaH3lper.com.Spawning.*;
import me.ThaH3lper.com.SaveLoad.*;
import me.ThaH3lper.com.Commands.*;
import org.bukkit.command.*;
import me.ThaH3lper.com.Clock.*;
import org.bukkit.event.*;
import me.ThaH3lper.com.Listener.*;
import org.bukkit.plugin.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;

public class EpicBoss extends JavaPlugin
{
    public Logger logger;
    public static EpicBoss plugin;
    public String menu;
    public static Random r;
    public SaveLoad mobs;
    public SaveLoad items;
    public SaveLoad loots;
    public SaveLoad skills;
    public SaveLoad savelist;
    public SaveLoad settings;
    public SaveLoad timers;
    public SaveLoad spawning;
    public List<SaveLoad> saveItemList;
    public List<SaveLoad> saveMobList;
    public List<SaveLoad> saveLootList;
    public List<SaveLoad> saveSkillList;
    public List<SaveLoad> saveTimerList;
    public List<SaveLoad> saveSpawningList;
    public List<UUID> allMobs;
    public List<Timer> allTimers;
    public List<FairDrops> listFair;
    public List<Item> fairItems;
    public List<EpicNormal> listLoots;
    public List<EpicItems> listItems;
    public List<EpicMobs> listMobs;
    public List<EpicMobsList> listMobslist;
    public List<EpicLocation> listLoc;
    public List<EpicSkill> listSkills;
    public List<EpicTimer> listTimers;
    public List<EpicSpawning> listSpawning;
    
    static {
        EpicBoss.r = new Random();
    }
    
    public EpicBoss() {
        this.logger = Logger.getLogger("Minecraft");
        this.menu = ChatColor.GREEN + "=====" + ChatColor.GOLD + ChatColor.BOLD + " EpicBoss Updated " + ChatColor.GREEN + "=====";
        this.allMobs = new ArrayList<UUID>();
        this.allTimers = new ArrayList<Timer>();
        this.listFair = new ArrayList<FairDrops>();
        this.fairItems = new ArrayList<Item>();
        this.listLoots = new ArrayList<EpicNormal>();
        this.listItems = new ArrayList<EpicItems>();
        this.listMobs = new ArrayList<EpicMobs>();
        this.listMobslist = new ArrayList<EpicMobsList>();
        this.listLoc = new ArrayList<EpicLocation>();
        this.listSkills = new ArrayList<EpicSkill>();
        this.listTimers = new ArrayList<EpicTimer>();
        this.listSpawning = new ArrayList<EpicSpawning>();
    }
    
    public void onDisable() {
        LoadSetup.SaveAll();
        final PluginDescriptionFile pdfFile = this.getDescription();
        this.logger.info(String.valueOf(pdfFile.getName()) + " Has Been Disabled!");
    }
    
    public void onEnable() {
        final PluginDescriptionFile pdfFile = this.getDescription();
        this.logger.info(String.valueOf(pdfFile.getName()) + " " + pdfFile.getVersion() + " Has Been Enabled!");
        EpicBoss.plugin = this;
        LoadSetup.LoadAll(true);
        this.getCommand("EpicBoss").setExecutor((CommandExecutor)new CommandInput());
        this.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin)this, (Runnable)new Clock(), 0L, 20L);
        final PluginManager manager = this.getServer().getPluginManager();
        manager.registerEvents((Listener)new MobDrop(), (Plugin)this);
        manager.registerEvents((Listener)new MobHit(), (Plugin)this);
        manager.registerEvents((Listener)new SignPlace(), (Plugin)this);
        manager.registerEvents((Listener)new LeashEvent(), (Plugin)this);
        manager.registerEvents((Listener)new MobSkill(), (Plugin)this);
        manager.registerEvents((Listener)new SlimeSplit(), (Plugin)this);
        manager.registerEvents((Listener)new MobSpawn(), (Plugin)this);
        manager.registerEvents((Listener)new MobDamaged(), (Plugin)this);
        manager.registerEvents((Listener)new SkillShootProjectileListener(), (Plugin)this);
    }
    
    public List<LivingEntity> getMobsAll() {
        final List<LivingEntity> list = new ArrayList<LivingEntity>();
        for (final World w : Bukkit.getWorlds()) {
            for (final LivingEntity e : w.getLivingEntities()) {
                if (this.allMobs.contains(e.getUniqueId())) {
                    list.add(e);
                }
            }
        }
        return list;
    }
}
