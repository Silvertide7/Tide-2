package com.li64.tide.data.item;

import com.li64.tide.data.ItemDataKey;
import com.li64.tide.data.rods.BaitContents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public final class TideItemData {
    //? if >=1.21 {
    public static final ItemDataKey<Double> FISH_LENGTH = new ComponentItemDataKey<>(TideDataComponents.FISH_LENGTH);
    public static final ItemDataKey<Boolean> IS_BUCKETABLE = new ComponentItemDataKey<>(TideDataComponents.IS_BUCKETABLE);
    public static final ItemDataKey<CompoundTag> FISHING_LINE = new ComponentItemDataKey<>(TideDataComponents.FISHING_LINE);
    public static final ItemDataKey<CompoundTag> FISHING_BOBBER = new ComponentItemDataKey<>(TideDataComponents.FISHING_BOBBER);
    public static final ItemDataKey<CompoundTag> FISHING_HOOK = new ComponentItemDataKey<>(TideDataComponents.FISHING_HOOK);
    public static final ItemDataKey<BaitContents> BAIT_CONTENTS = new ComponentItemDataKey<>(TideDataComponents.BAIT_CONTENTS);
    public static final ItemDataKey<ResourceKey<Item>> FISHY_NOTE_VARIANT = new ComponentItemDataKey<>(TideDataComponents.FISHY_NOTE_VARIANT);
    public static final ItemDataKey<SatchelContents> SATCHEL_CONTENTS = new ComponentItemDataKey<>(TideDataComponents.SATCHEL_CONTENTS);
    public static final ItemDataKey<Boolean> FISH_SATCHEL_OPENED = new ComponentItemDataKey<>(TideDataComponents.FISH_SATCHEL_OPENED);
    //?} else {
    /*public static final ItemDataKey<Double> FISH_LENGTH = new NbtItemDataKey<>(
            "FishLength", tag -> tag.getDouble("FishLength"),
            (tag, value) -> tag.putDouble("FishLength", value));
    public static final ItemDataKey<Long> CATCH_TIMESTAMP = new NbtItemDataKey<>(
            "CatchTimestamp", tag -> tag.getLong("CatchTimestamp"),
            (tag, value) -> tag.putLong("CatchTimestamp", value));
    public static final ItemDataKey<CompoundTag> FISHING_LINE = new NbtItemDataKey<>(
            "FishingLine", tag -> tag.getCompound("FishingLine"),
            (tag, value) -> tag.put("FishingLine", value));
    public static final ItemDataKey<CompoundTag> FISHING_BOBBER = new NbtItemDataKey<>(
            "FishingBobber", tag -> tag.getCompound("FishingBobber"),
            (tag, value) -> tag.put("FishingBobber", value));
    public static final ItemDataKey<CompoundTag> FISHING_HOOK = new NbtItemDataKey<>(
            "FishingHook", tag -> tag.getCompound("FishingHook"),
            (tag, value) -> tag.put("FishingHook", value));
    public static final ItemDataKey<BaitContents> BAIT_CONTENTS = new NbtItemDataKey<>(
            "BaitContents", tag -> {
                if (!tag.contains("BaitContents")) return null;
                return BaitContents.fromNbt(tag.getCompound("BaitContents"));
            }, (tag, value) -> tag.put("BaitContents", value.toNbt()));
    public static final ItemDataKey<ResourceKey<Item>> FISHY_NOTE_VARIANT = new NbtItemDataKey<>(
            "FishyNoteVariant", tag -> {
                if (!tag.contains("FishyNoteVariant")) return null;
                return ResourceKey.create(Registries.ITEM, ResourceLocation.tryParse(tag.getString("FishyNoteVariant")));
            },
            (tag, value) -> tag.putString("FishyNoteVariant", value.location().toString()));
    public static final ItemDataKey<SatchelContents> SATCHEL_CONTENTS = new NbtItemDataKey<>(
            "FishSatchelContents", SatchelContents::fromNbt,
            (tag, value) -> value.toNbt(tag));
    public static final ItemDataKey<Boolean> FISH_SATCHEL_OPENED = new NbtItemDataKey<>(
            "FishSatchelOpened", tag -> tag.getBoolean("FishSatchelOpened"),
            (tag, value) -> tag.putBoolean("FishSatchelOpened", value));
    *///?}
}
