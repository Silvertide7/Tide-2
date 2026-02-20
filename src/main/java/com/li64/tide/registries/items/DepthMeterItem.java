package com.li64.tide.registries.items;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class DepthMeterItem extends AbstractSurveyingItem {
    public DepthMeterItem(Properties properties) {
        super(properties, Component.translatable("item.tide.depth_meter.desc"));
    }

    @Override
    public String getSurveyResult(ServerLevel level, ServerPlayer player) {
        return Integer.toString(level.getSeaLevel() - player.blockPosition().getY());
    }

    @Override
    public Component parseSurveyResult(String result) {
        return Component.literal((Integer.parseInt(result) + " m").replace("-", "+"));
    }

    @Override
    public int updatePeriod() {
        return 5;
    }
}
