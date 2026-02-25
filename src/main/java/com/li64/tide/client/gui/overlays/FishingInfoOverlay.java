package com.li64.tide.client.gui.overlays;

import com.li64.tide.Tide;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class FishingInfoOverlay {
    public static Map<ResourceLocation, Component> CLIENT_SURVEY_DATA = Map.of();

    private static final int PADDING = 5;
    private static final int LINE_SPACING = 2;

    public static void render(GuiGraphics graphics, float dt) {
        Font font = Minecraft.getInstance().font;

        int i = 0;
        for (Map.Entry<ResourceLocation, Component> entry : CLIENT_SURVEY_DATA.entrySet()) {
            graphics.blit(
                    Tide.resource("textures/gui/info_icons/" + entry.getKey().getPath() + ".png"),
                    PADDING, PADDING + (font.lineHeight + LINE_SPACING) * i,
                    0f, 0f, 9, 9, 9, 9
            );
            graphics.drawString(
                    font, entry.getValue(),
                    PADDING + 12,
                    PADDING + (font.lineHeight + LINE_SPACING) * i + 1,
                    0xffffff
            );
            i++;
        }
    }
}
