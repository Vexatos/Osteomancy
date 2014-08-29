package com.morgrimm.osteomancy.client.handler;

import com.morgrimm.osteomancy.reference.Key;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;


public class KeyInputEventHandler {
    @SubscribeEvent
    public void handleKeyInput(InputEvent.KeyInputEvent event) {
    }

    private static Key getKeyPressed() {
        // Return which key binding has been pressed
        return Key.UNKNOWN;
    }
}
