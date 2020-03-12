package cn.hs.ant.core.adapter.dialect;

public interface Dialect {

    /**
     * 生成SQL语句
     * @param sql
     * @param pageSize
     * @param pageNum
     * @return
     */
    String getPageSql(String sql, int pageSize, int pageNum);

}
