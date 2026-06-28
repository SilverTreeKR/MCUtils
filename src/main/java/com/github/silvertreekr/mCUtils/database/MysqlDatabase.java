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

/**
 * Bukkit 플러그인을 위한 MySQL 데이터베이스 연결 및 비동기 쿼리 실행 관리자.
 *
 * <p>플러그인의 {@code config.yml}에 정의된 {@code database} 설정을 기반으로
 * MySQL에 연결하며, virtual thread 기반 executor에서 비동기 작업을 실행합니다.
 */
public class MysqlDatabase {
    /** SLF4J 로거. */
    private static final Logger logger = LoggerFactory.getLogger(MysqlDatabase.class);

    /** Virtual thread 기반 비동기 작업 실행기. */
    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    /** MySQL 접속 설정. */
    private final MysqlDatabaseConfig config;

    /**
     * 플러그인 설정에서 {@code database} 섹션을 읽어 {@link MysqlDatabase}를 초기화합니다.
     *
     * @param plugin Bukkit 플러그인 인스턴스
     * @return 초기화된 {@link MysqlDatabase} 인스턴스
     * @throws NullPointerException {@code database} 설정 섹션이 없는 경우
     */
    public static @NotNull MysqlDatabase initialize(@NotNull JavaPlugin plugin) {
        ConfigurationSection bukkitConfig = plugin.getConfig().getConfigurationSection("database");
        if (bukkitConfig == null) {
            throw new NullPointerException("database config is null");
        }

        MysqlDatabaseConfig databaseConfig = MysqlDatabaseConfig.fromBukkitConfig(bukkitConfig);
        return new MysqlDatabase(databaseConfig);
    }

    /**
     * 외부에서 직접 생성할 수 없습니다.
     * {@link #initialize(JavaPlugin)} 팩토리 메서드를 사용하세요.
     *
     * @param config MySQL 접속 설정
     */
    private MysqlDatabase(@NotNull MysqlDatabaseConfig config) {
        this.config = config;
    }

    /**
     * 현재 설정으로 새 MySQL {@link Connection}을 생성합니다.
     *
     * @return 생성된 MySQL 연결
     * @throws SQLException 연결 생성 중 오류가 발생한 경우
     * @throws RuntimeException MySQL 드라이버 클래스를 찾을 수 없는 경우
     */
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

    /**
     * 연결 {@link Connection}을 소비하는 작업을 비동기로 실행합니다.
     *
     * <p>작업 중 {@link SQLException}이 발생하면 로그로 기록되며, 예외는 전파되지 않습니다.
     *
     * @param consumer 실행할 연결 소비 작업
     * @return 완료 시점을 나타내는 {@link CompletableFuture}
     */
    public @NotNull CompletableFuture<Void> runAsync(Consumer<Connection> consumer) {
        return CompletableFuture.runAsync(() -> {
            try (Connection connection = connect()) {
                consumer.accept(connection);
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }, executor);
    }

    /**
     * 연결 {@link Connection}을 받아 결과를 반환하는 작업을 비동기로 실행합니다.
     *
     * <p>작업 중 {@link SQLException}이 발생하면 {@link RuntimeException}으로 감싸 다시 던집니다.
     *
     * @param <T> 반환 값 타입
     * @param function 실행할 연결 처리 함수
     * @return 작업 완료 후 결과를 포함하는 {@link CompletableFuture}
     */
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
