package fr.arthurdanjou.springboottraining.database.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import fr.arthurdanjou.springboottraining.SpringBoot;
import fr.arthurdanjou.springboottraining.config.file.MySQLConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

public class MySQLConnector {

    private SpringBoot main;
    private HikariDataSource dataSource;

    public MySQLConnector(SpringBoot main, MySQLConfig mySQLConfig) {
        this.main = main;
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("mysql://" + mySQLConfig.getHost() + ":" + mySQLConfig.getPort() + "/" + mySQLConfig.getDatabase());
        config.setUsername(mySQLConfig.getUser());
        config.setPassword(mySQLConfig.getPassword());
        config.setMinimumIdle(2);
        config.setMaximumPoolSize(10);
        config.setPoolName("Arthur SQL Pool");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");

        this.dataSource = new HikariDataSource(config);
    }

    public boolean isConnected() {
        return getConnection() != null;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            main.getLogger().log(Level.SEVERE, "Impossible de récupérer la connection !");
            e.printStackTrace();
        }
        return null;
    }

    public void shutdown() {
        if (isConnected()) {
            dataSource.close();
        }
    }

}
