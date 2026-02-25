//? if fabric {
package com.li64.tide.compat.accessories;

import com.li64.tide.data.TideTags;
import com.li64.tide.data.informational.FishingInfoManager;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import java.util.ArrayList;

public class TrinketsCompat {
    public static void addInformationalItems(Player player, ArrayList<Item> items) {
        var component = TrinketsApi.getTrinketComponent(player);
        if (component.isEmpty()) return;
        component.get().getEquipped(stack -> stack.is(TideTags.Items.INFORMATIONAL))
                .forEach(tuple -> {
                    Item item = tuple.getB().getItem();
                    FishingInfoManager.addInfoItem(items, item);
                });
    }
}
//?}