package me.ThaH3lper.com.Mobs;

import org.bukkit.*;
import org.bukkit.entity.*;

import me.ThaH3lper.com.Libs.RandomLib;

public class AllMobs
{
    public static LivingEntity spawnMob(final String Mobtype, final Location l) {
        if (Mobtype.equalsIgnoreCase("zombie")) {
            final Zombie e = (Zombie)l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
            e.setBaby(false);
            return (LivingEntity)e;
        }
        if (Mobtype.equalsIgnoreCase("babyzombie")) {
            final Zombie e = (Zombie)l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
            e.setBaby(true);
            return (LivingEntity)e;
        }
        if (Mobtype.equalsIgnoreCase("villagezombie")) {
            final Zombie e = (Zombie)l.getWorld().spawnEntity(l, EntityType.ZOMBIE_VILLAGER);
            e.setBaby(false);
            e.setVillagerProfession(RandomLib.getRandomVillagerProfession());
            return (LivingEntity)e;
        }
        if (Mobtype.equalsIgnoreCase("babyvillagezombie")) {
            final Zombie e = (Zombie)l.getWorld().spawnEntity(l, EntityType.ZOMBIE_VILLAGER);
            e.setBaby(true);
            e.setVillagerProfession(RandomLib.getRandomVillagerProfession());
            return (LivingEntity)e;
        }
        if (Mobtype.equalsIgnoreCase("wolf")) {
            final Wolf e2 = (Wolf)l.getWorld().spawnEntity(l, EntityType.WOLF);
            e2.setBreed(false);
            return (LivingEntity)e2;
        }
        if (Mobtype.equalsIgnoreCase("babywolf")) {
            final Wolf e2 = (Wolf)l.getWorld().spawnEntity(l, EntityType.WOLF);
            e2.setBreed(false);
            e2.setBaby();
            return (LivingEntity)e2;
        }
        if (Mobtype.equalsIgnoreCase("angrywolf")) {
            final Wolf e2 = (Wolf)l.getWorld().spawnEntity(l, EntityType.WOLF);
            e2.setAngry(true);
            e2.setBreed(false);
            return (LivingEntity)e2;
        }
        if (Mobtype.equalsIgnoreCase("angrybabywolf")) {
            final Wolf e2 = (Wolf)l.getWorld().spawnEntity(l, EntityType.WOLF);
            e2.setAngry(true);
            e2.setBreed(false);
            e2.setBaby();
            return (LivingEntity)e2;
        }
        if (Mobtype.equalsIgnoreCase("wither")) {
            final Wither e3 = (Wither)l.getWorld().spawnEntity(l, EntityType.WITHER);
            return (LivingEntity)e3;
        }
        if (Mobtype.equalsIgnoreCase("witch")) {
            final Witch e4 = (Witch)l.getWorld().spawnEntity(l, EntityType.WITCH);
            return (LivingEntity)e4;
        }
        if (Mobtype.equalsIgnoreCase("villager")) {
            final Villager e5 = (Villager)l.getWorld().spawnEntity(l, EntityType.VILLAGER);
            e5.setAdult();
            e5.setBreed(false);
            return (LivingEntity)e5;
        }
        if (Mobtype.equalsIgnoreCase("babyvillager")) {
            final Villager e5 = (Villager)l.getWorld().spawnEntity(l, EntityType.VILLAGER);
            e5.setBaby();
            e5.setBreed(false);
            return (LivingEntity)e5;
        }
        if (Mobtype.equalsIgnoreCase("squid")) {
            final Squid e6 = (Squid)l.getWorld().spawnEntity(l, EntityType.SQUID);
            return (LivingEntity)e6;
        }
        if (Mobtype.equalsIgnoreCase("spider")) {
            final Spider e7 = (Spider)l.getWorld().spawnEntity(l, EntityType.SPIDER);
            return (LivingEntity)e7;
        }
        if (Mobtype.equalsIgnoreCase("snowman")) {
            final Snowman e8 = (Snowman)l.getWorld().spawnEntity(l, EntityType.SNOWMAN);
            return (LivingEntity)e8;
        }
        if (Mobtype.equalsIgnoreCase("slime")) {
            final Slime e9 = (Slime)l.getWorld().spawnEntity(l, EntityType.SLIME);
            return (LivingEntity)e9;
        }
        if (Mobtype.equalsIgnoreCase("skeleton")) {
            final Skeleton e10 = (Skeleton)l.getWorld().spawnEntity(l, EntityType.SKELETON);
            return (LivingEntity)e10;
        }
        if (Mobtype.equalsIgnoreCase("witherskeleton")) {
            final Skeleton e10 = (Skeleton)l.getWorld().spawnEntity(l, EntityType.WITHER_SKELETON);
            return (LivingEntity)e10;
        }
        if (Mobtype.equalsIgnoreCase("silverfish")) {
            final Silverfish e11 = (Silverfish)l.getWorld().spawnEntity(l, EntityType.SILVERFISH);
            return (LivingEntity)e11;
        }
        if (Mobtype.equalsIgnoreCase("sheep")) {
            final Sheep e12 = (Sheep)l.getWorld().spawnEntity(l, EntityType.SHEEP);
            e12.setAdult();
            return (LivingEntity)e12;
        }
        if (Mobtype.equalsIgnoreCase("babysheep")) {
            final Sheep e12 = (Sheep)l.getWorld().spawnEntity(l, EntityType.SHEEP);
            e12.setBaby();
            return (LivingEntity)e12;
        }
        if (Mobtype.equalsIgnoreCase("pigzombie")) {
            final PigZombie e13 = (PigZombie)l.getWorld().spawnEntity(l, EntityType.PIG_ZOMBIE);
            return (LivingEntity)e13;
        }
        if (Mobtype.equalsIgnoreCase("angrypigzombie")) {
            final PigZombie e13 = (PigZombie)l.getWorld().spawnEntity(l, EntityType.PIG_ZOMBIE);
            e13.setAngry(true);
            return (LivingEntity)e13;
        }
        if (Mobtype.equalsIgnoreCase("babypigzombie")) {
            final PigZombie e13 = (PigZombie)l.getWorld().spawnEntity(l, EntityType.PIG_ZOMBIE);
            e13.setBaby(true);
            return (LivingEntity)e13;
        }
        if (Mobtype.equalsIgnoreCase("angrybabypigzombie")) {
            final PigZombie e13 = (PigZombie)l.getWorld().spawnEntity(l, EntityType.PIG_ZOMBIE);
            e13.setAngry(true);
            e13.setBaby(true);
            return (LivingEntity)e13;
        }
        if (Mobtype.equalsIgnoreCase("pig")) {
            final Pig e14 = (Pig)l.getWorld().spawnEntity(l, EntityType.PIG);
            e14.setAdult();
            return (LivingEntity)e14;
        }
        if (Mobtype.equalsIgnoreCase("babypig")) {
            final Pig e14 = (Pig)l.getWorld().spawnEntity(l, EntityType.PIG);
            e14.setBaby();
            return (LivingEntity)e14;
        }
        if (Mobtype.equalsIgnoreCase("ocelot")) {
            final Ocelot e15 = (Ocelot)l.getWorld().spawnEntity(l, EntityType.OCELOT);
            e15.setAdult();
            return (LivingEntity)e15;
        }
        if (Mobtype.equalsIgnoreCase("babyocelot")) {
            final Ocelot e15 = (Ocelot)l.getWorld().spawnEntity(l, EntityType.OCELOT);
            e15.setBaby();
            return (LivingEntity)e15;
        }
        if (Mobtype.equalsIgnoreCase("mushroomcow")) {
            final MushroomCow e16 = (MushroomCow)l.getWorld().spawnEntity(l, EntityType.MUSHROOM_COW);
            e16.setAdult();
            return (LivingEntity)e16;
        }
        if (Mobtype.equalsIgnoreCase("babymushroomcow")) {
            final MushroomCow e16 = (MushroomCow)l.getWorld().spawnEntity(l, EntityType.MUSHROOM_COW);
            e16.setBaby();
            return (LivingEntity)e16;
        }
        if (Mobtype.equalsIgnoreCase("magmacube")) {
            final MagmaCube e17 = (MagmaCube)l.getWorld().spawnEntity(l, EntityType.MAGMA_CUBE);
            return (LivingEntity)e17;
        }
        if (Mobtype.equalsIgnoreCase("irongolem")) {
            final IronGolem e18 = (IronGolem)l.getWorld().spawnEntity(l, EntityType.IRON_GOLEM);
            return (LivingEntity)e18;
        }
        if (Mobtype.equalsIgnoreCase("horse")) {
            final Horse e19 = (Horse)l.getWorld().spawnEntity(l, EntityType.HORSE);
            e19.setAdult();
            return (LivingEntity)e19;
        }
        if (Mobtype.equalsIgnoreCase("babyhorse")) {
            final Horse e19 = (Horse)l.getWorld().spawnEntity(l, EntityType.HORSE);
            e19.setBaby();
            return (LivingEntity)e19;
        }
        if (Mobtype.equalsIgnoreCase("giant")) {
            final Giant e20 = (Giant)l.getWorld().spawnEntity(l, EntityType.GIANT);
            return (LivingEntity)e20;
        }
        if (Mobtype.equalsIgnoreCase("ghast")) {
            final Ghast e21 = (Ghast)l.getWorld().spawnEntity(l, EntityType.GHAST);
            return (LivingEntity)e21;
        }
        if (Mobtype.equalsIgnoreCase("enderman")) {
            final Enderman e22 = (Enderman)l.getWorld().spawnEntity(l, EntityType.ENDERMAN);
            return (LivingEntity)e22;
        }
        if (Mobtype.equalsIgnoreCase("creeper")) {
            final Creeper e23 = (Creeper)l.getWorld().spawnEntity(l, EntityType.CREEPER);
            return (LivingEntity)e23;
        }
        if (Mobtype.equalsIgnoreCase("poweredcreeper")) {
            final Creeper e23 = (Creeper)l.getWorld().spawnEntity(l, EntityType.CREEPER);
            e23.setPowered(true);
            return (LivingEntity)e23;
        }
        if (Mobtype.equalsIgnoreCase("cow")) {
            final Cow e24 = (Cow)l.getWorld().spawnEntity(l, EntityType.COW);
            e24.setAdult();
            return (LivingEntity)e24;
        }
        if (Mobtype.equalsIgnoreCase("babycow")) {
            final Cow e24 = (Cow)l.getWorld().spawnEntity(l, EntityType.COW);
            e24.setBaby();
            return (LivingEntity)e24;
        }
        if (Mobtype.equalsIgnoreCase("chicken")) {
            final Chicken e25 = (Chicken)l.getWorld().spawnEntity(l, EntityType.CHICKEN);
            e25.setAdult();
            return (LivingEntity)e25;
        }
        if (Mobtype.equalsIgnoreCase("babychicken")) {
            final Chicken e25 = (Chicken)l.getWorld().spawnEntity(l, EntityType.CHICKEN);
            e25.setBaby();
            return (LivingEntity)e25;
        }
        if (Mobtype.equalsIgnoreCase("cavespider")) {
            final CaveSpider e26 = (CaveSpider)l.getWorld().spawnEntity(l, EntityType.CAVE_SPIDER);
            return (LivingEntity)e26;
        }
        if (Mobtype.equalsIgnoreCase("blaze")) {
            final Blaze e27 = (Blaze)l.getWorld().spawnEntity(l, EntityType.BLAZE);
            return (LivingEntity)e27;
        }
        if (Mobtype.equalsIgnoreCase("bat")) {
            final Bat e28 = (Bat)l.getWorld().spawnEntity(l, EntityType.BAT);
            return (LivingEntity)e28;
        }
        if (Mobtype.equalsIgnoreCase("enderdragon")) {
            final EnderDragon e29 = (EnderDragon)l.getWorld().spawnEntity(l, EntityType.ENDER_DRAGON);
            return (LivingEntity)e29;
        }
        if (Mobtype.equalsIgnoreCase("stray")) {
            final Stray e30 = (Stray)l.getWorld().spawnEntity(l, EntityType.STRAY);
            return (LivingEntity)e30;
        }
        if (Mobtype.equalsIgnoreCase("vex")) {
            final Vex e31 = (Vex)l.getWorld().spawnEntity(l, EntityType.VEX);
            return (LivingEntity)e31;
        }
        if (Mobtype.equalsIgnoreCase("vindicator")) {
            final Vindicator e32 = (Vindicator)l.getWorld().spawnEntity(l, EntityType.VINDICATOR);
            return (LivingEntity)e32;
        }
        if (Mobtype.equalsIgnoreCase("husk")) {
            final Husk e33 = (Husk)l.getWorld().spawnEntity(l, EntityType.HUSK);
            return (LivingEntity)e33;
        }
        if (Mobtype.equalsIgnoreCase("shulker")) {
            final Shulker e34 = (Shulker)l.getWorld().spawnEntity(l, EntityType.SHULKER);
            return (LivingEntity)e34;
        }
        if (Mobtype.equalsIgnoreCase("evoker")) {
            final Evoker e35 = (Evoker)l.getWorld().spawnEntity(l, EntityType.EVOKER);
            return (LivingEntity)e35;
        }
        if (Mobtype.equalsIgnoreCase("guardian")) {
            final Guardian e36 = (Guardian)l.getWorld().spawnEntity(l, EntityType.GUARDIAN);
            return (LivingEntity)e36;
        }
        if (Mobtype.equalsIgnoreCase("elderguardian")) {
            final ElderGuardian e37 = (ElderGuardian)l.getWorld().spawnEntity(l, EntityType.ELDER_GUARDIAN);
            return (LivingEntity)e37;
        }
        if (Mobtype.equalsIgnoreCase("endermite")) {
            final Endermite e38 = (Endermite)l.getWorld().spawnEntity(l, EntityType.ENDERMITE);
            return (LivingEntity)e38;
        }
        if (Mobtype.equalsIgnoreCase("rabbit")) {
            final Rabbit e39 = (Rabbit)l.getWorld().spawnEntity(l, EntityType.RABBIT);
            return (LivingEntity)e39;
        }
        if (Mobtype.equalsIgnoreCase("polarbear")) {
            final PolarBear e40 = (PolarBear)l.getWorld().spawnEntity(l, EntityType.POLAR_BEAR);
            return (LivingEntity)e40;
        }
        return null;
    }
}
