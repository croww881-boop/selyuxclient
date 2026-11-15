package com.selyux.client.gui;

import com.selyux.client.SelyuxClient;
import com.selyux.client.Theme;
import com.selyux.client.module.Category;
import com.selyux.client.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

/** GUI hitam-merah: satu panel per kategori, klik untuk toggle module. Esc untuk menutup. */
public class ClickGuiScreen extends Screen {
    private static final int PANEL_W = 112;
    private static final int GAP = 8;
    private static final int TOP = 56;
    private static final int HEADER = 24;
    private static final int ROW = 20;
    private static final int PAD = 4;

    public ClickGuiScreen() {
        super(Text.literal("Selyux Client"));
    }

    private int startX() {
        int n = Category.values().length;
        return (width - (n * PANEL_W + (n - 1) * GAP)) / 2;
    }

    @Override
    protected void init() {
        int i = 0;
        for (Category c : Category.values()) {
            int x = startX() + i * (PANEL_W + GAP);
            int y = TOP + HEADER;
            for (Module m : SelyuxClient.modules().byCategory(c)) {
                addDrawableChild(new ModuleButton(x + PAD, y, PANEL_W - PAD * 2, ROW - 2, m));
                y += ROW;
            }
            i++;
        }
    }

    @Override
    public void renderBackground(DrawContext ctx, int mouseX, int mouseY, float delta) {
        ctx.fill(0, 0, width, height, Theme.BACKDROP);

        Text title = Text.literal("SELYUX CLIENT").formatted(Formatting.BOLD);
        ctx.drawTextWithShadow(textRenderer, title, (width - textRenderer.getWidth(title)) / 2, 24, Theme.RED);
        Text sub = Text.literal("Klik module untuk ON / OFF");
        ctx.drawTextWithShadow(textRenderer, sub, (width - textRenderer.getWidth(sub)) / 2, 38, Theme.TEXT_DIM);

        int i = 0;
        for (Category c : Category.values()) {
            List<Module> list = SelyuxClient.modules().byCategory(c);
            int x = startX() + i * (PANEL_W + GAP);
            int h = HEADER + list.size() * ROW + PAD;
            ctx.fill(x, TOP, x + PANEL_W, TOP + h, Theme.PANEL);
            ctx.fill(x, TOP, x + PANEL_W, TOP + 2, Theme.RED);
            ctx.drawTextWithShadow(textRenderer, Text.literal(c.label).formatted(Formatting.BOLD),
                    x + 8, TOP + 9, Theme.RED);
            i++;
        }
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
