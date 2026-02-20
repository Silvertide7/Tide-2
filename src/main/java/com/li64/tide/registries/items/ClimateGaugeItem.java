package com.li64.tide.registries.items;

import com.li64.tide.Tide;
import com.li64.tide.util.TideUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class ClimateGaugeItem extends AbstractSurveyingItem {
    public ClimateGaugeItem(Properties properties) {
        super(properties,
                Component.translatable("item.tide.climate_gauge.desc_0"),
                Component.translatable("item.tide.climate_gauge.desc_1")
        );
    }

    @Override
    public String getSurveyResult(ServerLevel level, ServerPlayer player) {
        float temp = TideUtils.getTemperatureAt(player.blockPosition(), level);
        float degrees = Math.round(TideUtils.mcTempToRealTemp(temp) * 10) / 10.0f;
        return Float.toString(degrees);
    }

    @Override
    public Component parseSurveyResult(String result) {
        return Component.literal(Float.parseFloat(result) + (Tide.CONFIG.journal.useFahrenheit ? "°F" : "°C"));
    }

    @Override
    public int updatePeriod() {
        return 5;
    }
}
