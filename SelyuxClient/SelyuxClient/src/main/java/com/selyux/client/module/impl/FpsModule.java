package com.selyux.client.module.impl;

import com.selyux.client.Theme;
import com.selyux.client.module.Category;
import com.selyux.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class FpsModule extends Module {
    public FpsModule() {
        super("FPS", "Tampilkan FPS", Category.HUD);
    }

    @Override
    public void renderHud(DrawContext ctx, MinecraftClient mc) {
        String s = "FPS: " + mc.getCurrentFps();
        int w = mc.textRenderer.getWidth(s);
        ctx.fill(4, 20, 4 + w + 10, 33, Theme.HUD_BG);
        ctx.fill(4, 20, 6, 33, Theme.RED_DARK);
        ctx.drawTextWithShadow(mc.textRenderer, s, 10, 23, Theme.TEXT);
    }
}
