//? if neoforge {
/*package com.li64.tide.network.messages;

import com.li64.tide.Tide;
import com.li64.tide.client.TideClientHelper;
import com.wdiscute.starcatcher.registry.FishProperties;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record StarcatcherStartMinigameMsg(FishProperties properties, ItemStack rod, List<ResourceLocation> modifiers) implements TidePacketPayload {
    public static final ResourceLocation ID = Tide.resource("start_starcatcher_minigame");
    @Override public ResourceLocation id() { return ID; }

    public StarcatcherStartMinigameMsg(RegistryFriendlyByteBuf buf) {
        this(
            FishProperties.STREAM_CODEC.decode(buf),
            ItemStack.STREAM_CODEC.decode(buf),
            ByteBufCodecs.fromCodec(ResourceLocation.CODEC.listOf()).decode(buf)
        );
    }

    public static void encode(StarcatcherStartMinigameMsg message, RegistryFriendlyByteBuf buf) {
        FishProperties.STREAM_CODEC.encode(buf, message.properties);
        ItemStack.STREAM_CODEC.encode(buf, message.rod);
        ByteBufCodecs.fromCodec(ResourceLocation.CODEC.listOf()).encode(buf, message.modifiers);
    }

    public static void handle(StarcatcherStartMinigameMsg message, Player player) {
        TideClientHelper.startStarcatcherMinigame(message, player);
    }
}
*///?} else if forge {

//?}