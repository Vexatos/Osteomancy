package com.morgrimm.osteomancy.tileentity;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityVatWall extends TileEntity {
    private boolean isMaster, hasMaster;
    private int masterX, masterY, masterZ;

    public boolean isMaster() {
        return isMaster;
    }

    public boolean hasMaster() {
        return hasMaster;
    }

    public void setHasMaster(boolean bool) {
        hasMaster = bool;
    }

    public void setIsMaster(boolean bool) {
        isMaster = bool;
    }

    public int getMasterX() {
        return masterX;
    }

    public int getMasterY() {
        return masterY;
    }

    public int getMasterZ() {
        return masterZ;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!worldObj.isRemote) {
            if (hasMaster()) {
                if (isMaster()) {
                    // Multiblock function goes here!
                }
            } else if (checkMultiblock()) {
                System.out.println("Multiblock detected!");
                setupMultiblock();
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        compound.setInteger("masterX", masterX);
        compound.setInteger("masterY", masterY);
        compound.setInteger("masterZ", masterZ);
        compound.setBoolean("hasMaster", hasMaster);
        compound.setBoolean("isMaster", isMaster);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        masterX = compound.getInteger("masterX");
        masterY = compound.getInteger("masterY");
        masterZ = compound.getInteger("masterZ");
        hasMaster = compound.getBoolean("hasMaster");
        isMaster = compound.getBoolean("isMaster");
    }

    public void reset() {
        masterX = 0;
        masterY = 0;
        masterZ = 0;
        isMaster = false;
        hasMaster = false;
    }

    public void setMasterCoords(int x, int y, int z) {
        masterX = x;
        masterY = y;
        masterZ = z;
    }

    public boolean checkForMaster() {
        TileEntity tile = worldObj.getTileEntity(masterX, masterY, masterZ);
        return (tile != null) && (tile instanceof TileEntityVatWall);
    }

    public boolean checkMultiblock() {
        int i = 0;
        for (int x = xCoord - 1; x < (xCoord + 2); x++) {
            for (int y = yCoord; y < (yCoord + 3); y++) {
                for (int z = zCoord - 1; z < (zCoord + 2); z++) {
                    TileEntity tile = worldObj.getTileEntity(x, y, z);

                    if (tile != null && (tile instanceof TileEntityVatWall)) {
                        if (this.isMaster()) {
                            if (((TileEntityVatWall) tile).hasMaster()) i++;
                        } else if (!((TileEntityVatWall) tile).hasMaster()) i++;
                    }
                }
            }
        }

        return (i > 24) && (worldObj.isAirBlock(xCoord, yCoord + 1, zCoord))&& (worldObj.isAirBlock(xCoord, yCoord + 2, zCoord));
    }

    public void setupMultiblock() {
        for (int x = xCoord - 1; x < (xCoord + 2); x++) {
            for (int y = yCoord; y < (yCoord + 3); y++) {
                for (int z = zCoord - 1; z < (zCoord + 2); z++) {
                    TileEntity tile = worldObj.getTileEntity(x, y, z);

                    boolean master = ((x == xCoord) && (y == yCoord) && (z == zCoord));

                    if (tile != null && (tile instanceof TileEntityVatWall)) {
                        ((TileEntityVatWall) tile).setMasterCoords(xCoord, yCoord, zCoord);
                        ((TileEntityVatWall) tile).setHasMaster(true);
                        ((TileEntityVatWall) tile).setIsMaster(master);
                    }
                }
            }
        }
    }

    public void resetMultiblock() {
        for (int x = xCoord - 1; x < (xCoord + 2); x++) {
            for (int y = yCoord; y < (yCoord + 3); y++) {
                for (int z = zCoord - 1; z < (zCoord + 2); z++) {
                    TileEntity tile = worldObj.getTileEntity(x, y, z);

                    if (tile != null && (tile instanceof TileEntityVatWall)) {
                        ((TileEntityVatWall) tile).reset();
                    }
                }
            }
        }
    }
}
