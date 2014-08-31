package com.morgrimm.osteomancy.tileentity;

import net.minecraft.client.audio.ITickableSound;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.util.ResourceLocation;

public class LoopingTileEntitySound extends PositionedSound implements ITickableSound {

    private boolean donePlaying;

    protected LoopingTileEntitySound(ResourceLocation location, float x, float y, float z, float volume, float pitch) {
        super(location);
        this.xPosF = x;
        this.yPosF = y;
        this.zPosF = z;
        this.volume = volume;
        this.field_147663_c = pitch;
        this.repeat = true;
    }

    @Override
    public boolean isDonePlaying() {
        return donePlaying;
    }

    public void startPlaying() {
        this.donePlaying = false;
    }

    public void endPlaying() {
        this.donePlaying = true;
    }

    @Override
    public void update() {

    }
}
