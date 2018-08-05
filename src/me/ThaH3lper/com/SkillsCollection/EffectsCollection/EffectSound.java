package me.ThaH3lper.com.SkillsCollection.EffectsCollection;

import org.bukkit.*;

public class EffectSound
{
    public static void PlaySound(final Location location, final String ed) {
        final String[] data = ed.split(":");
        String sound = data[0];
        final float volume = (data.length > 1) ? Float.parseFloat(data[1]) : 1.0f;
        final float pitch = (data.length > 2) ? Float.parseFloat(data[2]) : 1.0f;
        if (sound.equals("random.wood_click")) {
            sound = "random.wood click";
        }
        else if (sound.equals("mob.ghast.affectionate_scream")) {
            sound = "mob.ghast.affectionate scream";
        }
    }
}
