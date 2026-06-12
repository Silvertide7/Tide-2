package com.li64.tide.config;

import com.li64.tide.Tide;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = Tide.MOD_ID + "_client")
public final class TideClientConfig implements ConfigData {
    @Comment("These settings can also be changed via Cloth Config's config screen")
    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.TransitiveObject
    public General general;

    @ConfigEntry.Category("journal")
    @ConfigEntry.Gui.TransitiveObject
    public Journal journal;

    @ConfigEntry.Category("minigame")
    @ConfigEntry.Gui.TransitiveObject
    public Minigame minigame;

    public TideClientConfig() {
        this.general = new General();
        this.journal = new Journal();
        this.minigame = new Minigame();
    }

    public static class General {
        @ConfigEntry.Gui.Tooltip
        public boolean defaultLineColor = false;

        @ConfigEntry.Gui.Tooltip
        public boolean ambientVoidParticles = true;
    }

    public static class Journal {
        @ConfigEntry.Gui.Tooltip
        public boolean showUnread = true;

        @ConfigEntry.Gui.Tooltip
        public boolean useAmPm = true;

        @ConfigEntry.Gui.Tooltip
        public boolean useFahrenheit = false;

        @ConfigEntry.Gui.Tooltip
        public boolean useRealDate = true;
    }

    public static class Minigame {
        @ConfigEntry.Gui.Tooltip
        public boolean doFeedback = true;
    }
}