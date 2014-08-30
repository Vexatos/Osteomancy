package com.morgrimm.osteomancy.tileentity;

import com.morgrimm.osteomancy.network.PacketHandler;
import com.morgrimm.osteomancy.network.packets.PacketTileEntityOst;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityOst extends TileEntity {

    protected ForgeDirection orientation;

    public TileEntityOst() {
        orientation = ForgeDirection.SOUTH;
    }

    public ForgeDirection getOrientation() {
        return orientation;
    }

    public int getOrientationInt() {
        return this.orientation.ordinal();
    }

    public void setOrientation(ForgeDirection orientation) {
        this.orientation = orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = ForgeDirection.getOrientation(orientation);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        orientation = ForgeDirection.getOrientation(compound.getByte("direction"));
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setByte("direction", (byte) orientation.ordinal());
    }

    @Override
    public Packet getDescriptionPacket() {
        return PacketHandler.INSTANCE.getPacketFrom(new PacketTileEntityOst(this));
    }
}
