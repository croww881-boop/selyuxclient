package com.selyux.client.gui;

import com.selyux.client.Theme;
import com.selyux.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ModuleButton extends ButtonWidget {
    private final Module module;

    public ModuleButton(int x, int y, int w, int h, Module module) {
        super(x, y, w, h, Text.literal(module.getName()), b -> module.toggle(), DEFAULT_NARRATION_SUPPLIER);
        this.module = module;
    }

    @Override
    public void renderWidget(DrawContext ctx, int mouseX, int mouseY, float delta) {
        boolean on = module.isEnabled();
        int bg = on ? (isHovered() ? Theme.RED : Theme.RED_DARK)
                    : (isHovered() ? Theme.BUTTON_HOVER : Theme.BUTTON);
        ctx.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), bg);
        ctx.fill(getX(), getY(), getX() + 2, getY() + getHeight(), on ? Theme.RED : Theme.RED_DARK);
        ctx.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, getMessage(),
                getX() + 8, getY() + (getHeight() - 8) / 2, on ? Theme.TEXT : Theme.TEXT_DIM);
    }
}
