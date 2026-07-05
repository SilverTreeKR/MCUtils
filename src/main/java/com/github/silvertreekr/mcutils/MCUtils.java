package com.github.silvertreekr.mcutils;

import com.github.silvertreekr.mcutils.commands.MaintenanceCommand;
import com.github.silvertreekr.mcutils.events.PreventCreeperExplodeListener;
import com.github.silvertreekr.mcutils.events.PreventEnchantingTableListener;
import com.github.silvertreekr.mcutils.events.PreventLoginWhileMaintenanceListner;
import com.github.silvertreekr.mcutils.events.PreventVillagerTradeListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCUtils extends JavaPlugin {
    private static MCUtils instance;
    private static MaintenanceManager maintenanceManager = new MaintenanceManager();
    private MysqlDatabase mysqlDatabase;
    private CouponManager couponManager;

    public static @NotNull MCUtils getInstance() {
        return instance;
    }
    public static MaintenanceManager getMaintenanceManager() {
        return maintenanceManager;
    }
    public @NotNull MysqlDatabase getMysqlDatabase() {
        return mysqlDatabase;
    }

    @Override
    public void onEnable() {
        instance = this;

        // Initialize Default Config
        saveDefaultConfig();
        reloadConfig();

        // Initialize the MaintenanceManager
        maintenanceManager.readConfig(this);

        // Initialize the MySQL Database
        try {
            mysqlDatabase = MysqlDatabase.initialize(this);

        } catch (Exception e) {
            getSLF4JLogger().error("Failed to initialize MySQL database", e);
            getServer().getPluginManager().disablePlugin(this);
        }

        new PreventVillagerTradeListener(this);
        new PreventEnchantingTableListener(this);
        new PreventCreeperExplodeListener(this);
        new PreventLoginWhileMaintenanceListner(this);
        new MaintenanceCommand(this);

        saveDefaultConfig();
        reloadConfig();
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
