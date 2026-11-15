package com.selyux.client.module.impl;

import com.selyux.client.Theme;
import com.selyux.client.module.Category;
import com.selyux.client.module.Module;
import com.selyux.client.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.util.Comparator;
import java.util.List;

/** Daftar module aktif di pojok kanan atas, diurutkan dari yang terpanjang. */
public class ArrayListModule extends Module {
    private final ModuleManager manager;

    public ArrayListModule(ModuleManager manager) {
        super("ArrayList", "Daftar module aktif", Category.HUD);
        this.manager = manager;
    }

    @Override
    public void renderHud(DrawContext ctx, MinecraftClient mc) {
        List<Module> active = manager.all().stream()
                .filter(m -> m.isEnabled() && m.getCategory() != Category.HUD)
                .sorted(Comparator.comparingInt((Module m) -> mc.textRenderer.getWidth(m.getName())).reversed())
                .toList();

        int screenW = mc.getWindow().getScaledWidth();
        int y = 4;
        for (Module m : active) {
            int w = mc.textRenderer.getWidth(m.getName());
            int x = screenW - w - 10;
            ctx.fill(x - 2, y, screenW - 2, y + 12, Theme.HUD_BG);
            ctx.fill(screenW - 4, y, screenW - 2, y + 12, Theme.RED);
            ctx.drawTextWithShadow(mc.textRenderer, m.getName(), x + 2, y + 2, Theme.RED);
            y += 12;
        }
    }
}
