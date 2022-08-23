package me.ThaH3lper.com.Timer;

import java.util.*;
import me.ThaH3lper.com.Mobs.*;

public class EpicTimer
{
    public String cmdName;
    public String file;
    public List<EpicMobs> bosses;
    public int amount;
    public int interval;
    public int walk;
    
    public EpicTimer(final List<EpicMobs> bosses, final int amount, final int interval, final int walk, final String cmdName, final String file) {
        this.bosses = bosses;
        this.amount = amount;
        this.interval = interval;
        this.walk = walk;
        this.cmdName = cmdName;
        this.file = file;
    }
}
