package com.li64.tide.registries.items;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;

public class SwordfishItem extends SwordItem {
    public SwordfishItem(Tiers tier, int damageMod, float speedMod, Properties properties) {
        /*? if >=1.21 {*/super(tier, properties.attributes(SwordItem.createAttributes(tier, damageMod, speedMod)));
        /*?} else*//*super(tier, damageMod, speedMod, properties);*/
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        return false;
    }
}
