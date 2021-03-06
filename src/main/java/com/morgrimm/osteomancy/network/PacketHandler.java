package com.morgrimm.osteomancy.network;

import com.morgrimm.osteomancy.network.packets.PacketKeyPressed;
import com.morgrimm.osteomancy.network.packets.PacketTileEntityOst;
import com.morgrimm.osteomancy.network.packets.PacketTileEntityVatWall;
import com.morgrimm.osteomancy.reference.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.NETWORK_CHANNEL_NAME);

    public static void init() {
        // Register packets here
        INSTANCE.registerMessage(PacketKeyPressed.class, PacketKeyPressed.class, 0, Side.SERVER);
        INSTANCE.registerMessage(PacketTileEntityOst.class, PacketTileEntityOst.class, 1, Side.CLIENT);
        INSTANCE.registerMessage(PacketTileEntityVatWall.class, PacketTileEntityVatWall.class, 2, Side.CLIENT);
    }
}