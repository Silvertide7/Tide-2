package com.li64.tide.registries.items;

import com.li64.tide.registries.TideBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class LunarCalendarItem extends BlockItem implements SurveyingItem, TooltipItem {
    public LunarCalendarItem(Properties properties) {
        super(TideBlocks.LUNAR_CALENDAR, properties);
    }

    @Override
    public void addTooltip(ItemStack stack, Consumer<Component> tooltip) {
        Style gray = Component.empty().getStyle().withColor(ChatFormatting.GRAY);
        tooltip.accept(Component.translatable("item.tide.lunar_calendar.desc").setStyle(gray));
    }

    @Override
    public String getSurveyResult(ServerLevel level, ServerPlayer player) {
        return Integer.toString(level.getMoonPhase());
    }

    @Override
    public Component parseSurveyResult(String result) {
        return Component.translatable("journal.info.moon_phase." + Integer.parseInt(result));
    }

    @Override
    public int updatePeriod() {
        return 20;
    }
}
