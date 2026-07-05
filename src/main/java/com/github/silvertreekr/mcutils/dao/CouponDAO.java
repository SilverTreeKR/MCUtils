package com.github.silvertreekr.mcutils.dao;

import com.github.silvertreekr.mcutils.database.MysqlDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class CouponDAO {
    private final MysqlDatabase database;

    public CouponDAO(MysqlDatabase database) {
        this.database = database;
    }

    public CompletableFuture<Void> initialize() {
        return database.runAsync(connection -> {
           try {
               Statement statement = connection.createStatement();
               statement.executeUpdate("""
                       CREATE TABLE IF NOT EXISTS user_coupon (
                       uuid VARCHAR(36) NOT NULL,
                       coupon VARCHAR(16) NOT NULL,
                       PRIMARY KEY (uuid, coupon)
                       ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
                       """);
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
        });
    }
    public CompletableFuture<Set<String>> getCoupons(UUID uuid) {
        return database.supplyAsync(connection -> {
            try {
                Set<String> coupons = new HashSet<>();
                String sql = "SELECT * FROM user_coupon WHERE uuid = ?;";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, uuid.toString());
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String coupon = resultSet.getString("coupon");
                   coupons.add(coupon);
                }
                return coupons;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public CompletableFuture<Void> setCoupon(UUID uuid, String coupon) {
        return database.runAsync(connection -> {
           String sql = "INSERT IGNORE INTO user_coupon(uuid, coupon) VALUES (?, ?);";
           try (PreparedStatement statement = connection.prepareStatement(sql)) {
               statement.setString(1, uuid.toString());
               statement.setString(2, coupon);
               statement.addBatch();
               statement.executeBatch();
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
        });
    }
}
