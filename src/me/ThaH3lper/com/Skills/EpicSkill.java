package me.ThaH3lper.com.Skills;

import java.util.*;

public class EpicSkill
{
    public List<String> skills;
    public String cmdName;
    public String file;
    public int cooldown;
    
    public EpicSkill(final String cmdName, final String file, final List<String> skills, final int cooldown) {
        this.cmdName = cmdName;
        this.file = file;
        this.skills = skills;
        this.cooldown = cooldown;
    }
}
