package com.selyux.client.gui;

import com.selyux.client.Theme;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/** Menu ESC bertema hitam-merah, tombol bawaan tetap berfungsi normal. */
public class SelyuxPauseScreen extends GameMenuScreen {
    public SelyuxPauseScreen() {
        super(true);
    }

    @Override
    protected void init() {
        super.init();
        addDrawableChild(new ThemedButton(10, 40, 110, 20, () -> "Selyux GUI", () -> false,
                () -> this.client.setScreen(new ClickGuiScreen())));
        addDrawableChild(new ThemedButton(10, 64, 110, 20, () -> "PvP Settings", () -> false,
                () -> this.client.setScreen(new PvpSettingsScreen(this))));
    }

    @Override
    public void renderBackground(DrawContext ctx, int mouseX, int mouseY, float delta) {
        ctx.fill(0, 0, width, height, 0xD8000000);
        ctx.fill(0, 0, width, 2, Theme.RED);
        ctx.fill(0, height - 2, width, height, Theme.RED);

        Text title = Text.literal("SELYUX CLIENT").formatted(Formatting.BOLD);
        ctx.drawTextWithShadow(textRenderer, title, 10, 12, Theme.RED);
    }
}
