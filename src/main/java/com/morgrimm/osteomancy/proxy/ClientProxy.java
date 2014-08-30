package com.morgrimm.osteomancy.proxy;

import com.morgrimm.osteomancy.client.renderer.item.ItemRendererWardPlinth;
import com.morgrimm.osteomancy.client.renderer.tileentity.TileEntityRendererWardPlinth;
import com.morgrimm.osteomancy.client.settings.Keybindings;
import com.morgrimm.osteomancy.init.ModBlocks;
import com.morgrimm.osteomancy.reference.Names;
import com.morgrimm.osteomancy.reference.RenderIds;
import com.morgrimm.osteomancy.tileentity.TileEntityVatWall;
import com.morgrimm.osteomancy.tileentity.TileEntityWardPlinth;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy{
    @Override
    public void registerKeybindings() {
    }

    @Override
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityVatWall.class, Names.Blocks.VAT_WALL);
    }

    @Override
    public void initRendering() {
        RenderIds.wardPlinth = RenderingRegistry.getNextAvailableRenderId();

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.WardPlinth), new ItemRendererWardPlinth());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWardPlinth.class, new TileEntityRendererWardPlinth());
    }
}