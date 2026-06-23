package com.github.silvertreekr.mCUtils;

import com.github.silvertreekr.mCUtils.commands.MaintenanceCommand;
import com.github.silvertreekr.mCUtils.events.PreventCreeperExplodeListener;
import com.github.silvertreekr.mCUtils.events.PreventEnchantingTableListener;
import com.github.silvertreekr.mCUtils.events.PreventLoginWhileMaintenanceListner;
import com.github.silvertreekr.mCUtils.events.PreventVillagerTradeListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCUtils extends JavaPlugin {

    private static MaintenanceManager maintenanceManager = new MaintenanceManager();
    public static MaintenanceManager getMaintenanceManager() { return maintenanceManager; }

    @Override
    public void onEnable() {
        new PreventVillagerTradeListener(this);
        new PreventEnchantingTableListener(this);
        new PreventCreeperExplodeListener(this);
        new PreventLoginWhileMaintenanceListner(this);
        new MaintenanceCommand(this);

        reloadConfig();
        maintenanceManager.readConfig(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
