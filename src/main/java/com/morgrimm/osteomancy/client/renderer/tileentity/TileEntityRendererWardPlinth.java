package com.morgrimm.osteomancy.client.renderer.tileentity;

import com.morgrimm.osteomancy.client.renderer.model.ModelWardPlinth;
import com.morgrimm.osteomancy.reference.Textures;
import com.morgrimm.osteomancy.tileentity.TileEntityWardPlinth;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityRendererWardPlinth extends TileEntitySpecialRenderer{

    private final ModelWardPlinth plinth = new ModelWardPlinth();
    private final RenderItem renderItem;

    public TileEntityRendererWardPlinth() {
        renderItem = new RenderItem();
        renderItem.setRenderManager(RenderManager.instance);
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float tick) {

        if (tile instanceof TileEntityWardPlinth) {

            // Render the plinth
            GL11.glPushMatrix();

            GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

            this.bindTexture(Textures.Models.WARD_PLINTH);

            this.plinth.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);

            GL11.glPopMatrix();

            // Render the item inside the plinth
            ItemStack itemstack = ((TileEntityWardPlinth) tile).getStackInSlot(0);

            if (itemstack != null) {
                GL11.glPushMatrix();

                float rotationAngle = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

                EntityItem ghostItem = new EntityItem(tile.getWorldObj());
                ghostItem.hoverStart = 0.0f;
                ghostItem.setEntityItemStack(itemstack);

                GL11.glScalef(1.0f, 1.0f, 1.0f);
                GL11.glTranslatef((float) x + 0.5f, (float) y + 0.25f, (float) z + 0.5f);
                GL11.glRotatef(rotationAngle, 0.0F, 1.0F, 0.0F);

                renderItem.doRender(ghostItem, 0, 0, 0, 0, 0);

                GL11.glPopMatrix();
            }
        }
    }
}
