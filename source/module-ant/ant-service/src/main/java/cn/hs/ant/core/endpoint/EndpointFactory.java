package cn.hs.ant.core.endpoint;

import cn.hs.config.HikariConfigurationUtil;
import com.zaxxer.hikari.HikariConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import static cn.hs.ant.bean.TerminalTypes.*;

/**
 * 数据终端工厂
 * @author swt
 */
public class EndpointFactory {
    private static Log log = LogFactory.getLog(EndpointFactory.class);
    private static Map<String, Endpoint> TERMINAL_MAP = new ConcurrentHashMap<>();

    /**
     * 获取数据节点
     * @param properties     属性
     * @param prefix         前缀
     * @return               终端
     */
    public static Endpoint getDataTerminal(Properties properties, String terminal, String prefix) {
        Endpoint endpoint = null;
        String key;
        if(StringUtils.equalsAnyIgnoreCase(terminal, MYSQL, ORACLE, POSTGRESQL, DB2, SQLSERVER)) {
            HikariConfig config = HikariConfigurationUtil.loadConfiguration(properties, prefix);
            key = String.format("%s%s", config.getJdbcUrl(), config.getUsername());
            if(TERMINAL_MAP.containsKey(key)) {
                return TERMINAL_MAP.get(key);
            }
            endpoint = new JdbcEndpoint(config);
            TERMINAL_MAP.put(key, endpoint);
        }
        return endpoint;
    }

}
