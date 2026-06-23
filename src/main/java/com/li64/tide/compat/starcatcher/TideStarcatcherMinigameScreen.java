//? if neoforge {
/*package com.li64.tide.compat.starcatcher;

import com.li64.tide.Tide;
import com.li64.tide.network.messages.MinigameServerMsg;
import com.li64.tide.network.messages.StarcatcherStartMinigameMsg;
import com.wdiscute.starcatcher.minigame.FishingMinigameScreen;
import com.wdiscute.starcatcher.registry.FishProperties;
import com.wdiscute.starcatcher.registry.minigamemodifiers.SCMinigameModifiers;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class TideStarcatcherMinigameScreen extends FishingMinigameScreen {
    public TideStarcatcherMinigameScreen(FishProperties properties, ItemStack rod) {
        super(properties, rod);
    }

    // client-only: builds + opens the delegated minigame screen (kept out of StarcatcherCompat so the server never loads a client class)
    public static void open(StarcatcherStartMinigameMsg message, Player player) {
        TideStarcatcherMinigameScreen screen = new TideStarcatcherMinigameScreen(message.properties(), message.rod());
        message.modifiers().forEach(key -> screen.addModifier(
                SCMinigameModifiers.getMinigameModifierSupplier(player.level(), key).get()));
        Minecraft.getInstance().setScreen(screen);
    }

    @Override
    public void onClose() {
        super.onClose();
        boolean wonMinigame = this.progressSmooth > 75;
        if (wonMinigame) Tide.NETWORK.sendToServer(new MinigameServerMsg((byte) (this.perfectCatch ? 3 : 2)));
        else Tide.NETWORK.sendToServer(new MinigameServerMsg((byte) 1));
    }
}
*///?}