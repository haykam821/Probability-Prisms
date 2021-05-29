package io.github.haykam821.probabilityprisms.block.entity;

import io.github.haykam821.probabilityprisms.Main;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.server.function.CommandFunctionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.Tag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;

public class ProbabilityPrismBlockEntity extends BlockEntity {
	private static final Identifier DEFAULT_FUNCTION_TAG_ID = new Identifier(Main.MOD_ID, "break_probability_prism");
	private static final int PERMISSION_LEVEL = 2;
	private static final String SIMPLE_NAME = "[?]";
	private static final Text NAME = new LiteralText(SIMPLE_NAME);
	private static final String FUNCTION_TAG_ID_KEY = "FunctionTagId";

	private Identifier functionTagId;

	public ProbabilityPrismBlockEntity(BlockPos pos, BlockState state) {
		super(Main.PROBABILITY_PRISM_BLOCK_ENTITY_TYPE, pos, state);
	}

	private Identifier getFunctionTagIdOrDefault() {
		if (this.functionTagId == null) {
			return DEFAULT_FUNCTION_TAG_ID;
		}
		return this.functionTagId;
	}

	private ServerCommandSource getCommandSource() {
		return new ServerCommandSource(CommandOutput.DUMMY, Vec3d.ofCenter(this.pos), Vec2f.ZERO, (ServerWorld) this.getWorld(), PERMISSION_LEVEL, SIMPLE_NAME, NAME, this.getWorld().getServer(), null);
	}

	public void activate() {
		if (this.world.isClient()) return;
		CommandFunctionManager functionManager = this.getWorld().getServer().getCommandFunctionManager();

		Tag<CommandFunction> tag = functionManager.getTaggedFunctions(this.getFunctionTagIdOrDefault());
		if (tag.values().isEmpty()) return;

		CommandFunction function = tag.getRandom(this.getWorld().getRandom());
		functionManager.execute(function, this.getCommandSource());
	}

	// Serialization
	@Override
	public void readNbt(NbtCompound nbt) {
		this.functionTagId = Identifier.tryParse(nbt.getString(FUNCTION_TAG_ID_KEY));
	}

	@Override
	public NbtCompound writeNbt(NbtCompound nbt) {
		if (this.functionTagId != null) {
			nbt.putString(FUNCTION_TAG_ID_KEY, this.functionTagId.toString());
		}

		return nbt;
	}
}
