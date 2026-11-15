package com.selyux.client.module.impl;

import com.selyux.client.module.Category;
import com.selyux.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class SprintModule extends Module {
    public SprintModule() {
        super("Auto Sprint", "Lari otomatis saat maju", Category.MOVEMENT);
    }

    @Override
    public void onTick(MinecraftClient mc) {
        ClientPlayerEntity p = mc.player;
        if (p == null) return;
        boolean canSprint = mc.options.forwardKey.isPressed()
                && !p.isSneaking()
                && !p.horizontalCollision
                && p.getHungerManager().getFoodLevel() > 6;
        if (canSprint) p.setSprinting(true);
    }
}
