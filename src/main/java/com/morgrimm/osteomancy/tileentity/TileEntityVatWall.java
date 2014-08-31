package com.morgrimm.osteomancy.tileentity;

import com.morgrimm.osteomancy.init.ModBlocks;
import com.morgrimm.osteomancy.init.ModFluids;
import com.morgrimm.osteomancy.init.ModItems;
import com.morgrimm.osteomancy.item.ItemSolvent;
import com.morgrimm.osteomancy.network.PacketHandler;
import com.morgrimm.osteomancy.network.packets.PacketTileEntityVatWall;
import com.morgrimm.osteomancy.reference.Names;
import com.morgrimm.osteomancy.reference.Reference;
import com.morgrimm.osteomancy.utility.NBTHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Resource;
import java.util.List;

public class TileEntityVatWall extends TileEntityOst {
    private boolean isMaster, hasMaster;
    private int masterX, masterY, masterZ, solventCount;
    private EnumVatWallTypes type;

    @SideOnly(Side.CLIENT)
    private LoopingTileEntitySound sound;
    private final ResourceLocation soundResource;

    private static ResourceLocation getSoundFor(String sound) {
        return sound != null ? new ResourceLocation(sound) : null;
    }

    public TileEntityVatWall() {
        super();
        this.type = EnumVatWallTypes.vatWallNormal;
        soundResource = getSoundFor(Names.Sounds.VAT_BUBBLE);
    }

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

    public void setType(EnumVatWallTypes type) {
        this.type = type;
    }

