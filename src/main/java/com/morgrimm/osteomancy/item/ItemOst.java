package com.morgrimm.osteomancy.item;

import WayofTime.alchemicalWizardry.api.items.interfaces.IBloodOrb;
import com.morgrimm.osteomancy.creativetab.CreativeTabOsteomancy;
import com.morgrimm.osteomancy.reference.Reference;
import com.morgrimm.osteomancy.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemOst extends Item {

    public ItemOst() {
        super();
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
        this.setUnlocalizedName(name);
        this.setTextureName(Textures.RESOURCE_PREFIX + name);
    }
}