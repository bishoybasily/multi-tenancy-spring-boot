package com.gmail.bishoybasily.application.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * @author bishoybasily
 * @since 2020-12-07
 */
@RequiredArgsConstructor
public class TenantBasedDataSource implements DataSource, AutoCloseable {

    private final static Map<String, HikariDataSource> DATA_SOURCE_MAP = new HashMap<>();
    private final static ThreadLocal<String> LOCAL = new ThreadLocal<>();

    public static HikariDataSource getDataSource(String id) {
        LOCAL.set(id);
        return getDataSource();
    }

    public static HikariDataSource getDataSource() {
        String id = getId();
        if (!DATA_SOURCE_MAP.containsKey(id)) {
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setIdleTimeout(1000 * 60);
            dataSource.setInitializationFailTimeout(0);
            dataSource.setMaximumPoolSize(5);
            dataSource.setMinimumIdle(1);
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUsername("root");
            dataSource.setPassword("toor");
            dataSource.setJdbcUrl(String.format("jdbc:mysql://%s:%d/%s?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true", "127.0.0.1", 3306, id));
            DATA_SOURCE_MAP.put(id, dataSource);
        }
        return DATA_SOURCE_MAP.get(id);
    }

    private static String getId() {
        String id = LOCAL.get();
        if (ObjectUtils.isEmpty(id))
            id = "multitenancy_application";
        return id;
    }

    public static Collection<HikariDataSource> getDataSources() {
        return DATA_SOURCE_MAP.values();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String s, String s1) throws SQLException {
        return getDataSource().getConnection(s, s1);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return getDataSource().getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter printWriter) throws SQLException {
        getDataSource().setLogWriter(printWriter);
    }

    @Override
    public void setLoginTimeout(int i) throws SQLException {
        getDataSource().setLoginTimeout(i);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return getDataSource().getLoginTimeout();
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return getDataSource().unwrap(aClass);
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return getDataSource().isWrapperFor(aClass);
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return getDataSource().getParentLogger();
    }

    @Override
    public void close() {
        getDataSources().forEach(HikariDataSource::close);
    }

}
