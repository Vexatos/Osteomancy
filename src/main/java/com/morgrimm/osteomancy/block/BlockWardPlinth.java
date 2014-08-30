package com.morgrimm.osteomancy.block;

import com.morgrimm.osteomancy.item.ItemWard;
import com.morgrimm.osteomancy.reference.Names;
import com.morgrimm.osteomancy.reference.RenderIds;
import com.morgrimm.osteomancy.tileentity.TileEntityWardPlinth;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWardPlinth extends BlockOst implements ITileEntityProvider {

    public BlockWardPlinth(Material material) {
        super(material);
        this.setName(Names.Blocks.WARD_PLINTH);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {

        TileEntityWardPlinth tile = (TileEntityWardPlinth) world.getTileEntity(x, y,z);

        if (tile == null || player.isSneaking()) {
            return false;
        }

        ItemStack currentItem = player.getCurrentEquippedItem();

        if (tile.getStackInSlot(0) == null && currentItem != null && currentItem.getItem() instanceof ItemWard) {

            ItemStack newItem = currentItem.copy();
            newItem.stackSize = 1;
            --currentItem.stackSize;
            tile.setInventorySlotContents(0, newItem);

        } else if (tile.getStackInSlot(0) != null && currentItem == null) {

            player.inventory.addItemStackToInventory(tile.getStackInSlot(0));
            tile.setInventorySlotContents(0, null);

        }

        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityWardPlinth();
    }

    @Override
    public int getRenderType() {
        return RenderIds.wardPlinth;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }
}
