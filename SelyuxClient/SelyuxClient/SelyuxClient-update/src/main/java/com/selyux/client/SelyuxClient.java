package com.selyux.client;

import com.selyux.client.gui.ClickGuiScreen;
import com.selyux.client.gui.SelyuxPauseScreen;
import com.selyux.client.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelyuxClient implements ClientModInitializer {
    public static final String MOD_ID = "selyuxclient";
    public static final String NAME = "Selyux Client";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public static final KeyBinding.Category CATEGORY =
            KeyBinding.Category.create(Identifier.of(MOD_ID, "main"));

    public static KeyBinding guiKey;
    public static KeyBinding zoomKey;
    private static ModuleManager modules;

    public static ModuleManager modules() {
        return modules;
    }

    @Override
    public void onInitializeClient() {
        modules = new ModuleManager();

        guiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.selyuxclient.gui", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, CATEGORY));
        zoomKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.selyuxclient.zoom", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, CATEGORY));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (guiKey.wasPressed()) {
                client.setScreen(new ClickGuiScreen());
            }
            // Ganti menu ESC bawaan dengan versi bertema Selyux
            if (client.currentScreen != null
                    && client.currentScreen.getClass() == GameMenuScreen.class) {
                client.setScreen(new SelyuxPauseScreen());
            }
            if (client.player != null) {
                modules.tick(client);
            }
        });

        HudElementRegistry.attachElementAfter(
                VanillaHudElements.MISC_OVERLAYS,
                Identifier.of(MOD_ID, "hud"),
                (context, tickCounter) -> modules.renderHud(context, MinecraftClient.getInstance()));

        LOGGER.info("{} loaded", NAME);
    }
}
