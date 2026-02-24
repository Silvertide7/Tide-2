//? if fabric {
package com.li64.tide.datagen.fabric.providers.fishing;

import com.li64.tide.data.TideLootTables;
import com.li64.tide.data.TideTags;
import com.li64.tide.data.fishing.FishingLootData;
import com.li64.tide.data.fishing.conditions.types.BiomeWhitelistCondition;
import com.li64.tide.data.fishing.conditions.types.BlockNearbyCondition;
import com.li64.tide.data.fishing.conditions.types.StructuresCondition;
import com.li64.tide.datagen.fabric.providers.SimpleDataOutput;
import com.li64.tide.datagen.fabric.providers.SimpleDataProvider;
import com.mojang.serialization.Codec;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class TideFishingLootDataProvider extends SimpleDataProvider<FishingLootData> {
    public TideFishingLootDataProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super("fishing/loot", output, registries);
    }

    @Override
    protected Codec<FishingLootData> dataCodec() {
        return FishingLootData.CODEC;
    }

    @Override
    protected void generate(SimpleDataOutput<FishingLootData> output) {
        FishingLootData.builder()
                .lootTable(BuiltInLootTables.FISHING_TREASURE)
                .selectionWeight(5)
                .selectionQuality(2)
                .openWater(true)
                .overworld()
                .water()
                .surface()
                .build("surface_treasure", output);

        FishingLootData.builder()
                .lootTable(TideLootTables.Fishing.TREASURE_UNDERGROUND)
                .selectionWeight(5)
                .selectionQuality(2)
                .overworld()
                .water()
                .below(41)
                .build("underground_treasure", output);

        FishingLootData.builder()
                .lootTable(TideLootTables.Fishing.TREASURE_NETHER)
                .selectionWeight(0.8)
                .selectionQuality(0.5)
                .nether()
                .build("nether_treasure", output);

        FishingLootData.builder()
                .lootTable(BuiltInLootTables.FISHING_JUNK)
                .selectionWeight(10)
                .selectionQuality(-2)
                .overworld()
                .water()
                .surface()
                .build("surface_junk", output);

        FishingLootData.builder()
                .lootTable(TideLootTables.Fishing.JUNK_UNDERGROUND)
                .selectionWeight(12)
                .selectionQuality(-2)
                .overworld()
                .water()
                .below(41)
                .build("underground_junk", output);

        FishingLootData.builder()
                .lootTable(TideLootTables.Fishing.JUNK_LAVA)
                .selectionWeight(30)
                .selectionQuality(-1)
                .lava()
                .build("lava_junk", output);

        //? if >=1.21 {
        FishingLootData.builder()
                .lootTable(TideLootTables.Fishing.TRIAL_CHAMBER_TREASURE)
                .selectionWeight(20)
                .selectionQuality(1)
                .condition(StructuresCondition.only(BuiltinStructures.TRIAL_CHAMBERS))
                .build("trial_chamber_treasure", output);
        //?}

        FishingLootData.builder()
                .lootTable(TideLootTables.Fishing.OCEAN_MONUMENT_TREASURE)
                .selectionWeight(20)
                .selectionQuality(1)
                .condition(StructuresCondition.only(BuiltinStructures.OCEAN_MONUMENT))
                .build("ocean_monument_treasure", output);

        FishingLootData.builder()
                .lootTable(TideLootTables.Fishing.DESERT_WELL_TREASURE)
                .selectionWeight(30)
                .selectionQuality(1)
                .condition(BlockNearbyCondition.inRadius(TideTags.Blocks.DESERT_WELL_LOOT, 3))
                .build("desert_well_treasure", output);

        FishingLootData.builder()
                .lootTable(TideLootTables.Fishing.SUNFLOWER_ROD)
                .selectionWeight(1.5)
                .selectionQuality(1)
                .condition(BiomeWhitelistCondition.fromTag(TideTags.Biomes.HAS_PLAINS_FISH))
                .build("sunflower_rod", output);
    }

    @Override
    public @NotNull String getName() {
        return "Fishing Loot Data";
    }
}
//?}