    public void setSolventCount(int count) {
        this.solventCount = count;
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

    public EnumVatWallTypes getType() {
        return type;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!worldObj.isRemote) {
            if (hasMaster()) {
                if (isMaster()) {
                    // Multiblock function goes here!
                    checkForEntities();
                    setAcidBlocks();
                    updateSound();
                }
            } else if (checkMultiblock()) {
                setupMultiblock();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    private void updateSound() {
        if (hasMaster() || isMaster()) {
            if (solventCount > 0) {
                if (sound != null) {
                    sound.endPlaying();
                    sound = null;
                    System.out.println("Stopping sound!");
                } else {
                    sound = new LoopingTileEntitySound(soundResource, xCoord, yCoord, zCoord, 0.1f, 1.0f);
                    //FMLClientHandler.instance().getClient().getSoundHandler().playSound(sound);
                    System.out.println("Playing sound!");
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        masterX = compound.getInteger("masterX");
        masterY = compound.getInteger("masterY");
        masterZ = compound.getInteger("masterZ");
        hasMaster = compound.getBoolean("hasMaster");
        isMaster = compound.getBoolean("isMaster");
        type = EnumVatWallTypes.valueOf(compound.getString("type").trim());
        solventCount = compound.getInteger("solventCount");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("masterX", masterX);
        compound.setInteger("masterY", masterY);
        compound.setInteger("masterZ", masterZ);
        compound.setBoolean("hasMaster", hasMaster);
        compound.setBoolean("isMaster", isMaster);
        compound.setString("type", type.name());
        compound.setInteger("solventCount", solventCount);
    }

    @Override
    public Packet getDescriptionPacket() {
        return PacketHandler.INSTANCE.getPacketFrom(new PacketTileEntityVatWall(this));
    }

    public void reset() {
        masterX = 0;
        masterY = 0;
        masterZ = 0;
        isMaster = false;
        hasMaster = false;
        type = EnumVatWallTypes.vatWallNormal;
    }

    public void setMasterCoords(int x, int y, int z) {
        masterX = x;
        masterY = y;
        masterZ = z;
    }

    public boolean hasSameMaster(TileEntityVatWall tile) {
        return (tile.getMasterX() == masterX) && (tile.getMasterY() == masterY) && (tile.getMasterZ() == masterZ);
    }

    public boolean checkForMaster() {
        TileEntity tile = worldObj.getTileEntity(masterX, masterY, masterZ);
        return (tile instanceof TileEntityVatWall);
    }

    public boolean checkMultiblock() {
        int i = 0;
        for (int x = xCoord - 1; x < (xCoord + 2); x++) {
            for (int y = yCoord; y < (yCoord + 3); y++) {
                for (int z = zCoord - 1; z < (zCoord + 2); z++) {
                    TileEntity tile = worldObj.getTileEntity(x, y, z);
                    worldObj.markBlockForUpdate(x, y, z);

                    if (tile instanceof TileEntityVatWall) {
                        if (this.isMaster()) {
                            if (((TileEntityVatWall) tile).hasMaster()) i++;
                        } else if (!((TileEntityVatWall) tile).hasMaster()) i++;
                    }
                }
            }
        }
        return (i > 24) && isBlockAirOrAcid(xCoord, yCoord + 1, zCoord) && isBlockAirOrAcid(xCoord, yCoord + 2, zCoord);
    }

    public void setupMultiblock() {
/*        for (int x = xCoord - 1; x < (xCoord + 2); x++) {
            for (int y = yCoord; y < (yCoord + 3); y++) {
                for (int z = zCoord - 1; z < (zCoord + 2); z++) {
                }
            }
        }*/

        // TEST CODE TO ALLOW FOR COHESIVE MULTIBLOCK TEXTURES

        // Bottom layer
        setupBlock(xCoord - 1, yCoord, zCoord - 1, EnumVatWallTypes.vatWallBotLeftCorner1);
        setupBlock(xCoord - 1, yCoord, zCoord, EnumVatWallTypes.vatWallMiddle1);
        setupBlock(xCoord - 1, yCoord, zCoord + 1, EnumVatWallTypes.vatWallBotRightCorner1);
        setupBlock(xCoord, yCoord, zCoord - 1, EnumVatWallTypes.vatWallMiddle2);
        setupBlock(xCoord, yCoord, zCoord, EnumVatWallTypes.vatWallMaster);
        setupBlock(xCoord, yCoord, zCoord + 1, EnumVatWallTypes.vatWallMiddle3);
        setupBlock(xCoord + 1, yCoord, zCoord - 1, EnumVatWallTypes.vatWallBotLeftCorner2);
        setupBlock(xCoord + 1, yCoord, zCoord, EnumVatWallTypes.vatWallMiddle4);
        setupBlock(xCoord + 1, yCoord, zCoord + 1, EnumVatWallTypes.vatWallBotRightCorner2);

        // Middle layer
        setupBlock(xCoord - 1, yCoord + 1, zCoord - 1, EnumVatWallTypes.vatWallMiddleLeftCorner1);
        setupBlock(xCoord - 1, yCoord + 1, zCoord, EnumVatWallTypes.vatWallMiddle5);
        setupBlock(xCoord - 1, yCoord + 1, zCoord + 1, EnumVatWallTypes.vatWallMiddleRightCorner1);
        setupBlock(xCoord, yCoord + 1, zCoord - 1, EnumVatWallTypes.vatWallMiddle6);
        //setupBlock(xCoord, yCoord + 1, zCoord); HOLLOW BLOCK
        setupBlock(xCoord, yCoord + 1, zCoord + 1, EnumVatWallTypes.vatWallMiddle7);
        setupBlock(xCoord + 1, yCoord + 1, zCoord - 1, EnumVatWallTypes.vatWallMiddleLeftCorner2);
        setupBlock(xCoord + 1, yCoord + 1, zCoord, EnumVatWallTypes.vatWallMiddle8);
        setupBlock(xCoord + 1, yCoord + 1, zCoord + 1, EnumVatWallTypes.vatWallMiddleRightCorner2);

        // Top layer
        setupBlock(xCoord - 1, yCoord + 2, zCoord - 1, EnumVatWallTypes.vatWallTopLeftCorner1);
        setupBlock(xCoord - 1, yCoord + 2, zCoord, EnumVatWallTypes.vatWallMiddle9);
        setupBlock(xCoord - 1, yCoord + 2, zCoord + 1, EnumVatWallTypes.vatWallTopRightCorner1);
        setupBlock(xCoord, yCoord + 2, zCoord - 1, EnumVatWallTypes.vatWallMiddle10);
        //setupBlock(xCoord, yCoord + 2, zCoord); HOLLOW BLOCK
        setupBlock(xCoord, yCoord + 2, zCoord + 1, EnumVatWallTypes.vatWallMiddle11);
        setupBlock(xCoord + 1, yCoord + 2, zCoord - 1, EnumVatWallTypes.vatWallTopLeftCorner2);
        setupBlock(xCoord + 1, yCoord + 2, zCoord, EnumVatWallTypes.vatWallMiddle12);
        setupBlock(xCoord + 1, yCoord + 2, zCoord + 1, EnumVatWallTypes.vatWallTopRightCorner2);

    }

    private void setupBlock(int x, int y, int z, EnumVatWallTypes type) {
        TileEntity tile = worldObj.getTileEntity(x, y, z);

        boolean master = ((x == xCoord) && (y == yCoord) && (z == zCoord));

        if (tile instanceof TileEntityVatWall) {
            ((TileEntityVatWall) tile).setMasterCoords(xCoord, yCoord, zCoord);
            ((TileEntityVatWall) tile).setHasMaster(true);
            ((TileEntityVatWall) tile).setIsMaster(master);
            ((TileEntityVatWall) tile).setType(type);
            worldObj.markBlockForUpdate(x, y, z);
        }
    }

    public void resetMultiblock() {
        for (int x = xCoord - 1; x < (xCoord + 2); x++) {
            for (int y = yCoord; y < (yCoord + 3); y++) {
                for (int z = zCoord - 1; z < (zCoord + 2); z++) {
                    TileEntity tile = worldObj.getTileEntity(x, y, z);

                    if (tile instanceof TileEntityVatWall) {
                        ((TileEntityVatWall) tile).reset();
                        worldObj.markBlockForUpdate(x, y, z);
                    }
                }
            }
        }

        if (worldObj.getBlock(xCoord, yCoord + 1, zCoord) == ModFluids.Acid) {
            worldObj.setBlockToAir(xCoord, yCoord + 1, zCoord);
        }
        if (worldObj.getBlock(xCoord, yCoord + 2, zCoord) == ModFluids.Acid) {
            worldObj.setBlockToAir(xCoord, yCoord + 2, zCoord);
        }
    }

    public boolean isBlockAirOrAcid(int x, int y, int z) {
        return (worldObj.isAirBlock(x, y, z) || (worldObj.getBlock(x, y, z) == ModFluids.Acid));
    }

    public void setAcidBlocks() {
        if (solventCount >= 1) {
            if (worldObj.getBlock(xCoord, yCoord + 1, zCoord) != ModFluids.Acid) {
                worldObj.setBlock(xCoord, yCoord + 1, zCoord, ModFluids.Acid);
            }
        } else {
            if (!worldObj.isAirBlock(xCoord, yCoord + 1, zCoord)) {
                worldObj.setBlockToAir(xCoord, yCoord + 1, zCoord);
            }
        }

        if (solventCount >= 2) {
            if (worldObj.getBlock(xCoord, yCoord + 2, zCoord) != ModFluids.Acid) {
                worldObj.setBlock(xCoord, yCoord + 2, zCoord, ModFluids.Acid);
            }
        } else {
            if (!worldObj.isAirBlock(xCoord, yCoord + 2, zCoord)) {
                worldObj.setBlockToAir(xCoord, yCoord + 2, zCoord);
            }
        }
    }

    public void checkForEntities() {
        AxisAlignedBB area = AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord + 1, zCoord - 1, xCoord + 1, yCoord + 2, zCoord + 1);

        List<EntityLiving> livingTargets = worldObj.getEntitiesWithinAABB(EntityLiving.class, area);
        List<EntityItem> itemTargets = worldObj.getEntitiesWithinAABB(EntityItem.class, area);

        // Check for solvent thrown in and act accordingly
        for (EntityItem item : itemTargets) {
            if (item.getEntityItem().getItem() instanceof ItemSolvent) {
                solventCount += 1;
                item.setDead();
            }
        }

        // Check for living things to melt the flesh off of and act accordingly
        for (EntityLiving target : livingTargets) {
            if (solventCount >= 2) {
                damageEntity(target);
            }
        }
    }

    private void damageEntity(EntityLiving entity) {
            if (entity.getHealth() <= Reference.VAT_DAMAGE) {
                ItemStack bonePile = new ItemStack(ModItems.bonePile, 1);
                NBTHelper.setString(bonePile, "entityClassName", entity.getClass().getName());

                entity.setDead();
                solventCount -= 2;

                EntityItem bones = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, bonePile);
                bones.setVelocity(0, 0, 0);

                worldObj.spawnEntityInWorld(bones);

            } else {
                entity.attackEntityFrom(DamageSource.generic, Reference.VAT_DAMAGE);
            }
    }
}
