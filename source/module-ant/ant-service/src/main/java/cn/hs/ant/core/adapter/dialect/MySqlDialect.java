package cn.hs.ant.core.adapter.dialect;

public class MySqlDialect implements Dialect {
    @Override
    public String getPageSql(String sql, int pageSize, int pageNum) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(sql);
        sqlBuilder.append(" LIMIT %d, %d ");

        int offset = pageNum * pageSize;
        return String.format(sqlBuilder.toString(), offset, pageSize);
    }
}
