package com.selyux.client.gui;

import com.selyux.client.Theme;
import com.selyux.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;

public class ModuleButton extends ButtonWidget {
    private final Module module;

    public ModuleButton(int x, int y, int w, int h, Module module) {
        super(x, y, w, h, net.minecraft.text.Text.empty(), b -> module.toggle(), DEFAULT_NARRATION_SUPPLIER);
        this.module = module;
    }

    @Override
    public void drawIcon(DrawContext ctx, int mouseX, int mouseY, float delta) {
        boolean on = module.isEnabled();
        int bg = on ? (isHovered() ? Theme.RED : Theme.RED_DARK)
                    : (isHovered() ? Theme.BUTTON_HOVER : Theme.BUTTON);
        ctx.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), bg);
        ctx.fill(getX(), getY(), getX() + 2, getY() + getHeight(), on ? Theme.RED : Theme.RED_DARK);
        ctx.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, module.getName(),
                getX() + 8, getY() + (getHeight() - 8) / 2, on ? Theme.TEXT : Theme.TEXT_DIM);
    }
}
