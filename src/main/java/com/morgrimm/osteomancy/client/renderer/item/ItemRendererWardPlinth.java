package com.morgrimm.osteomancy.client.renderer.item;

import com.morgrimm.osteomancy.client.renderer.model.ModelWardPlinth;
import com.morgrimm.osteomancy.reference.Textures;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemRendererWardPlinth implements IItemRenderer{

    private ModelWardPlinth plinth;

    public ItemRendererWardPlinth() {
        plinth = new ModelWardPlinth();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        switch (type) {
            case ENTITY: {
                renderWardPlinth(-0.5F, -0.38F, 0.5F);
            }

            case EQUIPPED: {
                renderWardPlinth(0.0F, 0.0F, 1.0F);
            }

            case EQUIPPED_FIRST_PERSON: {
                renderWardPlinth(0.0F, 2.0F, 1.0F);
            }


            case INVENTORY: {
                renderWardPlinth(0.0F, 1.0F, 0.0F);
            }
        }
    }

    private void renderWardPlinth(float x, float y, float z) {
        GL11.glPushMatrix();

        GL11.glScalef(1F, 1F, 1F);
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(180F, 1F, 0, 0);

        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.Models.WARD_PLINTH);

        this.plinth.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);

        GL11.glPopMatrix();
    }
}
