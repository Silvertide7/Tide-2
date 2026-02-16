package com.li64.tide.network;

import com.li64.tide.loaders.NetworkPlatform;
import com.li64.tide.network.messages.*;
//? if >=1.21 {
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import java.util.HashMap;
import java.util.Map;
//?}

public class TideMessages {
    /*? if >=1.21*/public static final Map<ResourceLocation, CustomPacketPayload.Type<? extends CustomPacketPayload>> ID_TO_TYPE = new HashMap<>();

    public static void init(NetworkPlatform network) {
        network.registerClientBoundPacket(OpenJournalMsg.class, OpenJournalMsg.ID, OpenJournalMsg::encode, OpenJournalMsg::new, OpenJournalMsg::handle);
        network.registerClientBoundPacket(ViewNoteMsg.class, ViewNoteMsg.ID, ViewNoteMsg::encode, ViewNoteMsg::new, ViewNoteMsg::handle);
        network.registerClientBoundPacket(MinigameClientMsg.class, MinigameClientMsg.ID, MinigameClientMsg::encode, MinigameClientMsg::new, MinigameClientMsg::handle);
        network.registerClientBoundPacket(EchoRodHookedMsg.class, EchoRodHookedMsg.ID, EchoRodHookedMsg::encode, EchoRodHookedMsg::new, EchoRodHookedMsg::handle);
        network.registerClientBoundPacket(SyncPlayerDataMsg.class, SyncPlayerDataMsg.ID, SyncPlayerDataMsg::encode, SyncPlayerDataMsg::new, SyncPlayerDataMsg::handle);
        network.registerClientBoundPacket(ShowToastMsg.class, ShowToastMsg.ID, ShowToastMsg::encode, ShowToastMsg::new, ShowToastMsg::handle);
        network.registerClientBoundPacket(SyncDataMsg.class, SyncDataMsg.ID, SyncDataMsg::encode, SyncDataMsg::new, SyncDataMsg::handle);

        network.registerServerBoundPacket(MinigameServerMsg.class, MinigameServerMsg.ID, MinigameServerMsg::encode, MinigameServerMsg::new, MinigameServerMsg::handle);
        network.registerServerBoundPacket(ReadProfileMsg.class, ReadProfileMsg.ID, ReadProfileMsg::encode, ReadProfileMsg::new, ReadProfileMsg::handle);
    }
}
