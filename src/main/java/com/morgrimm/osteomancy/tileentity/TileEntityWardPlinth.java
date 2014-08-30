package com.morgrimm.osteomancy.tileentity;

import com.morgrimm.osteomancy.item.ItemWard;
import com.morgrimm.osteomancy.reference.Names;
import com.morgrimm.osteomancy.utility.NBTHelper;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

public class TileEntityWardPlinth extends TileEntityOst implements IInventory {

    public static final int INVENTORY_SIZE = 1;
    public ItemStack outputItemStack;

    private ItemStack[] inventory;

    public TileEntityWardPlinth() {
        this.inventory = new ItemStack[INVENTORY_SIZE];
    }

    @Override
    public void updateEntity() {
        ItemStack itemstack = getStackInSlot(0);

        if (itemstack != null) {
            int range = NBTHelper.getInt(itemstack, "range");
            int wardedID = NBTHelper.getInt(itemstack, "wardedEntityID");


            List<EntityLiving> targets = worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(xCoord - range, yCoord - range, zCoord - range, xCoord + range, yCoord + range, zCoord + range));

            for (EntityLiving entity : targets) {
                if (entity.getEntityId() == wardedID) {
                    System.out.println("Matched entity!");
                    entity.getNavigator().tryMoveToXYZ(xCoord, yCoord + 1, zCoord, entity.getAIMoveSpeed());
                }
            }
        }
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack itemstack = getStackInSlot(slot);

        if (itemstack != null) {
            if (itemstack.stackSize <= amount) {
                setInventorySlotContents(slot, null);
            } else {
                itemstack = itemstack.splitStack(amount);

                if (itemstack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return itemstack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack itemstack = getStackInSlot(slot);

        if (itemstack != null) {
            setInventorySlotContents(slot, null);
        }

        return itemstack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack) {
        inventory[slot] = itemstack;

        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
            itemstack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return Names.Containers.WARD_PLINTH;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
        return itemstack.getItem() instanceof ItemWard;
    }
}