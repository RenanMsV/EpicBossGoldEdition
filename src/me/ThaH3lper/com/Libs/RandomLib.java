package me.ThaH3lper.com.Libs;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import me.ThaH3lper.com.EpicBoss;

public class RandomLib {
	public static DyeColor getRandomDyeColor() {
		ArrayList<DyeColor> DyeColorList = new ArrayList<DyeColor>();
		DyeColorList.add(DyeColor.BLACK);
		DyeColorList.add(DyeColor.BLUE);
		DyeColorList.add(DyeColor.BROWN);
		DyeColorList.add(DyeColor.CYAN);
		DyeColorList.add(DyeColor.GRAY);
		DyeColorList.add(DyeColor.GREEN);
		DyeColorList.add(DyeColor.LIGHT_BLUE);
		DyeColorList.add(DyeColor.LIME);
		DyeColorList.add(DyeColor.MAGENTA);
		DyeColorList.add(DyeColor.ORANGE);
		DyeColorList.add(DyeColor.PINK);
		DyeColorList.add(DyeColor.PURPLE);
		DyeColorList.add(DyeColor.RED);
		DyeColorList.add(DyeColor.SILVER);
		DyeColorList.add(DyeColor.WHITE);
		DyeColorList.add(DyeColor.YELLOW);
		return DyeColorList.get(EpicBoss.r.nextInt((15 - 0) + 1) + 0);
	}
	public static Color getRandomColor() {
		int r = EpicBoss.r.nextInt(128) + 128; // 128 ... 255
		int g = EpicBoss.r.nextInt(128) + 128; // 128 ... 255
		int b = EpicBoss.r.nextInt(128) + 128; // 128 ... 255
		return Color.fromRGB(r, g, b);
	}
}

