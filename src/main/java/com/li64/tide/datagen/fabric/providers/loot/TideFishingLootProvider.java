//? if fabric {
package com.li64.tide.datagen.fabric.providers.loot;

import com.li64.tide.data.TideLootTables;
import com.li64.tide.registries.TideItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

public class TideFishingLootProvider extends TideAbstractLootProvider {
    public TideFishingLootProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.FISHING);
    }

    @Override
    public void generateLoot(TideLootOutput output) {
        output.accept(
                TideLootTables.Fishing.TREASURE_UNDERGROUND,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.NAME_TAG))
                        .add(LootItem.lootTableItem(Items.SADDLE))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT))
                        .add(LootItem.lootTableItem(Items.GOLDEN_PICKAXE)
                                .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.1f, 0.35f)))
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(/*? if >=1.21 {*/this.registries, /*?}*/ConstantValue.exactly(30.0f)))
                        )
                        .add(LootItem.lootTableItem(Items.BOW)
                                .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.0f, 0.25f)))
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(/*? if >=1.21 {*/this.registries, /*?}*/ConstantValue.exactly(30.0f)))
                        )
                        .add(LootItem.lootTableItem(Items.FISHING_ROD)
                                .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.0f, 0.25f)))
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(/*? if >=1.21 {*/this.registries, /*?}*/ConstantValue.exactly(30.0f)))
                        )
                        .add(LootItem.lootTableItem(Items.BOOK)
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(/*? if >=1.21 {*/this.registries, /*?}*/ConstantValue.exactly(30.0f)))
                        )
                )
        );

        output.accept(
                TideLootTables.Fishing.JUNK_UNDERGROUND,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(10))
                        .add(LootItem.lootTableItem(Items.RAW_COPPER).setWeight(10))
                        .add(LootItem.lootTableItem(Items.WOODEN_PICKAXE).setWeight(10)
                                .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.1f, 0.9f))))
                        .add(LootItem.lootTableItem(Items.LEATHER_BOOTS).setWeight(10)
                                .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.1f, 0.9f))))
                        .add(LootItem.lootTableItem(Items.BONE).setWeight(10))
                        .add(LootItem.lootTableItem(Items.STRING).setWeight(5))
                        .add(LootItem.lootTableItem(Items.STICK).setWeight(5))
                        .add(LootItem.lootTableItem(Items.GLOW_INK_SAC).setWeight(1)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))))
                        .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(10))
                )
        );

        output.accept(
                TideLootTables.Fishing.TREASURE_NETHER,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.NETHERITE_SCRAP))
                        .add(LootItem.lootTableItem(Items.ANCIENT_DEBRIS))
                )
        );

        output.accept(
                TideLootTables.Fishing.JUNK_LAVA,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(TideItems.OBSIDIAN_FRAGMENT).setWeight(3))
                        .add(LootItem.lootTableItem(TideItems.FISH_BONE).setWeight(1))
                )
        );

        output.accept(
                TideLootTables.Fishing.OCEAN_MONUMENT_TREASURE,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.PRISMARINE_SHARD).setWeight(15))
                        .add(LootItem.lootTableItem(Items.PRISMARINE_CRYSTALS).setWeight(10))
                        .add(LootItem.lootTableItem(Items.WET_SPONGE).setWeight(3).setQuality(1))
                        .add(LootItem.lootTableItem(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(1).setQuality(1))
                )
        );

        //? if >=1.21 {
        output.accept(
                TideLootTables.Fishing.TRIAL_CHAMBER_TREASURE,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.COPPER_INGOT).setWeight(17))
                        .add(LootItem.lootTableItem(Items.WIND_CHARGE).setWeight(8))
                        .add(LootItem.lootTableItem(Items.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(1).setQuality(1))
                        .add(LootItem.lootTableItem(Items.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(1).setQuality(1))
                        .add(LootItem.lootTableItem(Items.HEAVY_CORE).setWeight(1).setQuality(1))
                )
        );
        //?}

        output.accept(
                TideLootTables.Fishing.DESERT_WELL_TREASURE,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(25))
                        .add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(20))
                        .add(LootItem.lootTableItem(Items.EMERALD).setWeight(4).setQuality(1))
                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(1).setQuality(1))
                )
        );

        output.accept(
                TideLootTables.Fishing.SUNFLOWER_ROD,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(TideItems.SUNFLOWER_FISHING_ROD)))
        );

        output.accept(
                TideLootTables.Fishing.BONUS_GOLD,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(140))
                        .add(LootItem.lootTableItem(Items.GOLDEN_CARROT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                                .setWeight(30))
                        .add(LootItem.lootTableItem(Items.GOLDEN_APPLE).setWeight(30))
                        .add(LootItem.lootTableItem(Items.GOLD_BLOCK).setWeight(30))
                        .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE).setWeight(1))
                        .add(LootItem.lootTableItem(Items.TOTEM_OF_UNDYING).setWeight(1))
                )
        );

        output.accept(
                TideLootTables.Fishing.BONUS_VILLAGE,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.EMERALD).setWeight(80))
                        .add(LootItem.lootTableItem(Items.STICK).setWeight(40))
                        .add(LootItem.lootTableItem(Items.APPLE).setWeight(40))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(40))
                        .add(LootItem.lootTableItem(Items.POPPY).setWeight(40))
                        .add(LootItem.lootTableItem(Items.BOOK).setWeight(40))
                        .add(LootItem.lootTableItem(Items.DEEPSLATE_EMERALD_ORE).setWeight(1))
                        .add(LootItem.lootTableItem(Items.EMERALD_BLOCK).setWeight(1))
                        .add(LootItem.lootTableItem(Items.TOTEM_OF_UNDYING).setWeight(1))
                )
        );
    }
}
//?}