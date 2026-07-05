package com.github.silvertreekr.mcutils;

import com.github.silvertreekr.mcutils.commands.MaintenanceCommand;
import com.github.silvertreekr.mcutils.dao.CouponDAO;
import com.github.silvertreekr.mcutils.dao.CouponManager;
import com.github.silvertreekr.mcutils.database.MysqlDatabase;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;


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
    public @NotNull CouponManager getCouponManager() {
        return couponManager;
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

        // Initialize CouponDAO & CouponManager
        CouponDAO couponDAO = new CouponDAO(mysqlDatabase);
        couponDAO.initialize();

        couponManager = new CouponManager(couponDAO);

        // Register EventListener
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
        if (mysqlDatabase != null) {
            mysqlDatabase.shutdown();
        }
    }
}
