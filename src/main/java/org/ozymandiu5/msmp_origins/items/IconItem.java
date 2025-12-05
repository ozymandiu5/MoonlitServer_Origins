package org.ozymandiu5.msmp_origins.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class IconItem extends Item {

    public IconItem(Properties properties) {
        super(properties);
    }

    public static int getType(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        try {
            return nbt.getInt("type");
        } catch (IllegalArgumentException err) {
            return 0;
        }
    }

    public static void setType(ItemStack stack, int type) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putInt("type", type);
    }
}
