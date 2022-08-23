package me.ThaH3lper.com.Libs;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.entity.Villager.Profession;

import me.ThaH3lper.com.EpicBoss;

public class RandomLib {

	public static DyeColor getRandomDyeColor() {
	    return (DyeColor)getRandomEnum(DyeColor.class);
	}
	
	public static Profession getRandomVillagerProfession() {
		return (Profession)getRandomEnum(Profession.class);
	}
	
	private static <E extends Enum<E>> Object getRandomEnum(Class<E> c) {
	    return c.getEnumConstants()[EpicBoss.r.nextInt(c.getEnumConstants().length)];
	}
	public static Color getRandomColor() {
		int r = EpicBoss.r.nextInt(128) + 128; // 128 ... 255
		int g = EpicBoss.r.nextInt(128) + 128; // 128 ... 255
		int b = EpicBoss.r.nextInt(128) + 128; // 128 ... 255
		return Color.fromRGB(r, g, b);
	}
}

