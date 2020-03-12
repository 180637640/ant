package cn.hs.ant.core.adapter.dialect;

public class InfomixDialect implements Dialect {
    @Override
    public String getPageSql(String sql, int pageSize, int pageNum) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT ");
        sqlBuilder.append(" SKIP %d ");
        sqlBuilder.append(" FIRST %d ");
        sqlBuilder.append(" * FROM ( ");
        sqlBuilder.append(sql);
        sqlBuilder.append(" ) TEMP_T ");

        int offset = pageNum * pageSize;
        return String.format(sqlBuilder.toString(), offset, pageSize);
    }
}
