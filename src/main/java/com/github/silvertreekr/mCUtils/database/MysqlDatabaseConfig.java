package com.github.silvertreekr.mCUtils.database;

import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MysqlDatabaseConfig {
    private final String address;
    private final String username;
    private final String password;
    private final String database;
    private final Map<String, Object> properties;

    public static @NotNull MysqlDatabaseConfig fromBukkitConfig(ConfigurationSection config) throws IllegalArgumentException {
        String address = config.getString("address");
        if (address == null) {
            throw new IllegalArgumentException("Mysql address is null");
        }

        String username = config.getString("username");
        if (username == null) {
            throw new IllegalArgumentException("Mysql username is null");
        }

        String password = config.getString("password");
        if (password == null) {
            throw new IllegalArgumentException("Mysql password is null");
        }

        String database = config.getString("database");
        if (database == null) {
            throw new IllegalArgumentException("Mysql database is null");
        }

        ConfigurationSection propertiesSection = config.getConfigurationSection("properties");
        Map<String, Object> properties = propertiesSection != null ? propertiesSection.getValues(false) : new HashMap<>();

        return new MysqlDatabaseConfig(address, username, password, database, properties);
    }

    private MysqlDatabaseConfig(@NotNull String address, @NotNull String username, @NotNull String password, @NotNull String database, @NotNull Map<String, Object> properties) {
        this.address = address;
        this.username = username;
        this.password = password;
        this.database = database;
        this.properties = properties;
    }

    public @NotNull String getAddress() {
        return address;
    }

    public @NotNull String getUsername() {
        return username;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public @NotNull String getDatabase() {
        return database;
    }

    public @NotNull Map<String, Object> getProperties() {
        return properties;
    }
}
