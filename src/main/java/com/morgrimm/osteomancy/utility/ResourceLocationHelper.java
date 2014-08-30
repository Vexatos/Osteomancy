package com.morgrimm.osteomancy.utility;

import com.morgrimm.osteomancy.reference.Reference;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationHelper {

    public static ResourceLocation getResourceLocation(String modid, String path) {
        return new ResourceLocation(modid, path);
    }

    public static ResourceLocation getResourceLocation(String path) {
        return getResourceLocation(Reference.MOD_ID.toLowerCase(), path);
    }
}
