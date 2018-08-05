package me.ThaH3lper.com.Location;

import org.bukkit.*;

public class EpicLocation
{
    public Location location;
    public String name;
    Chunk chunk;
    
    public EpicLocation(final String str) {
        final String[] part = str.split(",");
        final World w = Bukkit.getWorld(part[0]);
        final double x = Double.parseDouble(part[1]);
        final double y = Double.parseDouble(part[2]);
        final double z = Double.parseDouble(part[3]);
        final String n = part[4];
        final Location l = new Location(w, x, y, z);
        this.location = l;
        this.name = n;
        this.chunk = l.getChunk();
    }
    
    public EpicLocation(final Location location, final String name) {
        this.location = location;
        this.name = name;
    }
    
    public String getString() {
        final String s = String.valueOf(this.location.getWorld().getName()) + "," + (this.location.getBlockX() + 0.5) + "," + this.location.getBlockY() + "," + (this.location.getBlockZ() + 0.5) + "," + this.name;
        return s;
    }
    
    public void LoadChunk() {
        if (this.chunk != null) {
            this.chunk.load();
        }
    }
}
