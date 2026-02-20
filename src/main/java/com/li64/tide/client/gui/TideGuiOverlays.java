package com.li64.tide.client.gui;

import com.li64.tide.client.gui.overlays.CastBarOverlay;
import com.li64.tide.client.gui.overlays.CatchMinigameOverlay;
import com.li64.tide.client.gui.overlays.SonarOverlay;
import com.li64.tide.client.gui.overlays.SurveyResultsOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public class TideGuiOverlays {
    public static void render(GuiGraphics graphics) {
        /*? if >=1.21 {*/float dt = Minecraft.getInstance().getTimer().getRealtimeDeltaTicks();
        /*?} else*//*float dt = Minecraft.getInstance().getDeltaFrameTime();*/
        CatchMinigameOverlay.render(graphics, dt);
        CastBarOverlay.render(graphics, dt);
        SonarOverlay.render(graphics, dt);
        SurveyResultsOverlay.render(graphics, dt);
    }
}
