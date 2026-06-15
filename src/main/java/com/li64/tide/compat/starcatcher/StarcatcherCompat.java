//? if neoforge {
/*package com.li64.tide.compat.starcatcher;

import com.li64.tide.Tide;
import com.li64.tide.data.rods.CustomRodManager;
import com.li64.tide.network.messages.StarcatcherStartMinigameMsg;
import com.li64.tide.registries.entities.misc.fishing.HookAccessor;
import com.li64.tide.util.BaitUtils;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.io.SCDataComponents;
import com.wdiscute.starcatcher.io.SingleStackContainer;
import com.wdiscute.starcatcher.registry.FishProperties;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.Optional;

public class StarcatcherCompat {
    public static boolean start(ServerPlayer player, HookAccessor hook, ItemStack rod, List<ItemStack> hookedItems) {
        if (hookedItems.isEmpty()) return false;

        // assign data components to rod item
        ItemStack fakeRod = rod.copy();
        fakeRod.set(SCDataComponents.BOBBER, SingleStackContainer.from(CustomRodManager.getBobber(rod)));
        fakeRod.set(SCDataComponents.HOOK, SingleStackContainer.from(CustomRodManager.getHook(rod)));
        fakeRod.set(SCDataComponents.BAIT, SingleStackContainer.from(BaitUtils.getFirstBaitItem(rod).orElse(new ItemStack(Items.AIR))));

        // try get minigame properties for current fish
        Optional<FishProperties> optional = player.level().registryAccess()
                .registryOrThrow(Starcatcher.FISH_REGISTRY_KEY)
                .getOptional(BuiltInRegistries.ITEM.getKey(hookedItems.get(0).getItem()));

        // unwrap it or use a fallback if it doesn't exist
        FishProperties properties = optional.orElseGet(() -> FishProperties.builder()
                .withFish(hookedItems.get(0).getItemHolder()).build());

        // start the minigame
        Tide.NETWORK.sendToPlayer(new StarcatcherStartMinigameMsg(properties, fakeRod), player);
        return true;
    }
}
*///?} else if forge {

//?}