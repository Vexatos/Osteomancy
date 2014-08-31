package com.morgrimm.osteomancy;

import com.morgrimm.osteomancy.client.handler.KeyInputEventHandler;
import com.morgrimm.osteomancy.handler.ConfigurationHandler;
import com.morgrimm.osteomancy.init.ModBlocks;
import com.morgrimm.osteomancy.init.ModFluids;
import com.morgrimm.osteomancy.init.ModItems;
import com.morgrimm.osteomancy.network.PacketHandler;
import com.morgrimm.osteomancy.proxy.IProxy;
import com.morgrimm.osteomancy.rarities.Rarities;
import com.morgrimm.osteomancy.recipes.Recipes;
import com.morgrimm.osteomancy.reference.Reference;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid= Reference.MOD_ID, name= Reference.MOD_NAME, version=Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS, dependencies="required-after:AWWayofTime", canBeDeactivated = true)
public class Osteomancy {

    @Mod.Instance(Reference.MOD_ID)
    public static Osteomancy instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        PacketHandler.init();
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        proxy.registerKeybindings();
        ModItems.init();
        ModFluids.init();
        ModBlocks.init();
        Rarities.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // Register custom key bindings
        FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());

        // Register custom tile entities
        proxy.registerTileEntities();

        // Register custom rendering
        proxy.initRendering();

        // Register custom recipes
        Recipes.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
