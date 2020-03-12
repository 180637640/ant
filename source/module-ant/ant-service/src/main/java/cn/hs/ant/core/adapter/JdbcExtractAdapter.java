package cn.hs.ant.core.adapter;

import cn.hs.ant.bean.MetaData;
import cn.hs.ant.core.OnItemReadListener;
import cn.hs.ant.core.adapter.dialect.*;
import cn.hs.ant.core.endpoint.Endpoint;
import cn.hs.ant.core.endpoint.EndpointFactory;
import cn.hs.ant.core.endpoint.JdbcEndpoint;
import cn.hs.util.UF;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static cn.hs.ant.bean.AntDatabaseProperties.QUERY_SQL;
import static cn.hs.ant.bean.AntDatabaseProperties.SOURCE_ENDPOINT_DATASOURCE_PREFIX;
import static cn.hs.ant.bean.AntProperties.ANT_EXTRACT;
import static cn.hs.ant.bean.TerminalTypes.*;

/**
 * Jdbc抽取适配器
 * @author swt
 */
public class JdbcExtractAdapter implements ExtractAdapter {
    private static Log log = LogFactory.getLog(JdbcExtractAdapter.class);
    private JdbcEndpoint endpoint;
    private String querySql;
    private Dialect dialect;

    /** 数据更新监听 */
    private OnItemReadListener onItemReadListener;

    public JdbcExtractAdapter(Properties properties) {
        String extract = properties.getProperty(ANT_EXTRACT);
        endpoint = (JdbcEndpoint) EndpointFactory.getDataTerminal(properties, extract, SOURCE_ENDPOINT_DATASOURCE_PREFIX);
        querySql = properties.getProperty(QUERY_SQL);

        // 抽取适配器
        extract = UF.toString(extract).toLowerCase();
        switch (extract) {
            case MYSQL:{
                dialect = new MySqlDialect();
                break;
            }
            case ORACLE:{
                dialect = new OracleDialect();
                break;
            }
            case POSTGRESQL:{
                dialect = new PostgreSQLDialect();
                break;
            }
            case DB2:{
                dialect = new Db2Dialect();
                break;
            }
            case SQLSERVER:{
                dialect = new SqlServer2012Dialect();
                break;
            }
            default:
        }

    }

    @Override
    public void setEndpoint(Endpoint endpoint) {
        this.endpoint = (JdbcEndpoint) endpoint;
    }

    @Override
    public void setOnItemReadListener(OnItemReadListener onItemReadListener) {
        this.onItemReadListener = onItemReadListener;
    }

    @Override
    public void extract() {
        if(null == dialect || StringUtils.isBlank(querySql)) {
            return;
        }
        int pageNum = 0;
        int pageSize = 100;
        while (query(pageSize, pageNum) > 0) {
            pageNum++;
        }
    }

    /**
     * 分页查询
     * @param pageSize  每页记录数
     * @param pageNum   页码
     * @return          行数
     */
    private int query(int pageSize, int pageNum) {
        int rows = 0;
        try (Connection connection = endpoint.getDataSource().getConnection()){
            String sql = dialect.getPageSql(querySql, pageSize, pageNum);
            log.debug(String.format("执行 %s ", sql));
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            ResultSetMetaData resultSetMetaData = statement.getMetaData();
            int count = resultSetMetaData.getColumnCount();

            while (rs.next()) {
                rows++;
                // 更新数据
                if(null != onItemReadListener) {
                    Map<String, MetaData> metaDataMap = new HashMap<>(count);
                    for (int i = 1; i <= count; i++) {
                        MetaData metaData = new MetaData();
                        metaData.setColumnLabel(resultSetMetaData.getColumnLabel(i));
                        metaData.setColumnName(resultSetMetaData.getColumnName(i));
                        metaData.setColumnType(resultSetMetaData.getColumnType(i));
                        metaData.setColumnTypeName(resultSetMetaData.getColumnTypeName(i));
                        metaData.setColumnValue(rs.getObject(i));

                        metaDataMap.put(metaData.getColumnLabel().toUpperCase(), metaData);
                    }

                    onItemReadListener.itemRead(metaDataMap);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return rows;
    }

}
