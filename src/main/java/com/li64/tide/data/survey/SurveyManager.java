package com.li64.tide.data.survey;

import com.li64.tide.Tide;
import com.li64.tide.network.messages.SurveyDataMsg;
import com.li64.tide.registries.items.SurveyingItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;

import java.util.*;

public class SurveyManager {
    private static final Map<UUID, Map<ResourceLocation, String>> CACHE = new HashMap<>();
    private static final Map<UUID, Map<ResourceLocation, Integer>> TIMERS = new HashMap<>();

    private static boolean hasNewData = false;

    public static void tick(ServerPlayer player) {
        UUID id = player.getUUID();

        CACHE.putIfAbsent(id, new HashMap<>());
        TIMERS.putIfAbsent(id, new HashMap<>());

        Map<ResourceLocation, String> cache = CACHE.get(id);
        Map<ResourceLocation, Integer> timers = TIMERS.get(id);
        Map<ResourceLocation, String> results = new HashMap<>();

        hasNewData = false;

        // gather data from active items
        List<Item> surveyItems = getActiveSurveyItems(player);
        for (Item item : surveyItems) {
            if (!(item instanceof SurveyingItem surveyItem)) continue;
            ResourceLocation key = BuiltInRegistries.ITEM.getKey(item);

            int timer = timers.getOrDefault(key, 0);
            if (timer <= 0 || !cache.containsKey(key)) {
                hasNewData = true;
                String result = surveyItem.getSurveyResult(player.serverLevel(), player);
                results.put(key, result);
                cache.put(key, result);
                timers.put(key, surveyItem.updatePeriod());
            }
            else {
                if (cache.containsKey(key)) results.put(key, cache.get(key));
                timers.put(key, timer - 1);
            }
        }

        // remove stale entries
        Set.copyOf(cache.keySet()).forEach(key -> {
            Item item = BuiltInRegistries.ITEM.get(key);
            if (surveyItems.contains(item)) return;
            cache.remove(key);
            timers.remove(key);
            hasNewData = true;
        });

        // send new results to player
        if (!hasNewData) return;
        Tide.NETWORK.sendToPlayer(new SurveyDataMsg(results), player);
    }

    private static List<Item> getActiveSurveyItems(ServerPlayer player) {
        ArrayList<Item> items = new ArrayList<>();

        Item mainhand = player.getMainHandItem().getItem();
        Item offhand = player.getOffhandItem().getItem();

        if (mainhand instanceof SurveyingItem) items.add(mainhand);
        if (offhand instanceof SurveyingItem) items.add(offhand);

        return items;
//        return List.of(
//                TideItems.POCKET_WATCH,
//                TideItems.CLIMATE_GAUGE,
//                TideItems.DEPTH_METER,
//                TideItems.LUNAR_CALENDAR,
//                TideItems.WEATHER_RADIO
//        );
    }
}