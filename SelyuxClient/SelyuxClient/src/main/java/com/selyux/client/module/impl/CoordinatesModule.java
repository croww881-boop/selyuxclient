package com.selyux.client.module.impl;

import com.selyux.client.Theme;
import com.selyux.client.module.Category;
import com.selyux.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class CoordinatesModule extends Module {
    public CoordinatesModule() {
        super("Coordinates", "Tampilkan koordinat XYZ", Category.HUD);
    }

    @Override
    public void renderHud(DrawContext ctx, MinecraftClient mc) {
        if (mc.player == null) return;
        String s = "XYZ: " + mc.player.getBlockX() + " / " + mc.player.getBlockY() + " / " + mc.player.getBlockZ();
        int w = mc.textRenderer.getWidth(s);
        int y = mc.getWindow().getScaledHeight() - 18;
        ctx.fill(4, y, 4 + w + 10, y + 13, Theme.HUD_BG);
        ctx.fill(4, y, 6, y + 13, Theme.RED_DARK);
        ctx.drawTextWithShadow(mc.textRenderer, s, 10, y + 3, Theme.TEXT);
    }
}
