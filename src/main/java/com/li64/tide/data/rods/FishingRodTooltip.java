package com.li64.tide.data.rods;

import net.minecraft.world.inventory.tooltip.TooltipComponent;

public record FishingRodTooltip(int slots, BaitContents contents) implements TooltipComponent {}