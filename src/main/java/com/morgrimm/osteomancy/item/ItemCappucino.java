package com.morgrimm.osteomancy.item;

import com.morgrimm.osteomancy.reference.Names;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class ItemCappucino extends ItemFoodOst {
    public ItemCappucino(int healAmount, float saturationModifier, boolean isWolfsFavoriteFood) {
        super(healAmount, saturationModifier, isWolfsFavoriteFood);
        this.setCreativeTab(null);
        this.setMaxStackSize(1);
        this.setName(Names.Items.CAPPUCINO);
    }
}
