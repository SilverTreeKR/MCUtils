package com.github.silvertreekr.mCUtils.database;

import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Bukkit {@link ConfigurationSection}의 {@code database} 영역을 매핑하는 불변 설정 객체.
 *
 * <p>MySQL 접속에 필요한 주소, 사용자 이름, 비밀번호, 데이터베이스 이름과
 * 추가 JDBC 속성을 저장합니다.
 */
public class MysqlDatabaseConfig {
    /** MySQL 서버 주소. */
    private final String address;

    /** MySQL 사용자 이름. */
    private final String username;

    /** MySQL 비밀번호. */
    private final String password;

    /** 사용할 MySQL 데이터베이스 이름. */
    private final String database;

    /** JDBC에 전달할 추가 속성. */
    private final Map<String, Object> properties;

    /**
     * Bukkit 설정 섹션에서 필수 MySQL 접속 정보를 읽어 설정 객체를 생성합니다.
     *
     * @param config {@code database} 설정 섹션
     * @return 생성된 {@link MysqlDatabaseConfig} 인스턴스
     * @throws IllegalArgumentException {@code address}, {@code username}, {@code password}, {@code database} 중
     *                                  필수 값이 누락된 경우
     */
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

    /**
     * 외부에서 직접 생성할 수 없습니다.
     * {@link #fromBukkitConfig(ConfigurationSection)} 팩토리 메서드를 사용하세요.
     *
     * @param address    MySQL 서버 주소
     * @param username   MySQL 사용자 이름
     * @param password   MySQL 비밀번호
     * @param database   사용할 데이터베이스 이름
     * @param properties JDBC에 전달할 추가 속성
     */
    private MysqlDatabaseConfig(@NotNull String address, @NotNull String username, @NotNull String password, @NotNull String database, @NotNull Map<String, Object> properties) {
        this.address = address;
        this.username = username;
        this.password = password;
        this.database = database;
        this.properties = properties;
    }

    /**
     * MySQL 서버 주소를 반환합니다.
     *
     * @return MySQL 서버 주소
     */
    public @NotNull String getAddress() {
        return address;
    }

    /**
     * MySQL 사용자 이름을 반환합니다.
     *
     * @return MySQL 사용자 이름
     */
    public @NotNull String getUsername() {
        return username;
    }

    /**
     * MySQL 비밀번호를 반환합니다.
     *
     * <p>민감한 정보이므로 외부 노출에 주의하세요.
     *
     * @return MySQL 비밀번호
     */
    public @NotNull String getPassword() {
        return password;
    }

    /**
     * 사용할 데이터베이스 이름을 반환합니다.
     *
     * @return 데이터베이스 이름
     */
    public @NotNull String getDatabase() {
        return database;
    }

    /**
     * JDBC에 전달할 추가 속성 맵을 반환합니다.
     *
     * @return 추가 JDBC 속성
     */
    public @NotNull Map<String, Object> getProperties() {
        return properties;
    }
}
