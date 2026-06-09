//? if fabric {
package com.li64.tide.datagen.fabric.providers.tags;

import com.li64.tide.Tide;
import com.li64.tide.data.TideTags;
import com.li64.tide.registries.TideFish;
import com.li64.tide.registries.TideItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TideItemTagsProvider extends FabricTagProvider<Item> {
    public TideItemTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Registries.ITEM, registries);
    }

    @Override
    public @NotNull String getName() {
        return "Item Tags";
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        /* Mod specific tags */

        getOrCreateTagBuilder(TideTags.Items.FISHING_RODS)
                .add(Items.FISHING_ROD)
                .add(TideItems.STONE_FISHING_ROD)
                .add(TideItems.IRON_FISHING_ROD)
                .add(TideItems.GOLDEN_FISHING_ROD)
                .add(TideItems.CRYSTAL_FISHING_ROD)
                .add(TideItems.DIAMOND_FISHING_ROD)
                .add(TideItems.NETHERITE_FISHING_ROD)
                .add(TideItems.MIDAS_FISHING_ROD)
                .add(TideItems.ECHO_FISHING_ROD)
                .add(TideItems.PRISMARINE_FISHING_ROD)
                .add(TideItems.SUNFLOWER_FISHING_ROD)
                .add(TideItems.VILLAGE_FISHING_ROD)
                .add(TideItems.BLAZING_FISHING_ROD)
                .add(TideItems.HONEYCOMB_FISHING_ROD)
                .add(TideItems.BREEZE_FISHING_ROD);

        getOrCreateTagBuilder(TideTags.Items.LINES)
                .add(TideItems.FISHING_LINE)
                .add(TideItems.COPPER_LINE)
                .add(TideItems.IRON_LINE)
                .add(TideItems.GOLDEN_LINE)
                .add(TideItems.DIAMOND_LINE);

        getOrCreateTagBuilder(TideTags.Items.BOBBERS)
                .add(TideItems.RED_BOBBER)
                .add(TideItems.ORANGE_BOBBER)
                .add(TideItems.YELLOW_BOBBER)
                .add(TideItems.LIME_BOBBER)
                .add(TideItems.GREEN_BOBBER)
                .add(TideItems.LIGHT_BLUE_BOBBER)
                .add(TideItems.CYAN_BOBBER)
                .add(TideItems.BLUE_BOBBER)
                .add(TideItems.PURPLE_BOBBER)
                .add(TideItems.MAGENTA_BOBBER)
                .add(TideItems.PINK_BOBBER)
                .add(TideItems.WHITE_BOBBER)
                .add(TideItems.LIGHT_GRAY_BOBBER)
                .add(TideItems.GRAY_BOBBER)
                .add(TideItems.BLACK_BOBBER)
                .add(TideItems.BROWN_BOBBER)
                .add(TideItems.APPLE_BOBBER)
                .add(TideItems.GOLDEN_APPLE_BOBBER)
                .add(TideItems.ENCHANTED_GOLDEN_APPLE_BOBBER)
                .add(TideItems.IRON_BOBBER)
                .add(TideItems.GOLDEN_BOBBER)
                .add(TideItems.DIAMOND_BOBBER)
                .add(TideItems.NETHERITE_BOBBER)
                .add(TideItems.AMETHYST_BOBBER)
                .add(TideItems.ECHO_BOBBER)
                .add(TideItems.CHORUS_BOBBER)
                .add(TideItems.FEATHER_BOBBER)
                .add(TideItems.LICHEN_BOBBER)
                .add(TideItems.NAUTILUS_BOBBER)
                .add(TideItems.PEARL_BOBBER)
                .add(TideItems.HEART_BOBBER)
                .add(TideItems.GRASSY_BOBBER)
                .add(TideItems.DUCK_BOBBER);

        getOrCreateTagBuilder(TideTags.Items.HOOKS)
                .add(TideItems.FISHING_HOOK)
                .add(TideItems.FIERY_HOOK)
                .add(TideItems.PERMAFROST_HOOK)
                .add(TideItems.TWILIGHT_HOOK)
                .add(TideItems.LAVAPROOF_HOOK)
                .add(TideItems.VOID_HOOK)
                .addOptional(Tide.resource("hybrid_aquatic", "barbed_hook"))
                .addOptional(Tide.resource("hybrid_aquatic", "glowing_hook"))
                .addOptional(Tide.resource("hybrid_aquatic", "magnetic_hook"))
                .addOptional(Tide.resource("hybrid_aquatic", "creepermagnet_hook"))
                .addOptional(Tide.resource("hybrid_aquatic", "ominous_hook"));

        getOrCreateTagBuilder(TideTags.Items.BAIT_ITEMS)
                .add(TideItems.BAIT)
                .add(TideItems.LUCKY_BAIT)
                .add(TideItems.MAGNETIC_BAIT)
                .add(TideItems.INCANDESCENT_BAIT)
                .add(TideItems.ABYSS_BAIT)
                .add(TideItems.SHINY_BAIT);

        getOrCreateTagBuilder(TideTags.Items.LUCK_BOOSTING_RODS)
                .add(TideItems.GOLDEN_FISHING_ROD)
                .add(TideItems.MIDAS_FISHING_ROD);

        getOrCreateTagBuilder(TideTags.Items.LAVA_FISHING_RODS)
                .add(TideItems.NETHERITE_FISHING_ROD)
                .add(TideItems.BLAZING_FISHING_ROD);

        getOrCreateTagBuilder(TideTags.Items.LAVA_BUCKETS)
                .forceAddTag(TideTags.Convention.LAVA_BUCKETS)
                .add(Items.LAVA_BUCKET);

        getOrCreateTagBuilder(TideTags.Items.BAIT_PLANTS)
                .forceAddTag(TideTags.Convention.CROPS)
                .forceAddTag(TideTags.Convention.MUSHROOMS)
                .forceAddTag(ItemTags.SMALL_FLOWERS);

        getOrCreateTagBuilder(TideTags.Items.VANILLA_FISH)
                .add(Items.COD)
                .add(Items.SALMON)
                .add(Items.TROPICAL_FISH)
                .add(Items.PUFFERFISH);

        getOrCreateTagBuilder(TideTags.Items.LEGENDARY_FISH)
                .add(TideFish.MAGIC_CARP)
                .add(TideFish.SHOOTING_STARFISH)
                .add(TideFish.COELACANTH)
                .add(TideFish.DEVILS_HOLE_PUPFISH)
                .add(TideFish.MIDAS_FISH)
                .add(TideFish.ALPHA_FISH)
                .add(TideFish.VOIDSEEKER)
                .add(TideFish.DRAGON_FISH);

        getOrCreateTagBuilder(TideTags.Items.CAT_FOOD).forceAddTag(TideTags.Items.COOKABLE_FISH)
                .add(Items.SALMON).add(Items.COD);

        FabricTagBuilder fishBuilder = getOrCreateTagBuilder(TideTags.Items.FISH)
                .forceAddTag(TideTags.Items.VANILLA_FISH);
        TideFish.FISH_KEYS.forEach(fishBuilder::add);

        FabricTagBuilder cookableFishBuilder = getOrCreateTagBuilder(TideTags.Items.COOKABLE_FISH);
        TideFish.COOKABLE_FISH_MAP.values().stream()
                .flatMap(List::stream)
                .sorted(Comparator.comparing(ResourceKey::toString))
                .forEach(cookableFishBuilder::add);

        FabricTagBuilder cookedFishBuilder = getOrCreateTagBuilder(TideTags.Items.COOKED_FISH)
                .add(Items.COOKED_COD).add(Items.COOKED_SALMON);
        TideFish.COOKABLE_FISH_MAP.entrySet().stream()
                .sorted(Comparator.comparing(entry -> entry.getKey().toString()))
                .forEach(entry -> {
                    Item cookedItem = entry.getKey();
                    FabricTagBuilder cookableTag = getOrCreateTagBuilder(TideTags.Cookables.getCookableTag(cookedItem));
                    entry.getValue().forEach(cookableTag::add);
                    cookedFishBuilder.add(cookedItem);
                });

        getOrCreateTagBuilder(TideTags.Items.CRATES)
                .add(TideItems.WOODEN_CRATE)
                .add(TideItems.OBSIDIAN_CRATE)
                .add(TideItems.PURPUR_CRATE);

        getOrCreateTagBuilder(TideTags.Items.INFORMATIONAL)
                .add(TideItems.POCKET_WATCH)
                .add(TideItems.LUNAR_CALENDAR)
                .add(TideItems.DEPTH_METER)
                .add(TideItems.CLIMATE_GAUGE)
                .add(TideItems.WEATHER_RADIO)
                .add(TideItems.FISH_FINDER);

        getOrCreateTagBuilder(TideTags.Items.TRINKETS_INFORMATIONAL)
                .forceAddTag(TideTags.Items.INFORMATIONAL);

        getOrCreateTagBuilder(TideTags.Items.CURIOS_INFORMATIONAL)
                .forceAddTag(TideTags.Items.INFORMATIONAL);

        /* Common tags */

        getOrCreateTagBuilder(TagKey.create(Registries.ITEM, Tide.resource("forge", "tools/fishing_rods")))
                .forceAddTag(TideTags.Items.FISHING_RODS);

        getOrCreateTagBuilder(ItemTags.FISHES).forceAddTag(TideTags.Items.FISH);

        getOrCreateTagBuilder(TideTags.Convention.RAW_FISH_FOODS)
                .addTag(TideTags.Items.COOKABLE_FISH)
                .add(TideItems.FISH_SLICE);

        getOrCreateTagBuilder(TideTags.Convention.COOKED_FISH_FOODS)
                .addTag(TideTags.Items.COOKED_FISH)
                .add(TideItems.COOKED_FISH_SLICE);

        getOrCreateTagBuilder(TideTags.Convention.BOW_TOOLS).add(TideItems.STARLIGHT_BOW);

        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(TideFish.SAILFISH)
                .add(TideFish.SWORDFISH)
                .add(TideFish.BLAZING_SWORDFISH);

        getOrCreateTagBuilder(ItemTags.LECTERN_BOOKS).add(TideItems.FISHING_JOURNAL);

        //? if >=1.21 {
        getOrCreateTagBuilder(ItemTags.CAT_FOOD).forceAddTag(TideTags.Items.COOKABLE_FISH);

        getOrCreateTagBuilder(ItemTags.FISHING_ENCHANTABLE).forceAddTag(TideTags.Items.FISHING_RODS);
        getOrCreateTagBuilder(ItemTags.BOW_ENCHANTABLE).add(TideItems.STARLIGHT_BOW);
        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .forceAddTag(TideTags.Items.FISHING_RODS)
                .add(TideItems.STARLIGHT_BOW);
        getOrCreateTagBuilder(ItemTags.SWORD_ENCHANTABLE)
                .add(TideFish.SAILFISH)
                .add(TideFish.SWORDFISH)
                .add(TideFish.BLAZING_SWORDFISH);

        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR).add(TideItems.DRAGONFIN_BOOTS);
        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR_ENCHANTABLE).add(TideItems.DRAGONFIN_BOOTS);
        //?}

        /* Compat tags */

        getOrCreateTagBuilder(TagKey.create(Registries.ITEM, Tide.resource("stardew_fishing", "starts_minigame")))
                .addTag(TideTags.Items.FISH);

        getOrCreateTagBuilder(TagKey.create(Registries.ITEM, Tide.resource("stardew_fishing", "legendary_fish")))
                .addTag(TideTags.Items.LEGENDARY_FISH);
    }
}
//?}