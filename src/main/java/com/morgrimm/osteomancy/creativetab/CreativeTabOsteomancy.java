package com.morgrimm.osteomancy.creativetab;

import com.morgrimm.osteomancy.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTabOsteomancy {

    public static final CreativeTabs OSTEOMANCY_TAB = new CreativeTabs(Reference.MOD_ID) {
        @Override
        public Item getTabIconItem() {
            return Items.bone;
        }

        @Override
        public String getTranslatedTabLabel() {
            return Reference.MOD_NAME;
        }
    };
}
