package com.li64.tide.network.messages;

import com.li64.tide.Tide;
import com.li64.tide.client.gui.overlays.SurveyResultsOverlay;
import com.li64.tide.registries.items.SurveyingItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public record SurveyDataMsg(Map<ResourceLocation, String> data) implements TidePacketPayload {
    public static final ResourceLocation ID = Tide.resource("survey_data");
    @Override public ResourceLocation id() { return ID; }

    public SurveyDataMsg(RegistryFriendlyByteBuf buf) {
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

    public static void encode(SurveyDataMsg message, RegistryFriendlyByteBuf buf) {
        buf.writeInt(message.data.size());
        message.data.forEach((item, data) -> {
            buf.writeResourceLocation(item);
            buf.writeUtf(data);
        });
    }

    public static void handle(SurveyDataMsg message, Player player) {
        ArrayList<Component> results = new ArrayList<>();
        message.data().forEach((item, data) -> {
            SurveyingItem surveyItem = (SurveyingItem) BuiltInRegistries.ITEM.get(item);
            results.add(surveyItem.parseSurveyResult(data));
        });
        SurveyResultsOverlay.CLIENT_SURVEY_DATA = results;
    }
}