package com.selyux.client.module.impl;

import com.selyux.client.PvpConfig;
import com.selyux.client.module.Category;
import com.selyux.client.module.Module;
import com.selyux.client.module.ModuleManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Preset sekali klik: menyalakan semua tampilan PvP, lalu mengembalikan keadaan awal saat dimatikan. */
public class PvpModeModule extends Module {
    private final ModuleManager manager;
    private final Map<Module, Boolean> before = new LinkedHashMap<>();

    public PvpModeModule(ModuleManager manager) {
        super("PvP Mode", "Aktifkan preset tampilan PvP", Category.PVP);
        this.manager = manager;
    }

    private List<Module> preset() {
        List<Module> list = new ArrayList<>();
        list.add(manager.get(KeystrokesModule.class));
        list.add(manager.get(CpsModule.class));
        list.add(manager.get(ArmorHudModule.class));
        list.add(manager.get(PotionHudModule.class));
        if (PvpConfig.presetSprint) list.add(manager.get(SprintModule.class));
        if (PvpConfig.presetFullbright) list.add(manager.get(FullbrightModule.class));
        return list;
    }

    @Override
    protected void onEnable() {
        before.clear();
        for (Module m : preset()) {
            before.put(m, m.isEnabled());
            m.setEnabled(true);
        }
    }

    @Override
    protected void onDisable() {
        for (Map.Entry<Module, Boolean> e : before.entrySet()) {
            e.getKey().setEnabled(e.getValue());
        }
        before.clear();
    }
}
