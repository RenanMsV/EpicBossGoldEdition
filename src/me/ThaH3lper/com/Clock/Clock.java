package me.ThaH3lper.com.Clock;

import me.ThaH3lper.com.SaveLoad.*;
import me.ThaH3lper.com.*;
import me.ThaH3lper.com.Timer.*;
import me.ThaH3lper.com.Timer.Timer;
import me.ThaH3lper.com.Spawning.*;
import java.util.*;

public class Clock implements Runnable
{
    int save;
    int timer;
    int walk;
    
    public Clock() {
        this.save = 0;
        this.timer = 0;
        this.walk = 0;
    }
    
    @Override
    public void run() {
        ++this.save;
        ++this.timer;
        ++this.walk;
        if (this.save >= LoadSetup.Inteval) {
            executeSave();
            this.save = 0;
        }
        if (this.timer >= LoadSetup.timerupdate) {
            for (final Timer t : EpicBoss.plugin.allTimers) {
                t.tick(LoadSetup.timerupdate);
            }
            this.timer = 0;
        }
        if (this.walk >= LoadSetup.walkupdate) {
            for (final Timer t : EpicBoss.plugin.allTimers) {
                t.WalkCheck();
            }
            this.walk = 0;
        }
        SpawningHandler.updateSpawning();
    }
    
    public static void executeSave() {
        LoadSetup.SaveAll();
    }
}
