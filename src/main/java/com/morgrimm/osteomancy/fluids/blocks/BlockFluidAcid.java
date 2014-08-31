package com.morgrimm.osteomancy.fluids.blocks;

import com.morgrimm.osteomancy.reference.Names;
import com.morgrimm.osteomancy.reference.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class BlockFluidAcid extends BlockFluidClassic{

    public static IIcon still;
    public static IIcon flowing;

    public BlockFluidAcid() {
        super(FluidRegistry.getFluid(Names.Fluids.ACID), Material.water);
        this.setBlockName(Names.Fluids.ACID);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        still = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + "acid_still");
        flowing = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + "acid_flowing");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return side != 0 && side != 1 ? flowing : still;
    }
}
