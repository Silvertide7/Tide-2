package com.li64.tide.data.rods;

//? if >=1.21 {
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BaitContents {
    public static final Codec<BaitContents> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("max_stacks").forGetter(contents -> contents.maxStacks),
            ItemStack.CODEC.listOf().fieldOf("items").forGetter(contents -> contents.items)
    ).apply(instance, BaitContents::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, BaitContents> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, contents -> contents.maxStacks,
            ItemStack.STREAM_CODEC.apply(ByteBufCodecs.list()), contents -> contents.items,
            BaitContents::new
    );

    private final List<ItemStack> items;
    private final int maxStacks;

    public BaitContents(int maxStacks) {
        this(maxStacks, List.of());
    }

    public BaitContents(int maxStacks, List<ItemStack> items) {
        this.maxStacks = maxStacks;
        this.items = items;
    }

    public List<ItemStack> items() {
        return items;
    }

    public ItemStack get(int index) {
        return items().get(index);
    }

    public int size() {
        return this.items.size();
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    @SuppressWarnings("deprecation")
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other instanceof BaitContents contents)
            return ItemStack.listMatches(this.items, contents.items);
        return false;
    }

    @SuppressWarnings("deprecation")
    public int hashCode() {
        return ItemStack.hashStackList(this.items);
    }

    public String toString() {
        return "BaitContents" + this.items;
    }

    public static class Mutable {
        private final List<ItemStack> items;
        private final int maxStacks;

        public Mutable(BaitContents contents) {
            if (contents == null) {
                this.items = new ArrayList<>();
                this.maxStacks = 0;
            }
            else {
                this.items = new ArrayList<>(contents.items);
                this.maxStacks = contents.maxStacks;
            }
        }

        private int findStackIndex(ItemStack stack) {
            for (int i = 0; i < this.items.size(); ++i) {
                if (ItemStack.isSameItemSameComponents(this.items.get(i), stack)) return i;
            }
            return -1;
        }

        // inserts stack to the contents
        public void tryInsert(ItemStack stack) {
            if (!stack.isEmpty() && stack.getItem().canFitInsideContainerItems()) {
                int index = this.findStackIndex(stack);
                int count = stack.getCount();

                if (index != -1 && stack.isStackable()) {
                    ItemStack current = this.items.get(index);

                    int stackSize = stack.getMaxStackSize();
                    int amountToAdd = Math.min(stackSize - current.getCount(), stack.getCount());

                    ItemStack added = current.copyWithCount(current.getCount() + amountToAdd);
                    stack.shrink(amountToAdd);

                    this.items.set(index, added);
                }
                else if (items.size() < this.maxStacks) this.items.add(stack.split(count));
            }
        }

        // transfer items from slot to contents
        public void tryTransfer(Slot slot, Player pPlayer) {
            ItemStack slotStack = slot.safeTake(slot.getItem().getCount(), slot.getMaxStackSize(), pPlayer);
            this.tryInsert(slotStack);
            if (!slotStack.isEmpty()) slot.safeInsert(slotStack);
        }

        @Nullable
        public ItemStack removeStack() {
            if (this.items.isEmpty()) return null;
            return this.items.removeFirst().copy();
        }

        public void shrinkStack(ItemStack stack) {
            int index = findStackIndex(stack);
            if (index == -1) return;
            this.items.get(index).shrink(1);
            if (this.items.get(index).isEmpty()) this.items.remove(index);
        }

        public BaitContents toImmutable() {
            return new BaitContents(this.maxStacks, List.copyOf(this.items));
        }

        public boolean isEmpty() {
            return this.items.isEmpty();
        }
    }
}
//?} else {
/*import com.google.common.collect.ImmutableList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BaitContents implements TooltipComponent {
    public static final int MAX_STACKS = 3;
    private final List<ItemStack> items;

    public BaitContents() {
        this(List.of());
    }

    public BaitContents(List<ItemStack> items) {
        this.items = items;
    }

    public List<ItemStack> items() {
        return items;
    }

    public ItemStack get(int index) {
        return items().get(index);
    }

    public int size() {
        return this.items.size();
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    public String toString() {
        return "BaitContents" + this.items;
    }

    public CompoundTag toNbt() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("size", size());
        for (int i = 0; i < size(); i++) tag.put("item-" + i, items.get(i).save(new CompoundTag()));
        return tag;
    }

    public static BaitContents fromNbt(CompoundTag tag) {
        if (tag == null || tag.isEmpty()) return new BaitContents();
        ImmutableList.Builder<ItemStack> itemsBuilder = ImmutableList.builder();
        int size = tag.getInt("size");
        for (int i = 0; i < size; i++) itemsBuilder.add(ItemStack.of(tag.getCompound("item-" + i)));
        return new BaitContents(itemsBuilder.build());
    }

    public static class Mutable {
        private final List<ItemStack> items;

        public Mutable(BaitContents contents) {
            if (contents == null) this.items = new ArrayList<>();
            else this.items = new ArrayList<>(contents.items);
        }

        private int findStackIndex(ItemStack stack) {
            for (int i = 0; i < this.items.size(); ++i) {
                if (ItemStack.isSameItemSameTags(this.items.get(i), stack)) {
                    return i;
                }
            }
            return -1;
        }

        // inserts stack to the contents
        public void tryInsert(ItemStack stack) {
            if (!stack.isEmpty() && stack.getItem().canFitInsideContainerItems()) {
                int index = this.findStackIndex(stack);
                int count = stack.getCount();

                if (index != -1 && stack.isStackable()) {
                    ItemStack current = this.items.get(index);

                    int stackSize = stack.getMaxStackSize();
                    int amountToAdd = Math.min(stackSize - current.getCount(), stack.getCount());

                    ItemStack added = current.copyWithCount(current.getCount() + amountToAdd);
                    stack.shrink(amountToAdd);

                    this.items.set(index, added);
                } else {
                    if (items.size() < MAX_STACKS) this.items.add(stack.split(count));
                }
            }
        }

        // transfer items from slot to contents
        public void tryTransfer(Slot slot, Player pPlayer) {
            ItemStack slotStack = slot.safeTake(slot.getItem().getCount(), slot.getMaxStackSize(), pPlayer);
            this.tryInsert(slotStack);
            if (!slotStack.isEmpty()) slot.safeInsert(slotStack);
        }

        @Nullable
        public ItemStack removeStack() {
            if (this.items.isEmpty()) {
                return null;
            } else {
                return this.items.remove(0).copy();
            }
        }

        public void shrinkStack(ItemStack stack) {
            int index = findStackIndex(stack);
            if (index == -1) return;
            this.items.get(index).shrink(1);
            if (this.items.get(index).isEmpty()) this.items.remove(index);
        }

        public BaitContents toImmutable() {
            return new BaitContents(List.copyOf(this.items));
        }

        public boolean isEmpty() {
            return this.items.isEmpty();
        }
    }
}
*///?}