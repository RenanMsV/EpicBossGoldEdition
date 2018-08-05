package me.ThaH3lper.com.SaveLoad;

import java.io.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.SaveLoad.Load.*;
import me.ThaH3lper.com.Timer.*;
import org.bukkit.*;

public class LoadSetup
{
    public static File[] MobFiles;
    public static File[] ItemFiles;
    public static File[] LootFiles;
    public static File[] SkillFiles;
    public static File[] TimerFiles;
    public static File[] SpawningFiles;
    public static int Inteval;
    public static int timerupdate;
    public static int walkupdate;
    public static int ShowHealthRadius;
    public static String ShowHealth;
    
    static {
        LoadSetup.Inteval = 300;
    }
    
    public static void ResetAll() {
        EpicBoss.plugin.saveItemList.clear();
        EpicBoss.plugin.saveMobList.clear();
        EpicBoss.plugin.saveLootList.clear();
        EpicBoss.plugin.saveSkillList.clear();
        EpicBoss.plugin.saveTimerList.clear();
        EpicBoss.plugin.saveSpawningList.clear();
        EpicBoss.plugin.allMobs.clear();
        EpicBoss.plugin.allTimers.clear();
        EpicBoss.plugin.listFair.clear();
        EpicBoss.plugin.fairItems.clear();
        EpicBoss.plugin.listLoots.clear();
        EpicBoss.plugin.listItems.clear();
        EpicBoss.plugin.listMobslist.clear();
        EpicBoss.plugin.listMobs.clear();
        EpicBoss.plugin.listLoc.clear();
        EpicBoss.plugin.listSkills.clear();
        EpicBoss.plugin.listTimers.clear();
        EpicBoss.plugin.listSpawning.clear();
    }
    
    public static void LoadAll(final boolean msg) {
        EpicBoss.plugin.savelist = new SaveLoad(EpicBoss.plugin, "SaveList.yml", "Save");
        EpicBoss.plugin.mobs = new SaveLoad(EpicBoss.plugin, "MobsExample.yml", "Mobs");
        EpicBoss.plugin.items = new SaveLoad(EpicBoss.plugin, "ItemsExample.yml", "Items");
        EpicBoss.plugin.loots = new SaveLoad(EpicBoss.plugin, "LootExample.yml", "Loots");
        EpicBoss.plugin.skills = new SaveLoad(EpicBoss.plugin, "SkillExample.yml", "Skills");
        EpicBoss.plugin.timers = new SaveLoad(EpicBoss.plugin, "TimerExample.yml", "Timers");
        EpicBoss.plugin.spawning = new SaveLoad(EpicBoss.plugin, "SpawningExample.yml", "RandomSpawning");
        EpicBoss.plugin.settings = new SaveLoad(EpicBoss.plugin, "Settings.yml");
        LoadSetup.MobFiles = new File(EpicBoss.plugin.mobs.thefile.getParent()).listFiles();
        LoadSetup.ItemFiles = new File(EpicBoss.plugin.items.thefile.getParent()).listFiles();
        LoadSetup.LootFiles = new File(EpicBoss.plugin.loots.thefile.getParent()).listFiles();
        LoadSetup.SkillFiles = new File(EpicBoss.plugin.skills.thefile.getParent()).listFiles();
        LoadSetup.TimerFiles = new File(EpicBoss.plugin.timers.thefile.getParent()).listFiles();
        LoadSetup.SpawningFiles = new File(EpicBoss.plugin.spawning.thefile.getParent()).listFiles();
        EpicBoss.plugin.saveItemList = SaveLoadHandler.getSaveLoad(LoadSetup.ItemFiles, "Items");
        EpicBoss.plugin.saveMobList = SaveLoadHandler.getSaveLoad(LoadSetup.MobFiles, "Mobs");
        EpicBoss.plugin.saveLootList = SaveLoadHandler.getSaveLoad(LoadSetup.LootFiles, "Loots");
        EpicBoss.plugin.saveSkillList = SaveLoadHandler.getSaveLoad(LoadSetup.SkillFiles, "Skills");
        EpicBoss.plugin.saveTimerList = SaveLoadHandler.getSaveLoad(LoadSetup.TimerFiles, "Timers");
        EpicBoss.plugin.saveSpawningList = SaveLoadHandler.getSaveLoad(LoadSetup.SpawningFiles, "RandomSpawning");
        LoadItems.LoadAllItems();
        LoadMobs.LoadAllMobs();
        LoadMobList.LoadMobsList();
        LoadLocation.loadAllLocations();
        LoadLoots.LoadAllLoot();
        LoadSkills.LoadAllSkills();
        LoadTimers.LoadAllTimers();
        LoadSpawning.LoadSpawnings();
        TimerHandler.LoadAllTimers();
        loadSettings();
        if (msg) {
            EpicBoss.plugin.logger.info("----------<| \u001b[32mEpicBoss \u001b[33mGold \u001b[32mEdition \u001b[0m|>----------");
            EpicBoss.plugin.logger.info(SaveLoadHandler.getList("\u001b[31mMobFiles:\u001b[0m ", LoadSetup.MobFiles));
            EpicBoss.plugin.logger.info(SaveLoadHandler.getList("\u001b[31mItemFiles:\u001b[0m ", LoadSetup.ItemFiles));
            EpicBoss.plugin.logger.info(SaveLoadHandler.getList("\u001b[31mLootFiles:\u001b[0m ", LoadSetup.LootFiles));
            EpicBoss.plugin.logger.info(SaveLoadHandler.getList("\u001b[31mSkillFiles:\u001b[0m ", LoadSetup.SkillFiles));
            EpicBoss.plugin.logger.info(SaveLoadHandler.getList("\u001b[31mTimerFiles:\u001b[0m ", LoadSetup.TimerFiles));
            EpicBoss.plugin.logger.info(SaveLoadHandler.getList("\u001b[31mSpawningFiles:\u001b[0m ", LoadSetup.SpawningFiles));
            EpicBoss.plugin.logger.info("-----------------------------------------------");
        }
    }
    
    public static void SaveAll() {
        LoadMobList.SaveMobsList();
        LoadLocation.saveAllLocations();
        TimerHandler.SaveAllTimers();
    }
    
    public static void loadSettings() {
        if (EpicBoss.plugin.settings.getCustomConfig().contains("SaveInterval")) {
            final int i = EpicBoss.plugin.settings.getCustomConfig().getInt("SaveInterval");
            LoadSetup.Inteval = i * 60;
            LoadSetup.timerupdate = EpicBoss.plugin.settings.getCustomConfig().getInt("TimerUpdate");
            LoadSetup.walkupdate = EpicBoss.plugin.settings.getCustomConfig().getInt("WalkUpdate");
            LoadSetup.ShowHealthRadius = EpicBoss.plugin.settings.getCustomConfig().getInt("ShowHealthRadius");
            LoadSetup.ShowHealth = EpicBoss.plugin.settings.getCustomConfig().getString("ShowHealth");
            LoadSetup.ShowHealth = ChatColor.translateAlternateColorCodes('&', LoadSetup.ShowHealth);
        }
    }
}
