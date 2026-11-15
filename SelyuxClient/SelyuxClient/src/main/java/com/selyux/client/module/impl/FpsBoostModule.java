package com.selyux.client.module.impl;

import com.selyux.client.module.Category;
import com.selyux.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.ParticlesMode;

/** Menurunkan pengaturan grafis berat; nilai awal dikembalikan saat dimatikan. */
public class FpsBoostModule extends Module {
    private boolean saved;
    private int view;
    private int sim;
    private int blend;
    private double entityDist;
    private boolean shadows;
    private boolean smooth;
    private ParticlesMode particles;

    public FpsBoostModule() {
        super("FPS Boost", "Turunkan grafis untuk FPS tinggi", Category.RENDER);
    }

    @Override
    protected void onEnable() {
        GameOptions o = MinecraftClient.getInstance().options;
        view = o.getViewDistance().getValue();
        sim = o.getSimulationDistance().getValue();
        blend = o.getBiomeBlendRadius().getValue();
        entityDist = o.getEntityDistanceScaling().getValue();
        shadows = o.getEntityShadows().getValue();
        smooth = o.getAo().getValue();
        particles = o.getParticles().getValue();
        saved = true;

        o.getViewDistance().setValue(Math.min(view, 8));
        o.getSimulationDistance().setValue(Math.min(sim, 5));
        o.getBiomeBlendRadius().setValue(0);
        o.getEntityDistanceScaling().setValue(0.5);
        o.getEntityShadows().setValue(false);
        o.getAo().setValue(false);
        o.getParticles().setValue(ParticlesMode.MINIMAL);
    }

    @Override
    protected void onDisable() {
        if (!saved) return;
        GameOptions o = MinecraftClient.getInstance().options;
        o.getViewDistance().setValue(view);
        o.getSimulationDistance().setValue(sim);
        o.getBiomeBlendRadius().setValue(blend);
        o.getEntityDistanceScaling().setValue(entityDist);
        o.getEntityShadows().setValue(shadows);
        o.getAo().setValue(smooth);
        o.getParticles().setValue(particles);
        saved = false;
    }
}
