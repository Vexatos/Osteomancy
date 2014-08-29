package com.morgrimm.osteomancy.network.packets;

import com.morgrimm.osteomancy.reference.Key;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketKeyPressed implements IMessage, IMessageHandler<PacketKeyPressed, IMessage>{

    private byte key;

    // Required empty constructor
    public PacketKeyPressed(){}

    public PacketKeyPressed(Key key) {
        // Set key byte based on key passed
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.key = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeByte(key);
    }

    @Override
    public IMessage onMessage(PacketKeyPressed message, MessageContext ctx) {
        // Do action based on key pressed
        return null;
    }
}
