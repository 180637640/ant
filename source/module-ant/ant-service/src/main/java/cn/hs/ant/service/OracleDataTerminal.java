package cn.hs.ant.service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Oracle数据源
 * @author swt
 */
public class OracleDataTerminal implements DataTerminal {
    private static Log log = LogFactory.getLog(OracleDataTerminal.class);
    private HikariConfig config;
    private HikariDataSource dataSource;

    public HikariDataSource getDataSource() {
        return dataSource;
    }

    public OracleDataTerminal(String url, String username, String password) {
        log.info("数据源初始化 url = [" + url + "], username = [" + username + "], password = [" + password + "]");
        config = new HikariConfig();
        config.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        // 最小空闲连接数量
        config.setMinimumIdle(1);
        // 连接池最大连接数，默认是10
        config.setMaximumPoolSize(20);
        // 控制从池返回的连接的默认自动提交行为,默认值：true
        config.setAutoCommit(true);
        // 空闲连接存活最大时间，默认600000（10分钟）
        config.setIdleTimeout(600000);
        // 控制池中连接的最长生命周期，值0表示无限生命周期，默认1800000即30分钟
        config.setMaxLifetime(1800000);
        // 数据库连接超时时间,默认30秒，即30000
        config.setConnectionTimeout(30000);
        // 测试连接是否正常
        config.setConnectionTestQuery("select 1 from dual");

        dataSource = new HikariDataSource(config);
    }

}
