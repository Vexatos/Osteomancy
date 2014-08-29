package com.morgrimm.osteomancy.item;

import com.morgrimm.osteomancy.creativetab.CreativeTabOsteomancy;
import com.morgrimm.osteomancy.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;

public class ItemFoodOst extends ItemFood{

    public ItemFoodOst(int healAmount, float saturationModifier, boolean isWolfsFavoriteFood) {
        super(healAmount, saturationModifier, isWolfsFavoriteFood);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabOsteomancy.OSTEOMANCY_TAB);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(this.iconString);
    }

    protected void setName(String name) {
        this.setUnlocalizedName(Textures.RESOURCE_PREFIX + name);
        this.setTextureName(Textures.RESOURCE_PREFIX + name);
    }

}
