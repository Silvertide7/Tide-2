package com.li64.tide.registries.items;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class DepthMeterItem extends AbstractSurveyingItem {
    public DepthMeterItem(Properties properties) {
        super(properties, Component.translatable("item.tide.depth_meter.desc"));
    }

    @Override
    public Component getDisplayedInfo(ServerLevel level, ServerPlayer player) {
        return Component.literal((level.getSeaLevel() - player.blockPosition().getY() + " m").replace("-", "+"));
    }
}
