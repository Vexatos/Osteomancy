package com.morgrimm.osteomancy.item;

import WayofTime.alchemicalWizardry.api.items.interfaces.IBloodOrb;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import com.morgrimm.osteomancy.reference.Names;
import com.morgrimm.osteomancy.utility.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemCorporealOrb extends ItemOst implements IBloodOrb{

    public ItemCorporealOrb() {
        super();
        this.setName(Names.Items.CORPOREAL_ORB);
    }

    @Override
    public EnumRarity getRarity(ItemStack p_77613_1_) {
        return EnumRarity.rare;
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean p_77624_4_) {
        if (!(itemstack.stackTagCompound == null)) {
            if (hasOwner(itemstack)) {
                if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                    list.add("LP: " + SoulNetworkHandler.getCurrentEssence(getOwner(itemstack)));
                } else {
                    list.add("Stores raw life essence");
                }
            } else {
                list.add("Stores raw life essence");
            }
        } else {
            list.add("Stores raw life essence");
        }

        if (!(itemstack.stackTagCompound == null)) {
            list.add("Current owner: " + itemstack.stackTagCompound.getString("ownerName"));
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
        setOwner(itemstack, player);
        return itemstack;
    }

    @Override
    public int getMaxEssence() {
        return 1000000000;
    }

    @Override
    public int getOrbLevel() {
        return 5;
    }

    private static boolean hasOwner(ItemStack itemstack) {
        if (NBTHelper.getString(itemstack, "ownerName") == "") {
            return false;
        } else {
            return true;
        }
    }

    private static String getOwner(ItemStack itemstack) {
        return NBTHelper.getString(itemstack, "ownerName");
    }

    private static void setOwner(ItemStack itemstack, EntityPlayer player) {
        if (!hasOwner(itemstack)) {
            System.out.println("Setting owner...");
            NBTHelper.setString(itemstack, "ownerName", player.getDisplayName());
        }
    }
}

