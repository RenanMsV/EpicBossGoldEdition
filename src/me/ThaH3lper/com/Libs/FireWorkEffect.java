package me.ThaH3lper.com.Libs;

import java.lang.reflect.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.meta.*;

public class FireWorkEffect
{
    private Method world_getHandle;
    private Method nms_world_broadcastEntityEffect;
    private Method firework_getHandle;
    
    public FireWorkEffect() {
        this.world_getHandle = null;
        this.nms_world_broadcastEntityEffect = null;
        this.firework_getHandle = null;
    }
    
    public void playFirework(final World world, final Location loc, final FireworkEffect fe) throws Exception {
        final Firework fw = (Firework)world.spawn(loc, (Class)Firework.class);
        Object nms_world = null;
        Object nms_firework = null;
        if (this.world_getHandle == null) {
            this.world_getHandle = getMethod(world.getClass(), "getHandle");
            this.firework_getHandle = getMethod(fw.getClass(), "getHandle");
        }
        nms_world = this.world_getHandle.invoke(world, (Object[])null);
        nms_firework = this.firework_getHandle.invoke(fw, (Object[])null);
        if (this.nms_world_broadcastEntityEffect == null) {
            this.nms_world_broadcastEntityEffect = getMethod(nms_world.getClass(), "broadcastEntityEffect");
        }
        final FireworkMeta data = fw.getFireworkMeta();
        data.clearEffects();
        data.setPower(1);
        data.addEffect(fe);
        fw.setFireworkMeta(data);
        this.nms_world_broadcastEntityEffect.invoke(nms_world, nms_firework, (byte)17);
        fw.remove();
    }
    
    private static Method getMethod(final Class<?> cl, final String method) {
        Method[] methods;
        for (int length = (methods = cl.getMethods()).length, i = 0; i < length; ++i) {
            final Method m = methods[i];
            if (m.getName().equals(method)) {
                return m;
            }
        }
        return null;
    }
}
