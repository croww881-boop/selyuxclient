package com.selyux.client.module.impl;

import com.selyux.client.Theme;
import com.selyux.client.module.Category;
import com.selyux.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class WatermarkModule extends Module {
    public WatermarkModule() {
        super("Watermark", "Logo teks Selyux di pojok kiri atas", Category.HUD);
    }

    @Override
    public void renderHud(DrawContext ctx, MinecraftClient mc) {
        Text text = Text.literal("SELYUX").formatted(Formatting.BOLD)
                .append(Text.literal(" Client").formatted(Formatting.RESET));
        int w = mc.textRenderer.getWidth(text);
        ctx.fill(4, 4, 4 + w + 10, 17, Theme.HUD_BG);
        ctx.fill(4, 4, 6, 17, Theme.RED);
        ctx.drawTextWithShadow(mc.textRenderer, text, 10, 7, Theme.RED);
    }
}
