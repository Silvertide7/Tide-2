//? if fabric {
package com.li64.tide.datagen.fabric.providers.tags;

import com.li64.tide.Tide;
import com.li64.tide.data.TideTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class TideBiomeTagsProvider extends FabricTagProvider<Biome> {
    public TideBiomeTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Registries.BIOME, registries);
    }

    @Override
    public @NotNull String getName() {
        return "Biome Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateTagBuilder(TideTags.Biomes.WATER_BIOMES)
                .forceAddTag(BiomeTags.IS_RIVER)
                .forceAddTag(BiomeTags.IS_OCEAN);

        getOrCreateTagBuilder(TideTags.Biomes.HAS_FISHING_BOAT)
                .forceAddTag(BiomeTags.IS_OCEAN);

        getOrCreateTagBuilder(TideTags.Biomes.HAS_END_OASIS)
                .add(Biomes.END_MIDLANDS)
                .add(Biomes.END_HIGHLANDS);

        getOrCreateTagBuilder(TideTags.Biomes.CAN_CATCH_STARFISH)
                .forceAddTag(BiomeTags.IS_DEEP_OCEAN);

        getOrCreateTagBuilder(TideTags.Biomes.IS_SALTWATER)
                .forceAddTag(TideTags.Convention.IS_OCEAN)
                .forceAddTag(TideTags.Convention.IS_BEACH)
                .add(Biomes.STONY_SHORE)
                .add(Biomes.MUSHROOM_FIELDS);

        getOrCreateTagBuilder(TideTags.Biomes.HAS_CHERRY_GROVE_FISH)
                .add(Biomes.CHERRY_GROVE)
                .addOptional(Tide.resource("biomesoplenty", "snowblossom_grove"));

        getOrCreateTagBuilder(TideTags.Biomes.HAS_DEEP_DARK_FISH)
                .add(Biomes.DEEP_DARK);

        getOrCreateTagBuilder(TideTags.Biomes.HAS_PLAINS_FISH)
                .forceAddTag(TideTags.Convention.IS_PLAINS)
                .add(Biomes.PLAINS)
                .add(Biomes.SUNFLOWER_PLAINS);

        getOrCreateTagBuilder(TideTags.Biomes.HAS_DESERT_FISH)
                .forceAddTag(TideTags.Convention.IS_DESERT)
                .forceAddTag(TideTags.Convention.IS_BADLANDS)
                .addOptional(Tide.resource("biomesoplenty", "dune_beach"))
                .addOptional(Tide.resource("biomesoplenty", "lush_desert"));

        getOrCreateTagBuilder(TideTags.Biomes.HAS_COASTAL_FISH)
                .forceAddTag(BiomeTags.IS_BEACH)
                .add(Biomes.STONY_SHORE);

        getOrCreateTagBuilder(TideTags.Biomes.HAS_DRIPSTONE_FISH)
                .add(Biomes.DRIPSTONE_CAVES);

        getOrCreateTagBuilder(TideTags.Biomes.HAS_FROZEN_FISH)
                .forceAddTag(TideTags.Convention.IS_ICY)
                .forceAddTag(TideTags.Convention.IS_SNOWY)
                .add(Biomes.FROZEN_RIVER)
                .add(Biomes.FROZEN_OCEAN)
                .add(Biomes.SNOWY_BEACH)
                .add(Biomes.SNOWY_PLAINS)
                .add(Biomes.ICE_SPIKES)
                .addOptional(Tide.resource("biomesoplenty", "auroral_garden"))
                .addOptional(Tide.resource("biomesoplenty", "cold_desert"))
                .addOptional(Tide.resource("biomesoplenty", "tundra"))
                .addOptional(Tide.resource("biomesoplenty", "wintry_origin_valley"))
                .addOptional(Tide.resource("biomesoplenty", "snowy_coniferous_forest"))
                .addOptional(Tide.resource("biomesoplenty", "snowy_fir_clearing"))
                .addOptional(Tide.resource("biomesoplenty", "snowy_maple_woods"));

        getOrCreateTagBuilder(TideTags.Biomes.HAS_JUNGLE_FISH)
                .forceAddTag(TideTags.Convention.IS_JUNGLE)
                .addOptional(Tide.resource("biomesoplenty", "floodplain"))
                .addOptional(Tide.resource("biomesoplenty", "rainforest"))
                .addOptional(Tide.resource("biomesoplenty", "rocky_rainforest"))
                .addOptional(Tide.resource("biomesoplenty", "tropics"));

        getOrCreateTagBuilder(TideTags.Biomes.HAS_MOUNTAIN_FISH)
                .forceAddTag(TideTags.Convention.IS_MOUNTAIN)
                .forceAddTag(BiomeTags.IS_MOUNTAIN);

        getOrCreateTagBuilder(TideTags.Biomes.HAS_MUSHROOM_FISH)
                .forceAddTag(TideTags.Convention.IS_MUSHROOM)
                .addOptional(Tide.resource("biomesoplenty", "fungal_jungle"));

        getOrCreateTagBuilder(TideTags.Biomes.HAS_SWAMP_FISH)
                .forceAddTag(TideTags.Convention.IS_SWAMP)
                .addOptional(Tide.resource("hybrid_aquatic", "swamp"))
                .addOptional(Tide.resource("hybrid_aquatic", "mangroves"))
                .addOptional(Tide.resource("hybrid_aquatic", "marshes"))
                .addOptional(Tide.resource("biomesoplenty", "bayou"))
                .addOptional(Tide.resource("biomesoplenty", "bog"))
                .addOptional(Tide.resource("biomesoplenty", "marsh"));

        getOrCreateTagBuilder(TideTags.Biomes.THE_VOID)
                .add(Biomes.THE_VOID);
    }
}
//?}