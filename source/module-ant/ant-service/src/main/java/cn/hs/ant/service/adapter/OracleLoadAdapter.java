package cn.hs.ant.service.adapter;

import cn.hs.config.SQLTypesUtils;
import cn.hs.ant.service.DataTerminal;
import cn.hs.ant.service.MysqlDataTerminal;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Oracle加载适配器
 * @author swt
 */
public class OracleLoadAdapter implements LoadAdapter {
    private static Log log = LogFactory.getLog(OracleLoadAdapter.class);
    private MysqlDataTerminal dataTerminal;
    private String tableName;
    private List<String> columnNameList;
    private List<Integer> columnTypeList;

    public OracleLoadAdapter(DataTerminal dataTerminal) {
        this.dataTerminal = (MysqlDataTerminal) dataTerminal;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setColumnInfo(String columnInfo) {
        if(StringUtils.isBlank(columnInfo)) {
            return;
        }
        String[] c = StringUtils.split(columnInfo, ',');
        if(null == c || c.length <= 0) {
            return;
        }
        columnNameList = new ArrayList<>(c.length);
        columnTypeList = new ArrayList<>(c.length);
        for (String val : c) {
            String[] ct = StringUtils.split(val, '|');
            columnNameList.add(ct[0]);
            if(ct.length > 1) {
                columnTypeList.add(SQLTypesUtils.getColumnType(ct[1]));
            }
        }
    }

    @Override
    public void setDataTerminal(DataTerminal dataTerminal) {
        this.dataTerminal = (MysqlDataTerminal) dataTerminal;
    }

    @Override
    public void itemRead(List<String> nameList, List<Integer> typeList, List<Object> valueList) {
        try {
            int columnCount = columnNameList.size();
            List<String> valueName = new ArrayList<>(columnCount);
            List<String> updateColumnName = new ArrayList<>(columnCount);

            for (String columnName : columnNameList) {
                valueName.add("?");
                updateColumnName.add(String.format(" %s=VALUES(%s)", columnName, columnName));
            }

            try (Connection connection = dataTerminal.getDataSource().getConnection()){
                String sql = String.format("INSERT INTO %s (%s) VALUES (%s) ON DUPLICATE KEY UPDATE %s",
                        tableName,
                        StringUtils.join(columnNameList, ","),
                        StringUtils.join(valueName, ","),
                        StringUtils.join(updateColumnName, ","));

                log.debug(String.format("执行 %s ", sql));
                PreparedStatement statement = connection.prepareStatement(sql);
                for (int i = 0; i < columnCount; i++) {
                    statement.setObject(i+1, valueList.get(i), columnTypeList.get(i));
                }
                int rows = statement.executeUpdate();
                log.debug(String.format("操作影响行数 %d ", rows));
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
