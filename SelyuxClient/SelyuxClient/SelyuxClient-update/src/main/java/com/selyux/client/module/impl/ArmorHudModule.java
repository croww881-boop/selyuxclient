package com.selyux.client.module.impl;

import com.selyux.client.PvpConfig;
import com.selyux.client.Theme;
import com.selyux.client.module.Category;
import com.selyux.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class ArmorHudModule extends Module {
    private static final EquipmentSlot[] SLOTS = {
            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET
    };

    public ArmorHudModule() {
        super("Armor HUD", "Tampilkan armor dan durability", Category.PVP);
    }

    @Override
    public void renderHud(DrawContext ctx, MinecraftClient mc) {
        if (mc.player == null) return;
        int x = 6;
        int y0 = mc.getWindow().getScaledHeight() / 2 - 38;
        for (int i = 0; i < SLOTS.length; i++) {
            ItemStack stack = mc.player.getEquippedStack(SLOTS[i]);
            if (stack.isEmpty()) continue;
            int y = y0 + i * 18;
            ctx.drawItem(stack, x, y);
            if (PvpConfig.armorDurability && stack.isDamageable()) {
                int max = stack.getMaxDamage();
                int pct = (max - stack.getDamage()) * 100 / max;
                ctx.drawTextWithShadow(mc.textRenderer, pct + "%", x + 20, y + 4,
                        pct < 25 ? Theme.RED : Theme.TEXT);
            }
        }
    }
}
