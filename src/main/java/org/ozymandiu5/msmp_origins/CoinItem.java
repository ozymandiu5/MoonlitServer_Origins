package org.ozymandiu5.msmp_origins;

import java.util.Random;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

public class CoinItem extends MoonsilverItem {

	public CoinItem(Properties p_41383_, int value) {
		super(p_41383_, value);
	}

	@Override
	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
		if (entity.level().isClientSide || stack.getCount() != 1)
			return false;
		
		if (!entity.getTags().contains("coinflip_checked")) {
			Entity thrower = entity.getOwner();
			if (thrower != null && thrower.isShiftKeyDown()) {
				entity.addTag("coinflip");
			}
			entity.addTag("coinflip_checked");
		}
		
		if (entity.onGround() && entity.getTags().contains("coinflip")) {
			entity.setCustomName(Component.translatable("misc.msmp_origins." + (new Random().nextBoolean() ? "heads" : "tails")));
			entity.setCustomNameVisible(true);
			entity.removeTag("coinflip");
		}

		return false;
	}
}
	