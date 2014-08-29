package com.morgrimm.osteomancy.init;

import com.morgrimm.osteomancy.block.BlockOst;
import com.morgrimm.osteomancy.block.BlockVatWall;
import com.morgrimm.osteomancy.reference.Names;
import com.morgrimm.osteomancy.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
    // Declare blocks here
    public static final BlockOst VatWall = new BlockVatWall(Material.rock);

    public static void init() {
        // Register blocks here
        GameRegistry.registerBlock(VatWall, Names.Blocks.VAT_WALL);
    }
}
