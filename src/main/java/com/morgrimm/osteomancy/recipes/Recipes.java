package com.morgrimm.osteomancy.recipes;

import WayofTime.alchemicalWizardry.api.alchemy.AlchemyRecipeRegistry;
import com.morgrimm.osteomancy.init.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes {

    public static void init() {
        registerAlchemyRecipes();
    }

    public static void registerAlchemyRecipes() {
        AlchemyRecipeRegistry.registerRecipe(new ItemStack(ModItems.fleshSolvent, 1), 1, new ItemStack[]{new ItemStack(Items.rotten_flesh, 1), new ItemStack(Items.potionitem, 1, 0)}, 1);
    }
}