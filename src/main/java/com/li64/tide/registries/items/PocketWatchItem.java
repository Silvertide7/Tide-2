package com.li64.tide.registries.items;

import com.li64.tide.Tide;
import com.li64.tide.util.TideUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class PocketWatchItem extends AbstractInformationalItem {
    public PocketWatchItem(Properties properties) {
        super(properties, Component.translatable("item.tide.pocket_watch.desc"));
    }

    @Override
    public String getResult(ServerLevel level, ServerPlayer player) {
        return Long.toString(TideUtils.getTimeOfDay(level));
    }

    @Override
    public Component parseResult(String result) {
        return Component.literal(TideUtils.ticksToRealTime(Long.parseLong(result), Tide.CLIENT_CONFIG.journal.useAmPm));
    }

    @Override
    public int updatePeriod() {
        return 5;
    }
}
