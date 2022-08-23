package me.ThaH3lper.com.SaveLoad;

import java.io.*;
import java.util.*;
import me.ThaH3lper.com.*;

public class SaveLoadHandler
{
    public static String getList(String s, final File[] list) {
        for (final File f : list) {
            s = String.valueOf(s) + f.getName() + ", ";
        }
        return s;
    }
    
    public static List<SaveLoad> getSaveLoad(final File[] itemFiles, final String s) {
        final List<SaveLoad> list = new ArrayList<SaveLoad>();
        for (final File f : itemFiles) {
            list.add(new SaveLoad(EpicBoss.plugin, f.getName(), s));
        }
        return list;
    }
}
