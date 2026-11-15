package com.selyux.client.module.impl;

import com.selyux.client.Draw;
import com.selyux.client.PvpConfig;
import com.selyux.client.Theme;
import com.selyux.client.module.Category;
import com.selyux.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class KeystrokesModule extends Module {
    private static final int SIZE = 18;
    private static final int GAP = 2;

    public KeystrokesModule() {
        super("Keystrokes", "Tampilkan tombol WASD dan lompat", Category.PVP);
    }

    @Override
    public void renderHud(DrawContext ctx, MinecraftClient mc) {
        int totalW = SIZE * 3 + GAP * 2;
        int x0 = PvpConfig.keystrokesLeft ? 8 : mc.getWindow().getScaledWidth() - totalW - 8;
        int y0 = mc.getWindow().getScaledHeight() - 80;

        key(ctx, mc, x0 + SIZE + GAP, y0, SIZE, SIZE, "W", mc.options.forwardKey.isPressed());
        key(ctx, mc, x0, y0 + SIZE + GAP, SIZE, SIZE, "A", mc.options.leftKey.isPressed());
        key(ctx, mc, x0 + SIZE + GAP, y0 + SIZE + GAP, SIZE, SIZE, "S", mc.options.backKey.isPressed());
        key(ctx, mc, x0 + (SIZE + GAP) * 2, y0 + SIZE + GAP, SIZE, SIZE, "D", mc.options.rightKey.isPressed());
        key(ctx, mc, x0, y0 + (SIZE + GAP) * 2, totalW, 10, "", mc.options.jumpKey.isPressed());
    }

    private void key(DrawContext ctx, MinecraftClient mc, int x, int y, int w, int h, String label, boolean down) {
        Draw.box(ctx, x, y, w, h, down ? Theme.RED_DARK : Theme.HUD_BG, down ? Theme.RED : Theme.RED_DARK);
        if (!label.isEmpty()) {
            int tw = mc.textRenderer.getWidth(label);
            ctx.drawTextWithShadow(mc.textRenderer, label, x + (w - tw) / 2, y + (h - 8) / 2,
                    down ? Theme.TEXT : Theme.TEXT_DIM);
        }
    }
}
