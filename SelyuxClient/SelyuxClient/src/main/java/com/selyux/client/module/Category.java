package com.selyux.client.module;

public enum Category {
    MOVEMENT("Movement"),
    RENDER("Render"),
    HUD("HUD");

    public final String label;

    Category(String label) {
        this.label = label;
    }
}
