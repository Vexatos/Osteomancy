package com.morgrimm.osteomancy.init;

import com.morgrimm.osteomancy.fluids.FluidAcid;
import com.morgrimm.osteomancy.fluids.blocks.BlockFluidAcid;
import com.morgrimm.osteomancy.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids {

    // Fluids
    public static Fluid acid = new FluidAcid(Names.Fluids.ACID);

    // Fluid blocks
    public static BlockFluidClassic Acid;

    public static void init() {
        initFluids();
        initFluidBlocks();
    }

    private static void initFluids() {
        FluidRegistry.registerFluid(acid);
    }

    private static void initFluidBlocks() {
        Acid = new BlockFluidAcid();
        GameRegistry.registerBlock(Acid, Names.Fluids.ACID);
    }

}
