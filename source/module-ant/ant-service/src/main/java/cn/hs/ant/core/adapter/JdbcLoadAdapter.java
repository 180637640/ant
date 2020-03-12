package cn.hs.ant.core.adapter;

import cn.hs.ant.bean.ColumnMapping;
import cn.hs.ant.bean.MetaData;
import cn.hs.ant.core.endpoint.EndpointFactory;
import cn.hs.ant.core.endpoint.JdbcEndpoint;
import cn.hs.config.SQLTypesUtils;
import cn.hs.util.UF;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static cn.hs.ant.bean.AntDatabaseProperties.*;
import static cn.hs.ant.bean.TransformTypes.*;

/**
 * JDBC加载适配器
 * @author swt
 */
public class JdbcLoadAdapter implements LoadAdapter {
    private static Log log = LogFactory.getLog(JdbcLoadAdapter.class);
    private JdbcEndpoint endpoint;
    private String tableName;
    private List<ColumnMapping> columnMappingList = new ArrayList<>();

    public JdbcLoadAdapter(Properties properties) {
        String load = properties.getProperty(ANT_LOAD);
        this.endpoint = (JdbcEndpoint) EndpointFactory.getDataTerminal(properties, load, TARGET_ENDPOINT_DATASOURCE_PREFIX);
        tableName = getProperty(properties, TARGET_ENDPOINT_TABLE_NAME);

        for (Object key : properties.keySet()) {
            String label = key.toString();
            if(!label.startsWith(DATASOURCE_SOURCE_COLUMN_PREFIX)) {
                continue;
            }
            // 列转换。格式：key=目标列类型|转换方式（源列 SOURCE_COLUMN、默认值 DEFAULT_VALUE、SQL语句 SQL、 ）|源列名
            String value = properties.getProperty(label);
            if(StringUtils.isBlank(value)) {
                continue;
            }
            String[] val = StringUtils.split(value, '|');
            if(null == val || val.length != 3) {
                continue;
            }
            label = label.substring(DATASOURCE_SOURCE_COLUMN_PREFIX.length());

            ColumnMapping mapping = new ColumnMapping();
            mapping.setColumnLabel(label);
            mapping.setColumnType(SQLTypesUtils.getColumnType(val[0]));
            mapping.setColumnTypeName(val[0]);
            mapping.setTransformType(val[1]);
            mapping.setSource(val[2]);
            columnMappingList.add(mapping);
        }
    }

    /**
     * 获取属性值
     * @param properties    属性
     * @param key           健
     * @return              值
     */
    private String getProperty(Properties properties, String key) {
        String value = properties.getProperty(key);
        return UF.toString(value);
    }

    @Override
    public void itemRead(Map<String, MetaData> metaDataMap) {
        try {
            if(null == columnMappingList || columnMappingList.isEmpty()) {
                // 如果列不指定，以数据源列为准
                metaDataMap.forEach((s, metaData) -> {
                    ColumnMapping mapping = new ColumnMapping();
                    mapping.setColumnLabel(metaData.getColumnLabel());
                    mapping.setColumnType(SQLTypesUtils.getColumnType(metaData.getColumnTypeName()));
                    mapping.setColumnTypeName(metaData.getColumnTypeName());
                    mapping.setTransformType(SOURCE_COLUMN);
                    mapping.setSource(metaData.getColumnLabel());
                    columnMappingList.add(mapping);
                });
            }
            int columnCount = columnMappingList.size();
            List<String> valueNameList = new ArrayList<>(columnCount);
            List<String> columnNameList = new ArrayList<>(columnCount);
            List<String> updateColumnNameList = new ArrayList<>(columnCount);

            for (ColumnMapping columnMapping : columnMappingList) {
                columnNameList.add(columnMapping.getColumnLabel());
                valueNameList.add("?");
                updateColumnNameList.add(String.format(" %s=VALUES(%s)", columnMapping.getColumnLabel(), columnMapping.getColumnLabel()));
            }

            try (Connection connection = endpoint.getDataSource().getConnection()){
                String sql = String.format("INSERT INTO %s (%s) VALUES (%s) ON DUPLICATE KEY UPDATE %s",
                        tableName,
                        StringUtils.join(columnNameList, ","),
                        StringUtils.join(valueNameList, ","),
                        StringUtils.join(updateColumnNameList, ","));

                log.debug(String.format("执行 %s ", sql));
                PreparedStatement statement = connection.prepareStatement(sql);
                int parameterIndex = 1;
                for (ColumnMapping columnMapping : columnMappingList) {
                    switch (columnMapping.getTransformType()) {
                        case SOURCE_COLUMN :{
                            MetaData metaData = metaDataMap.get(columnMapping.getSource().toUpperCase());
                            if(null == metaData) {
                                log.error(String.format("列[%s]配置失败", columnMapping.getColumnLabel()));
                            }
                            Object value = (null == metaData) ? null : metaData.getColumnValue();
                            statement.setObject(parameterIndex, value, columnMapping.getColumnType());
                            break;
                        }
                        case DEFAULT_VALUE :
                        case SQL :{
                            statement.setObject(parameterIndex, columnMapping.getSource());
                            break;
                        }
                        default: {
                            statement.setObject(parameterIndex, null);
                        }
                    }
                    parameterIndex++;
                }
                int rows = statement.executeUpdate();
                log.debug(String.format("操作影响行数 %d ", rows));
            } catch (SQLException e) {
                log.error(e);
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

}
