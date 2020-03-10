package cn.hs.ant.service.adapter;

import cn.hs.config.SQLTypesUtils;
import cn.hs.ant.service.DataTerminal;
import cn.hs.ant.service.OnItemReadListener;
import cn.hs.ant.service.OracleDataTerminal;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Oracle抽取适配器
 * @author swt
 */
public class OracleExtractAdapter implements ExtractAdapter {
    private static Log log = LogFactory.getLog(OracleExtractAdapter.class);
    private OracleDataTerminal dataTerminal;
    private String querySql;
    private List<String> columnNameList;
    private List<Integer> columnTypeList;

    /** 数据更新监听 */
    private OnItemReadListener onItemReadListener;

    public OracleExtractAdapter(DataTerminal dataTerminal) {
        this.dataTerminal = (OracleDataTerminal) dataTerminal;
    }

    @Override
    public void setDataTerminal(DataTerminal dataTerminal) {
        this.dataTerminal = (OracleDataTerminal) dataTerminal;
    }

    @Override
    public void setOnItemReadListener(OnItemReadListener onItemReadListener) {
        this.onItemReadListener = onItemReadListener;
    }

    public void setQuerySql(String querySql) {
        this.querySql = querySql;
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
    public void extract() {
        int pageNum = 1;
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
        int offset = (pageNum - 1) * pageSize;
        if(offset < 0) {
            offset = 0;
        }
        int rows = 0;
        try (Connection connection = dataTerminal.getDataSource().getConnection()){

            String sql = String.format(
                    "select * from ( " +
                    "  select rownum rownum_, row_.* " +
                    "  from ( %s ) row_ " +
                    "  where rownum < ? " +
                    ") where rownum_ >= ?", querySql);
            log.info(String.format("执行 %s ", sql));

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, offset + pageSize);
            statement.setInt(2, offset);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                rows++;
                // 更新数据
                if(null != onItemReadListener) {
                    if(null == columnNameList || columnNameList.isEmpty()) {
                        continue;
                    }
                    List<Object> valueList = new ArrayList<>(columnNameList.size());
                    for (String name : columnNameList) {
                        valueList.add(rs.getObject(name));
                    }

                    onItemReadListener.itemRead(columnNameList, columnTypeList, valueList);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return rows;
    }

}
