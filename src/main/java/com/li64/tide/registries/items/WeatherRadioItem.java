package com.li64.tide.registries.items;

import com.li64.tide.data.fishing.conditions.types.WeatherType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.level.storage.ServerLevelData;

public class WeatherRadioItem extends AbstractSurveyingItem {
    public WeatherRadioItem(Properties properties) {
        super(properties, Component.translatable("item.tide.weather_radio.desc"));
    }

    @Override
    public Component getDisplayedInfo(ServerLevel level, ServerPlayer player) {
        ServerLevelData data = level.serverLevelData;
        int clearTime = data.getClearWeatherTime();
        int rainTime = data.getRainTime();
        int thunderTime = data.getThunderTime();
        boolean isRaining = data.isRaining();
        boolean isThundering = data.isThundering();
        WeatherType next;
        int nextTicks;
        if (clearTime > 0) {
            next = WeatherType.STORM;
            nextTicks = clearTime;
        }
        else if ((thunderTime < rainTime && isRaining)
                || (thunderTime == rainTime && !isRaining)) {
            next = isThundering ? WeatherType.RAIN : WeatherType.STORM;
            nextTicks = thunderTime;
        }
        else {
            next = isRaining ? WeatherType.CLEAR : WeatherType.RAIN;
            nextTicks = rainTime;
        }
        int seconds = Mth.positiveCeilDiv(nextTicks, 20);
        int minutes = Mth.positiveCeilDiv(seconds, 60);
        int hours = Mth.positiveCeilDiv(minutes, 60);
        Component weatherForecast = Component.translatable("journal.info.weather." + next.getSerializedName());
        Component timeForecast = Component.translatable("item.tide.weather_radio.forecast.hours", hours);
        if (minutes <= 1) timeForecast = Component.translatable("item.tide.weather_radio.forecast.seconds", seconds);
        else if (hours <= 1) timeForecast = Component.translatable("item.tide.weather_radio.forecast.minutes", minutes);
        return Component.translatable("item.tide.weather_radio.forecast", weatherForecast, timeForecast);
    }
}
