package com.morgrimm.osteomancy.block;

import com.morgrimm.osteomancy.creativetab.CreativeTabOsteomancy;
import com.morgrimm.osteomancy.reference.Reference;
import com.morgrimm.osteomancy.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockOst extends Block {

    public BlockOst(Material material) {
        super(material);
        this.setCreativeTab(CreativeTabOsteomancy.OSTEOMANCY_TAB);
    }

    protected void setName(String name) {
        this.setBlockName(Textures.RESOURCE_PREFIX + name);
        this.setBlockTextureName(Textures.RESOURCE_PREFIX + name);
    }
}