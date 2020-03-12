package cn.hs.ant.core.endpoint;

import cn.hs.config.HikariConfigurationUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Properties;

/**
 * JDBC数据源
 * @author swt
 */
public class JdbcEndpoint implements Endpoint {
    private static Log log = LogFactory.getLog(JdbcEndpoint.class);
    private HikariDataSource dataSource;

    public HikariDataSource getDataSource() {
        return dataSource;
    }

    public JdbcEndpoint(Properties properties, String prefix) {
        dataSource = new HikariDataSource(HikariConfigurationUtil.loadConfiguration(properties, prefix));
    }

    public JdbcEndpoint(HikariConfig config) {
        dataSource = new HikariDataSource(config);
    }

}
