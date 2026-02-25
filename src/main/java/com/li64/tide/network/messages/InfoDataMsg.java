package com.li64.tide.network.messages;

import com.li64.tide.Tide;
import com.li64.tide.client.gui.overlays.FishingInfoOverlay;
import com.li64.tide.registries.items.InformationalItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public record InfoDataMsg(Map<ResourceLocation, String> data) implements TidePacketPayload {
    public static final ResourceLocation ID = Tide.resource("survey_data");
    @Override public ResourceLocation id() { return ID; }

    public InfoDataMsg(RegistryFriendlyByteBuf buf) {
        this(fromBuffer(buf));
    }

    private static Map<ResourceLocation, String> fromBuffer(RegistryFriendlyByteBuf buf) {
        int count = buf.readInt();
        Map<ResourceLocation, String> data = new HashMap<>(count);
        for (int i = 0; i < count; i++) {
            data.put(buf.readResourceLocation(), buf.readUtf());
        }
        return data;
    }

    public static void encode(InfoDataMsg message, RegistryFriendlyByteBuf buf) {
        buf.writeInt(message.data.size());
        message.data.forEach((item, data) -> {
            buf.writeResourceLocation(item);
            buf.writeUtf(data);
        });
    }

    public static void handle(InfoDataMsg message, Player player) {
        Map<ResourceLocation, Component> results = new HashMap<>();
        message.data().forEach((key, data) -> {
            Item item = BuiltInRegistries.ITEM.get(key);
            results.put(key, ((InformationalItem)item).parseResult(data));
        });
        FishingInfoOverlay.CLIENT_SURVEY_DATA = results;
    }
}