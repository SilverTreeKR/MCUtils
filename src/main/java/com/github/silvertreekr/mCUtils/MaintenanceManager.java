package com.github.silvertreekr.mCUtils;

import org.bukkit.plugin.java.JavaPlugin;

public class MaintenanceManager {
    private boolean isEnabled = false;

    public boolean getStatus() {
        return isEnabled;
    }

    public void setStatus(boolean status) {
        isEnabled = status;
    }

    public void readConfig(JavaPlugin plugin) {
        isEnabled = plugin.getConfig().getBoolean("maintenace");
    }
}

