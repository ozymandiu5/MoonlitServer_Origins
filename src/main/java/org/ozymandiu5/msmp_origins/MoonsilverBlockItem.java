package org.ozymandiu5.msmp_origins;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class MoonsilverBlockItem extends BlockItem {

	int value;

	public MoonsilverBlockItem(Block block, Properties p_41383_, int value) {
		super(block, p_41383_);
		this.value = value;
	}

	@Override
	public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
		p_41423_.add(Component.translatable(MSMPOrigins.MOONSILVER.get().getDescriptionId() + ".count", "" + value * p_41421_.getCount()).withStyle(ChatFormatting.GRAY));
	}
}
