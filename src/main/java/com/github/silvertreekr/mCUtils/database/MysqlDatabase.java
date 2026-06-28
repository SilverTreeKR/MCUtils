package com.github.silvertreekr.mCUtils.database;

import com.mysql.cj.jdbc.Driver;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;

public class MysqlDatabase {
    private static final Logger logger = LoggerFactory.getLogger(MysqlDatabase.class);
    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
    private final MysqlDatabaseConfig config;

    public static @NotNull MysqlDatabase initialize(@NotNull JavaPlugin plugin) {
        ConfigurationSection bukkitConfig = plugin.getConfig().getConfigurationSection("database");
        if (bukkitConfig == null) {
            throw new NullPointerException("database config is null");
        }

        MysqlDatabaseConfig databaseConfig = MysqlDatabaseConfig.fromBukkitConfig(bukkitConfig);
        return new MysqlDatabase(databaseConfig);
    }

    private MysqlDatabase(@NotNull MysqlDatabaseConfig config) {
        this.config = config;
    }

    public @NotNull Connection connect() throws SQLException {
        try {
            Class.forName(Driver.class.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = String.format("jdbc:mysql://%s/%s", config.getAddress(), config.getDatabase());
        Properties properties = new Properties();
        properties.put("user", config.getUsername());
        properties.put("password", config.getPassword());
        properties.putAll(config.getProperties());
        return DriverManager.getConnection(url, properties);
    }

    public @NotNull CompletableFuture<Void> runAsync(Consumer<Connection> consumer) {
        return CompletableFuture.runAsync(() -> {
            try (Connection connection = connect()) {
                consumer.accept(connection);
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }, executor);
    }

    public <T> @NotNull CompletableFuture<T> supplyAsync(Function<Connection, T> function) {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection connection = connect()) {
                return function.apply(connection);
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }, executor);
    }
}
