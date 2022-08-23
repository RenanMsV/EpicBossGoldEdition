/*
package me.ThaH3lper.com.Libs;

import org.bukkit.craftbukkit.v1_19_R1.inventory.*;
//import net.minecraft.server.v1_12_R1.*;
import java.util.*;
import java.lang.reflect.*;
import java.util.concurrent.*;
import com.google.common.collect.*;
import javax.annotation.*;
import com.google.common.base.*;
import com.google.common.base.Objects;
import org.bukkit.inventory.ItemStack;

public class Attributes
{
    public ItemStack nmsStack;
    private NBTTagCompound parent;
    private NBTTagList attributes;
    
    public Attributes(final org.bukkit.inventory.ItemStack stack) {
        this.nmsStack = CraftItemStack.asNMSCopy(stack);
        if (this.nmsStack.getTag() == null) {
            this.nmsStack.setTag(this.parent = new NBTTagCompound());
        }
        else {
            this.parent = this.nmsStack.getTag();
        }
        if (this.parent.hasKey("AttributeModifiers")) {
            this.attributes = this.parent.getList("AttributeModifiers", 10);
        }
        else {
            this.attributes = new NBTTagList();
            this.parent.set("AttributeModifiers", (NBTBase)this.attributes);
        }
    }
    
    public org.bukkit.inventory.ItemStack getStack() {
        return (org.bukkit.inventory.ItemStack)CraftItemStack.asCraftMirror(this.nmsStack);
    }
    
    public int size() {
        return this.attributes.size();
    }
    
    public void add(final Attribute attribute) {
        this.attributes.add((NBTBase)attribute.data);
    }
    
    public boolean remove(final Attribute attribute) {
        final UUID uuid = attribute.getUUID();
        final Iterator<Attribute> it = this.values().iterator();
        while (it.hasNext()) {
            if (Objects.equal((Object)it.next().getUUID(), (Object)uuid)) {
                it.remove();
                return true;
            }
        }
        return false;
    }
    
    public void clear() {
        this.parent.set("AttributeModifiers", (NBTBase)(this.attributes = new NBTTagList()));
    }
    
    public Attribute get(final int index) {
        return new Attribute(this.attributes.get(index));
    }
    
    public Iterable<Attribute> values() {
        final List<NBTBase> list = this.getList();
        return new Iterable<Attribute>() {
            @Override
            public Iterator<Attribute> iterator() {
                return (Iterator<Attribute>)Iterators.transform((Iterator)list.iterator(), (Function)new Function<NBTBase, Attribute>() {
                    public Attribute apply(@Nullable final NBTBase data) {
                        return new Attribute((NBTTagCompound)data);
                    }
                });
            }
        };
    }
    
    private <T> List<T> getList() {
        try {
            final Field listField = NBTTagList.class.getDeclaredField("list");
            listField.setAccessible(true);
            return (List<T>)listField.get(this.attributes);
        }
        catch (Exception e) {
            throw new RuntimeException("Unable to access reflection.", e);
        }
    }
    
    public enum Operation
    {
        ADD_NUMBER("ADD_NUMBER", 0, 0), 
        MULTIPLY_PERCENTAGE("MULTIPLY_PERCENTAGE", 1, 1), 
        ADD_PERCENTAGE("ADD_PERCENTAGE", 2, 2);
        
        private int id;
        
        private Operation(final String s, final int n, final int id) {
            this.id = id;
        }
        
        public int getId() {
            return this.id;
        }
        
        public static Operation fromId(final int id) {
            Operation[] values;
            for (int length = (values = values()).length, i = 0; i < length; ++i) {
                final Operation op = values[i];
                if (op.getId() == id) {
                    return op;
                }
            }
            throw new IllegalArgumentException("Corrupt operation ID " + id + " detected.");
        }
    }
    
    public static class AttributeType
    {
        private static ConcurrentMap<String, AttributeType> LOOKUP;
        public static final AttributeType GENERIC_MAX_HEALTH;
        public static final AttributeType GENERIC_FOLLOW_RANGE;
        public static final AttributeType GENERIC_ATTACK_DAMAGE;
        public static final AttributeType GENERIC_MOVEMENT_SPEED;
        public static final AttributeType GENERIC_KNOCKBACK_RESISTANCE;
        private final String minecraftId;
        
        static {
            //AttributeType.LOOKUP = (ConcurrentMap<String, AttributeType>) Maps.newConcurrentMap();
        	AttributeType.LOOKUP = Maps.newConcurrentMap();
        	GENERIC_MAX_HEALTH = new AttributeType("generic.maxHealth").register();
            GENERIC_FOLLOW_RANGE = new AttributeType("generic.followRange").register();
            GENERIC_ATTACK_DAMAGE = new AttributeType("generic.attackDamage").register();
            GENERIC_MOVEMENT_SPEED = new AttributeType("generic.movementSpeed").register();
            GENERIC_KNOCKBACK_RESISTANCE = new AttributeType("generic.knockbackResistance").register();
        }
        
        public AttributeType(final String minecraftId) {
            this.minecraftId = minecraftId;
        }
        
        public String getMinecraftId() {
            return this.minecraftId;
        }
        
        public AttributeType register() {
            final AttributeType old = AttributeType.LOOKUP.putIfAbsent(this.minecraftId, this);
            return (old != null) ? old : this;
        }
        
        public static AttributeType fromId(final String minecraftId) {
            return AttributeType.LOOKUP.get(minecraftId);
        }
        
        public static Iterable<AttributeType> values() {
            return (Iterable<AttributeType>)AttributeType.LOOKUP.values();
        }
    }
    
    public static class Attribute
    {
        private NBTTagCompound data;
        
        private Attribute(final Builder builder) {
            this.data = new NBTTagCompound();
            this.setAmount(builder.amount);
            this.setOperation(builder.operation);
            this.setAttributeType(builder.type);
            this.setName(builder.name);
            this.setUUID(builder.uuid);
        }
        
        private Attribute(final NBTTagCompound data) {
            this.data = data;
        }
        
        public double getAmount() {
            return this.data.getDouble("Amount");
        }
        
        public void setAmount(final double amount) {
            this.data.setDouble("Amount", amount);
        }
        
        public Operation getOperation() {
            return Operation.fromId(this.data.getInt("Operation"));
        }
        
        public void setOperation(@Nonnull final Operation operation) {
            Preconditions.checkNotNull((Object)operation, (Object)"operation cannot be NULL.");
            this.data.setInt("Operation", operation.getId());
        }
        
        public AttributeType getAttributeType() {
            return AttributeType.fromId(this.data.getString("AttributeName"));
        }
        
        public void setAttributeType(@Nonnull final AttributeType type) {
            Preconditions.checkNotNull((Object)type, (Object)"type cannot be NULL.");
            this.data.setString("AttributeName", type.getMinecraftId());
        }
        
        public String getName() {
            return this.data.getString("Name");
        }
        
        public void setName(@Nonnull final String name) {
            this.data.setString("Name", name);
        }
        
        public UUID getUUID() {
            return new UUID(this.data.getLong("UUIDMost"), this.data.getLong("UUIDLeast"));
        }
        
        public void setUUID(@Nonnull final UUID id) {
            Preconditions.checkNotNull((Object)"id", (Object)"id cannot be NULL.");
            this.data.setLong("UUIDLeast", id.getLeastSignificantBits());
            this.data.setLong("UUIDMost", id.getMostSignificantBits());
        }
        
        public static Builder newBuilder() {
            return new Builder().uuid(UUID.randomUUID()).operation(Operation.ADD_NUMBER);
        }
        
        public static class Builder
        {
            private double amount;
            private Operation operation;
            private AttributeType type;
            private String name;
            private UUID uuid;
            
            private Builder() {
                this.operation = Operation.ADD_NUMBER;
            }
            
            public Builder amount(final double amount) {
                this.amount = amount;
                return this;
            }
            
            public Builder operation(final Operation operation) {
                this.operation = operation;
                return this;
            }
            
            public Builder type(final AttributeType type) {
                this.type = type;
                return this;
            }
            
            public Builder name(final String name) {
                this.name = name;
                return this;
            }
            
            public Builder uuid(final UUID uuid) {
                this.uuid = uuid;
                return this;
            }
            
            public Attribute build() {
                return new Attribute(this);
            }
        }
    }
}
*/