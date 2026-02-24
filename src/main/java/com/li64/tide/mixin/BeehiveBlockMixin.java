package com.li64.tide.mixin;

import com.li64.tide.registries.TideItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BeehiveBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BeehiveBlock.class)
public class BeehiveBlockMixin {
    @Inject(method = "dropHoneycomb", at = @At("TAIL"))
    private static void dropHoneycomb(Level level, BlockPos pos, CallbackInfo ci) {
        if (new Random().nextFloat() < 0.05f) BeehiveBlock.popResource(
                level, pos, new ItemStack(TideItems.HONEYCOMB_FISHING_ROD));
    }
}
