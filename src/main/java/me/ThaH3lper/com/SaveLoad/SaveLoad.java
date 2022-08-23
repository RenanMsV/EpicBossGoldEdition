package me.ThaH3lper.com.SaveLoad;

import me.ThaH3lper.com.*;
import org.bukkit.configuration.file.*;
//import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;
import org.bukkit.configuration.Configuration;

import java.util.logging.*;
import java.io.*;

public class SaveLoad
{
    private FileConfiguration DataConfig;
    private File data;
    private EpicBoss plugin;
    private String file;
    public File thefile;
    
    public SaveLoad(final EpicBoss plugin, final String newfile) {
        this(plugin, newfile, null);
    }
    
    public SaveLoad(final EpicBoss plugin, final String newfile, final String folder) {
        this.DataConfig = null;
        this.data = null;
        this.plugin = plugin;
        this.file = newfile;
        if (folder != null) {
            this.thefile = new File(plugin.getDataFolder() + System.getProperty("file.separator") + folder, newfile);
        }
        else {
            this.thefile = new File(plugin.getDataFolder(), newfile);
        }
        if (this.thefile.exists()) {
            this.data = this.thefile;
        }
        this.reloadCustomConfig();
        this.saveCustomConfig();
    }
    
    public void reloadCustomConfig() {
        if (this.data == null) {
            this.data = new File(this.thefile.getParent(), this.file);
            this.DataConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.data);
            final InputStream defConfigStream = this.plugin.getResource(this.file);
            if (defConfigStream != null) {
                final YamlConfiguration defConfig = YamlConfiguration.loadConfiguration((Reader)new InputStreamReader(defConfigStream));
                this.DataConfig.setDefaults((Configuration)defConfig);
            }
            this.getCustomConfig().options().copyDefaults(true);
            this.plugin.logger.info(String.valueOf(this.file) + " did not exist! Generated a new one!");
        }
        else {
            this.DataConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.data);
        }
    }
    
    public FileConfiguration getCustomConfig() {
        if (this.DataConfig == null) {
            this.reloadCustomConfig();
        }
        return this.DataConfig;
    }
    
    public void saveCustomConfig() {
        if (this.DataConfig == null || this.data == null) {
            return;
        }
        try {
            this.getCustomConfig().save(this.data);
        }
        catch (IOException ex) {
            this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.data, ex);
        }
    }
}
