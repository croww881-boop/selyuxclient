package com.selyux.client.gui;

import com.selyux.client.PvpConfig;
import com.selyux.client.Theme;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/** Pengaturan PvP: klik tombol untuk mengubah nilainya. */
public class PvpSettingsScreen extends Screen {
    private static final int W = 200;
    private static final int H = 20;
    private static final int GAP = 4;
    private static final int TOP = 70;
    private static final int ROWS = 6;

    private final Screen parent;

    public PvpSettingsScreen(Screen parent) {
        super(Text.literal("PvP Settings"));
        this.parent = parent;
    }

    private static String onOff(boolean v) {
        return v ? "ON" : "OFF";
    }

    @Override
    protected void init() {
        int x = (width - W) / 2;
        int y = TOP;

        y = row(x, y, () -> "Keystrokes posisi: " + (PvpConfig.keystrokesLeft ? "Kiri" : "Kanan"),
                () -> false, () -> PvpConfig.keystrokesLeft = !PvpConfig.keystrokesLeft);
        y = row(x, y, () -> "CPS klik kanan: " + onOff(PvpConfig.cpsShowRight),
                () -> PvpConfig.cpsShowRight, () -> PvpConfig.cpsShowRight = !PvpConfig.cpsShowRight);
        y = row(x, y, () -> "Armor durability %: " + onOff(PvpConfig.armorDurability),
                () -> PvpConfig.armorDurability, () -> PvpConfig.armorDurability = !PvpConfig.armorDurability);
        y = row(x, y, () -> "Potion sisa waktu: " + onOff(PvpConfig.potionTime),
                () -> PvpConfig.potionTime, () -> PvpConfig.potionTime = !PvpConfig.potionTime);
        y = row(x, y, () -> "Preset PvP: Auto Sprint " + onOff(PvpConfig.presetSprint),
                () -> PvpConfig.presetSprint, () -> PvpConfig.presetSprint = !PvpConfig.presetSprint);
        y = row(x, y, () -> "Preset PvP: Fullbright " + onOff(PvpConfig.presetFullbright),
                () -> PvpConfig.presetFullbright, () -> PvpConfig.presetFullbright = !PvpConfig.presetFullbright);

        addDrawableChild(new ThemedButton(x, y + 10, W, H, () -> "Kembali", () -> false,
                () -> this.client.setScreen(parent)));
    }

    private int row(int x, int y, Supplier<String> label, BooleanSupplier active, Runnable action) {
        addDrawableChild(new ThemedButton(x, y, W, H, label, active, action));
        return y + H + GAP;
    }

    @Override
    public void renderBackground(DrawContext ctx, int mouseX, int mouseY, float delta) {
        ctx.fill(0, 0, width, height, Theme.BACKDROP);

        int panelX = (width - W) / 2 - 10;
        int panelH = 50 + ROWS * (H + GAP) + H + 24;
        ctx.fill(panelX, 40, panelX + W + 20, 40 + panelH, Theme.PANEL);
        ctx.fill(panelX, 40, panelX + W + 20, 42, Theme.RED);

        Text title = Text.literal("PVP SETTINGS").formatted(Formatting.BOLD);
        ctx.drawTextWithShadow(textRenderer, title, (width - textRenderer.getWidth(title)) / 2, 50, Theme.RED);
        Text sub = Text.literal("Klik tombol untuk mengubah");
        ctx.drawTextWithShadow(textRenderer, sub, (width - textRenderer.getWidth(sub)) / 2, 58, Theme.TEXT_DIM);
    }

    @Override
    public void close() {
        this.client.setScreen(parent);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
