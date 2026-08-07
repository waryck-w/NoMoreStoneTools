package nmsp.modid.mixin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class WoodenPickaxeCopperMixin {
	@Inject(
			method = "isCorrectToolForDrops",
			at = @At("HEAD"),
			cancellable = true
	)
	private void nmsp$allowMiningCopperWithWood(
			ItemStack stack,
			BlockState state,
			CallbackInfoReturnable<Boolean> cir
	) {
		boolean isWoodenPickaxe = stack.is(Items.WOODEN_PICKAXE);
		boolean isCopperOre =
				state.is(Blocks.COPPER_ORE)
				|| state.is(Blocks.DEEPSLATE_COPPER_ORE);
		if (isWoodenPickaxe && isCopperOre) {
			cir.setReturnValue(true);
		}
	}
}