package com.morgrimm.osteomancy.init;

import com.morgrimm.osteomancy.item.ItemOst;
import com.morgrimm.osteomancy.item.ItemSolvent;
import com.morgrimm.osteomancy.reference.Names;
import com.morgrimm.osteomancy.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
    // Declare items here
    public static final ItemOst fleshSolvent = new ItemSolvent();

    public static void init() {
        // Register items here
        GameRegistry.registerItem(fleshSolvent, Names.Items.SOLVENT);
    }
}
