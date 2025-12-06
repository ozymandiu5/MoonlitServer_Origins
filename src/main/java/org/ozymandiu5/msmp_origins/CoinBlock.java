package org.ozymandiu5.msmp_origins;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class CoinBlock extends Block {
	
	public CoinBlock(BlockBehaviour.Properties p_56585_) {
		super(p_56585_);
	}

	@Override
	public boolean canSurvive(BlockState p_56602_, LevelReader p_56603_, BlockPos p_56604_) {
		BlockState blockstate = p_56603_.getBlockState(p_56604_.below());
		return Block.isFaceFull(blockstate.getCollisionShape(p_56603_, p_56604_.below()), Direction.UP);
	}
}