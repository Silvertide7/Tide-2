//? if fabric {
package com.li64.tide.datagen.fabric.providers.assets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.li64.tide.Tide;
import com.li64.tide.client.TideItemModelProperties;
import com.li64.tide.registries.TideBlocks;
import com.li64.tide.registries.TideFish;
import com.li64.tide.registries.TideItems;
import com.li64.tide.registries.blocks.FishDisplayBlock;
import com.li64.tide.registries.blocks.FishDisplayShape;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;

import java.util.Map;
import java.util.Optional;

public class TideModelProvider extends FabricModelProvider {
    private static final ModelTemplate FISH_SWORD_TEMPLATE = new ModelTemplate(
            Optional.of(Tide.resource("item/fish_sword")),
            Optional.empty(), TextureSlot.LAYER0);

    public TideModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {
        generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(TideBlocks.FISH_DISPLAY)
                .with(PropertyDispatch.property(FishDisplayBlock.SHAPE).generate(shape ->
                        Variant.variant().with(VariantProperties.MODEL, shape == FishDisplayShape.SHAPE_1x1
                                ? Tide.resource("block/fish_display")
                                : Tide.resource("block/fish_display_" + shape.getSerializedName()))
                )).with(BlockModelGenerators.createHorizontalFacingDispatch()));
    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {
        generateBlockItem(generator, TideItems.ANGLING_TABLE);
        generateBlockItem(generator, TideItems.WOODEN_CRATE);
        generateBlockItem(generator, TideItems.OBSIDIAN_CRATE);
        generateBlockItem(generator, TideItems.PURPUR_CRATE);

        generateFishingRod(generator, TideItems.STONE_FISHING_ROD);
        generateFishingRod(generator, TideItems.IRON_FISHING_ROD);
        generateFishingRod(generator, TideItems.GOLDEN_FISHING_ROD);
        generateFishingRod(generator, TideItems.CRYSTAL_FISHING_ROD);
        generateFishingRod(generator, TideItems.DIAMOND_FISHING_ROD);
        generateFishingRod(generator, TideItems.NETHERITE_FISHING_ROD);
        generateFishingRod(generator, TideItems.MIDAS_FISHING_ROD);
        generateFishingRod(generator, TideItems.ECHO_FISHING_ROD);
        generateFishingRod(generator, TideItems.PRISMARINE_FISHING_ROD);
        generateFishingRod(generator, TideItems.SUNFLOWER_FISHING_ROD);
        generateFishingRod(generator, TideItems.VILLAGE_FISHING_ROD);
        generateFishingRod(generator, TideItems.AMMONITE_FISHING_ROD);
        generateFishingRod(generator, TideItems.BLAZING_FISHING_ROD);
        generateFishingRod(generator, TideItems.HONEYCOMB_FISHING_ROD);
        generateFishingRod(generator, TideItems.BREEZE_FISHING_ROD);

        generator.generateFlatItem(TideItems.BAIT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.LUCKY_BAIT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.MAGNETIC_BAIT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.INCANDESCENT_BAIT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.ABYSS_BAIT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.SHINY_BAIT, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(TideItems.FISHING_HOOK, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.FIERY_HOOK, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.PERMAFROST_HOOK, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.TWILIGHT_HOOK, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.LAVAPROOF_HOOK, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.VOID_HOOK, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(TideItems.FISHING_LINE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.BRAIDED_LINE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.REINFORCED_LINE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.GOLDEN_LINE, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(TideItems.FISHING_JOURNAL, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.FISHY_NOTE, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(TideItems.POCKET_WATCH, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.LUNAR_CALENDAR, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.CLIMATE_GAUGE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.DEPTH_METER, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.WEATHER_RADIO, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(TideItems.ENCHANTED_POCKET_WATCH, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.DRAGONFIN_BOOTS, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(TideItems.FISH_BONE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.OBSIDIAN_FRAGMENT, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(TideItems.JELLY_TORCH, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(TideItems.COOKED_FISH, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.SMALL_COOKED_FISH, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.LARGE_COOKED_FISH, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.GRILLED_TUNA, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(TideItems.FISH_SLICE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(TideItems.COOKED_FISH_SLICE, ModelTemplates.FLAT_ITEM);

        TideFish.ORDERED.forEach(item -> {
            if (item instanceof SwordItem) generator.generateFlatItem(item, FISH_SWORD_TEMPLATE);
            else generator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
        });

        TideFish.SPAWNING_ITEMS.forEach(item -> generator.generateFlatItem(item, ModelTemplates.FLAT_ITEM));
    }

    private void generateFishingRod(ItemModelGenerators generator, Item rod) {
        ModelTemplates.FLAT_HANDHELD_ROD_ITEM.create(
                ModelLocationUtils.getModelLocation(rod),
                TextureMapping.layer0(rod),
                generator.output,
                this::fishingRodWithOverrides
        );
        ModelTemplates.FLAT_HANDHELD_ROD_ITEM.create(
                ModelLocationUtils.getModelLocation(rod, "_cast"),
                TextureMapping.layer0(ModelLocationUtils.getModelLocation(rod, "_cast")),
                generator.output
        );
    }

    private JsonObject fishingRodWithOverrides(ResourceLocation modelLocation, Map<TextureSlot, ResourceLocation> modelGetter) {
        JsonObject base = ModelTemplates.FLAT_HANDHELD_ROD_ITEM.createBaseTemplate(modelLocation, modelGetter);
        JsonArray overrides = new JsonArray();
        JsonObject override = new JsonObject();
        JsonObject predicate = new JsonObject();
        predicate.addProperty(TideItemModelProperties.CAST_PROPERTY.toString(), 1);
        override.add("predicate", predicate);
        override.addProperty("model", modelLocation.withSuffix("_cast").toString());
        overrides.add(override);
        base.add("overrides", overrides);
        return base;
    }

    private void generateBlockItem(ItemModelGenerators generator, Item blockItem) {
        ResourceLocation path = BuiltInRegistries.ITEM.getKey(blockItem);
        generator.generateFlatItem(blockItem, new ModelTemplate(
                Optional.of(path.withPrefix("block/")),
                Optional.empty()
        ));
    }
}
//?}