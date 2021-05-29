package io.github.haykam821.probabilityprisms.block;

import io.github.haykam821.probabilityprisms.block.entity.ProbabilityPrismBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ProbabilityPrismBlock extends Block implements BlockEntityProvider {
	public static final BooleanProperty UNSTABLE = Properties.UNSTABLE;

	public ProbabilityPrismBlock(Block.Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(UNSTABLE, true));
	}

	public void activate(World world, BlockPos pos) {
		if (world.isClient()) return;

		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity instanceof ProbabilityPrismBlockEntity) {
			((ProbabilityPrismBlockEntity) blockEntity).activate();
		}
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		if (!oldState.isOf(state.getBlock()) && world.isReceivingRedstonePower(pos)) {
			this.activate(world, pos);
			world.breakBlock(pos, false);
		}
	}

	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
		if (world.isReceivingRedstonePower(pos)) {
			this.activate(world, pos);
			world.breakBlock(pos, false);
		}
	}

	@Override
	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!player.isCreative() && state.get(UNSTABLE)) {
			this.activate(world, pos);
		}
		super.onBreak(world, pos, state, player);
	}

	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		builder.add(UNSTABLE);
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new ProbabilityPrismBlockEntity(pos, state);
	}
}
