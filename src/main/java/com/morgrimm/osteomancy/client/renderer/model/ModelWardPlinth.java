package com.morgrimm.osteomancy.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWardPlinth extends ModelBase
{
    //fields
    ModelRenderer claw1arm;
    ModelRenderer claw2arm;
    ModelRenderer claw3arm;
    ModelRenderer claw4arm;
    ModelRenderer base1;
    ModelRenderer base2;
    ModelRenderer claw1;
    ModelRenderer claw2;
    ModelRenderer claw3;
    ModelRenderer claw4;

    public ModelWardPlinth()
    {
        textureWidth = 64;
        textureHeight = 32;

        claw1arm = new ModelRenderer(this, 48, 15);
        claw1arm.addBox(0F, 0F, 0F, 1, 8, 1);
        claw1arm.setRotationPoint(-5.5F, 15F, 5F);
        claw1arm.setTextureSize(64, 32);
        claw1arm.mirror = true;
        setRotation(claw1arm, 0F, 0.8877426F, -0.1115358F);
        claw2arm = new ModelRenderer(this, 56, 19);
        claw2arm.addBox(0F, 0F, 0F, 1, 8, 1);
        claw2arm.setRotationPoint(4.5F, 15F, -5F);
        claw2arm.setTextureSize(64, 32);
        claw2arm.mirror = true;
        setRotation(claw2arm, 0F, 0.8876745F, 0.111544F);
        claw3arm = new ModelRenderer(this, 56, 0);
        claw3arm.addBox(0F, 0F, 0F, 1, 8, 1);
        claw3arm.setRotationPoint(-5F, 15F, -6F);
        claw3arm.setTextureSize(64, 32);
        claw3arm.mirror = true;
        setRotation(claw3arm, 0F, -0.8877443F, -0.1115358F);
        claw4arm = new ModelRenderer(this, 56, 9);
        claw4arm.addBox(0F, 0F, 0F, 1, 8, 1);
        claw4arm.setRotationPoint(5F, 15F, 4.5F);
        claw4arm.setTextureSize(64, 32);
        claw4arm.mirror = true;
        setRotation(claw4arm, 0F, -0.8877443F, 0.111544F);
        base1 = new ModelRenderer(this, 0, 0);
        base1.addBox(0F, 0F, 0F, 14, 1, 14);
        base1.setRotationPoint(-7F, 23F, -7F);
        base1.setTextureSize(64, 32);
        base1.mirror = true;
        setRotation(base1, 0F, 0F, 0F);
        base2 = new ModelRenderer(this, 0, 15);
        base2.addBox(0F, 0F, 0F, 12, 1, 12);
        base2.setRotationPoint(-6F, 22F, -6F);
        base2.setTextureSize(64, 32);
        base2.mirror = true;
        setRotation(base2, 0F, 0F, 0F);
        claw1 = new ModelRenderer(this, 48, 24);
        claw1.addBox(0F, 0F, 0F, 3, 1, 1);
        claw1.setRotationPoint(-5.5F, 15F, 5F);
        claw1.setTextureSize(64, 32);
        claw1.mirror = true;
        setRotation(claw1, 0F, 0.8877443F, -0.3035302F);
        claw2 = new ModelRenderer(this, 48, 26);
        claw2.addBox(0F, 0F, 0F, 3, 1, 1);
        claw2.setRotationPoint(3.3F, 14F, -3.8F);
        claw2.setTextureSize(64, 32);
        claw2.mirror = true;
        setRotation(claw2, 0F, 0.8178613F, 0.3035302F);
        claw3 = new ModelRenderer(this, 0, 28);
        claw3.addBox(0F, 0F, 0F, 1, 1, 3);
        claw3.setRotationPoint(3.3F, 14F, 3.5F);
        claw3.setTextureSize(64, 32);
        claw3.mirror = true;
        setRotation(claw3, -0.2230717F, 0.5576792F, 0F);
        claw4 = new ModelRenderer(this, 8, 28);
        claw4.addBox(0F, 0F, 0F, 1, 1, 3);
        claw4.setRotationPoint(-3.3F, 14F, -3.8F);
        claw4.setTextureSize(64, 32);
        claw4.mirror = true;
        setRotation(claw4, -0.1487144F, -2.472378F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        claw1arm.render(f5);
        claw2arm.render(f5);
        claw3arm.render(f5);
        claw4arm.render(f5);
        base1.render(f5);
        base2.render(f5);
        claw1.render(f5);
        claw2.render(f5);
        claw3.render(f5);
        claw4.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}

