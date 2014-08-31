package com.morgrimm.osteomancy.block;

import com.morgrimm.osteomancy.reference.Names;
import net.minecraft.block.material.Material;

public class BlockBone extends BlockOst{

    public BlockBone(Material material) {
        super(material);
        this.setName(Names.Blocks.BONE_BLOCK);
    }

}
