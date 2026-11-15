package com.selyux.client.module;

import com.selyux.client.module.impl.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        register(new SprintModule());
        register(new FullbrightModule());
        register(new ZoomModule());
        register(new WatermarkModule());
        register(new FpsModule());
        register(new CoordinatesModule());
        register(new ArrayListModule(this));

        // Aktif secara default
        get(WatermarkModule.class).setEnabled(true);
        get(FpsModule.class).setEnabled(true);
        get(ArrayListModule.class).setEnabled(true);
    }

    private void register(Module module) {
        modules.add(module);
    }

    public List<Module> all() {
        return Collections.unmodifiableList(modules);
    }

    public List<Module> byCategory(Category category) {
        List<Module> result = new ArrayList<>();
        for (Module m : modules) {
            if (m.getCategory() == category) result.add(m);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public <T extends Module> T get(Class<T> type) {
        for (Module m : modules) {
            if (type.isInstance(m)) return (T) m;
        }
        throw new IllegalArgumentException("Module tidak ditemukan: " + type);
    }

    public void tick(MinecraftClient mc) {
        for (Module m : modules) {
            if (m.isEnabled()) m.onTick(mc);
        }
    }

    public void renderHud(DrawContext context, MinecraftClient mc) {
        if (mc.options.hudHidden || mc.player == null) return;
        for (Module m : modules) {
            if (m.isEnabled()) m.renderHud(context, mc);
        }
    }
}
