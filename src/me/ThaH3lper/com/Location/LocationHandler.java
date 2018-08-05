package me.ThaH3lper.com.Location;

import me.ThaH3lper.com.*;
import java.util.*;

public class LocationHandler
{
    public static EpicLocation getEpicLocation(final String s) {
        for (final EpicLocation el : EpicBoss.plugin.listLoc) {
            if (s.equals(el.name)) {
                return el;
            }
        }
        return null;
    }
}
