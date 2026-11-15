package com.selyux.client.module.impl;

import com.selyux.client.module.Category;
import com.selyux.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

/** Terang penuh lewat efek night vision visual di sisi client. */
public class FullbrightModule extends Module {
    public FullbrightModule() {
        super("Fullbright", "Terang di mana saja", Category.RENDER);
    }

    @Override
    public void onTick(MinecraftClient mc) {
        if (mc.player == null) return;
        mc.player.addStatusEffect(new StatusEffectInstance(
                StatusEffects.NIGHT_VISION, 400, 0, false, false, false));
    }

    @Override
    protected void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null) mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
    }
}
