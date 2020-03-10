package cn.hs.ant.service;

import cn.hs.ant.bean.TerminalTypes;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据终端工厂
 * @author swt
 */
public class DataTerminalFactory {
    private static Log log = LogFactory.getLog(DataTerminalFactory.class);
    private static Map<Integer, DataTerminal> TERMINAL_MAP = new ConcurrentHashMap<>();

    /**
     * 获取数据节点
     * @param terminalTypes     终端类型
     * @param url               连接地址
     * @param username          用户名
     * @param password          密码
     * @return                  终端
     */
    public static DataTerminal getDataTerminal(String terminalTypes, String url, String username, String password) {
        int hashCode = String.format("%s-%s-%s", url, username, password).hashCode();
        if(TERMINAL_MAP.containsKey(hashCode)) {
            return TERMINAL_MAP.get(hashCode);
        }
        // 创建
        DataTerminal dataTerminal = null;
        if(StringUtils.equalsAnyIgnoreCase(terminalTypes, TerminalTypes.MYSQL)) {
            dataTerminal = new MysqlDataTerminal(url, username, password);
        } else if(StringUtils.equalsAnyIgnoreCase(terminalTypes, TerminalTypes.ORACLE)) {
            dataTerminal = new OracleDataTerminal(url, username, password);
        }
        TERMINAL_MAP.put(hashCode, dataTerminal);
        log.info("DataTerminalFactory.getDataTerminal terminalTypes = [" + terminalTypes + "], url = [" + url + "], username = [" + username + "], password = [" + password + "]");
        return dataTerminal;
    }

}
