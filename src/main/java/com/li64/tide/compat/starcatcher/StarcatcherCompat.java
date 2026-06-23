//? if neoforge {
/*package com.li64.tide.compat.starcatcher;

import com.li64.tide.Tide;
import com.li64.tide.data.rods.CustomRodManager;
import com.li64.tide.network.messages.StarcatcherStartMinigameMsg;
import com.li64.tide.registries.TideItems;
import com.li64.tide.registries.entities.misc.fishing.HookAccessor;
import com.li64.tide.registries.entities.misc.fishing.TideFishingHook;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.io.FishCaughtCounter;
import com.wdiscute.starcatcher.io.SCDataComponents;
import com.wdiscute.starcatcher.io.SingleStackContainer;
import com.wdiscute.starcatcher.registry.FishProperties;
import com.wdiscute.starcatcher.registry.minigamemodifiers.SCMinigameModifiers;
import com.wdiscute.starcatcher.tournament.TournamentHandler;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class StarcatcherCompat {
    private static final Set<ResourceLocation> WARNED_MISSING_FISH = new HashSet<>();

    public static boolean start(ServerPlayer player, HookAccessor hook, ItemStack rod, List<ItemStack> hookedItems) {
        if (hookedItems.isEmpty()) return false;

        // assign data components to rod item
        ItemStack fakeRod = rod.copy();
        fakeRod.set(SCDataComponents.BOBBER, SingleStackContainer.from(new ItemStack(Items.AIR)));
        fakeRod.set(SCDataComponents.HOOK, SingleStackContainer.from(new ItemStack(Items.AIR)));
        fakeRod.set(SCDataComponents.BAIT, SingleStackContainer.from(new ItemStack(Items.AIR)));

        // try get minigame properties for current fish
        Optional<FishProperties> optional = getFishProperties(player.level(), hookedItems.get(0));

        // unwrap it or use a fallback if it doesn't exist
        FishProperties properties = optional.orElseGet(() -> FishProperties.builder()
                .withFish(hookedItems.get(0).getItemHolder()).build());

        TideFishingHook tideHook = HookAccessor.getHook(player);
        if (tideHook != null) tideHook.setMinigameStartTime(System.currentTimeMillis());

        // start the minigame
        Tide.NETWORK.sendToPlayer(new StarcatcherStartMinigameMsg(properties, fakeRod, getMinigameModifiers(player, rod)), player);
        return true;
    }

    public static void completeCatch(ServerPlayer player, TideFishingHook hook, boolean perfectCatch) {
        List<ItemStack> hookedItems = hook.getHookedItems();
        if (hookedItems == null || hookedItems.isEmpty()) return;
        ItemStack caught = hookedItems.get(0);

        Optional<FishProperties> optional = getFishProperties(player.level(), caught);
        if (optional.isEmpty()) {
            ResourceLocation unknownFish = BuiltInRegistries.ITEM.getKey(caught.getItem());
            if (WARNED_MISSING_FISH.add(unknownFish)) {
                Tide.LOG.warn("Starcatcher has no fish properties for '{}'; keeping Tide's catch and skipping Starcatcher catch-completion (tournaments/golden/guide) for it", unknownFish);
            }
            return;
        }
        FishProperties properties = optional.get();

        RandomSource random = player.level().getRandom();
        float percentile = random.nextFloat() * 100f;
        int size = FishProperties.SizeAndWeight.getRandomSize(properties, percentile);
        int weight = FishProperties.SizeAndWeight.getRandomWeight(properties, percentile);
        boolean golden = random.nextFloat() < properties.sizeWeight().goldenChance()
                && FishCaughtCounter.canCatchGolden(properties, player);
        ResourceLocation fishId = BuiltInRegistries.ITEM.getKey(caught.getItem());

        long startTime = hook.getMinigameStartTime();
        int ticks = startTime > 0 ? (int) ((System.currentTimeMillis() - startTime) / 50L) : 0;

        FishCaughtCounter.awardFishCaughtCounter(properties, fishId, player, ticks, size, weight, percentile,
                perfectCatch, true, golden);
        TournamentHandler.addScore(player, properties, perfectCatch, size, weight, percentile);

        // item ownership: hand out Starcatcher's item (golden/size/weight) or keep Tide's (length)
        boolean useStarcatcherItem = switch (Tide.CONFIG.minigame.fishDataSource) {
            case STARCATCHER -> true;
            case TIDE -> false;
        };
        if (useStarcatcherItem) {
            ItemStack scItem = FishProperties.makeItemStack(hook.getRod(), properties, size, weight, percentile, golden, player, perfectCatch);
            scItem.setCount(caught.getCount());
            hook.replacePrimaryCatch(scItem);
        }
    }

    private static Optional<FishProperties> getFishProperties(Level level, ItemStack fish) {
        return level.registryAccess()
                .registryOrThrow(Starcatcher.FISH_REGISTRY_KEY)
                .getOptional(BuiltInRegistries.ITEM.getKey(fish.getItem()));
    }

    private static List<ResourceLocation> getMinigameModifiers(ServerPlayer player, ItemStack rod) {
        ItemStack line = CustomRodManager.getLine(rod);
        HashSet<ResourceLocation> modifiers = new HashSet<>();

        if (line.is(TideItems.IRON_LINE)) {
            modifiers.add(SCMinigameModifiers.BIGGER_GREEN_SWEET_SPOTS.getId());
        }
        if (line.is(TideItems.COPPER_LINE)) {
            modifiers.add(SCMinigameModifiers.SLOWER_MOVING_SWEET_SPOTS.getId());
            modifiers.add(SCMinigameModifiers.SLOWER_VANISHING.getId());
            modifiers.add(SCMinigameModifiers.SLIGHTLY_SLOWER_POINTER_SPEED.getId());
        }
        if (line.is(TideItems.GOLDEN_LINE)) {
            modifiers.add(SCMinigameModifiers.SLOWER_VANISHING.getId());
            modifiers.add(SCMinigameModifiers.SLOWER_MOVING_SWEET_SPOTS.getId());
            modifiers.add(SCMinigameModifiers.STOP_DECAY_ON_HIT.getId());
        }
        if (line.is(TideItems.DIAMOND_LINE)) {
            modifiers.add(SCMinigameModifiers.SLOWER_VANISHING.getId());
            modifiers.add(SCMinigameModifiers.BIGGER_GREEN_SWEET_SPOTS.getId());
            modifiers.add(SCMinigameModifiers.ADD_AQUA_SWEET_SPOT.getId());
            modifiers.add(SCMinigameModifiers.STOP_DECAY_ON_HIT.getId());
        }

        // bridge the player's equipped Starcatcher accessories (armor + curios) into the minigame
        SCMinigameModifiers.getMinigameModifiers(player).forEach(modifier ->
                modifiers.add(modifier.getRegistryHolderOrThrow().getId()));

        return modifiers.stream().toList();
    }
}
*///?} else if forge {

//?}