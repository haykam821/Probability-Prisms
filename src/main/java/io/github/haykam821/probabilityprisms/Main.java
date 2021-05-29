package io.github.haykam821.probabilityprisms;

import io.github.haykam821.probabilityprisms.block.ProbabilityPrismBlock;
import io.github.haykam821.probabilityprisms.block.entity.ProbabilityPrismBlockEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {
	public static final String MOD_ID = "probabilityprisms";

	private static final Identifier PROBABILITY_PRISM_ID = new Identifier(MOD_ID, "probability_prism");
	public static final Block PROBABILITY_PRISM = new ProbabilityPrismBlock(FabricBlockSettings.copy(Blocks.GOLD_BLOCK));
	public static final Item PROBABILITY_PRISM_ITEM = new BlockItem(PROBABILITY_PRISM, new Item.Settings().group(ItemGroup.REDSTONE));

	public static final BlockEntityType<ProbabilityPrismBlockEntity> PROBABILITY_PRISM_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder
		.create(ProbabilityPrismBlockEntity::new, PROBABILITY_PRISM)
		.build();

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, PROBABILITY_PRISM_ID, PROBABILITY_PRISM);
		Registry.register(Registry.ITEM, PROBABILITY_PRISM_ID, PROBABILITY_PRISM_ITEM);

		Registry.register(Registry.BLOCK_ENTITY_TYPE, PROBABILITY_PRISM_ID, PROBABILITY_PRISM_BLOCK_ENTITY_TYPE);
	}
}
