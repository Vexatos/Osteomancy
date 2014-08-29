package com.morgrimm.osteomancy.init;

import com.morgrimm.osteomancy.item.*;
import com.morgrimm.osteomancy.reference.Names;
import com.morgrimm.osteomancy.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
    // Declare items here
    public static final ItemOst fleshSolvent = new ItemSolvent();
    public static final ItemFoodOst cappucino = new ItemCappucino(2, 5, false);
    public static final ItemOst corporealOrb = new ItemCorporealOrb();

    public static void init() {
        // Register items here
        GameRegistry.registerItem(fleshSolvent, Names.Items.SOLVENT);
        GameRegistry.registerItem(cappucino, Names.Items.CAPPUCINO);
        GameRegistry.registerItem(corporealOrb, Names.Items.CORPOREAL_ORB);
    }
}
