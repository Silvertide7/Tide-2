package com.li64.tide.registries.items;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class LunarCalendarItem extends AbstractSurveyingItem {
    public LunarCalendarItem(Properties properties) {
        super(properties, Component.translatable("item.tide.lunar_calendar.desc"));
    }

    @Override
    public Component getDisplayedInfo(ServerLevel level, ServerPlayer player) {
        return Component.translatable("journal.info.moon_phase." + level.getMoonPhase());
    }
}
