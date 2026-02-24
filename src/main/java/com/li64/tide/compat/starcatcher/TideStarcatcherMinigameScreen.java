//? if neoforge || forge {
/*package com.li64.tide.compat.starcatcher;

import com.li64.tide.Tide;
import com.li64.tide.network.messages.MinigameServerMsg;
import com.wdiscute.starcatcher.minigame.FishingMinigameScreen;
import com.wdiscute.starcatcher.storage.FishProperties;
import net.minecraft.world.item.ItemStack;

public class TideStarcatcherMinigameScreen extends FishingMinigameScreen {
    public TideStarcatcherMinigameScreen(FishProperties properties, ItemStack rod) {
        super(properties, rod);
    }

    @Override
    public void onClose() {
        super.onClose();
        boolean wonMinigame = this.progressSmooth > 75;
        if (wonMinigame) Tide.NETWORK.sendToServer(new MinigameServerMsg((byte) 2));
        else Tide.NETWORK.sendToServer(new MinigameServerMsg((byte) 1));
    }
}
*///?}