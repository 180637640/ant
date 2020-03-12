package cn.hs.ant.core.adapter.dialect;

public class Db2Dialect implements Dialect {
    @Override
    public String getPageSql(String sql, int pageSize, int pageNum) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT * FROM (SELECT TMP_PAGE.*,ROWNUMBER() OVER() AS ROW_ID FROM ( ");
        sqlBuilder.append(sql);
        sqlBuilder.append(" ) AS TMP_PAGE) TMP_PAGE WHERE ROW_ID BETWEEN %d AND %d");
        int offset = pageNum * pageSize;
        return String.format(sqlBuilder.toString(), offset + pageSize, offset);
    }
}
