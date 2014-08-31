package com.morgrimm.osteomancy.recipes;

import WayofTime.alchemicalWizardry.api.alchemy.AlchemyRecipeRegistry;
import com.morgrimm.osteomancy.init.ModBlocks;
import com.morgrimm.osteomancy.init.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes {

    public static void init() {
        registerRecipes();
        registerAlchemyRecipes();
    }

    public static void registerAlchemyRecipes() {
        AlchemyRecipeRegistry.registerRecipe(new ItemStack(ModItems.fleshSolvent, 1), 1, new ItemStack[]{new ItemStack(Items.rotten_flesh, 1), new ItemStack(Items.potionitem, 1, 0)}, 1);
    }

    public static void registerRecipes() {
        GameRegistry.addRecipe(new ItemStack(ModBlocks.BoneBlock), "bbb", "bbb", "bbb", 'b', new ItemStack(Items.bone));
    }
}