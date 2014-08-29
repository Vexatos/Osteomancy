package com.morgrimm.osteomancy.proxy;

import com.morgrimm.osteomancy.client.settings.Keybindings;
import com.morgrimm.osteomancy.reference.Names;
import com.morgrimm.osteomancy.tileentity.TileEntityVatWall;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy{
    @Override
    public void registerKeybindings() {
    }

    @Override
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityVatWall.class, Names.Blocks.VAT_WALL);
    }
}
