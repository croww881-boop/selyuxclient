package com.selyux.client.gui;

import com.selyux.client.Theme;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/** Tombol hitam-merah serbaguna dengan label dinamis. */
public class ThemedButton extends ButtonWidget {
    private final Supplier<String> label;
    private final BooleanSupplier active;

    public ThemedButton(int x, int y, int w, int h, Supplier<String> label, BooleanSupplier active, Runnable action) {
        super(x, y, w, h, net.minecraft.text.Text.empty(), b -> action.run(), DEFAULT_NARRATION_SUPPLIER);
        this.label = label;
        this.active = active;
    }

    @Override
    public void drawIcon(DrawContext ctx, int mouseX, int mouseY, float delta) {
        boolean on = active.getAsBoolean();
        int bg = on ? (isHovered() ? Theme.RED : Theme.RED_DARK)
                    : (isHovered() ? Theme.BUTTON_HOVER : Theme.BUTTON);
        ctx.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), bg);
        ctx.fill(getX(), getY(), getX() + 2, getY() + getHeight(), on ? Theme.RED : Theme.RED_DARK);
        ctx.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, label.get(),
                getX() + 8, getY() + (getHeight() - 8) / 2, on ? Theme.TEXT : Theme.TEXT_DIM);
    }
}
