package com.morgrimm.osteomancy.block;

import com.morgrimm.osteomancy.reference.Names;
import com.morgrimm.osteomancy.tileentity.TileEntityVatWall;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockVatWall extends BlockOst implements ITileEntityProvider{

    public BlockVatWall (Material material) {
        super(material);
        this.setName(Names.Blocks.VAT_WALL);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        TileEntityVatWall tile = (TileEntityVatWall)world.getTileEntity(x, y, z);

        if (tile.isMaster() || tile.hasMaster()) {
            System.out.println("Multiblock has been right clicked");
        }
        return super.onBlockActivated(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        TileEntity tile = world.getTileEntity(x, y, z);

        if (tile != null && (tile instanceof TileEntityVatWall)) {
            TileEntityVatWall vat = (TileEntityVatWall) tile;

            if (vat.hasMaster()) {
                if (vat.isMaster()) {
                    if (!vat.checkMultiblock()) {
                        System.out.println("Resetting multiblock...");
                        vat.resetMultiblock();
                    }
                } else if (!vat.checkForMaster()) {
                    vat.reset();
                } else {
                    TileEntityVatWall master = (TileEntityVatWall)world.getTileEntity(vat.getMasterX(), vat.getMasterY(), vat.getMasterZ());

                    if (!master.checkMultiblock()) {
                        System.out.println("Resetting multiblock...");
                        master.resetMultiblock();
                    }
                }
            }
        }

        super.onNeighborBlockChange(world, x, y, z, block);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityVatWall();
    }
}
