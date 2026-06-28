package com.github.silvertreekr.mCUtils;

import com.github.silvertreekr.mCUtils.commands.MaintenanceCommand;
import com.github.silvertreekr.mCUtils.database.MysqlDatabase;
import com.github.silvertreekr.mCUtils.events.PreventCreeperExplodeListener;
import com.github.silvertreekr.mCUtils.events.PreventEnchantingTableListener;
import com.github.silvertreekr.mCUtils.events.PreventLoginWhileMaintenanceListner;
import com.github.silvertreekr.mCUtils.events.PreventVillagerTradeListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class MCUtils extends JavaPlugin {

    private static MaintenanceManager maintenanceManager = new MaintenanceManager();
    public static MaintenanceManager getMaintenanceManager() { return maintenanceManager; }

    private static MCUtils instance;
    public static MCUtils getInstance() {
        return instance;
    }

    private static MysqlDatabase mysqlDatabase;

    public static @NotNull MysqlDatabase getMysqlDatabase() {
        return mysqlDatabase;
    }

    @Override
    public void onEnable() {
        new PreventVillagerTradeListener(this);
        new PreventEnchantingTableListener(this);
        new PreventCreeperExplodeListener(this);
        new PreventLoginWhileMaintenanceListner(this);
        new MaintenanceCommand(this);

        saveDefaultConfig();
        reloadConfig();
        try {
            mysqlDatabase = MysqlDatabase.initialize(this);
        } catch (Exception e) {
            getSLF4JLogger().error("Could not initialize MySQL database.", e);
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        maintenanceManager.readConfig(this);
    }

    @Override
    public void onDisable() {
        maintenanceManager.saveConfig(this);
    }

    @Override
    public void onLoad() {
        instance = this;
    }
}
