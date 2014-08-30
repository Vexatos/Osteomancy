package com.morgrimm.osteomancy.block;

import com.morgrimm.osteomancy.reference.Names;
import com.morgrimm.osteomancy.reference.Reference;
import com.morgrimm.osteomancy.tileentity.TileEntityVatWall;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.swing.*;

public class BlockVatWall extends BlockOst implements ITileEntityProvider{

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockVatWall (Material material) {
        super(material);
        this.setName(Names.Blocks.VAT_WALL);
        icons = new IIcon[10];
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        TileEntityVatWall tile = (TileEntityVatWall)world.getTileEntity(x, y, z);

        // WHAT IT SHOULD DO ON RIGHT CLICK

        return super.onBlockActivated(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        icons[0] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + "vatWallNormal");
        icons[1] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + "vatWallCentre");
        icons[2] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + "vatWallCorner1");
        icons[3] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + "vatWallCorner2");
        icons[4] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + "vatWallCorner3");
        icons[5] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + "vatWallCorner4");
        icons[6] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + "vatWallMiddle1");
        icons[7] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + "vatWallMiddle2");
        icons[8] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + "vatWallMiddle3");
        icons[9] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + "vatWallMiddle4");
    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        TileEntityVatWall vat = (TileEntityVatWall) world.getTileEntity(x, y, z);

        System.out.println("hasMaster: " + vat.hasMaster());
        System.out.println("isMaster: " + vat.isMaster());

        if (vat.hasMaster() || vat.isMaster()) {

            return icons[0];
        }

        return icons[1];
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int p_149749_6_) {
        TileEntityVatWall tile = (TileEntityVatWall) world.getTileEntity(x, y, z);

        if (tile.isMaster()) {
            tile.resetMultiblock();
        }

        super.breakBlock(world, x, y, z, block, p_149749_6_);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        TileEntity tile = world.getTileEntity(x, y, z);

        if (tile instanceof TileEntityVatWall) {
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
