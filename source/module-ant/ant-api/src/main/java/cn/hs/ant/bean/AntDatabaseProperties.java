package cn.hs.ant.bean;

/**
 * ANT Database
 * @author swt
 */
public class AntDatabaseProperties extends AntProperties{

	/**
	 * 抽取SQL
	 */
	public static String QUERY_SQL = "query.sql";

	/**
	 * 目标表名
	 */
	public static String TARGET_ENDPOINT_TABLE_NAME = "target.endpoint.table.name";

	/**
	 * 转换字段前缀
	 */
	public static String DATASOURCE_SOURCE_COLUMN_PREFIX = "extract.transform.column.";

	/**
	 * 源数据源前缀
	 */
	public static String SOURCE_ENDPOINT_DATASOURCE_PREFIX = "source.endpoint.datasource.";

	/**
	 * 目标数据源前缀
	 */
	public static String TARGET_ENDPOINT_DATASOURCE_PREFIX = "target.endpoint.datasource.";

}
