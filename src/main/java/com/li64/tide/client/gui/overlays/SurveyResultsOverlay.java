package com.li64.tide.client.gui.overlays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import java.util.List;

public class SurveyResultsOverlay {
    public static List<Component> CLIENT_SURVEY_DATA = List.of();

    private static final int PADDING = 4;

    public static void render(GuiGraphics graphics, float dt) {
        Font font = Minecraft.getInstance().font;

        int i = 0;
        for (Component result : CLIENT_SURVEY_DATA) {
            graphics.drawString(font, result,
                    PADDING,
                    PADDING + font.lineHeight * i,
                    0xffffff
            );
            i++;
        }
    }
}
