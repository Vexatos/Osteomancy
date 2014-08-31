package com.morgrimm.osteomancy.network.packets;

import com.morgrimm.osteomancy.tileentity.EnumVatWallTypes;
import com.morgrimm.osteomancy.tileentity.TileEntityVatWall;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class PacketTileEntityVatWall implements IMessage, IMessageHandler<PacketTileEntityVatWall, IMessage> {

    public byte orientation;
    public boolean isMaster, hasMaster;
    public int masterX, masterY, masterZ, x, y, z, solventCount;
    public EnumVatWallTypes type;

    public PacketTileEntityVatWall() {}

    public PacketTileEntityVatWall(TileEntityVatWall tile) {
        this.orientation = (byte) tile.getOrientationInt();
        this.x = tile.xCoord;
        this.y = tile.yCoord;
        this.z = tile.zCoord;
        this.isMaster = tile.isMaster();
        this.hasMaster = tile.hasMaster();
        this.masterX = tile.getMasterX();
        this.masterY = tile.getMasterY();
        this.masterZ = tile.getMasterZ();
        this.type = tile.getType();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.orientation = buf.readByte();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.isMaster = buf.readBoolean();
        this.hasMaster = buf.readBoolean();
        this.masterX = buf.readInt();
        this.masterY = buf.readInt();
        this.masterZ = buf.readInt();
        this.type = EnumVatWallTypes.valueOf(EnumVatWallTypes.class, ByteBufUtils.readUTF8String(buf).trim());
        this.solventCount = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeByte(orientation);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeBoolean(isMaster);
        buf.writeBoolean(hasMaster);
        buf.writeInt(masterX);
        buf.writeInt(masterY);
        buf.writeInt(masterZ);
        ByteBufUtils.writeUTF8String(buf, type.name());
        buf.writeInt(solventCount);
    }

    @Override
    public IMessage onMessage(PacketTileEntityVatWall message, MessageContext ctx) {

        TileEntity tile = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if (tile instanceof TileEntityVatWall) {
            ((TileEntityVatWall) tile).setOrientation(message.orientation);
            ((TileEntityVatWall) tile).setIsMaster(message.isMaster);
            ((TileEntityVatWall) tile).setHasMaster(message.hasMaster);
            ((TileEntityVatWall) tile).setMasterCoords(message.masterX, message.masterY, message.masterZ);
            ((TileEntityVatWall) tile).setType(message.type);
            ((TileEntityVatWall) tile).setSolventCount(message.solventCount);
        }

        FMLClientHandler.instance().getClient().theWorld.markBlockForUpdate(message.x, message.y, message.z);

        return null;
    }
}
