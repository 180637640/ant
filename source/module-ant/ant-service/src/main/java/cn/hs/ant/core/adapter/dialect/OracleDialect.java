package cn.hs.ant.core.adapter.dialect;

public class OracleDialect implements Dialect {
    @Override
    public String getPageSql(String sql, int pageSize, int pageNum) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT * FROM ( ");
        sqlBuilder.append(" SELECT TMP_PAGE.*, ROWNUM ROW_ID FROM ( ");
        sqlBuilder.append(sql);
        sqlBuilder.append(" ) TMP_PAGE)");
        sqlBuilder.append(" WHERE ROW_ID <= %d AND ROW_ID > %d");
        int offset = pageNum * pageSize;
        return String.format(sqlBuilder.toString(), offset + pageSize, offset);
    }
}
