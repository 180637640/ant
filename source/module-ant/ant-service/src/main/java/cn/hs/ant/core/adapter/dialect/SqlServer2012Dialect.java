package cn.hs.ant.core.adapter.dialect;

public class SqlServer2012Dialect implements Dialect {
    @Override
    public String getPageSql(String sql, int pageSize, int pageNum) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(sql);
        sqlBuilder.append(" OFFSET %d ROWS FETCH NEXT %d ROWS ONLY ");

        int offset = pageNum * pageSize;
        return String.format(sqlBuilder.toString(), offset, pageSize);
    }
}
