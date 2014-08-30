package com.morgrimm.osteomancy.reference;

import com.morgrimm.osteomancy.utility.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public class Textures {
    public static final String RESOURCE_PREFIX = Reference.MOD_ID.toLowerCase() + ":";

    public static final class Models {
        private static final String MODEL_TEXTURE_LOCATION = "textures/models/";

        public static final ResourceLocation WARD_PLINTH = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "wardPlinth.png");
    }
}