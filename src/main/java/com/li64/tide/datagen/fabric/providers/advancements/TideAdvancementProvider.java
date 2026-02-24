//? if fabric {
package com.li64.tide.datagen.fabric.providers.advancements;

import com.li64.tide.Tide;
import com.li64.tide.data.TideCriteriaTriggers;
import com.li64.tide.data.TideTags;
import com.li64.tide.registries.TideFish;
import com.li64.tide.registries.TideItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.critereon.FishingRodHookedTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;

//? if >= 1.21 {
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.CriteriaTriggers;
import java.util.Optional;
//?} else {
/*import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
*///?}

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class TideAdvancementProvider extends FabricAdvancementProvider {
    @SuppressWarnings("unused")
    public TideAdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output/*? if >=1.21 {*/, registryLookup/*?}*/);
    }

    @Override
    /*? if >=1.21 {*/public void generateAdvancement(HolderLookup.Provider registries, Consumer<AdvancementHolder> output) {
    /*?} else*//*public void generateAdvancement(Consumer<Advancement> output) {*/
        var TASK_FRAME = /*? if >=1.21 {*/AdvancementType.TASK;/*?} else*//*FrameType.TASK;*/
        var GOAL_FRAME = /*? if >=1.21 {*/AdvancementType.GOAL;/*?} else*//*FrameType.GOAL;*/
        var CHALLENGE_FRAME = /*? if >=1.21 {*/AdvancementType.CHALLENGE;/*?} else*//*FrameType.CHALLENGE;*/

        var root = Advancement.Builder.advancement()
                .display(
                        TideFish.ANGELFISH,
                        Component.translatable("advancements.tide.root.title"),
                        Component.translatable("advancements.tide.root.description"),
                        Tide.resource("textures/gui/advancements/backgrounds/prismarine.png"),
                        TASK_FRAME,
                        false,
                        false,
                        false
                )
                .addCriterion("get_fishing_rod", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(TideTags.Convention.FISHING_ROD_TOOLS).build()))
                .save(output, Tide.resource("root").toString());

        var bait = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        TideItems.BAIT,
                        Component.translatable("advancements.tide.get_bait.title"),
                        Component.translatable("advancements.tide.get_bait.description"),
                        null,
                        TASK_FRAME,
                        true,
                        true,
                        false
                )
                .addCriterion("bait_items", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(TideTags.Items.BAIT_ITEMS).build()))
                .save(output, Tide.resource("get_bait").toString());

        var lava = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        TideItems.LAVAPROOF_HOOK,
                        Component.translatable("advancements.tide.fish_in_lava.title"),
                        Component.translatable("advancements.tide.fish_in_lava.description"),
                        null,
                        TASK_FRAME,
                        true,
                        true,
                        false
                )
                .addCriterion("fish_in_lava", TideCriteriaTriggers.simpleInstance(TideCriteriaTriggers.FISHED_IN_LAVA))
                .save(output, Tide.resource("fish_in_lava").toString());

        Advancement.Builder.advancement()
                .parent(lava)
                .display(
                        TideItems.VOID_HOOK,
                        Component.translatable("advancements.tide.fish_in_void.title"),
                        Component.translatable("advancements.tide.fish_in_void.description"),
                        null,
                        TASK_FRAME,
                        true,
                        true,
                        false
                )
                .addCriterion("fish_in_void", TideCriteriaTriggers.simpleInstance(TideCriteriaTriggers.FISHED_IN_VOID))
                .save(output, Tide.resource("fish_in_void").toString());

        Advancement.Builder.advancement()
                .parent(root)
                .display(
                        TideItems.WOODEN_CRATE,
                        Component.translatable("advancements.tide.pull_crate.title"),
                        Component.translatable("advancements.tide.pull_crate.description"),
                        null,
                        TASK_FRAME,
                        true,
                        true,
                        false
                )
                .addCriterion("pull_crate", TideCriteriaTriggers.simpleInstance(TideCriteriaTriggers.PULLED_CRATE))
                .save(output, Tide.resource("pull_crate").toString());

        Advancement.Builder.advancement()
                .parent(root)
                .display(
                        TideItems.CRYSTAL_FISHING_ROD,
                        Component.translatable("advancements.tide.all_fishing_rods.title"),
                        Component.translatable("advancements.tide.all_fishing_rods.description"),
                        null,
                        GOAL_FRAME,
                        true,
                        true,
                        false
                )
                .addCriterion("wood", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FISHING_ROD))
                .addCriterion("stone", InventoryChangeTrigger.TriggerInstance.hasItems(TideItems.STONE_FISHING_ROD))
                .addCriterion("iron", InventoryChangeTrigger.TriggerInstance.hasItems(TideItems.IRON_FISHING_ROD))
                .addCriterion("golden", InventoryChangeTrigger.TriggerInstance.hasItems(TideItems.GOLDEN_FISHING_ROD))
                .addCriterion("crystal", InventoryChangeTrigger.TriggerInstance.hasItems(TideItems.CRYSTAL_FISHING_ROD))
                .addCriterion("diamond", InventoryChangeTrigger.TriggerInstance.hasItems(TideItems.DIAMOND_FISHING_ROD))
                .addCriterion("netherite", InventoryChangeTrigger.TriggerInstance.hasItems(TideItems.NETHERITE_FISHING_ROD))
                .addCriterion("midas", InventoryChangeTrigger.TriggerInstance.hasItems(TideItems.MIDAS_FISHING_ROD))
                .addCriterion("echo", InventoryChangeTrigger.TriggerInstance.hasItems(TideItems.ECHO_FISHING_ROD))
                .addCriterion("prismarine", InventoryChangeTrigger.TriggerInstance.hasItems(TideItems.PRISMARINE_FISHING_ROD))
                .addCriterion("sunflower", InventoryChangeTrigger.TriggerInstance.hasItems(TideItems.SUNFLOWER_FISHING_ROD))
                .addCriterion("village", InventoryChangeTrigger.TriggerInstance.hasItems(TideItems.VILLAGE_FISHING_ROD))
                .addCriterion("blazing", InventoryChangeTrigger.TriggerInstance.hasItems(TideItems.BLAZING_FISHING_ROD))
                .addCriterion("honeycomb", InventoryChangeTrigger.TriggerInstance.hasItems(TideItems.HONEYCOMB_FISHING_ROD))
                .addCriterion("breeze", InventoryChangeTrigger.TriggerInstance.hasItems(TideItems.BREEZE_FISHING_ROD))
                .save(output, Tide.resource("all_fishing_rods").toString());

        var legendary = Advancement.Builder.advancement()
                .parent(bait)
                .display(
                        TideFish.SHOOTING_STARFISH,
                        Component.translatable("advancements.tide.catch_legendary.title"),
                        Component.translatable("advancements.tide.catch_legendary.description"),
                        null,
                        CHALLENGE_FRAME,
                        true,
                        true,
                        false
                )
                //? if >=1.21 {
                .addCriterion("catch_legendary", CriteriaTriggers.FISHING_ROD_HOOKED.createCriterion(new FishingRodHookedTrigger.TriggerInstance(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.of(ItemPredicate.Builder.item().of(TideTags.Items.LEGENDARY_FISH).build())
                )))
                //?} else {
                /*.addCriterion("catch_legendary", new FishingRodHookedTrigger.TriggerInstance(
                        ContextAwarePredicate.ANY,
                        ItemPredicate.ANY,
                        ContextAwarePredicate.ANY,
                        ItemPredicate.Builder.item().of(TideTags.Items.LEGENDARY_FISH).build()
                ))
                *///?}
                .save(output, Tide.resource("catch_legendary").toString());

        Advancement.Builder.advancement()
                .parent(legendary)
                .display(
                        TideFish.MIDAS_FISH,
                        Component.translatable("advancements.tide.catch_midas_with_midas_rod.title"),
                        Component.translatable("advancements.tide.catch_midas_with_midas_rod.description"),
                        null,
                        CHALLENGE_FRAME,
                        true,
                        true,
                        true
                )
                //? if >=1.21 {
                .addCriterion("catch_midas_with_midas_rod", CriteriaTriggers.FISHING_ROD_HOOKED.createCriterion(new FishingRodHookedTrigger.TriggerInstance(
                        Optional.empty(),
                        Optional.of(ItemPredicate.Builder.item().of(TideItems.MIDAS_FISHING_ROD).build()),
                        Optional.empty(),
                        Optional.of(ItemPredicate.Builder.item().of(TideFish.MIDAS_FISH).build())
                )))
                //?} else {
                /*.addCriterion("catch_midas_with_midas_rod", new FishingRodHookedTrigger.TriggerInstance(
                        ContextAwarePredicate.ANY,
                        ItemPredicate.Builder.item().of(TideItems.MIDAS_FISHING_ROD).build(),
                        ContextAwarePredicate.ANY,
                        ItemPredicate.Builder.item().of(TideFish.MIDAS_FISH).build()
                ))
                *///?}
                .save(output, Tide.resource("catch_midas_with_midas_rod").toString());

        Advancement.Builder.advancement()
                .parent(legendary)
                .display(
                        TideFish.COELACANTH,
                        Component.translatable("advancements.tide.freeze_coelacanth.title"),
                        Component.translatable("advancements.tide.freeze_coelacanth.description"),
                        null,
                        CHALLENGE_FRAME,
                        true,
                        true,
                        true
                )
                .addCriterion("freeze_coelacanth", TideCriteriaTriggers.simpleInstance(TideCriteriaTriggers.FROZE_COELACANTH))
                .save(output, Tide.resource("freeze_coelacanth").toString());

        Advancement.Builder.advancement()
                .parent(legendary)
                .display(
                        TideFish.DEVILS_HOLE_PUPFISH,
                        Component.translatable("advancements.tide.release_pupfish.title"),
                        Component.translatable("advancements.tide.release_pupfish.description"),
                        null,
                        CHALLENGE_FRAME,
                        true,
                        true,
                        true
                )
                .addCriterion("release_pupfish", TideCriteriaTriggers.simpleInstance(TideCriteriaTriggers.RELEASED_PUPFISH))
                .save(output, Tide.resource("release_pupfish").toString());

        var page = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        TideItems.FISHY_NOTE,
                        Component.translatable("advancements.tide.journal_halfway_completed.title"),
                        Component.translatable("advancements.tide.journal_halfway_completed.description"),
                        null,
                        TASK_FRAME,
                        true,
                        true,
                        false
                )
                .addCriterion("journal_halfway_completed", TideCriteriaTriggers.simpleInstance(TideCriteriaTriggers.JOURNAL_HALFWAY_COMPLETE))
                .save(output, Tide.resource("journal_halfway_completed").toString());

        Advancement.Builder.advancement()
                .parent(page)
                .display(
                        TideItems.FISHING_JOURNAL,
                        Component.translatable("advancements.tide.journal_completed.title"),
                        Component.translatable("advancements.tide.journal_completed.description"),
                        null,
                        CHALLENGE_FRAME,
                        true,
                        true,
                        false
                )
                .addCriterion("journal_completed", TideCriteriaTriggers.simpleInstance(TideCriteriaTriggers.JOURNAL_COMPLETE))
                .save(output, Tide.resource("journal_completed").toString());
    }
}
//?}