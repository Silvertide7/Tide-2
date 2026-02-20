package com.li64.tide.data.survey;

import com.li64.tide.Tide;
import com.li64.tide.network.messages.SurveyDataMsg;
import com.li64.tide.registries.TideItems;
import com.li64.tide.registries.items.SurveyingItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;

import java.util.*;

public class SurveyManager {
    private static final Map<UUID, Map<ResourceLocation, String>> CACHE = new HashMap<>();
    private static final Map<UUID, Map<ResourceLocation, Integer>> TIMERS = new HashMap<>();

    public static void tick(ServerPlayer player) {
        UUID id = player.getUUID();

        Map<ResourceLocation, String> results = new HashMap<>();
        CACHE.putIfAbsent(id, new HashMap<>());
        TIMERS.putIfAbsent(id, new HashMap<>());

        for (Item item : getActiveSurveyItems(player)) {
            if (!(item instanceof SurveyingItem surveyItem)) continue;
            ResourceLocation key = BuiltInRegistries.ITEM.getKey(item);

            int timer = TIMERS.get(id).getOrDefault(key, 0);
            if (timer <= 0) {
                String result = surveyItem.getSurveyResult(player.serverLevel(), player);
                results.put(key, result);
                CACHE.get(id).put(key, result);
                TIMERS.get(id).put(key, surveyItem.updatePeriod());
            }
            else {
                if (CACHE.get(id).containsKey(key)) results.put(key, CACHE.get(id).get(key));
                TIMERS.get(id).put(key, timer - 1);
            }
        }

        if (results.isEmpty()) return;
        Tide.NETWORK.sendToPlayer(new SurveyDataMsg(results), player);
    }

    private static List<Item> getActiveSurveyItems(ServerPlayer player) {
        return List.of(
                TideItems.POCKET_WATCH,
                TideItems.CLIMATE_GAUGE,
                TideItems.DEPTH_METER,
                TideItems.LUNAR_CALENDAR,
                TideItems.WEATHER_RADIO
        );
    }
}