package com.morgrimm.osteomancy.network.packets;

import com.morgrimm.osteomancy.tileentity.TileEntityOst;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class PacketTileEntityOst implements IMessage, IMessageHandler<PacketTileEntityOst, IMessage>{
    public int x, y, z;
    public byte orientation;

    public PacketTileEntityOst() {}

    public PacketTileEntityOst(TileEntityOst tile) {
        this.x = tile.xCoord;
        this.y = tile.yCoord;
        this.z = tile.zCoord;
        this.orientation = (byte) tile.getOrientation().ordinal();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.orientation = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(orientation);
    }

    @Override
    public IMessage onMessage(PacketTileEntityOst message, MessageContext ctx) {
        TileEntity tile = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if (tile instanceof TileEntityOst) {
            ((TileEntityOst) tile).setOrientation(message.orientation);
        }

        return null;
    }
}
