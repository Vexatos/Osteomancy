package com.morgrimm.osteomancy.block;

import com.morgrimm.osteomancy.reference.Names;
import com.morgrimm.osteomancy.tileentity.TileEntityVatWall;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockVatWall extends BlockOst implements ITileEntityProvider{

    public BlockVatWall (Material material) {
        super(material);
        this.setName(Names.Blocks.VAT_WALL);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        TileEntity tile = world.getTileEntity(x, y, z);

        if (tile != null && (tile instanceof TileEntityVatWall)) {
            TileEntityVatWall vat = (TileEntityVatWall) tile;

            if (vat.hasMaster()) {
                if (vat.isMaster()) {
                    if (!vat.checkMultiblock()) {
                        vat.resetMultiblock();
                    }
                } else if (!vat.checkForMaster()) {
                    vat.reset();
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
