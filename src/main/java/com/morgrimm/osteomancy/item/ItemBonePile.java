package com.morgrimm.osteomancy.item;

import com.morgrimm.osteomancy.reference.Names;
import com.morgrimm.osteomancy.utility.LogHelper;
import com.morgrimm.osteomancy.utility.NBTHelper;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBonePile extends ItemOst{

    public ItemBonePile() {
        super();
        this.setName(Names.Items.BONE_PILE);
        this.setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean p_77624_4_) {
        super.addInformation(itemstack, player, list, p_77624_4_);

        if (itemstack.stackTagCompound != null && itemstack.stackTagCompound.hasKey("entityClassName")) {
            list.add("A pile of " + getSimpleEntityName(NBTHelper.getString(itemstack, "entityClassName")).toLowerCase() + " bones. Poor bastard.");
        } else {
            list.add("A pile of bones.");
        }
    }

    private String getSimpleEntityName(String className) {
        try {
            return (String) EntityList.classToStringMapping.get(Class.forName(className));
        } catch (ClassNotFoundException e) {

            // Should never happen
            LogHelper.fatal("Something fucked up o,o");
            return "ERROR";
        }
    }
}
