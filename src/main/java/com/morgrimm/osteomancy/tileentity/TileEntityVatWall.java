package com.morgrimm.osteomancy.tileentity;

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

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!worldObj.isRemote) {
            if (hasMaster()) {
                System.out.println("Has master!");
                if (isMaster()) {
                    // Multiblock function goes here!
                    System.out.println("MULTIBLOCK FORMED");
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
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        masterX = compound.getInteger("masterX");
        masterY = compound.getInteger("masterY");
        masterZ = compound.getInteger("masterZ");
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
        System.out.println("Checking multiblock...");
        int i = 0;
        for (int x = xCoord - 1; x < (xCoord + 2); x++) {
            for (int y = yCoord; y < (yCoord + 3); y++) {
                for (int z = zCoord; z < (zCoord + 2); z++) {
                    TileEntity tile = worldObj.getTileEntity(x, y, z);

                    if (tile != null && (tile instanceof TileEntityVatWall)) {
                        if (this.isMaster()) {
                            //  DEBUG System.out.println("This is the master block.");
                            if (((TileEntityVatWall) tile).hasMaster()) i++;
                        } else if (!((TileEntityVatWall) tile).hasMaster()) i++;
                    }
                }
            }
        }

        System.out.println("Vat walls: " + i);
        return (i > 24); //&& (worldObj.isAirBlock(xCoord, yCoord + 1, zCoord));
    }

    public void setupMultiblock() {
        for (int x = xCoord - 1; x < (xCoord + 2); x++) {
            for (int y = yCoord; y < (yCoord + 3); y++) {
                for (int z = zCoord; z < (zCoord + 2); z++) {
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
                for (int z = zCoord; z < (zCoord + 2); z++) {
                    TileEntity tile = worldObj.getTileEntity(x, y, z);

                    if (tile != null && (tile instanceof TileEntityVatWall)) {
                        ((TileEntityVatWall) tile).reset();
                    }
                }
            }
        }
    }
}
