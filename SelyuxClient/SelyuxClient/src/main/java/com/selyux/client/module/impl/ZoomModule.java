package com.selyux.client.module.impl;

import com.selyux.client.SelyuxClient;
import com.selyux.client.module.Category;
import com.selyux.client.module.Module;
import net.minecraft.client.MinecraftClient;

/** Tahan tombol Zoom (default C) untuk memperkecil FOV. */
public class ZoomModule extends Module {
    private static final int ZOOM_FOV = 30;
    private Integer savedFov = null;

    public ZoomModule() {
        super("Zoom", "Tahan C untuk zoom", Category.RENDER);
    }

    @Override
    public void onTick(MinecraftClient mc) {
        boolean holding = SelyuxClient.zoomKey.isPressed();
        if (holding && savedFov == null) {
            savedFov = mc.options.getFov().getValue();
            mc.options.getFov().setValue(ZOOM_FOV);
        } else if (!holding && savedFov != null) {
            restore(mc);
        }
    }

    @Override
    protected void onDisable() {
        restore(MinecraftClient.getInstance());
    }

    private void restore(MinecraftClient mc) {
        if (savedFov != null) {
            mc.options.getFov().setValue(savedFov);
            savedFov = null;
        }
    }
}
