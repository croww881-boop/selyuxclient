package com.selyux.client.module.impl;

import com.selyux.client.PvpConfig;
import com.selyux.client.Theme;
import com.selyux.client.module.Category;
import com.selyux.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayDeque;

/** Penghitung klik per detik (hanya menampilkan, tidak mengubah apa pun di game). */
public class CpsModule extends Module {
    private final ArrayDeque<Long> left = new ArrayDeque<>();
    private final ArrayDeque<Long> right = new ArrayDeque<>();
    private boolean wasLeft;
    private boolean wasRight;

    public CpsModule() {
        super("CPS", "Tampilkan klik per detik", Category.PVP);
    }

    @Override
    public void renderHud(DrawContext ctx, MinecraftClient mc) {
        long window = mc.getWindow().getHandle();
        long now = System.currentTimeMillis();
        boolean inGame = mc.currentScreen == null;

        boolean l = inGame && GLFW.glfwGetMouseButton(window, GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS;
        boolean r = inGame && GLFW.glfwGetMouseButton(window, GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_PRESS;
        if (l && !wasLeft) left.add(now);
        if (r && !wasRight) right.add(now);
        wasLeft = l;
        wasRight = r;
        while (!left.isEmpty() && now - left.peekFirst() > 1000) left.pollFirst();
        while (!right.isEmpty() && now - right.peekFirst() > 1000) right.pollFirst();

        String s = "CPS: " + left.size() + (PvpConfig.cpsShowRight ? " | " + right.size() : "");
        int w = mc.textRenderer.getWidth(s);
        ctx.fill(4, 36, 4 + w + 10, 49, Theme.HUD_BG);
        ctx.fill(4, 36, 6, 49, Theme.RED_DARK);
        ctx.drawTextWithShadow(mc.textRenderer, s, 10, 39, Theme.TEXT);
    }
}
