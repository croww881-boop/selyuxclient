package com.selyux.client.module.impl;

import com.selyux.client.PvpConfig;
import com.selyux.client.Theme;
import com.selyux.client.module.Category;
import com.selyux.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.effect.StatusEffectInstance;

public class PotionHudModule extends Module {
    public PotionHudModule() {
        super("Potion HUD", "Tampilkan efek aktif dan sisa waktu", Category.PVP);
    }

    @Override
    public void renderHud(DrawContext ctx, MinecraftClient mc) {
        if (mc.player == null) return;
        int y = 52;
        for (StatusEffectInstance e : mc.player.getStatusEffects()) {
            String s = e.getEffectType().value().getName().getString() + " " + (e.getAmplifier() + 1);
            if (PvpConfig.potionTime) {
                if (e.isInfinite()) {
                    s += "  **";
                } else {
                    int sec = e.getDuration() / 20;
                    s += String.format("  %d:%02d", sec / 60, sec % 60);
                }
            }
            int w = mc.textRenderer.getWidth(s);
            ctx.fill(4, y, 4 + w + 10, y + 13, Theme.HUD_BG);
            ctx.fill(4, y, 6, y + 13, Theme.RED_DARK);
            ctx.drawTextWithShadow(mc.textRenderer, s, 10, y + 3, Theme.TEXT);
            y += 14;
        }
    }
}
