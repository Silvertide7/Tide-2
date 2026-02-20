package com.li64.tide.registries.items;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public interface SurveyingItem {
    /** run on the server, gets the raw data from the server */
    String getSurveyResult(ServerLevel level, ServerPlayer player);

    /** run on the client, interprets and translates the data from the server */
    Component parseSurveyResult(String result);

    int updatePeriod();
}
